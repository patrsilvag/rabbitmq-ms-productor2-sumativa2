package com.example.rabbitmqproductor2.repositories;

import com.example.rabbitmqproductor2.models.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    // Usamos nombres de columna reales de tu base de datos
    @Query(value = "SELECT NOMBRE_PACIENTE, COUNT(*) as cantidad, AVG(TO_NUMBER(REGEXP_SUBSTR(SIGNOS_VITALES, '\\d+'))) as promedio, MAX(TO_NUMBER(REGEXP_SUBSTR(SIGNOS_VITALES, '\\d+'))) as maximo "
            + "FROM FULLSTACK.ALERTAS_VITALES " + "WHERE FECHA_HORA_REGISTRO >= SYSDATE - 1 "
            + "GROUP BY NOMBRE_PACIENTE", nativeQuery = true)
    List<Object[]> obtenerResumenAlertas();
}
