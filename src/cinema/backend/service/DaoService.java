package cinema.backend.service;

import cinema.backend.dao.DaoManager;
import cinema.backend.entities.Film;
import cinema.backend.entities.Room;
import cinema.backend.entities.Seats;
import cinema.backend.entities.Show;
import java.time.LocalDate;
import java.time.LocalTime;
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
  public Show getShow(Long showId) {
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
  
  private void saveSeats(Show show) {
    Room room = getRoom(show.getRoomName());
    String seatStatus = "";
    for(int i = 0; i<room.getRowNr()*room.getColumnNr(); i++){
      seatStatus += "A";
    }
    Seats seat = new Seats();
    seat.setShowId(show.getShowId());
    seat.setRoomName(show.getRoomName());
    seat.setSeatsStatus(seatStatus);
    dm.saveSeats(seat);
  }

  @Override
  public Long saveShow(Long filmId, String roomName, LocalDate startDate, LocalTime startTime) {
    Show show = new Show();
    show.setFilmId(filmId);
    show.setRoomName(roomName);
    show.setStartDate(startDate);
    show.setStartTime(startTime);
    show = dm.saveShow(show);
    saveSeats(show);
    return show.getShowId();
  }

  @Override
  public void updateShow(Long showId, Long filmId, String roomName, LocalDate startDate, LocalTime startTime) {
    System.out.println("Just update");
    Show show = getShow(showId);
    show.setFilmId(filmId);
    show.setStartDate(startDate);
    show.setStartTime(startTime);
    if(!roomName.equals(show.getRoomName())) {
      System.out.println("Room update");
      System.out.println("old:" + show.getRoomName() + ",new:" + roomName + "#");
      updateSeats(show.getShowId(), roomName);
      show.setRoomName(roomName);
    }
    dm.updateShow(show);
    
  }

  private void updateSeats(Long showId, String roomName) {
    Room room = getRoom(roomName);
    String seatStatus = "";
    for(int i = 0; i<room.getRowNr()*room.getColumnNr(); i++){
      seatStatus += "A";
    }
    Seats seat = listSeatsByShowId(showId);
    seat.setRoomName(roomName);
    seat.setSeatsStatus(seatStatus);
    modifySeatsStatus(seat);
  }

  @Override
  public void deleteShow(Long showId) {
    dm.deleteShow(showId);
  }

  @Override
  public List<Show> listShowsByFilmIdAtDate(Long filmId, LocalDate date) {
    return dm.listShowsByFilmIdAtDate(filmId, date);
  }

  @Override
  public List<Show> listShowsByRoomAtDate(String roomName, LocalDate date) {
    return dm.listShowsByRoomAtDate(roomName, date);
  }
}
