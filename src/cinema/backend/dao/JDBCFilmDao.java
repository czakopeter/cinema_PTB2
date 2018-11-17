package cinema.backend.dao;

import cinema.backend.entities.Film;
import cinema.backend.enums.AgeLimit;
import cinema.backend.enums.Sync;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CzPet
 */
public class JDBCFilmDao implements FilmDao {
  private  Connection con;

  public JDBCFilmDao(Connection con) {
    this.con = con;
  }

  @Override
  public void setCon(Connection con) {
    this.con = con;
  }

  @Override
  public List<Film> findAll() {
    String sql = "SELECT * FROM \"USERNAME\".\"film\"";
    
    try (PreparedStatement statement = con.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();) {
      List<Film> result = new LinkedList<>();
      while (resultSet.next()) {
        result.add(setFilm(resultSet));
      }
      return result;
    }
    catch (SQLException ex) {
        Logger.getLogger(JDBCFilmDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @Override
  public Film findById(Long key) {
    String sql = "SELECT * FROM \"USERNAME\".\"film\" WHERE filmId = ?";
    try (PreparedStatement statement = createPreparedStatement(con, sql, key);
      ResultSet resultSet = statement.executeQuery();) {

      List<Film> result = new LinkedList<>();
      while (resultSet.next()) {
        result.add(setFilm(resultSet));
      }
      return result.get(0);
    }
    catch (SQLException ex) {
      Logger.getLogger(JDBCFilmDao.class.getName()).log(Level.SEVERE, null, ex);
    }
  return null;
  }
  
  private Film setFilm(ResultSet resultSet) throws SQLException {
        Film film = new Film();
        film.setFilmId(resultSet.getLong("filmId"));
        film.setTitle(resultSet.getString("title"));
        film.setCounty(resultSet.getString("country"));
        film.setSynconized(Sync.valueOf(resultSet.getString("syncronized")));
        film.setDirector(resultSet.getString("director"));
        film.setStoryline(resultSet.getString("storyline"));
        film.setRuntime(resultSet.getInt("runtime"));
        film.setLicenseToPlay(resultSet.getInt("licenseToPlay"));
        film.setAgeLimit(AgeLimit.getAgeLimit(resultSet.getString("ageLimit")));
        return film;
}
  
  private PreparedStatement createPreparedStatement(Connection con, String sql, Long key) throws SQLException {
    PreparedStatement statement = con.prepareStatement(sql);

    statement.setLong(1, key);

    return statement;
  }
}
