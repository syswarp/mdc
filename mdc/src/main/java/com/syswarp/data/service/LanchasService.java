package com.syswarp.data.service;

import com.syswarp.data.entity.Lanchas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class LanchasService extends CrudService<Lanchas, Integer> {

    private LanchasRepository repository;

    public LanchasService(@Autowired LanchasRepository repository) {
        this.repository = repository;
    }

    @Override
    protected LanchasRepository getRepository() {
        return repository;
    }

}
