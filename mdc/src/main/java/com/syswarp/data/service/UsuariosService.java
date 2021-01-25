package com.syswarp.data.service;

import com.syswarp.data.entity.Usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class UsuariosService extends CrudService<Usuarios, Integer> {

    private UsuariosRepository repository;

    public UsuariosService(@Autowired UsuariosRepository repository) {
        this.repository = repository;
    }

    @Override
    protected UsuariosRepository getRepository() {
        return repository;
    }

}
