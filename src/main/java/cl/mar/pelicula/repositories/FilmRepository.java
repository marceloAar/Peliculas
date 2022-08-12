package cl.mar.pelicula.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.mar.pelicula.models.Film;

@Repository
public interface FilmRepository extends CrudRepository<Film, Integer>{
	
	@Query(value="SELECT * FROM film WHERE title LIKE %?1%", nativeQuery = true)
	List<Film> filmFiltrado(String titulo);
	
	@Query(value ="SELECT * FROM film join film_category on film.film_id=film_category.film_id where film_category.category_id =?1", nativeQuery = true)
	List<Film> filtraCat(Integer id);
	
	@Query(value="SELECT * FROM film join film_category on film.film_id=film_category.film_id where film_category.category_id =?1 and film.title like %?2%", nativeQuery = true)
	List<Film> filtraAmbas(Integer id, String titulo);
	
	//Generamos metodo para obtener una "pagina" en que el parametro Pageable define su configuracion
	Page<Film> findAll(Pageable pageable);

}
