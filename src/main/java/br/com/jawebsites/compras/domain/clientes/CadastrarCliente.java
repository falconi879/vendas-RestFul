package br.com.jawebsites.compras.domain.clientes;

import java.time.LocalDate;
import java.util.Set;

import br.com.jawebsites.compras.domain.endereco.CadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarCliente(
		@NotBlank
		String nome,
		@Email
		String email,
		@NotBlank
		String apelido,
		@NotBlank
		String cpf,
		@NotNull
		LocalDate nascimento,
		@NotNull
		Genero genero,
		@NotNull
		TipoCliente tipoCliente,
		Set<String> telefone,
		 @NotNull @Valid
		CadastroEndereco endereco) {

}
