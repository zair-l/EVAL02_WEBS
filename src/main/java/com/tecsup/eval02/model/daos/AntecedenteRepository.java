package com.tecsup.eval02.model.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tecsup.eval02.model.entities.AntecedenteMedico;
import java.util.List;

public interface AntecedenteRepository extends JpaRepository<AntecedenteMedico, Long> {
    List<AntecedenteMedico> findByHistoriaId(Long historiaId);
}
