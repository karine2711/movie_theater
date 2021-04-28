package com.movie.theater.ui;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
/*
 * Created by JFormDesigner on Sun Apr 25 23:54:07 AMT 2021
 */


/**
 * @author Asya
 */
public class AddMoviePage extends JFrame {
    public AddMoviePage() {
        initComponents();
    }

    private void addSessionActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Asya
        menuBar1 = new JMenuBar();
        mainMenu = new JMenu();
        movies = new JMenu();
        sessions = new JMenu();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        addSession = new JButton();
        scrollPane2 = new JScrollPane();
        textArea2 = new JTextArea();
        movieName = new JLabel();
        dirName = new JLabel();
        movieDetails = new JLabel();
        dirSurname = new JLabel();
        scrollPane3 = new JScrollPane();
        textArea3 = new JTextArea();
        genre = new JLabel();
        scrollPane4 = new JScrollPane();
        textArea4 = new JTextArea();
        submit = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== mainMenu ========
            {
                mainMenu.setText("Main Menu");
            }
            menuBar1.add(mainMenu);

            //======== movies ========
            {
                movies.setText("Movies");
            }
            menuBar1.add(movies);

            //======== sessions ========
            {
                sessions.setText("Sessions");
            }
            menuBar1.add(sessions);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea1);
        }

        //---- addSession ----
        addSession.setText("Add a session for this movie");
        addSession.addActionListener(e -> addSessionActionPerformed(e));

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(textArea2);
        }

        //---- movieName ----
        movieName.setText("movie.Movie Name");

        //---- dirName ----
        dirName.setText("movie.Director Name");

        //---- movieDetails ----
        movieDetails.setText("Please enter the movie details");
        movieDetails.setFont(new Font("Roboto Light", Font.PLAIN, 20));

        //---- dirSurname ----
        dirSurname.setText("movie.Director Surname");

        //======== scrollPane3 ========
        {
            scrollPane3.setViewportView(textArea3);
        }

        //---- genre ----
        genre.setText("movie.Genre");

        //======== scrollPane4 ========
        {
            scrollPane4.setViewportView(textArea4);
        }

        //---- submit ----
        submit.setText("Submit");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 160, Short.MAX_VALUE)
                    .addComponent(movieDetails)
                    .addGap(155, 155, 155))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(122, 122, 122)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(movieName)
                                .addComponent(dirName)
                                .addComponent(dirSurname)
                                .addComponent(genre))
                            .addGap(100, 100, 100)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                                .addComponent(scrollPane1)
                                .addComponent(scrollPane3)
                                .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(250, 250, 250)
                            .addComponent(submit))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(184, 184, 184)
                            .addComponent(addSession)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(movieDetails)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(movieName))
                            .addGap(18, 18, 18)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(dirName))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(dirSurname)
                        .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(genre)
                        .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(36, 36, 36)
                    .addComponent(submit)
                    .addGap(33, 33, 33)
                    .addComponent(addSession)
                    .addGap(31, 31, 31))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Asya
    private JMenuBar menuBar1;
    private JMenu mainMenu;
    private JMenu movies;
    private JMenu sessions;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JButton addSession;
    private JScrollPane scrollPane2;
    private JTextArea textArea2;
    private JLabel movieName;
    private JLabel dirName;
    private JLabel movieDetails;
    private JLabel dirSurname;
    private JScrollPane scrollPane3;
    private JTextArea textArea3;
    private JLabel genre;
    private JScrollPane scrollPane4;
    private JTextArea textArea4;
    private JButton submit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        AddMoviePage movie = new AddMoviePage();
        movie.pack();
        movie.setVisible(true);
    }

}
