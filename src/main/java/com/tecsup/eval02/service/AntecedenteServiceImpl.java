package com.tecsup.eval02.service;

import com.tecsup.eval02.model.daos.AntecedenteRepository;
import com.tecsup.eval02.model.daos.HistoriaRepository;
import com.tecsup.eval02.model.entities.AntecedenteMedico;
import com.tecsup.eval02.model.entities.HistoriaClinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AntecedenteServiceImpl implements AntecedenteService {

    @Autowired
    private AntecedenteRepository antecedenteRepo;

    @Autowired
    private HistoriaRepository historiaRepo;

    @Override
    @Transactional
    public AntecedenteMedico crearAntecedente(AntecedenteMedico antecedente, Long idHistoria) {
        if (antecedente == null || idHistoria == null) return null;

        HistoriaClinica historia = historiaRepo.findById(idHistoria).orElse(null);
        if (historia == null) return null;

        antecedente.setHistoria(historia);
        return antecedenteRepo.save(antecedente);
    }

    @Override
    public List<AntecedenteMedico> listarAntecedentes() {
        return antecedenteRepo.findAll();
    }

    @Override
    public AntecedenteMedico obtenerAntecedente(Long id) {
        Optional<AntecedenteMedico> opt = antecedenteRepo.findById(id);
        return opt.orElse(null);
    }

    @Override
    @Transactional
    public AntecedenteMedico actualizarAntecedente(Long id, AntecedenteMedico datos) {
        AntecedenteMedico a = obtenerAntecedente(id);
        if (a == null) return null;

        a.setTipo(datos.getTipo());
        a.setDescripcion(datos.getDescripcion());
        // si se desea permitir cambiar historia, habría que validar y asignar aquí
        return antecedenteRepo.save(a);
    }

    @Override
    @Transactional
    public void eliminarAntecedente(Long id) {
        antecedenteRepo.deleteById(id);
    }
}