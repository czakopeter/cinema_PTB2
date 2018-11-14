package cinema.frontend;

import cinema.frontend.windows.DashboardWindow;
import java.util.List;

/**
 *
 * @author CzP
 */
public class GuiManager {
    private static DashboardWindow screen;
    private static final Service sevice = new DaoService();
    
    public static void start() {
        screen. = new DashboardWindow();
        screen.pack();
        screen.setVisible(true);
    }
    
    
    public static List<Film> listAllFilms() {
        return null;
    }
    
    public static List<Show> listAllShows() {
        return null;
    }
    
    public static void addNewShow(String filmId, String datetime, String roomName) {
        
    }
    
    public static void deleteShow(String showId) {
        
    }
    
    public static void filterShowByRoom(String roomName) {
        
    }
    
    public static void filterShowByFilm(String filmId) {
        
    }
    
    public static int getSoldTicketsForFilm(String filmId) {
        return 0;
    }
    
    public static int getEmptySeatForShow(String showId) {
        return 0;
    }
    
    public static void modifyBooking(List<Seat>) {
        
    }
    
    public static void showFilmWindow() {
        
    }
    
    public static void showShowWindow() {
        
    }
    
    public static void showBookingWindow() {
        
    }
}
