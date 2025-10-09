package com.tecsup.eval02.controller;

import com.tecsup.eval02.model.entities.*;
import com.tecsup.eval02.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> crear(@Valid @RequestBody Paciente paciente) {
        Paciente creado = pacienteService.crearPaciente(paciente);
        return ResponseEntity.created(URI.create("/api/pacientes/" + creado.getId())).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        Paciente p = pacienteService.obtenerPaciente(id);
        if (p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Paciente datos) {
        Paciente actualizado = pacienteService.actualizarPaciente(id, datos);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        pacienteService.desactivarPaciente(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/antecedentes")
    public ResponseEntity<?> agregarAntecedente(@PathVariable Long id, @Valid @RequestBody AntecedenteMedico antecedente) {
        AntecedenteMedico a = pacienteService.agregarAntecedente(id, antecedente);
        if (a == null) return ResponseEntity.notFound().build();
        return ResponseEntity.created(URI.create("/api/pacientes/" + id + "/antecedentes/" + a.getId())).body(a);
    }

    @GetMapping("/{id}/antecedentes")
    public ResponseEntity<?> listarAntecedentes(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.listarAntecedentes(id));
    }

    @GetMapping("/{id}/historia")
    public ResponseEntity<?> obtenerHistoria(@PathVariable Long id) {
        HistoriaClinica h = pacienteService.listarAntecedentes(id).isEmpty()
                ? null
                : pacienteService.listarAntecedentes(id).stream().findFirst()
                .map(a -> a.getHistoria())
                .orElse(null);

        return ResponseEntity.ok().build();
    }
}
