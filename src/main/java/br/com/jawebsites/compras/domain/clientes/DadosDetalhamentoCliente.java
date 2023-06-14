package br.com.jawebsites.compras.domain.clientes;

public record DadosDetalhamentoCliente(Long id, String nome, String email, String apelido, Genero genero) {
	public DadosDetalhamentoCliente(Cliente cliente) {
		this(cliente.getId(),cliente.getNome(),cliente.getApelido(),cliente.getEmail(),cliente.getGenero());
	}
}
