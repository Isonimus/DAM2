package com.tongosa.tombola.di.impl;

import com.tongosa.tombola.di.AccesoDatos;

public class AccesoDatosOracle2 implements AccesoDatos {

	private int numMaxConexiones;

	private int numMinConexiones;

	public void setNumMaxConexiones(int numMaxConexiones) {
		this.numMaxConexiones = numMaxConexiones;

	}

	public void setNumMinConexiones(int numMinConexiones) {
		this.numMinConexiones = numMinConexiones;
	}

	public void conectar() {
		System.out.println("conectar Oracle versi�n 2");
		System.out.println("numMaxConexiones: " + numMaxConexiones);
		System.out.println("numMinConexiones: " + numMinConexiones);

	}

	public void desconectar() {
		System.out.println("desconectar Oracle");

	}

}
