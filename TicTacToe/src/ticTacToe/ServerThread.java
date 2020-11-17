package ticTacToe;

import java.io.*;
import java.net.*;
 
public class ServerThread extends Thread {
	
    private Socket socket;
    char[][] partida;
    int anchura = 4;
    int altura = 4;
    Cerebro brain;
    boolean jugando;
 
    public ServerThread(Socket socket) {
    	
        this.socket = socket;
        partida = new char[4][4];
        brain = new Cerebro(4, 4);
        iniciarPartida();
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
            	
            	int respuesta = Integer.MIN_VALUE;
                movimientoJugador = reader.readLine();
                mover(Integer.parseInt(movimientoJugador), 'X');
                
                if(brain.hayGanador(partida)) {
                	
                	System.out.println("¡" + socket.getInetAddress() + " ha ganado!(" + ((Integer.parseInt(movimientoJugador)) + 10000) + ")" );
                	respuesta = 777; //CÓDIGO GANADOR
                	jugando = false;
                	
                }else {
                	
                	 //int movimiento = jugar();
                    int movimientoServer = brain.getMovimiento(partida);
                    mover(movimientoServer, 'O');
                    
                    if(brain.hayGanador(partida)) {
                    	
                    	System.out.println("¡" + socket.getInetAddress() + " ha perdido! (" + (movimientoServer + 1000) + ")");
                    	respuesta = (movimientoServer + 1000); //CÓDIGO PERDEDOR
                    	jugando = false;
                    	
                    }else {
                    	
                    	respuesta = movimientoServer; //CÓDIGO JUGADA
                    }
                }
                
                writer.println(respuesta);
                
            } while (jugando);
 
            socket.close();
            
        } catch (IOException ex) {
        	
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}