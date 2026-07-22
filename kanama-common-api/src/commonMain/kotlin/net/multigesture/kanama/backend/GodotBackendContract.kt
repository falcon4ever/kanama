package net.multigesture.kanama.backend

/** Internal platform seam. Game code never installs or selects a backend. */
@RequiresOptIn(
  level = RequiresOptIn.Level.ERROR,
  message = "This API is reserved for Kanama platform backends.",
)
@Retention(AnnotationRetention.BINARY)
annotation class InternalKanamaBackendApi

@InternalKanamaBackendApi
enum class GodotExecutionMode {
  IMMEDIATE_RESULT,
  SNAPSHOT_READ,
  QUEUED_MUTATION,
}

@InternalKanamaBackendApi
enum class GodotCallShape {
  BOOL_RET_INT,
  NOARGS_RET_VECTOR2,
  VECTOR2_ARG,
  NOARGS_RET_RECT2,
  NOARGS_VOID,
  TEXTURE2D_VECTOR2_COLOR_ARGS,
  STRING_STRING_LONG_RET_HANDLE,
  STRINGNAME_INT_RET_INT,
  UTILITY_NOARGS_VOID,
  UTILITY_NOARGS_RET_LONG,
  UTILITY_NOARGS_RET_DOUBLE,
  STRINGNAME_RET_HANDLE,
  OBJECT_BOOL_LONG_ARGS,
  OBJECT_ARG,
  NODEPATH_RET_HANDLE,
  LONG_RET_HANDLE,
  NOARGS_RET_HANDLE,
  OBJECT_LONG_VECTOR2_ARGS,
  STRINGNAME_CALLABLE_LONG_RET_LONG,
  STRINGNAME_RET_BOOL,
  NOARGS_RET_BOOL,
  NOARGS_RET_LONG,
  STRINGNAME_VECTOR2I_RET_INT,
}

@InternalKanamaBackendApi
enum class GodotReturnOwnership {
  BORROWED,
  OWNED,
  RETAINED_REFCOUNTED,
}

/** Generator-owned identity for one pinned Godot call site. */
@InternalKanamaBackendApi
data class GodotCallDescriptor(
  val opcode: Int,
  val className: String,
  val methodName: String,
  val hash: Long,
  val shape: GodotCallShape,
  val executionMode: GodotExecutionMode,
  val returnOwnership: GodotReturnOwnership,
) {
  init {
    require(opcode > 0) { "Godot call opcode must be positive" }
    require(className.isNotBlank()) { "Godot call class must not be blank" }
    require(methodName.isNotBlank()) { "Godot call method must not be blank" }
  }
}

/** Platform-neutral immutable value used by the first promoted call-shape family. */
@InternalKanamaBackendApi data class GodotVector2(val x: Float, val y: Float)

/** Platform-neutral immutable integer vector used by typed input signals. */
@InternalKanamaBackendApi data class GodotVector2i(val x: Int, val y: Int)

/** Platform-neutral immutable rectangle snapshot. */
@InternalKanamaBackendApi data class GodotRect2(val position: GodotVector2, val size: GodotVector2)

/** Platform-neutral immutable RGBA value used by typed draw commands. */
@InternalKanamaBackendApi
data class GodotColor(val r: Float, val g: Float, val b: Float, val a: Float = 1.0f)

/** Typed backend SPI. No reflective or `List<Any?>` dispatch is permitted here. */
@InternalKanamaBackendApi
interface GodotBackendSpi {
  fun requireLive(handle: GodotHandle)

  fun resolve(descriptor: GodotCallDescriptor): GodotCallSite

  fun invokeBoolRetInt(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Boolean,
  ): Int

  fun invokeNoArgsRetVector2(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): GodotVector2

