package com.saga.challenge.phael.repository;

import com.saga.challenge.phael.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, String> {
}
