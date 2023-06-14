package br.com.jawebsites.compras.domain.produtos;

import br.com.jawebsites.compras.domain.categorias.DadosDetalhamentoCategoria;

public record DadosListagemProduto(Long id, String codigo, String nome, DadosDetalhamentoCategoria categoria,
		Double preco, String medida, String obs) {
	public DadosListagemProduto(Produto produto) {
		this(produto.getId(), produto.getCodigo(), produto.getNome(),
				new DadosDetalhamentoCategoria(produto.getCategoria()), produto.getPreco(), produto.getMedida(),
				produto.getObs());
	}
}
