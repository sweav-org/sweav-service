package com.sweav.common.user

class UnAuthenticatedSweavUser (
    override val userId: String = "N/A",
    override val credentials: String = "N/A",
    override val clientIp: String = "",
    override val role: SweavUserRoleType = SweavUserRoleType.GUEST,
) : SweavUser {
    companion object {
        val INSTANCE: SweavUser = UnAuthenticatedSweavUser()
    }
}
