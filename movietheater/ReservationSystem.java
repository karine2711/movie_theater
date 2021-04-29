import static javax.swing.JOptionPane.YES_OPTION;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReservationSystem extends JFrame {
    MovieSession session;

    public ReservationSystem(MovieSession session) throws HeadlessException {
        this.session = session;
    }

    public static void main(String[] args) {
        Movie movie1 = new Movie("movie 1", new Director("name1", "surname1"), Genre.ACTION);
        MovieSession session1 = new MovieSession(movie1, LocalDateTime.now(),
                Duration.ofHours(2), 15);
        ReservationSystem reservationSystem = new ReservationSystem(session1);
        reservationSystem.resevationSystem();

    }

    private void resevationSystem() {
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
        ImageIcon backIcon = new ImageIcon(getClass().getResource("icons/back.png"));

        Image image = backIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        backIcon = new ImageIcon(newimg);  // transform it back
        JMenuItem menuItem = new JMenuItem(backIcon);

        menuItem.addActionListener((ActionEvent event) -> {
            System.out.println("Back");
        });
        menu.add(menuItem)
        ;
        menuBar.add(menuItem);
        setJMenuBar(menuBar);
//        header.add(new JButton());
//        footer.add(new JButton("Reserve"));
//        footer.add(new JButton("Cancel"));
//        this.getContentPane().add(footer);
    }

    private void addSeats() {
        JPanel seats = new JPanel();
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
        footer.setMinimumSize(dim);
        footer.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 10));
        footer.add(new JTextField(""));
        footer.add(new JButton("Reserve"));
        footer.add(new JButton("Cancel"));
        this.getContentPane().add(footer);
    }

    private void showReserveBox(JButton seat, int seatNumber) {
        int response = JOptionPane.showConfirmDialog(this, "Reserve?", "Reserve", JOptionPane.YES_NO_OPTION);
        if (response == YES_OPTION) {
            try {
                session.reserve(seatNumber);
                seat.setBackground(Color.RED);
            } catch (AlreadyReservedException e) {
                JOptionPane.showMessageDialog(this, "Place is already reserved!", "Error!", JOptionPane.ERROR_MESSAGE);
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
            } catch (NotReservedException e) {
                JOptionPane.showMessageDialog(this, "Place is not reserved!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("No");
        }
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
