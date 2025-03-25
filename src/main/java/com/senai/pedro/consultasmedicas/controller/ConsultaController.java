package com.senai.pedro.consultasmedicas.controller;

import com.senai.pedro.consultasmedicas.model.Consulta;
import com.senai.pedro.consultasmedicas.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/agendar")
    public void agendarConsulta(@RequestBody Consulta consulta) {
        consultaService.agendarConsulta(consulta);
    }

    @PutMapping("/{id}/cancelar")
    public void cancelarConsulta(@PathVariable Integer id) {
        consultaService.cancelarConsulta(id);
    }

    @PutMapping("/{id}/concluir")
    public void concluirConsulta(@PathVariable Integer id) {
        consultaService.concluirConsulta(id);
    }

    @GetMapping("/{id}")
    public Consulta getConsultaById(@PathVariable Integer id) {
        return consultaService.findById(id);
    }

}