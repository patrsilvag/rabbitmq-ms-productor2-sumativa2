package com.example.rabbitmqproductor2.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PacienteResumenDTO {
    private String paciente;
    private Long cantidadAlertas;
    private Double frecuenciaPromedio;
    private Double frecuenciaMaxima;
}
