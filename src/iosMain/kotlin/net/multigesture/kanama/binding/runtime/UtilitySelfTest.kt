package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.Mathf
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3i

/**
 * Task 129 B rows of the OBJECTCALLS SELFTEST scene-init phase: the generated common `GD` through
 * the iOS utility-call seam (`kanama_ios_godot_get_utility_function` /
 * `kanama_ios_godot_utility_call`). Each path gets a row whose expected value a call that never ran
 * cannot produce: the frame path (float / int / bool), the seeded RNG, the boxed path (String /
 * PackedByteArray arguments, String / Variant / packed / Object / raw-value returns, the >1024-byte
 * pending paths, a typed overload), a vararg call past the shim's 16 stack cells, and the common
 * Mathf. Nothing here may raise a shim fault: the phase asserts the exact fault count.
 */
internal fun utilitySelfTestRows(check: (String, Boolean) -> Unit) {
  val faultsBefore = ObjectCalls.faultCount()

  // Frame path: arguments in the builtin frame's slots, the raw return read back.
  check("utility-frame(absf(-2.5) -> 2.5)", GD.absf(-2.5) == 2.5)
  check("utility-frame(lerpf(0, 10, 0.25) -> 2.5)", GD.lerpf(0.0, 10.0, 0.25) == 2.5)
  check("utility-frame(maxi(3, 9) -> 9)", GD.maxi(3L, 9L) == 9L)
  check("utility-frame(is_equal_approx(1, 1.000001) -> true)", GD.isEqualApprox(1.0, 1.000001))
  check("utility-frame(is_inf(1) -> false)", !GD.isInf(1.0))

  // Seeding: the engine's global RNG, so seed() then randi() repeats (iOS used kotlin.random
  // before).
  GD.seed(12345L)
  val first = GD.randi()
  GD.randi()
  GD.seed(12345L)
  check("utility-rand(seed -> the same randi again)", GD.randi() == first)
  check("utility-rand(randi_range(7, 7) -> 7)", GD.randiRange(7L, 7L) == 7L)

  // Boxed path: Variant varargs, String / PackedByteArray arguments, typed and Variant returns.
  check(
    "utility-boxed(str(1, a, 2.5, true) -> 1a2.5true)",
    GD.str(1L, "a", 2.5, true) == "1a2.5true",
  )
  check("utility-boxed(type_string(4) -> String)", GD.typeString(4L) == "String")
  check("utility-boxed(typeof(Vector2) -> 5)", GD.typeOf(Vector2(1.0, 2.0)) == 5L)
  check("utility-boxed(max(1, 5, 3) -> 5)", GD.max(1L, 5L, 3L) == 5L)
  check(
    "utility-boxed(lerp(Vector2) -> Vector2(0.5, 1))",
    GD.lerp(Vector2(0.0, 0.0), Vector2(2.0, 4.0), 0.25) == Vector2(0.5, 1.0),
  )
  check("utility-boxed(str_to_var(\"42\") -> 42)", GD.strToVar("42") == 42L)
  check(
    "utility-boxed(bytes_to_var(var_to_bytes(42)) -> 42)",
    GD.bytesToVar(GD.varToBytes(42L)) == 42L,
  )
  check("utility-boxed(rand_from_seed -> 2 values)", GD.randFromSeed(42L).size == 2)
  // Past the 16 stack cells, the shim's heap path: every argument must arrive.
  val twenty = Array<Any?>(20) { it.toLong() }
  check("utility-vararg(str of 20 values)", GD.str(*twenty) == (0 until 20).joinToString(""))

  // Past the 1024-byte inline buffers: a long String return comes back through the shim's pending
  // UTF-8 slot, a long PackedByteArray through the pending container slot, whole.
  val long = "x".repeat(5000)
  check("utility-long(str of 5000 chars -> 5000)", GD.str(long).length == 5000)
  check(
    "utility-long(bytes_to_var(var_to_bytes(5000 chars)) round-trip)",
    GD.bytesToVar(GD.varToBytes(long)) == long,
  )
  // A raw value kind decoded from a Variant return (Vector3i), and a typed overload (frame path).
  check(
    "utility-boxed(abs(Vector3i(-1, 2, -3)) -> Vector3i(1, 2, 3))",
    GD.abs(Vector3i(-1, 2, -3)) == Vector3i(1, 2, 3),
  )
  check("utility-typed(max(1.5, 2.0) -> 2.0: Double)", GD.max(1.5, 2.0) == 2.0)

  // Object return (owned decode) and the typed isInstanceValid.
  val segment = ObjectCalls.constructObject("Node")
  if (segment.address() != 0L) {
    val node = GodotObject(GodotHandle(segment))
    val id = node.instanceId
    check(
      "utility-object(instance_from_id -> the same object)",
      GD.instanceFromId(id)?.instanceId == id,
    )
    check("utility-object(isInstanceValid(live) -> true)", GD.isInstanceValid(node))
    ObjectCalls.destroyObject(segment)
    check("utility-object(isInstanceValid(freed) -> false)", !GD.isInstanceValid(node))
  } else check("utility-object(Node constructed)", false)

  // print goes to Godot's log now (it was Kotlin's stdout); the row is that it returns.
  GD.print("[kanama][ios][kn] utility self-test: GD.print through the utility call")
  // Mathf is common code: wrap exists on iOS (City-Builder needs it).
  check("mathf(wrap(7.5, 0, 5) -> 2.5)", Mathf.wrap(7.5, 0.0, 5.0) == 2.5)
  check("utility(no kanama_ios_fault raised)", ObjectCalls.faultCount() == faultsBefore)
}
