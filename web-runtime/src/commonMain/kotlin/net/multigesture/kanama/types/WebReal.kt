package net.multigesture.kanama.types

// The Web half of the native generated `Real.kt` (task 134 A2): Godot's Web builds use float32
// `real_t`, so the value types store `Float`. The shared value-type sources
// (`src/commonMain/.../types/shared`, also compiled here) use these four declarations.

internal typealias GodotRealStorage = Float

internal const val REAL_IS_SINGLE: Boolean = true

/** A `Double` narrowed to `real_t` (rounded to nearest): what a value type stores. */
@Suppress("NOTHING_TO_INLINE")
internal inline fun narrowReal(value: Double): Float = value.toFloat()

/** A stored `real_t` widened to `Double` (exact): what a value type's property returns. */
@Suppress("NOTHING_TO_INLINE")
internal inline fun widenReal(value: Float): Double = value.toDouble()

/** A flat `real_t` buffer (the native `GodotRealArray`); the shared builtin formulas use it. */
internal typealias GodotRealArray = FloatArray
