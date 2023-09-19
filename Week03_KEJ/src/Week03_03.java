import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Week03_03 extends JFrame {
    private JLabel carLabel;
    private int carPositionX;

    public Week03_03() {
        setTitle("자동차 움직이기");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        carPositionX = 50;

        JButton leftButton = new JButton("LEFT");
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCarLeft();
            }
        });

        JButton rightButton = new JButton("RIGHT");
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCarRight();
            }
        });

        try {
            BufferedImage originalImage = ImageIO.read(new File("puppy.jpg"));
            Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            carLabel = new JLabel(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(leftButton);
        buttonPanel.add(rightButton);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(carLabel, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void moveCarLeft() {
        if (carPositionX > 0) {
            carPositionX -= 10;
            carLabel.setLocation(carPositionX, carLabel.getY());
        }
    }

    private void moveCarRight() {
        if (carPositionX < getWidth() - carLabel.getWidth()) {
            carPositionX += 10;
            carLabel.setLocation(carPositionX, carLabel.getY());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Week03_03();
            }
        });
    }
}

