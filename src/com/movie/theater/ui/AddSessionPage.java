/*
 * Created by JFormDesigner on Sun May 02 19:35:10 AMT 2021
 */

package com.movie.theater.ui;

import com.movie.theater.exception.OverlappingException;
import com.movie.theater.model.Movie;
import com.movie.theater.model.MovieSession;
import com.movie.theater.service.MovieManager;
import com.movie.theater.service.SessionManager;
import com.toedter.calendar.JDateChooser;
import lu.tudor.santec.jtimechooser.JTimeChooser;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Asya
 */
public class AddSessionPage extends JFrame {

    private static final MovieManager movieManager = MovieManager.getMovieManager();
    private static final SessionManager sessionManager = SessionManager.getSessionManager();
    private String currentMovie;
    private boolean isValidPrice = false;
    private boolean isValidDuration = false;

    public AddSessionPage() {
        initComponents();
        List<String> movies = movieManager.getMovieList().stream().map(Movie::getName).collect(Collectors.toList());
        addValidation();
        dateField.setMinSelectableDate(new Date());
        dateField.getDateEditor().setEnabled(false);
        durationField.setToolTipText("Format: hour:minute");
        priceField.setToolTipText("100 to 9990");
        movieField.setModel(new DefaultComboBoxModel(movies.toArray()));
        pack();
    }

    public AddSessionPage(Movie currentMovie) {
        this();
        movieField.setSelectedItem(currentMovie.getName());
    }

    public void addValidation() {
        durationField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validate();
            }

            private void validate() {
                if (durationField.getText().matches("[0-9]{1,2}:[0-9]{2}")) {
                    durationField.setBackground(Color.WHITE);
                    isValidDuration = true;
                } else {
                    durationField.setBackground(new Color(255, 153, 153));
                    isValidDuration = false;
                }
            }

        });
        priceField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validate();
            }

            private void validate() {
                if (!priceField.getText().matches("^[0-9][0-9]{2,3}0")) {
                    priceField.setBackground(new Color(255, 153, 153));
                    isValidPrice = false;
                } else {
                    priceField.setBackground(Color.WHITE);
                    isValidPrice = true;
                }
            }
        });

    }

    private void makeSessionFromText() {
        String stringPrice = priceField.getText();
        String duration = durationField.getText();
        if (!isValidDuration) {
            JOptionPane.showMessageDialog(this, "Duration must be in format hh:mm, for example 2:30");
        }

        if (!isValidPrice) {
            JOptionPane.showMessageDialog(this, "Price must be an integer from 100 to 9990");
        }
        int price = Integer.parseInt(stringPrice);
        Date date = dateField.getDate();
        LocalDate localDate = Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        int hour = timeField.getHours();
        int minute = timeField.getMinutes();
        LocalDateTime movieStartTime = LocalDateTime.of(localDate, LocalTime.of(hour, minute));
        Movie movie = movieManager.getMovieList()
                .stream()
                .filter(m -> m.getName()
                        .equals(movieField.getSelectedItem().toString())).findFirst()
                .get();


        MovieSession session = new MovieSession(movie, movieStartTime, Duration.ofMinutes(2), price);
        System.out.println(session);
        try {
            sessionManager.addSession(session);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OverlappingException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    private void submitButtonActionPerformed(ActionEvent e) {

        makeSessionFromText();
        dispose();
    }

    private void addAnotherSessionActionPerformed(ActionEvent e) {
        makeSessionFromText();
        if (isValidPrice && isValidDuration) {
            durationField.setText("");
            priceField.setText("");
            dateField.setDate(null);
            timeField.getTimeField().setText("00:00:00");
            durationField.setBackground(Color.WHITE);
            priceField.setBackground(Color.WHITE);
        }
    }

    private void sessionsMenuActionPerformed(ActionEvent e) {
        SessionsPage sessions = new SessionsPage();
        sessions.pack();
        sessions.setVisible(true);
        dispose();
    }

    private void moviesMenuActionPerformed(ActionEvent e) {
        MoviesPage movies = new MoviesPage();
        movies.pack();
        movies.setVisible(true);
        dispose();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Asya
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        moviesMenu = new JMenuItem();
        sessionsMenu = new JMenuItem();
        enterDetails = new JLabel();
        movieNameText = new JLabel();
        dateText = new JLabel();
        timeText = new JLabel();
        priceText = new JLabel();
        movieField = new JComboBox();
        priceField = new JTextField();
        dateField = new JDateChooser();
        timeField = new JTimeChooser();
        addAnotherSession = new JButton();
        submitButton = new JButton();
        durationText = new JLabel();
        durationField = new JTextField();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/com/movie/theater/icons/movie-3.png")).getImage());
        var contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("Main Menu");

                //---- moviesMenu ----
                moviesMenu.setText("Movies");
                moviesMenu.addActionListener(e -> moviesMenuActionPerformed(e));
                menu1.add(moviesMenu);

                //---- sessionsMenu ----
                sessionsMenu.setText("Sessions");
                sessionsMenu.addActionListener(e -> sessionsMenuActionPerformed(e));
                menu1.add(sessionsMenu);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //---- enterDetails ----
        enterDetails.setText("Please enter the session details");
        enterDetails.setFont(new Font("Roboto Light", Font.PLAIN, 20));

        //---- movieNameText ----
        movieNameText.setText("Movie Name");

        //---- dateText ----
        dateText.setText("Date");

        //---- timeText ----
        timeText.setText("Time");

        //---- priceText ----
        priceText.setText("Price");

        //---- addAnotherSession ----
        addAnotherSession.setText("Submit and add another session");
        addAnotherSession.addActionListener(e -> addAnotherSessionActionPerformed(e));

        //---- submitButton ----
        submitButton.setText("Submit");
        submitButton.addActionListener(e -> submitButtonActionPerformed(e));

        //---- durationText ----
        durationText.setText("Duration");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addContainerGap(191, Short.MAX_VALUE)
                                .addComponent(enterDetails, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
                                .addGap(169, 169, 169))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(218, 218, 218)
                                                .addComponent(addAnotherSession))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(293, 293, 293)
                                                .addComponent(submitButton)))
                                .addContainerGap(226, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                .addComponent(durationText, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addComponent(timeText, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(dateText, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(movieNameText, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(priceText, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(priceField, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(movieField, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dateField, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(timeField, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(durationField, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
                                                .addGap(107, 107, 107))))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(enterDetails)
                                .addGap(32, 32, 32)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(movieNameText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(movieField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(dateText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(timeText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(durationText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(durationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(priceText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(priceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(submitButton)
                                .addGap(18, 18, 18)
                                .addComponent(addAnotherSession)
                                .addGap(33, 33, 33))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Asya
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem moviesMenu;
    private JMenuItem sessionsMenu;
    private JLabel enterDetails;
    private JLabel movieNameText;
    private JLabel dateText;
    private JLabel timeText;
    private JLabel priceText;
    private JComboBox movieField;
    private JTextField priceField;
    private JDateChooser dateField;
    private JTimeChooser timeField;
    private JButton addAnotherSession;
    private JButton submitButton;
    private JLabel durationText;
    private JTextField durationField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public static void main(String[] args) {
        AddSessionPage newPage = new AddSessionPage();
        newPage.setVisible(true);
    }
}
