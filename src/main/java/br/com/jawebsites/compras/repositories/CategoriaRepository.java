package br.com.jawebsites.compras.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jawebsites.compras.domain.categorias.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Page<Categoria> findAllByAtivoTrue(Pageable paginacao);

}
