package br.com.jawebsites.compras.domain.clientes;

import br.com.jawebsites.compras.domain.endereco.CadastroEndereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCliente(Long id,String nome,String email,String apelido,CadastroEndereco endereco) {

}
