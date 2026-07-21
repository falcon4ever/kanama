package net.multigesture.kanama.types

import kotlin.jvm.JvmInline

/**
 * A handle for a resource's unique identifier — an opaque 64-bit value. At ptrcall a Godot RID is a
 * single uint64 (POD passthrough on iOS, same width as an Object handle).
 */
@JvmInline
value class RID(val value: Long) {
  fun isValid(): Boolean = value != 0L

  companion object {
    val EMPTY = RID(0L)
  }
}
