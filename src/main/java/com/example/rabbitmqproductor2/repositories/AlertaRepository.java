package com.example.rabbitmqproductor2.repositories;

import com.example.rabbitmqproductor2.models.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    @Query(value = """
            SELECT
                NOMBRE_PACIENTE,
                COUNT(*) cantidad,
                AVG(TO_NUMBER(REGEXP_SUBSTR(SIGNOS_VITALES, '\\d+'))) promedio,
                MAX(TO_NUMBER(REGEXP_SUBSTR(SIGNOS_VITALES, '\\d+'))) maximo
            FROM FULLSTACK.ALERTAS_VITALES
            WHERE FECHA_HORA_REGISTRO > :ultimaEjecucion
              AND FECHA_HORA_REGISTRO <= :fechaProceso
            GROUP BY NOMBRE_PACIENTE
            """, nativeQuery = true)
    List<Object[]> obtenerResumenAlertas(@Param("ultimaEjecucion") Timestamp ultimaEjecucion,
            @Param("fechaProceso") Timestamp fechaProceso);
}
