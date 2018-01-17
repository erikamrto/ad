package serpis.ad;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.*;

@Entity
public class Pedidolinea {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	private BigInteger pedido;
	private BigInteger articulo;
	private BigDecimal precio;
	private BigDecimal unidades;
	private BigDecimal importe;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger getPedido() {
		return pedido;
	}
	public void setPedido(BigInteger pedido) {
		this.pedido = pedido;
	}
	public BigInteger getArticulo() {
		return articulo;
	}
	public void setArticulo(BigInteger articulo) {
		this.articulo = articulo;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public BigDecimal getUnidades() {
		return unidades;
	}
	public void setUnidades(BigDecimal unidades) {
		this.unidades = unidades;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
}
