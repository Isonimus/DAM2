package vista;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VistaItem extends JPanel{
	
	private int anchura = 460;
	private int altura = 52;
	protected int margenSuperior = 10;
	protected int alturaComponentes = 30;
	int offsetSombra = 2;
	protected Dimension arcs = new Dimension(12, 12);
	private int xPos, yPos;
	protected final String URL_ICONO_DEFAULT = "img/icono.png";
	protected final String URL_ICONO_LIBRO = "img/iconoLibro.png";
	protected final String URL_ICONO_EDITORIAL = "img/iconoEditorial.png";
	protected final String URL_ICONO_AVATAR = "img/iconoAvatar.png";
	public JButton modificar, eliminar;
	public JLabel picLabel, info;
	BufferedImage iconoItem = null;
	String urlIcono;
	private EsquemaColor estilo;
	
	public VistaItem() {
		
		super();
		this.estilo = new EsquemaColor(EsquemaColor.ESTILO_DEFAULT);
	}
	
	public VistaItem(EsquemaColor estilo) {
		
		super();
		this.estilo = estilo;
	}
	
	public VistaItem(int xPos, int yPos, String itemType, int position, EsquemaColor estilo){
		
		super();
		setxPos(xPos);
		setyPos(yPos);
		this.estilo = estilo;
		init(itemType, position);
	}
	
	private void init(String itemType, int position) {
		
		setBackground(estilo.medioClaro);
		setBounds(getxPos(), getyPos(), getAnchura(), getAltura());
		setLayout(null);
		setOpaque(false);
		
		urlIcono = URL_ICONO_DEFAULT;
		
		switch(itemType) {
		
			case "Libro":
				urlIcono = URL_ICONO_LIBRO;
				break;
				
			case "Editorial":
				urlIcono = URL_ICONO_EDITORIAL;
				break;
				
			default:
				urlIcono = URL_ICONO_AVATAR;
				break;
		}
		
		setIcono(iconoItem, picLabel, urlIcono);
		
		info = new JLabel(position + " " + itemType);
		info.setBounds(40, margenSuperior, 150, alturaComponentes);
		info.setForeground(estilo.sombra);
		add(info);
		
		modificar = new JButton("Editar");
		modificar.setBounds(300, margenSuperior, 70, alturaComponentes);
		modificar.setBackground(estilo.medioOscuro);
		modificar.setForeground(estilo.medioClaro);
		
		add(modificar);
		
		eliminar = new JButton("Borrar");
		eliminar.setBounds(375, margenSuperior, 75, alturaComponentes);
		eliminar.setBackground(estilo.medioOscuro);
		eliminar.setForeground(estilo.medioClaro);
		
		add(eliminar);
		setVisible(true);
	}

	public int getAnchura() {
		return anchura;
	}

	public void setAnchura(int anchura) {
		this.anchura = anchura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public void setIcono(BufferedImage iconoItem, JLabel picLabel, String urlIcono) {
		
		try {
			
			iconoItem = ImageIO.read(new File(urlIcono));
			picLabel = new JLabel(new ImageIcon(iconoItem));
			int centroVertical = (getAltura()/2) - (alturaComponentes/2) - 2;
			picLabel.setBounds(5, centroVertical, 30, alturaComponentes);
			add(picLabel);
			
		} catch (IOException e) {
			
			System.out.println("No se ha podido cargar la imagen '" + urlIcono + "'." );
		}
	}
	
	public void paintComponent(Graphics g) {
		
		 super.paintComponent(g);
		 
		 setBounds(getxPos(), getyPos(), getAnchura(), getAltura());
		 
		 Graphics2D graphics = (Graphics2D) g;
		 graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		 graphics.setColor(estilo.sombra);
         graphics.fillRoundRect(
       		 offsetSombra,// posición X
       		 offsetSombra,// posición Y
             getAnchura() - offsetSombra, // anchura
             getAltura() - offsetSombra, // altura
             arcs.width, arcs.height); // radios de redondeo
		
		 graphics.setColor(getBackground());
	     graphics.fillRoundRect(0, 0, getAnchura()-offsetSombra, getAltura()-offsetSombra, arcs.width, arcs.height);
	}
}
