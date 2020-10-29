package dao;

import java.util.ArrayList;

public interface Dao<T>{
	
	void registrar(T t);
	
	void actualizar(T t);
	
	void eliminar(T t);
	
	ArrayList<T> listar();
}