  fun invokeVector2Arg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotVector2,
  )

  fun invokeNoArgsRetRect2(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): GodotRect2

  fun invokeNoArgsVoid(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  )

  fun invokeTexture2DVector2ColorArgs(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    texture: GodotHandle,
    position: GodotVector2,
    modulate: GodotColor,
  )

  fun invokeStringStringLongRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    first: String,
    second: String,
    value: Long,
  ): GodotHandle?

  fun invokeUtilityNoArgsVoid(descriptor: GodotCallDescriptor, callSite: GodotCallSite)

  fun invokeUtilityNoArgsRetLong(descriptor: GodotCallDescriptor, callSite: GodotCallSite): Long

  fun invokeUtilityNoArgsRetDouble(descriptor: GodotCallDescriptor, callSite: GodotCallSite): Double

  fun invokeStringNameIntRetInt(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    name: String,
    value: Int,
  ): Int

  fun invokeStringNameRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    value: String,
  ): GodotHandle?

  fun invokeObjectBoolLongArgs(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    objectValue: GodotHandle,
    boolValue: Boolean,
    longValue: Long,
  )

  fun invokeObjectArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotHandle?,
  )

  fun invokeNodePathRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    path: String,
  ): GodotHandle?

  fun invokeLongRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Long,
  ): GodotHandle?

  fun invokeNoArgsRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): GodotHandle?

  fun invokeObjectLongVector2Args(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    objectValue: GodotHandle?,
    longValue: Long,
    vectorValue: GodotVector2,
  )

  fun invokeStringNameCallableLongRetLong(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    signal: String,
    target: GodotHandle,
    method: String,
    flags: Long,
  ): Long

  fun invokeStringNameRetBool(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: String,
  ): Boolean

  fun invokeNoArgsRetBool(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): Boolean

  fun invokeNoArgsRetLong(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): Long

  fun invokeStringNameVector2iRetInt(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    name: String,
    value: GodotVector2i,
  ): Int
}

/**
 * Shared typed facade used by generated wrappers.
 *
 * The selected platform bootstrap installs exactly one target SPI. Call sites resolve once into an
 * opcode-indexed array, so the steady-state path does not dispatch by string or allocate argument
 * lists.
 */
@InternalKanamaBackendApi
@OptIn(InternalKanamaBackendApi::class)
object GodotBackendCalls {
  private const val MAX_INITIAL_OPCODE = 255
  private val resolved = arrayOfNulls<GodotCallSite>(MAX_INITIAL_OPCODE + 1)
  private var backend: GodotBackendSpi? = null

  @InternalKanamaBackendApi
  fun install(platformBackend: GodotBackendSpi) {
    backend = platformBackend
    resolved.fill(null)
  }

  @InternalKanamaBackendApi
  fun resetForTests() {
    backend = null
    resolved.fill(null)
  }

  fun invokeBoolRetInt(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: Boolean,
  ): Int {
    requireShape(descriptor, GodotCallShape.BOOL_RET_INT)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeBoolRetInt(descriptor, resolve(selected, descriptor), receiver, value)
  }

