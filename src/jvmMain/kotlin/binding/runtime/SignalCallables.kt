package net.multigesture.kanama.binding.runtime

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodType
import net.multigesture.kanama.KanamaBinding
import net.multigesture.kanama.ffi.GodotFFI

/**
 * Lambda signal connections on desktop/Android as GDExtension custom Callables (task 131, F9) --
 * the mechanism iOS's shim already used.
 *
 * Each connection is a `callable_custom_create2` Callable whose userdata is the
 * [SignalCallbackRegistry] id, whose `object_id` is the receiver's instance id, and whose
 * `free_func` releases the registry entry. Godot destroys the Callable -- and so calls `free_func`
 * -- whenever it drops the connection, which covers every way a connection ends without Kanama
 * having to observe it: the receiver is freed (`object_id` puts the connection in the receiver's
 * list, so its destructor disconnects it), the emitter is freed (its signal map is cleared), a
 * `CONNECT_ONE_SHOT` connection fires, a failed connect, and an explicit disconnect. Before this
 * the connection was a bound Callable to the receiver's generated `__kanama_signal_dispatchN`
 * method, which Godot never reported back, so a closure lived until `SignalConnection.close()`.
 *
 * With no hash/equal functions Godot identifies a custom Callable by `(call_func, userdata)`, so
 * [disconnect] rebuilds an equal Callable from the id. That temporary's own `free_func` releases
 * the id too, which is the intent (release is idempotent).
 */
object SignalCallables {

  /** `GDExtensionCallableCustomInfo2`: 11 pointer-sized fields. */
  private const val INFO_SIZE = 88L
  private const val OFF_USERDATA = 0L
  private const val OFF_TOKEN = 8L
  private const val OFF_OBJECT_ID = 16L
  private const val OFF_CALL = 24L
  private const val OFF_FREE = 40L

  private const val CALL_OK = 0
  private const val CALL_ERROR_TOO_FEW_ARGUMENTS = 4

  private const val CONNECT_HASH = 1518946055L
  private const val DISCONNECT_HASH = 1874754934L
  private const val IS_CONNECTED_HASH = 768136979L

