package com.minsa.sanama.model.configuracion;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LookupValue {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int idValue=0;
    private String descripcion;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int estado=0;

    public LookupValue(){
    }

    public int getIdValue() {
        return idValue;
    }

    public void setIdValue(int idValue) {
        this.idValue = idValue;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
