package serpis.ad.clases;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name = "pedido", catalog = "dbprueba")
public class Pedido implements java.io.Serializable {

	private Long id;

	private Cliente cliente;
	private Calendar fecha;
	private BigDecimal importe;
	private Set<Pedidolinea> pedidolineas = new HashSet<Pedidolinea>(0);

	public Pedido() {
	}

	public Pedido(Cliente cliente, Calendar fecha) {
		this.cliente = cliente;
		this.fecha = fecha;
	}

	public Pedido(Cliente cliente, Calendar fecha, BigDecimal importe, Set<Pedidolinea> pedidolineas) {
		this.cliente = cliente;
		this.fecha = fecha;
		this.importe = importe;
		this.pedidolineas = pedidolineas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "cliente", nullable = false)
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", nullable = false, length = 19)
	public Calendar getFecha() {
		return this.fecha;
	}

	public void setFecha(Calendar calendar) {
		this.fecha = calendar;
	}

	@Column(name = "importe", precision = 10)
	public BigDecimal getImporte() {
		importe = BigDecimal.ZERO;
		for (Pedidolinea pedidolinea : pedidolineas)
			importe.add(pedidolinea.getImporte());
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	@OrderColumn(name = "id")
	@OneToMany(mappedBy = "pedido")
	public Pedidolinea[] getPedidolineas() {
		return pedidolineas.toArray(new Pedidolinea[0]);
	}

	public void setPedidolineas(Set<Pedidolinea> pedidolineas) {
		this.pedidolineas = pedidolineas;
	}

	@PrePersist
	private void PrePersist() {
		// importe= unidades.multiply(precio);
	}

	public void add(Pedidolinea pedidolinea) {
		pedidolineas.add(pedidolinea);
		pedidolinea.setPedido(this);
	}

	public void remove(Pedidolinea pedidolinea) {
		pedidolineas.remove(pedidolinea);
		pedidolinea.setPedido(null);
	}

	@Override
	public String toString() {

		return String.format("[%s] %s %s %s", id, cliente.getNombre(), fecha.getTime(), importe);
	}

}