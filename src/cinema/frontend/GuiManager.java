package cinema.frontend;

import cinema.backend.entities.Film;
import cinema.backend.entities.Room;
import cinema.backend.entities.Seats;
import cinema.backend.entities.Show;
import cinema.backend.service.DaoService;
import cinema.backend.service.Service;
import cinema.frontend.windows.DashboardWindow;
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
    
    public static Show getShow(Long showId) {
      return service.getShow(String.valueOf(showId));
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
    
    public static void addNewShow(String filmId, String datetime, String roomName) {
      
    }
    
    public static List<Show> listShowsByFilmId(String filmId) {
      return service.listShowsByFilmId(Long.valueOf(filmId));
    }
    
    public static List<Show> listShowsByRoom(String roomName) {
      return service.listShowsByRoom(roomName);
    }
    
    public static int getSoldTicketsForFilm(String filmId) {
      return 0;
    }
    
    public static int getEmptySeatForShow(String showId) {
      return 0;
    }
    
    public static void modifyBooking(Seats seats) {
    }
/*    
    public static void deleteShow(String showId) {
        
    }
*/

  public static Room getRoom(String roomName) {
    return service.getRoom(roomName);
  }

  public static List<Seats> getSeatsByShow(String showId) {
    return service.listSeatsByShowId(Long.valueOf(showId));
  }
}
