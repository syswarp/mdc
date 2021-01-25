package com.syswarp.data.entity;

import javax.persistence.Entity;

import com.syswarp.data.AbstractEntity;

@Entity
public class Tipousuarios extends AbstractEntity {

    private String tipousuario;

    public String getTipousuario() {
        return tipousuario;
    }
    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
    }

}
