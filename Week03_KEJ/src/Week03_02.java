import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Week03_02 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("mile->km");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel mileLabel = new JLabel("마일 입력:");
        JTextField mileTextField = new JTextField(10);
        JLabel resultLabel = new JLabel("->");
        JTextField resultTextField = new JTextField(10);
        resultTextField.setEditable(false);
        JButton convertButton = new JButton("변환");

        panel.add(mileLabel);
        panel.add(mileTextField);
        panel.add(resultLabel);
        panel.add(resultTextField);
        panel.add(convertButton);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double miles = Double.parseDouble(mileTextField.getText());
                    double kilometers = miles * 1.609344;
                    resultTextField.setText(String.format("%.2f km", kilometers));
                } catch (NumberFormatException ex) {
                    resultTextField.setText("올바른 숫자를 입력하세요.");
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
