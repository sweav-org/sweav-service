package com.sweav.core.config

import com.sweav.core.domain.SweavAuditor
import com.sweav.common.user.SweavUser
import com.sweav.common.user.SweavUserDetails
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.time.LocalDateTime
import java.util.*

@EntityScan("com.sweav.core")
@EnableJpaRepositories("com.sweav.core")
@EnableJpaAuditing(auditorAwareRef = "sweavAuditorAware", dateTimeProviderRef = "sweavDateTimeProvider")
@Configuration
class JpaConfig {
    @Bean
    open fun sweavAuditorAware(): AuditorAware<SweavAuditor> {
        return AuditorAware<SweavAuditor> {
            val authentication: Authentication = SecurityContextHolder.getContext().authentication
            if (!authentication.isAuthenticated || authentication.principal !is SweavUserDetails) {
                Optional.empty<SweavAuditor>()
            }

            val sweavUserDetails: SweavUserDetails = authentication.principal as SweavUserDetails
            val okayUser: SweavUser = sweavUserDetails.user
            Optional.of(SweavAuditor(sweavUserDetails.username, okayUser.role))
        }
    }

    @Bean
    fun sweavDateTimeProvider(): DateTimeProvider = DateTimeProvider {
        Optional.of(LocalDateTime.now().plusHours(9L))
    }
}
