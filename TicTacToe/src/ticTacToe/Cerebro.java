package ticTacToe;

public class Cerebro {

	int[][] estrategia;
	int anchura;
	int altura;
	
	public Cerebro(int anchura, int altura) {
		
		super();
		estrategia = new int[anchura][altura];
		this.anchura = anchura;
		this.altura = altura;
		iniciarJugada();
	}
	
	public void iniciarJugada() {
		
		for(int i = 0; i < altura; i++) {
    		
    		for (int j = 0; j < anchura; j++) {
    			
    			estrategia[i][j] = 0;
    		}
    	}
	}
	
	public int getMovimiento(char[][] partida) {
		
		iniciarJugada();
		poblarEstrategia(partida);
		int movimiento = traducirEstrategia();
		renderTablero();
		return movimiento;
	}
	
	public void poblarEstrategia(char[][] partida) {
		
		estudiarFilas(partida);
		estudiarColumnas(partida);
		estudiarD1(partida);
		estudiarD2(partida);
	}
	
	public void estudiarFilas(char[][] partida) {
		
		//FILAS
		for(int i = 0; i < altura; i++) {
			
			boolean oponente = false;
			boolean server = false;
			int valor = 1;
		
    		for (int j = 0; j < anchura; j++) {
    			
    			if(partida[i][j] == 'O') {
    				
    				server = true;
    				valor++;
    				estrategia[i][j] = -1;
    				
    			}else if(partida[i][j] == 'X') {
    				
    				oponente = true;
    				estrategia[i][j] = -1;
    			}
    		}
    		
    		if(oponente & !server) {
    			
    			puntuarFila(i, 0);
    			
    		}else if(server & !oponente) {
    			
    			if(valor == 4) {
    				
    				puntuarFila(i, valor*3);
    				
    			}else {
    				
    				puntuarFila(i, valor*2);
    			}
    			
    		}else if(!oponente) {
    			
    			puntuarFila(i, 1);
    		}
    	}
	}
	
	public void puntuarFila(int fila, int valor) {
		
		for(int i = 0; i < anchura; i++) {
			
			if(estrategia[fila][i] != -1) {
				
				estrategia[fila][i] += valor;
			}
		}
	}
	
	public void estudiarColumnas(char[][] partida) {
		
		//COLUMNAS
		for(int i = 0; i < anchura; i++) {
			
			boolean oponente = false;
			boolean server = false;
			int valor = 1;
			
			for (int j = 0; j < altura; j++) {
				
				if(partida[j][i] == 'O') {
    				
    				server = true;
    				estrategia[j][i] = -1;
    				valor++;
    				
    			}else if(partida[j][i] == 'X') {
    				
    				oponente = true;
    				estrategia[j][i] = -1;
    			}
			}
			
			if(oponente & !server) {
    			
    			puntuarColumna(i, 0);
    			
    		}else if(server & !oponente) {
    			
    			if(valor == 4) {
    				
    				puntuarFila(i, valor*3);
    				
    			}else {
    				
    				puntuarFila(i, valor*2);
    			}
    			
    		}else if(!oponente) {
    			
    			puntuarColumna(i, 1);
    		}
		}
	}
	
	public void puntuarColumna(int columna, int valor) {
		
		for(int i = 0; i < altura; i++) {
			
			if(estrategia[i][columna] != -1) {
				
				estrategia[i][columna] += valor;
			}
		}
	}
	
	public void estudiarD1(char[][] partida) {
		
		//D1 (0,0 - 3,3)
		boolean oponente = false;
		boolean server = false;
		int valor = 1;
		
		for(int i = 0; i < anchura; i++) {
			
			if(partida[i][i] == 'O') {
				
				server = true;
				estrategia[i][i] = -1;
				valor++;
				
			}else if(partida[i][i] == 'X') {
				
				oponente = true;
				estrategia[i][i] = -1;
			}
		}
		
		if(oponente & !server) {
			
			puntuarDiagonal(1, 0);
			
		}else if(server & !oponente) {
			
			if(valor == 4) {
				
				puntuarDiagonal(1, valor*3);
				
			}else {
				
				puntuarDiagonal(1, valor*2);
			}
			
		}else if(!oponente & !server) {
			
			puntuarDiagonal(1, 1);
		}
	}
	
