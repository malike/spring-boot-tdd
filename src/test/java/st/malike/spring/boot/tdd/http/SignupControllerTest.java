package st.malike.spring.boot.tdd.http;

import com.google.gson.Gson;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import st.malike.spring.boot.tdd.AppMain;
import st.malike.spring.boot.tdd.repository.UserRepository;
import st.malike.spring.boot.tdd.util.Enums;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

/**
 * malike_st.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppMain.class)
@WebAppConfiguration
@IntegrationTest(value = "server.port:9000")
public class SignupControllerTest {

    @Autowired
    private SignupController signupController;
    @Autowired
    private UserRepository userRepository;
    Map user = null;


    @Before
    public void setUp() {

        user = new HashMap<>();
        user.put("username", "malike");
        user.put("password", "password");
        user.put("email", "st.malike@gmail.com");
        user.put("firstName", "Malike");
        user.put("lastName", "Kendeh");


        RestAssuredMockMvc.standaloneSetup(signupController);
    }

    @After
    public void tearDown() {

        userRepository.deleteAll();
    }

    @Test
    public void signup() {
        given()
                .log()
                .all().contentType("application/json")
                .body(new Gson().toJson(user))
                .when()
                .post("/user/signup")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("status", Matchers.is(true))
                .body("result.username", Matchers.is(user.get("username")));
    }

    @Test
    public void signupWithNoUserName() {
        user.remove("username");
        given()
                .log()
                .all().contentType("application/json")
                .body(new Gson().toJson(user))
                .when()
                .post("/user/signup")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("status", Matchers.is(false))
                .body("message", Matchers.is(Enums.JSONResponseMessage.REQUIRED_PARAMETER_MISSING.toString()));
    }


    @Test
    public void signupWithNoPasswordMissingParameterException() {
        user.remove("password");
        given()
                .log()
                .all().contentType("application/json")
                .body(new Gson().toJson(user))
                .when()
                .post("/user/signup")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("status", Matchers.is(false))
                .body("message", Matchers.is(Enums.JSONResponseMessage.REQUIRED_PARAMETER_MISSING.toString()));
    }


    @Test
    public void signupWithExistingUserNameOrEmailAlreadyExistException() {

        given()
                .log()
                .all().contentType("application/json")
                .body(new Gson().toJson(user))
                .when()
                .post("/user/signup")
                .then()
                .statusCode(HttpStatus.SC_OK);

        //note... the code above could also be mocked

        given()
                .log()
                .all().contentType("application/json")
                .body(new Gson().toJson(user))
                .when()
                .post("/user/signup")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("status", Matchers.is(false))
                .body("message", Matchers.is(Enums.JSONResponseMessage.TWO_OF_THAT_CANT_EXIST.toString()));
    }


}
