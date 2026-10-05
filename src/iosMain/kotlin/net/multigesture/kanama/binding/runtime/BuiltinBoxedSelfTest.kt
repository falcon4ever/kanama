package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.api.GD
import net.multigesture.kanama.builtins.bigrams
import net.multigesture.kanama.builtins.compress
import net.multigesture.kanama.builtins.decodeU32
import net.multigesture.kanama.builtins.decodeVar
import net.multigesture.kanama.builtins.decompress
import net.multigesture.kanama.builtins.getExtension
import net.multigesture.kanama.builtins.getStringFromUtf8
import net.multigesture.kanama.builtins.godotFormat
import net.multigesture.kanama.builtins.godotHexToInt
import net.multigesture.kanama.builtins.md5Buffer
import net.multigesture.kanama.builtins.num
import net.multigesture.kanama.builtins.splitFloats
import net.multigesture.kanama.builtins.toSnakeCase
import net.multigesture.kanama.builtins.toUpper
import net.multigesture.kanama.builtins.toUtf8Buffer
import net.multigesture.kanama.builtins.unicodeAt
import net.multigesture.kanama.types.NodePath

/**
 * Task 134 D2 rows of the OBJECTCALLS SELFTEST scene-init phase: Godot's String / NodePath /
 * PackedByteArray methods through the boxed builtin call (`kanama_ios_godot_builtin_call_boxed`).
 * One row per base, argument and return form the shim converts -- a String / NodePath /
 * PackedByteArray base, Godot's NULL instance (a static), String / Map / int arguments, String /
 * StringName / NodePath / int / bool / packed / Variant returns, a String past the 1024-byte inline
 * buffer -- each with an expected value a call that never ran cannot produce. No row may raise a
 * shim fault.
 */
internal fun builtinBoxedSelfTestRows(check: (String, Boolean) -> Unit) {
  val faultsBefore = ObjectCalls.faultCount()

  check(
    "builtin-string(get_extension(res://a/b.tar.gz) -> gz)",
    "res://a/b.tar.gz".getExtension() == "gz",
  )
  check(
    "builtin-string(to_snake_case(PlayerScore) -> player_score)",
    "PlayerScore".toSnakeCase() == "player_score",
  )
  check("builtin-string(static String.num(3.14159, 2) -> 3.14)", String.num(3.14159, 2L) == "3.14")
  check("builtin-string(unicode_at(2) of ab+emoji -> U+1F389)", "ab🎉".unicodeAt(2L) == 0x1F389L)
  check("builtin-string(split_floats -> [1.0, 2.5])", "1,2.5".splitFloats(",") == listOf(1.0, 2.5))
  check("builtin-string(bigrams(abc) -> [ab, bc])", "abc".bigrams() == listOf("ab", "bc"))
  check("builtin-string(md5_buffer -> 16 bytes)", "x".md5Buffer().size == 16)
  check(
    "builtin-string(godotFormat(Map) -> 1-x)",
    "{a}-{b}".godotFormat(mapOf<String, Any?>("a" to 1L, "b" to "x")) == "1-x",
  )
  check("builtin-string(to_upper of 3000 chars -> 3000)", "x".repeat(3000).toUpper().length == 3000)

  check("builtin-string(godotHexToInt(0x1F) -> 31)", "0x1F".godotHexToInt() == 31L)

  // NodePath's members are Godot's parse in Kotlin; hash() is the one NodePath base the shim
  // builds.
  val path = NodePath("Arm/Hand:position:x")
  val hash = path.hash()
  check(
    "builtin-nodepath(hash -> nonzero, stable)",
    hash != 0L && hash == NodePath("Arm/Hand:position:x").hash(),
  )
  check("builtin-nodepath(get_name(1) -> Hand: StringName)", path.getName(1L) == "Hand")
  check("builtin-nodepath(get_subname_count -> 2)", path.getSubnameCount() == 2L)
  check("builtin-nodepath(is_absolute -> false)", !path.isAbsolute())
  check("builtin-nodepath(slice(0, 1) -> Arm: NodePath)", path.slice(0L, 1L) == NodePath("Arm"))

  val text = "ünï 🎉"
  val utf8 = text.toUtf8Buffer()
  check("builtin-bytes(get_string_from_utf8 -> the text)", utf8.getStringFromUtf8() == text)
  check(
    "builtin-bytes(decompress(compress(deflate)) -> the bytes)",
    utf8.compress(1L).decompress(utf8.size.toLong(), 1L).contentEquals(utf8),
  )
  check(
    "builtin-bytes(decode_var(var_to_bytes(42)) -> 42: Variant)",
    GD.varToBytes(42L).decodeVar(0L) == 42L,
  )
  check(
    "builtin-bytes(decode_u32 (Kotlin) -> 0x04030201)",
    byteArrayOf(1, 2, 3, 4).decodeU32(0L) == 0x04030201L,
  )
  check("builtin(no kanama_ios_fault raised)", ObjectCalls.faultCount() == faultsBefore)
}
