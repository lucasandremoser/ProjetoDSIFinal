package com.univille.sistemastatus.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.univille.sistemastatus.model.UsuarioLogin;

@Repository
public interface UsuarioLoginRepository extends JpaRepository<UsuarioLogin, Long> {
    UsuarioLogin findByUsername(String username);
}
