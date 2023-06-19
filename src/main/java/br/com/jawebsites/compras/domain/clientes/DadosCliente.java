package br.com.jawebsites.compras.domain.clientes;

import java.time.LocalDate;
import java.util.Set;

import br.com.jawebsites.compras.domain.endereco.DadosEndereco;

public record DadosCliente(Long id,String nome,String email,String apelido,String cpf,LocalDate nascimento,
		Boolean ativo, Genero genero,TipoCliente tipoCliente,DadosEndereco endereco, Set<String> telefone) {
	public DadosCliente(Cliente cliente) {
		this(cliente.getId(),cliente.getNome(),cliente.getEmail(),cliente.getApelido(),cliente.getCpfCnpj(),
				cliente.getNascimento(),cliente.getAtivo(),cliente.getGenero(),cliente.getTipoCliente(),
				new DadosEndereco(cliente.getEndereco()), cliente.getTelefones());
	}
}


