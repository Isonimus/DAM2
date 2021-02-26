package com.tongosa.tombola.di;

public interface AccesoDatos {
	public void conectar();

	public void desconectar();

	public void setNumMaxConexiones(int numMaxConexiones);
}
