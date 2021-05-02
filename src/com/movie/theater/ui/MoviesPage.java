package com.movie.theater.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Sat Apr 24 22:34:08 AMT 2021
 */



/**
 * @author Asya
 */
public class MoviesPage extends JFrame {
    public MoviesPage() {
        initComponents();
    }

    private void mainMenuActionPerformed(ActionEvent e) {
        MainMenuPage menu = new MainMenuPage();
        menu.pack();
        menu.setVisible(true);
        dispose();
    }

    private void addMovieActionPerformed(ActionEvent e) {
        AddMoviePageOld addMovie = new AddMoviePageOld();
        addMovie.pack();
        addMovie.setVisible(true);
        dispose();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Asya
        mainMenu = new JButton();
        movies = new JLabel();
        addMovie = new JButton();
        filter = new JLabel();
        comboBox1 = new JComboBox();
        comboBox2 = new JComboBox();
        checkBox1 = new JCheckBox();

        //======== this ========
        var contentPane = getContentPane();

        //---- mainMenu ----
        mainMenu.setText("Main Menu");
        mainMenu.setFont(new Font("Roboto Light", Font.PLAIN, 13));
        mainMenu.addActionListener(e -> mainMenuActionPerformed(e));

        //---- movies ----
        movies.setText("Movies");
        movies.setFont(new Font("Roboto Light", Font.PLAIN, 20));

        //---- addMovie ----
        addMovie.setText("Add a movie");
        addMovie.setFont(new Font("Roboto Light", Font.PLAIN, 13));
        addMovie.addActionListener(e -> addMovieActionPerformed(e));

        //---- filter ----
        filter.setText("Filter");
        filter.setFont(new Font("Roboto Light", Font.BOLD | Font.ITALIC, 13));

        //---- checkBox1 ----
        checkBox1.setText("text");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkBox1)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(addMovie)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(mainMenu)
                                    .addGap(111, 111, 111)
                                    .addComponent(movies))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addComponent(filter)))))
                    .addContainerGap(215, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(mainMenu)
                        .addComponent(movies))
                    .addGap(30, 30, 30)
                    .addComponent(addMovie)
                    .addGap(36, 36, 36)
                    .addComponent(filter)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(checkBox1)
                    .addContainerGap(68, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Asya
    private JButton mainMenu;
    private JLabel movies;
    private JButton addMovie;
    private JLabel filter;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JCheckBox checkBox1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
