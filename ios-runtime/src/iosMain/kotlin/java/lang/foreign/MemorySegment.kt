package java.lang.foreign

/**
 * Tiny source-compatibility handle for iOS Kotlin/Native script probes.
 *
 * The desktop API uses the JDK FFM [MemorySegment]. The iOS backend only needs
 * opaque Godot object pointers at this layer, so the Native facade stores the
 * address as a `Long`.
 */
class MemorySegment private constructor(private val value: Long) {
    fun address(): Long = value

    companion object {
        val NULL: MemorySegment = MemorySegment(0)

        fun ofAddress(address: Long): MemorySegment = MemorySegment(address)
    }
}
