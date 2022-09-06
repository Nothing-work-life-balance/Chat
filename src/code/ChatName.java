package code;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.SwingConstants;

public class ChatName {

	private JFrame frame;
	private JTextField textField;
	private String IpData;
	private int portDataNum;
	
	//첫 화면으로부터 ip port 받아오기
	public ChatName(String IpData, int portDataNum) {
		this.IpData = IpData;
		this.portDataNum = portDataNum;
		initialize();
	}

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatName window = new ChatName();
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
	public ChatName() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 930, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 231, 255));
		panel.setBounds(12, 10, 916, 558);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nick Name");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel.setBounds(377, 175, 164, 46);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setText("닉네임 입력");
		textField.setFont(new Font("굴림", Font.PLAIN, 30));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(280, 231, 363, 79);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("돌아가기");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 30));
		btnNewButton.setBounds(12, 10, 155, 48);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
            	//프레임 꺼
				new ChatEnter();
            	frame.setVisible(false);
				
			}
		});
		
		
		JButton btnNewButton_1 = new JButton("입장하기");
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 30));
		btnNewButton_1.setBounds(725, 460, 155, 69);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Enter");
				            String name = textField.getText();
				            new ClientExample4(name, IpData, portDataNum );
				            //창 사라져
				            frame.setVisible(false);
				            
				            
//				            frame.setVisible(true);
//				            if(socket.isClosed()) {
//				            	JOptionPane.showMessageDialog(null, "연결에 실패했습니다");
//				            }else {			//연결에 성공했다면
//				            	System.out.println("else");
//				            	//프레임 꺼
//				            	
//				            }

				        }


			
		});
		
	}

}