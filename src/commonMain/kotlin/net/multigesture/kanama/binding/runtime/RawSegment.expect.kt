package net.multigesture.kanama.binding.runtime

/**
 * The raw engine pointer: the address a [net.multigesture.kanama.api.GodotHandle] carries, declared
 * once for every backend (task 104 step 3).
 *
 * A `sealed interface`, not a class, because `expect`/`actual` requires both sides to be the same
 * kind of classifier AND the same modality, and the JDK's `MemorySegment` is a sealed interface:
 * the iOS shim follows it (one private implementation beside it, as it had when it was a class).
 *
 * `address()` is the whole common surface — the generated wrapper tree and the runtime only ever
 * ask a pointer for its address (measured: 981 call sites, no other member) — so the two backends
 * can actualize it with a `typealias` to the pointer type they already have: the JDK FFM
 * `MemorySegment` on desktop/Android (rewritten to `com.v7878.foreign.MemorySegment` by the
 * PanamaPort source remap) and the Kotlin/Native shim of the same name on iOS. Only the JVM forbids
 * declaring a `java.*` package, which is why the iOS shim may keep its name.
 *
 * Declaring the pointer here is what lets the shared sources stop naming `java.lang.foreign` (step
 * 3 parcel A) and what makes the compiler, rather than a script, the proof that both backends have
 * it.
 *
 * Files named `*.expect.kt` hold `expect` declarations only: the Android source remap skips them by
 * name (an `expect` has no body to remap) and compiles the `actual` from `src/jvmMain` with its
 * `actual ` modifier stripped.
 */
expect sealed interface RawSegment {
  fun address(): Long
}

/**
 * The null engine pointer: the receiver of a static engine call and the marshalling of a null
 * object argument.
 *
 * Top level, not a companion member of [RawSegment], because a Java `static final` field
 * (`MemorySegment.NULL`) cannot actualize an `expect` companion member.
 */
expect val NULL_SEGMENT: RawSegment
