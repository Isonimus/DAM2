package chatClient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class ChatGUI extends JFrame implements ActionListener, WindowListener{
	
	private static final long serialVersionUID = 1L;
	private ChatClient cliente;
	private JTextArea pantalla;
	private JTextArea barraTexto;
	private JButton enviar;
	private Dimension dimension;
	private Border borde = BorderFactory.createLineBorder(Color.GRAY);
	
	public ChatGUI() {
		
		super();
		//this.cliente = cliente;
		this.dimension = new Dimension(500, 500);
		init();
	}
	
	private void init() {
		
		setTitle("Whatsagram! V1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setMinimumSize(dimension);
		setPreferredSize(dimension);
		setResizable(false);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		addWindowListener(this);
		
		pantalla = new JTextArea();
		pantalla.setBounds(10, 10, 470, 395);
		pantalla.setBorder(borde);
		pantalla.setEditable(false);
		getContentPane().add(pantalla);
		
		barraTexto = new JTextArea();
		barraTexto.setBounds(10, 415, 370, 50);
		barraTexto.setBorder(borde);
		barraTexto.setVisible(true);
		getContentPane().add(barraTexto);
		
		enviar = new JButton("Enviar");
		enviar.setBounds(390, 415, 90, 50);
		enviar.setBackground(Color.GRAY);
		enviar.setForeground(Color.LIGHT_GRAY);
		enviar.addActionListener(this);
		getContentPane().add(enviar);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		
		barraTexto.setText(null);
		barraTexto.requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {

		ChatGUI chat = new ChatGUI();
	}
}
