package br.com.jawebsites.compras.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jawebsites.compras.domain.categorias.Categoria;
import br.com.jawebsites.compras.domain.categorias.DadosAtualizacaoCategoria;
import br.com.jawebsites.compras.domain.categorias.DadosCadastroCategoria;
import br.com.jawebsites.compras.domain.categorias.DadosDetalhamentoCategoria;
import br.com.jawebsites.compras.repositories.CategoriaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoCategoria> cadastrar(@RequestBody @Valid DadosCadastroCategoria dados,
			UriComponentsBuilder uriBuilder) {
		var categoria = new Categoria(dados);
		repository.save(categoria);

		var uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoCategoria(categoria));
	}

	@GetMapping
	public ResponseEntity<Page<DadosDetalhamentoCategoria>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoCategoria::new);
		return ResponseEntity.ok(page);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoCategoria> detalhar(@PathVariable Long id) {
		var categoria = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoCategoria(categoria));
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoCategoria> atualizar(@RequestBody @Valid DadosAtualizacaoCategoria dados) {
		var categoria = repository.getReferenceById(dados.id());
		categoria.atualizarInformacoes(dados);

		return ResponseEntity.ok(new DadosDetalhamentoCategoria(categoria));
	}
	
}
