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

    private void mainMenuActionPerformed(ActionEvent e) {

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
        submit = new JButton();
        genreEnum = new JComboBox();
        button1 = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== mainMenu ========
            {
                mainMenu.setText("Main Menu");
                mainMenu.addActionListener(e -> mainMenuActionPerformed(e));
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
        addSession.setText("Submit and add a session for this movie");
        addSession.addActionListener(e -> addSessionActionPerformed(e));

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(textArea2);
        }

        //---- movieName ----
        movieName.setText("Movie Name");

        //---- dirName ----
        dirName.setText("Director Name");

        //---- movieDetails ----
        movieDetails.setText("Please enter the movie details");
        movieDetails.setFont(new Font("Roboto Light", Font.PLAIN, 20));

        //---- dirSurname ----
        dirSurname.setText("Director Surname");

        //======== scrollPane3 ========
        {
            scrollPane3.setViewportView(textArea3);
        }

        //---- genre ----
        genre.setText("Genre");

        //---- submit ----
        submit.setText("Submit");

        //---- button1 ----
        button1.setText("Submit and add another one");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 150, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(movieDetails)
                            .addGap(155, 155, 155))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(addSession)
                            .addGap(146, 146, 146))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(button1)
                            .addGap(180, 180, 180))))
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
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                .addComponent(scrollPane3)
                                .addComponent(genreEnum, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(250, 250, 250)
                            .addComponent(submit)))
                    .addContainerGap(118, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(movieDetails)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
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
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(genre)
                        .addComponent(genreEnum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(13, 13, 13)
                    .addComponent(submit)
                    .addGap(18, 18, 18)
                    .addComponent(button1)
                    .addGap(14, 14, 14)
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
    private JButton submit;
    private JComboBox genreEnum;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        AddMoviePage movie = new AddMoviePage();
        movie.pack();
        movie.setVisible(true);
    }

}
