package com.syswarp.data.service;

import com.syswarp.data.entity.Puertos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class PuertosService extends CrudService<Puertos, Integer> {

    private PuertosRepository repository;

    public PuertosService(@Autowired PuertosRepository repository) {
        this.repository = repository;
    }

    @Override
    protected PuertosRepository getRepository() {
        return repository;
    }

}
