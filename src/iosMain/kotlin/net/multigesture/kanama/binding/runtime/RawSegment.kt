package net.multigesture.kanama.binding.runtime

import java.lang.foreign.MemorySegment

/**
 * iOS: the raw engine pointer is the Kotlin/Native `java.lang.foreign.MemorySegment` shim — an
 * opaque class over a `Long` address. Only the JVM forbids declaring a `java.*` package, so the
 * shim keeps the name the desktop sources used before the pointer had a Kanama name.
 *
 * The `expect` declaration and what it may promise are in
 * `src/commonMain/kotlin/net/multigesture/kanama/binding/runtime/RawSegment.expect.kt`.
 */
actual typealias RawSegment = MemorySegment

/** iOS: the shim's address-0 pointer. */
actual val NULL_SEGMENT: RawSegment = MemorySegment.NULL
