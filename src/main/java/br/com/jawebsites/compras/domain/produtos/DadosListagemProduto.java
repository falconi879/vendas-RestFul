package br.com.jawebsites.compras.domain.produtos;

public record DadosListagemProduto(Long id, String codigo, String nome, Double preco, String medida, String obs) {
	public DadosListagemProduto(Produto produto) {
		this(produto.getId(), produto.getCodigo(), produto.getNome(), produto.getPreco(), produto.getMedida(), produto.getObs());
	}
}
