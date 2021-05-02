package com.movie.theater.ui;

import com.movie.theater.model.Movie;
import com.movie.theater.service.MovieManager;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
/*
 * Created by JFormDesigner on Sun Apr 25 23:54:07 AMT 2021
 */


/**
 * @author Asya
 */
public class AddSessionPage extends JFrame {
    private static final MovieManager movieManager=MovieManager.getMovieManager();
    private String currentMovie;
    public AddSessionPage() {
        initComponents();
        List<String> movies=  movieManager.getMoveList().stream().map(Movie::getName).collect(Collectors.toList());
        moviesDropdown.setModel(new DefaultComboBoxModel(movies.toArray()));
        pack();
    }

    public AddSessionPage(Movie currentMovie) {
        this();
        moviesDropdown.setSelectedItem(currentMovie.getName());
    }

    private void menu1ActionPerformed(ActionEvent e) {
        MainMenuPage menu = new MainMenuPage();
        menu.pack();
        menu.setVisible(true);
        dispose();
    }

    private void sessionMenuActionPerformed(ActionEvent e) {
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
        mainMenu = new JMenu();
        sessionMenu = new JMenuItem();
        moviesMenu = new JMenuItem();
        addAnotherSession = new JButton();
        movieName = new JLabel();
        date = new JLabel();
        enterDetails = new JLabel();
        time = new JLabel();
        scrollPane3 = new JScrollPane();
        textArea3 = new JTextArea();
        price = new JLabel();
        scrollPane4 = new JScrollPane();
        textArea4 = new JTextArea();
        submitButton = new JButton();
        moviesDropdown = new JComboBox();
        formattedTextField2 = new JFormattedTextField();

        //======== this ========
        var contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== mainMenu ========
            {
                mainMenu.setText("Main Menu");
                mainMenu.addActionListener(e -> menu1ActionPerformed(e));

                //---- sessionMenu ----
                sessionMenu.setText("Sessions");
                sessionMenu.addActionListener(e -> sessionMenuActionPerformed(e));
                mainMenu.add(sessionMenu);

                //---- moviesMenu ----
                moviesMenu.setText("Movies");
                moviesMenu.addActionListener(e -> moviesMenuActionPerformed(e));
                mainMenu.add(moviesMenu);
            }
            menuBar1.add(mainMenu);
        }
        setJMenuBar(menuBar1);

        //---- addAnotherSession ----
        addAnotherSession.setText("Submit and add another session");

        //---- movieName ----
        movieName.setText("Movie Name");

        //---- date ----
        date.setText("Date");

        //---- enterDetails ----
        enterDetails.setText("Please enter the session details");
        enterDetails.setFont(new Font("Roboto Light", Font.PLAIN, 20));

        //---- time ----
        time.setText("Time");

        //======== scrollPane3 ========
        {
            scrollPane3.setViewportView(textArea3);
        }

        //---- price ----
        price.setText("Price");

        //======== scrollPane4 ========
        {
            scrollPane4.setViewportView(textArea4);
        }

        //---- submitButton ----
        submitButton.setText("Submit");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(122, 122, 122)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(movieName)
                                .addComponent(date)
                                .addComponent(time)
                                .addComponent(price))
                            .addGap(100, 100, 100)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(scrollPane3)
                                .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(formattedTextField2, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(moviesDropdown))
                                    .addGap(2, 2, 2))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(135, 135, 135)
                            .addComponent(enterDetails))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(239, 239, 239)
                            .addComponent(submitButton))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(160, 160, 160)
                            .addComponent(addAnotherSession)))
                    .addContainerGap(150, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap(51, Short.MAX_VALUE)
                    .addComponent(enterDetails)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(movieName)
                                .addComponent(moviesDropdown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(48, 48, 48))
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(date)
                            .addComponent(formattedTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(time)
                        .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(price)
                        .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(40, 40, 40)
                    .addComponent(submitButton)
                    .addGap(31, 31, 31)
                    .addComponent(addAnotherSession)
                    .addGap(29, 29, 29))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Asya
    private JMenuBar menuBar1;
    private JMenu mainMenu;
    private JMenuItem sessionMenu;
    private JMenuItem moviesMenu;
    private JButton addAnotherSession;
    private JLabel movieName;
    private JLabel date;
    private JLabel enterDetails;
    private JLabel time;
    private JScrollPane scrollPane3;
    private JTextArea textArea3;
    private JLabel price;
    private JScrollPane scrollPane4;
    private JTextArea textArea4;
    private JButton submitButton;
    private JComboBox moviesDropdown;
    private JFormattedTextField formattedTextField2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        AddSessionPage session = new AddSessionPage();
        session.pack();
        session.setVisible(true);
    }

}
