package matrices;

public class Matriz {
	
	private int filas;
	private int columnas;
	int[][] matriz;
			
	Matriz(int filas, int columnas) {
		
		super();
		setFilas(filas);
		setColumnas(columnas);
		matriz = new int[filas][columnas];
	}
	
	public void poblarMatriz() {
		int valorMinimo = -23;
		int valorMaximo = 23;
		
		for(int i = 0; i < getFilas(); i++) {
			
			for(int j = 0; j < getColumnas(); j++) {
				
				matriz[i][j] =  valorMinimo + (int)(Math.random() * (valorMaximo - valorMinimo)) + 1;
				//System.out.println("-" + i + "" + j + ": " + matriz[i][j]) ;
			}
		}
	}
	
	public static Matriz multiplicarMatrices(Matriz m1, Matriz m2) {
		
		Matriz producto = new Matriz(m1.getFilas(), m2.getColumnas());
		
		for(int i = 0; i < m1.getFilas(); i++) { //POR CADA FILA DE m1
			
			for(int j = 0; j < m2.getColumnas(); j++) { //POR CADA COLUMNA DE m2
				
				producto.matriz[i][j] = 0; //INICIALIZAR
				
				for(int k = 0; k < m1.getColumnas(); k++) {
					
					producto.matriz[i][j] += m1.matriz[i][k] * m2.matriz[k][j];
				}
			}
		}
		
		return producto;
	}
	
	
	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	int getColumnas() {
		return columnas;
	}

	void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	
	@Override
	public String toString() {
		
		String info = "|"; //FORMATO
		
		for(int i = 0; i < filas; i++) {
			
			for(int j = 0; j < columnas; j++) {
				
				info += matriz[i][j];
						
				if(j == columnas - 1) {
					
					info += "|"; //FORMATO
					
				}else {
					
					info += "   "; //FORMATO
				}
			}
			
			if(i < filas - 1) {
				info += "\n|"; //FORMATO
			}
		}
		
		return info;
	}			
}
