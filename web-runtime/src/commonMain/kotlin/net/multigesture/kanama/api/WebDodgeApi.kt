@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.AnimatedSprite2DBackendContractProbe
import net.multigesture.kanama.backend.CollisionShape2DBackendContractProbe
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.backend.LabelBackendContractProbe
import net.multigesture.kanama.backend.PathFollow2DBackendContractProbe
import net.multigesture.kanama.backend.RigidBody2DBackendContractProbe
import net.multigesture.kanama.backend.SpriteFramesBackendContractProbe
import net.multigesture.kanama.backend.TimerBackendContractProbe
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.web.WebObjectId

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

  fun getSpriteFrames(): SpriteFrames? =
    AnimatedSprite2DBackendContractProbe(backendHandle).getSpriteFrames()?.let {
      SpriteFrames(WebObjectId(it.backendToken().toInt()))
    }
}

class SpriteFrames(godotObject: GodotHandle) : GodotObject(godotObject) {
  fun getAnimationNames(): List<String> =
    SpriteFramesBackendContractProbe(backendHandle).getAnimationNames()
}

class RigidBody2D(godotObject: GodotHandle) : Node2D(godotObject) {
  var linearVelocity: Vector2
    get() = unsupportedWebGameplayFamily("RigidBody2D.get_linear_velocity")
    set(value) {
      RigidBody2DBackendContractProbe(backendHandle)
        .setLinearVelocity(GodotVector2(value.x.toFloat(), value.y.toFloat()))
    }
}

class CollisionShape2D(godotObject: GodotHandle) : Node2D(godotObject) {
  fun setDisabled(disabled: Boolean) {
    CollisionShape2DBackendContractProbe(backendHandle).setDisabled(disabled)
  }

  fun setDeferred(property: String, value: Boolean) {
    CollisionShape2DBackendContractProbe(backendHandle).setDeferredBool(property, value)
  }
}

class Timer(godotObject: GodotHandle) : Node(godotObject.toBackendHandle()) {
  fun start(timeSec: Double = -1.0) {
    TimerBackendContractProbe(backendHandle).start(timeSec)
  }

  fun stop() {
    TimerBackendContractProbe(backendHandle).stop()
  }
}

class PathFollow2D(godotObject: GodotHandle) : Node2D(godotObject) {
  var progressRatio: Double
    get() = unsupportedWebGameplayFamily("PathFollow2D.get_progress_ratio")
    set(value) {
      PathFollow2DBackendContractProbe(backendHandle).setProgressRatio(value)
    }
}

class Marker2D(godotObject: GodotHandle) : Node2D(godotObject)

class CanvasLayer(godotObject: GodotHandle) : Node(godotObject.toBackendHandle())

class Label(godotObject: GodotHandle) : CanvasItem(godotObject.toBackendHandle()) {
  var text: String
    get() = unsupportedWebGameplayFamily("Label.get_text")
    set(value) {
      LabelBackendContractProbe(backendHandle).setText(value)
    }
}

class Button(godotObject: GodotHandle) : CanvasItem(godotObject.toBackendHandle())
