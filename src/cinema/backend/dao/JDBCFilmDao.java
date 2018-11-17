package cinema.backend.dao;

import cinema.backend.entities.Film;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
      Logger.getLogger(JDBCOrderDao.class.getName()).log(Level.SEVERE, null, ex);
    }
  return null;
  }
  
  private Film setFilm(ResultSet resultSet) throws SQLException {
        Film film = new Film();
        film.setFilmId(resultSet.getLong("filmId"));
        film.setTitle(resultSet.getString("title"));
        film.setOrderPrice(resultSet.getBigDecimal("orderPrice"));
        film.setStatus(OrderStatus.valueOf(resultSet.getString("status")));
        film.setRetailerName(resultSet.getString("retailerName"));
        return film;
}
  
  private PreparedStatement createPreparedStatement(Connection con, String sql, Long key) throws SQLException {
    PreparedStatement statement = con.prepareStatement(sql);

    statement.setLong(1, key);

    return statement;
  }
}
