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
private const val PT_VECTOR2 = 6
private const val PT_VECTOR3 = 8
private const val PT_OBJECT = 13
private const val PT_STRING_NAME = 15

fun ObjectCalls.ptrcallNoArgsRetUInt32(methodBind: MemorySegment, instance: MemorySegment): Long =
    memScoped {
        val ret = alloc<LongVar>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), null, null, 0, PT_INT64, ret.ptr)
        ret.value
    }

fun ObjectCalls.ptrcallWithBoolArgRetBool(methodBind: MemorySegment, instance: MemorySegment, a0: Boolean): Boolean =
    memScoped {
        val ret = alloc<ByteVar>()
        val c0 = alloc<ByteVar>(); c0.value = if (a0) 1 else 0
        val types = allocArray<IntVar>(1); types[0] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_BOOL, ret.ptr)
        ret.value.toInt() != 0
    }

fun ObjectCalls.ptrcallWithBoolArgRetInt(methodBind: MemorySegment, instance: MemorySegment, a0: Boolean): Int =
    memScoped {
        val ret = alloc<LongVar>()
        val c0 = alloc<ByteVar>(); c0.value = if (a0) 1 else 0
        val types = allocArray<IntVar>(1); types[0] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_INT64, ret.ptr)
        ret.value.toInt()
    }

fun ObjectCalls.ptrcallWithBoolArgRetObject(methodBind: MemorySegment, instance: MemorySegment, a0: Boolean): MemorySegment =
    memScoped {
        val ret = alloc<LongVar>(); ret.value = 0
        val c0 = alloc<ByteVar>(); c0.value = if (a0) 1 else 0
        val types = allocArray<IntVar>(1); types[0] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_OBJECT, ret.ptr)
        MemorySegment.ofAddress(ret.value)
    }

