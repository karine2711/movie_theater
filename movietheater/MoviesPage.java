import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Sat Apr 24 22:34:08 AMT 2021
 */



/**
 * @author Asya
 */
public class MoviesPage extends JFrame {
    public MoviesPage() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Asya
        label1 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("text");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(269, Short.MAX_VALUE)
                    .addComponent(label1)
                    .addGap(200, 200, 200))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(label1)
                    .addContainerGap(267, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Asya
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
