package controlador;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.Autor;
import modelo.Categoria;
import modelo.Cliente;
import modelo.Editorial;
import modelo.Libro;
import modelo.Motor;
import vista.InterfazLibreria;
import vista.VistaAutor;
import vista.VistaCategoria;
import vista.VistaCliente;
import vista.VistaEditar;
import vista.VistaEditarAutor;
import vista.VistaEditarCategoria;
import vista.VistaEditarCliente;
import vista.VistaEditarEditorial;
import vista.VistaEditarLibro;
import vista.VistaEditorial;
import vista.VistaItem;
import vista.VistaLibro;
import vista.VistaLista;

/**
 * CLASE CONTROLADOR.
 * 
 * Requiere una GUI y un motor (modelo).
 * Comunica la lógica con la interfaz de usuario.
 * @author Iker Laforga
 * 
 */
public class Controlador implements ActionListener, ItemListener{
	
	InterfazLibreria gui;
	Motor motor;
	
	public Controlador(InterfazLibreria gui, Motor motor){
		
		super();
		this.gui = gui;
		this.motor = motor;
		init();
	}
	
	public void init(){
		
		gui.panelMenu.bt1Menu.addActionListener(this);
		gui.panelMenu.bt2Menu.addActionListener(this);
		gui.panelMenu.bt3Menu.addActionListener(this);
		gui.panelMenu.bt4Menu.addActionListener(this);
		gui.panelMenu.bt5Menu.addActionListener(this);
		
		actualizarItems(gui.panelAutores, motor.getListaAutores());
		actualizarItems(gui.panelClientes, motor.getListaClientes());
		actualizarItems(gui.panelEditoriales, motor.getListaEditoriales());
		actualizarItems(gui.panelLibros, motor.getListaLibros()); //SÓLO ES NECESARIO ACTUALIZAR LOS ITEMS LIBRO
		actualizarItems(gui.panelCategorias, motor.getListaCategorias());
		gui.principal.repaint();
	}
	
