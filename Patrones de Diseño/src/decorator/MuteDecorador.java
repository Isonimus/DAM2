<<<<<<< HEAD
package decorator;

public class MuteDecorador extends UsuarioDecorador{

	public MuteDecorador(CuentaTelegram usuarioDecorado) {
		super(usuarioDecorado);
	}
	
	public void abrirCuenta(Usuario u) {
		usuarioDecorado.abrirCuenta(u);
		hacerMute(u);
	}

	public void hacerMute(Usuario u) {
		System.out.println("Ahora " + u.getNombre() + " est� mutead@.");
	}
}
=======
package decorator;

public class MuteDecorador extends UsuarioDecorador{

	public MuteDecorador(CuentaTelegram usuarioDecorado) {
		super(usuarioDecorado);
	}
	
	public void abrirCuenta(Usuario u) {
		usuarioDecorado.abrirCuenta(u);
		hacerMute(u);
	}

	public void hacerMute(Usuario u) {
		System.out.println("Ahora " + u.getNombre() + " est� mutead@.");
	}
}
>>>>>>> refs/remotes/origin/master
