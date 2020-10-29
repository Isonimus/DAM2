package vista;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 * Centraliza las clases que componen las vistas
 * de la aplicación.
 * @author Iker Laforga.
 *
 */
public class InterfazLibreria {
	
	public JFrame principal, supletorio;
	public VistaEditar edit;
	public VistaLista panelLibros, panelAutores, panelEditoriales, panelClientes, panelCategorias;
	public EsquemaColor estilo;
	public Menu panelMenu;
	public int alturaVistaAutor = 50;
	public int alturaVistaCliente = 50;
	public int alturaVistaEditorial = 50;
	public int alturaVistaLibro = 50;
	public int interespaciado = 5;
	public int anchura = 500;
	public int altura = 500;
	
	/**
	 * Constructor por defecto: asigna el esquema
	 * 'default' de colores. (Grises)
	 * 
	 */
	public InterfazLibreria(){
		
		super();
		this.estilo = new EsquemaColor(EsquemaColor.ESTILO_DEFAULT);
		init();
	}
	
	/**
	 * Constructor avanzado: toma por argumento un entero que 
	 * representa el esquema de color deseado.
	 * @param paletaColores int: el esquema de color deseado.
	 * 
	 */
	public InterfazLibreria(int paletaColores){
		
		super();
		this.estilo = new EsquemaColor(paletaColores);
		init();
	}
	
	public void init(){
		
		sobreescribirUIManager();
		
		principal = new JFrame("Librería Online");
		principal.setSize(anchura, altura);
		principal.setLayout(null);
		principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principal.setResizable(false);
		principal.setLocationRelativeTo(null);
		
		supletorio = new JFrame();
		supletorio.setSize(anchura, altura);
		supletorio.setLocationRelativeTo(principal);
		supletorio.setVisible(false);
		
		panelLibros = new VistaLista(anchura, 424, "LIBROS", estilo); //424
		principal.add(panelLibros);
		
		panelAutores = new VistaLista(anchura, 424, "AUTORES", estilo);
		principal.add(panelAutores);
		panelAutores.setVisible(false);
		
		panelEditoriales = new VistaLista(anchura, 424, "EDITORIALES", estilo);
		principal.add(panelEditoriales);
		panelEditoriales.setVisible(false);
		
		panelClientes = new VistaLista(anchura, 424, "CLIENTES", estilo);
		principal.add(panelClientes);
		panelClientes.setVisible(false);
		
		panelCategorias = new VistaLista(anchura, 424, "CATEGORÍAS", estilo);
		principal.add(panelCategorias);
		panelCategorias.setVisible(false);
		
		panelMenu = new Menu(anchura, 52, estilo);
		principal.add(panelMenu);
		principal.setVisible(true);
	}
	
	/**
	 * Sobreescribe algunas variables de color en el UIManager
	 * para aplicar el estilo deseado.
	 * 
	 */
	public void sobreescribirUIManager() {
		
		UIManager.put("OptionPane.background",new ColorUIResource(estilo.oscuro));
		UIManager.put("Panel.background",new ColorUIResource(estilo.oscuro));
		UIManager.put("OptionPane.messageForeground",new ColorUIResource(estilo.medioClaro));
		UIManager.put("Button.background", estilo.medioClaro);
		UIManager.put("Button.foreground", estilo.sombra);
		UIManager.put("TextField.background", estilo.medioClaro);
		UIManager.put("TextField.foreground", estilo.sombra);
		UIManager.put("ComboBox.background", estilo.medioClaro);
		UIManager.put("ComboBox.foreground", estilo.sombra);
	}
}
