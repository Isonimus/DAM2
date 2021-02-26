package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestConsultarJPAQL {

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
		String sentenciaJPAQL = "SELECT a FROM Autor a where a.id=?";
		Query query = entityManager.createQuery(sentenciaJPAQL);
		query.setParameter(1, 3);
		List<Autor> listAutores = query.getResultList();
		for (Autor autor : listAutores) {
			System.out.println(autor.getClass());
			System.out.println("id= " + autor.getCodAutor() + " - nombre= " + autor.getNomAutor());
		}
		// ////////////////////////////////////////////
		entityTransaction.commit();
		entityManager.close();
		// //////////////////////////////////////////
		entityManagerFactory.close();
	}

}
