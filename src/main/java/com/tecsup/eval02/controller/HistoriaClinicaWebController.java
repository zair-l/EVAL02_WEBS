package com.tecsup.eval02.controller;

import com.tecsup.eval02.model.entities.HistoriaClinica;
import com.tecsup.eval02.model.daos.PacienteRepository;
import com.tecsup.eval02.service.HistoriaClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/historias")
public class HistoriaClinicaWebController {

    @Autowired
    private HistoriaClinicaService historiaService;

    @Autowired
    private PacienteRepository pacienteRepo;

    @GetMapping
    public String listarHistorias(Model model) {
        model.addAttribute("historias", historiaService.listarHistorias());
        return "listHistoriasClinicas";
    }

    @GetMapping("/nuevo")
    public String nuevaHistoria(Model model) {
        model.addAttribute("historia", new HistoriaClinica());
        model.addAttribute("pacientes", pacienteRepo.findAll());
        return "formHistoriaClinica";
    }

    @PostMapping("/guardar")
    public String guardarHistoria(@ModelAttribute("historia") HistoriaClinica historia, Model model) {
        try {
            historiaService.crearHistoria(historia);
            return "redirect:/historias";
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("pacientes", pacienteRepo.findAll());
            return "formHistoriaClinica";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarHistoria(@PathVariable Long id, Model model) {
        HistoriaClinica historia = historiaService.obtenerHistoria(id);
        if (historia == null) return "redirect:/historias";

        model.addAttribute("historia", historia);
        model.addAttribute("pacientes", pacienteRepo.findAll());
        return "formHistoriaClinica";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarHistoria(@PathVariable Long id, @ModelAttribute("historia") HistoriaClinica historia) {
        historiaService.actualizarHistoria(id, historia);
        return "redirect:/historias";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarHistoria(@PathVariable Long id) {
        historiaService.eliminarHistoria(id);
        return "redirect:/historias";
    }
}