package com.movie.theater.ui;

import com.movie.theater.model.Genre;
import com.movie.theater.model.Movie;
import com.movie.theater.service.MovieManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.List;

public class TryUI extends JFrame {

    private JButton mainMenuButton;

    private static GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public TryUI() throws HeadlessException {
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
        this.addMainMenuButton();


        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.GREEN);
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));


        JLabel moviesLabel = new JLabel("Movies");
        Dimension label = new Dimension(panel1.getWidth() / 2, 500);
        moviesLabel.setFont(new Font(null, Font.PLAIN, 50));
        moviesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        moviesLabel.setPreferredSize(label);
//        moviesLabel.setMaximumSize(label);
//        moviesLabel.setMinimumSize(label);
        panel1.add(moviesLabel);
        JPanel movies = new JPanel();
//        moviesLabel.setPreferredSize(label);
//        moviesLabel.setMaximumSize(label);
//        moviesLabel.setMinimumSize(label);
        movies.setBackground(Color.pink);
        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(3);
        gridLayout.setRows(-1);
        gridLayout.setHgap(50);
        gridLayout.setVgap(50);
        movies.setLayout(gridLayout);
        JScrollPane scrollPane = new JScrollPane(movies, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel1.add(scrollPane);


        MovieManager movieManager = MovieManager.getMovieManager();
        List<Movie> moviesList = movieManager.getList();
        moviesList.forEach((movie) ->
        {
            JPanel moviePanel = new JPanel();
            moviePanel.setOpaque(false);

            moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));
            moviePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            ImageIcon backIcon = new ImageIcon("src/com/movie/theater/icons/cover1.jpg");
            Image image = backIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(200, 300, java.awt.Image.SCALE_SMOOTH);// scale it the smooth way
            backIcon = new ImageIcon(newimg);  // transform it back
            moviePanel.add(new JLabel(backIcon));

            JLabel movieName = new JLabel(movie.getName(), SwingConstants.CENTER);
            Dimension movieNameDimension = new Dimension(300, 30);
            movieName.setPreferredSize(movieNameDimension);
            movieName.setMinimumSize(movieNameDimension);
            movieName.setMaximumSize(movieNameDimension);
            movieName.setToolTipText(movie.getName());
            movieName.setFont(new Font(null, Font.PLAIN, 25));
            moviePanel.add(movieName);

            JPanel movieFooter = new JPanel();
//            movieFooter.setLayout(new FlowLayout(FlowLayout.RIGHT));
            movieFooter.setPreferredSize(movieNameDimension);
            movieFooter.setMinimumSize(movieNameDimension);
            movieFooter.setMaximumSize(movieNameDimension);
            movieFooter.setAlignmentX(Component.RIGHT_ALIGNMENT);
            movieFooter.setBackground(Color.BLACK);
            movieFooter.setOpaque(true);
            JButton deleteButton = new JButton();
            movieFooter.add(new JLabel(movie.getDirector().getFirstName()));

//            deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            movieFooter.add(deleteButton);
            movieFooter.add(new JButton("Edit"));
            moviePanel.add(movieFooter);
            Dimension dimension = new Dimension(200, 500);
            moviePanel.setPreferredSize(dimension);
            moviePanel.setMinimumSize(dimension);
            moviePanel.setMaximumSize(dimension);
            moviePanel.setIgnoreRepaint(true);


            movies.add(moviePanel);
        });

        this.getContentPane().add(panel1, gridBagConstraints);


        ///filter panel
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.BLACK);
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 1;
        this.getContentPane().add(panel3, gridBagConstraints);

        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.add(Box.createRigidArea(new Dimension(0, 50)));
//        panel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button = new JButton("Add Movie");
        button.setAlignmentX(CENTER_ALIGNMENT);
        panel3.add(button);


        panel3.add(Box.createRigidArea(new Dimension(0, 50)));
        JLabel filtersLabel = new JLabel("Filters");
        filtersLabel.setAlignmentX(CENTER_ALIGNMENT);
        filtersLabel.setForeground(Color.white);
        panel3.add(filtersLabel);
        this.pack();
        this.setVisible(true);

        panel3.add(Box.createRigidArea(new Dimension(0, 50)));

//        JComboBox filtersBox = new JComboBox();
        JList filtersBox=new JList(Genre.values());

//        filtersBox.setModel(new DefaultComboBoxModel(new String[]{"Choose"}));
//        filtersBox.addPopupMenuListener(new PopupMenuListener() {
//            @Override
//            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
//                filtersBox.setModel(new DefaultComboBoxModel(Genre.values()));
//            }
//
//            @Override
//            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
//
//            }
//
//            @Override
//            public void popupMenuCanceled(PopupMenuEvent e) {
//
//            }
//        });
        filtersBox.setAlignmentX(CENTER_ALIGNMENT);
        Dimension dimension = new Dimension(100, 150);
        filtersBox.setPreferredSize(dimension);
        filtersBox.setMinimumSize(dimension);
        filtersBox.setMaximumSize(dimension);
