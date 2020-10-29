package vista;

import javax.swing.JButton;
import javax.swing.JLabel;

import modelo.Cliente;

@SuppressWarnings("serial")
public class VistaCliente extends VistaItem{
	
	private Cliente cliente;
	private JLabel info;
	private EsquemaColor estilo;
	
	/**
	 * Constructor por defecto: aplica el estilo DEFAULT. (GRISES)
	 * @param xPos int: la posición X de la vista.
	 * @param yPos int: la posición Y de la vista.
	 * @param cliente Cliente: el objeto de datos.
	 */
	public VistaCliente(int xPos, int yPos, Cliente cliente) {

		super(new EsquemaColor(EsquemaColor.ESTILO_DEFAULT));
		setxPos(xPos);
		setyPos(yPos);
		setCliente(cliente);
		this.estilo = new EsquemaColor(EsquemaColor.ESTILO_DEFAULT);
		init();
	}
	
	/**
	 * Constructor avanzado: aplica el estilo deseado.
	 * @param xPos int: la posición X de la vista.
	 * @param yPos int: la posición Y de la vista.
	 * @param cliente Cliente: el objeto de datos.
	 * @param estilo EsquemaColor: el esquema deseado.
	 */
	public VistaCliente(int xPos, int yPos, Cliente cliente, EsquemaColor estilo) {

		super(estilo);
		setxPos(xPos);
		setyPos(yPos);
		setCliente(cliente);
		this.estilo = estilo;
		init();
	}

	private void init() {
		
		setBackground(estilo.medioClaro);
		setBounds(getxPos(), getyPos(), getAnchura(), getAltura());
		setLayout(null);
		setOpaque(false);
		
		setIcono(iconoItem, picLabel, URL_ICONO_AVATAR);
		
		info = new JLabel(getCliente().getDni() + " - " + getCliente().getNombre());
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