fun ObjectCalls.ptrcallWithDoubleAndBoolArgs(methodBind: MemorySegment, instance: MemorySegment, a0: Double, a1: Boolean) =
    memScoped {
        val c0 = alloc<DoubleVar>(); c0.value = a0
        val c1 = alloc<ByteVar>(); c1.value = if (a1) 1 else 0
        val types = allocArray<IntVar>(2); types[0] = PT_FLOAT64; types[1] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithDoubleAndTwoBoolArgs(methodBind: MemorySegment, instance: MemorySegment, a0: Double, a1: Boolean, a2: Boolean) =
    memScoped {
        val c0 = alloc<DoubleVar>(); c0.value = a0
        val c1 = alloc<ByteVar>(); c1.value = if (a1) 1 else 0
        val c2 = alloc<ByteVar>(); c2.value = if (a2) 1 else 0
        val types = allocArray<IntVar>(3); types[0] = PT_FLOAT64; types[1] = PT_BOOL; types[2] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(3); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithDoubleVector2TwoDoubleArgs(methodBind: MemorySegment, instance: MemorySegment, a0: Double, a1: Vector2, a2: Double, a3: Double) =
    memScoped {
        val c0 = alloc<DoubleVar>(); c0.value = a0
        val c1 = allocArray<FloatVar>(2); c1[0] = a1.x.toFloat(); c1[1] = a1.y.toFloat()
        val c2 = alloc<DoubleVar>(); c2.value = a2
        val c3 = alloc<DoubleVar>(); c3.value = a3
        val types = allocArray<IntVar>(4); types[0] = PT_FLOAT64; types[1] = PT_VECTOR2; types[2] = PT_FLOAT64; types[3] = PT_FLOAT64
        val ptrs = allocArray<COpaquePointerVar>(4); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>(); ptrs[3] = c3.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithFourDoubleArgs(methodBind: MemorySegment, instance: MemorySegment, a0: Double, a1: Double, a2: Double, a3: Double) =
    memScoped {
        val c0 = alloc<DoubleVar>(); c0.value = a0
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val c2 = alloc<DoubleVar>(); c2.value = a2
        val c3 = alloc<DoubleVar>(); c3.value = a3
        val types = allocArray<IntVar>(4); types[0] = PT_FLOAT64; types[1] = PT_FLOAT64; types[2] = PT_FLOAT64; types[3] = PT_FLOAT64
        val ptrs = allocArray<COpaquePointerVar>(4); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>(); ptrs[3] = c3.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithFourStringNameAndFloatArgRetVector2(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: String, a2: String, a3: String, a4: Double): Vector2 =
    memScoped {
        val ret = allocArray<FloatVar>(2)
        val c4 = alloc<DoubleVar>(); c4.value = a4
        val types = allocArray<IntVar>(5); types[0] = PT_STRING_NAME; types[1] = PT_STRING_NAME; types[2] = PT_STRING_NAME; types[3] = PT_STRING_NAME; types[4] = PT_FLOAT64
        val ptrs = allocArray<COpaquePointerVar>(5); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>(); ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>(); ptrs[3] = a3.cstr.ptr.reinterpret<CPointed>(); ptrs[4] = c4.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 5, PT_VECTOR2, ret)
        Vector2(ret[0].toDouble(), ret[1].toDouble())
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

fun ObjectCalls.ptrcallWithIntAndBoolArgsRetObject(methodBind: MemorySegment, instance: MemorySegment, a0: Int, a1: Boolean): MemorySegment =
    memScoped {
        val ret = alloc<LongVar>(); ret.value = 0
        val c0 = alloc<LongVar>(); c0.value = a0.toLong()
        val c1 = alloc<ByteVar>(); c1.value = if (a1) 1 else 0
        val types = allocArray<IntVar>(2); types[0] = PT_INT64; types[1] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_OBJECT, ret.ptr)
        MemorySegment.ofAddress(ret.value)
    }

fun ObjectCalls.ptrcallWithIntAndDoubleArg(methodBind: MemorySegment, instance: MemorySegment, a0: Int, a1: Double) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0.toLong()
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val types = allocArray<IntVar>(2); types[0] = PT_INT64; types[1] = PT_FLOAT64
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithIntAndLongArgs(methodBind: MemorySegment, instance: MemorySegment, a0: Int, a1: Long) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0.toLong()
        val c1 = alloc<LongVar>(); c1.value = a1
        val types = allocArray<IntVar>(2); types[0] = PT_INT64; types[1] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithIntAndLongArgsRetBool(methodBind: MemorySegment, instance: MemorySegment, a0: Int, a1: Long): Boolean =
    memScoped {
        val ret = alloc<ByteVar>()
        val c0 = alloc<LongVar>(); c0.value = a0.toLong()
        val c1 = alloc<LongVar>(); c1.value = a1
        val types = allocArray<IntVar>(2); types[0] = PT_INT64; types[1] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_BOOL, ret.ptr)
        ret.value.toInt() != 0
    }

fun ObjectCalls.ptrcallWithIntAndLongArgsRetDouble(methodBind: MemorySegment, instance: MemorySegment, a0: Int, a1: Long): Double =
    memScoped {
        val ret = alloc<DoubleVar>()
        val c0 = alloc<LongVar>(); c0.value = a0.toLong()
        val c1 = alloc<LongVar>(); c1.value = a1
        val types = allocArray<IntVar>(2); types[0] = PT_INT64; types[1] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_FLOAT64, ret.ptr)
        ret.value
    }

fun ObjectCalls.ptrcallWithIntAndThreeDoubleArgs(methodBind: MemorySegment, instance: MemorySegment, a0: Int, a1: Double, a2: Double, a3: Double) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0.toLong()
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val c2 = alloc<DoubleVar>(); c2.value = a2
        val c3 = alloc<DoubleVar>(); c3.value = a3
        val types = allocArray<IntVar>(4); types[0] = PT_INT64; types[1] = PT_FLOAT64; types[2] = PT_FLOAT64; types[3] = PT_FLOAT64
        val ptrs = allocArray<COpaquePointerVar>(4); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>(); ptrs[3] = c3.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
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

fun ObjectCalls.ptrcallWithIntArgRetDouble(methodBind: MemorySegment, instance: MemorySegment, a0: Int): Double =
    memScoped {
        val ret = alloc<DoubleVar>()
        val c0 = alloc<LongVar>(); c0.value = a0.toLong()
        val types = allocArray<IntVar>(1); types[0] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_FLOAT64, ret.ptr)
        ret.value
    }

