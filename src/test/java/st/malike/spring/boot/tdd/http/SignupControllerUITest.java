package st.malike.spring.boot.tdd.http;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.assertj.FluentLeniumAssertions;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import st.malike.spring.boot.tdd.AppMain;

import java.util.HashMap;
import java.util.Map;

/**
 * malike_st.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppMain.class)
@WebAppConfiguration
@IntegrationTest(value = "server.port:9000")
public class SignupControllerUITest extends FluentTest {

    public WebDriver webDriver = new FirefoxDriver();
    @Autowired
    private SignupController signupController;



    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }


    @Before
    public void setUp() {

        RestAssuredMockMvc.standaloneSetup(signupController);
    }


    @Test
    public void testViewSignupPage() throws Exception {
        goTo("http://localhost:9000/signup");
        FluentLeniumAssertions.assertThat(find(".header")).hasText("Signup");
    }


    @Test
    public void testFindSignupFormOnPage() throws Exception {
        goTo("http://localhost:9000/signup");

        //asserts inputs
        FluentList<FluentWebElement> usernameInput = find(".username");
        FluentList<FluentWebElement> emaillInput = find(".email");
        FluentList<FluentWebElement> passwordInput =find(".password");


        Assert.assertTrue("Username input not found", usernameInput != null && usernameInput.size() == 1);
        Assert.assertTrue("Password input not found", emaillInput != null && emaillInput.size() == 1);
        Assert.assertTrue("Email input not found", passwordInput != null && passwordInput.size() == 1);


    }


    @Test
    public void testFillSignupFormOnPage() throws Exception {
        goTo("http://localhost:9000/signup");

        //asserts inputs
        FluentList<FluentWebElement> usernameInput = find(".username");
        FluentList<FluentWebElement> emaillInput = find(".email");
        FluentList<FluentWebElement> passwordInput =find(".password");
        FluentList<FluentWebElement> signup =find("#signup");

        usernameInput.first().fill().with("malike");
        emaillInput.first().fill().with("st.malike@gmail.com");
        passwordInput.first().fill().with("password");

        signup.first().doubleClick();

        Assert.assertEquals("SUCCESS",webDriver.switchTo().alert().getText());
    }


}
