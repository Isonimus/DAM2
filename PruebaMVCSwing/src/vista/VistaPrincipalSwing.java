package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import controlador.Controlador;

public class VistaPrincipalSwing  implements WindowListener, ActionListener{
	
	private Controlador controlador;
	private JFrame marco;
	private PanelAutor panelAutor;
	private PanelEditorial panelEditorial;
	private PanelCategoria panelCategoria;
	private PanelLibro panelLibro;
	private JMenuBar menu;
	private JMenu menuApp;
	private JMenuItem menuAppAbout;
	private JMenuItem menuAppTerminar;
	private JMenu menuAutor;
	private JMenuItem menuAutorMantenimiento;
	private JMenuItem menuAutorListar;
	private JMenu menuEditorial;
	private JMenuItem menuEditorialMantenimiento;
	private JMenu menuCategoria;
	private JMenuItem menuCategoriaMantenimiento;
	private JMenu menuAlmacen;
	private JMenuItem menuAlmacenMantenimiento;
	private JMenu menuCatalogo;
	private JMenuItem menuCatalogoMantenimiento;
	private JMenu menuVenta;
	private JMenuItem menuVentaInforme;
	private JMenuItem itemMenuAux; //ALMACENA EL ITEM SELECCIONADO
	
	
	public VistaPrincipalSwing(Controlador controlador){
		
		super();
		this.controlador = controlador;
		initGUIComponents();
	}
	
	public void initGUIComponents() {
		
		marco = new JFrame("Librería Online - Administración");
		marco.setLayout(new BorderLayout());
		marco.setSize(710, 250);
		marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		marco.setLocationRelativeTo(null);
		
		//EL MENÚ
		menu = new JMenuBar();
		
		//MENÚ PRINCIPAL
		menuApp = new JMenu("Aplicación");
		menuAppAbout = new JMenuItem("Acerca de...");
		menuAppTerminar = new JMenuItem("Terminar");
		
		menuApp.add(menuAppAbout);
		menuApp.add(menuAppTerminar);
		menu.add(menuApp);
		
		//**ICONOS Y SHORTCUTS
		ImageIcon acercaDeIcon = new ImageIcon("src/iconos/acercaDe.png");
		menuAppAbout.setIcon(acercaDeIcon);
		KeyStroke ctrlBKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK);
		menuAppAbout.setAccelerator(ctrlBKeyStroke);
		
