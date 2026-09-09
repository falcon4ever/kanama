@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.InitialGodotCallDescriptors as D
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.web.WebObjectId

/**
 * Space-state ray query (hand-shaped, see `WEB_HANDSHAPED` in the wrapper generator). The applier
 * builds the query parameters engine-side, so RIDs never cross the Web seam and the receiver of
 * the crossing is the querying node, not a space-state handle.
 */

/**
 * Ray query inputs. Web adaptation: this never becomes an engine object — the applier constructs
 * `PhysicsRayQueryParameters3D` on its side, so the RID exclusion list stays engine-side too and
 * [exclude] carries the excluded body itself.
 */
class PhysicsRayQueryParameters3D
private constructor(
  internal val from: Vector3,
  internal val to: Vector3,
  internal val collisionMask: Long,
  internal val exclude: GodotObject?,
) {
  companion object {
    fun create(
      from: Vector3,
      to: Vector3,
      collisionMask: Long = 0xffffffffL,
      exclude: List<GodotObject> = emptyList(),
    ): PhysicsRayQueryParameters3D? {
      require(exclude.size <= 1) {
        "Kanama Web ray queries carry at most one exclusion (the corpus excludes only self)"
      }
      return PhysicsRayQueryParameters3D(from, to, collisionMask, exclude.firstOrNull())
    }
  }
}

/** A ray hit: `position` and `collider` mirror the engine Dictionary the desktop demo reads. */
class RayHit internal constructor(val position: Vector3, val collider: GodotObject?) {
  val isEmpty: Boolean
    get() = false
}

/**
 * Direct space state bound to the querying node. Web adaptation: the receiver of the crossing is
 * that node, and the engine resolves its world on the applier side.
 */
class PhysicsDirectSpaceState3D internal constructor(private val owner: Node3D) {
  /**
   * Returns null when the ray misses. The collider resolves to an already-tracked handle (script
   * or browser); untracked engine geometry reports no collider, which is all the demo needs — it
   * only ever compares the collider against a node it already holds.
   */
  fun intersectRay(query: PhysicsRayQueryParameters3D): RayHit? {
    val packed =
      GodotBackendCalls.invokeVector3Vector3LongObjectRetString(
        D.PHYSICSDIRECTSPACESTATE3D_INTERSECT_RAY,
        owner.backendHandle,
        query.from.toBackend(),
        query.to.toBackend(),
        query.collisionMask,
        query.exclude?.backendHandle,
      )
    val parts = packed.split('')
    require(parts.size == 5) { "Kanama Web ray query returned $packed" }
    if (parts[0] != "1") return null
    val colliderToken = parts[4].toInt()
    return RayHit(
      Vector3(parts[1].toDouble(), parts[2].toDouble(), parts[3].toDouble()),
      colliderToken.takeIf { it > 0 }?.let { GodotObject(WebObjectId(it)) },
    )
  }
}

class World3D internal constructor(private val owner: Node3D) {
  val directSpaceState: PhysicsDirectSpaceState3D
    get() = PhysicsDirectSpaceState3D(owner)
}

fun Node3D.getWorld3d(): World3D = World3D(this)
