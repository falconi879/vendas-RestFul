package br.com.jawebsites.compras.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.jawebsites.compras.domain.categorias.Categoria;
import br.com.jawebsites.compras.domain.categorias.DadosAtualizacaoCategoria;
import br.com.jawebsites.compras.domain.categorias.DadosCadastroCategoria;
import br.com.jawebsites.compras.domain.categorias.DadosDetalhamentoCategoria;
import br.com.jawebsites.compras.repositories.CategoriaRepository;
import jakarta.validation.Valid;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public DadosDetalhamentoCategoria cadastroCategoria(@Valid DadosCadastroCategoria dados) {
		var categoria = new Categoria(dados);
		repository.save(categoria);
		return new DadosDetalhamentoCategoria(categoria);

	}
	public Page<DadosDetalhamentoCategoria> buscarCategorias(@PageableDefault(size = 10, sort = { "nome" })Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoCategoria::new);
		return page;
	}
	
	public DadosDetalhamentoCategoria detalheCategoria(@PathVariable Long id) {
		var categoria = repository.getReferenceById(id);
		return new DadosDetalhamentoCategoria(categoria);
	}
	
	public DadosDetalhamentoCategoria atualizarCategoria(@Valid DadosAtualizacaoCategoria dados) {
		var categoria = repository.getReferenceById(dados.id());
		categoria.atualizarInformacoes(dados);
		return new DadosDetalhamentoCategoria(categoria);
	}
}
