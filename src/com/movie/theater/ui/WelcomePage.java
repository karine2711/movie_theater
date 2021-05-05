package com.movie.theater.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


/*
 * Created by JFormDesigner on Sat Apr 24 22:19:48 AMT 2021
 */


// Filter movie.Movie Filter filter()
// Filterer --- filer(List<Filter> f .

/**
 * @author Asya
 */
public class WelcomePage extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Asya
    private JButton mainMenu;
    private JLabel welcome2;
    private JLabel label1;
    private JLabel welcome3;

    public WelcomePage() {
        initComponents();
    }

    public static void main(String[] args) {
        WelcomePage welcome = new WelcomePage();
        welcome.pack();
        welcome.setVisible(true);
//
//        try {
//            URL resource = welcome.getClass().getResource("/Users/asyakhachatryan/Desktop/movie_theater/src/com/movie/theater/icons/movie-3.png");
//        BufferedImage image = ImageIO.read(resource);
//        welcome.setIconImage(image);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private void mainMenuActionPerformed(ActionEvent e) {
        MainMenuPage menu = new MainMenuPage();
        menu.pack();
        menu.setVisible(true);
        dispose();

    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Asya
        mainMenu = new JButton();
        welcome2 = new JLabel();
        label1 = new JLabel();
        welcome3 = new JLabel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/com/movie/theater/icons/movie-3.png")).getImage());
        var contentPane = getContentPane();

        //---- mainMenu ----
        mainMenu.setText("Main Menu");
        mainMenu.addActionListener(e -> mainMenuActionPerformed(e));

        //---- welcome2 ----
        welcome2.setText("WELCOME TO MOVIE THEATER");
        welcome2.setFont(new Font("Roboto Light", Font.PLAIN, 20));
        welcome2.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/com/movie/theater/icons/movie-3.png")));
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        //---- welcome3 ----
        welcome3.setFont(new Font("Roboto Light", Font.PLAIN, 20));
        welcome3.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(251, 251, 251)
                                                .addComponent(label1))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(228, 228, 228)
                                                .addComponent(mainMenu))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(200, 200, 200)
                                                .addComponent(welcome3, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(208, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addGap(0, 118, Short.MAX_VALUE)
                                .addComponent(welcome2, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addContainerGap(74, Short.MAX_VALUE)
                                .addComponent(label1)
                                .addGap(48, 48, 48)
                                .addComponent(welcome2)
                                .addGap(18, 18, 18)
                                .addComponent(welcome3)
                                .addGap(30, 30, 30)
                                .addComponent(mainMenu)
                                .addGap(134, 134, 134))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


}
