package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the detalles_compra database table.
 * 
 */
@Entity
@Table(name = "detalles_compra")
@NamedQuery(name = "DetallesCompra.findAll", query = "SELECT d FROM DetallesCompra d")
public class DetallesCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetallesCompraPK id;

	private int cantidad;

	// bi-directional many-to-one association to Compra
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_compra", insertable = false, updatable = false)
	private Compra compra;

	// bi-directional many-to-one association to Libro
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "isbn", insertable = false, updatable = false)
	private Libro libro;

	public DetallesCompra() {
	}

	public DetallesCompraPK getId() {
		return this.id;
	}

	public void setId(DetallesCompraPK id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Libro getLibro() {
		return this.libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

}