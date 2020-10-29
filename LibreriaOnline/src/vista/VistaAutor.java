package vista;

import javax.swing.JButton;
import javax.swing.JLabel;

import modelo.Autor;

@SuppressWarnings("serial")
public class VistaAutor extends VistaItem{
	
	private Autor autor;
	private JLabel info;
	private EsquemaColor estilo;
	
	/**
	 * Constructor por defecto: aplica el estilo DEFAULT. (GRISES)
	 * @param xPos int: la posición X de la vista.
	 * @param yPos int: la posición Y de la vista.
	 * @param autor Autor: el objeto de datos.
	 */
	public VistaAutor(int xPos, int yPos, Autor autor) {

		super(new EsquemaColor(EsquemaColor.ESTILO_DEFAULT));
		setxPos(xPos);
		setyPos(yPos);
		this.setAutor(autor);
		this.estilo = new EsquemaColor(EsquemaColor.ESTILO_DEFAULT);
		init();
	}
	
	/**
	 * Constructor avanzado: aplica el estilo deseado. 
	 * @param xPos int: la posición X de la vista.
	 * @param yPos int: la posición Y de la vista.
	 * @param autor Autor: el objeto de datos Autor.
	 * @param estilo EsquemaColor: el esquema deseado.
	 */
	public VistaAutor(int xPos, int yPos, Autor autor, EsquemaColor estilo) {

		super(estilo);
		setxPos(xPos);
		setyPos(yPos);
		this.setAutor(autor);
		this.estilo = estilo;
		init();
	}

	private void init() {
		
		setBackground(estilo.medioClaro);
		setBounds(getxPos(), getyPos(), getAnchura(), getAltura());
		setLayout(null);
		setOpaque(false);
		//setAltura(80);
		//setAnchura(473);
		
		setIcono(iconoItem, picLabel, URL_ICONO_AVATAR);
		
		info = new JLabel(getAutor().getCodAutor() + " - " + getAutor().getNombre());
		info.setBounds(40, margenSuperior, 150, alturaComponentes);
		info.setForeground(estilo.sombra);
		add(info);
		
		modificar = new JButton("Editar");
		modificar.setBounds(300, margenSuperior, 70, alturaComponentes);
		modificar.setBackground(estilo.medioOscuro);
		modificar.setForeground(estilo.medioClaro);
		add(modificar);
		
		eliminar = new JButton("Borrar");
		eliminar.setBounds(375, margenSuperior, 75, alturaComponentes);
		eliminar.setBackground(estilo.medioOscuro);
		eliminar.setForeground(estilo.medioClaro);
		
		add(eliminar);
		setVisible(true);
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
