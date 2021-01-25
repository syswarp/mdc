package com.syswarp.data.entity;

import javax.persistence.Entity;

import com.syswarp.data.AbstractEntity;
import java.time.LocalDate;
import java.time.LocalDate;

@Entity
public class Practicos extends AbstractEntity {

    private String practico;
    private String habilitacion;
    private String telefono;
    private String celular;
    private String otro_telefono;
    private String direccion;
    private LocalDate f_revisacion_medica;
    private LocalDate f_vencimiento_chaleco;
    private Integer idempresa;

    public String getPractico() {
        return practico;
    }
    public void setPractico(String practico) {
        this.practico = practico;
    }
    public String getHabilitacion() {
        return habilitacion;
    }
    public void setHabilitacion(String habilitacion) {
        this.habilitacion = habilitacion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getOtro_telefono() {
        return otro_telefono;
    }
    public void setOtro_telefono(String otro_telefono) {
        this.otro_telefono = otro_telefono;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public LocalDate getF_revisacion_medica() {
        return f_revisacion_medica;
    }
    public void setF_revisacion_medica(LocalDate f_revisacion_medica) {
        this.f_revisacion_medica = f_revisacion_medica;
    }
    public LocalDate getF_vencimiento_chaleco() {
        return f_vencimiento_chaleco;
    }
    public void setF_vencimiento_chaleco(LocalDate f_vencimiento_chaleco) {
        this.f_vencimiento_chaleco = f_vencimiento_chaleco;
    }
    public Integer getIdempresa() {
        return idempresa;
    }
    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

}
