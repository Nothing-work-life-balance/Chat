//package tcp.chat.multi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

class ReceiverThread extends Thread {
	Socket socket;
	ChatMain chat;
	ChatLog cl;
	Block bl;
	ReceiverThread(Socket socket, ChatMain chat) {
		this.socket = socket;
		this.chat = chat;
		this.cl = new ChatLog();
		this.bl = new Block();
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true) {
				
				// �����κ��� ���ŵ� �޽����� ����ͷ� ���
				
				String str = reader.readLine();
				String[] tmp1 = str.split(">");
				if(bl.check(tmp1[0])) {
					chat.textArea_1.append("");
					continue;
				}
				if (str == null)
					break;
				if(str.contains("NameList ")) {
					String[] tmp = str.split(" ");
					listAppend(tmp);
				} else {
					System.out.println(str);
					cl.save(str);
					chat.textArea_1.append(str + "\n");
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//채팅 인원 리스트
	private void listAppend(String[] tmp) {
		ArrayList<String> args = new ArrayList<String>();
		int i = 0;
		for(String name : tmp) {
			if(tmp[i].equals("NameList")) {
				i++;
				continue;
			}
			args.add(name);
		}
		chat.setList(args);
	}
}