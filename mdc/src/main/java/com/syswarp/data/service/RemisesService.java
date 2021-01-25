package com.syswarp.data.service;

import com.syswarp.data.entity.Remises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class RemisesService extends CrudService<Remises, Integer> {

    private RemisesRepository repository;

    public RemisesService(@Autowired RemisesRepository repository) {
        this.repository = repository;
    }

    @Override
    protected RemisesRepository getRepository() {
        return repository;
    }

}
