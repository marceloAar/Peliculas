package cl.mar.pelicula.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.mar.pelicula.models.Category;
import cl.mar.pelicula.repositories.CategoryRepository;


@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepo;
	
	public List<Category> mostrarTodas(){
		return (List<Category>) categoryRepo.findAll();
	}	
	
	public List<Category> listaOrdenada(){
		return categoryRepo.ordenaCatDesc();
	}
	
	
	
}
