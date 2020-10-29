package vista;

import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.Editorial;

@SuppressWarnings("serial")
public class VistaEditarEditorial extends VistaEditar {
	
	private Editorial editorial;
	private EsquemaColor estilo;
	
	public VistaEditarEditorial() {
		
		super(new EsquemaColor(EsquemaColor.ESTILO_DEFAULT));
		this.estilo = new EsquemaColor(EsquemaColor.ESTILO_DEFAULT);
		init();
	}
	
	public VistaEditarEditorial(EsquemaColor estilo) {
		
		super(estilo);
		this.estilo = estilo;
		init();
	}
	
	public VistaEditarEditorial(Editorial editorial, EsquemaColor estilo) {
		
		super(estilo);
		this.editorial = editorial;
		this.estilo = estilo;
		init();
		cargarDatos();
	}
	
	public void init() {
		
		setTipo("editorial");
		
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
		
		text1.setText(String.valueOf(editorial.getCodEditorial()));
		text2.setText(editorial.getNombre());
	}
}