  fun invokeNoArgsRetVector2(descriptor: GodotCallDescriptor, receiver: GodotHandle): GodotVector2 {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_VECTOR2)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeNoArgsRetVector2(descriptor, resolve(selected, descriptor), receiver)
  }

  fun invokeVector2Arg(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: GodotVector2,
  ) {
    requireShape(descriptor, GodotCallShape.VECTOR2_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeVector2Arg(descriptor, resolve(selected, descriptor), receiver, value)
  }

  fun invokeNoArgsRetRect2(descriptor: GodotCallDescriptor, receiver: GodotHandle): GodotRect2 {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_RECT2)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeNoArgsRetRect2(descriptor, resolve(selected, descriptor), receiver)
  }

  fun invokeNoArgsVoid(descriptor: GodotCallDescriptor, receiver: GodotHandle) {
    requireShape(descriptor, GodotCallShape.NOARGS_VOID)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeNoArgsVoid(descriptor, resolve(selected, descriptor), receiver)
  }

  fun invokeTexture2DVector2ColorArgs(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    texture: GodotHandle,
    position: GodotVector2,
    modulate: GodotColor,
  ) {
    requireShape(descriptor, GodotCallShape.TEXTURE2D_VECTOR2_COLOR_ARGS)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeTexture2DVector2ColorArgs(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      texture,
      position,
      modulate,
    )
  }

  fun invokeStringStringLongRetHandle(
    descriptor: GodotCallDescriptor,
    first: String,
    second: String,
    value: Long,
  ): GodotHandle? {
    requireShape(descriptor, GodotCallShape.STRING_STRING_LONG_RET_HANDLE)
    val selected = requireBackend()
    return selected.invokeStringStringLongRetHandle(
      descriptor,
      resolve(selected, descriptor),
      first,
      second,
      value,
    )
  }

  fun invokeUtilityNoArgsVoid(descriptor: GodotCallDescriptor) {
    requireShape(descriptor, GodotCallShape.UTILITY_NOARGS_VOID)
    val selected = requireBackend()
    selected.invokeUtilityNoArgsVoid(descriptor, resolve(selected, descriptor))
  }

  fun invokeUtilityNoArgsRetLong(descriptor: GodotCallDescriptor): Long {
    requireShape(descriptor, GodotCallShape.UTILITY_NOARGS_RET_LONG)
    val selected = requireBackend()
    return selected.invokeUtilityNoArgsRetLong(descriptor, resolve(selected, descriptor))
  }

  fun invokeUtilityNoArgsRetDouble(descriptor: GodotCallDescriptor): Double {
    requireShape(descriptor, GodotCallShape.UTILITY_NOARGS_RET_DOUBLE)
    val selected = requireBackend()
    return selected.invokeUtilityNoArgsRetDouble(descriptor, resolve(selected, descriptor))
  }

  fun invokeStringNameIntRetInt(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    name: String,
    value: Int,
  ): Int {
    requireShape(descriptor, GodotCallShape.STRINGNAME_INT_RET_INT)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeStringNameIntRetInt(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      name,
      value,
    )
  }

  fun invokeStringNameRetHandle(descriptor: GodotCallDescriptor, value: String): GodotHandle? {
    requireShape(descriptor, GodotCallShape.STRINGNAME_RET_HANDLE)
    val selected = requireBackend()
    return selected.invokeStringNameRetHandle(descriptor, resolve(selected, descriptor), value)
  }

  fun invokeObjectBoolLongArgs(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    objectValue: GodotHandle,
    boolValue: Boolean,
    longValue: Long,
  ) {
    requireShape(descriptor, GodotCallShape.OBJECT_BOOL_LONG_ARGS)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.requireLive(objectValue)
    selected.invokeObjectBoolLongArgs(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      objectValue,
      boolValue,
      longValue,
    )
  }

  fun invokeObjectArg(descriptor: GodotCallDescriptor, receiver: GodotHandle, value: GodotHandle?) {
    requireShape(descriptor, GodotCallShape.OBJECT_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    value?.let(selected::requireLive)
    selected.invokeObjectArg(descriptor, resolve(selected, descriptor), receiver, value)
  }

  fun invokeNodePathRetHandle(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    path: String,
  ): GodotHandle? {
    requireShape(descriptor, GodotCallShape.NODEPATH_RET_HANDLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeNodePathRetHandle(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      path,
    )
  }

  fun invokeLongRetHandle(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: Long,
  ): GodotHandle? {
    requireShape(descriptor, GodotCallShape.LONG_RET_HANDLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeLongRetHandle(descriptor, resolve(selected, descriptor), receiver, value)
  }

  fun invokeNoArgsRetHandle(descriptor: GodotCallDescriptor, receiver: GodotHandle): GodotHandle? {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_HANDLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeNoArgsRetHandle(descriptor, resolve(selected, descriptor), receiver)
  }

  fun invokeObjectLongVector2Args(
    descriptor: GodotCallDescriptor,
    objectValue: GodotHandle?,
    longValue: Long,
    vectorValue: GodotVector2,
  ) {
    requireShape(descriptor, GodotCallShape.OBJECT_LONG_VECTOR2_ARGS)
    val selected = requireBackend()
    objectValue?.let(selected::requireLive)
    selected.invokeObjectLongVector2Args(
      descriptor,
      resolve(selected, descriptor),
      objectValue,
      longValue,
      vectorValue,
    )
  }

  fun invokeStringNameCallableLongRetLong(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    signal: String,
    target: GodotHandle,
    method: String,
    flags: Long,
  ): Long {
    requireShape(descriptor, GodotCallShape.STRINGNAME_CALLABLE_LONG_RET_LONG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.requireLive(target)
    return selected.invokeStringNameCallableLongRetLong(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      signal,
      target,
      method,
      flags,
    )
  }

  fun invokeStringNameRetBool(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: String,
  ): Boolean {
    requireShape(descriptor, GodotCallShape.STRINGNAME_RET_BOOL)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeStringNameRetBool(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      value,
    )
  }

  fun invokeNoArgsRetBool(descriptor: GodotCallDescriptor, receiver: GodotHandle): Boolean {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_BOOL)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeNoArgsRetBool(descriptor, resolve(selected, descriptor), receiver)
  }

  fun invokeNoArgsRetLong(descriptor: GodotCallDescriptor, receiver: GodotHandle): Long {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_LONG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeNoArgsRetLong(descriptor, resolve(selected, descriptor), receiver)
  }

  fun invokeStringNameVector2iRetInt(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    name: String,
    value: GodotVector2i,
  ): Int {
    requireShape(descriptor, GodotCallShape.STRINGNAME_VECTOR2I_RET_INT)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeStringNameVector2iRetInt(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      name,
      value,
    )
  }

  private fun resolve(selected: GodotBackendSpi, descriptor: GodotCallDescriptor): GodotCallSite {
    require(descriptor.opcode <= MAX_INITIAL_OPCODE) {
      "Godot call opcode ${descriptor.opcode} exceeds the initial contract table"
    }
    return resolved[descriptor.opcode]
      ?: selected.resolve(descriptor).also { resolved[descriptor.opcode] = it }
  }

  private fun requireBackend(): GodotBackendSpi =
    backend ?: error("Kanama platform backend was not installed by platform bootstrap")

  private fun requireShape(descriptor: GodotCallDescriptor, expected: GodotCallShape) {
    require(descriptor.shape == expected) {
      "Godot call ${descriptor.className}.${descriptor.methodName} has shape " +
        "${descriptor.shape}, expected $expected"
    }
  }
}

/**
 * First common-source wrapper contract probe.
 *
 * This deliberately models only the promoted call shapes. It is not a replacement public Node2D
 * class: the production class still inherits through CanvasItem until that transitive closure is
 * admitted to the common module.
 */
@InternalKanamaBackendApi
class Node2DBackendContractProbe(val handle: GodotHandle) {
  val viewportRect: GodotRect2
    get() =
      GodotBackendCalls.invokeNoArgsRetRect2(
        InitialGodotCallDescriptors.CANVASITEM_GET_VIEWPORT_RECT,
        handle,
      )

  var position: GodotVector2
    get() =
      GodotBackendCalls.invokeNoArgsRetVector2(
        InitialGodotCallDescriptors.NODE2D_GET_POSITION,
        handle,
      )
    set(value) {
      GodotBackendCalls.invokeVector2Arg(
        InitialGodotCallDescriptors.NODE2D_SET_POSITION,
        handle,
        value,
      )
    }

  fun getChildCount(includeInternal: Boolean = false): Long =
    GodotBackendCalls.invokeBoolRetInt(
        InitialGodotCallDescriptors.NODE_GET_CHILD_COUNT,
        handle,
        includeInternal,
      )
      .toLong()

  fun queueRedraw() {
    GodotBackendCalls.invokeNoArgsVoid(InitialGodotCallDescriptors.CANVASITEM_QUEUE_REDRAW, handle)
  }

  fun drawTexture(texture: GodotHandle, position: GodotVector2, modulate: GodotColor) {
    GodotBackendCalls.invokeTexture2DVector2ColorArgs(
      InitialGodotCallDescriptors.CANVASITEM_DRAW_TEXTURE,
      handle,
      texture,
      position,
      modulate,
    )
  }

  fun emitSignal(name: String, value: Int): Int =
    GodotBackendCalls.invokeStringNameIntRetInt(
      InitialGodotCallDescriptors.OBJECT_EMIT_SIGNAL,
      handle,
      name,
      value,
    )
}

/** First typed singleton-call probe; production wrappers delegate through the same facade. */
@InternalKanamaBackendApi
object ResourceLoaderBackendContractProbe {
  fun load(path: String, typeHint: String = "", cacheMode: Long = 1L): GodotHandle? =
    GodotBackendCalls.invokeStringStringLongRetHandle(
      InitialGodotCallDescriptors.RESOURCELOADER_LOAD,
      path,
      typeHint,
      cacheMode,
    )
}

/** Typed global utility slice used by the first Bunnymark port. */
@InternalKanamaBackendApi
object GDBackendContractProbe {
  fun randomize() {
    GodotBackendCalls.invokeUtilityNoArgsVoid(InitialGodotCallDescriptors.UTILITY_RANDOMIZE)
  }

  fun randi(): Long =
    GodotBackendCalls.invokeUtilityNoArgsRetLong(InitialGodotCallDescriptors.UTILITY_RANDI)

  fun randf(): Double =
    GodotBackendCalls.invokeUtilityNoArgsRetDouble(InitialGodotCallDescriptors.UTILITY_RANDF)
}

/** Typed dynamic construction slice used by generated wrapper factories. */
@InternalKanamaBackendApi
object ClassDBBackendContractProbe {
  fun instantiate(className: String): GodotHandle? =
    GodotBackendCalls.invokeStringNameRetHandle(
      InitialGodotCallDescriptors.CLASSDB_INSTANTIATE,
      className,
    )
}

/** Typed Node mutation slice used by the sprite Bunnymark port. */
@InternalKanamaBackendApi
class NodeBackendContractProbe(private val handle: GodotHandle) {
  fun addChild(child: GodotHandle, forceReadableName: Boolean = false, internalMode: Long = 0L) {
    GodotBackendCalls.invokeObjectBoolLongArgs(
      InitialGodotCallDescriptors.NODE_ADD_CHILD,
      handle,
      child,
      forceReadableName,
      internalMode,
    )
  }

  fun removeChild(child: GodotHandle) {
    GodotBackendCalls.invokeObjectArg(InitialGodotCallDescriptors.NODE_REMOVE_CHILD, handle, child)
  }

  fun queueFree() {
    GodotBackendCalls.invokeNoArgsVoid(InitialGodotCallDescriptors.NODE_QUEUE_FREE, handle)
  }
}

/** Typed Sprite2D mutation slice used by the sprite Bunnymark port. */
@InternalKanamaBackendApi
class Sprite2DBackendContractProbe(private val handle: GodotHandle) {
  fun setTexture(texture: GodotHandle?) {
    GodotBackendCalls.invokeObjectArg(
      InitialGodotCallDescriptors.SPRITE2D_SET_TEXTURE,
      handle,
      texture,
    )
  }
}

/** Typed board-construction slice shared by Match3 across every backend. */
@InternalKanamaBackendApi
class NodeLookupBackendContractProbe(private val handle: GodotHandle) {
  fun getNodeOrNull(path: String): GodotHandle? =
    GodotBackendCalls.invokeNodePathRetHandle(
      InitialGodotCallDescriptors.NODE_GET_NODE_OR_NULL,
      handle,
      path,
    )

  fun getViewport(): GodotHandle? =
    GodotBackendCalls.invokeNoArgsRetHandle(InitialGodotCallDescriptors.NODE_GET_VIEWPORT, handle)
}

/** Typed PackedScene instantiation slice used by Match3 board construction. */
@InternalKanamaBackendApi
class PackedSceneBackendContractProbe(private val handle: GodotHandle) {
  fun instantiate(editState: Long = 0L): GodotHandle? =
    GodotBackendCalls.invokeLongRetHandle(
      InitialGodotCallDescriptors.PACKEDSCENE_INSTANTIATE,
      handle,
      editState,
    )
}

/** Typed viewport snapshot used by Match3 board centering. */
@InternalKanamaBackendApi
class ViewportBackendContractProbe(private val handle: GodotHandle) {
  val visibleRect: GodotRect2
    get() =
      GodotBackendCalls.invokeNoArgsRetRect2(
        InitialGodotCallDescriptors.VIEWPORT_GET_VISIBLE_RECT,
        handle,
      )
}

/** Typed Input singleton slice used by Match3 cursor setup. */
@InternalKanamaBackendApi
object InputBackendContractProbe {
  fun setCustomMouseCursor(
    texture: GodotHandle?,
    shape: Long = 0L,
    hotspot: GodotVector2 = GodotVector2(0.0f, 0.0f),
  ) {
    GodotBackendCalls.invokeObjectLongVector2Args(
      InitialGodotCallDescriptors.INPUT_SET_CUSTOM_MOUSE_CURSOR,
      texture,
      shape,
      hotspot,
    )
  }
}

/** Typed object-method Callable connection used for board wiring. */
@InternalKanamaBackendApi
class SignalBackendContractProbe(private val handle: GodotHandle) {
  fun connect(target: GodotHandle, signal: String, method: String, flags: Long = 0L): Long =
    GodotBackendCalls.invokeStringNameCallableLongRetLong(
      InitialGodotCallDescriptors.OBJECT_CONNECT,
      handle,
      signal,
      target,
      method,
      flags,
    )

  fun emitVector2i(name: String, value: GodotVector2i): Int =
    GodotBackendCalls.invokeStringNameVector2iRetInt(
      InitialGodotCallDescriptors.OBJECT_EMIT_SIGNAL_VECTOR2I,
      handle,
      name,
      value,
    )
}

/** Typed runtime type query used to narrow browser-delivered input events. */
@InternalKanamaBackendApi
class GodotObjectBackendContractProbe(private val handle: GodotHandle) {
  fun isClass(className: String): Boolean =
    GodotBackendCalls.invokeStringNameRetBool(
      InitialGodotCallDescriptors.OBJECT_IS_CLASS,
      handle,
      className,
    )
}

/** Typed InputEvent state queries shared by the generated input handoff. */
@InternalKanamaBackendApi
class InputEventBackendContractProbe(private val handle: GodotHandle) {
  fun isPressed(): Boolean =
    GodotBackendCalls.invokeNoArgsRetBool(InitialGodotCallDescriptors.INPUTEVENT_IS_PRESSED, handle)

  fun isReleased(): Boolean =
    GodotBackendCalls.invokeNoArgsRetBool(
      InitialGodotCallDescriptors.INPUTEVENT_IS_RELEASED,
      handle,
    )
}

/** Typed mouse-button query used by Match3 tile input. */
@InternalKanamaBackendApi
class InputEventMouseButtonBackendContractProbe(private val handle: GodotHandle) {
  fun getButtonIndex(): Long =
    GodotBackendCalls.invokeNoArgsRetLong(
      InitialGodotCallDescriptors.INPUTEVENTMOUSEBUTTON_GET_BUTTON_INDEX,
      handle,
    )
}

/** Immediate local pointer position used only while processing a delivered input event. */
@InternalKanamaBackendApi
class CanvasItemInputBackendContractProbe(private val handle: GodotHandle) {
  fun getLocalMousePosition(): GodotVector2 =
    GodotBackendCalls.invokeNoArgsRetVector2(
      InitialGodotCallDescriptors.CANVASITEM_GET_LOCAL_MOUSE_POSITION,
      handle,
    )
}
