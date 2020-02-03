package com.latte.admin.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST","손님"),
    HOST("ROLE_HOST","사장님");
    private final String key;
    private final String title;
}
