package net.multigesture.kanama.wrappers

import kotlin.test.Test
import kotlin.test.assertNotNull
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.types.Vector3

/**
 * Task 129 B review: GDScript's Variant-form utilities (`max`, `abs`, `clamp`, ...) on numbers give
 * typed results in Kotlin too. A compile test: the declared types below only compile when overload
 * resolution picks the typed `Double` / `Long` / `Int` overloads, and a vector still reaches the
 * `Any?` form. The calls need a running engine, so the probe is compiled, not invoked.
 */
class GdTypedOverloadsTest {
  @Suppress("UNUSED_VARIABLE")
  private val probe: () -> Unit = {
    val m: Double = GD.max(1.5, 2.0)
    val n: Long = GD.max(1L, 2L)
    val i: Int = GD.max(1, 2)
    val mixed: Long = GD.max(1, 2L)
    val c: Double = GD.clamp(5.0, 0.0, 1.0)
    val w: Long = GD.wrap(7L, 0L, 5L)
    val a: Double = GD.abs(-2.5)
    val s: Long = GD.sign(-3L)
    val f: Double = GD.floor(2.5)
    val l: Double = GD.lerp(0.0, 10.0, 0.25)
    val sn: Double = GD.snapped(7.3, 0.5)
    val v: Any? = GD.abs(Vector3(1.0, -2.0, 3.0))
    val three: Any? = GD.max(1.0, 2.0, 3.0)
  }

  @Test
  fun typedOverloadsResolve() {
    assertNotNull(probe)
  }
}
