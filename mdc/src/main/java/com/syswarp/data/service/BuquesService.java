package com.syswarp.data.service;

import com.syswarp.data.entity.Buques;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class BuquesService extends CrudService<Buques, Integer> {

    private BuquesRepository repository;

    public BuquesService(@Autowired BuquesRepository repository) {
        this.repository = repository;
    }

    @Override
    protected BuquesRepository getRepository() {
        return repository;
    }

}