fun ObjectCalls.ptrcallWithIntArgRetInt(methodBind: MemorySegment, instance: MemorySegment, a0: Int): Int =
    memScoped {
        val ret = alloc<LongVar>()
        val c0 = alloc<LongVar>(); c0.value = a0.toLong()
        val types = allocArray<IntVar>(1); types[0] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_INT64, ret.ptr)
        ret.value.toInt()
    }

fun ObjectCalls.ptrcallWithIntArgRetLong(methodBind: MemorySegment, instance: MemorySegment, a0: Int): Long =
    memScoped {
        val ret = alloc<LongVar>()
        val c0 = alloc<LongVar>(); c0.value = a0.toLong()
        val types = allocArray<IntVar>(1); types[0] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_INT64, ret.ptr)
        ret.value
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

fun ObjectCalls.ptrcallWithIntArgRetVector2(methodBind: MemorySegment, instance: MemorySegment, a0: Int): Vector2 =
    memScoped {
        val ret = allocArray<FloatVar>(2)
        val c0 = alloc<LongVar>(); c0.value = a0.toLong()
        val types = allocArray<IntVar>(1); types[0] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VECTOR2, ret)
        Vector2(ret[0].toDouble(), ret[1].toDouble())
    }

fun ObjectCalls.ptrcallWithIntArgRetVector3(methodBind: MemorySegment, instance: MemorySegment, a0: Int): Vector3 =
    memScoped {
        val ret = allocArray<FloatVar>(3)
        val c0 = alloc<LongVar>(); c0.value = a0.toLong()
        val types = allocArray<IntVar>(1); types[0] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VECTOR3, ret)
        Vector3(ret[0].toDouble(), ret[1].toDouble(), ret[2].toDouble())
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

fun ObjectCalls.ptrcallWithLongAndDoubleArg(methodBind: MemorySegment, instance: MemorySegment, a0: Long, a1: Double) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val types = allocArray<IntVar>(2); types[0] = PT_INT64; types[1] = PT_FLOAT64
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

fun ObjectCalls.ptrcallWithLongArgRetDouble(methodBind: MemorySegment, instance: MemorySegment, a0: Long): Double =
    memScoped {
        val ret = alloc<DoubleVar>()
        val c0 = alloc<LongVar>(); c0.value = a0
        val types = allocArray<IntVar>(1); types[0] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_FLOAT64, ret.ptr)
        ret.value
    }

fun ObjectCalls.ptrcallWithLongArgRetObject(methodBind: MemorySegment, instance: MemorySegment, a0: Long): MemorySegment =
    memScoped {
        val ret = alloc<LongVar>(); ret.value = 0
        val c0 = alloc<LongVar>(); c0.value = a0
        val types = allocArray<IntVar>(1); types[0] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_OBJECT, ret.ptr)
        MemorySegment.ofAddress(ret.value)
    }

fun ObjectCalls.ptrcallWithLongDoubleTwoBoolArgs(methodBind: MemorySegment, instance: MemorySegment, a0: Long, a1: Double, a2: Boolean, a3: Boolean) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val c2 = alloc<ByteVar>(); c2.value = if (a2) 1 else 0
        val c3 = alloc<ByteVar>(); c3.value = if (a3) 1 else 0
        val types = allocArray<IntVar>(4); types[0] = PT_INT64; types[1] = PT_FLOAT64; types[2] = PT_BOOL; types[3] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(4); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>(); ptrs[3] = c3.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithLongTwoDoubleAndBoolArgs(methodBind: MemorySegment, instance: MemorySegment, a0: Long, a1: Double, a2: Double, a3: Boolean) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val c2 = alloc<DoubleVar>(); c2.value = a2
        val c3 = alloc<ByteVar>(); c3.value = if (a3) 1 else 0
        val types = allocArray<IntVar>(4); types[0] = PT_INT64; types[1] = PT_FLOAT64; types[2] = PT_FLOAT64; types[3] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(4); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>(); ptrs[3] = c3.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithObjectAndBoolArg(methodBind: MemorySegment, instance: MemorySegment, a0: MemorySegment, a1: Boolean) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0.address()
        val c1 = alloc<ByteVar>(); c1.value = if (a1) 1 else 0
        val types = allocArray<IntVar>(2); types[0] = PT_OBJECT; types[1] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithObjectAndIntArg(methodBind: MemorySegment, instance: MemorySegment, a0: MemorySegment, a1: Int) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0.address()
        val c1 = alloc<LongVar>(); c1.value = a1.toLong()
        val types = allocArray<IntVar>(2); types[0] = PT_OBJECT; types[1] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithObjectArgRetBool(methodBind: MemorySegment, instance: MemorySegment, a0: MemorySegment): Boolean =
    memScoped {
        val ret = alloc<ByteVar>()
        val c0 = alloc<LongVar>(); c0.value = a0.address()
        val types = allocArray<IntVar>(1); types[0] = PT_OBJECT
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_BOOL, ret.ptr)
        ret.value.toInt() != 0
    }

