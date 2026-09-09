package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: Crypto
 */
class Crypto(handle: MemorySegment) : RefCounted(handle) {
    fun generateRsa(size: Int): CryptoKey? {
        checkOpen()
        return CryptoKey.wrap(ObjectCalls.ptrcallWithIntArgRetObject(generateRsaBind, handle, size))
    }

    fun generateSelfSignedCertificate(key: CryptoKey?, issuerName: String = "CN=myserver,O=myorganisation,C=IT", notBefore: String = "20140101000000", notAfter: String = "20340101000000"): X509Certificate? {
        checkOpen()
        return X509Certificate.wrap(ObjectCalls.ptrcallWithObjectThreeStringArgsRetObject(generateSelfSignedCertificateBind, handle, key?.requireOpenHandle() ?: MemorySegment.NULL, issuerName, notBefore, notAfter))
    }

    companion object {
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
