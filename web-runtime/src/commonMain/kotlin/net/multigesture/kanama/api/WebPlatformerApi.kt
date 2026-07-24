@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.EnvironmentBackendContractProbe
import net.multigesture.kanama.backend.GodotHandle as BackendGodotHandle
import net.multigesture.kanama.backend.GodotVector3
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.backend.Light3DBackendContractProbe
import net.multigesture.kanama.backend.Node3DBackendContractProbe
import net.multigesture.kanama.backend.OSBackendContractProbe
import net.multigesture.kanama.backend.RenderingServerBackendContractProbe
import net.multigesture.kanama.backend.WorldEnvironmentBackendContractProbe
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.web.WebObjectId

/**
 * Web API surface for the 3D rendering foundation (Task 60c).
 *
 * Shared Godot classes live in [WebGodotApi]; the 3D scene-graph, lighting, and environment classes
 * the 3D platformer drives live here and are scanned by the fail-loud Web gameplay coverage gate.
 * Transform reads round-trip through the read-your-write snapshot; write-only render-tuning
 * properties (whose getters the demo never calls) surface as nonblocking unsupported families.
 */
open class Node3D(godotObject: GodotHandle) : Node(godotObject.toBackendHandle()) {
  var position: Vector3
    get() =
      Node3DBackendContractProbe(backendHandle).position.let { Vector3(it.x, it.y, it.z) }
    set(value) {
      Node3DBackendContractProbe(backendHandle).position =
        GodotVector3(value.x.toFloat(), value.y.toFloat(), value.z.toFloat())
    }

  var rotation: Vector3
    get() =
      Node3DBackendContractProbe(backendHandle).rotation.let { Vector3(it.x, it.y, it.z) }
    set(value) {
      Node3DBackendContractProbe(backendHandle).rotation =
        GodotVector3(value.x.toFloat(), value.y.toFloat(), value.z.toFloat())
    }

  var scale: Vector3
    get() = Node3DBackendContractProbe(backendHandle).scale.let { Vector3(it.x, it.y, it.z) }
    set(value) {
      Node3DBackendContractProbe(backendHandle).scale =
        GodotVector3(value.x.toFloat(), value.y.toFloat(), value.z.toFloat())
    }
}

open class VisualInstance3D(godotObject: GodotHandle) : Node3D(godotObject)

open class GeometryInstance3D(godotObject: GodotHandle) : VisualInstance3D(godotObject)

open class Light3D(godotObject: GodotHandle) : VisualInstance3D(godotObject) {
  protected fun setParam(param: Long, value: Double) {
    Light3DBackendContractProbe(backendHandle).setParam(param, value)
  }

  companion object {
    const val PARAM_ENERGY: Long = 0L
    const val PARAM_SHADOW_OPACITY: Long = 17L
  }
}

class DirectionalLight3D(godotObject: GodotHandle) : Light3D(godotObject) {
  /** Write-only on Web: light_energy is Light3D.set_param(PARAM_ENERGY, value). */
  var lightEnergy: Double
    get() = unsupportedWebGameplayFamily("Light3D.get_light_energy")
    set(value) {
      setParam(PARAM_ENERGY, value)
    }

  /** Write-only on Web: shadow_opacity is Light3D.set_param(PARAM_SHADOW_OPACITY, value). */
  var shadowOpacity: Double
    get() = unsupportedWebGameplayFamily("Light3D.get_shadow_opacity")
    set(value) {
      setParam(PARAM_SHADOW_OPACITY, value)
    }
}

class Environment(godotObject: GodotHandle) : Resource(godotObject.toBackendHandle()) {
  /** Write-only on Web: the platformer lowers the Compatibility-renderer background energy. */
  var backgroundEnergyMultiplier: Double
    get() = unsupportedWebGameplayFamily("Environment.get_bg_energy_multiplier")
    set(value) {
      EnvironmentBackendContractProbe(backendHandle).setBgEnergyMultiplier(value)
    }
}

class WorldEnvironment(godotObject: GodotHandle) : Node(godotObject.toBackendHandle()) {
  val environment: Environment
    get() =
      WorldEnvironmentBackendContractProbe(backendHandle).getEnvironment()?.let { handle ->
        Environment(WebObjectId(handle.backendToken().toInt()))
      } ?: error("WorldEnvironment has no Environment resource")
}

object OS {
  fun hasFeature(tagName: String): Boolean = OSBackendContractProbe.hasFeature(tagName)
}

object RenderingServer {
  fun getCurrentRenderingMethod(): String =
    RenderingServerBackendContractProbe.getCurrentRenderingMethod()
}
