@file:OptIn(ExperimentalForeignApi::class)

package net.multigesture.kanama.binding.runtime

import java.lang.foreign.MemorySegment
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.COpaquePointerVar
import kotlinx.cinterop.CPointed
import kotlinx.cinterop.DoubleVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.FloatVar
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.cstr
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.set
import kotlinx.cinterop.value
import net.multigesture.kanama.ios.cinterop.KanamaIosCallableArgDesc
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_ptrcall
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.GodotReal
import net.multigesture.kanama.types.GodotRealVar
import net.multigesture.kanama.types.NodePath
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

/**
 * GENERATED iOS ObjectCalls helper bodies (scripts/generate_api_wrapper.py --ios-*). DO NOT EDIT BY
 * HAND. Re-run the generator instead.
 *
 * Each helper is an extension on the hand-written `object ObjectCalls`, so the generated Godot API
 * wrappers' `ObjectCalls.<helper>(...)` calls resolve here. Every helper marshals through the
 * single generic C dispatch `kanama_ios_godot_ptrcall`, applying the authoritative ptrcall width
 * table (scalar float->double/8B, scalar int->int64/8B, Vector components->GodotReal, Object->8B
 * handle, StringName built C-side). Helpers already hand-written in ObjectCalls.kt are the override
 * set and are NOT regenerated here.
 */
private const val PT_VOID = 0
private const val PT_BOOL = 1
private const val PT_INT64 = 3
private const val PT_FLOAT64 = 5
private const val PT_VECTOR2 = 6
private const val PT_VECTOR2I = 7
private const val PT_VECTOR3 = 8
private const val PT_VECTOR3I = 9
private const val PT_COLOR = 11
private const val PT_RECT2 = 12
private const val PT_OBJECT = 13
private const val PT_RID = 14
private const val PT_STRING_NAME = 15
private const val PT_STRING = 16
private const val PT_NODE_PATH = 17
private const val PT_BASIS = 18
private const val PT_TRANSFORM3D = 19
private const val PT_QUATERNION = 20
private const val PT_AABB = 21
private const val PT_TRANSFORM2D = 22
private const val PT_PROJECTION = 25
private const val PT_CALLABLE = 27
private const val PT_RECT2I = 37

fun ObjectCalls.ptrcallNoArgsRetAABB(methodBind: MemorySegment, instance: MemorySegment): AABB =
  memScoped {
    val ret = allocArray<GodotRealVar>(6)
    kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), null, null, 0, PT_AABB, ret)
    AABB(
      Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2])),
      Vector3(GodotReal.fromC(ret[3]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[5])),
    )
  }

fun ObjectCalls.ptrcallNoArgsRetBasis(methodBind: MemorySegment, instance: MemorySegment): Basis =
  memScoped {
    val ret = allocArray<GodotRealVar>(9)
    kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), null, null, 0, PT_BASIS, ret)
    Basis(
      Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[3]), GodotReal.fromC(ret[6])),
      Vector3(GodotReal.fromC(ret[1]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[7])),
      Vector3(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[5]), GodotReal.fromC(ret[8])),
    )
  }

fun ObjectCalls.ptrcallNoArgsRetProjection(
  methodBind: MemorySegment,
  instance: MemorySegment,
): Projection = memScoped {
  val ret = allocArray<GodotRealVar>(16)
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    null,
    null,
    0,
    PT_PROJECTION,
    ret,
  )
  Projection(
    Vector4(
      GodotReal.fromC(ret[0]),
      GodotReal.fromC(ret[1]),
      GodotReal.fromC(ret[2]),
      GodotReal.fromC(ret[3]),
    ),
    Vector4(
      GodotReal.fromC(ret[4]),
      GodotReal.fromC(ret[5]),
      GodotReal.fromC(ret[6]),
      GodotReal.fromC(ret[7]),
    ),
    Vector4(
      GodotReal.fromC(ret[8]),
      GodotReal.fromC(ret[9]),
      GodotReal.fromC(ret[10]),
      GodotReal.fromC(ret[11]),
    ),
    Vector4(
      GodotReal.fromC(ret[12]),
      GodotReal.fromC(ret[13]),
      GodotReal.fromC(ret[14]),
      GodotReal.fromC(ret[15]),
    ),
  )
}

fun ObjectCalls.ptrcallNoArgsRetQuaternion(
  methodBind: MemorySegment,
  instance: MemorySegment,
): Quaternion = memScoped {
  val ret = allocArray<GodotRealVar>(4)
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    null,
    null,
    0,
    PT_QUATERNION,
    ret,
  )
  Quaternion(
    GodotReal.fromC(ret[0]),
    GodotReal.fromC(ret[1]),
    GodotReal.fromC(ret[2]),
    GodotReal.fromC(ret[3]),
  )
}

fun ObjectCalls.ptrcallNoArgsRetRID(methodBind: MemorySegment, instance: MemorySegment): RID =
  memScoped {
    val ret = alloc<LongVar>()
    ret.value = 0
    kanama_ios_godot_ptrcall(
      methodBind.address(),
      instance.address(),
      null,
      null,
      0,
      PT_RID,
      ret.ptr,
    )
    RID(ret.value)
  }

fun ObjectCalls.ptrcallNoArgsRetRect2i(methodBind: MemorySegment, instance: MemorySegment): Rect2i =
  memScoped {
    val ret = allocArray<IntVar>(4)
    kanama_ios_godot_ptrcall(
      methodBind.address(),
      instance.address(),
      null,
      null,
      0,
      PT_RECT2I,
      ret,
    )
    Rect2i(Vector2i(ret[0], ret[1]), Vector2i(ret[2], ret[3]))
  }

fun ObjectCalls.ptrcallNoArgsRetTransform2D(
  methodBind: MemorySegment,
  instance: MemorySegment,
): Transform2D = memScoped {
  val ret = allocArray<GodotRealVar>(6)
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    null,
    null,
    0,
    PT_TRANSFORM2D,
    ret,
  )
  Transform2D(
    Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1])),
    Vector2(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3])),
    Vector2(GodotReal.fromC(ret[4]), GodotReal.fromC(ret[5])),
  )
}

fun ObjectCalls.ptrcallNoArgsRetTransform3D(
  methodBind: MemorySegment,
  instance: MemorySegment,
): Transform3D = memScoped {
  val ret = allocArray<GodotRealVar>(12)
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    null,
    null,
    0,
    PT_TRANSFORM3D,
    ret,
  )
  Transform3D(
    Basis(
      Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[3]), GodotReal.fromC(ret[6])),
      Vector3(GodotReal.fromC(ret[1]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[7])),
      Vector3(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[5]), GodotReal.fromC(ret[8])),
    ),
    Vector3(GodotReal.fromC(ret[9]), GodotReal.fromC(ret[10]), GodotReal.fromC(ret[11])),
  )
}

fun ObjectCalls.ptrcallNoArgsRetUInt32(methodBind: MemorySegment, instance: MemorySegment): Long =
  memScoped {
    val ret = alloc<LongVar>()
    kanama_ios_godot_ptrcall(
      methodBind.address(),
      instance.address(),
      null,
      null,
      0,
      PT_INT64,
      ret.ptr,
    )
    ret.value
  }

fun ObjectCalls.ptrcallWithAABBArg(methodBind: MemorySegment, instance: MemorySegment, a0: AABB) =
  memScoped {
    val c0 = allocArray<GodotRealVar>(6)
    c0[0] = GodotReal.toC(a0.position.x)
    c0[1] = GodotReal.toC(a0.position.y)
    c0[2] = GodotReal.toC(a0.position.z)
    c0[3] = GodotReal.toC(a0.size.x)
    c0[4] = GodotReal.toC(a0.size.y)
    c0[5] = GodotReal.toC(a0.size.z)
    val types = allocArray<IntVar>(1)
    types[0] = PT_AABB
    val ptrs = allocArray<COpaquePointerVar>(1)
    ptrs[0] = c0.reinterpret<CPointed>()
    kanama_ios_godot_ptrcall(
      methodBind.address(),
      instance.address(),
      types,
      ptrs,
      1,
      PT_VOID,
      null,
    )
    Unit
  }

fun ObjectCalls.ptrcallWithBasisArg(methodBind: MemorySegment, instance: MemorySegment, a0: Basis) =
  memScoped {
    val c0 = allocArray<GodotRealVar>(9)
    c0[0] = GodotReal.toC(a0.x.x)
    c0[1] = GodotReal.toC(a0.y.x)
    c0[2] = GodotReal.toC(a0.z.x)
    c0[3] = GodotReal.toC(a0.x.y)
    c0[4] = GodotReal.toC(a0.y.y)
    c0[5] = GodotReal.toC(a0.z.y)
    c0[6] = GodotReal.toC(a0.x.z)
    c0[7] = GodotReal.toC(a0.y.z)
    c0[8] = GodotReal.toC(a0.z.z)
    val types = allocArray<IntVar>(1)
    types[0] = PT_BASIS
    val ptrs = allocArray<COpaquePointerVar>(1)
    ptrs[0] = c0.reinterpret<CPointed>()
    kanama_ios_godot_ptrcall(
      methodBind.address(),
      instance.address(),
      types,
      ptrs,
      1,
      PT_VOID,
      null,
    )
    Unit
  }

fun ObjectCalls.ptrcallWithBasisArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Basis,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = allocArray<GodotRealVar>(9)
  c0[0] = GodotReal.toC(a0.x.x)
  c0[1] = GodotReal.toC(a0.y.x)
  c0[2] = GodotReal.toC(a0.z.x)
  c0[3] = GodotReal.toC(a0.x.y)
  c0[4] = GodotReal.toC(a0.y.y)
  c0[5] = GodotReal.toC(a0.z.y)
  c0[6] = GodotReal.toC(a0.x.z)
  c0[7] = GodotReal.toC(a0.y.z)
  c0[8] = GodotReal.toC(a0.z.z)
  val types = allocArray<IntVar>(1)
  types[0] = PT_BASIS
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithBoolAndDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Boolean,
  a1: Double,
) = memScoped {
  val c0 = alloc<ByteVar>()
  c0.value = if (a0) 1 else 0
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_BOOL
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithBoolAndIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Boolean,
  a1: Int,
) = memScoped {
  val c0 = alloc<ByteVar>()
  c0.value = if (a0) 1 else 0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_BOOL
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithBoolAndIntArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Boolean,
  a1: Int,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<ByteVar>()
  c0.value = if (a0) 1 else 0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_BOOL
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithBoolAndIntArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Boolean,
  a1: Int,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<ByteVar>()
  c0.value = if (a0) 1 else 0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_BOOL
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithBoolAndLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Boolean,
  a1: Long,
) = memScoped {
  val c0 = alloc<ByteVar>()
  c0.value = if (a0) 1 else 0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_BOOL
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithBoolArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Boolean,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<ByteVar>()
  c0.value = if (a0) 1 else 0
  val types = allocArray<IntVar>(1)
  types[0] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithBoolArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Boolean,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<ByteVar>()
  c0.value = if (a0) 1 else 0
  val types = allocArray<IntVar>(1)
  types[0] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithBoolArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Boolean,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<ByteVar>()
  c0.value = if (a0) 1 else 0
  val types = allocArray<IntVar>(1)
  types[0] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithBoolArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Boolean,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<ByteVar>()
  c0.value = if (a0) 1 else 0
  val types = allocArray<IntVar>(1)
  types[0] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithBoolArgRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Boolean,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<ByteVar>()
  c0.value = if (a0) 1 else 0
  val types = allocArray<IntVar>(1)
  types[0] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithBoolObjectArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Boolean,
  a1: MemorySegment,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<ByteVar>()
  c0.value = if (a0) 1 else 0
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_BOOL
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithBoolTwoDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Boolean,
  a1: Double,
  a2: Double,
) = memScoped {
  val c0 = alloc<ByteVar>()
  c0.value = if (a0) 1 else 0
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_BOOL
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithCallableArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0Object: MemorySegment,
  a0Method: String,
) = memScoped {
  val c0 = alloc<KanamaIosCallableArgDesc>()
  c0.object_handle = a0Object.address()
  c0.method = a0Method.cstr.ptr
  val types = allocArray<IntVar>(1)
  types[0] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithCallableArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0Object: MemorySegment,
  a0Method: String,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<KanamaIosCallableArgDesc>()
  c0.object_handle = a0Object.address()
  c0.method = a0Method.cstr.ptr
  val types = allocArray<IntVar>(1)
  types[0] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithCallableArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0Object: MemorySegment,
  a0Method: String,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<KanamaIosCallableArgDesc>()
  c0.object_handle = a0Object.address()
  c0.method = a0Method.cstr.ptr
  val types = allocArray<IntVar>(1)
  types[0] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithCallableBoolStringArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0Object: MemorySegment,
  a0Method: String,
  a1: Boolean,
  a2: String,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<KanamaIosCallableArgDesc>()
  c0.object_handle = a0Object.address()
  c0.method = a0Method.cstr.ptr
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_CALLABLE
  types[1] = PT_BOOL
  types[2] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithCallableIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0Object: MemorySegment,
  a0Method: String,
  a1: Int,
) = memScoped {
  val c0 = alloc<KanamaIosCallableArgDesc>()
  c0.object_handle = a0Object.address()
  c0.method = a0Method.cstr.ptr
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_CALLABLE
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithCallableLongArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0Object: MemorySegment,
  a0Method: String,
  a1: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<KanamaIosCallableArgDesc>()
  c0.object_handle = a0Object.address()
  c0.method = a0Method.cstr.ptr
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_CALLABLE
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithCallableTwoIntBoolStringArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0Object: MemorySegment,
  a0Method: String,
  a1: Int,
  a2: Int,
  a3: Boolean,
  a4: String,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<KanamaIosCallableArgDesc>()
  c0.object_handle = a0Object.address()
  c0.method = a0Method.cstr.ptr
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_CALLABLE
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_BOOL
  types[4] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = a4.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithDoubleAndBoolArgRetTransform2D(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Boolean,
): Transform2D = memScoped {
  val ret = allocArray<GodotRealVar>(6)
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_FLOAT64
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_TRANSFORM2D,
    ret,
  )
  Transform2D(
    Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1])),
    Vector2(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3])),
    Vector2(GodotReal.fromC(ret[4]), GodotReal.fromC(ret[5])),
  )
}

fun ObjectCalls.ptrcallWithDoubleAndBoolArgRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Boolean,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_FLOAT64
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithDoubleAndBoolArgRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Boolean,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_FLOAT64
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithDoubleAndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Boolean,
) = memScoped {
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_FLOAT64
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithDoubleAndColorArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Color,
) = memScoped {
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val types = allocArray<IntVar>(2)
  types[0] = PT_FLOAT64
  types[1] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithDoubleAndIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Int,
) = memScoped {
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_FLOAT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithDoubleAndTwoBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Boolean,
  a2: Boolean,
) = memScoped {
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_FLOAT64
  types[1] = PT_BOOL
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithDoubleAndTwoBoolArgsRetTransform3D(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Boolean,
  a2: Boolean,
): Transform3D = memScoped {
  val ret = allocArray<GodotRealVar>(12)
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_FLOAT64
  types[1] = PT_BOOL
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_TRANSFORM3D,
    ret,
  )
  Transform3D(
    Basis(
      Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[3]), GodotReal.fromC(ret[6])),
      Vector3(GodotReal.fromC(ret[1]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[7])),
      Vector3(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[5]), GodotReal.fromC(ret[8])),
    ),
    Vector3(GodotReal.fromC(ret[9]), GodotReal.fromC(ret[10]), GodotReal.fromC(ret[11])),
  )
}

fun ObjectCalls.ptrcallWithDoubleArgRetColor(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
): Color = memScoped {
  val ret = allocArray<FloatVar>(4)
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_COLOR, ret)
  Color(ret[0], ret[1], ret[2], ret[3])
}

fun ObjectCalls.ptrcallWithDoubleArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithDoubleArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithDoubleArgRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithDoubleArgRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithDoubleVector2TwoDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Vector2,
  a2: Double,
  a3: Double,
) = memScoped {
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_FLOAT64
  types[1] = PT_VECTOR2
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithFiveIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Int,
  a3: Int,
  a4: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val types = allocArray<IntVar>(5)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithFiveIntArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Int,
  a3: Int,
  a4: Int,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val types = allocArray<IntVar>(5)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithFloatArgRetFloat(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithFourDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Double,
  a2: Double,
  a3: Double,
) = memScoped {
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_FLOAT64
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithFourIntAndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Int,
  a3: Int,
  a4: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithFourIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Int,
  a3: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithFourIntArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Int,
  a3: Int,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithFourLongLongLongLongLongLongArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
  a2: Long,
  a3: Long,
  a4: Long,
  a5: Long,
  a6: Long,
  a7: Long,
  a8: Long,
  a9: Long,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<LongVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5
  val c6 = alloc<LongVar>()
  c6.value = a6
  val c7 = alloc<LongVar>()
  c7.value = a7
  val c8 = alloc<LongVar>()
  c8.value = a8
  val c9 = alloc<LongVar>()
  c9.value = a9
  val types = allocArray<IntVar>(10)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_INT64
  types[6] = PT_INT64
  types[7] = PT_INT64
  types[8] = PT_INT64
  types[9] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(10)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  ptrs[9] = c9.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    10,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithFourStringNameAndFloatArgRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: String,
  a3: String,
  a4: Double,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val types = allocArray<IntVar>(5)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_STRING_NAME
  types[3] = PT_STRING_NAME
  types[4] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = a3.cstr.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithFourVector2ArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Vector2,
  a2: Vector2,
  a3: Vector2,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val c3 = allocArray<GodotRealVar>(2)
  c3[0] = GodotReal.toC(a3.x)
  c3[1] = GodotReal.toC(a3.y)
  val types = allocArray<IntVar>(4)
  types[0] = PT_VECTOR2
  types[1] = PT_VECTOR2
  types[2] = PT_VECTOR2
  types[3] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithFourVector3ArgsRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3,
  a1: Vector3,
  a2: Vector3,
  a3: Vector3,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = allocArray<GodotRealVar>(3)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  c0[2] = GodotReal.toC(a0.z)
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val c2 = allocArray<GodotRealVar>(3)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  c2[2] = GodotReal.toC(a2.z)
  val c3 = allocArray<GodotRealVar>(3)
  c3[0] = GodotReal.toC(a3.x)
  c3[1] = GodotReal.toC(a3.y)
  c3[2] = GodotReal.toC(a3.z)
  val types = allocArray<IntVar>(4)
  types[0] = PT_VECTOR3
  types[1] = PT_VECTOR3
  types[2] = PT_VECTOR3
  types[3] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithIntAndBoolArgRetRect2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Boolean,
): Rect2 = memScoped {
  val ret = allocArray<GodotRealVar>(4)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_RECT2, ret)
  Rect2(
    Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1])),
    Vector2(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3])),
  )
}

fun ObjectCalls.ptrcallWithIntAndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndBoolArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Boolean,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithIntAndBoolArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Boolean,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithIntAndColorArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Color,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndDoubleArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndDoubleArgRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithIntAndDoubleArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithIntAndDoubleArgRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithIntAndDoubleArgRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithIntAndLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndLongArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Long,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithIntAndLongArgsRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Long,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithIntAndLongArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithIntAndLongArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Long,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithIntAndNodePathArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: NodePath,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_NODE_PATH
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.path.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndObjectArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: MemorySegment,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndObjectArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: MemorySegment,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithIntAndQuaternionArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Quaternion,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  c1[3] = GodotReal.toC(a1.w)
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_QUATERNION
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndRIDArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: RID,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndRect2Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Rect2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_RECT2
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndStringArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndStringArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: String,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithIntAndStringArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: String,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithIntAndStringArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: String,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithIntAndStringNameArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndStringNameArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: String,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithIntAndThreeBoolArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Boolean,
  a2: Boolean,
  a3: Boolean,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  types[2] = PT_BOOL
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithIntAndThreeDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
  a2: Double,
  a3: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndTransform2DArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Transform2D,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<GodotRealVar>(6)
  c1[0] = GodotReal.toC(a1.x.x)
  c1[1] = GodotReal.toC(a1.x.y)
  c1[2] = GodotReal.toC(a1.y.x)
  c1[3] = GodotReal.toC(a1.y.y)
  c1[4] = GodotReal.toC(a1.origin.x)
  c1[5] = GodotReal.toC(a1.origin.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_TRANSFORM2D
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndTransform3DArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Transform3D,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<GodotRealVar>(12)
  c1[0] = GodotReal.toC(a1.basis.x.x)
  c1[1] = GodotReal.toC(a1.basis.y.x)
  c1[2] = GodotReal.toC(a1.basis.z.x)
  c1[3] = GodotReal.toC(a1.basis.x.y)
  c1[4] = GodotReal.toC(a1.basis.y.y)
  c1[5] = GodotReal.toC(a1.basis.z.y)
  c1[6] = GodotReal.toC(a1.basis.x.z)
  c1[7] = GodotReal.toC(a1.basis.y.z)
  c1[8] = GodotReal.toC(a1.basis.z.z)
  c1[9] = GodotReal.toC(a1.origin.x)
  c1[10] = GodotReal.toC(a1.origin.y)
  c1[11] = GodotReal.toC(a1.origin.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_TRANSFORM3D
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndTwoDoubleArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
  a2: Double,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithIntAndUInt32Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndVector2Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndVector2iArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntAndVector2iArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithIntAndVector2iArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithIntAndVector3Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector3,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntArgRetBasis(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): Basis = memScoped {
  val ret = allocArray<GodotRealVar>(9)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_BASIS, ret)
  Basis(
    Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[3]), GodotReal.fromC(ret[6])),
    Vector3(GodotReal.fromC(ret[1]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[7])),
    Vector3(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[5]), GodotReal.fromC(ret[8])),
  )
}

fun ObjectCalls.ptrcallWithIntArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithIntArgRetColor(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): Color = memScoped {
  val ret = allocArray<FloatVar>(4)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_COLOR, ret)
  Color(ret[0], ret[1], ret[2], ret[3])
}

fun ObjectCalls.ptrcallWithIntArgRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithIntArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithIntArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithIntArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithIntArgRetQuaternion(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): Quaternion = memScoped {
  val ret = allocArray<GodotRealVar>(4)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_QUATERNION,
    ret,
  )
  Quaternion(
    GodotReal.fromC(ret[0]),
    GodotReal.fromC(ret[1]),
    GodotReal.fromC(ret[2]),
    GodotReal.fromC(ret[3]),
  )
}

fun ObjectCalls.ptrcallWithIntArgRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithIntArgRetRect2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): Rect2 = memScoped {
  val ret = allocArray<GodotRealVar>(4)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_RECT2, ret)
  Rect2(
    Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1])),
    Vector2(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3])),
  )
}

fun ObjectCalls.ptrcallWithIntArgRetRect2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): Rect2i = memScoped {
  val ret = allocArray<IntVar>(4)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_RECT2I, ret)
  Rect2i(Vector2i(ret[0], ret[1]), Vector2i(ret[2], ret[3]))
}

fun ObjectCalls.ptrcallWithIntArgRetTransform2D(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): Transform2D = memScoped {
  val ret = allocArray<GodotRealVar>(6)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_TRANSFORM2D,
    ret,
  )
  Transform2D(
    Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1])),
    Vector2(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3])),
    Vector2(GodotReal.fromC(ret[4]), GodotReal.fromC(ret[5])),
  )
}

fun ObjectCalls.ptrcallWithIntArgRetTransform3D(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): Transform3D = memScoped {
  val ret = allocArray<GodotRealVar>(12)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_TRANSFORM3D,
    ret,
  )
  Transform3D(
    Basis(
      Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[3]), GodotReal.fromC(ret[6])),
      Vector3(GodotReal.fromC(ret[1]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[7])),
      Vector3(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[5]), GodotReal.fromC(ret[8])),
    ),
    Vector3(GodotReal.fromC(ret[9]), GodotReal.fromC(ret[10]), GodotReal.fromC(ret[11])),
  )
}

