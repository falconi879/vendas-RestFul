package br.com.jawebsites.compras.domain.clientes;

import java.util.Set;

public record DadosResumidoCliente(String nome, Set<String> telefone) {
	
	public DadosResumidoCliente(Cliente cliente) {
		this(cliente.getNome(), cliente.getTelefones());
	}

}
