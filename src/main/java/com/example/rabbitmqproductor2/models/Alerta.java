package com.example.rabbitmqproductor2.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ALERTAS_VITALES", schema = "FULLSTACK")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ALERTA")
    private Long idAlerta;

    @Column(name = "NOMBRE_PACIENTE", nullable = false)
    private String nombrePaciente;

    @Column(name = "HABITACION", nullable = false)
    private String habitacion;

    @Column(name = "COLOR_ALERTA", nullable = false)
    private String colorAlerta;

    @Column(name = "SIGNOS_VITALES")
    private String signosVitales;

    @Column(name = "ESTADO", nullable = false)
    private String estado;

    @Column(name = "FECHA_HORA_REGISTRO", nullable = false)
    private LocalDateTime fechaHoraRegistro;
}
