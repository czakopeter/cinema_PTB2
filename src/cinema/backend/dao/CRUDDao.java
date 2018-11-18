package cinema.backend.dao;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author CzPet
 */
public interface CRUDDao<E, K> {
  void setCon(Connection con);
  
  List<E> findAll();
  
  E findById(K key);
  
//  void delete(K key);
  
//  E save(E entity);
  
//  void update(E entity);
}
