package st.malike.spring.boot.tdd.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import st.malike.spring.boot.tdd.exception.MissingDataException;
import st.malike.spring.boot.tdd.exception.MultipleAccountException;
import st.malike.spring.boot.tdd.service.UserService;
import st.malike.spring.boot.tdd.util.Enums;
import st.malike.spring.boot.tdd.util.JSONResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * malike_st.
 */
@Controller
public class SignupController extends ExceptionController {

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String signup() {
        return "signup";
    }

    @RequestMapping(value = {"/user/signup"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONResponse userSignup(@RequestBody Object data, HttpServletResponse response, HttpServletRequest request) throws MissingDataException, MultipleAccountException {
        JSONResponse jSONResponse = new JSONResponse();
        Map<String, Object> dataHash = (Map<String, Object>) data;
        String username = null;
        String password = null;
        String email = null;
        if (dataHash.containsKey("username")) {
            username = (String) dataHash.get("username");
        }
        if (dataHash.containsKey("password")) {
            password = (String) dataHash.get("password");
        }
        if (dataHash.containsKey("email")) {
            email = (String) dataHash.get("email");
        }

        if (null == password || username == null) {
            throw new MissingDataException("Required user details not found");
        }
        jSONResponse.setResult(userService.signup(email,username,password));
        jSONResponse.setStatus(true);
        jSONResponse.setMessage(Enums.JSONResponseMessage.SUCCESS.toString());
        return jSONResponse;
    }

}
