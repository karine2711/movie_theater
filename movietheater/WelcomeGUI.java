import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeGUI {

    public static void showWelcome(){
        JFrame frame = new JFrame("Welcome");
        frame.setBounds(100, 100, 467, 315);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JButton button = new JButton("Go to Main Menu");
        button.setBounds(8, 9, 441, 176);
        frame.getContentPane().add(button);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                MainMenuGUI menu = new MainMenuGUI();
                menu.showMainMenu();

            }
        });



        JLabel label = new JLabel("Welcome to Movie Theater");
        label.setBounds(20, 20, 80, 70 );


        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        panel.add(button);



        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Movie Theater");
        frame.pack();
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        showWelcome();

    }
}