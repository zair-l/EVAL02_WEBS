package com.tecsup.eval02.controller;

import com.tecsup.eval02.model.entities.Paciente;
import com.tecsup.eval02.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pacientes")
public class PacienteWebController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        return "listPacientes"; // vista HTML
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "formPaciente";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("paciente") Paciente paciente) {
        pacienteService.crearPaciente(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteService.obtenerPaciente(id);
        if (paciente == null) {
            return "redirect:/pacientes";
        }
        model.addAttribute("paciente", paciente);
        return "formPaciente";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute("paciente") Paciente paciente) {
        pacienteService.actualizarPaciente(id, paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/desactivar/{id}")
    public String desactivar(@PathVariable Long id) {
        pacienteService.desactivarPaciente(id);
        return "redirect:/pacientes";
    }
}