package ticTacToe;

import java.io.*;
import java.net.*;
 
public class ServerThread extends Thread {
	
    private Socket socket;
    char[][] partida;
    int anchura = 4;
    int altura = 4;
    Cerebro brain;
 
    public ServerThread(Socket socket) {
    	
        this.socket = socket;
        partida = new char[4][4];
        brain = new Cerebro(4, 4);
        iniciarPartida();
    }
    
    public void iniciarPartida() {
    	
    	System.out.println("¡Nueva partida!\n");
    	
    	for(int i = 0; i < altura; i++) {
    		
    		for (int j = 0; j < anchura; j++) {
    			
    			partida[i][j] = 'v';
    		}
    	}
    }
    
    public int jugar() {
    
    	boolean elegido = false;
    	int fila = 0;
    	int columna = 0;
    	
    	while(!elegido) {
    		
    		fila = (int) (Math.random() * (altura));
    		columna = (int) (Math.random() * (anchura));
    		
    		if(partida[fila][columna] == 'v') {
    			
    			elegido = true;
    		}
    	}

    	return (fila * anchura) + columna;
    }
    
    public void mover(int posicion, char jugador) {
    	
    	int fila = posicion / anchura;
    	int columna = posicion % anchura;
    	
    	partida[fila][columna] = jugador;
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
                
                if(brain.hayGanador(partida)) {
                	
                	System.out.println("¡Has ganado!");
                	
                }else {
                	
                	 //int movimiento = jugar();
                    int movimiento = brain.getMovimiento(partida);
                    mover(movimiento, 'O');
                    if(brain.hayGanador(partida)) {
                    	System.out.println("¡Has perdido!");
                    }
                    
                    writer.println(movimiento);
                }
                
            } while (!movimientoJugador.equals("bye"));
 
            socket.close();
            
        } catch (IOException ex) {
        	
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}