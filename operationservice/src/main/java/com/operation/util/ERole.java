package com.operation.util;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ERole {

    ROLE_USER(1, "ROLE_USER"),
    ROLE_MODERATOR(2, "ROLE_MODERATOR"),
    ROLE_ADMIN(3, "ROLE_ADMIN");

    private final Integer eRoleId;
    private final String eRoleDescription;

    ERole(Integer eRoleId, String eRoleDescription) {
        this.eRoleId = eRoleId;
        this.eRoleDescription = eRoleDescription;
    }

    public Integer geteRoleId() {
        return eRoleId;
    }

    public String geteRoleDescription() {
        return eRoleDescription;
    }
}
