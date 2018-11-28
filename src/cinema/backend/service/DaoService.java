package cinema.backend.service;

import cinema.backend.dao.DaoManager;
import cinema.backend.entities.Film;
import cinema.backend.entities.Room;
import cinema.backend.entities.Seats;
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

  @Override
  public List<Show> listShowsByFilmId(Long filmId) {
    return dm.listShowsByFilmId(filmId);
  }

  @Override
  public List<Show> listShowsByRoom(String roomName) {
    return dm.listShowsByRoom(roomName);
  }

  @Override
  public Show getShow(String showId) {
    return dm.getShow(showId);
  }

  @Override
  public Room getRoom(String roomName) {
    return dm.getRoom(roomName);
  }

  @Override
  public Seats listSeatsByShowId(Long showId) {
    return dm.listSeatsByShowId(showId);
  }

  @Override
  public void modifySeatsStatus(Seats seats) {
    dm.modifySeatsStatus(seats);
  }

  @Override
  public void saveShow(Long filmId, String roomName,  startDate, String startTime) {
    Show show = new Show();
    show.setFilmId(filmId);
    show.setRoomName(roomName);
    show.setStartDate(startDate);
    show.setStartTime(startTime);
    dm.saveShow(filmId, roomName, startDate, startTime);
  }

  @Override
  public void updateShow(Long showId, Long filmId, String roomName, String startDate, String startTime) {
    dm.saveShow(showId, filmId, roomName, startDate, startTime);
  }
}
