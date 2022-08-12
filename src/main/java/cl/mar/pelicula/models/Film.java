package cl.mar.pelicula.models;

import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Film {
	
	@Id
	@Column(columnDefinition = "smallint")
	private Integer filmId;
	private String title;
	@Column(columnDefinition = "TEXT")
	private String description;
	@Column(columnDefinition = "year")
	private int releaseYear; 
	@Column(columnDefinition = "tinyint")
	private short languageId;
	@Column(columnDefinition = "decimal")
	private double rentalRate; 
	private short length; 
	@Column(columnDefinition = "decimal")
	private double replacementCost; 
	@Column(columnDefinition = "enum")
	private String rating;

	@ManyToMany
	@JoinTable(name="film_category",
		joinColumns=@JoinColumn(name="film_id"),
		inverseJoinColumns=@JoinColumn(name="category_id"))
	//@ToString.Exclude
	private List<Category> listaCategoria;
	 	
	
		
}