package com.example.rabbitmqproductor2.scheduler;

import com.example.rabbitmqproductor2.dto.PacienteResumenDTO;
import com.example.rabbitmqproductor2.dto.ResumenDTO;
import com.example.rabbitmqproductor2.repositories.AlertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductorScheduler {

    private final AlertaRepository alertaRepository;
    private final RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate = 100000)
    public void enviarResumen() {
        List<Object[]> data = alertaRepository.obtenerResumenAlertas();
        if (data == null || data.isEmpty())
            return;

        List<PacienteResumenDTO> pacientes = data.stream()
                .map(obj -> PacienteResumenDTO.builder().paciente((String) obj[0])
                        .cantidadAlertas(((Number) obj[1]).longValue())
                        .frecuenciaPromedio(((Number) obj[2]).doubleValue())
                        .frecuenciaMaxima(((Number) obj[3]).doubleValue()).build())
                .toList();

        ResumenDTO resumen = ResumenDTO.builder().fechaResumen(LocalDateTime.now().toString())
                .pacientes(pacientes).build();

        rabbitTemplate.convertAndSend("cola-resumen", resumen);
        System.out.println("Resumen publicado en cola-resumen");
    }
}
