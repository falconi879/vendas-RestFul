package br.com.jawebsites.compras.domain.clientes;

import java.time.LocalDate;

import br.com.jawebsites.compras.domain.endereco.DadosEndereco;

public record DadosCliente(Long id,String nome,String email,String apelido,String cpf,LocalDate nascimento,
		Boolean ativo, Genero genero,DadosEndereco endereco) {
	public DadosCliente(Cliente cliente) {
		this(cliente.getId(),cliente.getNome(),cliente.getEmail(),cliente.getApelido(),cliente.getCpf(),
				cliente.getNascimento(),cliente.getAtivo(),cliente.getGenero(), new DadosEndereco(cliente.getEndereco()));
	}
}