  private val callStub: MemorySegment by lazy {
    Upcalls.stub(
      SignalCallables::class.java,
      "call",
      MethodType.methodType(
        Void.TYPE,
        MemorySegment::class.java,
        MemorySegment::class.java,
        Long::class.javaPrimitiveType,
        MemorySegment::class.java,
        MemorySegment::class.java,
      ),
      FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, JAVA_LONG, ADDRESS, ADDRESS),
    )
  }

  private val freeStub: MemorySegment by lazy {
    Upcalls.stub(
      SignalCallables::class.java,
      "free",
      MethodType.methodType(Void.TYPE, MemorySegment::class.java),
      FunctionDescriptor.ofVoid(ADDRESS),
    )
  }

  // Both shapes are prewarmed by NativeCallSurface (two_pointer_void, ptrcall).
  private val callableCustomCreate by lazy {
    GodotFFI.lookup("callable_custom_create2", FunctionDescriptor.ofVoid(ADDRESS, ADDRESS))
  }
  private val methodBindPtrcall by lazy {
    GodotFFI.lookup(
      "object_method_bind_ptrcall",
      FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, ADDRESS),
    )
  }
  private val connectBind by lazy { ObjectCalls.getMethodBind("Object", "connect", CONNECT_HASH) }
  private val disconnectBind by lazy {
    ObjectCalls.getMethodBind("Object", "disconnect", DISCONNECT_HASH)
  }
  private val isConnectedBind by lazy {
    ObjectCalls.getMethodBind("Object", "is_connected", IS_CONNECTED_HASH)
  }

  /**
   * `Object.connect(signal, <custom Callable for id>, flags)` on [emitter]; returns Godot's
   * `Error`. On failure Godot never keeps a copy, so the Callable dies here and releases [id].
   */
  fun connect(
    emitter: MemorySegment,
    signal: String,
    receiverInstanceId: Long,
    id: Long,
    flags: Long,
  ): Long =
    withCallable(receiverInstanceId, id) { arena, callable ->
      val flagsArg = arena.allocate(JAVA_INT)
      flagsArg.set(JAVA_INT, 0, BuiltinTypes.requireUInt32(flags))
      val args = arena.allocate(ADDRESS, 3)
      args.setAtIndex(ADDRESS, 0, GodotStrings.makeStringName(signal))
      args.setAtIndex(ADDRESS, 1, callable)
      args.setAtIndex(ADDRESS, 2, flagsArg)
      val ret = arena.allocate(JAVA_LONG)
      methodBindPtrcall.invoke(connectBind, emitter, args, ret)
      ret.get(JAVA_LONG, 0)
    }

  /**
   * Disconnects the connection [connect] made for [id], if [emitter] still has it. The caller
   * checks that [emitter] is alive: a freed emitter already dropped the connection.
   */
  fun disconnect(emitter: MemorySegment, signal: String, receiverInstanceId: Long, id: Long) {
    withCallable(receiverInstanceId, id) { arena, callable ->
      val args = arena.allocate(ADDRESS, 2)
      args.setAtIndex(ADDRESS, 0, GodotStrings.makeStringName(signal))
      args.setAtIndex(ADDRESS, 1, callable)
      val connected = arena.allocate(JAVA_BYTE)
      methodBindPtrcall.invoke(isConnectedBind, emitter, args, connected)
      if (connected.get(JAVA_BYTE, 0).toInt() != 0) {
        methodBindPtrcall.invoke(disconnectBind, emitter, args, MemorySegment.NULL)
      }
    }
  }

  private inline fun <R> withCallable(
    receiverInstanceId: Long,
    id: Long,
    block: (Arena, MemorySegment) -> R,
  ): R =
    Arena.ofConfined().use { arena ->
      val info = arena.allocate(INFO_SIZE, 8L)
      info.set(ADDRESS, OFF_USERDATA, MemorySegment.ofAddress(id))
      info.set(ADDRESS, OFF_TOKEN, KanamaBinding.libraryToken)
      info.set(JAVA_LONG, OFF_OBJECT_ID, receiverInstanceId)
      info.set(ADDRESS, OFF_CALL, callStub)
      info.set(ADDRESS, OFF_FREE, freeStub)
      val callable = BuiltinTypes.allocateCallable(arena)
      callableCustomCreate.invoke(callable, info)
      try {
        block(arena, callable)
      } finally {
        // Drops this reference; Godot holds its own copy for a live connection.
        BuiltinTypes.destroyTyped(VariantType.CALLABLE, callable)
      }
    }

  /**
   * `call_func`. Godot does not initialise [rError], so every path writes it. A throwing callback
   * is contained and reported like a GDScript runtime error in a called function: Godot prints the
   * script error and the call itself reports success with a nil return (`gdscript_vm.cpp`).
   */
  @JvmStatic
  fun call(
    userdata: MemorySegment,
    args: MemorySegment,
    argCount: Long,
    rReturn: MemorySegment,
    rError: MemorySegment,
  ) {
    val error = rError.reinterpret(12L)
    error.set(JAVA_INT, 0, CALL_OK)
    error.set(JAVA_INT, 4, 0)
    error.set(JAVA_INT, 8, 0)
    val entry = SignalCallbackRegistry.entry(userdata.address()) ?: return
    if (argCount < entry.argumentCount) {
      error.set(JAVA_INT, 0, CALL_ERROR_TOO_FEW_ARGUMENTS)
      error.set(JAVA_INT, 8, entry.argumentCount)
      return
    }
    try {
      val values =
        if (entry.argumentCount == 0) {
          emptyList()
        } else {
          val argv = args.reinterpret(entry.argumentCount * 8L)
          Arena.ofConfined().use { arena ->
            List(entry.argumentCount) { i ->
              BuiltinTypes.readVariantScalar(argv.getAtIndex(ADDRESS, i.toLong()), arena)
            }
          }
        }
      entry.callback(values)
    } catch (t: Throwable) {
      ScriptErrors.report(t, "signal lambda")
      runCatching {
        System.err.println("[kanama:kt] signal lambda failed: ${t.javaClass.name}: ${t.message}")
        t.printStackTrace(System.err)
      }
    }
  }

  /** `free_func`: Godot dropped the last copy of the Callable. */
  @JvmStatic
  fun free(userdata: MemorySegment) {
    SignalCallbackRegistry.unregister(userdata.address())
  }
}
