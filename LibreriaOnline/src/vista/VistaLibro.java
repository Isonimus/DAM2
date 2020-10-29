package vista;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import modelo.Autor;
import modelo.Libro;

@SuppressWarnings("serial")
public class VistaLibro extends VistaItem{
	
	private Libro libro;
	private JLabel info;
	private JLabel editorial, autores, precio, stock, isbn;
	private int alturaBotones;
	private EsquemaColor estilo;
	
	/**
	 * Constructor por defecto: aplica el estilo DEFAULT. (GRISES)
	 * @param xPos int: la posición X de la vista.
	 * @param yPos int: la posición Y de la vista.
	 * @param libro Libro: el objeto de datos.
	 */
	public VistaLibro(int xPos, int yPos, Libro libro) {

		super(new EsquemaColor(EsquemaColor.ESTILO_DEFAULT));
		setxPos(xPos);
		setyPos(yPos);
		setLibro(libro);
		this.estilo = new EsquemaColor(EsquemaColor.ESTILO_DEFAULT);
		init();
	}
	
	/**
	 * Constructor avanzado: aplica el estilo deseado.
	 * @param xPos int: la posición X de la vista.
	 * @param yPos int: la posición Y de la vista.
	 * @param libro Libro: el objeto de datos.
	 * @param estilo EsquemaColor: el esquema deseado.
	 */
	public VistaLibro(int xPos, int yPos, Libro libro, EsquemaColor estilo) {

		super(estilo);
		setxPos(xPos);
		setyPos(yPos);
		setLibro(libro);
		this.estilo = estilo;
		init();
	}

	private void init() {
		
		setBackground(estilo.medioClaro);
		setBounds(getxPos(), getyPos(), getAnchura(), getAltura());
		setLayout(null);
		setOpaque(false);
		setAltura(110);
		
		setIcono(iconoItem, picLabel, URL_ICONO_LIBRO);
		
		alturaBotones = (getAltura()/2) - (alturaComponentes/2);
		
		info = new JLabel(getLibro().getTitulo());
		info.setBounds(40, margenSuperior, 250, alturaComponentes);
		info.setForeground(estilo.sombra);
		add(info);
		
		autores = new JLabel();
		String nombres = "";
		
		for(int i = 0; i < getLibro().getAutores().size(); i++) {
			
			Autor autor = getLibro().getAutores().get(i);
			nombres += autor.getNombre();
			
			if(i < getLibro().getAutores().size()-1) {
				
				nombres += ", ";
				
			}else {
				
				nombres += ".";
			}
		}
		
		autores.setText(nombres);
		autores.setBounds(40, margenSuperior + 20, 450, alturaComponentes);
		autores.setFont(autores.getFont().deriveFont(Font.ITALIC, 11.0F));
		autores.setForeground(estilo.sombra);
		add(autores);
		
		editorial = new JLabel("Editorial: ");
		editorial.setBounds(40, margenSuperior + 40, 450, alturaComponentes);
		editorial.setFont(getEditorial().getFont().deriveFont(Font.PLAIN, 11.0F));
		editorial.setForeground(estilo.sombra);
		add(editorial);
		
		precio = new JLabel("Precio: " + String.valueOf(getLibro().getPrecio()) + "€");
		precio.setBounds(40, margenSuperior + 60, 450, alturaComponentes);
		precio.setFont(precio.getFont().deriveFont(Font.BOLD, 11.0F));
		precio.setForeground(estilo.sombra);
		add(precio);
		
		stock = new JLabel("En stock: " + String.valueOf(getLibro().getStock()) + ".");
		stock.setBounds(120, margenSuperior + 60, 450, alturaComponentes);
		stock.setFont(stock.getFont().deriveFont(Font.PLAIN, 11.0F));
		stock.setForeground(estilo.sombra);
		add(stock);
		
		isbn = new JLabel("ISBN: " + String.valueOf(getLibro().getIsbn()) + ".");
		isbn.setBounds(185, margenSuperior + 60, 450, alturaComponentes);
		isbn.setFont(isbn.getFont().deriveFont(Font.BOLD, 11.0F));
		isbn.setForeground(estilo.sombra);
		add(isbn);
		
		modificar = new JButton("Editar");
		modificar.setBounds(300, alturaBotones, 70, alturaComponentes);
		modificar.setBackground(estilo.medioOscuro);
		modificar.setForeground(estilo.medioClaro);
		
		add(modificar);
		
		eliminar = new JButton("Borrar");
		eliminar.setBounds(375, alturaBotones, 75, alturaComponentes);
		eliminar.setBackground(estilo.medioOscuro);
		eliminar.setForeground(estilo.medioClaro);
		
		add(eliminar);
		setVisible(true);
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public JLabel getEditorial() {
		return editorial;
	}

	public void setEditorial(JLabel editorial) {
		this.editorial = editorial;
	}
}
