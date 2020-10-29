package vista;

import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * CLASE MENÚ CON CINCO BOTONES
 * @author Iker Laforga
 *
 */
@SuppressWarnings("serial")
public class Menu extends JPanel{
	
	public JButton bt1Menu, bt2Menu, bt3Menu, bt4Menu, bt5Menu;
	private int anchura, altura;
	private int anchuraBoton = 100;
	private int alturaBoton = 30;
	private int margenHorizontal = 15;
	private int margenVertical = 5;
	private Insets margenBoton = new Insets(2, 4, 2, 4);
	private EsquemaColor estilo;
	
	/**
	 * CONSTRUCTOR STANDARD:
	 * requiere anchura y altura del menú. Los valores
	 * de padding entre botones, de margen vertical
	 * y dimensiones de los botones pueden modificarse mediante setters.
	 * @param anchura int
	 * @param altura int
	 */
	public Menu(int anchura, int altura){
		
		super();
		this.anchura = anchura;
		this.altura = altura;
		this.estilo = new EsquemaColor(EsquemaColor.ESTILO_DEFAULT);
		init();
	}
	
	/**
	 * CONSTRUCTOR CON ESTILO:
	 * requiere anchura y altura del menú, y un Esquema de Color. Los valores
	 * de padding entre botones, de margen vertical
	 * y dimensiones de los botones pueden modificarse mediante setters.
	 * @param anchura int
	 * @param altura int
	 * @param estilo EsquemaColor
	 */
	public Menu(int anchura, int altura, EsquemaColor estilo){
		
		super();
		this.anchura = anchura;
		this.altura = altura;
		this.estilo = estilo;
		init();
	}
	
	public void init() {
		
		setLayout(new FlowLayout (FlowLayout.CENTER, getMargenHorizontal(), getMargenVertical()));
		setBackground(estilo.oscuro); 
		setSize(anchura, altura);
		setBounds(0, 424, anchura, altura);
		
		bt1Menu = new JButton("Libros");
		bt1Menu.setSize(getAnchuraBoton(), getAlturaBoton());
		bt1Menu.setBackground(estilo.medioClaro);
		bt1Menu.setForeground(estilo.sombra);
		bt1Menu.setMargin(margenBoton);
		add(bt1Menu);
		
		bt2Menu = new JButton("Autores");
		bt2Menu.setSize(getAnchuraBoton(), getAlturaBoton());
		bt2Menu.setBackground(estilo.medioClaro);
		bt2Menu.setForeground(estilo.sombra);
		bt2Menu.setMargin(margenBoton);
		add(bt2Menu);
		
		bt3Menu = new JButton("Editoriales");
		bt3Menu.setSize(getAnchuraBoton(), getAlturaBoton());
		bt3Menu.setBackground(estilo.medioClaro);
		bt3Menu.setForeground(estilo.sombra);
		bt3Menu.setMargin(margenBoton);
		add(bt3Menu);
		
		bt4Menu = new JButton("Clientes");
		bt4Menu.setSize(getAnchuraBoton(), getAlturaBoton());
		bt4Menu.setBackground(estilo.medioClaro);
		bt4Menu.setForeground(estilo.sombra);
		bt4Menu.setMargin(margenBoton);
		add(bt4Menu);
		
		bt5Menu = new JButton("Categorías");
		bt5Menu.setSize(getAnchuraBoton(), getAlturaBoton());
		bt5Menu.setBackground(estilo.medioClaro);
		bt5Menu.setForeground(estilo.sombra);
		bt5Menu.setMargin(margenBoton);
		add(bt5Menu);
		
		setVisible(true);
	}

	public int getMargenHorizontal() {
		return margenHorizontal;
	}

	public void setMargenHorizontal(int margenHorizontal) {
		this.margenHorizontal = margenHorizontal;
	}

	public int getMargenVertical() {
		return margenVertical;
	}

	public void setMargenVertical(int margenVertical) {
		this.margenVertical = margenVertical;
	}

	public int getAnchuraBoton() {
		return anchuraBoton;
	}

	public void setAnchuraBoton(int anchuraBoton) {
		this.anchuraBoton = anchuraBoton;
	}

	public int getAlturaBoton() {
		return alturaBoton;
	}

	public void setAlturaBoton(int alturaBoton) {
		this.alturaBoton = alturaBoton;
	}
}
