@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.AnimationStateMachinePlaybackBackendContractProbe
import net.multigesture.kanama.backend.AudioStreamPlayer3DBackendContractProbe
import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.GodotObjectBackendContractProbe
import net.multigesture.kanama.backend.GodotVector3
import net.multigesture.kanama.backend.InitialGodotCallDescriptors
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.backend.NodeBackendContractProbe
import net.multigesture.kanama.backend.RigidBody3DBackendContractProbe
import net.multigesture.kanama.types.Vector3
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
  private var lastTravelled: String = ""

  /** Queue a transition to a named state (reset_on_teleport baked to Godot's default). */
  fun travel(state: String) {
    lastTravelled = state
    // Children free before parents during scene teardown: a dying skin's playback handle
    // may already be released while its owner still forwards states (Tween.kill precedent).
    if (!isWebBrowserHandleLive(handle.value)) return
    AnimationStateMachinePlaybackBackendContractProbe(backendHandle).travel(state)
  }

  /**
   * Web adaptation: the last travelled state, tracked client-side (the engine node may still
   * be mid-transition; the corpus only compares against its own travel targets).
   */
  fun getCurrentNode(): String = lastTravelled
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

  /** Long parameters (one-shot requests) ride the double family; Godot coerces Variants. */
  fun setParameter(path: String, value: Long) {
    GodotObjectBackendContractProbe(backendHandle).setIndexedDouble(path, value.toDouble())
  }

  fun setActive(active: Boolean) {
    GodotBackendCalls.invokeBoolArg(
      InitialGodotCallDescriptors.ANIMATIONMIXER_SET_ACTIVE,
      backendHandle,
      active,
    )
  }

  /** Resolve a named Animation resource to a tracked handle (loop-mode forcing). */
  fun getAnimation(name: String): Animation? =
    GodotBackendCalls.invokeNodePathRetHandle(
        InitialGodotCallDescriptors.ANIMATIONMIXER_GET_ANIMATION,
        backendHandle,
        name,
      )
      ?.let { Animation(WebObjectId(it.backendToken().toInt())) }

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

  fun setPitchScale(scale: Double) {
    AudioStreamPlayer3DBackendContractProbe(backendHandle).setPitchScale(scale)
  }

  fun isPlaying(): Boolean =
    GodotBackendCalls.invokeNoArgsRetBool(
      InitialGodotCallDescriptors.AUDIOSTREAMPLAYER3D_IS_PLAYING,
      backendHandle,
    )

  object Signals {
    const val finished: String = "finished"
  }
}

/** Dynamic rigid body (the third-person controller's destructible-box shards). */
open class RigidBody3D(godotObject: GodotHandle) : PhysicsBody3D(godotObject) {
  /** Write-only on Web: shards flip freeze off to enter simulation. */
  var freeze: Boolean
    get() = unsupportedWebGameplayFamily("RigidBody3D.is_freeze_enabled")
    set(value) {
      RigidBody3DBackendContractProbe(backendHandle).setFreezeEnabled(value)
    }

  /** Write-only on Web: shards clear sleeping so the force applies immediately. */
  var sleeping: Boolean
    get() = unsupportedWebGameplayFamily("RigidBody3D.is_sleeping")
    set(value) {
      RigidBody3DBackendContractProbe(backendHandle).setSleeping(value)
    }

  fun applyForce(force: Vector3, position: Vector3 = Vector3.ZERO) {
    RigidBody3DBackendContractProbe(backendHandle)
      .applyForce(
        GodotVector3(force.x.toFloat(), force.y.toFloat(), force.z.toFloat()),
        GodotVector3(position.x.toFloat(), position.y.toFloat(), position.z.toFloat()),
      )
  }

  fun setCollisionMaskValue(layer: Long, value: Boolean) {
    RigidBody3DBackendContractProbe(backendHandle).setCollisionMaskValue(layer, value)
  }

  fun applyImpulse(impulse: Vector3, position: Vector3 = Vector3.ZERO) {
    GodotBackendCalls.invokeVector3Vector3Arg(
      InitialGodotCallDescriptors.RIGIDBODY3D_APPLY_IMPULSE,
      backendHandle,
      GodotVector3(impulse.x.toFloat(), impulse.y.toFloat(), impulse.z.toFloat()),
      GodotVector3(position.x.toFloat(), position.y.toFloat(), position.z.toFloat()),
    )
  }

  fun applyCentralImpulse(impulse: Vector3) {
    GodotBackendCalls.invokeVector3Arg(
      InitialGodotCallDescriptors.RIGIDBODY3D_APPLY_CENTRAL_IMPULSE,
      backendHandle,
      GodotVector3(impulse.x.toFloat(), impulse.y.toFloat(), impulse.z.toFloat()),
    )
  }

  /** Write-only on Web. */
  var gravityScale: Double
    get() = unsupportedWebGameplayFamily("RigidBody3D.get_gravity_scale")
    set(value) {
      GodotBackendCalls.invokeDoubleArg(
        InitialGodotCallDescriptors.RIGIDBODY3D_SET_GRAVITY_SCALE,
        backendHandle,
        value,
      )
    }

  /** Write-only on Web. */
  var lockRotation: Boolean
    get() = unsupportedWebGameplayFamily("RigidBody3D.is_lock_rotation_enabled")
    set(value) {
      GodotBackendCalls.invokeBoolArg(
        InitialGodotCallDescriptors.RIGIDBODY3D_SET_LOCK_ROTATION_ENABLED,
        backendHandle,
        value,
      )
    }

  fun show() {
    visible = true
  }
}

/** Enable or disable the node's physics processing (the tutorial pauses the player on win). */
fun Node.setPhysicsProcess(enable: Boolean) {
  NodeBackendContractProbe(backendHandle).setPhysicsProcess(enable)
}

/** World-space rotation basis derived from the synchronous global-rotation read (scale-1 rigs). */
val Node3D.globalBasis: Basis
  get() = Basis.fromEuler(globalRotation)
