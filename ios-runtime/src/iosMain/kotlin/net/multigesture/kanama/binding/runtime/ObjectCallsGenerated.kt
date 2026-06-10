@file:OptIn(ExperimentalForeignApi::class)

package net.multigesture.kanama.binding.runtime

import java.lang.foreign.MemorySegment
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointed
import kotlinx.cinterop.COpaquePointerVar
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
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_ptrcall
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * GENERATED iOS ObjectCalls helper bodies (scripts/generate_api_wrapper.py --ios-*).
 * DO NOT EDIT BY HAND. Re-run the generator instead.
 *
 * Each helper is an extension on the hand-written `object ObjectCalls`, so the
 * generated Godot API wrappers' `ObjectCalls.<helper>(...)` calls resolve here. Every
 * helper marshals through the single generic C dispatch `kanama_ios_godot_ptrcall`,
 * applying the authoritative ptrcall width table (scalar float->double/8B, scalar
 * int->int64/8B, Vector components->float32, Object->8B handle, StringName built
 * C-side). Helpers already hand-written in ObjectCalls.kt are the override set and
 * are NOT regenerated here.
 */

private const val PT_VOID = 0
private const val PT_BOOL = 1
private const val PT_INT64 = 3
private const val PT_FLOAT64 = 5
private const val PT_VECTOR3 = 8
private const val PT_OBJECT = 13

fun ObjectCalls.ptrcallNoArgsRetUInt32(methodBind: MemorySegment, instance: MemorySegment): Long =
    memScoped {
        val ret = alloc<LongVar>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), null, null, 0, PT_INT64, ret.ptr)
        ret.value
    }

fun ObjectCalls.ptrcallWithIntAndBoolArgs(methodBind: MemorySegment, instance: MemorySegment, a0: Int, a1: Boolean) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0.toLong()
        val c1 = alloc<ByteVar>(); c1.value = if (a1) 1 else 0
        val types = allocArray<IntVar>(2); types[0] = PT_INT64; types[1] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithIntArgRetBool(methodBind: MemorySegment, instance: MemorySegment, a0: Int): Boolean =
    memScoped {
        val ret = alloc<ByteVar>()
        val c0 = alloc<LongVar>(); c0.value = a0.toLong()
        val types = allocArray<IntVar>(1); types[0] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_BOOL, ret.ptr)
        ret.value.toInt() != 0
    }

fun ObjectCalls.ptrcallWithIntArgRetObject(methodBind: MemorySegment, instance: MemorySegment, a0: Int): MemorySegment =
    memScoped {
        val ret = alloc<LongVar>(); ret.value = 0
        val c0 = alloc<LongVar>(); c0.value = a0.toLong()
        val types = allocArray<IntVar>(1); types[0] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_OBJECT, ret.ptr)
        MemorySegment.ofAddress(ret.value)
    }

fun ObjectCalls.ptrcallWithIntArgRetUInt32(methodBind: MemorySegment, instance: MemorySegment, a0: Int): Long =
    memScoped {
        val ret = alloc<LongVar>()
        val c0 = alloc<LongVar>(); c0.value = a0.toLong()
        val types = allocArray<IntVar>(1); types[0] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_INT64, ret.ptr)
        ret.value
    }

fun ObjectCalls.ptrcallWithLongAndBoolArgs(methodBind: MemorySegment, instance: MemorySegment, a0: Long, a1: Boolean) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0
        val c1 = alloc<ByteVar>(); c1.value = if (a1) 1 else 0
        val types = allocArray<IntVar>(2); types[0] = PT_INT64; types[1] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithLongArgRetBool(methodBind: MemorySegment, instance: MemorySegment, a0: Long): Boolean =
    memScoped {
        val ret = alloc<ByteVar>()
        val c0 = alloc<LongVar>(); c0.value = a0
        val types = allocArray<IntVar>(1); types[0] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_BOOL, ret.ptr)
        ret.value.toInt() != 0
    }

fun ObjectCalls.ptrcallWithObjectArgRetUInt32(methodBind: MemorySegment, instance: MemorySegment, a0: MemorySegment): Long =
    memScoped {
        val ret = alloc<LongVar>()
        val c0 = alloc<LongVar>(); c0.value = a0.address()
        val types = allocArray<IntVar>(1); types[0] = PT_OBJECT
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_INT64, ret.ptr)
        ret.value
    }

