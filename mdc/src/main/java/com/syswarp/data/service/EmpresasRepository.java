package com.syswarp.data.service;

import com.syswarp.data.entity.Empresas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresasRepository extends JpaRepository<Empresas, Integer> {

}