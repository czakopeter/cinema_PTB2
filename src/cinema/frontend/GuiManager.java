package cinema.frontend;

import cinema.backend.dao.DaoManager;
import cinema.backend.entities.Film;
import cinema.backend.entities.Room;
import cinema.backend.entities.Seats;
import cinema.backend.entities.Show;
import cinema.backend.service.DaoService;
import cinema.backend.service.Service;
import cinema.frontend.validators.Validator;
import cinema.frontend.windows.DashboardWindow;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author CzP
 */
public class GuiManager {
    private static DashboardWindow screen;
    private static final Service service = new DaoService(new DaoManager());
    
    public static void start() {
        screen = new DashboardWindow();
        screen.pack();
        screen.setVisible(true);
    }
    
  
    public static Film getFilm(String filmId) {
      return service.getFilm(Long.valueOf(filmId));
    }
    
    public static Film getFilm(Long filmId) {
      return service.getFilm(filmId);
    }
    
    public static Show getShow(Long showId) {
      return service.getShow(showId);
    }
    
    public static List<Film> listAllFilms() {
      return service.listAllFilms();
    }
        
    public static List<Show> listAllShows() {
      return service.listAllShows();
    }
    
    public static List<Room> listAllRooms() {
      return service.listAllRooms();
    }
    
    public static void saveShow(Long filmId, String roomName, String startDate, String startTime) {
      if(Validator.validateShow(filmId, roomName, startDate, startTime, screen)){
        Long showId = service.saveShow(filmId, roomName, LocalDate.parse(startDate), LocalTime.parse(startTime));
        screen.refreshListOfShow(showId);
      }
    }
    
    public static void updateShow(Long showId, Long filmId, String roomName, String startDate, String startTime) {
      service.updateShow(showId, filmId, roomName, LocalDate.parse(startDate), LocalTime.parse(startTime));
      screen.refreshListOfFilmAndShowPanel(showId, filmId);
    }
    
    public static void deleteShow(Long showId) {
      if(GuiManager.getSeatsByShow(showId).getSoldTicket() == 0) {
        service.deleteShow(showId);
      }
      screen.refreshListOfShow(showId);
    }
    
    public static List<Show> listShowsByFilmId(Long filmId) {
      return service.listShowsByFilmId(filmId);
    }
    
    public static List<Show> listShowsByRoom(String roomName) {
      return service.listShowsByRoom(roomName);
    }
    
    public static void modifyBooking(Seats seats) {
      service.modifySeatsStatus(seats);  
      screen.refreshListOfFilmAndShowPanel(seats.getShowId(), getShow(seats.getShowId()).getFilmId());
    }

  public static Room getRoom(String roomName) {
    return service.getRoom(roomName);
  }

  public static Seats getSeatsByShow(Long showId) {
    return service.listSeatsByShowId(showId);
  }
  
  public static List<Seats> getSeatsByFilm(Long filmId) {
    List<Seats> seats  = new LinkedList<>();
    List<Show> shows = listShowsByFilmId(filmId);
    shows.forEach(show -> seats.add(getSeatsByShow(show.getShowId())));
    return seats;
  }

  public static List<Show> listShowsByFilmIdAtDate(Long filmId, String startDate) {
    return service.listShowsByFilmIdAtDate(filmId, LocalDate.parse(startDate));
  }
  
  public static List<Show> listShowsByRoomAtDate(String roomName, String startDate) {
    return service.listShowsByRoomAtDate(roomName, LocalDate.parse(startDate));
  }
}
