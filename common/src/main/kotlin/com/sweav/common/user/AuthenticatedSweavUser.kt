package com.sweav.common.user

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore

class AuthenticatedSweavUser (
    override val userId: String,
    override val credentials: String,
    override val clientIp: String,
    val status: UserStatus,
) : SweavUser {
    override val role: SweavUserRoleType = SweavUserRoleType.USER

    @JsonIgnore
    fun isRegulated() = status == UserStatus.REGULATED
}

enum class UserStatus {
    ACTIVE,
    REGULATED,
    DORMANT,
    UNKNOWN;

    fun isActive(): Boolean = this == ACTIVE

    companion object {

        private val nameToType = entries.associateBy { it.name }

        @JsonCreator
        fun of(status: String): UserStatus {
            return nameToType[status] ?: UNKNOWN
        }
    }
}
