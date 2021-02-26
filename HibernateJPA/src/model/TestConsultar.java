package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestConsultar {

	/**
	 * @param args
	 * @throws @throws InstantiationException
	 */
	public static void main(String[] args) throws InstantiationException {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ceinmark_pu");
		// //////////////////////////////////////////
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		// //////////////////////////////////////////////
		// Buscar un registro
		Class claseAutor = Autor.class;
		Class claseCategoria = Categoria.class;
		// Autor autorInstanciado = (Autor) claseAutor.newInstance();
		Autor autor = entityManager.find(Autor.class, 7);
		System.out.println(autor.getClass());
		System.out.println("id= " + autor.getCodAutor() + " - nombre= " + autor.getNomAutor());
		// ////////////////////////////////////////////
		entityTransaction.commit();
		entityManager.close();
		// //////////////////////////////////////////
		entityManagerFactory.close();
	}

}
