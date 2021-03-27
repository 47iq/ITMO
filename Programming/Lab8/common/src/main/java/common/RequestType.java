package common;

import java.io.Serializable;

public enum RequestType implements Serializable {
    EXECUTE,
    ASK_TICKET,
    REGISTER,
    LOGIN,
    COLOR;
    private static final long serialVersionUID = 3110L;
}
