package com.senai.pedro.consultasmedicas.service;

import com.senai.pedro.consultasmedicas.exception.ConsultaException;
import com.senai.pedro.consultasmedicas.model.Consulta;
import com.senai.pedro.consultasmedicas.model.StatusConsulta;
import com.senai.pedro.consultasmedicas.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta findById(int id) {
        return consultaRepository.findById(id).orElseThrow(() -> new ConsultaException("Consulta não encontrada com ID: " + id));
    }

    public void agendarConsulta(Consulta consulta) {
        validarDadosConsulta(consulta);

        if (consultaRepository.existsByPacienteAndDataConsultaAndHorarioConsulta(consulta.getPaciente(), consulta.getDataConsulta(), consulta.getHorarioConsulta())) {
            throw new ConsultaException("O paciente já tem uma consulta agendada para esse horário!");
        }

        consulta.setStatus(StatusConsulta.AGENDADA);
        consultaRepository.save(consulta);
    }

    public void cancelarConsulta(Integer id) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> new ConsultaException("Consulta não encontrada"));

        if (consulta.getStatus() == StatusConsulta.REALIZADA) {
            throw new ConsultaException("Não é possível cancelar uma consulta já realizada.");
        }

        consulta.setStatus(StatusConsulta.CANCELADA);
        consultaRepository.save(consulta);
    }

    public void concluirConsulta(Integer id) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> new ConsultaException("Consulta não encontrada"));

        if (consulta.getStatus() == StatusConsulta.CANCELADA) {
            throw new ConsultaException("Não é possível concluir uma consulta cancelada.");
        }

        if (consulta.getDataConsulta().isAfter(LocalDate.now()) ||
                (consulta.getDataConsulta().isEqual(LocalDate.now()) && consulta.getHorarioConsulta().isAfter(LocalTime.now()))) {
            throw new ConsultaException("Não é possível concluir uma consulta antes do horário.");
        }

        consulta.setStatus(StatusConsulta.REALIZADA);
        consultaRepository.save(consulta);
    }

    // Tratamento de Exceções
    private void validarDadosConsulta(Consulta consulta) {
        if (consulta.getPaciente() == null) {
            throw new ConsultaException("O paciente é obrigatório.");
        }

        if (consulta.getMedico() == null) {
            throw new ConsultaException("O médico é obrigatório.");
        }

        if (consulta.getDataConsulta() == null || consulta.getHorarioConsulta() == null) {
            throw new ConsultaException("A data e o horário da consulta são obrigatórios.");
        }

        if (consulta.getDataConsulta().isBefore(LocalDate.now())) {
            throw new ConsultaException("A consulta não pode ser agendada para o passado.");
        }
    }

}