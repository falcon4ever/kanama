package net.multigesture.kanama.web

import kotlin.test.Test
import kotlin.test.assertEquals

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
}
