package com.syswarp.data.entity;

import javax.persistence.Entity;

import com.syswarp.data.AbstractEntity;

@Entity
public class Puertos extends AbstractEntity {

    private String puerto;
    private Integer adicional;
    private Integer bonificacion;
    private Integer interno;
    private Integer latitud;
    private Integer longitud;
    private String domicilio;

    public String getPuerto() {
        return puerto;
    }
    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }
    public Integer getAdicional() {
        return adicional;
    }
    public void setAdicional(Integer adicional) {
        this.adicional = adicional;
    }
    public Integer getBonificacion() {
        return bonificacion;
    }
    public void setBonificacion(Integer bonificacion) {
        this.bonificacion = bonificacion;
    }
    public Integer getInterno() {
        return interno;
    }
    public void setInterno(Integer interno) {
        this.interno = interno;
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
    public String getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

}
