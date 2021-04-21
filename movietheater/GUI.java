import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GUI {

    public GUI(){
        JFrame frame = new JFrame();
        JButton button = new JButton("Go to Main Menu");
        JLabel label = new JLabel("Welcome to Movie Theater");
        label.setBounds(20, 20, 80, 70 );
//        label.setVerticalAlignment(JLabel.TOP);
//        label.setPreferredSize(new Dimension(250, 100));
//        label.setForeground(new Color(48, 206, 201));


        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        panel.add(button);



        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Movie Theater");
        frame.pack();
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        new GUI();

    }
}
