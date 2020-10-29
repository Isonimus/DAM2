package vista;

import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.Categoria;

@SuppressWarnings("serial")
public class VistaEditarCategoria extends VistaEditar {
	
	private Categoria categoria;
	private EsquemaColor estilo;
	
	public VistaEditarCategoria() {
		
		super(new EsquemaColor(EsquemaColor.ESTILO_DEFAULT));
		this.estilo = new EsquemaColor(EsquemaColor.ESTILO_DEFAULT);
		init();
	}
	
	public VistaEditarCategoria(EsquemaColor estilo) {
		
		super(estilo);
		this.estilo = estilo;
		init();
	}
	
	public VistaEditarCategoria(Categoria categoria, EsquemaColor estilo) {
		
		super(estilo);
		this.categoria = categoria;
		this.estilo = estilo;
		init();
		cargarDatos();
	}
	
	public void init() {
		
		setTipo("categoria");
		
		label1 = new JLabel("Código: ");
		label1.setBounds(20, 20, 200, 25);
		label1.setForeground(estilo.medioClaro);
		text1 = new JTextField();
		text1.setBounds(100, 20, 220, 25);
		
		label2 = new JLabel("Nombre: ");
		label2.setBounds(20, 50, 200, 25);
		label2.setForeground(estilo.medioClaro);
		text2 = new JTextField();
		text2.setBounds(100, 50, 220, 25);
		
		add(label1);
		add(text1);
		add(label2);
		add(text2);
	}
	
	public void cargarDatos() {
		
		text1.setText(String.valueOf(categoria.getCodCategoria()));
		text2.setText(categoria.getNombre());
	}
}
