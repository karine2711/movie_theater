package com.movie.theater.ui;

import com.movie.theater.model.Director;
import com.movie.theater.model.Genre;
import com.movie.theater.model.Movie;
import com.movie.theater.service.MovieManager;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MoviesPage extends JFrame {

    private JButton mainMenuButton;
    private JPanel moviesPanel;
    JPanel filterPanel;
    JPanel movies = new JPanel();
    List<MovieFilter> movieFilters = new ArrayList<>();
    MovieByDirectorFilter movieByDirectorFilter = new MovieByDirectorFilter();
    MovieByGenreFilter movieByGenreFilter = new MovieByGenreFilter();
    MovieManager movieManager = MovieManager.getMovieManager();


    private static GridBagConstraints gridBagConstraints = new GridBagConstraints();

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

        //Movies Page Button
        addMainMenuButton();
        initMainContainer();
        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(3);
        gridLayout.setRows(-1);
        gridLayout.setHgap(50);
        gridLayout.setVgap(50);
        movies.setLayout(gridLayout);
        JScrollPane scrollPane = new JScrollPane(movies, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        moviesPanel.add(scrollPane);
        moviesPanel.setBackground(new Color(120, 111, 166));
        populateWithMovies(movieManager.getMoveList());

        this.getContentPane().add(moviesPanel, gridBagConstraints);


        ///filter panel
        initFilterPanel();


        List<Director> directorList = movieManager.getDirectorList();
//        JList<Director>
        this.pack();
        this.setVisible(true);
    }

    private void populateWithMovies(List<Movie> moviesList) {
        movies.removeAll();
        movies.setBackground(new Color(87, 75, 144));

        moviesList.forEach((movie) ->
        {
            JPanel moviePanel = new JPanel();
            moviePanel.setBackground(new Color(205, 218, 253));
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

            JLabel movieDirector = new JLabel(movie.getDirector().getFirstName());
            movieDirector.setMinimumSize(movieNameDimension);
            movieDirector.setMaximumSize(movieNameDimension);
            movieDirector.setPreferredSize(movieNameDimension);
            movieDirector.setToolTipText(movie.getName());
            movieDirector.setOpaque(true);
            movieDirector.setBackground(new Color(120, 111, 166));
            movieDirector.setFont(new Font(null, Font.ITALIC, 16));
            moviePanel.add(movieDirector);

            JPanel movieFooter = new JPanel();
            movieFooter.setLayout(new FlowLayout(FlowLayout.RIGHT));
            Dimension footerDimension = new Dimension(300, 35);
            movieFooter.setPreferredSize(footerDimension);
            movieFooter.setMinimumSize(footerDimension);
            movieFooter.setMaximumSize(footerDimension);
            movieFooter.setBackground(new Color(120, 111, 166));

            JButton deleteButton = new JButton("Delete");
            deleteButton.setBackground(Color.BLACK);
            deleteButton.setForeground(Color.WHITE);
            deleteButton.addActionListener(e -> {
                movieManager.deleteMovie(movie);
                movies.remove(moviePanel);
                pack();
            });

            movieFooter.add(deleteButton);
            JButton addSession = new JButton("Add session");
            addSession.setBackground(Color.BLACK);
            addSession.setForeground(Color.WHITE);
            addSession.addActionListener(e -> {
                AddSessionPage addSessionPage = new AddSessionPage(movie);
                addSessionPage.pack();
                addSessionPage.setVisible(true);
                dispose();
            });

            movieFooter.add(addSession);
            moviePanel.add(movieFooter);

            movies.add(moviePanel);
        });

//        movies.repaint();
        pack();
    }

    private void initFilterPanel() {
        createFilterPanel();
        addAddMovieButton();
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
            movieFilters.clear();
            movieByDirectorFilter.reset();
            movieByGenreFilter.reset();
            populateWithMovies(movieManager.getMoveList());
        });
        Dimension dim = new Dimension(200, 50);
        resetButton.setPreferredSize(dim);
        resetButton.setMinimumSize(dim);
        resetButton.setMaximumSize(dim);
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton.setBackground(new Color(120, 111, 166));
        filterPanel.add(resetButton);

    }

    private void addFilterButton() {
        JButton filterButton = new JButton("Filter");
        filterButton.addActionListener((e) -> {
            movieFilters.clear();
            movieFilters.add(movieByDirectorFilter);
            movieFilters.add(movieByGenreFilter);
            MovieFilterer filterer = new MovieFilterer(movieManager.getMoveList());
            List<Movie> filteredList = filterer.filter(movieFilters).getResult();
            System.out.println(filteredList);
            populateWithMovies(filteredList);
        });
        Dimension dim = new Dimension(200, 50);
        filterButton.setPreferredSize(dim);
        filterButton.setMinimumSize(dim);
        filterButton.setMaximumSize(dim);
        filterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        filterButton.setBackground(new Color(120, 111, 166));
        filterPanel.add(filterButton);

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
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    movieByDirectorFilter.addDirector(director);
                } else {
                    movieByDirectorFilter.removeDirector(director);
                }
                System.out.println(movieByDirectorFilter);
            });
            directorCheckBox.setForeground(Color.WHITE);
            directorCheckBox.setOpaque(false);
            directorFilterBox.add(directorCheckBox);
        }
        directorFilterBox.setBackground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(directorFilterBox);
        scrollPane.setBackground(Color.BLACK);
        directorsContainer.add(scrollPane);
//        filterPanel.add(directorFilterBox);
        return directorsContainer;
    }


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

    private void addAddMovieButton() {
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        JButton button = new JButton("Add Movie");
        button.addActionListener(e -> {
            AddMoviePage addMoviePage = new AddMoviePage();
            addMoviePage.pack();
            addMoviePage.setVisible(true);
            addMoviePage.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addMoviePage.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    super.windowClosed(e);
                    populateWithMovies(movieManager.getMoveList());
                }
            });
        });
        button.setBackground(new Color(120, 111, 166));
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
//        Dimension dimension = new Dimension(100, 120);
//        genreFiltersBox.setPreferredSize(dimension);
//        genreFiltersBox.setMinimumSize(dimension);

        for (Genre genre : Genre.values()) {
            String genreName = genre.name().toLowerCase();
            String firstLetter = String.valueOf(genreName.charAt(0));
            genreName = genreName.replace(firstLetter, firstLetter.toUpperCase());
            JCheckBox genreCheckBox = new JCheckBox(genreName);
            genreCheckBox.addItemListener((ItemEvent e) -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    movieByGenreFilter.addGenre(genre);
                } else {
                    movieByGenreFilter.removeGenre(genre);
                }
                System.out.println(movieByGenreFilter);
            });
            genreCheckBox.setForeground(Color.WHITE);
            genreCheckBox.setOpaque(false);
            genreFiltersBox.add(genreCheckBox);
        }
        genreFiltersBox.setBackground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(genreFiltersBox);
        scrollPane.setBackground(Color.BLACK);
        genresContainer.add(scrollPane);

//        filterPanel.add(genreFiltersBox);
        return genresContainer;
    }


    private void initMainContainer() {
        moviesPanel = new JPanel();
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


    public static void main(String[] args) {
        MoviesPage tryUI = new MoviesPage();

    }

    private void filterBoxItemStateChanged(ItemEvent e) {
        e.setSource(Genre.values());
        System.out.println("sdsd");
    }

    private void addMainMenuButton() {
        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBackground(Color.BLACK);
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
}