
import java.net.*;
class ClientExample4 {
	private String nickName;
	private String IpData;
	private int portDataNum;
	
	public ClientExample4() {};
	
	public ClientExample4(String name,String IpData, int portDataNum) {
		this.nickName = name;
		this.IpData = IpData;
		this.portDataNum = portDataNum;
		EnterServer();
		
	}
	
	public void EnterServer() {
        try {
	    // ������ ����
            Socket socket = new Socket(IpData, portDataNum);

            System.out.println(socket.isClosed());
            
            
            if(socket.isClosed()) {
            	System.out.println("close socket");
            }else {
            	System.out.println("connected socket");
            	
            	
            	ChatMain chat =new ChatMain(socket);    
            	
            	Thread thread1 = new SenderThread(socket, nickName,chat);
            	Thread thread2 = new ReceiverThread(socket,chat);
            	
            	thread1.start();
            	thread2.start();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}