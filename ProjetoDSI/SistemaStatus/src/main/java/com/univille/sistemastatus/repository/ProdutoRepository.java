package com.univille.sistemastatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.univille.sistemastatus.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
