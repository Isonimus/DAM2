package ticTacToe;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	
	String hostname;
    int port;
    int anchura;
    int altura;
    char[][] partida;
    Scanner scanner;
    boolean jugando;
    
    Client(String hostname, int port, int anchura, int altura) {
    	
    	this.hostname = hostname;
    	this.port = port;
    	this.anchura = anchura;
    	this.altura = altura;
    	partida = new char[4][4];
    	scanner = new Scanner(System.in);
    	launch();
    }
    
    public void launch() {
    	
    	try (Socket socket = new Socket(hostname, port)) {
        	
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            int movimiento;
            
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
 
                int respuestaServer = Integer.parseInt(reader.readLine());
                
                if(respuestaServer < (anchura * altura)) { //JUGADA NORMAL
                	
                	System.out.println("Jugada server: " + respuestaServer + "\n");
                    mover(respuestaServer, 'O');
                    
                }else if(respuestaServer  == 777) { //HAS GANADO
                	
                	System.out.println("Server: ¡Has ganado!");
                	jugando = false;
                	
                }else { //HAS PERDIDO
                	
                	System.out.println("Jugada server: " + respuestaServer + "\n");
                    mover(respuestaServer - 1000, 'O');
                    renderTablero(partida);
                    System.out.println("¡Has perdido!");
                    jugando = false;
                }
 
            } while (jugando);
 
            socket.close();
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
    
    public void iniciarPartida() {
    	
    	System.out.println("¡Nueva partida!\n");
    	
    	jugando = true;
    	
    	for(int i = 0; i < altura; i++) {
    		
    		for (int j = 0; j < anchura; j++) {
    			
    			partida[i][j] = 'v';
    		}
    	}
    }
    
    public void renderTablero(char[][] partida) {
    	
    	for(int i = 0; i < altura; i++) {
    		
    		System.out.print("|");
    		
    		for (int j = 0; j < anchura; j++) {
    			
    			if(partida[i][j] == 'v') { //CASILLA VACÍA
        			
        			System.out.print("   ");
        			
        		}else if (partida[i][j] == 'X'){ 
        			
        			System.out.print(" X ");
        			
        		}else if (partida[i][j] == 'O'){
        			
        			System.out.print(" O ");
        		}
    			
    			if(j < 3) {
    				System.out.print("|");
    			}else {
    				System.out.println("|");
    			}
    		}
    	}
    	
    	System.out.println("-----------------");
    }
    
    public void mover(int posicion, char jugador) {
    	
    	int fila = posicion / anchura;
    	int columna = posicion % anchura;
    	
    	partida[fila][columna] = jugador;
    }
 
    public static void main(String[] args) {
    	
    	Client c = new Client("localhost", 9090, 4, 4);
    	c.launch();
    }
}