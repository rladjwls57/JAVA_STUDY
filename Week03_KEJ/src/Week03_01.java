import javax.swing.*;

public class Week03_01 extends JFrame{
	public Week03_01() {
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel label1 = new JLabel("이름");
		JLabel label2 = new JLabel("패스워드");
		JLabel label3 = new JLabel("이메일 주소");
		JLabel label4 = new JLabel("전화번호");
		JTextField field1 = new JTextField(15);
		JTextField field2 = new JTextField(15);
		JTextField field3 = new JTextField(15);
		JTextField field4 = new JTextField(15);
		JButton button1 = new JButton("등록하기");
		JButton button2 = new JButton("취소");
		
		panel.add(label1);
		panel.add(field1);
		panel.add(label2);
		panel.add(field2);
		panel.add(label3);
		panel.add(field3);
		panel.add(label4);
		panel.add(field4);
		panel.add(button1);
		panel.add(button2);
		
		setSize(230, 200);
		setTitle("회원 등록하기");
		setVisible(true);
	}

public static void main(String argv[]) {
	Week03_01 f  = new Week03_01();
	}
}