fun ObjectCalls.ptrcallWithIntArgRetUInt32(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithIntArgRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithIntArgRetVector2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): Vector2i = memScoped {
  val ret = allocArray<IntVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR2I,
    ret,
  )
  Vector2i(ret[0], ret[1])
}

fun ObjectCalls.ptrcallWithIntArgRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithIntArgRetVector3i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
): Vector3i = memScoped {
  val ret = allocArray<IntVar>(3)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR3I,
    ret,
  )
  Vector3i(ret[0], ret[1], ret[2])
}

fun ObjectCalls.ptrcallWithIntBoolIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Boolean,
  a2: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntBoolIntBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Boolean,
  a2: Int,
  a3: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  types[2] = PT_INT64
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntBoolIntColorBoolIntColorTwoObjectBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Boolean,
  a2: Int,
  a3: Color,
  a4: Boolean,
  a5: Int,
  a6: Color,
  a7: MemorySegment,
  a8: MemorySegment,
  a9: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val c6 = allocArray<FloatVar>(4)
  c6[0] = a6.r
  c6[1] = a6.g
  c6[2] = a6.b
  c6[3] = a6.a
  val c7 = alloc<LongVar>()
  c7.value = a7.address()
  val c8 = alloc<LongVar>()
  c8.value = a8.address()
  val c9 = alloc<ByteVar>()
  c9.value = if (a9) 1 else 0
  val types = allocArray<IntVar>(10)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  types[2] = PT_INT64
  types[3] = PT_COLOR
  types[4] = PT_BOOL
  types[5] = PT_INT64
  types[6] = PT_COLOR
  types[7] = PT_OBJECT
  types[8] = PT_OBJECT
  types[9] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(10)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  ptrs[9] = c9.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 10, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntBoolNodePathIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Boolean,
  a2: NodePath,
  a3: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  types[2] = PT_NODE_PATH
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.path.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntBoolTwoIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Boolean,
  a2: Int,
  a3: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  types[2] = PT_INT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntCallableArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1Object: MemorySegment,
  a1Method: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<KanamaIosCallableArgDesc>()
  c1.object_handle = a1Object.address()
  c1.method = a1Method.cstr.ptr
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntColorArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Color,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithIntColorBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Color,
  a2: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_COLOR
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntDoubleBoolArgsRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
  a2: Boolean,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithIntDoubleBoolArgsRetQuaternion(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
  a2: Boolean,
): Quaternion = memScoped {
  val ret = allocArray<GodotRealVar>(4)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_QUATERNION,
    ret,
  )
  Quaternion(
    GodotReal.fromC(ret[0]),
    GodotReal.fromC(ret[1]),
    GodotReal.fromC(ret[2]),
    GodotReal.fromC(ret[3]),
  )
}

fun ObjectCalls.ptrcallWithIntDoubleBoolArgsRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
  a2: Boolean,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithIntDoubleLongTwoBoolArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
  a2: Long,
  a3: Boolean,
  a4: Boolean,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  types[2] = PT_INT64
  types[3] = PT_BOOL
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithIntDoubleObjectTwoDoubleArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
  a2: MemorySegment,
  a3: Double,
  a4: Double,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val types = allocArray<IntVar>(5)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  types[2] = PT_OBJECT
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithIntDoubleQuaternionArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
  a2: Quaternion,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = allocArray<GodotRealVar>(4)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  c2[2] = GodotReal.toC(a2.z)
  c2[3] = GodotReal.toC(a2.w)
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  types[2] = PT_QUATERNION
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithIntDoubleStringNameArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
  a2: String,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  types[2] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithIntDoubleTwoBoolDoubleLongTwoBoolArgsRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
  a2: Boolean,
  a3: Boolean,
  a4: Double,
  a5: Long,
  a6: Boolean,
  a7: Boolean,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5
  val c6 = alloc<ByteVar>()
  c6.value = if (a6) 1 else 0
  val c7 = alloc<ByteVar>()
  c7.value = if (a7) 1 else 0
  val types = allocArray<IntVar>(8)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  types[2] = PT_BOOL
  types[3] = PT_BOOL
  types[4] = PT_FLOAT64
  types[5] = PT_INT64
  types[6] = PT_BOOL
  types[7] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(8)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    8,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithIntDoubleVector3ArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
  a2: Vector3,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = allocArray<GodotRealVar>(3)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  c2[2] = GodotReal.toC(a2.z)
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  types[2] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithIntLongBoolStringArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Long,
  a2: Boolean,
  a3: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_BOOL
  types[3] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = a3.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntLongIntStringArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Long,
  a2: Int,
  a3: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = a3.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntObjectAndIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: MemorySegment,
  a2: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_OBJECT
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntObjectBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: MemorySegment,
  a2: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_OBJECT
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntObjectDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: MemorySegment,
  a2: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_OBJECT
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntObjectIntBoolTwoStringArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: MemorySegment,
  a2: Int,
  a3: Boolean,
  a4: String,
  a5: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(6)
  types[0] = PT_INT64
  types[1] = PT_OBJECT
  types[2] = PT_INT64
  types[3] = PT_BOOL
  types[4] = PT_STRING
  types[5] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = a4.cstr.ptr.reinterpret<CPointed>()
  ptrs[5] = a5.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntObjectStringNameArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: MemorySegment,
  a2: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_OBJECT
  types[2] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntRect2Rect2Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Rect2,
  a2: Rect2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val c2 = allocArray<GodotRealVar>(4)
  c2[0] = GodotReal.toC(a2.position.x)
  c2[1] = GodotReal.toC(a2.position.y)
  c2[2] = GodotReal.toC(a2.size.x)
  c2[3] = GodotReal.toC(a2.size.y)
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_RECT2
  types[2] = PT_RECT2
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntStringAndIntArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: String,
  a2: Int,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_STRING
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithIntStringObjectArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: String,
  a2: MemorySegment,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_STRING
  types[2] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithIntThreeDoubleBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
  a2: Double,
  a3: Double,
  a4: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntTransform2DDoubleBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Transform2D,
  a2: Double,
  a3: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<GodotRealVar>(6)
  c1[0] = GodotReal.toC(a1.x.x)
  c1[1] = GodotReal.toC(a1.x.y)
  c1[2] = GodotReal.toC(a1.y.x)
  c1[3] = GodotReal.toC(a1.y.y)
  c1[4] = GodotReal.toC(a1.origin.x)
  c1[5] = GodotReal.toC(a1.origin.y)
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_TRANSFORM2D
  types[2] = PT_FLOAT64
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntTransform3DDoubleBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Transform3D,
  a2: Double,
  a3: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<GodotRealVar>(12)
  c1[0] = GodotReal.toC(a1.basis.x.x)
  c1[1] = GodotReal.toC(a1.basis.y.x)
  c1[2] = GodotReal.toC(a1.basis.z.x)
  c1[3] = GodotReal.toC(a1.basis.x.y)
  c1[4] = GodotReal.toC(a1.basis.y.y)
  c1[5] = GodotReal.toC(a1.basis.z.y)
  c1[6] = GodotReal.toC(a1.basis.x.z)
  c1[7] = GodotReal.toC(a1.basis.y.z)
  c1[8] = GodotReal.toC(a1.basis.z.z)
  c1[9] = GodotReal.toC(a1.origin.x)
  c1[10] = GodotReal.toC(a1.origin.y)
  c1[11] = GodotReal.toC(a1.origin.z)
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_TRANSFORM3D
  types[2] = PT_FLOAT64
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntTwoBoolTwoIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Boolean,
  a2: Boolean,
  a3: Int,
  a4: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val types = allocArray<IntVar>(5)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  types[2] = PT_BOOL
  types[3] = PT_INT64
  types[4] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntTwoDoubleTwoVector2ArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Double,
  a2: Double,
  a3: Vector2,
  a4: Vector2,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = allocArray<GodotRealVar>(2)
  c3[0] = GodotReal.toC(a3.x)
  c3[1] = GodotReal.toC(a3.y)
  val c4 = allocArray<GodotRealVar>(2)
  c4[0] = GodotReal.toC(a4.x)
  c4[1] = GodotReal.toC(a4.y)
  val types = allocArray<IntVar>(5)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_VECTOR2
  types[4] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithIntTwoLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Long,
  a2: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Boolean,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Boolean,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Boolean,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetVector2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Boolean,
): Vector2i = memScoped {
  val ret = allocArray<IntVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR2I,
    ret,
  )
  Vector2i(ret[0], ret[1])
}

fun ObjectCalls.ptrcallWithIntVector2iAndIntArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntVector2iAndObjectArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: MemorySegment,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntVector2iIntArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Int,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithIntVector2iIntArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Int,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithIntVector2iIntArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Int,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithIntVector2iIntArgsRetRect2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Int,
): Rect2 = memScoped {
  val ret = allocArray<GodotRealVar>(4)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_RECT2, ret)
  Rect2(
    Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1])),
    Vector2(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3])),
  )
}

fun ObjectCalls.ptrcallWithIntVector2iIntArgsRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Int,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithIntVector2iIntObjectArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Int,
  a3: MemorySegment,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.address()
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  types[3] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntVector2iIntRect2Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Int,
  a3: Rect2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = allocArray<GodotRealVar>(4)
  c3[0] = GodotReal.toC(a3.position.x)
  c3[1] = GodotReal.toC(a3.position.y)
  c3[2] = GodotReal.toC(a3.size.x)
  c3[3] = GodotReal.toC(a3.size.y)
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  types[3] = PT_RECT2
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntVector2iIntVector2Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Int,
  a3: Vector2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = allocArray<GodotRealVar>(2)
  c3[0] = GodotReal.toC(a3.x)
  c3[1] = GodotReal.toC(a3.y)
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  types[3] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntVector2iIntVector2iArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Int,
  a3: Vector2i,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = allocArray<IntVar>(2)
  c3[0] = a3.x
  c3[1] = a3.y
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  types[3] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntVector2iIntVector2iIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Int,
  a3: Vector2i,
  a4: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = allocArray<IntVar>(2)
  c3[0] = a3.x
  c3[1] = a3.y
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val types = allocArray<IntVar>(5)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  types[3] = PT_VECTOR2I
  types[4] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntVector2iTwoIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Int,
  a3: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntVector2iTwoIntVector2iIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector2i,
  a2: Int,
  a3: Int,
  a4: Vector2i,
  a5: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = allocArray<IntVar>(2)
  c4[0] = a4.x
  c4[1] = a4.y
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val types = allocArray<IntVar>(6)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_VECTOR2I
  types[5] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithIntVector3ArgsRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Vector3,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithLongAndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndColorArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Color,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndDoubleArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndFourIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
  a2: Int,
  a3: Int,
  a4: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val types = allocArray<IntVar>(5)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndFourIntArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
  a2: Int,
  a3: Int,
  a4: Int,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val types = allocArray<IntVar>(5)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithLongAndFourIntArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
  a2: Int,
  a3: Int,
  a4: Int,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val types = allocArray<IntVar>(5)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithLongAndIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndIntArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithLongAndIntArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithLongAndIntArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithLongAndIntArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithLongAndIntArgsRetUInt32(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithLongAndIntArgsRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithLongAndNodePathArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: NodePath,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_NODE_PATH
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.path.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndObjectArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: MemorySegment,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndRIDArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: RID,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndStringArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndThreeStringNameArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: String,
  a2: String,
  a3: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_STRING_NAME
  types[2] = PT_STRING_NAME
  types[3] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = a3.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndThreeUInt32Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
  a2: Long,
  a3: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndTransform3DArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Transform3D,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = allocArray<GodotRealVar>(12)
  c1[0] = GodotReal.toC(a1.basis.x.x)
  c1[1] = GodotReal.toC(a1.basis.y.x)
  c1[2] = GodotReal.toC(a1.basis.z.x)
  c1[3] = GodotReal.toC(a1.basis.x.y)
  c1[4] = GodotReal.toC(a1.basis.y.y)
  c1[5] = GodotReal.toC(a1.basis.z.y)
  c1[6] = GodotReal.toC(a1.basis.x.z)
  c1[7] = GodotReal.toC(a1.basis.y.z)
  c1[8] = GodotReal.toC(a1.basis.z.z)
  c1[9] = GodotReal.toC(a1.origin.x)
  c1[10] = GodotReal.toC(a1.origin.y)
  c1[11] = GodotReal.toC(a1.origin.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_TRANSFORM3D
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndTwoIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
  a2: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndTwoIntArgsRetUInt32(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
  a2: Int,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithLongAndTwoObjectArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: MemorySegment,
  a2: MemorySegment,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_OBJECT
  types[2] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndTwoStringArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: String,
  a2: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_STRING
  types[2] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndTwoStringNameArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: String,
  a2: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_STRING_NAME
  types[2] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndTwoStringNameArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: String,
  a2: String,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_STRING_NAME
  types[2] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithLongAndUInt32ArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithLongAndUInt32ArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithLongAndVector2Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Vector2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongAndVector3Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Vector3,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithLongArgRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithLongArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithLongArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithLongArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithLongArgRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithLongArgRetTransform3D(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Transform3D = memScoped {
  val ret = allocArray<GodotRealVar>(12)
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_TRANSFORM3D,
    ret,
  )
  Transform3D(
    Basis(
      Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[3]), GodotReal.fromC(ret[6])),
      Vector3(GodotReal.fromC(ret[1]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[7])),
      Vector3(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[5]), GodotReal.fromC(ret[8])),
    ),
    Vector3(GodotReal.fromC(ret[9]), GodotReal.fromC(ret[10]), GodotReal.fromC(ret[11])),
  )
}

fun ObjectCalls.ptrcallWithLongArgRetUInt32(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithLongArgRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithLongArgRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithLongBoolDoubleIntAndTwoDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Boolean,
  a2: Double,
  a3: Int,
  a4: Double,
  a5: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  types[2] = PT_FLOAT64
  types[3] = PT_INT64
  types[4] = PT_FLOAT64
  types[5] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongBoolIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Boolean,
  a2: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongBoolRIDThreeUInt32Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Boolean,
  a2: RID,
  a3: Long,
  a4: Long,
  a5: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<LongVar>()
  c2.value = a2.value
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<LongVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  types[2] = PT_RID
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongBoolTwoUInt32Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Boolean,
  a2: Long,
  a3: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  types[2] = PT_INT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongCallableArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1Object: MemorySegment,
  a1Method: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<KanamaIosCallableArgDesc>()
  c1.object_handle = a1Object.address()
  c1.method = a1Method.cstr.ptr
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongCallableArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1Object: MemorySegment,
  a1Method: String,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<KanamaIosCallableArgDesc>()
  c1.object_handle = a1Object.address()
  c1.method = a1Method.cstr.ptr
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithLongDoubleTwoBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Double,
  a2: Boolean,
  a3: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  types[2] = PT_BOOL
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongIntStringNameArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
  a2: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongIntVector2Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
  a2: Vector2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongObjectVector2IntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: MemorySegment,
  a2: Vector2,
  a3: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_OBJECT
  types[2] = PT_VECTOR2
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongRIDAndUInt32Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: RID,
  a2: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_RID
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongRIDLongArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: RID,
  a2: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_RID
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithLongRect2Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Rect2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_RECT2
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongTwoDoubleAndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Double,
  a2: Double,
  a3: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongUInt32RIDThreeUInt32Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
  a2: RID,
  a3: Long,
  a4: Long,
  a5: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2.value
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<LongVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_RID
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongVector2AndDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Vector2,
  a2: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithLongVector3AndDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Vector3,
  a2: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_VECTOR3
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithNodePathAndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: NodePath,
  a1: Boolean,
) = memScoped {
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_NODE_PATH
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.path.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithNodePathAndIntArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: NodePath,
  a1: Int,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_NODE_PATH
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.path.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithNodePathAndLongArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: NodePath,
  a1: Long,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_NODE_PATH
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.path.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithNodePathAndLongArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: NodePath,
  a1: Long,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_NODE_PATH
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.path.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithNodePathArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: NodePath,
) = memScoped {
  val types = allocArray<IntVar>(1)
  types[0] = PT_NODE_PATH
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.path.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithNodePathArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: NodePath,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val types = allocArray<IntVar>(1)
  types[0] = PT_NODE_PATH
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.path.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithNodePathArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: NodePath,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val types = allocArray<IntVar>(1)
  types[0] = PT_NODE_PATH
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.path.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithNodePathArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: NodePath,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val types = allocArray<IntVar>(1)
  types[0] = PT_NODE_PATH
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.path.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithNodePathArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: NodePath,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val types = allocArray<IntVar>(1)
  types[0] = PT_NODE_PATH
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.path.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithNodePathRect2TwoIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: NodePath,
  a1: Rect2,
  a2: Int,
  a3: Int,
) = memScoped {
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_NODE_PATH
  types[1] = PT_RECT2
  types[2] = PT_INT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.path.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithNodePathStringNameArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: NodePath,
  a1: String,
) = memScoped {
  val types = allocArray<IntVar>(2)
  types[0] = PT_NODE_PATH
  types[1] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.path.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectAndBoolArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectAndBoolArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Boolean,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithObjectAndBoolArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Boolean,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithObjectAndBoolArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Boolean,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithObjectAndDoubleArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectAndIntArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectAndIntArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Int,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithObjectAndIntArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Int,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithObjectAndIntArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Int,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithObjectAndLongArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithObjectAndLongArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Long,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithObjectAndRect2Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Rect2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_RECT2
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectAndStringArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectAndStringArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: String,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithObjectAndStringNameArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectAndTransform3DArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Transform3D,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<GodotRealVar>(12)
  c1[0] = GodotReal.toC(a1.basis.x.x)
  c1[1] = GodotReal.toC(a1.basis.y.x)
  c1[2] = GodotReal.toC(a1.basis.z.x)
  c1[3] = GodotReal.toC(a1.basis.x.y)
  c1[4] = GodotReal.toC(a1.basis.y.y)
  c1[5] = GodotReal.toC(a1.basis.z.y)
  c1[6] = GodotReal.toC(a1.basis.x.z)
  c1[7] = GodotReal.toC(a1.basis.y.z)
  c1[8] = GodotReal.toC(a1.basis.z.z)
  c1[9] = GodotReal.toC(a1.origin.x)
  c1[10] = GodotReal.toC(a1.origin.y)
  c1[11] = GodotReal.toC(a1.origin.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_TRANSFORM3D
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectAndTwoBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Boolean,
  a2: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_BOOL
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectAndTwoIntArgsRetRect2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Int,
  a2: Int,
): Rect2 = memScoped {
  val ret = allocArray<GodotRealVar>(4)
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_RECT2, ret)
  Rect2(
    Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1])),
    Vector2(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3])),
  )
}

fun ObjectCalls.ptrcallWithObjectAndVector2iArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Vector2i,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val types = allocArray<IntVar>(1)
  types[0] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithObjectArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val types = allocArray<IntVar>(1)
  types[0] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithObjectArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val types = allocArray<IntVar>(1)
  types[0] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithObjectArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val types = allocArray<IntVar>(1)
  types[0] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithObjectArgRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val types = allocArray<IntVar>(1)
  types[0] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithObjectArgRetTransform2D(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
): Transform2D = memScoped {
  val ret = allocArray<GodotRealVar>(6)
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val types = allocArray<IntVar>(1)
  types[0] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_TRANSFORM2D,
    ret,
  )
  Transform2D(
    Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1])),
    Vector2(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3])),
    Vector2(GodotReal.fromC(ret[4]), GodotReal.fromC(ret[5])),
  )
}

