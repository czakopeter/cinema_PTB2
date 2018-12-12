package cinema.backend.dao;

import cinema.backend.entities.Film;
import cinema.backend.entities.Room;
import cinema.backend.entities.Seats;
import cinema.backend.entities.Show;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CzPet
 */
public class DaoManager {
  private static final String URL = "jdbc:derby:cinemaDB";
  private static final String USER = "username";
  private static final String PASSWORD = "password";
  
  private Connection con;
  
  private FilmDao filmDao;
  private ShowDao showDao;
  private RoomDao roomDao;
  private SeatDao seatDao;

  public DaoManager() {
    this.filmDao = new JDBCFilmDao(con);
    this.showDao = new JDBCShowDao(con);
    this.roomDao = new JDBCRoomDao(con);
    this.seatDao = new JDBCSeatDao(con);
    
  }

  public List<Film> listAllFilms() {
    open();
    filmDao.setCon(con);
    List<Film> films = filmDao.findAll();
    close();
    return films;
  }

  public Film getFilm(long filmId) {
    open();
    filmDao.setCon(con);
    Film film = filmDao.findById(filmId);
    close();
    return film;
  }
  
  public List<Show> listAllShows() {
      open();
      showDao.setCon(con);
      List<Show> shows = showDao.findAll();
      close();
      return shows;
  }
  
  public List<Room> listAllRooms() {
      open();
      roomDao.setCon(con);
      List<Room> rooms = roomDao.findAll();
      close();
      return rooms;
  }
  
  public Room getRoom(String roomName) {
    open();
    roomDao.setCon(con);
    Room room = roomDao.findById(roomName);
    close();
    return room;
  }
  
  public Seats listSeatsByShowId(long showId) {
    open();
    seatDao.setCon(con);
    Seats seat = seatDao.findByShowId(showId);
    close();
    return seat;
  }
  
  public List<Show> listShowsByFilmId(Long filmId) {
    open();
    showDao.setCon(con);
    List<Show> shows = showDao.findShowsByFilmId(filmId);
    close();
    return shows;
  }
  
  public List<Show> listShowsByRoom(String roomName) {
    open();
    showDao.setCon(con);
    List<Show> shows = showDao.listShowsByRoom(roomName);
    close();
    return shows;
  }
  
  public Show getShow(Long showId) {
    open();
    showDao.setCon(con);
    Show show = showDao.findById(showId);
    close();
    return show;
  }
  
  public void modifySeatsStatus(Seats seats) {
    open();
    seatDao.setCon(con);
    seatDao.update(seats);
    close();
  }
  
  public Show saveShow(Show show) {
    open();
    showDao.setCon(con);
    show = showDao.save(show);
    close();
    return show;
  }

  public void updateShow(Show show) {
    open();
    showDao.setCon(con);
    showDao.update(show);
    close();
  }
  
  public void saveSeats(Seats seat) {
    open();
    seatDao.setCon(con);
    seatDao.save(seat);
    close();
  }
  
  public void deleteShow(Long showId) {
    deleteSeatByShowId(showId);
    open();
    showDao.setCon(con);
    showDao.delete(showId);
    close();
  }
  
  private void deleteSeatByShowId(Long showId) {
    open();
    seatDao.setCon(con);
    seatDao.deleteByShowId(showId);
    close();
  }
  
  public List<Show> listShowsByFilmIdAtDate(Long filmId, LocalDate date) {
    open();
    showDao.setCon(con);
    List<Show> shows = showDao.listShowsByFilmIdAtDate(filmId, date);
    close();
    return shows;
  }

  public List<Show> listShowsByRoomAtDate(String roomName, LocalDate date) {
    open();
    showDao.setCon(con);
    List<Show> shows = showDao.listShowsByRoomAtDate(roomName, date);
    close();
    return shows;
  }
  
  private void open() {
    try {
      DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      con = DriverManager.getConnection(URL, USER, PASSWORD);
    }
    catch (SQLException ex) {
      Logger.getLogger(DaoManager.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (ClassNotFoundException ex) {
      Logger.getLogger(DaoManager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void close() {
    try {
      if ((con != null) && !con.isClosed()) {
        con.close();
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(DaoManager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}