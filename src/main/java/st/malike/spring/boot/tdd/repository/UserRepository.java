package st.malike.spring.boot.tdd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import st.malike.spring.boot.tdd.model.User;

import java.io.Serializable;

/**
 * malike_st.
 */

@Repository
public interface UserRepository extends CrudRepository<User,Serializable> {

  public User findByUsername(String username);
}
