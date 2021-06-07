package no.apps.bedrock.utils

import java.security.MessageDigest

val String.sha256: ByteArray
    get() = MessageDigest.getInstance("SHA")
        .let {
            it.update(this.encodeToByteArray())
            it.digest()
        }

fun ByteArray.toHexString() = joinToString("") { "%02x".format(it) }