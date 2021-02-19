<<<<<<< HEAD
package decorator;

public class UsuarioNormal implements CuentaTelegram {

	@Override
	public void abrirCuenta(Usuario u) {
		System.out.println("Se ha creado una nueva cuenta normal de Telegram.");
		System.out.println("Usuario: " + u.getNombre() + ".");
	}

}
=======
package decorator;

public class UsuarioNormal implements CuentaTelegram {

	@Override
	public void abrirCuenta(Usuario u) {
		System.out.println("Se ha creado una nueva cuenta normal de Telegram.");
		System.out.println("Usuario: " + u.getNombre() + ".");
	}

}
>>>>>>> refs/remotes/origin/master
