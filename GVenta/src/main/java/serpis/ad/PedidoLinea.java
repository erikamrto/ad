package serpis.ad;

import javax.persistence.*;

@Entity
public class PedidoLinea {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="articulo")
	private Articulo articulo;
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	@Override
	public String toString() {
		return String.format("[%s] (%s)", id, pedido);

	}
	
}
