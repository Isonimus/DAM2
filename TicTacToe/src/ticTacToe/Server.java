package ticTacToe;

import java.io.*;
import java.net.*;
 
/**
 * This program demonstrates a simple TCP/IP socket server that echoes every
 * message from the client in reversed form.
 * This server is multi-threaded.
 *
 * @author www.codejava.net
 */
public class Server {
 
    public static void main(String[] args) {
        
    	//if (args.length < 1) return;
    	
        int port = Integer.parseInt("9090");
        System.out.println("Starting server...");
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Server is listening on port " + port);
 
            while (true) {
            	
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket.getInetAddress() + ":" + socket.getPort());
 
                new ServerThread(socket).start();
            }
 
        } catch (IOException ex) {
        	
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
            
        }
    }
}