package net.multigesture.kanama.backend

/**
 * Internal seam between the Web call contract and its one implementer, the generated Kotlin/Wasm
 * backend. Game code never installs or selects a backend.
 */
@RequiresOptIn(
  level = RequiresOptIn.Level.ERROR,
  message =
    "This API is reserved for the Kanama Web backend and the wrappers built on its contract.",
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
  BOOL_ARG,
  BOOL_RET_INT,
  BOOL_RET_HANDLE,
  DOUBLE_ARG,
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
  LONG_ARG,
  LONG_RET_HANDLE,
  NOARGS_RET_HANDLE,
  OBJECT_LONG_VECTOR2_ARGS,
  STRINGNAME_CALLABLE_LONG_RET_LONG,
  STRINGNAME_BOUND_CALLABLE_LONG_RET_LONG,
  STRINGNAME_RET_INT,
  STRINGNAME_RET_BOOL,
  STRINGNAME_RET_BOOL_SINGLETON,
  NOARGS_RET_BOOL,
  NOARGS_RET_DOUBLE,
  NOARGS_RET_LONG,
  NOARGS_RET_STRING_ARRAY,
  STRINGNAME_ARG,
  STRINGNAME_BOOL_ARG,
  LONG_BOOL_ARG,
  STRINGNAME_STRINGNAME_ARG,
  STRINGNAME_VECTOR2I_RET_INT,
  NOARGS_RET_COLOR,
  COLOR_ARG,
  OBJECT_NODEPATH_VECTOR2_DOUBLE_RET_HANDLE,
  OBJECT_NODEPATH_COLOR_DOUBLE_RET_HANDLE,
  NOARGS_RET_VECTOR3,
  VECTOR3_ARG,
  LONG_DOUBLE_ARG,
  NOARGS_RET_STRING_SINGLETON,
  STRINGNAME_ARG_SINGLETON,
  STRINGNAME_DOUBLE_ARG,
  STRINGNAME_STRINGNAME_RET_DOUBLE_SINGLETON,
  VECTOR3_VECTOR3_ARG,
  LONG_ARG_SINGLETON,
  OBJECT_RET_HANDLE,
  OBJECT_NODEPATH_VECTOR3_DOUBLE_RET_HANDLE,
  OBJECT_NODEPATH_DOUBLE_DOUBLE_RET_HANDLE,
  COLOR_RET_HANDLE,
  CALLABLE_RET_HANDLE,
  NOARGS_RET_LONG_SINGLETON,
  STRINGNAME_OBJECT_RET_INT,
  VECTOR3_RET_HANDLE,
  NOARGS_RET_HANDLE_LIST,
  LONG_RET_VECTOR3,
  STRINGNAME_RET_DOUBLE_SINGLETON,
  STRINGNAME_VECTOR3_VECTOR3_ARG,
  STRINGNAME_STRING_RET_INT,
  CALLABLE_DOUBLE_RANGE_RET_HANDLE,
  VECTOR3I_LONG_LONG_ARG,
  VECTOR3I_RET_LONG,
  BASIS_RET_LONG,
  NOARGS_RET_VECTOR3I_LIST,
  NOARGS_RET_LONG_LIST_SINGLETON,
  LONG_RET_BOOL_SINGLETON,
  NOARGS_RET_VECTOR2_SINGLETON,
  STRINGNAME_RET_HANDLE_LIST,
  DOUBLE_RET_HANDLE,
  LONG_OBJECT_ARG,
  LONG_TRANSFORM3D_ARG,
  LONG_RET_STRING,
  LONG_RET_LONG,
  LONG_LONG_RET_STRING,
  LONG_LONG_RET_HANDLE,
  VECTOR2_RET_VECTOR3,
  OBJECT_STRING_RET_LONG_SINGLETON,
  STRING_STRING_BOOL_BOOL_RET_HANDLE_LIST,
  STRINGNAME_LONG_ARG,
  STRINGNAME_VECTOR2_ARG,
  STRINGNAME_OBJECT_ARG,
  STRINGNAME_OBJECT_ARG_SINGLETON,
  STRINGNAME_RET_VECTOR2,
  NOARGS_RET_STRING,
  STRINGNAME_RET_STRING,
  DOUBLE_RET_DOUBLE,
  VECTOR3_VECTOR3_LONG_OBJECT_RET_STRING,
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

/** Immutable contract value used by the first promoted call-shape family. */
@InternalKanamaBackendApi data class GodotVector2(val x: Float, val y: Float)

/** Immutable contract integer vector used by typed input signals. */
@InternalKanamaBackendApi data class GodotVector2i(val x: Int, val y: Int)

/** Immutable contract 3D vector used by the 3D node transform families. */
@InternalKanamaBackendApi data class GodotVector3(val x: Float, val y: Float, val z: Float)

/** Immutable contract rectangle snapshot. */
@InternalKanamaBackendApi data class GodotRect2(val position: GodotVector2, val size: GodotVector2)

/** Immutable contract RGBA value used by typed draw commands. */
@InternalKanamaBackendApi
data class GodotColor(val r: Float, val g: Float, val b: Float, val a: Float = 1.0f)

/** Immutable contract integer 3D vector used by the grid-cell families. */
@InternalKanamaBackendApi data class GodotVector3i(val x: Int, val y: Int, val z: Int)

/** Immutable contract basis as the three axis (column) vectors. */
@InternalKanamaBackendApi
data class GodotBasis(val x: GodotVector3, val y: GodotVector3, val z: GodotVector3)

/** Immutable contract 3D transform (basis plus origin). */
@InternalKanamaBackendApi
data class GodotTransform3D(val basis: GodotBasis, val origin: GodotVector3)

/**
 * Typed backend SPI. Its only implementer is the generated Kotlin/Wasm `WebCommonGodotBackend`
 * (`scripts/generate_web_backend.py`), which overrides every member, so every member is abstract: a
 * shape the generator stops emitting fails at compile time rather than at the first call. No
 * reflective or `List<Any?>` dispatch is permitted here.
 */
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

  fun invokeBoolRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Boolean,
  ): GodotHandle?

  fun invokeBoolArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Boolean,
  )

  fun invokeDoubleArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Double,
  )

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

  fun invokeLongArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Long,
  )

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

  fun invokeStringNameBoundCallableLongRetLong(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    signal: String,
    target: GodotHandle,
    method: String,
    boundValue: Long,
    flags: Long,
  ): Long

  fun invokeStringNameRetInt(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: String,
  ): Int

  fun invokeStringNameRetBool(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: String,
  ): Boolean

  /** Singleton query (no receiver): the backend supplies the calling context itself. */
  fun invokeStringNameRetBoolSingleton(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    value: String,
  ): Boolean

  fun invokeNoArgsRetBool(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): Boolean

  fun invokeNoArgsRetDouble(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): Double

  fun invokeNoArgsRetLong(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): Long

  fun invokeNoArgsRetStringArray(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): List<String>

  fun invokeStringNameArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: String,
  )

  fun invokeStringNameBoolArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    name: String,
    value: Boolean,
  )

  fun invokeLongBoolArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    layer: Long,
    value: Boolean,
  )

  fun invokeStringNameStringNameArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    first: String,
    second: String,
  )

  fun invokeStringNameVector2iRetInt(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    name: String,
    value: GodotVector2i,
  ): Int

  fun invokeNoArgsRetColor(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): GodotColor

  fun invokeColorArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotColor,
  )

  fun invokeObjectNodePathVector2DoubleRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    target: GodotHandle,
    property: String,
    finalValue: GodotVector2,
    duration: Double,
  ): GodotHandle?

  fun invokeObjectNodePathColorDoubleRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    target: GodotHandle,
    property: String,
    finalValue: GodotColor,
    duration: Double,
  ): GodotHandle?

  fun invokeNoArgsRetVector3(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): GodotVector3

  fun invokeVector3Arg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotVector3,
  )

  fun invokeLongDoubleArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    longValue: Long,
    doubleValue: Double,
  )

  /** Singleton String query (no receiver): the backend supplies the calling context. */
  fun invokeNoArgsRetStringSingleton(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
  ): String

  /** Singleton String-argument void call (no receiver): e.g. Input.action_press/action_release. */
  fun invokeStringNameArgSingleton(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    value: String,
  )

  fun invokeStringNameDoubleArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: String,
    doubleValue: Double,
  )

  /** Singleton two-StringName → Double query (no receiver): e.g. Input.get_axis. */
  fun invokeStringNameStringNameRetDoubleSingleton(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    first: String,
    second: String,
  ): Double

  /** Two-Vector3 void call: e.g. Node3D.look_at_from_position with baked up/use_model_front. */
  fun invokeVector3Vector3Arg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    first: GodotVector3,
    second: GodotVector3,
  )

  /** Singleton Long-argument void call (no receiver): e.g. RenderingServer shadow tuning. */
  fun invokeLongArgSingleton(descriptor: GodotCallDescriptor, callSite: GodotCallSite, value: Long)

  /** Object-argument fluent call returning a handle: e.g. Tween.bind_node self-return. */
  fun invokeObjectRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotHandle,
  ): GodotHandle?

  /** Vector3 property tweener (mirrors the Vector2/Color variants). */
  fun invokeObjectNodePathVector3DoubleRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    target: GodotHandle,
    property: String,
    finalValue: GodotVector3,
    duration: Double,
  ): GodotHandle?

  /**
   * SCALAR property tweener -- `tween_property(node, "position:y", 4.0, 0.5)`.
   *
   * The sibling of the Vector2/Color/Vector3 variants, and the one that was missing: a component
   * path like `"position:y"` takes a NUMBER, so third-person's coin spill (CoinsContainer) had no
   * arm at all and faulted the Web boundary when a beetle attack landed. Every other tween shape
   * was present, which is exactly why nobody noticed.
   */
  fun invokeObjectNodePathDoubleDoubleRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    target: GodotHandle,
    property: String,
    finalValue: Double,
    duration: Double,
  ): GodotHandle?

  /**
   * A fluent call taking a COLOR and returning the receiver -- `PropertyTweener.from(Color)`.
   *
   * Task 64 tier 2: Icone's fade sets a custom starting value so the tween runs from the opposite
   * alpha rather than from wherever the modulate happened to be. Without this the web override had
   * to drop `from(...)` entirely, which is a different animation.
   */
  fun invokeColorRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotColor,
  ): GodotHandle?

  /** Callable-argument fluent call: e.g. Tween.tween_callback(Callable(target, method)). */
  fun invokeCallableRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    target: GodotHandle,
    method: String,
  ): GodotHandle?

  /** Singleton no-args Long query (no receiver): e.g. Input.get_mouse_mode. */
  fun invokeNoArgsRetLongSingleton(descriptor: GodotCallDescriptor, callSite: GodotCallSite): Long

  /** Singleton no-args Long-list query (no receiver): e.g. Input.get_connected_joypads. */
  fun invokeNoArgsRetLongListSingleton(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
  ): List<Long>

  /** Singleton Long-argument boolean query (no receiver): e.g. Input.is_key_pressed. */
  fun invokeLongRetBoolSingleton(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    value: Long,
  ): Boolean

  /** Singleton no-args Vector2 query (no receiver): e.g. Input.get_last_mouse_velocity. */
  fun invokeNoArgsRetVector2Singleton(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
  ): GodotVector2

  /** StringName-argument handle-list query: e.g. SceneTree.get_nodes_in_group. */
  fun invokeStringNameRetHandleList(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: String,
  ): List<GodotHandle>

  /** Double-argument handle query (a retained RefCounted result): e.g. SceneTree.create_timer. */
  fun invokeDoubleRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Double,
  ): GodotHandle?

  /** Signal emission carrying one Godot-object argument. */
  fun invokeStringNameObjectRetInt(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    name: String,
    value: GodotHandle,
  ): Int

  /** Motion sweep returning the first collision as a closeable handle. */
  fun invokeVector3RetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotVector3,
  ): GodotHandle?

  /** Handle-list query (overlapping scripted bodies). */
  fun invokeNoArgsRetHandleList(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): List<GodotHandle>

  /** Indexed Vector3 query (shape-cast collision points). */
  fun invokeLongRetVector3(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Long,
  ): GodotVector3

  /** Singleton double query keyed by one name (raw strength, float settings). */
  fun invokeStringNameRetDoubleSingleton(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    value: String,
  ): Double

  /** Dynamic method dispatch carrying two Vector3 arguments. */
  fun invokeStringNameVector3Vector3Arg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    name: String,
    first: GodotVector3,
    second: GodotVector3,
  )

  /** Method tween over an interpolated double range. */
  fun invokeCallableDoubleRangeRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    target: GodotHandle,
    method: String,
    fromValue: Double,
    toValue: Double,
    duration: Double,
  ): GodotHandle?

  /** Signal emission carrying one String argument. */
  fun invokeStringNameStringRetInt(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    name: String,
    value: String,
  ): Int

  /** Grid-cell write carrying a Vector3i position plus item and orientation indices. */
  fun invokeVector3iLongLongArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotVector3i,
    first: Long,
    second: Long,
  )

  /** Grid-cell integer query keyed by a Vector3i position. */
  fun invokeVector3iRetLong(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotVector3i,
  ): Long

  /** Integer query keyed by a rotation basis (orthogonal orientation index). */
  fun invokeBasisRetLong(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotBasis,
  ): Long

  /** Vector3i-list query (occupied grid cells). */
  fun invokeNoArgsRetVector3iList(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): List<GodotVector3i>

  /** Indexed object write (mesh-library item mesh). */
  fun invokeLongObjectArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    longValue: Long,
    objectValue: GodotHandle,
  )

  /** Indexed transform write (mesh-library item transform). */
  fun invokeLongTransform3dArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    longValue: Long,
    value: GodotTransform3D,
  )

  /** Indexed string query (scene-state node type). */
  fun invokeLongRetString(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Long,
  ): String

  /** Indexed integer query (scene-state property count). */
  fun invokeLongRetLong(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Long,
  ): Long

  /** Doubly indexed string query (scene-state property name). */
  fun invokeLongLongRetString(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    first: Long,
    second: Long,
  ): String

  /** Doubly indexed object query (scene-state property value; non-objects resolve null). */
  fun invokeLongLongRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    first: Long,
    second: Long,
  ): GodotHandle?

  /** Screen-point to world-space Vector3 query (camera ray projection). */
  fun invokeVector2RetVector3(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotVector2,
  ): GodotVector3

  /** Singleton resource persist (ResourceSaver.save). */
  fun invokeObjectStringRetLongSingleton(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    resource: GodotHandle,
    path: String,
    flags: Long,
  ): Long

  /** Descendant search returning tracked handles (Node.find_children). */
  fun invokeStringStringBoolBoolRetHandleList(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    pattern: String,
    type: String,
    recursive: Boolean,
    owned: Boolean,
  ): List<GodotHandle>

  /** Named int-valued property write (AnimationTree one-shot requests). */
  fun invokeStringNameLongArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    name: String,
    value: Long,
  )

  /** Named Vector2-valued property write (AnimationTree blend positions). */
  fun invokeStringNameVector2Arg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    name: String,
    value: GodotVector2,
  )

  /** Named object-valued call (one-object deferred dispatch). */
  fun invokeStringNameObjectArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    name: String,
    value: GodotHandle,
  )

  /**
   * Singleton call carrying a name and one object handle -- `InputMap.action_add_event(action,
   * event)` (task 64 tier 3). No receiver: the singleton is reached through the active script
   * owner, and the object argument is a handle that owner already tracks (the constructed
   * InputEventKey).
   */
  fun invokeStringNameObjectArgSingleton(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    name: String,
    value: GodotHandle,
  )

  /** Named Vector2-valued property read (AnimationTree blend positions). */
  fun invokeStringNameRetVector2(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    name: String,
  ): GodotVector2

  /** No-argument String read (Node.get_name, LineEdit.get_text). */
  fun invokeNoArgsRetString(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): String

  /** Named String read (ConfigFile.get_value's tagged transport). */
  fun invokeStringNameRetString(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    name: String,
  ): String

  /** Double-argument Double query (Noise.get_noise_1d). */
  fun invokeDoubleRetDouble(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Double,
  ): Double

  /**
   * Space-state ray query. The receiver is a node in the target world: the backend derives the
   * space state and builds the query parameters on its own side, so RIDs never cross the seam.
   */
  fun invokeVector3Vector3LongObjectRetString(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    from: GodotVector3,
    to: GodotVector3,
    collisionMask: Long,
    exclude: GodotHandle?,
  ): String
}

