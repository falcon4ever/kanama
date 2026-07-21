package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty

@ScriptClass(attachTo = "Node")
class ResourceOwnerSmoke(val godotObject: MemorySegment) {
  @ScriptProperty var smokeResource: SmokeResource? = null

  @OnReady
  fun ready() {
    System.err.println(
      "[kanama:kt] ResourceOwnerSmoke payload=${smokeResource?.payload} present=${smokeResource != null}"
    )
    val res = smokeResource
    System.err.println(
      "[kanama:kt] ResourceOwnerSmoke resource_slots int=${res?.customIntValue} " +
        "stream_present=${res?.stream != null} stream_length=${res?.stream?.getLength()} " +
        "mesh_present=${res?.mesh != null} shape_present=${res?.shape != null}"
    )
  }
}
