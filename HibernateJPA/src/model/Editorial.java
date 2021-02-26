package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the editorial database table.
 * 
 */
@Entity
@NamedQuery(name = "Editorial.findAll", query = "SELECT e FROM Editorial e")
public class Editorial implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name = "cod_editorial")
	private int codEditorial;

	@Column(name = "nom_editorial")
	private String nomEditorial;

	public Editorial() {
	}

	public int getCodEditorial() {
		return this.codEditorial;
	}

	public void setCodEditorial(int codEditorial) {
		this.codEditorial = codEditorial;
	}

	public String getNomEditorial() {
		return this.nomEditorial;
	}

	public void setNomEditorial(String nomEditorial) {
		this.nomEditorial = nomEditorial;
	}

}