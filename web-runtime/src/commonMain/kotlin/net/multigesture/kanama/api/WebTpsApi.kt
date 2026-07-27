@file:OptIn(net.multigesture.kanama.backend.InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.ClassDBBackendContractProbe
import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.GodotVector3
import net.multigesture.kanama.backend.InitialGodotCallDescriptors as D
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.web.WebObjectId

/**
 * Web API surface for tps-demo (Task 60i) — the last demo of the Web corpus.
 *
 * Three groups live here:
 * 1. wrappers over the families admitted in the 60i contract pass (AnimationTree parameters and
 *    root motion, the material chain, CPU particles, the settings UI, ConfigFile, noise);
 * 2. the space-state ray query, whose parameters are built engine-side (RIDs never cross the
 *    Web seam), so [PhysicsRayQueryParameters3D] is a plain Kotlin value here;
 * 3. facades for families the browser cannot host. Web builds are single-player: ENet needs UDP
 *    sockets, so the multiplayer surface reports a local authoritative session (peer 1, no
 *    remote peers) and the lobby's connect paths fail the way the demo already handles. The
 *    display/renderer settings that the compatibility renderer ignores are no-ops.
 */
private fun Vector3.toBackend(): GodotVector3 =
  GodotVector3(x.toFloat(), y.toFloat(), z.toFloat())

private fun GodotVector3.toApi(): Vector3 = Vector3(x.toDouble(), y.toDouble(), z.toDouble())

private fun Vector2.toBackend(): GodotVector2 = GodotVector2(x.toFloat(), y.toFloat())

private fun GodotVector2.toApi(): Vector2 = Vector2(x.toDouble(), y.toDouble())

// ---------------------------------------------------------------------------
// Liveness. Web adaptation: the tracked-handle registry is the source of truth,
// so both desktop guards collapse onto GD.isInstanceValid (the corpus-wide rule).
// ---------------------------------------------------------------------------

fun GodotObject.isQueuedForDeletion(): Boolean = !GD.isInstanceValid(this)

fun GodotObject.isInsideTree(): Boolean = GD.isInstanceValid(this)

// ---------------------------------------------------------------------------
// Node / Object surface.
// ---------------------------------------------------------------------------

fun Node.getName(): String = GodotBackendCalls.invokeNoArgsRetString(D.NODE_GET_NAME, backendHandle)

fun Node.setName(name: String) {
  GodotBackendCalls.invokeStringNameArg(D.NODE_SET_NAME, backendHandle, name)
}

fun Node.hasNode(path: String): Boolean = getNodeOrNull(path) != null

fun Node.getNode(path: String): GodotObject? = getNodeOrNull(path)

/** Godot's `propagate_call("set", [property, value])` (the shadow-mapping sweep). */
fun Node.propagateSet(property: String, value: Boolean) {
  GodotBackendCalls.invokeStringNameBoolArg(
    D.NODE_PROPAGATE_SET_BOOL,
    backendHandle,
    property,
    value,
  )
}

fun GodotObject.callDeferred(method: String) {
  GodotBackendCalls.invokeStringNameArg(D.OBJECT_CALL_DEFERRED_NOARGS, backendHandle, method)
}

fun GodotObject.callDeferred(method: String, argument: GodotObject) {
  GodotBackendCalls.invokeStringNameObjectArg(
    D.OBJECT_CALL_DEFERRED_OBJECT,
    backendHandle,
    method,
    argument.backendHandle,
  )
}

fun GodotObject.hasSignal(signal: String): Boolean =
  GodotBackendCalls.invokeStringNameRetBool(D.OBJECT_HAS_SIGNAL, backendHandle, signal)

/** Property-path write (AnimationTree parameters); the typed overloads pick the transport. */
fun GodotObject.set(path: String, value: String) {
  GodotBackendCalls.invokeStringNameStringNameArg(D.OBJECT_SET_INDEXED_STRING, backendHandle, path, value)
}

fun GodotObject.set(path: String, value: Long) {
  GodotBackendCalls.invokeStringNameLongArg(D.OBJECT_SET_INDEXED_LONG, backendHandle, path, value)
}

fun GodotObject.set(path: String, value: Double) {
  GodotBackendCalls.invokeStringNameDoubleArg(
    D.OBJECT_SET_INDEXED_DOUBLE,
    backendHandle,
    path,
    value,
  )
}

fun GodotObject.set(path: String, value: Vector2) {
  GodotBackendCalls.invokeStringNameVector2Arg(
    D.OBJECT_SET_INDEXED_VECTOR2,
    backendHandle,
    path,
    value.toBackend(),
  )
}

/** Vector2-valued property read (the robot's aim blend position). */
fun GodotObject.getVector2(path: String): Vector2 =
  GodotBackendCalls.invokeStringNameRetVector2(D.OBJECT_GET_PROPERTY_VECTOR2, backendHandle, path)
    .toApi()

// ---------------------------------------------------------------------------
// AnimationTree.
// ---------------------------------------------------------------------------

/** Blend-tree driver: parameters are property-path writes, root motion reads are synchronous. */
class AnimationTree(godotObject: GodotHandle) : AnimationMixer(godotObject) {
  fun getRootMotionPosition(): Vector3 =
    GodotBackendCalls.invokeNoArgsRetVector3(D.ANIMATIONMIXER_GET_ROOT_MOTION_POSITION, backendHandle)
      .toApi()

  /**
   * Web adaptation: the engine's Quaternion crosses as euler angles (the applier converts) and is
   * recomposed here, so no new value-type channel is needed for one call.
   */
  fun getRootMotionRotation(): Quaternion =
    Basis.fromEuler(
        GodotBackendCalls.invokeNoArgsRetVector3(
            D.ANIMATIONMIXER_GET_ROOT_MOTION_ROTATION,
            backendHandle,
          )
          .toApi()
      )
      .getRotationQuaternion()
}

// ---------------------------------------------------------------------------
// Particles, lights, and the material chain.
// ---------------------------------------------------------------------------

class CPUParticles3D(godotObject: GodotHandle) : GeometryInstance3D(godotObject) {
  var emitting: Boolean
    get() = unsupportedWebGameplayFamily("CPUParticles3D.is_emitting")
    set(value) {
      GodotBackendCalls.invokeBoolArg(D.CPUPARTICLES3D_SET_EMITTING, backendHandle, value)
    }

  var emissionBoxExtents: Vector3
    get() =
      GodotBackendCalls.invokeNoArgsRetVector3(
          D.CPUPARTICLES3D_GET_EMISSION_BOX_EXTENTS,
          backendHandle,
        )
        .toApi()
    set(value) {
      GodotBackendCalls.invokeVector3Arg(
        D.CPUPARTICLES3D_SET_EMISSION_BOX_EXTENTS,
        backendHandle,
        value.toBackend(),
      )
    }

  val lifetime: Double
    get() = GodotBackendCalls.invokeNoArgsRetDouble(D.CPUPARTICLES3D_GET_LIFETIME, backendHandle)

  fun restart(keepSeed: Boolean = false) {
    GodotBackendCalls.invokeBoolArg(D.CPUPARTICLES3D_RESTART, backendHandle, keepSeed)
  }
}

class OmniLight3D(godotObject: GodotHandle) : Light3D(godotObject)

class SpotLight3D(godotObject: GodotHandle) : Light3D(godotObject)

var Light3D.shadowEnabled: Boolean
  get() = unsupportedWebGameplayFamily("Light3D.get_shadow")
  set(value) {
    GodotBackendCalls.invokeBoolArg(D.LIGHT3D_SET_SHADOW, backendHandle, value)
  }

/** Baked lightmap payload; only ever loaded and assigned. */
class LightmapGIData internal constructor(godotObject: GodotHandle) :
  Resource(godotObject.toBackendHandle())

class LightmapGI(godotObject: GodotHandle) : VisualInstance3D(godotObject) {
  var lightData: LightmapGIData?
    get() = unsupportedWebGameplayFamily("LightmapGI.get_light_data")
    set(value) {
      GodotBackendCalls.invokeObjectArg(
        D.LIGHTMAPGI_SET_LIGHT_DATA,
        backendHandle,
        value?.backendHandle,
      )
    }

  companion object {
    fun create(): LightmapGI {
      val handle =
        checkNotNull(ClassDBBackendContractProbe.instantiate("LightmapGI")) {
          "Godot could not instantiate LightmapGI"
        }
      return LightmapGI(WebObjectId(handle.backendToken().toInt()))
    }
  }
}

/** Surface material; `nextPass` is the fade-shader chain the death parts duplicate. */
open class Material internal constructor(godotObject: GodotHandle) :
  Resource(godotObject.toBackendHandle()) {
  var nextPass: Material?
    get() =
      GodotBackendCalls.invokeNoArgsRetHandle(D.MATERIAL_GET_NEXT_PASS, backendHandle)?.let {
        Material(WebObjectId(it.backendToken().toInt()))
      }
    set(value) {
      GodotBackendCalls.invokeObjectArg(
        D.MATERIAL_SET_NEXT_PASS,
        backendHandle,
        value?.backendHandle,
      )
    }

  /** Duplicate this material; the copy is owned (close it, or release it at teardown). */
  fun duplicate(deep: Boolean = false): Material? =
    GodotBackendCalls.invokeBoolRetHandle(D.RESOURCE_DUPLICATE, backendHandle, deep)?.let {
      Material(WebObjectId(it.backendToken().toInt()))
    }

  /** Release this material's tracked browser handle (duplicates are Kotlin-owned). */
  fun close() {
    releaseWebConstructedObject(handle.value)
  }

  companion object {
    fun fromResource(resource: Resource?): Material? =
      resource?.let { Material(WebObjectId(it.backendHandle.backendToken().toInt())) }
  }
}

class ShaderMaterial internal constructor(godotObject: GodotHandle) : Material(godotObject) {
  fun setShaderParameter(name: String, value: Double) {
    GodotBackendCalls.invokeStringNameDoubleArg(
      D.SHADERMATERIAL_SET_SHADER_PARAMETER,
      backendHandle,
      name,
      value,
    )
  }

  companion object {
    fun fromResource(resource: Resource?): ShaderMaterial? =
      resource?.let { ShaderMaterial(WebObjectId(it.backendHandle.backendToken().toInt())) }
  }
}

fun MeshInstance3D.getSurfaceOverrideMaterial(surface: Int): Material? =
  GodotBackendCalls.invokeLongRetHandle(
      D.MESHINSTANCE3D_GET_SURFACE_OVERRIDE_MATERIAL,
      backendHandle,
      surface.toLong(),
    )
    ?.let { Material(WebObjectId(it.backendToken().toInt())) }

fun Mesh.surfaceGetMaterial(surface: Int): Material? =
  GodotBackendCalls.invokeLongRetHandle(
      D.MESH_SURFACE_GET_MATERIAL,
      backendHandle,
      surface.toLong(),
    )
    ?.let { Material(WebObjectId(it.backendToken().toInt())) }

fun Mesh.surfaceSetMaterial(surface: Int, material: Material) {
  GodotBackendCalls.invokeLongObjectArg(
    D.MESH_SURFACE_SET_MATERIAL,
    backendHandle,
    surface.toLong(),
    material.backendHandle,
  )
}

// ---------------------------------------------------------------------------
// Space-state ray query. The applier builds the query parameters engine-side.
// ---------------------------------------------------------------------------

/**
 * Ray query inputs. Web adaptation: this never becomes an engine object — the applier constructs
 * `PhysicsRayQueryParameters3D` on its side, so the RID exclusion list stays engine-side too and
 * [exclude] carries the excluded body itself.
 */
class PhysicsRayQueryParameters3D
private constructor(
  internal val from: Vector3,
  internal val to: Vector3,
  internal val collisionMask: Long,
  internal val exclude: GodotObject?,
) {
  companion object {
    fun create(
      from: Vector3,
      to: Vector3,
      collisionMask: Long = 0xffffffffL,
      exclude: List<GodotObject> = emptyList(),
    ): PhysicsRayQueryParameters3D? {
      require(exclude.size <= 1) {
        "Kanama Web ray queries carry at most one exclusion (the corpus excludes only self)"
      }
      return PhysicsRayQueryParameters3D(from, to, collisionMask, exclude.firstOrNull())
    }
  }
}

/** A ray hit: `position` and `collider` mirror the engine Dictionary the desktop demo reads. */
class RayHit internal constructor(val position: Vector3, val collider: GodotObject?) {
  val isEmpty: Boolean
    get() = false
}

/**
 * Direct space state bound to the querying node. Web adaptation: the receiver of the crossing is
 * that node, and the engine resolves its world on the applier side.
 */
class PhysicsDirectSpaceState3D internal constructor(private val owner: Node3D) {
  /**
   * Returns null when the ray misses. The collider resolves to an already-tracked handle (script
   * or browser); untracked engine geometry reports no collider, which is all the demo needs — it
   * only ever compares the collider against a node it already holds.
   */
  fun intersectRay(query: PhysicsRayQueryParameters3D): RayHit? {
    val packed =
      GodotBackendCalls.invokeVector3Vector3LongObjectRetString(
        D.PHYSICSDIRECTSPACESTATE3D_INTERSECT_RAY,
        owner.backendHandle,
        query.from.toBackend(),
        query.to.toBackend(),
        query.collisionMask,
        query.exclude?.backendHandle,
      )
    val parts = packed.split('')
    require(parts.size == 5) { "Kanama Web ray query returned $packed" }
    if (parts[0] != "1") return null
    val colliderToken = parts[4].toInt()
    return RayHit(
      Vector3(parts[1].toDouble(), parts[2].toDouble(), parts[3].toDouble()),
      colliderToken.takeIf { it > 0 }?.let { GodotObject(WebObjectId(it)) },
    )
  }
}

class World3D internal constructor(private val owner: Node3D) {
  val directSpaceState: PhysicsDirectSpaceState3D
    get() = PhysicsDirectSpaceState3D(owner)
}

fun Node3D.getWorld3d(): World3D = World3D(this)

// ---------------------------------------------------------------------------
// Bodies.
// ---------------------------------------------------------------------------

/** Current gravity vector; tps integrates gravity itself each physics tick. */
fun PhysicsBody3D.getGravity(): Vector3 =
  GodotBackendCalls.invokeNoArgsRetVector3(D.PHYSICSBODY3D_GET_GRAVITY, backendHandle).toApi()

fun CharacterBody3D.setUpDirection(direction: Vector3) {
  GodotBackendCalls.invokeVector3Arg(
    D.CHARACTERBODY3D_SET_UP_DIRECTION,
    backendHandle,
    direction.toBackend(),
  )
}

/** Whole-mask collision writes (death clears every layer/mask bit at once). */
var PhysicsBody3D.collisionLayer: Long
  get() = unsupportedWebGameplayFamily("CollisionObject3D.get_collision_layer")
  set(value) {
    GodotBackendCalls.invokeLongArg(D.COLLISIONOBJECT3D_SET_COLLISION_LAYER, backendHandle, value)
  }

var PhysicsBody3D.collisionMask: Long
  get() = unsupportedWebGameplayFamily("CollisionObject3D.get_collision_mask")
  set(value) {
    GodotBackendCalls.invokeLongArg(D.COLLISIONOBJECT3D_SET_COLLISION_MASK, backendHandle, value)
  }

var CollisionShape3D.disabled: Boolean
  get() = unsupportedWebGameplayFamily("CollisionShape3D.is_disabled")
  set(value) {
    setDisabled(value)
  }

/** Indexed child as a tracked handle (the forklift and death-part models index their model child). */
fun Node.getChild(index: Int): GodotObject? =
  GodotBackendCalls.invokeLongRetHandle(D.NODE_GET_CHILD, backendHandle, index.toLong())?.let {
    GodotObject(WebObjectId(it.backendToken().toInt()))
  }

fun Node3D.show() {
  visible = true
}

/** Godot's Node3D.orthonormalize: re-orthonormalize the local basis in place. */
fun Node3D.orthonormalize() {
  basis = basis.orthonormalized()
}

var RigidBody3D.linearVelocity: Vector3
  get() = unsupportedWebGameplayFamily("RigidBody3D.get_linear_velocity")
  set(value) {
    GodotBackendCalls.invokeVector3Arg(
      D.RIGIDBODY3D_SET_LINEAR_VELOCITY,
      backendHandle,
      value.toBackend(),
    )
  }

// ---------------------------------------------------------------------------
// Timers, tree, camera.
// ---------------------------------------------------------------------------

fun Timer.getTimeLeft(): Double =
  GodotBackendCalls.invokeNoArgsRetDouble(D.TIMER_GET_TIME_LEFT, backendHandle)

val SceneTree.root: Viewport
  get() =
    Viewport(
      checkNotNull(GodotBackendCalls.invokeNoArgsRetHandle(D.SCENETREE_GET_ROOT, backendHandle)) {
          "SceneTree has no root window"
        }
        .let { WebObjectId(it.backendToken().toInt()) }
    )

fun Viewport.getCamera3d(): Camera3D? =
  GodotBackendCalls.invokeNoArgsRetHandle(D.VIEWPORT_GET_CAMERA_3D, backendHandle)?.let {
    Camera3D(WebObjectId(it.backendToken().toInt()))
  }

fun Camera3D.makeCurrent() {
  GodotBackendCalls.invokeNoArgsVoid(D.CAMERA3D_MAKE_CURRENT, backendHandle)
}

// ---------------------------------------------------------------------------
// Settings UI.
// ---------------------------------------------------------------------------

fun Control.grabFocus() {
  GodotBackendCalls.invokeNoArgsVoid(D.CONTROL_GRAB_FOCUS, backendHandle)
}

val Control.position: Vector2
  get() = GodotBackendCalls.invokeNoArgsRetVector2(D.CONTROL_GET_POSITION, backendHandle).toApi()

val Control.size: Vector2
  get() = GodotBackendCalls.invokeNoArgsRetVector2(D.CONTROL_GET_SIZE, backendHandle).toApi()

var BaseButton.buttonPressed: Boolean
  get() = GodotBackendCalls.invokeNoArgsRetBool(D.BASEBUTTON_IS_PRESSED, backendHandle)
  set(value) {
    GodotBackendCalls.invokeBoolArg(D.BASEBUTTON_SET_PRESSED, backendHandle, value)
  }

var BaseButton.disabled: Boolean
  get() = unsupportedWebGameplayFamily("BaseButton.is_disabled")
  set(value) {
    GodotBackendCalls.invokeBoolArg(D.BASEBUTTON_SET_DISABLED, backendHandle, value)
  }

var BaseButton.buttonGroup: ButtonGroup?
  get() = unsupportedWebGameplayFamily("BaseButton.get_button_group")
  set(value) {
    GodotBackendCalls.invokeObjectArg(
      D.BASEBUTTON_SET_BUTTON_GROUP,
      backendHandle,
      value?.backendHandle,
    )
  }

/** Radio-group resource; constructed and owned by the settings menu. */
class ButtonGroup internal constructor(godotObject: GodotHandle) :
  Resource(godotObject.toBackendHandle()) {
  fun close() {
    releaseWebConstructedObject(handle.value)
  }

  companion object {
    fun create(): ButtonGroup {
      val handle =
        checkNotNull(ClassDBBackendContractProbe.instantiate("ButtonGroup")) {
          "Godot could not instantiate ButtonGroup"
        }
      return ButtonGroup(WebObjectId(handle.backendToken().toInt()))
    }
  }
}

var Button.text: String
  get() = unsupportedWebGameplayFamily("Button.get_text")
  set(value) {
    GodotBackendCalls.invokeStringNameArg(D.BUTTON_SET_TEXT, backendHandle, value)
  }

class LineEdit(godotObject: GodotHandle) : Control(godotObject) {
  var text: String
    get() = GodotBackendCalls.invokeNoArgsRetString(D.LINEEDIT_GET_TEXT, backendHandle)
    set(value) {
      GodotBackendCalls.invokeStringNameArg(D.LINEEDIT_SET_TEXT, backendHandle, value)
    }

  var editable: Boolean
    get() = unsupportedWebGameplayFamily("LineEdit.is_editable")
    set(value) {
      GodotBackendCalls.invokeBoolArg(D.LINEEDIT_SET_EDITABLE, backendHandle, value)
    }
}

/** Range base (SpinBox port value, ProgressBar loading value). */
open class Range(godotObject: GodotHandle) : Control(godotObject) {
  var value: Double
    get() = GodotBackendCalls.invokeNoArgsRetDouble(D.RANGE_GET_VALUE, backendHandle)
    set(newValue) {
      GodotBackendCalls.invokeDoubleArg(D.RANGE_SET_VALUE, backendHandle, newValue)
    }
}

class SpinBox(godotObject: GodotHandle) : Range(godotObject)

class ProgressBar(godotObject: GodotHandle) : Range(godotObject)

class ColorRect(godotObject: GodotHandle) : Control(godotObject)

// ---------------------------------------------------------------------------
// ConfigFile. Values ride a tagged string through the shared query channel.
// ---------------------------------------------------------------------------

private const val UNIT = ""

class ConfigFile internal constructor(godotObject: GodotHandle) :
  Resource(godotObject.toBackendHandle()) {
  fun close() {
    releaseWebConstructedObject(handle.value)
  }

  fun load(path: String) {
    GodotBackendCalls.invokeStringNameArg(D.CONFIGFILE_LOAD, backendHandle, path)
  }

  fun save(path: String) {
    GodotBackendCalls.invokeStringNameArg(D.CONFIGFILE_SAVE, backendHandle, path)
  }

  fun hasSectionKey(section: String, key: String): Boolean =
    GodotBackendCalls.invokeStringNameRetBool(
      D.CONFIGFILE_HAS_SECTION_KEY,
      backendHandle,
      section + UNIT + key,
    )

  fun setValue(section: String, key: String, value: Any?) {
    val tagged =
      when (value) {
        is Boolean -> "b:$value"
        is Long -> "i:$value"
        is Int -> "i:$value"
        is Double -> "f:$value"
        is String -> "s:$value"
        else -> error("Kanama Web ConfigFile does not carry ${value?.let { it::class }} values")
      }
    GodotBackendCalls.invokeStringNameArg(
      D.CONFIGFILE_SET_VALUE,
      backendHandle,
      section + UNIT + key + UNIT + tagged,
    )
  }

  fun getValue(section: String, key: String): Any? {
    val tagged =
      GodotBackendCalls.invokeStringNameRetString(
        D.CONFIGFILE_GET_VALUE,
        backendHandle,
        section + UNIT + key,
      )
    val body = tagged.drop(2)
    return when {
      tagged.startsWith("b:") -> body == "true"
      tagged.startsWith("i:") -> body.toLong()
      tagged.startsWith("f:") -> body.toDouble()
      tagged.startsWith("s:") -> body
      else -> null
    }
  }

  companion object {
    fun create(): ConfigFile {
      val handle =
        checkNotNull(ClassDBBackendContractProbe.instantiate("ConfigFile")) {
          "Godot could not instantiate ConfigFile"
        }
      return ConfigFile(WebObjectId(handle.backendToken().toInt()))
    }
  }
}

// ---------------------------------------------------------------------------
// Noise (camera shake).
// ---------------------------------------------------------------------------

class FastNoiseLite internal constructor(godotObject: GodotHandle) :
  Resource(godotObject.toBackendHandle()) {
  var seed: Int
    get() = unsupportedWebGameplayFamily("FastNoiseLite.get_seed")
    set(value) {
      GodotBackendCalls.invokeLongArg(D.FASTNOISELITE_SET_SEED, backendHandle, value.toLong())
    }

  var fractalOctaves: Int
    get() = unsupportedWebGameplayFamily("FastNoiseLite.get_fractal_octaves")
    set(value) {
      GodotBackendCalls.invokeLongArg(
        D.FASTNOISELITE_SET_FRACTAL_OCTAVES,
        backendHandle,
        value.toLong(),
      )
    }

  var fractalLacunarity: Double
    get() = unsupportedWebGameplayFamily("FastNoiseLite.get_fractal_lacunarity")
    set(value) {
      GodotBackendCalls.invokeDoubleArg(
        D.FASTNOISELITE_SET_FRACTAL_LACUNARITY,
        backendHandle,
        value,
      )
    }

  fun getNoise1d(position: Double): Double =
    GodotBackendCalls.invokeDoubleRetDouble(D.NOISE_GET_NOISE_1D, backendHandle, position)

  fun close() {
    releaseWebConstructedObject(handle.value)
  }

  companion object {
    fun create(): FastNoiseLite {
      val handle =
        checkNotNull(ClassDBBackendContractProbe.instantiate("FastNoiseLite")) {
          "Godot could not instantiate FastNoiseLite"
        }
      return FastNoiseLite(WebObjectId(handle.backendToken().toInt()))
    }
  }
}

// ---------------------------------------------------------------------------
// Skeleton attachment (the robot's ray origin bone).
// ---------------------------------------------------------------------------

class BoneAttachment3D(godotObject: GodotHandle) : Node3D(godotObject)

// ---------------------------------------------------------------------------
// Engine / OS singletons.
// ---------------------------------------------------------------------------

object Engine {
  var maxFps: Int
    get() = unsupportedWebGameplayFamily("Engine.get_max_fps")
    set(value) {
      GodotBackendCalls.invokeLongArgSingleton(D.ENGINE_SET_MAX_FPS, value.toLong())
    }

  fun getFramesPerSecond(): Long =
    GodotBackendCalls.invokeNoArgsRetLongSingleton(D.ENGINE_GET_FRAMES_PER_SECOND)
}

fun OS.getStaticMemoryUsage(): Long =
  GodotBackendCalls.invokeNoArgsRetLongSingleton(D.OS_GET_STATIC_MEMORY_USAGE)

fun Input.getActionStrength(action: String): Double =
  GodotBackendCalls.invokeStringNameRetDoubleSingleton(D.INPUT_GET_ACTION_STRENGTH, action)

// ---------------------------------------------------------------------------
// Multiplayer facade. Web builds are single-player: the browser has no UDP
// sockets for ENet, so this reports a local authoritative session and the
// lobby's connect paths fail through the demo's own error handling.
// ---------------------------------------------------------------------------

open class MultiplayerPeer internal constructor() {
  open fun closeConnection() = Unit

  open fun close() = Unit
}

class OfflineMultiplayerPeer internal constructor() : MultiplayerPeer() {
  companion object {
    fun create(): OfflineMultiplayerPeer = OfflineMultiplayerPeer()
  }
}

class ENetMultiplayerPeer internal constructor() : MultiplayerPeer() {
  /** Always fails on Web (no UDP sockets); the lobby surfaces the error to the player. */
  fun createServer(@Suppress("UNUSED_PARAMETER") port: Int): Long = ERR_UNAVAILABLE

  fun createClient(
    @Suppress("UNUSED_PARAMETER") address: String,
    @Suppress("UNUSED_PARAMETER") port: Int,
  ): Long = ERR_UNAVAILABLE

  companion object {
    private const val ERR_UNAVAILABLE = 35L

    fun create(): ENetMultiplayerPeer = ENetMultiplayerPeer()
  }
}

class MultiplayerAPI internal constructor() {
  fun isServer(): Boolean = true

  fun getUniqueId(): Int = 1

  fun getPeers(): List<Int> = emptyList()

  fun getRemoteSenderId(): Int = 0

  fun getMultiplayerPeer(): MultiplayerPeer? = peer

  var multiplayerPeer: MultiplayerPeer?
    get() = peer
    set(value) {
      peer = value
    }

  /** No remote peers ever connect, so these signals never fire; connects are no-ops. */
  fun signal(@Suppress("UNUSED_PARAMETER") name: String): InertSignal = InertSignal

  object Signals {
    const val peerConnected = "peer_connected"
    const val peerDisconnected = "peer_disconnected"
    const val connectedToServer = "connected_to_server"
    const val connectionFailed = "connection_failed"
    const val serverDisconnected = "server_disconnected"
  }

  private companion object {
    var peer: MultiplayerPeer? = null
  }
}

/** A signal that can never fire on Web; connecting to it is deliberately inert. */
object InertSignal {
  fun connect(
    @Suppress("UNUSED_PARAMETER") target: GodotObject,
    @Suppress("UNUSED_PARAMETER") argumentCount: Int = 0,
    @Suppress("UNUSED_PARAMETER") handler: (List<Any?>) -> Unit,
  ) = Unit
}

object SceneMultiplayer {
  fun fromApi(api: MultiplayerAPI?): SceneMultiplayerHandle? = api?.let { SceneMultiplayerHandle }
}

object SceneMultiplayerHandle {
  var serverRelay: Boolean = false
}

private val multiplayerApi = MultiplayerAPI()

/** Nullable to match the desktop shape, so ported call sites keep their `?.` chains. */
fun Node.getMultiplayer(): MultiplayerAPI? = multiplayerApi

/** Mirrors the desktop owned-peer idiom ("close what you create", task 61). */
inline fun <T : MultiplayerPeer, R> T.use(block: (T) -> R): R {
  try {
    return block(this)
  } finally {
    close()
  }
}

/** Same owned-handle idiom for the settings menu's radio groups. */
inline fun <R> ButtonGroup.use(block: (ButtonGroup) -> R): R {
  try {
    return block(this)
  } finally {
    close()
  }
}

/** Replication node; with a single local peer there is nothing to synchronize. */
class MultiplayerSynchronizer(godotObject: GodotHandle) : Node(godotObject.toBackendHandle()) {
  var publicVisibility: Boolean = true

  fun setMultiplayerAuthority(@Suppress("UNUSED_PARAMETER") id: Int) = Unit

  fun getMultiplayerAuthority(): Int = 1
}

object IP {
  /** No socket surface on Web; the lobby falls back to its "this device" copy. */
  fun getLocalAddresses(): List<String> = emptyList()
}

// ---------------------------------------------------------------------------
// Display / renderer settings. The compatibility renderer ignores most of the
// tps graphics menu, so those writes are no-ops rather than fake successes.
// ---------------------------------------------------------------------------

/** Window settings facade: mode and 3D scaling are fixed by the browser canvas. */
class Window internal constructor() {
  var mode: Long = MODE_WINDOWED
  var scaling3dScale: Double = 1.0
  var scaling3dMode: Long = 0L
  var useTaa: Boolean = false
  var msaa3d: Long = 0L
  var screenSpaceAa: Long = 0L

  companion object {
    const val MODE_WINDOWED = 0L
    const val MODE_FULLSCREEN = 3L
    const val MODE_EXCLUSIVE_FULLSCREEN = 4L
    const val MODE_MAXIMIZED = 2L
  }
}

private val browserWindow = Window()

fun Node.getWindow(): Window = browserWindow

object DisplayServer {
  const val VSYNC_DISABLED = 0L
  const val VSYNC_ENABLED = 1L
  const val VSYNC_ADAPTIVE = 2L
  const val VSYNC_MAILBOX = 3L

  /** Web adaptation: the demo branches on "headless"; the browser is a real display. */
  fun getName(): String = "Web"

  fun windowSetVsyncMode(@Suppress("UNUSED_PARAMETER") mode: Long) = Unit

  fun windowGetVsyncMode(): Long = VSYNC_ENABLED
}

/**
 * Renderer settings the compatibility renderer has no equivalent for. They stay inert rather than
 * pretending to apply; the settings menu still records the player's choice in the config file.
 */
object RenderingServerQuality {
  const val ENV_SDFGI_RAY_COUNT_32 = 2L
  const val ENV_SDFGI_RAY_COUNT_96 = 5L
  const val VOXEL_GI_QUALITY_LOW = 0L
  const val VOXEL_GI_QUALITY_HIGH = 1L
  const val ENV_SSAO_QUALITY_MEDIUM = 1L
  const val ENV_SSAO_QUALITY_HIGH = 2L
  const val ENV_SSIL_QUALITY_MEDIUM = 1L
  const val ENV_SSIL_QUALITY_HIGH = 2L
}

fun RenderingServer.getCurrentRenderingDriverName(): String = "opengl3"

fun RenderingServer.environmentSetSdfgiRayCount(@Suppress("UNUSED_PARAMETER") count: Long) = Unit

fun RenderingServer.voxelGiSetQuality(@Suppress("UNUSED_PARAMETER") quality: Long) = Unit

fun RenderingServer.environmentSetSsaoQuality(
  @Suppress("UNUSED_PARAMETER") quality: Long,
  @Suppress("UNUSED_PARAMETER") halfSize: Boolean,
  @Suppress("UNUSED_PARAMETER") adaptiveTarget: Double,
  @Suppress("UNUSED_PARAMETER") blurPasses: Int,
  @Suppress("UNUSED_PARAMETER") fadeOutFrom: Double,
  @Suppress("UNUSED_PARAMETER") fadeOutTo: Double,
) = Unit

fun RenderingServer.environmentSetSsilQuality(
  @Suppress("UNUSED_PARAMETER") quality: Long,
  @Suppress("UNUSED_PARAMETER") halfSize: Boolean,
  @Suppress("UNUSED_PARAMETER") adaptiveTarget: Double,
  @Suppress("UNUSED_PARAMETER") blurPasses: Int,
  @Suppress("UNUSED_PARAMETER") fadeOutFrom: Double,
  @Suppress("UNUSED_PARAMETER") fadeOutTo: Double,
) = Unit

/** The browser canvas owns fullscreen; the demo's own toggle stays inert. */
fun Viewport.setInputAsHandled() = Unit

/** Baked lightmap data load (the LightmapGI settings path). */
fun ResourceLoader.loadLightmapGIData(path: String): LightmapGIData? =
  load(path, "LightmapGIData")?.let { LightmapGIData(it.handle) }

/**
 * Threaded-load facade. The Web export is a `nothreads` build, so a background load would never
 * make progress: the request loads synchronously and then reports LOADED. The demo's loading
 * screen still runs its normal status/progress path, it just completes on the first poll.
 */
object ThreadedLoad {
  const val THREAD_LOAD_IN_PROGRESS = 0L
  const val THREAD_LOAD_FAILED = 1L
  const val THREAD_LOAD_INVALID_RESOURCE = 2L
  const val THREAD_LOAD_LOADED = 3L

  private val loaded = mutableMapOf<String, PackedScene?>()

  fun request(path: String) {
    loaded[path] = ResourceLoader.loadPackedScene(path)
  }

  fun status(path: String): Long =
    when {
      !loaded.containsKey(path) -> THREAD_LOAD_IN_PROGRESS
      loaded[path] == null -> THREAD_LOAD_FAILED
      else -> THREAD_LOAD_LOADED
    }

  fun take(path: String): PackedScene? = loaded[path]
}

class ThreadedLoadStatus internal constructor(val status: Long, val progress: Double?)

fun ResourceLoader.loadThreadedRequest(
  path: String,
  @Suppress("UNUSED_PARAMETER") typeHint: String = "",
  @Suppress("UNUSED_PARAMETER") useSubThreads: Boolean = false,
) {
  ThreadedLoad.request(path)
}

fun ResourceLoader.loadThreadedGetStatusWithProgress(path: String): ThreadedLoadStatus {
  val status = ThreadedLoad.status(path)
  return ThreadedLoadStatus(status, if (status == ThreadedLoad.THREAD_LOAD_LOADED) 1.0 else 0.0)
}

fun ResourceLoader.loadThreadedGetPackedScene(path: String): PackedScene? = ThreadedLoad.take(path)

/** Frame-deferred work; the Web scheduler already runs posts on the next frame. */
fun MainThread.postNextFrame(block: () -> Unit) = post(block)

fun GD.radToDeg(radians: Double): Double = radians * 180.0 / kotlin.math.PI

fun GD.pushError(message: Any?) {
  println("ERROR: $message")
}

var Environment.glowEnabled: Boolean
  get() = unsupportedWebGameplayFamily("Environment.is_glow_enabled")
  set(value) {
    GodotBackendCalls.invokeBoolArg(D.ENVIRONMENT_SET_GLOW_ENABLED, backendHandle, value)
  }

/**
 * Global-illumination and screen-space effect toggles. The Web export runs the compatibility
 * renderer, which has no SDFGI, VoxelGI, SSAO, SSIL, or volumetric fog: these stay inert rather
 * than pretending to apply.
 */
var Environment.sdfgiEnabled: Boolean
  get() = false
  set(@Suppress("UNUSED_PARAMETER") value) = Unit

var Environment.ssaoEnabled: Boolean
  get() = false
  set(@Suppress("UNUSED_PARAMETER") value) = Unit

var Environment.ssilEnabled: Boolean
  get() = false
  set(@Suppress("UNUSED_PARAMETER") value) = Unit

var Environment.volumetricFogEnabled: Boolean
  get() = false
  set(@Suppress("UNUSED_PARAMETER") value) = Unit
