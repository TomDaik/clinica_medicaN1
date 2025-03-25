package com.senai.pedro.consultasmedicas.service;

import com.senai.pedro.consultasmedicas.exception.PacienteException;
import com.senai.pedro.consultasmedicas.model.Paciente;
import com.senai.pedro.consultasmedicas.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente save(Paciente paciente){
        validarPaciente(paciente);
        return this.pacienteRepository.save(paciente);
    }

    public List<Paciente> findAll(){
        return pacienteRepository.findAll();
    }

    public Paciente update(Paciente paciente){
        return this.save(paciente);
    }

    public void delete(Integer id){
        if (!pacienteRepository.existsById(id)) {
            throw new PacienteException("Paciente não encontrado para exclusão.");
        }
        this.pacienteRepository.deleteById(id);
    }

    // Tratamento de Exceções
    private void validarPaciente(Paciente paciente) {
        if (paciente.getNome() == null || paciente.getNome().trim().isEmpty()) {
            throw new PacienteException("O nome do paciente é obrigatório.");
        }

        if (paciente.getCpf() == null || paciente.getCpf().trim().isEmpty()) {
            throw new PacienteException("O CPF do paciente é obrigatório.");
        }

        if (paciente.getCpf().length() != 11 || !paciente.getCpf().matches("\\d+")) {
            throw new PacienteException("O CPF deve conter 11 dígitos numéricos.");
        }

        if (paciente.getDataNascimento().isAfter(LocalDate.now())) {
            throw new PacienteException("Data de nascimento não pode ser no futuro.");
        }

        if (paciente.getTelefone() == null || paciente.getTelefone().trim().isEmpty()) {
            throw new PacienteException("O Telefone do paciente é obrigatório.");
        }
    }

}
