package br.com.jawebsites.compras.domain.categorias;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_categorias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Boolean ativo=true;

	public Categoria(@Valid DadosCadastroCategoria dados) {
		this.nome = dados.nome();
		
	}

	public void atualizarInformacoes(@Valid DadosAtualizacaoCategoria dados) {
		if(dados.nome()!=null) {
			this.nome=dados.nome();
		}
	}

}
