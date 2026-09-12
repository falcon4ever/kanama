package net.multigesture.kanama.backend

import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

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
    assertDescriptor(
      InitialGodotCallDescriptors.GPUPARTICLES2D_SET_EMITTING,
      2_586_408_642L,
      GodotCallShape.BOOL_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.GPUPARTICLES2D_IS_EMITTING,
      36_873_697L,
      GodotCallShape.NOARGS_RET_BOOL,
      GodotExecutionMode.SNAPSHOT_READ,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.GPUPARTICLES2D_GET_LIFETIME,
      1_740_695_150L,
      GodotCallShape.NOARGS_RET_DOUBLE,
      GodotExecutionMode.SNAPSHOT_READ,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.AUDIOSTREAMPLAYER_SET_STREAM,
      2_210_767_741L,
      GodotCallShape.OBJECT_ARG,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.AUDIOSTREAMPLAYER_SET_BUS,
      3_304_788_590L,
      GodotCallShape.STRINGNAME_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.AUDIOSTREAMPLAYER_SET_VOLUME_DB,
      373_806_689L,
      GodotCallShape.DOUBLE_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.AUDIOSTREAMPLAYER_SET_PITCH_SCALE,
      373_806_689L,
      GodotCallShape.DOUBLE_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.AUDIOSTREAMPLAYER_PLAY,
      1_958_160_172L,
      GodotCallShape.DOUBLE_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.NODE_GET_TREE,
      2_958_820_483L,
      GodotCallShape.NOARGS_RET_HANDLE,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.SCENETREE_QUIT,
      1_995_695_955L,
      GodotCallShape.LONG_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.NODE3D_SET_POSITION,
      3_460_891_852L,
      GodotCallShape.VECTOR3_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.NODE3D_GET_POSITION,
      3_360_562_783L,
      GodotCallShape.NOARGS_RET_VECTOR3,
      GodotExecutionMode.SNAPSHOT_READ,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.NODE3D_SET_SCALE,
      3_460_891_852L,
      GodotCallShape.VECTOR3_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.CANVASLAYER_SET_VISIBLE,
      2_586_408_642L,
      GodotCallShape.BOOL_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.WORLDENVIRONMENT_GET_ENVIRONMENT,
      3_082_064_660L,
      GodotCallShape.NOARGS_RET_HANDLE,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.ENVIRONMENT_SET_BG_ENERGY_MULTIPLIER,
      373_806_689L,
      GodotCallShape.DOUBLE_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.OS_HAS_FEATURE,
      3_927_539_163L,
      GodotCallShape.STRINGNAME_RET_BOOL_SINGLETON,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.LIGHT3D_SET_PARAM,
      1_722_734_213L,
      GodotCallShape.LONG_DOUBLE_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.RENDERINGSERVER_GET_CURRENT_RENDERING_METHOD,
      201_670_096L,
      GodotCallShape.NOARGS_RET_STRING_SINGLETON,
      GodotExecutionMode.SNAPSHOT_READ,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.INPUT_ACTION_PRESS,
      1_713_091_165L,
      GodotCallShape.STRINGNAME_ARG_SINGLETON,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.INPUT_ACTION_RELEASE,
      3_304_788_590L,
      GodotCallShape.STRINGNAME_ARG_SINGLETON,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.CANVASITEM_IS_VISIBLE,
      36_873_697L,
      GodotCallShape.NOARGS_RET_BOOL,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.PATHFOLLOW3D_SET_PROGRESS_RATIO,
      373_806_689L,
      GodotCallShape.DOUBLE_ARG,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.NODE3D_LOOK_AT_FROM_POSITION,
      2_086_826_090L,
      GodotCallShape.VECTOR3_VECTOR3_ARG,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.NODE3D_ROTATE_Y,
      373_806_689L,
      GodotCallShape.DOUBLE_ARG,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.CHARACTERBODY3D_GET_SLIDE_COLLISION_COUNT,
      3_905_245_786L,
      GodotCallShape.NOARGS_RET_LONG,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.CHARACTERBODY3D_GET_SLIDE_COLLISION,
      107_003_663L,
      GodotCallShape.LONG_RET_HANDLE,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.KINEMATICCOLLISION3D_GET_COLLIDER,
      2_639_523_548L,
      GodotCallShape.NOARGS_RET_HANDLE,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.KINEMATICCOLLISION3D_GET_NORMAL,
      1_914_908_202L,
      GodotCallShape.NOARGS_RET_VECTOR3,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    // Node.duplicate returns a brand-new node the caller owns until add_child.
    assertEquals(3_511_555_459L, InitialGodotCallDescriptors.NODE_DUPLICATE.hash)
    assertEquals(GodotCallShape.NOARGS_RET_HANDLE, InitialGodotCallDescriptors.NODE_DUPLICATE.shape)
    assertEquals(
      GodotExecutionMode.IMMEDIATE_RESULT,
      InitialGodotCallDescriptors.NODE_DUPLICATE.executionMode,
    )
    assertEquals(
      GodotReturnOwnership.OWNED,
      InitialGodotCallDescriptors.NODE_DUPLICATE.returnOwnership,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.DIRECTIONALLIGHT3D_SET_SKY_MODE,
      2_691_194_817L,
      GodotCallShape.LONG_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.RENDERINGSERVER_DIRECTIONAL_SOFT_SHADOW_FILTER_SET_QUALITY,
      3_613_045_266L,
      GodotCallShape.LONG_ARG_SINGLETON,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.INPUTEVENT_IS_ACTION_PRESSED,
      1_631_499_404L,
      GodotCallShape.STRINGNAME_RET_BOOL,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.RAYCAST3D_SET_TARGET_POSITION,
      3_460_891_852L,
      GodotCallShape.VECTOR3_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.RAYCAST3D_GET_TARGET_POSITION,
      3_360_562_783L,
      GodotCallShape.NOARGS_RET_VECTOR3,
      GodotExecutionMode.SNAPSHOT_READ,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.RAYCAST3D_FORCE_RAYCAST_UPDATE,
      3_218_959_716L,
      GodotCallShape.NOARGS_VOID,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.RAYCAST3D_IS_COLLIDING,
      36_873_697L,
      GodotCallShape.NOARGS_RET_BOOL,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.RAYCAST3D_GET_COLLIDER,
      1_981_248_198L,
      GodotCallShape.NOARGS_RET_HANDLE,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.RAYCAST3D_GET_COLLISION_POINT,
      3_360_562_783L,
      GodotCallShape.NOARGS_RET_VECTOR3,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.RAYCAST3D_GET_COLLISION_NORMAL,
      3_360_562_783L,
      GodotCallShape.NOARGS_RET_VECTOR3,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.CHARACTERBODY3D_IS_ON_CEILING,
      36_873_697L,
      GodotCallShape.NOARGS_RET_BOOL,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.INPUT_SET_MOUSE_MODE,
      2_228_490_894L,
      GodotCallShape.LONG_ARG_SINGLETON,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.INPUTEVENTMOUSEMOTION_GET_RELATIVE,
      3_341_600_327L,
      GodotCallShape.NOARGS_RET_VECTOR2,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.ANIMATEDSPRITE3D_PLAY,
      3_269_405_555L,
      GodotCallShape.STRINGNAME_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.ANIMATEDSPRITE3D_SET_FRAME,
      1_286_410_249L,
      GodotCallShape.LONG_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.TEXTURERECT_SET_TEXTURE,
      4_051_416_890L,
      GodotCallShape.OBJECT_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.OBJECT_HAS_METHOD,
      2_619_796_661L,
      GodotCallShape.STRINGNAME_RET_BOOL,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.OBJECT_CALL_DOUBLE,
      3_400_424_181L,
      GodotCallShape.STRINGNAME_DOUBLE_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.NODE_GET_CHILD,
      541_253_412L,
      GodotCallShape.LONG_RET_HANDLE,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.NODE3D_GET_GLOBAL_POSITION,
      3_360_562_783L,
      GodotCallShape.NOARGS_RET_VECTOR3,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.TIMER_IS_STOPPED,
      36_873_697L,
      GodotCallShape.NOARGS_RET_BOOL,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.VISUALINSTANCE3D_SET_LAYER_MASK,
      1_286_410_249L,
      GodotCallShape.LONG_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    // Tween fluent/tweener returns are retained RefCounted values.
    listOf(
        InitialGodotCallDescriptors.TWEEN_BIND_NODE to GodotCallShape.OBJECT_RET_HANDLE,
        InitialGodotCallDescriptors.TWEEN_SET_EASE to GodotCallShape.LONG_RET_HANDLE,
        InitialGodotCallDescriptors.TWEEN_TWEEN_PROPERTY_VECTOR3 to
          GodotCallShape.OBJECT_NODEPATH_VECTOR3_DOUBLE_RET_HANDLE,
        InitialGodotCallDescriptors.TWEEN_TWEEN_CALLBACK to GodotCallShape.CALLABLE_RET_HANDLE,
      )
      .forEach { (descriptor, shape) ->
        assertEquals(shape, descriptor.shape)
        assertEquals(GodotExecutionMode.IMMEDIATE_RESULT, descriptor.executionMode)
        assertEquals(GodotReturnOwnership.RETAINED_REFCOUNTED, descriptor.returnOwnership)
      }
  }

  @Test
  fun node3dTransformFamilyRoundTripsThroughTheFacade() {
    val backend = RecordingBackend()
    GodotBackendCalls.install(backend)
    val handle = GodotHandle.fromBackendToken(17)
    val d = InitialGodotCallDescriptors

    assertEquals(
      GodotVector3(0.0f, 0.0f, 0.0f),
      GodotBackendCalls.invokeNoArgsRetVector3(d.NODE3D_GET_POSITION, handle),
    )
    GodotBackendCalls.invokeVector3Arg(
      d.NODE3D_SET_POSITION,
      handle,
      GodotVector3(1.0f, 2.0f, 3.0f),
    )
    assertEquals(
      GodotVector3(1.0f, 2.0f, 3.0f),
      GodotBackendCalls.invokeNoArgsRetVector3(d.NODE3D_GET_POSITION, handle),
    )
    assertEquals(
      GodotVector3(0.0f, 0.0f, 0.0f),
      GodotBackendCalls.invokeNoArgsRetVector3(d.NODE3D_GET_ROTATION, handle),
    )
    GodotBackendCalls.invokeVector3Arg(
      d.NODE3D_SET_ROTATION,
      handle,
      GodotVector3(0.1f, 0.2f, 0.3f),
    )
    assertEquals(
      GodotVector3(0.1f, 0.2f, 0.3f),
      GodotBackendCalls.invokeNoArgsRetVector3(d.NODE3D_GET_ROTATION, handle),
    )
    assertEquals(
      GodotVector3(1.0f, 1.0f, 1.0f),
      GodotBackendCalls.invokeNoArgsRetVector3(d.NODE3D_GET_SCALE, handle),
    )
    GodotBackendCalls.invokeVector3Arg(d.NODE3D_SET_SCALE, handle, GodotVector3(2.0f, 2.0f, 2.0f))
    assertEquals(
      GodotVector3(2.0f, 2.0f, 2.0f),
      GodotBackendCalls.invokeNoArgsRetVector3(d.NODE3D_GET_SCALE, handle),
    )
  }

  @Test
  fun platformerFamiliesUseTypedCalls() {
    val backend = RecordingBackend()
    GodotBackendCalls.install(backend)
    val handle = GodotHandle.fromBackendToken(17)
    val d = InitialGodotCallDescriptors

    GodotBackendCalls.invokeBoolArg(d.CANVASLAYER_SET_VISIBLE, handle, true)
    assertEquals(true, backend.canvasLayerVisible)
    assertEquals(
      true,
      GodotBackendCalls.invokeStringNameRetBoolSingleton(d.OS_HAS_FEATURE, "android"),
    )
    assertEquals(false, GodotBackendCalls.invokeStringNameRetBoolSingleton(d.OS_HAS_FEATURE, "ios"))
    val environment =
      GodotBackendCalls.invokeNoArgsRetHandle(d.WORLDENVIRONMENT_GET_ENVIRONMENT, handle)
    assertEquals(52L, environment?.backendToken())
    GodotBackendCalls.invokeDoubleArg(
      d.ENVIRONMENT_SET_BG_ENERGY_MULTIPLIER,
      checkNotNull(environment),
      0.25,
    )
    assertEquals(82 to 0.25, backend.doubleArguments.last())
    GodotBackendCalls.invokeLongDoubleArg(d.LIGHT3D_SET_PARAM, handle, 0L, 0.24)
    GodotBackendCalls.invokeLongDoubleArg(d.LIGHT3D_SET_PARAM, handle, 17L, 0.85)
    assertEquals(listOf(Triple(84, 0L, 0.24), Triple(84, 17L, 0.85)), backend.longDoubleArgs)
    assertEquals(
      "gl_compatibility",
      GodotBackendCalls.invokeNoArgsRetStringSingleton(
        d.RENDERINGSERVER_GET_CURRENT_RENDERING_METHOD
      ),
    )
    GodotBackendCalls.invokeStringNameArgSingleton(d.INPUT_ACTION_PRESS, "jump")
    GodotBackendCalls.invokeStringNameArgSingleton(d.INPUT_ACTION_RELEASE, "jump")
    assertEquals(listOf(86 to "jump", 87 to "jump"), backend.singletonStringArgs)
  }

  @Test
  fun facadeUsesTypedCallsAndCachesResolvedCallSites() {
    val backend = RecordingBackend()
    GodotBackendCalls.install(backend)
    val handle = GodotHandle.fromBackendToken(17)
    val d = InitialGodotCallDescriptors

    assertEquals(
      GodotVector2(1.0f, 2.0f),
      GodotBackendCalls.invokeNoArgsRetVector2(d.NODE2D_GET_POSITION, handle),
    )
    assertEquals(
      GodotVector2(1.0f, 2.0f),
      GodotBackendCalls.invokeNoArgsRetVector2(d.NODE2D_GET_POSITION, handle),
    )
    GodotBackendCalls.invokeVector2Arg(d.NODE2D_SET_POSITION, handle, GodotVector2(3.0f, 4.0f))
    assertEquals(
      GodotVector2(3.0f, 4.0f),
      GodotBackendCalls.invokeNoArgsRetVector2(d.NODE2D_GET_POSITION, handle),
    )
    assertEquals(
      GodotVector2(1.0f, 1.0f),
      GodotBackendCalls.invokeNoArgsRetVector2(d.NODE2D_GET_SCALE, handle),
    )
    GodotBackendCalls.invokeVector2Arg(d.NODE2D_SET_SCALE, handle, GodotVector2(1.25f, 0.75f))
    assertEquals(
      GodotVector2(1.25f, 0.75f),
      GodotBackendCalls.invokeNoArgsRetVector2(d.NODE2D_GET_SCALE, handle),
    )
    assertEquals(
      GodotColor(1.0f, 1.0f, 1.0f, 1.0f),
      GodotBackendCalls.invokeNoArgsRetColor(d.CANVASITEM_GET_MODULATE, handle),
    )
    GodotBackendCalls.invokeColorArg(
      d.CANVASITEM_SET_MODULATE,
      handle,
      GodotColor(0.8f, 0.7f, 0.6f, 0.5f),
    )
    assertEquals(
      GodotColor(0.8f, 0.7f, 0.6f, 0.5f),
      GodotBackendCalls.invokeNoArgsRetColor(d.CANVASITEM_GET_MODULATE, handle),
    )
    assertEquals(
      GodotRect2(GodotVector2(0.0f, 0.0f), GodotVector2(640.0f, 480.0f)),
      GodotBackendCalls.invokeNoArgsRetRect2(d.CANVASITEM_GET_VIEWPORT_RECT, handle),
    )
    GodotBackendCalls.invokeNoArgsVoid(d.CANVASITEM_QUEUE_REDRAW, handle)
    GodotBackendCalls.invokeTexture2DVector2ColorArgs(
      d.CANVASITEM_DRAW_TEXTURE,
      handle,
      GodotHandle.fromBackendToken(23),
      GodotVector2(12.0f, 34.0f),
      GodotColor(1.0f, 0.5f, 0.25f),
    )
    val texture =
      GodotBackendCalls.invokeStringStringLongRetHandle(
        d.RESOURCELOADER_LOAD,
        "res://bunny.svg",
        "Texture2D",
        1L,
      )
    assertEquals(31L, texture?.backendToken())
    val sprite = GodotBackendCalls.invokeStringNameRetHandle(d.CLASSDB_INSTANTIATE, "Sprite2D")
    assertEquals(41L, sprite?.backendToken())
    GodotBackendCalls.invokeObjectBoolLongArgs(
      d.NODE_ADD_CHILD,
      handle,
      checkNotNull(sprite),
      false,
      0L,
    )
    val sceneTree = checkNotNull(GodotBackendCalls.invokeNoArgsRetHandle(d.NODE_GET_TREE, handle))
    assertEquals(71L, sceneTree.backendToken())
    GodotBackendCalls.invokeLongArg(d.SCENETREE_QUIT, sceneTree, 7L)
    GodotBackendCalls.invokeObjectArg(d.SPRITE2D_SET_TEXTURE, sprite, texture)
    assertEquals(
      31L,
      GodotBackendCalls.invokeNoArgsRetHandle(d.SPRITE2D_GET_TEXTURE, sprite)?.backendToken(),
    )
    GodotBackendCalls.invokeObjectArg(d.NODE_REMOVE_CHILD, handle, sprite)
    GodotBackendCalls.invokeNoArgsVoid(d.NODE_QUEUE_FREE, sprite)
    val board = GodotBackendCalls.invokeNodePathRetHandle(d.NODE_GET_NODE_OR_NULL, handle, "Board")
    assertEquals(51L, board?.backendToken())
    val viewport = GodotBackendCalls.invokeNoArgsRetHandle(d.NODE_GET_VIEWPORT, handle)
    assertEquals(52L, viewport?.backendToken())
    assertEquals(
      GodotRect2(GodotVector2(0.0f, 0.0f), GodotVector2(640.0f, 480.0f)),
      GodotBackendCalls.invokeNoArgsRetRect2(d.VIEWPORT_GET_VISIBLE_RECT, checkNotNull(viewport)),
    )
    val tile =
      GodotBackendCalls.invokeLongRetHandle(d.PACKEDSCENE_INSTANTIATE, checkNotNull(texture), 0L)
    assertEquals(53L, tile?.backendToken())
    GodotBackendCalls.invokeObjectLongVector2Args(
      d.INPUT_SET_CUSTOM_MOUSE_CURSOR,
      texture,
      0L,
      GodotVector2(0.0f, 0.0f),
    )
    assertEquals(
      0L,
      GodotBackendCalls.invokeStringNameCallableLongRetLong(
        d.OBJECT_CONNECT,
        checkNotNull(tile),
        "tile_pressed",
        handle,
        "_on_tile_pressed",
        0L,
      ),
    )
    assertEquals(
      true,
      GodotBackendCalls.invokeStringNameRetBool(d.OBJECT_IS_CLASS, tile, "InputEventMouseButton"),
    )
    assertEquals(true, GodotBackendCalls.invokeNoArgsRetBool(d.INPUTEVENT_IS_PRESSED, tile))
    assertEquals(false, GodotBackendCalls.invokeNoArgsRetBool(d.INPUTEVENT_IS_RELEASED, tile))
    assertEquals(
      1L,
      GodotBackendCalls.invokeNoArgsRetLong(d.INPUTEVENTMOUSEBUTTON_GET_BUTTON_INDEX, tile),
    )
    assertEquals(
      GodotVector2(320.0f, 240.0f),
      GodotBackendCalls.invokeNoArgsRetVector2(
        d.CANVASITEM_GET_LOCAL_MOUSE_POSITION,
        checkNotNull(board),
      ),
    )
    assertEquals(
      0,
      GodotBackendCalls.invokeStringNameVector2iRetInt(
        d.OBJECT_EMIT_SIGNAL_VECTOR2I,
        tile,
        "tile_pressed",
        GodotVector2i(3, 4),
      ),
    )
    assertEquals(
      0L,
      GodotBackendCalls.invokeStringNameBoundCallableLongRetLong(
        d.OBJECT_CONNECT_BOUND_LONG,
        tile,
        "finished",
        tile,
        "_kanama_web_signal_dispatch0",
        71L,
        4L,
      ),
    )
    assertEquals(
      0,
      GodotBackendCalls.invokeStringNameRetInt(d.OBJECT_EMIT_SIGNAL_NOARGS, tile, "finished"),
    )
    val tween = checkNotNull(GodotBackendCalls.invokeNoArgsRetHandle(d.NODE_CREATE_TWEEN, handle))
    assertEquals(61L, tween.backendToken())
    assertEquals(
      61L,
      GodotBackendCalls.invokeBoolRetHandle(d.TWEEN_SET_PARALLEL, tween, true)?.backendToken(),
    )
    val vectorTweener =
      checkNotNull(
        GodotBackendCalls.invokeObjectNodePathVector2DoubleRetHandle(
          d.TWEEN_TWEEN_PROPERTY_VECTOR2,
          tween,
          handle,
          "position",
          GodotVector2(12.0f, 34.0f),
          0.3,
        )
      )
    assertEquals(62L, vectorTweener.backendToken())
    assertEquals(
      63L,
      GodotBackendCalls.invokeObjectNodePathColorDoubleRetHandle(
          d.TWEEN_TWEEN_PROPERTY_COLOR,
          tween,
          handle,
          "modulate",
          GodotColor(0.5f, 0.6f, 0.7f, 0.8f),
          0.1,
        )
        ?.backendToken(),
    )
    assertEquals(
      62L,
      GodotBackendCalls.invokeLongRetHandle(d.PROPERTYTWEENER_SET_TRANS, vectorTweener, 10L)
        ?.backendToken(),
    )
    assertEquals(
      62L,
      GodotBackendCalls.invokeLongRetHandle(d.PROPERTYTWEENER_SET_EASE, vectorTweener, 1L)
        ?.backendToken(),
    )
    GodotBackendCalls.invokeNoArgsVoid(d.TWEEN_KILL, tween)
    assertEquals(false, GodotBackendCalls.invokeNoArgsRetBool(d.GPUPARTICLES2D_IS_EMITTING, tile))
    GodotBackendCalls.invokeBoolArg(d.GPUPARTICLES2D_SET_EMITTING, tile, true)
    assertEquals(true, GodotBackendCalls.invokeNoArgsRetBool(d.GPUPARTICLES2D_IS_EMITTING, tile))
    assertEquals(true, GodotBackendCalls.invokeNoArgsRetBool(d.GPUPARTICLES2D_IS_EMITTING, tile))
    assertEquals(1.0, GodotBackendCalls.invokeNoArgsRetDouble(d.GPUPARTICLES2D_GET_LIFETIME, tile))
    assertEquals(1.0, GodotBackendCalls.invokeNoArgsRetDouble(d.GPUPARTICLES2D_GET_LIFETIME, tile))
    val audioStream =
      GodotBackendCalls.invokeStringStringLongRetHandle(
        d.RESOURCELOADER_LOAD,
        "res://sounds/tile-swap.ogg",
        "AudioStream",
        1L,
      )
    assertEquals(31L, audioStream?.backendToken())
    val audio =
      checkNotNull(
        GodotBackendCalls.invokeStringNameRetHandle(d.CLASSDB_INSTANTIATE, "AudioStreamPlayer")
      )
    GodotBackendCalls.invokeObjectArg(d.AUDIOSTREAMPLAYER_SET_STREAM, audio, audioStream)
    GodotBackendCalls.invokeObjectArg(d.AUDIOSTREAMPLAYER_SET_STREAM, audio, null)
    GodotBackendCalls.invokeStringNameArg(d.AUDIOSTREAMPLAYER_SET_BUS, audio, "master")
    GodotBackendCalls.invokeDoubleArg(d.AUDIOSTREAMPLAYER_SET_VOLUME_DB, audio, -10.0)
    GodotBackendCalls.invokeDoubleArg(d.AUDIOSTREAMPLAYER_SET_PITCH_SCALE, audio, 1.2)
    GodotBackendCalls.invokeDoubleArg(d.AUDIOSTREAMPLAYER_PLAY, audio, 0.0)
    assertFailsWith<IllegalArgumentException> {
      GodotBackendCalls.invokeDoubleArg(d.AUDIOSTREAMPLAYER_SET_PITCH_SCALE, audio, Double.NaN)
    }
    GodotBackendCalls.invokeUtilityNoArgsVoid(d.UTILITY_RANDOMIZE)
    assertEquals(4_294_967_295L, GodotBackendCalls.invokeUtilityNoArgsRetLong(d.UTILITY_RANDI))
    assertEquals(0.75, GodotBackendCalls.invokeUtilityNoArgsRetDouble(d.UTILITY_RANDF))
    assertEquals(
      0,
      GodotBackendCalls.invokeStringNameIntRetInt(
        d.OBJECT_EMIT_SIGNAL,
        handle,
        "benchmark_finished",
        42,
      ),
    )
    assertEquals(7, GodotBackendCalls.invokeBoolRetInt(d.NODE_GET_CHILD_COUNT, handle, false))

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
        23 to 1,
        24 to 1,
        25 to 1,
        26 to 1,
        27 to 1,
        28 to 1,
        29 to 1,
        30 to 1,
        31 to 1,
        32 to 1,
        33 to 1,
        34 to 1,
        35 to 1,
        36 to 1,
        37 to 1,
        38 to 1,
        39 to 1,
        40 to 1,
        41 to 1,
        42 to 1,
        43 to 1,
        44 to 1,
        45 to 1,
        46 to 1,
        47 to 1,
        48 to 1,
        49 to 1,
        50 to 1,
        51 to 1,
        52 to 1,
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
    assertEquals("tile_pressed" to GodotVector2i(3, 4), backend.emittedVectorSignal)
    assertEquals(listOf("Sprite2D", "AudioStreamPlayer"), backend.constructedClasses)
    assertEquals(
      listOf(
        Triple("res://bunny.svg", "Texture2D", 1L),
        Triple("res://sounds/tile-swap.ogg", "AudioStream", 1L),
      ),
      backend.resourceLoads,
    )
    assertEquals(Triple(17L, 41L, false), backend.addedChild)
    assertEquals(17L to 41L, backend.removedChild)
    assertEquals(listOf(41L to 31L, 41L to 31L, 41L to null), backend.objectArguments)
    assertEquals("master", backend.stringNameArgument)
    assertEquals(listOf(48 to -10.0, 49 to 1.2, 50 to 0.0), backend.doubleArguments)
    assertEquals(71L to 7L, backend.longArgument)
    assertEquals(41L, backend.queuedFree)
  }

  private data class DrawCall(
    val textureToken: Long,
    val position: GodotVector2,
    val modulate: GodotColor,
  )

  private fun assertDescriptor(
    descriptor: GodotCallDescriptor,
    hash: Long,
    shape: GodotCallShape,
    executionMode: GodotExecutionMode,
  ) {
    assertEquals(hash, descriptor.hash)
    assertEquals(shape, descriptor.shape)
    assertEquals(executionMode, descriptor.executionMode)
    assertEquals(GodotReturnOwnership.BORROWED, descriptor.returnOwnership)
  }

  private class RecordingBackend : GodotBackendSpi {
    val resolveCounts = mutableMapOf<Int, Int>()
    private var position = GodotVector2(1.0f, 2.0f)
    private var scale = GodotVector2(1.0f, 1.0f)
    private var modulate = GodotColor(1.0f, 1.0f, 1.0f, 1.0f)
    var queuedRedraws = 0
    var drawCall: DrawCall? = null
    var randomizeCalls = 0
    var emittedSignal: Pair<String, Int>? = null
    var emittedVectorSignal: Pair<String, GodotVector2i>? = null
    val constructedClasses = mutableListOf<String>()
    val resourceLoads = mutableListOf<Triple<String, String, Long>>()
    var addedChild: Triple<Long, Long, Boolean>? = null
    var removedChild: Pair<Long, Long>? = null
    val objectArguments = mutableListOf<Pair<Long, Long?>>()
    var stringNameArgument: String? = null
    val doubleArguments = mutableListOf<Pair<Int, Double>>()
    var longArgument: Pair<Long, Long>? = null
    var queuedFree: Long? = null
    private var particlesEmitting = false
    private var position3 = GodotVector3(0.0f, 0.0f, 0.0f)
    private var rotation3 = GodotVector3(0.0f, 0.0f, 0.0f)
    private var scale3 = GodotVector3(1.0f, 1.0f, 1.0f)

    override fun requireLive(handle: GodotHandle) {
      require(handle.backendToken() in setOf(17L, 31L, 41L, 51L, 52L, 53L, 61L, 62L, 63L, 71L))
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

    override fun invokeBoolRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: Boolean,
    ): GodotHandle? {
      assertEquals(61L, receiver.backendToken())
      assertEquals(true, value)
      return receiver
    }

    var canvasLayerVisible: Boolean? = null

    override fun invokeBoolArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: Boolean,
    ) {
      require(descriptor.opcode in setOf(43, 80))
      if (descriptor.opcode == 80) canvasLayerVisible = value else particlesEmitting = value
    }

    override fun invokeStringNameRetBoolSingleton(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      value: String,
    ): Boolean {
      assertEquals(83, descriptor.opcode)
      return value == "android"
    }

    val longDoubleArgs = mutableListOf<Triple<Int, Long, Double>>()

    override fun invokeLongDoubleArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      longValue: Long,
      doubleValue: Double,
    ) {
      longDoubleArgs += Triple(descriptor.opcode, longValue, doubleValue)
    }

    override fun invokeNoArgsRetStringSingleton(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
    ): String {
      assertEquals(85, descriptor.opcode)
      return "gl_compatibility"
    }

    val singletonStringArgs = mutableListOf<Pair<Int, String>>()

    override fun invokeStringNameArgSingleton(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      value: String,
    ) {
      singletonStringArgs += descriptor.opcode to value
    }

    override fun invokeDoubleArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: Double,
    ) {
      doubleArguments += descriptor.opcode to value
    }

    override fun invokeNoArgsRetVector2(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): GodotVector2 =
      when (descriptor.opcode) {
        27 -> GodotVector2(320.0f, 240.0f)
        29 -> scale
        else -> position
      }

    override fun invokeVector2Arg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: GodotVector2,
    ) {
      when (descriptor.opcode) {
        30 -> scale = value
        else -> position = value
      }
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
      when (descriptor.opcode) {
        5 -> queuedRedraws += 1
        15 -> queuedFree = receiver.backendToken()
        37 -> assertEquals(61L, receiver.backendToken())
      }
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
      assertEquals(1L, value)
      resourceLoads += Triple(first, second, value)
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
      constructedClasses += value
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
      else objectArguments += call
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
      return when (descriptor.opcode) {
        18 -> {
          assertEquals(0L, value)
          GodotHandle.fromBackendToken(53L)
        }
        41 -> {
          assertEquals(10L, value)
          receiver
        }
        42 -> {
          assertEquals(1L, value)
          receiver
        }
        else -> error("Unexpected long-return-handle opcode=${descriptor.opcode}")
      }
    }

    override fun invokeLongArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: Long,
    ) {
      assertEquals(52, descriptor.opcode)
      longArgument = receiver.backendToken() to value
    }

    override fun invokeNoArgsRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): GodotHandle? =
      GodotHandle.fromBackendToken(
        when (descriptor.opcode) {
          33 -> 31L
          36 -> 61L
          51 -> 71L
          else -> 52L
        }
      )

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

    override fun invokeStringNameBoundCallableLongRetLong(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      signal: String,
      target: GodotHandle,
      method: String,
      boundValue: Long,
      flags: Long,
    ): Long {
      assertEquals(53L, receiver.backendToken())
      assertEquals("finished", signal)
      assertEquals(53L, target.backendToken())
      assertEquals("_kanama_web_signal_dispatch0", method)
      assertEquals(71L, boundValue)
      assertEquals(4L, flags)
      return 0L
    }

    override fun invokeStringNameRetInt(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: String,
    ): Int {
      assertEquals(53L, receiver.backendToken())
      assertEquals("finished", value)
      return 0
    }

    override fun invokeStringNameRetBool(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: String,
    ): Boolean = value == "InputEventMouseButton"

    override fun invokeNoArgsRetBool(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): Boolean = if (descriptor.opcode == 44) particlesEmitting else descriptor.opcode == 24

    override fun invokeNoArgsRetDouble(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): Double {
      assertEquals(45, descriptor.opcode)
      return 1.0
    }

    override fun invokeNoArgsRetLong(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): Long = 1L

    override fun invokeStringNameArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: String,
    ) {
      assertEquals(47, descriptor.opcode)
      stringNameArgument = value
    }

    override fun invokeStringNameVector2iRetInt(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      name: String,
      value: GodotVector2i,
    ): Int {
      emittedVectorSignal = name to value
      return 0
    }

    override fun invokeNoArgsRetColor(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): GodotColor = modulate

    override fun invokeColorArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: GodotColor,
    ) {
      modulate = value
    }

    override fun invokeObjectNodePathVector2DoubleRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      target: GodotHandle,
      property: String,
      finalValue: GodotVector2,
      duration: Double,
    ): GodotHandle? {
      assertEquals(61L, receiver.backendToken())
      assertEquals(17L, target.backendToken())
      assertEquals("position", property)
      assertEquals(GodotVector2(12.0f, 34.0f), finalValue)
      assertEquals(0.3, duration)
      return GodotHandle.fromBackendToken(62L)
    }

    override fun invokeObjectNodePathColorDoubleRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      target: GodotHandle,
      property: String,
      finalValue: GodotColor,
      duration: Double,
    ): GodotHandle? {
      assertEquals(61L, receiver.backendToken())
      assertEquals(17L, target.backendToken())
      assertEquals("modulate", property)
      assertEquals(GodotColor(0.5f, 0.6f, 0.7f, 0.8f), finalValue)
      assertEquals(0.1, duration)
      return GodotHandle.fromBackendToken(63L)
    }

    override fun invokeNoArgsRetVector3(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): GodotVector3 =
      when (descriptor.opcode) {
        75 -> position3
        77 -> rotation3
        79 -> scale3
        else -> error("Unexpected Vector3 read opcode=${descriptor.opcode}")
      }

    override fun invokeVector3Arg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: GodotVector3,
    ) {
      when (descriptor.opcode) {
        74 -> position3 = value
        76 -> rotation3 = value
        78 -> scale3 = value
        else -> error("Unexpected Vector3 write opcode=${descriptor.opcode}")
      }
    }

    // Every SPI member is abstract (task 95); the shapes below are not exercised by this test.
    private fun unexercised(descriptor: GodotCallDescriptor): Nothing =
      error("RecordingBackend does not exercise ${descriptor.className}.${descriptor.methodName}")

    override fun invokeNoArgsRetStringArray(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): List<String> = unexercised(descriptor)

    override fun invokeStringNameBoolArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      name: String,
      value: Boolean,
    ) = unexercised(descriptor)

    override fun invokeLongBoolArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      layer: Long,
      value: Boolean,
    ) = unexercised(descriptor)

    override fun invokeStringNameStringNameArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      first: String,
      second: String,
    ) = unexercised(descriptor)

    override fun invokeStringNameDoubleArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: String,
      doubleValue: Double,
    ) = unexercised(descriptor)

    override fun invokeStringNameStringNameRetDoubleSingleton(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      first: String,
      second: String,
    ): Double = unexercised(descriptor)

    override fun invokeVector3Vector3Arg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      first: GodotVector3,
      second: GodotVector3,
    ) = unexercised(descriptor)

    override fun invokeLongArgSingleton(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      value: Long,
    ) = unexercised(descriptor)

    override fun invokeObjectRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: GodotHandle,
    ): GodotHandle? = unexercised(descriptor)

    override fun invokeObjectNodePathVector3DoubleRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      target: GodotHandle,
      property: String,
      finalValue: GodotVector3,
      duration: Double,
    ): GodotHandle? = unexercised(descriptor)

    override fun invokeObjectNodePathDoubleDoubleRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      target: GodotHandle,
      property: String,
      finalValue: Double,
      duration: Double,
    ): GodotHandle? = unexercised(descriptor)

    override fun invokeColorRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: GodotColor,
    ): GodotHandle? = unexercised(descriptor)

    override fun invokeCallableRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      target: GodotHandle,
      method: String,
    ): GodotHandle? = unexercised(descriptor)

    override fun invokeNoArgsRetLongSingleton(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
    ): Long = unexercised(descriptor)

    override fun invokeNoArgsRetLongListSingleton(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
    ): List<Long> = unexercised(descriptor)

    override fun invokeLongRetBoolSingleton(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      value: Long,
    ): Boolean = unexercised(descriptor)

    override fun invokeNoArgsRetVector2Singleton(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
    ): GodotVector2 = unexercised(descriptor)

    override fun invokeStringNameRetHandleList(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: String,
    ): List<GodotHandle> = unexercised(descriptor)

    override fun invokeStringNameObjectRetInt(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      name: String,
      value: GodotHandle,
    ): Int = unexercised(descriptor)

    override fun invokeVector3RetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: GodotVector3,
    ): GodotHandle? = unexercised(descriptor)

    override fun invokeNoArgsRetHandleList(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): List<GodotHandle> = unexercised(descriptor)

    override fun invokeLongRetVector3(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: Long,
    ): GodotVector3 = unexercised(descriptor)

    override fun invokeStringNameRetDoubleSingleton(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      value: String,
    ): Double = unexercised(descriptor)

    override fun invokeStringNameVector3Vector3Arg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      name: String,
      first: GodotVector3,
      second: GodotVector3,
    ) = unexercised(descriptor)

    override fun invokeCallableDoubleRangeRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      target: GodotHandle,
      method: String,
      fromValue: Double,
      toValue: Double,
      duration: Double,
    ): GodotHandle? = unexercised(descriptor)

    override fun invokeStringNameStringRetInt(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      name: String,
      value: String,
    ): Int = unexercised(descriptor)

    override fun invokeVector3iLongLongArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: GodotVector3i,
      first: Long,
      second: Long,
    ) = unexercised(descriptor)

    override fun invokeVector3iRetLong(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: GodotVector3i,
    ): Long = unexercised(descriptor)

    override fun invokeBasisRetLong(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: GodotBasis,
    ): Long = unexercised(descriptor)

    override fun invokeNoArgsRetVector3iList(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): List<GodotVector3i> = unexercised(descriptor)

    override fun invokeLongObjectArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      longValue: Long,
      objectValue: GodotHandle,
    ) = unexercised(descriptor)

    override fun invokeLongTransform3dArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      longValue: Long,
      value: GodotTransform3D,
    ) = unexercised(descriptor)

    override fun invokeLongRetString(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: Long,
    ): String = unexercised(descriptor)

    override fun invokeLongRetLong(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: Long,
    ): Long = unexercised(descriptor)

    override fun invokeLongLongRetString(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      first: Long,
      second: Long,
    ): String = unexercised(descriptor)

    override fun invokeLongLongRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      first: Long,
      second: Long,
    ): GodotHandle? = unexercised(descriptor)

    override fun invokeVector2RetVector3(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: GodotVector2,
    ): GodotVector3 = unexercised(descriptor)

    override fun invokeObjectStringRetLongSingleton(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      resource: GodotHandle,
      path: String,
      flags: Long,
    ): Long = unexercised(descriptor)

    override fun invokeStringStringBoolBoolRetHandleList(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      pattern: String,
      type: String,
      recursive: Boolean,
      owned: Boolean,
    ): List<GodotHandle> = unexercised(descriptor)

    override fun invokeStringNameLongArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      name: String,
      value: Long,
    ) = unexercised(descriptor)

    override fun invokeStringNameVector2Arg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      name: String,
      value: GodotVector2,
    ) = unexercised(descriptor)

    override fun invokeStringNameObjectArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      name: String,
      value: GodotHandle,
    ) = unexercised(descriptor)

    override fun invokeStringNameObjectArgSingleton(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      name: String,
      value: GodotHandle,
    ) = unexercised(descriptor)

    override fun invokeStringNameRetVector2(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      name: String,
    ): GodotVector2 = unexercised(descriptor)

    override fun invokeNoArgsRetString(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): String = unexercised(descriptor)

    override fun invokeStringNameRetString(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      name: String,
    ): String = unexercised(descriptor)

    override fun invokeDoubleRetDouble(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: Double,
    ): Double = unexercised(descriptor)

    override fun invokeVector3Vector3LongObjectRetString(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      from: GodotVector3,
      to: GodotVector3,
      collisionMask: Long,
      exclude: GodotHandle?,
    ): String = unexercised(descriptor)
  }
}