fun ObjectCalls.ptrcallWithObjectArgRetLong(methodBind: MemorySegment, instance: MemorySegment, a0: MemorySegment): Long =
    memScoped {
        val ret = alloc<LongVar>()
        val c0 = alloc<LongVar>(); c0.value = a0.address()
        val types = allocArray<IntVar>(1); types[0] = PT_OBJECT
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_INT64, ret.ptr)
        ret.value
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

fun ObjectCalls.ptrcallWithObjectBoolLongArgs(methodBind: MemorySegment, instance: MemorySegment, a0: MemorySegment, a1: Boolean, a2: Long) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0.address()
        val c1 = alloc<ByteVar>(); c1.value = if (a1) 1 else 0
        val c2 = alloc<LongVar>(); c2.value = a2
        val types = allocArray<IntVar>(3); types[0] = PT_OBJECT; types[1] = PT_BOOL; types[2] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(3); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithObjectLongAndVector2Arg(methodBind: MemorySegment, instance: MemorySegment, a0: MemorySegment, a1: Long, a2: Vector2) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0.address()
        val c1 = alloc<LongVar>(); c1.value = a1
        val c2 = allocArray<FloatVar>(2); c2[0] = a2.x.toFloat(); c2[1] = a2.y.toFloat()
        val types = allocArray<IntVar>(3); types[0] = PT_OBJECT; types[1] = PT_INT64; types[2] = PT_VECTOR2
        val ptrs = allocArray<COpaquePointerVar>(3); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>(); ptrs[2] = c2.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithStringNameAndBoolArg(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: Boolean) =
    memScoped {
        val c1 = alloc<ByteVar>(); c1.value = if (a1) 1 else 0
        val types = allocArray<IntVar>(2); types[0] = PT_STRING_NAME; types[1] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithStringNameAndBoolArgRetBool(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: Boolean): Boolean =
    memScoped {
        val ret = alloc<ByteVar>()
        val c1 = alloc<ByteVar>(); c1.value = if (a1) 1 else 0
        val types = allocArray<IntVar>(2); types[0] = PT_STRING_NAME; types[1] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_BOOL, ret.ptr)
        ret.value.toInt() != 0
    }

fun ObjectCalls.ptrcallWithStringNameAndBoolArgRetDouble(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: Boolean): Double =
    memScoped {
        val ret = alloc<DoubleVar>()
        val c1 = alloc<ByteVar>(); c1.value = if (a1) 1 else 0
        val types = allocArray<IntVar>(2); types[0] = PT_STRING_NAME; types[1] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_FLOAT64, ret.ptr)
        ret.value
    }

