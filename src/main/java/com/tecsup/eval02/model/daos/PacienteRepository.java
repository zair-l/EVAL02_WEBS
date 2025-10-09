package com.tecsup.eval02.model.daos;


import org.springframework.data.jpa.repository.JpaRepository;
import com.tecsup.eval02.model.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}

