import java.io.PrintWriter;
import java.util.*;

public class Block {
   String ip;
   PrintWriter writer;
   
   //차단시킨 사람 목록 저장
   static ArrayList<String> blArray;
   
   public Block() {
	   blArray = new ArrayList<String>();
   }

   public boolean check(String ip) {
	   String[] res = ip.split("-");
	   for(String k : blArray) {
		   if(k.equals(res[1])) {
			   return true;
		   }
	   }
	   return false;
   }
   
   //block list(차단시킨 사람 목록)에 추가
   public void blockList(String name) {
      this.ip = name;
      blArray.add(name);
   }
   
   //block list(차단시킨 사람 목록)에서 삭제
   public void blockListR(String name) {
      blArray.remove(name);
   }

}