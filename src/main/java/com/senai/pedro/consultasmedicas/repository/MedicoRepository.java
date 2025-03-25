package com.senai.pedro.consultasmedicas.repository;

import com.senai.pedro.consultasmedicas.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    @Query("SELECT mdc FROM Medico mdc WHERE mdc.crm = :crm")
    Optional<Medico> findByCrm(String crm);

    @Query("SELECT mdc FROM Medico mdc WHERE mdc.especialidade = :especialidade")
    List<Medico> findByEspecialidade(String especialidade);
}