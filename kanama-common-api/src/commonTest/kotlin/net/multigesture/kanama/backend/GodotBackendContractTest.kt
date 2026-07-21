package net.multigesture.kanama.backend

import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(InternalKanamaBackendApi::class)
class GodotBackendContractTest {
  @AfterTest
  fun resetBackend() {
    GodotBackendCalls.resetForTests()
  }

  @Test
  fun initialDescriptorsPinHashesShapesAndExecutionModes() {
    assertEquals(894402480L, InitialGodotCallDescriptors.NODE_GET_CHILD_COUNT.hash)
    assertEquals(
      GodotExecutionMode.IMMEDIATE_RESULT,
      InitialGodotCallDescriptors.NODE_GET_CHILD_COUNT.executionMode,
    )
    assertEquals(3341600327L, InitialGodotCallDescriptors.NODE2D_GET_POSITION.hash)
    assertEquals(
      GodotExecutionMode.SNAPSHOT_READ,
      InitialGodotCallDescriptors.NODE2D_GET_POSITION.executionMode,
    )
    assertEquals(743155724L, InitialGodotCallDescriptors.NODE2D_SET_POSITION.hash)
    assertEquals(
      GodotExecutionMode.QUEUED_MUTATION,
      InitialGodotCallDescriptors.NODE2D_SET_POSITION.executionMode,
    )
  }

  @Test
  fun commonProbeUsesTypedCallsAndCachesResolvedCallSites() {
    val backend = RecordingBackend()
    GodotBackendCalls.install(backend)
    val probe = Node2DBackendContractProbe(GodotHandle.fromBackendToken(17))

    assertEquals(GodotVector2(1.0f, 2.0f), probe.position)
    assertEquals(GodotVector2(1.0f, 2.0f), probe.position)
    probe.position = GodotVector2(3.0f, 4.0f)
    assertEquals(GodotVector2(3.0f, 4.0f), probe.position)
    assertEquals(
      GodotRect2(GodotVector2(0.0f, 0.0f), GodotVector2(640.0f, 480.0f)),
      probe.viewportRect,
    )
    probe.queueRedraw()
    assertEquals(7L, probe.getChildCount())

    assertEquals(mapOf(1 to 1, 2 to 1, 3 to 1, 4 to 1, 5 to 1), backend.resolveCounts)
    assertEquals(1, backend.queuedRedraws)
  }

  private class RecordingBackend : GodotBackendSpi {
    val resolveCounts = mutableMapOf<Int, Int>()
    private var position = GodotVector2(1.0f, 2.0f)
    var queuedRedraws = 0

    override fun requireLive(handle: GodotHandle) {
      require(handle.backendToken() == 17L)
    }

    override fun resolve(descriptor: GodotCallDescriptor): GodotCallSite {
      resolveCounts[descriptor.opcode] = resolveCounts.getOrElse(descriptor.opcode) { 0 } + 1
      return GodotCallSite.fromBackendToken(descriptor.opcode.toLong())
    }

    override fun invokeBoolRetInt(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: Boolean,
    ): Int = 7

    override fun invokeNoArgsRetVector2(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): GodotVector2 = position

    override fun invokeVector2Arg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: GodotVector2,
    ) {
      position = value
    }

    override fun invokeNoArgsRetRect2(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): GodotRect2 = GodotRect2(GodotVector2(0.0f, 0.0f), GodotVector2(640.0f, 480.0f))

    override fun invokeNoArgsVoid(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ) {
      queuedRedraws += 1
    }
  }
}
