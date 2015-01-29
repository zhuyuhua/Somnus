package com.somnus.users.domain.event;

import com.somnus.users.domain.vo.UserId;

public class UserCreatedEvent {

    private UserId userId;
    private String username;
    private String name;
    private String password;

    public UserCreatedEvent(UserId userId, String name, String username, String password) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public UserId getUserIdentifier() {
        return this.userId;
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

    @Override
    public String toString() {
        return "UserCreatedEvent{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
