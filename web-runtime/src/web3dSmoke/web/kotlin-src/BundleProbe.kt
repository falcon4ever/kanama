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
   * Task 129 E: the shared GD on Web against its GDScript twin, bit for bit. Bit i = case i of
   * `gd_twin.gd`'s gd_value (0..13); bit 14 = str; bit 15 = isInstanceValid / instanceFromId.
   */
  fun gdRows(value: Long): Long {
    val kotlin =
      listOf(
        { GD.sin(0.7) },
        { GD.pow(2.5, 1.3) },
        { GD.atan2(0.3, -0.8) },
        { GD.lerpf(1.0, 3.0, 0.25) },
        { GD.snappedf(7.37, 0.25) },
        { GD.wrapf(-0.5, 0.0, 2.0) },
        { Mathf.sin(1.1) },
        { Mathf.smoothStep(0.0, 2.0, 0.6) },
        {
          GD.seed(42L)
          GD.randf()
        },
        { GD.fposmod(-5.5, 3.0) },
        { Mathf.ease(0.4, 2.0) },
        { Mathf.dbToLinear(-6.0) },
        { Mathf.roundToInt(2.5).toDouble() },
        { Mathf.exp(1.7) },
      )
    var mask = 0L
    kotlin.forEachIndexed { index, case ->
      val twin = WebExperimentalGenericCall.callImmediate(twin(), "gd_value", listOf(index.toLong())).asDouble()
      if (case().toRawBits() == twin.toRawBits()) mask = mask or (1L shl index)
    }
    if (GD.str(1L, "a", 2.5) == twinText(1)) mask = mask or (1L shl 14)
    if (
      GD.isInstanceValid(self) &&
        GD.instanceFromId(self.instanceId)?.isSameInstance(self) == true
    ) {
      mask = mask or (1L shl 15)
    }
    return mask
  }

  private fun twin(): Node = self.requireAs("../GdTwin", ::Node)

  private fun twinText(index: Int): String =
    WebExperimentalGenericCall.callImmediate(twin(), "gd_text", listOf(index.toLong())).asString()
}
