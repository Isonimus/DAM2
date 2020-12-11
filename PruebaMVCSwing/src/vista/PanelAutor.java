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

public class PanelAutor implements ActionListener, 
									KeyListener,
									ListSelectionListener, 
									TableModelListener,
									MouseListener{
	
	private JFrame marco;
	private Controlador controlador;
	private JLabel tituloFuncion = new JLabel("MANTENIMIENTO DE AUTORES");
	private JLabel etiquetaNuevo = new JLabel("Nuevo autor: ");
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
	
	public PanelAutor(JFrame marco, Controlador controlador) {
		
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
				return columnIndex == 1;
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
			
			ResultSet datos = controlador.obtenerDatosMasMetadatosAutor();
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
	
	public void reestablecerPanelCRUD(){
		
	}
	
	public void deshabilitarPanelEdicion() {
		
	}
	
	public JPanel getPanelCentral() {
		
		return panelCentral;
	}
	
	public Dimension getTamanoMarcoRequerido() {
		
		return tamanoMarcoRequerido;
	}
	
	public void modificarRegistro(int id, String valor, int fila, int columna) {
		
		String pregunta = "Ha modificado el nombre del autor. ¿Está seguro?";
		String feedback = "No se ha modificado el autor.";
		if(preguntarUsuario(pregunta) == JOptionPane.YES_OPTION) {
			
		}
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
				etiquetaNuevo.setText("Nuevo Autor: ");
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
		
		switch(ae.getActionCommand()) {
			
			case "nuevo":
				tabla.setEnabled(true);
				tabla.clearSelection();
				deshabilitarPanelCRUD();
				habilitarPanelEdicion();
				break;
				
			case "Cancelar":
				deshabilitarPanelEdicion();
				modoEdicion = false;
				etiquetaNuevo.setText("Nuevo Autor: ");
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
				etiquetaNuevo.setText("Editar Autor: ");
				textoNuevo.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 1));
				habilitarPanelEdicion();
				aceptarNuevo.setActionCommand("Modificar");
				break;
				
			case "Modificar":
				modificarRegistro();
				deshabilitarPanelEdicion();
				etiquetaNuevo.setText("Nuevo Autor: ");
				aceptarNuevo.setActionCommand("Aceptar");
				habilitarPanelCRUD();
				tabla.setEnabled(true);
				modoEdicion = false;
				break;
		}
		
	}

	private void borrarRegistro() {
		// TODO Auto-generated method stub
		
	}

	private void habilitarPanelCRUD() {
		// TODO Auto-generated method stub
		
	}

	private void registrarNuevo() {
		// TODO Auto-generated method stub
		
	}

	private void habilitarPanelEdicion() {
		textoNuevo.setEnabled(true);
		
	}

	private void deshabilitarPanelCRUD() {
		// TODO Auto-generated method stub
		
	}
}
