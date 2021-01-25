package com.syswarp.data.service;

import com.syswarp.data.entity.Estados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class EstadosService extends CrudService<Estados, Integer> {

    private EstadosRepository repository;

    public EstadosService(@Autowired EstadosRepository repository) {
        this.repository = repository;
    }

    @Override
    protected EstadosRepository getRepository() {
        return repository;
    }

}
