package web3d

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotCallable
import net.multigesture.kanama.api.GodotError
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotSignal
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Mathf
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.builtins.godotFormat
import net.multigesture.kanama.web.WebExperimentalGenericCall

/**
 * Harness-only (task 129 E, task 138 items 2 and 20): the Web parity rows of the web bundle. Main's
 * `bundle_probe` / `bundle_probe_after` call these and fold the masks for the driver.
 */
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
   * bit-exact; 1: a libm transcendental). Bit 0 = every row within its bound (a mismatch prints
   * the row), bit 1 = str, bit 2 = isInstanceValid / instanceFromId.
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
        1 to { GD.linearToDb(0.3) },
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
      )
    var mask = 1L
    rows.forEachIndexed { index, (bound, case) ->
      val twin = WebExperimentalGenericCall.callImmediate(twin(), "gd_value", listOf(index.toLong())).asDouble()
      val local = case()
      val ulps = kotlin.math.abs(local.toRawBits() - twin.toRawBits())
      if (ulps > bound) {
        mask = mask and 1L.inv()
        GD.print("gd twin row $index: Kotlin $local vs engine $twin ($ulps ulps, bound $bound)")
      }
    }
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
