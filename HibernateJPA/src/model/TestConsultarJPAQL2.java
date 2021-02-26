package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestConsultarJPAQL2 {

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
		String sentenciaJPAQL = "SELECT l.categoria.nomCategoria FROM Libro l where l.autor.id=?";
		Query query = entityManager.createQuery(sentenciaJPAQL);
		query.setParameter(1, 3);
		List<String> listNombresCategorias = query.getResultList();
		for (String nombre : listNombresCategorias) {
			System.out.println("nombre: " + nombre);
		}
		// ////////////////////////////////////////////
		entityTransaction.commit();
		entityManager.close();
		// //////////////////////////////////////////
		entityManagerFactory.close();
	}

}
