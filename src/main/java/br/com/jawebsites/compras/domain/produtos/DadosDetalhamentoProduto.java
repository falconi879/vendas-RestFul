package br.com.jawebsites.compras.domain.produtos;

import java.time.LocalDate;

import br.com.jawebsites.compras.domain.categorias.DadosDetalhamentoCategoria;

public record DadosDetalhamentoProduto(Long id, String codigo, String nome, DadosDetalhamentoCategoria categoria, String obs, String medida,
		LocalDate dataCadastro,Double preco, Boolean ativo) {
	public DadosDetalhamentoProduto (Produto produto) {
		this(produto.getId(), produto.getCodigo(),produto.getNome(), new DadosDetalhamentoCategoria(produto.getCategoria()), produto.getObs(),
				produto.getMedida(),produto.getDataCadastro(),produto.getPreco(),produto.getAtivo());
	}

}
