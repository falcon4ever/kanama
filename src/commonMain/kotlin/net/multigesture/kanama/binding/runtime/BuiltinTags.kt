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
// Task 129 B: the Variant types a utility call converts its arguments to and decodes its return
// from
// (UtilityCalls), and the raw value kinds a decoded Variant return can carry (iOS). Held to the
// VariantType enum by scripts/check_pt_tag_tables.py.
const val VT_NIL = 0

const val VT_BOOL = 1

const val VT_INT = 2

const val VT_FLOAT = 3

const val VT_STRING = 4

const val VT_RECT2 = 7

const val VT_RECT2I = 8

const val VT_VECTOR3I = 10

const val VT_TRANSFORM2D = 11

const val VT_VECTOR4 = 12

const val VT_VECTOR4I = 13

const val VT_PLANE = 14

const val VT_AABB = 16

const val VT_PROJECTION = 19

const val VT_RID = 23

const val VT_PACKED_BYTE_ARRAY = 29

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

// Task 134 B: the iOS builtin-call frame passes argument slots as POD (PT_INT32 is passthrough in
// the
// shim) and a String argument as a C string the shim builds the Godot String from.
const val PT_INT32 = 2

const val PT_STRING = 16

// Task 129 B: the utility call (`kanama_ios_godot_utility_call`) builds a PackedByteArray argument
// from a KanamaIosPackedArgDesc and boxes a Variant argument from a KanamaIosVariantArgDesc.
const val PT_PACKED_BYTE_ARRAY = 31

const val PT_VARIANT = 38
