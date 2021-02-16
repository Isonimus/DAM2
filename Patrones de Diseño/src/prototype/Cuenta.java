package prototype;

public class Cuenta implements ICuenta{
	
	private String tipo;
	private double saldo;
	
	public Cuenta() {
		
		setTipo("Ahorro");
	}
	
	public ICuenta clonar() {
		
		Cuenta cuenta = null; //CREAR UNA CUENTA B
		
		try {
			
			cuenta = (Cuenta) clone(); //CLONAR (MÉTODO DE JAVA)
			
		}catch(CloneNotSupportedException e) { //SI NO SE PUEDE CLONAR
			e.printStackTrace();
		}
		
		return (ICuenta) cuenta;
	}
	
	public String toString() {
		
		return "Cuenta [tipo = " + getTipo() + ", saldo = " + getSaldo() + "]";
	}
	
	//G&S
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
}
