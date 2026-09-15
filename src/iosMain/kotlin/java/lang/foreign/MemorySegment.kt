package java.lang.foreign

/**
 * Tiny source-compatibility handle for iOS Kotlin/Native: the raw Godot object pointer.
 *
 * The desktop API uses the JDK FFM `MemorySegment`. The iOS backend only needs opaque Godot object
 * pointers at this layer, so this facade stores the address as a `Long`.
 *
 * It is a SEALED INTERFACE, not a class, because it is one half of an `expect`/`actual` pair: the
 * common `expect sealed interface RawSegment` (task 104 step 3 parcel C') is actualized by a
 * typealias to the JDK `MemorySegment` on desktop/Android and to this shim on iOS, and
 * `expect`/`actual` requires both sides to be the same kind of classifier and the same modality —
 * the JDK's `MemorySegment` is a sealed interface. Only the JVM forbids declaring a `java.*`
 * package, so the shim may keep the name the shared sources used before the pointer had a Kanama
 * name. Construction stays through [ofAddress]; the implementation is one private value holder,
 * exactly as it was when this was a class.
 */
sealed interface MemorySegment {
  fun address(): Long

  companion object {
    val NULL: MemorySegment = Address(0)

    fun ofAddress(address: Long): MemorySegment = Address(address)
  }
}

private class Address(private val value: Long) : MemorySegment {
  override fun address(): Long = value
}
