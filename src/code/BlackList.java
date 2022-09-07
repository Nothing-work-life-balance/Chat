import java.util.ArrayList;
public class BlackList {
	// server java file
	// 강퇴기능 클래스 => 영구차단
	
	private static ArrayList<User> blackList = new ArrayList<User>();
	
	//블랙리스트 추가
	synchronized public void appned(User user) {
		blackList.add(user);
	}
	
	//blackList 삭제
	synchronized public void delete(User user) {
		blackList.remove(user);
	}
	
	//blackList 내역 출력(nickname과 ipv4 주소)
	public void getList() {
		for(User user : blackList) {
			String name = user.getName();
			String ip = user.getIp();
			System.out.println("이름: " + name + "\t" + "Ip: " + ip);
		}
	}
	
	// search메소드 오버로딩
	// search(User user), search(String ip)
	// blackList 유무 검색(User Object로 검색)
	// true => List에 있음, false => List에 없음
	public boolean search(User user) {
		boolean a = (blackList.contains(user)) ? true : false;
		return a;
	}
	
	// blackList 유무 검색(Ipv4 Address로 검색)
	// true => List에 있음, false => List에 없음
	// 채팅방 접속시 필터링 거칠 때 사용 권장
	public boolean search(String ip) {
		boolean tmp = false;
		for(User user : blackList) {
			if(user.getIp().equals(ip)) {
				tmp = true;
				break;
			}
		}
		return tmp;
	}
	
	//BlackList가 완전히 빈 상태인지 확인
	//true = empty, false = non empty
	public boolean isEmpty() {
		return blackList.isEmpty();
	}
}