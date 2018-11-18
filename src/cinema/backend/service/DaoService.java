package cinema.backend.service;

import cinema.backend.dao.DaoManager;
import cinema.backend.entities.Film;
import cinema.backend.entities.Room;
import cinema.backend.entities.Show;
import java.util.List;

/**
 *
 * @author CzPet
 */
public class DaoService implements Service {
  
  private DaoManager dm = new DaoManager();

  @Override
  public List<Film> listAllFilms() {
    return dm.listAllFilms();
  }

  @Override
  public Film getFilm(Long filmId) {
    return dm.getFilm(filmId);
  }

  @Override
  public List<Show> listAllShows() {
    return dm.listAllShows();
  }

  @Override
  public List<Room> listAllRooms() {
    return dm.listAllRooms();
  }
}