	/**
	 * REFRESHER REWORK: APROXIMACIÓN 1 - GENERALIZADA
	 * Actualiza los ITEMS de la vista desde los arrays del modelo.
	 * @param (VistaLista)v La vista contenedor.
	 * @param (ArrayList<E>)a El array de carga.
	 *//*
	public <E> void actualizarItems(VistaLista v, ArrayList<E> a) { //WIP TODO: REFACTOR AF
		
		int xPos = 5;
		int yPos = 5;
		
		v.contenedorItems.setPreferredSize(new Dimension(460, (a.size()*55)+5)); //OJO
		v.contenedorItems.removeAll();
		
		for(int i = 0; i < a.size(); i++) {
			
			VistaItem item = null;
			E element = a.get(i);
			final int id = i;
			
			if(element instanceof Autor) {
				
				Motor.log("Actualizando autor...");
				
				item = new VistaAutor(xPos, yPos, (Autor)element);
				
				//LISTENER DINÁMICO BOTÓN MODIFICAR
				item.modificar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						Motor.log("Quieres modificar el autor " + id);
						gui.supletorio.setTitle("Editar autor...");
						inflar(element, id);
						gui.supletorio.repaint();
						gui.supletorio.setVisible(true);
					}
				});
				
				//LISTENER DINÁMICO BOTÓN ELIMINAR
				item.eliminar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						Motor.log("Quieres eliminar el autor " + id);
						motor.getDAOAutor().eliminar((Autor) element);
						motor.setListaAutores(motor.getDAOAutor().listar());
						actualizarItems((VistaLista) gui.panelAutores, motor.getListaAutores());
						gui.principal.repaint();
					}
				});
				
			}if(element instanceof Cliente) {
				
				Motor.log("Actualizando cliente...");
				
				item = new VistaCliente(xPos, yPos, (Cliente)element);
				
				//LISTENER DINÁMICO BOTÓN MODIFICAR
				item.modificar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						Motor.log("Quieres modificar el cliente " + id);
						gui.supletorio.setTitle("Editar cliente...");
						inflar(element, id);
						gui.supletorio.repaint();
						gui.supletorio.setVisible(true);
					}
				});
				
				//LISTENER DINÁMICO BOTÓN ELIMINAR
				item.eliminar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						Motor.log("Quieres eliminar el cliente " + id);
						
					}
				});
				
			}if(element instanceof Editorial) {
				
				Motor.log("Actualizando editorial...");
				
				item = new VistaEditorial(xPos, yPos, (Editorial)element);
				
				//LISTENER DINÁMICO BOTÓN MODIFICAR
				item.modificar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						Motor.log("Quieres modificar la editorial " + id);
						gui.supletorio.setTitle("Editar editorial...");
						inflar(element, id);
						gui.supletorio.repaint();
						gui.supletorio.setVisible(true);
					}
				});
				
				//LISTENER DINÁMICO BOTÓN ELIMINAR
				item.eliminar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						Motor.log("Quieres eliminar la editorial " + id);
						
					}
				});
				
			}if(element instanceof Libro) {
				
				Motor.log("Actualizando libro...");
				
				item = new VistaLibro(xPos, yPos, (Libro)element);
				
				//LISTENER DINÁMICO BOTÓN MODIFICAR
				item.modificar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						Motor.log("Quieres modificar el libro " + id);
						gui.supletorio.setTitle("Editar libro...");
						inflar(element, id);
						gui.supletorio.repaint();
						gui.supletorio.setVisible(true);
					}
				});
				
				//LISTENER DINÁMICO BOTÓN ELIMINAR
				item.eliminar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						Motor.log("Quieres eliminar el libro " + id);
						
					}
				});
			}
			
			v.contenedorItems.add(item);
			yPos += item.getAltura() + 5;
		}
		
		if(a.size() == 0) { //POC TODO: REFACTOR AF
			
			Motor.log("No hay elementos disponibles en la base de datos.");
			VistaItem panelNoDatos = new VistaItem(5, 5, "No hay elementos disponibles.", 0);
			panelNoDatos.eliminar.setVisible(false);
			panelNoDatos.modificar.setVisible(false);
			panelNoDatos.info.setBounds(40, 0, 460, 50);
			panelNoDatos.info.setText("No hay elementos disponibles.");
			v.contenedorItems.add(panelNoDatos);
		}
	} */
	
	
	/**APROXIMACIÓN 2
	 * Actualiza los items AUTOR de la vista a partir de los datos
	 * del modelo.
	 *//*
	public void actualizarItemsAutor() {
		
		int xPos = 5;
		int yPos = 5;
		VistaLista vista = gui.panelAutores;
		int numeroAutores = motor.getListaAutores().size();
		int alturaVista = gui.alturaVistaAutor + gui.interespaciado;
		vista.contenedorItems.setPreferredSize(new Dimension(460, ((numeroAutores + 1) * alturaVista) + gui.interespaciado)); //OJO
		vista.contenedorItems.removeAll();
		
		for(int i = 0; i < numeroAutores; i++) {
			
			Autor autor = motor.getListaAutores().get(i);
			VistaAutor vistaAutor = new VistaAutor(xPos, yPos, autor);
			final int id = i;
			
			Motor.log("Añadiendo vista autor...");
			
			if(numeroAutores < 7) {
				
				vistaAutor.setSize(470, vistaAutor.getHeight());
			}
				
			//LISTENER DINÁMICO BOTÓN MODIFICAR
			vistaAutor.modificar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					Motor.log("Quieres modificar el autor " + id);
					gui.supletorio.setTitle("Editar autor...");
					inflar(autor, id);
					ajustarSupletorio(360, 180);
					gui.supletorio.repaint();
					gui.supletorio.setVisible(true);
				}
			});
				
			//LISTENER DINÁMICO BOTÓN ELIMINAR
			vistaAutor.eliminar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
						
					Motor.log("Quieres eliminar el autor " + id);
					motor.getDAOAutor().eliminar(autor);
					motor.setListaAutores(motor.getDAOAutor().listar());
					actualizarItemsAutor();
					gui.principal.repaint();
				}
			});
			
			vista.contenedorItems.add(vistaAutor);
			yPos += vistaAutor.getAltura() + 5;
		}
		
		if(numeroAutores == 0) { //POC TODO: REFACTOR AF
			
			Motor.log("No hay autores disponibles en la base de datos.");
			VistaItem panelNoDatos = new VistaItem(xPos, yPos, "Autor", 0);
			panelNoDatos.eliminar.setVisible(false);
			panelNoDatos.modificar.setVisible(false);
			panelNoDatos.info.setBounds(40, 0, 460, 50);
			panelNoDatos.info.setText("No hay autores disponibles.");
			vista.contenedorItems.add(panelNoDatos);
			yPos += panelNoDatos.getAltura() + 5;
		}
		
		//EL BOTÓN PARA AÑADIR ITEMS
		VistaItem nuevoItem = new VistaItem(5, yPos, "Autor", 0);
		nuevoItem.eliminar.setVisible(false);
		nuevoItem.modificar.setVisible(false);
		nuevoItem.info.setBounds(40, 0, 460, 50);
		nuevoItem.info.setText("Nuevo autor...");
		JButton anyadir = new JButton("Añadir Autor");
		anyadir.setBounds(305, 10, 150, 30);
		anyadir.setBackground(Color.gray);
		anyadir.setForeground(Color.LIGHT_GRAY);
		anyadir.addActionListener(this);
		nuevoItem.add(anyadir);
		
		if(numeroAutores < 7) { //TODO: REPENSAR
			
			nuevoItem.setSize(470, nuevoItem.getHeight());
		}
		
		vista.contenedorItems.add(nuevoItem);
		vista.contenedorItems.revalidate();
	}
	
	public void actualizarItemsCliente() {
		
		int xPos = 5;
		int yPos = 5;
		VistaLista vista = gui.panelClientes;
		int numeroClientes = motor.getListaClientes().size();
		int alturaVista = gui.alturaVistaCliente + gui.interespaciado;
		vista.contenedorItems.setPreferredSize(new Dimension(460, ((numeroClientes + 1) * alturaVista) + gui.interespaciado)); //OJO
		vista.contenedorItems.removeAll();
		
		for(int i = 0; i < numeroClientes; i++) {
			
			Cliente cliente = motor.getListaClientes().get(i);
			VistaCliente vistaCliente = new VistaCliente(xPos, yPos, cliente);
			final int id = i;
			
			Motor.log("Añadiendo vista cliente...");
			
			if(numeroClientes < 7) {
				
				vistaCliente.setSize(470, vistaCliente.getHeight());
			}
				
			//LISTENER DINÁMICO BOTÓN MODIFICAR
			vistaCliente.modificar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					Motor.log("Quieres modificar el cliente " + id);
					gui.supletorio.setTitle("Editar cliente...");
					inflar(cliente, id);
					ajustarSupletorio(360, 360);
					gui.supletorio.repaint();
					gui.supletorio.setVisible(true);
				}
			});
				
			//LISTENER DINÁMICO BOTÓN ELIMINAR
			vistaCliente.eliminar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
						
					Motor.log("Quieres eliminar el cliente " + id);
					motor.getDAOCliente().eliminar(cliente);
					motor.setListaClientes(motor.getDAOCliente().listar());
					actualizarItemsCliente();
					gui.principal.repaint();
				}
			});
				
			vista.contenedorItems.add(vistaCliente);
			yPos += vistaCliente.getAltura() + 5;
		}
		
		if(numeroClientes == 0) { //POC TODO: REFACTOR AF
			
			Motor.log("No hay clientes disponibles en la base de datos.");
			VistaItem panelNoDatos = new VistaItem(5, 5, "Cliente", 0);
			panelNoDatos.eliminar.setVisible(false);
			panelNoDatos.modificar.setVisible(false);
			panelNoDatos.info.setBounds(40, 0, 460, 50);
			panelNoDatos.info.setText("No hay clientes disponibles.");
			vista.contenedorItems.add(panelNoDatos);
			yPos += panelNoDatos.getAltura() + 5;
		}
		
		//EL BOTÓN PARA AÑADIR ITEMS
		VistaItem nuevoItem = new VistaItem(5, yPos, "Cliente", 0);
		nuevoItem.eliminar.setVisible(false);
		nuevoItem.modificar.setVisible(false);
		nuevoItem.info.setBounds(40, 0, 460, 50);
		nuevoItem.info.setText("Nuevo cliente...");
		JButton anyadir = new JButton("Añadir Cliente");
		anyadir.setBounds(305, 10, 150, 30);
		anyadir.setBackground(Color.gray);
		anyadir.setForeground(Color.LIGHT_GRAY);
		anyadir.addActionListener(this);
		nuevoItem.add(anyadir);
		
		if(numeroClientes < 7) { //TODO: REPENSAR
					
			nuevoItem.setSize(470, nuevoItem.getHeight());
		}
				
		vista.contenedorItems.add(nuevoItem);
		vista.contenedorItems.revalidate();
	}
	
	public void actualizarItemsEditorial() {
		
		int xPos = 5;
		int yPos = 5;
		VistaLista vista = gui.panelEditoriales;
		int numeroEditoriales = motor.getListaEditoriales().size();
		int alturaVista = gui.alturaVistaEditorial + gui.interespaciado;
		vista.contenedorItems.setPreferredSize(new Dimension(460, ((numeroEditoriales + 1) * alturaVista) + gui.interespaciado)); //OJO
		vista.contenedorItems.removeAll();
		
		for(int i = 0; i < numeroEditoriales; i++) {
			
			Editorial editorial = motor.getListaEditoriales().get(i);
			VistaEditorial vistaEditorial = new VistaEditorial(xPos, yPos, editorial);
			final int id = i;
			
			Motor.log("Añadiendo vista editorial...");
			
			if(numeroEditoriales < 7) {
				
				vistaEditorial.setSize(470, vistaEditorial.getHeight());
			}
				
			//LISTENER DINÁMICO BOTÓN MODIFICAR
			vistaEditorial.modificar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					Motor.log("Quieres modificar la editorial " + id);
					gui.supletorio.setTitle("Editar editorial...");
					inflar(editorial, id);
					ajustarSupletorio(360, 180);
					gui.supletorio.repaint();
					gui.supletorio.setVisible(true);
				}
			});
				
			//LISTENER DINÁMICO BOTÓN ELIMINAR
			vistaEditorial.eliminar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
						
					Motor.log("Quieres eliminar la editorial " + id);
					motor.getDAOEditorial().eliminar(editorial);
					motor.setListaEditoriales(motor.getDAOEditorial().listar());
					actualizarItemsEditorial();
					gui.principal.repaint();
				}
			});
				
			vista.contenedorItems.add(vistaEditorial);
			yPos += vistaEditorial.getAltura() + 5;
		}
		
		if(numeroEditoriales == 0) { //POC TODO: REFACTOR AF
			
			Motor.log("No hay editoriales disponibles en la base de datos.");
			VistaItem panelNoDatos = new VistaItem(5, 5, "Editorial", 0);
			panelNoDatos.eliminar.setVisible(false);
			panelNoDatos.modificar.setVisible(false);
			panelNoDatos.info.setBounds(40, 0, 460, 50);
			panelNoDatos.info.setText("No hay editoriales disponibles.");
			vista.contenedorItems.add(panelNoDatos);
			yPos += panelNoDatos.getAltura() + 5;
		}
		
		//EL BOTÓN PARA AÑADIR ITEMS
		VistaItem nuevoItem = new VistaItem(5, yPos, "Editorial", 0);
		nuevoItem.eliminar.setVisible(false);
		nuevoItem.modificar.setVisible(false);
		nuevoItem.info.setBounds(40, 0, 460, 50);
		nuevoItem.info.setText("Nueva editorial...");
		JButton anyadir = new JButton("Añadir Editorial");
		anyadir.setBounds(305, 10, 150, 30);
		anyadir.setBackground(Color.gray);
		anyadir.setForeground(Color.LIGHT_GRAY);
		anyadir.addActionListener(this);
		nuevoItem.add(anyadir);
		
		if(numeroEditoriales < 7) { //TODO: REPENSAR
					
			nuevoItem.setSize(470, nuevoItem.getHeight());
		}
				
		vista.contenedorItems.add(nuevoItem);
		vista.contenedorItems.revalidate();
	}
	
	public void actualizarItemsLibro() {
		
		int xPos = 5;
		int yPos = 5;
		VistaLista vista = gui.panelLibros;
		int numeroLibros = motor.getListaLibros().size();
		int alturaVista = gui.alturaVistaLibro + gui.interespaciado;
		vista.contenedorItems.setPreferredSize(new Dimension(460, ((numeroLibros + 1) * alturaVista) + gui.interespaciado)); //OJO
		vista.contenedorItems.removeAll();
		
		for(int i = 0; i < numeroLibros; i++) {
			
			Libro libro = motor.getListaLibros().get(i);
			VistaLibro vistaLibro = new VistaLibro(xPos, yPos, libro);
			final int id = i;
			
			Motor.log("Añadiendo vista libro...");
			
			if(numeroLibros < 7) {
				
				vistaLibro.setSize(470, vistaLibro.getHeight());
			}
				
			//LISTENER DINÁMICO BOTÓN MODIFICAR
			vistaLibro.modificar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					Motor.log("Quieres modificar el libro " + id);
					gui.supletorio.setTitle("Editar libro...");
					inflar(libro, id);
					ajustarSupletorio(500, 300);
					gui.supletorio.repaint();
					gui.supletorio.setVisible(true);
				}
			});
				
			//LISTENER DINÁMICO BOTÓN ELIMINAR
			vistaLibro.eliminar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
						
					Motor.log("Quieres eliminar el libro " + id);
					motor.getDAOLibro().eliminar(libro);
					motor.setListaLibros(motor.getDAOLibro().listar());
					actualizarItemsLibro();
					gui.principal.repaint();
				}
			});
				
			vista.contenedorItems.add(vistaLibro);
			yPos += vistaLibro.getAltura() + 5;
		}
		
		if(numeroLibros == 0) { //POC TODO: REFACTOR AF
			
			Motor.log("No hay libros disponibles en la base de datos.");
			VistaItem panelNoDatos = new VistaItem(5, 5, "Libro", 0);
			panelNoDatos.eliminar.setVisible(false);
			panelNoDatos.modificar.setVisible(false);
			panelNoDatos.info.setBounds(40, 0, 460, 50);
			panelNoDatos.info.setText("No hay libros disponibles.");
			vista.contenedorItems.add(panelNoDatos);
			yPos += panelNoDatos.getAltura() + 5;
		}
		
		//EL BOTÓN PARA AÑADIR ITEMS
		VistaItem nuevoItem = new VistaItem(5, yPos, "Libro", 0);
		nuevoItem.eliminar.setVisible(false);
		nuevoItem.modificar.setVisible(false);
		nuevoItem.info.setBounds(40, 0, 460, 50);
		nuevoItem.info.setText("Nuevo libro...");
		JButton anyadir = new JButton("Añadir Libro");
		anyadir.setBounds(305, 10, 150, 30);
		anyadir.setBackground(Color.gray);
		anyadir.setForeground(Color.LIGHT_GRAY);
		anyadir.addActionListener(this);
		nuevoItem.add(anyadir);
		
		if(numeroLibros < 7) { //TODO: REPENSAR
							
			nuevoItem.setSize(470, nuevoItem.getHeight());
		}
						
		vista.contenedorItems.add(nuevoItem);
		vista.contenedorItems.revalidate();
	}*/
	
