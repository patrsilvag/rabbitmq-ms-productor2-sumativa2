package com.example.rabbitmqproductor2.scheduler;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.rabbitmqproductor2.dto.PacienteResumenDTO;
import com.example.rabbitmqproductor2.dto.ResumenDTO;
import com.example.rabbitmqproductor2.models.CtrlProcesos;
import com.example.rabbitmqproductor2.repositories.AlertaRepository;
import com.example.rabbitmqproductor2.repositories.CtrlProcesosRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductorScheduler {

    private final AlertaRepository alertaRepository;
    private final CtrlProcesosRepository ctrlProcesosRepository;
    private final RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "0 */5 * * * *") // cada 5 minutos
   // @Scheduled(cron = "*/120 * * * * *") // cada 120 segundos
    public void enviarResumen() {

        CtrlProcesos proceso = ctrlProcesosRepository.findById("RESUMEN_ALERTAS").orElseThrow(
                () -> new RuntimeException("No existe RESUMEN_ALERTAS en CONTROL_PROCESOS"));

        Timestamp ultimaEjecucion = proceso.getUltimaEjecucion();

        // Hora hasta donde se procesará esta ejecución
        Timestamp fechaProceso = new Timestamp(System.currentTimeMillis());

        List<Object[]> data = alertaRepository.obtenerResumenAlertas(ultimaEjecucion, fechaProceso);

        System.out.println("Última ejecución: " + ultimaEjecucion);
        System.out.println("Fecha proceso: " + fechaProceso);
        System.out.println("Cantidad registros: " + data.size());

        for (Object[] fila : data) {
            System.out.println(fila[0] + " | " + fila[1] + " | " + fila[2] + " | " + fila[3]);
        }

        if (data == null || data.isEmpty()) {
            proceso.setUltimaEjecucion(fechaProceso);
            ctrlProcesosRepository.save(proceso);
            return;
        }

        List<PacienteResumenDTO> pacientes = data.stream()
                .map(obj -> PacienteResumenDTO.builder().paciente((String) obj[0])
                        .cantidadAlertas(((Number) obj[1]).longValue())
                        .frecuenciaPromedio(((Number) obj[2]).doubleValue())
                        .frecuenciaMaxima(((Number) obj[3]).doubleValue()).build())
                .toList();

        ResumenDTO resumen = ResumenDTO.builder().fechaResumen(LocalDateTime.now().toString())
                .pacientes(pacientes).build();

        rabbitTemplate.convertAndSend("","cola-resumen", resumen);

        proceso.setUltimaEjecucion(fechaProceso);
        ctrlProcesosRepository.save(proceso);

        System.out.println("Resumen publicado en cola-resumen");
    }
}
