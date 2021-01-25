package com.syswarp.data.entity;

import javax.persistence.Entity;

import com.syswarp.data.AbstractEntity;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDate;

@Entity
public class Timeship extends AbstractEntity {

    private Integer idbuque;
    private Integer idagencia;
    private LocalDate fecha_etp;
    private Integer idempresa;
    private String observaciones;
    private Integer idpuertodesde;
    private Integer idmuelledesde;
    private Integer idpuertohasta;
    private Integer idmuellehasta;
    private LocalDate fecha_transaccion;
    private Integer idmaniobra;
    private Integer idestado;
    private LocalDate f_est_inicio_maniobra;
    private LocalDate f_est_fin_maniobra;
    private Integer idpractico;
    private LocalDate f_asignacion;
    private Integer idlancha;
    private Integer idremis;
    private Integer nrodespacho;
    private LocalDate f_fin_maniobra;
    private LocalDate f_desembarco;
    private LocalDate f_presentacion;

    public Integer getIdbuque() {
        return idbuque;
    }
    public void setIdbuque(Integer idbuque) {
        this.idbuque = idbuque;
    }
    public Integer getIdagencia() {
        return idagencia;
    }
    public void setIdagencia(Integer idagencia) {
        this.idagencia = idagencia;
    }
    public LocalDate getFecha_etp() {
        return fecha_etp;
    }
    public void setFecha_etp(LocalDate fecha_etp) {
        this.fecha_etp = fecha_etp;
    }
    public Integer getIdempresa() {
        return idempresa;
    }
    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public Integer getIdpuertodesde() {
        return idpuertodesde;
    }
    public void setIdpuertodesde(Integer idpuertodesde) {
        this.idpuertodesde = idpuertodesde;
    }
    public Integer getIdmuelledesde() {
        return idmuelledesde;
    }
    public void setIdmuelledesde(Integer idmuelledesde) {
        this.idmuelledesde = idmuelledesde;
    }
    public Integer getIdpuertohasta() {
        return idpuertohasta;
    }
    public void setIdpuertohasta(Integer idpuertohasta) {
        this.idpuertohasta = idpuertohasta;
    }
    public Integer getIdmuellehasta() {
        return idmuellehasta;
    }
    public void setIdmuellehasta(Integer idmuellehasta) {
        this.idmuellehasta = idmuellehasta;
    }
    public LocalDate getFecha_transaccion() {
        return fecha_transaccion;
    }
    public void setFecha_transaccion(LocalDate fecha_transaccion) {
        this.fecha_transaccion = fecha_transaccion;
    }
    public Integer getIdmaniobra() {
        return idmaniobra;
    }
    public void setIdmaniobra(Integer idmaniobra) {
        this.idmaniobra = idmaniobra;
    }
    public Integer getIdestado() {
        return idestado;
    }
    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }
    public LocalDate getF_est_inicio_maniobra() {
        return f_est_inicio_maniobra;
    }
    public void setF_est_inicio_maniobra(LocalDate f_est_inicio_maniobra) {
        this.f_est_inicio_maniobra = f_est_inicio_maniobra;
    }
    public LocalDate getF_est_fin_maniobra() {
        return f_est_fin_maniobra;
    }
    public void setF_est_fin_maniobra(LocalDate f_est_fin_maniobra) {
        this.f_est_fin_maniobra = f_est_fin_maniobra;
    }
    public Integer getIdpractico() {
        return idpractico;
    }
    public void setIdpractico(Integer idpractico) {
        this.idpractico = idpractico;
    }
    public LocalDate getF_asignacion() {
        return f_asignacion;
    }
    public void setF_asignacion(LocalDate f_asignacion) {
        this.f_asignacion = f_asignacion;
    }
    public Integer getIdlancha() {
        return idlancha;
    }
    public void setIdlancha(Integer idlancha) {
        this.idlancha = idlancha;
    }
    public Integer getIdremis() {
        return idremis;
    }
    public void setIdremis(Integer idremis) {
        this.idremis = idremis;
    }
    public Integer getNrodespacho() {
        return nrodespacho;
    }
    public void setNrodespacho(Integer nrodespacho) {
        this.nrodespacho = nrodespacho;
    }
    public LocalDate getF_fin_maniobra() {
        return f_fin_maniobra;
    }
    public void setF_fin_maniobra(LocalDate f_fin_maniobra) {
        this.f_fin_maniobra = f_fin_maniobra;
    }
    public LocalDate getF_desembarco() {
        return f_desembarco;
    }
    public void setF_desembarco(LocalDate f_desembarco) {
        this.f_desembarco = f_desembarco;
    }
    public LocalDate getF_presentacion() {
        return f_presentacion;
    }
    public void setF_presentacion(LocalDate f_presentacion) {
        this.f_presentacion = f_presentacion;
    }

}
