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
    }

    private void sessionsActionPerformed(ActionEvent e) {
        SessionsPage sessions = new SessionsPage();
        sessions.pack();
        sessions.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Asya
        movies = new JButton();
        sessions = new JButton();
        movieTheater = new JLabel();

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
        movieTheater.setText("Movie Theater");
        movieTheater.setFont(new Font("Roboto Light", Font.PLAIN, 20));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(209, 209, 209)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(sessions, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                .addComponent(movies, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(201, 201, 201)
                            .addComponent(movieTheater)))
                    .addContainerGap(211, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(92, 92, 92)
                    .addComponent(movieTheater)
                    .addGap(30, 30, 30)
                    .addComponent(movies, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                    .addGap(35, 35, 35)
                    .addComponent(sessions, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(82, Short.MAX_VALUE))
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
