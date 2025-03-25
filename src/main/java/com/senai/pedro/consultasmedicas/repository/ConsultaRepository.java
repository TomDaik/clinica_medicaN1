package com.senai.pedro.consultasmedicas.repository;

import com.senai.pedro.consultasmedicas.model.Consulta;
import com.senai.pedro.consultasmedicas.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer>{
    boolean existsByPacienteAndDataConsultaAndHorarioConsulta(Paciente paciente, LocalDate dataConsulta, LocalTime horarioConsulta);
}