fun ObjectCalls.ptrcallWithObjectArgRetUInt32(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val types = allocArray<IntVar>(1)
  types[0] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithObjectBoolLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Boolean,
  a2: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_BOOL
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectBoolStringLongArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Boolean,
  a2: String,
  a3: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c3 = alloc<LongVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_BOOL
  types[2] = PT_STRING
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithObjectBoolStringLongBoolArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Boolean,
  a2: String,
  a3: Long,
  a4: Boolean,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_OBJECT
  types[1] = PT_BOOL
  types[2] = PT_STRING
  types[3] = PT_INT64
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithObjectBoolTwoCallableArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Boolean,
  a2Object: MemorySegment,
  a2Method: String,
  a3Object: MemorySegment,
  a3Method: String,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<KanamaIosCallableArgDesc>()
  c2.object_handle = a2Object.address()
  c2.method = a2Method.cstr.ptr
  val c3 = alloc<KanamaIosCallableArgDesc>()
  c3.object_handle = a3Object.address()
  c3.method = a3Method.cstr.ptr
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_BOOL
  types[2] = PT_CALLABLE
  types[3] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithObjectCallableArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1Object: MemorySegment,
  a1Method: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<KanamaIosCallableArgDesc>()
  c1.object_handle = a1Object.address()
  c1.method = a1Method.cstr.ptr
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectCallableStringArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1Object: MemorySegment,
  a1Method: String,
  a2: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<KanamaIosCallableArgDesc>()
  c1.object_handle = a1Object.address()
  c1.method = a1Method.cstr.ptr
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_CALLABLE
  types[2] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectColorLongBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Color,
  a2: Long,
  a3: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_COLOR
  types[2] = PT_INT64
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectColorTwoBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Color,
  a2: Boolean,
  a3: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_COLOR
  types[2] = PT_BOOL
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectDoubleColorArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Double,
  a2: Color,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_FLOAT64
  types[2] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectDoubleIntStringNameArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Double,
  a2: Int,
  a3: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_FLOAT64
  types[2] = PT_INT64
  types[3] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = a3.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectDoubleTwoBoolArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Double,
  a2: Boolean,
  a3: Boolean,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_FLOAT64
  types[2] = PT_BOOL
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithObjectIntBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Int,
  a2: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_INT64
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectIntStringArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Int,
  a2: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_INT64
  types[2] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectIntTransform3DArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Int,
  a2: Transform3D,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = allocArray<GodotRealVar>(12)
  c2[0] = GodotReal.toC(a2.basis.x.x)
  c2[1] = GodotReal.toC(a2.basis.y.x)
  c2[2] = GodotReal.toC(a2.basis.z.x)
  c2[3] = GodotReal.toC(a2.basis.x.y)
  c2[4] = GodotReal.toC(a2.basis.y.y)
  c2[5] = GodotReal.toC(a2.basis.z.y)
  c2[6] = GodotReal.toC(a2.basis.x.z)
  c2[7] = GodotReal.toC(a2.basis.y.z)
  c2[8] = GodotReal.toC(a2.basis.z.z)
  c2[9] = GodotReal.toC(a2.origin.x)
  c2[10] = GodotReal.toC(a2.origin.y)
  c2[11] = GodotReal.toC(a2.origin.z)
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_INT64
  types[2] = PT_TRANSFORM3D
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectIntTwoBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Int,
  a2: Boolean,
  a3: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_INT64
  types[2] = PT_BOOL
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectLongAndVector2Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Long,
  a2: Vector2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_INT64
  types[2] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectLongBoolDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Long,
  a2: Boolean,
  a3: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_INT64
  types[2] = PT_BOOL
  types[3] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectLongStringLongStringUInt32BoolArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Long,
  a2: String,
  a3: Long,
  a4: String,
  a5: Long,
  a6: Boolean,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c5 = alloc<LongVar>()
  c5.value = a5
  val c6 = alloc<ByteVar>()
  c6.value = if (a6) 1 else 0
  val types = allocArray<IntVar>(7)
  types[0] = PT_OBJECT
  types[1] = PT_INT64
  types[2] = PT_STRING
  types[3] = PT_INT64
  types[4] = PT_STRING
  types[5] = PT_INT64
  types[6] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = a4.cstr.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    7,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithObjectNodePathObjectIntArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: NodePath,
  a2: MemorySegment,
  a3: Int,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_NODE_PATH
  types[2] = PT_OBJECT
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.path.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithObjectObjectIntBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: MemorySegment,
  a2: Int,
  a3: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_OBJECT
  types[2] = PT_INT64
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectObjectIntTwoBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: MemorySegment,
  a2: Int,
  a3: Boolean,
  a4: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_OBJECT
  types[1] = PT_OBJECT
  types[2] = PT_INT64
  types[3] = PT_BOOL
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectRIDArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: RID,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithObjectRIDCallableArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: RID,
  a2Object: MemorySegment,
  a2Method: String,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = alloc<KanamaIosCallableArgDesc>()
  c2.object_handle = a2Object.address()
  c2.method = a2Method.cstr.ptr
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_RID
  types[2] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithObjectRIDThreeUInt32LongArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: RID,
  a2: Long,
  a3: Long,
  a4: Long,
  a5: Long,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<LongVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_OBJECT
  types[1] = PT_RID
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    6,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithObjectRect2BoolColorBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Rect2,
  a2: Boolean,
  a3: Color,
  a4: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_OBJECT
  types[1] = PT_RECT2
  types[2] = PT_BOOL
  types[3] = PT_COLOR
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectStringAndIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: String,
  a2: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_STRING
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectStringAndObjectArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: String,
  a2: MemorySegment,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_STRING
  types[2] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithObjectStringArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: String,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithObjectStringArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: String,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithObjectStringBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: String,
  a2: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_STRING
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectStringCallableArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: String,
  a2Object: MemorySegment,
  a2Method: String,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c2 = alloc<KanamaIosCallableArgDesc>()
  c2.object_handle = a2Object.address()
  c2.method = a2Method.cstr.ptr
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_STRING
  types[2] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithObjectStringIntLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: String,
  a2: Int,
  a3: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_STRING
  types[2] = PT_INT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectStringLongArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: String,
  a2: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_STRING
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithObjectStringNameAndBoolArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: String,
  a2: Boolean,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_STRING_NAME
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithObjectStringObjectArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: String,
  a2: MemorySegment,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_STRING
  types[2] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithObjectThreeDoubleLongStringNameArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Double,
  a2: Double,
  a3: Double,
  a4: Long,
  a5: String,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<LongVar>()
  c4.value = a4
  val types = allocArray<IntVar>(6)
  types[0] = PT_OBJECT
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  types[4] = PT_INT64
  types[5] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = a5.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    6,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithObjectThreeStringArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: String,
  a2: String,
  a3: String,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_STRING
  types[2] = PT_STRING
  types[3] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = a3.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithObjectTwoDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Double,
  a2: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectTwoIntArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Int,
  a2: Int,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithObjectTwoIntBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Int,
  a2: Int,
  a3: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectTwoRect2AndColorArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Rect2,
  a2: Rect2,
  a3: Color,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val c2 = allocArray<GodotRealVar>(4)
  c2[0] = GodotReal.toC(a2.position.x)
  c2[1] = GodotReal.toC(a2.position.y)
  c2[2] = GodotReal.toC(a2.size.x)
  c2[3] = GodotReal.toC(a2.size.y)
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_RECT2
  types[2] = PT_RECT2
  types[3] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectTwoRect2ColorThreeDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Rect2,
  a2: Rect2,
  a3: Color,
  a4: Double,
  a5: Double,
  a6: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val c2 = allocArray<GodotRealVar>(4)
  c2[0] = GodotReal.toC(a2.position.x)
  c2[1] = GodotReal.toC(a2.position.y)
  c2[2] = GodotReal.toC(a2.size.x)
  c2[3] = GodotReal.toC(a2.size.y)
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val c6 = alloc<DoubleVar>()
  c6.value = a6
  val types = allocArray<IntVar>(7)
  types[0] = PT_OBJECT
  types[1] = PT_RECT2
  types[2] = PT_RECT2
  types[3] = PT_COLOR
  types[4] = PT_FLOAT64
  types[5] = PT_FLOAT64
  types[6] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 7, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectTwoRect2ColorTwoBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Rect2,
  a2: Rect2,
  a3: Color,
  a4: Boolean,
  a5: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val c2 = allocArray<GodotRealVar>(4)
  c2[0] = GodotReal.toC(a2.position.x)
  c2[1] = GodotReal.toC(a2.position.y)
  c2[2] = GodotReal.toC(a2.size.x)
  c2[3] = GodotReal.toC(a2.size.y)
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val c5 = alloc<ByteVar>()
  c5.value = if (a5) 1 else 0
  val types = allocArray<IntVar>(6)
  types[0] = PT_OBJECT
  types[1] = PT_RECT2
  types[2] = PT_RECT2
  types[3] = PT_COLOR
  types[4] = PT_BOOL
  types[5] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectVector2AndColorArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Vector2,
  a2: Color,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_VECTOR2
  types[2] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectVector2IntStringNameArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Vector2,
  a2: Int,
  a3: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_VECTOR2
  types[2] = PT_INT64
  types[3] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = a3.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectVector2StringIntColorDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Vector2,
  a2: String,
  a3: Int,
  a4: Color,
  a5: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = allocArray<FloatVar>(4)
  c4[0] = a4.r
  c4[1] = a4.g
  c4[2] = a4.b
  c4[3] = a4.a
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_OBJECT
  types[1] = PT_VECTOR2
  types[2] = PT_STRING
  types[3] = PT_INT64
  types[4] = PT_COLOR
  types[5] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectVector2StringLongDoubleIntColorThreeLongDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Vector2,
  a2: String,
  a3: Long,
  a4: Double,
  a5: Int,
  a6: Color,
  a7: Long,
  a8: Long,
  a9: Long,
  a10: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val c6 = allocArray<FloatVar>(4)
  c6[0] = a6.r
  c6[1] = a6.g
  c6[2] = a6.b
  c6[3] = a6.a
  val c7 = alloc<LongVar>()
  c7.value = a7
  val c8 = alloc<LongVar>()
  c8.value = a8
  val c9 = alloc<LongVar>()
  c9.value = a9
  val c10 = alloc<DoubleVar>()
  c10.value = a10
  val types = allocArray<IntVar>(11)
  types[0] = PT_OBJECT
  types[1] = PT_VECTOR2
  types[2] = PT_STRING
  types[3] = PT_INT64
  types[4] = PT_FLOAT64
  types[5] = PT_INT64
  types[6] = PT_COLOR
  types[7] = PT_INT64
  types[8] = PT_INT64
  types[9] = PT_INT64
  types[10] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(11)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  ptrs[9] = c9.ptr.reinterpret<CPointed>()
  ptrs[10] = c10.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 11, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectVector2StringLongDoubleThreeIntColorFourLongDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Vector2,
  a2: String,
  a3: Long,
  a4: Double,
  a5: Int,
  a6: Int,
  a7: Int,
  a8: Color,
  a9: Long,
  a10: Long,
  a11: Long,
  a12: Long,
  a13: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val c6 = alloc<LongVar>()
  c6.value = a6.toLong()
  val c7 = alloc<LongVar>()
  c7.value = a7.toLong()
  val c8 = allocArray<FloatVar>(4)
  c8[0] = a8.r
  c8[1] = a8.g
  c8[2] = a8.b
  c8[3] = a8.a
  val c9 = alloc<LongVar>()
  c9.value = a9
  val c10 = alloc<LongVar>()
  c10.value = a10
  val c11 = alloc<LongVar>()
  c11.value = a11
  val c12 = alloc<LongVar>()
  c12.value = a12
  val c13 = alloc<DoubleVar>()
  c13.value = a13
  val types = allocArray<IntVar>(14)
  types[0] = PT_OBJECT
  types[1] = PT_VECTOR2
  types[2] = PT_STRING
  types[3] = PT_INT64
  types[4] = PT_FLOAT64
  types[5] = PT_INT64
  types[6] = PT_INT64
  types[7] = PT_INT64
  types[8] = PT_COLOR
  types[9] = PT_INT64
  types[10] = PT_INT64
  types[11] = PT_INT64
  types[12] = PT_INT64
  types[13] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(14)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.reinterpret<CPointed>()
  ptrs[9] = c9.ptr.reinterpret<CPointed>()
  ptrs[10] = c10.ptr.reinterpret<CPointed>()
  ptrs[11] = c11.ptr.reinterpret<CPointed>()
  ptrs[12] = c12.ptr.reinterpret<CPointed>()
  ptrs[13] = c13.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 14, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectVector2StringLongDoubleTwoIntColorFourLongDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Vector2,
  a2: String,
  a3: Long,
  a4: Double,
  a5: Int,
  a6: Int,
  a7: Color,
  a8: Long,
  a9: Long,
  a10: Long,
  a11: Long,
  a12: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val c6 = alloc<LongVar>()
  c6.value = a6.toLong()
  val c7 = allocArray<FloatVar>(4)
  c7[0] = a7.r
  c7[1] = a7.g
  c7[2] = a7.b
  c7[3] = a7.a
  val c8 = alloc<LongVar>()
  c8.value = a8
  val c9 = alloc<LongVar>()
  c9.value = a9
  val c10 = alloc<LongVar>()
  c10.value = a10
  val c11 = alloc<LongVar>()
  c11.value = a11
  val c12 = alloc<DoubleVar>()
  c12.value = a12
  val types = allocArray<IntVar>(13)
  types[0] = PT_OBJECT
  types[1] = PT_VECTOR2
  types[2] = PT_STRING
  types[3] = PT_INT64
  types[4] = PT_FLOAT64
  types[5] = PT_INT64
  types[6] = PT_INT64
  types[7] = PT_COLOR
  types[8] = PT_INT64
  types[9] = PT_INT64
  types[10] = PT_INT64
  types[11] = PT_INT64
  types[12] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(13)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  ptrs[9] = c9.ptr.reinterpret<CPointed>()
  ptrs[10] = c10.ptr.reinterpret<CPointed>()
  ptrs[11] = c11.ptr.reinterpret<CPointed>()
  ptrs[12] = c12.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 13, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectVector2StringLongDoubleTwoIntColorThreeLongDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Vector2,
  a2: String,
  a3: Long,
  a4: Double,
  a5: Int,
  a6: Int,
  a7: Color,
  a8: Long,
  a9: Long,
  a10: Long,
  a11: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val c6 = alloc<LongVar>()
  c6.value = a6.toLong()
  val c7 = allocArray<FloatVar>(4)
  c7[0] = a7.r
  c7[1] = a7.g
  c7[2] = a7.b
  c7[3] = a7.a
  val c8 = alloc<LongVar>()
  c8.value = a8
  val c9 = alloc<LongVar>()
  c9.value = a9
  val c10 = alloc<LongVar>()
  c10.value = a10
  val c11 = alloc<DoubleVar>()
  c11.value = a11
  val types = allocArray<IntVar>(12)
  types[0] = PT_OBJECT
  types[1] = PT_VECTOR2
  types[2] = PT_STRING
  types[3] = PT_INT64
  types[4] = PT_FLOAT64
  types[5] = PT_INT64
  types[6] = PT_INT64
  types[7] = PT_COLOR
  types[8] = PT_INT64
  types[9] = PT_INT64
  types[10] = PT_INT64
  types[11] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(12)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  ptrs[9] = c9.ptr.reinterpret<CPointed>()
  ptrs[10] = c10.ptr.reinterpret<CPointed>()
  ptrs[11] = c11.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 12, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectVector2StringTwoIntColorDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Vector2,
  a2: String,
  a3: Int,
  a4: Int,
  a5: Color,
  a6: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val c5 = allocArray<FloatVar>(4)
  c5[0] = a5.r
  c5[1] = a5.g
  c5[2] = a5.b
  c5[3] = a5.a
  val c6 = alloc<DoubleVar>()
  c6.value = a6
  val types = allocArray<IntVar>(7)
  types[0] = PT_OBJECT
  types[1] = PT_VECTOR2
  types[2] = PT_STRING
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_COLOR
  types[6] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 7, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithObjectVector2iAndDoubleArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: Vector2i,
  a2: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_VECTOR2I
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithQuaternionArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Quaternion,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(4)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  c0[2] = GodotReal.toC(a0.z)
  c0[3] = GodotReal.toC(a0.w)
  val types = allocArray<IntVar>(1)
  types[0] = PT_QUATERNION
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndAABBArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: AABB,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(6)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.position.z)
  c1[3] = GodotReal.toC(a1.size.x)
  c1[4] = GodotReal.toC(a1.size.y)
  c1[5] = GodotReal.toC(a1.size.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_AABB
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndBasisArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Basis,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(9)
  c1[0] = GodotReal.toC(a1.x.x)
  c1[1] = GodotReal.toC(a1.y.x)
  c1[2] = GodotReal.toC(a1.z.x)
  c1[3] = GodotReal.toC(a1.x.y)
  c1[4] = GodotReal.toC(a1.y.y)
  c1[5] = GodotReal.toC(a1.z.y)
  c1[6] = GodotReal.toC(a1.x.z)
  c1[7] = GodotReal.toC(a1.y.z)
  c1[8] = GodotReal.toC(a1.z.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_BASIS
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndBoolArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndBoolArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDAndBoolArgRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithRIDAndColorArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Color,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndDoubleArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndDoubleArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Double,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDAndIntArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndIntArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithRIDAndIntArgRetColor(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
): Color = memScoped {
  val ret = allocArray<FloatVar>(4)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_COLOR, ret)
  Color(ret[0], ret[1], ret[2], ret[3])
}

fun ObjectCalls.ptrcallWithRIDAndIntArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithRIDAndIntArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDAndIntArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithRIDAndIntArgRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithRIDAndIntArgRetTransform2D(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
): Transform2D = memScoped {
  val ret = allocArray<GodotRealVar>(6)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_TRANSFORM2D,
    ret,
  )
  Transform2D(
    Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1])),
    Vector2(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3])),
    Vector2(GodotReal.fromC(ret[4]), GodotReal.fromC(ret[5])),
  )
}

fun ObjectCalls.ptrcallWithRIDAndIntArgRetTransform3D(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
): Transform3D = memScoped {
  val ret = allocArray<GodotRealVar>(12)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_TRANSFORM3D,
    ret,
  )
  Transform3D(
    Basis(
      Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[3]), GodotReal.fromC(ret[6])),
      Vector3(GodotReal.fromC(ret[1]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[7])),
      Vector3(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[5]), GodotReal.fromC(ret[8])),
    ),
    Vector3(GodotReal.fromC(ret[9]), GodotReal.fromC(ret[10]), GodotReal.fromC(ret[11])),
  )
}

fun ObjectCalls.ptrcallWithRIDAndIntArgRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithRIDAndIntArgRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithRIDAndLongArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndLongArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDAndLongArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithRIDAndLongArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDAndLongArgRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithRIDAndLongArgRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithRIDAndLongArgRetVector2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
): Vector2i = memScoped {
  val ret = allocArray<IntVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR2I,
    ret,
  )
  Vector2i(ret[0], ret[1])
}

fun ObjectCalls.ptrcallWithRIDAndObjectArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: MemorySegment,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndRect2Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Rect2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_RECT2
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndStringArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndStringArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: String,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithRIDAndStringArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: String,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithRIDAndThreeBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
  a2: Boolean,
  a3: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_BOOL
  types[2] = PT_BOOL
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndThreeDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Double,
  a2: Double,
  a3: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndThreeIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
  a2: Int,
  a3: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndThreeLongArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Long,
  a3: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDAndTransform2DArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Transform2D,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(6)
  c1[0] = GodotReal.toC(a1.x.x)
  c1[1] = GodotReal.toC(a1.x.y)
  c1[2] = GodotReal.toC(a1.y.x)
  c1[3] = GodotReal.toC(a1.y.y)
  c1[4] = GodotReal.toC(a1.origin.x)
  c1[5] = GodotReal.toC(a1.origin.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_TRANSFORM2D
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndTransform3DArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Transform3D,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(12)
  c1[0] = GodotReal.toC(a1.basis.x.x)
  c1[1] = GodotReal.toC(a1.basis.y.x)
  c1[2] = GodotReal.toC(a1.basis.z.x)
  c1[3] = GodotReal.toC(a1.basis.x.y)
  c1[4] = GodotReal.toC(a1.basis.y.y)
  c1[5] = GodotReal.toC(a1.basis.z.y)
  c1[6] = GodotReal.toC(a1.basis.x.z)
  c1[7] = GodotReal.toC(a1.basis.y.z)
  c1[8] = GodotReal.toC(a1.basis.z.z)
  c1[9] = GodotReal.toC(a1.origin.x)
  c1[10] = GodotReal.toC(a1.origin.y)
  c1[11] = GodotReal.toC(a1.origin.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_TRANSFORM3D
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Double,
  a2: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndTwoIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
  a2: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndTwoIntArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
  a2: Int,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithRIDAndTwoLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Long,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Long,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Long,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithRIDAndTwoObjectArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: MemorySegment,
  a2: MemorySegment,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_OBJECT
  types[2] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithRIDAndTwoUInt32ArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDAndTwoUInt32ArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Long,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithRIDAndTwoVector2Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: Vector2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndTwoVector3Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector3,
  a2: Vector3,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val c2 = allocArray<GodotRealVar>(3)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  c2[2] = GodotReal.toC(a2.z)
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_VECTOR3
  types[2] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndUInt32Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndUInt32ArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithRIDAndUInt32ArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDAndUInt32ArgRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithRIDAndUInt32ArgRetTransform3D(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
): Transform3D = memScoped {
  val ret = allocArray<GodotRealVar>(12)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_TRANSFORM3D,
    ret,
  )
  Transform3D(
    Basis(
      Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[3]), GodotReal.fromC(ret[6])),
      Vector3(GodotReal.fromC(ret[1]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[7])),
      Vector3(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[5]), GodotReal.fromC(ret[8])),
    ),
    Vector3(GodotReal.fromC(ret[9]), GodotReal.fromC(ret[10]), GodotReal.fromC(ret[11])),
  )
}

fun ObjectCalls.ptrcallWithRIDAndVector2Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndVector2ArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithRIDAndVector2ArgRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithRIDAndVector2ArgRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithRIDAndVector2iArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2i,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndVector2iArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2i,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDAndVector3Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector3,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDAndVector3ArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector3,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithRIDAndVector3ArgRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector3,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithRIDAndVector3ArgRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector3,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithRIDArg(methodBind: MemorySegment, instance: MemorySegment, a0: RID) =
  memScoped {
    val c0 = alloc<LongVar>()
    c0.value = a0.value
    val types = allocArray<IntVar>(1)
    types[0] = PT_RID
    val ptrs = allocArray<COpaquePointerVar>(1)
    ptrs[0] = c0.ptr.reinterpret<CPointed>()
    kanama_ios_godot_ptrcall(
      methodBind.address(),
      instance.address(),
      types,
      ptrs,
      1,
      PT_VOID,
      null,
    )
    Unit
  }

fun ObjectCalls.ptrcallWithRIDArgRetAABB(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
): AABB = memScoped {
  val ret = allocArray<GodotRealVar>(6)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(1)
  types[0] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_AABB, ret)
  AABB(
    Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2])),
    Vector3(GodotReal.fromC(ret[3]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[5])),
  )
}

fun ObjectCalls.ptrcallWithRIDArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(1)
  types[0] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithRIDArgRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(1)
  types[0] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(1)
  types[0] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithRIDArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(1)
  types[0] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(1)
  types[0] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithRIDArgRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(1)
  types[0] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithRIDArgRetRect2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
): Rect2 = memScoped {
  val ret = allocArray<GodotRealVar>(4)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(1)
  types[0] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_RECT2, ret)
  Rect2(
    Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1])),
    Vector2(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3])),
  )
}

fun ObjectCalls.ptrcallWithRIDArgRetTransform2D(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
): Transform2D = memScoped {
  val ret = allocArray<GodotRealVar>(6)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(1)
  types[0] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_TRANSFORM2D,
    ret,
  )
  Transform2D(
    Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1])),
    Vector2(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3])),
    Vector2(GodotReal.fromC(ret[4]), GodotReal.fromC(ret[5])),
  )
}

fun ObjectCalls.ptrcallWithRIDArgRetTransform3D(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
): Transform3D = memScoped {
  val ret = allocArray<GodotRealVar>(12)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(1)
  types[0] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_TRANSFORM3D,
    ret,
  )
  Transform3D(
    Basis(
      Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[3]), GodotReal.fromC(ret[6])),
      Vector3(GodotReal.fromC(ret[1]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[7])),
      Vector3(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[5]), GodotReal.fromC(ret[8])),
    ),
    Vector3(GodotReal.fromC(ret[9]), GodotReal.fromC(ret[10]), GodotReal.fromC(ret[11])),
  )
}

fun ObjectCalls.ptrcallWithRIDArgRetUInt32(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(1)
  types[0] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDArgRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(1)
  types[0] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithRIDArgRetVector2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
): Vector2i = memScoped {
  val ret = allocArray<IntVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(1)
  types[0] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR2I,
    ret,
  )
  Vector2i(ret[0], ret[1])
}

fun ObjectCalls.ptrcallWithRIDArgRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(1)
  types[0] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithRIDArgRetVector3i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
): Vector3i = memScoped {
  val ret = allocArray<IntVar>(3)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val types = allocArray<IntVar>(1)
  types[0] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR3I,
    ret,
  )
  Vector3i(ret[0], ret[1], ret[2])
}

