import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exec {
    private JButton fightButton;
    private JPanel panel1;

    public Exec() {
        fightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"Hello World!");
            }
        });
    }

    public static void main (String[] args){
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Exec().panel1);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600,500);
        frame.setVisible(true);
    }
}
