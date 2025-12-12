package com.crs.main.util.enums;

public class UserUtils {
    public enum UserRole {
        USER,
        CUSTOMER,
        ADMIN,
        MANAGER,
        STAFF
    }

    public enum AccountStatus {
        ACTIVE,
        INACTIVE,
        SUSPENDED,
        DELETED,
        PENDING_VERIFICATION,
        BANNED
    }
}
