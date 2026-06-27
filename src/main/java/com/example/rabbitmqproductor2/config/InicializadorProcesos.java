package com.example.rabbitmqproductor2.config;

import com.example.rabbitmqproductor2.models.CtrlProcesos;
import com.example.rabbitmqproductor2.repositories.CtrlProcesosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class InicializadorProcesos implements CommandLineRunner {

    private final CtrlProcesosRepository ctrlProcesosRepository;

    @Override
    public void run(String... args) {

        if (!ctrlProcesosRepository.existsById("RESUMEN_ALERTAS")) {

            CtrlProcesos proceso = new CtrlProcesos();
            proceso.setNombreProceso("RESUMEN_ALERTAS");
            proceso.setUltimaEjecucion(new Timestamp(System.currentTimeMillis()));

            ctrlProcesosRepository.save(proceso);

            System.out.println("Registro RESUMEN_ALERTAS creado.");
        }
    }
}