	/**
	 * Actualiza las vistas de Item de una VistaLista a partir
	 * de un ArrayList de objetos de datos.
	 * @param <E> El tipo de dato
	 * @param v VistaLista: La vistaLista a actualizar
	 * @param a ArrayList<E>: El ArrayList de objetos de datos
	 */
	private <E> void actualizarItems(VistaLista v, ArrayList<E> a) {
		
		int xPos = 5;
		int yPos = 5;
		VistaLista vista = v;
		ArrayList<E> items = a;
		int numeroItems = items.size();
		String tipoElemento = null;
		int alturaVista = 0; 
		boolean ancho = false;
		
		vista.contenedorItems.removeAll();
		
		for(int i = 0; i < numeroItems; i++) {
			
			VistaItem vistaItem = null;
			E element = a.get(i);
			final int id = i;
			
			if(element instanceof Autor) {
				
				vistaItem = new VistaAutor(xPos, yPos, (Autor)element, gui.estilo);
				tipoElemento = "Autor";
				
			}else if(element instanceof Cliente) {
			
				vistaItem = new VistaCliente(xPos, yPos, (Cliente)element, gui.estilo);
				tipoElemento = "Cliente";
				
			}else if(element instanceof Editorial) {
				
				vistaItem = new VistaEditorial(xPos, yPos, (Editorial)element, gui.estilo);
				tipoElemento = "Editorial";
				
			}else if(element instanceof Libro) {
				
				VistaLibro vl = new VistaLibro(xPos, yPos, (Libro)element, gui.estilo);
				vl.getEditorial().setText(vl.getEditorial().getText() + motor.getDAOLibro().getNombreEditorial((Libro)element) + ".");
				vistaItem = vl;
				tipoElemento = "Libro";
				
				
			}else if(element instanceof Categoria) {
				
				vistaItem = new VistaCategoria(xPos, yPos, (Categoria)element, gui.estilo);
				tipoElemento = "Categoria";
			}
			
			//AJUSTA LA ANCHURA DEL ITEM SI LA VISTA TIENE SCROLL
			if(numeroItems * vistaItem.getAltura() <= 260) {
				
				vistaItem.setAnchura(472);
				ancho = true;
			}
				
			//LISTENER BOTÓN MODIFICAR
			vistaItem.modificar.setActionCommand("Modificar_" + tipoElemento + "_" + id);
			vistaItem.modificar.addActionListener(this);
			
				
			//LISTENER BOTÓN ELIMINAR
			vistaItem.eliminar.setActionCommand("Eliminar_" + tipoElemento + "_" + id);
			vistaItem.eliminar.addActionListener(this);
			
			Motor.log("Añadiendo vista " + tipoElemento + "...");
			vista.contenedorItems.add(vistaItem);
			alturaVista = vistaItem.getAltura() + gui.interespaciado;
			yPos += alturaVista;
		}
		
		if(numeroItems == 0) { //SI NO HAY ITEMS EN LA BDD
			
			Motor.log("No hay " + tipoElemento + "es disponibles en la base de datos.");
			VistaItem panelNoDatos = new VistaItem(xPos, yPos, tipoElemento, 0, gui.estilo);
			panelNoDatos.eliminar.setVisible(false);
			panelNoDatos.modificar.setVisible(false);
			panelNoDatos.info.setBounds(40, 0, 460, 50);
			panelNoDatos.info.setText("No hay " + tipoElemento + "es disponibles.");
			vista.contenedorItems.add(panelNoDatos);
			yPos += panelNoDatos.getAltura() + 5;
		}
		
		//EL BOTÓN PARA AÑADIR ITEMS NUEVOS
		VistaItem nuevoItem = new VistaItem(5, yPos, tipoElemento, 0, gui.estilo);
		nuevoItem.eliminar.setVisible(false);
		nuevoItem.modificar.setVisible(false);
		nuevoItem.info.setBounds(40, 0, 460, 50);
		nuevoItem.info.setText("Nuevo " + tipoElemento + "...");
		JButton anyadir = new JButton("Añadir " + tipoElemento);
		anyadir.setBounds(300, 10, 150, 30);
		anyadir.setBackground(gui.estilo.medioOscuro);
		anyadir.setForeground(gui.estilo.medioClaro);
		anyadir.addActionListener(this);
		nuevoItem.add(anyadir);
		
		if(ancho) {
			
			//nuevoItem.setSize(470, nuevoItem.getHeight());
			nuevoItem.setAnchura(472);
		}
		
		vista.contenedorItems.add(nuevoItem);
		vista.contenedorItems.setPreferredSize(new Dimension(460, ((numeroItems * alturaVista) + 55) + gui.interespaciado));
		vista.contenedorItems.revalidate();
	}
	
