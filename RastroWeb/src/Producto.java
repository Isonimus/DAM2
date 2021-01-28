//BEAN PRODUCTO
public class Producto {
	
	private int id;
	private String descripcion;
	private double importe;
	
	public Producto(int id, String descripcion, double importe) {
		
		this.id = id;
		this.descripcion = descripcion;
		this.importe = importe;
	}

	public int getId() {return id;}

	public void setId(int id) {this.id = id;}

	public String getDescripcion() {return descripcion;}

	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

	public double getImporte() {return importe;}

	public void setImporte(double importe) {this.importe = importe;}
}
