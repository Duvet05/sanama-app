package com.minsa.sanama.model.admision;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.minsa.sanama.model.rrhh.Medico;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgramacionCita {
    private int idCita;
    private Paciente paciente;
    private Medico medico;
    private LocalTime horaCita;
    private LocalDate fechaCita;
    private String lugarCita;
    private String tipoCita;
    private String codigoCita;
    private EstadoCita estadoCita;
    private int estado;
    private boolean tieneAcompanhante;
    private String nombreAcompanhante;
    private String dniAcompanhante;
    private String parentezco;
    private Triaje triaje;

    public ProgramacionCita() {
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Triaje getTriaje() {
        return triaje;
    }

    public void setTriaje(Triaje triaje) {
        this.triaje = triaje;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public LocalTime getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(LocalTime horaCita) {
        this.horaCita = horaCita;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getLugarCita() {
        return lugarCita;
    }

    public void setLugarCita(String lugarCita) {
        this.lugarCita = lugarCita;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

    public String getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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
