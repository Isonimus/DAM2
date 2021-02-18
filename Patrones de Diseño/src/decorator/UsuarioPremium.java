package decorator;

public class UsuarioPremium implements CuentaTelegram{

	@Override
	public void abrirCuenta(Usuario u) {
		System.out.println("Se ha creado una nueva cuenta Premium de Telegram.");
		System.out.println("Usuario: " + u.getNombre() + ".");
	}

}
