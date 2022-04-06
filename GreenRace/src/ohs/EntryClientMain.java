package ohs;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;

class TCPMulframe extends JFrame {
	
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	
	class TCPSingleReceiver extends Thread{
		
		@Override
		public void run() {
		
			super.run();
		}
	
	}
	
}

public class EntryClientMain {

	public static void main(String[] args) {
		
		try {
			Socket socket = new Socket("192.168.35.10", 8888);
			System.out.println("서버 접속 성공");
			
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			
			System.out.println(ois.readObject());
			
			ois.close();
			is.close();
			socket.close();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
