package com.minsa.sanama.model.atencionmedica;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.minsa.sanama.model.admision.ProgramacionCita;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CitaMedica extends ProgramacionCita{
    private String codigoCitaMedica;
    private HojaMedica hojaMedica;
    private ArrayList<OrdenLaboratorio> ordenesLaboratorio;
    private int requiereTriaje;
    private boolean tieneAcompanhante;
    private String nombreAcompanhante;
    private String dniAcompanhante;
    private String parentezco;

    public CitaMedica() {
    }
    
    public String getCodigoCitaMedica() {
        return codigoCitaMedica;
    }

    public void setCodigoCitaMedica(String codigoCitaMedica) {
        this.codigoCitaMedica = codigoCitaMedica;
    }

    public HojaMedica getHojaMedica() {
        return hojaMedica;
    }

    public void setHojaMedica(HojaMedica hojaMedica) {
        this.hojaMedica = hojaMedica;
    }
    
    public ArrayList<OrdenLaboratorio> getOrdenesLaboratorio() {
        return ordenesLaboratorio;
    }

    public void setOrdenesLaboratorio(ArrayList<OrdenLaboratorio> ordenesLaboratorio) {
        this.ordenesLaboratorio = ordenesLaboratorio;
    }

    public int getRequiereTriaje() {
        return requiereTriaje;
    }

    public void setRequiereTriaje(int requiereTriaje) {
        this.requiereTriaje = requiereTriaje;
    }
    public boolean isTieneAcompanhante() {
        return tieneAcompanhante;
    }

    public void setTieneAcompanhante(boolean tieneAcompanhante) {
        this.tieneAcompanhante = tieneAcompanhante;
    }

    public String getNombreAcompanhante() {
        return nombreAcompanhante;
    }

    public void setNombreAcompanhante(String nombreAcompanhante) {
        this.nombreAcompanhante = nombreAcompanhante;
    }

    public String getDniAcompanhante() {
        return dniAcompanhante;
    }

    public void setDniAcompanhante(String dniAcompanhante) {
        this.dniAcompanhante = dniAcompanhante;
    }

    public String getParentezco() {
        return parentezco;
    }

    public void setParentezco(String parentezco) {
        this.parentezco = parentezco;
    }
}
