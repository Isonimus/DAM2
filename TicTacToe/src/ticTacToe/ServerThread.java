package ticTacToe;

import java.io.*;
import java.net.*;
 
public class ServerThread extends Thread {
	
    private Socket socket;
    char[] partida = new char[16];
 
    public ServerThread(Socket socket) {
    	
        this.socket = socket;
        iniciarPartida();
    }
    
    public void iniciarPartida() {
    	
    	System.out.println("¡Nueva partida!");
    	
    	for(int i = 0; i < partida.length; i++) {
    		
    		partida[i] = 'v';
    	}
    }
    
    public int jugar() {
    	
    	boolean elegido = false;
    	int movimiento = 0;
    	
    	while(!elegido) {
    		
    		movimiento = (int) (Math.random() * (partida.length -1));
    		
    		if(partida[movimiento] == 'v') {
    			
    			elegido = true;
    		}
    	}

    	return movimiento;
    }
    
    public void mover(int posicion, char jugador) {
    	
    	partida[posicion] = jugador;
    }
 
    public void run() {
    	
        try {
        	
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
 
            String movimientoJugador;
 
            do {
            	
                movimientoJugador = reader.readLine();
                mover(Integer.parseInt(movimientoJugador), 'X');
                
                int movimiento = jugar();
                mover(movimiento, 'O');
                
                writer.println(movimiento);
 
            } while (!movimientoJugador.equals("bye"));
 
            socket.close();
            
        } catch (IOException ex) {
        	
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}