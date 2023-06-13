package br.com.jawebsites.compras.domain.produtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduto(
		@NotBlank
		String codigo,
		@NotBlank
		String nome,
		Long categoria,
		@NotNull
		Double preco,
		String obs,
		@NotBlank
		String medida
		) {

}
