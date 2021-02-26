package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cod_categoria")
	private int codCategoria;

	@Column(name = "nom_categoria")
	private String nomCategoria;

	// bi-directional many-to-one association to Libro
	@OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
	private List<Libro> libros;

	public Categoria() {
	}

	public int getCodCategoria() {
		return this.codCategoria;
	}

	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getNomCategoria() {
		return this.nomCategoria;
	}

	public void setNomCategoria(String nomCategoria) {
		this.nomCategoria = nomCategoria;
	}

}