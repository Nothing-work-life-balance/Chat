
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
		frame.setBounds(100, 100, 1280, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		txtIp = new JTextField();
		txtIp.setHorizontalAlignment(SwingConstants.CENTER);
		txtIp.setFont(new Font("굴림", Font.PLAIN, 30));
		txtIp.setBounds(153, 66, 393, 70);
		frame.getContentPane().add(txtIp);
		txtIp.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel.setBounds(47, 57, 127, 86);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("굴림", Font.PLAIN, 30));
		textField.setColumns(10);
		textField.setBounds(708, 66, 283, 70);
		frame.getContentPane().add(textField);
		
		
		
		JLabel lblPort = new JLabel("PORT");
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setFont(new Font("굴림", Font.PLAIN, 30));
		lblPort.setBounds(560, 52, 136, 96);
		frame.getContentPane().add(lblPort);
		
		
		
		JButton btnNewButton = new JButton("입장");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String IpData = txtIp.getText();
				String PortData = textField.getText();
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
		
		
		
		
		
		
	}
}