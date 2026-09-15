package net.multigesture.kanama.binding.runtime

/**
 * Godot's wire numbers for builtin (value-type) calls: the `Variant::Type` ids a builtin method is
 * resolved against, and the ptrcall argument tags a [BArg] carries.
 *
 * Common `const val`s, not members of the `expect object BuiltinCalls`: these are VALUES, and an
 * `expect` declaration cannot carry one. Declaring them per backend made the compiler prove only
 * that both sides have a `VT_BASIS` — not that both say 17 — which is the half that matters at the
 * ABI (task 119 finding 16). One declaration cannot disagree with itself.
 *
 * `VT_*` must match the engine's `Variant::Type` enum and the [VariantType] entries of the same
 * name; `PT_*` must match the `KANAMA_IOS_PT_*` enum in `ios/bootstrap/kanama_ios_shim.c`, which
 * dispatches on them. The desktop ptr-ABI is positional and untyped — the callee knows the layout —
 * so desktop carries the tags for the shared call sites and the shim's benefit.
 */
const val VT_VECTOR2 = 5

const val VT_VECTOR3 = 9

const val VT_QUATERNION = 15

const val VT_BASIS = 17

const val VT_TRANSFORM3D = 18

const val PT_BOOL = 1

const val PT_INT64 = 3

const val PT_FLOAT64 = 5

const val PT_VECTOR2 = 6

const val PT_VECTOR3 = 8

const val PT_TRANSFORM3D = 19

const val PT_QUATERNION = 20
