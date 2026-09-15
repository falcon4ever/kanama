package net.multigesture.kanama.binding.runtime

import java.lang.foreign.MemorySegment

/**
 * The raw engine pointer: the address a [net.multigesture.kanama.api.GodotHandle] carries, named
 * once per backend under this one fully-qualified name.
 *
 * iOS aliases the Kotlin/Native `java.lang.foreign.MemorySegment` shim (a `Long` address behind an
 * opaque class); desktop/Android alias the JDK FFM type. Declaring the pointer here is what lets
 * the shared wrapper tree stop naming `java.lang.foreign` (task 104 step 3) — only the JVM forbids
 * declaring a `java.*` package, so the shim itself may keep its name on Native.
 *
 * Parcel C turns this into `expect class RawSegment` + `expect val NULL_SEGMENT` once the root is
 * one multiplatform module; until then each platform declares a plain typealias, exactly as
 * `GodotHandle` did in step 1.
 */
typealias RawSegment = MemorySegment

/**
 * The null engine pointer: the receiver of a static engine call and the marshalling of a null
 * object argument. A Java `static final` field cannot actualize an `expect` companion member, so
 * this is a top-level value and stays one in parcel C.
 */
val NULL_SEGMENT: RawSegment = MemorySegment.NULL
