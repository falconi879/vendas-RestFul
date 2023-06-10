package br.com.jawebsites.compras.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jawebsites.compras.domain.produtos.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Page<Produto> findAllByAtivoTrue(Pageable paginacao);
	
}
