package com.sweav.core.domain

import com.sweav.common.user.SweavUserRoleType
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
class SweavAuditor (
    val id: String,
    @Enumerated(EnumType.STRING)
    val role: SweavUserRoleType
)
