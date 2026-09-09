package net.multigesture.kanama.binding.runtime

import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import net.multigesture.kanama.ffi.GodotFFI

/**
 * Builds Panama upcall stubs that Godot can call as plain C function pointers. Stubs are allocated
 * in the process-long [GodotFFI.arena] so the engine can dereference them forever.
 *
 * Every engine-facing callback we install (create_instance, free_instance, get_virtual, per-virtual
 * dispatchers, method call_func/ptrcall_func, the ScriptInstance vtable, the loader/saver and
 * language virtuals) routes through this helper. The caller provides the target class, a static
 * method name, the Java-side method type, and the C-side function descriptor that describes the
 * expected ABI.
 *
 * ## Exception containment (task 98)
 *
 * A Throwable that escapes an FFM upcall unwinds through native frames and aborts the JVM -- and
 * Godot with it -- instead of surfacing as an engine error. Containment used to be hand-placed
 * (`catch (t: Throwable)` in nine of the 112 upcall targets); everything else, including the `.kt`
 * resource loader, was one IO failure away from taking the process down. [stub] therefore wraps
 * every target in [MethodHandles.catchException]: the handler logs the failure (one stack trace per
 * site, then one line per occurrence) and returns the zero of the target's return type -- `0` for
 * `Byte`/`Int`/`Long`, `MemorySegment.NULL` for pointers, nothing for `void`. The engine reads that
 * as "not handled" / "no override" / "instantiation failed" / a NIL return, all of which it already
 * copes with. Bespoke handlers whose return value carries meaning (task 50's property accessors
 * report "owned, write rejected"; `siCall` sets the call error) stay in place -- this is the floor
 * beneath them, not a replacement.
 *
 * This composes method handles only; it creates no native adapter, so the task-83 prewarm invariant
 * (no downcall shape linked after the first lifecycle upcall) is untouched.
 */
object Upcalls {

  fun stub(
    targetClass: Class<*>,
    methodName: String,
    methodType: MethodType,
    descriptor: FunctionDescriptor,
  ): MemorySegment {
    val label = "${targetClass.simpleName}.$methodName"
    val handle = MethodHandles.lookup().findStatic(targetClass, methodName, methodType)
    return GodotFFI.upcallStub(contain(handle, label), descriptor, label)
  }

  /**
   * Wraps [target] so that any Throwable it lets escape is logged under [label] and replaced by the
   * zero value of its return type. Package-visible so the containment can be unit-exercised without
   * a running engine.
   */
  internal fun contain(target: MethodHandle, label: String): MethodHandle {
    val returnType = target.type().returnType()
    val zero: MethodHandle =
      if (returnType == MemorySegment::class.java) {
        // MethodHandles.empty would return null here, and a null pointer return NPEs inside the
        // upcall adapter -- the exact crash we are containing. Hand the engine a real NULL.
        MethodHandles.constant(MemorySegment::class.java, MemorySegment.NULL)
      } else {
        MethodHandles.empty(MethodType.methodType(returnType))
      }
    // (Throwable) -> R: log first (void combiner, so no argument is inserted), then the zero.
    val logThenZero =
      MethodHandles.foldArguments(
        MethodHandles.dropArguments(zero, 0, Throwable::class.java),
        logFailure.bindTo(label),
      )
    // A handler taking only the exception (an empty prefix of the target's parameters) is legal.
    return MethodHandles.catchException(target, Throwable::class.java, logThenZero)
  }

  private val failureCounts = ConcurrentHashMap<String, AtomicInteger>()

  private val logFailure: MethodHandle =
    MethodHandles.lookup()
      .findStatic(
        Upcalls::class.java,
        "logUpcallFailure",
        MethodType.methodType(Void.TYPE, String::class.java, Throwable::class.java),
      )

  /** Containment log: `[kanama] upcall <site> threw: ...`, with the stack trace once per site. */
  @JvmStatic
  fun logUpcallFailure(label: String, t: Throwable) {
    val occurrence = failureCounts.computeIfAbsent(label) { AtomicInteger() }.incrementAndGet()
    val suffix = if (occurrence == 1) "" else " (occurrence $occurrence; stack trace printed once)"
    System.err.println(
      "[kanama] upcall $label threw: ${t::class.qualifiedName}: ${t.message}$suffix"
    )
    if (occurrence == 1) t.printStackTrace(System.err)
  }
}
