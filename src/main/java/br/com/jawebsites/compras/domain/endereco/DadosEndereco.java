package br.com.jawebsites.compras.domain.endereco;

public record DadosEndereco(String logradouro, String bairro,String cep,String numero,String complemento,
		String cidade, String uf) {
	public DadosEndereco(Endereco endereco) {
		this(endereco.getLogradouro(),endereco.getBairro(),endereco.getCep(),endereco.getNumero(),
				endereco.getComplemento(),endereco.getCidade(),endereco.getUf());
	}
}
