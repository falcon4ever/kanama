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
