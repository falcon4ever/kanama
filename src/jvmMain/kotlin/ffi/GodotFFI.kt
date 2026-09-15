package net.multigesture.kanama.ffi

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.invoke.MethodHandle
import java.util.concurrent.ConcurrentHashMap

/**
 * Low-level Panama FFI cache for Kanama.
 *
 * Holds the one thing Godot hands us at startup — the `p_get_proc_address` function pointer —
 * wrapped as a Panama [MethodHandle]. Everything else (every GDExtension function we ever call) is
 * resolved through [lookup] by passing a name and a [FunctionDescriptor].
 *
 * This is the *only* place in the project that calls `MemorySegment.ofAddress` with a raw `long`.
 * All other code should receive real [MemorySegment]s or [MethodHandle]s from here.
 *
 * It is also the **single funnel for native adapter creation** (task 83): every downcall handle and
 * every upcall stub in the desktop/Android backend is linked through [downcallHandle],
 * [prelinkDowncall] or [upcallStub], never through [linker] directly. See [NativeCallSurface] for
 * why that matters and `scripts/check_native_call_surface.py` for the gate that keeps it true.
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

  // ---- Native adapter accounting (task 83) ----

  /**
   * `java.lang.foreign.Linker` generates a native adapter (a downcall stub blob plus a specialized
   * binding class) **once per `FunctionDescriptor`**, not once per symbol: `AbstractLinker` keys
   * its `DOWNCALL_CACHE` on the descriptor and the linker options, and the symbol-bound overload is
   * just `downcallHandle(descriptor).bindTo(symbol)`. So the unit of native code generation — the
   * thing that must not first happen inside a Godot→JVM upcall — is the *shape*, not the call site.
   *
   * These sets track which shapes this process has already linked, so the trace can distinguish
   * "new adapter generated here" from "existing adapter reused here".
   */
  private val linkedDowncallShapes = ConcurrentHashMap.newKeySet<FunctionDescriptor>()

  private val linkedUpcallShapes = ConcurrentHashMap.newKeySet<FunctionDescriptor>()

  private val startNanos = System.nanoTime()

  @Volatile private var lifecycleUpcallsInstalled = false

  /**
   * Opt-in adapter trace. Off in release: with the variable unset every hook below is a volatile
   * read plus a set membership test, and nothing is printed.
   */
  private val traceAdapters = System.getenv("KANAMA_TRACE_NATIVE_ADAPTERS") == "1"

  private fun elapsedSeconds(): String =
    String.format("%.3f", (System.nanoTime() - startNanos) / 1_000_000_000.0)

  private fun phase(): String = if (lifecycleUpcallsInstalled) "post-boundary" else "bootstrap"

  private fun noteDowncallShape(descriptor: FunctionDescriptor, label: String) {
    // Only a *first* sighting of a shape generates native code; repeats hit the JDK's cache.
    if (!linkedDowncallShapes.add(descriptor)) return
    if (!traceAdapters) return
    val violation = if (lifecycleUpcallsInstalled) " VIOLATION" else ""
    System.err.println(
      "[kanama:adapter] downcall shape=$descriptor label=$label " +
        "t=${elapsedSeconds()}s phase=${phase()}$violation"
    )
  }

  private fun noteUpcallShape(descriptor: FunctionDescriptor, label: String) {
    if (!linkedUpcallShapes.add(descriptor)) return
    if (!traceAdapters) return
    System.err.println(
      "[kanama:adapter] upcall shape=$descriptor label=$label t=${elapsedSeconds()}s phase=${phase()}"
    )
  }

  /**
   * Marks the point at which Godot gains the ability to call back into the JVM — everything after
   * this runs, or can run, inside a Godot→JVM upcall.
   *
   * Called once from `KanamaBinding.installInitCallbacks`.
   */
  fun markLifecycleUpcallsInstalled() {
    if (lifecycleUpcallsInstalled) return
    lifecycleUpcallsInstalled = true
    if (!traceAdapters) return
    System.err.println(
      "[kanama:adapter] boundary first-lifecycle-upcall-install t=${elapsedSeconds()}s " +
        "downcall-shapes=${linkedDowncallShapes.size} upcall-shapes=${linkedUpcallShapes.size}"
    )
  }

  /**
   * Wrap the raw proc-address pointer as a Panama [MethodHandle]. Must be called exactly once, as
   * the first thing Kanama does after `KanamaBinding.init` is entered.
   */
  fun bootstrap(procAddrRaw: Long) {
    check(getProcAddress == null) { "GodotFFI.bootstrap called twice" }
    require(procAddrRaw != 0L) { "procAddr is null" }
    val segment = MemorySegment.ofAddress(procAddrRaw).reinterpret(Long.MAX_VALUE)
    getProcAddress = downcallHandle(segment, getProcAddressDescriptor, "get_proc_address")
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
    return downcallHandle(fnAddr, descriptor, name)
  }

  /**
   * Bind [symbol] with [descriptor]. [label] names the call family for the adapter trace and is not
   * used for anything else.
   *
   * Use this instead of `GodotFFI.linker.downcallHandle` so that every adapter in the backend is
   * visible to the trace and to `scripts/check_native_call_surface.py`.
   */
  fun downcallHandle(
    symbol: MemorySegment,
    descriptor: FunctionDescriptor,
    label: String,
  ): MethodHandle {
    noteDowncallShape(descriptor, label)
    return linker.downcallHandle(symbol, descriptor)
  }

  /**
   * Link the native adapter for [descriptor] without binding a symbol, so that a later
   * [downcallHandle] for the same shape is a cache hit and generates no code.
   *
   * This is how [NativeCallSurface] prewarms; it is intentionally symbol-free, because prewarming
   * must not depend on which engine entry points happen to exist.
   */
  fun prelinkDowncall(descriptor: FunctionDescriptor, label: String) {
    noteDowncallShape(descriptor, label)
    linker.downcallHandle(descriptor)
  }

  /**
   * Build an upcall stub for [target] in the process-long [arena]. [label] names the callback for
   * the adapter trace.
   */
  fun upcallStub(
    target: MethodHandle,
    descriptor: FunctionDescriptor,
    label: String,
  ): MemorySegment {
    noteUpcallShape(descriptor, label)
    return linker.upcallStub(target, descriptor, arena)
  }
}
