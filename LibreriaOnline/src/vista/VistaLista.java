package vista;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

@SuppressWarnings("serial")
public class VistaLista extends JPanel{
	
	private JLabel labelTitulo;
	public String titulo;
	private int anchura, altura;
	public JPanel contenedorItems;
	private EsquemaColor estilo;
	
	/**
	 * Constructor por defecto: aplica el estilo DEFAULT. (GRISES) 
	 * @param anchura int: la anchura de la vista.
	 * @param altura int: la altura de la vista.
	 * @param titulo String: el título de la vista.
	 */
	VistaLista(int anchura, int altura, String titulo){
		
		super();
		this.anchura = anchura;
		this.altura = altura;
		this.titulo = titulo;
		this.estilo = new EsquemaColor(EsquemaColor.ESTILO_DEFAULT);
		init();	
	}
	
	/**
	 * Constructor avanzado: aplica el estilo deseado.
	 * @param anchura int: la anchura de la vista.
	 * @param altura int: la altura de la vista.
	 * @param titulo String: el título de la vista.
	 * @param estilo EsquemaColor: el esquema deseado.
	 */
	VistaLista(int anchura, int altura, String titulo, EsquemaColor estilo){
		
		super();
		this.anchura = anchura;
		this.altura = altura;
		this.titulo = titulo;
		this.estilo = estilo;
		init();	
	}
	
	private void init() {
		
		setLayout(null);
		setSize(anchura, altura);
		setBackground(estilo.medioClaro);
		
		JPanel encabezado = new JPanel();
		encabezado.setSize(anchura, 40);
		encabezado.setBackground(estilo.oscuro);
		labelTitulo = new JLabel(titulo);
		labelTitulo.setFont(labelTitulo.getFont().deriveFont(20.0F));
		labelTitulo.setForeground(estilo.medioClaro);
		labelTitulo.setBounds(30, 10, 500, 20);
		encabezado.add(labelTitulo);
		add(encabezado);
		
		contenedorItems = new JPanel();
		contenedorItems.setBackground(estilo.medioOscuro);
		contenedorItems.setPreferredSize(new Dimension(460, 500));
		contenedorItems.setLayout(null);
		
		JScrollPane scroll = new JScrollPane(contenedorItems);
		scroll.setBounds(0, 40, 485, 384);//495
		scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			
		    @Override
		    protected void configureScrollBarColors() {
		    	
		        this.thumbColor = estilo.medioOscuro;
		        this.thumbDarkShadowColor = estilo.sombra;
		        this.thumbLightShadowColor = estilo.oscuro;
		        this.thumbHighlightColor = estilo.medioClaro;
		        this.trackColor = estilo.sombra;//new Color(102, 102, 102);
		    }
		    
		    @Override
		    protected JButton createDecreaseButton(int orientation) {
		    	
		    	JButton boton = crearBotonInvisible();
		        return boton;
		    }

		    @Override
		    protected JButton createIncreaseButton(int orientation) {
		    	
		        JButton boton = crearBotonInvisible();
		        return boton;
		    }
		    
		    protected JButton crearBotonInvisible() {
		    	
		    	JButton boton = new JButton();
			    boton.setPreferredSize(new Dimension(0, 0));
		        boton.setMinimumSize(new Dimension(0, 0));
		        boton.setMaximumSize(new Dimension(0, 0));
			    return boton;
		    }
		});
		
		scroll.setBorder(BorderFactory.createEmptyBorder());
		add(scroll);
		setVisible(true);
	}
}
