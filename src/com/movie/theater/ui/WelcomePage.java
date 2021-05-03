package com.movie.theater.ui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
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
        dispose();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Asya
        mainMenu = new JButton();
        welcome = new JLabel();
        welcome2 = new JLabel();
        label1 = new JLabel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/com/movie/theater/icons/movie-3.png")).getImage());
        var contentPane = getContentPane();

        //---- mainMenu ----
        mainMenu.setText("Main Menu");
        mainMenu.addActionListener(e -> mainMenuActionPerformed(e));

        //---- welcome ----
        welcome.setText("Hello Miranda,");
        welcome.setFont(new Font("Roboto Light", Font.PLAIN, 20));

        //---- welcome2 ----
        welcome2.setText("Welcome to \"And Action!\"");
        welcome2.setFont(new Font("Roboto Light", Font.PLAIN, 20));

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/com/movie/theater/icons/movie-3.png")));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(220, 220, 220)
                            .addComponent(mainMenu))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(206, 206, 206)
                            .addComponent(welcome, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(159, 159, 159)
                            .addComponent(welcome2, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(242, 242, 242)
                            .addComponent(label1)))
                    .addContainerGap(139, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(47, Short.MAX_VALUE)
                    .addComponent(label1)
                    .addGap(18, 18, 18)
                    .addComponent(welcome, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(welcome2, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(mainMenu)
                    .addGap(106, 106, 106))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Asya
    private JButton mainMenu;
    private JLabel welcome;
    private JLabel welcome2;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        WelcomePage welcome = new WelcomePage();
        welcome.pack();
        welcome.setVisible(true);
//
//
//        try {
//            URL resource = welcome.getClass().getResource("/Users/asyakhachatryan/Desktop/movie_theater/src/com/movie/theater/icons/movie-3.png");
//        BufferedImage image = ImageIO.read(resource);
//        welcome.setIconImage(image);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


}
