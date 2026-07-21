package net.multigesture.kanama.binding.runtime

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_DOUBLE
import java.lang.foreign.ValueLayout.JAVA_FLOAT
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodHandle
import java.util.concurrent.ConcurrentHashMap
import net.multigesture.kanama.api.GodotCallable
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.RefCounted
import net.multigesture.kanama.api.Resource
import net.multigesture.kanama.ffi.GodotFFI
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.GodotReal
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Projection
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i
import net.multigesture.kanama.types.Vector4
import net.multigesture.kanama.types.Vector4i

/**
 * Helpers for Variant builtin-type constructors and methods.
 *
 * Used by script-language callbacks that must return builtins such as PackedStringArray or
 * Dictionary via ptrcall.
 */
object BuiltinTypes {
  /** `sizeof(Variant)` on 64-bit (`float_64`), from `extension_api.json`. */
  const val VARIANT_SIZE: Long = 24L

  /** `sizeof(Packed*Array)` on 64-bit (`float_64`), from `extension_api.json`. */
  const val PACKED_ARRAY_SIZE: Long = 16L
  const val PACKED_ARRAY_ALIGN: Long = 8L

  /** `sizeof(Callable)` on 64-bit (`float_64`), from `extension_api.json`. */
  const val CALLABLE_SIZE: Long = 16L
  const val CALLABLE_ALIGN: Long = 8L
  // Signal is ObjectID + StringName (16 bytes in every float/precision build config).
  const val SIGNAL_SIZE: Long = 16L
  const val SIGNAL_ALIGN: Long = 8L

  fun allocatePackedArray(arena: Arena): MemorySegment =
    arena.allocate(PACKED_ARRAY_SIZE, PACKED_ARRAY_ALIGN)

  fun allocateCallable(arena: Arena): MemorySegment = arena.allocate(CALLABLE_SIZE, CALLABLE_ALIGN)

  fun allocateSignal(arena: Arena): MemorySegment = arena.allocate(SIGNAL_SIZE, SIGNAL_ALIGN)

  fun requireInt32(value: Long): Int {
    require(value in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong()) {
      "Value $value is outside Godot int32 range ${Int.MIN_VALUE}..${Int.MAX_VALUE}"
    }
    return value.toInt()
  }

  fun requireUInt8(value: Long): Int {
    require(value in 0L..0xffL) { "Value $value is outside Godot uint8 range 0..255" }
    return value.toInt()
  }

  fun requireUInt16(value: Long): Int {
    require(value in 0L..0xffffL) { "Value $value is outside Godot uint16 range 0..65535" }
    return value.toInt()
  }

  fun requireUInt32(value: Long): Int {
    require(value in 0L..0xffff_ffffL) {
      "Value $value is outside Godot uint32 range 0..4294967295"
    }
    return value.toInt()
  }

  private val getPtrConstructor by lazy {
    GodotFFI.lookup(
      "variant_get_ptr_constructor",
      FunctionDescriptor.of(ADDRESS, JAVA_INT, JAVA_INT),
    )
  }

  private val getPtrBuiltinMethod by lazy {
    GodotFFI.lookup(
      "variant_get_ptr_builtin_method",
      FunctionDescriptor.of(ADDRESS, JAVA_INT, ADDRESS, JAVA_LONG),
    )
  }

  private val getPtrKeyedSetter by lazy {
    GodotFFI.lookup("variant_get_ptr_keyed_setter", FunctionDescriptor.of(ADDRESS, JAVA_INT))
  }

  private val getPtrDestructor by lazy {
    GodotFFI.lookup("variant_get_ptr_destructor", FunctionDescriptor.of(ADDRESS, JAVA_INT))
  }

  // Direct data pointers for bulk packed-array transfers. Per-element
  // builtin calls cost ~microseconds each on the desktop JVM but ~100x
  // more on the Android ART/PanamaPort path — a 1 MB PackedByteArray was
  // measured at ~85 s there (snowplow heightmap, Pixel 7). Payloads move
  // as single MemorySegment copies instead.
  private val packedByteArrayOperatorIndexConst by lazy {
    GodotFFI.lookup(
      "packed_byte_array_operator_index_const",
      FunctionDescriptor.of(ADDRESS, ADDRESS, JAVA_LONG),
    )
  }

  private val packedByteArrayOperatorIndex by lazy {
    GodotFFI.lookup(
      "packed_byte_array_operator_index",
      FunctionDescriptor.of(ADDRESS, ADDRESS, JAVA_LONG),
    )
  }

  private val packedFloat32ArrayOperatorIndexConst by lazy {
    GodotFFI.lookup(
      "packed_float32_array_operator_index_const",
      FunctionDescriptor.of(ADDRESS, ADDRESS, JAVA_LONG),
    )
  }

  private val packedFloat32ArrayOperatorIndex by lazy {
    GodotFFI.lookup(
      "packed_float32_array_operator_index",
      FunctionDescriptor.of(ADDRESS, ADDRESS, JAVA_LONG),
    )
  }

  private val variantDestroy by lazy {
    GodotFFI.lookup("variant_destroy", FunctionDescriptor.ofVoid(ADDRESS))
  }

  private val variantNewNil by lazy {
    GodotFFI.lookup("variant_new_nil", FunctionDescriptor.ofVoid(ADDRESS))
  }

  private val variantNewCopy by lazy {
    GodotFFI.lookup("variant_new_copy", FunctionDescriptor.ofVoid(ADDRESS, ADDRESS))
  }

  private val constructors = ConcurrentHashMap<Pair<Int, Int>, MethodHandle>()
  private val methods = ConcurrentHashMap<Triple<Int, String, Long>, MethodHandle>()
  private val keyedSetters = ConcurrentHashMap<Int, MethodHandle>()
  private val destructors = ConcurrentHashMap<Int, MethodHandle>()

  private fun ptrConstructor(type: VariantType, constructorIndex: Int): MethodHandle =
    constructors.getOrPut(type.id to constructorIndex) {
      val fn = getPtrConstructor.invoke(type.id, constructorIndex) as MemorySegment
      check(fn.address() != 0L) {
        "variant_get_ptr_constructor(${type.name}, $constructorIndex) returned NULL"
      }
      GodotFFI.linker.downcallHandle(fn, FunctionDescriptor.ofVoid(ADDRESS, ADDRESS))
    }

