package cinema.backend.service;

import cinema.backend.entities.Film;
import cinema.backend.entities.Room;
import cinema.backend.entities.Seats;
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

  public List<Room> listAllRooms();

  public List<Show> listShowsByFilmId(Long filmid);

  public List<Show> listShowsByRoom(String roomName);

  public Show getShow(String showId);

  public Room getRoom(String roomName);

  public Seats listSeatsByShowId(Long showId);

  public void modifySeatsStatus(Seats seats);
}
