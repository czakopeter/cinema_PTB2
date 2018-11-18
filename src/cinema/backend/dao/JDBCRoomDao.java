package cinema.backend.dao;

import cinema.backend.entities.Film;
import cinema.backend.entities.Room;
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
public class JDBCRoomDao implements RoomDao {
  private  Connection con;

  public JDBCRoomDao(Connection con) {
    this.con = con;
  }
  
  
  @Override
  public void setCon(Connection con) {
    this.con = con;
  }
  
  @Override
  public List<Room> findAll() {
    String sql = "SELECT * FROM \"USERNAME\".\"room\"";
    
    try (PreparedStatement statement = con.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();) {
      List<Room> result = new LinkedList<>();
      while (resultSet.next()) {
        result.add(setRoom(resultSet));
      }
      return result;
    }
    catch (SQLException ex) {
        Logger.getLogger(JDBCRoomDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @Override
  public Room findById(String key) {
    String sql = "SELECT * FROM \"USERNAME\".\"room\" WHERE roomName = ?";
    try (PreparedStatement statement = createPreparedStatement(con, sql, key);
      ResultSet resultSet = statement.executeQuery();) {

      List<Room> result = new LinkedList<>();
      while (resultSet.next()) {
        result.add(setRoom(resultSet));
      }
      return result.get(0);
    }
    catch (SQLException ex) {
      Logger.getLogger(JDBCRoomDao.class.getName()).log(Level.SEVERE, null, ex);
    }
  return null;
  }
  
  private Room setRoom(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setRoomName(resultSet.getString("roomName"));
        room.setRowNr(resultSet.getInt("rowNr"));
        room.setColumnNr(resultSet.getInt("columnNr"));
        return room;
}
  
  private PreparedStatement createPreparedStatement(Connection con, String sql, String key) throws SQLException {
    PreparedStatement statement = con.prepareStatement(sql);

    statement.setString(1, key);

    return statement;
  }
}
