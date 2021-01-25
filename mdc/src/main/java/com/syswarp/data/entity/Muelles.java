package com.syswarp.data.entity;

import javax.persistence.Entity;

import com.syswarp.data.AbstractEntity;

@Entity
public class Muelles extends AbstractEntity {

    private Integer muelle;
    private Integer idpuerto;
    private Integer kilometro;
    private String emax;
    private Integer rv;
    private String estructura;
    private Integer det;
    private Integer latitud;
    private Integer longitud;

    public Integer getMuelle() {
        return muelle;
    }
    public void setMuelle(Integer muelle) {
        this.muelle = muelle;
    }
    public Integer getIdpuerto() {
        return idpuerto;
    }
    public void setIdpuerto(Integer idpuerto) {
        this.idpuerto = idpuerto;
    }
    public Integer getKilometro() {
        return kilometro;
    }
    public void setKilometro(Integer kilometro) {
        this.kilometro = kilometro;
    }
    public String getEmax() {
        return emax;
    }
    public void setEmax(String emax) {
        this.emax = emax;
    }
    public Integer getRv() {
        return rv;
    }
    public void setRv(Integer rv) {
        this.rv = rv;
    }
    public String getEstructura() {
        return estructura;
    }
    public void setEstructura(String estructura) {
        this.estructura = estructura;
    }
    public Integer getDet() {
        return det;
    }
    public void setDet(Integer det) {
        this.det = det;
    }
    public Integer getLatitud() {
        return latitud;
    }
    public void setLatitud(Integer latitud) {
        this.latitud = latitud;
    }
    public Integer getLongitud() {
        return longitud;
    }
    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

}
