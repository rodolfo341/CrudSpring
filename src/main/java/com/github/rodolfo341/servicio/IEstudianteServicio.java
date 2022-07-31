package com.github.rodolfo341.servicio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.rodolfo341.entidad.Estudiante;

public interface IEstudianteServicio {
	
//	public List<Estudiante> listarTodosLosEstudiantes();
	
	public Page<Estudiante> encontrarTodos(Pageable pageable);
	
	public Estudiante guardarEstudiante(Estudiante estudiante);
	
	public Estudiante obtenerEstudiantePorId(Long id);
	
	public Estudiante actualizarEstudiante(Estudiante estudiante);
	
	public void eliminarEstudiante(Long id);
	
	
}
