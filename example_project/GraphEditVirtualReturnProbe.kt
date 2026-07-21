package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.OverrideVirtual
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.types.Vector2

/**
 * Task 29 probe: exercises the PackedVector2Array (`List<Vector2>`) virtual-return family against
 * GraphEdit's virtual table.
 */
@ScriptClass(attachTo = "GraphEdit")
class GraphEditVirtualReturnProbe(val godotObject: MemorySegment) {

  // PackedVector2Array return (List<Vector2>).
  @OverrideVirtual
  fun _get_connection_line(fromPosition: Vector2, toPosition: Vector2): List<Vector2> =
    listOf(fromPosition, toPosition)
}
