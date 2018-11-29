package cinema.backend.dao;

import cinema.backend.entities.Show;
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
}
