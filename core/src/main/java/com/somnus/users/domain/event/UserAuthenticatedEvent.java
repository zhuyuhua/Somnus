package com.somnus.users.domain.event;

import com.somnus.users.domain.vo.UserId;

public class UserAuthenticatedEvent {
    private final UserId userId;

    public UserAuthenticatedEvent(UserId userId) {
        this.userId = userId;
    }

    public UserId getUserId() {
        return this.userId;
    }
}
