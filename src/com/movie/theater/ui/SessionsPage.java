package com.movie.theater.ui;

import com.movie.theater.model.Genre;
import com.movie.theater.model.Movie;
import com.movie.theater.model.MovieSession;
import com.movie.theater.service.SessionManager;
import com.movie.theater.service.moviefilter.MovieFilterer;
import com.movie.theater.service.moviesessionfilter.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SessionsPage extends JFrame {

    private JButton mainMenuButton;
    private JPanel sessionsPanel;
    JPanel filterPanel;
    JPanel sessions = new JPanel();
    List<SessionFilter> sessionFilters = new ArrayList<>();
    SessionByDateFilter sessionByDateFilter = null;
    SessionByGenreFilter sessionByGenreFilter = new SessionByGenreFilter();
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
        gridBagConstraints.weightx = 2;
        gridBagConstraints.weighty = 2;
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

        populateWithSessions(sessionManager.getSessionList());
        this.getContentPane().add(sessionsPanel, gridBagConstraints);

        //movies page end

        ///filter panel
        initFilterPanel();


        this.pack();
        this.setVisible(true);
    }

    private void populateWithSessions(List<MovieSession> sessionsList) {
        sessions.removeAll();
        sessions.setBackground(Color.LIGHT_GRAY);

        sessionsList.forEach((session) ->
        {
            JPanel sessionPanel = new JPanel();
            sessionPanel.setOpaque(false);
            Dimension dimension = new Dimension(300, 520);
            sessionPanel.setPreferredSize(dimension);
            sessionPanel.setMinimumSize(dimension);
            sessionPanel.setMaximumSize(dimension);

            sessionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel movieName = new JLabel(session.getMovie().getName(), SwingConstants.CENTER);
            Dimension movieNameDimension = new Dimension(300, 20);
            movieName.setPreferredSize(movieNameDimension);
            movieName.setMinimumSize(movieNameDimension);
            movieName.setMaximumSize(movieNameDimension);
            movieName.setToolTipText(session.getMovie().getName());
            movieName.setFont(new Font(null, Font.PLAIN, 20));
            sessionPanel.add(movieName);

            JLabel movieDirector = new JLabel(session.getMovie().getDirector().getFirstName());
            movieDirector.setMinimumSize(movieNameDimension);
            movieDirector.setMaximumSize(movieNameDimension);
            movieDirector.setPreferredSize(movieNameDimension);
            movieDirector.setToolTipText(session.getMovie().getName());
            movieDirector.setOpaque(true);
            movieDirector.setBackground(Color.YELLOW);
            movieDirector.setFont(new Font(null, Font.PLAIN, 16));
            sessionPanel.add(movieDirector);

            JLabel genre = new JLabel(session.getMovie().getGenre().toString());
            genre.setMinimumSize(movieNameDimension);
            genre.setMaximumSize(movieNameDimension);
            genre.setPreferredSize(movieNameDimension);
            genre.setToolTipText(session.getMovie().getName());
            genre.setOpaque(true);
            genre.setBackground(Color.YELLOW);
            genre.setFont(new Font(null, Font.PLAIN, 16));
            sessionPanel.add(genre);

            JLabel date = new JLabel(String.valueOf(session.getLocalDateTime()));
            date.setMinimumSize(movieNameDimension);
            date.setMaximumSize(movieNameDimension);
            date.setPreferredSize(movieNameDimension);
            date.setToolTipText(session.getMovie().getName());
            date.setOpaque(true);
            date.setBackground(Color.YELLOW);
            date.setFont(new Font(null, Font.PLAIN, 16));
            sessionPanel.add(date);

            JLabel price = new JLabel(String.valueOf(session.getPriceForSession()));
            price.setMinimumSize(movieNameDimension);
            price.setMaximumSize(movieNameDimension);
            price.setPreferredSize(movieNameDimension);
            price.setToolTipText(session.getMovie().getName());
            price.setOpaque(true);
            price.setBackground(Color.YELLOW);
            price.setFont(new Font(null, Font.PLAIN, 16));
            sessionPanel.add(price);


            JPanel sessionFooter = new JPanel();
            sessionFooter.setLayout(new FlowLayout(FlowLayout.RIGHT));
            Dimension footerDimension = new Dimension(300, 35);
            sessionFooter.setPreferredSize(footerDimension);
            sessionFooter.setMinimumSize(footerDimension);
            sessionFooter.setMaximumSize(footerDimension);

            JButton deleteButton = new JButton("Delete Session");

            deleteButton.addActionListener(e -> {
                try {
                    sessionManager.deleteSession(session);
                sessions.remove(sessionPanel);
                pack();
                } catch (IOException ex){
                    System.out.println(ex.getMessage());
                }
                });

            JButton getTicket = new JButton("Get Tickets");
            getTicket.addActionListener(e -> {
                ReservationSystem reservationSystem = new ReservationSystem(session);
                reservationSystem.pack();
                reservationSystem.setVisible(true);
                dispose();
            });


            sessionFooter.add(getTicket);
            sessionFooter.add(deleteButton);
            sessionPanel.add(sessionFooter);
            sessionPanel.setBackground(Color.blue);

            sessions.add(sessionPanel);


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
        filterPanel.add(Box.createRigidArea(new Dimension(0, 15)));
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
            SessionFilterer filterer = new SessionFilterer(sessionManager.getSessionList());
            List<MovieSession> filteredList = filterer.filter(sessionFilters).getResult();
            System.out.println(filteredList);
            populateWithSessions(filteredList);
        });
        filterPanel.add(filterButton);

    }

    private void addFiltersChecks() {
        JPanel filtersChecks = new JPanel();
        filtersChecks.setLayout(new BoxLayout(filtersChecks, BoxLayout.X_AXIS));
        filtersChecks.add(getGenreFiltersBox());
        filtersChecks.add(Box.createRigidArea(new Dimension(30, 0)));
        filtersChecks.add(getDateFilterBox());
        filtersChecks.add(getMovieFiltersBox());
        filtersChecks.add(getPriceFiltersBox());
        Dimension dimension = new Dimension(400, 300);
        filtersChecks.setPreferredSize(dimension);
        filtersChecks.setMinimumSize(dimension);
        filtersChecks.setMaximumSize(dimension);
        filtersChecks.setOpaque(false);
        filterPanel.add(filtersChecks);
    }

    private JPanel getDateFilterBox() {

        JPanel datesContainer = new JPanel();
        datesContainer.setOpaque(false);
        datesContainer.setLayout(new BoxLayout(datesContainer, BoxLayout.Y_AXIS));
        JLabel dates = new JLabel("Dates");
        dates.setForeground(Color.WHITE);
        dates.setFont(new Font(null, Font.BOLD, 16));
        datesContainer.add(dates);


        JPanel datesFilterBox = new JPanel();
        datesFilterBox.setLayout(new BoxLayout(datesFilterBox, BoxLayout.Y_AXIS));
//        datesFilterBox.setPreferredSize(dimension);
//        datesFilterBox.setMinimumSize(dimension);


        datesFilterBox.setAlignmentX(CENTER_ALIGNMENT);
        datesFilterBox.setOpaque(false);

        Dimension dimension = new Dimension(100, 120);
        datesFilterBox.setPreferredSize(dimension);
        datesFilterBox.setMinimumSize(dimension);

        return datesContainer;
    }

        private JPanel getGenreFiltersBox() {
        JPanel genresContainer = new JPanel();
        genresContainer.setOpaque(false);
        genresContainer.setLayout(new BoxLayout(genresContainer, BoxLayout.Y_AXIS));
        JLabel genres = new JLabel("Genres");
        genres.setForeground(Color.WHITE);
        genres.setFont(new Font(null, Font.BOLD, 16));
        genresContainer.add(genres);


        JPanel genreFiltersBox = new JPanel();
        genreFiltersBox.setLayout(new BoxLayout(genreFiltersBox, BoxLayout.Y_AXIS));

        genreFiltersBox.setAlignmentX(CENTER_ALIGNMENT);
        genreFiltersBox.setOpaque(false);
        Dimension dimension = new Dimension(100, 120);
        genreFiltersBox.setPreferredSize(dimension);
        genreFiltersBox.setMinimumSize(dimension);

        for (Genre genre : Genre.values()) {
            String genreName = genre.name().toLowerCase();
            String firstLetter = String.valueOf(genreName.charAt(0));
            genreName = genreName.replace(firstLetter, firstLetter.toUpperCase());
            JCheckBox genreCheckBox = new JCheckBox(genreName);
            genreCheckBox.addItemListener((ItemEvent e) -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    sessionByGenreFilter.addGenre(genre);
                } else {
                    sessionByGenreFilter.removeGenre(genre);
                }
                System.out.println(sessionByGenreFilter);
            });
            genreFiltersBox.add(genreCheckBox);
        }
        genresContainer.add(new JScrollPane(genreFiltersBox));
