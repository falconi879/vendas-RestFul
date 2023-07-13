package br.com.jawebsites.compras.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jawebsites.compras.domain.pedidos.CadastrarPedido;
import br.com.jawebsites.compras.domain.pedidos.DadosPedido;
import br.com.jawebsites.compras.domain.pedidos.Pedido;
import br.com.jawebsites.compras.repositories.ClienteRepository;
import br.com.jawebsites.compras.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public DadosPedido cadastrarPedido(CadastrarPedido dados){
		var cliente = clienteRepository.getReferenceById(dados.cliente()); 
		var pedido =new Pedido(dados,cliente);
		pedidoRepository.save(pedido);
		return new DadosPedido(pedido);
	}
}
