package com.tecsup.eval02.service;

import com.tecsup.eval02.model.entities.AntecedenteMedico;
import java.util.List;

public interface AntecedenteService {

    AntecedenteMedico crearAntecedente(AntecedenteMedico antecedente, Long idHistoria);
    List<AntecedenteMedico> listarAntecedentes();
    AntecedenteMedico obtenerAntecedente(Long id);
    AntecedenteMedico actualizarAntecedente(Long id, AntecedenteMedico datos);
    void eliminarAntecedente(Long id);
}
