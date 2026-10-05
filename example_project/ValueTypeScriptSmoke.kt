package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.SignalConnection
import net.multigesture.kanama.generated.flats
import net.multigesture.kanama.generated.frames
import net.multigesture.kanama.generated.solids
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Projection
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i
import net.multigesture.kanama.types.Vector4
import net.multigesture.kanama.types.Vector4i

/**
 * Task 133 value types as script types: Rect2, Rect2i, Vector4, Vector4i, Plane, AABB, Transform2D,
 * Transform3D, Projection, Vector3i, Quaternion and Basis exported (with constant and literal
 * defaults), passed through functions and signals both ways. `value_type_script_smoke.tscn` stores
 * a value for each, for this script and its GDScript twin (`value_type_script_twin.gd`);
 * `value_type_script_driver.gd` drives both the same way and prints one line per side and step,
 * which `scripts/runtime_smoke.sh` requires to be identical.
 */
@ScriptClass(attachTo = "Node")
class ValueTypeScriptSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @Export var rect: Rect2 = Rect2(Vector2(1.0, 2.0), Vector2(3.0, 4.0))
  @Export var recti: Rect2i = Rect2i.ZERO
  @Export var vec4: Vector4 = Vector4.ONE
  @Export var vec4i: Vector4i = Vector4i(1, -2, 3, -4)
  @Export var plane: Plane = Plane.PLANE_XY
  @Export var box: AABB = AABB(Vector3(1.0, 2.0, 3.0), Vector3(4.0, 5.0, 6.0))
  @Export var xform2: Transform2D = Transform2D.FLIP_X
  @Export var xform3: Transform3D = Transform3D(Basis.IDENTITY, Vector3(1.0, 2.0, 3.0))
  @Export var proj: Projection = Projection.IDENTITY
  @Export var cell: Vector3i = Vector3i(1, 2, 3)
  @Export var rot: Quaternion = Quaternion.IDENTITY
  @Export var basis: Basis = Basis.FLIP_Y

  private val connections = mutableListOf<SignalConnection>()

  @Signal fun flats(rect: Rect2, recti: Rect2i, vec4: Vector4, vec4i: Vector4i) = Unit

  @Signal fun solids(plane: Plane, box: AABB, cell: Vector3i, rot: Quaternion) = Unit

  @Signal
  fun frames(xform2: Transform2D, xform3: Transform3D, proj: Projection, basis: Basis) = Unit

  fun echoRect(v: Rect2): Rect2 = v

  fun echoRecti(v: Rect2i): Rect2i = v

  fun echoVec4(v: Vector4): Vector4 = v

  fun echoVec4i(v: Vector4i): Vector4i = v

  fun echoPlane(v: Plane): Plane = v

  fun echoBox(v: AABB): AABB = v

  fun echoXform2(v: Transform2D): Transform2D = v

  fun echoXform3(v: Transform3D): Transform3D = v

  fun echoProj(v: Projection): Projection = v

  fun echoCell(v: Vector3i): Vector3i = v

  fun echoRot(v: Quaternion): Quaternion = v

  fun echoBasis(v: Basis): Basis = v

  /**
   * Typed handlers (the generated `flats` / `solids` / `frames` handles) that store what they
   * receive in the exports; the driver reads them back.
   */
  fun listen() {
    val owner = GodotObject(godotObject)
    connections +=
      flats.connect(owner) { a, b, c, d ->
        rect = a
        recti = b
        vec4 = c
        vec4i = d
      }
    connections +=
      solids.connect(owner) { a, b, c, d ->
        plane = a
        box = b
        cell = c
        rot = d
      }
    connections +=
      frames.connect(owner) { a, b, c, d ->
        xform2 = a
        xform3 = b
        proj = c
        basis = d
      }
  }

  fun unlisten() {
    connections.forEach { it.close() }
    connections.clear()
  }

  /** Emits the three signals with the current exports. */
  fun emitAll() {
    flats.emit(rect, recti, vec4, vec4i)
    solids.emit(plane, box, cell, rot)
    frames.emit(xform2, xform3, proj, basis)
  }
}
