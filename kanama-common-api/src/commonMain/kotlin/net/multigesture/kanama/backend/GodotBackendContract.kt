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
