package com.syswarp.data.service;

import com.syswarp.data.entity.Empresas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class EmpresasService extends CrudService<Empresas, Integer> {

    private EmpresasRepository repository;

    public EmpresasService(@Autowired EmpresasRepository repository) {
        this.repository = repository;
    }

    @Override
    protected EmpresasRepository getRepository() {
        return repository;
    }

}
