package com.tecsup.eval02.service;

import com.tecsup.eval02.model.daos.HistoriaRepository;
import com.tecsup.eval02.model.daos.PacienteRepository;
import com.tecsup.eval02.model.entities.HistoriaClinica;
import com.tecsup.eval02.model.entities.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    @Autowired
    private HistoriaRepository historiaRepo;

    @Autowired
    private PacienteRepository pacienteRepo;

    @Override
    @Transactional
    public HistoriaClinica crearHistoria(HistoriaClinica historia) {
        if (historia == null || historia.getPaciente() == null || historia.getPaciente().getId() == null) {
            throw new IllegalStateException("Debe seleccionar un paciente válido.");
        }

        Optional<Paciente> paciente = pacienteRepo.findById(historia.getPaciente().getId());
        if (paciente.isEmpty()) {
            throw new IllegalStateException("Paciente no encontrado.");
        }

        historia.setPaciente(paciente.get());
        if (historia.getFechaApertura() == null) {
            historia.setFechaApertura(LocalDate.now());
        }

        return historiaRepo.save(historia);
    }

    @Override
    public List<HistoriaClinica> listarHistorias() {
        return historiaRepo.findAll();
    }

    @Override
    public HistoriaClinica obtenerHistoria(Long id) {
        return historiaRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public HistoriaClinica actualizarHistoria(Long id, HistoriaClinica datos) {
        HistoriaClinica h = obtenerHistoria(id);
        if (h == null) return null;

        h.setFechaApertura(datos.getFechaApertura());
        h.setObservaciones(datos.getObservaciones());
        return historiaRepo.save(h);
    }

    @Override
    @Transactional
    public void eliminarHistoria(Long id) {
        historiaRepo.deleteById(id);
    }

    public List<HistoriaClinica> listarPorPaciente(Long idPaciente) {
        return historiaRepo.findByPacienteId(idPaciente);
    }
}