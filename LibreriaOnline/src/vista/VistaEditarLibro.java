package vista;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.Autor;
import modelo.Libro;

@SuppressWarnings("serial")
public class VistaEditarLibro extends VistaEditar {
	
	private Libro libro;
	private ArrayList<Autor> autores;
	private ArrayList<JComboBox<String>> combos;
	private EsquemaColor estilo;
	
	public VistaEditarLibro() {
		
		super(new EsquemaColor(EsquemaColor.ESTILO_DEFAULT));
		this.setAutores(new ArrayList<Autor>());
		this.estilo = new EsquemaColor(EsquemaColor.ESTILO_DEFAULT);
		setCombos(new ArrayList<JComboBox<String>>());
		init();
	}
	
	public VistaEditarLibro(EsquemaColor estilo) {
		
		super(estilo);
		this.setAutores(new ArrayList<Autor>());
		this.estilo = estilo;
		setCombos(new ArrayList<JComboBox<String>>());
		init();
	}
	
	public VistaEditarLibro(Libro libro, EsquemaColor estilo) {
		
		super(estilo);
		this.setLibro(libro);
		this.setAutores(libro.getAutores());
		setCombos(new ArrayList<JComboBox<String>>());
		this.estilo = estilo;
		init();
		cargarDatos();
	}
	
	public void init() {
		
		setTipo("libro");
		
		label1 = new JLabel("ISBN: ");
		label1.setBounds(20, 20, 200, 25);
		label1.setForeground(estilo.medioClaro);
		text1 = new JTextField();
		text1.setBounds(100, 20, 220, 25);
		
		label2 = new JLabel("Título: ");
		label2.setBounds(20, 50, 200, 25);
		label2.setForeground(estilo.medioClaro);
		text2 = new JTextField();
		text2.setBounds(100, 50, 220, 25);
		
		label3 = new JLabel("Precio: ");
		label3.setBounds(20, 80, 200, 25);
		label3.setForeground(estilo.medioClaro);
		text3 = new JTextField();
		text3.setBounds(100, 80, 220, 25);
		
		label4 = new JLabel("Stock: ");
		label4.setBounds(20, 110, 200, 25);
		label4.setForeground(estilo.medioClaro);
		text4 = new JTextField();
		text4.setBounds(100, 110, 220, 25);
		
		label5 = new JLabel("Cod. Categ: ");
		label5.setBounds(20, 140, 200, 25);
		label5.setForeground(estilo.medioClaro);
		text5 = new JTextField();
		text5.setBounds(100, 140, 220, 25);
		
		label6 = new JLabel("Cod. Edit: ");
		label6.setBounds(20, 170, 200, 25);
		label6.setForeground(estilo.medioClaro);
		text6 = new JTextField();
		text6.setBounds(100, 170, 220, 25);
	
		add(label1);
		add(text1);
		add(label2);
		add(text2);
		add(label3);
		add(text3);
		add(label4);
		add(text4);
		add(label5);
		add(text5);
		add(label6);
		add(text6);
	}
	
	public void cargarDatos() {
		
		text1.setText(String.valueOf(getLibro().getIsbn()));
		text2.setText(getLibro().getTitulo());
		text3.setText(String.valueOf(getLibro().getPrecio()));
		text4.setText(String.valueOf(getLibro().getStock()));
		text5.setText(String.valueOf(getLibro().getCodCategoria()));
		text6.setText(String.valueOf(getLibro().getCodEditorial()));
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public ArrayList<JComboBox<String>> getCombos() {
		return combos;
	}

	public void setCombos(ArrayList<JComboBox<String>> combos) {
		this.combos = combos;
	}

	public ArrayList<Autor> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<Autor> autores) {
		this.autores = autores;
	}
	
}
