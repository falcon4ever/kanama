package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Provides access to advanced cryptographic functionalities.
 *
 * Generated from Godot docs: Crypto
 */
class Crypto(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Generates a `PackedByteArray` of cryptographically secure random bytes with given `size`.
     *
     * Generated from Godot docs: Crypto.generate_random_bytes
     */
    fun generateRandomBytes(size: Int): ByteArray {
        return ObjectCalls.ptrcallWithIntArgRetByteArray(generateRandomBytesBind, handle, size)
    }

    /**
     * Generates an RSA `CryptoKey` that can be used for creating self-signed certificates and passed
     * to `StreamPeerTLS.accept_stream`.
     *
     * Generated from Godot docs: Crypto.generate_rsa
     */
    fun generateRsa(size: Int): CryptoKey? {
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
        return X509Certificate.wrap(ObjectCalls.ptrcallWithObjectThreeStringArgsRetObject(generateSelfSignedCertificateBind, handle, key?.requireOpenHandle() ?: MemorySegment.NULL, issuerName, notBefore, notAfter))
    }

    /**
     * Sign a given `hash` of type `hash_type` with the provided private `key`.
     *
     * Generated from Godot docs: Crypto.sign
     */
    fun sign(hashType: Long, hash: ByteArray, key: CryptoKey?): ByteArray {
        return ObjectCalls.ptrcallWithLongByteArrayObjectArgsRetByteArray(signBind, handle, hashType, hash, key?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Verify that a given `signature` for `hash` of type `hash_type` against the provided public
     * `key`.
     *
     * Generated from Godot docs: Crypto.verify
     */
    fun verify(hashType: Long, hash: ByteArray, signature: ByteArray, key: CryptoKey?): Boolean {
        return ObjectCalls.ptrcallWithLongTwoByteArrayObjectArgsRetBool(verifyBind, handle, hashType, hash, signature, key?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Encrypt the given `plaintext` with the provided public `key`. Note: The maximum size of accepted
     * plaintext is limited by the key size.
     *
     * Generated from Godot docs: Crypto.encrypt
     */
    fun encrypt(key: CryptoKey?, plaintext: ByteArray): ByteArray {
        return ObjectCalls.ptrcallWithObjectAndByteArrayArgRetByteArray(encryptBind, handle, key?.requireOpenHandle() ?: MemorySegment.NULL, plaintext)
    }

    /**
     * Decrypt the given `ciphertext` with the provided private `key`. Note: The maximum size of
     * accepted ciphertext is limited by the key size.
     *
     * Generated from Godot docs: Crypto.decrypt
     */
    fun decrypt(key: CryptoKey?, ciphertext: ByteArray): ByteArray {
        return ObjectCalls.ptrcallWithObjectAndByteArrayArgRetByteArray(decryptBind, handle, key?.requireOpenHandle() ?: MemorySegment.NULL, ciphertext)
    }

    /**
     * Generates an HMAC (https://en.wikipedia.org/wiki/HMAC) digest of `msg` using `key`. The
     * `hash_type` parameter is the hashing algorithm that is used for the inner and outer hashes.
     * Currently, only `HashingContext.HASH_SHA256` and `HashingContext.HASH_SHA1` are supported.
     *
     * Generated from Godot docs: Crypto.hmac_digest
     */
    fun hmacDigest(hashType: Long, key: ByteArray, msg: ByteArray): ByteArray {
        return ObjectCalls.ptrcallWithLongAndTwoByteArrayArgsRetByteArray(hmacDigestBind, handle, hashType, key, msg)
    }

    /**
     * Compares two `PackedByteArray`s for equality without leaking timing information in order to
     * prevent timing attacks. See this blog post
     * (https://paragonie.com/blog/2015/11/preventing-timing-attacks-on-string-comparison-with-double-hmac-strategy)
     * for more information.
     *
     * Generated from Godot docs: Crypto.constant_time_compare
     */
    fun constantTimeCompare(trusted: ByteArray, received: ByteArray): Boolean {
        return ObjectCalls.ptrcallWithTwoByteArrayArgsRetBool(constantTimeCompareBind, handle, trusted, received)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Crypto? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Crypto? =
            if (handle.address() == 0L) null else Crypto(handle)

        private const val GENERATE_RANDOM_BYTES_HASH = 47165747L
        private val generateRandomBytesBind by lazy {
            ObjectCalls.getMethodBind("Crypto", "generate_random_bytes", GENERATE_RANDOM_BYTES_HASH)
        }

        private const val GENERATE_RSA_HASH = 1237515462L
        private val generateRsaBind by lazy {
            ObjectCalls.getMethodBind("Crypto", "generate_rsa", GENERATE_RSA_HASH)
        }

        private const val GENERATE_SELF_SIGNED_CERTIFICATE_HASH = 492266173L
        private val generateSelfSignedCertificateBind by lazy {
            ObjectCalls.getMethodBind("Crypto", "generate_self_signed_certificate", GENERATE_SELF_SIGNED_CERTIFICATE_HASH)
        }

        private const val SIGN_HASH = 1673662703L
        private val signBind by lazy {
            ObjectCalls.getMethodBind("Crypto", "sign", SIGN_HASH)
        }

        private const val VERIFY_HASH = 2805902225L
        private val verifyBind by lazy {
            ObjectCalls.getMethodBind("Crypto", "verify", VERIFY_HASH)
        }

        private const val ENCRYPT_HASH = 2361793670L
        private val encryptBind by lazy {
            ObjectCalls.getMethodBind("Crypto", "encrypt", ENCRYPT_HASH)
        }

        private const val DECRYPT_HASH = 2361793670L
        private val decryptBind by lazy {
            ObjectCalls.getMethodBind("Crypto", "decrypt", DECRYPT_HASH)
        }

        private const val HMAC_DIGEST_HASH = 2368951203L
        private val hmacDigestBind by lazy {
            ObjectCalls.getMethodBind("Crypto", "hmac_digest", HMAC_DIGEST_HASH)
        }

        private const val CONSTANT_TIME_COMPARE_HASH = 1024142237L
        private val constantTimeCompareBind by lazy {
            ObjectCalls.getMethodBind("Crypto", "constant_time_compare", CONSTANT_TIME_COMPARE_HASH)
        }
    }
}
