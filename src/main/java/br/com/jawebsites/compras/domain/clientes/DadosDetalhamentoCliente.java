package br.com.jawebsites.compras.domain.clientes;

import java.util.Set;

public record DadosDetalhamentoCliente(Long id, String nome, String email, String apelido, Genero genero,TipoCliente tipoCliente,
		Set<String> telefone) {
	public DadosDetalhamentoCliente(Cliente cliente) {
		this(cliente.getId(),cliente.getNome(),cliente.getApelido(),cliente.getEmail(),cliente.getGenero(),
				cliente.getTipoCliente(),cliente.getTelefones());
	}
}
