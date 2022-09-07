// �� Ŭ���̾�Ʈ ���ӿ� ���� �ϳ��� �۵��ϴ� ������ Ŭ���� 

//package tcp.chat.multi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PerClinetThread extends Thread {

	// ArrayList ��ü�� ���� �����尡 �����ϰ� ������ �� �ִ� ����ȭ�� ����Ʈ�� ����ϴ�.
	static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<PrintWriter>());
	ChatterList chatter;
	Socket socket;
	PrintWriter writer;
	BlackList bl;
	PerClinetThread(Socket socket) {
		this.socket = socket;
		try {
			writer = new PrintWriter(socket.getOutputStream());
			list.add(writer);
			chatter = new ChatterList();
			bl = new BlackList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void run() {
		String name = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// ���ŵ� ù��° ���ڿ��� ��ȭ������ ����ϱ� ���� ����
			name = reader.readLine();
			//true == 블랙리스트에 있음
			if(filtering(name)) {
				System.out.println("차단되었습니다");
				return;
			}
			User user = new User(name, socket);
			sendAll("#" + name + " connected");
			System.out.println(name + " visit");
			chatter.setChatterList(user);
			getNameList();
			while (true) {
				String str = reader.readLine();
				if(forceUser(str)) {
					continue;
				} else if(whis(str)) {
					new Whispher(name, str);
					continue;
				}
				if (str == null)
					break;
				sendAll(name + ">" + str); // ���ŵ� �޽��� �տ� ��ȭ���� �ٿ��� ��� Ŭ���̾�Ʈ�� �۽�
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			list.remove(writer);
			sendAll("#" + name + " quit"); // ����ڰ� ä���� �����ߴٴ� �޽����� ��� Ŭ���̾�Ʈ�� �����ϴ�.
			deleteNameList();
			try {
				socket.close();
			} catch (Exception ignored) {
			}
		}
	}

	// ������ ����Ǿ� �ִ� ��� Ŭ���̾�Ʈ�� �Ȱ��� �޽����� �����ϴ�.
	private void sendAll(String str) {
		for (PrintWriter writer : list) {
			writer.println(str);
			writer.flush();
		}
	}

	// 채팅 리스트 추가. 이 메소드 실행 시 현재 참가한 목록 볼 수 있음
	private void getNameList() {
		chatter.setAuth();
		ArrayList<String> user = chatter.getName();
		String res = "NameList ";
		int i = 0;
		for (String name : user) {
			if (i == 0) {
				res += name + "-cap" + " ";
				i++;
			} else {
				res += name + " ";
			}
		}
		sendAll(res);
	}
	
	// 블랙리스트 등재 검색
	private boolean filtering(String name) {
		String[] ip = name.split("-");
		boolean tmp = false;
		tmp = bl.search(ip[1]);
		return tmp;
	}
	
	// 귓속말
	public boolean whis(String str) {
		String[] tmp = str.split(" ");
		if(!tmp[0].equals("/whispher")) {
			return false;
		}
		return true;
	}
	
	// 블랙리스트 등록, 영구차단
	public boolean forceUser(String str) {
		String[] tmp = str.split(" ");
		if(!tmp[0].equals("/force")) {
			return false;
		}
		String[] getIp = tmp[1].split("-");
		sendAll("User forced");
		chatter.setBalckList(getIp[1]);
		return true;
	}
	
	// 채팅리스트 삭제. 채팅방 나갈 시 참가자 목록에서 삭제
	private void deleteNameList() {
		InetAddress ia = socket.getInetAddress();
		if (chatter.isEmpty()) {
			return;
		}
		chatter.delete(ia.getHostAddress());
		ArrayList<String> user = chatter.getName();
		String res = "NameList ";
		int i = 0;
		for (String name : user) {
			if (i == 0) {
				res += name + "-cap" + " ";
				i++;
			} else {
				res += name + " ";
			}
		}
		sendAll(res);
	}
	
	class Whispher {
		ChatterList cl;
		Socket socket;
		String name;
		String sendName;
		public Whispher(String sendName, String content) {
			this.sendName = sendName;
			String res = getContent(content);
			User user = getUser();
			this.socket = user.getSocket();
			send(res);
		}
		
		synchronized private void send(String res) {
			PrintWriter pw = null;
			try {
			pw = new PrintWriter(socket.getOutputStream());
			pw.println(sendName + "님으로부터 귓속말>" + res);
			pw.flush();
			} catch(IOException e) {
				
			}
		}
		
		// get content
		private String getContent(String str) {
			String[] tmp = str.split(" ");
			String res = "";
			int i = 0;
			for(String temp : tmp) {
				if(i == 0 || i == 1) {
					if(i == 1) {
						this.name = temp;
						i++;
					}
					i++;
					continue;
				}
				res += temp + " ";
			}
			return res;
		}
		
		// get user object 
		private User getUser() {
			ChatterList cl = new ChatterList();
			String[] tmp = name.split("-");
			User user = cl.getUser(tmp[1]);
			return user;
		}
		
	}
}