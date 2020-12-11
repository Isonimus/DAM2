package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Modelo;

public class VistaAutorSwing implements WindowListener{
	
	private Controlador controlador;
	private DefaultTableModel modeloTabla;
	
	public VistaAutorSwing(Controlador controlador) {
		
		this.setControlador(controlador);
		initGUIComponents();
		//CARGA DE DATOS
		cargarDatosEnTabla(modeloTabla);
	}
	
	public void initGUIComponents() {
		
		JFrame marco = new JFrame("SWING: Mostrar Autores.");
		marco.setSize(300, 200);
		marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		marco.setLocationRelativeTo(null);
		
		//COMPONENTES
		JLabel titulo = new JLabel("   Listado de Autores:");
		modeloTabla = new DefaultTableModel() {
			/**
			 * SERIALIZABLE: VERSIÓN 1
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				
				return columnIndex == 1; // TRUE SI ES LA PRIMERA ([1]) COL
			}
		};
		
		JTable tabla = new JTable(modeloTabla);
		JScrollPane panelTabla = new JScrollPane(tabla);
		panelTabla.setPreferredSize(new Dimension(200, 200));
		JPanel panelIzquierdo = new JPanel();
		//panelIzquierdo.setBackground(Color.blue);
		JPanel panelDerecho = new JPanel();
		//panelDerecho.setBackground(Color.blue);
		JPanel panelSur = new JPanel();
		//panelSur.setBackground(Color.red);
		
		//AÑADIR LISTENER
		marco.addWindowListener(this);
		
		//AÑADIR COMPONENTES A CONTENTPANE:
		marco.add(titulo, BorderLayout.NORTH);
		marco.add(panelIzquierdo, BorderLayout.WEST);
		marco.add(panelTabla, BorderLayout.CENTER);
		marco.add(panelDerecho, BorderLayout.EAST);
		marco.add(panelSur, BorderLayout.SOUTH);
	
		marco.setVisible(true);
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

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	// WINDOWLISTENER
	//-------------------------------------------------------------------------------------------------
	
	@Override
	public void windowClosing(WindowEvent arg0) { // SE LLAMA AL PULSAR ASPA [X]
		
		controlador.terminar();
		System.out.println("Aplicación terminada.");
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) { /*GANAR FOCUS*/}

	@Override
	public void windowClosed(WindowEvent arg0) { /*CERRAR VENTANA*/}

	@Override
	public void windowDeactivated(WindowEvent arg0) { /* PERDER FOCUS*/}

	@Override
	public void windowDeiconified(WindowEvent arg0) { /* ABRIR DESDE MINIMIZADO*/}

	@Override
	public void windowIconified(WindowEvent arg0) { /* MINIMIZADO*/}

	@Override
	public void windowOpened(WindowEvent arg0) { /* ABRIR VENTANA*/}
	
	// FIN WINDOWLISTENER
	//-------------------------------------------------------------------------------------------------
}