	/**
	 * Rellena los datos de la vista de Edición a partir
	 * del elemento dado, y la añade al panel Supletorio.
	 * @param <E> El tipo de elemento
	 * @param element E: El elemento de datos a mostrar
	 * @param index int: La posición del elemento en el array del modelo
	 */
	private <E> void inflarVistaEditar(E element, int index) {
		
		VistaEditar edit = new VistaEditar(gui.estilo);
		
		if(element instanceof Autor) {
			
			Autor autor = (Autor) element;
			edit = new VistaEditarAutor(autor, gui.estilo);
			
		}else if(element instanceof Cliente) {
			
			Cliente cliente = (Cliente) element;
			edit = new VistaEditarCliente(cliente, gui.estilo);
			
		}else if(element instanceof Editorial) {
			
			Editorial editorial = (Editorial) element;
			edit = new VistaEditarEditorial(editorial, gui.estilo);
			
		}else if (element instanceof Libro) {
			
			Libro libro = (Libro) element;
			edit = new VistaEditarLibro(libro, gui.estilo);
			
		}else if (element instanceof Categoria) {
			
			Categoria categoria = (Categoria) element;
			edit = new VistaEditarCategoria(categoria, gui.estilo);
			
		}else if (element == null) {
			
		}
		
		edit.getCancelar().addActionListener(this);
		edit.getCommit().addActionListener(this);
		edit.setId(index);
		gui.edit = edit;
		gui.supletorio.setContentPane(edit);
		gui.supletorio.repaint();
	}
	
