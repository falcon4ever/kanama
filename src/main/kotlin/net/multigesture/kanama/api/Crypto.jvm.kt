package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Crypto (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Crypto waits on: ptrcallWithIntArgRetByteArray,
//   ptrcallWithLongAndTwoByteArrayArgsRetByteArray, ptrcallWithLongByteArrayObjectArgsRetByteArray,
//   ptrcallWithLongTwoByteArrayObjectArgsRetBool, ptrcallWithObjectAndByteArrayArgRetByteArray,
//   ptrcallWithTwoByteArrayArgsRetBool
// Index: docs/contributing/ios-shape-gap.md

/**
 * Generates a `PackedByteArray` of cryptographically secure random bytes with given `size`.
 *
 * Generated from Godot docs: Crypto.generate_random_bytes
 */
fun Crypto.generateRandomBytes(size: Int): ByteArray {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetByteArray(generateRandomBytesBind, handle, size)
}

/**
 * Sign a given `hash` of type `hash_type` with the provided private `key`.
 *
 * Generated from Godot docs: Crypto.sign
 */
fun Crypto.sign(hashType: Long, hash: ByteArray, key: CryptoKey?): ByteArray {
    checkOpen()
    return ObjectCalls.ptrcallWithLongByteArrayObjectArgsRetByteArray(signBind, handle, hashType, hash, key?.requireOpenHandle() ?: MemorySegment.NULL)
}

/**
 * Verify that a given `signature` for `hash` of type `hash_type` against the provided public
 * `key`.
 *
 * Generated from Godot docs: Crypto.verify
 */
fun Crypto.verify(hashType: Long, hash: ByteArray, signature: ByteArray, key: CryptoKey?): Boolean {
    checkOpen()
    return ObjectCalls.ptrcallWithLongTwoByteArrayObjectArgsRetBool(verifyBind, handle, hashType, hash, signature, key?.requireOpenHandle() ?: MemorySegment.NULL)
}

/**
 * Encrypt the given `plaintext` with the provided public `key`. Note: The maximum size of accepted
 * plaintext is limited by the key size.
 *
 * Generated from Godot docs: Crypto.encrypt
 */
fun Crypto.encrypt(key: CryptoKey?, plaintext: ByteArray): ByteArray {
    checkOpen()
    return ObjectCalls.ptrcallWithObjectAndByteArrayArgRetByteArray(encryptBind, handle, key?.requireOpenHandle() ?: MemorySegment.NULL, plaintext)
}

/**
 * Decrypt the given `ciphertext` with the provided private `key`. Note: The maximum size of
 * accepted ciphertext is limited by the key size.
 *
 * Generated from Godot docs: Crypto.decrypt
 */
fun Crypto.decrypt(key: CryptoKey?, ciphertext: ByteArray): ByteArray {
    checkOpen()
    return ObjectCalls.ptrcallWithObjectAndByteArrayArgRetByteArray(decryptBind, handle, key?.requireOpenHandle() ?: MemorySegment.NULL, ciphertext)
}

/**
 * Generates an HMAC (https://en.wikipedia.org/wiki/HMAC) digest of `msg` using `key`. The
 * `hash_type` parameter is the hashing algorithm that is used for the inner and outer hashes.
 * Currently, only `HashingContext.HASH_SHA256` and `HashingContext.HASH_SHA1` are supported.
 *
 * Generated from Godot docs: Crypto.hmac_digest
 */
fun Crypto.hmacDigest(hashType: Long, key: ByteArray, msg: ByteArray): ByteArray {
    checkOpen()
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
fun Crypto.constantTimeCompare(trusted: ByteArray, received: ByteArray): Boolean {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoByteArrayArgsRetBool(constantTimeCompareBind, handle, trusted, received)
}

private const val GENERATE_RANDOM_BYTES_HASH = 47165747L
private val generateRandomBytesBind by lazy {
    ObjectCalls.getMethodBind("Crypto", "generate_random_bytes", GENERATE_RANDOM_BYTES_HASH)
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
