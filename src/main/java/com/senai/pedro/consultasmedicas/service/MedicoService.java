package com.senai.pedro.consultasmedicas.service;

import com.senai.pedro.consultasmedicas.exception.MedicoException;
import com.senai.pedro.consultasmedicas.model.Medico;
import com.senai.pedro.consultasmedicas.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico save(Medico medico) {
        validarMedico(medico);
        return this.medicoRepository.save(medico);
    }

    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    public Medico update(Medico medico) {
        return this.save(medico);
    }

    public void delete(Integer id) {
        if (!medicoRepository.existsById(id)) {
            throw new MedicoException("Paciente não encontrado para exclusão.");
        }
        this.medicoRepository.deleteById(id);
    }

    // Tratamento de Exceções
    private void validarMedico(Medico medico) {
        if (medico.getNome() == null || medico.getNome().trim().isEmpty()) {
            throw new MedicoException("O nome do medico é obrigatório.");
        }

        if (medico.getEspecialidade() == null || medico.getEspecialidade().trim().isEmpty()) {
            throw new MedicoException("A especialidade do medico é obrigatória.");
        }

        if (medico.getCrm() == null || medico.getCrm().trim().isEmpty()) {
            throw new MedicoException("O crm do medico é obrigatório.");
        }
    }
}