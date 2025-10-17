package com.tecsup.eval02.controller;

import com.tecsup.eval02.model.entities.AntecedenteMedico;
import com.tecsup.eval02.model.entities.HistoriaClinica;
import com.tecsup.eval02.service.AntecedenteService;
import com.tecsup.eval02.service.HistoriaClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/antecedentes")
public class AntecedenteWebController {

    @Autowired
    private AntecedenteService antecedenteService;

    @Autowired
    private HistoriaClinicaService historiaService;

    @GetMapping
    public String listarAntecedentes(Model model) {
        model.addAttribute("antecedentes", antecedenteService.listarAntecedentes());
        return "listAntecedentes";
    }

    @GetMapping("/nuevo")
    public String nuevoAntecedente(Model model) {
        model.addAttribute("antecedente", new AntecedenteMedico());
        model.addAttribute("historias", historiaService.listarHistorias());
        return "formAntecedente";
    }

    @PostMapping("/guardar")
    public String guardarAntecedente(@ModelAttribute("antecedente") AntecedenteMedico antecedente,
                                     @RequestParam(value = "historiaId", required = false) Long historiaId) {
        // Si el formulario envía la historia por su id, la usamos; si no, tomará la que venga en el objeto
        Long id = (historiaId != null) ? historiaId :
                (antecedente.getHistoria() != null ? antecedente.getHistoria().getId() : null);

        if (id == null) {
            // no se proporcionó historia -> redirigir al formulario con mensaje (puedes mejorar con flash)
            return "redirect:/antecedentes/nuevo";
        }

        antecedenteService.crearAntecedente(antecedente, id);
        return "redirect:/antecedentes";
    }

    @GetMapping("/editar/{id}")
    public String editarAntecedente(@PathVariable Long id, Model model) {
        AntecedenteMedico antecedente = antecedenteService.obtenerAntecedente(id);
        if (antecedente == null) return "redirect:/antecedentes";

        model.addAttribute("antecedente", antecedente);
        model.addAttribute("historias", historiaService.listarHistorias());
        return "formAntecedente";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarAntecedente(@PathVariable Long id, @ModelAttribute("antecedente") AntecedenteMedico antecedente) {
        antecedenteService.actualizarAntecedente(id, antecedente);
        return "redirect:/antecedentes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAntecedente(@PathVariable Long id) {
        antecedenteService.eliminarAntecedente(id);
        return "redirect:/antecedentes";
    }
}