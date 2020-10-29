package roundedCorners;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RoundedJPanel extends JPanel{
	
	int width, height;
	int offsetSombra;
	protected Dimension arcs = new Dimension(12, 12);
	
	public RoundedJPanel(int width, int height, int offsetSombra) {
		
		super();
		this.width = width;
		this.height = height;
		this.offsetSombra = offsetSombra;
		init();
	}
	
	public void init() {
		
		setSize(width, height);
		setBackground(Color.white);
		setLayout(null);
		setVisible(true);
		setOpaque(false);
	}
	
	public void paintComponent(Graphics g) {
		
		 super.paintComponent(g);
		 
		 Graphics2D graphics = (Graphics2D) g;
		 graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		 
		 graphics.setColor(Color.DARK_GRAY);
         graphics.fillRoundRect(
        		 offsetSombra,// X position
        		 offsetSombra,// Y position
                 width - offsetSombra, // width
                 height - offsetSombra, // height
                 arcs.width, arcs.height);
		
		 graphics.setColor(getBackground());
	     graphics.fillRoundRect(0, 0, width-offsetSombra, height-offsetSombra, arcs.width, arcs.height);
	}
}
