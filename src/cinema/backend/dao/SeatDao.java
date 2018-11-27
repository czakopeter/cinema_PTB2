package cinema.backend.dao;

import cinema.backend.entities.Seats;
import java.util.List;

/**
 *
 * @author CzPet
 */
public interface SeatDao extends CRUDDao<Seats, Long>{
  List<Seats> findByShowId(Long showId);
}
