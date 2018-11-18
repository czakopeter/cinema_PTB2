package cinema.backend.dao;

import cinema.backend.entities.Show;
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
 * @author CzP
 */
public class JDBCShowDao implements ShowDao {
  private Connection con;
    
  public JDBCShowDao(Connection con) {
    this.con = con;
  }
    
  @Override
  public void setCon(Connection con) {
    this.con = con;
  }

    @Override
    public List<Show> findAll() {
        String sql = "SELECT * FROM \"USERNAME\".\"show\"";
    
        try (PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();) {
          List<Show> result = new LinkedList<>();
          while (resultSet.next()) {
            result.add(setShow(resultSet));
          }
      return result;
        }
        catch (SQLException ex) {
            Logger.getLogger(JDBCShowDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Show findById(Long key) {
        String sql = "SELECT * FROM \"USERNAME\".\"show\" WHERE showId = ?";
        try (PreparedStatement statement = createPreparedStatement(con, sql, key);
        ResultSet resultSet = statement.executeQuery();) {

        List<Show> result = new LinkedList<>();
        while (resultSet.next()) {
            result.add(setShow(resultSet));
        }
        return result.get(0);
        }
        catch (SQLException ex) {
            Logger.getLogger(JDBCShowDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Show setShow(ResultSet resultSet) throws SQLException {
        Show show = new Show();
        show.setShowId(resultSet.getLong("showId"));
        show.setFilmId(resultSet.getLong("filmId"));
        show.setRoomName(resultSet.getString("roomName"));
        show.setStartAtDate(resultSet.getDate("startAtDatetime").toLocalDate());
        return  show;
    }
    
  private PreparedStatement createPreparedStatement(Connection con, String sql, Long key) throws SQLException {
  PreparedStatement statement = con.prepareStatement(sql);

  statement.setLong(1, key);

  return statement;
  }
}