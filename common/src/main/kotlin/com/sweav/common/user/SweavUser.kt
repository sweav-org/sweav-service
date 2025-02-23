package com.sweav.common.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.sweav.common.util.JsonUtils
import com.sweav.common.util.toObject
import org.slf4j.LoggerFactory
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "role",
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    visible = true,
    defaultImpl = UnAuthenticatedSweavUser::class
)
@JsonSubTypes(
    JsonSubTypes.Type(name = "GUEST", value = UnAuthenticatedSweavUser::class),
    JsonSubTypes.Type(name = "USER", value = AuthenticatedSweavUser::class),
    JsonSubTypes.Type(name = "ADMIN", value = AdminSweavUser::class),

    )
interface SweavUser {
    val userId: String
    val credentials: String
    val clientIp: String
    val role: SweavUserRoleType

    fun toAuthentication(): String {
        return String(Base64.getEncoder().encode(JsonUtils.toJsonBytes(this)))
    }

    companion object {
        private val logger = LoggerFactory.getLogger(SweavUser::class.java)

        fun fromAuthentication(okayAuthenticationHeader: String): SweavUser {
            if (okayAuthenticationHeader.isBlank()) {
                return UnAuthenticatedSweavUser.INSTANCE
            }

            return deserialize(okayAuthenticationHeader) ?: UnAuthenticatedSweavUser.INSTANCE
        }

        private fun deserialize(okayAuthenticationHeader: String): SweavUser? {
            return try {
                String(Base64.getDecoder().decode(okayAuthenticationHeader))
                    .toObject(SweavUser::class.java)
            } catch (e: Exception) {
                logger.error("Authentication Header [{}] is invalid.", okayAuthenticationHeader, e)
                null
            }
        }
    }
}
