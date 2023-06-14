package br.com.jawebsites.compras.domain.produtos;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.jawebsites.compras.domain.categorias.Categoria;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_produtos")
@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private String nome;
	private String obs;
	private String medida;
	private LocalDate dataCadastro;
	private Double preco;
	private Boolean ativo;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	public Produto(DadosCadastroProduto dados, Categoria categoria) {
		this.codigo = dados.codigo().toUpperCase();
		this.preco = dados.preco();
		this.nome = dados.nome().toUpperCase();
		this.categoria = categoria;
		this.medida = dados.medida().toUpperCase();
		this.obs = dados.obs().toUpperCase();
		this.ativo = true;
		this.dataCadastro = LocalDate.now();
	}

	public void atualizarInformacoes(DadosAtualizacaoProduto dados, Categoria categoria) {
		if (dados.codigo() != null) {
			this.codigo = dados.codigo().toUpperCase();
		}
		if (dados.preco() != null) {
			this.preco = dados.preco();
		}
		if (dados.nome() != null) {
			this.nome = dados.nome().toUpperCase();
		}
		if(dados.categoria()!=null) {
			this.categoria = categoria;
		}
		if (dados.medida() != null) {
			this.medida = dados.medida().toUpperCase();
		}
		if (dados.obs() != null) {
			this.obs = dados.obs().toUpperCase();
		}
		this.ativo = true;
	}

	public void excluir() {
		this.ativo = false;
	}

}
