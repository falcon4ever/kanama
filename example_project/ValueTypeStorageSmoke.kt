package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node2D
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Projection
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector4

/**
 * Task 134 A2 probe, run by `scripts/runtime_smoke.sh` as its own scene
 * (`value_type_storage_smoke.tscn`), beside `value_type_storage_ref.gd`, which prints the same
 * three lines from GDScript in the same run; the smoke requires the two to be identical.
 *
 * Value types store their components at Godot's width, so a position written and read back is `==`
 * to the one written (and, as in GDScript, its `x` is not `== 0.1`), `toString()` is GDScript's
 * `str()`, and Kotlin-side arithmetic gives the engine's float32 bits.
 */
@ScriptClass(attachTo = "Node2D")
class ValueTypeStorageSmoke(godotObject: GodotHandle) :
  KanamaScript<Node2D>(godotObject, ::Node2D) {
  @OnReady
  fun ready() {
    val v = Vector2(0.1, 0.2)
    self.position = v
    val back = self.position
    report("roundtrip_eq=${back == v} str=$back x_eq_literal=${back.x == 0.1}")

    val q = Quaternion(0.1, 0.2, 0.3, 0.9)
    val values =
      listOf(
        Vector2(0.1, 0.2),
        Vector3(1, 2, 3),
        Vector2(12345.678, -0.000001),
        Vector4(0.1, 0.2, 0.3, 0.4),
        q,
        Plane(Vector3(0, 1, 0), 2.5),
        Color(0.123456, 1.0 / 3.0, 2.5, -0.00001),
        Rect2(Vector2(0.1, 0.2), Vector2(3, 4)),
        AABB(Vector3(1, 2, 3), Vector3(0.5, 0.25, 0.125)),
        Basis(q.normalized()),
        Transform3D(Basis.IDENTITY, Vector3(0.1, 0.2, 0.3)),
        Transform2D(Vector2(1, 0), Vector2(0, 1), Vector2(0.5, 0.25)),
        Projection.IDENTITY,
      )
    report("str=${values.joinToString("|")}")

    val a = Vector2(0.1, 0.2)
    val b = Vector2(0.7, 0.3)
    val c = Vector3(0.1, 0.2, 0.3)
    val d = Vector3(0.7, 0.11, 0.13)
    val q2 = Quaternion(0.5, 0.1, 0.2, 0.8)
    val basis = Basis(q.normalized())
    val ops =
      listOf(
        bits(a + b),
        bits(a - b),
        bits(a * 0.1),
        bits(a / 0.3),
        bits(-a),
        bits(a.dot(b)),
        bits(a.length()),
        bits(a.lengthSquared()),
        bits(c.cross(d)),
        bits(c.normalized()),
        bits(c.length()),
        bits(q * q2),
        bits(q.normalized()),
        bits(basis.x) + "/" + bits(basis.y) + "/" + bits(basis.z),
        bits(basis * c),
        bits(a.rotated(0.3)),
      )
    report("bits=${ops.joinToString("|")}")
    report("parity=${parity()}")
  }

  // The randomized parity row: PARITY_INPUTS fixed-seed inputs (the same xorshift32 stream as
  // value_type_storage_ref.gd), and per operation an FNV-1a hash over the float32 bits of every
  // result. Any operation whose bits differ from Godot's for any input changes its hash.
  private var seed = 2463534242L

  private fun nextRandom(): Long {
    seed = seed xor ((seed shl 13) and 0xFFFFFFFFL)
    seed = seed xor (seed ushr 17)
    seed = seed xor ((seed shl 5) and 0xFFFFFFFFL)
    return seed
  }

  private fun nextValue(): Double {
    val r = nextRandom()
    return ((r % 200001) - 100000) / 10000.0 * SCALES[((r ushr 24) % 7).toInt()]
  }

  private val hashes = linkedMapOf<String, Long>()

  private fun mix(name: String, value: Double) {
    val bits = value.toFloat().toRawBits().toLong() and 0xFFFFFFFFL
    hashes[name] = ((hashes[name] ?: 2166136261L) xor bits) * 16777619L and 0xFFFFFFFFL
  }

  private fun mix(name: String, v: Vector2) {
    mix(name, v.x)
    mix(name, v.y)
  }

  private fun mix(name: String, v: Vector3?) {
    if (v == null) {
      mix(name, -1.0e30)
      return
    }
    mix(name, v.x)
    mix(name, v.y)
    mix(name, v.z)
  }

  private fun mix(name: String, v: Vector4) {
    mix(name, v.x)
    mix(name, v.y)
    mix(name, v.z)
    mix(name, v.w)
  }

  private fun mix(name: String, q: Quaternion) {
    mix(name, q.x)
    mix(name, q.y)
    mix(name, q.z)
    mix(name, q.w)
  }

  private fun parity(): String {
    repeat(PARITY_INPUTS) {
      val p = DoubleArray(18) { nextValue() }
      val a = Vector2(p[0], p[1])
      val b = Vector2(p[2], p[3])
      val c = Vector3(p[4], p[5], p[6])
      val d = Vector3(p[7], p[8], p[9])
      val e = Vector4(p[10], p[11], p[12], p[13])
      val f = Vector4(p[14], p[15], p[16], p[17])
      val q = Quaternion(p[0], p[4], p[8], p[12])
      val q2 = Quaternion(p[1], p[5], p[9], p[13])
      val s = p[16]
      mix("v2_add", a + b)
      mix("v2_sub", a - b)
      mix("v2_mul", a * s)
      mix("v2_div", a / s)
      mix("v2_neg", -a)
      mix("v2_dot", a.dot(b))
      mix("v2_len", a.length())
      mix("v2_len2", a.lengthSquared())
      mix("v2_norm", a.normalized())
      mix("v2_dist", a.distanceTo(b))
      mix("v3_add", c + d)
      mix("v3_sub", c - d)
      mix("v3_mul", c * s)
      mix("v3_div", c / s)
      mix("v3_neg", -c)
      mix("v3_dot", c.dot(d))
      mix("v3_len", c.length())
      mix("v3_len2", c.lengthSquared())
      mix("v3_norm", c.normalized())
      mix("v3_cross", c.cross(d))
      mix("v3_dist", c.distanceTo(d))
      mix("v4_add", e + f)
      mix("v4_sub", e - f)
      mix("v4_mul", e * s)
      mix("v4_neg", -e)
      mix("v4_dot", e.dot(f))
      mix("v4_len", e.length())
      mix("q_mul", q * q2)
      mix("q_norm", q.normalized())
      mix("q_dot", q.dot(q2))
      mix("q_len", q.length())
      val basis = Basis(q.normalized())
      mix("basis_q", basis.x)
      mix("basis_q", basis.y)
      mix("basis_q", basis.z)
      mix("basis_xform", basis * c)
      mix("t3d_xform", Transform3D(basis, d) * c)
      val plane = Plane(d.normalized(), s)
      mix("plane_dist", plane.distanceTo(c))
      mix("plane_ray", plane.intersectsRay(c, Vector3(p[0], p[1], p[2])))
      mix("rect_area", Rect2(Vector2.ZERO, a).area())
      mix("aabb_volume", AABB(Vector3.ZERO, c).volume())
    }
    return "n=$PARITY_INPUTS " +
      hashes.entries.joinToString(" ") { (name, hash) -> "$name=${hash.toString(16)}" }
  }

  private companion object {
    const val PARITY_INPUTS = 256
    val SCALES = doubleArrayOf(0.001, 0.01, 0.1, 1.0, 10.0, 100.0, 1000.0)
  }

  private fun report(line: String) {
    System.err.println("[kanama:kt] ValueTypeStorage kotlin $line")
  }

  // The float32 bits as GDScript's `PackedFloat32Array([f]).to_byte_array().hex_encode()`.
  private fun bits(value: Double): String {
    val raw = value.toFloat().toRawBits()
    return (0 until 4).joinToString("") {
      ((raw shr (8 * it)) and 0xFF).toString(16).padStart(2, '0')
    }
  }

  private fun bits(v: Vector2): String = "${bits(v.x)},${bits(v.y)}"

  private fun bits(v: Vector3): String = "${bits(v.x)},${bits(v.y)},${bits(v.z)}"

  private fun bits(v: Quaternion): String = "${bits(v.x)},${bits(v.y)},${bits(v.z)},${bits(v.w)}"
}
