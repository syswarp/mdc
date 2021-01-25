package com.syswarp.data.entity;

import javax.persistence.Entity;

import com.syswarp.data.AbstractEntity;

@Entity
public class Maniobras extends AbstractEntity {

    private String maniobra;

    public String getManiobra() {
        return maniobra;
    }
    public void setManiobra(String maniobra) {
        this.maniobra = maniobra;
    }

}
