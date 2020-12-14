package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;

public class PanelLibro implements ActionListener, 
									KeyListener,
									ListSelectionListener, 
									TableModelListener,
									MouseListener{
	
	private JFrame marco;
	private Controlador controlador;
	private JLabel tituloFuncion = new JLabel("MANTENIMIENTO DE LIBROS");
	private JLabel etiquetaNuevo = new JLabel("Nuevo libro: ");
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private ListSelectionModel seleccionTabla;
	private JButton nuevo;
	private JButton borrar;
	private JButton editar;
	private JTextField textoNuevo;
	private JButton cancelarNuevo;
	private JButton aceptarNuevo;
	private String valorInicialCeldaAUX;
	private boolean modoEdicion = false;
	
	//EL PANEL BASE
	private JPanel panelCentral;
	
	//EL TAMAÑO REQUERIDO DEL MARCO
	//EN FUNCIÓN DEL <panelCentral>
	private Dimension tamanoMarcoRequerido;
	
	public PanelLibro(JFrame marco, Controlador controlador) {
		
		this.marco = marco;
		this.controlador = controlador;
		initGUIComponents();
	}
	
	public void initGUIComponents() {
		
		modeloTabla = new DefaultTableModel() {
			
			// Nº SERIE
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int roeIndex, int columnIndex) {
				return true;
			}
		};
		
		tabla = new JTable(modeloTabla);
		
		// SELECCIÓN SIMPLE
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//LISTENERS DE LA TABLA
		seleccionTabla = tabla.getSelectionModel();
		seleccionTabla.addListSelectionListener(this);
		modeloTabla.addTableModelListener(this);
		tabla.addMouseListener(this);
		
		//SCROLL
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		//BOTONES CRUD
		editar = new JButton("Editar");
		borrar = new JButton("Borrar");
		nuevo = new JButton("Nuevo");
		
		//ESTADO INICIAL DE LOS BOTONES CRUD
		reestablecerPanelCRUD();
		
		//PANEL PARA LOS BOTONES CRUD
		JPanel botonesCRUD = new JPanel(new GridLayout(3, 1));
		botonesCRUD.add(editar);
		botonesCRUD.add(borrar);
		botonesCRUD.add(nuevo);
		
		//LISTENERS BOTONES
		editar.addActionListener(this);
		borrar.addActionListener(this);
		nuevo.addActionListener(this);
		
		//COMPONENTES EDICION
		textoNuevo = new JTextField(45);
		cancelarNuevo = new JButton("Cancelar");
		aceptarNuevo = new JButton("Aceptar");
		deshabilitarPanelEdicion();
		
		//PANELES EDICION
		JPanel panelEdicion = new JPanel(new BorderLayout());
		JPanel panelEdicionNuevo = new JPanel();
		
		//BOXLAYOUT PARA CONTROLAR LOS ELEMENTOS
		panelEdicionNuevo.setLayout(new BoxLayout(panelEdicionNuevo, BoxLayout.LINE_AXIS));
		panelEdicionNuevo.add(textoNuevo);
		panelEdicionNuevo.add(cancelarNuevo);
		panelEdicionNuevo.add(aceptarNuevo);
		
		//DISTRIBUCIÓN DE LOS CONTROLES
		panelEdicion.add(new JPanel(), BorderLayout.NORTH);
		panelEdicion.add(etiquetaNuevo, BorderLayout.CENTER);
		panelEdicion.add(panelEdicionNuevo, BorderLayout.SOUTH);
		
		//LISTENERS EDICION
		textoNuevo.addKeyListener(this);
		cancelarNuevo.addActionListener(this);
		aceptarNuevo.addActionListener(this);
		
		//AGRUPAR LOS COMPONENTES PARA ESTA FUNCIÓN
		panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		
		panelCentral.add(tituloFuncion, BorderLayout.NORTH);
		panelCentral.add(panelTabla, BorderLayout.CENTER);
		panelCentral.add(botonesCRUD, BorderLayout.EAST);
		panelCentral.add(panelEdicion, BorderLayout.SOUTH);
		
		//TAMAÑO DEL MARCO REQUERIDO
		tamanoMarcoRequerido = new Dimension(710, 250);
		marco.setSize(tamanoMarcoRequerido);
		
		//ACTUALIZAR EL FRAME PRINCIPAL
		marco.add(panelCentral, BorderLayout.CENTER);
		
		//VALIDAR Y REPINTAR
		marco.revalidate();
		marco.repaint();
		
		//CARGAR DATOS EN LA TABLA
		cargarDatosEnTabla(modeloTabla);
	}
	
	public void cargarDatosEnTabla(DefaultTableModel modeloTabla) {
		
		try {
			
			ResultSet datos = controlador.obtenerDatosMasMetadatosLibro();
			ResultSetMetaData metadatos = datos.getMetaData();
			
			//CREAR CABECERAS
			for(int col = 1; col <= metadatos.getColumnCount(); col++) {
				
				modeloTabla.addColumn(metadatos.getColumnLabel(col));
			}
			
			//CARGA DE DATOS
			while(datos.next()) {
				
				Object[] fila = new Object[metadatos.getColumnCount()];
				
				for(int col = 0; col < metadatos.getColumnCount(); col++) {
					
					fila[col] = datos.getObject(col + 1);
				}
				
				modeloTabla.addRow(fila);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public JPanel getPanelCentral() {
		
		return panelCentral;
	}
	
	public Dimension getTamanoMarcoRequerido() {
		
		return tamanoMarcoRequerido;
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		
		valorInicialCeldaAUX = (String) tabla.getValueAt(tabla.getSelectedRow(), 1);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {;}

	@Override
	public void mouseExited(MouseEvent arg0) {;}

	@Override
	public void mousePressed(MouseEvent arg0) {;}

	@Override
	public void mouseReleased(MouseEvent arg0) {;}

	@Override
	//!!!LOS ÍNDICES DE FILAS Y COLUMNAS EMPIEZAN EN 1!!
	public void valueChanged(ListSelectionEvent lse) {
		
		if(tabla.getSelectedRow() == -1) {
			
			editar.setEnabled(false);
			borrar.setEnabled(false);
			
		}else {
			
			editar.setEnabled(true);
			borrar.setEnabled(true);
		}
	}
	
	@Override
	public void tableChanged(TableModelEvent tme) {
		// CAMBIOS EN EL MODELO DE DATOS (CELDAS)
		// EN LA CARGA INICIAL SE LANZAN EVENTOS CON VALOR -1
		// EL PRIMER IF LOS ESQUIVA
		if(tme.getFirstRow() >= 0 && tme.getColumn() >= 0) {
			
			if(!modoEdicion) {
				
				String valorFinalCeldaAUX = (String) tabla.getValueAt(tme.getFirstRow(), tme.getColumn());
				
				if(valorInicialCeldaAUX.compareTo(valorFinalCeldaAUX) != 0) {
					
					modificarRegistro((int) tabla.getValueAt(tme.getFirstRow(), 0), valorFinalCeldaAUX, tme.getFirstRow(), tme.getColumn());
				}
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {

		if(KeyEvent.getKeyText(ke.getKeyCode()).equals("Enter")) {
		
			if(!modoEdicion) {
				
				registrarNuevo();
				deshabilitarPanelEdicion();
				habilitarPanelCRUD();
				tabla.setEnabled(true);
				
			}else {
				
				modificarRegistro();
				deshabilitarPanelEdicion();
				etiquetaNuevo.setText("Nuevo Libro: ");
				aceptarNuevo.setActionCommand("Aceptar");
				habilitarPanelCRUD();
				tabla.setEnabled(true);
				modoEdicion = false;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {;}

	@Override
	public void keyTyped(KeyEvent arg0) {;}

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println(ae.getActionCommand());
		switch(ae.getActionCommand()) {
			
			case "Nuevo":
				tabla.setEnabled(true);
				tabla.clearSelection();
				deshabilitarPanelCRUD();
				habilitarPanelEdicion();
				break;
				
			case "Cancelar":
				deshabilitarPanelEdicion();
				modoEdicion = false;
				etiquetaNuevo.setText("Nuevo Libro: ");
				aceptarNuevo.setActionCommand("Aceptar");
				reestablecerPanelCRUD();
				tabla.setEnabled(true);
				tabla.clearSelection();
				break;
				
			case "Aceptar":
				registrarNuevo();
				deshabilitarPanelEdicion();
				habilitarPanelCRUD();
				tabla.setEnabled(true);
				break;
				
			case "Borrar":
				borrarRegistro();
				break;
				
			case "Editar":
				modoEdicion = true;
				tabla.setEnabled(false);
				deshabilitarPanelCRUD();
				etiquetaNuevo.setText("Editar Libro: ");
				textoNuevo.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 1));
				habilitarPanelEdicion();
				aceptarNuevo.setActionCommand("Modificar");
				break;
				
			case "Modificar":
				modificarRegistro();
				deshabilitarPanelEdicion();
				etiquetaNuevo.setText("Nuevo Libro: ");
				aceptarNuevo.setActionCommand("Aceptar");
				habilitarPanelCRUD();
				tabla.setEnabled(true);
				modoEdicion = false;
				break;
		}
		
	}
	
	// MÉTODOS DE UTILIDAD
	// PANEL EDICIÓN
	private void habilitarPanelEdicion() {
		
		textoNuevo.setEnabled(true);
		textoNuevo.requestFocus();
		cancelarNuevo.setEnabled(true);
		aceptarNuevo.setEnabled(true);
	}
	
	public void deshabilitarPanelEdicion() {
		
		textoNuevo.setText("");
		textoNuevo.setEnabled(false);
		cancelarNuevo.setEnabled(false);
		aceptarNuevo.setEnabled(false);
	}
	
	// PANEL CRUD
	public void reestablecerPanelCRUD(){
		
		editar.setEnabled(false);
		borrar.setEnabled(false);
		nuevo.setEnabled(true);
	}

	private void habilitarPanelCRUD() {
		
		editar.setEnabled(true);
		borrar.setEnabled(true);
		nuevo.setEnabled(true);
	}
	
	private void deshabilitarPanelCRUD() {

		editar.setEnabled(false);
		borrar.setEnabled(false);
		nuevo.setEnabled(false);
	}
	
	//JOPTIONPANE DE INFO AL USUARIO
	private void informarUsuario(String mensaje) {
		
		JOptionPane.showMessageDialog(marco, mensaje, tituloFuncion.getText(), JOptionPane.INFORMATION_MESSAGE);
	}
	
	//Método genérico para preguntar al usuario
	private int preguntarUsuario(String pregunta) {
		
		int confirmacion = JOptionPane.showConfirmDialog(marco, pregunta);
		return confirmacion;
	}
	
	// EL CRUD
	//Modifica el regisro seleccionado
	private void modificarRegistro() {
		
		String feedBack = controlador.actualizarAutor((int)tabla.getValueAt(tabla.getSelectedRow(), 0), textoNuevo.getText());
		//
		if (feedBack.equals("Modificación de autor correcta.")) {
			modeloTabla.setValueAt(textoNuevo.getText(), tabla.getSelectedRow(), 1);
		}
		informarUsuario(feedBack);
	}
	
	//Cuando la modificación se produce en la propia tabla
	private void modificarRegistro(int claveModificacion, String valorModificacion, int fila, int columna) {
		
		String pregunta = "Ha modificado el nombre del autor cuyo código es: " + claveModificacion + "\n¿Está seguro?";
		String feedBack = "No se ha modificado el autor.";
		if (preguntarUsuario(pregunta) == JOptionPane.YES_OPTION) {
			feedBack = controlador.actualizarAutor(claveModificacion, valorModificacion);
		}
		else {
			tabla.setValueAt(valorInicialCeldaAUX, fila, columna);
		}
		informarUsuario(feedBack);
	}
	
	private void borrarRegistro() {
		//Preparación pregunta para el usuario
		String pregunta = "Va a borrar el autor con código: " + tabla.getValueAt(tabla.getSelectedRow(), 0) + "\n¿Está seguro?";
		String feedBack = "No se ha borrado el autor.";
		if (preguntarUsuario(pregunta) == JOptionPane.YES_OPTION) {
			feedBack = controlador.eliminarAutor((int)tabla.getValueAt(tabla.getSelectedRow(), 0));
			//
			if (feedBack.equals("Se ha borrado el autor.")) {
				modeloTabla.removeRow(tabla.getSelectedRow());
			}
		}
		informarUsuario(feedBack);
	}

	private void registrarNuevo() {
		//Se solicita al controlador que registre los datos y se informa del resultado al usuario
		informarUsuario(controlador.insertarAutor(textoNuevo.getText()));
		//Se limpia la tabla de datos
		modeloTabla.setRowCount(0);
		modeloTabla.setColumnCount(0);
		//Se carga la tabla que ya contiene los datos nuevos y se muestra el último registro añadido
		cargarDatosEnTabla(modeloTabla);
		//Se muestra la última entrada en la tabla
		tabla.setRowSelectionInterval(tabla.getRowCount() - 1, tabla.getRowCount() - 1);
		tabla.scrollRectToVisible(tabla.getCellRect(tabla.getRowCount() -1 , 0, true));
	}
}
