package cinema.backend.dao;

import cinema.backend.entities.Film;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

  public DaoManager() {
    this.filmDao = new JDBCFilmDao(con);
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
