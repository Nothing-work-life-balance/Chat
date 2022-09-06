package code;


import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ChatEnter {

	private JFrame frame;
	private JTextField txtIp;
	private JTextField textField;
	
	// [ IP number, PORT Number]
	private String  []itemBox =  new String[2];

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatEnter window = new ChatEnter();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChatEnter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 960);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		txtIp = new JTextField();
		txtIp.setHorizontalAlignment(SwingConstants.CENTER);
		txtIp.setFont(new Font("굴림", Font.PLAIN, 30));
		txtIp.setText("192.168.0.90");
		txtIp.setBounds(153, 66, 393, 70);
		frame.getContentPane().add(txtIp);
		txtIp.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel.setBounds(47, 57, 127, 86);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		
		
		// Nickname panel // hide → ip, port cheack → true → setVisible(true)
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(230, 231, 255));
		panel2.setBounds(163, 94, 916, 558);
		frame.getContentPane().add(panel2);
		panel2.setLayout(null);
		panel2.setVisible(false);
		
		
		
		
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("9002");
		textField.setFont(new Font("굴림", Font.PLAIN, 30));
		textField.setColumns(10);
		textField.setBounds(708, 66, 283, 70);
		frame.getContentPane().add(textField);
		
		
		
		JLabel lblPort = new JLabel("PORT");
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setFont(new Font("굴림", Font.PLAIN, 30));
		lblPort.setBounds(560, 52, 136, 96);
		frame.getContentPane().add(lblPort);
		
		
		
		JButton btnNewButton = new JButton("추가");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String IpData = txtIp.getText();
				String PortData = textField.getText();
				//String 값은 int 값으로 전환해서 보내준다.
				int portDataNum = Integer.parseInt(PortData);
				System.out.print(IpData+"   "+PortData);
				System.out.println("로 입장합니다");
				//닉네임 정하는 페이지 열어서 
				new ChatName(IpData, portDataNum);
				frame.setVisible(false);

				
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 30));
		btnNewButton.setBounds(1040, 65, 187, 70);
		frame.getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(101, 196, 1126, 618);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(5,1,10,10));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 128, 255));
		panel.add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("IP : 192.168.0.90");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 30));
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("PORT : 9002");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 30));
		panel_1.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Enter");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("click Enter");
				//지정 ip와 port 서버가 있다면 옮겨줘야한다.
				
			}
		});
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 30));
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Delete");
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1_1.setFont(new Font("굴림", Font.PLAIN, 30));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//델리
				System.out.println("click del");
			}
		});
		panel_1.add(btnNewButton_1_1);
		
		
		

		
	}
		
	
	//리스트값 받아서 추가
	public void addItem() {
		
	}
		
}