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
import java.util.ArrayList;
import java.util.List;

public class Sessions extends JFrame {

    private JButton mainMenuButton;
    private JPanel moviesPanel;
    JPanel filterPanel;
    JPanel movies = new JPanel();
    List<MovieFilter> movieFilters = new ArrayList<>();
    MovieByDirectorFilter movieByDirectorFilter = new MovieByDirectorFilter();
    MovieByGenreFilter movieByGenreFilter = new MovieByGenreFilter();
    MovieManager movieManager = MovieManager.getMovieManager();


    private static GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public Sessions() throws HeadlessException {
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
        movies.setLayout(gridLayout);
        JScrollPane scrollPane = new JScrollPane(movies, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        moviesPanel.add(scrollPane);
//        populateWithMovies(movieManager.getMoveList());
        this.getContentPane().add(moviesPanel, gridBagConstraints);

        //movies page end

        ///filter panel
        initFilterPanel();


        List<Director> directorList = movieManager.getDirectorList();
//        JList<Director>
        this.pack();
        this.setVisible(true);
    }

//    private void populateWithMovies(List<Movie> moviesList) {
//        movies.removeAll();
//        movies.setBackground(Color.pink);
//
//        moviesList.forEach((movie) ->
//        {
//            JPanel moviePanel = new JPanel();
//            moviePanel.setOpaque(false);
//            Dimension dimension = new Dimension(300, 520);
//            moviePanel.setPreferredSize(dimension);
//            moviePanel.setMinimumSize(dimension);
//            moviePanel.setMaximumSize(dimension);
//
//            moviePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
//            ImageIcon backIcon = new ImageIcon("src/com/movie/theater/icons/cover1.jpg");
//            Image image = backIcon.getImage(); // transform it
//            Image newimg = image.getScaledInstance(300, 380, Image.SCALE_AREA_AVERAGING);// scale it the smooth way
//            backIcon = new ImageIcon(newimg);  // transform it back
//            moviePanel.add(new JLabel(backIcon));
//
//            JLabel movieName = new JLabel(movie.getName(), SwingConstants.CENTER);
//            Dimension movieNameDimension = new Dimension(300, 20);
//            movieName.setPreferredSize(movieNameDimension);
//            movieName.setMinimumSize(movieNameDimension);
//            movieName.setMaximumSize(movieNameDimension);
//            movieName.setToolTipText(movie.getName());
//            movieName.setFont(new Font(null, Font.PLAIN, 20));
//            moviePanel.add(movieName);
//
//            JLabel movieDirector = new JLabel(movie.getDirector().getFirstName());
//            movieDirector.setMinimumSize(movieNameDimension);
//            movieDirector.setMaximumSize(movieNameDimension);
//            movieDirector.setPreferredSize(movieNameDimension);
//            movieDirector.setToolTipText(movie.getName());
//            movieDirector.setOpaque(true);
//            movieDirector.setBackground(Color.YELLOW);
//            movieDirector.setFont(new Font(null, Font.ITALIC, 16));
//            moviePanel.add(movieDirector);
//
//            JPanel movieFooter = new JPanel();
//            movieFooter.setLayout(new FlowLayout(FlowLayout.RIGHT));
//            Dimension footerDimension = new Dimension(300, 35);
//            movieFooter.setPreferredSize(footerDimension);
//            movieFooter.setMinimumSize(footerDimension);
//            movieFooter.setMaximumSize(footerDimension);
//
//            JButton deleteButton = new JButton("Delete");
//            movieFooter.add(deleteButton);
//            movieFooter.add(new JButton("Add session"));
//            moviePanel.add(movieFooter);
//            moviePanel.setBackground(Color.orange);
//
//            movies.add(moviePanel);
//        });

//        movies.repaint();
//        pack();
//    }

    private void initFilterPanel() {
        createFilterPanel();
        addAddMovieButton();
        addFiltersLabel();
        addFiltersChecks();
        filterPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        addFilterButton();
        addResetButton();

    }

    private void addResetButton() {
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener((e) -> {
            movieFilters.clear();
            movieByDirectorFilter.reset();
            movieByGenreFilter.reset();
//           populateWithMovies(movieManager.getMoveList());
        });
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
//            populateWithMovies(filteredList);
        });
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
//        Dimension dimension = new Dimension(100, 120);
//        directorFilterBox.setPreferredSize(dimension);
//        directorFilterBox.setMinimumSize(dimension);

        JPanel directorFilterBox = new JPanel();
        directorFilterBox.setLayout(new BoxLayout(directorFilterBox, BoxLayout.Y_AXIS));

        directorFilterBox.setAlignmentX(CENTER_ALIGNMENT);
        directorFilterBox.setOpaque(false);
        for (Director director : movieManager.getDirectorList()) {
            String directorFullName = director.getFullName();
            JCheckBox genreCheckBox = new JCheckBox(directorFullName);
            genreCheckBox.addItemListener((ItemEvent e) -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    movieByDirectorFilter.addDirector(director);
                } else {
                    movieByDirectorFilter.removeDirector(director);
                }
                System.out.println(movieByDirectorFilter);
            });
            directorFilterBox.add(genreCheckBox);
        }
        directorsContainer.add(new JScrollPane(directorFilterBox));
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
//        panel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button = new JButton("Add Movie");
        button.addActionListener(e -> {
            AddMoviePage AddMoviePage = new AddMoviePage();
            AddMoviePage.pack();
            AddMoviePage.setVisible(true);
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
            genreFiltersBox.add(genreCheckBox);
        }
        genresContainer.add(new JScrollPane(genreFiltersBox));
//        filterPanel.add(genreFiltersBox);
        return genresContainer;
    }


    private void initMainContainer() {
        moviesPanel = new JPanel();
        moviesPanel.setBackground(Color.GREEN);
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
        Dimension label = new Dimension(moviesPanel.getWidth() / 2, 500);
        moviesLabel.setFont(new Font(null, Font.PLAIN, 50));
        moviesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        moviesLabel.setPreferredSize(label);
//        moviesLabel.setMaximumSize(label);
//        moviesLabel.setMinimumSize(label);
        moviesPanel.add(moviesLabel);
    }


    public static void main(String[] args) {
        Sessions tryUI = new Sessions();

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
}
