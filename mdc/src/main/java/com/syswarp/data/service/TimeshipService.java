package com.syswarp.data.service;

import com.syswarp.data.entity.Timeship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDate;

@Service
public class TimeshipService extends CrudService<Timeship, Integer> {

    private TimeshipRepository repository;

    public TimeshipService(@Autowired TimeshipRepository repository) {
        this.repository = repository;
    }

    @Override
    protected TimeshipRepository getRepository() {
        return repository;
    }

}
