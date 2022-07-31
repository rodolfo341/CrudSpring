package com.github.rodolfo341.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.rodolfo341.entidad.Estudiante;

@Repository
public interface IEstudianteRepositorio extends JpaRepository<Estudiante, Long> {
	
}
