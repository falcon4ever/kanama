package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.OverrideVirtual
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.types.Projection
import net.multigesture.kanama.types.Vector4

/**
 * Task 29 probe: exercises the Projection virtual-return family against RenderSceneDataExtension's
 * virtual table.
 */
@ScriptClass(attachTo = "RenderSceneDataExtension")
class RenderSceneDataVirtualReturnProbe(val godotObject: MemorySegment) {

  // Projection value-type return (distinct per-column values so the smoke
  // can assert column order survived).
  @OverrideVirtual
  fun _get_cam_projection(): Projection =
    Projection(
      Vector4(1.0f, 0.0f, 0.0f, 0.0f),
      Vector4(0.0f, 2.0f, 0.0f, 0.0f),
      Vector4(0.0f, 0.0f, 3.0f, 0.0f),
      Vector4(0.0f, 0.0f, 0.0f, 4.0f),
    )
}
