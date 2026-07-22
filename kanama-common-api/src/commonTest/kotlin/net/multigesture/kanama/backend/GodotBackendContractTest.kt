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
    probe.drawTexture(
      GodotHandle.fromBackendToken(23),
      GodotVector2(12.0f, 34.0f),
      GodotColor(1.0f, 0.5f, 0.25f),
    )
    assertEquals(
      31L,
      ResourceLoaderBackendContractProbe.load("res://bunny.svg", "Texture2D")?.backendToken(),
    )
    GDBackendContractProbe.randomize()
    assertEquals(4_294_967_295L, GDBackendContractProbe.randi())
    assertEquals(0.75, GDBackendContractProbe.randf())
    assertEquals(0, probe.emitSignal("benchmark_finished", 42))
    assertEquals(7L, probe.getChildCount())

    assertEquals(
      mapOf(
        1 to 1,
        2 to 1,
        3 to 1,
        4 to 1,
        5 to 1,
        6 to 1,
        7 to 1,
        8 to 1,
        9 to 1,
        10 to 1,
        11 to 1,
      ),
      backend.resolveCounts,
    )
    assertEquals(1, backend.queuedRedraws)
    assertEquals(
      DrawCall(
        textureToken = 23,
        position = GodotVector2(12.0f, 34.0f),
        modulate = GodotColor(1.0f, 0.5f, 0.25f),
      ),
      backend.drawCall,
    )
    assertEquals(1, backend.randomizeCalls)
    assertEquals("benchmark_finished" to 42, backend.emittedSignal)
  }

  private data class DrawCall(
    val textureToken: Long,
    val position: GodotVector2,
    val modulate: GodotColor,
  )

  private class RecordingBackend : GodotBackendSpi {
    val resolveCounts = mutableMapOf<Int, Int>()
    private var position = GodotVector2(1.0f, 2.0f)
    var queuedRedraws = 0
    var drawCall: DrawCall? = null
    var randomizeCalls = 0
    var emittedSignal: Pair<String, Int>? = null

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

    override fun invokeTexture2DVector2ColorArgs(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      texture: GodotHandle,
      position: GodotVector2,
      modulate: GodotColor,
    ) {
      drawCall = DrawCall(texture.backendToken(), position, modulate)
    }

    override fun invokeStringStringLongRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      first: String,
      second: String,
      value: Long,
    ): GodotHandle? {
      assertEquals("res://bunny.svg", first)
      assertEquals("Texture2D", second)
      assertEquals(1L, value)
      return GodotHandle.fromBackendToken(31)
    }

    override fun invokeUtilityNoArgsVoid(descriptor: GodotCallDescriptor, callSite: GodotCallSite) {
      randomizeCalls += 1
    }

    override fun invokeUtilityNoArgsRetLong(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
    ): Long = 4_294_967_295L

    override fun invokeUtilityNoArgsRetDouble(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
    ): Double = 0.75

    override fun invokeStringNameIntRetInt(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      name: String,
      value: Int,
    ): Int {
      emittedSignal = name to value
      return 0
    }
  }
}