		ImageIcon terminarIcon = new ImageIcon("src/iconos/cerrar.png");
		menuAppTerminar.setIcon(terminarIcon);
		KeyStroke ctrlTKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK);
		menuAppTerminar.setAccelerator(ctrlTKeyStroke);
		
		//MENÚ AUTOR
		menuAutor = new JMenu("Autor");
		menuAutorMantenimiento = new JMenuItem("Mantener autores");
		menuAutorListar = new JMenuItem("Listar autores");
		menuAutor.add(menuAutorMantenimiento);
		menuAutor.add(menuAutorListar);
		menu.add(menuAutor);
		
		//**ICONOS Y SHORTCUTS
		ImageIcon autorIcon = new ImageIcon("src/iconos/autor.png");
		menuAutorMantenimiento.setIcon(autorIcon);
		KeyStroke ctrlAKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK);
		menuAutorMantenimiento.setAccelerator(ctrlAKeyStroke);
		
		ImageIcon listarIcon = new ImageIcon("src/iconos/listado.png");
		menuAutorListar.setIcon(listarIcon);
		KeyStroke ctrlIKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK);
		menuAutorListar.setAccelerator(ctrlIKeyStroke);
		
		//MENÚ EDITORIAL
		menuEditorial = new JMenu("Editorial");
		menuEditorialMantenimiento = new JMenuItem("Mantener editoriales");
		//menuEditorialListar = new JMenuItem("Listar editoriales");
		menuEditorial.add(menuEditorialMantenimiento);
		//menuEditorial.add(menuEditorialListar);
		menu.add(menuEditorial);
		
		//**ICONOS Y SHORTCUTS
		ImageIcon editorialIcon = new ImageIcon("src/iconos/editorial.png");
		menuEditorialMantenimiento.setIcon(editorialIcon);
		KeyStroke ctrlEKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK);
		menuEditorialMantenimiento.setAccelerator(ctrlEKeyStroke);
		
		//MENÚ CATEGORIA
		menuCategoria = new JMenu("Categoría");
		menuCategoriaMantenimiento = new JMenuItem("Mantener categorías");
		//menuCategoriaListar = new JMenuItem("Listar categorías");
		menuCategoria.add(menuCategoriaMantenimiento);
		//menuCategoria.add(menuCategoriaListar);
		menu.add(menuCategoria);
		
		//**ICONOS Y SHORTCUTS
		ImageIcon categoriaIcon = new ImageIcon("src/iconos/categoria.png");
		menuCategoriaMantenimiento.setIcon(categoriaIcon);
		KeyStroke ctrlCKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK);
		menuCategoriaMantenimiento.setAccelerator(ctrlCKeyStroke);
		
		//MENÚ ALMACÉN
		menuAlmacen = new JMenu("Almacén");
		menuAlmacenMantenimiento = new JMenuItem("Mantener libros");
		menuAlmacen.add(menuAlmacenMantenimiento);
		menu.add(menuAlmacen);
		
		//**ICONOS Y SHORTCUTS
		ImageIcon almacenIcon = new ImageIcon("src/iconos/almacen.png");
		menuAlmacenMantenimiento.setIcon(almacenIcon);
		KeyStroke ctrlLKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK);
		menuAlmacenMantenimiento.setAccelerator(ctrlLKeyStroke);
		
		//MENÚ CATÁLOGO
		menuCatalogo = new JMenu("Catálogo");
		menuCatalogoMantenimiento = new JMenuItem("Mantener catalogo");
		menuCatalogo.add(menuCatalogoMantenimiento);
		menu.add(menuCatalogo);
		
		//**ICONOS Y SHORTCUTS
		ImageIcon catalogoIcon = new ImageIcon("src/iconos/catalogo.png");
		menuCatalogoMantenimiento.setIcon(catalogoIcon);
		KeyStroke ctrlOKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
		menuCatalogoMantenimiento.setAccelerator(ctrlOKeyStroke);
		
		//MENÚ VENTA
		menuVenta = new JMenu("Ventas");
		menuVentaInforme = new JMenuItem("Informe ventas");
		menuVenta.add(menuVentaInforme);
		menu.add(menuVenta);
		
		//**ICONOS Y SHORTCUTS
		ImageIcon ventaIcon = new ImageIcon("src/iconos/venta.png");
		menuVentaInforme.setIcon(ventaIcon);
		KeyStroke ctrlVKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK);
		menuVentaInforme.setAccelerator(ctrlVKeyStroke);

		//AÑADIR EL MENÚ AL FRAME
		marco.setJMenuBar(menu);
		
		//LISTENERS AL MENÚ
		marco.addWindowListener(this);
		menuAppAbout.addActionListener(this);
		menuAppTerminar.addActionListener(this);
		menuAutorMantenimiento.addActionListener(this);
		menuAutorListar.addActionListener(this);
		menuEditorialMantenimiento.addActionListener(this);
		menuCategoriaMantenimiento.addActionListener(this);
		menuAlmacenMantenimiento.addActionListener(this);
		menuCatalogoMantenimiento.addActionListener(this);
		menuVentaInforme.addActionListener(this);
		
		JPanel panelSuperior = new JPanel();
		JPanel panelIzquierdo = new JPanel();
		JPanel panelDerecho = new JPanel();
		JPanel panelSur = new JPanel();
		
		marco.add(panelSuperior, BorderLayout.NORTH);
		marco.add(panelIzquierdo, BorderLayout.WEST);
		marco.add(panelDerecho, BorderLayout.EAST);
		marco.add(panelSur, BorderLayout.SOUTH);
		
		marco.setVisible(true);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("Terminar")) {
			
			controlador.terminar();
			System.out.println("Aplicación terminada.");
			System.exit(0);
		}
		
		if(actionCommand.equals("Mantener autores")) {
			
			if(itemMenuAux != null) {
				
				itemMenuAux.setEnabled(true);
			}
			
			itemMenuAux = menuAutorMantenimiento;
			menuAutorMantenimiento.setEnabled(false);
			
			//SI HABÍA ALGO CARGADO EN PANEL CENTRAL, QUITAR
			if(marco.getContentPane().getComponentCount() == 5) {
				
				marco.getContentPane().remove(4);
			}
			
			//SI ES LA PRIMERA VEZ QUE SE CARGA, SE CREA
			if(panelAutor == null) {
				
				panelAutor = new PanelAutor(marco, controlador);
				
			}else{
				
				marco.add(panelAutor.getPanelCentral(), BorderLayout.CENTER);
				marco.setSize(panelAutor.getTamanoMarcoRequerido());
				marco.validate();
				marco.repaint();
			}

		}
		
		if(actionCommand.equals("Mantener editoriales")) {
			
			if(itemMenuAux != null) {
				
				itemMenuAux.setEnabled(true);
			}
			
			itemMenuAux = menuEditorialMantenimiento;
			menuEditorialMantenimiento.setEnabled(false);
			
			//SI HABÍA ALGO CARGADO EN PANEL CENTRAL, QUITAR
			if(marco.getContentPane().getComponentCount() == 5) {
				
				marco.getContentPane().remove(4);
			}
			
			//SI ES LA PRIMERA VEZ QUE SE CARGA, SE CREA
			if(panelEditorial == null) {
				
				panelEditorial = new PanelEditorial(marco, controlador);
				
			}else{
				
				marco.add(panelEditorial.getPanelCentral(), BorderLayout.CENTER);
				marco.setSize(panelEditorial.getTamanoMarcoRequerido());
				marco.validate();
				marco.repaint();
			}

		}
		
		if(actionCommand.equals("Mantener categorías")) {
			
			if(itemMenuAux != null) {
				
				itemMenuAux.setEnabled(true);
			}
			
			itemMenuAux = menuCategoriaMantenimiento;
			menuCategoriaMantenimiento.setEnabled(false);
			
			//SI HABÍA ALGO CARGADO EN PANEL CENTRAL, QUITAR
			if(marco.getContentPane().getComponentCount() == 5) {
				
				marco.getContentPane().remove(4);
			}
			
			//SI ES LA PRIMERA VEZ QUE SE CARGA, SE CREA
			if(panelCategoria == null) {
				
				panelCategoria = new PanelCategoria(marco, controlador);
				
			}else{
				
				marco.add(panelCategoria.getPanelCentral(), BorderLayout.CENTER);
				marco.setSize(panelCategoria.getTamanoMarcoRequerido());
				marco.validate();
				marco.repaint();
			}

		}
		
		if(actionCommand.equals("Mantener libros")) {
			
			if(itemMenuAux != null) {
				
				itemMenuAux.setEnabled(true);
			}
			
			itemMenuAux = menuAlmacenMantenimiento;
			menuAlmacenMantenimiento.setEnabled(false);
			
			//SI HABÍA ALGO CARGADO EN PANEL CENTRAL, QUITAR
			if(marco.getContentPane().getComponentCount() == 5) {
				
				marco.getContentPane().remove(4);
			}
			
			//SI ES LA PRIMERA VEZ QUE SE CARGA, SE CREA
			if(panelLibro == null) {
				
				panelLibro = new PanelLibro(marco, controlador);
				
			}else{
				
				marco.add(panelLibro.getPanelCentral(), BorderLayout.CENTER);
				marco.setSize(panelLibro.getTamanoMarcoRequerido());
				marco.validate();
				marco.repaint();
			}

		}
	}
}