fun ObjectCalls.ptrcallWithRIDBoolColorDoubleDoubleDoubleDoubleDoubleDoubleDoubleLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
  a2: Color,
  a3: Double,
  a4: Double,
  a5: Double,
  a6: Double,
  a7: Double,
  a8: Double,
  a9: Double,
  a10: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val c6 = alloc<DoubleVar>()
  c6.value = a6
  val c7 = alloc<DoubleVar>()
  c7.value = a7
  val c8 = alloc<DoubleVar>()
  c8.value = a8
  val c9 = alloc<DoubleVar>()
  c9.value = a9
  val c10 = alloc<LongVar>()
  c10.value = a10
  val types = allocArray<IntVar>(11)
  types[0] = PT_RID
  types[1] = PT_BOOL
  types[2] = PT_COLOR
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  types[5] = PT_FLOAT64
  types[6] = PT_FLOAT64
  types[7] = PT_FLOAT64
  types[8] = PT_FLOAT64
  types[9] = PT_FLOAT64
  types[10] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(11)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  ptrs[9] = c9.ptr.reinterpret<CPointed>()
  ptrs[10] = c10.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 11, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDBoolDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
  a2: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_BOOL
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDBoolDoubleDoubleDoubleDoubleDoubleDoubleDoubleDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
  a2: Double,
  a3: Double,
  a4: Double,
  a5: Double,
  a6: Double,
  a7: Double,
  a8: Double,
  a9: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val c6 = alloc<DoubleVar>()
  c6.value = a6
  val c7 = alloc<DoubleVar>()
  c7.value = a7
  val c8 = alloc<DoubleVar>()
  c8.value = a8
  val c9 = alloc<DoubleVar>()
  c9.value = a9
  val types = allocArray<IntVar>(10)
  types[0] = PT_RID
  types[1] = PT_BOOL
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  types[5] = PT_FLOAT64
  types[6] = PT_FLOAT64
  types[7] = PT_FLOAT64
  types[8] = PT_FLOAT64
  types[9] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(10)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  ptrs[9] = c9.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 10, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDBoolDoubleTwoColorDoubleDoubleDoubleDoubleDoubleBoolThreeDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
  a2: Double,
  a3: Color,
  a4: Color,
  a5: Double,
  a6: Double,
  a7: Double,
  a8: Double,
  a9: Double,
  a10: Boolean,
  a11: Double,
  a12: Double,
  a13: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = allocArray<FloatVar>(4)
  c4[0] = a4.r
  c4[1] = a4.g
  c4[2] = a4.b
  c4[3] = a4.a
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val c6 = alloc<DoubleVar>()
  c6.value = a6
  val c7 = alloc<DoubleVar>()
  c7.value = a7
  val c8 = alloc<DoubleVar>()
  c8.value = a8
  val c9 = alloc<DoubleVar>()
  c9.value = a9
  val c10 = alloc<ByteVar>()
  c10.value = if (a10) 1 else 0
  val c11 = alloc<DoubleVar>()
  c11.value = a11
  val c12 = alloc<DoubleVar>()
  c12.value = a12
  val c13 = alloc<DoubleVar>()
  c13.value = a13
  val types = allocArray<IntVar>(14)
  types[0] = PT_RID
  types[1] = PT_BOOL
  types[2] = PT_FLOAT64
  types[3] = PT_COLOR
  types[4] = PT_COLOR
  types[5] = PT_FLOAT64
  types[6] = PT_FLOAT64
  types[7] = PT_FLOAT64
  types[8] = PT_FLOAT64
  types[9] = PT_FLOAT64
  types[10] = PT_BOOL
  types[11] = PT_FLOAT64
  types[12] = PT_FLOAT64
  types[13] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(14)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  ptrs[9] = c9.ptr.reinterpret<CPointed>()
  ptrs[10] = c10.ptr.reinterpret<CPointed>()
  ptrs[11] = c11.ptr.reinterpret<CPointed>()
  ptrs[12] = c12.ptr.reinterpret<CPointed>()
  ptrs[13] = c13.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 14, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDBoolFourDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
  a2: Double,
  a3: Double,
  a4: Double,
  a5: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_RID
  types[1] = PT_BOOL
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  types[5] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDBoolIntDoubleLongBoolDoubleBoolThreeDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
  a2: Int,
  a3: Double,
  a4: Long,
  a5: Boolean,
  a6: Double,
  a7: Boolean,
  a8: Double,
  a9: Double,
  a10: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<LongVar>()
  c4.value = a4
  val c5 = alloc<ByteVar>()
  c5.value = if (a5) 1 else 0
  val c6 = alloc<DoubleVar>()
  c6.value = a6
  val c7 = alloc<ByteVar>()
  c7.value = if (a7) 1 else 0
  val c8 = alloc<DoubleVar>()
  c8.value = a8
  val c9 = alloc<DoubleVar>()
  c9.value = a9
  val c10 = alloc<DoubleVar>()
  c10.value = a10
  val types = allocArray<IntVar>(11)
  types[0] = PT_RID
  types[1] = PT_BOOL
  types[2] = PT_INT64
  types[3] = PT_FLOAT64
  types[4] = PT_INT64
  types[5] = PT_BOOL
  types[6] = PT_FLOAT64
  types[7] = PT_BOOL
  types[8] = PT_FLOAT64
  types[9] = PT_FLOAT64
  types[10] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(11)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  ptrs[9] = c9.ptr.reinterpret<CPointed>()
  ptrs[10] = c10.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 11, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDBoolIntThreeDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
  a2: Int,
  a3: Double,
  a4: Double,
  a5: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_RID
  types[1] = PT_BOOL
  types[2] = PT_INT64
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  types[5] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDBoolRect2Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
  a2: Rect2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = allocArray<GodotRealVar>(4)
  c2[0] = GodotReal.toC(a2.position.x)
  c2[1] = GodotReal.toC(a2.position.y)
  c2[2] = GodotReal.toC(a2.size.x)
  c2[3] = GodotReal.toC(a2.size.y)
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_BOOL
  types[2] = PT_RECT2
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDBoolRect2TwoCallableArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
  a2: Rect2,
  a3Object: MemorySegment,
  a3Method: String,
  a4Object: MemorySegment,
  a4Method: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = allocArray<GodotRealVar>(4)
  c2[0] = GodotReal.toC(a2.position.x)
  c2[1] = GodotReal.toC(a2.position.y)
  c2[2] = GodotReal.toC(a2.size.x)
  c2[3] = GodotReal.toC(a2.size.y)
  val c3 = alloc<KanamaIosCallableArgDesc>()
  c3.object_handle = a3Object.address()
  c3.method = a3Method.cstr.ptr
  val c4 = alloc<KanamaIosCallableArgDesc>()
  c4.object_handle = a4Object.address()
  c4.method = a4Method.cstr.ptr
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_BOOL
  types[2] = PT_RECT2
  types[3] = PT_CALLABLE
  types[4] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDBoolThreeDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
  a2: Double,
  a3: Double,
  a4: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_BOOL
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDBoolThreeDoubleBoolRIDArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
  a2: Double,
  a3: Double,
  a4: Double,
  a5: Boolean,
  a6: RID,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<ByteVar>()
  c5.value = if (a5) 1 else 0
  val c6 = alloc<LongVar>()
  c6.value = a6.value
  val types = allocArray<IntVar>(7)
  types[0] = PT_RID
  types[1] = PT_BOOL
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  types[5] = PT_BOOL
  types[6] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 7, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDBoolTwoDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
  a2: Double,
  a3: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_BOOL
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDBoolTwoDoubleBoolThreeDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
  a2: Double,
  a3: Double,
  a4: Boolean,
  a5: Double,
  a6: Double,
  a7: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val c6 = alloc<DoubleVar>()
  c6.value = a6
  val c7 = alloc<DoubleVar>()
  c7.value = a7
  val types = allocArray<IntVar>(8)
  types[0] = PT_RID
  types[1] = PT_BOOL
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  types[4] = PT_BOOL
  types[5] = PT_FLOAT64
  types[6] = PT_FLOAT64
  types[7] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(8)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 8, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDBoolVector2iArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
  a2: Vector2i,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = allocArray<IntVar>(2)
  c2[0] = a2.x
  c2[1] = a2.y
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_BOOL
  types[2] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithRIDCallableArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1Object: MemorySegment,
  a1Method: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<KanamaIosCallableArgDesc>()
  c1.object_handle = a1Object.address()
  c1.method = a1Method.cstr.ptr
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDCallableTwoUInt32ArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1Object: MemorySegment,
  a1Method: String,
  a2: Long,
  a3: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<KanamaIosCallableArgDesc>()
  c1.object_handle = a1Object.address()
  c1.method = a1Method.cstr.ptr
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_CALLABLE
  types[2] = PT_INT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDColorDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Color,
  a2: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_COLOR
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDColorFourUInt32ArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Color,
  a2: Long,
  a3: Long,
  a4: Long,
  a5: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<LongVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_RID
  types[1] = PT_COLOR
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    6,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDColorLongTwoDoubleLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Color,
  a2: Long,
  a3: Double,
  a4: Double,
  a5: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_RID
  types[1] = PT_COLOR
  types[2] = PT_INT64
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  types[5] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDDoubleAndLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Double,
  a2: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_FLOAT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDDoubleAndLongArgsRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Double,
  a2: Long,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_FLOAT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDDoubleBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Double,
  a2: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_FLOAT64
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDDoubleBoolVector2iArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Double,
  a2: Boolean,
  a3: Vector2i,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = allocArray<IntVar>(2)
  c3[0] = a3.x
  c3[1] = a3.y
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_FLOAT64
  types[2] = PT_BOOL
  types[3] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithRIDDoubleVector2TwoDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Double,
  a2: Vector2,
  a3: Double,
  a4: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_FLOAT64
  types[2] = PT_VECTOR2
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDFourDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Double,
  a2: Double,
  a3: Double,
  a4: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDFourDoubleLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Double,
  a2: Double,
  a3: Double,
  a4: Double,
  a5: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_RID
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  types[5] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDIntAndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
  a2: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDIntAndColorArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
  a2: Color,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDIntAndObjectArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
  a2: MemorySegment,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDIntAndRIDArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
  a2: RID,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.value
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDIntAndStringArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
  a2: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDIntAndTransform2DArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
  a2: Transform2D,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = allocArray<GodotRealVar>(6)
  c2[0] = GodotReal.toC(a2.x.x)
  c2[1] = GodotReal.toC(a2.x.y)
  c2[2] = GodotReal.toC(a2.y.x)
  c2[3] = GodotReal.toC(a2.y.y)
  c2[4] = GodotReal.toC(a2.origin.x)
  c2[5] = GodotReal.toC(a2.origin.y)
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_TRANSFORM2D
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDIntAndTransform3DArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
  a2: Transform3D,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = allocArray<GodotRealVar>(12)
  c2[0] = GodotReal.toC(a2.basis.x.x)
  c2[1] = GodotReal.toC(a2.basis.y.x)
  c2[2] = GodotReal.toC(a2.basis.z.x)
  c2[3] = GodotReal.toC(a2.basis.x.y)
  c2[4] = GodotReal.toC(a2.basis.y.y)
  c2[5] = GodotReal.toC(a2.basis.z.y)
  c2[6] = GodotReal.toC(a2.basis.x.z)
  c2[7] = GodotReal.toC(a2.basis.y.z)
  c2[8] = GodotReal.toC(a2.basis.z.z)
  c2[9] = GodotReal.toC(a2.origin.x)
  c2[10] = GodotReal.toC(a2.origin.y)
  c2[11] = GodotReal.toC(a2.origin.z)
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_TRANSFORM3D
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDIntAndVector3Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
  a2: Vector3,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = allocArray<GodotRealVar>(3)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  c2[2] = GodotReal.toC(a2.z)
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDIntBoolDoubleVector2Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
  a2: Boolean,
  a3: Double,
  a4: Vector2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = allocArray<GodotRealVar>(2)
  c4[0] = GodotReal.toC(a4.x)
  c4[1] = GodotReal.toC(a4.y)
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_BOOL
  types[3] = PT_FLOAT64
  types[4] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDIntCallableArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
  a2Object: MemorySegment,
  a2Method: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<KanamaIosCallableArgDesc>()
  c2.object_handle = a2Object.address()
  c2.method = a2Method.cstr.ptr
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDIntDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
  a2: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDIntLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
  a2: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDIntLongThreeBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Int,
  a2: Long,
  a3: Boolean,
  a4: Boolean,
  a5: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val c5 = alloc<ByteVar>()
  c5.value = if (a5) 1 else 0
  val types = allocArray<IntVar>(6)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_BOOL
  types[4] = PT_BOOL
  types[5] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDLongAndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDLongAndRIDArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: RID,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2.value
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDLongCallableArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2Object: MemorySegment,
  a2Method: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<KanamaIosCallableArgDesc>()
  c2.object_handle = a2Object.address()
  c2.method = a2Method.cstr.ptr
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDLongDoubleBoolDoubleBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Double,
  a3: Boolean,
  a4: Double,
  a5: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<ByteVar>()
  c5.value = if (a5) 1 else 0
  val types = allocArray<IntVar>(6)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_FLOAT64
  types[3] = PT_BOOL
  types[4] = PT_FLOAT64
  types[5] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDLongIntArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Int,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithRIDLongTwoDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Double,
  a3: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDLongVector2LongColorArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Vector2,
  a3: Long,
  a4: Color,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = allocArray<FloatVar>(4)
  c4[0] = a4.r
  c4[1] = a4.g
  c4[2] = a4.b
  c4[3] = a4.a
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_VECTOR2
  types[3] = PT_INT64
  types[4] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDLongVector2iAndVector2Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Vector2i,
  a3: Vector2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = allocArray<IntVar>(2)
  c2[0] = a2.x
  c2[1] = a2.y
  val c3 = allocArray<GodotRealVar>(2)
  c3[0] = GodotReal.toC(a3.x)
  c3[1] = GodotReal.toC(a3.y)
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_VECTOR2I
  types[3] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDLongVector2iArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Vector2i,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = allocArray<IntVar>(2)
  c2[0] = a2.x
  c2[1] = a2.y
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDLongVector2iArgsRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Vector2i,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = allocArray<IntVar>(2)
  c2[0] = a2.x
  c2[1] = a2.y
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithRIDObjectIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: MemorySegment,
  a2: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_OBJECT
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDRIDTransform3DRIDTransform3DArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Transform3D,
  a3: RID,
  a4: Transform3D,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = allocArray<GodotRealVar>(12)
  c2[0] = GodotReal.toC(a2.basis.x.x)
  c2[1] = GodotReal.toC(a2.basis.y.x)
  c2[2] = GodotReal.toC(a2.basis.z.x)
  c2[3] = GodotReal.toC(a2.basis.x.y)
  c2[4] = GodotReal.toC(a2.basis.y.y)
  c2[5] = GodotReal.toC(a2.basis.z.y)
  c2[6] = GodotReal.toC(a2.basis.x.z)
  c2[7] = GodotReal.toC(a2.basis.y.z)
  c2[8] = GodotReal.toC(a2.basis.z.z)
  c2[9] = GodotReal.toC(a2.origin.x)
  c2[10] = GodotReal.toC(a2.origin.y)
  c2[11] = GodotReal.toC(a2.origin.z)
  val c3 = alloc<LongVar>()
  c3.value = a3.value
  val c4 = allocArray<GodotRealVar>(12)
  c4[0] = GodotReal.toC(a4.basis.x.x)
  c4[1] = GodotReal.toC(a4.basis.y.x)
  c4[2] = GodotReal.toC(a4.basis.z.x)
  c4[3] = GodotReal.toC(a4.basis.x.y)
  c4[4] = GodotReal.toC(a4.basis.y.y)
  c4[5] = GodotReal.toC(a4.basis.z.y)
  c4[6] = GodotReal.toC(a4.basis.x.z)
  c4[7] = GodotReal.toC(a4.basis.y.z)
  c4[8] = GodotReal.toC(a4.basis.z.z)
  c4[9] = GodotReal.toC(a4.origin.x)
  c4[10] = GodotReal.toC(a4.origin.y)
  c4[11] = GodotReal.toC(a4.origin.z)
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_TRANSFORM3D
  types[3] = PT_RID
  types[4] = PT_TRANSFORM3D
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDRect2BoolColorBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Rect2,
  a2: Boolean,
  a3: Color,
  a4: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_RECT2
  types[2] = PT_BOOL
  types[3] = PT_COLOR
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDRect2ColorBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Rect2,
  a2: Color,
  a3: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_RECT2
  types[2] = PT_COLOR
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDRect2IntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Rect2,
  a2: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_RECT2
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDRect2RIDBoolColorBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Rect2,
  a2: RID,
  a3: Boolean,
  a4: Color,
  a5: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val c2 = alloc<LongVar>()
  c2.value = a2.value
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val c4 = allocArray<FloatVar>(4)
  c4[0] = a4.r
  c4[1] = a4.g
  c4[2] = a4.b
  c4[3] = a4.a
  val c5 = alloc<ByteVar>()
  c5.value = if (a5) 1 else 0
  val types = allocArray<IntVar>(6)
  types[0] = PT_RID
  types[1] = PT_RECT2
  types[2] = PT_RID
  types[3] = PT_BOOL
  types[4] = PT_COLOR
  types[5] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDRect2RIDRect2ColorArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Rect2,
  a2: RID,
  a3: Rect2,
  a4: Color,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val c2 = alloc<LongVar>()
  c2.value = a2.value
  val c3 = allocArray<GodotRealVar>(4)
  c3[0] = GodotReal.toC(a3.position.x)
  c3[1] = GodotReal.toC(a3.position.y)
  c3[2] = GodotReal.toC(a3.size.x)
  c3[3] = GodotReal.toC(a3.size.y)
  val c4 = allocArray<FloatVar>(4)
  c4[0] = a4.r
  c4[1] = a4.g
  c4[2] = a4.b
  c4[3] = a4.a
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_RECT2
  types[2] = PT_RID
  types[3] = PT_RECT2
  types[4] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDRect2RIDRect2ColorIntTwoDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Rect2,
  a2: RID,
  a3: Rect2,
  a4: Color,
  a5: Int,
  a6: Double,
  a7: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val c2 = alloc<LongVar>()
  c2.value = a2.value
  val c3 = allocArray<GodotRealVar>(4)
  c3[0] = GodotReal.toC(a3.position.x)
  c3[1] = GodotReal.toC(a3.position.y)
  c3[2] = GodotReal.toC(a3.size.x)
  c3[3] = GodotReal.toC(a3.size.y)
  val c4 = allocArray<FloatVar>(4)
  c4[0] = a4.r
  c4[1] = a4.g
  c4[2] = a4.b
  c4[3] = a4.a
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val c6 = alloc<DoubleVar>()
  c6.value = a6
  val c7 = alloc<DoubleVar>()
  c7.value = a7
  val types = allocArray<IntVar>(8)
  types[0] = PT_RID
  types[1] = PT_RECT2
  types[2] = PT_RID
  types[3] = PT_RECT2
  types[4] = PT_COLOR
  types[5] = PT_INT64
  types[6] = PT_FLOAT64
  types[7] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(8)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 8, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDRect2RIDRect2ColorTwoBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Rect2,
  a2: RID,
  a3: Rect2,
  a4: Color,
  a5: Boolean,
  a6: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val c2 = alloc<LongVar>()
  c2.value = a2.value
  val c3 = allocArray<GodotRealVar>(4)
  c3[0] = GodotReal.toC(a3.position.x)
  c3[1] = GodotReal.toC(a3.position.y)
  c3[2] = GodotReal.toC(a3.size.x)
  c3[3] = GodotReal.toC(a3.size.y)
  val c4 = allocArray<FloatVar>(4)
  c4[0] = a4.r
  c4[1] = a4.g
  c4[2] = a4.b
  c4[3] = a4.a
  val c5 = alloc<ByteVar>()
  c5.value = if (a5) 1 else 0
  val c6 = alloc<ByteVar>()
  c6.value = if (a6) 1 else 0
  val types = allocArray<IntVar>(7)
  types[0] = PT_RID
  types[1] = PT_RECT2
  types[2] = PT_RID
  types[3] = PT_RECT2
  types[4] = PT_COLOR
  types[5] = PT_BOOL
  types[6] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 7, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDStringAndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: String,
  a2: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_STRING
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDStringNameAndIntArgRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: String,
  a2: Int,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_STRING_NAME
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithRIDStringNameRIDIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: String,
  a2: RID,
  a3: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c2 = alloc<LongVar>()
  c2.value = a2.value
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_STRING_NAME
  types[2] = PT_RID
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDThreeBoolAndColorArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Boolean,
  a2: Boolean,
  a3: Boolean,
  a4: Color,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val c4 = allocArray<FloatVar>(4)
  c4[0] = a4.r
  c4[1] = a4.g
  c4[2] = a4.b
  c4[3] = a4.a
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_BOOL
  types[2] = PT_BOOL
  types[3] = PT_BOOL
  types[4] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDThreeVector2TwoRIDArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: Vector2,
  a3: Vector2,
  a4: RID,
  a5: RID,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val c3 = allocArray<GodotRealVar>(2)
  c3[0] = GodotReal.toC(a3.x)
  c3[1] = GodotReal.toC(a3.y)
  val c4 = alloc<LongVar>()
  c4.value = a4.value
  val c5 = alloc<LongVar>()
  c5.value = a5.value
  val types = allocArray<IntVar>(6)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_VECTOR2
  types[3] = PT_VECTOR2
  types[4] = PT_RID
  types[5] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDTransform3DVector3TwoColorUInt32Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Transform3D,
  a2: Vector3,
  a3: Color,
  a4: Color,
  a5: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(12)
  c1[0] = GodotReal.toC(a1.basis.x.x)
  c1[1] = GodotReal.toC(a1.basis.y.x)
  c1[2] = GodotReal.toC(a1.basis.z.x)
  c1[3] = GodotReal.toC(a1.basis.x.y)
  c1[4] = GodotReal.toC(a1.basis.y.y)
  c1[5] = GodotReal.toC(a1.basis.z.y)
  c1[6] = GodotReal.toC(a1.basis.x.z)
  c1[7] = GodotReal.toC(a1.basis.y.z)
  c1[8] = GodotReal.toC(a1.basis.z.z)
  c1[9] = GodotReal.toC(a1.origin.x)
  c1[10] = GodotReal.toC(a1.origin.y)
  c1[11] = GodotReal.toC(a1.origin.z)
  val c2 = allocArray<GodotRealVar>(3)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  c2[2] = GodotReal.toC(a2.z)
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = allocArray<FloatVar>(4)
  c4[0] = a4.r
  c4[1] = a4.g
  c4[2] = a4.b
  c4[3] = a4.a
  val c5 = alloc<LongVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_RID
  types[1] = PT_TRANSFORM3D
  types[2] = PT_VECTOR3
  types[3] = PT_COLOR
  types[4] = PT_COLOR
  types[5] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDTwoCallableArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1Object: MemorySegment,
  a1Method: String,
  a2Object: MemorySegment,
  a2Method: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<KanamaIosCallableArgDesc>()
  c1.object_handle = a1Object.address()
  c1.method = a1Method.cstr.ptr
  val c2 = alloc<KanamaIosCallableArgDesc>()
  c2.object_handle = a2Object.address()
  c2.method = a2Method.cstr.ptr
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_CALLABLE
  types[2] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDTwoLongAndVector2Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Long,
  a3: Vector2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = allocArray<GodotRealVar>(2)
  c3[0] = GodotReal.toC(a3.x)
  c3[1] = GodotReal.toC(a3.y)
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDTwoLongArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Long,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithRIDTwoLongArgsRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Long,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDTwoLongBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Long,
  a3: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDTwoLongDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Long,
  a3: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDTwoRect2ColorTwoBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Rect2,
  a2: Rect2,
  a3: Color,
  a4: Boolean,
  a5: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val c2 = allocArray<GodotRealVar>(4)
  c2[0] = GodotReal.toC(a2.position.x)
  c2[1] = GodotReal.toC(a2.position.y)
  c2[2] = GodotReal.toC(a2.size.x)
  c2[3] = GodotReal.toC(a2.size.y)
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val c5 = alloc<ByteVar>()
  c5.value = if (a5) 1 else 0
  val types = allocArray<IntVar>(6)
  types[0] = PT_RID
  types[1] = PT_RECT2
  types[2] = PT_RECT2
  types[3] = PT_COLOR
  types[4] = PT_BOOL
  types[5] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDTwoRect2RIDTwoVector2TwoLongBoolColorArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Rect2,
  a2: Rect2,
  a3: RID,
  a4: Vector2,
  a5: Vector2,
  a6: Long,
  a7: Long,
  a8: Boolean,
  a9: Color,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val c2 = allocArray<GodotRealVar>(4)
  c2[0] = GodotReal.toC(a2.position.x)
  c2[1] = GodotReal.toC(a2.position.y)
  c2[2] = GodotReal.toC(a2.size.x)
  c2[3] = GodotReal.toC(a2.size.y)
  val c3 = alloc<LongVar>()
  c3.value = a3.value
  val c4 = allocArray<GodotRealVar>(2)
  c4[0] = GodotReal.toC(a4.x)
  c4[1] = GodotReal.toC(a4.y)
  val c5 = allocArray<GodotRealVar>(2)
  c5[0] = GodotReal.toC(a5.x)
  c5[1] = GodotReal.toC(a5.y)
  val c6 = alloc<LongVar>()
  c6.value = a6
  val c7 = alloc<LongVar>()
  c7.value = a7
  val c8 = alloc<ByteVar>()
  c8.value = if (a8) 1 else 0
  val c9 = allocArray<FloatVar>(4)
  c9[0] = a9.r
  c9[1] = a9.g
  c9[2] = a9.b
  c9[3] = a9.a
  val types = allocArray<IntVar>(10)
  types[0] = PT_RID
  types[1] = PT_RECT2
  types[2] = PT_RECT2
  types[3] = PT_RID
  types[4] = PT_VECTOR2
  types[5] = PT_VECTOR2
  types[6] = PT_INT64
  types[7] = PT_INT64
  types[8] = PT_BOOL
  types[9] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(10)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  ptrs[5] = c5.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  ptrs[9] = c9.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 10, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDTwoVector2ColorDoubleBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: Vector2,
  a3: Color,
  a4: Double,
  a5: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<ByteVar>()
  c5.value = if (a5) 1 else 0
  val types = allocArray<IntVar>(6)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_VECTOR2
  types[3] = PT_COLOR
  types[4] = PT_FLOAT64
  types[5] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDTwoVector2TwoRIDArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: Vector2,
  a3: RID,
  a4: RID,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val c3 = alloc<LongVar>()
  c3.value = a3.value
  val c4 = alloc<LongVar>()
  c4.value = a4.value
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_VECTOR2
  types[3] = PT_RID
  types[4] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDTwoVector3BoolArgsRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector3,
  a2: Vector3,
  a3: Boolean,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val c2 = allocArray<GodotRealVar>(3)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  c2[2] = GodotReal.toC(a2.z)
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_VECTOR3
  types[2] = PT_VECTOR3
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithRIDUInt32BoolArgsRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Boolean,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithRIDUInt32BoolArgsRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2: Boolean,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithRIDUInt32CallableArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Long,
  a2Object: MemorySegment,
  a2Method: String,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<KanamaIosCallableArgDesc>()
  c2.object_handle = a2Object.address()
  c2.method = a2Method.cstr.ptr
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_INT64
  types[2] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDVector2ColorBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: Color,
  a3: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_COLOR
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2ColorDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: Color,
  a3: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_COLOR
  types[3] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2DoubleColorBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: Double,
  a3: Color,
  a4: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_FLOAT64
  types[3] = PT_COLOR
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2IntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2IntColorDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: Int,
  a3: Color,
  a4: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_INT64
  types[3] = PT_COLOR
  types[4] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2IntTwoColorDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: Int,
  a3: Color,
  a4: Color,
  a5: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = allocArray<FloatVar>(4)
  c4[0] = a4.r
  c4[1] = a4.g
  c4[2] = a4.b
  c4[3] = a4.a
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_INT64
  types[3] = PT_COLOR
  types[4] = PT_COLOR
  types[5] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2StringLongDoubleIntColorThreeLongDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: String,
  a3: Long,
  a4: Double,
  a5: Int,
  a6: Color,
  a7: Long,
  a8: Long,
  a9: Long,
  a10: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val c6 = allocArray<FloatVar>(4)
  c6[0] = a6.r
  c6[1] = a6.g
  c6[2] = a6.b
  c6[3] = a6.a
  val c7 = alloc<LongVar>()
  c7.value = a7
  val c8 = alloc<LongVar>()
  c8.value = a8
  val c9 = alloc<LongVar>()
  c9.value = a9
  val c10 = alloc<DoubleVar>()
  c10.value = a10
  val types = allocArray<IntVar>(11)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_STRING
  types[3] = PT_INT64
  types[4] = PT_FLOAT64
  types[5] = PT_INT64
  types[6] = PT_COLOR
  types[7] = PT_INT64
  types[8] = PT_INT64
  types[9] = PT_INT64
  types[10] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(11)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  ptrs[9] = c9.ptr.reinterpret<CPointed>()
  ptrs[10] = c10.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 11, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2StringLongDoubleThreeIntColorFourLongDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: String,
  a3: Long,
  a4: Double,
  a5: Int,
  a6: Int,
  a7: Int,
  a8: Color,
  a9: Long,
  a10: Long,
  a11: Long,
  a12: Long,
  a13: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val c6 = alloc<LongVar>()
  c6.value = a6.toLong()
  val c7 = alloc<LongVar>()
  c7.value = a7.toLong()
  val c8 = allocArray<FloatVar>(4)
  c8[0] = a8.r
  c8[1] = a8.g
  c8[2] = a8.b
  c8[3] = a8.a
  val c9 = alloc<LongVar>()
  c9.value = a9
  val c10 = alloc<LongVar>()
  c10.value = a10
  val c11 = alloc<LongVar>()
  c11.value = a11
  val c12 = alloc<LongVar>()
  c12.value = a12
  val c13 = alloc<DoubleVar>()
  c13.value = a13
  val types = allocArray<IntVar>(14)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_STRING
  types[3] = PT_INT64
  types[4] = PT_FLOAT64
  types[5] = PT_INT64
  types[6] = PT_INT64
  types[7] = PT_INT64
  types[8] = PT_COLOR
  types[9] = PT_INT64
  types[10] = PT_INT64
  types[11] = PT_INT64
  types[12] = PT_INT64
  types[13] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(14)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.reinterpret<CPointed>()
  ptrs[9] = c9.ptr.reinterpret<CPointed>()
  ptrs[10] = c10.ptr.reinterpret<CPointed>()
  ptrs[11] = c11.ptr.reinterpret<CPointed>()
  ptrs[12] = c12.ptr.reinterpret<CPointed>()
  ptrs[13] = c13.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 14, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2StringLongDoubleTwoIntColorFourLongDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: String,
  a3: Long,
  a4: Double,
  a5: Int,
  a6: Int,
  a7: Color,
  a8: Long,
  a9: Long,
  a10: Long,
  a11: Long,
  a12: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val c6 = alloc<LongVar>()
  c6.value = a6.toLong()
  val c7 = allocArray<FloatVar>(4)
  c7[0] = a7.r
  c7[1] = a7.g
  c7[2] = a7.b
  c7[3] = a7.a
  val c8 = alloc<LongVar>()
  c8.value = a8
  val c9 = alloc<LongVar>()
  c9.value = a9
  val c10 = alloc<LongVar>()
  c10.value = a10
  val c11 = alloc<LongVar>()
  c11.value = a11
  val c12 = alloc<DoubleVar>()
  c12.value = a12
  val types = allocArray<IntVar>(13)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_STRING
  types[3] = PT_INT64
  types[4] = PT_FLOAT64
  types[5] = PT_INT64
  types[6] = PT_INT64
  types[7] = PT_COLOR
  types[8] = PT_INT64
  types[9] = PT_INT64
  types[10] = PT_INT64
  types[11] = PT_INT64
  types[12] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(13)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  ptrs[9] = c9.ptr.reinterpret<CPointed>()
  ptrs[10] = c10.ptr.reinterpret<CPointed>()
  ptrs[11] = c11.ptr.reinterpret<CPointed>()
  ptrs[12] = c12.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 13, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2StringLongDoubleTwoIntColorThreeLongDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: String,
  a3: Long,
  a4: Double,
  a5: Int,
  a6: Int,
  a7: Color,
  a8: Long,
  a9: Long,
  a10: Long,
  a11: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val c6 = alloc<LongVar>()
  c6.value = a6.toLong()
  val c7 = allocArray<FloatVar>(4)
  c7[0] = a7.r
  c7[1] = a7.g
  c7[2] = a7.b
  c7[3] = a7.a
  val c8 = alloc<LongVar>()
  c8.value = a8
  val c9 = alloc<LongVar>()
  c9.value = a9
  val c10 = alloc<LongVar>()
  c10.value = a10
  val c11 = alloc<DoubleVar>()
  c11.value = a11
  val types = allocArray<IntVar>(12)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_STRING
  types[3] = PT_INT64
  types[4] = PT_FLOAT64
  types[5] = PT_INT64
  types[6] = PT_INT64
  types[7] = PT_COLOR
  types[8] = PT_INT64
  types[9] = PT_INT64
  types[10] = PT_INT64
  types[11] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(12)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  ptrs[9] = c9.ptr.reinterpret<CPointed>()
  ptrs[10] = c10.ptr.reinterpret<CPointed>()
  ptrs[11] = c11.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 12, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2ThreeIntColorDoubleArgsRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: Int,
  a3: Int,
  a4: Int,
  a5: Color,
  a6: Double,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val c5 = allocArray<FloatVar>(4)
  c5[0] = a5.r
  c5[1] = a5.g
  c5[2] = a5.b
  c5[3] = a5.a
  val c6 = alloc<DoubleVar>()
  c6.value = a6
  val types = allocArray<IntVar>(7)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_COLOR
  types[6] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    7,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDVector2TwoColorDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: Color,
  a3: Color,
  a4: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_COLOR
  types[3] = PT_COLOR
  types[4] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2TwoDoubleColorBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: Double,
  a3: Double,
  a4: Color,
  a5: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = allocArray<FloatVar>(4)
  c4[0] = a4.r
  c4[1] = a4.g
  c4[2] = a4.b
  c4[3] = a4.a
  val c5 = alloc<ByteVar>()
  c5.value = if (a5) 1 else 0
  val types = allocArray<IntVar>(6)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  types[4] = PT_COLOR
  types[5] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2TwoIntColorDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: Int,
  a3: Int,
  a4: Color,
  a5: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = allocArray<FloatVar>(4)
  c4[0] = a4.r
  c4[1] = a4.g
  c4[2] = a4.b
  c4[3] = a4.a
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_COLOR
  types[5] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2TwoIntColorDoubleArgsRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: Int,
  a3: Int,
  a4: Color,
  a5: Double,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = allocArray<FloatVar>(4)
  c4[0] = a4.r
  c4[1] = a4.g
  c4[2] = a4.b
  c4[3] = a4.a
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_COLOR
  types[5] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    6,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDVector2TwoRIDArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2,
  a2: RID,
  a3: RID,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = alloc<LongVar>()
  c2.value = a2.value
  val c3 = alloc<LongVar>()
  c3.value = a3.value
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_VECTOR2
  types[2] = PT_RID
  types[3] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2iLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2i,
  a2: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2iLongArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2i,
  a2: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithRIDVector2iLongArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2i,
  a2: Long,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithRIDVector2iLongArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2i,
  a2: Long,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithRIDVector2iLongArgsRetRect2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2i,
  a2: Long,
): Rect2 = memScoped {
  val ret = allocArray<GodotRealVar>(4)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_RECT2, ret)
  Rect2(
    Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1])),
    Vector2(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3])),
  )
}

