package com.tecsup.eval02.model.daos;

import com.tecsup.eval02.model.entities.AntecedenteMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AntecedenteRepository extends JpaRepository<AntecedenteMedico, Long> {
    List<AntecedenteMedico> findByHistoriaId(Long historiaId);
}