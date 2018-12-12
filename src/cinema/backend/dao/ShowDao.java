package cinema.backend.dao;

import cinema.backend.entities.Show;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author CzP
 */
public interface ShowDao extends CRUDDao<Show, Long>{

  public List<Show> findShowsByFilmId(Long filmId);

  public List<Show> listShowsByRoom(String roomName);

  public Show save(Show show);

  public void update(Show show);
  
  public void delete(long key);

  public List<Show> listShowsByFilmIdAtDate(Long filmId, LocalDate date);

  public List<Show> listShowsByRoomAtDate(String roomName, LocalDate date);
}
