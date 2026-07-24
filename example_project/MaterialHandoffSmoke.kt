package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.BoxMesh
import net.multigesture.kanama.api.FileAccess
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.MeshInstance3D
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.PackedScene
import net.multigesture.kanama.api.ResourceSaver
import net.multigesture.kanama.api.StandardMaterial3D
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

// Task 61 / issue #91 regression guard. A StandardMaterial3D.create()'d resource handed to the
// engine and then released via use{}/close() must survive so ResourceSaver.save can persist it.
// With the task-61 owning-create() fix, close() drops only the wrapper's own reference; the engine
// keeps its own, so the resource lives on. Pre-fix, close() dropped the engine's only reference and
// freed the material ("Parameter material is null"). Two distinct Ref<Material> sinks are exercised
// (surface override + material override) so the gate proves the ownership model, not one method.
// Every created resource is closed via use{} — "close what you create" (task 61 A1 contract).
@ScriptClass(attachTo = "Node")
class MaterialHandoffSmoke(godotObject: MemorySegment) : KanamaScript<Node>(godotObject, ::Node) {

  private val node = selfAs(::Node)

  // Parent each mesh instance under this (in-tree) node so scene teardown frees it and the mesh.
  private fun freshBox(): MeshInstance3D {
    val mi = MeshInstance3D.fromHandle(ObjectCalls.constructObject("MeshInstance3D"))!!
    BoxMesh.create().use {
      mi.mesh = it
    } // surface 0 exists; release the wrapper's ref, mesh keeps its
    node.addChild(mi)
    return mi
  }

  private fun savedSceneHasMaterial(label: String, mi: MeshInstance3D): Boolean {
    val path = "user://mat_handoff_$label.tscn"
    return PackedScene.create().use { packed ->
      packed.pack(mi)
      val err = ResourceSaver.save(packed, path)
      err == 0L && FileAccess.getFileAsString(path).contains("StandardMaterial3D")
    }
  }

  @OnReady
  fun ready() {
    // create() must return an OWNING wrapper: all backends construct via classdb_construct_object3
    // (task 62), which hands back a RefCounted already owned — the fresh resource's refcount reads
    // exactly 1. A non-owning construct would leave it fragile (issue #91); an over-reference would
    // read 2 and leak. (iOS guards the same invariant in its on-device self-test.)
    val createRefcount = StandardMaterial3D.create().use { it.getReferenceCount() }

    // Sink 1 — MeshInstance3D.setSurfaceOverrideMaterial (issue #91's exact path).
    val miSurface = freshBox()
    StandardMaterial3D.create()
      .apply { albedoColor = Color(1f, 0f, 0f) }
      .use { miSurface.setSurfaceOverrideMaterial(0, it) }
    val surfaceHasMaterial = savedSceneHasMaterial("surface", miSurface)

    // Sink 2 — GeometryInstance3D.materialOverride (a different Ref<Material> slot).
    val miOverride = freshBox()
    StandardMaterial3D.create()
      .apply { albedoColor = Color(0f, 1f, 0f) }
      .use { miOverride.materialOverride = it }
    val overrideHasMaterial = savedSceneHasMaterial("override", miOverride)

    System.err.println(
      "[kanama:kt] MaterialHandoffSmoke create_refcount=$createRefcount " +
        "surface_has_material=$surfaceHasMaterial override_has_material=$overrideHasMaterial"
    )
  }
}
