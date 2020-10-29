package vista;

import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.Autor;

@SuppressWarnings("serial")
public class VistaEditarAutor extends VistaEditar {
	
	private Autor autor;
	private EsquemaColor estilo;
	
	public VistaEditarAutor() {
		
		super(new EsquemaColor(EsquemaColor.ESTILO_DEFAULT));
		this.estilo = new EsquemaColor(EsquemaColor.ESTILO_DEFAULT);
		init();
	}
	
	public VistaEditarAutor(EsquemaColor estilo) {
		
		super(estilo);
		this.estilo = estilo;
		init();
	}
	
	public VistaEditarAutor(Autor autor, EsquemaColor estilo) {
		
		super(estilo);
		this.autor = autor;
		this.estilo = estilo;
		init();
		cargarDatos();
	}
	
	public void init() {
		
		setTipo("autor");

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
		repaint();
	}
	
	public void cargarDatos() { //TODO: MOVER AL CONTROLADOR
		
		text1.setText(String.valueOf(autor.getCodAutor()));
		text2.setText(autor.getNombre());
	}
}
