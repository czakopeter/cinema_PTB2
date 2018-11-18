package cinema.backend.dao;

import cinema.backend.entities.Seat;
import java.util.List;

/**
 *
 * @author CzPet
 */
public interface SeatDao extends CRUDDao<Seat, Long>{
  List<Seat> findByShowId(Long showId);
}
