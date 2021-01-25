package com.syswarp.data.service;

import com.syswarp.data.entity.Practicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import java.time.LocalDate;
import java.time.LocalDate;

@Service
public class PracticosService extends CrudService<Practicos, Integer> {

    private PracticosRepository repository;

    public PracticosService(@Autowired PracticosRepository repository) {
        this.repository = repository;
    }

    @Override
    protected PracticosRepository getRepository() {
        return repository;
    }

}
