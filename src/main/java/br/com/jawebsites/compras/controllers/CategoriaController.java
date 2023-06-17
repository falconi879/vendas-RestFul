package br.com.jawebsites.compras.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jawebsites.compras.domain.categorias.DadosAtualizacaoCategoria;
import br.com.jawebsites.compras.domain.categorias.DadosCadastroCategoria;
import br.com.jawebsites.compras.domain.categorias.DadosDetalhamentoCategoria;
import br.com.jawebsites.compras.services.CategoriaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService service;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoCategoria> cadastrar(@RequestBody DadosCadastroCategoria dados,
			UriComponentsBuilder uriBuilder) {
		var categoria = service.cadastroCategoria(dados);
		var uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.id()).toUri();
		return ResponseEntity.created(uri).body(categoria);
	}

	@GetMapping
	public ResponseEntity<Page<DadosDetalhamentoCategoria>> listar(Pageable paginacao) {
		var page = service.buscarCategorias(paginacao);
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoCategoria> detalhar(Long id) {
		var categoria = service.detalheCategoria(id);
		return ResponseEntity.ok(categoria);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoCategoria> atualizar(@RequestBody @Valid DadosAtualizacaoCategoria dados) {
		var categoria = service.atualizarCategoria(dados);
		return ResponseEntity.ok(categoria);
	}
}
