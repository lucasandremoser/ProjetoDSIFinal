package com.univille.sistemastatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univille.sistemastatus.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
