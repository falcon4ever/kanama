package net.multigesture.kanama.web

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import net.multigesture.kanama.binding.runtime.ScriptErrorFrame
import net.multigesture.kanama.binding.runtime.ScriptErrorReport

/**
 * Task 131 item 14: the stack lines a browser gives a Kotlin/Wasm exception, and the report built
 * from them. The production export's lines name no function and no file (see
 * [WebScriptErrors.framesOf]); the named forms are what a build with a name section or a resolved
 * source map would give.
 */
class WebScriptErrorsTest {
  private fun frame(line: String): ScriptErrorFrame? = WebScriptErrors.parseFrame(line)

  @Test
  fun anonymousWasmFramesAndJsHelpersAreNotFrames() {
    assertNull(frame("IllegalStateException: boom"))
    assertNull(frame("    at kotlin.createJsError (http://h/kanama-web-spike.js?v=1:1:9352)"))
    assertNull(
      frame("    at Object.createJsError__externalAdapter (file:///x/import-object.mjs:31:11)")
    )
    assertNull(frame("    at http://h/6a28.wasm:wasm-function[670]:0x38d93"))
    assertNull(frame("globalThis.requestAnimationFrame/<@http://h/kanama-web-bridge.js:2040:17"))
    assertNull(frame("    at Object.run (http://h/index.js:12:5)"))
    assertNull(frame("    at some.pkg.Helper (http://h/kanama-web-bridge.js:9:1)"))
    assertNull(frame("wasm-function[670]@http://h/6a28.wasm:wasm-function[670]:0x38d93"))
    assertNull(frame("    at wasm-function[670] (http://h/6a28.wasm:wasm-function[670]:0x38d93)"))
  }

  @Test
  fun aNamedFrameWithoutASourceKeepsItsClassAndMethod() {
    val parsed =
      frame("    at web3d.ErrorProbe.errorThrow (http://h/a.wasm:wasm-function[12]:0xabc)")!!
    assertEquals("web3d.ErrorProbe", parsed.className)
    assertEquals("errorThrow", parsed.methodName)
    assertEquals("", parsed.fileName)
    assertEquals(0, parsed.line)
  }

  @Test
  fun aSourceMappedFrameCarriesTheKotlinFileAndLine() {
    val chrome = frame("    at web3d.ErrorProbe.errorThrow (http://h/src/ErrorProbe.kt:41:9)")!!
    assertEquals("ErrorProbe.kt", chrome.fileName)
    assertEquals(41, chrome.line)
    val gecko = frame("web3d.ErrorProbe.errorThrow@http://h/src/ErrorProbe.kt:41:9")!!
    assertEquals("web3d.ErrorProbe", gecko.className)
    assertEquals("ErrorProbe.kt", gecko.fileName)
    assertEquals(41, gecko.line)
  }

  @Test
  fun aThrownExceptionIsReportedWithItsTypeMessageAndContainmentSite() {
    val report = WebScriptErrors.reportFor(IllegalStateException("boom"), "ErrorProbe.error_throw")
    assertEquals("kotlin.IllegalStateException", report.description)
    assertEquals("kotlin.IllegalStateException: boom", report.message)
    // The production export's trace names no function (the report falls back to the containment
    // site); this Node test build keeps the name section, so it reports the throwing frame instead.
    assertTrue(
      report.function == "ErrorProbe.error_throw" || report.function.contains("aThrownException"),
      report.function,
    )
    assertEquals("", report.file)
  }

  @Test
  fun theRenderedTextLooksLikeDesktopsScriptError() {
    val text =
      WebScriptErrors.render(
        ScriptErrorReport(
          description = "kotlin.IllegalStateException",
          message = "kotlin.IllegalStateException: boom",
          function = "ErrorProbe.error_throw",
          file = "",
          line = 0,
        )
      )
    assertEquals(
      "SCRIPT ERROR: kotlin.IllegalStateException: boom\n" +
        "   at: ErrorProbe.error_throw (no source line on Wasm)",
      text,
    )
    val located =
      WebScriptErrors.render(
        ScriptErrorReport("e", "e: m", "Player.ready", file = "Player.kt", line = 12)
      )
    assertEquals("SCRIPT ERROR: e: m\n   at: Player.ready (Player.kt:12)", located)
  }
}
