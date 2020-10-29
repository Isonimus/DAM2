package demoTabla;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Demo {
	
	JTable tabla;
	JFrame frame;
	JScrollPane scrollPane;
	Dimension dimension;
	String[] columnNames = {"Nombre",
            				"Apellido",
            				"Apellido 2",
            				"DNI",
            				"Dirección",
            				"Verificado"};
	
	Object[][] data = { {"Iker", "Laforga", "Rodrigo", 12345678, "Calle Falsa, 123", new Boolean(true)},
		    			{"Mikel", "Sánchez", "López", 23232323, "Velázquez, 10", new Boolean(true)},
		    			{"Alex", "Moreno", "García", 12121212, "Rekalde 23", new Boolean(true)},
		    			{"Elena", "Agudo", "Santos", 20202020, "Iturribide 32", new Boolean(true)},
		    			{"David", "Aranda", "Jiménez", 10121416, "Goya, 25", new Boolean(true)}};
	
	public Demo(){
		
		super();
		init(500, 500);
	}
	
	public Demo(int anchura, int altura){
		
		super();
		init(anchura, altura);
	}
	
	public void init(int anchura, int altura) {
		
		dimension = new Dimension(anchura, altura);
		
		frame = new JFrame("Demo tablas");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabla = new JTable(data, columnNames);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		
		//tabla.setDefaultRenderer(String.class, centerRenderer);
		for (int columnIndex = 0; columnIndex < tabla.getColumnCount(); columnIndex++){
			
            tabla.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }
		//tabla.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		
		scrollPane = new JScrollPane(tabla);
		scrollPane.setPreferredSize(dimension);
		scrollPane.setMinimumSize(dimension);
		tabla.setFillsViewportHeight(true);
		
		frame.setContentPane(scrollPane);
		frame.setVisible(true);
		frame.pack();
	}
	
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		Demo demo = new Demo();
	}
}
