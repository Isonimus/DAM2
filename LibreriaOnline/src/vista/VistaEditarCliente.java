package vista;

import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import modelo.Cliente;

@SuppressWarnings("serial")
public class VistaEditarCliente extends VistaEditar {
	
	private Cliente cliente;
	private EsquemaColor estilo;
	
	public VistaEditarCliente() {
		
		super(new EsquemaColor(EsquemaColor.ESTILO_DEFAULT));
		this.estilo = new EsquemaColor(EsquemaColor.ESTILO_DEFAULT);
		init();
	}
	
	public VistaEditarCliente(EsquemaColor estilo) {
		
		super(estilo);
		this.estilo = estilo;
		init();
	}
	
	public VistaEditarCliente(Cliente cliente, EsquemaColor estilo) {
		
		super(estilo);
		this.cliente = cliente;
		this.estilo = estilo;
		init();
		
		cargarDatos();
	}
	
	public void init() {
		
		setTipo("cliente");
		
		label1 = new JLabel("DNI: ");
		label1.setBounds(20, 20, 200, 25);
		label1.setForeground(estilo.medioClaro);
		text1 = new JTextField();
		text1.setBounds(100, 20, 220, 25);
		
		label2 = new JLabel("Nombre: ");
		label2.setBounds(20, 50, 200, 25);
		label2.setForeground(estilo.medioClaro);
		text2 = new JTextField();
		text2.setBounds(100, 50, 220, 25);
		
		label3 = new JLabel("Apellido 1: ");
		label3.setBounds(20, 80, 200, 25);
		label3.setForeground(estilo.medioClaro);
		text3 = new JTextField();
		text3.setBounds(100, 80, 220, 25);
		
		label4 = new JLabel("Apellido 2: ");
		label4.setBounds(20, 110, 200, 25);
		label4.setForeground(estilo.medioClaro);
		text4 = new JTextField();
		text4.setBounds(100, 110, 220, 25);
		
		label5 = new JLabel("Dirección: ");
		label5.setBounds(20, 140, 200, 25);
		label5.setForeground(estilo.medioClaro);
		text5 = new JTextField();
		text5.setBounds(100, 140, 220, 25);
		
		label6 = new JLabel("Email: ");
		label6.setBounds(20, 170, 200, 25);
		label6.setForeground(estilo.medioClaro);
		text6 = new JTextField();
		text6.setBounds(100, 170, 220, 25);
		
		label7 = new JLabel("F. Nacim: ");
		label7.setBounds(20, 200, 200, 25);
		label7.setForeground(estilo.medioClaro);
		fecha = new JDateChooser();
		fecha.setBounds(100, 200, 220, 25);
		
		label8 = new JLabel("Usuario: ");
		label8.setBounds(20, 230, 200, 25);
		label8.setForeground(estilo.medioClaro);
		text8 = new JTextField();
		text8.setBounds(100, 230, 220, 25);
		
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
		add(label7);
		add(fecha);
		add(label8);
		add(text8);
	}
	
	public void cargarDatos() {
		
		text1.setText(cliente.getDni());
		text2.setText(cliente.getNombre());
		text3.setText(cliente.getApellido1());
		text4.setText(cliente.getApellido2());
		text5.setText(cliente.getDireccion());
		text6.setText(cliente.getEmail());
		fecha.setDate(cliente.getFechaNacimiento());
		text8.setText(cliente.getUsuario());	
	}
}
