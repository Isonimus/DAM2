package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestConsultarSQLNativo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ceinmark_pu");
		// //////////////////////////////////////////
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		// //////////////////////////////////////////////
		// Buscar un registro
		String sentenciaSQLNativo = "select cod_categoria,nom_categoria from categoria";
		Query query = entityManager.createNativeQuery(sentenciaSQLNativo);
		List<Object[]> listCategorias = query.getResultList();
		for (Object[] registro : listCategorias) {
			System.out.println("codigo: " + registro[0]);
			System.out.println("nombre: " + registro[1]);
		}
		// ////////////////////////////////////////////
		entityTransaction.commit();
		entityManager.close();
		// //////////////////////////////////////////
		entityManagerFactory.close();
	}

}
