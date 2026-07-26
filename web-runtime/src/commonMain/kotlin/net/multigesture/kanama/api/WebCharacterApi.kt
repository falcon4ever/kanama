@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.AnimationStateMachinePlaybackBackendContractProbe
import net.multigesture.kanama.backend.AudioStreamPlayer3DBackendContractProbe
import net.multigesture.kanama.backend.GodotObjectBackendContractProbe
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.backend.NodeBackendContractProbe
import net.multigesture.kanama.web.WebObjectId

/**
 * Web API surface for the 3D character-controller tutorial (Task 60e corpus).
 *
 * Shared Godot classes live in [WebGodotApi]/[WebPlatformerApi]/[WebFpsApi]; the AnimationTree
 * state-machine and positional-audio surface the tutorial drives lives here and is scanned by the
 * fail-loud Web gameplay coverage gate.
 */

/** AnimationTree state-machine playback resolved through the mixer's parameter object. */
class AnimationNodeStateMachinePlayback internal constructor(godotObject: GodotHandle) :
  GodotObject(godotObject) {
  /** Queue a transition to a named state (reset_on_teleport baked to Godot's default). */
  fun travel(state: String) {
    AnimationStateMachinePlaybackBackendContractProbe(backendHandle).travel(state)
  }
}

/** AnimationPlayer/AnimationTree base: the tutorial drives an AnimationTree state machine. */
class AnimationMixer(godotObject: GodotHandle) : Node(godotObject.toBackendHandle()) {
  /** Resolve the playback object behind a `parameters/.../playback` property. */
  fun getStateMachinePlayback(path: String): AnimationNodeStateMachinePlayback =
    GodotObjectBackendContractProbe(backendHandle).getObjectProperty(path)?.let { handle ->
      AnimationNodeStateMachinePlayback(WebObjectId(handle.backendToken().toInt()))
    } ?: error("AnimationMixer parameter '$path' is not an AnimationNodeStateMachinePlayback")

  /** Double-valued blend parameter write (the run tilt add_amount). */
  fun setParameter(path: String, value: Double) {
    GodotObjectBackendContractProbe(backendHandle).setIndexedDouble(path, value)
  }

  object Signals {
    const val animationFinished: String = "animation_finished"
  }
}

/** Positional 3D audio (footsteps/jump/land on the tutorial player). */
class AudioStreamPlayer3D(godotObject: GodotHandle) : Node3D(godotObject) {
  fun play(fromPosition: Double = 0.0) {
    AudioStreamPlayer3DBackendContractProbe(backendHandle).play(fromPosition)
  }

  fun stop() {
    AudioStreamPlayer3DBackendContractProbe(backendHandle).stop()
  }
}

/** Enable or disable the node's physics processing (the tutorial pauses the player on win). */
fun Node.setPhysicsProcess(enable: Boolean) {
  NodeBackendContractProbe(backendHandle).setPhysicsProcess(enable)
}

/** World-space rotation basis derived from the synchronous global-rotation read (scale-1 rigs). */
val Node3D.globalBasis: Basis
  get() = Basis.fromEuler(globalRotation)
