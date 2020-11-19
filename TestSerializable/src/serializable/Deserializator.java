package serializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 
 * @author Iker Laforga
 *
 */
public class Deserializator {

	public Deserializator() {

	}

	private Bean deserializar(String ruta) {
		
		Bean bean = null;
		
		try {
			
	         FileInputStream fileIn = new FileInputStream(ruta);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         bean = (Bean) in.readObject();
	         in.close();
	         fileIn.close();
	         
	      } catch (IOException i) {
	         i.printStackTrace();
	     
	      } catch (ClassNotFoundException c) {
	         System.out.println("Clase Bean no encontrada.");
	         c.printStackTrace();
	      }
		
		return bean;
	}

	public static void main(String[] args) {

		Deserializator serializator = new Deserializator();
		String ruta = "C:\\Serializable\\Hero.ser";
		Bean reconstruido = serializator.deserializar(ruta);
		
		System.out.println(reconstruido.getName());
		System.out.println(reconstruido.getDay());
		System.out.println(reconstruido.getObjetos()[0]);
		System.out.println(reconstruido.getObjetos()[1]);
		System.out.println(reconstruido.getObjetos()[2]);
	}
}
