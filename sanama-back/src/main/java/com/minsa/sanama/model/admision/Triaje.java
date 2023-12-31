package com.minsa.sanama.model.admision;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Triaje {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int idTriaje=0;
    private String codigoTriaje;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int peso=0;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int talla=0;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int temperatura=0;
    private String motivoVisita;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int presionArterial=0;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String presionSistolica="0";
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String presionDiastolica="0";
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int estado=0;
    private String prioridad;
    private LocalDate fechaTriaje;
    private LocalTime horaTriaje;
    private String saturacionOxigeno;
    private String frecuenciaCardiaca;
    private String frecuenciaRespiratoria;
    private String nivelConciencia;
    private String glasgow;     //aqui
    private String eyesOpen;    //aqui
    private String talkingCorrectly;    //aqui
    private String ableToMoveBody;      //aqui
    private String nivelDolor;
    private String condicionesPrexistentes;
    private Paciente paciente;


    public Triaje() {
    }

    public String getGlasgow() {
        return glasgow;
    }

    public void setGlasgow(String glasgow) {
        this.glasgow = glasgow;
    }

    public String getEyesOpen() {
        return eyesOpen;
    }

    public void setEyesOpen(String eyesOpen) {
        this.eyesOpen = eyesOpen;
    }

    public String getPresionSistolica() {
        return presionSistolica;
    }

    public void setPresionSistolica(String presionSistolica) {
        this.presionSistolica = presionSistolica;
    }

    public String getPresionDiastolica() {
        return presionDiastolica;
    }

    public void setPresionDiastolica(String presionDiastolica) {
        this.presionDiastolica = presionDiastolica;
    }

    public String getTalkingCorrectly() {
        return talkingCorrectly;
    }

    public void setTalkingCorrectly(String talkingCorrectly) {
        this.talkingCorrectly = talkingCorrectly;
    }

    public String getAbleToMoveBody() {
        return ableToMoveBody;
    }

    public void setAbleToMoveBody(String ableToMoveBody) {
        this.ableToMoveBody = ableToMoveBody;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public int getIdTriaje() {
        return idTriaje;
    }

    public void setIdTriaje(int idTriaje) {
        this.idTriaje = idTriaje;
    }

    public String getCodigoTriaje() {
        return codigoTriaje;
    }

    public void setCodigoTriaje(String codigoTriaje) {
        this.codigoTriaje = codigoTriaje;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public String getMotivoVisita() {
        return motivoVisita;
    }

    public void setMotivoVisita(String motivoVisita) {
        this.motivoVisita = motivoVisita;
    }

    public int getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(int presionArterial) {
        this.presionArterial = presionArterial;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public LocalDate getFechaTriaje() {
        return fechaTriaje;
    }

    public void setFechaTriaje(LocalDate fechaTriaje) {
        this.fechaTriaje = fechaTriaje;
    }

    public LocalTime getHoraTriaje() {
        return horaTriaje;
    }

    public void setHoraTriaje(LocalTime horaTriaje) {
        this.horaTriaje = horaTriaje;
    }

    public String getSaturacionOxigeno() {
        return saturacionOxigeno;
    }

    public void setSaturacionOxigeno(String saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public String getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(String frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public String getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(String frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public String getNivelConciencia() {
        return nivelConciencia;
    }

    public void setNivelConciencia(String nivelConciencia) {
        this.nivelConciencia = nivelConciencia;
    }

    public String getNivelDolor() {
        return nivelDolor;
    }

    public void setNivelDolor(String nivelDolor) {
        this.nivelDolor = nivelDolor;
    }

    public String getCondicionesPrexistentes() {
        return condicionesPrexistentes;
    }

    public void setCondicionesPrexistentes(String condicionesPrexistentes) {
        this.condicionesPrexistentes = condicionesPrexistentes;
    }
}
