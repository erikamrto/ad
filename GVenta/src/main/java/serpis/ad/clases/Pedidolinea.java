package serpis.ad.clases;
// Generated 15-ene-2018 11:51:42 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "pedidolinea", catalog = "dbprueba")
public class Pedidolinea implements java.io.Serializable {

	private Long id;
	private Articulo articulo;
	private Pedido pedido;
	private BigDecimal precio;
	private BigDecimal unidades;
	private BigDecimal importe;

	public Pedidolinea() {
	}

	public Pedidolinea(Articulo articulo, Pedido pedido) {
		this.articulo = articulo;
		this.pedido = pedido;
	}

	public Pedidolinea(Articulo articulo, Pedido pedido, BigDecimal precio, BigDecimal unidades, BigDecimal importe) {
		this.articulo = articulo;
		this.pedido = pedido;
		this.precio = precio;
		this.unidades = unidades;
		this.importe = importe;
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
	@JoinColumn(name = "articulo", nullable = false)
	public Articulo getArticulo() {
		return this.articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
		precio = articulo.getPrecio();
		unidades = new BigDecimal(1);
		//importe = unidades.multiply(precio);

	}
	
	public void setUnidades(BigDecimal unidades) {
		this.unidades = unidades;
		//importe= unidades.multiply(precio);
		
	}

	public void setPrecio(BigDecimal precio) {
		this.precio=precio;
		importe= unidades.multiply(precio);
	}
	

	@ManyToOne
	@JoinColumn(name = "pedido", nullable = false)
	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Column(name = "precio", precision = 10)
	public BigDecimal getPrecio() {
		return this.precio;
	}

	@Column(name = "unidades", precision = 10)
	public BigDecimal getUnidades() {
		return this.unidades;
	}

	@Column(name = "importe", precision = 10)
	public BigDecimal getImporte() {
		return unidades.multiply(precio);
		//return this.importe;
	}

	  public void setImporte(BigDecimal importe) {
	        this.importe = importe;
	}
	@PrePersist
	private void PrePersist() {
		importe= unidades.multiply(precio);
	}


	@Override
	public String toString() {

		return String.format("%-5s%-15s%-15s%-15s%-15s%-15s", String.valueOf(this.getId()),
				String.valueOf(this.getArticulo()), String.valueOf(this.getPedido()), String.valueOf(this.getPrecio()),
				String.valueOf(this.getUnidades()), String.valueOf(this.getImporte()));

	}

}
