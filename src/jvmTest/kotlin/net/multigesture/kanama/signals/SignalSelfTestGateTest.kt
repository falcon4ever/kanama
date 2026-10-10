package net.multigesture.kanama.signals

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import net.multigesture.kanama.binding.runtime.SignalSelfTest

/** When the on-device typed-signal self-test runs (task 138 item 23). */
class SignalSelfTestGateTest {
  private fun decide(override: String, debug: Boolean, editor: Boolean) =
    SignalSelfTest.decide(override, { debug }, { editor })

  @Test
  fun runsInDebugGameBuildsOnly() {
    assertTrue(decide("", debug = true, editor = false))
    assertFalse(decide("", debug = false, editor = false))
    assertFalse(decide("", debug = true, editor = true))
  }

  @Test
  fun theEnvironmentForcesItOnOrOff() {
    assertTrue(decide("1", debug = false, editor = true))
    assertTrue(decide(" ON ", debug = false, editor = false))
    assertFalse(decide("0", debug = true, editor = false))
    assertFalse(decide("off", debug = true, editor = false))
    assertTrue(decide("maybe", debug = true, editor = false))
  }
}
