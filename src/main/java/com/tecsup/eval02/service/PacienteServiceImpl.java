package com.tecsup.eval02.service;

import com.tecsup.eval02.model.daos.AntecedenteRepository;
import com.tecsup.eval02.model.daos.HistoriaRepository;
import com.tecsup.eval02.model.daos.PacienteRepository;
import com.tecsup.eval02.model.entities.AntecedenteMedico;
import com.tecsup.eval02.model.entities.HistoriaClinica;
import com.tecsup.eval02.model.entities.Paciente;
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
        historia.setObservaciones("Historia clínica inicial creada automáticamente.");
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
        Paciente paciente = pacienteRepo.findById(id).orElse(null);
        if (paciente != null) {
            paciente.setEstado("INACTIVO");
            pacienteRepo.save(paciente);
        }
    }


    @Override
    public List<AntecedenteMedico> listarAntecedentes(Long pacienteId) {
        List<HistoriaClinica> historias = historiaRepo.findByPacienteId(pacienteId);
        if (historias.isEmpty()) return List.of();

        HistoriaClinica historia = historias.get(0);
        return antecedenteRepo.findByHistoriaId(historia.getId());
    }


    @Override
    @Transactional
    public AntecedenteMedico agregarAntecedente(Long pacienteId, AntecedenteMedico antecedente) {
        List<HistoriaClinica> historias = historiaRepo.findByPacienteId(pacienteId);
        if (historias.isEmpty()) return null;

        HistoriaClinica historia = historias.get(historias.size() - 1);
        antecedente.setHistoria(historia);
        return antecedenteRepo.save(antecedente);
    }
}