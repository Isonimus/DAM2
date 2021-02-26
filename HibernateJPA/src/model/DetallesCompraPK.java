package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the detalles_compra database table.
 * 
 */
@Embeddable
public class DetallesCompraPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idCompra;

	private String isbn;

	public DetallesCompraPK() {
	}
	public int getIdCompra() {
		return this.idCompra;
	}
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
	public String getIsbn() {
		return this.isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetallesCompraPK)) {
			return false;
		}
		DetallesCompraPK castOther = (DetallesCompraPK)other;
		return 
			(this.idCompra == castOther.idCompra)
			&& this.isbn.equals(castOther.isbn);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCompra;
		hash = hash * prime + this.isbn.hashCode();
		
		return hash;
	}
}