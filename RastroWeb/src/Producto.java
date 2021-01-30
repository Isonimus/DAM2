import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

//BEAN PRODUCTO
public class Producto implements HttpSessionBindingListener {
	
	private int id;
	private String descripcion;
	private double importe;
	private int cantidad; 
	
	public Producto(int id, String descripcion, double importe){
		
		this.id = id;
		this.descripcion = descripcion;
		this.importe = importe;
		this.setCantidad(1);
	}
	
	public void valueBound(HttpSessionBindingEvent e) {
		//cantidad ++;
	}
	
	public void valueUnbound(HttpSessionBindingEvent e) {
		//cantidad --;
	}

	public int getId() {return id;}

	public void setId(int id) {this.id = id;}

	public String getDescripcion() {return descripcion;}

	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

	public double getImporte() {return importe;}

	public void setImporte(double importe) {this.importe = importe;}

	public int getCantidad() {return cantidad;}

	public void setCantidad(int cantidad) {this.cantidad = cantidad;}
}
