package code;

// �� Ŭ���̾�Ʈ ���ӿ� ���� �ϳ��� �۵��ϴ� ������ Ŭ���� 

import java.io.BufferedReader;
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

	PerClinetThread(Socket socket) {
		this.socket = socket;
		try {
			writer = new PrintWriter(socket.getOutputStream());
			list.add(writer);
			chatter = new ChatterList();
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
			User user = new User(name, socket);
			sendAll("#" + name + " connected");
			System.out.println(name + " visit");
			chatter.setChatterList(user);
			getNameList();
			while (true) {
				String str = reader.readLine();
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
		if(chatter.isEmpty()) {
			chatter.setChatterList(null);;
		}
		ArrayList<String> user = chatter.getName();
		String res = "NameList ";
		for (String name : user) {
			res += name+" ";
		}
		sendAll(res);
	}
	
	// 채팅리스트 삭제. 채팅방 나갈 시 참가자 목록에서 삭제
	private void deleteNameList() {
		InetAddress ia = socket.getLocalAddress();
		if(chatter.isEmpty()) {
			return;
		}
		chatter.delete(ia.getHostAddress());
		ArrayList<String> user = chatter.getName();
		String res = "NameList ";
		for (String name : user) {
			res += name+" ";
		}
		sendAll(res);
	}
}