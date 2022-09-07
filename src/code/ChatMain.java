
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
	JPopupMenu popupMenu_me;
	JPopupMenu popupMenu_other;
	JPopupMenu popupMenu_king;
	String myname;
	JMenuItem menu1,menu2,menu3,menu4,menu5,menu6;
	int count = 0;
	JLabel lblNewLabel_1;
	String select_name;
	Block b = new Block();
	String title;

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
		
		
		//방이름 설정 기능
		if(title == null) title = "방이름설정";
		JLabel lblNewLabel = new JLabel(title);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(60, 84, 162, 33);
		room.add(lblNewLabel);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {	//만약 마우스 두번왼쪽클릭한다면
					JLabel la = (JLabel)e.getSource();	//이벤트를 발생시킨 객체의 위치값을 가져온다. 
					String label_default = la.getText();	//위치값에 텍스트를 띄운다.
					JOptionPane aa=new JOptionPane();	//팝업창을 하나띄워
					String user_title=aa.showInputDialog("방이름을 무엇으로 바꾸시겠습니까?");
					if(user_title != null) {
						la.setText(user_title);
//						if(myname.substring(myname.length()-3).equals("cap")) {
//							System.out.println("방장이 이름을 바꾸었습니다.");
//							title = user_title;
//							System.out.println(Roominfo.title);
//						}
					}
					
				}
			}
		});
		
		lblNewLabel_1 = new JLabel(count+"명");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(104, 150, 72, 26);
		room.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("돌아가기");
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton_1.setBounds(12, 10, 99, 41);
		room.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 228, 275, 685);
		panel.add(scrollPane);
		
		user_list_1 = new JList<>();
		user_list_1.setFont(new Font("굴림", Font.PLAIN, 20));
		scrollPane.setViewportView(user_list_1);
		
		user_list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//하나만 선택 될 수 있도록
		user_list_1.addMouseListener(new MouseAdapter() {	//마우스 헨들러
			public void mouseClicked(MouseEvent e) {	// 마우스 클릭한다면
//				 String value = (String)list.getModel().getElementAt(list.locationToIndex(e.getPoint()));
				if (e.getClickCount() == 2) {	//두번 클릭한다면
				 int index = user_list_1.locationToIndex(e.getPoint());		//눌렀을때 jlist에 회원들을 클릭했을때 그 회원의 번호를 알려준다.
//				 if (index >= 0) {
					 String o = user_list_1.getModel().getElementAt(index); // 이름을받아
					 select_name = o;	//선택한 이름 select_name으로 받아줘 나중에 필요
					 if(o.equals(myname + " (나)")) {	//나를  클릭한다면
						 popupMenu_me.show(user_list_1, e.getX(), e.getY());	//팝업창.띄워 그리고 xy좌표를 보여줘
//					 }else if(o.equals(myname+"-cap (나)")){
//						 popupMenu_me.show(user_list_1, e.getX(), e.getY());
					 }else if(myname.substring(myname.length()-3).equals("cap") && !o.equals(myname + " (나)")){	//내가 방장일경우
						 popupMenu_king.show(user_list_1, e.getX(), e.getY());	//방장만 쓸수 있는 팝업을 띄워준다.
					 }else {
						 popupMenu_other.show(user_list_1, e.getX(), e.getY());	//다른 사람을 클릭했을때

					 }
//			     }
				}
//				 System.out.println(value);
			}
		});
		
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
		
		popupMenu_me = new JPopupMenu();
		popupMenu_me.add(menu1= new JMenuItem("이름 바꾸기"));
		
		popupMenu_other = new JPopupMenu();
		popupMenu_other.add(menu2= new JMenuItem("귓속말"));
		popupMenu_other.add(new JPopupMenu.Separator());
		popupMenu_other.add(menu3 = new JMenuItem("차단"));
		
		popupMenu_king = new JPopupMenu();
		popupMenu_king.add(menu4= new JMenuItem("귓속말"));
		popupMenu_king.add(new JPopupMenu.Separator());
		popupMenu_king.add(menu5 = new JMenuItem("차단"));
		popupMenu_king.add(new JPopupMenu.Separator());
		popupMenu_king.add(menu6 = new JMenuItem("강퇴"));
		
		menu1.addActionListener(this);
		menu2.addActionListener(this);
		menu3.addActionListener(this);
		menu4.addActionListener(this);
		menu5.addActionListener(this);
		menu6.addActionListener(this);

		
		frame.setVisible(true);
		
	}
	
	//리스트 왼쪽 멤버 리스트
	//model = list라고 생각하면 편할까?? 과연???
	public void setList(ArrayList<String> args) {
		model = new DefaultListModel<>();
		count =0;
		for(String name : args) { //사람 수 만큼 세고
			count++;//사람 수 올려줘
			
			if(name.equals(myname)) {	//이름이 내 이름이랑 같다면
				model.addElement(name+" (나)");	//내 이름과 (나) 표시를 해줘
			}else if(name.equals(myname+"-cap")){	//방장은 이름뒤에 cap이 있다면.
				model.addElement(name+" (나)");	//(나) 를 붙여줘
				myname = name;
			}else {
				model.addElement(name);	//모델에 이름추가해
			}
		}
		user_list_1.setModel(model);
		lblNewLabel_1.setText(count+"명");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		System.out.println(str);
		if(str.equals("전송")) {
			if(!textArea.getText().equals("")) {	//전송버튼 눌렀을때 아무것도 안적혀있으면 보내지마라
				sendmsg(textArea.getText());
				textArea.setText("");
				textArea.requestFocus();	//키보드 커서 위치 초기화 , 입력하면 입력한 커서에 그대로 위치하므로 처음위치로 보내줘야한다.
				textArea_1.setCaretPosition(textArea_1.getDocument().getLength());
			}
			
		}else if(str.equals("강퇴")) {	//강퇴를 눌렀다면
			String msg = "/force "+select_name;
			writer.println(msg);
			writer.flush();	//전송
			
		}else if(str.equals("귓속말")) {	//귓속말
			textArea.setText("/whispher "+select_name);	// 위와같이 
			textArea.requestFocus(); //처음에 커서로
		}else if(str.equals("차단")) {	//차단버튼
			String[] str_name = select_name.split("-");	//잘라, ip만받을 수 있도록
			b.blockList(str_name[1]);
		}else if(str.equals("이름 바꾸기")) {
			System.out.println(select_name + "이름을 바꿉니다.");
		}
	}
	
	public void sendmsg(String str) {
	      String[] a = str.split(" ");
	      String res = "";
	      if (a[0].equals("/whispher")) {
	         int i = 0;
	         for (String b : a) {
	            if (i < 2) {
	               i++;
	               continue;
	            }
	            res += b + " ";
	         }
	         textArea_1.append(a[1] + "님에게 귓속말>" + res + "\n");
	      }
	      writer.println(str);
	      writer.flush();
	   }
	
	class PopUpDemo extends JPopupMenu {
		JMenuItem anItem;
		public PopUpDemo() {
			anItem = new JMenuItem("Click Me!");
			add(anItem);
		}
	}
	

}