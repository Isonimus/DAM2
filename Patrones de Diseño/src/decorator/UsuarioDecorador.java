package decorator;

public abstract class UsuarioDecorador implements CuentaTelegram {
	
	protected CuentaTelegram usuarioDecorado;
	
	public UsuarioDecorador(CuentaTelegram usuarioDecorado) {
		this.usuarioDecorado = usuarioDecorado;
	}
	
	@Override
	public void abrirCuenta(Usuario u) {
		this.usuarioDecorado.abrirCuenta(u);
	}
}