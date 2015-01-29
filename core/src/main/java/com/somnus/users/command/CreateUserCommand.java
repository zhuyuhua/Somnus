package com.somnus.users.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.axonframework.common.Assert;

import com.somnus.users.domain.vo.UserId;

public class CreateUserCommand {
    private UserId userId;
    @NotNull
    @Size(min = 3)
    private String username;
    private String name;
    @NotNull
    @Size(min = 3)
    private String password;

    public CreateUserCommand(UserId userId, String name, String username, String password) {
        Assert.notNull(userId, "The provided userId cannot be null");
        Assert.notNull(name, "The provided name cannot be null");
        Assert.notNull(username, "The provided username cannot be null");
        Assert.notNull(password, "The provided password cannot be null");

        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
