package common;

import java.io.Serializable;

public class DefaultUser implements User, Serializable {

    private static final Long serialVersionUID = 102230L;

    private final String login;

    private final String password;

    public DefaultUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return login;
    }
}
