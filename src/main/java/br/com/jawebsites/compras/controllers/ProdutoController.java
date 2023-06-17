package br.com.jawebsites.compras.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.com.jawebsites.compras.domain.produtos.DadosAtualizacaoProduto;
import br.com.jawebsites.compras.domain.produtos.DadosCadastroProduto;
import br.com.jawebsites.compras.domain.produtos.DadosDetalhamentoProduto;
import br.com.jawebsites.compras.domain.produtos.DadosListagemProduto;
import br.com.jawebsites.compras.services.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoProduto> cadastrar(@RequestBody DadosCadastroProduto dados,
			UriComponentsBuilder uriBuilder) {

		var produto = service.cadastroProduto(dados);
		var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.id()).toUri();
		return ResponseEntity.created(uri).body(produto);
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemProduto>> listar(Pageable paginacao) {
		var page = service.listarProdutos(paginacao);
		return ResponseEntity.ok(page);
	}

	// busca produto por id
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoProduto> detalhar(@PathVariable Long id) {
		var produto = service.buscarProdutoId(id);
		return ResponseEntity.ok(produto);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoProduto> atualizar(@RequestBody @Valid DadosAtualizacaoProduto dados) {
		var produto = service.atualizaProduto(dados);
		return ResponseEntity.ok(produto);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoProduto> excluir(@PathVariable Long id) {
		service.desativaProduto(id);
		return ResponseEntity.noContent().build();
	}

}
