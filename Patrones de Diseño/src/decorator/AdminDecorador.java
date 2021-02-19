<<<<<<< HEAD
package decorator;

public class AdminDecorador extends UsuarioDecorador{

	public AdminDecorador(CuentaTelegram usuarioDecorado) {
		super(usuarioDecorado);
	}
	
	public void abrirCuenta(Usuario u) {
		usuarioDecorado.abrirCuenta(u);
		hacerAdmin(u);
	}

	public void hacerAdmin(Usuario u) {
		System.out.println("Ahora " + u.getNombre() + " tiene privilegios de Admin.");
	}
}
=======
package decorator;

public class AdminDecorador extends UsuarioDecorador{

	public AdminDecorador(CuentaTelegram usuarioDecorado) {
		super(usuarioDecorado);
	}
	
	public void abrirCuenta(Usuario u) {
		usuarioDecorado.abrirCuenta(u);
		hacerAdmin(u);
	}

	public void hacerAdmin(Usuario u) {
		System.out.println("Ahora " + u.getNombre() + " tiene privilegios de Admin.");
	}
}
>>>>>>> refs/remotes/origin/master
