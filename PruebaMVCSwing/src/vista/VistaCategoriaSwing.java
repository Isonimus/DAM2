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
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;

public class VistaCategoriaSwing implements WindowListener{
	
	private Controlador controlador;
	private DefaultTableModel modeloTabla;
	
	public VistaCategoriaSwing(Controlador controlador) {
		
		this.setControlador(controlador);
		initGUIComponents();
		cargarDatosEnTabla(modeloTabla);
	}
	
	public void initGUIComponents() {
		
		JFrame marco = new JFrame("SWING: Mostrar Categorías.");
		marco.setSize(300, 200);
		marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		marco.setLocationRelativeTo(null);
		
		JLabel titulo = new JLabel("   Listado de Categorías:");
		
		modeloTabla = new DefaultTableModel() {
			
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				
				return columnIndex == 1;
			}
		};
		
		JTable tabla = new JTable(modeloTabla);
		JScrollPane panelTabla = new JScrollPane(tabla);
		panelTabla.setPreferredSize(new Dimension(200, 200));
		
		JPanel panelIzquierdo = new JPanel();
		JPanel panelDerecho = new JPanel();
		JPanel panelSur = new JPanel();
		
		marco.addWindowListener(this);
		
		marco.add(titulo, BorderLayout.NORTH);
		marco.add(panelIzquierdo, BorderLayout.WEST);
		marco.add(panelTabla, BorderLayout.CENTER);
		marco.add(panelDerecho, BorderLayout.EAST);
		marco.add(panelSur, BorderLayout.SOUTH);
		
		marco.setVisible(true);
	}
	
	public void cargarDatosEnTabla(DefaultTableModel modeloTabla) {
		
		try {
			
			ResultSet datos = controlador.obtenerDatosMasMetadatosCategoria();
			ResultSetMetaData metadatos = datos.getMetaData();
			
			//CREAR CABECERAS
			for(int col = 1; col <= metadatos.getColumnCount(); col++) {
				
				modeloTabla.addColumn(metadatos.getColumnLabel(col));
			}
			
			while(datos.next()) {
				
				Object[] fila = new Object[metadatos.getColumnCount()];
				
				for(int col = 0; col < metadatos.getColumnCount(); col++) {
					
					fila[col] = datos.getObject(col + 1);
				}
				
				modeloTabla.addRow(fila);
			}
			
		}catch(SQLException e){
			
		}
		
	}

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	@Override
	public void windowClosing(WindowEvent arg0) {
		
		controlador.terminar();
		System.out.println("Aplicación terminada.");
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {;}

	@Override
	public void windowClosed(WindowEvent arg0) {;}

	@Override
	public void windowDeactivated(WindowEvent arg0) {;}

	@Override
	public void windowDeiconified(WindowEvent arg0) {;}

	@Override
	public void windowIconified(WindowEvent arg0) {;}

	@Override
	public void windowOpened(WindowEvent arg0) {;}
}
