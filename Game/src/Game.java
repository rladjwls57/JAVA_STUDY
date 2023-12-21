import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Game extends JFrame implements KeyListener, ActionListener {

    private static final long serialVersionUID = 1L;

    private JLabel timeLabel;
    private JLabel messageLabel;
    private JButton startButton;
    private JLabel catLabel;
    private JLabel mouseLabel;
    private JLabel[] cheeseLabels;

    private int remainingCheese;
    private int timeRemaining;

    private Thread catThread;
    private Timer timer;

    private boolean gameEnded = false;
    private Point mouseLocation;

    private boolean gameStarted = false;

    public Game() {
        setTitle("Mouse Game");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        timeLabel = new JLabel("시간: 30     남은 치즈: 60"); 
        timeLabel.setBounds(820, 10, 150, 30);
        add(timeLabel);

        messageLabel = new JLabel("고양이를 피하고 치즈를 먹으세요!");
        messageLabel.setBounds(10, 10, 300, 30);
        add(messageLabel);

        startButton = new JButton("시작");
        startButton.setBounds(450, 10, 100, 30);
        startButton.addActionListener(this);
        add(startButton);

        catLabel = new JLabel(new ImageIcon(loadImage("cat1.png")));
        catLabel.setBounds(10, 50, 50, 50);
        add(catLabel);

        mouseLabel = new JLabel(new ImageIcon(loadImage("mouse4.png")));
        mouseLabel.setBounds(475, 375, 50, 50);
        add(mouseLabel);

        cheeseLabels = new JLabel[60];
        for (int i = 0; i < 60; i++) {
            cheeseLabels[i] = new JLabel(new ImageIcon(loadImage("cheese.png")));
            cheeseLabels[i].setSize(30, 30);
            add(cheeseLabels[i]);
            placeCheeseRandomly(cheeseLabels[i]);
        }

        remainingCheese = 60;
        timeRemaining = 30;

        mouseLocation = new Point(mouseLabel.getLocation());

        addKeyListener(this);
        setFocusable(true);
    }

    private BufferedImage loadImage(String fileName) {
        try {
            URL resource = getClass().getResource("/" + fileName);
            if (resource != null) {
                BufferedImage image = ImageIO.read(resource);
                int width = image.getWidth();
                int height = image.getHeight();
                int scaledWidth = 50;
                int scaledHeight = (int) ((double) scaledWidth / width * height);
                return resizeImage(image, scaledWidth, scaledHeight);
            } else {
                System.err.println("리소스를 찾을 수 없습니다: " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();
        return resizedImage;
    }

    private void placeCheeseRandomly(JLabel cheeseLabel) {
        Random random = new Random();

        int maxX = getWidth() - 2 * cheeseLabel.getWidth();
        int maxY = getHeight() - 2 * cheeseLabel.getHeight() - 50;

        int x = random.nextInt(maxX);
        int y = random.nextInt(maxY) + 50;

        cheeseLabel.setLocation(x, y);
    }

    private void moveCatTowardsMouse() {
        Point catLocation = catLabel.getLocation();
        Point mouseLocation = mouseLabel.getLocation();

        SwingUtilities.invokeLater(() -> {
            try {
                if (catLocation.x < mouseLocation.x) {
                    catLocation.x += 30;
                } else if (catLocation.x > mouseLocation.x) {
                    catLocation.x -= 30;
                }

                if (catLocation.y < mouseLocation.y) {
                    catLocation.y += 30;
                } else if (catLocation.y > mouseLocation.y) {
                    catLocation.y -= 30;
                }

                catLabel.setLocation(catLocation);
                checkCollision();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void moveCat(int direction) {
        Random random = new Random();
        Point catLocation = new Point(catLabel.getLocation());

        ImageIcon catImage1 = new ImageIcon(loadImage("cat1.png"));
        ImageIcon catImage2 = new ImageIcon(loadImage("cat2.png"));

        try {
            switch (direction) {
                case 0: 
                    catLocation.x -= 30;
                    catLabel.setIcon(catImage1);
                    break;
                case 1: 
                    catLocation.x += 30;
                    catLabel.setIcon(catImage2);
                    break;
                case 2: 
                    catLocation.y -= 30;
                    break;
                case 3:
                    catLocation.y += 30;
                    break;
                default:
            }

            SwingUtilities.invokeAndWait(() -> {
                catLabel.setLocation(catLocation);
                checkCollision();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void moveMouse(int keyCode) {
        if (gameStarted) { 
            if (!gameEnded) {
                ImageIcon mouseImage1, mouseImage2, mouseImage3, mouseImage4;
                mouseImage1 = new ImageIcon(loadImage("mouse1.png"));
                mouseImage2 = new ImageIcon(loadImage("mouse2.png"));
                mouseImage3 = new ImageIcon(loadImage("mouse3.png"));
                mouseImage4 = new ImageIcon(loadImage("mouse4.png"));

                Point mouseLocation = new Point(this.mouseLocation);

                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        if (mouseLocation.x - 50 >= 0) {
                            mouseLocation.x -= 50;
                            mouseLabel.setIcon(mouseImage4);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (mouseLocation.x + 50 <= getWidth() - mouseLabel.getWidth()) {
                            mouseLocation.x += 50;
                            mouseLabel.setIcon(mouseImage2);
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (mouseLocation.y - 50 >= 50) {
                            mouseLocation.y -= 50;
                            mouseLabel.setIcon(mouseImage3);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (mouseLocation.y + 50 <= getHeight() - mouseLabel.getHeight()) {
                            mouseLocation.y += 50;
                            mouseLabel.setIcon(mouseImage1);
                        }
                        break;
                    default:
                }

                SwingUtilities.invokeLater(() -> {
                    this.mouseLocation = mouseLocation;
                    mouseLabel.setLocation(mouseLocation);
                    checkCollision();
                });
            }
        }
    }

    private void checkCollision() {
        Rectangle mouseRect = mouseLabel.getBounds();
        Rectangle catRect = catLabel.getBounds();

        for (JLabel cheeseLabel : cheeseLabels) {
            Rectangle cheeseRect = cheeseLabel.getBounds();
            if (mouseRect.intersects(cheeseRect) && cheeseLabel.isVisible()) {
                cheeseLabel.setVisible(false);
                remainingCheese--;
                if (remainingCheese == 0) {
                    showMessage("성공");
                    endGame();
                    return;
                }
            }
        }

        if (remainingCheese > 0 && mouseRect.intersects(catRect)) {
            showMessage("게임 오버");
            endGame();
        }

        SwingUtilities.invokeLater(() -> timeLabel.setText("시간: " + timeRemaining + "     남은 치즈: " + remainingCheese));
    }

    private void updateTime(ActionEvent e) {
        if (!Thread.interrupted()) {
            SwingUtilities.invokeLater(() -> {
                timeRemaining--;
                timeLabel.setText("시간: " + timeRemaining + "     남은 치즈: " + remainingCheese);
                if (timeRemaining <= 0) {
                    showMessage("게임 오버");
                    endGame();
                }
            });
        }
    }

    private void endGame() {
        gameEnded = true; 
        gameStarted = false;
        if (catThread != null) {
            catThread.interrupt();
        }
        if (timer != null) {
            timer.stop();
        }
        startButton.setEnabled(true);
    }

    private void showMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            messageLabel.setText(message);
            repaint();
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        moveMouse(keyCode);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startButton.setEnabled(false);
            gameEnded = false; 
            gameStarted = true; 
            catThread = new Thread(() -> {
                Random random = new Random();
                while (timeRemaining > 0 && remainingCheese > 0) {
                    moveCatTowardsMouse();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        break; 
                    }
                }
                if (remainingCheese == 0) {
                    timeLabel.setText("시간: " + timeRemaining + "     남은 치즈: 0");
                    showMessage("성공");
                } else {
                    showMessage("게임 오버");
                }
                endGame();
            });

            catThread.start();
            timer = new Timer(1000, this::updateTime);
            timer.start();
            requestFocus();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            game.setVisible(true);
        });
    }
}
