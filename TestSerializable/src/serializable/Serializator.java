package serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 
 * @author Iker Laforga
 *
 */
public class Serializator {

	public Serializator() {

	}

	private void serializar(Bean bean) {

		try {
			
			FileOutputStream fileOut = new FileOutputStream("C:\\Serializable\\" + bean.getName() + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(bean);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in C:\\Serializable\\" + bean.getName() + ".ser");
			
		} catch (IOException i) {
			
			i.printStackTrace();
		}
	}

	public static void main(String[] args) {

		String[] objetos = new String[3];
		objetos[0] = "Cuchillo";
		objetos[1] = "Espada";
		objetos[2] = "Escudo";

		Bean bean = new Bean("Hero", 23, objetos);
		Serializator serializator = new Serializator();
		serializator.serializar(bean);
	}
}
