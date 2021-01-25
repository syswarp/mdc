package com.syswarp.data.service;

import com.syswarp.data.entity.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

}