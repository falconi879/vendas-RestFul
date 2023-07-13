package br.com.jawebsites.compras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jawebsites.compras.domain.pedidos.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
