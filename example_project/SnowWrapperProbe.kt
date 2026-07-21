package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.api.NoiseTexture2D
import net.multigesture.kanama.api.ShaderMaterial
import net.multigesture.kanama.api.Sprite3D
import net.multigesture.kanama.api.SubViewport

@ScriptClass(attachTo = "Node")
class SnowWrapperProbe(val godotObject: MemorySegment) {
  @ScriptProperty var snowShader: ShaderMaterial? = null

  @ScriptProperty var heightMap: NoiseTexture2D? = null

  @ScriptProperty var depthViewport: SubViewport? = null

  @ScriptProperty var cursorSprite: Sprite3D? = null
}
