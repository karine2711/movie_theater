package com.movie.theater.ui;

import com.movie.theater.model.Genre;
import com.movie.theater.model.Movie;
import com.movie.theater.model.MovieSession;
import com.movie.theater.service.MovieManager;
import com.movie.theater.service.SessionManager;
import com.movie.theater.service.moviesessionfilter.*;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class SessionsPage extends JFrame {

    private JButton mainMenuButton;
    private JPanel sessionsPanel;
    JPanel filterPanel;
    JPanel sessions = new JPanel();
    List<SessionFilter> sessionFilters = new ArrayList<>();
    SessionByDateFilter sessionByDateFilter = new SessionByDateFilter();
    SessionByGenreFilter sessionByGenreFilter = new SessionByGenreFilter();
    SessionByMovieFilter sessionByMovieFilter = new SessionByMovieFilter();
    SessionByPriceFilter sessionByPriceFilter = new SessionByPriceFilter();
    SessionManager sessionManager = SessionManager.getSessionManager();
    MovieManager movieManager = MovieManager.getMovieManager();
    boolean isValidPrice;


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

        //Sessions Page Button
        addMainMenuButton();
        initMainContainer();

        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(3);
        gridLayout.setRows(-1);
        gridLayout.setHgap(20);
        gridLayout.setVgap(10);
        sessions.setLayout(gridLayout);
        JScrollPane scrollPane = new JScrollPane(sessions, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sessionsPanel.add(scrollPane);
        sessionsPanel.setBackground(new Color(100, 17, 173));

        populateWithSessions(sessionManager.getSessionList());

        this.getContentPane().add(sessionsPanel, gridBagConstraints);


        ///filter panel
        initFilterPanel();


        this.pack();
        this.setVisible(true);
    }

    private void populateWithSessions(List<MovieSession> sessionsList) {
        sessions.removeAll();
        sessions.setBackground(new Color(100, 17, 173));

        sessionsList.forEach((session) ->
        {
            JPanel sessionPanel = new JPanel();
            sessionPanel.setLayout(new GridLayout(6, 1));
            sessionPanel.setBackground(new Color(249, 250, 255));
            sessionPanel.setBorder(BorderFactory.createSoftBevelBorder(0));
            Dimension dimension = new Dimension(300, 200);
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
            movieName.setBackground(new Color(27, 30, 35));
            movieName.setForeground(Color.white);
            movieName.setOpaque(true);
            sessionPanel.add(movieName);

            JLabel movieDirector = new JLabel(session.getMovie().getDirector().getFirstName());
            movieDirector.setMinimumSize(movieNameDimension);
            movieDirector.setMaximumSize(movieNameDimension);
            movieDirector.setPreferredSize(movieNameDimension);
            movieDirector.setToolTipText(session.getMovie().getName());
            movieDirector.setOpaque(false);
            movieDirector.setBackground(Color.WHITE);
            movieDirector.setFont(new Font(null, Font.PLAIN, 16));
            sessionPanel.add(movieDirector);

            JLabel genre = new JLabel(session.getMovie().getGenre().toString());
            genre.setMinimumSize(movieNameDimension);
            genre.setMaximumSize(movieNameDimension);
            genre.setPreferredSize(movieNameDimension);
            genre.setToolTipText(session.getMovie().getName());
            genre.setOpaque(true);
            genre.setBackground(Color.WHITE);
            genre.setFont(new Font(null, Font.PLAIN, 16));
            sessionPanel.add(genre);

            JLabel date = new JLabel(String.valueOf(session.getLocalDateTime()));
            date.setMinimumSize(movieNameDimension);
            date.setMaximumSize(movieNameDimension);
            date.setPreferredSize(movieNameDimension);
            date.setToolTipText(session.getMovie().getName());
            date.setOpaque(true);
            date.setBackground(Color.WHITE);
            date.setFont(new Font(null, Font.PLAIN, 16));
            sessionPanel.add(date);

            JLabel price = new JLabel(String.valueOf(session.getPriceForSession()));
            price.setMinimumSize(movieNameDimension);
            price.setMaximumSize(movieNameDimension);
            price.setPreferredSize(movieNameDimension);
            price.setToolTipText(session.getMovie().getName());
            price.setOpaque(true);
            price.setBackground(Color.WHITE);
            price.setFont(new Font(null, Font.PLAIN, 16));
            sessionPanel.add(price);


            JPanel sessionFooter = new JPanel();
            sessionFooter.setLayout(new FlowLayout(FlowLayout.RIGHT));
            Dimension footerDimension = new Dimension(300, 35);
            sessionFooter.setPreferredSize(footerDimension);
            sessionFooter.setMinimumSize(footerDimension);
            sessionFooter.setMaximumSize(footerDimension);
            sessionFooter.setOpaque(false);

            JButton deleteButton = new JButton("Delete Session");
            deleteButton.setBackground(new Color(100, 17, 173));
            deleteButton.setForeground(Color.white);

            deleteButton.addActionListener(e -> {
                try {
                    sessionManager.deleteSession(session);
                    sessions.remove(sessionPanel);
                    pack();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });

            JButton getTicket = new JButton("Get Tickets");
            getTicket.setBackground(new Color(100, 17, 173));
            getTicket.setForeground(Color.WHITE);
            getTicket.addActionListener(e -> {
                ReservationSystem reservationSystem = new ReservationSystem(session);
                reservationSystem.pack();
                reservationSystem.setVisible(true);
                dispose();
            });


            sessionFooter.add(getTicket);
            sessionFooter.add(deleteButton);
            sessionPanel.add(sessionFooter);
            sessionPanel.setBackground(Color.WHITE);

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

        addFiltersChecks1();
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
            populateWithSessions(sessionManager.getSessionList());
        });
        filterPanel.add(resetButton);

    }

    private void addFilterButton() {
        JButton filterButton = new JButton("Filter");
        filterButton.addActionListener((e) -> filter());
        filterPanel.add(filterButton);
    }


    private void filter() {
        sessionFilters.clear();
        sessionFilters.add(sessionByDateFilter);
        sessionFilters.add(sessionByGenreFilter);
        sessionFilters.add(sessionByMovieFilter);
        sessionFilters.add(sessionByPriceFilter);
        SessionFilterer filterer = new SessionFilterer(sessionManager.getSessionList());
        List<MovieSession> filteredList = filterer.filter(sessionFilters).getResult();
        System.out.println(filteredList);
        populateWithSessions(filteredList);
    }

    private void addFiltersChecks() {
        JPanel filtersChecks = new JPanel();
        filtersChecks.setLayout(new BoxLayout(filtersChecks, BoxLayout.X_AXIS));
        filtersChecks.add(getGenreFiltersBox());
        filtersChecks.add(Box.createRigidArea(new Dimension(30, 0)));
        filtersChecks.add(getMovieFiltersBox());
//        filtersChecks.add(Box.createRigidArea(new Dimension(30, 0)));
//        filtersChecks.add(getPriceFiltersBox());
//        filtersChecks.add(Box.createRigidArea(new Dimension(30, 0)));
//        filtersChecks.add(getDateFiltersBox());


        Dimension dimension = new Dimension(350, 200);
        filtersChecks.setPreferredSize(dimension);
        filtersChecks.setMinimumSize(dimension);
        filtersChecks.setMaximumSize(dimension);
        filtersChecks.setOpaque(false);

        filterPanel.add(filtersChecks);

    }

    private void addFiltersChecks1() {

        JPanel filtersChecks1 = new JPanel();
        filtersChecks1.setLayout(new BoxLayout(filtersChecks1, BoxLayout.X_AXIS));
        filtersChecks1.add(getDateFiltersBox());
        filtersChecks1.add(Box.createRigidArea(new Dimension(30, 0)));
        filtersChecks1.add(getPriceFiltersBox());

        Dimension dimension1 = new Dimension(350, 90);
        filtersChecks1.setPreferredSize(dimension1);
        filtersChecks1.setMinimumSize(dimension1);
        filtersChecks1.setMaximumSize(dimension1);
        filtersChecks1.setOpaque(false);
        filterPanel.add(filtersChecks1);
    }

    private JPanel getDateFiltersBox() {

        JPanel datesContainer = new JPanel();
        datesContainer.setOpaque(false);
        datesContainer.setLayout(new BoxLayout(datesContainer, BoxLayout.Y_AXIS));
        JLabel dates = new JLabel("Dates");
        dates.setForeground(Color.WHITE);
        dates.setFont(new Font(null, Font.BOLD, 16));
        datesContainer.add(dates);
        JDateChooser dateChooser = new JDateChooser();
        datesContainer.add(Box.createRigidArea(new Dimension(0, 25)));
        datesContainer.add(dateChooser);
        dateChooser.getDateEditor().setEnabled(false);
//        dateChooser.setMinSelectableDate(new Date());
        dateChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if (dateChooser.getDate() == null) return;
                LocalDate localDate = Instant.ofEpochMilli(dateChooser.getDate().getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                sessionByDateFilter.setDate(localDate);
            }
        });

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
            genreCheckBox.setForeground(Color.BLACK);
            genreCheckBox.setOpaque(false);
            genreFiltersBox.add(genreCheckBox);
        }
        genresContainer.add(new JScrollPane(genreFiltersBox));
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
        Dimension dimension = new Dimension(100, 200);
        movieFiltersBox.setPreferredSize(dimension);
        movieFiltersBox.setMinimumSize(dimension);

        movieFiltersBox.setMaximumSize(new Dimension(100, 200));

        for (Movie movie : movieManager.getMovieList()) {
            String movieName = movie.getName();
            JCheckBox movieCheckBox = new JCheckBox(movieName);
            movieCheckBox.addItemListener((ItemEvent e) -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    sessionByMovieFilter.addMovie(movie);
                } else {
                    sessionByMovieFilter.removeMovie(movie);
                }
            });
            movieCheckBox.setForeground(Color.BLACK);
            movieCheckBox.setOpaque(false);
            movieFiltersBox.add(movieCheckBox);
        }

        JScrollPane jScrollPane = new JScrollPane(movieFiltersBox);
        moviesContainer.add(jScrollPane);
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

        JLabel minLabel = new JLabel("Minimum");
        minLabel.setForeground(Color.WHITE);
        JLabel maxLabel = new JLabel("Maximum");
        maxLabel.setForeground(Color.WHITE);
        JTextField minimum = new JTextField();
        JTextField maximum = new JTextField();
        priceContainer.add(minLabel);
        priceContainer.add(minimum);
        validateMinPrice(minimum);
        validateMaxPrice(maximum);
        priceContainer.add(maxLabel);
        priceContainer.add(maximum);
        return priceContainer;
    }

    public void validateMinPrice(JTextField field) {
        field.getDocument().addDocumentListener(new DocumentListener() {
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
                if (field.getText().matches("[0-9]{2,3}0")) {
                    field.setBackground(Color.WHITE);
                    sessionByPriceFilter.setMinPrice(Integer.parseInt(field.getText()));
                } else if (field.getText().isEmpty()) {
                    field.setBackground(Color.WHITE);
                    sessionByPriceFilter.setMinPrice(0);
                } else {
                    field.setBackground(new Color(255, 153, 153));
                    sessionByPriceFilter.setMinPrice(0);
                }
            }
        });

    }

    public void validateMaxPrice(JTextField field) {
        field.getDocument().addDocumentListener(new DocumentListener() {
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
                if (field.getText().matches("[0-9]{2,3}0")) {
                    field.setBackground(Color.WHITE);
                    sessionByPriceFilter.setMaxPrice(Integer.parseInt(field.getText()));
                } else if (field.getText().isEmpty()) {
                    field.setBackground(Color.WHITE);
                    sessionByPriceFilter.setMaxPrice(9990);
                } else {
                    field.setBackground(new Color(255, 153, 153));
                    sessionByPriceFilter.setMaxPrice(9990);
                }
            }
        });

    }


    private void createFilterPanel() {
        filterPanel = new JPanel();
        filterPanel.setBackground(new Color(27, 30, 35));
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
        Dimension dimension = new Dimension(200, 100);
        JButton button = new JButton("Add Session");
        button.addActionListener(e -> {
            AddSessionPage addSessionPage = new AddSessionPage();
            addSessionPage.pack();
            addSessionPage.setVisible(true);
            addSessionPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addSessionPage.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    super.windowClosed(e);
                    populateWithSessions(sessionManager.getSessionList());
                }
            });
        });
        button.setPreferredSize(dimension);
        button.setMinimumSize(dimension);
        button.setMaximumSize(dimension);
        button.setBackground(new Color(100, 17, 173));
        button.setBorder(BorderFactory.createBevelBorder(0));
        button.setForeground(Color.WHITE);
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
//        filterPanel.add(Box.createRigidArea(new Dimension(0, 50)));
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
        sessionsLabel.setForeground(Color.WHITE);
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
        mainMenuButton.setBackground(Color.WHITE);
        mainMenuButton.setForeground(Color.black);
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
