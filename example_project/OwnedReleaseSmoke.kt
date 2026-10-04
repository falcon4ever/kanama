package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.BoxMesh
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.MainThread
import net.multigesture.kanama.api.MeshInstance3D
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Performance
import net.multigesture.kanama.api.Resource
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Task 132 probe, run by `scripts/runtime_smoke.sh` as its own scene (`owned_release_smoke.tscn`):
 * a forgotten `close()` is a late release, not a leak.
 * - Red/green: 10,000 owned `Resource.create()` wrappers are dropped without `close()`; once the GC
 *   has collected them and the main thread has drained their releases, Godot's object count is back
 *   to the baseline taken before they were made. With `KANAMA_GC_RELEASES=0` (the fallback off: the
 *   red run) the count stays 10,000 higher.
 * - No double release: a getter's owned `+1` that was closed AND then collected releases once
 *   (close() cancelled its cleanup), and 100 getter results dropped unclosed release theirs, so the
 *   mesh ends at exactly the two references it should have (this script's and the node's).
 *
 * Each frame posts a `System.gc()` until the count is back (or [MAX_FRAMES] pass), then prints one
 * line and quits -- after dropping one more owned Resource on the way out, which the shutdown GC
 * (D4) must release before Godot's leak report (the smoke asserts no `Leaked instance`).
 */
@ScriptClass(attachTo = "Node")
class OwnedReleaseSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  private var baseline = 0L
  private var afterDrop = 0L
  private var frames = 0
  private var mesh: BoxMesh? = null
  private var holder: MeshInstance3D? = null

  @OnReady
  fun ready() {
    val box = BoxMesh.create() // ours: refcount 1
    val node = MeshInstance3D(GodotHandle(ObjectCalls.constructObject("MeshInstance3D")))
    node.setMesh(box) // the node's: 2
    val closed = node.getMesh()!! // an owned +1: 3
    closed.close() // released, its cleanup cancelled: 2
    repeat(DROPPED_GETTERS) { node.getMesh() } // 100 owned +1s dropped unclosed: 102
    mesh = box
    holder = node

    baseline = objectCount()
    repeat(DROPPED_RESOURCES) { Resource.create() }
    afterDrop = objectCount()
    System.err.println("[kanama:kt] OwnedReleaseSmoke dropped=${afterDrop - baseline}")
    awaitRelease()
  }

  private fun awaitRelease() {
    MainThread.postNextFrame {
      frames += 1
      val now = objectCount()
      val refs = mesh!!.getReferenceCount()
      if ((backToBaseline(now) && refs == 2) || frames >= MAX_FRAMES) {
        report(now, refs)
      } else {
        System.gc()
        awaitRelease()
      }
    }
  }

  private fun report(now: Long, refs: Int) {
    System.err.println(
      "[kanama:kt] OwnedReleaseSmoke baseline=$baseline after_drop=$afterDrop after_gc=$now " +
        "back_to_baseline=${backToBaseline(now)} mesh_refcount=$refs frames=$frames"
    )
    holder?.let { ObjectCalls.destroyObject(it.handle.segment) }
    holder = null
    mesh?.close()
    mesh = null
    dropJustBeforeQuit()
    self.getTree()?.quit()
  }

  // A handful of engine objects come and go between frames on their own (one, in practice), so
  // "back" means within [SLACK] of the baseline -- against the 10,000 that were dropped.
  private fun backToBaseline(now: Long): Boolean = now - baseline <= SLACK

  // No frame runs after quit(): only the shutdown GC + drain can release this one.
  private fun dropJustBeforeQuit() {
    Resource.create()
  }

  private fun objectCount(): Long =
    Performance.getMonitor(Performance.Monitor.OBJECT_COUNT).toLong()

  private companion object {
    const val DROPPED_RESOURCES = 10_000
    const val DROPPED_GETTERS = 100
    const val MAX_FRAMES = 240
    const val SLACK = 16L
  }
}