fun ObjectCalls.ptrcallWithRIDVector2iLongArgsRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2i,
  a2: Long,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithRIDVector2iLongObjectArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2i,
  a2: Long,
  a3: MemorySegment,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3.address()
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  types[3] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2iLongRect2Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2i,
  a2: Long,
  a3: Rect2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = allocArray<GodotRealVar>(4)
  c3[0] = GodotReal.toC(a3.position.x)
  c3[1] = GodotReal.toC(a3.position.y)
  c3[2] = GodotReal.toC(a3.size.x)
  c3[3] = GodotReal.toC(a3.size.y)
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  types[3] = PT_RECT2
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2iLongVector2Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2i,
  a2: Long,
  a3: Vector2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = allocArray<GodotRealVar>(2)
  c3[0] = GodotReal.toC(a3.x)
  c3[1] = GodotReal.toC(a3.y)
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  types[3] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRIDVector2iTwoLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: Vector2i,
  a2: Long,
  a3: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithRect2ArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Rect2,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = allocArray<GodotRealVar>(4)
  c0[0] = GodotReal.toC(a0.position.x)
  c0[1] = GodotReal.toC(a0.position.y)
  c0[2] = GodotReal.toC(a0.size.x)
  c0[3] = GodotReal.toC(a0.size.y)
  val types = allocArray<IntVar>(1)
  types[0] = PT_RECT2
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithRect2ColorBoolDoubleBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Rect2,
  a1: Color,
  a2: Boolean,
  a3: Double,
  a4: Boolean,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(4)
  c0[0] = GodotReal.toC(a0.position.x)
  c0[1] = GodotReal.toC(a0.position.y)
  c0[2] = GodotReal.toC(a0.size.x)
  c0[3] = GodotReal.toC(a0.size.y)
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_RECT2
  types[1] = PT_COLOR
  types[2] = PT_BOOL
  types[3] = PT_FLOAT64
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringAndBoolArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Boolean,
) = memScoped {
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringAndBoolArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Boolean,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithStringAndBoolArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Boolean,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithStringAndBoolArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Boolean,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringAndBoolArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Boolean,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithStringAndColorArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Color,
) = memScoped {
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringAndDoubleArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Double,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringAndFiveIntArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: Int,
  a3: Int,
  a4: Int,
  a5: Int,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val types = allocArray<IntVar>(6)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    6,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringAndIntArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringAndIntArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithStringAndIntArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithStringAndIntArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringAndIntArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithStringAndIntArgRetVector2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
): Vector2i = memScoped {
  val ret = allocArray<IntVar>(2)
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR2I,
    ret,
  )
  Vector2i(ret[0], ret[1])
}

fun ObjectCalls.ptrcallWithStringAndLongArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Long,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithStringAndLongArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringAndObjectArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringAndObjectArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithStringAndObjectArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringAndObjectArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithStringAndRIDArgRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: RID,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithStringAndThreeIntArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: Int,
  a3: Int,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithStringAndTransform3DArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Transform3D,
) = memScoped {
  val c1 = allocArray<GodotRealVar>(12)
  c1[0] = GodotReal.toC(a1.basis.x.x)
  c1[1] = GodotReal.toC(a1.basis.y.x)
  c1[2] = GodotReal.toC(a1.basis.z.x)
  c1[3] = GodotReal.toC(a1.basis.x.y)
  c1[4] = GodotReal.toC(a1.basis.y.y)
  c1[5] = GodotReal.toC(a1.basis.z.y)
  c1[6] = GodotReal.toC(a1.basis.x.z)
  c1[7] = GodotReal.toC(a1.basis.y.z)
  c1[8] = GodotReal.toC(a1.basis.z.z)
  c1[9] = GodotReal.toC(a1.origin.x)
  c1[10] = GodotReal.toC(a1.origin.y)
  c1[11] = GodotReal.toC(a1.origin.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_TRANSFORM3D
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringAndTwoBoolArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Boolean,
  a2: Boolean,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_BOOL
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithStringAndTwoIntArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: Int,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithStringAndTwoObjectArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
  a2: MemorySegment,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_OBJECT
  types[2] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
) = memScoped {
  val types = allocArray<IntVar>(1)
  types[0] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val types = allocArray<IntVar>(1)
  types[0] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithStringArgRetColor(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
): Color = memScoped {
  val ret = allocArray<FloatVar>(4)
  val types = allocArray<IntVar>(1)
  types[0] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_COLOR, ret)
  Color(ret[0], ret[1], ret[2], ret[3])
}

fun ObjectCalls.ptrcallWithStringArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val types = allocArray<IntVar>(1)
  types[0] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithStringArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val types = allocArray<IntVar>(1)
  types[0] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val types = allocArray<IntVar>(1)
  types[0] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithStringBoolDoubleArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Boolean,
  a2: Double,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_BOOL
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringBoolObjectArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Boolean,
  a2: MemorySegment,
) = memScoped {
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_BOOL
  types[2] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringBoolStringArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Boolean,
  a2: String,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_BOOL
  types[2] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithStringCallableArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1Object: MemorySegment,
  a1Method: String,
) = memScoped {
  val c1 = alloc<KanamaIosCallableArgDesc>()
  c1.object_handle = a1Object.address()
  c1.method = a1Method.cstr.ptr
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringCallableArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1Object: MemorySegment,
  a1Method: String,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c1 = alloc<KanamaIosCallableArgDesc>()
  c1.object_handle = a1Object.address()
  c1.method = a1Method.cstr.ptr
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithStringCallableObjectArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1Object: MemorySegment,
  a1Method: String,
  a2: MemorySegment,
) = memScoped {
  val c1 = alloc<KanamaIosCallableArgDesc>()
  c1.object_handle = a1Object.address()
  c1.method = a1Method.cstr.ptr
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_CALLABLE
  types[2] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringColorThreeBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Color,
  a2: Boolean,
  a3: Boolean,
  a4: Boolean,
) = memScoped {
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_STRING
  types[1] = PT_COLOR
  types[2] = PT_BOOL
  types[3] = PT_BOOL
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringFourDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Double,
  a2: Double,
  a3: Double,
  a4: Double,
) = memScoped {
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val types = allocArray<IntVar>(5)
  types[0] = PT_STRING
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringIntAndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: Boolean,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringIntAndIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: Int,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringIntAndLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: Long,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringIntAndObjectArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: MemorySegment,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringIntAndStringArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: String,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringIntCallableArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2Object: MemorySegment,
  a2Method: String,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<KanamaIosCallableArgDesc>()
  c2.object_handle = a2Object.address()
  c2.method = a2Method.cstr.ptr
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringIntObjectArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: MemorySegment,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringIntStringArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: String,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringIntStringBoolArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: String,
  a3: Boolean,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_STRING
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringLongBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Long,
  a2: Boolean,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringLongDoubleIntThreeLongArgsRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Long,
  a2: Double,
  a3: Int,
  a4: Long,
  a5: Long,
  a6: Long,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<LongVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5
  val c6 = alloc<LongVar>()
  c6.value = a6
  val types = allocArray<IntVar>(7)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_FLOAT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_INT64
  types[6] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    7,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithStringLongDoubleTwoIntFourLongArgsRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Long,
  a2: Double,
  a3: Int,
  a4: Int,
  a5: Long,
  a6: Long,
  a7: Long,
  a8: Long,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val c5 = alloc<LongVar>()
  c5.value = a5
  val c6 = alloc<LongVar>()
  c6.value = a6
  val c7 = alloc<LongVar>()
  c7.value = a7
  val c8 = alloc<LongVar>()
  c8.value = a8
  val types = allocArray<IntVar>(9)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_FLOAT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_INT64
  types[6] = PT_INT64
  types[7] = PT_INT64
  types[8] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(9)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    9,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithStringLongObjectTwoBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Long,
  a2: MemorySegment,
  a3: Boolean,
  a4: Boolean,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_OBJECT
  types[3] = PT_BOOL
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringLongStringArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Long,
  a2: String,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameAndBoolArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Boolean,
) = memScoped {
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameAndBoolArgRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Boolean,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringNameAndCallableArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1Object: MemorySegment,
  a1Method: String,
) = memScoped {
  val c1 = alloc<KanamaIosCallableArgDesc>()
  c1.object_handle = a1Object.address()
  c1.method = a1Method.cstr.ptr
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameAndColorArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Color,
) = memScoped {
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameAndDoubleArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Double,
) = memScoped {
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameAndIntArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameAndIntArgRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringNameAndIntArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithStringNameAndIntArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithStringNameAndLongArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Long,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameAndObjectArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameAndObjectArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithStringNameAndObjectArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringNameAndThreeDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Double,
  a2: Double,
  a3: Double,
) = memScoped {
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING_NAME
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameAndTwoBoolArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Boolean,
  a2: Boolean,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING_NAME
  types[1] = PT_BOOL
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithStringNameAndVector2Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Vector2,
) = memScoped {
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
) = memScoped {
  val types = allocArray<IntVar>(1)
  types[0] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val types = allocArray<IntVar>(1)
  types[0] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithStringNameArgRetColor(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
): Color = memScoped {
  val ret = allocArray<FloatVar>(4)
  val types = allocArray<IntVar>(1)
  types[0] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_COLOR, ret)
  Color(ret[0], ret[1], ret[2], ret[3])
}

fun ObjectCalls.ptrcallWithStringNameArgRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val types = allocArray<IntVar>(1)
  types[0] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringNameArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val types = allocArray<IntVar>(1)
  types[0] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithStringNameArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val types = allocArray<IntVar>(1)
  types[0] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringNameArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val types = allocArray<IntVar>(1)
  types[0] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithStringNameArgRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val types = allocArray<IntVar>(1)
  types[0] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithStringNameDoubleBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Double,
  a2: Boolean,
) = memScoped {
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING_NAME
  types[1] = PT_FLOAT64
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameDoubleDoubleBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Double,
  a2: Double,
  a3: Boolean,
) = memScoped {
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING_NAME
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameDoubleTwoLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Double,
  a2: Long,
  a3: Long,
) = memScoped {
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING_NAME
  types[1] = PT_FLOAT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameFourDoubleBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Double,
  a2: Double,
  a3: Double,
  a4: Double,
  a5: Boolean,
) = memScoped {
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<ByteVar>()
  c5.value = if (a5) 1 else 0
  val types = allocArray<IntVar>(6)
  types[0] = PT_STRING_NAME
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  types[5] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameIntAndStringNameArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: String,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING_NAME
  types[1] = PT_INT64
  types[2] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameIntObjectDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: MemorySegment,
  a3: Double,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING_NAME
  types[1] = PT_INT64
  types[2] = PT_OBJECT
  types[3] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameIntStringNameIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: String,
  a3: Int,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING_NAME
  types[1] = PT_INT64
  types[2] = PT_STRING_NAME
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameIntStringNameIntArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: String,
  a3: Int,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING_NAME
  types[1] = PT_INT64
  types[2] = PT_STRING_NAME
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithStringNameIntStringNameIntBoolArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: String,
  a3: Int,
  a4: Boolean,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_STRING_NAME
  types[1] = PT_INT64
  types[2] = PT_STRING_NAME
  types[3] = PT_INT64
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringNameIntStringNameIntDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: String,
  a3: Int,
  a4: Double,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val types = allocArray<IntVar>(5)
  types[0] = PT_STRING_NAME
  types[1] = PT_INT64
  types[2] = PT_STRING_NAME
  types[3] = PT_INT64
  types[4] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameObjectAndBoolArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
  a2: Boolean,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING_NAME
  types[1] = PT_OBJECT
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithStringNameObjectAndVector2Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
  a2: Vector2,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING_NAME
  types[1] = PT_OBJECT
  types[2] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameObjectDoubleIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
  a2: Double,
  a3: Int,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING_NAME
  types[1] = PT_OBJECT
  types[2] = PT_FLOAT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameObjectDoubleTwoBoolDoubleLongTwoBoolArgsRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
  a2: Double,
  a3: Boolean,
  a4: Boolean,
  a5: Double,
  a6: Long,
  a7: Boolean,
  a8: Boolean,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val c6 = alloc<LongVar>()
  c6.value = a6
  val c7 = alloc<ByteVar>()
  c7.value = if (a7) 1 else 0
  val c8 = alloc<ByteVar>()
  c8.value = if (a8) 1 else 0
  val types = allocArray<IntVar>(9)
  types[0] = PT_STRING_NAME
  types[1] = PT_OBJECT
  types[2] = PT_FLOAT64
  types[3] = PT_BOOL
  types[4] = PT_BOOL
  types[5] = PT_FLOAT64
  types[6] = PT_INT64
  types[7] = PT_BOOL
  types[8] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(9)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    9,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringNameObjectIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
  a2: Int,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING_NAME
  types[1] = PT_OBJECT
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameThreeDoubleBoolTwoLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Double,
  a2: Double,
  a3: Double,
  a4: Boolean,
  a5: Long,
  a6: Long,
) = memScoped {
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val c5 = alloc<LongVar>()
  c5.value = a5
  val c6 = alloc<LongVar>()
  c6.value = a6
  val types = allocArray<IntVar>(7)
  types[0] = PT_STRING_NAME
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  types[4] = PT_BOOL
  types[5] = PT_INT64
  types[6] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 7, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameTransform3DTwoVector3LongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Transform3D,
  a2: Vector3,
  a3: Vector3,
  a4: Long,
) = memScoped {
  val c1 = allocArray<GodotRealVar>(12)
  c1[0] = GodotReal.toC(a1.basis.x.x)
  c1[1] = GodotReal.toC(a1.basis.y.x)
  c1[2] = GodotReal.toC(a1.basis.z.x)
  c1[3] = GodotReal.toC(a1.basis.x.y)
  c1[4] = GodotReal.toC(a1.basis.y.y)
  c1[5] = GodotReal.toC(a1.basis.z.y)
  c1[6] = GodotReal.toC(a1.basis.x.z)
  c1[7] = GodotReal.toC(a1.basis.y.z)
  c1[8] = GodotReal.toC(a1.basis.z.z)
  c1[9] = GodotReal.toC(a1.origin.x)
  c1[10] = GodotReal.toC(a1.origin.y)
  c1[11] = GodotReal.toC(a1.origin.z)
  val c2 = allocArray<GodotRealVar>(3)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  c2[2] = GodotReal.toC(a2.z)
  val c3 = allocArray<GodotRealVar>(3)
  c3[0] = GodotReal.toC(a3.x)
  c3[1] = GodotReal.toC(a3.y)
  c3[2] = GodotReal.toC(a3.z)
  val c4 = alloc<LongVar>()
  c4.value = a4
  val types = allocArray<IntVar>(5)
  types[0] = PT_STRING_NAME
  types[1] = PT_TRANSFORM3D
  types[2] = PT_VECTOR3
  types[3] = PT_VECTOR3
  types[4] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringNameTwoDoubleTwoBoolDoubleLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Double,
  a2: Double,
  a3: Boolean,
  a4: Boolean,
  a5: Double,
  a6: Long,
) = memScoped {
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val c6 = alloc<LongVar>()
  c6.value = a6
  val types = allocArray<IntVar>(7)
  types[0] = PT_STRING_NAME
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_BOOL
  types[4] = PT_BOOL
  types[5] = PT_FLOAT64
  types[6] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 7, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringObjectBoolArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
  a2: Boolean,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_OBJECT
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithStringObjectBoolColorArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
  a2: Boolean,
  a3: Color,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING
  types[1] = PT_OBJECT
  types[2] = PT_BOOL
  types[3] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringObjectBoolStringArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
  a2: Boolean,
  a3: String,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING
  types[1] = PT_OBJECT
  types[2] = PT_BOOL
  types[3] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = a3.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringObjectIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
  a2: Int,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_OBJECT
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringObjectIntRect2ColorIntColorArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
  a2: Int,
  a3: Rect2,
  a4: Color,
  a5: Int,
  a6: Color,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = allocArray<GodotRealVar>(4)
  c3[0] = GodotReal.toC(a3.position.x)
  c3[1] = GodotReal.toC(a3.position.y)
  c3[2] = GodotReal.toC(a3.size.x)
  c3[3] = GodotReal.toC(a3.size.y)
  val c4 = allocArray<FloatVar>(4)
  c4[0] = a4.r
  c4[1] = a4.g
  c4[2] = a4.b
  c4[3] = a4.a
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val c6 = allocArray<FloatVar>(4)
  c6[0] = a6.r
  c6[1] = a6.g
  c6[2] = a6.b
  c6[3] = a6.a
  val types = allocArray<IntVar>(7)
  types[0] = PT_STRING
  types[1] = PT_OBJECT
  types[2] = PT_INT64
  types[3] = PT_RECT2
  types[4] = PT_COLOR
  types[5] = PT_INT64
  types[6] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 7, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringObjectIntRect2StringArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
  a2: Int,
  a3: Rect2,
  a4: String,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = allocArray<GodotRealVar>(4)
  c3[0] = GodotReal.toC(a3.position.x)
  c3[1] = GodotReal.toC(a3.position.y)
  c3[2] = GodotReal.toC(a3.size.x)
  c3[3] = GodotReal.toC(a3.size.y)
  val types = allocArray<IntVar>(5)
  types[0] = PT_STRING
  types[1] = PT_OBJECT
  types[2] = PT_INT64
  types[3] = PT_RECT2
  types[4] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = a4.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithStringObjectUInt32StringArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: MemorySegment,
  a2: Long,
  a3: String,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING
  types[1] = PT_OBJECT
  types[2] = PT_INT64
  types[3] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = a3.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringRect2LongThreeIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Rect2,
  a2: Long,
  a3: Int,
  a4: Int,
  a5: Int,
) = memScoped {
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val types = allocArray<IntVar>(6)
  types[0] = PT_STRING
  types[1] = PT_RECT2
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringStringNameFourDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Double,
  a3: Double,
  a4: Double,
  a5: Double,
) = memScoped {
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_STRING
  types[1] = PT_STRING_NAME
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  types[5] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringStringStringStringStringLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: String,
  a3: String,
  a4: String,
  a5: Long,
) = memScoped {
  val c5 = alloc<LongVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_STRING
  types[1] = PT_STRING
  types[2] = PT_STRING
  types[3] = PT_STRING
  types[4] = PT_STRING
  types[5] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = a3.cstr.ptr.reinterpret<CPointed>()
  ptrs[4] = a4.cstr.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringThreeIntLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: Int,
  a3: Int,
  a4: Long,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<LongVar>()
  c4.value = a4
  val types = allocArray<IntVar>(5)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringTwoBoolAndDoubleArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Boolean,
  a2: Boolean,
  a3: Double,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING
  types[1] = PT_BOOL
  types[2] = PT_BOOL
  types[3] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringTwoCallableArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1Object: MemorySegment,
  a1Method: String,
  a2Object: MemorySegment,
  a2Method: String,
) = memScoped {
  val c1 = alloc<KanamaIosCallableArgDesc>()
  c1.object_handle = a1Object.address()
  c1.method = a1Method.cstr.ptr
  val c2 = alloc<KanamaIosCallableArgDesc>()
  c2.object_handle = a2Object.address()
  c2.method = a2Method.cstr.ptr
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_CALLABLE
  types[2] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringTwoIntTwoBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Int,
  a2: Int,
  a3: Boolean,
  a4: Boolean,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_BOOL
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringTwoLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Long,
  a2: Long,
) = memScoped {
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithStringTwoLongArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Long,
  a2: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithStringUInt32TwoIntArgsRetVector2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: Long,
  a2: Int,
  a3: Int,
): Vector2i = memScoped {
  val ret = allocArray<IntVar>(2)
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_VECTOR2I,
    ret,
  )
  Vector2i(ret[0], ret[1])
}

