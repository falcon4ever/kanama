package net.multigesture.kanama.binding.runtime

import java.lang.foreign.MemorySegment

/**
 * Desktop/Android: the raw engine pointer IS the JDK FFM `MemorySegment` (the Android plugin's
 * PanamaPort source remap rewrites the package to `com.v7878.foreign`, and strips the `actual `
 * modifier — Android compiles the copied sources as plain Kotlin, not as a KMP target).
 *
 * The `expect` declaration and what it may promise are in
 * `src/commonMain/kotlin/net/multigesture/kanama/binding/runtime/RawSegment.expect.kt`.
 */
actual typealias RawSegment = MemorySegment

/** Desktop/Android: FFM's shared zero-length pointer at address 0. */
actual val NULL_SEGMENT: RawSegment = MemorySegment.NULL
