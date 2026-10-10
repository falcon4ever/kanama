package net.multigesture.kanama.binding.runtime

import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodType
import net.multigesture.kanama.KanamaBinding
import net.multigesture.kanama.api.Engine
import net.multigesture.kanama.api.GodotError
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.OS
import net.multigesture.kanama.api.Signal0
import net.multigesture.kanama.api.Signal1
import net.multigesture.kanama.api.Signal2
import net.multigesture.kanama.api.Signal3
import net.multigesture.kanama.api.Signal4
import net.multigesture.kanama.api.Signal5
import net.multigesture.kanama.api.SignalArgReader
import net.multigesture.kanama.api.SignalArgType
import net.multigesture.kanama.api.SignalConnection
import net.multigesture.kanama.binding.ObjectRegistry
import net.multigesture.kanama.ffi.GodotFFI
import net.multigesture.kanama.types.Vector3

/**
 * A typed-signal self-test that runs on the device (task 138 item 23), in the shared
 * desktop/Android JVM code: the Android-only typed-emit failure of task 138 item 21 (tagged heap
 * pointers) lived five days because no Android smoke required a typed signal to fire. The Kanama
 * smokes (`runtime_smoke.sh`, `android_smoke.sh`) switch it on and require its verdict.
 *
 * A Kanama test hook, OFF for users: it costs about 15-20 ms and a hundred classes on the first
 * frame and registers an extension class ([RECEIVER_CLASS]) that stays until shutdown. It runs
 * once, on the first frame of the main loop ([onFrame], called from the script language's
 * `frame()`), only when the project setting [SETTING] (`kanama/debug/signal_self_test`, default
 * false) is true in a debug build that is not the editor. `KANAMA_SIGNAL_SELFTEST=1` forces it on
 * (also in a release build and in the editor), `0` off. It prints `[kanama] SIGNAL SELFTEST: <n>
 * passed, <m> failed in <ms> ms` and one `[kanama] SIGNAL SELFTEST FAIL: <row> got <values>` line
 * per failed row.
 *
 * What it covers: `Signal0` … `Signal5` emitted from Kotlin with mixed argument types (Long,
 * Double, Boolean, String, Vector3, Object), each received by a Kotlin lambda connection and by a
 * Callable to a method of an extension class registered here (Godot's half of the `@Function` path:
 * `Callable` → `GDExtensionMethodBind::call` → `call_func` with the emitted Variants; the
 * receiver's upcalls decode through [JvmSignalArgReader], not the decoder KSP generates for a
 * `@Function`); a one-shot connection; an emit from inside a handler, three levels deep (the
 * emit-frame pool) with the outer handler's own arguments checked after the nested emits returned;
 * and the release of every connection, closure and object of its own afterwards.
 */
internal object SignalSelfTest {
  /** `1`/`true`/`on` force the self-test on, `0`/`false`/`off` off; unset: [SETTING] decides. */
  const val ENVIRONMENT_VARIABLE: String = "KANAMA_SIGNAL_SELFTEST"

  /**
   * The project setting that opts a debug build in (default false; the Android smoke writes it).
   */
  const val SETTING: String = "kanama/debug/signal_self_test"

  private const val RECEIVER_CLASS = "KanamaSignalSelfTestReceiver"
  private const val CALL_ERROR_SIZE = 12L
  private const val BIG = 0x1234_5678_9ABC_DEF0L
  private const val HUGE = 9_000_000_000_000L

  @Volatile private var done = false

  /**
   * Whether the self-test runs for [override] (the environment value) and [setting]; pure, for unit
   * tests. Without an override it needs the setting, a debug build and a game (not the editor).
   */
  fun decide(
    override: String,
    setting: () -> Boolean,
    debugBuild: () -> Boolean,
    editorHint: () -> Boolean,
  ): Boolean =
    when (override.trim().lowercase()) {
      "1",
      "true",
      "on" -> true
      "0",
      "false",
      "off" -> false
      else -> setting() && debugBuild() && !editorHint()
    }

  private fun boolSetting(name: String): Boolean {
    val settings = GodotObject(GodotHandle(ObjectCalls.getSingleton("ProjectSettings")))
    return settings.call("has_setting", name) == true && settings.call("get_setting", name) == true
  }

