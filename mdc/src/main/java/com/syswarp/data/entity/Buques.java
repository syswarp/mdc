package com.syswarp.data.entity;

import javax.persistence.Entity;

import com.syswarp.data.AbstractEntity;

@Entity
public class Buques extends AbstractEntity {

    private String imo;
    private String flag;
    private Integer grt;
    private String nombre;
    private Integer eslora;
    private Integer manga;
    private Integer puntal;
    private Integer coef;
    private Integer coeffac;
    private Integer grtfac;

    public String getImo() {
        return imo;
    }
    public void setImo(String imo) {
        this.imo = imo;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public Integer getGrt() {
        return grt;
    }
    public void setGrt(Integer grt) {
        this.grt = grt;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getEslora() {
        return eslora;
    }
    public void setEslora(Integer eslora) {
        this.eslora = eslora;
    }
    public Integer getManga() {
        return manga;
    }
    public void setManga(Integer manga) {
        this.manga = manga;
    }
    public Integer getPuntal() {
        return puntal;
    }
    public void setPuntal(Integer puntal) {
        this.puntal = puntal;
    }
    public Integer getCoef() {
        return coef;
    }
    public void setCoef(Integer coef) {
        this.coef = coef;
    }
    public Integer getCoeffac() {
        return coeffac;
    }
    public void setCoeffac(Integer coeffac) {
        this.coeffac = coeffac;
    }
    public Integer getGrtfac() {
        return grtfac;
    }
    public void setGrtfac(Integer grtfac) {
        this.grtfac = grtfac;
    }

}
