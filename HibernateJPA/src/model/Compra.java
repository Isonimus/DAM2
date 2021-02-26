package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the compra database table.
 * 
 */
@Entity
@NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c")
public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_compra")
	private int idCompra;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_compra")
	private Date fechaCompra;

	// bi-directional many-to-one association to Cliente
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dni")
	private Cliente cliente;

	// bi-directional many-to-one association to DetallesCompra
	@OneToMany(mappedBy = "compra", fetch = FetchType.LAZY)
	private List<DetallesCompra> detallesCompras;

	public Compra() {
	}

	public int getIdCompra() {
		return this.idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public Date getFechaCompra() {
		return this.fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<DetallesCompra> getDetallesCompras() {
		return this.detallesCompras;
	}

	public void setDetallesCompras(List<DetallesCompra> detallesCompras) {
		this.detallesCompras = detallesCompras;
	}

	public DetallesCompra addDetallesCompra(DetallesCompra detallesCompra) {
		getDetallesCompras().add(detallesCompra);
		detallesCompra.setCompra(this);

		return detallesCompra;
	}

	public DetallesCompra removeDetallesCompra(DetallesCompra detallesCompra) {
		getDetallesCompras().remove(detallesCompra);
		detallesCompra.setCompra(null);

		return detallesCompra;
	}

}