package cinema.backend.dao;

import cinema.backend.entities.Film;
import cinema.backend.entities.Seat;
import cinema.backend.enums.AgeLimit;
import cinema.backend.enums.SeatStatus;
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
public class JDBCSeatDao implements SeatDao {
  private  Connection con;

  public JDBCSeatDao(Connection con) {
    this.con = con;
  }
  
  @Override
  public void setCon(Connection con) {
    this.con = con;
  }
  
  @Override
  public List<Seat> findAll() {
    String sql = "SELECT * FROM \"USERNAME\".\"seat\"";
    
    try (PreparedStatement statement = con.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();) {
      List<Seat> result = new LinkedList<>();
      while (resultSet.next()) {
        result.add(setSeat(resultSet));
      }
      return result;
    }
    catch (SQLException ex) {
        Logger.getLogger(JDBCSeatDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @Override
  public Seat findById(Long key) {
    String sql = "SELECT * FROM \"USERNAME\".\"Seat\" WHERE seatId = ?";
    try (PreparedStatement statement = createPreparedStatement(con, sql, key);
      ResultSet resultSet = statement.executeQuery();) {

      List<Seat> result = new LinkedList<>();
      while (resultSet.next()) {
        result.add(setSeat(resultSet));
      }
      return result.get(0);
    }
    catch (SQLException ex) {
      Logger.getLogger(JDBCSeatDao.class.getName()).log(Level.SEVERE, null, ex);
    }
  return null;
  }
  
  private Seat setSeat(ResultSet resultSet) throws SQLException {
    Seat seat = new Seat();
    seat.setSeatId(resultSet.getLong("seatId"));
    seat.setShowId(resultSet.getLong("showId"));
    seat.setRoomName(resultSet.getString("roomName"));
    seat.setRow(resultSet.getInt("rowIdx"));
    seat.setColumn(resultSet.getInt("columnIdx"));
    seat.setStatus(SeatStatus.valueOf(resultSet.getString("status")));
    return seat;
  }
  
  private PreparedStatement createPreparedStatement(Connection con, String sql, Long key) throws SQLException {
    PreparedStatement statement = con.prepareStatement(sql);

    statement.setLong(1, key);

    return statement;
  }

  @Override
  public List<Seat> findByShowId(Long showId) {
    String sql = "SELECT * FROM \"USERNAME\".\"Seat\" WHERE showId = ?";
    
    try (PreparedStatement statement = con.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();) {
      List<Seat> result = new LinkedList<>();
      while (resultSet.next()) {
        result.add(setSeat(resultSet));
      }
      return result;
    }
    catch (SQLException ex) {
        Logger.getLogger(JDBCSeatDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
}
