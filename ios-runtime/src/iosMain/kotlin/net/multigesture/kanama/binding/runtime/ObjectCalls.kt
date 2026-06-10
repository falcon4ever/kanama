@file:OptIn(ExperimentalForeignApi::class)

package net.multigesture.kanama.binding.runtime

import java.lang.foreign.MemorySegment
import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.CName
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
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.plus
import kotlinx.cinterop.ptr
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.set
import kotlinx.cinterop.value
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_construct_object
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_get_method_bind
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_get_singleton
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_ptrcall
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * iOS implementation of the runtime abstraction the generated Godot API wrappers
 * call (mirrors the desktop `ObjectCalls`). Every helper routes through the single
 * generic C dispatch `kanama_ios_godot_ptrcall`; Kotlin lays out the POD/struct/object
 * arg bytes here, the C side passes them straight to `object_method_bind_ptrcall`.
 *
 * Marshalling rules (see docs/internals/ios-backend-architecture.md):
 * - SCALAR `float` args/returns are 8-byte `double` at ptrcall (PtrToArg<float> =
 *   convert<float,double>) — so a Godot `float` scalar uses the `*Double*` helpers.
 * - Vector/Color COMPONENTS are real_t = 4-byte float32 in single-precision builds —
 *   so Vector2/Vector3 marshal their Double components as float32.
 */
object ObjectCalls {
    // ptrcall type tags — must match the KANAMA_IOS_PT_* enum in kanama_ios_shim.c.
    private const val PT_VOID = 0
    private const val PT_BOOL = 1
    private const val PT_INT32 = 2
    private const val PT_INT64 = 3
    private const val PT_FLOAT64 = 5
    private const val PT_VECTOR2 = 6
    private const val PT_VECTOR3 = 8
    private const val PT_OBJECT = 13

    fun constructObject(className: String): MemorySegment =
        MemorySegment.ofAddress(kanama_ios_godot_construct_object(className))

    fun getMethodBind(className: String, methodName: String, hash: Long): MemorySegment =
        MemorySegment.ofAddress(kanama_ios_godot_get_method_bind(className, methodName, hash))

    // Resolve a Godot engine singleton (Input, Engine, …). Mirrors desktop ObjectCalls;
    // used by the bespoke Input glue (and generated singleton wrappers, longer term).
    fun getSingleton(className: String): MemorySegment =
        MemorySegment.ofAddress(kanama_ios_godot_get_singleton(className))

