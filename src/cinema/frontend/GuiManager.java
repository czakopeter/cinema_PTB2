package cinema.frontend;

import cinema.backend.entities.Film;
import cinema.backend.entities.Room;
import cinema.backend.entities.Seats;
import cinema.backend.entities.Show;
import cinema.backend.service.DaoService;
import cinema.backend.service.Service;
import cinema.frontend.windows.DashboardWindow;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author CzP
 */
public class GuiManager {
    private static DashboardWindow screen;
    private static final Service service = new DaoService();
    
    public static void start() {
        screen = new DashboardWindow();
        screen.pack();
        screen.setVisible(true);
    }
    
  
    public static Film getFilm(String filmId) {
      return service.getFilm(Long.valueOf(filmId));
    }
    
    public static Show getShow(String showId) {
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
      service.saveShow(filmId, roomName, startDate, startTime);
    }
    
    public static void updateShow(long showId, Long filmId, String roomName, String startDate, String startTime) {
      service.updateShow(showId, filmId, roomName, startDate, startTime);
    }
    
    public static void deleteShow(Long showId) {
        
    }
    
    public static List<Show> listShowsByFilmId(Long filmId) {
      return service.listShowsByFilmId(filmId);
    }
    
    public static List<Show> listShowsByRoom(String roomName) {
      return service.listShowsByRoom(roomName);
    }
    
    public static void modifyBooking(Seats seats) {
      service.modifySeatsStatus(seats);
      screen.refreshListOfFilmAndShowPanel();
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
}
