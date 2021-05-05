package com.movie.theater.ui;

import com.movie.theater.model.Director;
import com.movie.theater.model.Genre;
import com.movie.theater.model.Movie;
import com.movie.theater.model.MovieSession;
import com.movie.theater.service.MovieManager;
import com.movie.theater.service.SessionManager;
import com.movie.theater.service.moviefilter.MovieByDirectorFilter;
import com.movie.theater.service.moviefilter.MovieByGenreFilter;
import com.movie.theater.service.moviefilter.MovieFilter;
import com.movie.theater.service.moviefilter.MovieFilterer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MoviesPage extends JFrame {

    private static final GridBagConstraints gridBagConstraints = new GridBagConstraints();
    private JPanel filterPanel = new JPanel();
    private JPanel movies = new JPanel();
    private List<MovieFilter> movieFilters = new ArrayList<>();
    private MovieByDirectorFilter movieByDirectorFilter = new MovieByDirectorFilter();
    private MovieByGenreFilter movieByGenreFilter = new MovieByGenreFilter();
    private MovieManager movieManager = MovieManager.getMovieManager();
    private SessionManager sessionManager = SessionManager.getSessionManager();
    private List<JCheckBox> checkBoxes = new ArrayList<>();
    private JButton mainMenuButton = new JButton("Main menu");
    private JPanel moviesPanel = new JPanel();

    public MoviesPage() throws HeadlessException {
        Dimension uidim = Toolkit.getDefaultToolkit().getScreenSize();

        this.setMinimumSize(uidim);
        this.setMaximumSize(uidim);
        this.setPreferredSize(uidim);
        this.setBackground(Color.BLACK);

        GridBagLayout layout = new GridBagLayout();
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        layout.setConstraints(this.getContentPane(), gridBagConstraints);
        this.getContentPane().setLayout(layout);

        addMainMenuButton();
        initMainContainer();
        addMoviesPanel();
        initFilterPanel();

        this.pack();
        this.setVisible(true);
    }


    private void addMainMenuButton() {
        mainMenuButton.setBackground(new Color(27, 30, 35));
        mainMenuButton.setForeground(Color.WHITE);
        mainMenuButton.setFont(new Font(null, Font.BOLD, 20));
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

    private void initMainContainer() {
        moviesPanel.setBackground(Color.black);
        gridBagConstraints.weightx = 0.9;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;

        moviesPanel.setLayout(new BoxLayout(moviesPanel, BoxLayout.Y_AXIS));
        addMoviesLabel();
    }

    private void addMoviesLabel() {
        JLabel moviesLabel = new JLabel("Movies");
        moviesLabel.setFont(new Font(null, Font.PLAIN, 50));
        moviesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        moviesLabel.setForeground(Color.WHITE);
        moviesPanel.add(moviesLabel);
    }

    private void addMoviesPanel() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(3);
        gridLayout.setRows(-1);
        gridLayout.setHgap(50);
        gridLayout.setVgap(50);
        movies.setLayout(gridLayout);
        JScrollPane scrollPane = new JScrollPane(movies, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        moviesPanel.add(scrollPane);
        moviesPanel.setBackground(new Color(100, 17, 173));
        populateWithMovies(movieManager.getMovieList());
        this.getContentPane().add(moviesPanel, gridBagConstraints);

    }

    private void populateWithMovies(List<Movie> moviesList) {
        movies.removeAll();
        movies.setBackground(new Color(100, 17, 173));
        moviesList.forEach((movie) -> addMovieBox(movie));

        pack();
    }

    private void addMovieBox(Movie movie) {
        JPanel moviePanel = new JPanel();
        moviePanel.setBackground(new Color(249, 250, 255));
        moviePanel.setBorder(BorderFactory.createSoftBevelBorder(0));
        Dimension dimension = new Dimension(300, 520);
        moviePanel.setPreferredSize(dimension);
        moviePanel.setMinimumSize(dimension);
        moviePanel.setMaximumSize(dimension);

        moviePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        File file = new File("src/com/movie/theater/icons/");
        Optional<File> name = Arrays.stream(file.listFiles()).filter(f -> f.getName().startsWith(movie.getName().replaceAll(" ", "_"))).findFirst();
        String path = "src/com/movie/theater/icons/default.png";
        if (name.isPresent()) {
            path = name.get().getAbsolutePath();
        }

        ImageIcon backIcon = new ImageIcon(path);
        Image image = backIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(300, 380, Image.SCALE_AREA_AVERAGING);// scale it the smooth way
        backIcon = new ImageIcon(newimg);  // transform it back
        moviePanel.add(new JLabel(backIcon));

        JLabel movieName = new JLabel(movie.getName(), SwingConstants.CENTER);
        Dimension movieNameDimension = new Dimension(300, 20);
        movieName.setPreferredSize(movieNameDimension);
        movieName.setMinimumSize(movieNameDimension);
        movieName.setMaximumSize(movieNameDimension);
        movieName.setToolTipText(movie.getName());
        movieName.setFont(new Font(null, Font.PLAIN, 20));
        moviePanel.add(movieName);

        JLabel movieDirector = new JLabel(movie.getDirector().getFullName());
        movieDirector.setMinimumSize(movieNameDimension);
        movieDirector.setMaximumSize(movieNameDimension);
        movieDirector.setPreferredSize(movieNameDimension);
        movieDirector.setToolTipText(movie.getName());
        movieDirector.setOpaque(false);
        movieDirector.setFont(new Font(null, Font.ITALIC, 16));
        moviePanel.add(movieDirector);

        JLabel genre = new JLabel(movie.getGenre().toString());
        genre.setMinimumSize(movieNameDimension);
        genre.setMaximumSize(movieNameDimension);
        genre.setPreferredSize(movieNameDimension);
        genre.setToolTipText(movie.getName());
        genre.setOpaque(false);
        genre.setFont(new Font(null, Font.ITALIC, 16));
        moviePanel.add(genre);

        JPanel movieFooter = new JPanel();
        movieFooter.setLayout(new FlowLayout(FlowLayout.RIGHT));
        Dimension footerDimension = new Dimension(300, 35);
        movieFooter.setPreferredSize(footerDimension);
        movieFooter.setMinimumSize(footerDimension);
        movieFooter.setMaximumSize(footerDimension);
        movieFooter.setOpaque(false);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(100, 17, 173));
        deleteButton.setForeground(Color.white);

        deleteButton.addActionListener(e -> {
            List<MovieSession> sessionsToDelete = SessionManager
                    .getSessionManager()
                    .SESSION_LIST
                    .stream()
                    .filter(s -> s.getMovie().equals(movie))
                    .collect(Collectors.toList());
            sessionsToDelete.forEach(s -> {
                try {
                    sessionManager.deleteSession(s);
                } catch (IOException exception) {
                    showExitCase();
                }
            });
            movieManager.deleteMovie(movie);
            movies.remove(moviePanel);
            pack();
        });

        movieFooter.add(deleteButton);
        JButton addSession = new JButton("Add session");
        addSession.setBackground(new Color(100, 17, 173));
        addSession.setForeground(Color.WHITE);
        addSession.addActionListener(e -> {
            AddSessionPage addSessionPage = new AddSessionPage(movie);
            addSessionPage.pack();
            addSessionPage.setVisible(true);
            addSessionPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        });

        movieFooter.add(addSession);
        moviePanel.add(movieFooter);

        movies.add(moviePanel);
    }


    private void initFilterPanel() {
        createFilterPanel();
        addSpace();
        addAddMovieButton();
        addSpace();
        addFiltersLabel();
        addSpace();
        addFiltersChecks();
        addSpace();
        addFilterButton();
        addSpace();
        addResetButton();
    }

    private void createFilterPanel() {
        filterPanel.setBackground(new Color(27, 30, 35));
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 1;
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        this.getContentPane().add(filterPanel, gridBagConstraints);
    }

    private void addSpace() {
        filterPanel.add(Box.createRigidArea(new Dimension(0, 50)));
    }

    private void addAddMovieButton() {
        JButton button = new JButton("Add Movie");
        button.addActionListener(e -> addMovieButtonActionPerformed());

        Dimension dimension = new Dimension(200, 100);
        button.setPreferredSize(dimension);
        button.setMinimumSize(dimension);
        button.setMaximumSize(dimension);
        button.setBackground(new Color(100, 17, 173));
        button.setBorder(BorderFactory.createBevelBorder(0));
        button.setForeground(Color.WHITE);
        button.setAlignmentX(CENTER_ALIGNMENT);

        filterPanel.add(button);
    }

    public void addMovieButtonActionPerformed() {
        AddMoviePage addMoviePage = new AddMoviePage();
        addMoviePage.pack();
        addMoviePage.setVisible(true);
        addMoviePage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addMoviePage.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                populateWithMovies(movieManager.getMovieList());
            }
        });
    }

    private void addFiltersLabel() {
        JLabel filtersLabel = new JLabel("Filters");
        filtersLabel.setAlignmentX(CENTER_ALIGNMENT);
        filtersLabel.setForeground(Color.white);
        filterPanel.add(filtersLabel);
        this.pack();
        this.setVisible(true);
    }

    private void addFiltersChecks() {
        JPanel filtersChecks = new JPanel();
        filtersChecks.setLayout(new BoxLayout(filtersChecks, BoxLayout.X_AXIS));
        filtersChecks.add(getGenreFiltersBox());
        filtersChecks.add(Box.createRigidArea(new Dimension(30, 0)));
        filtersChecks.add(getDirectorsFilterBox());
        Dimension dimension = new Dimension(400, 300);
        filtersChecks.setPreferredSize(dimension);
        filtersChecks.setMinimumSize(dimension);
        filtersChecks.setMaximumSize(dimension);
        filtersChecks.setOpaque(false);
        filterPanel.add(filtersChecks);
    }

    private void addFilterButton() {
        JButton filterButton = new JButton("Filter");
        filterButton.addActionListener((e) -> {
            movieFilters.clear();
            movieFilters.add(movieByDirectorFilter);
            movieFilters.add(movieByGenreFilter);
            MovieFilterer filterer = new MovieFilterer(movieManager.getMovieList());
            List<Movie> filteredList = filterer.filter(movieFilters).getResult();
            populateWithMovies(filteredList);
        });
        Dimension dim = new Dimension(200, 50);
        filterButton.setPreferredSize(dim);
        filterButton.setMinimumSize(dim);
        filterButton.setMaximumSize(dim);
        filterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        filterButton.setBackground(new Color(100, 17, 173));
        filterButton.setBorder(BorderFactory.createBevelBorder(0));
        filterButton.setForeground(Color.white);
        filterPanel.add(filterButton);

    }

    private void addResetButton() {
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener((e) -> {
            movieFilters.clear();
            movieByDirectorFilter.reset();
            movieByGenreFilter.reset();
            checkBoxes.forEach(c -> c.setSelected(false));
            populateWithMovies(movieManager.getMovieList());
        });
        Dimension dim = new Dimension(200, 50);
        resetButton.setPreferredSize(dim);
        resetButton.setMinimumSize(dim);
        resetButton.setMaximumSize(dim);
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton.setBackground(new Color(100, 17, 173));
        resetButton.setBorder(BorderFactory.createBevelBorder(0));
        resetButton.setForeground(Color.white);
        filterPanel.add(resetButton);

    }

    private void showExitCase() {
        JOptionPane.showMessageDialog(this, "Sorry something went wrong! The program need to exit");
        System.exit(-1);
    }


    private JPanel getDirectorsFilterBox() {
        JPanel directorsContainer = new JPanel();
        directorsContainer.setOpaque(false);
        directorsContainer.setLayout(new BoxLayout(directorsContainer, BoxLayout.Y_AXIS));
        JLabel directors = new JLabel("Directors");
        directors.setForeground(Color.WHITE);
        directors.setFont(new Font(null, Font.BOLD, 16));
        directorsContainer.add(directors);
        JPanel directorFilterBox = new JPanel();
        directorFilterBox.setLayout(new BoxLayout(directorFilterBox, BoxLayout.Y_AXIS));

        directorFilterBox.setAlignmentX(CENTER_ALIGNMENT);
        for (Director director : movieManager.getDirectorList()) {
            String directorFullName = director.getFullName();
            JCheckBox directorCheckBox = new JCheckBox(directorFullName);
            directorCheckBox.addItemListener((ItemEvent e) -> {
                directorCheckBoxItemChanged(e, director);
            });
            directorCheckBox.setForeground(Color.WHITE);
            directorCheckBox.setOpaque(false);
            checkBoxes.add(directorCheckBox);
            directorFilterBox.add(directorCheckBox);
        }
        directorFilterBox.setBackground(new Color(27, 30, 35));
        JScrollPane scrollPane = new JScrollPane(directorFilterBox);
        scrollPane.setBackground(Color.BLACK);
        directorsContainer.add(scrollPane);
        return directorsContainer;
    }

    private void directorCheckBoxItemChanged(ItemEvent e, Director director) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            movieByDirectorFilter.addDirector(director);
        } else {
            movieByDirectorFilter.removeDirector(director);
        }
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
            genreCheckBox.addItemListener((ItemEvent e) -> genreCheckBoxItemChanged(e, genre));
            genreCheckBox.setForeground(Color.WHITE);
            genreCheckBox.setOpaque(false);
            checkBoxes.add(genreCheckBox);
            genreFiltersBox.add(genreCheckBox);
        }
        genreFiltersBox.setBackground(new Color(27, 30, 35));
        JScrollPane scrollPane = new JScrollPane(genreFiltersBox);
        scrollPane.setBackground(Color.BLACK);
        genresContainer.add(scrollPane);

        return genresContainer;
    }

    private void genreCheckBoxItemChanged(ItemEvent e, Genre genre) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            movieByGenreFilter.addGenre(genre);
        } else {
            movieByGenreFilter.removeGenre(genre);
        }
    }

}