fun ObjectCalls.ptrcallWithThreeVector3AndBoolArgs(methodBind: MemorySegment, instance: MemorySegment, a0: Vector3, a1: Vector3, a2: Vector3, a3: Boolean) =
    memScoped {
        val c0 = allocArray<FloatVar>(3); c0[0] = a0.x.toFloat(); c0[1] = a0.y.toFloat(); c0[2] = a0.z.toFloat()
        val c1 = allocArray<FloatVar>(3); c1[0] = a1.x.toFloat(); c1[1] = a1.y.toFloat(); c1[2] = a1.z.toFloat()
        val c2 = allocArray<FloatVar>(3); c2[0] = a2.x.toFloat(); c2[1] = a2.y.toFloat(); c2[2] = a2.z.toFloat()
        val c3 = alloc<ByteVar>(); c3.value = if (a3) 1 else 0
        val types = allocArray<IntVar>(4); types[0] = PT_VECTOR3; types[1] = PT_VECTOR3; types[2] = PT_VECTOR3; types[3] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(4); ptrs[0] = c0.reinterpret<CPointed>(); ptrs[1] = c1.reinterpret<CPointed>(); ptrs[2] = c2.reinterpret<CPointed>(); ptrs[3] = c3.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithTwoVector3AndBoolArgs(methodBind: MemorySegment, instance: MemorySegment, a0: Vector3, a1: Vector3, a2: Boolean) =
    memScoped {
        val c0 = allocArray<FloatVar>(3); c0[0] = a0.x.toFloat(); c0[1] = a0.y.toFloat(); c0[2] = a0.z.toFloat()
        val c1 = allocArray<FloatVar>(3); c1[0] = a1.x.toFloat(); c1[1] = a1.y.toFloat(); c1[2] = a1.z.toFloat()
        val c2 = alloc<ByteVar>(); c2.value = if (a2) 1 else 0
        val types = allocArray<IntVar>(3); types[0] = PT_VECTOR3; types[1] = PT_VECTOR3; types[2] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(3); ptrs[0] = c0.reinterpret<CPointed>(); ptrs[1] = c1.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithUInt32AndBoolArgs(methodBind: MemorySegment, instance: MemorySegment, a0: Long, a1: Boolean) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0
        val c1 = alloc<ByteVar>(); c1.value = if (a1) 1 else 0
        val types = allocArray<IntVar>(2); types[0] = PT_INT64; types[1] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithUInt32AndIntArg(methodBind: MemorySegment, instance: MemorySegment, a0: Long, a1: Int) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0
        val c1 = alloc<LongVar>(); c1.value = a1.toLong()
        val types = allocArray<IntVar>(2); types[0] = PT_INT64; types[1] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithUInt32AndIntArgRetInt(methodBind: MemorySegment, instance: MemorySegment, a0: Long, a1: Int): Int =
    memScoped {
        val ret = alloc<LongVar>()
        val c0 = alloc<LongVar>(); c0.value = a0
        val c1 = alloc<LongVar>(); c1.value = a1.toLong()
        val types = allocArray<IntVar>(2); types[0] = PT_INT64; types[1] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_INT64, ret.ptr)
        ret.value.toInt()
    }

fun ObjectCalls.ptrcallWithUInt32AndIntArgRetObject(methodBind: MemorySegment, instance: MemorySegment, a0: Long, a1: Int): MemorySegment =
    memScoped {
        val ret = alloc<LongVar>(); ret.value = 0
        val c0 = alloc<LongVar>(); c0.value = a0
        val c1 = alloc<LongVar>(); c1.value = a1.toLong()
        val types = allocArray<IntVar>(2); types[0] = PT_INT64; types[1] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_OBJECT, ret.ptr)
        MemorySegment.ofAddress(ret.value)
    }