  private fun ptrBuiltinMethod(type: VariantType, method: String, hash: Long): MethodHandle =
    methods.getOrPut(Triple(type.id, method, hash)) {
      val fn =
        getPtrBuiltinMethod.invoke(type.id, GodotStrings.makeStringName(method), hash)
          as MemorySegment
      check(fn.address() != 0L) {
        "variant_get_ptr_builtin_method(${type.name}, $method, $hash) returned NULL"
      }
      GodotFFI.linker.downcallHandle(
        fn,
        FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, JAVA_INT),
      )
    }

  private fun ptrKeyedSetter(type: VariantType): MethodHandle =
    keyedSetters.getOrPut(type.id) {
      val fn = getPtrKeyedSetter.invoke(type.id) as MemorySegment
      check(fn.address() != 0L) { "variant_get_ptr_keyed_setter(${type.name}) returned NULL" }
      GodotFFI.linker.downcallHandle(fn, FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS))
    }

  private fun ptrDestructor(type: VariantType): MethodHandle =
    destructors.getOrPut(type.id) {
      val fn = getPtrDestructor.invoke(type.id) as MemorySegment
      check(fn.address() != 0L) { "variant_get_ptr_destructor(${type.name}) returned NULL" }
      GodotFFI.linker.downcallHandle(fn, FunctionDescriptor.ofVoid(ADDRESS))
    }

  /** Destroy an initialized builtin-typed value in-place. */
  fun destroyTyped(type: VariantType, valuePtr: MemorySegment) {
    ptrDestructor(type).invoke(valuePtr)
  }

  /** Initialize [dest] as a Variant containing nil. */
  fun initNilVariant(dest: MemorySegment) {
    variantNewNil.invoke(dest)
  }

  /** Destroy an initialized Variant in-place. */
  fun destroyVariant(variantPtr: MemorySegment) {
    variantDestroy.invoke(variantPtr)
  }

  /**
   * Heap-owned Variant copy for values Godot assigns to editor placeholders. The copied Variant
   * retains Godot-managed resources and typed arrays using the engine's normal Variant lifetime
   * rules.
   */
  class StoredVariant
  internal constructor(private val arena: Arena, private val variant: MemorySegment) :
    AutoCloseable {
    fun writeCopyTo(dest: MemorySegment) {
      variantNewCopy.invoke(dest, variant)
    }

    override fun close() {
      variantDestroy.invoke(variant)
      arena.close()
    }
  }

  fun copyVariant(variant: MemorySegment): StoredVariant {
    val arena = Arena.ofShared()
    val copy = arena.allocate(VARIANT_SIZE, 8L)
    variantNewCopy.invoke(copy, variant)
    return StoredVariant(arena, copy)
  }

  /**
   * Initialize [variantOut] as a Variant from the scalar Kotlin values Kanama currently exposes in
   * the public generic call path.
   */
  fun initVariantFromAny(variantOut: MemorySegment, value: Any?, arena: Arena) {
    when (value) {
      null -> initNilVariant(variantOut)
      is String -> variantFromStringInto(value, variantOut, arena)
      is Boolean -> variantFromBoolInto(value, variantOut, arena)
      is Int -> variantFromLongInto(value.toLong(), variantOut, arena)
      is Long -> variantFromLongInto(value, variantOut, arena)
      is Float -> variantFromDoubleInto(value.toDouble(), variantOut, arena)
      is Double -> variantFromDoubleInto(value, variantOut, arena)
      is Color -> variantFromColorInto(value, variantOut, arena)
      is Quaternion -> variantFromQuaternionInto(value, variantOut, arena)
      is Vector2 -> variantFromVector2Into(value, variantOut, arena)
      is Vector3 -> variantFromVector3Into(value, variantOut, arena)
      is Vector4 -> variantFromVector4Into(value, variantOut, arena)
      is Rect2 -> variantFromRect2Into(value, variantOut, arena)
      is AABB -> variantFromAABBInto(value, variantOut, arena)
      is Plane -> variantFromPlaneInto(value, variantOut, arena)
      is Basis -> variantFromBasisInto(value, variantOut, arena)
      is Transform3D -> variantFromTransform3DInto(value, variantOut, arena)
      is Transform2D -> variantFromTransform2DInto(value, variantOut, arena)
      is Projection -> variantFromProjectionInto(value, variantOut, arena)
      is Vector2i -> variantFromVector2iInto(value, variantOut, arena)
      is Vector3i -> variantFromVector3iInto(value, variantOut, arena)
      is Vector4i -> variantFromVector4iInto(value, variantOut, arena)
      is Rect2i -> variantFromRect2iInto(value, variantOut, arena)
      is NodePath -> variantFromNodePathInto(value, variantOut, arena)
      is RID -> variantFromRidInto(value, variantOut, arena)
      is ByteArray -> variantFromPackedByteArrayInto(value, variantOut, arena)
      is List<*> -> variantFromArrayInto(value, variantOut, arena)
      is Map<*, *> -> variantFromDictionaryInto(value, variantOut, arena)
      is GodotObject -> variantFromObjectInto(value.handle, variantOut, arena)
      // Variant(Object*) already refs RefCounted values; adding an extra
      // retain here leaks whenever the temporary Variant is destroyed.
      is Resource -> variantFromObjectInto(value.requireOpenHandle(), variantOut, arena)
      else -> error("Unsupported Variant value type: ${value::class.qualifiedName}")
    }
  }

  /** Decode the scalar Variant return types supported by the public call path. */
  fun readVariantScalar(variant: MemorySegment, arena: Arena): Any? =
    variantToScalar(variant, arena)

  /**
   * Owned scalar decode for calls that mint a fresh object whose sole reference lives in the return
   * Variant (ClassDB.instantiate). The borrowed [readVariantScalar] decode would hand back a handle
   * that dies when the caller destroys the Variant. Retain RefCounted results before that destroy
   * and return the owning [RefCounted] wrapper (`close()` releases — the task-31 return-ownership
   * convention). Non-RefCounted objects stay borrowed [GodotObject]: the caller owns those through
   * the scene tree / `free()`.
   */
  fun readVariantScalarOwned(variant: MemorySegment, arena: Arena): Any? {
    val value = variantToScalar(variant, arena)
    if (value is GodotObject && value.isClass("RefCounted")) {
      ObjectCalls.ptrcallNoArgsRetBool(referenceBind, value.handle)
      return RefCounted(value.handle)
    }
    return value
  }

  fun readVariantStringList(variant: MemorySegment, arena: Arena): List<String> =
    when (val value = variantToScalar(variant, arena)) {
      is List<*> -> value.mapNotNull { it as? String }
      is String -> listOf(value)
      else -> emptyList()
    }

  /**
   * Int-element Array read for enum-list script properties (task 38). Non-numeric elements default
   * to 0 instead of dropping: the inspector's Add Element/resize appends `null` slots (the script
   * getter hands it an untyped Array, which null-fills on resize), and dropping them would make
   * those edits silent no-ops.
   */
  fun readVariantLongList(variant: MemorySegment, arena: Arena): List<Long> =
    when (val value = variantToScalar(variant, arena)) {
      is List<*> -> value.map { (it as? Number)?.toLong() ?: 0L }
      is Number -> listOf(value.toLong())
      else -> emptyList()
    }

  fun readVariantNodePath(variant: MemorySegment, arena: Arena): NodePath =
    when (val value = variantToScalar(variant, arena)) {
      is NodePath -> value
      is String -> NodePath(value)
      else -> NodePath.EMPTY
    }

  fun readTypedNodePath(nodePath: MemorySegment, arena: Arena): NodePath =
    NodePath(readNodePathString(nodePath, arena))

  fun <T> readVariantObject(
    variant: MemorySegment,
    arena: Arena,
    wrapper: (MemorySegment) -> T?,
  ): T? {
    val scratch = arena.allocate(ADDRESS)
    VariantConverters.variantToType(VariantType.OBJECT).invoke(scratch, variant)
    val handle = scratch.get(ADDRESS, 0)
    return if (handle.address() == 0L) null else wrapper(handle)
  }

  fun <T> readVariantObjectRetained(
    variant: MemorySegment,
    arena: Arena,
    wrapper: (MemorySegment) -> T?,
  ): T? =
    readVariantObject(variant, arena, wrapper).also { value ->
      if (value is Resource) {
        value.retainForKotlinWrapper()
      }
    }

  fun <T> readVariantObjectRetainedHandle(
    variant: MemorySegment,
    arena: Arena,
    wrapper: (MemorySegment) -> T?,
  ): T? {
    val scratch = arena.allocate(ADDRESS)
    VariantConverters.variantToType(VariantType.OBJECT).invoke(scratch, variant)
    val handle = scratch.get(ADDRESS, 0)
    if (handle.address() == 0L) return null
    ObjectCalls.ptrcallNoArgsRetBool(referenceBind, handle)
    return wrapper(handle)
  }

  fun <T : Any> readVariantObjectArray(
    variant: MemorySegment,
    arena: Arena,
    wrapper: (MemorySegment) -> T?,
  ): List<T> {
    val scratch = arena.allocate(8L, 8L)
    VariantConverters.variantToType(VariantType.ARRAY).invoke(scratch, variant)
    try {
      return readArrayObjects(scratch, wrapper)
    } finally {
      destroyTyped(VariantType.ARRAY, scratch)
    }
  }

  fun <T : Any> readVariantObjectArrayRetained(
    variant: MemorySegment,
    arena: Arena,
    wrapper: (MemorySegment) -> T?,
  ): List<T> =
    readVariantObjectArray(variant, arena, wrapper).also { values ->
      values.forEach { value ->
        if (value is Resource) {
          value.retainForKotlinWrapper()
        }
      }
    }

  fun <T : Any> readVariantObjectArrayRetainedHandles(
    variant: MemorySegment,
    arena: Arena,
    wrapper: (MemorySegment) -> T?,
  ): List<T> {
    val scratch = arena.allocate(8L, 8L)
    VariantConverters.variantToType(VariantType.ARRAY).invoke(scratch, variant)
    try {
      return readArrayObjects(scratch) { handle ->
        ObjectCalls.ptrcallNoArgsRetBool(referenceBind, handle)
        wrapper(handle)
      }
    } finally {
      destroyTyped(VariantType.ARRAY, scratch)
    }
  }

  fun releaseRefCounted(handle: MemorySegment) {
    if (handle.address() == 0L) return
    val shouldDestroy = ObjectCalls.ptrcallNoArgsRetBool(unreferenceBind, handle)
    if (System.getenv("KANAMA_TRACE_SCRIPT_PROPERTY_CLEANUP") == "1") {
      System.err.println(
        "[kanama:kt] script property cleanup RefCounted handle=0x${handle.address().toString(16)} destroy=$shouldDestroy"
      )
    }
    if (shouldDestroy) {
      ObjectCalls.destroyObject(handle)
    }
  }

  /**
   * Construct [type] into [dest] using [constructorIndex]. [args] are typed pointers for that
   * constructor, matching Godot's builtin ABI.
   */
  fun construct(
    type: VariantType,
    dest: MemorySegment,
    constructorIndex: Int = 0,
    args: List<MemorySegment> = emptyList(),
  ) {
    Arena.ofConfined().use { arena ->
      val argArray =
        if (args.isEmpty()) {
          MemorySegment.NULL
        } else {
          val arr = arena.allocate(ADDRESS, args.size.toLong())
          args.forEachIndexed { i, arg -> arr.setAtIndex(ADDRESS, i.toLong(), arg) }
          arr
        }
      ptrConstructor(type, constructorIndex).invoke(dest, argArray)
    }
  }

  /**
   * Calls a builtin method on [base] of [type].
   *
   * [args] are typed pointers expected by that builtin method signature. [rReturn] may be NULL for
   * void methods.
   */
  fun call(
    type: VariantType,
    method: String,
    hash: Long,
    base: MemorySegment,
    args: List<MemorySegment>,
    rReturn: MemorySegment = MemorySegment.NULL,
  ) {
    Arena.ofConfined().use { arena ->
      val argArray =
        if (args.isEmpty()) {
          MemorySegment.NULL
        } else {
          val arr = arena.allocate(ADDRESS, args.size.toLong())
          args.forEachIndexed { i, arg -> arr.setAtIndex(ADDRESS, i.toLong(), arg) }
          arr
        }
      ptrBuiltinMethod(type, method, hash).invoke(base, argArray, rReturn, args.size)
    }
  }

  /** Initialize [dest] as a PackedStringArray and append [values]. */
  fun initPackedStringArray(dest: MemorySegment, values: List<String>) {
    construct(VariantType.PACKED_STRING_ARRAY, dest, constructorIndex = 0)
    if (values.isEmpty()) return
    // PackedStringArray.push_back(String) -> bool
    val pushBackHash = 816187996L
    Arena.ofConfined().use { arena ->
      val boolRet = arena.allocate(JAVA_BYTE)
      for (v in values) {
        val str = arena.allocate(8L, 8L)
        GodotStrings.initString(str, v)
        try {
          call(
            type = VariantType.PACKED_STRING_ARRAY,
            method = "push_back",
            hash = pushBackHash,
            base = dest,
            args = listOf(str),
            rReturn = boolRet,
          )
        } finally {
          GodotStrings.destroyString(str)
        }
      }
    }
  }

  /** Initialize [dest] as a PackedByteArray and append [values]. */
  fun initPackedByteArray(dest: MemorySegment, values: ByteArray) {
    construct(VariantType.PACKED_BYTE_ARRAY, dest, constructorIndex = 0)
    if (values.isEmpty()) return
    resizePackedArray(VariantType.PACKED_BYTE_ARRAY, dest, values.size)
    val data =
      (packedByteArrayOperatorIndex.invoke(dest, 0L) as MemorySegment).reinterpret(
        values.size.toLong()
      )
    MemorySegment.copy(values, 0, data, JAVA_BYTE, 0L, values.size)
  }

  /** Initialize [dest] as a PackedInt32Array and append [values]. */
  fun initPackedInt32Array(dest: MemorySegment, values: List<Int>) {
    construct(VariantType.PACKED_INT32_ARRAY, dest, constructorIndex = 0)
    if (values.isEmpty()) return
    // PackedInt32Array.push_back(int) -> bool
    val pushBackHash = 694024632L
    Arena.ofConfined().use { arena ->
      val intArg = arena.allocate(JAVA_LONG)
      val boolRet = arena.allocate(JAVA_BYTE)
      for (value in values) {
        intArg.set(JAVA_LONG, 0, value.toLong())
        call(
          type = VariantType.PACKED_INT32_ARRAY,
          method = "push_back",
          hash = pushBackHash,
          base = dest,
          args = listOf(intArg),
          rReturn = boolRet,
        )
      }
    }
  }

  /** Initialize [dest] as a PackedInt64Array and append [values]. */
  fun initPackedInt64Array(dest: MemorySegment, values: List<Long>) {
    construct(VariantType.PACKED_INT64_ARRAY, dest, constructorIndex = 0)
    if (values.isEmpty()) return
    // PackedInt64Array.push_back(int) -> bool
    val pushBackHash = 694024632L
    Arena.ofConfined().use { arena ->
      val intArg = arena.allocate(JAVA_LONG)
      val boolRet = arena.allocate(JAVA_BYTE)
      for (value in values) {
        intArg.set(JAVA_LONG, 0, value)
        call(
          type = VariantType.PACKED_INT64_ARRAY,
          method = "push_back",
          hash = pushBackHash,
          base = dest,
          args = listOf(intArg),
          rReturn = boolRet,
        )
      }
    }
  }

  /** Initialize [dest] as a PackedFloat32Array and append [values]. */
  fun initPackedFloat32Array(dest: MemorySegment, values: List<Float>) {
    construct(VariantType.PACKED_FLOAT32_ARRAY, dest, constructorIndex = 0)
    if (values.isEmpty()) return
    resizePackedArray(VariantType.PACKED_FLOAT32_ARRAY, dest, values.size)
    val data =
      (packedFloat32ArrayOperatorIndex.invoke(dest, 0L) as MemorySegment).reinterpret(
        values.size * 4L
      )
    MemorySegment.copy(values.toFloatArray(), 0, data, JAVA_FLOAT, 0L, values.size)
  }

  /** Initialize [dest] as a PackedFloat64Array and append [values]. */
  fun initPackedFloat64Array(dest: MemorySegment, values: List<Double>) {
    construct(VariantType.PACKED_FLOAT64_ARRAY, dest, constructorIndex = 0)
    if (values.isEmpty()) return
    // PackedFloat64Array.push_back(float) -> bool
    val pushBackHash = 4094791666L
    Arena.ofConfined().use { arena ->
      val floatArg = arena.allocate(JAVA_DOUBLE)
      val boolRet = arena.allocate(JAVA_BYTE)
      for (value in values) {
        floatArg.set(JAVA_DOUBLE, 0, value)
        call(
          type = VariantType.PACKED_FLOAT64_ARRAY,
          method = "push_back",
          hash = pushBackHash,
          base = dest,
          args = listOf(floatArg),
          rReturn = boolRet,
        )
      }
    }
  }

  /** Initialize [dest] as a PackedVector2Array and append [values]. */
  fun initPackedVector2Array(dest: MemorySegment, values: List<Vector2>) {
    construct(VariantType.PACKED_VECTOR2_ARRAY, dest, constructorIndex = 0)
    if (values.isEmpty()) return
    // PackedVector2Array.push_back(Vector2) -> bool
    val pushBackHash = 4188891560L
    Arena.ofConfined().use { arena ->
      val vectorArg = arena.allocate(GodotReal.SIZE_BYTES * 2, GodotReal.ALIGN_BYTES)
      val boolRet = arena.allocate(JAVA_BYTE)
      for (value in values) {
        GodotReal.writeIndex(vectorArg, 0, value.x)
        GodotReal.writeIndex(vectorArg, 1, value.y)
        call(
          type = VariantType.PACKED_VECTOR2_ARRAY,
          method = "push_back",
          hash = pushBackHash,
          base = dest,
          args = listOf(vectorArg),
          rReturn = boolRet,
        )
      }
    }
  }

  /** Initialize [dest] as Array[PackedVector2Array]. */
  fun initArrayOfPackedVector2Arrays(dest: MemorySegment, values: List<List<Vector2>>) {
    construct(VariantType.ARRAY, dest, constructorIndex = 0)
    if (values.isEmpty()) return

    val pushBackHash = 3316032543L
    Arena.ofConfined().use { arena ->
      values.forEach { value ->
        val packed = allocatePackedArray(arena)
        val variant = arena.allocate(VARIANT_SIZE, 8L)
        var variantInitialized = false
        try {
          initPackedVector2Array(packed, value)
          VariantConverters.variantFromType(VariantType.PACKED_VECTOR2_ARRAY)
            .invoke(variant, packed)
          variantInitialized = true
          call(
            type = VariantType.ARRAY,
            method = "push_back",
            hash = pushBackHash,
            base = dest,
            args = listOf(variant),
          )
        } finally {
          if (variantInitialized) {
            variantDestroy.invoke(variant)
          }
          destroyTyped(VariantType.PACKED_VECTOR2_ARRAY, packed)
        }
      }
    }
  }

  /** Initialize [dest] as Array[PackedStringArray]. */
  fun initArrayOfPackedStringArrays(dest: MemorySegment, values: List<List<String>>) {
    construct(VariantType.ARRAY, dest, constructorIndex = 0)
    if (values.isEmpty()) return

    val pushBackHash = 3316032543L
    Arena.ofConfined().use { arena ->
      values.forEach { value ->
        val packed = allocatePackedArray(arena)
        initPackedStringArray(packed, value)
        val variant = arena.allocate(VARIANT_SIZE, 8L)
        try {
          VariantConverters.variantFromType(VariantType.PACKED_STRING_ARRAY).invoke(variant, packed)
          call(
            type = VariantType.ARRAY,
            method = "push_back",
            hash = pushBackHash,
            base = dest,
            args = listOf(variant),
          )
        } finally {
          variantDestroy.invoke(variant)
          destroyTyped(VariantType.PACKED_STRING_ARRAY, packed)
        }
      }
    }
  }

  /** Initialize [dest] as a PackedVector3Array and append [values]. */
  fun initPackedVector3Array(dest: MemorySegment, values: List<Vector3>) {
    construct(VariantType.PACKED_VECTOR3_ARRAY, dest, constructorIndex = 0)
    if (values.isEmpty()) return
    // PackedVector3Array.push_back(Vector3) -> bool
    val pushBackHash = 3295363524L
    Arena.ofConfined().use { arena ->
      val vectorArg = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
      val boolRet = arena.allocate(JAVA_BYTE)
      for (value in values) {
        GodotReal.writeIndex(vectorArg, 0, value.x)
        GodotReal.writeIndex(vectorArg, 1, value.y)
        GodotReal.writeIndex(vectorArg, 2, value.z)
        call(
          type = VariantType.PACKED_VECTOR3_ARRAY,
          method = "push_back",
          hash = pushBackHash,
          base = dest,
          args = listOf(vectorArg),
          rReturn = boolRet,
        )
      }
    }
  }

  /** Initialize [dest] as a PackedColorArray and append [values]. */
  fun initPackedColorArray(dest: MemorySegment, values: List<Color>) {
    construct(VariantType.PACKED_COLOR_ARRAY, dest, constructorIndex = 0)
    if (values.isEmpty()) return
    // PackedColorArray.push_back(Color) -> bool
    val pushBackHash = 1007858200L
    Arena.ofConfined().use { arena ->
      val colorArg = arena.allocate(16L, 4L)
      val boolRet = arena.allocate(JAVA_BYTE)
      for (value in values) {
        colorArg.set(JAVA_FLOAT, 0, value.r)
        colorArg.set(JAVA_FLOAT, 4, value.g)
        colorArg.set(JAVA_FLOAT, 8, value.b)
        colorArg.set(JAVA_FLOAT, 12, value.a)
        call(
          type = VariantType.PACKED_COLOR_ARRAY,
          method = "push_back",
          hash = pushBackHash,
          base = dest,
          args = listOf(colorArg),
          rReturn = boolRet,
        )
      }
    }
  }

  /** Initialize [dest] as a PackedVector4Array and append [values]. */
  fun initPackedVector4Array(dest: MemorySegment, values: List<Vector4>) {
    construct(VariantType.PACKED_VECTOR4_ARRAY, dest, constructorIndex = 0)
    if (values.isEmpty()) return
    // PackedVector4Array.push_back(Vector4) -> bool
    val pushBackHash = 3289167688L
    Arena.ofConfined().use { arena ->
      val vectorArg = arena.allocate(GodotReal.SIZE_BYTES * 4, GodotReal.ALIGN_BYTES)
      val boolRet = arena.allocate(JAVA_BYTE)
      for (value in values) {
        GodotReal.writeIndex(vectorArg, 0, value.x)
        GodotReal.writeIndex(vectorArg, 1, value.y)
        GodotReal.writeIndex(vectorArg, 2, value.z)
        GodotReal.writeIndex(vectorArg, 3, value.w)
        call(
          type = VariantType.PACKED_VECTOR4_ARRAY,
          method = "push_back",
          hash = pushBackHash,
          base = dest,
          args = listOf(vectorArg),
          rReturn = boolRet,
        )
      }
    }
  }

  /** Initialize [dest] as StringName from Kotlin [value]. */
  fun initStringName(dest: MemorySegment, value: String) {
    Arena.ofConfined().use { arena ->
      val str = arena.allocate(8L, 8L)
      GodotStrings.initString(str, value)
      try {
        construct(
          type = VariantType.STRING_NAME,
          dest = dest,
          constructorIndex = 2,
          args = listOf(str),
        )
      } finally {
        GodotStrings.destroyString(str)
      }
    }
  }

  /** Initialize [dest] as Callable from object + method name. */
  fun initCallable(dest: MemorySegment, objectArg: MemorySegment, methodName: String) {
    Arena.ofConfined().use { arena ->
      val objectCell = arena.allocate(ADDRESS)
      objectCell.set(ADDRESS, 0, objectArg)
      val methodStringName = arena.allocate(8L, 8L)
      initStringName(methodStringName, methodName)
      try {
        construct(
          type = VariantType.CALLABLE,
          dest = dest,
          constructorIndex = 2,
          args = listOf(objectCell, methodStringName),
        )
      } finally {
        destroyTyped(VariantType.STRING_NAME, methodStringName)
      }
    }
  }

  fun readCallable(src: MemorySegment): GodotCallable? {
    Arena.ofConfined().use { arena ->
      val objectRet = arena.allocate(ADDRESS)
      call(
        type = VariantType.CALLABLE,
        method = "get_object",
        hash = 4008621732L,
        base = src,
        args = emptyList(),
        rReturn = objectRet,
      )
      val objectHandle = objectRet.get(ADDRESS, 0)
      if (objectHandle.address() == 0L) return null

      val methodRet = arena.allocate(8L, 8L)
      try {
        call(
          type = VariantType.CALLABLE,
          method = "get_method",
          hash = 1825232092L,
          base = src,
          args = emptyList(),
          rReturn = methodRet,
        )
        return GodotCallable(GodotObject(objectHandle), GodotStrings.readStringName(methodRet))
      } finally {
        destroyTyped(VariantType.STRING_NAME, methodRet)
      }
    }
  }

  /** Read all values from an initialized PackedStringArray value pointer. */
  fun readPackedStringArray(src: MemorySegment): List<String> {
    // PackedStringArray.size() -> int
    val sizeHash = 3173160232L
    // PackedStringArray.get(int) -> String
    val getHash = 2162347432L
    Arena.ofConfined().use { arena ->
      val sizeRet = arena.allocate(JAVA_LONG)
      call(
        type = VariantType.PACKED_STRING_ARRAY,
        method = "size",
        hash = sizeHash,
        base = src,
        args = emptyList(),
        rReturn = sizeRet,
      )
      val size = sizeRet.get(JAVA_LONG, 0).toInt()
      if (size <= 0) return emptyList()

      val values = ArrayList<String>(size)
      val indexArg = arena.allocate(JAVA_LONG)
      for (i in 0 until size) {
        indexArg.set(JAVA_LONG, 0, i.toLong())
        val strRet = arena.allocate(8L, 8L)
        call(
          type = VariantType.PACKED_STRING_ARRAY,
          method = "get",
          hash = getHash,
          base = src,
          args = listOf(indexArg),
          rReturn = strRet,
        )
        try {
          values += GodotStrings.readString(strRet)
        } finally {
          GodotStrings.destroyString(strRet)
        }
      }
      return values
    }
  }

  /** Read all values from an initialized PackedByteArray value pointer. */
  fun readPackedByteArray(src: MemorySegment): ByteArray {
    val size = packedArraySize(VariantType.PACKED_BYTE_ARRAY, src)
    if (size <= 0) return ByteArray(0)
    val data =
      (packedByteArrayOperatorIndexConst.invoke(src, 0L) as MemorySegment).reinterpret(
        size.toLong()
      )
    val values = ByteArray(size)
    MemorySegment.copy(data, JAVA_BYTE, 0L, values, 0, size)
    return values
  }

  /** Packed*Array.size() -> int; the hash is shared across packed types. */
  private fun packedArraySize(type: VariantType, base: MemorySegment): Int {
    val sizeHash = 3173160232L
    Arena.ofConfined().use { arena ->
      val sizeRet = arena.allocate(JAVA_LONG)
      call(
        type = type,
        method = "size",
        hash = sizeHash,
        base = base,
        args = emptyList(),
        rReturn = sizeRet,
      )
      return sizeRet.get(JAVA_LONG, 0).toInt()
    }
  }

  /** Packed*Array.resize(int) -> int; the hash is shared across packed types. */
  private fun resizePackedArray(type: VariantType, base: MemorySegment, size: Int) {
    val resizeHash = 848867239L
    Arena.ofConfined().use { arena ->
      val sizeArg = arena.allocate(JAVA_LONG)
      sizeArg.set(JAVA_LONG, 0, size.toLong())
      val intRet = arena.allocate(JAVA_LONG)
      call(
        type = type,
        method = "resize",
        hash = resizeHash,
        base = base,
        args = listOf(sizeArg),
        rReturn = intRet,
      )
    }
  }

  /** Read all values from an initialized PackedInt32Array value pointer. */
  fun readPackedInt32Array(src: MemorySegment): List<Int> {
    // PackedInt32Array.size() -> int
    val sizeHash = 3173160232L
    // PackedInt32Array.get(int) -> int
    val getHash = 4103005248L
    Arena.ofConfined().use { arena ->
      val sizeRet = arena.allocate(JAVA_LONG)
      call(
        type = VariantType.PACKED_INT32_ARRAY,
        method = "size",
        hash = sizeHash,
        base = src,
        args = emptyList(),
        rReturn = sizeRet,
      )
      val size = sizeRet.get(JAVA_LONG, 0).toInt()
      if (size <= 0) return emptyList()

      val values = ArrayList<Int>(size)
      val indexArg = arena.allocate(JAVA_LONG)
      val valueRet = arena.allocate(JAVA_LONG)
      for (i in 0 until size) {
        indexArg.set(JAVA_LONG, 0, i.toLong())
        call(
          type = VariantType.PACKED_INT32_ARRAY,
          method = "get",
          hash = getHash,
          base = src,
          args = listOf(indexArg),
          rReturn = valueRet,
        )
        values += valueRet.get(JAVA_LONG, 0).toInt()
      }
      return values
    }
  }

  /** Read all values from an initialized PackedInt64Array value pointer. */
  fun readPackedInt64Array(src: MemorySegment): List<Long> {
    // PackedInt64Array.size() -> int
    val sizeHash = 3173160232L
    // PackedInt64Array.get(int) -> int
    val getHash = 4103005248L
    Arena.ofConfined().use { arena ->
      val sizeRet = arena.allocate(JAVA_LONG)
      call(
        type = VariantType.PACKED_INT64_ARRAY,
        method = "size",
        hash = sizeHash,
        base = src,
        args = emptyList(),
        rReturn = sizeRet,
      )
      val size = sizeRet.get(JAVA_LONG, 0).toInt()
      if (size <= 0) return emptyList()

      val values = ArrayList<Long>(size)
      val indexArg = arena.allocate(JAVA_LONG)
      val valueRet = arena.allocate(JAVA_LONG)
      for (i in 0 until size) {
        indexArg.set(JAVA_LONG, 0, i.toLong())
        call(
          type = VariantType.PACKED_INT64_ARRAY,
          method = "get",
          hash = getHash,
          base = src,
          args = listOf(indexArg),
          rReturn = valueRet,
        )
        values += valueRet.get(JAVA_LONG, 0)
      }
      return values
    }
  }

  /** Read all values from an initialized PackedFloat32Array value pointer. */
  fun readPackedFloat32Array(src: MemorySegment): List<Float> {
    val size = packedArraySize(VariantType.PACKED_FLOAT32_ARRAY, src)
    if (size <= 0) return emptyList()
    val data =
      (packedFloat32ArrayOperatorIndexConst.invoke(src, 0L) as MemorySegment).reinterpret(size * 4L)
    val values = FloatArray(size)
    MemorySegment.copy(data, JAVA_FLOAT, 0L, values, 0, size)
    return values.toList()
  }

  /** Read all values from an initialized PackedFloat64Array value pointer. */
  fun readPackedFloat64Array(src: MemorySegment): List<Double> {
    // PackedFloat64Array.size() -> int
    val sizeHash = 3173160232L
    // PackedFloat64Array.get(int) -> float
    val getHash = 1401583798L
    Arena.ofConfined().use { arena ->
      val sizeRet = arena.allocate(JAVA_LONG)
      call(
        type = VariantType.PACKED_FLOAT64_ARRAY,
        method = "size",
        hash = sizeHash,
        base = src,
        args = emptyList(),
        rReturn = sizeRet,
      )
      val size = sizeRet.get(JAVA_LONG, 0).toInt()
      if (size <= 0) return emptyList()

      val values = ArrayList<Double>(size)
      val indexArg = arena.allocate(JAVA_LONG)
      val valueRet = arena.allocate(JAVA_DOUBLE)
      for (i in 0 until size) {
        indexArg.set(JAVA_LONG, 0, i.toLong())
        call(
          type = VariantType.PACKED_FLOAT64_ARRAY,
          method = "get",
          hash = getHash,
          base = src,
          args = listOf(indexArg),
          rReturn = valueRet,
        )
        values += valueRet.get(JAVA_DOUBLE, 0)
      }
      return values
    }
  }

  /** Read all values from an initialized PackedVector2Array value pointer. */
  fun readPackedVector2Array(src: MemorySegment): List<Vector2> {
    // PackedVector2Array.size() -> int
    val sizeHash = 3173160232L
    // PackedVector2Array.get(int) -> Vector2
    val getHash = 2609058838L
    Arena.ofConfined().use { arena ->
      val sizeRet = arena.allocate(JAVA_LONG)
      call(
        type = VariantType.PACKED_VECTOR2_ARRAY,
        method = "size",
        hash = sizeHash,
        base = src,
        args = emptyList(),
        rReturn = sizeRet,
      )
      val size = sizeRet.get(JAVA_LONG, 0).toInt()
      if (size <= 0) return emptyList()

      val values = ArrayList<Vector2>(size)
      val indexArg = arena.allocate(JAVA_LONG)
      val valueRet = arena.allocate(GodotReal.SIZE_BYTES * 2, GodotReal.ALIGN_BYTES)
      for (i in 0 until size) {
        indexArg.set(JAVA_LONG, 0, i.toLong())
        call(
          type = VariantType.PACKED_VECTOR2_ARRAY,
          method = "get",
          hash = getHash,
          base = src,
          args = listOf(indexArg),
          rReturn = valueRet,
        )
        values +=
          Vector2(x = GodotReal.readIndex(valueRet, 0), y = GodotReal.readIndex(valueRet, 1))
      }
      return values
    }
  }

  /** Read an initialized Array whose elements are PackedVector2Array values. */
  fun readArrayPackedVector2Arrays(src: MemorySegment): List<List<Vector2>> =
    readArrayScalars(src).mapNotNull { value ->
      val elements = value as? List<*> ?: return@mapNotNull null
      elements.mapNotNull { it as? Vector2 }.takeIf { it.size == elements.size }
    }

  /** Read all values from an initialized PackedVector3Array value pointer. */
  fun readPackedVector3Array(src: MemorySegment): List<Vector3> {
    // PackedVector3Array.size() -> int
    val sizeHash = 3173160232L
    // PackedVector3Array.get(int) -> Vector3
    val getHash = 1394941017L
    Arena.ofConfined().use { arena ->
      val sizeRet = arena.allocate(JAVA_LONG)
      call(
        type = VariantType.PACKED_VECTOR3_ARRAY,
        method = "size",
        hash = sizeHash,
        base = src,
        args = emptyList(),
        rReturn = sizeRet,
      )
      val size = sizeRet.get(JAVA_LONG, 0).toInt()
      if (size <= 0) return emptyList()

      val values = ArrayList<Vector3>(size)
      val indexArg = arena.allocate(JAVA_LONG)
      val valueRet = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
      for (i in 0 until size) {
        indexArg.set(JAVA_LONG, 0, i.toLong())
        call(
          type = VariantType.PACKED_VECTOR3_ARRAY,
          method = "get",
          hash = getHash,
          base = src,
          args = listOf(indexArg),
          rReturn = valueRet,
        )
        values +=
          Vector3(
            x = GodotReal.readIndex(valueRet, 0),
            y = GodotReal.readIndex(valueRet, 1),
            z = GodotReal.readIndex(valueRet, 2),
          )
      }
      return values
    }
  }

  /** Read all values from an initialized PackedColorArray value pointer. */
  fun readPackedColorArray(src: MemorySegment): List<Color> {
    // PackedColorArray.size() -> int
    val sizeHash = 3173160232L
    // PackedColorArray.get(int) -> Color
    val getHash = 2972831132L
    Arena.ofConfined().use { arena ->
      val sizeRet = arena.allocate(JAVA_LONG)
      call(
        type = VariantType.PACKED_COLOR_ARRAY,
        method = "size",
        hash = sizeHash,
        base = src,
        args = emptyList(),
        rReturn = sizeRet,
      )
      val size = sizeRet.get(JAVA_LONG, 0).toInt()
      if (size <= 0) return emptyList()

      val values = ArrayList<Color>(size)
      val indexArg = arena.allocate(JAVA_LONG)
      val valueRet = arena.allocate(16L, 4L)
      for (i in 0 until size) {
        indexArg.set(JAVA_LONG, 0, i.toLong())
        call(
          type = VariantType.PACKED_COLOR_ARRAY,
          method = "get",
          hash = getHash,
          base = src,
          args = listOf(indexArg),
          rReturn = valueRet,
        )
        values +=
          Color(
            r = valueRet.get(JAVA_FLOAT, 0),
            g = valueRet.get(JAVA_FLOAT, 4),
            b = valueRet.get(JAVA_FLOAT, 8),
            a = valueRet.get(JAVA_FLOAT, 12),
          )
      }
      return values
    }
  }

  /** Read all values from an initialized PackedVector4Array value pointer. */
  fun readPackedVector4Array(src: MemorySegment): List<Vector4> {
    // PackedVector4Array.size() -> int
    val sizeHash = 3173160232L
    // PackedVector4Array.get(int) -> Vector4
    val getHash = 1227817084L
    Arena.ofConfined().use { arena ->
      val sizeRet = arena.allocate(JAVA_LONG)
      call(
        type = VariantType.PACKED_VECTOR4_ARRAY,
        method = "size",
        hash = sizeHash,
        base = src,
        args = emptyList(),
        rReturn = sizeRet,
      )
      val size = sizeRet.get(JAVA_LONG, 0).toInt()
      if (size <= 0) return emptyList()

      val values = ArrayList<Vector4>(size)
      val indexArg = arena.allocate(JAVA_LONG)
      val valueRet = arena.allocate(GodotReal.SIZE_BYTES * 4, GodotReal.ALIGN_BYTES)
      for (i in 0 until size) {
        indexArg.set(JAVA_LONG, 0, i.toLong())
        call(
          type = VariantType.PACKED_VECTOR4_ARRAY,
          method = "get",
          hash = getHash,
          base = src,
          args = listOf(indexArg),
          rReturn = valueRet,
        )
        values +=
          Vector4(
            x = GodotReal.readIndex(valueRet, 0),
            y = GodotReal.readIndex(valueRet, 1),
            z = GodotReal.readIndex(valueRet, 2),
            w = GodotReal.readIndex(valueRet, 3),
          )
      }
      return values
    }
  }

  /** Read an initialized Array as scalar Kotlin values. */
  fun readArrayScalars(src: MemorySegment): List<Any?> {
    val sizeHash = 3173160232L
    val getHash = 708700221L
    Arena.ofConfined().use { arena ->
      val sizeRet = arena.allocate(JAVA_LONG)
      call(
        type = VariantType.ARRAY,
        method = "size",
        hash = sizeHash,
        base = src,
        args = emptyList(),
        rReturn = sizeRet,
      )
      val size = sizeRet.get(JAVA_LONG, 0).toInt()
      if (size <= 0) return emptyList()

      val values = ArrayList<Any?>(size)
      val indexArg = arena.allocate(JAVA_LONG)
      for (i in 0 until size) {
        indexArg.set(JAVA_LONG, 0, i.toLong())
        val valueVariant = arena.allocate(VARIANT_SIZE, 8L)
        call(
          type = VariantType.ARRAY,
          method = "get",
          hash = getHash,
          base = src,
          args = listOf(indexArg),
          rReturn = valueVariant,
        )
        try {
          values += variantToScalar(valueVariant, arena)
        } finally {
          variantDestroy.invoke(valueVariant)
        }
      }
      return values
    }
  }

  /**
   * Read an initialized Array whose elements are expected to be Object variants. Returned wrappers
   * are non-owning.
   */
  fun readArrayObjects(src: MemorySegment): List<GodotObject> =
    readArrayObjects(src) { handle -> GodotObject(handle) }

  fun <T : Any> readArrayObjects(src: MemorySegment, wrapper: (MemorySegment) -> T?): List<T> {
    val sizeHash = 3173160232L
    val getHash = 708700221L
    Arena.ofConfined().use { arena ->
      val sizeRet = arena.allocate(JAVA_LONG)
      call(
        type = VariantType.ARRAY,
        method = "size",
        hash = sizeHash,
        base = src,
        args = emptyList(),
        rReturn = sizeRet,
      )
      val size = sizeRet.get(JAVA_LONG, 0).toInt()
      if (size <= 0) return emptyList()

      val values = ArrayList<T>(size)
      val indexArg = arena.allocate(JAVA_LONG)
      val valueVariant = arena.allocate(VARIANT_SIZE, 8L)
      val objectScratch = arena.allocate(ADDRESS)
      for (i in 0 until size) {
        indexArg.set(JAVA_LONG, 0, i.toLong())
        call(
          type = VariantType.ARRAY,
          method = "get",
          hash = getHash,
          base = src,
          args = listOf(indexArg),
          rReturn = valueVariant,
        )
        try {
          VariantConverters.variantToType(VariantType.OBJECT).invoke(objectScratch, valueVariant)
          val handle = objectScratch.get(ADDRESS, 0)
          if (handle.address() != 0L) {
            wrapper(handle)?.let(values::add)
          }
        } finally {
          variantDestroy.invoke(valueVariant)
        }
      }
      return values
    }
  }

  fun readArrayNodePaths(src: MemorySegment): List<NodePath> =
    readArrayScalars(src).mapNotNull { value ->
      when (value) {
        is NodePath -> value
        is String -> NodePath(value)
        else -> null
      }
    }

  fun readArrayPlanes(src: MemorySegment): List<Plane> =
    readArrayScalars(src).mapNotNull { it as? Plane }

  fun readArrayRect2(src: MemorySegment): List<Rect2> =
    readArrayScalars(src).mapNotNull { it as? Rect2 }

  fun readArrayTransform3D(src: MemorySegment): List<Transform3D> =
    readArrayScalars(src).mapNotNull { it as? Transform3D }

  fun readArrayVector2(src: MemorySegment): List<Vector2> =
    readArrayScalars(src).mapNotNull { it as? Vector2 }

  fun readArrayVector3(src: MemorySegment): List<Vector3> =
    readArrayScalars(src).mapNotNull { it as? Vector3 }

  fun readArrayArrays(src: MemorySegment): List<List<Any?>> =
    readArrayScalars(src).mapNotNull { value ->
      @Suppress("UNCHECKED_CAST")
      value as? List<Any?>
    }

  fun readArrayPackedStringArrays(src: MemorySegment): List<List<String>> =
    readArrayScalars(src).mapNotNull { value ->
      @Suppress("UNCHECKED_CAST")
      value as? List<String>
    }

  fun readArrayPackedByteArrays(src: MemorySegment): List<ByteArray> =
    readArrayScalars(src).mapNotNull { it as? ByteArray }

  /** Read an initialized Array whose elements are expected to be Vector2i variants. */
  fun readArrayVector2i(src: MemorySegment): List<Vector2i> {
    val sizeHash = 3173160232L
    val getHash = 708700221L
    Arena.ofConfined().use { arena ->
      val sizeRet = arena.allocate(JAVA_LONG)
      call(
        type = VariantType.ARRAY,
        method = "size",
        hash = sizeHash,
        base = src,
        args = emptyList(),
        rReturn = sizeRet,
      )
      val size = sizeRet.get(JAVA_LONG, 0).toInt()
      if (size <= 0) return emptyList()

      val values = ArrayList<Vector2i>(size)
      val indexArg = arena.allocate(JAVA_LONG)
      for (i in 0 until size) {
        indexArg.set(JAVA_LONG, 0, i.toLong())
        val valueVariant = arena.allocate(VARIANT_SIZE, 8L)
        call(
          type = VariantType.ARRAY,
          method = "get",
          hash = getHash,
          base = src,
          args = listOf(indexArg),
          rReturn = valueVariant,
        )
        try {
          (variantToScalar(valueVariant, arena) as? Vector2i)?.let(values::add)
        } finally {
          variantDestroy.invoke(valueVariant)
        }
      }
      return values
    }
  }

  /** Read an initialized Array whose elements are expected to be Vector3i variants. */
  fun readArrayVector3i(src: MemorySegment): List<Vector3i> {
    val sizeHash = 3173160232L
    val getHash = 708700221L
    Arena.ofConfined().use { arena ->
      val sizeRet = arena.allocate(JAVA_LONG)
      call(
        type = VariantType.ARRAY,
        method = "size",
        hash = sizeHash,
        base = src,
        args = emptyList(),
        rReturn = sizeRet,
      )
      val size = sizeRet.get(JAVA_LONG, 0).toInt()
      if (size <= 0) return emptyList()

      val values = ArrayList<Vector3i>(size)
      val indexArg = arena.allocate(JAVA_LONG)
      for (i in 0 until size) {
        indexArg.set(JAVA_LONG, 0, i.toLong())
        val valueVariant = arena.allocate(VARIANT_SIZE, 8L)
        call(
          type = VariantType.ARRAY,
          method = "get",
          hash = getHash,
          base = src,
          args = listOf(indexArg),
          rReturn = valueVariant,
        )
        try {
          (variantToScalar(valueVariant, arena) as? Vector3i)?.let(values::add)
        } finally {
          variantDestroy.invoke(valueVariant)
        }
      }
      return values
    }
  }

  /** Read an initialized Array whose elements are expected to be integer variants. */
  fun readArrayLongs(src: MemorySegment): List<Long> {
    val sizeHash = 3173160232L
    val getHash = 708700221L
    Arena.ofConfined().use { arena ->
      val sizeRet = arena.allocate(JAVA_LONG)
      call(
        type = VariantType.ARRAY,
        method = "size",
        hash = sizeHash,
        base = src,
        args = emptyList(),
        rReturn = sizeRet,
      )
      val size = sizeRet.get(JAVA_LONG, 0).toInt()
      if (size <= 0) return emptyList()

      val values = ArrayList<Long>(size)
      val indexArg = arena.allocate(JAVA_LONG)
      for (i in 0 until size) {
        indexArg.set(JAVA_LONG, 0, i.toLong())
        val valueVariant = arena.allocate(VARIANT_SIZE, 8L)
        call(
          type = VariantType.ARRAY,
          method = "get",
          hash = getHash,
          base = src,
          args = listOf(indexArg),
          rReturn = valueVariant,
        )
        try {
          when (val value = variantToScalar(valueVariant, arena)) {
            is Long -> values.add(value)
            is Int -> values.add(value.toLong())
          }
        } finally {
          variantDestroy.invoke(valueVariant)
        }
      }
      return values
    }
  }

  fun readArrayRids(src: MemorySegment): List<RID> = readArrayScalars(src).mapNotNull { it as? RID }

  fun readArrayStrings(src: MemorySegment): List<String> =
    readArrayScalars(src).mapNotNull { it as? String }

  fun readArrayStringNames(src: MemorySegment): List<String> =
    readArrayScalars(src).mapNotNull { it as? String }

  fun readArrayDictionaries(src: MemorySegment): List<Map<String, Any?>> =
    readArrayScalars(src).mapNotNull { value ->
      @Suppress("UNCHECKED_CAST")
      value as? Map<String, Any?>
    }

  fun readDictionaryScalars(src: MemorySegment): Map<String, Any?> {
    val keysHash = 4144163970L
    val arraySizeHash = 3173160232L
    val arrayGetHash = 708700221L
    val dictionaryGetHash = 2205440559L
    Arena.ofConfined().use { arena ->
      val keys = arena.allocate(8L, 8L)
      call(
        type = VariantType.DICTIONARY,
        method = "keys",
        hash = keysHash,
        base = src,
        args = emptyList(),
        rReturn = keys,
      )
      try {
        val sizeRet = arena.allocate(JAVA_LONG)
        call(
          type = VariantType.ARRAY,
          method = "size",
          hash = arraySizeHash,
          base = keys,
          args = emptyList(),
          rReturn = sizeRet,
        )
        val size = sizeRet.get(JAVA_LONG, 0).toInt()
        if (size <= 0) return emptyMap()

        val result = LinkedHashMap<String, Any?>(size)
        val indexArg = arena.allocate(JAVA_LONG)
        for (i in 0 until size) {
          indexArg.set(JAVA_LONG, 0, i.toLong())
          val keyVariant = arena.allocate(24L, 8L)
          call(
            type = VariantType.ARRAY,
            method = "get",
            hash = arrayGetHash,
            base = keys,
            args = listOf(indexArg),
            rReturn = keyVariant,
          )
          try {
            val key = variantToScalar(keyVariant, arena) as? String ?: continue
            val defaultVariant = arena.allocate(24L, 8L)
            val valueVariant = arena.allocate(24L, 8L)
            initNilVariant(defaultVariant)
            try {
              call(
                type = VariantType.DICTIONARY,
                method = "get",
                hash = dictionaryGetHash,
                base = src,
                args = listOf(keyVariant, defaultVariant),
                rReturn = valueVariant,
              )
              result[key] = variantToScalar(valueVariant, arena)
            } finally {
              variantDestroy.invoke(valueVariant)
              variantDestroy.invoke(defaultVariant)
            }
          } finally {
            variantDestroy.invoke(keyVariant)
          }
        }
        return result
      } finally {
        destroyTyped(VariantType.ARRAY, keys)
      }
    }
  }

  /**
   * Read a Dictionary [src] into a Kotlin map preserving the decoded scalar key (String, Long, …) —
   * the typed-Map analogue of [readDictionaryScalars], which drops non-String keys (issue #40).
   * Values are decoded with [variantToScalar]; object values decode to null here (use the
   * object-value readers below for typed wrapper/resource-script values).
   */
  private fun readDictionaryAnyScalars(
    src: MemorySegment,
    keepNullValues: Boolean = false,
  ): LinkedHashMap<Any?, Any?> =
    readDictionaryEntries(src, keepNullValues) { valueVariant, arena ->
      variantToScalar(valueVariant, arena)
    }

  /**
   * Object-valued Dictionary read: decode scalar keys, wrap each value's Object handle via
   * [wrapper]; 0-handle (nil) values are dropped. Mirrors [readArrayObjects] for arrays.
   */
  private fun <V : Any> readDictionaryObjectValues(
    src: MemorySegment,
    wrapper: (MemorySegment) -> V?,
  ): LinkedHashMap<Any?, V> {
    val map =
      readDictionaryEntries(src) { valueVariant, arena ->
        val objectScratch = arena.allocate(ADDRESS)
        VariantConverters.variantToType(VariantType.OBJECT).invoke(objectScratch, valueVariant)
        val handle = objectScratch.get(ADDRESS, 0)
        if (handle.address() != 0L) wrapper(handle) else null
      }
    @Suppress("UNCHECKED_CAST")
    return map as LinkedHashMap<Any?, V>
  }

  /**
   * Shared typed-dictionary iterator: walk the Dictionary keys, decode each scalar key with
   * [variantToScalar], decode each value with [decodeValue], and drop entries whose value decodes
   * to null. Lifetime handling mirrors [readDictionaryScalars].
   */
  private fun <V> readDictionaryEntries(
    src: MemorySegment,
    keepNullValues: Boolean = false,
    decodeValue: (MemorySegment, Arena) -> V?,
  ): LinkedHashMap<Any?, V?> {
    val keysHash = 4144163970L
    val arraySizeHash = 3173160232L
    val arrayGetHash = 708700221L
    val dictionaryGetHash = 2205440559L
    Arena.ofConfined().use { arena ->
      val keys = arena.allocate(8L, 8L)
      call(
        type = VariantType.DICTIONARY,
        method = "keys",
        hash = keysHash,
        base = src,
        args = emptyList(),
        rReturn = keys,
      )
      try {
        val sizeRet = arena.allocate(JAVA_LONG)
        call(
          type = VariantType.ARRAY,
          method = "size",
          hash = arraySizeHash,
          base = keys,
          args = emptyList(),
          rReturn = sizeRet,
        )
        val size = sizeRet.get(JAVA_LONG, 0).toInt()
        if (size <= 0) return LinkedHashMap()

        val result = LinkedHashMap<Any?, V?>(size)
        val indexArg = arena.allocate(JAVA_LONG)
        for (i in 0 until size) {
          indexArg.set(JAVA_LONG, 0, i.toLong())
          val keyVariant = arena.allocate(24L, 8L)
          call(
            type = VariantType.ARRAY,
            method = "get",
            hash = arrayGetHash,
            base = keys,
            args = listOf(indexArg),
            rReturn = keyVariant,
          )
          try {
            val key = variantToScalar(keyVariant, arena) ?: continue
            val defaultVariant = arena.allocate(24L, 8L)
            val valueVariant = arena.allocate(24L, 8L)
            initNilVariant(defaultVariant)
            try {
              call(
                type = VariantType.DICTIONARY,
                method = "get",
                hash = dictionaryGetHash,
                base = src,
                args = listOf(keyVariant, defaultVariant),
                rReturn = valueVariant,
              )
              val value = decodeValue(valueVariant, arena)
              // Drop nil values by default (typed-array parity). A nullable-value typed
              // Map opts into keeping them so the key survives with a null (C# parity);
              // the generated non-null decode still drops via `?: return@mapNotNull`.
              if (keepNullValues || value != null) result[key] = value
            } finally {
              variantDestroy.invoke(valueVariant)
              variantDestroy.invoke(defaultVariant)
            }
          } finally {
            variantDestroy.invoke(keyVariant)
          }
        }
        return result
      } finally {
        destroyTyped(VariantType.ARRAY, keys)
      }
    }
  }

  /**
   * Decode a Dictionary-carrying Variant into a Kotlin map with scalar keys preserved (issue #40).
   * Objects decode to null; use [readVariantDictionaryObjectValues] and its retained variants for
   * typed wrapper/resource-script values.
   */
  fun readVariantDictionaryAny(
    variant: MemorySegment,
    arena: Arena,
    keepNullValues: Boolean = false,
  ): Map<Any?, Any?> {
    val scratch = arena.allocate(8L, 8L)
    VariantConverters.variantToType(VariantType.DICTIONARY).invoke(scratch, variant)
    try {
      return readDictionaryAnyScalars(scratch, keepNullValues)
    } finally {
      destroyTyped(VariantType.DICTIONARY, scratch)
    }
  }

  /**
   * Object-valued typed Dictionary read (non-retained) — the dictionary analogue of
   * [readVariantObjectArray].
   */
  fun <V : Any> readVariantDictionaryObjectValues(
    variant: MemorySegment,
    arena: Arena,
    wrapper: (MemorySegment) -> V?,
  ): Map<Any?, V> {
    val scratch = arena.allocate(8L, 8L)
    VariantConverters.variantToType(VariantType.DICTIONARY).invoke(scratch, variant)
    try {
      return readDictionaryObjectValues(scratch, wrapper)
    } finally {
      destroyTyped(VariantType.DICTIONARY, scratch)
    }
  }

  /**
   * Object-valued typed Dictionary read that retains Resource values — analogue of
   * [readVariantObjectArrayRetained].
   */
  fun <V : Any> readVariantDictionaryObjectValuesRetained(
    variant: MemorySegment,
    arena: Arena,
    wrapper: (MemorySegment) -> V?,
  ): Map<Any?, V> =
    readVariantDictionaryObjectValues(variant, arena, wrapper).also { map ->
      map.values.forEach { value ->
        if (value is Resource) {
          value.retainForKotlinWrapper()
        }
      }
    }

  /**
   * Object-valued typed Dictionary read that reference-binds each value handle — analogue of
   * [readVariantObjectArrayRetainedHandles].
   */
  fun <V : Any> readVariantDictionaryObjectValuesRetainedHandles(
    variant: MemorySegment,
    arena: Arena,
    wrapper: (MemorySegment) -> V?,
  ): Map<Any?, V> {
    val scratch = arena.allocate(8L, 8L)
    VariantConverters.variantToType(VariantType.DICTIONARY).invoke(scratch, variant)
    try {
      return readDictionaryObjectValues(scratch) { handle ->
        ObjectCalls.ptrcallNoArgsRetBool(referenceBind, handle)
        wrapper(handle)
      }
    } finally {
      destroyTyped(VariantType.DICTIONARY, scratch)
    }
  }

  /**
   * Initialize [dest] as Dictionary and fill selected scalar metadata keys.
   *
   * Supported keys are String. Supported values are the strict [initVariantFromAny] value set.
   */
  fun initDictionary(dest: MemorySegment, entries: Map<String, Any?>) {
    construct(VariantType.DICTIONARY, dest, constructorIndex = 0)
    if (entries.isEmpty()) return

    val set = ptrKeyedSetter(VariantType.DICTIONARY)
    Arena.ofConfined().use { arena ->
      entries.forEach { (key, value) ->
        val keyVar = arena.allocate(24L, 8L)
        val valueVar = arena.allocate(24L, 8L)
        variantFromStringInto(key, keyVar, arena)
        initVariantFromAny(valueVar, value, arena)
        try {
          set.invoke(dest, keyVar, valueVar)
        } finally {
          variantDestroy.invoke(keyVar)
          variantDestroy.invoke(valueVar)
        }
      }
    }
  }

  /**
   * Like [initDictionary] but accepts any [initVariantFromAny]-supported key — the write side of
   * typed `Map<K, V>` exports (issue #40), where keys may be Long/Int/enum-ordinal, not just
   * String.
   */
  fun initDictionaryAny(dest: MemorySegment, entries: Map<*, *>) {
    construct(VariantType.DICTIONARY, dest, constructorIndex = 0)
    if (entries.isEmpty()) return

    val set = ptrKeyedSetter(VariantType.DICTIONARY)
    Arena.ofConfined().use { arena ->
      entries.forEach { (key, value) ->
        val keyVar = arena.allocate(24L, 8L)
        val valueVar = arena.allocate(24L, 8L)
        initVariantFromAny(keyVar, key, arena)
        initVariantFromAny(valueVar, value, arena)
        try {
          set.invoke(dest, keyVar, valueVar)
        } finally {
          variantDestroy.invoke(keyVar)
          variantDestroy.invoke(valueVar)
        }
      }
    }
  }

  /** Initialize [dest] as a Variant containing a Dictionary value. */
  fun initVariantDictionary(dest: MemorySegment, entries: Map<String, Any?>) {
    Arena.ofConfined().use { arena ->
      val dictionary = arena.allocate(8L, 8L)
      initDictionary(dictionary, entries)
      try {
        VariantConverters.variantFromType(VariantType.DICTIONARY).invoke(dest, dictionary)
      } finally {
        destroyTyped(VariantType.DICTIONARY, dictionary)
      }
    }
  }

  /**
   * Like [initVariantDictionary] but accepts any [initVariantFromAny]-supported key — the write
   * side of typed `Map<K, V>` exports (issue #40), paired with the any-key reader
   * [readVariantDictionaryAny]. Kept separate from the generic [initVariantFromAny] Map path so
   * generic Dictionary reads and writes stay String-key-only and symmetrical.
   */
  fun initVariantDictionaryAny(dest: MemorySegment, entries: Map<*, *>) {
    Arena.ofConfined().use { arena ->
      val dictionary = arena.allocate(8L, 8L)
      initDictionaryAny(dictionary, entries)
      try {
        VariantConverters.variantFromType(VariantType.DICTIONARY).invoke(dest, dictionary)
      } finally {
        destroyTyped(VariantType.DICTIONARY, dictionary)
      }
    }
  }

  /**
   * Initialize [dest] as Array and append Dictionary entries represented as scalar key/value maps.
   */
  fun initArrayOfDictionaries(dest: MemorySegment, entries: List<Map<String, Any?>>) {
    construct(VariantType.ARRAY, dest, constructorIndex = 0)
    if (entries.isEmpty()) return

    // Array.push_back(Variant) -> void
    val pushBackHash = 3316032543L
    Arena.ofConfined().use { arena ->
      entries.forEach { map ->
        val dict = arena.allocate(8L, 8L)
        initDictionary(dict, map)
        val dictVariant = arena.allocate(24L, 8L)
        VariantConverters.variantFromType(VariantType.DICTIONARY).invoke(dictVariant, dict)
        try {
          call(
            type = VariantType.ARRAY,
            method = "push_back",
            hash = pushBackHash,
            base = dest,
            args = listOf(dictVariant),
          )
        } finally {
          // Do not destroy dictVariant here. Some editor call paths
          // appear to keep references past this call, and eager
          // destroy can invalidate metadata values.
        }
      }
    }
  }

  /**
   * Initialize [dest] as a Signal value bound to (object, signal name) via the Signal(Object,
   * StringName) constructor (index 2). The constructor copies both arguments (Signal stores the
   * target as an ObjectID, so a freed target degrades to a no-op instead of dangling); destroy
   * [dest] with destroyTyped(VariantType.SIGNAL, ...) after the call.
   */
  fun initSignal(dest: MemorySegment, objectHandle: MemorySegment, signalName: String) {
    Arena.ofConfined().use { arena ->
      val objectCell = arena.allocate(ADDRESS)
      objectCell.set(ADDRESS, 0, objectHandle)
      val nameCell = GodotStrings.makeStringName(signalName)
      construct(VariantType.SIGNAL, dest, constructorIndex = 2, args = listOf(objectCell, nameCell))
    }
  }

  fun initArrayOfRids(dest: MemorySegment, values: List<RID>) {
    construct(VariantType.ARRAY, dest, constructorIndex = 0)
    if (values.isEmpty()) return

    val pushBackHash = 3316032543L
    Arena.ofConfined().use { arena ->
      values.forEach { rid ->
        val variant = arena.allocate(VARIANT_SIZE, 8L)
        variantFromRidInto(rid, variant, arena)
        try {
          call(
            type = VariantType.ARRAY,
            method = "push_back",
            hash = pushBackHash,
            base = dest,
            args = listOf(variant),
          )
        } finally {
          variantDestroy.invoke(variant)
        }
      }
    }
  }

  fun initArrayOfObjects(dest: MemorySegment, values: List<*>) {
    construct(VariantType.ARRAY, dest, constructorIndex = 0)
    if (values.isEmpty()) return

    val pushBackHash = 3316032543L
    Arena.ofConfined().use { arena ->
      values.forEach { value ->
        val objectHandle =
          when (value) {
            is Resource -> value.requireOpenHandle()
            is GodotObject -> value.handle
            else ->
              error(
                "Unsupported Object array value type: ${value?.let { it::class.qualifiedName } ?: "null"}"
              )
          }
        val variant = arena.allocate(VARIANT_SIZE, 8L)
        variantFromObjectInto(objectHandle, variant, arena)
        try {
          call(
            type = VariantType.ARRAY,
            method = "push_back",
            hash = pushBackHash,
            base = dest,
            args = listOf(variant),
          )
        } finally {
          variantDestroy.invoke(variant)
        }
      }
    }
  }

  fun initArrayOfStringNames(dest: MemorySegment, values: List<String>) {
    construct(VariantType.ARRAY, dest, constructorIndex = 0)
    if (values.isEmpty()) return

    val pushBackHash = 3316032543L
    Arena.ofConfined().use { arena ->
      values.forEach { value ->
        val variant = arena.allocate(VARIANT_SIZE, 8L)
        val stringName = GodotStrings.makeStringName(value)
        VariantConverters.variantFromType(VariantType.STRING_NAME).invoke(variant, stringName)
        try {
          call(
            type = VariantType.ARRAY,
            method = "push_back",
            hash = pushBackHash,
            base = dest,
            args = listOf(variant),
          )
        } finally {
          variantDestroy.invoke(variant)
        }
      }
    }
  }

  /**
   * Initialize [dest] as Array and append values that can be represented by [initVariantFromAny].
   */
  fun initArray(dest: MemorySegment, values: List<Any?>) {
    construct(VariantType.ARRAY, dest, constructorIndex = 0)
    if (values.isEmpty()) return

    val pushBackHash = 3316032543L
    Arena.ofConfined().use { arena ->
      values.forEach { value ->
        val variant = arena.allocate(VARIANT_SIZE, 8L)
        initVariantFromAny(variant, value, arena)
        try {
          call(
            type = VariantType.ARRAY,
            method = "push_back",
            hash = pushBackHash,
            base = dest,
            args = listOf(variant),
          )
        } finally {
          variantDestroy.invoke(variant)
        }
      }
    }
  }

  private fun variantToScalar(variant: MemorySegment, arena: Arena): Any? =
    when (VariantConverters.variantTypeOf(variant)) {
      VariantType.NIL -> null
      VariantType.STRING -> {
        val scratch = arena.allocate(8L, 8L)
        VariantConverters.variantToType(VariantType.STRING).invoke(scratch, variant)
        try {
          GodotStrings.readString(scratch)
        } finally {
          GodotStrings.destroyString(scratch)
        }
      }

      VariantType.NODE_PATH -> {
        val scratch = arena.allocate(8L, 8L)
        VariantConverters.variantToType(VariantType.NODE_PATH).invoke(scratch, variant)
        try {
          NodePath(readNodePathString(scratch, arena))
        } finally {
          destroyTyped(VariantType.NODE_PATH, scratch)
        }
      }

      VariantType.RID -> {
        val scratch = arena.allocate(JAVA_LONG)
        VariantConverters.variantToType(VariantType.RID).invoke(scratch, variant)
        RID(scratch.get(JAVA_LONG, 0))
      }

      VariantType.BOOL -> {
        val scratch = arena.allocate(JAVA_BYTE)
        VariantConverters.variantToType(VariantType.BOOL).invoke(scratch, variant)
        scratch.get(JAVA_BYTE, 0).toInt() != 0
      }

      VariantType.INT -> {
        val scratch = arena.allocate(JAVA_LONG)
        VariantConverters.variantToType(VariantType.INT).invoke(scratch, variant)
        scratch.get(JAVA_LONG, 0)
      }

      VariantType.FLOAT -> {
        val scratch = arena.allocate(JAVA_DOUBLE)
        VariantConverters.variantToType(VariantType.FLOAT).invoke(scratch, variant)
        scratch.get(JAVA_DOUBLE, 0)
      }

      VariantType.COLOR -> {
        val scratch = arena.allocate(16L, 4L)
        VariantConverters.variantToType(VariantType.COLOR).invoke(scratch, variant)
        Color(
          r = scratch.get(JAVA_FLOAT, 0),
          g = scratch.get(JAVA_FLOAT, 4),
          b = scratch.get(JAVA_FLOAT, 8),
          a = scratch.get(JAVA_FLOAT, 12),
        )
      }

      VariantType.VECTOR2 -> {
        val scratch = arena.allocate(GodotReal.SIZE_BYTES * 2, GodotReal.ALIGN_BYTES)
        VariantConverters.variantToType(VariantType.VECTOR2).invoke(scratch, variant)
        Vector2(x = GodotReal.readIndex(scratch, 0), y = GodotReal.readIndex(scratch, 1))
      }

      VariantType.VECTOR3 -> {
        val scratch = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
        VariantConverters.variantToType(VariantType.VECTOR3).invoke(scratch, variant)
        Vector3(
          x = GodotReal.readIndex(scratch, 0),
          y = GodotReal.readIndex(scratch, 1),
          z = GodotReal.readIndex(scratch, 2),
        )
      }

      VariantType.VECTOR2I -> {
        val scratch = arena.allocate(8L, 4L)
        VariantConverters.variantToType(VariantType.VECTOR2I).invoke(scratch, variant)
        Vector2i(
          x = scratch.get(java.lang.foreign.ValueLayout.JAVA_INT, 0),
          y = scratch.get(java.lang.foreign.ValueLayout.JAVA_INT, 4),
        )
      }

      VariantType.VECTOR3I -> {
        val scratch = arena.allocate(12L, 4L)
        VariantConverters.variantToType(VariantType.VECTOR3I).invoke(scratch, variant)
        Vector3i(
          x = scratch.get(java.lang.foreign.ValueLayout.JAVA_INT, 0),
          y = scratch.get(java.lang.foreign.ValueLayout.JAVA_INT, 4),
          z = scratch.get(java.lang.foreign.ValueLayout.JAVA_INT, 8),
        )
      }

      VariantType.VECTOR4I -> {
        val scratch = arena.allocate(16L, 4L)
        VariantConverters.variantToType(VariantType.VECTOR4I).invoke(scratch, variant)
        Vector4i(
          x = scratch.get(java.lang.foreign.ValueLayout.JAVA_INT, 0),
          y = scratch.get(java.lang.foreign.ValueLayout.JAVA_INT, 4),
          z = scratch.get(java.lang.foreign.ValueLayout.JAVA_INT, 8),
          w = scratch.get(java.lang.foreign.ValueLayout.JAVA_INT, 12),
        )
      }

      VariantType.RECT2I -> {
        val scratch = arena.allocate(16L, 4L)
        VariantConverters.variantToType(VariantType.RECT2I).invoke(scratch, variant)
        Rect2i(
          position =
            Vector2i(
              scratch.get(java.lang.foreign.ValueLayout.JAVA_INT, 0),
              scratch.get(java.lang.foreign.ValueLayout.JAVA_INT, 4),
            ),
          size =
            Vector2i(
              scratch.get(java.lang.foreign.ValueLayout.JAVA_INT, 8),
              scratch.get(java.lang.foreign.ValueLayout.JAVA_INT, 12),
            ),
        )
      }

      VariantType.QUATERNION -> {
        val scratch = arena.allocate(GodotReal.SIZE_BYTES * 4, GodotReal.ALIGN_BYTES)
        VariantConverters.variantToType(VariantType.QUATERNION).invoke(scratch, variant)
        Quaternion(
          x = GodotReal.readIndex(scratch, 0),
          y = GodotReal.readIndex(scratch, 1),
          z = GodotReal.readIndex(scratch, 2),
          w = GodotReal.readIndex(scratch, 3),
        )
      }

      VariantType.VECTOR4 -> {
        val scratch = arena.allocate(GodotReal.SIZE_BYTES * 4, GodotReal.ALIGN_BYTES)
        VariantConverters.variantToType(VariantType.VECTOR4).invoke(scratch, variant)
        Vector4(
          x = GodotReal.readIndex(scratch, 0),
          y = GodotReal.readIndex(scratch, 1),
          z = GodotReal.readIndex(scratch, 2),
          w = GodotReal.readIndex(scratch, 3),
        )
      }

      VariantType.RECT2 -> {
        val scratch = arena.allocate(GodotReal.SIZE_BYTES * 4, GodotReal.ALIGN_BYTES)
        VariantConverters.variantToType(VariantType.RECT2).invoke(scratch, variant)
        Rect2(
          position = Vector2(GodotReal.readIndex(scratch, 0), GodotReal.readIndex(scratch, 1)),
          size = Vector2(GodotReal.readIndex(scratch, 2), GodotReal.readIndex(scratch, 3)),
        )
      }

      VariantType.AABB -> {
        val scratch = arena.allocate(GodotReal.SIZE_BYTES * 6, GodotReal.ALIGN_BYTES)
        VariantConverters.variantToType(VariantType.AABB).invoke(scratch, variant)
        AABB(
          position =
            Vector3(
              GodotReal.readIndex(scratch, 0),
              GodotReal.readIndex(scratch, 1),
              GodotReal.readIndex(scratch, 2),
            ),
          size =
            Vector3(
              GodotReal.readIndex(scratch, 3),
              GodotReal.readIndex(scratch, 4),
              GodotReal.readIndex(scratch, 5),
            ),
        )
      }

      VariantType.PLANE -> {
        val scratch = arena.allocate(GodotReal.SIZE_BYTES * 4, GodotReal.ALIGN_BYTES)
        VariantConverters.variantToType(VariantType.PLANE).invoke(scratch, variant)
        Plane(
          normal =
            Vector3(
              GodotReal.readIndex(scratch, 0),
              GodotReal.readIndex(scratch, 1),
              GodotReal.readIndex(scratch, 2),
            ),
          d = GodotReal.readIndex(scratch, 3),
        )
      }

      VariantType.BASIS -> {
        val scratch = arena.allocate(GodotReal.SIZE_BYTES * 9, GodotReal.ALIGN_BYTES)
        VariantConverters.variantToType(VariantType.BASIS).invoke(scratch, variant)
        Basis(
          x =
            Vector3(
              GodotReal.readIndex(scratch, 0),
              GodotReal.readIndex(scratch, 3),
              GodotReal.readIndex(scratch, 6),
            ),
          y =
            Vector3(
              GodotReal.readIndex(scratch, 1),
              GodotReal.readIndex(scratch, 4),
              GodotReal.readIndex(scratch, 7),
            ),
          z =
            Vector3(
              GodotReal.readIndex(scratch, 2),
              GodotReal.readIndex(scratch, 5),
              GodotReal.readIndex(scratch, 8),
            ),
        )
      }

      VariantType.TRANSFORM3D -> {
        val scratch = arena.allocate(GodotReal.SIZE_BYTES * 12, GodotReal.ALIGN_BYTES)
        VariantConverters.variantToType(VariantType.TRANSFORM3D).invoke(scratch, variant)
        Transform3D(
          basis =
            Basis(
              x =
                Vector3(
                  GodotReal.readIndex(scratch, 0),
                  GodotReal.readIndex(scratch, 3),
                  GodotReal.readIndex(scratch, 6),
                ),
              y =
                Vector3(
                  GodotReal.readIndex(scratch, 1),
                  GodotReal.readIndex(scratch, 4),
                  GodotReal.readIndex(scratch, 7),
                ),
              z =
                Vector3(
                  GodotReal.readIndex(scratch, 2),
                  GodotReal.readIndex(scratch, 5),
                  GodotReal.readIndex(scratch, 8),
                ),
            ),
          origin =
            Vector3(
              GodotReal.readIndex(scratch, 9),
              GodotReal.readIndex(scratch, 10),
              GodotReal.readIndex(scratch, 11),
            ),
        )
      }

      VariantType.TRANSFORM2D -> {
        val scratch = arena.allocate(GodotReal.SIZE_BYTES * 6, GodotReal.ALIGN_BYTES)
        VariantConverters.variantToType(VariantType.TRANSFORM2D).invoke(scratch, variant)
        Transform2D(
          x = Vector2(GodotReal.readIndex(scratch, 0), GodotReal.readIndex(scratch, 1)),
          y = Vector2(GodotReal.readIndex(scratch, 2), GodotReal.readIndex(scratch, 3)),
          origin = Vector2(GodotReal.readIndex(scratch, 4), GodotReal.readIndex(scratch, 5)),
        )
      }

      VariantType.PROJECTION -> {
        val scratch = arena.allocate(GodotReal.SIZE_BYTES * 16, GodotReal.ALIGN_BYTES)
        VariantConverters.variantToType(VariantType.PROJECTION).invoke(scratch, variant)
        fun col(index: Long) =
          Vector4(
            GodotReal.readIndex(scratch, index),
            GodotReal.readIndex(scratch, index + 1),
            GodotReal.readIndex(scratch, index + 2),
            GodotReal.readIndex(scratch, index + 3),
          )
        Projection(col(0), col(4), col(8), col(12))
      }

      VariantType.OBJECT -> {
        val scratch = arena.allocate(ADDRESS)
        VariantConverters.variantToType(VariantType.OBJECT).invoke(scratch, variant)
        val handle = scratch.get(ADDRESS, 0)
        if (handle.address() == 0L) null else GodotObject(handle)
      }

      VariantType.PACKED_BYTE_ARRAY -> {
        val scratch = allocatePackedArray(arena)
        VariantConverters.variantToType(VariantType.PACKED_BYTE_ARRAY).invoke(scratch, variant)
        try {
          readPackedByteArray(scratch)
        } finally {
          destroyTyped(VariantType.PACKED_BYTE_ARRAY, scratch)
        }
      }

      VariantType.PACKED_STRING_ARRAY -> {
        val scratch = allocatePackedArray(arena)
        VariantConverters.variantToType(VariantType.PACKED_STRING_ARRAY).invoke(scratch, variant)
        try {
          readPackedStringArray(scratch)
        } finally {
          destroyTyped(VariantType.PACKED_STRING_ARRAY, scratch)
        }
      }

      VariantType.PACKED_INT32_ARRAY -> {
        val scratch = allocatePackedArray(arena)
        VariantConverters.variantToType(VariantType.PACKED_INT32_ARRAY).invoke(scratch, variant)
        try {
          readPackedInt32Array(scratch)
        } finally {
          destroyTyped(VariantType.PACKED_INT32_ARRAY, scratch)
        }
      }

      VariantType.PACKED_INT64_ARRAY -> {
        val scratch = allocatePackedArray(arena)
        VariantConverters.variantToType(VariantType.PACKED_INT64_ARRAY).invoke(scratch, variant)
        try {
          readPackedInt64Array(scratch)
        } finally {
          destroyTyped(VariantType.PACKED_INT64_ARRAY, scratch)
        }
      }

      VariantType.PACKED_FLOAT32_ARRAY -> {
        val scratch = allocatePackedArray(arena)
        VariantConverters.variantToType(VariantType.PACKED_FLOAT32_ARRAY).invoke(scratch, variant)
        try {
          readPackedFloat32Array(scratch)
        } finally {
          destroyTyped(VariantType.PACKED_FLOAT32_ARRAY, scratch)
        }
      }

      VariantType.PACKED_FLOAT64_ARRAY -> {
        val scratch = allocatePackedArray(arena)
        VariantConverters.variantToType(VariantType.PACKED_FLOAT64_ARRAY).invoke(scratch, variant)
        try {
          readPackedFloat64Array(scratch)
        } finally {
          destroyTyped(VariantType.PACKED_FLOAT64_ARRAY, scratch)
        }
      }

      VariantType.PACKED_VECTOR2_ARRAY -> {
        val scratch = allocatePackedArray(arena)
        VariantConverters.variantToType(VariantType.PACKED_VECTOR2_ARRAY).invoke(scratch, variant)
        try {
          readPackedVector2Array(scratch)
        } finally {
          destroyTyped(VariantType.PACKED_VECTOR2_ARRAY, scratch)
        }
      }

      VariantType.PACKED_VECTOR3_ARRAY -> {
        val scratch = allocatePackedArray(arena)
        VariantConverters.variantToType(VariantType.PACKED_VECTOR3_ARRAY).invoke(scratch, variant)
        try {
          readPackedVector3Array(scratch)
        } finally {
          destroyTyped(VariantType.PACKED_VECTOR3_ARRAY, scratch)
        }
      }

      VariantType.PACKED_COLOR_ARRAY -> {
        val scratch = allocatePackedArray(arena)
        VariantConverters.variantToType(VariantType.PACKED_COLOR_ARRAY).invoke(scratch, variant)
        try {
          readPackedColorArray(scratch)
        } finally {
          destroyTyped(VariantType.PACKED_COLOR_ARRAY, scratch)
        }
      }

      VariantType.PACKED_VECTOR4_ARRAY -> {
        val scratch = allocatePackedArray(arena)
        VariantConverters.variantToType(VariantType.PACKED_VECTOR4_ARRAY).invoke(scratch, variant)
        try {
          readPackedVector4Array(scratch)
        } finally {
          destroyTyped(VariantType.PACKED_VECTOR4_ARRAY, scratch)
        }
      }

      VariantType.ARRAY -> {
        val scratch = arena.allocate(8L, 8L)
        VariantConverters.variantToType(VariantType.ARRAY).invoke(scratch, variant)
        try {
          readArrayScalars(scratch)
        } finally {
          destroyTyped(VariantType.ARRAY, scratch)
        }
      }

      VariantType.DICTIONARY -> {
        val scratch = arena.allocate(8L, 8L)
        VariantConverters.variantToType(VariantType.DICTIONARY).invoke(scratch, variant)
        try {
          readDictionaryScalars(scratch)
        } finally {
          destroyTyped(VariantType.DICTIONARY, scratch)
        }
      }

      else -> null
    }

  private fun variantFromStringInto(value: String, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(8L, 8L)
    GodotStrings.initString(scratch, value)
    try {
      VariantConverters.variantFromType(VariantType.STRING).invoke(variantOut, scratch)
    } finally {
      GodotStrings.destroyString(scratch)
    }
  }

  private fun variantFromBoolInto(value: Boolean, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(JAVA_BYTE)
    scratch.set(JAVA_BYTE, 0, if (value) 1.toByte() else 0.toByte())
    VariantConverters.variantFromType(VariantType.BOOL).invoke(variantOut, scratch)
  }

  private fun variantFromLongInto(value: Long, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(JAVA_LONG)
    scratch.set(JAVA_LONG, 0, value)
    VariantConverters.variantFromType(VariantType.INT).invoke(variantOut, scratch)
  }

  private fun variantFromDoubleInto(value: Double, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(java.lang.foreign.ValueLayout.JAVA_DOUBLE)
    scratch.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE, 0, value)
    VariantConverters.variantFromType(VariantType.FLOAT).invoke(variantOut, scratch)
  }

  private fun variantFromRidInto(value: RID, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(JAVA_LONG)
    scratch.set(JAVA_LONG, 0, value.value)
    VariantConverters.variantFromType(VariantType.RID).invoke(variantOut, scratch)
  }

  private fun variantFromObjectInto(value: MemorySegment, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(ADDRESS)
    scratch.set(ADDRESS, 0, value)
    VariantConverters.variantFromType(VariantType.OBJECT).invoke(variantOut, scratch)
  }

  private fun variantFromArrayInto(value: List<*>, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(8L, 8L)
    initArray(scratch, value)
    try {
      VariantConverters.variantFromType(VariantType.ARRAY).invoke(variantOut, scratch)
    } finally {
      destroyTyped(VariantType.ARRAY, scratch)
    }
  }

  private fun variantFromDictionaryInto(value: Map<*, *>, variantOut: MemorySegment, arena: Arena) {
    // Generic Map -> Variant is String-key-only, symmetric with the generic reader
    // [readDictionaryScalars] (which drops non-String keys). Typed `Map<K, V>` exports
    // with non-String keys use the dedicated [initVariantDictionaryAny] write path
    // instead, paired with the any-key reader [readVariantDictionaryAny] (issue #40).
    val entries =
      value.entries.associate { (key, entryValue) ->
        val stringKey =
          key as? String
            ?: error(
              "Unsupported Dictionary key type: ${key?.let { it::class.qualifiedName } ?: "null"}"
            )
        stringKey to entryValue
      }
    val scratch = arena.allocate(8L, 8L)
    initDictionary(scratch, entries)
    try {
      VariantConverters.variantFromType(VariantType.DICTIONARY).invoke(variantOut, scratch)
    } finally {
      destroyTyped(VariantType.DICTIONARY, scratch)
    }
  }

  private fun variantFromPackedByteArrayInto(
    value: ByteArray,
    variantOut: MemorySegment,
    arena: Arena,
  ) {
    val scratch = allocatePackedArray(arena)
    initPackedByteArray(scratch, value)
    try {
      VariantConverters.variantFromType(VariantType.PACKED_BYTE_ARRAY).invoke(variantOut, scratch)
    } finally {
      destroyTyped(VariantType.PACKED_BYTE_ARRAY, scratch)
    }
  }

  private fun variantFromColorInto(value: Color, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(16L, 4L)
    scratch.set(JAVA_FLOAT, 0, value.r)
    scratch.set(JAVA_FLOAT, 4, value.g)
    scratch.set(JAVA_FLOAT, 8, value.b)
    scratch.set(JAVA_FLOAT, 12, value.a)
    VariantConverters.variantFromType(VariantType.COLOR).invoke(variantOut, scratch)
  }

  private fun variantFromVector2Into(value: Vector2, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(GodotReal.SIZE_BYTES * 2, GodotReal.ALIGN_BYTES)
    GodotReal.writeIndex(scratch, 0, value.x)
    GodotReal.writeIndex(scratch, 1, value.y)
    VariantConverters.variantFromType(VariantType.VECTOR2).invoke(variantOut, scratch)
  }

  private fun variantFromVector3Into(value: Vector3, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
    GodotReal.writeIndex(scratch, 0, value.x)
    GodotReal.writeIndex(scratch, 1, value.y)
    GodotReal.writeIndex(scratch, 2, value.z)
    VariantConverters.variantFromType(VariantType.VECTOR3).invoke(variantOut, scratch)
  }

  private fun variantFromVector2iInto(value: Vector2i, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(8L, 4L)
    scratch.set(java.lang.foreign.ValueLayout.JAVA_INT, 0, value.x)
    scratch.set(java.lang.foreign.ValueLayout.JAVA_INT, 4, value.y)
    VariantConverters.variantFromType(VariantType.VECTOR2I).invoke(variantOut, scratch)
  }

  private fun variantFromVector3iInto(value: Vector3i, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(12L, 4L)
    scratch.set(java.lang.foreign.ValueLayout.JAVA_INT, 0, value.x)
    scratch.set(java.lang.foreign.ValueLayout.JAVA_INT, 4, value.y)
    scratch.set(java.lang.foreign.ValueLayout.JAVA_INT, 8, value.z)
    VariantConverters.variantFromType(VariantType.VECTOR3I).invoke(variantOut, scratch)
  }

  private fun variantFromVector4iInto(value: Vector4i, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(16L, 4L)
    scratch.set(java.lang.foreign.ValueLayout.JAVA_INT, 0, value.x)
    scratch.set(java.lang.foreign.ValueLayout.JAVA_INT, 4, value.y)
    scratch.set(java.lang.foreign.ValueLayout.JAVA_INT, 8, value.z)
    scratch.set(java.lang.foreign.ValueLayout.JAVA_INT, 12, value.w)
    VariantConverters.variantFromType(VariantType.VECTOR4I).invoke(variantOut, scratch)
  }

  private fun variantFromRect2iInto(value: Rect2i, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(16L, 4L)
    scratch.set(java.lang.foreign.ValueLayout.JAVA_INT, 0, value.position.x)
    scratch.set(java.lang.foreign.ValueLayout.JAVA_INT, 4, value.position.y)
    scratch.set(java.lang.foreign.ValueLayout.JAVA_INT, 8, value.size.x)
    scratch.set(java.lang.foreign.ValueLayout.JAVA_INT, 12, value.size.y)
    VariantConverters.variantFromType(VariantType.RECT2I).invoke(variantOut, scratch)
  }

  private fun variantFromQuaternionInto(
    value: Quaternion,
    variantOut: MemorySegment,
    arena: Arena,
  ) {
    val scratch = arena.allocate(GodotReal.SIZE_BYTES * 4, GodotReal.ALIGN_BYTES)
    GodotReal.writeIndex(scratch, 0, value.x)
    GodotReal.writeIndex(scratch, 1, value.y)
    GodotReal.writeIndex(scratch, 2, value.z)
    GodotReal.writeIndex(scratch, 3, value.w)
    VariantConverters.variantFromType(VariantType.QUATERNION).invoke(variantOut, scratch)
  }

  private fun variantFromVector4Into(value: Vector4, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(GodotReal.SIZE_BYTES * 4, GodotReal.ALIGN_BYTES)
    GodotReal.writeIndex(scratch, 0, value.x)
    GodotReal.writeIndex(scratch, 1, value.y)
    GodotReal.writeIndex(scratch, 2, value.z)
    GodotReal.writeIndex(scratch, 3, value.w)
    VariantConverters.variantFromType(VariantType.VECTOR4).invoke(variantOut, scratch)
  }

  private fun variantFromRect2Into(value: Rect2, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(GodotReal.SIZE_BYTES * 4, GodotReal.ALIGN_BYTES)
    GodotReal.writeIndex(scratch, 0, value.position.x)
    GodotReal.writeIndex(scratch, 1, value.position.y)
    GodotReal.writeIndex(scratch, 2, value.size.x)
    GodotReal.writeIndex(scratch, 3, value.size.y)
    VariantConverters.variantFromType(VariantType.RECT2).invoke(variantOut, scratch)
  }

  private fun variantFromAABBInto(value: AABB, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(GodotReal.SIZE_BYTES * 6, GodotReal.ALIGN_BYTES)
    GodotReal.writeIndex(scratch, 0, value.position.x)
    GodotReal.writeIndex(scratch, 1, value.position.y)
    GodotReal.writeIndex(scratch, 2, value.position.z)
    GodotReal.writeIndex(scratch, 3, value.size.x)
    GodotReal.writeIndex(scratch, 4, value.size.y)
    GodotReal.writeIndex(scratch, 5, value.size.z)
    VariantConverters.variantFromType(VariantType.AABB).invoke(variantOut, scratch)
  }

  private fun variantFromPlaneInto(value: Plane, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(GodotReal.SIZE_BYTES * 4, GodotReal.ALIGN_BYTES)
    GodotReal.writeIndex(scratch, 0, value.normal.x)
    GodotReal.writeIndex(scratch, 1, value.normal.y)
    GodotReal.writeIndex(scratch, 2, value.normal.z)
    GodotReal.writeIndex(scratch, 3, value.d)
    VariantConverters.variantFromType(VariantType.PLANE).invoke(variantOut, scratch)
  }

  private fun variantFromBasisInto(value: Basis, variantOut: MemorySegment, arena: Arena) {
    val scratch = arena.allocate(GodotReal.SIZE_BYTES * 9, GodotReal.ALIGN_BYTES)
    GodotReal.writeIndex(scratch, 0, value.x.x)
    GodotReal.writeIndex(scratch, 1, value.y.x)
    GodotReal.writeIndex(scratch, 2, value.z.x)
    GodotReal.writeIndex(scratch, 3, value.x.y)
    GodotReal.writeIndex(scratch, 4, value.y.y)
    GodotReal.writeIndex(scratch, 5, value.z.y)
    GodotReal.writeIndex(scratch, 6, value.x.z)
    GodotReal.writeIndex(scratch, 7, value.y.z)
    GodotReal.writeIndex(scratch, 8, value.z.z)
    VariantConverters.variantFromType(VariantType.BASIS).invoke(variantOut, scratch)
  }

  private fun variantFromTransform3DInto(
    value: Transform3D,
    variantOut: MemorySegment,
    arena: Arena,
  ) {
    val scratch = arena.allocate(GodotReal.SIZE_BYTES * 12, GodotReal.ALIGN_BYTES)
    GodotReal.writeIndex(scratch, 0, value.basis.x.x)
    GodotReal.writeIndex(scratch, 1, value.basis.y.x)
    GodotReal.writeIndex(scratch, 2, value.basis.z.x)
    GodotReal.writeIndex(scratch, 3, value.basis.x.y)
    GodotReal.writeIndex(scratch, 4, value.basis.y.y)
    GodotReal.writeIndex(scratch, 5, value.basis.z.y)
    GodotReal.writeIndex(scratch, 6, value.basis.x.z)
    GodotReal.writeIndex(scratch, 7, value.basis.y.z)
    GodotReal.writeIndex(scratch, 8, value.basis.z.z)
    GodotReal.writeIndex(scratch, 9, value.origin.x)
    GodotReal.writeIndex(scratch, 10, value.origin.y)
    GodotReal.writeIndex(scratch, 11, value.origin.z)
    VariantConverters.variantFromType(VariantType.TRANSFORM3D).invoke(variantOut, scratch)
  }

  private fun variantFromTransform2DInto(
    value: Transform2D,
    variantOut: MemorySegment,
    arena: Arena,
  ) {
    val scratch = arena.allocate(GodotReal.SIZE_BYTES * 6, GodotReal.ALIGN_BYTES)
    GodotReal.writeIndex(scratch, 0, value.x.x)
    GodotReal.writeIndex(scratch, 1, value.x.y)
    GodotReal.writeIndex(scratch, 2, value.y.x)
    GodotReal.writeIndex(scratch, 3, value.y.y)
    GodotReal.writeIndex(scratch, 4, value.origin.x)
    GodotReal.writeIndex(scratch, 5, value.origin.y)
    VariantConverters.variantFromType(VariantType.TRANSFORM2D).invoke(variantOut, scratch)
  }

  private fun variantFromProjectionInto(
    value: Projection,
    variantOut: MemorySegment,
    arena: Arena,
  ) {
    val scratch = arena.allocate(GodotReal.SIZE_BYTES * 16, GodotReal.ALIGN_BYTES)
    fun writeCol(index: Long, c: Vector4) {
      GodotReal.writeIndex(scratch, index, c.x)
      GodotReal.writeIndex(scratch, index + 1, c.y)
      GodotReal.writeIndex(scratch, index + 2, c.z)
      GodotReal.writeIndex(scratch, index + 3, c.w)
    }
    writeCol(0, value.x)
    writeCol(4, value.y)
    writeCol(8, value.z)
    writeCol(12, value.w)
    VariantConverters.variantFromType(VariantType.PROJECTION).invoke(variantOut, scratch)
  }

  /**
   * NodePath is heap-allocated and refcounted (no fixed value layout) so we construct one from a
   * Godot String, copy it into the Variant, then destroy our temporary handle. Mirrors the approach
   * in `ObjectCalls.ptrcallWithNodePathArg*`.
   */
  private fun variantFromNodePathInto(value: NodePath, variantOut: MemorySegment, arena: Arena) {
    val nodePath = arena.allocate(8L, 8L)
    val pathString = arena.allocate(8L, 8L)
    try {
      GodotStrings.initString(pathString, value.path)
      construct(
        type = VariantType.NODE_PATH,
        dest = nodePath,
        constructorIndex = 2,
        args = listOf(pathString),
      )
      VariantConverters.variantFromType(VariantType.NODE_PATH).invoke(variantOut, nodePath)
    } finally {
      destroyTyped(VariantType.NODE_PATH, nodePath)
      GodotStrings.destroyString(pathString)
    }
  }

  private fun readNodePathString(nodePath: MemorySegment, arena: Arena): String {
    val parts = ArrayList<String>()
    val intRet = arena.allocate(JAVA_LONG)

    fun callInt(method: String, hash: Long): Long {
      call(VariantType.NODE_PATH, method, hash, nodePath, emptyList(), intRet)
      return intRet.get(JAVA_LONG, 0)
    }

    val isAbsoluteRet = arena.allocate(JAVA_BYTE)
    call(VariantType.NODE_PATH, "is_absolute", 3918633141L, nodePath, emptyList(), isAbsoluteRet)
    val isAbsolute = isAbsoluteRet.get(JAVA_BYTE, 0).toInt() != 0

    val indexArg = arena.allocate(JAVA_LONG)
    val stringNameRet = arena.allocate(8L, 8L)

    repeat(callInt("get_name_count", 3173160232L).toInt()) { i ->
      indexArg.set(JAVA_LONG, 0, i.toLong())
      call(
        VariantType.NODE_PATH,
        "get_name",
        2948586938L,
        nodePath,
        listOf(indexArg),
        stringNameRet,
      )
      try {
        parts += GodotStrings.readStringName(stringNameRet)
      } finally {
        destroyTyped(VariantType.STRING_NAME, stringNameRet)
      }
    }

    val path = buildString {
      if (isAbsolute) append("/")
      append(parts.joinToString("/"))
    }

    val subnames = ArrayList<String>()
    repeat(callInt("get_subname_count", 3173160232L).toInt()) { i ->
      indexArg.set(JAVA_LONG, 0, i.toLong())
      call(
        VariantType.NODE_PATH,
        "get_subname",
        2948586938L,
        nodePath,
        listOf(indexArg),
        stringNameRet,
      )
      try {
        subnames += GodotStrings.readStringName(stringNameRet)
      } finally {
        destroyTyped(VariantType.STRING_NAME, stringNameRet)
      }
    }

    return if (subnames.isEmpty()) path else "$path:${subnames.joinToString(":")}"
  }

  private const val REFCOUNTED_REFERENCE_HASH = 2240911060L
  private const val REFCOUNTED_UNREFERENCE_HASH = 2240911060L

  private val referenceBind by lazy {
    ObjectCalls.getMethodBind("RefCounted", "reference", REFCOUNTED_REFERENCE_HASH)
  }

  private val unreferenceBind by lazy {
    ObjectCalls.getMethodBind("RefCounted", "unreference", REFCOUNTED_UNREFERENCE_HASH)
  }
}
