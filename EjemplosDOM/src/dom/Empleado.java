package dom;

public class Empleado {
	
	private int codEmpleado;
	private String nombre;
	private String apellido;
	
	public Empleado() {}
	
	public Empleado(int codEmpleado, String nombre, String apellido) {
		super();
		this.codEmpleado = codEmpleado;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public int getCodEmpleado() {
		return codEmpleado;
	}

	public void setCodEmpleado(int codEmpleado) {
		this.codEmpleado = codEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	
}