fun ObjectCalls.ptrcallWithThreeCallableArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0Object: MemorySegment,
  a0Method: String,
  a1Object: MemorySegment,
  a1Method: String,
  a2Object: MemorySegment,
  a2Method: String,
) = memScoped {
  val c0 = alloc<KanamaIosCallableArgDesc>()
  c0.object_handle = a0Object.address()
  c0.method = a0Method.cstr.ptr
  val c1 = alloc<KanamaIosCallableArgDesc>()
  c1.object_handle = a1Object.address()
  c1.method = a1Method.cstr.ptr
  val c2 = alloc<KanamaIosCallableArgDesc>()
  c2.object_handle = a2Object.address()
  c2.method = a2Method.cstr.ptr
  val types = allocArray<IntVar>(3)
  types[0] = PT_CALLABLE
  types[1] = PT_CALLABLE
  types[2] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithThreeDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Double,
  a2: Double,
) = memScoped {
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_FLOAT64
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithThreeDoubleArgsRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Double,
  a2: Double,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_FLOAT64
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithThreeDoubleBoolArgsRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Double,
  a2: Double,
  a3: Boolean,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_FLOAT64
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithThreeIntAndVector2Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Int,
  a3: Vector2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = allocArray<GodotRealVar>(2)
  c3[0] = GodotReal.toC(a3.x)
  c3[1] = GodotReal.toC(a3.y)
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithThreeIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithThreeIntArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Int,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithThreeIntArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Int,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithThreeIntArgsRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Int,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithThreeIntArgsRetVector2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Int,
): Vector2i = memScoped {
  val ret = allocArray<IntVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR2I,
    ret,
  )
  Vector2i(ret[0], ret[1])
}

fun ObjectCalls.ptrcallWithThreeLongArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
  a2: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithThreeLongFourIntLongArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
  a2: Long,
  a3: Int,
  a4: Int,
  a5: Int,
  a6: Int,
  a7: Long,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val c6 = alloc<LongVar>()
  c6.value = a6.toLong()
  val c7 = alloc<LongVar>()
  c7.value = a7
  val types = allocArray<IntVar>(8)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_INT64
  types[6] = PT_INT64
  types[7] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(8)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    8,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithThreeLongFourUInt32ArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
  a2: Long,
  a3: Long,
  a4: Long,
  a5: Long,
  a6: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<LongVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5
  val c6 = alloc<LongVar>()
  c6.value = a6
  val types = allocArray<IntVar>(7)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_INT64
  types[6] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    7,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithThreeObjectCallableArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: MemorySegment,
  a2: MemorySegment,
  a3Object: MemorySegment,
  a3Method: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val c3 = alloc<KanamaIosCallableArgDesc>()
  c3.object_handle = a3Object.address()
  c3.method = a3Method.cstr.ptr
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_OBJECT
  types[2] = PT_OBJECT
  types[3] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithThreeRIDArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: RID,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = alloc<LongVar>()
  c2.value = a2.value
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithThreeStringAndIntArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: String,
  a3: Int,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING
  types[1] = PT_STRING
  types[2] = PT_STRING
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithThreeStringArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: String,
) = memScoped {
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_STRING
  types[2] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithThreeStringCallableArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: String,
  a3Object: MemorySegment,
  a3Method: String,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c3 = alloc<KanamaIosCallableArgDesc>()
  c3.object_handle = a3Object.address()
  c3.method = a3Method.cstr.ptr
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING
  types[1] = PT_STRING
  types[2] = PT_STRING
  types[3] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithThreeStringNameAndDoubleArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: String,
  a3: Double,
) = memScoped {
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_STRING_NAME
  types[3] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithThreeStringNameArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: String,
) = memScoped {
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithThreeStringNameObjectArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: String,
  a3: MemorySegment,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c3 = alloc<LongVar>()
  c3.value = a3.address()
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_STRING_NAME
  types[3] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithThreeStringNameTwoDoubleBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: String,
  a3: Double,
  a4: Double,
  a5: Boolean,
) = memScoped {
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<ByteVar>()
  c5.value = if (a5) 1 else 0
  val types = allocArray<IntVar>(6)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_STRING_NAME
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  types[5] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithThreeVector2AndIntArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Vector2,
  a2: Vector2,
  a3: Int,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_VECTOR2
  types[1] = PT_VECTOR2
  types[2] = PT_VECTOR2
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithThreeVector2ArgsRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Vector2,
  a2: Vector2,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val types = allocArray<IntVar>(3)
  types[0] = PT_VECTOR2
  types[1] = PT_VECTOR2
  types[2] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithThreeVector2DoubleArgsRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Vector2,
  a2: Vector2,
  a3: Double,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_VECTOR2
  types[1] = PT_VECTOR2
  types[2] = PT_VECTOR2
  types[3] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithThreeVector2iArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Vector2i,
  a2: Vector2i,
) = memScoped {
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = allocArray<IntVar>(2)
  c2[0] = a2.x
  c2[1] = a2.y
  val types = allocArray<IntVar>(3)
  types[0] = PT_VECTOR2I
  types[1] = PT_VECTOR2I
  types[2] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithThreeVector3AndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3,
  a1: Vector3,
  a2: Vector3,
  a3: Boolean,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(3)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  c0[2] = GodotReal.toC(a0.z)
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val c2 = allocArray<GodotRealVar>(3)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  c2[2] = GodotReal.toC(a2.z)
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_VECTOR3
  types[1] = PT_VECTOR3
  types[2] = PT_VECTOR3
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithThreeVector3AndIntArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3,
  a1: Vector3,
  a2: Vector3,
  a3: Int,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(3)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  c0[2] = GodotReal.toC(a0.z)
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val c2 = allocArray<GodotRealVar>(3)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  c2[2] = GodotReal.toC(a2.z)
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_VECTOR3
  types[1] = PT_VECTOR3
  types[2] = PT_VECTOR3
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithThreeVector3ArgsRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3,
  a1: Vector3,
  a2: Vector3,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = allocArray<GodotRealVar>(3)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  c0[2] = GodotReal.toC(a0.z)
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val c2 = allocArray<GodotRealVar>(3)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  c2[2] = GodotReal.toC(a2.z)
  val types = allocArray<IntVar>(3)
  types[0] = PT_VECTOR3
  types[1] = PT_VECTOR3
  types[2] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithTransform2DArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Transform2D,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(6)
  c0[0] = GodotReal.toC(a0.x.x)
  c0[1] = GodotReal.toC(a0.x.y)
  c0[2] = GodotReal.toC(a0.y.x)
  c0[3] = GodotReal.toC(a0.y.y)
  c0[4] = GodotReal.toC(a0.origin.x)
  c0[5] = GodotReal.toC(a0.origin.y)
  val types = allocArray<IntVar>(1)
  types[0] = PT_TRANSFORM2D
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTransform2DObjectTransform2DArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Transform2D,
  a1: MemorySegment,
  a2: Transform2D,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = allocArray<GodotRealVar>(6)
  c0[0] = GodotReal.toC(a0.x.x)
  c0[1] = GodotReal.toC(a0.x.y)
  c0[2] = GodotReal.toC(a0.y.x)
  c0[3] = GodotReal.toC(a0.y.y)
  c0[4] = GodotReal.toC(a0.origin.x)
  c0[5] = GodotReal.toC(a0.origin.y)
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = allocArray<GodotRealVar>(6)
  c2[0] = GodotReal.toC(a2.x.x)
  c2[1] = GodotReal.toC(a2.x.y)
  c2[2] = GodotReal.toC(a2.y.x)
  c2[3] = GodotReal.toC(a2.y.y)
  c2[4] = GodotReal.toC(a2.origin.x)
  c2[5] = GodotReal.toC(a2.origin.y)
  val types = allocArray<IntVar>(3)
  types[0] = PT_TRANSFORM2D
  types[1] = PT_OBJECT
  types[2] = PT_TRANSFORM2D
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithTransform2DVector2ArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Transform2D,
  a1: Vector2,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = allocArray<GodotRealVar>(6)
  c0[0] = GodotReal.toC(a0.x.x)
  c0[1] = GodotReal.toC(a0.x.y)
  c0[2] = GodotReal.toC(a0.y.x)
  c0[3] = GodotReal.toC(a0.y.y)
  c0[4] = GodotReal.toC(a0.origin.x)
  c0[5] = GodotReal.toC(a0.origin.y)
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_TRANSFORM2D
  types[1] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithTransform2DVector2ObjectDoubleBoolArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Transform2D,
  a1: Vector2,
  a2: MemorySegment,
  a3: Double,
  a4: Boolean,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = allocArray<GodotRealVar>(6)
  c0[0] = GodotReal.toC(a0.x.x)
  c0[1] = GodotReal.toC(a0.x.y)
  c0[2] = GodotReal.toC(a0.y.x)
  c0[3] = GodotReal.toC(a0.y.y)
  c0[4] = GodotReal.toC(a0.origin.x)
  c0[5] = GodotReal.toC(a0.origin.y)
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_TRANSFORM2D
  types[1] = PT_VECTOR2
  types[2] = PT_OBJECT
  types[3] = PT_FLOAT64
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithTransform2DVector2ObjectTransform2DVector2ArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Transform2D,
  a1: Vector2,
  a2: MemorySegment,
  a3: Transform2D,
  a4: Vector2,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = allocArray<GodotRealVar>(6)
  c0[0] = GodotReal.toC(a0.x.x)
  c0[1] = GodotReal.toC(a0.x.y)
  c0[2] = GodotReal.toC(a0.y.x)
  c0[3] = GodotReal.toC(a0.y.y)
  c0[4] = GodotReal.toC(a0.origin.x)
  c0[5] = GodotReal.toC(a0.origin.y)
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val c3 = allocArray<GodotRealVar>(6)
  c3[0] = GodotReal.toC(a3.x.x)
  c3[1] = GodotReal.toC(a3.x.y)
  c3[2] = GodotReal.toC(a3.y.x)
  c3[3] = GodotReal.toC(a3.y.y)
  c3[4] = GodotReal.toC(a3.origin.x)
  c3[5] = GodotReal.toC(a3.origin.y)
  val c4 = allocArray<GodotRealVar>(2)
  c4[0] = GodotReal.toC(a4.x)
  c4[1] = GodotReal.toC(a4.y)
  val types = allocArray<IntVar>(5)
  types[0] = PT_TRANSFORM2D
  types[1] = PT_VECTOR2
  types[2] = PT_OBJECT
  types[3] = PT_TRANSFORM2D
  types[4] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithTransform2DVector2TwoColorUInt32Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Transform2D,
  a1: Vector2,
  a2: Color,
  a3: Color,
  a4: Long,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(6)
  c0[0] = GodotReal.toC(a0.x.x)
  c0[1] = GodotReal.toC(a0.x.y)
  c0[2] = GodotReal.toC(a0.y.x)
  c0[3] = GodotReal.toC(a0.y.y)
  c0[4] = GodotReal.toC(a0.origin.x)
  c0[5] = GodotReal.toC(a0.origin.y)
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = alloc<LongVar>()
  c4.value = a4
  val types = allocArray<IntVar>(5)
  types[0] = PT_TRANSFORM2D
  types[1] = PT_VECTOR2
  types[2] = PT_COLOR
  types[3] = PT_COLOR
  types[4] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTransform3DAndDoubleArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Transform3D,
  a1: Double,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = allocArray<GodotRealVar>(12)
  c0[0] = GodotReal.toC(a0.basis.x.x)
  c0[1] = GodotReal.toC(a0.basis.y.x)
  c0[2] = GodotReal.toC(a0.basis.z.x)
  c0[3] = GodotReal.toC(a0.basis.x.y)
  c0[4] = GodotReal.toC(a0.basis.y.y)
  c0[5] = GodotReal.toC(a0.basis.z.y)
  c0[6] = GodotReal.toC(a0.basis.x.z)
  c0[7] = GodotReal.toC(a0.basis.y.z)
  c0[8] = GodotReal.toC(a0.basis.z.z)
  c0[9] = GodotReal.toC(a0.origin.x)
  c0[10] = GodotReal.toC(a0.origin.y)
  c0[11] = GodotReal.toC(a0.origin.z)
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_TRANSFORM3D
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithTransform3DAndLongArgsRetTransform3D(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Transform3D,
  a1: Long,
): Transform3D = memScoped {
  val ret = allocArray<GodotRealVar>(12)
  val c0 = allocArray<GodotRealVar>(12)
  c0[0] = GodotReal.toC(a0.basis.x.x)
  c0[1] = GodotReal.toC(a0.basis.y.x)
  c0[2] = GodotReal.toC(a0.basis.z.x)
  c0[3] = GodotReal.toC(a0.basis.x.y)
  c0[4] = GodotReal.toC(a0.basis.y.y)
  c0[5] = GodotReal.toC(a0.basis.z.y)
  c0[6] = GodotReal.toC(a0.basis.x.z)
  c0[7] = GodotReal.toC(a0.basis.y.z)
  c0[8] = GodotReal.toC(a0.basis.z.z)
  c0[9] = GodotReal.toC(a0.origin.x)
  c0[10] = GodotReal.toC(a0.origin.y)
  c0[11] = GodotReal.toC(a0.origin.z)
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_TRANSFORM3D
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_TRANSFORM3D,
    ret,
  )
  Transform3D(
    Basis(
      Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[3]), GodotReal.fromC(ret[6])),
      Vector3(GodotReal.fromC(ret[1]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[7])),
      Vector3(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[5]), GodotReal.fromC(ret[8])),
    ),
    Vector3(GodotReal.fromC(ret[9]), GodotReal.fromC(ret[10]), GodotReal.fromC(ret[11])),
  )
}

fun ObjectCalls.ptrcallWithTransform3DArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Transform3D,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(12)
  c0[0] = GodotReal.toC(a0.basis.x.x)
  c0[1] = GodotReal.toC(a0.basis.y.x)
  c0[2] = GodotReal.toC(a0.basis.z.x)
  c0[3] = GodotReal.toC(a0.basis.x.y)
  c0[4] = GodotReal.toC(a0.basis.y.y)
  c0[5] = GodotReal.toC(a0.basis.z.y)
  c0[6] = GodotReal.toC(a0.basis.x.z)
  c0[7] = GodotReal.toC(a0.basis.y.z)
  c0[8] = GodotReal.toC(a0.basis.z.z)
  c0[9] = GodotReal.toC(a0.origin.x)
  c0[10] = GodotReal.toC(a0.origin.y)
  c0[11] = GodotReal.toC(a0.origin.z)
  val types = allocArray<IntVar>(1)
  types[0] = PT_TRANSFORM3D
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTransform3DRIDObjectArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Transform3D,
  a1: RID,
  a2: MemorySegment,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = allocArray<GodotRealVar>(12)
  c0[0] = GodotReal.toC(a0.basis.x.x)
  c0[1] = GodotReal.toC(a0.basis.y.x)
  c0[2] = GodotReal.toC(a0.basis.z.x)
  c0[3] = GodotReal.toC(a0.basis.x.y)
  c0[4] = GodotReal.toC(a0.basis.y.y)
  c0[5] = GodotReal.toC(a0.basis.z.y)
  c0[6] = GodotReal.toC(a0.basis.x.z)
  c0[7] = GodotReal.toC(a0.basis.y.z)
  c0[8] = GodotReal.toC(a0.basis.z.z)
  c0[9] = GodotReal.toC(a0.origin.x)
  c0[10] = GodotReal.toC(a0.origin.y)
  c0[11] = GodotReal.toC(a0.origin.z)
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_TRANSFORM3D
  types[1] = PT_RID
  types[2] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithTransform3DVector3ObjectDoubleBoolIntArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Transform3D,
  a1: Vector3,
  a2: MemorySegment,
  a3: Double,
  a4: Boolean,
  a5: Int,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = allocArray<GodotRealVar>(12)
  c0[0] = GodotReal.toC(a0.basis.x.x)
  c0[1] = GodotReal.toC(a0.basis.y.x)
  c0[2] = GodotReal.toC(a0.basis.z.x)
  c0[3] = GodotReal.toC(a0.basis.x.y)
  c0[4] = GodotReal.toC(a0.basis.y.y)
  c0[5] = GodotReal.toC(a0.basis.z.y)
  c0[6] = GodotReal.toC(a0.basis.x.z)
  c0[7] = GodotReal.toC(a0.basis.y.z)
  c0[8] = GodotReal.toC(a0.basis.z.z)
  c0[9] = GodotReal.toC(a0.origin.x)
  c0[10] = GodotReal.toC(a0.origin.y)
  c0[11] = GodotReal.toC(a0.origin.z)
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val types = allocArray<IntVar>(6)
  types[0] = PT_TRANSFORM3D
  types[1] = PT_VECTOR3
  types[2] = PT_OBJECT
  types[3] = PT_FLOAT64
  types[4] = PT_BOOL
  types[5] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    6,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithTransform3DVector3TwoColorUInt32Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Transform3D,
  a1: Vector3,
  a2: Color,
  a3: Color,
  a4: Long,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(12)
  c0[0] = GodotReal.toC(a0.basis.x.x)
  c0[1] = GodotReal.toC(a0.basis.y.x)
  c0[2] = GodotReal.toC(a0.basis.z.x)
  c0[3] = GodotReal.toC(a0.basis.x.y)
  c0[4] = GodotReal.toC(a0.basis.y.y)
  c0[5] = GodotReal.toC(a0.basis.z.y)
  c0[6] = GodotReal.toC(a0.basis.x.z)
  c0[7] = GodotReal.toC(a0.basis.y.z)
  c0[8] = GodotReal.toC(a0.basis.z.z)
  c0[9] = GodotReal.toC(a0.origin.x)
  c0[10] = GodotReal.toC(a0.origin.y)
  c0[11] = GodotReal.toC(a0.origin.z)
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = alloc<LongVar>()
  c4.value = a4
  val types = allocArray<IntVar>(5)
  types[0] = PT_TRANSFORM3D
  types[1] = PT_VECTOR3
  types[2] = PT_COLOR
  types[3] = PT_COLOR
  types[4] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Boolean,
  a1: Boolean,
) = memScoped {
  val c0 = alloc<ByteVar>()
  c0.value = if (a0) 1 else 0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_BOOL
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoBoolArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Boolean,
  a1: Boolean,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<ByteVar>()
  c0.value = if (a0) 1 else 0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_BOOL
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithTwoCallableArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0Object: MemorySegment,
  a0Method: String,
  a1Object: MemorySegment,
  a1Method: String,
) = memScoped {
  val c0 = alloc<KanamaIosCallableArgDesc>()
  c0.object_handle = a0Object.address()
  c0.method = a0Method.cstr.ptr
  val c1 = alloc<KanamaIosCallableArgDesc>()
  c1.object_handle = a1Object.address()
  c1.method = a1Method.cstr.ptr
  val types = allocArray<IntVar>(2)
  types[0] = PT_CALLABLE
  types[1] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoColorArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Color,
  a1: Color,
) = memScoped {
  val c0 = allocArray<FloatVar>(4)
  c0[0] = a0.r
  c0[1] = a0.g
  c0[2] = a0.b
  c0[3] = a0.a
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val types = allocArray<IntVar>(2)
  types[0] = PT_COLOR
  types[1] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoDoubleAndIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Double,
  a2: Int,
) = memScoped {
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_FLOAT64
  types[1] = PT_FLOAT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoDoubleAndLongArgsRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Double,
  a2: Long,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_FLOAT64
  types[1] = PT_FLOAT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithTwoDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Double,
) = memScoped {
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_FLOAT64
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoDoubleArgsRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Double,
  a1: Double,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<DoubleVar>()
  c0.value = a0
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_FLOAT64
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithTwoIntAndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoIntAndColorArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Color,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoIntAndLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoIntAndNodePathArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: NodePath,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_NODE_PATH
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.path.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoIntAndObjectArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: MemorySegment,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoIntAndQuaternionArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Quaternion,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = allocArray<GodotRealVar>(4)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  c2[2] = GodotReal.toC(a2.z)
  c2[3] = GodotReal.toC(a2.w)
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_QUATERNION
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoIntAndStringArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoIntAndStringNameArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoIntAndThreeBoolArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Boolean,
  a3: Boolean,
  a4: Boolean,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_BOOL
  types[3] = PT_BOOL
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithTwoIntAndVector2iArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Vector2i,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = allocArray<IntVar>(2)
  c2[0] = a2.x
  c2[1] = a2.y
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoIntAndVector3Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Vector3,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = allocArray<GodotRealVar>(3)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  c2[2] = GodotReal.toC(a2.z)
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoIntArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithTwoIntArgsRetColor(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
): Color = memScoped {
  val ret = allocArray<FloatVar>(4)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_COLOR, ret)
  Color(ret[0], ret[1], ret[2], ret[3])
}

