package net.multigesture.kanama.signals

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import net.multigesture.kanama.binding.runtime.SignalSelfTest

/** When the on-device typed-signal self-test runs (task 138 item 23): opt-in, never by default. */
class SignalSelfTestGateTest {
  private fun decide(override: String, setting: Boolean, debug: Boolean, editor: Boolean) =
    SignalSelfTest.decide(override, { setting }, { debug }, { editor })

  @Test
  fun offUnlessTheProjectSettingOptsIn() {
    assertFalse(decide("", setting = false, debug = true, editor = false))
    assertTrue(decide("", setting = true, debug = true, editor = false))
  }

  @Test
  fun theSettingStillNeedsADebugGameBuild() {
    assertFalse(decide("", setting = true, debug = false, editor = false))
    assertFalse(decide("", setting = true, debug = true, editor = true))
  }

  @Test
  fun theEnvironmentForcesItOnOrOff() {
    assertTrue(decide("1", setting = false, debug = false, editor = true))
    assertTrue(decide(" ON ", setting = false, debug = true, editor = false))
    assertFalse(decide("0", setting = true, debug = true, editor = false))
    assertFalse(decide("off", setting = true, debug = true, editor = false))
    assertFalse(decide("maybe", setting = false, debug = true, editor = false))
    assertTrue(decide("maybe", setting = true, debug = true, editor = false))
  }

  @Test
  fun theSettingIsNotReadWhenTheEnvironmentDecides() {
    var read = false
    SignalSelfTest.decide(
      "1",
      {
        read = true
        true
      },
      { true },
      { false },
    )
    assertFalse(read)
  }
}
