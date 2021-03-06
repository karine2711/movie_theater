/*
 * Created by JFormDesigner on Sun May 02 19:35:10 AMT 2021
 */

package com.movie.theater.ui;

import com.movie.theater.model.Director;
import com.movie.theater.model.Genre;
import com.movie.theater.model.Movie;
import com.movie.theater.service.MovieManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;

/**
 * @author Asya
 */
public class AddMoviePage extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Asya
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem moviesMenu;
    private JMenuItem sessionsMenu;
    private JLabel enterDetails;
    private JLabel movieNameText;
    private JLabel dirNameText;
    private JLabel dirSurnameText;
    private JLabel genreText;
    private JButton addAnotherMovie;
    private JButton submitButton;
    private JButton addSession;
    private JTextField movieNameField;
    private JTextField dirSurnameField;
    private JTextField dirNameField;
    private JComboBox genreField;
    private JButton upload;
    private JLabel genreText2;
    private boolean movieExists;

    public AddMoviePage() {
        initComponents();
        addValidation();
        genreField.setModel(new DefaultComboBoxModel(Genre.values()));
    }

    public void addValidation() {
        movieNameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validate();
            }

            private void validate() {
                if (MovieManager.getMovieManager().exists(movieNameField.getText())) {
                    movieNameField.setBackground(new Color(255, 153, 153));
                    movieExists = true;
                } else {
                    movieNameField.setBackground(Color.WHITE);
                    movieExists = false;
                }
            }
        });

    }


    public static void main(String[] args) {
        AddMoviePage newPage = new AddMoviePage();
        newPage.setVisible(true);
    }

    private Movie makeMovieFromText() {
        String dirSurname = dirSurnameField.getText();
        String dirName = dirNameField.getText();
        String movieName = movieNameField.getText();
        String genre = genreField.getSelectedItem().toString();

        Movie movie = new Movie(movieName, new Director(dirName, dirSurname), Genre.valueOf(genre.toUpperCase()));

        MovieManager manager = MovieManager.getMovieManager();
        try {
            manager.addMovie(movie);
        } catch (Exception x) {
            System.out.println(x.getMessage());
        }

        return movie;
    }

    private void submitButtonActionPerformed(ActionEvent e) {
        if (movieExists) {
            JOptionPane.showMessageDialog(this,"Movie already exists!");
            return;
        }
        makeMovieFromText();
        dispose();
    }

    private void sessionsMenuActionPerformed(ActionEvent e) {
        SessionsPage sessions = new SessionsPage();
        sessions.pack();
        sessions.setVisible(true);
        dispose();
    }

    private void moviesMenuActionPerformed(ActionEvent e) {
        MoviesPage movies = new MoviesPage();
        movies.pack();
        movies.setVisible(true);
        dispose();
    }

    private void addSessionActionPerformed(ActionEvent e) {
        if (movieExists) {
            JOptionPane.showMessageDialog(this,"Movie already exists!");
            return;
        }
        Movie created = makeMovieFromText();
        AddSessionPage addSession = new AddSessionPage(created);
        addSession.pack();
        addSession.setVisible(true);
        dispose();
    }

    private void addAnotherMovieActionPerformed(ActionEvent e) {
        if (movieExists) {
            JOptionPane.showMessageDialog(this,"Movie already exists!");
            return;
        }
        makeMovieFromText();
        movieNameField.setText("");
        dirNameField.setText("");
        dirSurnameField.setText("");
    }

    private void uploadActionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        String extension = filename.substring(filename.lastIndexOf('.'));
        try {
            Files.copy(f.toPath(), Path.of("src/com/movie/theater/icons/" + movieNameField.getText().replaceAll(" ", "_") + "." + extension.toLowerCase()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, "Failed to upload image");
        }

    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Asya
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        moviesMenu = new JMenuItem();
        sessionsMenu = new JMenuItem();
        enterDetails = new JLabel();
        movieNameText = new JLabel();
        dirNameText = new JLabel();
        dirSurnameText = new JLabel();
        genreText = new JLabel();
        addAnotherMovie = new JButton();
        submitButton = new JButton();
        addSession = new JButton();
        movieNameField = new JTextField();
        dirSurnameField = new JTextField();
        dirNameField = new JTextField();
        genreField = new JComboBox();
        upload = new JButton();
        genreText2 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("Main Menu");

                //---- moviesMenu ----
                moviesMenu.setText("Movies");
                moviesMenu.addActionListener(e -> moviesMenuActionPerformed(e));
                menu1.add(moviesMenu);

                //---- sessionsMenu ----
                sessionsMenu.setText("Sessions");
                sessionsMenu.addActionListener(e -> sessionsMenuActionPerformed(e));
                menu1.add(sessionsMenu);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //---- enterDetails ----
        enterDetails.setText("Please enter the movie detalis");
        enterDetails.setFont(new Font("Roboto Light", Font.PLAIN, 20));

        //---- movieNameText ----
        movieNameText.setText("Movie Name");

        //---- dirNameText ----
        dirNameText.setText("Director Name");

        //---- dirSurnameText ----
        dirSurnameText.setText("Director Surname");

        //---- genreText ----
        genreText.setText("Genre");

        //---- addAnotherMovie ----
        addAnotherMovie.setText("Submit and add another movie");
        addAnotherMovie.addActionListener(e -> addAnotherMovieActionPerformed(e));

        //---- submitButton ----
        submitButton.setText("Submit");
        submitButton.addActionListener(e -> submitButtonActionPerformed(e));

        //---- addSession ----
        addSession.setText("Submit and add a session for this movie");
        addSession.addActionListener(e -> addSessionActionPerformed(e));

        //---- upload ----
        upload.setText("Choose photo");
        upload.addActionListener(e -> uploadActionPerformed(e));

        //---- genreText2 ----
        genreText2.setText("Upload banner");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addContainerGap(191, Short.MAX_VALUE)
                                .addComponent(enterDetails, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
                                .addGap(169, 169, 169))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(dirSurnameText, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(genreText, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dirNameText, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(movieNameText, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(genreText2, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(movieNameField, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                                .addComponent(dirSurnameField, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                                .addComponent(dirNameField, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                                .addComponent(genreField, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                                        .addComponent(upload))
                                .addGap(124, 124, 124))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(230, 230, 230)
                                                .addComponent(addAnotherMovie))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(196, 196, 196)
                                                .addComponent(addSession))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(299, 299, 299)
                                                .addComponent(submitButton)))
                                .addContainerGap(200, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(enterDetails)
                                .addGap(32, 32, 32)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(movieNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(movieNameText, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(dirNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dirNameText, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(dirSurnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dirSurnameText, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(genreField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(genreText, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(genreText2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(upload))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addComponent(submitButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addAnotherMovie)
                                .addGap(18, 18, 18)
                                .addComponent(addSession)
                                .addGap(15, 15, 15))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}
