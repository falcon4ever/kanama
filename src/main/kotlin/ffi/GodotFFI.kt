package net.multigesture.kanama.ffi

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.invoke.MethodHandle

/**
 * Low-level Panama FFI cache for Kanama.
 *
 * Holds the one thing Godot hands us at startup — the `p_get_proc_address` function pointer —
 * wrapped as a Panama [MethodHandle]. Everything else (every GDExtension function we ever call) is
 * resolved through [lookup] by passing a name and a [FunctionDescriptor].
 *
 * This is the *only* place in the project that calls `MemorySegment.ofAddress` with a raw `long`.
 * All other code should receive real [MemorySegment]s or [MethodHandle]s from here.
 */
object GodotFFI {

  val linker: Linker = Linker.nativeLinker()

  /**
   * Process-long arena for small, long-lived allocations like function names and upcall stubs.
   * Anything allocated here lives until the JVM exits — matches the GDExtension lifetime.
   */
  val arena: Arena = Arena.ofShared()

  /**
   * Descriptor for `GDExtensionInterfaceGetProcAddress`: `void(*)() get_proc_address(const char
   * *name)`.
   */
  private val getProcAddressDescriptor: FunctionDescriptor = FunctionDescriptor.of(ADDRESS, ADDRESS)

  private var getProcAddress: MethodHandle? = null

  /**
   * Wrap the raw proc-address pointer as a Panama [MethodHandle]. Must be called exactly once, as
   * the first thing Kanama does after `KanamaBinding.init` is entered.
   */
  fun bootstrap(procAddrRaw: Long) {
    check(getProcAddress == null) { "GodotFFI.bootstrap called twice" }
    require(procAddrRaw != 0L) { "procAddr is null" }
    val segment = MemorySegment.ofAddress(procAddrRaw).reinterpret(Long.MAX_VALUE)
    getProcAddress = linker.downcallHandle(segment, getProcAddressDescriptor)
  }

  /**
   * Resolve a GDExtension function by name and return a [MethodHandle] bound to the given
   * [descriptor]. Throws if the name isn't known to the engine.
   */
  fun lookup(name: String, descriptor: FunctionDescriptor): MethodHandle {
    val handle = checkNotNull(getProcAddress) { "GodotFFI not bootstrapped" }
    val nameSegment = arena.allocateFrom(name)
    val fnAddr = handle.invokeWithArguments(nameSegment) as MemorySegment
    require(fnAddr.address() != 0L) {
      "GDExtension function '$name' not found (get_proc_address returned NULL)"
    }
    return linker.downcallHandle(fnAddr, descriptor)
  }
}