    // ---- no-arg ----
    fun ptrcallNoArgs(methodBind: MemorySegment, instance: MemorySegment) {
        kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), null, null, 0, PT_VOID, null)
    }

    fun ptrcallNoArgsRetBool(methodBind: MemorySegment, instance: MemorySegment): Boolean =
        memScoped {
            val ret = alloc<ByteVar>()
            kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), null, null, 0, PT_BOOL, ret.ptr)
            ret.value.toInt() != 0
        }

    // All Godot scalar ints are 8 bytes (int64) at ptrcall (PtrToArg<int32_t> =
    // convert<int32_t,int64_t>), so int returns read 8 bytes then narrow.
    fun ptrcallNoArgsRetInt(methodBind: MemorySegment, instance: MemorySegment): Int =
        memScoped {
            val ret = alloc<LongVar>()
            kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), null, null, 0, PT_INT64, ret.ptr)
            ret.value.toInt()
        }

    fun ptrcallNoArgsRetLong(methodBind: MemorySegment, instance: MemorySegment): Long =
        memScoped {
            val ret = alloc<LongVar>()
            kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), null, null, 0, PT_INT64, ret.ptr)
            ret.value
        }

    fun ptrcallNoArgsRetDouble(methodBind: MemorySegment, instance: MemorySegment): Double =
        memScoped {
            val ret = alloc<DoubleVar>()
            kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), null, null, 0, PT_FLOAT64, ret.ptr)
            ret.value
        }

    fun ptrcallNoArgsRetObject(methodBind: MemorySegment, instance: MemorySegment): MemorySegment =
        memScoped {
            val ret = alloc<LongVar>()
            ret.value = 0
            kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), null, null, 0, PT_OBJECT, ret.ptr)
            MemorySegment.ofAddress(ret.value)
        }

    fun ptrcallNoArgsRetVector2(methodBind: MemorySegment, instance: MemorySegment): Vector2 =
        memScoped {
            val ret = allocArray<FloatVar>(2)
            kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), null, null, 0, PT_VECTOR2, ret)
            Vector2(ret[0].toDouble(), ret[1].toDouble())
        }

    fun ptrcallNoArgsRetVector3(methodBind: MemorySegment, instance: MemorySegment): Vector3 =
        memScoped {
            val ret = allocArray<FloatVar>(3)
            kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), null, null, 0, PT_VECTOR3, ret)
            Vector3(ret[0].toDouble(), ret[1].toDouble(), ret[2].toDouble())
        }

    // ---- single arg, void return ----
    fun ptrcallWithBoolArg(methodBind: MemorySegment, instance: MemorySegment, value: Boolean) =
        memScoped {
            val cell = alloc<ByteVar>(); cell.value = if (value) 1 else 0
            val types = allocArray<IntVar>(1); types[0] = PT_BOOL
            val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = cell.ptr.reinterpret<CPointed>()
            kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VOID, null)
            Unit
        }

    // Scalar int args are 8 bytes (int64) at ptrcall — widen the Kotlin Int.
    fun ptrcallWithIntArg(methodBind: MemorySegment, instance: MemorySegment, value: Int) =
        memScoped {
            val cell = alloc<LongVar>(); cell.value = value.toLong()
            val types = allocArray<IntVar>(1); types[0] = PT_INT64
            val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = cell.ptr.reinterpret<CPointed>()
            kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VOID, null)
            Unit
        }

    fun ptrcallWithLongArg(methodBind: MemorySegment, instance: MemorySegment, value: Long) =
        memScoped {
            val cell = alloc<LongVar>(); cell.value = value
            val types = allocArray<IntVar>(1); types[0] = PT_INT64
            val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = cell.ptr.reinterpret<CPointed>()
            kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VOID, null)
            Unit
        }

    // Godot scalar `float` args are 8-byte double at ptrcall — this helper covers both.
    fun ptrcallWithDoubleArg(methodBind: MemorySegment, instance: MemorySegment, value: Double) =
        memScoped {
            val cell = alloc<DoubleVar>(); cell.value = value
            val types = allocArray<IntVar>(1); types[0] = PT_FLOAT64
            val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = cell.ptr.reinterpret<CPointed>()
            kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VOID, null)
            Unit
        }

    fun ptrcallWithVector2Arg(methodBind: MemorySegment, instance: MemorySegment, value: Vector2) =
        memScoped {
            val cell = allocArray<FloatVar>(2)
            cell[0] = value.x.toFloat(); cell[1] = value.y.toFloat()
            val types = allocArray<IntVar>(1); types[0] = PT_VECTOR2
            val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = cell.reinterpret<CPointed>()
            kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VOID, null)
            Unit
        }

    fun ptrcallWithVector3Arg(methodBind: MemorySegment, instance: MemorySegment, value: Vector3) =
        memScoped {
            val cell = allocArray<FloatVar>(3)
            cell[0] = value.x.toFloat(); cell[1] = value.y.toFloat(); cell[2] = value.z.toFloat()
            val types = allocArray<IntVar>(1); types[0] = PT_VECTOR3
            val ptrs = allocArray<COpaquePointerVar>(1); ptrs[0] = cell.reinterpret<CPointed>()
            kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, 1, PT_VOID, null)
            Unit
        }

    fun ptrcallWithObjectArgs(methodBind: MemorySegment, instance: MemorySegment, objectArgs: List<MemorySegment>) =
        memScoped {
            val n = objectArgs.size
            val cells = allocArray<LongVar>(if (n > 0) n else 1)
            val types = allocArray<IntVar>(if (n > 0) n else 1)
            val ptrs = allocArray<COpaquePointerVar>(if (n > 0) n else 1)
            for (i in 0 until n) {
                cells[i] = objectArgs[i].address()
                types[i] = PT_OBJECT
                ptrs[i] = (cells + i)!!.reinterpret<CPointed>()
            }
            kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), types, ptrs, n, PT_VOID, null)
            Unit
        }
}

