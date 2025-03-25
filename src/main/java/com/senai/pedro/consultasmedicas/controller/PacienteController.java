package com.senai.pedro.consultasmedicas.controller;

import com.senai.pedro.consultasmedicas.model.Paciente;
import com.senai.pedro.consultasmedicas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteService.findAll();
    }

    @PostMapping
    public Paciente savePaciente(@RequestBody Paciente paciente) {
        return pacienteService.save(paciente);
    }

    @PutMapping
    public Paciente updatePaciente(@RequestBody Paciente paciente) {
        return pacienteService.update(paciente);
    }

    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable Integer id) {
        this.pacienteService.delete(id);
    }
}
