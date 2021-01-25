package com.syswarp.data.service;

import com.syswarp.data.entity.Maniobras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class ManiobrasService extends CrudService<Maniobras, Integer> {

    private ManiobrasRepository repository;

    public ManiobrasService(@Autowired ManiobrasRepository repository) {
        this.repository = repository;
    }

    @Override
    protected ManiobrasRepository getRepository() {
        return repository;
    }

}
