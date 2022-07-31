package com.github.rodolfo341;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.rodolfo341.entidad.Estudiante;
import com.github.rodolfo341.repositorio.IEstudianteRepositorio;

@SpringBootApplication
public class Crud1Application  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Crud1Application.class, args);
	}

	@Autowired
	private IEstudianteRepositorio repositorio;
	
	@Override
	public void run(String... args) throws Exception {
		Estudiante estudiante1 = new Estudiante("Rodolfo", "Aravena", "Rodolfo@mail");
		repositorio.save(estudiante1);
		
		Estudiante estudiante2 = new Estudiante("Diere", "Aravena", "Diereo@mail");
		repositorio.save(estudiante2);
		
	}

}
