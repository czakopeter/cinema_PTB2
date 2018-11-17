package cinema.backend.dao;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author CzPet
 */
public interface CRUDDao<E, K> {
//  void delete(K key);
  
  List<E> findAll();
  
  E findById(K key);
  
//  E save(E entity);
  
//  void update(E entity);
  
   void setCon(Connection con);
}
