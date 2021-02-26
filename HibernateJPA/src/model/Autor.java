package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the autor database table.
 * 
 */
@Entity
@Table(name = "autor")
@NamedQuery(name = "Autor.findAll", query = "SELECT a FROM Autor a")
public class Autor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cod_autor")
	private int codAutor;

	@Column(name = "nom_autor", length = 45)
	private String nomAutor;

	@PrePersist
	private void imprimir() {
		System.out.println("ANTES DE IMPRIMIR");
		System.out.println(this);
	}

	public Autor() {
	}

	public int getCodAutor() {
		return this.codAutor;
	}

	public void setCodAutor(int codAutor) {
		this.codAutor = codAutor;
	}

	public String getNomAutor() {
		return this.nomAutor;
	}

	public void setNomAutor(String nomAutor) {
		this.nomAutor = nomAutor;
	}

	@Override
	public String toString() {
		return "Autor [codAutor=" + codAutor + ", nomAutor=" + nomAutor + "]";
	}

}