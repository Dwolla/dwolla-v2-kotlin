package com.dwolla.util

import java.time.Instant

internal class Token(val accessToken: String, expiresIn: Long) {

    companion object {
        private const val EXPIRES_IN_LEEWAY = 60L
    }

    private val expiresAt: Instant = Instant.now().plusSeconds(expiresIn).minusSeconds(EXPIRES_IN_LEEWAY)

    fun isExpired(): Boolean {
        return expiresAt.isBefore(Instant.now())
    }
}
