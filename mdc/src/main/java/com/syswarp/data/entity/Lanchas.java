package com.syswarp.data.entity;

import javax.persistence.Entity;

import com.syswarp.data.AbstractEntity;

@Entity
public class Lanchas extends AbstractEntity {

    private String lancha;
    private String cuit;
    private String domicilio;

    public String getLancha() {
        return lancha;
    }
    public void setLancha(String lancha) {
        this.lancha = lancha;
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
