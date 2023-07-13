package br.com.jawebsites.compras.domain.pedidos;

import java.time.LocalDateTime;

import br.com.jawebsites.compras.domain.clientes.DadosResumidoCliente;
import br.com.jawebsites.compras.domain.pagamentos.Pagamento;

public record DadosPedido(Long id, String obs, LocalDateTime dataAbertura, LocalDateTime dataFinal, DadosResumidoCliente cliente,
		Pagamento pagamento) {

	public DadosPedido(Pedido pedido) {
		this(pedido.getId(), pedido.getObs(), pedido.getDataAbertura(), pedido.getDataFinal(), new DadosResumidoCliente(pedido.getCliente()),pedido.getPagamento());
	}

}
