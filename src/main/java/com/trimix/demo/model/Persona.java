package com.trimix.demo.model;

import java.util.Date;

public class Persona {

    private long id;
    private String nombre;
    private String apellido;
    private long numeroDocumento;
    private TipoDocumento tipoDocumento;
    private Date fechaNacimiento;

    public Persona(long perId, String perNombre, String perApellido, long perNumeroDocumento,
                   TipoDocumento perTipoDocumento, Date perFechaNacimiento) {
        this.id = perId;
        this.nombre = perNombre;
        this.apellido = perApellido;
        this.numeroDocumento = perNumeroDocumento;
        this.tipoDocumento = perTipoDocumento;
        this.fechaNacimiento = perFechaNacimiento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