fun ObjectCalls.ptrcallWithStringNameAndDoubleArg(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: Double) =
    memScoped {
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val types = allocArray<IntVar>(2); types[0] = PT_STRING_NAME; types[1] = PT_FLOAT64
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithStringNameAndIntArg(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: Int) =
    memScoped {
        val c1 = alloc<LongVar>(); c1.value = a1.toLong()
        val types = allocArray<IntVar>(2); types[0] = PT_STRING_NAME; types[1] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithStringNameAndObjectArg(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: MemorySegment) =
    memScoped {
        val c1 = alloc<LongVar>(); c1.value = a1.address()
        val types = allocArray<IntVar>(2); types[0] = PT_STRING_NAME; types[1] = PT_OBJECT
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithStringNameAndThreeDoubleArgs(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: Double, a2: Double, a3: Double) =
    memScoped {
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val c2 = alloc<DoubleVar>(); c2.value = a2
        val c3 = alloc<DoubleVar>(); c3.value = a3
        val types = allocArray<IntVar>(4); types[0] = PT_STRING_NAME; types[1] = PT_FLOAT64; types[2] = PT_FLOAT64; types[3] = PT_FLOAT64
        val ptrs = allocArray<COpaquePointerVar>(4); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>(); ptrs[3] = c3.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithStringNameArg(methodBind: MemorySegment, instance: MemorySegment, a0: String) =
    memScoped {
        val types = allocArray<IntVar>(1); types[0] = PT_STRING_NAME
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithStringNameArgRetBool(methodBind: MemorySegment, instance: MemorySegment, a0: String): Boolean =
    memScoped {
        val ret = alloc<ByteVar>()
        val types = allocArray<IntVar>(1); types[0] = PT_STRING_NAME
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_BOOL, ret.ptr)
        ret.value.toInt() != 0
    }

fun ObjectCalls.ptrcallWithStringNameDoubleDoubleBoolArgs(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: Double, a2: Double, a3: Boolean) =
    memScoped {
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val c2 = alloc<DoubleVar>(); c2.value = a2
        val c3 = alloc<ByteVar>(); c3.value = if (a3) 1 else 0
        val types = allocArray<IntVar>(4); types[0] = PT_STRING_NAME; types[1] = PT_FLOAT64; types[2] = PT_FLOAT64; types[3] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(4); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>(); ptrs[3] = c3.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithStringNameDoubleTwoLongArgs(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: Double, a2: Long, a3: Long) =
    memScoped {
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val c2 = alloc<LongVar>(); c2.value = a2
        val c3 = alloc<LongVar>(); c3.value = a3
        val types = allocArray<IntVar>(4); types[0] = PT_STRING_NAME; types[1] = PT_FLOAT64; types[2] = PT_INT64; types[3] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(4); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>(); ptrs[3] = c3.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithStringNameFourDoubleBoolArgs(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: Double, a2: Double, a3: Double, a4: Double, a5: Boolean) =
    memScoped {
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val c2 = alloc<DoubleVar>(); c2.value = a2
        val c3 = alloc<DoubleVar>(); c3.value = a3
        val c4 = alloc<DoubleVar>(); c4.value = a4
        val c5 = alloc<ByteVar>(); c5.value = if (a5) 1 else 0
        val types = allocArray<IntVar>(6); types[0] = PT_STRING_NAME; types[1] = PT_FLOAT64; types[2] = PT_FLOAT64; types[3] = PT_FLOAT64; types[4] = PT_FLOAT64; types[5] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(6); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>(); ptrs[3] = c3.ptr.reinterpret<CPointed>(); ptrs[4] = c4.ptr.reinterpret<CPointed>(); ptrs[5] = c5.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithStringNameThreeDoubleBoolTwoLongArgs(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: Double, a2: Double, a3: Double, a4: Boolean, a5: Long, a6: Long) =
    memScoped {
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val c2 = alloc<DoubleVar>(); c2.value = a2
        val c3 = alloc<DoubleVar>(); c3.value = a3
        val c4 = alloc<ByteVar>(); c4.value = if (a4) 1 else 0
        val c5 = alloc<LongVar>(); c5.value = a5
        val c6 = alloc<LongVar>(); c6.value = a6
        val types = allocArray<IntVar>(7); types[0] = PT_STRING_NAME; types[1] = PT_FLOAT64; types[2] = PT_FLOAT64; types[3] = PT_FLOAT64; types[4] = PT_BOOL; types[5] = PT_INT64; types[6] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(7); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>(); ptrs[3] = c3.ptr.reinterpret<CPointed>(); ptrs[4] = c4.ptr.reinterpret<CPointed>(); ptrs[5] = c5.ptr.reinterpret<CPointed>(); ptrs[6] = c6.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 7, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithThreeDoubleArgs(methodBind: MemorySegment, instance: MemorySegment, a0: Double, a1: Double, a2: Double) =
    memScoped {
        val c0 = alloc<DoubleVar>(); c0.value = a0
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val c2 = alloc<DoubleVar>(); c2.value = a2
        val types = allocArray<IntVar>(3); types[0] = PT_FLOAT64; types[1] = PT_FLOAT64; types[2] = PT_FLOAT64
        val ptrs = allocArray<COpaquePointerVar>(3); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithThreeStringNameAndDoubleArg(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: String, a2: String, a3: Double) =
    memScoped {
        val c3 = alloc<DoubleVar>(); c3.value = a3
        val types = allocArray<IntVar>(4); types[0] = PT_STRING_NAME; types[1] = PT_STRING_NAME; types[2] = PT_STRING_NAME; types[3] = PT_FLOAT64
        val ptrs = allocArray<COpaquePointerVar>(4); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>(); ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>(); ptrs[3] = c3.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 4, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithThreeStringNameTwoDoubleBoolArgs(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: String, a2: String, a3: Double, a4: Double, a5: Boolean) =
    memScoped {
        val c3 = alloc<DoubleVar>(); c3.value = a3
        val c4 = alloc<DoubleVar>(); c4.value = a4
        val c5 = alloc<ByteVar>(); c5.value = if (a5) 1 else 0
        val types = allocArray<IntVar>(6); types[0] = PT_STRING_NAME; types[1] = PT_STRING_NAME; types[2] = PT_STRING_NAME; types[3] = PT_FLOAT64; types[4] = PT_FLOAT64; types[5] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(6); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>(); ptrs[2] = a2.cstr.ptr.reinterpret<CPointed>(); ptrs[3] = c3.ptr.reinterpret<CPointed>(); ptrs[4] = c4.ptr.reinterpret<CPointed>(); ptrs[5] = c5.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 6, PT_VOID, null)
        Unit
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

fun ObjectCalls.ptrcallWithTwoDoubleArgs(methodBind: MemorySegment, instance: MemorySegment, a0: Double, a1: Double) =
    memScoped {
        val c0 = alloc<DoubleVar>(); c0.value = a0
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val types = allocArray<IntVar>(2); types[0] = PT_FLOAT64; types[1] = PT_FLOAT64
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithTwoIntArgsRetBool(methodBind: MemorySegment, instance: MemorySegment, a0: Int, a1: Int): Boolean =
    memScoped {
        val ret = alloc<ByteVar>()
        val c0 = alloc<LongVar>(); c0.value = a0.toLong()
        val c1 = alloc<LongVar>(); c1.value = a1.toLong()
        val types = allocArray<IntVar>(2); types[0] = PT_INT64; types[1] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_BOOL, ret.ptr)
        ret.value.toInt() != 0
    }

fun ObjectCalls.ptrcallWithTwoLongAndIntArgs(methodBind: MemorySegment, instance: MemorySegment, a0: Long, a1: Long, a2: Int) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0
        val c1 = alloc<LongVar>(); c1.value = a1
        val c2 = alloc<LongVar>(); c2.value = a2.toLong()
        val types = allocArray<IntVar>(3); types[0] = PT_INT64; types[1] = PT_INT64; types[2] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(3); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithTwoLongArgsRetInt(methodBind: MemorySegment, instance: MemorySegment, a0: Long, a1: Long): Int =
    memScoped {
        val ret = alloc<LongVar>()
        val c0 = alloc<LongVar>(); c0.value = a0
        val c1 = alloc<LongVar>(); c1.value = a1
        val types = allocArray<IntVar>(2); types[0] = PT_INT64; types[1] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.ptr.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_INT64, ret.ptr)
        ret.value.toInt()
    }

fun ObjectCalls.ptrcallWithTwoStringNameAndDoubleArg(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: String, a2: Double) =
    memScoped {
        val c2 = alloc<DoubleVar>(); c2.value = a2
        val types = allocArray<IntVar>(3); types[0] = PT_STRING_NAME; types[1] = PT_STRING_NAME; types[2] = PT_FLOAT64
        val ptrs = allocArray<COpaquePointerVar>(3); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>(); ptrs[2] = c2.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithTwoStringNameArgs(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: String) =
    memScoped {
        val types = allocArray<IntVar>(2); types[0] = PT_STRING_NAME; types[1] = PT_STRING_NAME
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: String): Boolean =
    memScoped {
        val ret = alloc<ByteVar>()
        val types = allocArray<IntVar>(2); types[0] = PT_STRING_NAME; types[1] = PT_STRING_NAME
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_BOOL, ret.ptr)
        ret.value.toInt() != 0
    }

fun ObjectCalls.ptrcallWithTwoStringNameArgsRetDouble(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: String): Double =
    memScoped {
        val ret = alloc<DoubleVar>()
        val types = allocArray<IntVar>(2); types[0] = PT_STRING_NAME; types[1] = PT_STRING_NAME
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_FLOAT64, ret.ptr)
        ret.value
    }

fun ObjectCalls.ptrcallWithTwoStringNameArgsRetInt(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: String): Int =
    memScoped {
        val ret = alloc<LongVar>()
        val types = allocArray<IntVar>(2); types[0] = PT_STRING_NAME; types[1] = PT_STRING_NAME
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_INT64, ret.ptr)
        ret.value.toInt()
    }

fun ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(methodBind: MemorySegment, instance: MemorySegment, a0: String, a1: String): MemorySegment =
    memScoped {
        val ret = alloc<LongVar>(); ret.value = 0
        val types = allocArray<IntVar>(2); types[0] = PT_STRING_NAME; types[1] = PT_STRING_NAME
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = a0.cstr.ptr.reinterpret<CPointed>(); ptrs[1] = a1.cstr.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_OBJECT, ret.ptr)
        MemorySegment.ofAddress(ret.value)
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

fun ObjectCalls.ptrcallWithUInt32AndDoubleArg(methodBind: MemorySegment, instance: MemorySegment, a0: Long, a1: Double) =
    memScoped {
        val c0 = alloc<LongVar>(); c0.value = a0
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val types = allocArray<IntVar>(2); types[0] = PT_INT64; types[1] = PT_FLOAT64
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

fun ObjectCalls.ptrcallWithUInt32ArgRetDouble(methodBind: MemorySegment, instance: MemorySegment, a0: Long): Double =
    memScoped {
        val ret = alloc<DoubleVar>()
        val c0 = alloc<LongVar>(); c0.value = a0
        val types = allocArray<IntVar>(1); types[0] = PT_INT64
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_FLOAT64, ret.ptr)
        ret.value
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

fun ObjectCalls.ptrcallWithVector2AndBoolArg(methodBind: MemorySegment, instance: MemorySegment, a0: Vector2, a1: Boolean) =
    memScoped {
        val c0 = allocArray<FloatVar>(2); c0[0] = a0.x.toFloat(); c0[1] = a0.y.toFloat()
        val c1 = alloc<ByteVar>(); c1.value = if (a1) 1 else 0
        val types = allocArray<IntVar>(2); types[0] = PT_VECTOR2; types[1] = PT_BOOL
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VOID, null)
        Unit
    }

fun ObjectCalls.ptrcallWithVector2AndDoubleArgRetVector3(methodBind: MemorySegment, instance: MemorySegment, a0: Vector2, a1: Double): Vector3 =
    memScoped {
        val ret = allocArray<FloatVar>(3)
        val c0 = allocArray<FloatVar>(2); c0[0] = a0.x.toFloat(); c0[1] = a0.y.toFloat()
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val types = allocArray<IntVar>(2); types[0] = PT_VECTOR2; types[1] = PT_FLOAT64
        val ptrs = allocArray<COpaquePointerVar>(2); ptrs[0] = c0.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 2, PT_VECTOR3, ret)
        Vector3(ret[0].toDouble(), ret[1].toDouble(), ret[2].toDouble())
    }

fun ObjectCalls.ptrcallWithVector2ArgRetBool(methodBind: MemorySegment, instance: MemorySegment, a0: Vector2): Boolean =
    memScoped {
        val ret = alloc<ByteVar>()
        val c0 = allocArray<FloatVar>(2); c0[0] = a0.x.toFloat(); c0[1] = a0.y.toFloat()
        val types = allocArray<IntVar>(1); types[0] = PT_VECTOR2
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_BOOL, ret.ptr)
        ret.value.toInt() != 0
    }

fun ObjectCalls.ptrcallWithVector2ArgRetDouble(methodBind: MemorySegment, instance: MemorySegment, a0: Vector2): Double =
    memScoped {
        val ret = alloc<DoubleVar>()
        val c0 = allocArray<FloatVar>(2); c0[0] = a0.x.toFloat(); c0[1] = a0.y.toFloat()
        val types = allocArray<IntVar>(1); types[0] = PT_VECTOR2
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_FLOAT64, ret.ptr)
        ret.value
    }

fun ObjectCalls.ptrcallWithVector2ArgRetLong(methodBind: MemorySegment, instance: MemorySegment, a0: Vector2): Long =
    memScoped {
        val ret = alloc<LongVar>()
        val c0 = allocArray<FloatVar>(2); c0[0] = a0.x.toFloat(); c0[1] = a0.y.toFloat()
        val types = allocArray<IntVar>(1); types[0] = PT_VECTOR2
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_INT64, ret.ptr)
        ret.value
    }

fun ObjectCalls.ptrcallWithVector2ArgRetVector2(methodBind: MemorySegment, instance: MemorySegment, a0: Vector2): Vector2 =
    memScoped {
        val ret = allocArray<FloatVar>(2)
        val c0 = allocArray<FloatVar>(2); c0[0] = a0.x.toFloat(); c0[1] = a0.y.toFloat()
        val types = allocArray<IntVar>(1); types[0] = PT_VECTOR2
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VECTOR2, ret)
        Vector2(ret[0].toDouble(), ret[1].toDouble())
    }

fun ObjectCalls.ptrcallWithVector2ArgRetVector3(methodBind: MemorySegment, instance: MemorySegment, a0: Vector2): Vector3 =
    memScoped {
        val ret = allocArray<FloatVar>(3)
        val c0 = allocArray<FloatVar>(2); c0[0] = a0.x.toFloat(); c0[1] = a0.y.toFloat()
        val types = allocArray<IntVar>(1); types[0] = PT_VECTOR2
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VECTOR3, ret)
        Vector3(ret[0].toDouble(), ret[1].toDouble(), ret[2].toDouble())
    }

fun ObjectCalls.ptrcallWithVector2DoubleVector2Args(methodBind: MemorySegment, instance: MemorySegment, a0: Vector2, a1: Double, a2: Vector2) =
    memScoped {
        val c0 = allocArray<FloatVar>(2); c0[0] = a0.x.toFloat(); c0[1] = a0.y.toFloat()
        val c1 = alloc<DoubleVar>(); c1.value = a1
        val c2 = allocArray<FloatVar>(2); c2[0] = a2.x.toFloat(); c2[1] = a2.y.toFloat()
        val types = allocArray<IntVar>(3); types[0] = PT_VECTOR2; types[1] = PT_FLOAT64; types[2] = PT_VECTOR2
        val ptrs = allocArray<COpaquePointerVar>(3); ptrs[0] = c0.reinterpret<CPointed>(); ptrs[1] = c1.ptr.reinterpret<CPointed>(); ptrs[2] = c2.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 3, PT_VOID, null)
        Unit
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

fun ObjectCalls.ptrcallWithVector3ArgRetBool(methodBind: MemorySegment, instance: MemorySegment, a0: Vector3): Boolean =
    memScoped {
        val ret = alloc<ByteVar>()
        val c0 = allocArray<FloatVar>(3); c0[0] = a0.x.toFloat(); c0[1] = a0.y.toFloat(); c0[2] = a0.z.toFloat()
        val types = allocArray<IntVar>(1); types[0] = PT_VECTOR3
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_BOOL, ret.ptr)
        ret.value.toInt() != 0
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

fun ObjectCalls.ptrcallWithVector3ArgRetVector2(methodBind: MemorySegment, instance: MemorySegment, a0: Vector3): Vector2 =
    memScoped {
        val ret = allocArray<FloatVar>(2)
        val c0 = allocArray<FloatVar>(3); c0[0] = a0.x.toFloat(); c0[1] = a0.y.toFloat(); c0[2] = a0.z.toFloat()
        val types = allocArray<IntVar>(1); types[0] = PT_VECTOR3
        val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = c0.reinterpret<CPointed>()
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VECTOR2, ret)
        Vector2(ret[0].toDouble(), ret[1].toDouble())
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
