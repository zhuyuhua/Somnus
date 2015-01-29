
package com.somnus.users.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthenticateUserCommand {

    private final String userName;
    @NotNull
    @Size(min = 3)
    private final char[] password;

    public AuthenticateUserCommand(String userName, char[] password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public char[] getPassword() {
        return password;
    }
}
