package prototype;

public class Demo {

	public static void main(String[] args) {
		
		Cuenta cuentaAhorro = new Cuenta();
		cuentaAhorro.setSaldo(2300);
		Cuenta cuentaClonada = (Cuenta) cuentaAhorro.clonar();
		
		if(cuentaClonada != null) {
			
			System.out.println(cuentaClonada);
		}
		
		//COMPARAR REFERENCIAS EN MEMORIA PARA VERIFICAR QUE NO ES EL MISMO OBJETO
		System.out.println("Son el mismo objeto en memoria: " + (cuentaClonada == cuentaAhorro));
	}
}
