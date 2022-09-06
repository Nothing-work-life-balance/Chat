package code;

import java.util.ArrayList;
import java.util.List;

public class ChatterList {

	//현재 채팅방에 참가하고 있는 유저 목록
	private static List<User> chatterList = new ArrayList<User>();
	
	//참가한 채팅유저 정보 저장
	public void setChatterList(User user) {
		chatterList.add(user);
	}
	
	//채팅방에 참가하고 있는 유저 닉네임 ArrayList
	public ArrayList<String> getName() {
		ArrayList<String> name = new ArrayList<String>();
		for(User user : chatterList) {
			name.add(user.getName());
		}
		return name;
	}
	
	//채팅방 총 인원수
	public int getCount() {
		return chatterList.size();
	}
	
	//채팅방 강퇴
	// 반드시 Ipv4로 검색
	public void delete(String ip) {
		User temp = null;
		for(User user : chatterList) {
			if(user.getIp().equals(ip)) {
				temp = user;
				break;
			}
		}
		if(temp == null) System.out.println("맞는 ip가 없습니다");
		else {
			chatterList.remove(temp);
			System.out.println("삭제하였습니다");
		}
	}
	
	public boolean isEmpty() {
		boolean a = (chatterList.isEmpty()) ? true : false;
		return a;
	}
}