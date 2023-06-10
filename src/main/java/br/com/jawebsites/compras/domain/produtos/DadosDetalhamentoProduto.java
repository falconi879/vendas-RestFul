package br.com.jawebsites.compras.domain.produtos;

import java.time.LocalDate;

public record DadosDetalhamentoProduto(Long id, String codigo, String nome, String obs, String medida,
		LocalDate dataCadastro,Double preco, Boolean ativo) {
	public DadosDetalhamentoProduto (Produto produto) {
		this(produto.getId(), produto.getCodigo(),produto.getNome(),produto.getObs(),
				produto.getMedida(),produto.getDataCadastro(),produto.getPreco(),produto.getAtivo());
	}

}
