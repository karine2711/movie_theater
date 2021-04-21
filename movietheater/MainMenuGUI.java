import javax.swing.*;
import java.awt.*;

public class MainMenuGUI {
    public static void main(String[] args) {
        showMainMenu();

    }

    public static void showMainMenu (){
        JFrame frame = new JFrame("Welcome");
        frame.setBounds(100, 100, 467, 315);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel label = new JLabel("Main Menu");
        frame.getContentPane().add(label);

        JButton moviesButton = new JButton("Movies");
        moviesButton.setBounds(10, 10, 100, 100);
        frame.add(moviesButton);

        JButton sessionsButton = new JButton("Sessions");
        sessionsButton.setBounds(10, 200, 100, 100);
        frame.add(sessionsButton);

        frame.setVisible(true);


    }

}