//        filterPanel.add(genreFiltersBox);
        return genresContainer;


    }

        private JPanel getMovieFiltersBox() {
        JPanel moviesContainer = new JPanel();
        moviesContainer.setOpaque(false);
        moviesContainer.setLayout(new BoxLayout(moviesContainer, BoxLayout.Y_AXIS));
        JLabel movies = new JLabel("Movies");
        movies.setForeground(Color.WHITE);
        movies.setFont(new Font(null, Font.BOLD, 16));
        moviesContainer.add(movies);


        JPanel movieFiltersBox = new JPanel();
        movieFiltersBox.setLayout(new BoxLayout(movieFiltersBox, BoxLayout.Y_AXIS));

        movieFiltersBox.setAlignmentX(CENTER_ALIGNMENT);
        movieFiltersBox.setOpaque(false);
        Dimension dimension = new Dimension(100, 120);
        movieFiltersBox.setPreferredSize(dimension);
        movieFiltersBox.setMinimumSize(dimension);


        moviesContainer.add(new JScrollPane(movieFiltersBox));
        filterPanel.add(movieFiltersBox);
        return moviesContainer;
    }

        private JPanel getPriceFiltersBox() {
        JPanel priceContainer = new JPanel();
        priceContainer.setOpaque(false);
        priceContainer.setLayout(new BoxLayout(priceContainer, BoxLayout.Y_AXIS));
        JLabel price = new JLabel("Price");
        price.setForeground(Color.WHITE);
        price.setFont(new Font(null, Font.BOLD, 16));
        priceContainer.add(price);


        JPanel priceFiltersBox = new JPanel();
        priceFiltersBox.setLayout(new BoxLayout(priceFiltersBox, BoxLayout.Y_AXIS));

        priceFiltersBox.setAlignmentX(CENTER_ALIGNMENT);
        priceFiltersBox.setOpaque(false);
        Dimension dimension = new Dimension(100, 120);
        priceFiltersBox.setPreferredSize(dimension);
        priceFiltersBox.setMinimumSize(dimension);

        priceContainer.add(new JScrollPane(priceFiltersBox));
//        filterPanel.add(genreFiltersBox);
        return priceContainer;
    }


    private void createFilterPanel() {
        filterPanel = new JPanel();
        filterPanel.setBackground(Color.BLUE);
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
        sessionsPanel.setBackground(Color.MAGENTA);
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
