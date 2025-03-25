package com.senai.pedro.consultasmedicas.repository;

import com.senai.pedro.consultasmedicas.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    @Query("SELECT p FROM Paciente p WHERE p.cpf = :cpf")
    Optional<Paciente> findByCpf(String cpf);
}
