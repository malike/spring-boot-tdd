package st.malike.spring.boot.tdd.exception;

/**
 * malike_st.
 */
public class MultipleAccountException extends Exception {

    public MultipleAccountException() {
    }

    public MultipleAccountException(String message) {
        super(message);
    }

    public MultipleAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultipleAccountException(Throwable cause) {
        super(cause);
    }

    public MultipleAccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