fun ObjectCalls.ptrcallWithUInt32AndObjectArg(methodBind: MemorySegment, instance: MemorySegment, a0: Long, a1: MemorySegment) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0
        val c1 = alloc<LongVar>(); c1.value = a1.address()
        val types = allocArray<IntVar>(2); types[0] = PT_INT64; types[1] = PT_OBJECT
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithUInt32Arg(methodBind: MemorySegment, instance: MemorySegment, a0: Long) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0
        val types = allocArray<IntVar>(1); types[0] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithUInt32ArgRetBool(methodBind: MemorySegment, instance: MemorySegment, a0: Long): Boolean =
    memScoped {
        val ret = alloc<ByteVar>()
        val c0 = alloc<LongVar>(); c0.value = a0
        val types = allocArray<IntVar>(1); types[0] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_BOOL, ret.ptr)
        ret.value.toInt() != 0
    }

fun ObjectCalls.ptrcallWithUInt32ArgRetInt(methodBind: MemorySegment, instance: MemorySegment, a0: Long): Int =
    memScoped {
        val ret = alloc<LongVar>()
        val c0 = alloc<LongVar>(); c0.value = a0
        val types = allocArray<IntVar>(1); types[0] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_INT64, ret.ptr)
        ret.value.toInt()
    }

fun ObjectCalls.ptrcallWithUInt32ArgRetObject(methodBind: MemorySegment, instance: MemorySegment, a0: Long): MemorySegment =
    memScoped {
        val ret = alloc<LongVar>(); ret.value = 0
        val c0 = alloc<LongVar>(); c0.value = a0
        val types = allocArray<IntVar>(1); types[0] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_OBJECT, ret.ptr)
        MemorySegment.ofAddress(ret.value)
    }

fun ObjectCalls.ptrcallWithVector3AndDoubleArg(methodBind: MemorySegment, instance: MemorySegment, a0: Vector3, a1: Double) =
    memScoped {
        val c0 = allocArray<FloatVar>(3); c0[0] = a0.x.toFloat(); c0[1] = a0.y.toFloat(); c0[2] = a0.z.toFloat()
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val types = allocArray<IntVar>(2); types[0] = PT_VECTOR3; types[1] = PT_FLOAT64
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithVector3ArgRetDouble(methodBind: MemorySegment, instance: MemorySegment, a0: Vector3): Double =
    memScoped {
        val ret = alloc<DoubleVar>()
        val c0 = allocArray<FloatVar>(3); c0[0] = a0.x.toFloat(); c0[1] = a0.y.toFloat(); c0[2] = a0.z.toFloat()
        val types = allocArray<IntVar>(1); types[0] = PT_VECTOR3
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_FLOAT64, ret.ptr)
        ret.value
    }

fun ObjectCalls.ptrcallWithVector3ArgRetVector3(methodBind: MemorySegment, instance: MemorySegment, a0: Vector3): Vector3 =
    memScoped {
        val ret = allocArray<FloatVar>(3)
        val c0 = allocArray<FloatVar>(3); c0[0] = a0.x.toFloat(); c0[1] = a0.y.toFloat(); c0[2] = a0.z.toFloat()
        val types = allocArray<IntVar>(1); types[0] = PT_VECTOR3
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VECTOR3, ret)
        Vector3(ret[0].toDouble(), ret[1].toDouble(), ret[2].toDouble())
    }

fun ObjectCalls.ptrcallWithVector3BoolFloatBoolIntArgsRetObject(methodBind: MemorySegment, instance: MemorySegment, a0: Vector3, a1: Boolean, a2: Double, a3: Boolean, a4: Int): MemorySegment =
    memScoped {
        val ret = alloc<LongVar>(); ret.value = 0
        val c0 = allocArray<FloatVar>(3); c0[0] = a0.x.toFloat(); c0[1] = a0.y.toFloat(); c0[2] = a0.z.toFloat()
        val c1 = alloc<ByteVar>(); c1.value = if (a1) 1 else 0
        val c2 = alloc<DoubleVar>(); c2.value = a2
        val c3 = alloc<ByteVar>(); c3.value = if (a3) 1 else 0
        val c4 = alloc<LongVar>(); c4.value = a4.toLong()
        val types = allocArray<IntVar>(5); types[0] = PT_VECTOR3; types[1] = PT_BOOL; types[2] = PT_FLOAT64; types[3] = PT_BOOL; types[4] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(5); ptrs[0] = c0.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>(); ptrs[3] = c3.ptr.reinterpret<CPointed>(); ptrs[4] = c4.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_OBJECT, ret.ptr)
        MemorySegment.ofAddress(ret.value)
    }
