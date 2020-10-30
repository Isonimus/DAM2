package vista;

import java.awt.Color;

/**
 * Gestiona los esquemas de color que se aplican
 * a las vistas.
 * 
 * @author Iker Laforga
 *
 */

//TODO MIRAR CARGA DE ARCHIVO CONFIG http://chuwiki.chuidiang.org/index.php?title=Leer_y_modificar_fichero_de_propiedades_en_java
public class EsquemaColor {
	
	public Color sombra;
	public Color oscuro;
	public Color medioOscuro;
	public Color medioClaro;
	public Color claro;
	public Color brillo;
	public static final int ESTILO_DEFAULT = 0;
	public static final int ESTILO_CALIDO = 1;
	public static final int ESTILO_FRIO = 2;
	public static final int ESTILO_ECO = 3;
	public static final int ESTILO_VINTAGE = 4;
	
	public EsquemaColor() {
		
		super();
		aplicarEsquema(ESTILO_DEFAULT);
	}
	
	public EsquemaColor(int estilo) {
		
		super();
		aplicarEsquema(estilo);
	}
	
	/**
	 * Aplica el esquema de color correspondiente
	 * según el int pasado por parámetro:
	 * 0: DEFAULT
	 * 1: CÁLIDO
	 * 2: FRÍO
	 * 3: ECO
	 * @param estilo int: el int del esquema a aplicar.
	 */
	private void aplicarEsquema(int estilo){
		
		switch (estilo) {
			
			case 0: //GRISES
				
				sombra = Color.DARK_GRAY;
				oscuro = new Color(102, 102, 102);
				medioOscuro = Color.gray;
				medioClaro = Color.LIGHT_GRAY;
				claro = null;
				brillo = null;
				break;
				
			case 1: //ROJOS
				
				sombra = new Color(119, 3, 14);
				oscuro = new Color(99, 47, 15);
				medioOscuro = new Color(255, 117, 69);
				medioClaro = new Color(234, 215, 217);
				claro = null;
				brillo = null;
				break;
				
			case 2: //AZULES
				
				sombra = new Color(16, 48, 99);
				oscuro = new Color(46, 87, 165);
				medioOscuro = new Color(78, 160, 201);
				medioClaro = new Color(161, 218, 226);
				claro = null;
				brillo = null;
				break;
			
			case 3: //VERDES
				
				sombra = new Color(14, 73, 14);
				oscuro = new Color(35, 124, 38);
				medioOscuro = new Color(84, 176, 56);
				medioClaro = new Color(172, 221, 155);
				claro = null;
				brillo = null;
				break;
				
			case 4: //VINTAGE
				
				sombra = new Color(38, 17, 11);
				oscuro = new Color(38, 17, 11);
				medioOscuro = new Color(160, 99, 46);
				medioClaro = new Color(240, 215, 200);
				claro = null;
				brillo = null;
				break;
				
			default: //DEFAULT
				
				sombra = Color.DARK_GRAY;
				oscuro = new Color(102, 102, 102);
				medioOscuro = Color.gray;
				medioClaro = Color.LIGHT_GRAY;
				claro = null;
				brillo = null;
				break;
		}
	}
}
