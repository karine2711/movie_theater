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
public class AddSessionPage extends JFrame {
    public AddSessionPage() {
        initComponents();
    }

    private void menu1ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Asya
        menuBar1 = new JMenuBar();
        mainMenu = new JMenu();
        sessions = new JMenu();
        movies = new JMenu();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
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
        button2 = new JButton();
        comboBox1 = new JComboBox();

        //======== this ========
        var contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== mainMenu ========
            {
                mainMenu.setText("Main Menu");
                mainMenu.addActionListener(e -> menu1ActionPerformed(e));
            }
            menuBar1.add(mainMenu);

            //======== sessions ========
            {
                sessions.setText("Sessions");
            }
            menuBar1.add(sessions);

            //======== movies ========
            {
                movies.setText("Movies");
            }
            menuBar1.add(movies);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea1);
        }

        //---- addAnotherSession ----
        addAnotherSession.setText("Add another session");

        //---- movieName ----
        movieName.setText("movie.Movie Name");

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

        //---- button2 ----
        button2.setText("Submit");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(209, 209, 209)
                            .addComponent(addAnotherSession))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(122, 122, 122)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(movieName)
                                .addComponent(date)
                                .addComponent(time)
                                .addComponent(price))
                            .addGap(100, 100, 100)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(scrollPane1)
                                .addComponent(scrollPane3)
                                .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(comboBox1)
                                    .addGap(2, 2, 2))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(250, 250, 250)
                            .addComponent(button2))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(enterDetails)
                            .addGap(2, 2, 2)))
                    .addContainerGap(150, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap(33, Short.MAX_VALUE)
                    .addComponent(enterDetails)
                    .addGap(27, 27, 27)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(movieName)
                                .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(date))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(time)
                        .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(price)
                        .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(36, 36, 36)
                    .addComponent(button2)
                    .addGap(36, 36, 36)
                    .addComponent(addAnotherSession)
                    .addGap(28, 28, 28))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Asya
    private JMenuBar menuBar1;
    private JMenu mainMenu;
    private JMenu sessions;
    private JMenu movies;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
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
    private JButton button2;
    private JComboBox comboBox1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        AddSessionPage session = new AddSessionPage();
        session.pack();
        session.setVisible(true);
    }

}
