package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.OverrideVirtual
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Task 29 probe: exercises the Transform3D, PackedVector3Array (`List<Vector3>`), and
 * PackedFloat64Array (`DoubleArray`) virtual-return families against XRInterfaceExtension's virtual
 * table.
 */
@ScriptClass(attachTo = "XRInterfaceExtension")
class XrVirtualReturnProbe(val godotObject: MemorySegment) {

  // Transform3D value-type return.
  @OverrideVirtual
  fun _get_camera_transform(): Transform3D = Transform3D(Basis.IDENTITY, Vector3(7.0f, 8.0f, 9.0f))

  // PackedVector3Array return (List<Vector3>).
  @OverrideVirtual
  fun _get_play_area(): List<Vector3> = listOf(Vector3(1.0f, 0.0f, 0.0f), Vector3(0.0f, 0.0f, 1.0f))

  // PackedFloat64Array return (DoubleArray — width-sensitive: 8-byte elements;
  // 1.0e308 does not survive a float32 round-trip, so a width regression fails loudly).
  @OverrideVirtual
  fun _get_projection_for_view(
    view: Long,
    aspect: Double,
    zNear: Double,
    zFar: Double,
  ): DoubleArray = doubleArrayOf(aspect, zNear, zFar, 1.0e308)
}
