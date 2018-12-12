package cinema.backend.dao;

import cinema.backend.entities.Show;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
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
  public void delete(long key) {
    String sql = "DELETE FROM \"USERNAME\".\"show\" WHERE showId = ?";
    
    try (PreparedStatement statement = con.prepareStatement(sql)) {
      statement.setLong(1, key);
      statement.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(JDBCShowDao.class.getName()).log(Level.SEVERE, null, ex);
    }
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
        show.setStartDate(resultSet.getDate("startDate").toLocalDate());
        show.setStartTime(resultSet.getTime("startTime").toLocalTime());
        return  show;
    }
    
  private PreparedStatement createPreparedStatement(Connection con, String sql, Long key) throws SQLException {
  PreparedStatement statement = con.prepareStatement(sql);

  statement.setLong(1, key);

  return statement;
  }
  
  private PreparedStatement createPreparedStatement(Connection con, String sql, String key) throws SQLException {
  PreparedStatement statement = con.prepareStatement(sql);

  statement.setString(1, key);

  return statement;
  }

  @Override
  public List<Show> findShowsByFilmId(Long filmId) {
    String sql = "SELECT * FROM \"USERNAME\".\"show\" WHERE filmId = ?";
        try (PreparedStatement statement = createPreparedStatement(con, sql, filmId);
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
  public List<Show> listShowsByRoom(String roomName) {
    String sql = "SELECT * FROM \"USERNAME\".\"show\" WHERE roomName = ?";
        try (PreparedStatement statement = createPreparedStatement(con, sql, roomName);
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
  public Show save(Show show) {
    String sql = "INSERT INTO \"USERNAME\".\"show\" (filmId, roomName, startDate, startTime) VALUES (?, ?, ?, ?)";
    
    try (PreparedStatement statement = createPreparedStatementForSave(con, sql, show);
            ResultSet generatedKey = statement.getGeneratedKeys();) {
      if(generatedKey.next()) {
        show.setShowId(generatedKey.getLong(1));
        return show;
      } else {
        throw new SQLException("create show failed");
      }
    } catch (SQLException ex) {
        Logger.getLogger(JDBCShowDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
  
  private PreparedStatement createPreparedStatementForSave(Connection con, String sql, Show show) throws SQLException{
    PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    
    statement.setLong(1, show.getFilmId());
    statement.setString(2, show.getRoomName());
    statement.setDate(3, Date.valueOf(show.getStartDate()));
    statement.setTime(4, Time.valueOf(show.getStartTime()));
    
    statement.executeUpdate();
    
    return statement;
  }

  @Override
  public void update(Show show) {
    String sql = "UPDATE \"USERNAME\".\"show\" SET filmId=?, roomName=?, startDate=?, startTime=? WHERE showId=?";
    try (PreparedStatement statement = createPreparedStatementForUpdate(con, sql, show);) {
      statement.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(JDBCSeatDao.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private PreparedStatement createPreparedStatementForUpdate(Connection con, String sql, Show show) throws SQLException{
    PreparedStatement statement = con.prepareStatement(sql);

    statement.setLong(1, show.getFilmId());
    statement.setString(2, show.getRoomName());
    statement.setDate(3, Date.valueOf(show.getStartDate()));
    statement.setTime(4, Time.valueOf(show.getStartTime()));
    statement.setLong(5, show.getShowId());
    
    return statement;
  }

  @Override
  public List<Show> listShowsByFilmIdAtDate(Long filmId, LocalDate date) {
    String sql = "SELECT * FROM \"USERNAME\".\"show\" WHERE filmId = ? and startDate = ?";
        try (PreparedStatement statement = createPreparedStatement(con, sql, filmId, date);
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
  public List<Show> listShowsByRoomAtDate(String roomName, LocalDate date) {
    String sql = "SELECT * FROM \"USERNAME\".\"show\" WHERE roomName = ? and startDate = ?";
        try (PreparedStatement statement = createPreparedStatement(con, sql, roomName, date);
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
  
  private PreparedStatement createPreparedStatement(Connection con, String sql, String key, LocalDate date) throws SQLException {
  PreparedStatement statement = con.prepareStatement(sql);

  statement.setString(1, key);
  statement.setDate(2, Date.valueOf(date));

  return statement;
  }
  
  private PreparedStatement createPreparedStatement(Connection con, String sql, Long key, LocalDate date) throws SQLException {
  PreparedStatement statement = con.prepareStatement(sql);

  statement.setLong(1, key);
  statement.setDate(2, Date.valueOf(date));

  return statement;
  }
}
