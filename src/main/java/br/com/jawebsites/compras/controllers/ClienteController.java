package br.com.jawebsites.compras.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jawebsites.compras.domain.clientes.CadastrarCliente;
import br.com.jawebsites.compras.domain.clientes.DadosAtualizacaoCliente;
import br.com.jawebsites.compras.domain.clientes.DadosCliente;
import br.com.jawebsites.compras.domain.clientes.DadosDetalhamentoCliente;
import br.com.jawebsites.compras.services.ClienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosCliente> cadastrar(@RequestBody @Valid CadastrarCliente dados,
			UriComponentsBuilder uriBuilder) {
		var cliente = service.cadastrarCliente(dados);
		var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.id()).toUri();
		return ResponseEntity.created(uri).body(cliente);
	}

	@GetMapping
	public ResponseEntity<Page<DadosCliente>> listar(
			@PageableDefault(size = 25, sort = { "id" }) Pageable paginacao) {
		var page = service.listarCliente(paginacao);
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoCliente> detalhar(@PathVariable Long id) {
		var cliente = service.detalharClienteId(id);
		return ResponseEntity.ok(cliente);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosCliente> atualizar(@RequestBody DadosAtualizacaoCliente dados) {
		var cliente = service.atualizarCliente(dados);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosCliente> excluir(@PathVariable Long id) {
		service.desativaCliente(id);
		return ResponseEntity.noContent().build();
	}
}
