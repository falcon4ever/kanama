package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Provides access to advanced cryptographic functionalities.
 *
 * Generated from Godot docs: Crypto
 */
class Crypto(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Generates a `PackedByteArray` of cryptographically secure random bytes with given `size`.
     *
     * Generated from Godot docs: Crypto.generate_random_bytes
     */
    fun generateRandomBytes(size: Int): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetByteArray(Binds.generateRandomBytesBind, segment, size)
    }

    /**
     * Generates an RSA `CryptoKey` that can be used for creating self-signed certificates and passed
     * to `StreamPeerTLS.accept_stream`.
     *
     * Generated from Godot docs: Crypto.generate_rsa
     */
    fun generateRsa(size: Int): CryptoKey? {
        checkOpen()
        return CryptoKey.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.generateRsaBind, segment, size))
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
        return X509Certificate.wrapOwned(ObjectCalls.ptrcallWithObjectThreeStringArgsRetObject(Binds.generateSelfSignedCertificateBind, segment, key?.requireOpenHandle() ?: NULL_SEGMENT, issuerName, notBefore, notAfter))
    }

    /**
     * Sign a given `hash` of type `hash_type` with the provided private `key`.
     *
     * Generated from Godot docs: Crypto.sign
     */
    fun sign(hashType: HashingContext.HashType, hash: ByteArray, key: CryptoKey?): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallWithLongByteArrayObjectArgsRetByteArray(Binds.signBind, segment, hashType.value, hash, key?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Verify that a given `signature` for `hash` of type `hash_type` against the provided public
     * `key`.
     *
     * Generated from Godot docs: Crypto.verify
     */
    fun verify(hashType: HashingContext.HashType, hash: ByteArray, signature: ByteArray, key: CryptoKey?): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongTwoByteArrayObjectArgsRetBool(Binds.verifyBind, segment, hashType.value, hash, signature, key?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Encrypt the given `plaintext` with the provided public `key`. Note: The maximum size of accepted
     * plaintext is limited by the key size.
     *
     * Generated from Godot docs: Crypto.encrypt
     */
    fun encrypt(key: CryptoKey?, plaintext: ByteArray): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectAndByteArrayArgRetByteArray(Binds.encryptBind, segment, key?.requireOpenHandle() ?: NULL_SEGMENT, plaintext)
    }

    /**
     * Decrypt the given `ciphertext` with the provided private `key`. Note: The maximum size of
     * accepted ciphertext is limited by the key size.
     *
     * Generated from Godot docs: Crypto.decrypt
     */
    fun decrypt(key: CryptoKey?, ciphertext: ByteArray): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectAndByteArrayArgRetByteArray(Binds.decryptBind, segment, key?.requireOpenHandle() ?: NULL_SEGMENT, ciphertext)
    }

    /**
     * Generates an HMAC (https://en.wikipedia.org/wiki/HMAC) digest of `msg` using `key`. The
     * `hash_type` parameter is the hashing algorithm that is used for the inner and outer hashes.
     * Currently, only `HashingContext.HashType.SHA256` and `HashingContext.HashType.SHA1` are
     * supported.
     *
     * Generated from Godot docs: Crypto.hmac_digest
     */
    fun hmacDigest(hashType: HashingContext.HashType, key: ByteArray, msg: ByteArray): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallWithLongAndTwoByteArrayArgsRetByteArray(Binds.hmacDigestBind, segment, hashType.value, key, msg)
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
        checkOpen()
        return ObjectCalls.ptrcallWithTwoByteArrayArgsRetBool(Binds.constantTimeCompareBind, segment, trusted, received)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Crypto? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Crypto? =
            if (handle.address() == 0L) null else RefCounted.owned(Crypto(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Crypto? =
            if (handle.address() == 0L) null else Crypto(GodotHandle(handle))
    }

    private object Binds {
        private const val GENERATE_RANDOM_BYTES_HASH = 47165747L
        @JvmField
        val generateRandomBytesBind =
            ObjectCalls.getMethodBind("Crypto", "generate_random_bytes", GENERATE_RANDOM_BYTES_HASH)

        private const val GENERATE_RSA_HASH = 1237515462L
        @JvmField
        val generateRsaBind =
            ObjectCalls.getMethodBind("Crypto", "generate_rsa", GENERATE_RSA_HASH)

        private const val GENERATE_SELF_SIGNED_CERTIFICATE_HASH = 492266173L
        @JvmField
        val generateSelfSignedCertificateBind =
            ObjectCalls.getMethodBind("Crypto", "generate_self_signed_certificate", GENERATE_SELF_SIGNED_CERTIFICATE_HASH)

        private const val SIGN_HASH = 1673662703L
        @JvmField
        val signBind =
            ObjectCalls.getMethodBind("Crypto", "sign", SIGN_HASH)

        private const val VERIFY_HASH = 2805902225L
        @JvmField
        val verifyBind =
            ObjectCalls.getMethodBind("Crypto", "verify", VERIFY_HASH)

        private const val ENCRYPT_HASH = 2361793670L
        @JvmField
        val encryptBind =
            ObjectCalls.getMethodBind("Crypto", "encrypt", ENCRYPT_HASH)

        private const val DECRYPT_HASH = 2361793670L
        @JvmField
        val decryptBind =
            ObjectCalls.getMethodBind("Crypto", "decrypt", DECRYPT_HASH)

        private const val HMAC_DIGEST_HASH = 2368951203L
        @JvmField
        val hmacDigestBind =
            ObjectCalls.getMethodBind("Crypto", "hmac_digest", HMAC_DIGEST_HASH)

        private const val CONSTANT_TIME_COMPARE_HASH = 1024142237L
        @JvmField
        val constantTimeCompareBind =
            ObjectCalls.getMethodBind("Crypto", "constant_time_compare", CONSTANT_TIME_COMPARE_HASH)
    }
}
