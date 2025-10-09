package com.tecsup.eval02.service;

import com.tecsup.eval02.model.daos.*;
import com.tecsup.eval02.model.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepo;

    @Autowired
    private HistoriaRepository historiaRepo;

    @Autowired
    private AntecedenteRepository antecedenteRepo;

    @Override
    @Transactional
    public Paciente crearPaciente(Paciente paciente) {
        Paciente p = pacienteRepo.save(paciente);

        HistoriaClinica historia = new HistoriaClinica();
        historia.setPaciente(p);
        historia.setFechaApertura(LocalDate.now());
        historia.setObservaciones("Historia creada automáticamente");
        historiaRepo.save(historia);

        return p;
    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteRepo.findAll();
    }

    @Override
    public Paciente obtenerPaciente(Long id) {
        Optional<Paciente> opt = pacienteRepo.findById(id);
        return opt.orElse(null);
    }

    @Override
    @Transactional
    public Paciente actualizarPaciente(Long id, Paciente datos) {
        Paciente p = obtenerPaciente(id);
        if (p == null) return null;

        p.setDni(datos.getDni());
        p.setNombres(datos.getNombres());
        p.setApellidos(datos.getApellidos());
        p.setFechaNacimiento(datos.getFechaNacimiento());
        p.setSexo(datos.getSexo());
        p.setDireccion(datos.getDireccion());
        p.setTelefono(datos.getTelefono());
        p.setCorreo(datos.getCorreo());
        return pacienteRepo.save(p);
    }

    @Override
    @Transactional
    public void desactivarPaciente(Long id) {
        Paciente p = obtenerPaciente(id);
        if (p != null) {
            p.setEstado("INACTIVO");
            pacienteRepo.save(p);
        }
    }

    @Override
    public List<AntecedenteMedico> listarAntecedentes(Long pacienteId) {
        HistoriaClinica h = historiaRepo.findByPacienteId(pacienteId);
        if (h == null) return List.of();
        return antecedenteRepo.findByHistoriaId(h.getId());
    }

    @Override
    @Transactional
    public AntecedenteMedico agregarAntecedente(Long pacienteId, AntecedenteMedico antecedente) {
        HistoriaClinica h = historiaRepo.findByPacienteId(pacienteId);
        if (h == null) return null;
        antecedente.setHistoria(h);
        return antecedenteRepo.save(antecedente);
    }
}
