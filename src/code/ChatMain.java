package code;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class ChatMain implements ActionListener{

	private JFrame frame;
	private JList list;
	private DefaultListModel<String> model;
	private JScrollPane scrolled;
	JTextArea textArea;
	JTextArea textArea_1;
	Socket socket;
	PrintWriter writer;
	JList<String> user_list_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChatMain window = new ChatMain();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public ChatMain(Socket socket) throws IOException {
		this.socket = socket;
		initialize();
		writer = new PrintWriter(socket.getOutputStream());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 960);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(0, 0, 275, 913);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel room = new JPanel();
		room.setBounds(0, 0, 275, 218);
		room.setBackground(new Color(255, 255, 255));
		panel.add(room);
		room.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("채팅방 이름");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(60, 84, 162, 33);
		room.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("30명");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(104, 150, 72, 26);
		room.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("돌아가기");
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton_1.setBounds(12, 10, 99, 41);
		room.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChatEnter();
				frame.setVisible(false);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 228, 275, 685);
		panel.add(scrollPane);
		
		user_list_1 = new JList<>();
		user_list_1.setFont(new Font("굴림", Font.PLAIN, 20));
		scrollPane.setViewportView(user_list_1);
		
		user_list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//하나만 선택 될 수 있도록
		
		JPanel send = new JPanel();
		send.setBackground(new Color(255, 255, 255));
		send.setBounds(283, 716, 983, 197);
		frame.getContentPane().add(send);
		send.setLayout(null);
		
		JButton btnNewButton = new JButton("전송");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 30));
		btnNewButton.setBounds(854, 25, 117, 52);
		send.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 842, 197);
		send.add(scrollPane_2);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		scrollPane_2.setViewportView(textArea);
		textArea.setLineWrap(true);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(283, 0, 983, 706);
		frame.getContentPane().add(scrollPane_1);
		
		textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea_1.setBackground(new Color(240, 254, 255));
		scrollPane_1.setViewportView(textArea_1);
		textArea_1.setLineWrap(true);
		textArea_1.setEditable(false);
//		textArea_1.setCaretPosition(textArea_1.getText().length());
//		int pos = textArea_1.getText().length();
//		textArea_1.setCaretPosition(pos);
//		textArea_1.requestFocus();
//		scrollPane_1.getVerticalScrollBar().setValue(scrollPane_1.getVerticalScrollBar().getMaximum());
		frame.setVisible(true);
		
	}
	
	public void setList(ArrayList<String> args) {
		model = new DefaultListModel<>();
		for(String name : args) {
			model.addElement(name);
		}
		user_list_1.setModel(model);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("전송")) {
			sendmsg(textArea.getText());
			textArea.setText("");
			textArea.requestFocus();
			textArea_1.setCaretPosition(textArea_1.getDocument().getLength());
		}
	}
	
	public void sendmsg(String str) {
		writer.println(str);
        writer.flush();
	}
}