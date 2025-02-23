package com.sweav.common.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SweavUserDetails(
    val user: SweavUser,
) : UserDetails {
    private val authorities: Collection<SimpleGrantedAuthority> = emptyList()
    override fun getAuthorities(): Collection<GrantedAuthority>? {
        return authorities
    }

    override fun getUsername(): String = user.userId

    override fun getPassword(): String = user.credentials

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
