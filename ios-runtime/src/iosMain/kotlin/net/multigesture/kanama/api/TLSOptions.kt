package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: TLSOptions
 */
class TLSOptions(handle: MemorySegment) : RefCounted(handle) {
    fun isServer(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isServerBind, handle)
    }

    fun isUnsafeClient(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isUnsafeClientBind, handle)
    }

    fun getCommonNameOverride(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getCommonNameOverrideBind, handle)
    }

    fun getTrustedCaChain(): X509Certificate? {
        checkOpen()
        return X509Certificate.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTrustedCaChainBind, handle))
    }

    fun getPrivateKey(): CryptoKey? {
        checkOpen()
        return CryptoKey.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPrivateKeyBind, handle))
    }

    fun getOwnCertificate(): X509Certificate? {
        checkOpen()
        return X509Certificate.wrap(ObjectCalls.ptrcallNoArgsRetObject(getOwnCertificateBind, handle))
    }

    companion object {
        fun client(trustedChain: X509Certificate?, commonNameOverride: String = ""): TLSOptions? {
            return TLSOptions.wrap(ObjectCalls.ptrcallWithObjectStringArgRetObject(clientBind, MemorySegment.NULL, trustedChain?.requireOpenHandle() ?: MemorySegment.NULL, commonNameOverride))
        }

        fun clientUnsafe(trustedChain: X509Certificate?): TLSOptions? {
            return TLSOptions.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(clientUnsafeBind, MemorySegment.NULL, trustedChain?.requireOpenHandle() ?: MemorySegment.NULL))
        }

        fun server(key: CryptoKey?, certificate: X509Certificate?): TLSOptions? {
            return TLSOptions.wrap(ObjectCalls.ptrcallWithTwoObjectArgsRetObject(serverBind, MemorySegment.NULL, key?.requireOpenHandle() ?: MemorySegment.NULL, certificate?.requireOpenHandle() ?: MemorySegment.NULL))
        }

        fun fromHandle(handle: MemorySegment): TLSOptions? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TLSOptions? =
            if (handle.address() == 0L) null else TLSOptions(handle)

        private const val CLIENT_HASH = 3565000357L
        private val clientBind by lazy {
            ObjectCalls.getMethodBind("TLSOptions", "client", CLIENT_HASH)
        }

        private const val CLIENT_UNSAFE_HASH = 2090251749L
        private val clientUnsafeBind by lazy {
            ObjectCalls.getMethodBind("TLSOptions", "client_unsafe", CLIENT_UNSAFE_HASH)
        }

        private const val SERVER_HASH = 36969539L
        private val serverBind by lazy {
            ObjectCalls.getMethodBind("TLSOptions", "server", SERVER_HASH)
        }

        private const val IS_SERVER_HASH = 36873697L
        private val isServerBind by lazy {
            ObjectCalls.getMethodBind("TLSOptions", "is_server", IS_SERVER_HASH)
        }

        private const val IS_UNSAFE_CLIENT_HASH = 36873697L
        private val isUnsafeClientBind by lazy {
            ObjectCalls.getMethodBind("TLSOptions", "is_unsafe_client", IS_UNSAFE_CLIENT_HASH)
        }

        private const val GET_COMMON_NAME_OVERRIDE_HASH = 201670096L
        private val getCommonNameOverrideBind by lazy {
            ObjectCalls.getMethodBind("TLSOptions", "get_common_name_override", GET_COMMON_NAME_OVERRIDE_HASH)
        }

        private const val GET_TRUSTED_CA_CHAIN_HASH = 1120709175L
        private val getTrustedCaChainBind by lazy {
            ObjectCalls.getMethodBind("TLSOptions", "get_trusted_ca_chain", GET_TRUSTED_CA_CHAIN_HASH)
        }

        private const val GET_PRIVATE_KEY_HASH = 2119971811L
        private val getPrivateKeyBind by lazy {
            ObjectCalls.getMethodBind("TLSOptions", "get_private_key", GET_PRIVATE_KEY_HASH)
        }

        private const val GET_OWN_CERTIFICATE_HASH = 1120709175L
        private val getOwnCertificateBind by lazy {
            ObjectCalls.getMethodBind("TLSOptions", "get_own_certificate", GET_OWN_CERTIFICATE_HASH)
        }
    }
}
