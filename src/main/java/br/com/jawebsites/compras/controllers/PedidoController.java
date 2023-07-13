package br.com.jawebsites.compras.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jawebsites.compras.domain.pedidos.CadastrarPedido;
import br.com.jawebsites.compras.domain.pedidos.DadosPedido;
import br.com.jawebsites.compras.services.PedidoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
@RestController
@RequestMapping("pedido")
public class PedidoController {
	@Autowired
	private PedidoService service;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosPedido> cadastrar(@RequestBody @Valid CadastrarPedido dados,
			UriComponentsBuilder uriBuilder) {
		var pedido = service.cadastrarPedido(dados);
		var uri = uriBuilder.path("/produto/{id}").buildAndExpand(pedido.id()).toUri();
		return ResponseEntity.created(uri).body(pedido);
	}

}
