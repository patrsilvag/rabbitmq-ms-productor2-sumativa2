package com.example.rabbitmqproductor2.models;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTROL_PROCESOS", schema = "FULLSTACK")
public class CtrlProcesos {

    @Id
    @Column(name = "NOMBRE_PROCESO", nullable = false, length = 100)
    private String nombreProceso;

    @Column(name = "ULTIMA_EJECUCION")
    private Timestamp ultimaEjecucion;

    public CtrlProcesos() {}

    public CtrlProcesos(String nombreProceso, Timestamp ultimaEjecucion) {
        this.nombreProceso = nombreProceso;
        this.ultimaEjecucion = ultimaEjecucion;
    }

    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    public Timestamp getUltimaEjecucion() {
        return ultimaEjecucion;
    }

    public void setUltimaEjecucion(Timestamp ultimaEjecucion) {
        this.ultimaEjecucion = ultimaEjecucion;
    }

    @Override
    public String toString() {
        return "CtrlProcesos{" + "nombreProceso='" + nombreProceso + '\'' + ", ultimaEjecucion="
                + ultimaEjecucion + '}';
    }
}
