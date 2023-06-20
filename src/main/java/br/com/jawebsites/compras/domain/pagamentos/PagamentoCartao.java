package br.com.jawebsites.compras.domain.pagamentos;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PagamentoCartao extends Pagamento{
	private static final long serialVersionUID = 1L;

	private Integer numeroParcela;
}
