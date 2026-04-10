package com.github.claraf025.ms.pedidos.repositories;

import com.github.claraf025.ms.pedidos.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
