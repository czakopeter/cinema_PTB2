package cinema.frontend;

import cinema.backend.entities.Film;
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
    
    public static List<Film> listAllFilms() {
      return service.listAllFilms();
    }
/*    
    public static List<Show> listAllShows() {
      return null;
    }
    
    public static void addNewShow(String filmId, String datetime, String roomName) {
        
    }
    
    public static void deleteShow(String showId) {
        
    }
    
    public static List<Show> filterShowByRoom(String roomName) {
      return null;
    }
    
    public static List<Show> filterShowByFilm(String filmId) {
      return null;
    }
    
    public static int getSoldTicketsForFilm(String filmId) {
        return 0;
    }
    
    public static int getEmptySeatForShow(String showId) {
        return 0;
    }
    
    public static void modifyBooking(List<Seat>) {
        
    }
    
    public static void showBookingWindow() {
        
    }
*/
}
