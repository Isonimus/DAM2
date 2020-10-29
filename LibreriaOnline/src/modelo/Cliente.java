package modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente {
	
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String direccion;
	private String email;
	private Date fechaNacimiento;
	private String usuario;
	private String contrasenya;
	
	public Cliente() {
		
		super();
	}
	
	public Cliente(String dni, String nombre, String apellido1, String apellido2) {
		
		super();
		this.setDni(dni);
		this.setNombre(nombre);
		this.setApellido1(apellido1);
		this.setApellido2(apellido2);
	}

	public Cliente(ResultSet resultado) {
		
		try {
			
			this.setDni(resultado.getString(1));
			this.setNombre(resultado.getString(2));
			this.setApellido1(resultado.getString(3));
			this.setApellido2(resultado.getString(4));
			this.setDireccion(resultado.getString(5));
			this.setEmail(resultado.getString(6));
			this.setFechaNacimiento(Date.valueOf(resultado.getString(7)));
			this.setUsuario(resultado.getString(8));
			this.setContrasenya(resultado.getString(9));
			
		} catch (SQLException e) {
			
			System.out.println("Error al cargar el cliente.");
			e.printStackTrace();
		}
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public String toString() {
		return "cliente";
	}
}
