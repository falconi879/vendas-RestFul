package br.com.jawebsites.compras.domain.clientes;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.jawebsites.compras.domain.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_clientes")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String apelido;
	private String cpf;
	private LocalDate nascimento;
	private Boolean ativo;

	@Enumerated(EnumType.STRING)
	private Genero genero;

	@Embedded
	private Endereco endereco;

	public Cliente(CadastrarCliente dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.apelido = dados.apelido();
		this.cpf = dados.cpf();
		this.nascimento = dados.nascimento();
		this.ativo = true;
		this.genero = dados.genero();
		this.endereco = new Endereco(dados.endereco());
	}

	public void atualizarInformacoes(DadosAtualizacaoCliente dados) {
		if(dados.nome()!=null) {
			this.nome = dados.nome();
		}
		if(dados.email()!=null) {
			this.email = dados.email();
		}
		if(dados.apelido()!=null) {
			this.apelido = dados.apelido();
		}
		if(dados.endereco()!=null) {
			this.endereco.atualizarInformacoes(dados.endereco());
		}
	}

	public void excluir() {

		this.ativo=false;
	}
}
