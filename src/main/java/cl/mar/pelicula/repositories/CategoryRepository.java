package cl.mar.pelicula.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.mar.pelicula.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>{
	
	@Query(value="SELECT * FROM category order by name DESC", nativeQuery = true)
	List<Category> ordenaCatDesc();

}
