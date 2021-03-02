package common;

import java.io.Serializable;

public class DefaultResponse implements Response, Serializable {

    private static final long serialVersionUID = 1000L;

    private final boolean successful;

    private final String message;

    public DefaultResponse(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getMessage() {
        return message;
    }
}
