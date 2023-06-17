package br.com.jawebsites.compras.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import br.com.jawebsites.compras.domain.produtos.DadosAtualizacaoProduto;
import br.com.jawebsites.compras.domain.produtos.DadosCadastroProduto;
import br.com.jawebsites.compras.domain.produtos.DadosDetalhamentoProduto;
import br.com.jawebsites.compras.domain.produtos.DadosListagemProduto;
import br.com.jawebsites.compras.domain.produtos.Produto;
import br.com.jawebsites.compras.repositories.CategoriaRepository;
import br.com.jawebsites.compras.repositories.ProdutoRepository;
import jakarta.validation.Valid;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	@Autowired
	private CategoriaRepository categoriarepository;

	public DadosDetalhamentoProduto cadastroProduto(@Valid DadosCadastroProduto dados) {
		var categoria = categoriarepository.getReferenceById(dados.categoria());
		var produto = new Produto(dados, categoria);
		repository.save(produto);
		return new DadosDetalhamentoProduto(produto);
	}

	public Page<DadosListagemProduto> listarProdutos(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemProduto::new);
		return page;
	}

	public DadosDetalhamentoProduto buscarProdutoId(Long id) {
		var produto = repository.getReferenceById(id);
		return new DadosDetalhamentoProduto(produto);
	}

	public DadosDetalhamentoProduto atualizaProduto(DadosAtualizacaoProduto dados) {
		var categoria = categoriarepository.getReferenceById(dados.categoria());
		var produto = repository.getReferenceById(dados.id());
		produto.atualizarInformacoes(dados, categoria);
		return new DadosDetalhamentoProduto(produto);
	}

	public Produto desativaProduto(Long id) {
		var produto = repository.getReferenceById(id);
		produto.excluir();
		return produto;
	}
}