fun ObjectCalls.ptrcallWithTwoIntArgsRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithTwoIntArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithTwoIntArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithTwoIntArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithTwoIntArgsRetQuaternion(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
): Quaternion = memScoped {
  val ret = allocArray<GodotRealVar>(4)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_QUATERNION,
    ret,
  )
  Quaternion(
    GodotReal.fromC(ret[0]),
    GodotReal.fromC(ret[1]),
    GodotReal.fromC(ret[2]),
    GodotReal.fromC(ret[3]),
  )
}

fun ObjectCalls.ptrcallWithTwoIntArgsRetRect2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
): Rect2i = memScoped {
  val ret = allocArray<IntVar>(4)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_RECT2I, ret)
  Rect2i(Vector2i(ret[0], ret[1]), Vector2i(ret[2], ret[3]))
}

fun ObjectCalls.ptrcallWithTwoIntArgsRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithTwoIntArgsRetVector2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
): Vector2i = memScoped {
  val ret = allocArray<IntVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR2I,
    ret,
  )
  Vector2i(ret[0], ret[1])
}

fun ObjectCalls.ptrcallWithTwoIntArgsRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithTwoIntBoolLongArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Boolean,
  a3: Long,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<LongVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_BOOL
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithTwoIntColorLongTwoBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Color,
  a3: Long,
  a4: Boolean,
  a5: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val c5 = alloc<ByteVar>()
  c5.value = if (a5) 1 else 0
  val types = allocArray<IntVar>(6)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_COLOR
  types[3] = PT_INT64
  types[4] = PT_BOOL
  types[5] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoIntDoubleArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Double,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithTwoIntLongColorBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Long,
  a3: Color,
  a4: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_COLOR
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoIntLongColorBoolArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Long,
  a3: Color,
  a4: Boolean,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_COLOR
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithTwoIntStringArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: String,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithTwoIntThreeLongDoubleBoolIntBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Long,
  a3: Long,
  a4: Long,
  a5: Double,
  a6: Boolean,
  a7: Int,
  a8: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<LongVar>()
  c4.value = a4
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val c6 = alloc<ByteVar>()
  c6.value = if (a6) 1 else 0
  val c7 = alloc<LongVar>()
  c7.value = a7.toLong()
  val c8 = alloc<ByteVar>()
  c8.value = if (a8) 1 else 0
  val types = allocArray<IntVar>(9)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_FLOAT64
  types[6] = PT_BOOL
  types[7] = PT_INT64
  types[8] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(9)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 9, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoIntTwoBoolArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Boolean,
  a3: Boolean,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_BOOL
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithTwoIntTwoBoolDoubleBoolArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Boolean,
  a3: Boolean,
  a4: Double,
  a5: Boolean,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<ByteVar>()
  c5.value = if (a5) 1 else 0
  val types = allocArray<IntVar>(6)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_BOOL
  types[3] = PT_BOOL
  types[4] = PT_FLOAT64
  types[5] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    6,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithTwoIntTwoStringAndIntArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: String,
  a3: String,
  a4: Int,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val types = allocArray<IntVar>(5)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_STRING
  types[3] = PT_STRING
  types[4] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  ptrs[3] = a3.cstr.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithTwoIntVector2DoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Vector2,
  a3: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_VECTOR2
  types[3] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoIntVector2iArgRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Vector2i,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = allocArray<IntVar>(2)
  c2[0] = a2.x
  c2[1] = a2.y
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithTwoIntVector2iVector2Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Int,
  a1: Int,
  a2: Vector2i,
  a3: Vector2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.toLong()
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = allocArray<IntVar>(2)
  c2[0] = a2.x
  c2[1] = a2.y
  val c3 = allocArray<GodotRealVar>(2)
  c3[0] = GodotReal.toC(a3.x)
  c3[1] = GodotReal.toC(a3.y)
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_VECTOR2I
  types[3] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoLongAndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
  a2: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoLongAndBoolArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
  a2: Boolean,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithTwoLongAndIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
  a2: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoLongArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoLongArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithTwoLongArgsRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithTwoLongArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithTwoLongArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithTwoLongArgsRetQuaternion(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
): Quaternion = memScoped {
  val ret = allocArray<GodotRealVar>(4)
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_QUATERNION,
    ret,
  )
  Quaternion(
    GodotReal.fromC(ret[0]),
    GodotReal.fromC(ret[1]),
    GodotReal.fromC(ret[2]),
    GodotReal.fromC(ret[3]),
  )
}

fun ObjectCalls.ptrcallWithTwoLongArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithTwoLongArgsRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithTwoLongArgsRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithTwoLongStringArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
  a2: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoObjectArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: MemorySegment,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoObjectArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: MemorySegment,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithTwoObjectArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: MemorySegment,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_OBJECT
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithTwoObjectCallableArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: MemorySegment,
  a2Object: MemorySegment,
  a2Method: String,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<KanamaIosCallableArgDesc>()
  c2.object_handle = a2Object.address()
  c2.method = a2Method.cstr.ptr
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_OBJECT
  types[2] = PT_CALLABLE
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoObjectIntArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: MemorySegment,
  a2: Int,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_OBJECT
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithTwoObjectTransform2DColorArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: MemorySegment,
  a2: Transform2D,
  a3: Color,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = allocArray<GodotRealVar>(6)
  c2[0] = GodotReal.toC(a2.x.x)
  c2[1] = GodotReal.toC(a2.x.y)
  c2[2] = GodotReal.toC(a2.y.x)
  c2[3] = GodotReal.toC(a2.y.y)
  c2[4] = GodotReal.toC(a2.origin.x)
  c2[5] = GodotReal.toC(a2.origin.y)
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_OBJECT
  types[2] = PT_TRANSFORM2D
  types[3] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoObjectTransform3DObjectArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: MemorySegment,
  a2: Transform3D,
  a3: MemorySegment,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = allocArray<GodotRealVar>(12)
  c2[0] = GodotReal.toC(a2.basis.x.x)
  c2[1] = GodotReal.toC(a2.basis.y.x)
  c2[2] = GodotReal.toC(a2.basis.z.x)
  c2[3] = GodotReal.toC(a2.basis.x.y)
  c2[4] = GodotReal.toC(a2.basis.y.y)
  c2[5] = GodotReal.toC(a2.basis.z.y)
  c2[6] = GodotReal.toC(a2.basis.x.z)
  c2[7] = GodotReal.toC(a2.basis.y.z)
  c2[8] = GodotReal.toC(a2.basis.z.z)
  c2[9] = GodotReal.toC(a2.origin.x)
  c2[10] = GodotReal.toC(a2.origin.y)
  c2[11] = GodotReal.toC(a2.origin.z)
  val c3 = alloc<LongVar>()
  c3.value = a3.address()
  val types = allocArray<IntVar>(4)
  types[0] = PT_OBJECT
  types[1] = PT_OBJECT
  types[2] = PT_TRANSFORM3D
  types[3] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoObjectUInt32ArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: MemorySegment,
  a1: MemorySegment,
  a2: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.address()
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_OBJECT
  types[1] = PT_OBJECT
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithTwoRIDAndTransform2DArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Transform2D,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = allocArray<GodotRealVar>(6)
  c2[0] = GodotReal.toC(a2.x.x)
  c2[1] = GodotReal.toC(a2.x.y)
  c2[2] = GodotReal.toC(a2.y.x)
  c2[3] = GodotReal.toC(a2.y.y)
  c2[4] = GodotReal.toC(a2.origin.x)
  c2[5] = GodotReal.toC(a2.origin.y)
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_TRANSFORM2D
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoRIDAndVector2Arg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Vector2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoRIDArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoRIDArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithTwoRIDArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithTwoRIDArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val types = allocArray<IntVar>(2)
  types[0] = PT_RID
  types[1] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithTwoRIDBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoRIDDoubleIntBoolArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Double,
  a3: Int,
  a4: Boolean,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_FLOAT64
  types[3] = PT_INT64
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithTwoRIDIntRIDIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Int,
  a3: RID,
  a4: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.value
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_INT64
  types[3] = PT_RID
  types[4] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoRIDLongVector2LongColorDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Long,
  a3: Vector2,
  a4: Long,
  a5: Color,
  a6: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = allocArray<GodotRealVar>(2)
  c3[0] = GodotReal.toC(a3.x)
  c3[1] = GodotReal.toC(a3.y)
  val c4 = alloc<LongVar>()
  c4.value = a4
  val c5 = allocArray<FloatVar>(4)
  c5[0] = a5.r
  c5[1] = a5.g
  c5[2] = a5.b
  c5[3] = a5.a
  val c6 = alloc<DoubleVar>()
  c6.value = a6
  val types = allocArray<IntVar>(7)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_INT64
  types[3] = PT_VECTOR2
  types[4] = PT_INT64
  types[5] = PT_COLOR
  types[6] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 7, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoRIDRect2IntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Rect2,
  a3: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = allocArray<GodotRealVar>(4)
  c2[0] = GodotReal.toC(a2.position.x)
  c2[1] = GodotReal.toC(a2.position.y)
  c2[2] = GodotReal.toC(a2.size.x)
  c2[3] = GodotReal.toC(a2.size.y)
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_RECT2
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoRIDThreeUInt32ArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Long,
  a3: Long,
  a4: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<LongVar>()
  c4.value = a4
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithTwoRIDThreeVector3FourUInt32ArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Vector3,
  a3: Vector3,
  a4: Vector3,
  a5: Long,
  a6: Long,
  a7: Long,
  a8: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = allocArray<GodotRealVar>(3)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  c2[2] = GodotReal.toC(a2.z)
  val c3 = allocArray<GodotRealVar>(3)
  c3[0] = GodotReal.toC(a3.x)
  c3[1] = GodotReal.toC(a3.y)
  c3[2] = GodotReal.toC(a3.z)
  val c4 = allocArray<GodotRealVar>(3)
  c4[0] = GodotReal.toC(a4.x)
  c4[1] = GodotReal.toC(a4.y)
  c4[2] = GodotReal.toC(a4.z)
  val c5 = alloc<LongVar>()
  c5.value = a5
  val c6 = alloc<LongVar>()
  c6.value = a6
  val c7 = alloc<LongVar>()
  c7.value = a7
  val c8 = alloc<LongVar>()
  c8.value = a8
  val types = allocArray<IntVar>(9)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_VECTOR3
  types[3] = PT_VECTOR3
  types[4] = PT_VECTOR3
  types[5] = PT_INT64
  types[6] = PT_INT64
  types[7] = PT_INT64
  types[8] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(9)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    9,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithTwoRIDTransform2DBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Transform2D,
  a3: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = allocArray<GodotRealVar>(6)
  c2[0] = GodotReal.toC(a2.x.x)
  c2[1] = GodotReal.toC(a2.x.y)
  c2[2] = GodotReal.toC(a2.y.x)
  c2[3] = GodotReal.toC(a2.y.y)
  c2[4] = GodotReal.toC(a2.origin.x)
  c2[5] = GodotReal.toC(a2.origin.y)
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_TRANSFORM2D
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoRIDTransform2DColorRIDArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Transform2D,
  a3: Color,
  a4: RID,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = allocArray<GodotRealVar>(6)
  c2[0] = GodotReal.toC(a2.x.x)
  c2[1] = GodotReal.toC(a2.x.y)
  c2[2] = GodotReal.toC(a2.y.x)
  c2[3] = GodotReal.toC(a2.y.y)
  c2[4] = GodotReal.toC(a2.origin.x)
  c2[5] = GodotReal.toC(a2.origin.y)
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = alloc<LongVar>()
  c4.value = a4.value
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_TRANSFORM2D
  types[3] = PT_COLOR
  types[4] = PT_RID
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoRIDTransform3DBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Transform3D,
  a3: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = allocArray<GodotRealVar>(12)
  c2[0] = GodotReal.toC(a2.basis.x.x)
  c2[1] = GodotReal.toC(a2.basis.y.x)
  c2[2] = GodotReal.toC(a2.basis.z.x)
  c2[3] = GodotReal.toC(a2.basis.x.y)
  c2[4] = GodotReal.toC(a2.basis.y.y)
  c2[5] = GodotReal.toC(a2.basis.z.y)
  c2[6] = GodotReal.toC(a2.basis.x.z)
  c2[7] = GodotReal.toC(a2.basis.y.z)
  c2[8] = GodotReal.toC(a2.basis.z.z)
  c2[9] = GodotReal.toC(a2.origin.x)
  c2[10] = GodotReal.toC(a2.origin.y)
  c2[11] = GodotReal.toC(a2.origin.z)
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_TRANSFORM3D
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoRIDTwoIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Int,
  a3: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_INT64
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoRIDTwoLongVector2LongColorDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Long,
  a3: Long,
  a4: Vector2,
  a5: Long,
  a6: Color,
  a7: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = allocArray<GodotRealVar>(2)
  c4[0] = GodotReal.toC(a4.x)
  c4[1] = GodotReal.toC(a4.y)
  val c5 = alloc<LongVar>()
  c5.value = a5
  val c6 = allocArray<FloatVar>(4)
  c6[0] = a6.r
  c6[1] = a6.g
  c6[2] = a6.b
  c6[3] = a6.a
  val c7 = alloc<DoubleVar>()
  c7.value = a7
  val types = allocArray<IntVar>(8)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_VECTOR2
  types[5] = PT_INT64
  types[6] = PT_COLOR
  types[7] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(8)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 8, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoRIDVector2TwoDoubleColorDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Vector2,
  a3: Double,
  a4: Double,
  a5: Color,
  a6: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = allocArray<FloatVar>(4)
  c5[0] = a5.r
  c5[1] = a5.g
  c5[2] = a5.b
  c5[3] = a5.a
  val c6 = alloc<DoubleVar>()
  c6.value = a6
  val types = allocArray<IntVar>(7)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_VECTOR2
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  types[5] = PT_COLOR
  types[6] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 7, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoRIDVector2TwoDoubleLongColorDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Vector2,
  a3: Double,
  a4: Double,
  a5: Long,
  a6: Color,
  a7: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5
  val c6 = allocArray<FloatVar>(4)
  c6[0] = a6.r
  c6[1] = a6.g
  c6[2] = a6.b
  c6[3] = a6.a
  val c7 = alloc<DoubleVar>()
  c7.value = a7
  val types = allocArray<IntVar>(8)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_VECTOR2
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  types[5] = PT_INT64
  types[6] = PT_COLOR
  types[7] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(8)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 8, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoRIDVector3RIDVector3Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: RID,
  a1: RID,
  a2: Vector3,
  a3: RID,
  a4: Vector3,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0.value
  val c1 = alloc<LongVar>()
  c1.value = a1.value
  val c2 = allocArray<GodotRealVar>(3)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  c2[2] = GodotReal.toC(a2.z)
  val c3 = alloc<LongVar>()
  c3.value = a3.value
  val c4 = allocArray<GodotRealVar>(3)
  c4[0] = GodotReal.toC(a4.x)
  c4[1] = GodotReal.toC(a4.y)
  c4[2] = GodotReal.toC(a4.z)
  val types = allocArray<IntVar>(5)
  types[0] = PT_RID
  types[1] = PT_RID
  types[2] = PT_VECTOR3
  types[3] = PT_RID
  types[4] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoStringAndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Boolean,
) = memScoped {
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_STRING
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoStringAndIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Int,
) = memScoped {
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_STRING
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoStringAndTwoBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Boolean,
  a3: Boolean,
) = memScoped {
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING
  types[1] = PT_STRING
  types[2] = PT_BOOL
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoStringArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
) = memScoped {
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoStringArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithTwoStringArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithTwoStringArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING
  types[1] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithTwoStringBoolArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Boolean,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING
  types[1] = PT_STRING
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithTwoStringCallableStringArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2Object: MemorySegment,
  a2Method: String,
  a3: String,
) = memScoped {
  val c2 = alloc<KanamaIosCallableArgDesc>()
  c2.object_handle = a2Object.address()
  c2.method = a2Method.cstr.ptr
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING
  types[1] = PT_STRING
  types[2] = PT_CALLABLE
  types[3] = PT_STRING
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = a3.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoStringColorBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Color,
  a3: Boolean,
) = memScoped {
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING
  types[1] = PT_STRING
  types[2] = PT_COLOR
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoStringIntTwoDoubleLongBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Int,
  a3: Double,
  a4: Double,
  a5: Long,
  a6: Boolean,
) = memScoped {
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5
  val c6 = alloc<ByteVar>()
  c6.value = if (a6) 1 else 0
  val types = allocArray<IntVar>(7)
  types[0] = PT_STRING
  types[1] = PT_STRING
  types[2] = PT_INT64
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  types[5] = PT_INT64
  types[6] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 7, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoStringNameAndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Boolean,
) = memScoped {
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Boolean,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Boolean,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithTwoStringNameAndColorArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Color,
) = memScoped {
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoStringNameAndDoubleArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Double,
) = memScoped {
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoStringNameAndIntArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Int,
) = memScoped {
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoStringNameAndObjectArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: MemorySegment,
) = memScoped {
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoStringNameArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
) = memScoped {
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithTwoStringNameArgsRetColor(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
): Color = memScoped {
  val ret = allocArray<FloatVar>(4)
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_COLOR, ret)
  Color(ret[0], ret[1], ret[2], ret[3])
}

fun ObjectCalls.ptrcallWithTwoStringNameArgsRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithTwoStringNameArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithTwoStringNameArgsRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithTwoStringNameArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithTwoStringNameFourUInt32ArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Long,
  a3: Long,
  a4: Long,
  a5: Long,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<LongVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5
  val types = allocArray<IntVar>(6)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    6,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithTwoStringNameFourUInt32ObjectArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Long,
  a3: Long,
  a4: Long,
  a5: Long,
  a6: MemorySegment,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<LongVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5
  val c6 = alloc<LongVar>()
  c6.value = a6.address()
  val types = allocArray<IntVar>(7)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_INT64
  types[6] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    7,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithTwoStringNameLongUInt32LongVector2iTwoUInt32TwoBoolArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Long,
  a3: Long,
  a4: Long,
  a5: Vector2i,
  a6: Long,
  a7: Long,
  a8: Boolean,
  a9: Boolean,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c2 = alloc<LongVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<LongVar>()
  c4.value = a4
  val c5 = allocArray<IntVar>(2)
  c5[0] = a5.x
  c5[1] = a5.y
  val c6 = alloc<LongVar>()
  c6.value = a6
  val c7 = alloc<LongVar>()
  c7.value = a7
  val c8 = alloc<ByteVar>()
  c8.value = if (a8) 1 else 0
  val c9 = alloc<ByteVar>()
  c9.value = if (a9) 1 else 0
  val types = allocArray<IntVar>(10)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_INT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  types[5] = PT_VECTOR2I
  types[6] = PT_INT64
  types[7] = PT_INT64
  types[8] = PT_BOOL
  types[9] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(10)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  ptrs[9] = c9.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    10,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithTwoStringNameTwoObjectBoolArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: MemorySegment,
  a3: MemorySegment,
  a4: Boolean,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val c3 = alloc<LongVar>()
  c3.value = a3.address()
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_OBJECT
  types[3] = PT_OBJECT
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithTwoStringNameUInt32ArgRetVector2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: Long,
): Vector2i = memScoped {
  val ret = allocArray<IntVar>(2)
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_STRING_NAME
  types[1] = PT_STRING_NAME
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR2I,
    ret,
  )
  Vector2i(ret[0], ret[1])
}

fun ObjectCalls.ptrcallWithTwoStringTwoObjectArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: String,
  a1: String,
  a2: MemorySegment,
  a3: MemorySegment,
) = memScoped {
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val c3 = alloc<LongVar>()
  c3.value = a3.address()
  val types = allocArray<IntVar>(4)
  types[0] = PT_STRING
  types[1] = PT_STRING
  types[2] = PT_OBJECT
  types[3] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
  ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoUInt32AndDoubleArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
  a2: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_INT64
  types[1] = PT_INT64
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoVector2Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Vector2,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2
  types[1] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoVector2ColorDoubleBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Vector2,
  a2: Color,
  a3: Double,
  a4: Boolean,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val types = allocArray<IntVar>(5)
  types[0] = PT_VECTOR2
  types[1] = PT_VECTOR2
  types[2] = PT_COLOR
  types[3] = PT_FLOAT64
  types[4] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoVector2ColorTwoDoubleTwoBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Vector2,
  a2: Color,
  a3: Double,
  a4: Double,
  a5: Boolean,
  a6: Boolean,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<ByteVar>()
  c5.value = if (a5) 1 else 0
  val c6 = alloc<ByteVar>()
  c6.value = if (a6) 1 else 0
  val types = allocArray<IntVar>(7)
  types[0] = PT_VECTOR2
  types[1] = PT_VECTOR2
  types[2] = PT_COLOR
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  types[5] = PT_BOOL
  types[6] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 7, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoVector2DoubleArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Vector2,
  a2: Double,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_VECTOR2
  types[1] = PT_VECTOR2
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithTwoVector2iAndObjectArgRetVector2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Vector2i,
  a2: MemorySegment,
): Vector2i = memScoped {
  val ret = allocArray<IntVar>(2)
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2.address()
  val types = allocArray<IntVar>(3)
  types[0] = PT_VECTOR2I
  types[1] = PT_VECTOR2I
  types[2] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR2I,
    ret,
  )
  Vector2i(ret[0], ret[1])
}

