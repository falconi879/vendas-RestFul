package br.com.jawebsites.compras.domain.produtos;

public record DadosAtualizacaoProduto(Long id, String codigo, Long categoria, String nome, String obs, String medida,Double preco, Boolean ativo) {

}
