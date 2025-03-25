package com.senai.pedro.consultasmedicas.controller;

import com.senai.pedro.consultasmedicas.model.Medico;
import com.senai.pedro.consultasmedicas.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public List<Medico> getAllMedicos() {
        return medicoService.findAll();
    }

    @PostMapping
    public Medico saveMedico(@RequestBody Medico medico) {
        return medicoService.save(medico);
    }

    @PutMapping
    public Medico updateMedico(@RequestBody Medico medico) {
        return medicoService.update(medico);
    }

    @DeleteMapping("/{id}")
    public void deleteMedico(@PathVariable Integer id) {
        this.medicoService.delete(id);
    }
}
