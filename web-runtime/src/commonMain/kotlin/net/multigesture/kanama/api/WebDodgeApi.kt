@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.AnimatedSprite2DBackendContractProbe
import net.multigesture.kanama.backend.InternalKanamaBackendApi

/**
 * Web API surface specific to the dodge-the-creeps 2D demo (Task 60b).
 *
 * Shared Godot classes live in [WebGodotApi]; demo-driven classes that the 2D-corpus work introduces
 * live here and are scanned by the fail-loud Web gameplay coverage gate. Write-only properties whose
 * getters dodge never calls surface as nonblocking unsupported families.
 */
class AnimatedSprite2D(godotObject: GodotHandle) : Node2D(godotObject) {
  var flipV: Boolean
    get() = unsupportedWebGameplayFamily("AnimatedSprite2D.is_flipped_v")
    set(value) {
      AnimatedSprite2DBackendContractProbe(backendHandle).setFlipV(value)
    }

  var flipH: Boolean
    get() = unsupportedWebGameplayFamily("AnimatedSprite2D.is_flipped_h")
    set(value) {
      AnimatedSprite2DBackendContractProbe(backendHandle).setFlipH(value)
    }

  var animation: String
    get() = unsupportedWebGameplayFamily("AnimatedSprite2D.get_animation")
    set(value) {
      AnimatedSprite2DBackendContractProbe(backendHandle).setAnimation(value)
    }

  fun play() {
    AnimatedSprite2DBackendContractProbe(backendHandle).play()
  }

  fun stop() {
    AnimatedSprite2DBackendContractProbe(backendHandle).stop()
  }
}
