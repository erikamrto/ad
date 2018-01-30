package serpis.ad;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente cliente;
	private Calendar fecha = Calendar.getInstance();
	private BigDecimal importe;
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@OneToMany(mappedBy="pedido", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<PedidoLinea> pedidoLineas = new ArrayList<>();
	
	public List<PedidoLinea> getPedidoLineas() {
		return pedidoLineas;
	}

	@Override
	public String toString() {
		return String.format("[%s] %s %s %s", id, cliente.getNombre(), fecha.getTime(), importe);

	}
	
}
