package clientSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;

/**
 * 
 *  - Socket(InetAddress address, int port)

	- Socket(String host, int port)

	- Socket(InetAddress address, int port, InetAddress localAddr, int localPort)
 *
 */

public class TestClientSocket {
	
	Socket socket;
	OutputStream output;
	InputStream input;
	
	public TestClientSocket() {
		
		super();
	}
	
	public void createSocket(String address, int port){
		
		try {
			
			socket = new Socket(address, port);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void sendData(String message) {
		
		try {
			
			output = socket.getOutputStream();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		PrintWriter writer = new PrintWriter(output, true); //TRUE: AUTOFLUSH
		writer.println(message);
	}
	
	public void readData() {
		
		try {
			
			input = socket.getInputStream();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		
		try {
			
			String line = reader.readLine();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    // reads a line of text
	}
	
	public void closeSocket() {
		
		try {
			
			socket.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		TestClientSocket t = new TestClientSocket();
		
		t.createSocket("google.com", 80);
		t.sendData("This is a message sent to the server");
		t.readData();
		t.closeSocket();

	}

}
