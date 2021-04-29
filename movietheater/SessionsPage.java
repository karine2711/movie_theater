import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/*
 * Created by JFormDesigner on Sat Apr 24 22:34:08 AMT 2021
 */


/**
 * @author Asya
 */
public class SessionsPage extends JFrame {
    public SessionsPage() {
        initComponents();
    }

    private void mainMenuActionPerformed(ActionEvent e) {
        MainMenuPage menu = new MainMenuPage();
        menu.pack();
        menu.setVisible(true);
    }

    private void addMovieActionPerformed(ActionEvent e) {
        AddSessionPage addSession = new AddSessionPage();
        addSession.pack();
        addSession.setVisible(true);
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Asya
        mainMenu = new JButton();
        movies = new JLabel();
        addMovie = new JButton();
        filter = new JLabel();

        //======== this ========
        var contentPane = getContentPane();

        //---- mainMenu ----
        mainMenu.setText("Main Menu");
        mainMenu.setFont(new Font("Roboto Light", Font.PLAIN, 13));
        mainMenu.addActionListener(e -> button1ActionPerformed(e));

        //---- movies ----
        movies.setText("Sessions");
        movies.setFont(new Font("Roboto Light", Font.PLAIN, 20));

        //---- addMovie ----
        addMovie.setText("Add a session");
        addMovie.setFont(new Font("Roboto Light", Font.PLAIN, 13));
        addMovie.addActionListener(e -> addMovieActionPerformed(e));

        //---- filter ----
        filter.setText("Filter");
        filter.setFont(new Font("Roboto Light", Font.BOLD | Font.ITALIC, 13));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
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
                            .addComponent(filter)))
                    .addContainerGap(198, Short.MAX_VALUE))
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
                    .addContainerGap(185, Short.MAX_VALUE))
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
