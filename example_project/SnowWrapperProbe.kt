package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.NoiseTexture2D
import net.multigesture.kanama.api.ShaderMaterial
import net.multigesture.kanama.api.Sprite3D
import net.multigesture.kanama.api.SubViewport

@ScriptClass(attachTo = "Node")
class SnowWrapperProbe(val godotObject: GodotHandle) {
  @Export var snowShader: ShaderMaterial? = null

  @Export var heightMap: NoiseTexture2D? = null

  @Export var depthViewport: SubViewport? = null

  @Export var cursorSprite: Sprite3D? = null
}
