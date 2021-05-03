/*
 * Created by JFormDesigner on Sun May 02 19:35:10 AMT 2021
 */

package com.movie.theater.ui;

import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.GroupLayout;

import com.movie.theater.model.Director;
import com.movie.theater.model.Genre;
import com.movie.theater.model.Movie;
import com.movie.theater.model.MovieSession;
import com.movie.theater.service.MovieManager;
import com.toedter.calendar.*;
import lu.tudor.santec.jtimechooser.*;

/**
 * @author Asya
 */
public class AddSessionPage extends JFrame {

    private static final MovieManager movieManager = MovieManager.getMovieManager();
    private String currentMovie;

    public AddSessionPage() {
        initComponents();
        List<String> movies = movieManager.getMoveList().stream().map(Movie::getName).collect(Collectors.toList());
        movieField.setModel(new DefaultComboBoxModel(movies.toArray()));
        pack();
    }

    public AddSessionPage(Movie currentMovie) {
        this();
        movieField.setSelectedItem(currentMovie.getName());
    }

    private void makeSessionFromText(){
        int price = Integer.parseInt(priceField.getText());
        int duration = Integer.parseInt(durationField.getText());


        Duration.ofHours().plus(Duration.ofMinutes())
        LocalDateTime.of()


//        MovieSession session = new MovieSession(movie, date, Duration.ofMinutes(duration), price);


        try {

        } catch (Exception x){
            System.out.println(x.getMessage());
        }

    }

    private void submitButtonActionPerformed(ActionEvent e) {
        MoviesPage sessions = new MoviesPage();
        sessions.pack();
        sessions.setVisible(true);
        dispose();

        makeSessionFromText();

    }

    private void addAnotherSessionActionPerformed(ActionEvent e) {
        durationField.setText("");
        priceField.setText("");
        dateField.setDate(null);
        timeField.getTimeField().setText("00:00:00");

        makeSessionFromText();
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
