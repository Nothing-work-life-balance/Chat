//채팅방 기록 저장
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class ChatLog {
	private File chatLog = null;//chatLog 저장 파일
	public ChatLog() {
		try {
			chatLog = new File("./log/chatLog.log");
			File log = new File("./log");
			if(!log.exists()) {
				log.mkdir();
			}
			if(!chatLog.exists()) {
				chatLog.createNewFile();
			}
		} catch(Exception e) {
			
		}
	}
	//현재 시간 포메팅해서 반환
	public String formating() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:MM:ss");
		return dtf.format(LocalDateTime.now());
	}
	
	// 로그 저장
	synchronized public void save(String content) {
		FileOutputStream fos = null;
		try {
			String res = formating() +" " + content + "\n";
			fos = new FileOutputStream(chatLog, true);
			byte[] data = res.getBytes(StandardCharsets.UTF_8);
			fos.write(data);
			fos.flush();
			
		} catch (IOException e) {
			e.getStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}