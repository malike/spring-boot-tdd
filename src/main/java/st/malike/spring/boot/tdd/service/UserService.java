package st.malike.spring.boot.tdd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import st.malike.spring.boot.tdd.exception.MissingDataException;
import st.malike.spring.boot.tdd.exception.MultipleAccountException;
import st.malike.spring.boot.tdd.model.User;
import st.malike.spring.boot.tdd.repository.UserRepository;

/**
 * malike_st.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public User signup(String email, String username, String password) throws MissingDataException, MultipleAccountException {
        if(username==null || password ==null){
            throw new MissingDataException("username and password are required");
        }
        User findUser = findByUserName(username);
        if(findUser != null){
            throw new MultipleAccountException("account already exists with username");
        }
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        return save(user);
    }

    public User save(User user) {
        return  userRepository.save(user);
    }


    public User findByUserName(String username) {
        return  userRepository.findByUsername(username);
    }



}
