package cl.mar.pelicula.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.mar.pelicula.models.Film;
import cl.mar.pelicula.services.CategoryService;
import cl.mar.pelicula.services.FilmService;

@Controller
@RequestMapping("/pelis")
public class PeliculaController {

	@Autowired
	FilmService peliculaService;
	
	@Autowired
	CategoryService categoryService;		
	
	//Recibimos por url el numero de pagina
	@GetMapping("/busca/{pagina}")
	public String pelis(@PathVariable(value="pagina") int pagina, Model model) {
	
		Page<Film> pelisPaginadas = peliculaService.listarPagina(pagina, 20);  //Guardamos pagina y registros por pagina
		List<Film> listaPeliculas = pelisPaginadas.getContent(); //pasamos la configuracion del Page a un List
		model.addAttribute("peliculas", listaPeliculas);
		
		model.addAttribute("paginaActual", pagina); //Enviamos el numero de pagina para utilizarlo en el html
		model.addAttribute("totalPaginas", pelisPaginadas.getTotalPages()); //metodo envia el total de paginas al html
		model.addAttribute("categorias", categoryService.mostrarTodas());
		return "inicio";
	}
		
	//Redirigimos a la url de inicio con valor de pagina 1
	@GetMapping("/busca") 
	public String home(Model model) {		
		return pelis(1, model);
	}		
		
	
	//filtra las peliculas por su categoria
	@PostMapping("/busca/filtrado")
	public String filtraCatTitulo(@RequestParam(value="idCat") Integer id, String titulo, Model model) {		
		//Page<Film> pelisPaginadas = peliculaService.listarPagina(1, 20); 				
		//model.addAttribute("totalPaginas", pelisPaginadas.getTotalPages());
		model.addAttribute("totalPaginas", 1);
		
		model.addAttribute("peliculas", peliculaService.filtraTodo(id, titulo));
		model.addAttribute("categorias", categoryService.mostrarTodas());	
		
		
		return "inicio";
	}	
	
	//muestra informacion de una pelicula
	@GetMapping("/busca/verPeli/{id}")
	public String infoPeli(@PathVariable Integer id, Model model) {    	
    	model.addAttribute("pelicula", peliculaService.buscaUnaPeli(id));
		return "show";
	}
		
}
