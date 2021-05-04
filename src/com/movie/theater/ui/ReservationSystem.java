package com.movie.theater.ui;

import com.movie.theater.exception.AlreadyReservedException;
import com.movie.theater.exception.NotReservedException;
import com.movie.theater.model.Director;
import com.movie.theater.model.Genre;
import com.movie.theater.model.Movie;
import com.movie.theater.model.MovieSession;
import com.movie.theater.service.SerializationUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import static javax.swing.JOptionPane.YES_OPTION;

public class ReservationSystem extends JFrame {
    MovieSession session;
    JPanel seats = new JPanel();
    JTextField reserveRange = new JTextField();

    public ReservationSystem(MovieSession session) throws HeadlessException {
        this.session = session;
        reservationSystem();
    }

    public static void main(String[] args) {
        Movie movie1 = new Movie("movie 1", new Director("name1", "surname1"), Genre.ACTION);
        MovieSession session1 = new MovieSession(movie1, LocalDateTime.now(),
                Duration.ofHours(2), 15);
        ReservationSystem reservationSystem = new ReservationSystem(session1);
        reservationSystem.reservationSystem();

    }

    private void reservationSystem() {
        this.setBounds(0, 0, 700, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));


        addHeader();
        addSeats();
        addFooter();
        this.setVisible(true);
    }

    private void addHeader() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu();
        Dimension dim = new Dimension(700, 50);
        menu.setPreferredSize(dim);
        menu.setMaximumSize(dim);
        menu.setMinimumSize(dim);
        menu.setRequestFocusEnabled(true);
        ImageIcon backIcon = new ImageIcon("src/com/movie/theater/icons/back.png");

        Image image = backIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        backIcon = new ImageIcon(newimg);  // transform it back
        JMenuItem menuItem = new JMenuItem(backIcon);

        menuItem.addActionListener((ActionEvent event) -> {
            SessionsPage sessionsPage = new SessionsPage();
            sessionsPage.pack();
            sessionsPage.setVisible(true);
            dispose();
        });
        menu.add(menuItem);
        menuBar.add(menuItem);
        setJMenuBar(menuBar);
//        header.add(new JButton());
//        footer.add(new JButton("Reserve"));
//        footer.add(new JButton("Cancel"));
//        this.getContentPane().add(footer);
    }

    private void addSeats() {

        LayoutManager gridManager = new GridLayout(10, 10);

        for (int i = 0; i < 100; i++) {
            final int seatNumber = i + 1;
            JButton seat = new JButton(String.valueOf(seatNumber));
            if (session.isReserved(seatNumber)) {
                seat.setBackground(Color.RED);
            } else {
                seat.setBackground(Color.GREEN);
            }
            seat.addActionListener(
                    (ActionEvent actionEvent) -> {
                        int seatNum = Integer.parseInt(actionEvent.getActionCommand());
                        if (session.isReserved(seatNum)) {
                            showCancelBox(seat, seatNum);
                        } else {
                            showReserveBox(seat, seatNum);
                        }
                    }
            );
            seats.add(seat);
        }
        seats.setLayout(gridManager);
        seats.setSize(700, 700);
        this.getContentPane().add(seats);
    }

    private void addFooter() {
        JPanel footer = new JPanel();
        Dimension dim = new Dimension(700, 50);
        footer.setPreferredSize(dim);
        footer.setMaximumSize(dim);
        footer.setMaximumSize(dim);
        footer.setMinimumSize(dim);
        footer.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 10));
        Dimension dimension = new Dimension(100, 50);
        reserveRange.setPreferredSize(dimension);
        reserveRange.setMinimumSize(dimension);
        reserveRange.setMaximumSize(dimension);
        footer.add(reserveRange);
        JButton reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(e -> {
            String text = reserveRange.getText();
            if (text.matches("[0-9]{0,3}-[0-9]{0,3}")) {
                String[] ranges = text.split("-");
                int min = Integer.parseInt(ranges[0]);
                int max = Integer.parseInt(ranges[1]);
                for (int i = min; i <= max; i++) {
                    try {
                        session.reserve(i);
                        SerializationUtil.serializeSessions();
                    } catch (AlreadyReservedException alreadyReservedException) {
                        JOptionPane.showMessageDialog(this, alreadyReservedException.getMessage());
                    } catch (IOException ioException) {
                        showExitCase();
                    }
                    seats.getComponent(i - 1).setBackground(Color.RED);
                }
            }


        });

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> {
            String[] ranges = reserveRange.getText().split("-");
            int min = Integer.parseInt(ranges[0]);
            int max = Integer.parseInt(ranges[1]);
            for (int i = min; i <= max; i++) {
                try {
                    session.cancelReservation(i);
                    seats.getComponent(i - 1).setBackground(Color.GREEN);
                    SerializationUtil.serializeSessions();
                } catch (NotReservedException notReservedException) {
                    notReservedException.printStackTrace();
                } catch (IOException ioException) {
                    showExitCase();
                }
            }
        });
        footer.add(reserveButton);
        footer.add(cancel);
        this.getContentPane().add(footer);
    }

    private void showReserveBox(JButton seat, int seatNumber) {
        int response = JOptionPane.showConfirmDialog(this, "Reserve?", "Reserve", JOptionPane.YES_NO_OPTION);
        if (response == YES_OPTION) {
            try {
                session.reserve(seatNumber);
                seat.setBackground(Color.RED);
                SerializationUtil.serializeSessions();
            } catch (AlreadyReservedException e) {
                JOptionPane.showMessageDialog(this, "Place is already reserved!", "Error!", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                showExitCase();
            }

        } else {
            System.out.println("No");
        }
    }

    private void showCancelBox(JButton seat, int seatNumber) {
        int response = JOptionPane.showConfirmDialog(this, "Cancel?", "Cancel", JOptionPane.YES_NO_OPTION);
        if (response == YES_OPTION) {
            try {
                session.cancelReservation(seatNumber);
                seat.setBackground(Color.GREEN);
                SerializationUtil.serializeSessions();
            } catch (NotReservedException e) {
                JOptionPane.showMessageDialog(this, "Place is not reserved!", "Error!", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                showExitCase();
            }
        } else {
            System.out.println("No");
        }
    }

    private void showExitCase() {
        JOptionPane.showMessageDialog(this, "Sorry something went wrong! The program need to exit");
        System.exit(-1);
    }

    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

}
