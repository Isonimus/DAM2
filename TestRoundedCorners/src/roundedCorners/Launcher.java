package roundedCorners;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Launcher extends JFrame{
	
	JFrame frame = new JFrame("Test Rounded Borders");
	
	public Launcher(){
		
		super();
		init();
		cargarPanels();
	}
	
	public void init() {
		
		setTitle("Test Rounded Borders");
		setSize(500, 500);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.GRAY);
		setVisible(true);
		
	}
	
	public void cargarPanels() {
		int xInicial = 5;
		int yInicial = 5;
		
		for(int i = 0; i < 8; i++) {
			
			RoundedJPanel panel = new RoundedJPanel(475, 50, 2);
			panel.setBounds(xInicial, yInicial, panel.width, panel.height);
			JLabel label = new JLabel("Item " + i);
			label.setBounds(10, 10, 50, 30);
			panel.add(label);
			add(panel);
			yInicial += 55;
		}
		
		revalidate();
		repaint();
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		Launcher launcher = new Launcher();
	}
}
