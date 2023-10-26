package com.minsa.sanama.model.laboratorio;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamenMedico {
    private int idExamen;
    private String codigo;
    private String nombre;
    private String tipo;
    private byte[] archivo;
    private int estado;
    private OrdenLaboratorio ordenLaboratorio;

    private String observaciones;
    private String doctorFirmante;

    public ExamenMedico() {
    }
    
    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public OrdenLaboratorio getOrdenLaboratorio() {
        return ordenLaboratorio;
    }

    public void setOrdenLaboratorio(OrdenLaboratorio ordenLaboratorio) {
        this.ordenLaboratorio = ordenLaboratorio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDoctorFirmante() {
        return doctorFirmante;
    }

    public void setDoctorFirmante(String doctorFirmante) {
        this.doctorFirmante = doctorFirmante;
    }
}
