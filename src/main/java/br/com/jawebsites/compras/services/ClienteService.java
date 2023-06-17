package br.com.jawebsites.compras.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import br.com.jawebsites.compras.domain.clientes.CadastrarCliente;
import br.com.jawebsites.compras.domain.clientes.Cliente;
import br.com.jawebsites.compras.domain.clientes.DadosAtualizacaoCliente;
import br.com.jawebsites.compras.domain.clientes.DadosCliente;
import br.com.jawebsites.compras.domain.clientes.DadosDetalhamentoCliente;
import br.com.jawebsites.compras.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public DadosCliente cadastrarCliente(CadastrarCliente dados){
		var cliente =new Cliente(dados);
		repository.save(cliente);
		return new DadosCliente(cliente);
	}
	public Page<DadosCliente> listarCliente(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosCliente::new);
		return page;
	}

	public DadosDetalhamentoCliente detalharClienteId(Long id) {
		var cliente = repository.getReferenceById(id);
		return new DadosDetalhamentoCliente(cliente);
	}
	public DadosCliente atualizarCliente(DadosAtualizacaoCliente dados) {
		var cliente = repository.getReferenceById(dados.id());
		
		cliente.atualizarInformacoes(dados);

		return new DadosCliente(cliente);
	}
	public Cliente desativaCliente(Long id) {
        var cliente = repository.getReferenceById(id);
        cliente.excluir();
        return cliente;
    }

	
}
