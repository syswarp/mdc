package com.syswarp.data.entity;

import javax.persistence.Entity;

import com.syswarp.data.AbstractEntity;

@Entity
public class Usuarios extends AbstractEntity {

    private String email;
    private String clave;
    private String nombre;
    private String habilitado;
    private Integer idtipousuario;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getHabilitado() {
        return habilitado;
    }
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }
    public Integer getIdtipousuario() {
        return idtipousuario;
    }
    public void setIdtipousuario(Integer idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

}
