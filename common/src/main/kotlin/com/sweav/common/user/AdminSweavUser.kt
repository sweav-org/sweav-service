package com.sweav.common.user

class AdminSweavUser (
    override val userId: String,
    override val credentials: String,
    override val clientIp: String,
) : SweavUser {
    override val role: SweavUserRoleType = SweavUserRoleType.ADMIN
}