fun ObjectCalls.ptrcallWithTwoVector2iArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Vector2i,
) = memScoped {
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2I
  types[1] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoVector2iIntVector2iIntVector2iArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Vector2i,
  a2: Int,
  a3: Vector2i,
  a4: Int,
  a5: Vector2i,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = allocArray<IntVar>(2)
  c1[0] = a1.x
  c1[1] = a1.y
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val c3 = allocArray<IntVar>(2)
  c3[0] = a3.x
  c3[1] = a3.y
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val c5 = allocArray<IntVar>(2)
  c5[0] = a5.x
  c5[1] = a5.y
  val types = allocArray<IntVar>(6)
  types[0] = PT_VECTOR2I
  types[1] = PT_VECTOR2I
  types[2] = PT_INT64
  types[3] = PT_VECTOR2I
  types[4] = PT_INT64
  types[5] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    6,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithTwoVector3AndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3,
  a1: Vector3,
  a2: Boolean,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(3)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  c0[2] = GodotReal.toC(a0.z)
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_VECTOR3
  types[1] = PT_VECTOR3
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoVector3Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3,
  a1: Vector3,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(3)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  c0[2] = GodotReal.toC(a0.z)
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR3
  types[1] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithTwoVector3ArgsRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3,
  a1: Vector3,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = allocArray<GodotRealVar>(3)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  c0[2] = GodotReal.toC(a0.z)
  val c1 = allocArray<GodotRealVar>(3)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  c1[2] = GodotReal.toC(a1.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR3
  types[1] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithTwoVector3iArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3i,
  a1: Vector3i,
) = memScoped {
  val c0 = allocArray<IntVar>(3)
  c0[0] = a0.x
  c0[1] = a0.y
  c0[2] = a0.z
  val c1 = allocArray<IntVar>(3)
  c1[0] = a1.x
  c1[1] = a1.y
  c1[2] = a1.z
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR3I
  types[1] = PT_VECTOR3I
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithUInt32AndBoolArgRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Boolean,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithUInt32AndBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Boolean,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithUInt32AndDoubleArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Double,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithUInt32AndIntArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithUInt32AndIntArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithUInt32AndIntArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Int,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithUInt32AndLongArgRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Long,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithUInt32AndObjectArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: MemorySegment,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithUInt32AndTransform2DArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Transform2D,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = allocArray<GodotRealVar>(6)
  c1[0] = GodotReal.toC(a1.x.x)
  c1[1] = GodotReal.toC(a1.x.y)
  c1[2] = GodotReal.toC(a1.y.x)
  c1[3] = GodotReal.toC(a1.y.y)
  c1[4] = GodotReal.toC(a1.origin.x)
  c1[5] = GodotReal.toC(a1.origin.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_TRANSFORM2D
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithUInt32AndTransform3DArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Transform3D,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = allocArray<GodotRealVar>(12)
  c1[0] = GodotReal.toC(a1.basis.x.x)
  c1[1] = GodotReal.toC(a1.basis.y.x)
  c1[2] = GodotReal.toC(a1.basis.z.x)
  c1[3] = GodotReal.toC(a1.basis.x.y)
  c1[4] = GodotReal.toC(a1.basis.y.y)
  c1[5] = GodotReal.toC(a1.basis.z.y)
  c1[6] = GodotReal.toC(a1.basis.x.z)
  c1[7] = GodotReal.toC(a1.basis.y.z)
  c1[8] = GodotReal.toC(a1.basis.z.z)
  c1[9] = GodotReal.toC(a1.origin.x)
  c1[10] = GodotReal.toC(a1.origin.y)
  c1[11] = GodotReal.toC(a1.origin.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_TRANSFORM3D
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithUInt32AndVector2Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Vector2,
) = memScoped {
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = allocArray<GodotRealVar>(2)
  c1[0] = GodotReal.toC(a1.x)
  c1[1] = GodotReal.toC(a1.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithUInt32Arg(methodBind: MemorySegment, instance: MemorySegment, a0: Long) =
  memScoped {
    val c0 = alloc<LongVar>()
    c0.value = a0
    val types = allocArray<IntVar>(1)
    types[0] = PT_INT64
    val ptrs = allocArray<COpaquePointerVar>(1)
    ptrs[0] = c0.ptr.reinterpret<CPointed>()
    kanama_ios_godot_ptrcall(
      methodBind.address(),
      instance.address(),
      types,
      ptrs,
      1,
      PT_VOID,
      null,
    )
    Unit
  }

fun ObjectCalls.ptrcallWithUInt32ArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithUInt32ArgRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithUInt32ArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithUInt32ArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithUInt32ArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithUInt32ArgRetProjection(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Projection = memScoped {
  val ret = allocArray<GodotRealVar>(16)
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_PROJECTION,
    ret,
  )
  Projection(
    Vector4(
      GodotReal.fromC(ret[0]),
      GodotReal.fromC(ret[1]),
      GodotReal.fromC(ret[2]),
      GodotReal.fromC(ret[3]),
    ),
    Vector4(
      GodotReal.fromC(ret[4]),
      GodotReal.fromC(ret[5]),
      GodotReal.fromC(ret[6]),
      GodotReal.fromC(ret[7]),
    ),
    Vector4(
      GodotReal.fromC(ret[8]),
      GodotReal.fromC(ret[9]),
      GodotReal.fromC(ret[10]),
      GodotReal.fromC(ret[11]),
    ),
    Vector4(
      GodotReal.fromC(ret[12]),
      GodotReal.fromC(ret[13]),
      GodotReal.fromC(ret[14]),
      GodotReal.fromC(ret[15]),
    ),
  )
}

fun ObjectCalls.ptrcallWithUInt32ArgRetTransform2D(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Transform2D = memScoped {
  val ret = allocArray<GodotRealVar>(6)
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_TRANSFORM2D,
    ret,
  )
  Transform2D(
    Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1])),
    Vector2(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3])),
    Vector2(GodotReal.fromC(ret[4]), GodotReal.fromC(ret[5])),
  )
}

fun ObjectCalls.ptrcallWithUInt32ArgRetTransform3D(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Transform3D = memScoped {
  val ret = allocArray<GodotRealVar>(12)
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_TRANSFORM3D,
    ret,
  )
  Transform3D(
    Basis(
      Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[3]), GodotReal.fromC(ret[6])),
      Vector3(GodotReal.fromC(ret[1]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[7])),
      Vector3(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[5]), GodotReal.fromC(ret[8])),
    ),
    Vector3(GodotReal.fromC(ret[9]), GodotReal.fromC(ret[10]), GodotReal.fromC(ret[11])),
  )
}

fun ObjectCalls.ptrcallWithUInt32ArgRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithUInt32ArgRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = alloc<LongVar>()
  c0.value = a0
  val types = allocArray<IntVar>(1)
  types[0] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithUInt32ThreeDoubleArgsRetProjection(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Double,
  a2: Double,
  a3: Double,
): Projection = memScoped {
  val ret = allocArray<GodotRealVar>(16)
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val types = allocArray<IntVar>(4)
  types[0] = PT_INT64
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_PROJECTION,
    ret,
  )
  Projection(
    Vector4(
      GodotReal.fromC(ret[0]),
      GodotReal.fromC(ret[1]),
      GodotReal.fromC(ret[2]),
      GodotReal.fromC(ret[3]),
    ),
    Vector4(
      GodotReal.fromC(ret[4]),
      GodotReal.fromC(ret[5]),
      GodotReal.fromC(ret[6]),
      GodotReal.fromC(ret[7]),
    ),
    Vector4(
      GodotReal.fromC(ret[8]),
      GodotReal.fromC(ret[9]),
      GodotReal.fromC(ret[10]),
      GodotReal.fromC(ret[11]),
    ),
    Vector4(
      GodotReal.fromC(ret[12]),
      GodotReal.fromC(ret[13]),
      GodotReal.fromC(ret[14]),
      GodotReal.fromC(ret[15]),
    ),
  )
}

fun ObjectCalls.ptrcallWithUInt32Transform3DArgsRetTransform3D(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Long,
  a1: Transform3D,
): Transform3D = memScoped {
  val ret = allocArray<GodotRealVar>(12)
  val c0 = alloc<LongVar>()
  c0.value = a0
  val c1 = allocArray<GodotRealVar>(12)
  c1[0] = GodotReal.toC(a1.basis.x.x)
  c1[1] = GodotReal.toC(a1.basis.y.x)
  c1[2] = GodotReal.toC(a1.basis.z.x)
  c1[3] = GodotReal.toC(a1.basis.x.y)
  c1[4] = GodotReal.toC(a1.basis.y.y)
  c1[5] = GodotReal.toC(a1.basis.z.y)
  c1[6] = GodotReal.toC(a1.basis.x.z)
  c1[7] = GodotReal.toC(a1.basis.y.z)
  c1[8] = GodotReal.toC(a1.basis.z.z)
  c1[9] = GodotReal.toC(a1.origin.x)
  c1[10] = GodotReal.toC(a1.origin.y)
  c1[11] = GodotReal.toC(a1.origin.z)
  val types = allocArray<IntVar>(2)
  types[0] = PT_INT64
  types[1] = PT_TRANSFORM3D
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.ptr.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_TRANSFORM3D,
    ret,
  )
  Transform3D(
    Basis(
      Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[3]), GodotReal.fromC(ret[6])),
      Vector3(GodotReal.fromC(ret[1]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[7])),
      Vector3(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[5]), GodotReal.fromC(ret[8])),
    ),
    Vector3(GodotReal.fromC(ret[9]), GodotReal.fromC(ret[10]), GodotReal.fromC(ret[11])),
  )
}

fun ObjectCalls.ptrcallWithVector2AndBoolArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Boolean,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector2AndBoolArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Boolean,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithVector2AndBoolArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Boolean,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithVector2AndDoubleArgRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Double,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithVector2AndIntArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Int,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector2ArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithVector2ArgRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithVector2ArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithVector2ArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithVector2ArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithVector2ArgRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithVector2ArgRetVector2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
): Vector2i = memScoped {
  val ret = allocArray<IntVar>(2)
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR2I,
    ret,
  )
  Vector2i(ret[0], ret[1])
}

fun ObjectCalls.ptrcallWithVector2ArgRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithVector2BoolFloatBoolArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Boolean,
  a2: Double,
  a3: Boolean,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val types = allocArray<IntVar>(4)
  types[0] = PT_VECTOR2
  types[1] = PT_BOOL
  types[2] = PT_FLOAT64
  types[3] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    4,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithVector2DoubleColorBoolDoubleBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Double,
  a2: Color,
  a3: Boolean,
  a4: Double,
  a5: Boolean,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = allocArray<FloatVar>(4)
  c2[0] = a2.r
  c2[1] = a2.g
  c2[2] = a2.b
  c2[3] = a2.a
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<ByteVar>()
  c5.value = if (a5) 1 else 0
  val types = allocArray<IntVar>(6)
  types[0] = PT_VECTOR2
  types[1] = PT_FLOAT64
  types[2] = PT_COLOR
  types[3] = PT_BOOL
  types[4] = PT_FLOAT64
  types[5] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(6)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector2DoubleVector2Args(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Double,
  a2: Vector2,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = allocArray<GodotRealVar>(2)
  c2[0] = GodotReal.toC(a2.x)
  c2[1] = GodotReal.toC(a2.y)
  val types = allocArray<IntVar>(3)
  types[0] = PT_VECTOR2
  types[1] = PT_FLOAT64
  types[2] = PT_VECTOR2
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector2FourDoubleIntColorDoubleBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Double,
  a2: Double,
  a3: Double,
  a4: Double,
  a5: Int,
  a6: Color,
  a7: Double,
  a8: Boolean,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<DoubleVar>()
  c4.value = a4
  val c5 = alloc<LongVar>()
  c5.value = a5.toLong()
  val c6 = allocArray<FloatVar>(4)
  c6[0] = a6.r
  c6[1] = a6.g
  c6[2] = a6.b
  c6[3] = a6.a
  val c7 = alloc<DoubleVar>()
  c7.value = a7
  val c8 = alloc<ByteVar>()
  c8.value = if (a8) 1 else 0
  val types = allocArray<IntVar>(9)
  types[0] = PT_VECTOR2
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  types[4] = PT_FLOAT64
  types[5] = PT_INT64
  types[6] = PT_COLOR
  types[7] = PT_FLOAT64
  types[8] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(9)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  ptrs[8] = c8.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 9, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector2Rect2ArgsRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Rect2,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = allocArray<GodotRealVar>(4)
  c1[0] = GodotReal.toC(a1.position.x)
  c1[1] = GodotReal.toC(a1.position.y)
  c1[2] = GodotReal.toC(a1.size.x)
  c1[3] = GodotReal.toC(a1.size.y)
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2
  types[1] = PT_RECT2
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithVector2ThreeDoubleIntColorDoubleBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Double,
  a2: Double,
  a3: Double,
  a4: Int,
  a5: Color,
  a6: Double,
  a7: Boolean,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<DoubleVar>()
  c3.value = a3
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val c5 = allocArray<FloatVar>(4)
  c5[0] = a5.r
  c5[1] = a5.g
  c5[2] = a5.b
  c5[3] = a5.a
  val c6 = alloc<DoubleVar>()
  c6.value = a6
  val c7 = alloc<ByteVar>()
  c7.value = if (a7) 1 else 0
  val types = allocArray<IntVar>(8)
  types[0] = PT_VECTOR2
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_FLOAT64
  types[4] = PT_INT64
  types[5] = PT_COLOR
  types[6] = PT_FLOAT64
  types[7] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(8)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  ptrs[7] = c7.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 8, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector2TwoDoubleColorBoolDoubleBoolArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Double,
  a2: Double,
  a3: Color,
  a4: Boolean,
  a5: Double,
  a6: Boolean,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = allocArray<FloatVar>(4)
  c3[0] = a3.r
  c3[1] = a3.g
  c3[2] = a3.b
  c3[3] = a3.a
  val c4 = alloc<ByteVar>()
  c4.value = if (a4) 1 else 0
  val c5 = alloc<DoubleVar>()
  c5.value = a5
  val c6 = alloc<ByteVar>()
  c6.value = if (a6) 1 else 0
  val types = allocArray<IntVar>(7)
  types[0] = PT_VECTOR2
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_COLOR
  types[4] = PT_BOOL
  types[5] = PT_FLOAT64
  types[6] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(7)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  ptrs[5] = c5.ptr.reinterpret<CPointed>()
  ptrs[6] = c6.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 7, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector2TwoDoubleTwoLongArgsRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2,
  a1: Double,
  a2: Double,
  a3: Long,
  a4: Long,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = allocArray<GodotRealVar>(2)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<LongVar>()
  c3.value = a3
  val c4 = alloc<LongVar>()
  c4.value = a4
  val types = allocArray<IntVar>(5)
  types[0] = PT_VECTOR2
  types[1] = PT_FLOAT64
  types[2] = PT_FLOAT64
  types[3] = PT_INT64
  types[4] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithVector2iAndBoolArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Boolean,
) = memScoped {
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2I
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector2iAndColorArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Color,
) = memScoped {
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = allocArray<FloatVar>(4)
  c1[0] = a1.r
  c1[1] = a1.g
  c1[2] = a1.b
  c1[3] = a1.a
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2I
  types[1] = PT_COLOR
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector2iAndDoubleArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Double,
) = memScoped {
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2I
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector2iAndIntArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Int,
) = memScoped {
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2I
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector2iAndIntArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Int,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2I
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithVector2iAndIntArgRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Int,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2I
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithVector2iAndIntArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Int,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2I
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithVector2iAndIntArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Int,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2I
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithVector2iAndIntArgRetRect2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Int,
): Rect2i = memScoped {
  val ret = allocArray<IntVar>(4)
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2I
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_RECT2I, ret)
  Rect2i(Vector2i(ret[0], ret[1]), Vector2i(ret[2], ret[3]))
}

fun ObjectCalls.ptrcallWithVector2iAndLongArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Long,
) = memScoped {
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2I
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector2iAndLongArgRetVector2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Long,
): Vector2i = memScoped {
  val ret = allocArray<IntVar>(2)
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = alloc<LongVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2I
  types[1] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_VECTOR2I,
    ret,
  )
  Vector2i(ret[0], ret[1])
}

fun ObjectCalls.ptrcallWithVector2iAndObjectArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: MemorySegment,
) = memScoped {
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = alloc<LongVar>()
  c1.value = a1.address()
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR2I
  types[1] = PT_OBJECT
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector2iAndTwoBoolArgsRetVector2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Boolean,
  a2: Boolean,
): Vector2i = memScoped {
  val ret = allocArray<IntVar>(2)
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<ByteVar>()
  c2.value = if (a2) 1 else 0
  val types = allocArray<IntVar>(3)
  types[0] = PT_VECTOR2I
  types[1] = PT_BOOL
  types[2] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_VECTOR2I,
    ret,
  )
  Vector2i(ret[0], ret[1])
}

fun ObjectCalls.ptrcallWithVector2iAndTwoIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Int,
  a2: Int,
) = memScoped {
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_VECTOR2I
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector2iArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithVector2iArgRetColor(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
): Color = memScoped {
  val ret = allocArray<FloatVar>(4)
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_COLOR, ret)
  Color(ret[0], ret[1], ret[2], ret[3])
}

fun ObjectCalls.ptrcallWithVector2iArgRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithVector2iArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithVector2iArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithVector2iArgRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithVector2iArgRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithVector2iArgRetVector2i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
): Vector2i = memScoped {
  val ret = allocArray<IntVar>(2)
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR2I
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR2I,
    ret,
  )
  Vector2i(ret[0], ret[1])
}

fun ObjectCalls.ptrcallWithVector2iIntDoubleArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Int,
  a2: Double,
) = memScoped {
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_VECTOR2I
  types[1] = PT_INT64
  types[2] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector2iIntVector2iIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Int,
  a2: Vector2i,
  a3: Int,
) = memScoped {
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = allocArray<IntVar>(2)
  c2[0] = a2.x
  c2[1] = a2.y
  val c3 = alloc<LongVar>()
  c3.value = a3.toLong()
  val types = allocArray<IntVar>(4)
  types[0] = PT_VECTOR2I
  types[1] = PT_INT64
  types[2] = PT_VECTOR2I
  types[3] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(4)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector2iLongLongArgsRetRID(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector2i,
  a1: Long,
  a2: Long,
): RID = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = allocArray<IntVar>(2)
  c0[0] = a0.x
  c0[1] = a0.y
  val c1 = alloc<LongVar>()
  c1.value = a1
  val c2 = alloc<LongVar>()
  c2.value = a2
  val types = allocArray<IntVar>(3)
  types[0] = PT_VECTOR2I
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    3,
    PT_RID,
    ret.ptr,
  )
  RID(ret.value)
}

fun ObjectCalls.ptrcallWithVector3AndBoolArgRetLong(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3,
  a1: Boolean,
): Long = memScoped {
  val ret = alloc<LongVar>()
  val c0 = allocArray<GodotRealVar>(3)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  c0[2] = GodotReal.toC(a0.z)
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR3
  types[1] = PT_BOOL
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    2,
    PT_INT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithVector3AndDoubleArg(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3,
  a1: Double,
) = memScoped {
  val c0 = allocArray<GodotRealVar>(3)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  c0[2] = GodotReal.toC(a0.z)
  val c1 = alloc<DoubleVar>()
  c1.value = a1
  val types = allocArray<IntVar>(2)
  types[0] = PT_VECTOR3
  types[1] = PT_FLOAT64
  val ptrs = allocArray<COpaquePointerVar>(2)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
  Unit
}

fun ObjectCalls.ptrcallWithVector3ArgRetBool(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3,
): Boolean = memScoped {
  val ret = alloc<ByteVar>()
  val c0 = allocArray<GodotRealVar>(3)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  c0[2] = GodotReal.toC(a0.z)
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_BOOL,
    ret.ptr,
  )
  ret.value.toInt() != 0
}

fun ObjectCalls.ptrcallWithVector3ArgRetDouble(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3,
): Double = memScoped {
  val ret = alloc<DoubleVar>()
  val c0 = allocArray<GodotRealVar>(3)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  c0[2] = GodotReal.toC(a0.z)
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_FLOAT64,
    ret.ptr,
  )
  ret.value
}

fun ObjectCalls.ptrcallWithVector3ArgRetVector2(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3,
): Vector2 = memScoped {
  val ret = allocArray<GodotRealVar>(2)
  val c0 = allocArray<GodotRealVar>(3)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  c0[2] = GodotReal.toC(a0.z)
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR2,
    ret,
  )
  Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))
}

fun ObjectCalls.ptrcallWithVector3ArgRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = allocArray<GodotRealVar>(3)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  c0[2] = GodotReal.toC(a0.z)
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithVector3ArgRetVector3i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3,
): Vector3i = memScoped {
  val ret = allocArray<IntVar>(3)
  val c0 = allocArray<GodotRealVar>(3)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  c0[2] = GodotReal.toC(a0.z)
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR3
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR3I,
    ret,
  )
  Vector3i(ret[0], ret[1], ret[2])
}

fun ObjectCalls.ptrcallWithVector3BoolFloatBoolIntArgsRetObject(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3,
  a1: Boolean,
  a2: Double,
  a3: Boolean,
  a4: Int,
): MemorySegment = memScoped {
  val ret = alloc<LongVar>()
  ret.value = 0
  val c0 = allocArray<GodotRealVar>(3)
  c0[0] = GodotReal.toC(a0.x)
  c0[1] = GodotReal.toC(a0.y)
  c0[2] = GodotReal.toC(a0.z)
  val c1 = alloc<ByteVar>()
  c1.value = if (a1) 1 else 0
  val c2 = alloc<DoubleVar>()
  c2.value = a2
  val c3 = alloc<ByteVar>()
  c3.value = if (a3) 1 else 0
  val c4 = alloc<LongVar>()
  c4.value = a4.toLong()
  val types = allocArray<IntVar>(5)
  types[0] = PT_VECTOR3
  types[1] = PT_BOOL
  types[2] = PT_FLOAT64
  types[3] = PT_BOOL
  types[4] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(5)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  ptrs[3] = c3.ptr.reinterpret<CPointed>()
  ptrs[4] = c4.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    5,
    PT_OBJECT,
    ret.ptr,
  )
  MemorySegment.ofAddress(ret.value)
}

fun ObjectCalls.ptrcallWithVector3iArgRetBasis(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3i,
): Basis = memScoped {
  val ret = allocArray<GodotRealVar>(9)
  val c0 = allocArray<IntVar>(3)
  c0[0] = a0.x
  c0[1] = a0.y
  c0[2] = a0.z
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR3I
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_BASIS, ret)
  Basis(
    Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[3]), GodotReal.fromC(ret[6])),
    Vector3(GodotReal.fromC(ret[1]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[7])),
    Vector3(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[5]), GodotReal.fromC(ret[8])),
  )
}

fun ObjectCalls.ptrcallWithVector3iArgRetInt(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3i,
): Int = memScoped {
  val ret = alloc<LongVar>()
  val c0 = allocArray<IntVar>(3)
  c0[0] = a0.x
  c0[1] = a0.y
  c0[2] = a0.z
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR3I
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_INT64,
    ret.ptr,
  )
  ret.value.toInt()
}

fun ObjectCalls.ptrcallWithVector3iArgRetVector3(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3i,
): Vector3 = memScoped {
  val ret = allocArray<GodotRealVar>(3)
  val c0 = allocArray<IntVar>(3)
  c0[0] = a0.x
  c0[1] = a0.y
  c0[2] = a0.z
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR3I
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR3,
    ret,
  )
  Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))
}

fun ObjectCalls.ptrcallWithVector3iArgRetVector3i(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3i,
): Vector3i = memScoped {
  val ret = allocArray<IntVar>(3)
  val c0 = allocArray<IntVar>(3)
  c0[0] = a0.x
  c0[1] = a0.y
  c0[2] = a0.z
  val types = allocArray<IntVar>(1)
  types[0] = PT_VECTOR3I
  val ptrs = allocArray<COpaquePointerVar>(1)
  ptrs[0] = c0.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(
    methodBind.address(),
    instance.address(),
    types,
    ptrs,
    1,
    PT_VECTOR3I,
    ret,
  )
  Vector3i(ret[0], ret[1], ret[2])
}

fun ObjectCalls.ptrcallWithVector3iIntIntArgs(
  methodBind: MemorySegment,
  instance: MemorySegment,
  a0: Vector3i,
  a1: Int,
  a2: Int,
) = memScoped {
  val c0 = allocArray<IntVar>(3)
  c0[0] = a0.x
  c0[1] = a0.y
  c0[2] = a0.z
  val c1 = alloc<LongVar>()
  c1.value = a1.toLong()
  val c2 = alloc<LongVar>()
  c2.value = a2.toLong()
  val types = allocArray<IntVar>(3)
  types[0] = PT_VECTOR3I
  types[1] = PT_INT64
  types[2] = PT_INT64
  val ptrs = allocArray<COpaquePointerVar>(3)
  ptrs[0] = c0.reinterpret<CPointed>()
  ptrs[1] = c1.ptr.reinterpret<CPointed>()
  ptrs[2] = c2.ptr.reinterpret<CPointed>()
  kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
  Unit
}
