import javax.swing.*;
import java.awt.*;

public class Card extends JFrame {
    public Card() {
        setTitle("명함");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(1, 2));

        ImageIcon image = new ImageIcon("puppy.jpg");
        JLabel imageLabel = new JLabel(image);
        add(imageLabel);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 1));

        JLabel nameLabel = new JLabel("김어진");
        JLabel majorLabel = new JLabel("사이버보안 전공");
        JLabel universityLabel = new JLabel("덕성여자대학교");

        infoPanel.add(nameLabel);
        infoPanel.add(majorLabel);
        infoPanel.add(universityLabel);

        add(infoPanel);

        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Card();
        });
    }
}
