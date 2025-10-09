package com.tecsup.eval02.service;

import com.tecsup.eval02.model.entities.Paciente;
import com.tecsup.eval02.model.entities.AntecedenteMedico;
import java.util.List;

public interface PacienteService {

    Paciente crearPaciente(Paciente paciente);
    List<Paciente> listarPacientes();
    Paciente obtenerPaciente(Long id);
    Paciente actualizarPaciente(Long id, Paciente datos);
    void desactivarPaciente(Long id);

    List<AntecedenteMedico> listarAntecedentes(Long pacienteId);
    AntecedenteMedico agregarAntecedente(Long pacienteId, AntecedenteMedico antecedente);
}
