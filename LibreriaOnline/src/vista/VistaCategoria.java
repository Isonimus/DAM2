package vista;

import javax.swing.JButton;
import javax.swing.JLabel;

import modelo.Categoria;

@SuppressWarnings("serial")
public class VistaCategoria extends VistaItem{
	
	private Categoria categoria;
	private JLabel info;
	private EsquemaColor estilo;
	
	/**
	 * Constructor por defecto: aplica el estilo DEFAULT. (GRISES)
	 * @param xPos int: la posición X de la vista.
	 * @param yPos int: la posición Y de la vista.
	 * @param categoria Categoria: el objeto de datos.
	 */
	public VistaCategoria(int xPos, int yPos, Categoria categoria) {

		super(new EsquemaColor(EsquemaColor.ESTILO_DEFAULT));
		setxPos(xPos);
		setyPos(yPos);
		setCategoria(categoria);
		this.estilo = new EsquemaColor(EsquemaColor.ESTILO_DEFAULT);
		init();
	}
	
	/**
	 * Constructor avanzado: aplica el estilo deseado.
	 * @param xPos int: la posición X de la vista.
	 * @param yPos int: la posición Y de la vista.
	 * @param categoria Categoria: el objeto de datos.
	 * @param estilo EsquemaColor: el esquema deseado.
	 */
	public VistaCategoria(int xPos, int yPos, Categoria categoria, EsquemaColor estilo) {

		super(estilo);
		setxPos(xPos);
		setyPos(yPos);
		setCategoria(categoria);
		this.estilo = estilo;
		init();
	}

	private void init() {
		
		setBackground(estilo.medioClaro);
		setBounds(getxPos(), getyPos(), getAnchura(), getAltura());
		setLayout(null);
		setOpaque(false);
		
		setIcono(iconoItem, picLabel, URL_ICONO_EDITORIAL);
		
		info = new JLabel(getCategoria().getCodCategoria() + " - " + getCategoria().getNombre());
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
