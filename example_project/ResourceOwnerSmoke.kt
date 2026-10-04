package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle

@ScriptClass(attachTo = "Node")
class ResourceOwnerSmoke(val godotObject: GodotHandle) {
  @Export var smokeResource: SmokeResource? = null

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
