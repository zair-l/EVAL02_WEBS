package com.tecsup.eval02.service;


import com.tecsup.eval02.model.entities.HistoriaClinica;
import java.util.List;

public interface HistoriaClinicaService {

    HistoriaClinica crearHistoria(HistoriaClinica historia);
    List<HistoriaClinica> listarHistorias();
    HistoriaClinica obtenerHistoria(Long id);
    HistoriaClinica actualizarHistoria(Long id, HistoriaClinica datos);
    void eliminarHistoria(Long id);
}
