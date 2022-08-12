package cl.mar.pelicula.models;

import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Category {
	
	@Id
	@Column(columnDefinition = "tinyint")
	private Integer categoryId;
	private String name;
	
	
	@ManyToMany
	@JoinTable(name="film_category", 
		joinColumns=@JoinColumn(name="category_id"), 
		inverseJoinColumns=@JoinColumn(name="film_id"))
	@ToString.Exclude
	private List<Film> listaFilm;
    	
	

}
