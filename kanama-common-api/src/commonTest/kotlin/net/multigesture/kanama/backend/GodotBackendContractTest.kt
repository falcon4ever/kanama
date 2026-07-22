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
    val texture = ResourceLoaderBackendContractProbe.load("res://bunny.svg", "Texture2D")
    assertEquals(31L, texture?.backendToken())
    val sprite = ClassDBBackendContractProbe.instantiate("Sprite2D")
    assertEquals(41L, sprite?.backendToken())
    val node = NodeBackendContractProbe(probe.handle)
    node.addChild(checkNotNull(sprite))
    Sprite2DBackendContractProbe(sprite).setTexture(texture)
    node.removeChild(sprite)
    NodeBackendContractProbe(sprite).queueFree()
    val board = NodeLookupBackendContractProbe(probe.handle).getNodeOrNull("Board")
    assertEquals(51L, board?.backendToken())
    val viewport = NodeLookupBackendContractProbe(probe.handle).getViewport()
    assertEquals(52L, viewport?.backendToken())
    assertEquals(
      GodotRect2(GodotVector2(0.0f, 0.0f), GodotVector2(640.0f, 480.0f)),
      ViewportBackendContractProbe(checkNotNull(viewport)).visibleRect,
    )
    val tile = PackedSceneBackendContractProbe(checkNotNull(texture)).instantiate()
    assertEquals(53L, tile?.backendToken())
    InputBackendContractProbe.setCustomMouseCursor(texture)
    assertEquals(
      0L,
      SignalBackendContractProbe(checkNotNull(tile))
        .connect(probe.handle, "tile_pressed", "_on_tile_pressed"),
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
        12 to 1,
        13 to 1,
        14 to 1,
        15 to 1,
        16 to 1,
        17 to 1,
        18 to 1,
        19 to 1,
        20 to 1,
        21 to 1,
        22 to 1,
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
    assertEquals("Sprite2D", backend.constructedClass)
    assertEquals(Triple(17L, 41L, false), backend.addedChild)
    assertEquals(17L to 41L, backend.removedChild)
    assertEquals(41L to 31L, backend.objectArgument)
    assertEquals(41L, backend.queuedFree)
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
    var constructedClass: String? = null
    var addedChild: Triple<Long, Long, Boolean>? = null
    var removedChild: Pair<Long, Long>? = null
    var objectArgument: Pair<Long, Long?>? = null
    var queuedFree: Long? = null

    override fun requireLive(handle: GodotHandle) {
      require(handle.backendToken() in setOf(17L, 31L, 41L, 51L, 52L, 53L))
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
      if (descriptor.opcode == 5) queuedRedraws += 1 else queuedFree = receiver.backendToken()
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

    override fun invokeStringNameRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      value: String,
    ): GodotHandle {
      constructedClass = value
      return GodotHandle.fromBackendToken(41L)
    }

    override fun invokeObjectBoolLongArgs(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      objectValue: GodotHandle,
      boolValue: Boolean,
      longValue: Long,
    ) {
      assertEquals(0L, longValue)
      addedChild = Triple(receiver.backendToken(), objectValue.backendToken(), boolValue)
    }

    override fun invokeObjectArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: GodotHandle?,
    ) {
      val call = receiver.backendToken() to value?.backendToken()
      if (descriptor.opcode == 14) removedChild = call.first to checkNotNull(call.second)
      else objectArgument = call
    }

    override fun invokeNodePathRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      path: String,
    ): GodotHandle? {
      assertEquals("Board", path)
      return GodotHandle.fromBackendToken(51L)
    }

    override fun invokeLongRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: Long,
    ): GodotHandle? {
      assertEquals(0L, value)
      return GodotHandle.fromBackendToken(53L)
    }

    override fun invokeNoArgsRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): GodotHandle? = GodotHandle.fromBackendToken(52L)

    override fun invokeObjectLongVector2Args(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      objectValue: GodotHandle?,
      longValue: Long,
      vectorValue: GodotVector2,
    ) {
      assertEquals(31L, objectValue?.backendToken())
      assertEquals(0L, longValue)
      assertEquals(GodotVector2(0.0f, 0.0f), vectorValue)
    }

    override fun invokeStringNameCallableLongRetLong(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      signal: String,
      target: GodotHandle,
      method: String,
      flags: Long,
    ): Long {
      assertEquals(53L, receiver.backendToken())
      assertEquals("tile_pressed", signal)
      assertEquals(17L, target.backendToken())
      assertEquals("_on_tile_pressed", method)
      assertEquals(0L, flags)
      return 0L
    }
  }
}
