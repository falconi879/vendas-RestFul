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

import br.com.jawebsites.compras.domain.produtos.DadosAtualizacaoProduto;
import br.com.jawebsites.compras.domain.produtos.DadosCadastroProduto;
import br.com.jawebsites.compras.domain.produtos.DadosDetalhamentoProduto;
import br.com.jawebsites.compras.domain.produtos.DadosListagemProduto;
import br.com.jawebsites.compras.domain.produtos.Produto;
import br.com.jawebsites.compras.repositories.CategoriaRepository;
import br.com.jawebsites.compras.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	@Autowired
	private CategoriaRepository categoriarepository;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoProduto> cadastrar(@RequestBody @Valid DadosCadastroProduto dados,
			UriComponentsBuilder uriBuilder) {
		var categoria = categoriarepository.getReferenceById(dados.categoria());
		System.out.println(categoria);
		var produto = new Produto(dados,categoria);
		
		System.out.println(produto);
		repository.save(produto);

		var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
	}

	// buscar todos produtos ativos
	@GetMapping
	public ResponseEntity<Page<DadosListagemProduto>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemProduto::new);
		return ResponseEntity.ok(page);
	}

	// busca produto por id
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoProduto> detalhar(@PathVariable Long id) {
		var produto = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoProduto> atualizar(@RequestBody @Valid DadosAtualizacaoProduto dados) {
		var produto = repository.getReferenceById(dados.id());
		produto.atualizarInformacoes(dados);

		return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
	}
	
	 @DeleteMapping("/{id}")
	    @Transactional
	    public ResponseEntity<DadosDetalhamentoProduto> excluir(@PathVariable Long id) {
	        var produto = repository.getReferenceById(id);
	        produto.excluir();
	        return ResponseEntity.noContent().build();
	    }


}
