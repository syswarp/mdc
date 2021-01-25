package com.syswarp.data.service;

import com.syswarp.data.entity.Agencias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class AgenciasService extends CrudService<Agencias, Integer> {

    private AgenciasRepository repository;

    public AgenciasService(@Autowired AgenciasRepository repository) {
        this.repository = repository;
    }

    @Override
    protected AgenciasRepository getRepository() {
        return repository;
    }

}
