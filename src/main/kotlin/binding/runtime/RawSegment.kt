package net.multigesture.kanama.binding.runtime

import java.lang.foreign.MemorySegment

/**
 * The raw engine pointer: the address a [net.multigesture.kanama.api.GodotHandle] carries, named
 * once per backend under this one fully-qualified name.
 *
 * Desktop/Android is the JDK FFM `MemorySegment` (the Android plugin's PanamaPort source remap
 * rewrites it to `com.v7878.foreign.MemorySegment`); iOS aliases its Kotlin/Native shim of the same
 * name. Declaring the pointer here is what lets the shared wrapper tree stop naming
 * `java.lang.foreign` (task 104 step 3).
 *
 * Parcel C of task 104 makes the pair `expect`/`actual` — one declaration in common code, these
 * typealiases as its per-platform implementation — once the root is a single multiplatform module.
 * Until then each platform declares a plain typealias, exactly as `GodotHandle` did in step 1.
 */
typealias RawSegment = MemorySegment

/**
 * The null engine pointer: the receiver of a static engine call and the marshalling of a null
 * object argument. A Java `static final` field cannot actualize an `expect` companion member, so
 * this is a top-level value and stays one in parcel C.
 */
val NULL_SEGMENT: RawSegment = MemorySegment.NULL
