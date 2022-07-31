package com.github.rodolfo341.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.rodolfo341.entidad.Estudiante;
import com.github.rodolfo341.repositorio.IEstudianteRepositorio;

@Service
public class EstudianteServicioEmpl implements IEstudianteServicio {

	@Autowired 
	private IEstudianteRepositorio repositorio;
	
//	@Override
//	public List<Estudiante> listarTodosLosEstudiantes() {
//		return repositorio.findAll();
//	}

	@Override
	public Estudiante guardarEstudiante(Estudiante estudiante) {
		return repositorio.save(estudiante);
	}

	@Override
	public Estudiante obtenerEstudiantePorId(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public Estudiante actualizarEstudiante(Estudiante estudiante) {
		return repositorio.save(estudiante);
	}

	@Override
	public void eliminarEstudiante(Long id) {
		repositorio.deleteById(id);
	}

	@Override
	public Page<Estudiante> encontrarTodos(Pageable pageable) {
		return repositorio.findAll(pageable);
	}

}
