@file:OptIn(net.multigesture.kanama.backend.InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.ClassDBBackendContractProbe
import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.GodotBasis
import net.multigesture.kanama.backend.GodotObjectBackendContractProbe
import net.multigesture.kanama.backend.GodotTransform3D
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.GodotVector3
import net.multigesture.kanama.backend.GodotVector3i
import net.multigesture.kanama.backend.InitialGodotCallDescriptors
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i
import net.multigesture.kanama.web.KanamaWebScript
import net.multigesture.kanama.web.WebObjectId
import net.multigesture.kanama.web.webScriptInstance

private fun Vector3i.toBackend(): GodotVector3i = GodotVector3i(x, y, z)

private fun Basis.toBackend(): GodotBasis =
  GodotBasis(x.toBackend(), y.toBackend(), z.toBackend())

private fun Vector3.toBackend(): GodotVector3 =
  GodotVector3(x.toFloat(), y.toFloat(), z.toFloat())

private fun GodotVector3.toApi(): Vector3 = Vector3(x.toDouble(), y.toDouble(), z.toDouble())

/** City-Builder surface: the runtime-built structure grid. */
class GridMap(godotObject: GodotHandle) : Node3D(godotObject) {
  fun setMeshLibrary(meshLibrary: MeshLibrary?) {
    GodotBackendCalls.invokeObjectArg(
      InitialGodotCallDescriptors.GRIDMAP_SET_MESH_LIBRARY,
      backendHandle,
      meshLibrary?.backendHandle,
    )
  }

  fun setCellItem(position: Vector3i, item: Int, orientation: Int = 0) {
    GodotBackendCalls.invokeVector3iLongLongArg(
      InitialGodotCallDescriptors.GRIDMAP_SET_CELL_ITEM,
      backendHandle,
      position.toBackend(),
      item.toLong(),
      orientation.toLong(),
    )
  }

  fun getCellItem(position: Vector3i): Int =
    GodotBackendCalls.invokeVector3iRetLong(
        InitialGodotCallDescriptors.GRIDMAP_GET_CELL_ITEM,
        backendHandle,
        position.toBackend(),
      )
      .toInt()

  fun getCellItemOrientation(position: Vector3i): Int =
    GodotBackendCalls.invokeVector3iRetLong(
        InitialGodotCallDescriptors.GRIDMAP_GET_CELL_ITEM_ORIENTATION,
        backendHandle,
        position.toBackend(),
      )
      .toInt()

  fun getOrthogonalIndexFromBasis(basis: Basis): Int =
    GodotBackendCalls.invokeBasisRetLong(
        InitialGodotCallDescriptors.GRIDMAP_GET_ORTHOGONAL_INDEX_FROM_BASIS,
        backendHandle,
        basis.toBackend(),
      )
      .toInt()

  fun clear() {
    GodotBackendCalls.invokeNoArgsVoid(InitialGodotCallDescriptors.GRIDMAP_CLEAR, backendHandle)
  }

  fun getUsedCells(): List<Vector3i> =
    GodotBackendCalls.invokeNoArgsRetVector3iList(
        InitialGodotCallDescriptors.GRIDMAP_GET_USED_CELLS,
        backendHandle,
      )
      .map { Vector3i(it.x, it.y, it.z) }

  companion object {
    const val INVALID_CELL_ITEM: Long = -1L
  }
}

/** Runtime-built mesh library; `create()` hands back an owning wrapper — close what you create. */
class MeshLibrary(godotObject: GodotHandle) : Resource(godotObject.toBackendHandle()) {
  fun createItem(id: Int) {
    GodotBackendCalls.invokeLongArg(
      InitialGodotCallDescriptors.MESHLIBRARY_CREATE_ITEM,
      backendHandle,
      id.toLong(),
    )
  }

  fun setItemMesh(id: Int, mesh: Mesh?) {
    val target = requireNotNull(mesh) { "Kanama Web MeshLibrary.setItemMesh requires a mesh" }
    GodotBackendCalls.invokeLongObjectArg(
      InitialGodotCallDescriptors.MESHLIBRARY_SET_ITEM_MESH,
      backendHandle,
      id.toLong(),
      target.backendHandle,
    )
  }

  fun setItemMeshTransform(id: Int, meshTransform: Transform3D) {
    GodotBackendCalls.invokeLongTransform3dArg(
      InitialGodotCallDescriptors.MESHLIBRARY_SET_ITEM_MESH_TRANSFORM,
      backendHandle,
      id.toLong(),
      GodotTransform3D(meshTransform.basis.toBackend(), meshTransform.origin.toBackend()),
    )
  }

  /** Releases this constructed library's reference ("close what you create"). */
  fun close() {
    releaseWebConstructedObject(handle.value)
  }

  companion object {
    fun create(): MeshLibrary {
      val created =
        checkNotNull(ClassDBBackendContractProbe.instantiate("MeshLibrary")) {
          "Godot could not instantiate MeshLibrary"
        }
      return MeshLibrary(WebObjectId(created.backendToken().toInt()))
    }
  }
}

/** Read-only recorded scene contents (mesh extraction without instantiating). */
class SceneState internal constructor(godotObject: GodotHandle) : GodotObject(godotObject) {
  fun getNodeCount(): Int =
    GodotBackendCalls.invokeNoArgsRetLong(
        InitialGodotCallDescriptors.SCENESTATE_GET_NODE_COUNT,
        backendHandle,
      )
      .toInt()

  fun getNodeType(idx: Int): String =
    GodotBackendCalls.invokeLongRetString(
      InitialGodotCallDescriptors.SCENESTATE_GET_NODE_TYPE,
      backendHandle,
      idx.toLong(),
    )

  fun getNodePropertyCount(idx: Int): Int =
    GodotBackendCalls.invokeLongRetLong(
        InitialGodotCallDescriptors.SCENESTATE_GET_NODE_PROPERTY_COUNT,
        backendHandle,
        idx.toLong(),
      )
      .toInt()

  fun getNodePropertyName(idx: Int, propIdx: Int): String =
    GodotBackendCalls.invokeLongLongRetString(
      InitialGodotCallDescriptors.SCENESTATE_GET_NODE_PROPERTY_NAME,
      backendHandle,
      idx.toLong(),
      propIdx.toLong(),
    )

  /** Object-valued recorded properties resolve to a tracked handle; other values are null. */
  fun getNodePropertyValue(idx: Int, propIdx: Int): Any? =
    GodotBackendCalls.invokeLongLongRetHandle(
        InitialGodotCallDescriptors.SCENESTATE_GET_NODE_PROPERTY_VALUE,
        backendHandle,
        idx.toLong(),
        propIdx.toLong(),
      )
      ?.let { GodotObject(WebObjectId(it.backendToken().toInt())) }
}

/** Mesh resource wrapper (extracted from scene state and duplicated for the library). */
class Mesh internal constructor(godotObject: GodotHandle) : Resource(godotObject.toBackendHandle()) {
  fun duplicate(subresources: Boolean = false): Resource? =
    GodotBackendCalls.invokeBoolRetHandle(
        InitialGodotCallDescriptors.RESOURCE_DUPLICATE,
        backendHandle,
        subresources,
      )
      ?.let { Resource(WebObjectId(it.backendToken().toInt())) }

  companion object {
    fun fromObject(value: GodotObject?): Mesh? =
      value
        ?.takeIf { GodotObjectBackendContractProbe(it.backendHandle).isClass("Mesh") }
        ?.let { Mesh(it.handle) }
  }
}

fun PackedScene.getState(): SceneState? =
  GodotBackendCalls.invokeNoArgsRetHandle(
      InitialGodotCallDescriptors.PACKEDSCENE_GET_STATE,
      backendHandle,
    )
    ?.let { SceneState(WebObjectId(it.backendToken().toInt())) }

fun MeshInstance3D.getMesh(): Mesh? =
  GodotBackendCalls.invokeNoArgsRetHandle(
      InitialGodotCallDescriptors.MESHINSTANCE3D_GET_MESH,
      backendHandle,
    )
    ?.let { Mesh(WebObjectId(it.backendToken().toInt())) }

fun Camera3D.projectRayOrigin(screenPoint: Vector2): Vector3 =
  GodotBackendCalls.invokeVector2RetVector3(
      InitialGodotCallDescriptors.CAMERA3D_PROJECT_RAY_ORIGIN,
      backendHandle,
      GodotVector2(screenPoint.x.toFloat(), screenPoint.y.toFloat()),
    )
    .toApi()

fun Camera3D.projectRayNormal(screenPoint: Vector2): Vector3 =
  GodotBackendCalls.invokeVector2RetVector3(
      InitialGodotCallDescriptors.CAMERA3D_PROJECT_RAY_NORMAL,
      backendHandle,
      GodotVector2(screenPoint.x.toFloat(), screenPoint.y.toFloat()),
    )
    .toApi()

/** Fresh viewport-space pointer position (no snapshot mirrors pointer state). */
fun Viewport.getMousePosition(): Vector2 =
  GodotBackendCalls.invokeNoArgsRetVector2(
      InitialGodotCallDescriptors.VIEWPORT_GET_MOUSE_POSITION,
      backendHandle,
    )
    .let { Vector2(it.x.toDouble(), it.y.toDouble()) }

fun Node.findChildren(
  pattern: String,
  type: String = "",
  recursive: Boolean = true,
  owned: Boolean = true,
): List<Node> =
  GodotBackendCalls.invokeStringStringBoolBoolRetHandleList(
      InitialGodotCallDescriptors.NODE_FIND_CHILDREN,
      backendHandle,
      pattern,
      type,
      recursive,
      owned,
    )
    .map { Node(it) }

fun GodotObject.isClass(name: String): Boolean =
  GodotObjectBackendContractProbe(backendHandle).isClass(name)

fun GodotObject.asObject(): GodotObject = this

fun Input.isActionJustReleased(action: String): Boolean =
  GodotBackendCalls.invokeStringNameRetBoolSingleton(
    InitialGodotCallDescriptors.INPUT_IS_ACTION_JUST_RELEASED,
    action,
  )

object ResourceSaver {
  /** Persists [resource]; scripted resources pull current Kotlin values before serializing. */
  fun save(resource: Resource, path: String, flags: Long = 0L): Long =
    GodotBackendCalls.invokeObjectStringRetLongSingleton(
      InitialGodotCallDescriptors.RESOURCESAVER_SAVE,
      resource.backendHandle,
      path,
      flags,
    )
}

/** Generic resource load (scripted resources resolve to their hydrated script handle). */
fun ResourceLoader.load(
  path: String,
  typeHint: String = "",
  cacheMode: Long = ResourceLoader.CACHE_MODE_REUSE,
): Resource? =
  net.multigesture.kanama.backend.ResourceLoaderBackendContractProbe.load(path, typeHint, cacheMode)
    ?.let { Resource(WebObjectId(it.backendToken().toInt())) }

/** An owned scripted resource created from Kotlin; release via [close] when handed off. */
class OwnedScriptResource<out T>
@PublishedApi
internal constructor(
  /** The live Kotlin script instance — your `@ScriptClass(attachTo = "Resource")` type. */
  val instance: T,
  /** The owning resource wrapper. Save it, assign it into a slot, or [close] to release. */
  val resource: ScriptResource,
) : AutoCloseable {
  override fun close() = resource.close()
}

/** Resource wrapper over a live script handle whose creation reference we own. */
class ScriptResource internal constructor(godotObject: GodotHandle) :
  Resource(godotObject.toBackendHandle()) {
  private var closed = false

  fun close() {
    if (closed) return
    closed = true
    releaseWebScriptResource(handle.value)
  }
}

/**
 * Creates a brand-new, engine-backed script resource of type [T] purely from Kotlin — the Web
 * counterpart of the desktop `newScriptInstance` ("close what you create" applies).
 */
inline fun <reified T : KanamaWebScript> newScriptInstance(): OwnedScriptResource<T> {
  val className =
    checkNotNull(T::class.simpleName) { "Kanama Web script resource type must be named" }
  val handle = newWebScriptResourceHandle(className)
  val instance =
    checkNotNull(webScriptInstance(handle) as? T) {
      "Godot did not hydrate a $className script instance"
    }
  return OwnedScriptResource(instance, newWebScriptResourceWrapper(handle))
}

@PublishedApi
internal fun newWebScriptResourceHandle(className: String): Int {
  val handle = instantiateWebScript(className)
  check(handle != 0) { "Godot could not instantiate script resource $className" }
  return handle
}

@PublishedApi
internal fun newWebScriptResourceWrapper(handle: Int): ScriptResource =
  ScriptResource(WebObjectId(handle))
