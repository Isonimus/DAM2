package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public interface Persistent<T> {
	
	public static Vector<DAO> cargaResultSetToVector(ResultSet resultado) throws SQLException {
		return null;
	}
	
	public static Vector<DAO> buscaResultadosConConsulta(String consulta) throws SQLException{
		return null;
		
	}
	
}
