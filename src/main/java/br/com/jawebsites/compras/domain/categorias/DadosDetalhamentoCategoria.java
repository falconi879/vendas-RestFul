package br.com.jawebsites.compras.domain.categorias;

public record DadosDetalhamentoCategoria(Long id, String nome) {
	public DadosDetalhamentoCategoria(Categoria categoria) {
		this(categoria.getId(), categoria.getNome());
	}

}
