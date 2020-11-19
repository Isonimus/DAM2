package serializable;

import java.io.Serializable;

public class Bean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private int day;
	private String[] objetos;
	
	public Bean(String name, int day, String[] objetos) {
		
		setName(name);
		setDay(day);
		setObjetos(objetos);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String[] getObjetos() {
		return objetos;
	}

	public void setObjetos(String[] objetos) {
		this.objetos = objetos;
	}
}
