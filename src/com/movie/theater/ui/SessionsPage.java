package com.movie.theater.ui;

import com.movie.theater.model.Genre;
import com.movie.theater.model.Movie;
import com.movie.theater.model.MovieSession;
import com.movie.theater.service.SessionManager;
import com.movie.theater.service.moviefilter.MovieFilterer;
import com.movie.theater.service.moviesessionfilter.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

public class SessionsPage extends JFrame {

    private JButton mainMenuButton;
    private JPanel sessionsPanel;
    JPanel filterPanel;
    JPanel sessions = new JPanel();
    List<SessionFilter> sessionFilters = new ArrayList<>();
    SessionByDateFilter sessionByDateFilter = null;
    SessionByGenreFilter sessionByGenreFilter = null;
    SessionByMovieFilter sessionByMovieFilter = null;
    SessionByPriceFilter sessionByPriceFilter = null;
    SessionManager sessionManager = SessionManager.getSessionManager();


    private static GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public SessionsPage() throws HeadlessException {
        Dimension uidim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setMinimumSize(uidim);
        this.setMaximumSize(uidim);
        this.setPreferredSize(uidim);
        GridBagLayout layout = new GridBagLayout();
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        layout.setConstraints(this.getContentPane(), gridBagConstraints);
        this.getContentPane().setLayout(layout);

        //Movies Page Button
        addMainMenuButton();
        initMainContainer();

        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(3);
        gridLayout.setRows(-1);
        gridLayout.setHgap(50);
        gridLayout.setVgap(50);
        sessions.setLayout(gridLayout);
        JScrollPane scrollPane = new JScrollPane(sessions, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sessionsPanel.add(scrollPane);
//        populateWithSessions(sessionManager.getSessionList());
        this.getContentPane().add(sessionsPanel, gridBagConstraints);

        //movies page end

        ///filter panel
        initFilterPanel();


        this.pack();
        this.setVisible(true);
    }

    private void populateWithSessions(List<MovieSession> sessionsList) {
        sessions.removeAll();
        sessions.setBackground(Color.pink);

        sessionsList.forEach((session) ->
        {
            JPanel moviePanel = new JPanel();
            moviePanel.setOpaque(false);
            Dimension dimension = new Dimension(300, 520);
            moviePanel.setPreferredSize(dimension);
            moviePanel.setMinimumSize(dimension);
            moviePanel.setMaximumSize(dimension);

            moviePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel movieName = new JLabel(session.getMovie().getName(), SwingConstants.CENTER);
            Dimension movieNameDimension = new Dimension(300, 20);
            movieName.setPreferredSize(movieNameDimension);
            movieName.setMinimumSize(movieNameDimension);
            movieName.setMaximumSize(movieNameDimension);
            movieName.setToolTipText(session.getMovie().getName());
            movieName.setFont(new Font(null, Font.PLAIN, 20));
            moviePanel.add(movieName);

            JLabel movieDirector = new JLabel(session.getMovie().getDirector().getFirstName());
            movieDirector.setMinimumSize(movieNameDimension);
            movieDirector.setMaximumSize(movieNameDimension);
            movieDirector.setPreferredSize(movieNameDimension);
            movieDirector.setToolTipText(session.getMovie().getName());
            movieDirector.setOpaque(true);
            movieDirector.setBackground(Color.YELLOW);
            movieDirector.setFont(new Font(null, Font.ITALIC, 16));
            moviePanel.add(movieDirector);

            JLabel genre = new JLabel(session.getMovie().getGenre().toString());
            genre.setMinimumSize(movieNameDimension);
            genre.setMaximumSize(movieNameDimension);
            genre.setPreferredSize(movieNameDimension);
            genre.setToolTipText(session.getMovie().getName());
            genre.setOpaque(true);
            genre.setBackground(Color.YELLOW);
            genre.setFont(new Font(null, Font.ITALIC, 16));
            moviePanel.add(genre);

            JLabel date = new JLabel(String.valueOf(session.getLocalDateTime()));
            date.setMinimumSize(movieNameDimension);
            date.setMaximumSize(movieNameDimension);
            date.setPreferredSize(movieNameDimension);
            date.setToolTipText(session.getMovie().getName());
            date.setOpaque(true);
            date.setBackground(Color.YELLOW);
            date.setFont(new Font(null, Font.ITALIC, 16));
            moviePanel.add(date);

            JLabel price = new JLabel(String.valueOf(session.getPriceForSession()));
            price.setMinimumSize(movieNameDimension);
            price.setMaximumSize(movieNameDimension);
            price.setPreferredSize(movieNameDimension);
            price.setToolTipText(session.getMovie().getName());
            price.setOpaque(true);
            price.setBackground(Color.YELLOW);
            price.setFont(new Font(null, Font.ITALIC, 16));
            moviePanel.add(price);


            JPanel movieFooter = new JPanel();
            movieFooter.setLayout(new FlowLayout(FlowLayout.RIGHT));
            Dimension footerDimension = new Dimension(300, 35);
            movieFooter.setPreferredSize(footerDimension);
            movieFooter.setMinimumSize(footerDimension);
            movieFooter.setMaximumSize(footerDimension);

            JButton deleteButton = new JButton("Delete");
            movieFooter.add(deleteButton);
            moviePanel.add(movieFooter);
            moviePanel.setBackground(Color.orange);

            sessions.add(moviePanel);
        });

        sessions.repaint();
        pack();
    }

    private void initFilterPanel() {
        createFilterPanel();
        addAddSessionButton();
        addFiltersLabel();
        addFiltersChecks();
        filterPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        addFilterButton();
        addResetButton();

    }

    private void addResetButton() {
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener((e) -> {
            sessionFilters.clear();
//            sessionByDateFilter.reset();
//            sessionByGenreFilter.reset();
//            sessionByMovieFilter.reset();
//            sessionByPriceFilter.reset();
//           populateWithMovies(movieManager.getMoveList());
        });
        filterPanel.add(resetButton);

    }

    private void addFilterButton() {
        JButton filterButton = new JButton("Filter");
        filterButton.addActionListener((e) -> {
            sessionFilters.clear();
            sessionFilters.add(sessionByDateFilter);
            sessionFilters.add(sessionByGenreFilter);
            sessionFilters.add(sessionByMovieFilter);
            sessionFilters.add(sessionByPriceFilter);
//            SessionFilterer filterer = new SessionFilterer(sessionManager.getSessionList());
//            List<MovieSession> filteredList = filterer.filter(sessionFilters).getResult();
//            System.out.println(filteredList);
//            populateWithSessions(filteredList);
        });
        filterPanel.add(filterButton);

    }

    private void addFiltersChecks() {
        JPanel filtersChecks = new JPanel();
        filtersChecks.setLayout(new BoxLayout(filtersChecks, BoxLayout.X_AXIS));
//        filtersChecks.add(getGenreFiltersBox());
        filtersChecks.add(Box.createRigidArea(new Dimension(30, 0)));
//        filtersChecks.add(getDirectorsFilterBox());
        Dimension dimension = new Dimension(400, 300);
        filtersChecks.setPreferredSize(dimension);
        filtersChecks.setMinimumSize(dimension);
        filtersChecks.setMaximumSize(dimension);
        filtersChecks.setOpaque(false);
        filterPanel.add(filtersChecks);
    }

//    private JPanel getDateFilterBox() {
//
//        JPanel directorsContainer = new JPanel();
//        directorsContainer.setOpaque(false);
//        directorsContainer.setLayout(new BoxLayout(directorsContainer, BoxLayout.Y_AXIS));
//        JLabel directors = new JLabel("Directors");
//        directors.setForeground(Color.WHITE);
//        directors.setFont(new Font(null, Font.BOLD, 16));
//        directorsContainer.add(directors);
//        Dimension dimension = new Dimension(100, 120);
//        directorFilterBox.setPreferredSize(dimension);
//        directorFilterBox.setMinimumSize(dimension);

//        JPanel directorFilterBox = new JPanel();
//        directorFilterBox.setLayout(new BoxLayout(directorFilterBox, BoxLayout.Y_AXIS));
//
//        directorFilterBox.setAlignmentX(CENTER_ALIGNMENT);
//        directorFilterBox.setOpaque(false);
//        for (Director director : sessionManager.getDirectorList()) {
//            String directorFullName = director.getFullName();
//            JCheckBox genreCheckBox = new JCheckBox(directorFullName);
//            genreCheckBox.addItemListener((ItemEvent e) -> {
//                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    movieByDirectorFilter.addDirector(director);
//                } else {
//                    movieByDirectorFilter.removeDirector(director);
//                }
//                System.out.println(movieByDirectorFilter);
//            });
//            directorFilterBox.add(genreCheckBox);
//        }
//        directorsContainer.add(new JScrollPane(directorFilterBox));
////        filterPanel.add(directorFilterBox);
//        return directorsContainer;

        //TODO from 203 - 223 change
//    }


    private void createFilterPanel() {
        filterPanel = new JPanel();
        filterPanel.setBackground(Color.BLACK);
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 1;
        this.getContentPane().add(filterPanel, gridBagConstraints);
    }

    private void addAddSessionButton() {
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.add(Box.createRigidArea(new Dimension(0, 50)));
//        panel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button = new JButton("Add Session");
        button.addActionListener(e -> {
            AddSessionPage addSessionPage = new AddSessionPage();
            addSessionPage.pack();
            addSessionPage.setVisible(true);
//            dispose();
        });
        button.setAlignmentX(CENTER_ALIGNMENT);
        filterPanel.add(button);
    }


//    private JPanel getGenreFiltersBox() {
//        JPanel genresContainer = new JPanel();
//        genresContainer.setOpaque(false);
//        genresContainer.setLayout(new BoxLayout(genresContainer, BoxLayout.Y_AXIS));
//        JLabel genres = new JLabel("Genres");
//        genres.setForeground(Color.WHITE);
//        genres.setFont(new Font(null, Font.BOLD, 16));
//        genresContainer.add(genres);
//
//
//        JPanel genreFiltersBox = new JPanel();
//        genreFiltersBox.setLayout(new BoxLayout(genreFiltersBox, BoxLayout.Y_AXIS));
//
//        genreFiltersBox.setAlignmentX(CENTER_ALIGNMENT);
//        genreFiltersBox.setOpaque(false);
//        Dimension dimension = new Dimension(100, 120);
//        genreFiltersBox.setPreferredSize(dimension);
//        genreFiltersBox.setMinimumSize(dimension);

//        for (Genre genre : Genre.values()) {
//            String genreName = genre.name().toLowerCase();
//            String firstLetter = String.valueOf(genreName.charAt(0));
//            genreName = genreName.replace(firstLetter, firstLetter.toUpperCase());
//            JCheckBox genreCheckBox = new JCheckBox(genreName);
//            genreCheckBox.addItemListener((ItemEvent e) -> {
//                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    movieByGenreFilter.addGenre(genre);
//                } else {
//                    movieByGenreFilter.removeGenre(genre);
//                }
//                System.out.println(movieByGenreFilter);
//            });
//            genreFiltersBox.add(genreCheckBox);
//        }
//        genresContainer.add(new JScrollPane(genreFiltersBox));
////        filterPanel.add(genreFiltersBox);
//        return genresContainer;

        //TODO 286 - 303 change
//    }

    private void addFiltersLabel() {
        filterPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        JLabel filtersLabel = new JLabel("Filters");
        filtersLabel.setAlignmentX(CENTER_ALIGNMENT);
        filtersLabel.setForeground(Color.white);
        filterPanel.add(filtersLabel);
        this.pack();
        this.setVisible(true);
        filterPanel.add(Box.createRigidArea(new Dimension(0, 50)));
    }


    private void initMainContainer() {
        sessionsPanel = new JPanel();
        sessionsPanel.setBackground(Color.GREEN);
        gridBagConstraints.weightx = 0.9;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;

        sessionsPanel.setLayout(new BoxLayout(sessionsPanel, BoxLayout.Y_AXIS));


        addSessionsLabel();
    }

    private void addSessionsLabel() {
        JLabel sessionsLabel = new JLabel("Sessions");
        Dimension label = new Dimension(sessionsPanel.getWidth() / 2, 500);
        sessionsLabel.setFont(new Font(null, Font.PLAIN, 50));
        sessionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        sessionsLabel.setPreferredSize(label);
//        sessionsLabel.setMaximumSize(label);
//        sessionsLabel.setMinimumSize(label);
        sessionsPanel.add(sessionsLabel);
    }


    private void filterBoxItemStateChanged(ItemEvent e) {
        e.setSource(Genre.values());
        System.out.println("sdsd");
    }

    private void addMainMenuButton() {
        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBackground(Color.RED);
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 0.2;
        this.getContentPane().add(mainMenuButton, gridBagConstraints);

        mainMenuButton.addActionListener((e) -> {
            MainMenuPage menu = new MainMenuPage();
            menu.pack();
            menu.setVisible(true);
            this.dispose();
        });
    }

    public static void main(String[] args) {
        SessionsPage sessionsPage = new SessionsPage();

    }
}
