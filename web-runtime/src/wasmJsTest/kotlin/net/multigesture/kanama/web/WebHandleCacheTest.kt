package net.multigesture.kanama.web

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Fix round 2: `GodotObject.instanceId` is kept per handle token, so a fresh wrapper of the same
 * handle (`getChild(0).instanceId` every frame) does not cross again, and a released token reads
 * anew.
 */
class WebHandleCacheTest {
  @Test
  fun aSecondReadOfTheSameHandleDoesNotFetchAndAReleaseForgetsIt() {
    val token = 0x40012345
    var fetches = 0
    val fetch = {
      fetches++
      77L + fetches
    }
    WebHandleCache.forget(token)
    assertEquals(78L, WebHandleCache.read(token, "get_instance_id", fetch))
    assertEquals(78L, WebHandleCache.read(token, "get_instance_id", fetch))
    assertEquals(1, fetches)
    WebHandleCache.forget(token)
    assertEquals(79L, WebHandleCache.read(token, "get_instance_id", fetch))
    assertEquals(2, fetches)
    WebHandleCache.forget(token)
  }

  private class FakeScript(handle: Int) : KanamaWebScript(WebObjectId(handle))

  /**
   * Fix round 3: a freed script slot is reused under generation + 1, and nothing kept for the old
   * token survives to answer for the new object.
   */
  @Test
  fun aFreedScriptSlotIsReusedWithoutTheOldTokensCachedValue() {
    val registry = WebInstanceRegistry { _, handle -> FakeScript(handle) }
    val old = registry.reserve(scriptId = 1)
    registry.construct(old) { build -> build() }
    WebHandleCache.read(old, "get_instance_id") { 4242L }
    assertTrue(WebHandleCache.contains(old))

    assertTrue(registry.free(old))
    assertFalse(WebHandleCache.contains(old))

    val reused = registry.reserve(scriptId = 1)
    assertEquals(old and 0xffff, reused and 0xffff)
    assertEquals((old ushr 16) + 1, reused ushr 16)
    registry.construct(reused) { build -> build() }
    assertEquals(9L, WebHandleCache.read(reused, "get_instance_id") { 9L })
    registry.free(reused)
  }

  /** Fix round 3: every release path of a browser handle token goes through a forget. */
  @Test
  fun unregisteringABrowserHandleForgetsItsCachedValues() {
    for (kind in WebBrowserHandleKind.entries) {
      val handle = 0x40020000 + kind.ordinal
      registerWebBrowserHandle(handle, kind)
      WebHandleCache.read(handle, "get_instance_id") { 1L }
      assertTrue(WebHandleCache.contains(handle), "$kind")
      unregisterWebBrowserHandle(handle, kind)
      assertFalse(WebHandleCache.contains(handle), "$kind")
    }
  }

  @Test
  fun discardingABrowserHandleForgetsItsCachedValues() {
    val handle = 0x40030002
    registerWebBrowserHandle(handle, WebBrowserHandleKind.OBJECT)
    WebHandleCache.read(handle, "get_instance_id") { 1L }
    assertTrue(discardWebBrowserHandle(handle))
    assertFalse(WebHandleCache.contains(handle))
  }

  /** Fix round 3: build, read, free over many cycles leaves the cache where it started. */
  @Test
  fun theCacheStaysBoundedOverManyBuildReadFreeCycles() {
    val registry = WebInstanceRegistry { _, handle -> FakeScript(handle) }
    val before = WebHandleCache.size
    repeat(2000) { cycle ->
      val script = registry.reserve(scriptId = 1)
      registry.construct(script) { build -> build() }
      WebHandleCache.read(script, "get_instance_id") { cycle.toLong() }
      registry.free(script)
      val node = 0x40040000 + cycle
      registerWebBrowserHandle(node, WebBrowserHandleKind.NODE)
      WebHandleCache.read(node, "get_instance_id") { cycle.toLong() }
      assertTrue(discardWebBrowserHandle(node, freed = true))
      assertTrue(WebHandleCache.size <= before + 1, "cycle $cycle: ${WebHandleCache.size}")
    }
    assertEquals(before, WebHandleCache.size)
  }
}
