package vista;

import java.awt.BorderLayout;
import java.awt.Component;
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
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Categoria;
import modelo.Editorial;
import modelo.Libro;

public class PanelLibro implements ActionListener, 
									KeyListener,
									ListSelectionListener, 
									TableModelListener,
									MouseListener{
	
	private JFrame marco;
	private Controlador controlador;
	private JLabel tituloFuncion = new JLabel("MANTENIMIENTO DE LIBROS");
	private JLabel etiquetaNuevo = new JLabel("Nuevo libro: ");
	private JTable tablaLibros;
	private DefaultTableModel modeloTabla;
	private ListSelectionModel seleccionTabla;
	private JButton nuevo;
	private JButton borrar;
	private JButton editar;
	private JTabbedPane solapas;
	
	//AUTORES DEL LIBRO
	private JLabel autoresLibroTitulo;
	private JTable tablaAutoresLibro;
	private DefaultTableModel modeloTablaAutoresLibro;
	private ListSelectionModel seleccionTablaAutoresLibro;
	private JButton quitarAutor;
	
	//AUTORES GENERAL
	private JLabel autoresTitulo;
	private JTable tablaAutores;
	private DefaultTableModel modeloTablaAutores;
	private ListSelectionModel seleccionTablaAutores;
	private JButton anyadirAutor;
	private JButton recargarAutores;
	
	//EDICION LIBRO
	private JLabel etiquetaISBN;
	private JTextField textoISBN;
	private JLabel etiquetaTitulo;
	private JTextField textoTitulo;
	private JLabel etiquetaPrecio;
	private JTextField textoPrecio;
	private JLabel etiquetaExistencias;
	private JTextField textoExistencias;
	private JLabel etiquetaCategoria;
	private JComboBox<Categoria> comboCategorias;
	private JLabel etiquetaEditorial;
	private JComboBox<Editorial> comboEditoriales;
	private JButton cancelarNuevo;
	private JButton aceptarNuevo;
	private JButton limpiarNuevo;
	private JButton recargarListas;
	private String valorInicialCeldaAUX;
	private String valorInicialISBN;
	private boolean modoEdicion = false;
	Dimension dimensionBoton = new Dimension(300, 30);
	
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
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		
		tablaLibros = new JTable(modeloTabla);
		
		// SELECCIÓN SIMPLE
		tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//LISTENERS DE LA TABLA
		seleccionTabla = tablaLibros.getSelectionModel();
		seleccionTabla.addListSelectionListener(this);
		modeloTabla.addTableModelListener(this);
		tablaLibros.addMouseListener(this);
		
		//SCROLL
		JScrollPane panelTabla = new JScrollPane(tablaLibros);
		
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
		
		//COMPONENTES PANEL EDICIÓN DE AUTORES
		//AUTORES DEL LIBRO
		autoresLibroTitulo = new JLabel("    Autores del libro");
		modeloTablaAutoresLibro = new DefaultTableModel() {
			
			// Nº SERIE
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		
		//EL MODO DE SELECCIÓN
		tablaAutoresLibro = new JTable(modeloTablaAutoresLibro);
		tablaAutoresLibro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//SCROLL
		JScrollPane panelTablaAutoresLibro = new JScrollPane(tablaAutoresLibro);
		panelTablaAutoresLibro.setPreferredSize(new Dimension(500, 30));
		
		//LISTENERS DE LA TABLA
		seleccionTablaAutoresLibro = tablaAutoresLibro.getSelectionModel();
		seleccionTablaAutoresLibro.addListSelectionListener(this);
		modeloTablaAutoresLibro.addTableModelListener(this);
		tablaAutoresLibro.addMouseListener(this);
		
		//BOTONES
		quitarAutor = new JButton("Quitar Autor");
		quitarAutor.addActionListener(this);
		quitarAutor.setEnabled(false);
		
		//AUTORES GENERAL
		autoresTitulo = new JLabel("    Autores");
		modeloTablaAutores = new DefaultTableModel() {
			
			// Nº SERIE
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		
		//EL MODO DE SELECCIÓN
		tablaAutores = new JTable(modeloTablaAutores);
		tablaAutores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//SCROLL
		JScrollPane panelTablaAutores = new JScrollPane(tablaAutores);
		panelTablaAutores.setPreferredSize(new Dimension(500, 30));
				
		//LISTENERS DE LA TABLA
		seleccionTablaAutores = tablaAutores.getSelectionModel();
		seleccionTablaAutores.addListSelectionListener(this);
		modeloTablaAutores.addTableModelListener(this);
		tablaAutores.addMouseListener(this);
		
		//BOTONES
		anyadirAutor = new JButton("Añadir Autor");
		anyadirAutor.setActionCommand("Anyadir Autor");
		anyadirAutor.addActionListener(this);
		anyadirAutor.setMaximumSize(dimensionBoton);
		recargarAutores = new JButton("Recargar Autores");
		recargarAutores.addActionListener(this);
		recargarAutores.setMaximumSize(dimensionBoton);
		//PANELES AUTORES
		JPanel panelGeneralAutores = new JPanel(new GridLayout(2,1));
		JPanel panelAutoresLibro = new JPanel(new BorderLayout());
		JPanel panelAutores = new JPanel(new BorderLayout());
		JPanel panelBotoneraAutores = new JPanel();
		panelBotoneraAutores.setLayout(new BoxLayout(panelBotoneraAutores, BoxLayout.Y_AXIS));
		
		panelAutoresLibro.add(autoresLibroTitulo, BorderLayout.NORTH);
		panelAutoresLibro.add(panelTablaAutoresLibro, BorderLayout.CENTER);
		panelAutoresLibro.add(new JPanel(), BorderLayout.WEST);
		panelAutoresLibro.add(new JPanel(), BorderLayout.SOUTH);
		panelAutoresLibro.add(quitarAutor, BorderLayout.EAST);
		//
		panelBotoneraAutores.add(anyadirAutor);
		panelBotoneraAutores.add(recargarAutores);
		//
		panelAutores.add(autoresTitulo, BorderLayout.NORTH);
		panelAutores.add(panelTablaAutores, BorderLayout.CENTER);
		panelAutores.add(new JPanel(), BorderLayout.WEST);
		panelAutores.add(new JPanel(), BorderLayout.SOUTH);
		panelAutores.add(panelBotoneraAutores, BorderLayout.EAST);
		//
		panelGeneralAutores.add(panelAutoresLibro);
		panelGeneralAutores.add(panelAutores);
		deshabilitarPanelAutores();
		
		//COMPONENTES PANEL EDICIÓN
		etiquetaISBN = new JLabel("ISBN");
		etiquetaISBN.setAlignmentX(Component.LEFT_ALIGNMENT);
		textoISBN = new JTextField();
		textoISBN.setAlignmentX(Component.LEFT_ALIGNMENT);
		etiquetaTitulo = new JLabel("Título");
		textoTitulo = new JTextField();
		etiquetaPrecio = new JLabel("Precio");
		textoPrecio = new JTextField();
		etiquetaExistencias = new JLabel("Existencias");
		textoExistencias = new JTextField();
		etiquetaCategoria = new JLabel("Categoría");
		comboCategorias = new JComboBox<Categoria>();
		etiquetaEditorial = new JLabel("Editorial");
		comboEditoriales = new JComboBox<Editorial>();
		cancelarNuevo = new JButton("Cancelar");
		cancelarNuevo.setMaximumSize(dimensionBoton);
		aceptarNuevo = new JButton("Aceptar");
		aceptarNuevo.setMaximumSize(dimensionBoton);
		limpiarNuevo = new JButton("Limpiar");
		limpiarNuevo.setMaximumSize(dimensionBoton);
		recargarListas = new JButton("Recargar");
		recargarListas.setMaximumSize(dimensionBoton);
		cargarCombos();
		deshabilitarPanelEdicion();
		
		//PANELES EDICION
		JPanel panelEdicion = new JPanel(new BorderLayout());
		JPanel panelEdicionNuevo = new JPanel();
		JPanel panelEdicionTotal = new JPanel();
		
		//BOXLAYOUT PARA CONTROLAR LOS ELEMENTOS
		panelEdicionNuevo.setLayout(new GridLayout(12,1));
		panelEdicionNuevo.add(etiquetaISBN);
		panelEdicionNuevo.add(textoISBN);
		panelEdicionNuevo.add(etiquetaTitulo);
		panelEdicionNuevo.add(textoTitulo);
		panelEdicionNuevo.add(etiquetaPrecio);
		panelEdicionNuevo.add(textoPrecio);
		panelEdicionNuevo.add(etiquetaExistencias);
		panelEdicionNuevo.add(textoExistencias);
		panelEdicionNuevo.add(etiquetaCategoria);
		panelEdicionNuevo.add(comboCategorias);
		panelEdicionNuevo.add(etiquetaEditorial);
		panelEdicionNuevo.add(comboEditoriales);
		
		JPanel panelBotoneraNuevo = new JPanel();
		panelBotoneraNuevo.setLayout(new BoxLayout(panelBotoneraNuevo, BoxLayout.Y_AXIS));
		panelBotoneraNuevo.add(cancelarNuevo);
		panelBotoneraNuevo.add(aceptarNuevo);
		panelBotoneraNuevo.add(limpiarNuevo);
		panelBotoneraNuevo.add(recargarListas);
		
		panelEdicionTotal.setLayout(new BorderLayout());
		panelEdicionTotal.add(new JPanel(), BorderLayout.NORTH);
		panelEdicionTotal.add(new JPanel(), BorderLayout.WEST);
		panelEdicionTotal.add(panelEdicionNuevo, BorderLayout.CENTER);
		panelEdicionTotal.add(panelBotoneraNuevo, BorderLayout.EAST);
		panelEdicionTotal.add(new JPanel(), BorderLayout.SOUTH);
		
		//DISTRIBUCIÓN DE LOS CONTROLES
		panelEdicion.add(new JPanel(), BorderLayout.NORTH);
		panelEdicion.add(panelEdicionTotal, BorderLayout.CENTER);
		//panelEdicion.add(panelEdicionTotal, BorderLayout.SOUTH);
		
		//LISTENERS EDICION
		textoISBN.addKeyListener(this);
		textoTitulo.addKeyListener(this);
		textoPrecio.addKeyListener(this);
		textoExistencias.addKeyListener(this);
		cancelarNuevo.addActionListener(this);
		aceptarNuevo.addActionListener(this);
		limpiarNuevo.addActionListener(this);
		recargarListas.addActionListener(this);
		
		//AGRUPAR LOS COMPONENTES PARA ESTA FUNCIÓN
		panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		
		solapas = new JTabbedPane();
		solapas.addTab("Autores", null, panelGeneralAutores, "Editar autores del libro");
		solapas.addTab("Nuevo libro", null, panelEdicion, "Añadir nuevo libro");
		
		panelCentral.add(tituloFuncion, BorderLayout.NORTH);
		panelCentral.add(panelTabla, BorderLayout.CENTER);
		panelCentral.add(botonesCRUD, BorderLayout.EAST);
		panelCentral.add(solapas, BorderLayout.SOUTH);
		
		//TAMAÑO DEL MARCO REQUERIDO
		tamanoMarcoRequerido = new Dimension(710, 574);
		marco.setSize(tamanoMarcoRequerido);
		
		//ACTUALIZAR EL FRAME PRINCIPAL
		marco.add(panelCentral, BorderLayout.CENTER);
		
		//VALIDAR Y REPINTAR
		marco.revalidate();
		marco.repaint();
		
		//CARGAR DATOS EN LA TABLA
		cargarDatosEnTabla(modeloTabla);
		cargarDatosEnTablaAutores(modeloTablaAutores);
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
	
	public void cargarDatosEnTablaAutores(DefaultTableModel modeloTabla) {
		
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
	
	public void cargarDatosEnTablaAutoresLibro(DefaultTableModel modeloTabla, int isbn) {
		
		try {
			
			ResultSet datos = controlador.obtenerDatosMasMetadatosAutoresLibro(isbn);
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
	
	public void cargarCombos() {
		
		try {
			
			Vector<Categoria> categorias = controlador.obtenerCategorias();
			Vector<Editorial> editoriales = controlador.obtenerEditoriales();
			comboCategorias.removeAllItems();
			comboEditoriales.removeAllItems();
			
			for(Categoria categoria : categorias) {
				comboCategorias.addItem(categoria);
			}
			
			for(Editorial editorial : editoriales) {
				comboEditoriales.addItem(editorial);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		//valorInicialCeldaAUX = (String) tabla.getValueAt(tabla.getSelectedRow(), 1); // tabla.getSelectedColumn() PROBLEM TODO!!
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {;}

	@Override
	public void mouseExited(MouseEvent arg0) {;}

	@Override
	public void mousePressed(MouseEvent me) {
		
		//USAR ESTE LISTENER PARA ESTA FUNCION
		//DISCRIMINAR QUÉ COMPONENTE LANZÓ EL EVENTO (QUÉ TABLA)
		JTable tabla = (JTable) me.getSource();
		
		if(tabla == tablaLibros) {
			
			//VACIAR LA TABLA
			modeloTablaAutoresLibro.setRowCount(0);
			modeloTablaAutoresLibro.setColumnCount(0);
			
			int isbn = (int) tabla.getValueAt(tabla.getSelectedRow(), 0);
			System.out.println("Tabla superior: ISBN " + isbn);
			cargarDatosEnTablaAutoresLibro(modeloTablaAutoresLibro, isbn);
			habilitarPanelAutores();
			deshabilitarPanelAutores();
			tablaAutores.getSelectionModel().clearSelection();
			
		}else if(tabla == tablaAutoresLibro) {
			
			int id = (int) tabla.getValueAt(tabla.getSelectedRow(), 0);
			quitarAutor.setEnabled(true);
			System.out.println("Tabla media: Código " + id);
			
		}else if(tabla == tablaAutores) {
			
			int codAutor = (int) tabla.getValueAt(tabla.getSelectedRow(), 0);
			
			if(tablaLibros.getSelectedRowCount() > 0) {
				
				anyadirAutor.setEnabled(true);
			}
			
			System.out.println("Tabla inferior: Código " + codAutor);
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {;}

	@Override
	//!!!LOS ÍNDICES DE FILAS Y COLUMNAS EMPIEZAN EN 1!!
	public void valueChanged(ListSelectionEvent lse) {
		
		if(tablaLibros.getSelectedRow() == -1) {
			
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
				
				String valorFinalCeldaAUX = (String) tablaLibros.getValueAt(tme.getFirstRow(), tme.getColumn());
				
				if(valorInicialCeldaAUX.compareTo(valorFinalCeldaAUX) != 0) {
					
					//modificarRegistro((int) tabla.getValueAt(tme.getFirstRow(), 0), tme.getFirstRow(), tme.getColumn());
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
				tablaLibros.setEnabled(true);
				
			}else {
				
				modificarRegistro();
				deshabilitarPanelEdicion();
				etiquetaNuevo.setText("Nuevo Libro: ");
				aceptarNuevo.setActionCommand("Aceptar");
				habilitarPanelCRUD();
				tablaLibros.setEnabled(true);
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
				tablaLibros.setEnabled(true);
				tablaLibros.getSelectionModel().clearSelection();
				deshabilitarPanelCRUD();
				habilitarPanelEdicion();
				solapas.setSelectedIndex(1);
				//solapas.setTabComponentAt(1, new JLabel("Nuevo libro"));
				break;
				
			case "Cancelar":
				modoEdicion = false;
				etiquetaNuevo.setText("Nuevo Libro: ");
				aceptarNuevo.setActionCommand("Aceptar");
				reestablecerPanelCRUD();
				tablaLibros.setEnabled(true);
				tablaLibros.clearSelection();
				valorInicialISBN = null;
				deshabilitarPanelEdicion();
				solapas.setSelectedIndex(0);
				solapas.setTabComponentAt(1, new JLabel("Nuevo libro"));
				solapas.setEnabledAt(0, true);
				modeloTablaAutoresLibro.setRowCount(0);
				modeloTablaAutoresLibro.setColumnCount(0);
				break;
				
			case "Aceptar":
				
				try {
					
					registrarNuevo();
					reestablecerPanelCRUD();
					deshabilitarPanelEdicion();
					tablaLibros.setEnabled(true);
				
				}catch(NumberFormatException e) {
					
					informarUsuario("Los datos introducidos no tienen el formato correcto.\n"
							+ "Por favor, comprueba que correspondan con el siguiente patrón:\n"
							+ "ISBN -> Valor numérico de 8 o 13 cifras.\n"
							+ "Título -> Texto.\n"
							+ "Precio -> Valor numérico, admite decimales.\n"
							+ "Existencias -> Valor numérico entero. ");
					
				}catch(NullPointerException e) {
					
					informarUsuario("Debes seleccionar una Categoría y una Editorial para el libro.");
					
				}
				
				break;
				
			case "Borrar":
				borrarRegistro();
				break;
				
			case "Editar":
				modoEdicion = true;
				tablaLibros.setEnabled(false);
				deshabilitarPanelCRUD();
				cargarCombos();
				etiquetaNuevo.setText("Editar Libro: "); 
				valorInicialISBN = String.valueOf(tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0));
				cargarSeleccionEnFormulario();
				habilitarPanelEdicion();
				aceptarNuevo.setActionCommand("Modificar");
				solapas.setSelectedIndex(1);
				solapas.setTabComponentAt(1, new JLabel("Editar libro"));
				break;
				
			case "Modificar":
				
				try {
					
					modificarRegistro();
					etiquetaNuevo.setText("Nuevo Libro: ");
					aceptarNuevo.setActionCommand("Aceptar");
					reestablecerPanelCRUD();
					deshabilitarPanelEdicion();
					tablaLibros.setEnabled(true);
					modoEdicion = false;
					solapas.setTabComponentAt(1, new JLabel("Nuevo libro"));
					valorInicialISBN = null;
					
				}catch(NumberFormatException e) {
					
					informarUsuario("Los datos introducidos no tienen el formato correcto.\n"
							+ "Por favor, comprueba que correspondan con el siguiente patrón:\n"
							+ "ISBN -> Valor numérico de 8 o 13 cifras.\n"
							+ "Título -> Texto.\n"
							+ "Precio -> Valor numérico, admite decimales.\n"
							+ "Existencias -> Valor numérico entero. ");
				}
				
				break;
				
			case "Limpiar":
				
				if(modoEdicion) {
					cargarSeleccionEnFormulario();
				}else {
					limpiarFormulario();
				}
				break;
				
			case "Recargar":
				cargarCombos();
				break;
				
			case "Quitar Autor":
				
				int id = (int) tablaAutoresLibro.getValueAt(tablaAutoresLibro.getSelectedRow(), 0);
				int isbn = (int) tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0);
				eliminarAutor(id, isbn);
				System.out.println(id + " - " + isbn);
				break;
				
			case "Anyadir Autor":
				
				int id2 = (int) tablaAutores.getValueAt(tablaAutores.getSelectedRow(), 0);
				int isbn2 = (int) tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0);
				anyadirAutor(id2, isbn2);
				System.out.println(id2 + " - " + isbn2);
				break;
				
			case "Recargar Autores":
				
				modeloTablaAutores.setColumnCount(0);
				modeloTablaAutores.setRowCount(0);
				cargarDatosEnTablaAutores(modeloTablaAutores);
				break;
		}
	}
	
	// MÉTODOS DE UTILIDAD
	// PANEL EDICIÓN
	private void habilitarPanelEdicion() {
		
		textoISBN.setEnabled(true);
		textoISBN.requestFocus();
		textoTitulo.setEnabled(true);
		textoPrecio.setEnabled(true);
		textoExistencias.setEnabled(true);
		comboCategorias.setEnabled(true);
		comboEditoriales.setEnabled(true);
		cancelarNuevo.setEnabled(true);
		aceptarNuevo.setEnabled(true);
		limpiarNuevo.setEnabled(true);
		recargarListas.setEnabled(true);
	}
	
	public void deshabilitarPanelEdicion() {
		
		limpiarFormulario();
		textoISBN.setEnabled(false);
		textoTitulo.setEnabled(false);
		textoPrecio.setEnabled(false);
		textoExistencias.setEnabled(false);
		comboCategorias.setEnabled(false);
		comboEditoriales.setEnabled(false);
		cancelarNuevo.setEnabled(false);
		aceptarNuevo.setEnabled(false);
		limpiarNuevo.setEnabled(false);
		recargarListas.setEnabled(false);
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
	
	private void deshabilitarPanelAutores() {
		
		quitarAutor.setEnabled(false);
		anyadirAutor.setEnabled(false);
	}
	
	private void habilitarPanelAutores() {
		
		anyadirAutor.setEnabled(true);
	}
	
	private void limpiarFormulario() {
		
		textoISBN.setText("");
		textoTitulo.setText("");
		textoPrecio.setText("");
		textoExistencias.setText("");
		comboCategorias.setSelectedIndex(-1);
		comboEditoriales.setSelectedIndex(-1);
	}
	
	private void cargarSeleccionEnFormulario() {
		
		textoISBN.setText(valorInicialISBN);
		textoTitulo.setText((String) tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 1));
		textoPrecio.setText(String.valueOf(tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 2)));
		textoExistencias.setText(String.valueOf(tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 3)));
		int categoriaSeleccionada = parsearCategoria(String.valueOf(tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 4)));
		comboCategorias.setSelectedIndex(categoriaSeleccionada);
		int editorialSeleccionada = parsearEditorial(String.valueOf(tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 5)));
		comboEditoriales.setSelectedIndex(editorialSeleccionada);
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
		
		Libro libro = generarLibro();
		String feedBack = controlador.actualizarLibro(Integer.parseInt(valorInicialISBN), libro);
		
		if (feedBack.equals("Modificación de libro correcta.")) {
			
			modeloTabla.setRowCount(0);
			modeloTabla.setColumnCount(0);
			cargarDatosEnTabla(modeloTabla);
		}
		informarUsuario(feedBack);
	}
	
	private void anyadirAutor(int idAutor, int isbn) {
		
		String pregunta = "Va a añadir el autor con ID : " + idAutor + ".\n¿Está seguro?";
		String feedBack = "No se ha añadido el autor.";
		
		if (preguntarUsuario(pregunta) == JOptionPane.YES_OPTION) {
			
			feedBack = controlador.addAutorLibro(idAutor, isbn);
			
			if (feedBack.equals("Autor añadido correctamente al libro " + isbn + ".")) {
				
				modeloTablaAutoresLibro.setRowCount(0);
				modeloTablaAutoresLibro.setColumnCount(0);
				System.out.println("Recargando tabla: ISBN " + isbn);
				cargarDatosEnTablaAutoresLibro(modeloTablaAutoresLibro, isbn);
			}
		}
		informarUsuario(feedBack);
	}
	
	private void eliminarAutor(int idAutor, int isbn) {
		
		String pregunta = "Va a eliminar el autor con ID : " + idAutor + ".\n¿Está seguro?";
		String feedBack = "No se ha eliminado el autor.";
		
		if (preguntarUsuario(pregunta) == JOptionPane.YES_OPTION) {
			
			feedBack = controlador.dropAutorLibro(idAutor, isbn);
			
			if (feedBack.equals("Autor eliminado correctamente del libro " + isbn + ".")) {
				
				modeloTablaAutoresLibro.setRowCount(0);
				modeloTablaAutoresLibro.setColumnCount(0);
				System.out.println("Recargando tabla: ISBN " + isbn);
				cargarDatosEnTablaAutoresLibro(modeloTablaAutoresLibro, isbn);
			}
		}
		
		informarUsuario(feedBack);
	}
	
	//Cuando la modificación se produce en la propia tabla
	/*
	private void modificarRegistro(int claveModificacion, int fila, int columna) {
		
		Libro libro = new Libro((String) tabla.getValueAt(tabla.getSelectedRow(), 1));
		libro.setIsbn((int) tabla.getValueAt(tabla.getSelectedRow(), 0));
		libro.setPrecio(Double.parseDouble((String) tabla.getValueAt(tabla.getSelectedRow(), 2)));
		libro.setStock(Integer.parseInt((String) tabla.getValueAt(tabla.getSelectedRow(), 3)));
		libro.setCategoria(1);
		libro.setEditorial(1);
		String pregunta = "Ha modificado el libro cuyo ISBN es: " + claveModificacion + "\n¿Está seguro?";
		String feedBack = "No se ha modificado el libro.";
		
		if (preguntarUsuario(pregunta) == JOptionPane.YES_OPTION) {
			
			feedBack = controlador.actualizarLibro(claveModificacion, libro);
			
		}else{
			
			tabla.setValueAt(valorInicialCeldaAUX, fila, columna);
		}
		
		informarUsuario(feedBack);
	}
	*/
	
	private void borrarRegistro() {
		
		String pregunta = "Va a borrar el libro con ISBN: " + tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0) + "\n¿Está seguro?";
		String feedBack = "No se ha borrado el libro.";
		
		if (preguntarUsuario(pregunta) == JOptionPane.YES_OPTION) {
			
			feedBack = controlador.eliminarLibro((int)tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0));
			
			if (feedBack.equals("Se ha borrado el libro.")) {
				
				modeloTabla.removeRow(tablaLibros.getSelectedRow());
			}
		}
		
		informarUsuario(feedBack);
	}

	private void registrarNuevo() {
		
		Libro libro = generarLibro();
		
		//Se solicita al controlador que registre los datos y se informa del resultado al usuario
		informarUsuario(controlador.insertarLibro(libro));
		//Se limpia la tabla de datos
		modeloTabla.setRowCount(0);
		modeloTabla.setColumnCount(0);
		//Se carga la tabla que ya contiene los datos nuevos y se muestra el último registro añadido
		cargarDatosEnTabla(modeloTabla);
		//Se muestra la última entrada en la tabla
		tablaLibros.setRowSelectionInterval(tablaLibros.getRowCount() - 1, tablaLibros.getRowCount() - 1);
		tablaLibros.scrollRectToVisible(tablaLibros.getCellRect(tablaLibros.getRowCount() -1 , 0, true));
		tablaLibros.clearSelection();
	}
	
	private Libro generarLibro() throws NumberFormatException, NullPointerException{
		
		Libro libro = new Libro(textoTitulo.getText());
		libro.setIsbn(Integer.parseInt(textoISBN.getText())); //CAPTURAR EXCEPCIÓN DE FORMATO MISMATCH
		libro.setPrecio(Double.parseDouble(textoPrecio.getText())); // CAPTURAR EXCEPCIÓN DE FORMATO MISMATCH
		libro.setStock(Integer.parseInt(textoExistencias.getText())); // CAPTURAR EXCEPCIÓN DE FORMATO MISMATCH
		Categoria categoria = (Categoria) comboCategorias.getSelectedItem(); //CONTROL DE COMBO VACÍO TODO
		libro.setCategoria(categoria.getIdCategoria());
		Editorial editorial = (Editorial) comboEditoriales.getSelectedItem(); //CONTROL DE COMBO VACÍO TODO
		libro.setEditorial(editorial.getIdEditorial());
		return libro;
	}
	
	private int parsearCategoria(String categoria) {
		
		int index = -1; 
		
		try {
			
			Vector<Categoria> categorias = controlador.obtenerCategorias();
			
			for(int i = 0; i < categorias.size() && index < 0; i++) {
				
				if(categorias.get(i).getNombreCategoria().equals(categoria)) {
					
					index = i;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return index;
	}
	
	private int parsearEditorial(String editorial) {
		
		int index = -1; 
		
		try {
			
			Vector<Editorial> editoriales = controlador.obtenerEditoriales();
			
			for(int i = 0; i < editoriales.size() && index < 0; i++) {
				
				if(editoriales.get(i).getNombreEditorial().equals(editorial)) {
					
					index = i;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return index;
	}
}
