package com.example.rabbitmqproductor2.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ResumenDTO {
    private String fechaResumen;
    private List<PacienteResumenDTO> pacientes;
}
