package decorator;

public class Telegram {

	public static void main(String[] args) {
		
		Usuario u = new Usuario(1, "Marce");
		
		CuentaTelegram cuenta = new UsuarioPremium();
		CuentaTelegram cuentaAdmin = new AdminDecorador(cuenta);
		CuentaTelegram cuentaAdminMute = new MuteDecorador(cuentaAdmin); // SE PUEDE ANIDAR CAPAS
																		 // COMO UNA CEBOLLA, PERO
		cuentaAdminMute.abrirCuenta(u);									 // HAY QUE TENER EN CUENTA 
																		 // QUE EL ORDEN IMPORTA. MUCHO. 
	}
}
