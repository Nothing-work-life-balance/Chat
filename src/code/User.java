import java.net.InetAddress;
import java.net.Socket;

public class User {
	private String name;//접속 닉네임
	private int get_auth;// 권한 부여 여부(부마스터, 일반회원)
	private String ip;//접속한 기기 Ipv4 주소
	private Socket socket;
	//일반 회원 생성시 사용
	public User(String name, Socket socket) {
		try {
			InetAddress ip = socket.getInetAddress();
			this.ip = ip.getHostAddress();
			this.name = name;
			this.socket = socket;
		} catch (NullPointerException e) {
			System.out.println("ip를 찾을 수 없습니다. 다시 시도해주세요");
		} 
	}
	
	public Socket getSocket() {
		return this.socket;
	}
	
	//get_auth setter, 방장이 권한 부여시 사용
	public void setGet_auth(int get_auth) {
		this.get_auth = get_auth;
	}
	//get_auth getter
	public int getGet_auth() {
		return get_auth;
	}
	//name getter
	public String getName() {
		return this.name;
	}
	//name setter(닉네임 변경시 사용)
	public void setName(String name) {
		this.name = name;
	}
	
	//Ipv4 address getter
	public String getIp() {
		return ip;
	}
}