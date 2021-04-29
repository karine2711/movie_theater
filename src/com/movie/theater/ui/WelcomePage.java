package com.movie.theater.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Sat Apr 24 22:19:48 AMT 2021
 */


// Filter movie.Movie Filter filter()
// Filterer --- filer(List<Filter> f .
/**
 * @author Asya
 */
public class WelcomePage extends JFrame {
    public WelcomePage() {
        initComponents();
    }

    private void mainMenuActionPerformed(ActionEvent e) {
        MainMenuPage menu = new MainMenuPage();
        menu.pack();
        menu.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Asya
        mainMenu = new JButton();
        welcome = new JLabel();

        //======== this ========
        var contentPane = getContentPane();

        //---- mainMenu ----
        mainMenu.setText("Main Menu");
        mainMenu.addActionListener(e -> mainMenuActionPerformed(e));

        //---- welcome ----
        welcome.setText("Welcome to movie.Movie Theater");
        welcome.setFont(new Font("Roboto Light", Font.PLAIN, 20));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(190, 190, 190)
                            .addComponent(mainMenu))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(113, 113, 113)
                            .addComponent(welcome, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(118, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(125, 125, 125)
                    .addComponent(welcome, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                    .addGap(84, 84, 84)
                    .addComponent(mainMenu)
                    .addContainerGap(104, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());

        //TODO: JScrollPane
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Asya
    private JButton mainMenu;
    private JLabel welcome;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        WelcomePage welcome = new WelcomePage();
        welcome.pack();
        welcome.setVisible(true);
new WelcomePage().initComponents();
    }


}
