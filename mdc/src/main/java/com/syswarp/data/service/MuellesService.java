package com.syswarp.data.service;

import com.syswarp.data.entity.Muelles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class MuellesService extends CrudService<Muelles, Integer> {

    private MuellesRepository repository;

    public MuellesService(@Autowired MuellesRepository repository) {
        this.repository = repository;
    }

    @Override
    protected MuellesRepository getRepository() {
        return repository;
    }

}
