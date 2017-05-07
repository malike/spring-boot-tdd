package st.malike.spring.boot.tdd.http;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import st.malike.spring.boot.tdd.exception.MissingDataException;
import st.malike.spring.boot.tdd.exception.MultipleAccountException;
import st.malike.spring.boot.tdd.util.Enums;
import st.malike.spring.boot.tdd.util.JSONResponse;

/**
 * malike_st.
 */
@Controller
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = MissingDataException.class)
    @ResponseBody
    public JSONResponse missingDataException(MissingDataException e) {
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.setCount(0);
        jsonResponse.setMessage(Enums.JSONResponseMessage.REQUIRED_PARAMETER_MISSING.toString());
        jsonResponse.setStatus(false);
        jsonResponse.setResult(e.getLocalizedMessage());
        return jsonResponse;
    }

    @ExceptionHandler(value = MultipleAccountException.class)
    @ResponseBody
    public JSONResponse multipleAccountException(MultipleAccountException e) {
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.setCount(0);
        jsonResponse.setMessage(Enums.JSONResponseMessage.TWO_OF_THAT_CANT_EXIST.toString());
        jsonResponse.setStatus(false);
        jsonResponse.setResult(e.getLocalizedMessage());
        return jsonResponse;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONResponse genericException(Exception e) {
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.setCount(0);
        jsonResponse.setMessage(Enums.JSONResponseMessage.SERVER_ERROR.toString());
        jsonResponse.setStatus(false);
        jsonResponse.setResult(e.getLocalizedMessage());
        return jsonResponse;
    }
}
