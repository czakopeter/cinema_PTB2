package cinema.backend.service;

import cinema.backend.entities.Film;
import java.util.List;

/**
 *
 * @author CzPet
 */
public interface Service {

  public List<Film> listAllFilms();

  public Film getFilm(Long filmId);
  
}
