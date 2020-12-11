package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public interface Persistent<T> {
	
	public Vector<DAO> cargaResultSetToVector(ResultSet resultado) throws SQLException;
	
	public Vector<DAO> buscaResultadosConConsulta(String consulta) throws SQLException;
	
}
