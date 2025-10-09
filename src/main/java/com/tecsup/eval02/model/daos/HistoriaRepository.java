package com.tecsup.eval02.model.daos;


import org.springframework.data.jpa.repository.JpaRepository;
import com.tecsup.eval02.model.entities.HistoriaClinica;

public interface HistoriaRepository extends JpaRepository<HistoriaClinica, Long> {
    HistoriaClinica findByPacienteId(Long pacienteId);
}
