package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.OverrideVirtual
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.types.Transform2D

/**
 * Task 29 probe: exercises the Transform2D virtual-return family against
 * PhysicsDirectBodyState2DExtension's virtual table.
 */
@ScriptClass(attachTo = "PhysicsDirectBodyState2DExtension")
class Body2dVirtualReturnProbe(val godotObject: MemorySegment) {

  // Transform2D value-type return (non-identity components so the smoke can
  // assert real values survived).
  @OverrideVirtual
  fun _get_transform(): Transform2D =
    Transform2D(
      net.multigesture.kanama.types.Vector2(1.0f, 2.0f),
      net.multigesture.kanama.types.Vector2(3.0f, 4.0f),
      net.multigesture.kanama.types.Vector2(5.0f, 6.0f),
    )
}
