package cinema.backend.dao;

import cinema.backend.entities.Seats;
import java.util.List;

/**
 *
 * @author CzPet
 */
public interface SeatDao extends CRUDDao<Seats, Long>{
  Seats findByShowId(Long showId);

  public void update(Seats seats);

  public void save(Seats seat);

  public void deleteByShowId(Long showId);
}
