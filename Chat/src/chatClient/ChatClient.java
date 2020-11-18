package chatClient;

import java.net.*;
import java.io.*;
 
/**
 * This is the chat client program.
 * Type 'bye' to terminte the program.
 *
 * @author www.codejava.net
 */
public class ChatClient {
	
    private String hostname;
    private int port;
    private String userName;
 
    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }
 
    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);
 
            System.out.println("Conexión establecida con el servidor de chat.");
 
            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();
 
        } catch (UnknownHostException ex) {
            System.out.println("Servidor no encontrado: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
 
    }
 
    void setUserName(String userName) {
        this.userName = userName;
    }
 
    String getUserName() {
        return this.userName;
    }
 
    public static void main(String[] args) {
        //if (args.length < 2) return;
 
        //String hostname = args[0];
    	String hostname = "172.16.1.10";
        //int port = Integer.parseInt(args[1]);
    	int port = 8989;
 
        ChatClient client = new ChatClient(hostname, port);
        client.execute();
    }
}
