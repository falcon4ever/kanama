@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.InitialGodotCallDescriptors as D
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.web.WebObjectId
import net.multigesture.kanama.web.WebPackedValues

/**
 * Space-state ray queries with desktop's API (hand-shaped: `WEB_HANDSHAPED` and
 * `WEB_INERT_FACADES` in the wrapper generator). A ray is one engine crossing that mints no handle:
 * the parameters below are a Kotlin value, the crossing's receiver is the querying node, and the
 * applier builds Godot's `PhysicsRayQueryParameters3D` from the parts in that node's world. A RID
 * crosses as its decimal id, as everywhere on Web; the engine's answer comes back through the
 * Variant codec, its collider as the body's one handle.
 */

/**
 * Desktop's ray query parameters (`create`, the eight properties and their accessors) as a Kotlin
 * value: nothing crosses until [PhysicsDirectSpaceState3D.intersectRay]. `AutoCloseable` like
 * desktop's `RefCounted`, so `use { }` compiles; [close] has nothing to release.
 */
class PhysicsRayQueryParameters3D
private constructor(from: Vector3, to: Vector3, collisionMask: Long, exclude: List<RID>) :
  AutoCloseable {
  var from: Vector3 = from

  var to: Vector3 = to

  /** Godot's uint32 mask (0..0xffffffff, checked at the query). */
  var collisionMask: Long = collisionMask

  /** The RIDs of the objects the ray ignores (`CollisionObject3D.getRid()`). */
  var exclude: List<RID> = exclude.toList()
    set(value) {
      field = value.toList()
    }

  var collideWithBodies: Boolean = true

  var collideWithAreas: Boolean = false

  var hitFromInside: Boolean = false

  var hitBackFaces: Boolean = true

  fun setFrom(from: Vector3) {
    this.from = from
  }

  fun getFrom(): Vector3 = from

  fun setTo(to: Vector3) {
    this.to = to
  }

  fun getTo(): Vector3 = to

  fun setCollisionMask(collisionMask: Long) {
    this.collisionMask = collisionMask
  }

  fun getCollisionMask(): Long = collisionMask

  fun setExclude(exclude: List<RID>) {
    this.exclude = exclude
  }

  fun getExclude(): List<RID> = exclude

  fun setCollideWithBodies(enable: Boolean) {
    collideWithBodies = enable
  }

  fun isCollideWithBodiesEnabled(): Boolean = collideWithBodies

  fun setCollideWithAreas(enable: Boolean) {
    collideWithAreas = enable
  }

  fun isCollideWithAreasEnabled(): Boolean = collideWithAreas

  fun setHitFromInside(enable: Boolean) {
    hitFromInside = enable
  }

  fun isHitFromInsideEnabled(): Boolean = hitFromInside

  fun setHitBackFaces(enable: Boolean) {
    hitBackFaces = enable
  }

  fun isHitBackFacesEnabled(): Boolean = hitBackFaces

  override fun close() = Unit

  companion object {
    /** Desktop's `create`: nullable as there, never null here. */
    fun create(
      from: Vector3,
      to: Vector3,
      collisionMask: Long = 4294967295L,
      exclude: List<RID> = emptyList(),
    ): PhysicsRayQueryParameters3D? = PhysicsRayQueryParameters3D(from, to, collisionMask, exclude)
  }
}

/** The four query flags, in the applier's bit order. */
private fun PhysicsRayQueryParameters3D.flags(): Int =
  (if (collideWithBodies) 1 else 0) or
    (if (collideWithAreas) 2 else 0) or
    (if (hitFromInside) 4 else 0) or
    (if (hitBackFaces) 8 else 0)

/**
 * The direct space state of the querying node's world. Web adaptation: bound to that node, which
 * receives the crossing; the engine resolves its world on the applier side.
 */
class PhysicsDirectSpaceState3D internal constructor(private val owner: Node3D) {
  /**
   * Desktop's `intersectRay`: the engine's Dictionary as a Map (`position`, `normal`,
   * `face_index`, `collider_id`, `collider`, `shape`, `rid`), empty when the ray hits nothing. The
   * collider is the body's one handle (its script's, the one a lookup made, or one made for it that
   * lives as long as the body), so it `==` any other reference to the body. One crossing.
   */
  fun intersectRay(parameters: PhysicsRayQueryParameters3D): Map<String, Any?> {
    require(parameters.collisionMask in 0L..0xffffffffL) {
      "Kanama Web ray query collision mask must fit Godot's uint32 ABI (0..0xffffffff), " +
        "got ${parameters.collisionMask}"
    }
    val parts = ArrayList<String>(8 + parameters.exclude.size)
    val from = parameters.from
    val to = parameters.to
    parts += from.x.toString()
    parts += from.y.toString()
    parts += from.z.toString()
    parts += to.x.toString()
    parts += to.y.toString()
    parts += to.z.toString()
    parts += parameters.collisionMask.toString()
    parts += parameters.flags().toString()
    for (rid in parameters.exclude) parts += rid.value.toString()
    val packed =
      GodotBackendCalls.invokeStringListRetString(
        D.PHYSICSDIRECTSPACESTATE3D_INTERSECT_RAY,
        owner.backendHandle,
        parts,
      )
    if (packed.isEmpty()) return emptyMap()
    // `<collider handle><US><the other entries, one Variant-codec Dictionary>`.
    val colliderToken = packed.substringBefore('\u001f').toInt()
    val entries = WebPackedValues.decodeVariant(packed.substringAfter('\u001f'))
    check(entries is Map<*, *>) { "Kanama Web ray query returned $packed" }
    val collider = colliderToken.takeIf { it != 0 }?.let { GodotObject(WebObjectId(it)) }
    val hit = LinkedHashMap<String, Any?>(entries.size + 1)
    for ((key, value) in entries) {
      hit[key as String] = value
      // Godot's own order: the collider follows its instance id.
      if (key == "collider_id") hit["collider"] = collider
    }
    if ("collider" !in hit) hit["collider"] = collider
    return hit
  }
}

/**
 * The querying node's world: only [directSpaceState] (desktop's nullable spelling). A real
 * `World3D` handle would cost a crossing and a RefCounted handle per query, and nothing in the
 * corpus reads anything else off it. `AutoCloseable` like desktop's `Resource`.
 */
class World3D internal constructor(private val owner: Node3D) : AutoCloseable {
  val directSpaceState: PhysicsDirectSpaceState3D?
    get() = PhysicsDirectSpaceState3D(owner)

  fun getDirectSpaceState(): PhysicsDirectSpaceState3D? = directSpaceState

  override fun close() = Unit
}
