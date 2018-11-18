package cinema.backend.service;

import cinema.backend.entities.Film;
import cinema.backend.entities.Show;
import java.util.List;

/**
 *
 * @author CzPet
 */
public interface Service {

  public List<Show> listAllShows();

  public List<Film> listAllFilms();

  public Film getFilm(Long filmId);
  
}
