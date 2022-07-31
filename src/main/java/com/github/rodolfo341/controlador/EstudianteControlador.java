package com.github.rodolfo341.controlador;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.rodolfo341.entidad.Estudiante;
import com.github.rodolfo341.servicio.IEstudianteServicio;

@Controller
public class EstudianteControlador {
	
	@Autowired 
	private IEstudianteServicio servicio;
	
	@GetMapping({"/","/estudiantes"})
	public String encontrarTodos(@RequestParam Map<String, Object> params, Model modelo) {
		int page = params.get("page") != null ? Integer.valueOf(params.get("page").toString()) - 1  : 0 ;
		PageRequest pr =PageRequest.of(page, 5);
		
		Page<Estudiante> pageEstudiante = servicio.encontrarTodos(pr);
		
		int totalPage = pageEstudiante.getTotalPages();
		
		if( totalPage > 2 ) {
			List<Integer>pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			modelo.addAttribute("pages", pages);
		}
		
		modelo.addAttribute("listaDeEstudiantes", pageEstudiante.getContent());
		modelo.addAttribute("paginaActual", page + 1 );
		modelo.addAttribute("paginaSiguiente", page + 2 );
		modelo.addAttribute("paginaAnterior", page);
		modelo.addAttribute("ultimaPagina", totalPage);
		
		return "/estudiantes";
	}
	
	
//	@GetMapping({"/","/estudiantes"})
//	public String listarEstudiantes(Model modelo) {
//		modelo.addAttribute("listaDeEstudiantes",servicio.listarTodosLosEstudiantes());
//		return "/estudiantes";
//	}
	
	@GetMapping("/estudiantes/nuevo")
	public String mostrarFormularioDeRegistroEstudiante(Model modelo) {
		Estudiante estudienate = new Estudiante();
		modelo.addAttribute("estudiante",estudienate);
		return "crear_estudiante";
	}
	
	@PostMapping("/estudiantes")
	public String guardarEstudiante(@ModelAttribute("estudiante") Estudiante estudiante) {
		servicio.guardarEstudiante(estudiante);
		return "redirect:/estudiantes";
	}
	
	@GetMapping("/estudiantes/editar/{id}")
	public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("estudiante", servicio.obtenerEstudiantePorId(id));
		return "editar_estudiante";
	}
	
	@PostMapping("/estudiantes/{id}")
	public String actualizarEstudiante(@PathVariable Long id, @ModelAttribute("estudiante") Estudiante estudiante, Model modelo) {
		Estudiante estudianteExistente = servicio.obtenerEstudiantePorId(id);
		estudianteExistente.setId(id);
		estudianteExistente.setNombre(estudiante.getNombre());
		estudianteExistente.setApellido(estudiante.getApellido());
		estudianteExistente.setEmail(estudiante.getEmail());
		servicio.actualizarEstudiante(estudianteExistente);		
		return "redirect:/estudiantes";
	}
	
	@GetMapping("/estudiantes/{id}")
	public String eliminarEstudianter(@PathVariable Long id) {
		servicio.eliminarEstudiante(id);
		return "redirect:/estudiantes";
	}	
	
}
