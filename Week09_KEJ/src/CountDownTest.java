import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CountDownTest extends JFrame {
    private JLabel label;
    private JButton startButton;
    private JButton stopButton;
    private Counter counter;

    class Counter extends Thread {
        private volatile boolean running = true;

        public void run() {
            for (int i = 0; i <= 100; i++) {
                if (!running) {
                    return; 
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
                label.setText(i + "");
            }
        }

        public void stopCounting() {
            running = false;
        }
    }

    public CountDownTest() {
        setTitle("카운트다운");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        label = new JLabel("0");
        label.setFont(new Font("Serif", Font.BOLD, 100));

        startButton = new JButton("카운트 다시시작");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (counter == null || !counter.isAlive()) {
                    counter = new Counter();
                    counter.start();
                }
            }
        });

        stopButton = new JButton("카운트 중지");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (counter != null && counter.isAlive()) {
                    counter.stopCounting();
                }
            }
        });

        panel.add(label);
        panel.add(startButton);
        panel.add(stopButton);

        label.setBounds(0, 0, 384, 111);
        startButton.setBounds(100, 120, 100, 30);
        stopButton.setBounds(220, 120, 100, 30);
    }

    public static void main(String[] args) {
        CountDownTest frame = new CountDownTest();
        frame.setVisible(true);
    }
}
