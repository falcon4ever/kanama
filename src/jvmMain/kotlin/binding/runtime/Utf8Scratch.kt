package net.multigesture.kanama.binding.runtime

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

/**
 * A reusable native UTF-8 buffer (task 134 D2 review): one per builtin frame, so String arguments
 * and returns cross without a per-call arena. It grows (doubling) when a text needs more; the old
 * buffer is left to the GC-backed arena.
 */
internal class Utf8Scratch(initialBytes: Long = 256L) {
  private var buffer: MemorySegment = Arena.ofAuto().allocate(initialBytes)

  /** The buffer as it is. */
  fun current(): MemorySegment = buffer

  /** The buffer, grown to at least [bytes]. */
  fun atLeast(bytes: Long): MemorySegment {
    if (buffer.byteSize() < bytes)
      buffer = Arena.ofAuto().allocate(maxOf(bytes, buffer.byteSize() * 2))
    return buffer
  }
}
