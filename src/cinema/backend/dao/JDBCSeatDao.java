package cinema.backend.dao;

import cinema.backend.entities.Seats;
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
  public List<Seats> findAll() {
    String sql = "SELECT * FROM \"USERNAME\".\"seats\"";
    
    try (PreparedStatement statement = con.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();) {
      List<Seats> result = new LinkedList<>();
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
  public Seats findById(Long key) {
    String sql = "SELECT * FROM \"USERNAME\".\"seats\" WHERE seatsId = ?";
    try (PreparedStatement statement = createPreparedStatement(con, sql, key);
      ResultSet resultSet = statement.executeQuery();) {

      List<Seats> result = new LinkedList<>();
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
  
  private Seats setSeat(ResultSet resultSet) throws SQLException {
    Seats seat = new Seats();
    seat.setSeatId(resultSet.getLong("seatsId"));
    seat.setShowId(resultSet.getLong("showId"));
    seat.setRoomName(resultSet.getString("roomName"));
    seat.setSeatsStatus(resultSet.getString("seatsStatus"));
    return seat;
  }
  
  private PreparedStatement createPreparedStatement(Connection con, String sql, Long key) throws SQLException {
    PreparedStatement statement = con.prepareStatement(sql);

    statement.setLong(1, key);

    return statement;
  }

  @Override
  public Seats findByShowId(Long key) {
    String sql = "SELECT * FROM \"USERNAME\".\"seats\" WHERE showId = ?";
    
    try (PreparedStatement statement = createPreparedStatement(con, sql, key);
      ResultSet resultSet = statement.executeQuery();) {
      List<Seats> result = new LinkedList<>();
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

  @Override
  public void update(Seats seats) {
    String sql = "UPDATE \"USERNAME\".\"seats\" SET showId=?, roomName=?, seatsStatus=? WHERE seatsId=?";
    try (PreparedStatement statement = createPreparedStatementForUpdate(con, sql, seats);) {
      statement.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(JDBCSeatDao.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private PreparedStatement createPreparedStatementForUpdate(Connection con, String sql, Seats seats) throws SQLException{
    PreparedStatement statement = con.prepareStatement(sql);

    statement.setLong(1, seats.getShowId());
    statement.setString(2, seats.getRoomName());
    statement.setString(3, seats.getSeatsStatus());
    statement.setLong(4, seats.getSeatId());

  return statement;
  }

  @Override
  public void save(Seats seat) {
    String sql = "INSERT INTO \"USERNAME\".\"seats\" (showId, roomName, seatsStatus) VALUES (?, ?, ?)";
    
    try (PreparedStatement statement = createPreparedStatementForSave(con, sql, seat);) {
      statement.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(JDBCSeatDao.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private PreparedStatement createPreparedStatementForSave(Connection con, String sql, Seats seat) throws SQLException{
    PreparedStatement statement = con.prepareStatement(sql);
    
    statement.setLong(1, seat.getShowId());
    statement.setString(2, seat.getRoomName());
    statement.setString(3, seat.getSeatsStatus());
    
    return statement;
  }
}
