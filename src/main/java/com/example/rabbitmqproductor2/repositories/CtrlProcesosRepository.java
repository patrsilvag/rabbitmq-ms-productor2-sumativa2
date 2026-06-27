package com.example.rabbitmqproductor2.repositories;

import com.example.rabbitmqproductor2.models.CtrlProcesos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CtrlProcesosRepository extends JpaRepository<CtrlProcesos, String> {
}
