package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestModificar {

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
		Autor autor = entityManager.find(Autor.class, 57);
		System.out.println(autor.getClass());
		System.out.println("id= " + autor.getCodAutor() + " - nombre= " + autor.getNomAutor());
		autor.setNomAutor("Juan");
		entityManager.persist(autor);
		// ////////////////////////////////////////////
		entityTransaction.commit();
		entityManager.close();
		// //////////////////////////////////////////
		entityManagerFactory.close();
	}

}
