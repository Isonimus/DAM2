package ticTacToe;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	
	String hostname;
    int port;
    char[] partida;
    Scanner scanner;
    
    Client(String hostname, int port) {
    	
    	this.hostname = hostname;
    	this.port = port;
    	partida = new char[16];
    	scanner = new Scanner(System.in);
    	launch();
    }
    
    public void launch() {
    	
    	try (Socket socket = new Socket(hostname, port)) {
        	
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            int movimiento;
            String text = ""; //CREAR SALIDA
            
            iniciarPartida();
 
            do {
            	
            	renderTablero(partida);
            	
            	System.out.println("Tu jugada: ");
                movimiento = scanner.nextInt();
                mover(movimiento, 'X');
                renderTablero(partida);
                writer.println(movimiento);
 
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
                String respuesta = reader.readLine();
                System.out.println("Jugada server: " + respuesta + "\n");
                mover(Integer.parseInt(respuesta), 'O');
 
            } while (!text.equals("bye"));
 
            socket.close();
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
    
    public void iniciarPartida() {
    	
    	System.out.println("¡Nueva partida!\n");
    	
    	for(int i = 0; i < partida.length; i++) {
    		
    		partida[i] = 'v';
    	}
    }
    
    public void renderTablero(char[] partida) {
    	
    	int contador = 0;
    	
    	for(int i = 0; i < partida.length; i++) {
    		
    		System.out.print("|");
    		
    		if(partida[i] == 'v') { //CASILLA VACÍA
    			
    			System.out.print("   ");
    			
    		}else if (partida[i] == 'X'){ 
    			
    			System.out.print(" X ");
    			
    		}else if (partida[i] == 'O'){
    			
    			System.out.print(" O ");
    		}
    		
    		contador++;
    		
    		if(contador == 4) {
    			
    			contador = 0;
    			System.out.println("|");
    		}
    	}
    	
    	System.out.println("-----------------");
    }
    
    public void mover(int posicion, char jugador) {
    	
    	partida[posicion] = jugador;
    }
 
    public static void main(String[] args) {
    	
    	Client c = new Client("localhost", 9090);
    	c.launch();
    }
}