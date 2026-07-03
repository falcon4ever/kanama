package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * TLS configuration for clients and servers.
 *
 * Generated from Godot docs: TLSOptions
 */
class TLSOptions(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Returns `true` if created with `TLSOptions.server`, `false` otherwise.
     *
     * Generated from Godot docs: TLSOptions.is_server
     */
    fun isServer(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isServerBind, handle)
    }

    /**
     * Returns `true` if created with `TLSOptions.client_unsafe`, `false` otherwise.
     *
     * Generated from Godot docs: TLSOptions.is_unsafe_client
     */
    fun isUnsafeClient(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUnsafeClientBind, handle)
    }

    /**
     * Returns the common name (domain name) override specified when creating with `TLSOptions.client`.
     *
     * Generated from Godot docs: TLSOptions.get_common_name_override
     */
    fun getCommonNameOverride(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCommonNameOverrideBind, handle)
    }

    /**
     * Returns the CA `X509Certificate` chain specified when creating with `TLSOptions.client` or
     * `TLSOptions.client_unsafe`.
     *
     * Generated from Godot docs: TLSOptions.get_trusted_ca_chain
     */
    fun getTrustedCaChain(): X509Certificate? {
        return X509Certificate.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTrustedCaChainBind, handle))
    }

    /**
     * Returns the `CryptoKey` specified when creating with `TLSOptions.server`.
     *
     * Generated from Godot docs: TLSOptions.get_private_key
     */
    fun getPrivateKey(): CryptoKey? {
        return CryptoKey.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPrivateKeyBind, handle))
    }

    /**
     * Returns the `X509Certificate` specified when creating with `TLSOptions.server`.
     *
     * Generated from Godot docs: TLSOptions.get_own_certificate
     */
    fun getOwnCertificate(): X509Certificate? {
        return X509Certificate.wrap(ObjectCalls.ptrcallNoArgsRetObject(getOwnCertificateBind, handle))
    }

    companion object {
        /**
         * Creates a TLS client configuration which validates certificates and their common names (fully
         * qualified domain names). You can specify a custom `trusted_chain` of certification authorities
         * (the default CA list will be used if `null`), and optionally provide a `common_name_override` if
         * you expect the certificate to have a common name other than the server FQDN. Note: On the Web
         * platform, TLS verification is always enforced against the CA list of the web browser. This is
         * considered a security feature.
         *
         * Generated from Godot docs: TLSOptions.client
         */
        fun client(trustedChain: X509Certificate?, commonNameOverride: String = ""): TLSOptions? {
            return TLSOptions.wrap(ObjectCalls.ptrcallWithObjectStringArgRetObject(clientBind, MemorySegment.NULL, trustedChain?.requireOpenHandle() ?: MemorySegment.NULL, commonNameOverride))
        }

        /**
         * Creates an unsafe TLS client configuration where certificate validation is optional. You can
         * optionally provide a valid `trusted_chain`, but the common name of the certificates will never
         * be checked. Using this configuration for purposes other than testing is not recommended. Note:
         * On the Web platform, TLS verification is always enforced against the CA list of the web browser.
         * This is considered a security feature.
         *
         * Generated from Godot docs: TLSOptions.client_unsafe
         */
        fun clientUnsafe(trustedChain: X509Certificate?): TLSOptions? {
            return TLSOptions.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(clientUnsafeBind, MemorySegment.NULL, trustedChain?.requireOpenHandle() ?: MemorySegment.NULL))
        }

        /**
         * Creates a TLS server configuration using the provided `key` and `certificate`. Note: The
         * `certificate` should include the full certificate chain up to the signing CA (certificates file
         * can be concatenated using a general purpose text editor).
         *
         * Generated from Godot docs: TLSOptions.server
         */
        fun server(key: CryptoKey?, certificate: X509Certificate?): TLSOptions? {
            return TLSOptions.wrap(ObjectCalls.ptrcallWithTwoObjectArgsRetObject(serverBind, MemorySegment.NULL, key?.requireOpenHandle() ?: MemorySegment.NULL, certificate?.requireOpenHandle() ?: MemorySegment.NULL))
        }

        @JvmStatic
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