//        filtersBox.setForeground(Color.white);
        panel3.add(filtersBox);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        TryUI tryUI = new TryUI();
        Dimension uidim = Toolkit.getDefaultToolkit().getScreenSize();
        tryUI.setMinimumSize(uidim);
        tryUI.setMaximumSize(uidim);
        tryUI.setPreferredSize(uidim);
        GridBagLayout layout = new GridBagLayout();
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        layout.setConstraints(tryUI.getContentPane(), gridBagConstraints);
        tryUI.getContentPane().setLayout(layout);

        //Movies Page Button
        tryUI.addMainMenuButton();


        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.GREEN);
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));


        JLabel moviesLabel = new JLabel("Movies");
        Dimension label = new Dimension(panel1.getWidth() / 2, 500);
        moviesLabel.setFont(new Font(null, Font.PLAIN, 50));
        moviesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        moviesLabel.setPreferredSize(label);
//        moviesLabel.setMaximumSize(label);
//        moviesLabel.setMinimumSize(label);
        panel1.add(moviesLabel);
        JPanel movies = new JPanel();
//        moviesLabel.setPreferredSize(label);
//        moviesLabel.setMaximumSize(label);
//        moviesLabel.setMinimumSize(label);
        movies.setBackground(Color.pink);
        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(3);
        gridLayout.setRows(-1);
        gridLayout.setHgap(50);
        gridLayout.setVgap(50);
        movies.setLayout(gridLayout);
        JScrollPane scrollPane = new JScrollPane(movies, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel1.add(scrollPane);


        MovieManager movieManager = MovieManager.getMovieManager();
        List<Movie> moviesList = movieManager.getList();
        moviesList.forEach((movie) ->
        {
            JPanel moviePanel = new JPanel();
            moviePanel.setOpaque(false);

            moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));
            moviePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            ImageIcon backIcon = new ImageIcon("src/com/movie/theater/icons/cover1.jpg");
            Image image = backIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(200, 300, java.awt.Image.SCALE_SMOOTH);// scale it the smooth way
            backIcon = new ImageIcon(newimg);  // transform it back
            moviePanel.add(new JLabel(backIcon));

            JLabel movieName = new JLabel(movie.getName(), SwingConstants.CENTER);
            Dimension movieNameDimension = new Dimension(300, 30);
            movieName.setPreferredSize(movieNameDimension);
            movieName.setMinimumSize(movieNameDimension);
            movieName.setMaximumSize(movieNameDimension);
            movieName.setToolTipText(movie.getName());
            movieName.setFont(new Font(null, Font.PLAIN, 25));
            moviePanel.add(movieName);

            JPanel movieFooter = new JPanel();
//            movieFooter.setLayout(new FlowLayout(FlowLayout.RIGHT));
            movieFooter.setPreferredSize(movieNameDimension);
            movieFooter.setMinimumSize(movieNameDimension);
            movieFooter.setMaximumSize(movieNameDimension);
            movieFooter.setAlignmentX(Component.RIGHT_ALIGNMENT);
            movieFooter.setBackground(Color.BLACK);
            movieFooter.setOpaque(true);
            JButton deleteButton = new JButton();
            movieFooter.add(new JLabel(movie.getDirector().getFirstName()));

//            deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            movieFooter.add(deleteButton);
            movieFooter.add(new JButton("Edit"));
            moviePanel.add(movieFooter);
            Dimension dimension = new Dimension(200, 500);
            moviePanel.setPreferredSize(dimension);
            moviePanel.setMinimumSize(dimension);
            moviePanel.setMaximumSize(dimension);
            moviePanel.setIgnoreRepaint(true);


            movies.add(moviePanel);
        });

        tryUI.getContentPane().add(panel1, gridBagConstraints);


        ///filter panel
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.BLACK);
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 1;
        tryUI.getContentPane().add(panel3, gridBagConstraints);

        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.add(Box.createRigidArea(new Dimension(0, 50)));
//        panel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button = new JButton("Add Movie");
        button.setAlignmentX(CENTER_ALIGNMENT);
        panel3.add(button);


        panel3.add(Box.createRigidArea(new Dimension(0, 50)));
        JLabel filtersLabel = new JLabel("Filters");
        filtersLabel.setAlignmentX(CENTER_ALIGNMENT);
        filtersLabel.setForeground(Color.white);
        panel3.add(filtersLabel);
        tryUI.pack();
        tryUI.setVisible(true);

        panel3.add(Box.createRigidArea(new Dimension(0, 50)));

//        JComboBox filtersBox = new JComboBox();
        JList filtersBox=new JList(Genre.values());

//        filtersBox.setModel(new DefaultComboBoxModel(new String[]{"Choose"}));
//        filtersBox.addPopupMenuListener(new PopupMenuListener() {
//            @Override
//            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
//                filtersBox.setModel(new DefaultComboBoxModel(Genre.values()));
//            }
//
//            @Override
//            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
//
//            }
//
//            @Override
//            public void popupMenuCanceled(PopupMenuEvent e) {
//
//            }
//        });
         filtersBox.setAlignmentX(CENTER_ALIGNMENT);
        Dimension dimension = new Dimension(100, 150);
        filtersBox.setPreferredSize(dimension);
        filtersBox.setMinimumSize(dimension);
        filtersBox.setMaximumSize(dimension);
//        filtersBox.setForeground(Color.white);
        panel3.add(filtersBox);
        tryUI.pack();
        tryUI.setVisible(true);
    }

    private void filterBoxItemStateChanged(ItemEvent e) {
        e.setSource(Genre.values());
        System.out.println("sdsd");
    }

    private void addMainMenuButton() {
        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBackground(Color.RED);
        gridBagConstraints.weightx = 0.2;
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
