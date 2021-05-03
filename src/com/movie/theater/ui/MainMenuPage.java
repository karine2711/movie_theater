package com.movie.theater.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Sun Apr 25 23:01:07 AMT 2021
 */



/**
 * @author Asya
 */
public class MainMenuPage extends JFrame {
    public MainMenuPage() {
        initComponents();
    }

    private void moviesActionPerformed(ActionEvent e) {
        MoviesPage movies = new MoviesPage();
        movies.pack();
        movies.setVisible(true);
        dispose();
    }

    private void sessionsActionPerformed(ActionEvent e) {
        SessionsPage sessions = new SessionsPage();
        sessions.pack();
        sessions.setVisible(true);
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Asya
        movies = new JButton();
        sessions = new JButton();
        movieTheater = new JLabel();
        label1 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();

        //---- movies ----
        movies.setText("Movies");
        movies.setFont(new Font("Roboto Light", Font.PLAIN, 13));
        movies.addActionListener(e -> moviesActionPerformed(e));

        //---- sessions ----
        sessions.setText("Sessions");
        sessions.setFont(new Font("Roboto Light", Font.PLAIN, 13));
        sessions.addActionListener(e -> sessionsActionPerformed(e));

        //---- movieTheater ----
        movieTheater.setText("Let's go to...");
        movieTheater.setFont(new Font("Roboto Light", Font.PLAIN, 20));

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/com/movie/theater/icons/1415702.png")));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(208, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(sessions, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(movies, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                        .addComponent(movieTheater, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
                    .addGap(191, 191, 191))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(232, 232, 232)
                    .addComponent(label1)
                    .addContainerGap(246, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addComponent(movieTheater)
                    .addGap(32, 32, 32)
                    .addComponent(movies, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                    .addGap(35, 35, 35)
                    .addComponent(sessions, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(66, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Asya
    private JButton movies;
    private JButton sessions;
    private JLabel movieTheater;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
