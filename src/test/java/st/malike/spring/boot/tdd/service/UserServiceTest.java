package st.malike.spring.boot.tdd.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import st.malike.spring.boot.tdd.exception.MissingDataException;
import st.malike.spring.boot.tdd.exception.MultipleAccountException;
import st.malike.spring.boot.tdd.model.User;
import st.malike.spring.boot.tdd.repository.UserRepository;

/**
 * malike_st.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    User user;

    @Before
    public void setUp() {

        //given
        user = new User();
        user.setEmail("st.malike@gmail.com");
        user.setPassword("password");
        user.setUsername("malike");

        //when
    }

    @Test
    public void saveUser() throws MissingDataException, MultipleAccountException {
        //when
        Mockito.when(userRepository.save(user)).thenReturn(user);

        userService.signup(user.getEmail(), user.getUsername(), user.getPassword());

        Mockito.verify(userRepository, Mockito.times(1)).save(user);

    }

    @Test(expected = MissingDataException.class)
    public void saveUserNoUserNameMissingDataException() throws MissingDataException, MultipleAccountException {
        //when

        userService.signup(user.getEmail(), null, user.getPassword());

    }

    @Test(expected = MissingDataException.class)
    public void saveUserNoPasswordMissingDataException() throws MissingDataException, MultipleAccountException {
        //when

        userService.signup(user.getEmail(), user.getUsername(), null);

    }

    @Test(expected=MultipleAccountException.class)
    public void saveUserAlreadyExistException() throws MultipleAccountException, MissingDataException {
        //when
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        userService.signup(user.getEmail(), user.getUsername(), user.getPassword());


    }


}