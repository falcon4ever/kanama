package net.multigesture.kanama.binding.runtime

import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import net.multigesture.kanama.ffi.GodotFFI

/**
 * Builds Panama upcall stubs that Godot can call as plain C function pointers. Stubs are allocated
 * in the process-long [GodotFFI.arena] so the engine can dereference them forever.
 *
 * Every engine-facing callback we install (create_instance, free_instance, get_virtual, per-virtual
 * dispatchers, method call_func/ptrcall_func) routes through this helper. The caller provides the
 * target class, a static method name, the Java-side method type, and the C-side function descriptor
 * that describes the expected ABI.
 */
object Upcalls {

  fun stub(
    targetClass: Class<*>,
    methodName: String,
    methodType: MethodType,
    descriptor: FunctionDescriptor,
  ): MemorySegment {
    val handle = MethodHandles.lookup().findStatic(targetClass, methodName, methodType)
    return GodotFFI.linker.upcallStub(handle, descriptor, GodotFFI.arena)
  }
}
