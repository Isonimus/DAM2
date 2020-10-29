package vista;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class VistaEditar extends JPanel {
	
	public JLabel label1, label2, label3, label4, label5, label6, label7, label8;
	public JTextField text1, text2, text3, text4, text5, text6, text7, text8;
	public ArrayList<JComboBox<String>> combos;
	public JDateChooser fecha;
	private JButton cancelar;
	private JButton commit;
	private JButton insertar;
	private String tipo;
	private int id;
	private EsquemaColor estilo;
	
	public VistaEditar() {
		
		super();
		this.estilo = new EsquemaColor(EsquemaColor.ESTILO_DEFAULT);
		init();
	}
	
	public VistaEditar(EsquemaColor estilo) {
		
		super();
		this.estilo = estilo;
		init();
	}
	
	private void init() {
		
		setBackground(estilo.oscuro);
		setLayout(null);
		reset();
		setVisible(true);
	}
	
	public void reset() { //TODO: MOVER AL CONTROLADOR
		
		combos = new ArrayList<JComboBox<String>>();
		
		removeAll();
		setCancelar(new JButton("Cancelar"));
		getCancelar().setBounds(100, 400, 100, 30);
		
		setCommit(new JButton("Modificar"));
		getCommit().setBounds(220, 400, 100, 30);
		
		setInsertar(new JButton("Añadir"));
		getInsertar().setBounds(220, 400, 100, 30);
		
		add(getCancelar());
		add(getCommit());
		add(getInsertar());
	}

	public JButton getCancelar() {
		return cancelar;
	}

	public void setCancelar(JButton cancelar) {
		this.cancelar = cancelar;
	}

	public JButton getCommit() {
		return commit;
	}

	public void setCommit(JButton commit) {
		this.commit = commit;
	}

	public JButton getInsertar() {
		return insertar;
	}

	public void setInsertar(JButton insertar) {
		this.insertar = insertar;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
