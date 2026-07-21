@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)

package net.multigesture.kanama.types

import kotlinx.cinterop.FloatVar

/**
 * Godot's `real_t` scalar on iOS.
 *
 * iOS currently supports the single-precision Godot template path only. Keep this API shape in sync
 * with the JVM `GodotReal` surface so future precision changes are centralized here instead of
 * scattered across value types and ptrcall helpers.
 */
typealias real_t = Float

typealias GodotRealVar = FloatVar

typealias GodotRealArray = FloatArray

object GodotReal {
  const val SIZE_BYTES: Long = 4L
  const val ALIGN_BYTES: Long = 4L

  fun fromNumber(value: Number): real_t = value.toFloat()

  fun fromDouble(value: Double): real_t = value.toFloat()

  fun fromFloat(value: Float): real_t = value

  fun toC(value: real_t): Float = value

  fun fromC(value: Float): real_t = value
}
