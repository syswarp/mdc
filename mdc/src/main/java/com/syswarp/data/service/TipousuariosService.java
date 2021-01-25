package com.syswarp.data.service;

import com.syswarp.data.entity.Tipousuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class TipousuariosService extends CrudService<Tipousuarios, Integer> {

    private TipousuariosRepository repository;

    public TipousuariosService(@Autowired TipousuariosRepository repository) {
        this.repository = repository;
    }

    @Override
    protected TipousuariosRepository getRepository() {
        return repository;
    }

}
