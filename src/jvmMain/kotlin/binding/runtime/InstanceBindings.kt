package net.multigesture.kanama.binding.runtime

import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodType
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong
import net.multigesture.kanama.ffi.GodotFFI

/**
 * Desktop/Android: the instance binding behind [ObjectRuntime.liveFlagOf] (task 132 D7), on while
 * [FreedObjectChecks.bindings] (`KANAMA_FREED_OBJECT_CHECKS=binding`).
 *
 * `object_get_instance_binding(object, token, callbacks)` returns the binding Kanama attached to
 * the object, creating it on first use through [bindingCreate]; Godot calls [bindingFree] from the
 * object's destructor, on whatever thread frees it. The binding pointer is a key into [liveFlags],
 * never a native allocation, so nothing native outlives the object and a wrapper that outlives it
 * only holds a dead Kotlin flag. Its own object (not `ObjectRuntime`) because the two upcall
 * targets must be public JVM statics, and an `actual` declares nothing public its `expect` lacks.
 */
internal object InstanceBindings {
  /**
   * The per-construction binding lookup as JVM constants (task 131 item 16): `@JvmField` in an
   * `object` is a `static final` field the JIT folds, called through `invokeExact` on its exact
   * type, and the binding key comes back as a `long` (64-bit targets only), so a lookup allocates
   * no `MemorySegment`. Initialized on first use ([available]), after `GodotFFI.bootstrap`.
   */
  private object Lookup {
    /**
     * `void *object_get_instance_binding(GDExtensionObjectPtr, void *token, const callbacks *)`:
     * `(MemorySegment, MemorySegment, MemorySegment)J`.
     */
    @JvmField
    val GET_INSTANCE_BINDING: MethodHandle =
      GodotFFI.lookup(
        "object_get_instance_binding",
        FunctionDescriptor.of(JAVA_LONG, ADDRESS, ADDRESS, ADDRESS),
      )

    /** Kanama's binding token: the address of a byte only this runtime owns. */
    @JvmField val TOKEN: MemorySegment = GodotFFI.arena.allocate(8L, 8L)

    /** `GDExtensionInstanceBindingCallbacks { create, free, reference }` (reference: none). */
    @JvmField val CALLBACKS: MemorySegment = bindingCallbacks()
  }

  private fun bindingCallbacks(): MemorySegment {
    val create =
      Upcalls.stub(
        InstanceBindings::class.java,
        "bindingCreate",
        MethodType.methodType(
          MemorySegment::class.java,
          MemorySegment::class.java,
          MemorySegment::class.java,
        ),
        FunctionDescriptor.of(ADDRESS, ADDRESS, ADDRESS),
      )
    val free =
      Upcalls.stub(
        InstanceBindings::class.java,
        "bindingFree",
        MethodType.methodType(
          Void.TYPE,
          MemorySegment::class.java,
          MemorySegment::class.java,
          MemorySegment::class.java,
        ),
        FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS),
      )
    return GodotFFI.arena.allocate(ADDRESS.byteSize() * 3, ADDRESS.byteAlignment()).also {
      it.set(ADDRESS, 0L, create)
      it.set(ADDRESS, ADDRESS.byteSize(), free)
      it.set(ADDRESS, ADDRESS.byteSize() * 2, MemorySegment.NULL)
    }
  }

  private val liveFlags = ConcurrentHashMap<Long, LiveFlag>()
  private val nextBindingKey = AtomicLong()

  /**
   * Resolves the binding entry point and builds the callbacks once, when the freed-object check is
   * configured: false keeps the instance-id lookup.
   */
  fun available(): Boolean =
    runCatching { Lookup.CALLBACKS }
      .onFailure { System.err.println("[kanama:kt] object_get_instance_binding: ${it.message}") }
      .isSuccess

  fun liveFlagOf(segment: RawSegment): LiveFlag {
    val key =
      Lookup.GET_INSTANCE_BINDING.invokeExact(segment, Lookup.TOKEN, Lookup.CALLBACKS) as Long
    return liveFlags[key] ?: LiveFlag.freed()
  }

  @JvmStatic
  fun bindingCreate(token: MemorySegment, instance: MemorySegment): MemorySegment {
    val key = nextBindingKey.incrementAndGet()
    liveFlags[key] = LiveFlag(ObjectCalls.objectGetInstanceId(instance))
    return MemorySegment.ofAddress(key)
  }

  @JvmStatic
  fun bindingFree(token: MemorySegment, instance: MemorySegment, binding: MemorySegment) {
    liveFlags.remove(binding.address())?.dead = true
  }
}
