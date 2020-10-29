package modelo;

import java.util.ArrayList;

import dao.DAOAutorImpl;
import dao.DAOCategoriaImpl;
import dao.DAOClienteImpl;
import dao.DAOEditorialImpl;
import dao.DAOLibroImpl;

/**
 * Centraliza las clases que componen el modelo (POJO & DAO)
 * @author Iker Laforga
 *
 */
public class Motor {
	
	private ArrayList<Autor> listaAutores;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Editorial> listaEditoriales;
	private ArrayList<Libro> listaLibros;
	private ArrayList<Categoria> listaCategorias;
	private DAOAutorImpl DAOAutor;
	private DAOClienteImpl DAOCliente;
	private DAOEditorialImpl DAOEditorial;
	private DAOLibroImpl DAOLibro;
	private DAOCategoriaImpl DAOCategoria;
	private static boolean verbose = true;
	
	public Motor(){
		
		super();
		init();
	}
	
	public void init() {
		
		DAOAutor = new DAOAutorImpl();
		DAOCliente = new DAOClienteImpl();
		DAOEditorial = new DAOEditorialImpl();
		DAOLibro = new DAOLibroImpl();
		DAOCategoria = new DAOCategoriaImpl();
		
		listaAutores = DAOAutor.listar();
		listaClientes = DAOCliente.listar();
		listaEditoriales = DAOEditorial.listar();
		listaLibros = DAOLibro.listar();
		firmarLibros();
		listaCategorias = DAOCategoria.listar();
	}

	public ArrayList<Autor> getListaAutores() {
		return listaAutores;
	}

	public void setListaAutores(ArrayList<Autor> listaAutores) {
		this.listaAutores = listaAutores;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ArrayList<Editorial> getListaEditoriales() {
		return listaEditoriales;
	}

	public void setListaEditoriales(ArrayList<Editorial> listaEditoriales) {
		this.listaEditoriales = listaEditoriales;
	}

	public ArrayList<Libro> getListaLibros() {
		return listaLibros;
	}

	public void setListaLibros(ArrayList<Libro> listaLibros) {
		this.listaLibros = listaLibros;
	}

	public ArrayList<Categoria> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(ArrayList<Categoria> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public DAOAutorImpl getDAOAutor() {
		return DAOAutor;
	}

	public void setDAOAutor(DAOAutorImpl dAOAutor) {
		DAOAutor = dAOAutor;
	}

	public DAOClienteImpl getDAOCliente() {
		return DAOCliente;
	}

	public void setDAOCliente(DAOClienteImpl dAOCliente) {
		DAOCliente = dAOCliente;
	}

	public DAOEditorialImpl getDAOEditorial() {
		return DAOEditorial;
	}

	public void setDAOEditorial(DAOEditorialImpl dAOEditorial) {
		DAOEditorial = dAOEditorial;
	}

	public DAOLibroImpl getDAOLibro() {
		return DAOLibro;
	}

	public void setDAOLibro(DAOLibroImpl dAOLibro) {
		DAOLibro = dAOLibro;
	}
	
	public DAOCategoriaImpl getDAOCategoria() {
		return DAOCategoria;
	}

	public void setDAOCategoria(DAOCategoriaImpl dAOCategoria) {
		DAOCategoria = dAOCategoria;
	}

	public static boolean isVerbose() {
		return verbose;
	}

	public static void setVerbose(boolean verbose) {
		Motor.verbose = verbose;
	}
	
	
	/**
	 * Actualiza el ArrayList de Autores de los libros.
	 */
	public void firmarLibros() {
		
		for(Libro libro : listaLibros) {
			
			libro.setAutores(DAOLibro.listarAutores(libro));
		}
	}

	/**
	 * Pasa un mensaje a la consola si el motor está en modo verboso (modo debug).
	 * @param 
	 */
	public static void log(String mensaje) {
		
		if(isVerbose()) {
			System.out.println(mensaje);
		}
	}
	
	public void eliminarItem(String tipoElemento, int index){
		
		Motor.log("Quieres eliminar el " + tipoElemento + " " + index);
		
		switch(tipoElemento) {
		
			case "Autor":
				
				Autor autor = getListaAutores().get(index);
				getDAOAutor().dropObras(autor);
				getDAOAutor().eliminar(autor);
				setListaAutores(getDAOAutor().listar());
				break;
				
			case "Cliente":
				
				Cliente cliente = getListaClientes().get(index);
				getDAOCliente().eliminar(cliente);
				setListaClientes(getDAOCliente().listar());
				break;
				
			case "Editorial":
				
				Editorial editorial = getListaEditoriales().get(index);
				getDAOEditorial().eliminar(editorial);
				setListaEditoriales(getDAOEditorial().listar());
				break;
				
			case "Libro":
				
				Libro libro = getListaLibros().get(index);
				libro.setAutores(new ArrayList<Autor>());
				getDAOLibro().actualizarAutores(libro);
				getDAOLibro().eliminar(libro);
				setListaLibros(getDAOLibro().listar());
				firmarLibros();
				break;
				
			case "Categoria":
				
				Categoria categoria = getListaCategorias().get(index);
				getDAOCategoria().eliminar(categoria);
				setListaCategorias(getDAOCategoria().listar());
				break;
		}
	}
	
}
