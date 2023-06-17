package br.com.jawebsites.compras.domain.clientes;

import br.com.jawebsites.compras.domain.endereco.CadastroEndereco;

public record DadosAtualizacaoCliente(Long id,String nome,String email,String apelido,CadastroEndereco endereco) {

}
