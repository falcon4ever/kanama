package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node2D
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Task 134 B probe, run by `scripts/runtime_smoke.sh` (`builtin_reentry_smoke.tscn`) beside
 * `builtin_reentry_ref.gd`, which prints the same results from GDScript in the same run.
 *
 * An engine builtin can re-enter Kotlin while it runs: its WARN/ERR print reaches every logger
 * synchronously, and `builtin_reentry_logger.gd` (installed by `builtin_reentry_install.gd`) then
 * calls [scribble], which makes builtin calls of its own. Those nested calls must not overwrite the
 * outer call's frame, which the engine is still reading: `Basis.lookingAt` with a colinear `up`
 * warns and then keeps using `up`; `Color.html` with a bad code errors after reading its String.
 */
@ScriptClass(attachTo = "Node2D")
class BuiltinReentrySmoke(godotObject: GodotHandle) : KanamaScript<Node2D>(godotObject, ::Node2D) {
  var hits = 0L

  /** Called by the GDScript logger from inside an engine error print: writes builtin frames. */
  fun scribble(): Long {
    hits++
    val s = Basis.lookingAt(Vector3(7.0, 0.0, 0.0), Vector3(0.0, 0.0, 7.0), true)
    val c = Color.fromString("#123456", Color(0.0, 0.0, 0.0, 0.0))
    return (s.x.x + c.r).toLong()
  }

  @OnReady
  fun ready() {
    val b = Basis.lookingAt(Vector3(0.0, 2.0, 0.0), Vector3(0.0, 1.0, 0.0), false)
    report("basis=$b")
    val m =
      AABB(Vector3(0.0, 0.0, 0.0), Vector3(-1.0, 1.0, 1.0))
        .merge(AABB(Vector3(5.0, 5.0, 5.0), Vector3(1.0, 1.0, 1.0)))
    report("merge=$m")
    val h = Color.html("#zz")
    report("html=$h")
    val n = Vector2(1.0, 2.0).slerp(Vector2(3.0, 4.0).slerp(Vector2(5.0, 1.0), 0.25), 0.5)
    report("nested=$n")
    report("hits=${if (hits > 0) "reentered" else "none"}")
  }

  private fun report(line: String) {
    System.err.println("[kanama:kt] BuiltinReentry kotlin $line")
  }
}
