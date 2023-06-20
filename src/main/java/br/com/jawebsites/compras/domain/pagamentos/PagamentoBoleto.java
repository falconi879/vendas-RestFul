package br.com.jawebsites.compras.domain.pagamentos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class PagamentoBoleto extends Pagamento{
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimento;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPagamento;

}
