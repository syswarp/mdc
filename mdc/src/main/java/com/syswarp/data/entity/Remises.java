package com.syswarp.data.entity;

import javax.persistence.Entity;

import com.syswarp.data.AbstractEntity;

@Entity
public class Remises extends AbstractEntity {

    private String remis;
    private String cuit;
    private String domicilio;

    public String getRemis() {
        return remis;
    }
    public void setRemis(String remis) {
        this.remis = remis;
    }
    public String getCuit() {
        return cuit;
    }
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

}