// Debug-gated self-test (called from the C scene-init self-test): validates the full
// Kotlin->C->Godot path through ObjectCalls — Vector3 float32 component layout, bool,
// int64 return, and scalar-float-as-double — against real Godot on device.
@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_objectcalls_selftest")
fun kanamaIosRuntimeObjectCallsSelfTest() {
    var pass = 0
    var fail = 0
    fun check(label: String, cond: Boolean) {
        if (cond) {
            pass++
        } else {
            fail++
            println("[kanama][ios][kn] OBJECTCALLS SELFTEST FAIL: $label")
        }
    }

    val n3 = ObjectCalls.constructObject("Node3D")
    ObjectCalls.ptrcallWithVector3Arg(
        ObjectCalls.getMethodBind("Node3D", "set_position", 3460891852L), n3, Vector3(1.0, 2.0, 3.0))
    val p = ObjectCalls.ptrcallNoArgsRetVector3(
        ObjectCalls.getMethodBind("Node3D", "get_position", 3360562783L), n3)
    check("vector3", p.x == 1.0 && p.y == 2.0 && p.z == 3.0)

    // Generated-helper round-trip (T3.1): ptrcallWithVector3ArgRetVector3 lives in the
    // GENERATED ObjectCallsGenerated.kt (extension on ObjectCalls), not hand-written —
    // it exercises the generator's Vector3 arg-in + Vector3 ret-out codegen against real
    // Godot. Use a FRESH Node3D (identity transform) so to_global is the identity map
    // and the assertion is independent of in-tree global-transform semantics; this
    // isolates the marshalling (arg float32 layout in, ret float32 layout out).
    val n3b = ObjectCalls.constructObject("Node3D")
    val g = ObjectCalls.ptrcallWithVector3ArgRetVector3(
        ObjectCalls.getMethodBind("Node3D", "to_global", 192990374L), n3b, Vector3(10.0, 20.0, 30.0))
    println("[kanama][ios][kn] OBJECTCALLS SELFTEST generated-vector3 g=(${g.x}, ${g.y}, ${g.z})")
    check("generated-vector3-arg-ret", g.x == 10.0 && g.y == 20.0 && g.z == 30.0)

    ObjectCalls.ptrcallWithBoolArg(
        ObjectCalls.getMethodBind("Node3D", "set_visible", 2586408642L), n3, false)
    check("bool", !ObjectCalls.ptrcallNoArgsRetBool(
        ObjectCalls.getMethodBind("Node3D", "is_visible", 36873697L), n3))

    val id = ObjectCalls.ptrcallNoArgsRetLong(
        ObjectCalls.getMethodBind("Object", "get_instance_id", 3905245786L), n3)
    check("int64-ret", id != 0L)

    val n2 = ObjectCalls.constructObject("Node2D")
    ObjectCalls.ptrcallWithDoubleArg(
        ObjectCalls.getMethodBind("Node2D", "set_rotation", 373806689L), n2, 0.5)
    check("scalar-float-as-double", ObjectCalls.ptrcallNoArgsRetDouble(
        ObjectCalls.getMethodBind("Node2D", "get_rotation", 1740695150L), n2) == 0.5)

    val parts = ObjectCalls.constructObject("GPUParticles3D")
    ObjectCalls.ptrcallWithIntArg(
        ObjectCalls.getMethodBind("GPUParticles3D", "set_amount", 1286410249L), parts, 1234567)
    check("scalar-int(8-byte)", ObjectCalls.ptrcallNoArgsRetInt(
        ObjectCalls.getMethodBind("GPUParticles3D", "get_amount", 3905245786L), parts) == 1234567)

    // T3.3: Input singleton + action polling marshalling. With no live input the values
    // are 0.0/false, but this validates getSingleton + the generated StringName-arg
    // helpers (ptrcallWithTwoStringNameArgsRetDouble / ptrcallWithStringNameAndBoolArgRetBool)
    // on device. The gameplay assertion (nonzero under input) is T3.4's full-demo run.
    val inputSingleton = ObjectCalls.getSingleton("Input")
    check("input-singleton", inputSingleton.address() != 0L)
    val axis = ObjectCalls.ptrcallWithTwoStringNameArgsRetDouble(
        ObjectCalls.getMethodBind("Input", "get_axis", 1958752504L), inputSingleton, "ui_left", "ui_right")
    check("input-get_axis(no-input==0)", axis == 0.0)
    val jumped = ObjectCalls.ptrcallWithStringNameAndBoolArgRetBool(
        ObjectCalls.getMethodBind("Input", "is_action_just_pressed", 1558498928L), inputSingleton, "ui_accept", false)
    check("input-is_action_just_pressed(no-input==false)", !jumped)

    println("[kanama][ios][kn] OBJECTCALLS SELFTEST: $pass passed, $fail failed")
}
