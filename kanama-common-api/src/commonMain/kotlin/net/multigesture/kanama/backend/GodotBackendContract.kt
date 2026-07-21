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
}