	public void estudiarD2(char[][] partida) {
		
		//D2 (3,0 - 0,3)
		boolean oponente = false;
		boolean server = false;
		int cont = altura-1;
		int valor = 1;
		
		for(int i = 0; i < anchura; i++) {
			
			if(partida[cont][i] == 'O') {
				
				server = true;
				estrategia[cont][i] = -1;
				valor++;
				
			}else if(partida[cont][i] == 'X') {
				
				oponente = true;
				estrategia[cont][i] = -1;
			}
			cont --;
		}
		
		if(oponente & !server) {
			
			puntuarDiagonal(2, 0);
			
		}else if(server & !oponente) {
			
			if(valor == 4) {
				
				puntuarDiagonal(2, valor*3);
				
			}else {
				
				puntuarDiagonal(2, valor*2);
			}
			
		}else if(!oponente) {
			
			puntuarDiagonal(2, 1);
		}
	}
	
	public void puntuarDiagonal(int diagonal, int valor) {
		
		if(diagonal == 1) {
			
			for(int i = 0; i < anchura; i++) {
				
				if(estrategia[i][i] != -1) {
					
					estrategia[i][i] += valor;
				}
			}
			
		}else if(diagonal == 2) {
			
			int cont = altura-1;
			
			for(int i = 0; i < anchura; i++) {
				
				if(estrategia[cont][i] != -1) {
					
					estrategia[cont][i] += valor;
				}
				
				cont --;
			}
		}
	}
	
	public int traducirEstrategia() {
		
		int fila = Integer.MIN_VALUE;
		int columna = Integer.MIN_VALUE;
		int mejorPuntuacion = Integer.MIN_VALUE;
		
		for(int i = 0; i < altura; i++) {
			
			for(int j = 0; j < anchura; j++) {
				
				if(estrategia[i][j] > mejorPuntuacion) {
					
					mejorPuntuacion = estrategia[i][j];
					fila = i;
					columna = j;
				}
			}
		}
		
		return (fila * anchura) + columna;
	}
	
	public boolean hayGanador(char[][] partida) {
		
		boolean ganador = false;
		
		//FILAS
		for(int i = 0; i < altura && !ganador; i++) {
    		
			char contraste = partida[i][0];
			
			if(contraste != 'v') {
				
				ganador = true;
				
				for(int j = 1; j < anchura; j++) {
					
					if(partida[i][j] != contraste) {
						
						ganador = false;
					}
				}
			}
		}
		
		//COLUMNAS
		for(int i = 0; i < anchura && !ganador; i++) {
			
			char contraste = partida[0][i];
			
			if(contraste != 'v') {
				
				ganador = true;
				
				for (int j = 1; j < altura; j++) {
					
					if(partida[j][i] != contraste) {
						
						ganador = false;
					}
				}
			}
		}
		
		//D1
		if(!ganador) {
			
			char contraste = partida[0][0];
			
			if(contraste != 'v') {
				
				ganador = true;
				
				for(int i = 1; i < anchura; i++) {
					
					if(partida[i][i] != contraste) {
						
						ganador = false;
					}
				}
			}
		}
		
		//D2
		if(!ganador) {
			
			int cont = altura-1;
			char contraste = partida[cont][0];
			
			if(contraste != 'v') {
				
				ganador = true;
				
				for(int i = 1; i < anchura; i++) {
					
					cont --;
					
					if(partida[cont][i] != contraste) {
						
						ganador = false;
					}
				}
			}
		}
		
		return ganador;
	} 
	
	 public void renderTablero() {
	    	
	    	for(int i = 0; i < altura; i++) {
	    		
	    		System.out.print("|");
	    		
	    		for (int j = 0; j < anchura; j++) {
	    			
	    			System.out.print(estrategia[i][j]);
	    			
	    			if(j < 3) {
	    				System.out.print("|");
	    			}else {
	    				System.out.println("|");
	    			}
	    		}
	    	}
	    	
	    	System.out.println("-----------------");
	    }
	
}
