package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Provides access to advanced cryptographic functionalities.
 *
 * Generated from Godot docs: Crypto
 */
class Crypto(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Generates an RSA `CryptoKey` that can be used for creating self-signed certificates and passed
     * to `StreamPeerTLS.accept_stream`.
     *
     * Generated from Godot docs: Crypto.generate_rsa
     */
    fun generateRsa(size: Int): CryptoKey? {
        checkOpen()
        return CryptoKey.wrap(ObjectCalls.ptrcallWithIntArgRetObject(generateRsaBind, handle, size))
    }

    /**
     * Generates a self-signed `X509Certificate` from the given `CryptoKey` and `issuer_name`. The
     * certificate validity will be defined by `not_before` and `not_after` (first valid date and last
     * valid date). The `issuer_name` must contain at least "CN=" (common name, i.e. the domain name),
     * "O=" (organization, i.e. your company name), "C=" (country, i.e. 2 lettered ISO-3166 code of the
     * country the organization is based in). A small example to generate an RSA key and an X509
     * self-signed certificate.
     *
     * Generated from Godot docs: Crypto.generate_self_signed_certificate
     */
    fun generateSelfSignedCertificate(key: CryptoKey?, issuerName: String = "CN=myserver,O=myorganisation,C=IT", notBefore: String = "20140101000000", notAfter: String = "20340101000000"): X509Certificate? {
        checkOpen()
        return X509Certificate.wrap(ObjectCalls.ptrcallWithObjectThreeStringArgsRetObject(generateSelfSignedCertificateBind, handle, key?.requireOpenHandle() ?: MemorySegment.NULL, issuerName, notBefore, notAfter))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Crypto? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Crypto? =
            if (handle.address() == 0L) null else Crypto(handle)

        private const val GENERATE_RSA_HASH = 1237515462L
        private val generateRsaBind by lazy {
            ObjectCalls.getMethodBind("Crypto", "generate_rsa", GENERATE_RSA_HASH)
        }

        private const val GENERATE_SELF_SIGNED_CERTIFICATE_HASH = 492266173L
        private val generateSelfSignedCertificateBind by lazy {
            ObjectCalls.getMethodBind("Crypto", "generate_self_signed_certificate", GENERATE_SELF_SIGNED_CERTIFICATE_HASH)
        }
    }
}
