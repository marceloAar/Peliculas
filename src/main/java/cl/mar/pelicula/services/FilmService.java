package cl.mar.pelicula.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.mar.pelicula.models.Film;
import cl.mar.pelicula.repositories.FilmRepository;

@Service
public class FilmService {

	@Autowired
	FilmRepository filmRepo;
	
	public List<Film> mostrarTodas(){
		return (List<Film>) filmRepo.findAll();
	}
	
	public List<Film> filtraTodo(Integer id, String titulo){
		
		if(titulo.isBlank()) {
			return filmRepo.filtraCat(id);
		}if(id==0) {
			return filmRepo.filmFiltrado(titulo);
		}else {
			return filmRepo.filtraAmbas(id, titulo);
		}	
	}
	
		
	//busca una pelicula por su id
    public Film buscaUnaPeli(Integer id) {
        Optional<Film> unaPeli = filmRepo.findById(id);
        
        if(unaPeli.isPresent()) {
            return unaPeli.get();
        } else {
            return null;
        }
    }
    
    //Implementamos el metodo findAll(pageable) desde el repository
  	public Page<Film> listarPagina(int numeroPagina, int cantidadRegistros) {
  		//Recibe el numero de pagina y la cantidad de registros por pagina
  		Pageable pageable = PageRequest.of(numeroPagina - 1 , cantidadRegistros); 
  		return filmRepo.findAll(pageable);
  	}
}