/**
 * Typed facade the Web wrappers call into; the Wasm entry point installs the one generated backend.
 *
 * Call sites resolve once into an opcode-indexed array, so the steady-state path does not dispatch
 * by string or allocate argument lists.
 */
@InternalKanamaBackendApi
@OptIn(InternalKanamaBackendApi::class)
object GodotBackendCalls {
  // Derived from the shared contract so the cache cannot silently fall behind the
  // opcode table (task 60i grew it past the previous hardcoded bound).
  private val MAX_INITIAL_OPCODE = InitialGodotCallDescriptors.MAX_OPCODE
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

  fun invokeBoolRetHandle(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: Boolean,
  ): GodotHandle? {
    requireShape(descriptor, GodotCallShape.BOOL_RET_HANDLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeBoolRetHandle(descriptor, resolve(selected, descriptor), receiver, value)
  }

  fun invokeBoolArg(descriptor: GodotCallDescriptor, receiver: GodotHandle, value: Boolean) {
    requireShape(descriptor, GodotCallShape.BOOL_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeBoolArg(descriptor, resolve(selected, descriptor), receiver, value)
  }

  fun invokeDoubleArg(descriptor: GodotCallDescriptor, receiver: GodotHandle, value: Double) {
    requireShape(descriptor, GodotCallShape.DOUBLE_ARG)
    require(value.isFinite()) { "Godot Double argument must be finite" }
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeDoubleArg(descriptor, resolve(selected, descriptor), receiver, value)
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

  fun invokeLongArg(descriptor: GodotCallDescriptor, receiver: GodotHandle, value: Long) {
    requireShape(descriptor, GodotCallShape.LONG_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeLongArg(descriptor, resolve(selected, descriptor), receiver, value)
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

  fun invokeStringNameBoundCallableLongRetLong(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    signal: String,
    target: GodotHandle,
    method: String,
    boundValue: Long,
    flags: Long,
  ): Long {
    requireShape(descriptor, GodotCallShape.STRINGNAME_BOUND_CALLABLE_LONG_RET_LONG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.requireLive(target)
    return selected.invokeStringNameBoundCallableLongRetLong(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      signal,
      target,
      method,
      boundValue,
      flags,
    )
  }

  fun invokeStringNameRetInt(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: String,
  ): Int {
    requireShape(descriptor, GodotCallShape.STRINGNAME_RET_INT)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeStringNameRetInt(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      value,
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

  fun invokeStringNameRetBoolSingleton(descriptor: GodotCallDescriptor, value: String): Boolean {
    requireShape(descriptor, GodotCallShape.STRINGNAME_RET_BOOL_SINGLETON)
    val selected = requireBackend()
    return selected.invokeStringNameRetBoolSingleton(
      descriptor,
      resolve(selected, descriptor),
      value,
    )
  }

  fun invokeNoArgsRetBool(descriptor: GodotCallDescriptor, receiver: GodotHandle): Boolean {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_BOOL)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeNoArgsRetBool(descriptor, resolve(selected, descriptor), receiver)
  }

  fun invokeNoArgsRetDouble(descriptor: GodotCallDescriptor, receiver: GodotHandle): Double {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_DOUBLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeNoArgsRetDouble(descriptor, resolve(selected, descriptor), receiver)
  }

  fun invokeNoArgsRetStringArray(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
  ): List<String> {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_STRING_ARRAY)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeNoArgsRetStringArray(descriptor, resolve(selected, descriptor), receiver)
  }

  fun invokeNoArgsRetLong(descriptor: GodotCallDescriptor, receiver: GodotHandle): Long {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_LONG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeNoArgsRetLong(descriptor, resolve(selected, descriptor), receiver)
  }

  fun invokeStringNameArg(descriptor: GodotCallDescriptor, receiver: GodotHandle, value: String) {
    requireShape(descriptor, GodotCallShape.STRINGNAME_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeStringNameArg(descriptor, resolve(selected, descriptor), receiver, value)
  }

  fun invokeStringNameBoolArg(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    name: String,
    value: Boolean,
  ) {
    requireShape(descriptor, GodotCallShape.STRINGNAME_BOOL_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeStringNameBoolArg(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      name,
      value,
    )
  }

  fun invokeLongBoolArg(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    layer: Long,
    value: Boolean,
  ) {
    requireShape(descriptor, GodotCallShape.LONG_BOOL_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeLongBoolArg(descriptor, resolve(selected, descriptor), receiver, layer, value)
  }

  fun invokeStringNameStringNameArg(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    first: String,
    second: String,
  ) {
    requireShape(descriptor, GodotCallShape.STRINGNAME_STRINGNAME_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeStringNameStringNameArg(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      first,
      second,
    )
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

  fun invokeNoArgsRetColor(descriptor: GodotCallDescriptor, receiver: GodotHandle): GodotColor {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_COLOR)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeNoArgsRetColor(descriptor, resolve(selected, descriptor), receiver)
  }

  fun invokeColorArg(descriptor: GodotCallDescriptor, receiver: GodotHandle, value: GodotColor) {
    requireShape(descriptor, GodotCallShape.COLOR_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeColorArg(descriptor, resolve(selected, descriptor), receiver, value)
  }

  fun invokeObjectNodePathVector2DoubleRetHandle(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    target: GodotHandle,
    property: String,
    finalValue: GodotVector2,
    duration: Double,
  ): GodotHandle? {
    requireShape(descriptor, GodotCallShape.OBJECT_NODEPATH_VECTOR2_DOUBLE_RET_HANDLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.requireLive(target)
    return selected.invokeObjectNodePathVector2DoubleRetHandle(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      target,
      property,
      finalValue,
      duration,
    )
  }

  fun invokeObjectNodePathColorDoubleRetHandle(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    target: GodotHandle,
    property: String,
    finalValue: GodotColor,
    duration: Double,
  ): GodotHandle? {
    requireShape(descriptor, GodotCallShape.OBJECT_NODEPATH_COLOR_DOUBLE_RET_HANDLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.requireLive(target)
    return selected.invokeObjectNodePathColorDoubleRetHandle(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      target,
      property,
      finalValue,
      duration,
    )
  }

  fun invokeNoArgsRetVector3(descriptor: GodotCallDescriptor, receiver: GodotHandle): GodotVector3 {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_VECTOR3)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeNoArgsRetVector3(descriptor, resolve(selected, descriptor), receiver)
  }

  fun invokeVector3Arg(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: GodotVector3,
  ) {
    requireShape(descriptor, GodotCallShape.VECTOR3_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeVector3Arg(descriptor, resolve(selected, descriptor), receiver, value)
  }

  fun invokeLongDoubleArg(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    longValue: Long,
    doubleValue: Double,
  ) {
    requireShape(descriptor, GodotCallShape.LONG_DOUBLE_ARG)
    require(doubleValue.isFinite()) { "Godot Double argument must be finite" }
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeLongDoubleArg(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      longValue,
      doubleValue,
    )
  }

  fun invokeNoArgsRetStringSingleton(descriptor: GodotCallDescriptor): String {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_STRING_SINGLETON)
    val selected = requireBackend()
    return selected.invokeNoArgsRetStringSingleton(descriptor, resolve(selected, descriptor))
  }

  fun invokeStringNameArgSingleton(descriptor: GodotCallDescriptor, value: String) {
    requireShape(descriptor, GodotCallShape.STRINGNAME_ARG_SINGLETON)
    val selected = requireBackend()
    selected.invokeStringNameArgSingleton(descriptor, resolve(selected, descriptor), value)
  }

  fun invokeStringNameDoubleArg(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: String,
    doubleValue: Double,
  ) {
    requireShape(descriptor, GodotCallShape.STRINGNAME_DOUBLE_ARG)
    require(doubleValue.isFinite())
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeStringNameDoubleArg(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      value,
      doubleValue,
    )
  }

  fun invokeStringNameStringNameRetDoubleSingleton(
    descriptor: GodotCallDescriptor,
    first: String,
    second: String,
  ): Double {
    requireShape(descriptor, GodotCallShape.STRINGNAME_STRINGNAME_RET_DOUBLE_SINGLETON)
    val selected = requireBackend()
    return selected.invokeStringNameStringNameRetDoubleSingleton(
      descriptor,
      resolve(selected, descriptor),
      first,
      second,
    )
  }

  fun invokeVector3Vector3Arg(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    first: GodotVector3,
    second: GodotVector3,
  ) {
    requireShape(descriptor, GodotCallShape.VECTOR3_VECTOR3_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeVector3Vector3Arg(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      first,
      second,
    )
  }

  fun invokeLongArgSingleton(descriptor: GodotCallDescriptor, value: Long) {
    requireShape(descriptor, GodotCallShape.LONG_ARG_SINGLETON)
    val selected = requireBackend()
    selected.invokeLongArgSingleton(descriptor, resolve(selected, descriptor), value)
  }

  fun invokeNoArgsRetLongSingleton(descriptor: GodotCallDescriptor): Long {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_LONG_SINGLETON)
    val selected = requireBackend()
    return selected.invokeNoArgsRetLongSingleton(descriptor, resolve(selected, descriptor))
  }

  fun invokeNoArgsRetLongListSingleton(descriptor: GodotCallDescriptor): List<Long> {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_LONG_LIST_SINGLETON)
    val selected = requireBackend()
    return selected.invokeNoArgsRetLongListSingleton(descriptor, resolve(selected, descriptor))
  }

  fun invokeLongRetBoolSingleton(descriptor: GodotCallDescriptor, value: Long): Boolean {
    requireShape(descriptor, GodotCallShape.LONG_RET_BOOL_SINGLETON)
    val selected = requireBackend()
    return selected.invokeLongRetBoolSingleton(descriptor, resolve(selected, descriptor), value)
  }

  fun invokeNoArgsRetVector2Singleton(descriptor: GodotCallDescriptor): GodotVector2 {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_VECTOR2_SINGLETON)
    val selected = requireBackend()
    return selected.invokeNoArgsRetVector2Singleton(descriptor, resolve(selected, descriptor))
  }

  fun invokeStringNameRetHandleList(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: String,
  ): List<GodotHandle> {
    requireShape(descriptor, GodotCallShape.STRINGNAME_RET_HANDLE_LIST)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeStringNameRetHandleList(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      value,
    )
  }

  fun invokeDoubleRetHandle(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: Double,
  ): GodotHandle? {
    requireShape(descriptor, GodotCallShape.DOUBLE_RET_HANDLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeDoubleRetHandle(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      value,
    )
  }

  fun invokeStringNameObjectRetInt(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    name: String,
    value: GodotHandle,
  ): Int {
    requireShape(descriptor, GodotCallShape.STRINGNAME_OBJECT_RET_INT)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeStringNameObjectRetInt(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      name,
      value,
    )
  }

  fun invokeVector3RetHandle(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: GodotVector3,
  ): GodotHandle? {
    requireShape(descriptor, GodotCallShape.VECTOR3_RET_HANDLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeVector3RetHandle(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      value,
    )
  }

  fun invokeNoArgsRetHandleList(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
  ): List<GodotHandle> {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_HANDLE_LIST)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeNoArgsRetHandleList(descriptor, resolve(selected, descriptor), receiver)
  }

  fun invokeLongRetVector3(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: Long,
  ): GodotVector3 {
    requireShape(descriptor, GodotCallShape.LONG_RET_VECTOR3)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeLongRetVector3(descriptor, resolve(selected, descriptor), receiver, value)
  }

  fun invokeStringNameRetDoubleSingleton(descriptor: GodotCallDescriptor, value: String): Double {
    requireShape(descriptor, GodotCallShape.STRINGNAME_RET_DOUBLE_SINGLETON)
    val selected = requireBackend()
    return selected.invokeStringNameRetDoubleSingleton(
      descriptor,
      resolve(selected, descriptor),
      value,
    )
  }

  fun invokeStringNameVector3Vector3Arg(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    name: String,
    first: GodotVector3,
    second: GodotVector3,
  ) {
    requireShape(descriptor, GodotCallShape.STRINGNAME_VECTOR3_VECTOR3_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeStringNameVector3Vector3Arg(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      name,
      first,
      second,
    )
  }

  fun invokeStringNameStringRetInt(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    name: String,
    value: String,
  ): Int {
    requireShape(descriptor, GodotCallShape.STRINGNAME_STRING_RET_INT)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeStringNameStringRetInt(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      name,
      value,
    )
  }

  fun invokeVector3iLongLongArg(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: GodotVector3i,
    first: Long,
    second: Long,
  ) {
    requireShape(descriptor, GodotCallShape.VECTOR3I_LONG_LONG_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeVector3iLongLongArg(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      value,
      first,
      second,
    )
  }

  fun invokeVector3iRetLong(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: GodotVector3i,
  ): Long {
    requireShape(descriptor, GodotCallShape.VECTOR3I_RET_LONG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeVector3iRetLong(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      value,
    )
  }

  fun invokeBasisRetLong(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: GodotBasis,
  ): Long {
    requireShape(descriptor, GodotCallShape.BASIS_RET_LONG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeBasisRetLong(descriptor, resolve(selected, descriptor), receiver, value)
  }

  fun invokeNoArgsRetVector3iList(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
  ): List<GodotVector3i> {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_VECTOR3I_LIST)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeNoArgsRetVector3iList(descriptor, resolve(selected, descriptor), receiver)
  }

  fun invokeLongObjectArg(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    longValue: Long,
    objectValue: GodotHandle,
  ) {
    requireShape(descriptor, GodotCallShape.LONG_OBJECT_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.requireLive(objectValue)
    selected.invokeLongObjectArg(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      longValue,
      objectValue,
    )
  }

  fun invokeLongTransform3dArg(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    longValue: Long,
    value: GodotTransform3D,
  ) {
    requireShape(descriptor, GodotCallShape.LONG_TRANSFORM3D_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeLongTransform3dArg(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      longValue,
      value,
    )
  }

  fun invokeLongRetString(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: Long,
  ): String {
    requireShape(descriptor, GodotCallShape.LONG_RET_STRING)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeLongRetString(descriptor, resolve(selected, descriptor), receiver, value)
  }

  fun invokeLongRetLong(descriptor: GodotCallDescriptor, receiver: GodotHandle, value: Long): Long {
    requireShape(descriptor, GodotCallShape.LONG_RET_LONG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeLongRetLong(descriptor, resolve(selected, descriptor), receiver, value)
  }

  fun invokeLongLongRetString(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    first: Long,
    second: Long,
  ): String {
    requireShape(descriptor, GodotCallShape.LONG_LONG_RET_STRING)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeLongLongRetString(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      first,
      second,
    )
  }

  fun invokeLongLongRetHandle(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    first: Long,
    second: Long,
  ): GodotHandle? {
    requireShape(descriptor, GodotCallShape.LONG_LONG_RET_HANDLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeLongLongRetHandle(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      first,
      second,
    )
  }

  fun invokeVector2RetVector3(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: GodotVector2,
  ): GodotVector3 {
    requireShape(descriptor, GodotCallShape.VECTOR2_RET_VECTOR3)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeVector2RetVector3(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      value,
    )
  }

  fun invokeObjectStringRetLongSingleton(
    descriptor: GodotCallDescriptor,
    resource: GodotHandle,
    path: String,
    flags: Long,
  ): Long {
    requireShape(descriptor, GodotCallShape.OBJECT_STRING_RET_LONG_SINGLETON)
    val selected = requireBackend()
    selected.requireLive(resource)
    return selected.invokeObjectStringRetLongSingleton(
      descriptor,
      resolve(selected, descriptor),
      resource,
      path,
      flags,
    )
  }

  fun invokeStringStringBoolBoolRetHandleList(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    pattern: String,
    type: String,
    recursive: Boolean,
    owned: Boolean,
  ): List<GodotHandle> {
    requireShape(descriptor, GodotCallShape.STRING_STRING_BOOL_BOOL_RET_HANDLE_LIST)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeStringStringBoolBoolRetHandleList(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      pattern,
      type,
      recursive,
      owned,
    )
  }

  fun invokeStringNameLongArg(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    name: String,
    value: Long,
  ) {
    requireShape(descriptor, GodotCallShape.STRINGNAME_LONG_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeStringNameLongArg(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      name,
      value,
    )
  }

  fun invokeStringNameVector2Arg(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    name: String,
    value: GodotVector2,
  ) {
    requireShape(descriptor, GodotCallShape.STRINGNAME_VECTOR2_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeStringNameVector2Arg(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      name,
      value,
    )
  }

  fun invokeStringNameObjectArg(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    name: String,
    value: GodotHandle,
  ) {
    requireShape(descriptor, GodotCallShape.STRINGNAME_OBJECT_ARG)
    val selected = requireBackend()
    selected.requireLive(receiver)
    selected.invokeStringNameObjectArg(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      name,
      value,
    )
  }

  fun invokeStringNameObjectArgSingleton(
    descriptor: GodotCallDescriptor,
    name: String,
    value: GodotHandle,
  ) {
    requireShape(descriptor, GodotCallShape.STRINGNAME_OBJECT_ARG_SINGLETON)
    val selected = requireBackend()
    selected.requireLive(value)
    selected.invokeStringNameObjectArgSingleton(
      descriptor,
      resolve(selected, descriptor),
      name,
      value,
    )
  }

  fun invokeStringNameRetVector2(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    name: String,
  ): GodotVector2 {
    requireShape(descriptor, GodotCallShape.STRINGNAME_RET_VECTOR2)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeStringNameRetVector2(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      name,
    )
  }

  fun invokeNoArgsRetString(descriptor: GodotCallDescriptor, receiver: GodotHandle): String {
    requireShape(descriptor, GodotCallShape.NOARGS_RET_STRING)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeNoArgsRetString(descriptor, resolve(selected, descriptor), receiver)
  }

  fun invokeStringNameRetString(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    name: String,
  ): String {
    requireShape(descriptor, GodotCallShape.STRINGNAME_RET_STRING)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeStringNameRetString(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      name,
    )
  }

  fun invokeDoubleRetDouble(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: Double,
  ): Double {
    requireShape(descriptor, GodotCallShape.DOUBLE_RET_DOUBLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeDoubleRetDouble(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      value,
    )
  }

  fun invokeVector3Vector3LongObjectRetString(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    from: GodotVector3,
    to: GodotVector3,
    collisionMask: Long,
    exclude: GodotHandle?,
  ): String {
    requireShape(descriptor, GodotCallShape.VECTOR3_VECTOR3_LONG_OBJECT_RET_STRING)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeVector3Vector3LongObjectRetString(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      from,
      to,
      collisionMask,
      exclude,
    )
  }

  fun invokeCallableDoubleRangeRetHandle(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    target: GodotHandle,
    method: String,
    fromValue: Double,
    toValue: Double,
    duration: Double,
  ): GodotHandle? {
    requireShape(descriptor, GodotCallShape.CALLABLE_DOUBLE_RANGE_RET_HANDLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeCallableDoubleRangeRetHandle(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      target,
      method,
      fromValue,
      toValue,
      duration,
    )
  }

  fun invokeObjectRetHandle(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: GodotHandle,
  ): GodotHandle? {
    requireShape(descriptor, GodotCallShape.OBJECT_RET_HANDLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeObjectRetHandle(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      value,
    )
  }

  fun invokeObjectNodePathVector3DoubleRetHandle(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    target: GodotHandle,
    property: String,
    finalValue: GodotVector3,
    duration: Double,
  ): GodotHandle? {
    requireShape(descriptor, GodotCallShape.OBJECT_NODEPATH_VECTOR3_DOUBLE_RET_HANDLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeObjectNodePathVector3DoubleRetHandle(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      target,
      property,
      finalValue,
      duration,
    )
  }

  fun invokeObjectNodePathDoubleDoubleRetHandle(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    target: GodotHandle,
    property: String,
    finalValue: Double,
    duration: Double,
  ): GodotHandle? {
    requireShape(descriptor, GodotCallShape.OBJECT_NODEPATH_DOUBLE_DOUBLE_RET_HANDLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeObjectNodePathDoubleDoubleRetHandle(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      target,
      property,
      finalValue,
      duration,
    )
  }

  fun invokeColorRetHandle(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    value: GodotColor,
  ): GodotHandle? {
    requireShape(descriptor, GodotCallShape.COLOR_RET_HANDLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeColorRetHandle(descriptor, resolve(selected, descriptor), receiver, value)
  }

  fun invokeCallableRetHandle(
    descriptor: GodotCallDescriptor,
    receiver: GodotHandle,
    target: GodotHandle,
    method: String,
  ): GodotHandle? {
    requireShape(descriptor, GodotCallShape.CALLABLE_RET_HANDLE)
    val selected = requireBackend()
    selected.requireLive(receiver)
    return selected.invokeCallableRetHandle(
      descriptor,
      resolve(selected, descriptor),
      receiver,
      target,
      method,
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
    backend ?: error("Kanama Web backend was not installed by the Wasm entry point")

  private fun requireShape(descriptor: GodotCallDescriptor, expected: GodotCallShape) {
    require(descriptor.shape == expected) {
      "Godot call ${descriptor.className}.${descriptor.methodName} has shape " +
        "${descriptor.shape}, expected $expected"
    }
  }
}
