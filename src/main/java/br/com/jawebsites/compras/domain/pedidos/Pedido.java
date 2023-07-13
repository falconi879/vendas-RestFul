package br.com.jawebsites.compras.domain.pedidos;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.jawebsites.compras.domain.clientes.Cliente;
import br.com.jawebsites.compras.domain.pagamentos.Pagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_pedidos")
@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor

public class Pedido implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String obs;
	private LocalDateTime dataAbertura;
	private LocalDateTime dataFinal;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pagamento_id")
	private Pagamento pagamento;
	
	public Pedido(CadastrarPedido dados, Cliente cliente) {
		
		this.dataAbertura  = LocalDateTime.now();
		this.obs = dados.obs();
		this.cliente = cliente;
	
	}
	
}