  /** Called on every main-loop frame; does its work on the first one only. */
  fun onFrame() {
    if (done) return
    done = true
    val enabled =
      runCatching {
          decide(
            OS.getEnvironment(ENVIRONMENT_VARIABLE),
            setting = { boolSetting(SETTING) },
            debugBuild = { OS.isDebugBuild() },
            editorHint = { Engine.isEditorHint() },
          )
        }
        .getOrDefault(false)
    if (!enabled) return
    val started = System.nanoTime()
    val run = Run()
    try {
      run.execute()
    } catch (t: Throwable) {
      run.fail("exception", "${t::class.qualifiedName}: ${t.message}")
      t.printStackTrace(System.err)
    }
    val ms = (System.nanoTime() - started) / 1_000_000
    System.err.println(
      "[kanama] SIGNAL SELFTEST: ${run.passed} passed, ${run.failed} failed in $ms ms"
    )
  }

  // ---- the receiver class: six methods taking the emitted Variants --------------------------

  private var receiverClass: ClassDB.RegisteredClass? = null

  /** The test run whose receipts the method upcalls record into. */
  @Volatile private var current: Run? = null

  private fun registerReceiverClass(): ClassDB.RegisteredClass {
    receiverClass?.let {
      return it
    }
    val voidFive =
      MethodType.methodType(
        Void.TYPE,
        MemorySegment::class.java,
        MemorySegment::class.java,
        MemorySegment::class.java,
        Long::class.javaPrimitiveType,
        MemorySegment::class.java,
        MemorySegment::class.java,
      )
    val callDescriptor =
      FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, JAVA_LONG, ADDRESS, ADDRESS)
    val ptrcallStub =
      Upcalls.stub(
        SignalSelfTest::class.java,
        "ptrcallNoop",
        MethodType.methodType(
          Void.TYPE,
          MemorySegment::class.java,
          MemorySegment::class.java,
          MemorySegment::class.java,
          MemorySegment::class.java,
        ),
        FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, ADDRESS),
      )
    val createStub =
      Upcalls.stub(
        SignalSelfTest::class.java,
        "createInstance",
        MethodType.methodType(
          MemorySegment::class.java,
          MemorySegment::class.java,
          java.lang.Byte.TYPE,
        ),
        FunctionDescriptor.of(ADDRESS, ADDRESS, JAVA_BYTE),
      )
    val freeStub =
      Upcalls.stub(
        SignalSelfTest::class.java,
        "freeInstance",
        MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java),
        FunctionDescriptor.ofVoid(ADDRESS, ADDRESS),
      )
    val virtualStub =
      Upcalls.stub(
        SignalSelfTest::class.java,
        "getVirtual",
        MethodType.methodType(
          MemorySegment::class.java,
          MemorySegment::class.java,
          MemorySegment::class.java,
          java.lang.Integer.TYPE,
        ),
        FunctionDescriptor.of(ADDRESS, ADDRESS, ADDRESS, JAVA_INT),
      )
    val library = KanamaBinding.libraryToken
    val cls =
      ClassDB.registerClass(
        library,
        ClassDB.ClassSpec(
          name = RECEIVER_CLASS,
          parentName = "Object",
          isExposed = false,
          createInstance = createStub,
          freeInstance = freeStub,
          getVirtual = virtualStub,
        ),
      )
    val argTypes =
      listOf(
        emptyList(),
        listOf(VariantType.INT),
        listOf(VariantType.FLOAT, VariantType.STRING),
        listOf(VariantType.VECTOR3, VariantType.STRING, VariantType.INT),
        listOf(VariantType.OBJECT, VariantType.VECTOR3, VariantType.FLOAT, VariantType.BOOL),
        listOf(
          VariantType.INT,
          VariantType.FLOAT,
          VariantType.STRING,
          VariantType.VECTOR3,
          VariantType.OBJECT,
        ),
      )
    val stubs = listOf("call0", "call1", "call2", "call3", "call4", "call5")
    for (slot in 0..5) {
      ClassDB.registerMethod(
        library,
        cls,
        ClassDB.MethodSpec(
          name = "on$slot",
          args = argTypes[slot].mapIndexed { i, type -> ClassDB.MethodArg("a$i", type) },
          callStub =
            Upcalls.stub(SignalSelfTest::class.java, stubs[slot], voidFive, callDescriptor),
          ptrcallStub = ptrcallStub,
        ),
      )
    }
    receiverClass = cls
    return cls
  }

  /** An instance of the receiver class: an `Object` with this class's extension instance set. */
  private fun newReceiver(): GodotObject =
    GodotObject(GodotHandle(createInstance(MemorySegment.NULL, 0)))

  private object ReceiverSentinel

  /** The [ObjectRegistry] handle of the newest receiver instance (the free callback drops it). */
  @Volatile private var receiverHandle = 0L

  @JvmStatic
  fun createInstance(userdata: MemorySegment, notifyPostinitialize: Byte): MemorySegment {
    val obj = ObjectCalls.constructObject("Object")
    val handle = ObjectRegistry.register(ReceiverSentinel)
    receiverHandle = handle
    GodotFFI.lookup("object_set_instance", FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS))
      .invoke(obj, checkNotNull(receiverClass).className, MemorySegment.ofAddress(handle))
    return obj
  }

  @JvmStatic
  fun freeInstance(userdata: MemorySegment, instance: MemorySegment) {
    ObjectRegistry.unregister(instance.address())
  }

  @JvmStatic
  fun getVirtual(userdata: MemorySegment, name: MemorySegment, hash: Int): MemorySegment =
    MemorySegment.NULL

  @JvmStatic
  fun ptrcallNoop(
    userdata: MemorySegment,
    instance: MemorySegment,
    args: MemorySegment,
    rRet: MemorySegment,
  ) {}

  @JvmStatic
  fun call0(
    u: MemorySegment,
    i: MemorySegment,
    a: MemorySegment,
    n: Long,
    r: MemorySegment,
    e: MemorySegment,
  ) = receive(0, a, n, e)

  @JvmStatic
  fun call1(
    u: MemorySegment,
    i: MemorySegment,
    a: MemorySegment,
    n: Long,
    r: MemorySegment,
    e: MemorySegment,
  ) = receive(1, a, n, e)

  @JvmStatic
  fun call2(
    u: MemorySegment,
    i: MemorySegment,
    a: MemorySegment,
    n: Long,
    r: MemorySegment,
    e: MemorySegment,
  ) = receive(2, a, n, e)

  @JvmStatic
  fun call3(
    u: MemorySegment,
    i: MemorySegment,
    a: MemorySegment,
    n: Long,
    r: MemorySegment,
    e: MemorySegment,
  ) = receive(3, a, n, e)

  @JvmStatic
  fun call4(
    u: MemorySegment,
    i: MemorySegment,
    a: MemorySegment,
    n: Long,
    r: MemorySegment,
    e: MemorySegment,
  ) = receive(4, a, n, e)

  @JvmStatic
  fun call5(
    u: MemorySegment,
    i: MemorySegment,
    a: MemorySegment,
    n: Long,
    r: MemorySegment,
    e: MemorySegment,
  ) = receive(5, a, n, e)

  /** `GDExtensionClassMethodCall` of `on<slot>`: reads the Variants as a `@Function` call would. */
  private fun receive(slot: Int, args: MemorySegment, argCount: Long, rError: MemorySegment) {
    val error = rError.reinterpret(CALL_ERROR_SIZE)
    error.set(JAVA_INT, 0, 0)
    error.set(JAVA_INT, 4, 0)
    error.set(JAVA_INT, 8, 0)
    val run = current ?: return
    JvmSignalArgReader.current().dispatch(args, argCount.toInt()) { reader ->
      run.methodLog[slot] += describe(slot, reader)
    }
  }

  // ---- what every receiver records ------------------------------------------------------------

  private fun address(obj: GodotObject?): Long? = obj?.handle?.segment?.address()

  private fun fmt1(a: Long) = "$a"

  private fun fmt2(a: Double, b: String) = "$a|$b"

  private fun fmt3(a: Vector3, b: String, c: Long) = "$a|$b|$c"

  private fun fmt4(a: GodotObject?, b: Vector3, c: Double, d: Boolean) = "${address(a)}|$b|$c|$d"

  private fun fmt5(a: Long, b: Double, c: String, d: Vector3, e: GodotObject?) =
    "$a|$b|$c|$d|${address(e)}"

  /** The method receiver's row: its arguments read straight from the Variants. */
  private fun describe(slot: Int, r: SignalArgReader): String =
    when (slot) {
      0 -> ""
      1 -> fmt1(r.long(0))
      2 -> fmt2(r.double(0), r.string(1))
      3 -> fmt3(r.value(0) as Vector3, r.string(1), r.long(2))
      4 ->
        fmt4(
          r.objectHandle(0)?.let { GodotObject(it) },
          r.value(1) as Vector3,
          r.double(2),
          r.bool(3),
        )
      else ->
        fmt5(
          r.long(0),
          r.double(1),
          r.string(2),
          r.value(3) as Vector3,
          r.objectHandle(4)?.let { GodotObject(it) },
        )
    }

  private fun vec(t: Int) = Vector3(1.5 + t, -2.0 - t, 3.25 * t)

  private class Run {
    var passed = 0
    var failed = 0

    /** Per signal slot: what the lambda / the method receiver got, in order. */
    val lambdaLog = List(6) { mutableListOf<String>() }
    val methodLog = List(6) { mutableListOf<String>() }
    val trace = mutableListOf<String>()

    fun check(row: String, expected: Any?, got: Any?) {
      if (expected == got) {
        passed++
      } else {
        fail(row, "$got (expected $expected)")
      }
    }

    fun fail(row: String, values: String) {
      failed++
      System.err.println("[kanama] SIGNAL SELFTEST FAIL: $row got $values")
    }

    /** The live lambda closures whose receiver is the test's holder object. */
    private fun ownClosures(holderInstanceId: Long) =
      SignalCallbackRegistry.connectionsTo(setOf(holderInstanceId))

    fun execute() {
      var emitterOut: GodotObject? = null
      var holderOut: GodotObject? = null
      var receiverOut: GodotObject? = null
      var holderInstanceId = 0L
      var receiverRegistryHandle = 0L
      val connections = mutableListOf<SignalConnection>()
      val methodConnects = mutableListOf<Pair<String, String>>()
      try {
        current = this
        registerReceiverClass()
        val emitter = GodotObject(GodotHandle(ObjectCalls.constructObject("Object")))
        emitterOut = emitter
        val holder = GodotObject(GodotHandle(ObjectCalls.constructObject("Object")))
        holderOut = holder
        holderInstanceId = holder.instanceId
        val receiver = newReceiver()
        receiverOut = receiver
        receiverRegistryHandle = receiverHandle
        for (n in 0..5) emitter.addUserSignal("st$n")
        for (name in listOf("outer", "inner", "deep")) emitter.addUserSignal("st_$name")

        val vector = SignalArgType.valueOf<Vector3>("Vector3", Vector3::class)
        val objectArg = SignalArgType.objectOf("Object") { GodotObject(it) }
        val s0 = Signal0(emitter, "st0")
        val s1 = Signal1(emitter, "st1", SignalArgType.LONG)
        val s2 = Signal2(emitter, "st2", SignalArgType.DOUBLE, SignalArgType.STRING)
        val s3 = Signal3(emitter, "st3", vector, SignalArgType.STRING, SignalArgType.LONG)
        val s4 =
          Signal4(emitter, "st4", objectArg, vector, SignalArgType.DOUBLE, SignalArgType.BOOLEAN)
        val s5 =
          Signal5(
            emitter,
            "st5",
            SignalArgType.LONG,
            SignalArgType.DOUBLE,
            SignalArgType.STRING,
            vector,
            objectArg,
          )

        // (a) a Kotlin lambda per signal.
        connections += s0.connect(holder) { lambdaLog[0] += "" }
        connections += s1.connect(holder) { a -> lambdaLog[1] += fmt1(a) }
        connections += s2.connect(holder) { a, b -> lambdaLog[2] += fmt2(a, b) }
        connections += s3.connect(holder) { a, b, c -> lambdaLog[3] += fmt3(a, b, c) }
        connections += s4.connect(holder) { a, b, c, d -> lambdaLog[4] += fmt4(a, b, c, d) }
        connections += s5.connect(holder) { a, b, c, d, e -> lambdaLog[5] += fmt5(a, b, c, d, e) }
        // (b) a Callable to a method of the registered class.
        val methodErrors =
          listOf(
            s0.connect(receiver, "on0"),
            s1.connect(receiver, "on1"),
            s2.connect(receiver, "on2"),
            s3.connect(receiver, "on3"),
            s4.connect(receiver, "on4"),
            s5.connect(receiver, "on5"),
          )
        for (n in 0..5) methodConnects += "st$n" to "on$n"
        check("connect errors", List(6) { GodotError.OK }, methodErrors)
        check("connect lambda errors", List(6) { GodotError.OK }, connections.map { it.error })

        val emit =
          listOf<(Int) -> Unit>(
            { s0.emit() },
            { t -> s1.emit(BIG + t) },
            { t -> s2.emit(2.5 + t, "héllo ✓$t") },
            { t -> s3.emit(vec(t), "three$t", -42L - t) },
            { t -> s4.emit(holder, vec(t + 1), 6.125 + t, t % 2 == 0) },
            { t -> s5.emit(HUGE + t, -0.5 - t, "five$t", vec(t + 2), holder) },
          )
        val expected =
          listOf<(Int) -> String>(
            { "" },
            { t -> fmt1(BIG + t) },
            { t -> fmt2(2.5 + t, "héllo ✓$t") },
            { t -> fmt3(vec(t), "three$t", -42L - t) },
            { t -> fmt4(holder, vec(t + 1), 6.125 + t, t % 2 == 0) },
            { t -> fmt5(HUGE + t, -0.5 - t, "five$t", vec(t + 2), holder) },
          )

        // Two rounds: the second reuses the emit frame the first one wrote.
        for (t in 1..2) for (n in 0..5) emit[n](t)
        for (n in 0..5) {
          val want = listOf(expected[n](1), expected[n](2))
          check("Signal$n lambda", want, lambdaLog[n])
          check("Signal$n method", want, methodLog[n])
        }

        // A one-shot connection fires once of two emits.
        val oneShot = mutableListOf<String>()
        connections +=
          s1.connect(holder, GodotObject.ConnectFlags.ONE_SHOT) { a -> oneShot += fmt1(a) }
        emit[1](3)
        emit[1](4)
        check("one-shot", listOf(expected[1](3)), oneShot)
        check("one-shot leaves the permanent lambda", 4, lambdaLog[1].size)

        // An emit from inside a handler, three levels deep; the outer method receiver runs after
        // the nested emits and still sees its own argument.
        val outer = Signal1(emitter, "st_outer", SignalArgType.LONG)
        val inner = Signal2(emitter, "st_inner", SignalArgType.STRING, vector)
        val deep = Signal1(emitter, "st_deep", SignalArgType.DOUBLE)
        connections += deep.connect(holder) { d -> trace += "deep:$d" }
        connections +=
          inner.connect(holder) { s, v ->
            deep.emit(0.75)
            trace += "inner:$s|$v"
          }
        connections +=
          outer.connect(holder) { a ->
            inner.emit("nested", vec(9))
            trace += "outer:$a"
          }
        methodLog[1].clear()
        methodConnects += "st_outer" to "on1"
        check("nested connect", GodotError.OK, outer.connect(receiver, "on1"))
        outer.emit(77L + BIG)
        check(
          "nested emit order",
          listOf("deep:0.75", "inner:nested|${vec(9)}", "outer:${77L + BIG}"),
          trace,
        )
        check("nested outer method sees its argument", listOf(fmt1(77L + BIG)), methodLog[1])

        // Every lambda connection but the fired one-shot is registered (the rows below count the
        // holder's entries, so they must see them).
        check(
          "lambda closures registered",
          connections.size - 1,
          ownClosures(holderInstanceId).size,
        )

        // Disconnect everything: nothing is delivered any more, no connection or closure is left.
        for (connection in connections) connection.close()
        connections.clear()
        for ((signal, method) in methodConnects) emitter.disconnect(signal, receiver, method)
        methodConnects.clear()
        val signals = (0..5).map { "st$it" } + listOf("st_outer", "st_inner", "st_deep")
        check(
          "connections left",
          emptyList<String>(),
          signals.filter { emitter.hasConnections(it) },
        )
        val received = lambdaLog.sumOf { it.size } + methodLog.sumOf { it.size } + trace.size
        for (t in 5..6) for (n in 0..5) emit[n](t)
        outer.emit(1L)
        check(
          "delivered after disconnect",
          0,
          lambdaLog.sumOf { it.size } + methodLog.sumOf { it.size } + trace.size - received,
        )
        check("closure entries left", emptyMap<Long, Any>(), ownClosures(holderInstanceId))
      } finally {
        val liveEmitter = emitterOut
        val liveReceiver = receiverOut
        if (liveEmitter != null && liveReceiver != null) {
          for (connection in connections) runCatching { connection.close() }
          for ((signal, method) in methodConnects) {
            runCatching { liveEmitter.disconnect(signal, liveReceiver, method) }
          }
        }
        for (obj in listOf(emitterOut, holderOut, receiverOut)) {
          if (obj != null) ObjectCalls.destroyObject(obj.handle.segment)
        }
        current = null
      }
      // Only this test's own entries are counted (the registries are process-global, and another
      // thread may create instances during the first frame): the lambda closures connected to the
      // holder, and the receiver's registry handle.
      check("closure entries left after free", emptyMap<Long, Any>(), ownClosures(holderInstanceId))
      check(
        "receiver registry entry left after free",
        null,
        if (receiverRegistryHandle == 0L) null else ObjectRegistry.get(receiverRegistryHandle),
      )
    }
  }
}