	/**
	 * Carga y visualiza los JComboBox de los autores
	 * en la vista de Edición de un libro.
	 * @param libro Libro: El libro a cargar
	 * @param edit VistaEditar: La VistaEditar a modificar
	 */
	public void cargarCombosAutores(Libro libro, VistaEditar edit) {
		
		int yInicial = 20;
		ArrayList<Autor> autores = libro.getAutores();
		int numAutores = autores.size();
		for(int i = 0; i < autores.size(); i++) {
			
			JComboBox<String> combo = new JComboBox<String>();
			combo.addItem("Seleccionar...");
			
			for(int j = 0; j < motor.getListaAutores().size(); j++) {
				
				combo.addItem(motor.getListaAutores().get(j).getNombre());
				combo.setSelectedItem(autores.get(i).getNombre());
			}
			
			combo.setBounds(330, yInicial, 140, 25);
			combo.addItemListener(this);
			edit.combos.add(combo);
			//System.out.println("Cargar Autores: " + gui.edit.combos.toString());
			edit.add(combo);
			yInicial += 30;
		}
		
		if(numAutores < 6) { //EL COMBO PARA AÑADIR
			//System.out.println("menos de 7");
			JComboBox<String> combo = new JComboBox<String>();
			combo.addItem("Seleccionar...");
			
			for(int j = 0; j < motor.getListaAutores().size(); j++) {
				
				combo.addItem(motor.getListaAutores().get(j).getNombre());
				combo.setSelectedItem("Seleccionar...");
			}
			
			combo.setBounds(330, yInicial, 140, 25);
			combo.addItemListener(this);
			edit.add(combo);
			edit.combos.add(combo);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Motor.log(e.getActionCommand());
		
		if(e.getSource() == gui.panelMenu.bt1Menu) {
			
			gui.supletorio.setVisible(false);
			motor.setListaLibros(motor.getDAOLibro().listar());
			motor.firmarLibros();
			actualizarItems(gui.panelLibros, motor.getListaLibros());
			gui.panelLibros.setVisible(true);
			gui.panelAutores.setVisible(false);
			gui.panelEditoriales.setVisible(false);
			gui.panelClientes.setVisible(false);
			gui.panelCategorias.setVisible(false);
			
		}else if(e.getSource() == gui.panelMenu.bt2Menu) {
			
			gui.supletorio.setVisible(false);
			motor.setListaAutores(motor.getDAOAutor().listar());
			actualizarItems(gui.panelAutores, motor.getListaAutores());
			gui.panelLibros.setVisible(false);
			gui.panelAutores.setVisible(true);
			gui.panelEditoriales.setVisible(false);
			gui.panelClientes.setVisible(false);
			gui.panelCategorias.setVisible(false);
			
		}else if(e.getSource() == gui.panelMenu.bt3Menu) {
			
			gui.supletorio.setVisible(false);
			motor.setListaEditoriales(motor.getDAOEditorial().listar());
			actualizarItems(gui.panelEditoriales, motor.getListaEditoriales());
			gui.panelLibros.setVisible(false);
			gui.panelAutores.setVisible(false);
			gui.panelEditoriales.setVisible(true);
			gui.panelClientes.setVisible(false);
			gui.panelCategorias.setVisible(false);
			
		}else if(e.getSource() == gui.panelMenu.bt4Menu) {
			
			gui.supletorio.setVisible(false);
			motor.setListaClientes(motor.getDAOCliente().listar());
			actualizarItems(gui.panelClientes, motor.getListaClientes());
			gui.panelLibros.setVisible(false);
			gui.panelAutores.setVisible(false);
			gui.panelEditoriales.setVisible(false);
			gui.panelClientes.setVisible(true);
			gui.panelCategorias.setVisible(false);
			
		}else if(e.getSource() == gui.panelMenu.bt5Menu) {
			
			gui.supletorio.setVisible(false);
			motor.setListaCategorias(motor.getDAOCategoria().listar());
			actualizarItems(gui.panelCategorias, motor.getListaCategorias());
			gui.panelLibros.setVisible(false);
			gui.panelAutores.setVisible(false);
			gui.panelEditoriales.setVisible(false);
			gui.panelClientes.setVisible(false);
			gui.panelCategorias.setVisible(true);
			
		}else if(e.getActionCommand().contains("Modificar_")) {
			
			String[] params = e.getActionCommand().split("_");
			String tipoElemento = params[1];
			int index = Integer.parseInt(params[2]);
			
			Motor.log("Quieres modificar el " + tipoElemento + " " + index);
			gui.supletorio.setTitle("Editar " + tipoElemento + "...");
			
			switch(tipoElemento) {
				
				case "Autor":
					
					Autor autor = motor.getListaAutores().get(index);
					inflarVistaEditar(autor, index);
					ajustarSupletorio(360, 180);
					break;
					
				case "Cliente":
					
					Cliente cliente = motor.getListaClientes().get(index);
					inflarVistaEditar(cliente, index);
					ajustarSupletorio(360, 360);
					break;
					
				case "Editorial":
					
					Editorial editorial = motor.getListaEditoriales().get(index);
					inflarVistaEditar(editorial, index);
					ajustarSupletorio(360, 180);
					break;
					
				case "Libro":
					
					Libro libro = motor.getListaLibros().get(index);
					inflarVistaEditar(libro, index);
					cargarCombosAutores(libro, gui.edit);
					ajustarSupletorio(500, 300);
					break;
					
				case "Categoria":
					
					Categoria categoria = motor.getListaCategorias().get(index);
					inflarVistaEditar(categoria, index);
					ajustarSupletorio(360, 180);
					break;
			}
			
			gui.supletorio.repaint();
			gui.supletorio.setVisible(true);
			
		}else if(e.getActionCommand().contains("Eliminar_")) {
			
			String[] params = e.getActionCommand().split("_");
			String tipoElemento = params[1];
			int index = Integer.parseInt(params[2]);
			
			//Motor.log("Quieres eliminar el " + tipoElemento + " " + index);
			
			lanzarDialogoEliminar(gui.supletorio, "¿Seguro que quieres eliminar el " + tipoElemento + "?", tipoElemento, index);
			
			switch(tipoElemento) {
			
				case "Autor":
					
					actualizarItems(gui.panelAutores, motor.getListaAutores());
					break;
					
				case "Cliente":
					
					actualizarItems(gui.panelClientes, motor.getListaClientes());
					break;
					
				case "Editorial":
					
					actualizarItems(gui.panelEditoriales, motor.getListaEditoriales());
					break;
					
				case "Libro":
				
					actualizarItems(gui.panelLibros, motor.getListaLibros());
					break;
					
				case "Categoria":
					
					actualizarItems(gui.panelCategorias, motor.getListaCategorias());
					break;
			}
			
			gui.principal.repaint();
			
		}else if (e.getActionCommand().equals("Añadir Autor")) {
			
			Motor.log("Añadir autor");
			VistaEditar vista = new VistaEditarAutor(gui.estilo);
			gui.edit = vista;
			gui.edit.getCommit().setVisible(false);
			gui.edit.getCancelar().addActionListener(this);
			gui.edit.getInsertar().setActionCommand("insertarAutor");
			gui.edit.getInsertar().addActionListener(this);
			gui.supletorio.setContentPane(vista);
			gui.supletorio.repaint();
			gui.supletorio.setTitle("Nuevo autor...");
			ajustarSupletorio(360, 180);
			gui.supletorio.setVisible(true);
			//gui.principal.setEnabled(false); //TODO: HACERLO EL SUPLETORIO MODAL
			
		}else if (e.getActionCommand().equals("Añadir Cliente")) {
			
			Motor.log("Añadir cliente");
			VistaEditar vista = new VistaEditarCliente(gui.estilo);
			gui.edit = vista;
			gui.edit.getCommit().setVisible(false);
			gui.edit.getCancelar().addActionListener(this);
			gui.edit.getInsertar().setActionCommand("insertarCliente");
			gui.edit.getInsertar().addActionListener(this);
			gui.supletorio.setContentPane(vista);
			gui.supletorio.repaint();
			gui.supletorio.setTitle("Nuevo cliente...");
			ajustarSupletorio(360, 360);
			gui.supletorio.setVisible(true);
			
		}else if (e.getActionCommand().equals("Añadir Editorial")) {
			
			Motor.log("Añadir editorial");
			VistaEditar vista = new VistaEditarEditorial(gui.estilo);
			gui.edit = vista;
			gui.edit.getCommit().setVisible(false);
			gui.edit.getCancelar().addActionListener(this);
			gui.edit.getInsertar().setActionCommand("insertarEditorial");
			gui.edit.getInsertar().addActionListener(this);
			gui.supletorio.setContentPane(vista);
			gui.supletorio.repaint();
			gui.supletorio.setTitle("Nueva editorial...");
			ajustarSupletorio(360, 180);
			gui.supletorio.setVisible(true);
			
		}else if (e.getActionCommand().equals("Añadir Libro")) {
			
			Motor.log("Añadir libro");
			VistaEditarLibro vista = new VistaEditarLibro(gui.estilo);
			gui.edit = vista;
			Libro nuevoLibro = new Libro();
			nuevoLibro.setAutores(new ArrayList<Autor>());
			((VistaEditarLibro) gui.edit).setLibro(nuevoLibro);
			cargarCombosAutores(nuevoLibro, vista);
			vista.setCombos(new ArrayList<JComboBox<String>>());
			gui.edit.getCommit().setVisible(false);
			gui.edit.getCancelar().addActionListener(this);
			gui.edit.getInsertar().setActionCommand("insertarLibro");
			gui.edit.getInsertar().addActionListener(this);
			gui.supletorio.setContentPane(vista);
			gui.supletorio.repaint();
			gui.supletorio.setTitle("Nuevo libro...");
			ajustarSupletorio(500, 300);
			gui.supletorio.setVisible(true);
			
		}else if (e.getActionCommand().equals("Añadir Categoria")) {
			
			Motor.log("Añadir Categoría");
			VistaEditar vista = new VistaEditarCategoria(gui.estilo);
			gui.edit = vista;
			gui.edit.getCommit().setVisible(false);
			gui.edit.getCancelar().addActionListener(this);
			gui.edit.getInsertar().setActionCommand("insertarCategoria");
			gui.edit.getInsertar().addActionListener(this);
			gui.supletorio.setContentPane(vista);
			gui.supletorio.repaint();
			gui.supletorio.setTitle("Nueva categoría...");
			ajustarSupletorio(360, 180);
			gui.supletorio.setVisible(true);
			
		}else if(e.getSource() == gui.edit.getCancelar()) {
			
			gui.supletorio.setVisible(false);
			
		}else if(e.getSource() == gui.edit.getCommit()) {
			
			int index = gui.edit.getId();
			gui.supletorio.setVisible(false);
			
			switch(gui.edit.getTipo()) {
				
				case "autor":
					
					Autor autor = motor.getListaAutores().get(index);
					autor.setNombre(gui.edit.text2.getText());
					motor.getDAOAutor().actualizar(autor);
					motor.setListaAutores(motor.getDAOAutor().listar());
					actualizarItems(gui.panelAutores, motor.getListaAutores());
					break;
					
				case "cliente":
					
					Cliente cliente = motor.getListaClientes().get(index);
					cliente.setNombre(gui.edit.text2.getText());
					cliente.setApellido1(gui.edit.text3.getText());
					cliente.setApellido2(gui.edit.text4.getText());
					cliente.setDireccion(gui.edit.text5.getText());
					cliente.setEmail(gui.edit.text6.getText());
					cliente.setFechaNacimiento(new java.sql.Date(gui.edit.fecha.getDate().getTime()));
					cliente.setUsuario(gui.edit.text8.getText());
					motor.getDAOCliente().actualizar(cliente);
					motor.setListaClientes(motor.getDAOCliente().listar());
					actualizarItems(gui.panelClientes, motor.getListaClientes());
					break;
					
				case "editorial":
					
					Editorial editorial = motor.getListaEditoriales().get(index);
					editorial.setNombre(gui.edit.text2.getText());
					motor.getDAOEditorial().actualizar(editorial);
					motor.setListaEditoriales(motor.getDAOEditorial().listar());
					actualizarItems(gui.panelEditoriales, motor.getListaEditoriales());
					break;
					
				case "libro":
					
					Libro libro = motor.getListaLibros().get(index);
					libro.setTitulo(gui.edit.text2.getText());
					libro.setPrecio(Double.parseDouble(gui.edit.text3.getText()));
					libro.setStock(Integer.parseInt(gui.edit.text4.getText()));
					libro.setCodCategoria(Integer.parseInt(gui.edit.text5.getText()));
					libro.setCodEditorial(Integer.parseInt(gui.edit.text6.getText()));
					motor.getDAOLibro().actualizar(libro);
					motor.getDAOLibro().actualizarAutores(libro);
					motor.setListaLibros(motor.getDAOLibro().listar());
					motor.firmarLibros();
					actualizarItems(gui.panelLibros, motor.getListaLibros());
					break;
					
				case "categoria":
					
					Categoria categoria = motor.getListaCategorias().get(index);
					categoria.setNombre(gui.edit.text2.getText());
					motor.getDAOCategoria().actualizar(categoria);
					motor.setListaCategorias(motor.getDAOCategoria().listar());
					actualizarItems(gui.panelCategorias, motor.getListaCategorias());
					break;
			}
			
			gui.principal.repaint();
			
		}else if(e.getSource() == gui.edit.getInsertar()) {
			
			gui.supletorio.setVisible(false);
			
			if(e.getActionCommand().equals("insertarAutor")) {
				
				Autor autor = new Autor();
				autor.setCodAutor(motor.getDAOAutor().getNextCode());
				autor.setNombre(gui.edit.text2.getText());
				motor.getDAOAutor().registrar(autor);
				motor.setListaAutores(motor.getDAOAutor().listar());
				actualizarItems(gui.panelAutores, motor.getListaAutores());
				
			}else if(e.getActionCommand().equals("insertarCliente")) {
				
				Cliente cliente = new Cliente();
				cliente.setDni(gui.edit.text1.getText());
				cliente.setNombre(gui.edit.text2.getText());
				cliente.setApellido1(gui.edit.text3.getText());
				cliente.setApellido2(gui.edit.text4.getText());
				cliente.setDireccion(gui.edit.text5.getText());
				cliente.setEmail(gui.edit.text6.getText());
				cliente.setFechaNacimiento(new java.sql.Date(gui.edit.fecha.getDate().getTime()));
				cliente.setUsuario(gui.edit.text8.getText());
				cliente.setContrasenya("123456");
				motor.getDAOCliente().registrar(cliente);
				motor.setListaClientes(motor.getDAOCliente().listar());
				actualizarItems(gui.panelClientes, motor.getListaClientes());
				
			}else if(e.getActionCommand().equals("insertarEditorial")) {
				
				Editorial editorial = new Editorial();
				editorial.setCodEditorial(motor.getDAOEditorial().getNextCode());
				editorial.setNombre(gui.edit.text2.getText());
				motor.getDAOEditorial().registrar(editorial);
				motor.setListaEditoriales(motor.getDAOEditorial().listar());
				actualizarItems(gui.panelEditoriales, motor.getListaEditoriales());
				
			}else if(e.getActionCommand().equals("insertarLibro")) {

				Libro libro = ((VistaEditarLibro) gui.edit).getLibro();
				libro.setIsbn(Integer.parseInt(gui.edit.text1.getText()));
				libro.setTitulo(gui.edit.text2.getText());
				libro.setPrecio(Double.parseDouble(gui.edit.text3.getText()));
				libro.setStock(Integer.parseInt(gui.edit.text4.getText()));
				libro.setCodCategoria(Integer.parseInt(gui.edit.text5.getText()));
				libro.setCodEditorial(Integer.parseInt(gui.edit.text6.getText()));
				motor.getDAOLibro().registrar(libro);
				motor.getDAOLibro().actualizarAutores(libro);
				motor.setListaLibros(motor.getDAOLibro().listar());
				motor.firmarLibros();
				actualizarItems(gui.panelLibros, motor.getListaLibros());
				
			}else if(e.getActionCommand().equals("insertarCategoria")) {
				
				Categoria categoria = new Categoria();
				categoria.setCodCategoria(motor.getDAOCategoria().getNextCode());
				categoria.setNombre(gui.edit.text2.getText());
				motor.getDAOCategoria().registrar(categoria);
				motor.setListaCategorias(motor.getDAOCategoria().listar());
				actualizarItems(gui.panelCategorias, motor.getListaCategorias());
			}
			
			gui.principal.repaint();
		}
	}
	
	/**
	 * Ajusta el tamaño del JFrame supletorio de la GUI a un tamaño dado,
	 * y modifica la posición de los botones.
	 * @param anchura
	 * @param altura
	 */
	public void ajustarSupletorio(int anchura, int altura) {
		
		gui.supletorio.setSize(anchura, altura);
		gui.edit.getCancelar().setBounds(100, altura-80, gui.edit.getCancelar().getWidth(), gui.edit.getCancelar().getHeight());
		gui.edit.getCommit().setBounds(220, altura-80, gui.edit.getCommit().getWidth(), gui.edit.getCommit().getHeight());
		gui.edit.getInsertar().setBounds(220, altura-80, gui.edit.getInsertar().getWidth(), gui.edit.getInsertar().getHeight());
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if(e.getStateChange() == ItemEvent.SELECTED) {
			
			//RESETEAR LOS AUTORES DEL LIBRO
			((VistaEditarLibro) gui.edit).getLibro().setAutores(new ArrayList<Autor>());
			
			//POR CADA COMBO
			for(int j = 0; j < gui.edit.combos.size(); j++) {
				
				JComboBox<String> combo = gui.edit.combos.get(j);
				
				//SI ES UN AUTOR
				if(!combo.getSelectedItem().equals("Seleccionar...")) {
					
					//Y NO EXISTE YA EN EL ARRAY 
					//TODO: REFACTORIZAR: AHORA LOS AUTORES SON COMPARABLES (ARRAY.CONTAINS())
					boolean existe = false;
					for(int i = 0; i < ((VistaEditarLibro) gui.edit).getLibro().getAutores().size(); i++) {
						
						if(((VistaEditarLibro) gui.edit).getLibro().getAutores().get(i) ==  motor.getListaAutores().get(combo.getSelectedIndex()-1)) {
							
							existe = true;
						}
					}
					
					//AÑADIR AL ARRAY DE AUTORES
					if(!existe) {
						
						((VistaEditarLibro) gui.edit).getLibro().getAutores().add(motor.getListaAutores().get(combo.getSelectedIndex()-1));
					}
				}
				
				//ELIMINAR EL COMBO
				gui.edit.remove(combo);
			}
			
			//RESETEAR Y RECARGAR COMBOS
			gui.edit.combos = new ArrayList<JComboBox<String>>();
			cargarCombosAutores(((VistaEditarLibro) gui.edit).getLibro(), gui.edit);
			
			//REFRESCAR
			gui.edit.revalidate();
			gui.supletorio.repaint();
			
		}else if(e.getStateChange() == ItemEvent.DESELECTED) {
			
			
		}
	}
	
	/**
	 * Lanza un mensaje de diálogo que permite eliminar un 
	 * item de la base de datos o cancelar la operación.
	 * @param frame JFrame de referencia para posición.
	 * @param mensaje String con el mensaje a mostrar.
	 * @param tipoItem El tipo de item a eliminar.
	 * @param index El índice del item a eliminar en el modelo.
	 */
	public void lanzarDialogoEliminar(JFrame frame, String mensaje, String tipoItem, int index) {
		
		String[] opciones = {"Eliminar", "Cancelar"};
		
		JDialog dialogo = new JDialog(frame, "Eliminar " + tipoItem + "...", Dialog.ModalityType.APPLICATION_MODAL); //BLOQUEANTE
		
        JOptionPane op = new JOptionPane( //AÑADIR LAS OPCIONES
        			
				 mensaje, //EL MENSAJE DEL CUADRO DE DIÁLOGO
				 JOptionPane.WARNING_MESSAGE, //int TIPO OPCIONES
				 JOptionPane.INFORMATION_MESSAGE, //int TIPO MENSAJE
				 null, //Icon ICONO
				 opciones, //Object[] OPCIONES
				 opciones[0]);
        
        op.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        op.setPreferredSize(new Dimension(300, 88));
        
        op.addPropertyChangeListener(new PropertyChangeListener() { //SU LISTENER TODO: SEPARAR DE AQUÍ
           
            public void propertyChange(PropertyChangeEvent e) {
            	
                String opcion = String.valueOf(e.getNewValue());
                
                if(opcion.equals(opciones[0])) {
                	
                	Motor.log("Eliminando item...");
                	dialogo.dispose();
                	motor.eliminarItem(tipoItem, index);

                }else if (opcion.equals(opciones[1])){
                	
                	Motor.log("Cancelar eliminación.");
                	dialogo.dispose();
                }
            }
        });

        dialogo.setLayout(new BorderLayout());
        dialogo.setContentPane(op);
        dialogo.pack();
        dialogo.setLocationRelativeTo(frame);
        dialogo.setVisible(true);
	}
	////
}
