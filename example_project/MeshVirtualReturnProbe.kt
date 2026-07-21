package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.OverrideVirtual
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Vector3

/**
 * Task 29 probe (one of six; see `VirtualOverrideProbe` for the task-13 set): exercises the AABB,
 * generic Array (`List<Any?>`), and Dictionary (`Map<String, Any?>`) virtual-return families
 * against Mesh's virtual table.
 *
 * Families with no arg-compatible virtual in 4.7 are not probed anywhere:
 * `LongArray`/`PackedInt64Array` (no virtual returns it), `FloatArray`
 * (`AnimationNodeExtension._process_animation_node` takes an unsupported `PackedFloat64Array` arg),
 * `List<Color>`/`Rect2` (all candidates take unsupported `RID`/`Rect2`/`Variant` args). They share
 * the emitted shape of probed siblings (int32/float64 packed, vector2 packed, AABB respectively).
 */
@ScriptClass(attachTo = "Mesh")
class MeshVirtualReturnProbe(val godotObject: MemorySegment) {

  // AABB value-type return, boxed via initVariantFromAny.
  @OverrideVirtual
  fun _get_aabb(): AABB = AABB(Vector3(1.0f, 2.0f, 3.0f), Vector3(4.0f, 5.0f, 6.0f))

  // Generic Array return (List<Any?> -> Godot Array), mixed audited element types.
  @OverrideVirtual fun _surface_get_arrays(index: Long): List<Any?> = listOf(index, "kanama", true)

  // Dictionary return (Map<String, Any?> -> Godot Dictionary).
  @OverrideVirtual
  fun _surface_get_lods(index: Long): Map<String, Any?> = mapOf("lod" to index, "name" to "kanama")
}
