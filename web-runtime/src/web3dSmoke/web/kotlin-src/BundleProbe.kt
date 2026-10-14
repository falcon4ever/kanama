package web3d

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotCallable
import net.multigesture.kanama.api.GodotError
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.GodotSignal
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Mathf
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Signal2
import net.multigesture.kanama.api.SignalArgType
import net.multigesture.kanama.builtins.godotFormat
import net.multigesture.kanama.web.WebExperimentalGenericCall

/**
 * Harness-only (task 129 E, task 138 items 2 and 20): the Web parity rows of the web bundle. Main's
 * `bundle_probe` / `bundle_probe_after` call these and fold the masks for the driver.
 */
/** The stated bounds of the composite sweeps (scripts/web_local_utilities.py). */
private const val EASE_ABS_BOUND = 2.220446049250313e-16

private const val LINEAR_TO_DB_ULPS = 2L

@ScriptClass(attachTo = "Node")
class BundleProbe(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @Signal("pair") fun pair(value: Long) = Unit

  @Signal("solo") fun solo(value: Long) = Unit

  private var firstFired = 0L
  private var secondFired = 0L
  private var methodFired = 0L
  private var deferredValue = 0L

  fun onPair(value: Long) {
    methodFired += value
  }

  fun echo(value: Long): Long = value * 2

  fun deferredSet(value: Long) {
    deferredValue = value
  }

  /**
   * Task 138 item 20: two Kotlin lambdas from this script to the same signal both connect (1) and
   * both fire (2). Godot keys a signal's slots by the base callable, so before the fix the second
   * connect answered "already connected".
   */
  fun lambdaPair(value: Long): Long {
    var mask = 0L
    val first = self.signal("pair").connectLong(self) { firstFired += it }
    val second = self.signal("pair").connectLong(self) { secondFired += it }
    if (first == GodotError.OK && second == GodotError.OK) mask = mask or 1L
    self.emitSignal("pair", 3L)
    if (firstFired == 3L && secondFired == 3L) mask = mask or 2L
    return mask
  }

  /**
   * Task 138 item 2, signal queries (after [lambdaPair]): 1 = a method connection, 2/4 =
   * isConnected for it and for a method that is not connected, 8 = hasConnections, 16 =
   * getConnections lists all three with the method's Callable, flags and Signal, 32 = each lambda
   * connection has its own callable, 64 = disconnect.
   */
  fun signalQueries(value: Long): Long {
    var mask = 0L
    val signal = self.signal("pair")
    if (signal.connect(self, "on_pair") == GodotError.OK) mask = mask or 1L
    if (signal.isConnected(self, "on_pair")) mask = mask or 2L
    if (!signal.isConnected(self, "echo")) mask = mask or 4L
    if (signal.hasConnections()) mask = mask or 8L
    val connections = signal.getConnections()
    val byMethod = connections.firstOrNull { (it["callable"] as? GodotCallable)?.method == "on_pair" }
    if (
      connections.size == 3 &&
        byMethod != null &&
        byMethod["flags"] == 0L &&
        byMethod["signal"] is GodotSignal &&
        (byMethod["callable"] as GodotCallable).target.isSameInstance(self)
    ) {
      mask = mask or 16L
    }
    val lambdaTargets =
      connections.mapNotNull { (it["callable"] as? GodotCallable)?.takeIf { c -> c.method == "fire" } }
    if (lambdaTargets.size == 2 && !lambdaTargets[0].target.isSameInstance(lambdaTargets[1].target)) {
      mask = mask or 32L
    }
    signal.disconnect(self, "on_pair")
    if (!signal.isConnected(self, "on_pair")) mask = mask or 64L
    return mask
  }

  /**
   * Task 138 item 2, GodotCallable: 1/2 = isValid for a method and a missing one, 4 = call, 8 =
   * callv, 16 = getArgumentCount, 32 = getObject, 64 = callDeferred has not run yet ([deferredRan]
   * reads it after the frame).
   */
  fun callableRows(value: Long): Long {
    var mask = 0L
    val echo = GodotCallable(self, "echo")
    if (echo.isValid()) mask = mask or 1L
    if (!GodotCallable(self, "no_such_method").isValid()) mask = mask or 2L
    if (echo.call(21L) == 42L) mask = mask or 4L
    if (echo.callv(listOf(5L)) == 10L) mask = mask or 8L
    if (echo.getArgumentCount() == 1L) mask = mask or 16L
    if (echo.getObject()?.isSameInstance(self) == true) mask = mask or 32L
    GodotCallable(self, "deferred_set").callDeferred(7L)
    if (deferredValue == 0L) mask = mask or 64L
    return mask
  }

  /** 1 once [callableRows]'s deferred call has run. */
  fun deferredRan(value: Long): Long = if (deferredValue == 7L) 1L else 0L

  /**
   * Task 138 item 2, Map / List arguments and Array / Dictionary results on the builtin and
   * utility crossing: 1/2 = String.format with a Dictionary / an Array (the GDScript twin's text),
   * 4 = str_to_var's nested Array, 8 = var_to_str of a nested Dictionary, 16 = typeof an Array and
   * a Dictionary, 32 = rand_from_seed's PackedInt64Array.
   */
  fun containerRows(value: Long): Long {
    var mask = 0L
    if ("{a}-{b}".godotFormat(mapOf("a" to 1L, "b" to "x")) == twinText(2)) mask = mask or 1L
    if ("{0}+{1}".godotFormat(listOf(2L, 3.5)) == twinText(3)) mask = mask or 2L
    if (GD.strToVar("[1, 2.5, \"x\", {\"k\": true}]") == listOf(1L, 2.5, "x", mapOf("k" to true))) {
      mask = mask or 4L
    }
    if (GD.varToStr(mapOf("k" to listOf(1L, 2L))) == twinText(0)) mask = mask or 8L
    if (GD.typeOf(listOf<Any?>()) == 28L && GD.typeOf(mapOf<Any?, Any?>()) == 27L) mask = mask or 16L
    if (GD.randFromSeed(7L).size == 2) mask = mask or 32L
    return mask
  }

  /**
   * Task 129 E: the shared GD on Web against its GDScript twin. Web runs the arithmetic utilities
   * in Kotlin (task 129 E review B1, scripts/web_local_utilities.py) and the twin asks the engine:
   * each row is (ulp bound, Kotlin call), the bound the row's utility carries in that table (0:
   * bit-exact; 1: a libm transcendental; 2: linear_to_db; `ease`'s table bound is absolute, so its
   * rows keep the 1 ulp these inputs reach and [sweepsWithinBounds] checks the table's). Bit 0 = every
   * row within its bound, no row but the engine-RNG ones reaching the bridge's builtin-call channel,
   * and the composite sweeps within their bounds (a mismatch prints the row), bit 1 = str, bit 2 =
   * isInstanceValid / instanceFromId.
   */
  fun gdRows(value: Long): Long {
    val rows: List<Pair<Int, () -> Double>> =
      listOf(
        1 to { GD.sin(0.7) },
        1 to { GD.pow(2.5, 1.3) },
        1 to { GD.atan2(0.3, -0.8) },
        0 to { GD.lerpf(1.0, 3.0, 0.25) },
        0 to { GD.snappedf(7.37, 0.25) },
        0 to { GD.wrapf(-0.5, 0.0, 2.0) },
        1 to { Mathf.sin(1.1) },
        0 to { Mathf.smoothStep(0.0, 2.0, 0.6) },
        0 to {
          GD.seed(42L)
          GD.randf()
        },
        0 to { GD.fposmod(-5.5, 3.0) },
        1 to { Mathf.ease(0.4, 2.0) },
        1 to { Mathf.dbToLinear(-6.0) },
        0 to { Mathf.roundToInt(2.5).toDouble() },
        1 to { Mathf.exp(1.7) },
        1 to { GD.cos(2.3) },
        1 to { GD.tan(0.4) },
        1 to { GD.asin(0.3) },
        1 to { GD.acos(-0.6) },
        1 to { GD.atan(1.7) },
        1 to { GD.log(12.5) },
        1 to { GD.sinh(0.9) },
        1 to { GD.cosh(0.9) },
        1 to { GD.tanh(0.9) },
        1 to { GD.asinh(1.3) },
        1 to { GD.acosh(2.4) },
        1 to { GD.atanh(0.45) },
        2 to { GD.linearToDb(0.3) },
        1 to { GD.ease(0.7, -2.4) },
        1 to { GD.ease(0.2, 0.4) },
        0 to { GD.cubicInterpolate(1.0, 4.0, -2.0, 7.0, 0.3) },
        0 to { GD.cubicInterpolateAngle(0.2, 6.0, -0.4, 6.4, 0.6) },
        0 to { GD.cubicInterpolateInTime(1.0, 4.0, -2.0, 7.0, 0.3, 1.0, -0.5, 1.5) },
        0 to { GD.cubicInterpolateAngleInTime(0.5, 5.9, -1.0, 2.0, 0.4, 1.0, -0.5, 1.5) },
        0 to { GD.bezierInterpolate(0.0, 1.5, 2.5, 4.0, 0.35) },
        0 to { GD.bezierDerivative(0.0, 1.5, 2.5, 4.0, 0.35) },
        0 to { Mathf.lerpAngle(0.3, 5.9, 0.4) },
        0 to { GD.angleDifference(0.3, 5.9) },
        0 to { Mathf.rotateToward(0.2, -2.8, 0.5) },
        0 to { Mathf.pingPong(7.3, 2.0) },
        0 to { Mathf.inverseLerp(2.0, 7.0, 3.3) },
        0 to { GD.remap(3.3, 2.0, 7.0, -1.0, 1.0) },
        0 to { GD.moveToward(1.0, 4.0, 0.7) },
        0 to { GD.fmod(-7.3, 2.1) },
        0 to { GD.sqrt(2.0) },
        0 to { GD.degToRad(33.0) },
        0 to { Mathf.floorToInt(-3.5).toDouble() },
        0 to { GD.snappedi(17.4, 5L).toDouble() },
        0 to { Mathf.posmod(-7L, 3L).toDouble() },
        0 to { GD.nearestPo2(37L).toDouble() },
        0 to { GD.wrapi(-3L, 0L, 5L).toDouble() },
        0 to { GD.max(1.5, 2.5, -1.0) as Double },
        0 to { GD.clamp(0.0 as Any?, 3.0, 1.0) as Double },
        0 to { (GD.min(4L, -2L, 9L) as Long).toDouble() },
        // Engine RNG parity (decision 2026-10-14): the same seed draws the same numbers as GDScript.
        0 to {
          GD.seed(12345L)
          GD.randi().toDouble()
        },
        0 to {
          GD.seed(7L)
          GD.randfRange(-5.0, 5.0) + GD.randiRange(-100L, 100L).toDouble()
        },
      )
    var mask = 1L
    // The rows that still cross by design: the engine RNG ones (8: `GD.seed` + `GD.randf`; 53: seed +
    // randi; 54: seed + randfRange + randiRange). Every other row must not reach the bridge's
    // builtin-call channel at all (fix round 2: a silent crossing).
    val engineCalls = mapOf(8 to 2L, 53 to 2L, 54 to 3L)
    rows.forEachIndexed { index, (bound, case) ->
      val twin = WebExperimentalGenericCall.callImmediate(twin(), "gd_value", listOf(index.toLong())).asDouble()
      val callsBefore = twinLong("builtin_calls")
      val local = case()
      val crossed = twinLong("builtin_calls") - callsBefore
      val ulps = kotlin.math.abs(local.toRawBits() - twin.toRawBits())
      if (ulps > bound) {
        mask = mask and 1L.inv()
        GD.print("gd twin row $index: Kotlin $local vs engine $twin ($ulps ulps, bound $bound)")
      }
      if (crossed != (engineCalls[index] ?: 0L)) {
        mask = mask and 1L.inv()
        GD.print("gd twin row $index: $crossed builtin calls, expected ${engineCalls[index] ?: 0L}")
      }
    }
    if (!sweepsWithinBounds()) mask = mask and 1L.inv()
    if (GD.str(1L, "a", 2.5) == twinText(1)) mask = mask or 2L
    if (
      GD.isInstanceValid(self) &&
        GD.instanceFromId(self.instanceId)?.isSameInstance(self) == true
    ) {
      mask = mask or 4L
    }
    return mask
  }

  /**
   * Fix round 2: the composite utilities against the engine over a sweep, each within the bound
   * scripts/web_local_utilities.py states for it (the twin builds the same inputs with the same
   * IEEE operations). `ease` is `1 - (1 - x)^(1/c)` or `(1 - (...)^c) / 2 + 1/2` on some branches:
   * the subtraction cancels, so its bound is absolute (one ulp of 1.0), not in ulps of the result.
   */
  private fun sweepsWithinBounds(): Boolean {
    var ok = true
    val check = { kind: Long, name: String, local: List<Double>, ulpBound: Long, absBound: Double ->
      @Suppress("UNCHECKED_CAST")
      val engine = twin().call("sweep", kind) as List<Double>
      var worstUlps = 0L
      var worstAbs = 0.0
      engine.forEachIndexed { index, value ->
        worstUlps = maxOf(worstUlps, kotlin.math.abs(local[index].toRawBits() - value.toRawBits()))
        worstAbs = maxOf(worstAbs, kotlin.math.abs(local[index] - value))
      }
      if (engine.size != local.size || worstUlps > ulpBound || worstAbs > absBound) {
        ok = false
        GD.print("gd sweep $name: ${engine.size} points, worst $worstUlps ulps / $worstAbs (bound $ulpBound ulps / $absBound)")
      }
    }
    val curves = listOf(-4.8, -3.0, -2.4, -1.0, -0.5, -0.2, 0.2, 0.4, 0.7, 1.5, 2.0, 4.8)
    check(0L, "ease", curves.flatMap { c -> (0..256).map { GD.ease(it / 256.0, c) } }, Long.MAX_VALUE, EASE_ABS_BOUND)
    check(1L, "linear_to_db", (0 until 2048).map { GD.linearToDb(sweepLinear(it)) }, LINEAR_TO_DB_ULPS, Double.MAX_VALUE)
    check(2L, "db_to_linear", (0 until 2048).map { GD.dbToLinear((it - 1024) / 16.0) }, 1L, Double.MAX_VALUE)
    return ok
  }

  private fun sweepLinear(i: Int): Double =
    if (i < 1024) (i + 1).toDouble() / 1024.0 * 4.0 else 1.0 + (i - 1536).toDouble() / 1048576.0

  /**
   * Task 129 E review B2: objects in a call's result keep the handle that already tracks them. 1 =
   * twenty rounds of the triggers (`GD.instanceFromId`, an Array result of `call("get_children")`,
   * an object result of `call`, getConnections' lambda guards) leave the live browser handles where
   * one warm round left them; 2 = `GD.instanceFromId(id) == ` the node; 4 =
   * `call("get_children")[i] == getChild(i)`.
   */
  fun handleRows(value: Long): Long {
    val parent = checkNotNull(self.getParent())
    val twin = twin()
    val round = {
      GD.instanceFromId(twin.instanceId)
      parent.call("get_children")
      twin.call("get_parent")
      self.signal("pair").getConnections()
    }
    round()
    val before = twinLong("live_handles")
    repeat(20) { round() }
    val after = twinLong("live_handles")
    var mask = 0L
    if (after == before) mask = mask or 1L else GD.print("handle rows: live handles $before -> $after")
    if (GD.instanceFromId(twin.instanceId) == twin) mask = mask or 2L
    val kids = parent.call("get_children") as List<*>
    val index = kids.indexOfFirst { it == twin }
    if (index >= 0 && kids[index] == parent.getChild(index)) mask = mask or 4L
    return mask
  }

  /**
   * Task 129 E fix 2: a call's result naming the object a signal dispatch lent Kotlin keeps that
   * handle after the handler returns (the dispatch used to release it under the kept result). Both
   * dispatch arms (`ping`: the one-object arm, `ping2`: packed arguments) receive a node no script
   * tracks; each handler keeps `GD.instanceFromId(arg.instanceId)` and the matching element of
   * `call("get_children")`. 1 = every kept result still answers after the dispatches; 2 = in every
   * round both results `==` the argument; 4 = twenty more rounds leave the live browser handles
   * where the first round left them.
   */
  fun lentRows(value: Long): Long {
    val twin = twin()
    val kept = mutableListOf<GodotObject>()
    var rounds = 0
    var same = 0
    val keep = { arg: GodotObject ->
      rounds++
      val found = GD.instanceFromId(arg.instanceId)
      val child = (twin.call("get_children") as List<*>).firstOrNull { it == arg }
      if (found != null) kept += found
      if (child is GodotObject) kept += child
      if (found == arg && child == arg) same++
    }
    val one = twin.signal("ping").connectObject(self) { keep(it) }
    val packed =
      Signal2(twin, "ping2", SignalArgType.VARIANT, SignalArgType.LONG).connect(self) { arg, _ ->
        keep(arg as GodotObject)
      }
    WebExperimentalGenericCall.callImmediate(twin, "emit_ping", listOf(1L))
    val before = twinLong("live_handles")
    WebExperimentalGenericCall.callImmediate(twin, "emit_ping", listOf(20L))
    val after = twinLong("live_handles")
    one?.close()
    packed.close()
    var mask = 0L
    val answering =
      kept.count {
        try {
          GD.isInstanceValid(it) && it.call("get_name") == "LentPing"
        } catch (e: Throwable) {
          false
        }
      }
    if (kept.size == 4 * 21 && answering == kept.size) mask = mask or 1L
    else GD.print("lent rows: ${kept.size} kept, $answering answer")
    if (rounds == 2 * 21 && same == rounds) mask = mask or 2L
    else GD.print("lent rows: $same of $rounds rounds kept identity")
    if (after == before) mask = mask or 4L
    else GD.print("lent rows: live handles $before -> $after")
    return mask
  }

  private var guardsBefore = 0L

  /**
   * Task 129 E review S1, first half: a lambda connection from this script's `solo` signal to the
   * script-backed GuardReceiver (its receiver), then the receiver is freed. 1 = it connected and
   * its guard is live.
   */
  fun guardReceiverConnect(value: Long): Long {
    guardsBefore = twinLong("guard_count")
    val receiver = self.requireAs("../GuardReceiver", ::Node)
    val connected = self.signal("solo").connectLong(receiver) { _ -> Unit }
    val live = self.signal("solo").hasConnections() && twinLong("guard_count") == guardsBefore + 1
    receiver.queueFree()
    return if (connected == GodotError.OK && live) 1L else 0L
  }

  /** After the frees: 1 = the connection ended with its receiver and no guard remains. */
  fun guardReceiverAfter(value: Long): Long =
    if (!self.signal("solo").hasConnections() && twinLong("guard_count") == guardsBefore) 1L else 0L

  private fun twinLong(method: String): Long =
    WebExperimentalGenericCall.callImmediate(twin(), method, listOf(0L)).asLong()

  private fun twin(): Node = self.requireAs("../GdTwin", ::Node)

  private fun twinText(index: Int): String =
    WebExperimentalGenericCall.callImmediate(twin(), "gd_text", listOf(index.toLong())).asString()
}
