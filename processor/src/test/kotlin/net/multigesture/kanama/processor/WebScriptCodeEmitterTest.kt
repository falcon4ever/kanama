package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WebScriptCodeEmitterTest {

  private fun model(simpleName: String, greeting: String = "greeting") =
    ScriptModel(
      simpleName = simpleName,
      fqName = "net.multigesture.kanama.web.$simpleName",
      attachTo = "Node2D",
      isTool = false,
      isGlobalClass = false,
      properties =
        listOf(
          ScriptPropertyModel(
            kotlinName = greeting,
            godotName = greeting,
            type = TypeMapping.STRING,
            isMutable = true,
          )
        ),
      toolButtons = emptyList(),
      virtuals =
        listOf(
          VirtualModel("_enter_tree", "callEnterTree", "enterTree"),
          VirtualModel("_ready", "callReady", "ready"),
          VirtualModel(
            "_process",
            "callProcess",
            "process",
            args = listOf(ArgModel("delta", TypeMapping.FLOAT)),
          ),
          VirtualModel("_exit_tree", "callExitTree", "exitTree"),
        ),
      methods =
        listOf(
          MethodModel(
            kotlinName = "echo",
            godotName = "echo",
            returnType = TypeMapping.INT,
            args = listOf(ArgModel("value", TypeMapping.INT)),
            kind = MethodKind.REGULAR,
          ),
          MethodModel(
            kotlinName = "reset",
            godotName = "reset",
            returnType = null,
            args = emptyList(),
            kind = MethodKind.REGULAR,
          ),
        ),
      signals = listOf(SignalModel("changed", listOf(ArgModel("value", TypeMapping.INT)))),
    )

  @Test
  fun emitsDeterministicNumericRegistryAndTypedDispatch() {
    val source =
      WebScriptCodeEmitter(
          listOf(
            WebScriptInput(model("SecondScript"), "res://z/SecondScript.kt"),
            WebScriptInput(model("FirstScript"), "res://a/FirstScript.kt"),
          )
        )
        .registrySource()

    val firstDescriptor = source.indexOf("className = \"net.multigesture.kanama.web.FirstScript\"")
    val secondDescriptor =
      source.indexOf("className = \"net.multigesture.kanama.web.SecondScript\"")
    assertTrue(firstDescriptor >= 0)
    assertTrue(secondDescriptor > firstDescriptor, "resource paths must define stable script IDs")

    assertTrue(source.contains("const val PROTOCOL_VERSION: Int = 16"))
    assertTrue(source.contains("1 -> FirstScript(WebObjectId(objectId))"))
    assertTrue(source.contains("2 -> SecondScript(WebObjectId(objectId))"))
    assertTrue(source.contains("WebMemberDescriptor(1, \"greeting\")"))
    assertTrue(source.contains("(script as FirstScript).process(delta)"))
    assertTrue(source.contains("fun draw(scriptId: Int, script: KanamaWebScript)"))
    assertTrue(source.contains("fun enterTree(scriptId: Int, script: KanamaWebScript)"))
    assertTrue(source.contains("(script as FirstScript).enterTree()"))
    assertTrue(source.contains("fun exitTree(scriptId: Int, script: KanamaWebScript)"))
    assertTrue(source.contains("(script as FirstScript).exitTree()"))
    assertTrue(source.contains("(script as FirstScript).echo(value)"))
    assertTrue(source.contains("(script as FirstScript).reset()"))

    assertFalse(source.contains("java.lang.foreign"))
    assertFalse(source.contains("MemorySegment"))
    assertFalse(source.contains("Class.forName"))
    assertFalse(source.contains("Map<String, Any"))
  }

  @Test
  fun outputIsIndependentOfInputOrder() {
    val first = WebScriptInput(model("FirstScript"), "res://a/FirstScript.kt")
    val second = WebScriptInput(model("SecondScript"), "res://z/SecondScript.kt")

    assertEquals(
      WebScriptCodeEmitter(listOf(first, second)).registrySource(),
      WebScriptCodeEmitter(listOf(second, first)).registrySource(),
    )
  }

  @Test
  fun emitsProxyWithLifecycleBatchAndImmediatePaths() {
    val emitter =
      WebScriptCodeEmitter(listOf(WebScriptInput(model("FirstScript"), "res://FirstScript.kt")))
    val proxy = emitter.proxySources().single { it.sourceResourcePath.isNotEmpty() }

    assertEquals("res://FirstScript.kt", proxy.sourceResourcePath)
    assertEquals("res://kanama-web/generated/FirstScript.gd", proxy.proxyResourcePath)
    assertTrue(proxy.source.contains("extends Node"))
    assertTrue(proxy.source.contains("@export var greeting: String"))
    assertTrue(proxy.source.contains("JavaScriptBridge.get_interface(\"KanamaWebBridge\")"))
    assertTrue(proxy.source.contains("refreshNode2DSnapshot(_kanama_handle,"))
    assertTrue(proxy.source.contains("opcode == 3"))
    assertTrue(proxy.source.contains("opcode == 30"))
    assertTrue(proxy.source.contains("opcode == 32"))
    assertTrue(proxy.source.contains("_kanama_immediate_call"))
    assertTrue(proxy.source.contains("_kanama_resource_load"))
    assertTrue(proxy.source.contains("installProxyCallbacks"))
    assertTrue(proxy.source.contains("func _kanama_ensure_created() -> int"))
    assertTrue(proxy.source.contains("setStringProperty(_kanama_handle, 1, greeting)"))
    assertTrue(proxy.source.contains("_kanama_signal_emit"))
    assertTrue(proxy.source.contains("_kanama_resource_release"))
    assertTrue(proxy.source.contains("_kanama_tween_callback"))
    assertTrue(proxy.source.contains("func _kanama_noargs_object(args: Array) -> int:"))
    assertTrue(proxy.source.contains("value = receiver.create_tween()"))
    assertTrue(proxy.source.contains("func _kanama_tween_call(args: Array) -> int:"))
    assertTrue(proxy.source.contains("(receiver as Tween).kill()"))
    assertTrue(proxy.source.contains("(receiver as Tween).set_parallel(bool(args[2]))"))
    assertTrue(proxy.source.contains("(receiver as Tween).tween_property("))
    assertTrue(proxy.source.contains("(receiver as PropertyTweener).set_trans(int(args[2]))"))
    assertTrue(proxy.source.contains("(receiver as PropertyTweener).set_ease(int(args[2]))"))
    assertTrue(proxy.source.contains("CONNECT_ONE_SHOT"))
    assertTrue(proxy.source.contains("var _kanama_tween_targets: Dictionary = {}"))
    assertTrue(proxy.source.contains("_kanama_tween_targets[receiver_handle] = targets"))
    assertTrue(proxy.source.contains("for target_handle in targets:"))
    assertTrue(proxy.source.contains("_kanama_bridge.refreshNode2DSnapshot(int(target_handle),"))
    assertTrue(proxy.source.contains("_kanama_bridge.releaseTweenGraph(tween_handle)"))
    assertTrue(proxy.source.contains("if _kanama_handle == 0:"))
    assertTrue(proxy.source.contains("_kanama_clear_callbacks()"))
    assertTrue(proxy.source.contains("recordImmediateChildCount(result)"))
    assertTrue(proxy.source.contains("func _enter_tree()"))
    assertTrue(proxy.source.contains("_kanama_bridge.enterTree(_kanama_handle)"))
    assertTrue(proxy.source.contains("func _process(delta: float)"))
    assertTrue(proxy.source.contains("func _draw()"))
    assertTrue(proxy.source.contains("_kanama_bridge.draw(_kanama_handle)"))
    assertTrue(proxy.source.contains("func _exit_tree()"))
    assertTrue(proxy.source.contains("func _kanama_apply_commands(args: Array)"))
    assertTrue(proxy.source.contains("js_buffer_to_packed_byte_array(args[0])"))
    assertTrue(proxy.source.contains("bytes.decode_s32(offset + 8)"))
    assertTrue(proxy.source.contains("opcode == 6"))
    assertTrue(proxy.source.contains("offset += 36"))
    assertTrue(proxy.source.contains("callInt(_kanama_handle, 1, 47)"))
    assertTrue(proxy.source.contains("func echo(value: int) -> int"))
    assertTrue(proxy.source.contains("func reset() -> void"))
    assertTrue(proxy.source.contains("callNoArgs(_kanama_handle, 2)"))

    assertTrue(
      emitter
        .proxyManifest()
        .contains("res://FirstScript.kt\tres://kanama-web/generated/FirstScript.gd\tFirstScript.gd")
    )
  }

  @Test
  fun emitsParticleSnapshotsAndQueuedEmittingMutation() {
    val particle = model("Particles").copy(attachTo = "GPUParticles2D")
    val proxy =
      WebScriptCodeEmitter(listOf(WebScriptInput(particle, "res://kotlin-src/Particles.kt")))
        .proxySources()
        .single { it.sourceResourcePath.isNotEmpty() }
        .source

    assertTrue(proxy.contains("extends GPUParticles2D"))
    assertTrue(proxy.contains("refreshParticlesSnapshot(_kanama_handle, emitting, lifetime)"))
    assertTrue(
      proxy.indexOf("refreshParticlesSnapshot(_kanama_handle, emitting, lifetime)") <
        proxy.indexOf("_kanama_bridge.ready(_kanama_handle)")
    )
    assertTrue(proxy.contains("opcode == 43 and target_object is GPUParticles2D"))
    assertTrue(
      proxy.contains(
        "(target_object as GPUParticles2D).emitting = bytes.decode_s32(offset + 8) != 0"
      )
    )

    val parentProxy =
      WebScriptCodeEmitter(listOf(WebScriptInput(model("Main"), "res://kotlin-src/Main.kt")))
        .proxySources()
        .single { it.sourceResourcePath.isNotEmpty() }
        .source
    assertTrue(parentProxy.contains("opcode == 43 and target_object is GPUParticles2D"))
  }

  @Test
  fun emitsExactAudioPlayerCommandDecodersInEveryProxy() {
    val proxy =
      WebScriptCodeEmitter(listOf(WebScriptInput(model("Main"), "res://kotlin-src/Main.kt")))
        .proxySources()
        .single { it.sourceResourcePath.isNotEmpty() }
        .source

    assertTrue(proxy.contains("opcode == 46 and target_object is AudioStreamPlayer"))
    assertTrue(proxy.contains("_kanama_object_handles.get(stream_handle) as AudioStream"))
    assertTrue(proxy.contains("(target_object as AudioStreamPlayer).set_stream(stream)"))
    assertTrue(proxy.contains("opcode == 47 and target_object is AudioStreamPlayer"))
    assertTrue(proxy.contains("resolveCommandStringName(bus_id)"))
    assertTrue(proxy.contains("set_bus(StringName(bus_name))"))
    assertTrue(proxy.contains("set_volume_db(bytes.decode_double(offset + 8))"))
    assertTrue(proxy.contains("set_pitch_scale(bytes.decode_double(offset + 8))"))
    assertTrue(proxy.contains("play(bytes.decode_double(offset + 8))"))
  }

  @Test
  fun emitsExactSceneTreeLifecycleCallsInEveryProxy() {
    val proxy =
      WebScriptCodeEmitter(listOf(WebScriptInput(model("Main"), "res://kotlin-src/Main.kt")))
        .proxySources()
        .single { it.sourceResourcePath.isNotEmpty() }
        .source

    assertTrue(proxy.contains("opcode == 51 and receiver != null"))
    assertTrue(proxy.contains("value = receiver.get_tree()"))
    assertTrue(proxy.contains("opcode == 52 and target_object is SceneTree"))
    assertTrue(proxy.contains("quit(bytes.decode_s32(offset + 8))"))
  }

  @Test
  fun emitsMatch3TypedContractAndExplicitGameplayBlockers() {
    val main =
      ScriptModel(
        simpleName = "Main",
        fqName = "net.multigesture.kanama.demos.match3.Main",
        attachTo = "Node2D",
        isTool = false,
        isGlobalClass = false,
        properties =
          listOf(
            ScriptPropertyModel(
              kotlinName = "width",
              godotName = "width",
              type = TypeMapping.INT,
              isMutable = true,
              exportSubgroup = ScriptPropertyGroupModel("Properties", "", usage = 6),
            ),
            ScriptPropertyModel(
              kotlinName = "tileScene",
              godotName = "tile_scene",
              type = TypeMapping.OBJECT,
              isMutable = true,
              objectWrapperFqName = "net.multigesture.kanama.api.PackedScene",
              nullable = true,
            ),
            ScriptPropertyModel(
              kotlinName = "textures",
              godotName = "textures",
              type = TypeMapping.ARRAY,
              isMutable = true,
              arrayElementWrapperFqName = "net.multigesture.kanama.api.Texture2D",
            ),
            ScriptPropertyModel(
              kotlinName = "openHandCursor",
              godotName = "open_hand_cursor",
              type = TypeMapping.OBJECT,
              isMutable = true,
              objectWrapperFqName = "net.multigesture.kanama.api.Texture2D",
              nullable = true,
            ),
          ),
        toolButtons = emptyList(),
        virtuals =
          listOf(
            VirtualModel(
              "_input",
              "callInput",
              "input",
              args =
                listOf(
                  ArgModel(
                    "event",
                    TypeMapping.OBJECT,
                    objectWrapperFqName = "net.multigesture.kanama.api.GodotObject",
                  )
                ),
            )
          ),
        methods =
          listOf(
            MethodModel(
              kotlinName = "onTilePressed",
              godotName = "_on_tile_pressed",
              returnType = null,
              args = listOf(ArgModel("gridPosition", TypeMapping.VECTOR2I)),
              kind = MethodKind.REGULAR,
            )
          ),
        signals = emptyList(),
      )
    val tile =
      ScriptModel(
        simpleName = "Tile",
        fqName = "net.multigesture.kanama.demos.match3.Tile",
        attachTo = "Area2D",
        isTool = false,
        isGlobalClass = false,
        properties = emptyList(),
        toolButtons = emptyList(),
        virtuals = emptyList(),
        methods =
          listOf(
            MethodModel(
              kotlinName = "setTileType",
              godotName = "set_tile_type",
              returnType = null,
              args =
                listOf(
                  ArgModel("id", TypeMapping.STRING),
                  ArgModel(
                    "texture",
                    TypeMapping.OBJECT,
                    objectWrapperFqName = "net.multigesture.kanama.api.Texture2D",
                  ),
                ),
              kind = MethodKind.REGULAR,
            ),
            MethodModel(
              kotlinName = "inputEvent",
              godotName = "_input_event",
              returnType = null,
              args =
                listOf(
                  ArgModel(
                    "viewport",
                    TypeMapping.OBJECT,
                    objectWrapperFqName = "net.multigesture.kanama.api.GodotObject",
                  ),
                  ArgModel(
                    "event",
                    TypeMapping.OBJECT,
                    objectWrapperFqName = "net.multigesture.kanama.api.GodotObject",
                  ),
                  ArgModel("shapeIdx", TypeMapping.INT),
                ),
              kind = MethodKind.REGULAR,
            ),
            MethodModel(
              kotlinName = "getTileType",
              godotName = "get_tile_type",
              returnType = TypeMapping.STRING,
              args = emptyList(),
              kind = MethodKind.REGULAR,
            ),
          ),
        signals = listOf(SignalModel("tile_pressed", listOf(ArgModel("pos", TypeMapping.VECTOR2I)))),
      )
    val emitter =
      WebScriptCodeEmitter(
        listOf(
          WebScriptInput(main, "res://kotlin-src/Main.kt"),
          WebScriptInput(tile, "res://kotlin-src/Tile.kt"),
        )
      )
    val proxies = emitter.proxySources().associateBy { it.fileName }
    val mainProxy = proxies.getValue("Main").source
    val tileProxy = proxies.getValue("Tile").source

    assertTrue(mainProxy.contains("extends Node2D"))
    assertTrue(mainProxy.contains("@export_subgroup(\"Properties\")"))
    assertTrue(mainProxy.contains("@export var tile_scene: PackedScene = null"))
    assertTrue(mainProxy.contains("@export var textures: Array[Texture2D] = []"))
    assertTrue(mainProxy.contains("@export var open_hand_cursor: Texture2D = null"))
    assertTrue(mainProxy.contains("setLongProperty(_kanama_handle, 1, width)"))
    assertTrue(mainProxy.contains("setObjectProperty(_kanama_handle, 2, property_handle_2)"))
    assertTrue(mainProxy.contains("setObjectArrayProperty(_kanama_handle, 3, property_handles_3)"))
    assertTrue(mainProxy.contains("func _kanama_node_lookup(args: Array) -> int:"))
    assertTrue(mainProxy.contains("recordImmediateObjectHandle(script_handle)"))
    assertTrue(mainProxy.contains("if is_same(_kanama_object_handles[existing_handle], value):"))
    assertTrue(mainProxy.contains("refreshNode2DSnapshot(result_handle,"))
    assertTrue(mainProxy.contains("func _kanama_packed_scene_instantiate(args: Array) -> int:"))
    assertTrue(mainProxy.contains("func _kanama_input_cursor(args: Array) -> int:"))
    assertTrue(mainProxy.contains("func _kanama_connect(args: Array) -> int:"))
    assertTrue(mainProxy.contains("callable = callable.bind(int(args[5]))"))
    assertTrue(mainProxy.contains("func _kanama_signal_emit(args: Array) -> int:"))
    assertTrue(mainProxy.contains("result = value.emit_signal(StringName(String(args[1])))"))
    assertTrue(mainProxy.contains("func _kanama_web_signal_dispatch0(callback_id: int) -> void:"))
    assertTrue(mainProxy.contains("dispatchSignal0(_kanama_handle, callback_id)"))
    assertTrue(mainProxy.contains("func _input(event: InputEvent) -> void:"))
    assertTrue(mainProxy.contains("_kanama_bridge.input(_kanama_handle, event_handle)"))
    assertTrue(
      mainProxy.contains("callVector2i(_kanama_handle, 1, gridPosition.x, gridPosition.y)")
    )

    assertTrue(tileProxy.contains("extends Area2D"))
    assertTrue(tileProxy.contains("signal tile_pressed(pos: Vector2i)"))
    assertTrue(tileProxy.contains("func set_tile_type(id: String, texture: Texture2D) -> void:"))
    assertTrue(
      tileProxy.contains(
        "func _input_event(viewport: Object, event: Object, shapeIdx: int) -> void:"
      )
    )
    assertTrue(tileProxy.contains("func get_tile_type() -> String:"))
    assertTrue(tileProxy.contains("shouldDeferGameplayMethod"))
    assertTrue(tileProxy.contains("recordDeferredGameplayMethod"))
    assertTrue(
      tileProxy.contains(
        "callObjectObjectLong(_kanama_handle, 2, first_handle, second_handle, shapeIdx)"
      )
    )
    assertTrue(
      tileProxy.contains("unsupportedGameplayMethod(_KANAMA_SCRIPT_ID, 1, \"set_tile_type\")")
    )

    // Neither match3 script declares _enter_tree, so neither proxy may emit the crossing:
    // the enter-tree path is opt-in per script, not another unconditional lifecycle hook.
    assertFalse(mainProxy.contains("func _enter_tree()"), "Main must not emit _enter_tree")
    assertFalse(tileProxy.contains("func _enter_tree()"), "Tile must not emit _enter_tree")

    val protocol = emitter.protocolManifest()
    assertTrue(protocol.contains("\"protocolVersion\": 16"))
    assertTrue(protocol.contains("\"attachTo\": \"Area2D\""))
    assertTrue(protocol.contains("\"type\": \"List<net.multigesture.kanama.api.Texture2D>\""))
    assertTrue(protocol.contains("\"type\": \"net.multigesture.kanama.types.Vector2i\""))
    assertTrue(protocol.contains("\"name\": \"_input_event\""))

    val constants = emitter.constantsSource()
    assertTrue(constants.contains("object TileSignals"))
    assertTrue(constants.contains("fun tilePressed("))
    assertTrue(constants.contains("const val setTileType: String = \"set_tile_type\""))
    assertTrue(emitter.compatibilitySources().containsKey("net.multigesture.kanama.demos.match3"))
    assertTrue(emitter.proxyManifest().startsWith("# kanama-web-protocol=16\n"))

    val registry = emitter.registrySource()
    assertTrue(registry.contains("(script as Main).width = value"))
    assertTrue(registry.contains("(script as Main).tileScene = handle?.let"))
    assertTrue(registry.contains("(script as Main).textures = values.map"))
    assertTrue(registry.contains("(script as Main).input("))
    assertTrue(registry.contains("(script as Main).onTilePressed(Vector2i(x, y))"))
    assertTrue(registry.contains("(script as Tile).inputEvent("))
  }

  // ---------- Task 66a: virtuals the Web backend does not dispatch ----------

  private fun scriptWith(vararg virtuals: VirtualModel) =
    ScriptModel(
      simpleName = "Player",
      fqName = "net.multigesture.kanama.web.Player",
      attachTo = "CharacterBody3D",
      isTool = false,
      isGlobalClass = false,
      properties = emptyList(),
      toolButtons = emptyList(),
      virtuals = virtuals.toList(),
      methods = emptyList(),
      signals = emptyList(),
    )

  private val webOptions = mapOf("kanamaRuntimeTarget" to "web")

  @Test
  fun acceptsEnterTreeOnAWebTargetNowThatItIsDispatched() {
    // Task 66b: _enter_tree joined DISPATCHED_VIRTUALS (proxy crossing + registry dispatcher),
    // so the 66a build-time rejection must no longer fire for it — while still firing for every
    // other undispatched virtual (covered below).
    val model = scriptWith(VirtualModel("_enter_tree", "enterTree", "enterTree"))

    assertTrue(WebScriptCodeEmitter.undispatchedVirtualErrors(model, webOptions).isEmpty())
    assertTrue("_enter_tree" in WebScriptCodeEmitter.DISPATCHED_VIRTUALS)
  }

  @Test
  fun acceptsEnterTreeOnJvmAndIosTargets() {
    val model = scriptWith(VirtualModel("_enter_tree", "callEnterTree", "enterTree"))

    // JVM and iOS leave `kanamaRuntimeTarget` unset (only web-runtime sets it); an explicit
    // non-web value must not trip the gate either.
    assertTrue(WebScriptCodeEmitter.undispatchedVirtualErrors(model, emptyMap()).isEmpty())
    assertTrue(
      WebScriptCodeEmitter.undispatchedVirtualErrors(model, mapOf("kanamaRuntimeTarget" to "ios"))
        .isEmpty()
    )
    assertFalse(WebScriptCodeEmitter.isWebTarget(emptyMap()))
    assertFalse(WebScriptCodeEmitter.isWebTarget(mapOf("kanamaRuntimeTarget" to "ios")))
    assertTrue(WebScriptCodeEmitter.isWebTarget(webOptions))
  }

  @Test
  fun rejectsEveryOtherUndispatchedVirtualOnAWebTarget() {
    val model =
      scriptWith(
        VirtualModel("_ready", "ready", "ready"),
        VirtualModel(
          "_shortcut_input",
          "shortcutInput",
          "shortcutInput",
          args =
            listOf(ArgModel("event", TypeMapping.OBJECT, "net.multigesture.kanama.api.GodotObject")),
        ),
      )

    val errors = WebScriptCodeEmitter.undispatchedVirtualErrors(model, webOptions)

    assertEquals(1, errors.size, "only the undispatched virtual may be rejected, got $errors")
    assertTrue(errors.single().contains("Player.shortcutInput"), errors.single())
    assertTrue(errors.single().contains("_shortcut_input"), errors.single())
  }

  @Test
  fun everyDispatchedVirtualReachesTheGeneratedRegistry() {
    val eventArg =
      listOf(ArgModel("event", TypeMapping.OBJECT, "net.multigesture.kanama.api.GodotObject"))
    val deltaArg = listOf(ArgModel("delta", TypeMapping.FLOAT))
    val model =
      scriptWith(
        VirtualModel("_enter_tree", "enterTree", "onEnterTree"),
        VirtualModel("_ready", "ready", "onReady"),
        VirtualModel("_process", "process", "onProcess", args = deltaArg),
        VirtualModel("_physics_process", "physicsProcess", "onPhysicsProcess", args = deltaArg),
        VirtualModel("_draw", "draw", "onDraw"),
        VirtualModel("_exit_tree", "exitTree", "onExitTree"),
        VirtualModel("_input", "input", "onInput", args = eventArg),
        VirtualModel("_unhandled_input", "unhandledInput", "onUnhandledInput", args = eventArg),
      )

    // The claim behind the gate: everything in DISPATCHED_VIRTUALS really is crossed into
    // Kotlin, so a virtual outside the set is the only silent drop.
    assertTrue(
      WebScriptCodeEmitter.undispatchedVirtualErrors(model, webOptions).isEmpty(),
      "DISPATCHED_VIRTUALS must cover this fixture",
    )
    assertEquals(
      WebScriptCodeEmitter.DISPATCHED_VIRTUALS,
      model.virtuals.map { it.virtualName }.toSet(),
      "fixture must exercise exactly the dispatched set",
    )

    val registry =
      WebScriptCodeEmitter(listOf(WebScriptInput(model, "res://Player.kt"))).registrySource()
    for (virtual in model.virtuals) {
      assertTrue(
        registry.contains("(script as Player).${virtual.kotlinMethodName}("),
        "${virtual.virtualName} is listed as dispatched but never reaches the script",
      )
    }
  }

  // ---- Task 64: NodePath / hint metadata / Vector3 completion / property guards ----

  private fun task64Property(
    kotlinName: String,
    godotName: String,
    type: TypeMapping,
    hint: Int = 0,
    hintString: String = "",
    defaultLiteral: String? = null,
    enumFqName: String? = null,
    narrow: NarrowScalar? = null,
  ) =
    ScriptPropertyModel(
      kotlinName = kotlinName,
      godotName = godotName,
      type = type,
      isMutable = true,
      hint = hint,
      hintString = hintString,
      defaultLiteral = defaultLiteral,
      enumFqName = enumFqName,
      enumEntries = if (enumFqName != null) listOf("A", "B") else emptyList(),
      narrow = narrow,
    )

  private fun task64Model(properties: List<ScriptPropertyModel>) =
    ScriptModel(
      simpleName = "Task64Script",
      fqName = "net.multigesture.kanama.web.Task64Script",
      attachTo = "Node3D",
      isTool = false,
      isGlobalClass = false,
      properties = properties,
      toolButtons = emptyList(),
      virtuals = emptyList(),
      methods = emptyList(),
      signals = emptyList(),
    )

  @Test
  fun emitsNodePathRangeAndVector3PropertyArms() {
    val model =
      task64Model(
        listOf(
          task64Property(
            "viewPath",
            "view",
            TypeMapping.NODE_PATH,
            defaultLiteral = "net.multigesture.kanama.types.NodePath(\"../View\")",
          ),
          task64Property(
            "numberOfJumps",
            "number_of_jumps",
            TypeMapping.INT,
            hint = 1,
            hintString = "0,100,1",
            defaultLiteral = "2",
          ),
          task64Property(
            "sensitivity",
            "sensitivity",
            TypeMapping.FLOAT,
            hint = 1,
            hintString = "0.0,1.0,0.01,or_greater",
            defaultLiteral = "0.25",
          ),
          task64Property(
            "spawnOffset",
            "spawn_offset",
            TypeMapping.VECTOR3,
            defaultLiteral = "net.multigesture.kanama.types.Vector3(1.0, 2.0, 3.0)",
          ),
          task64Property(
            "restPoint",
            "rest_point",
            TypeMapping.VECTOR3,
            defaultLiteral = "net.multigesture.kanama.types.Vector3.ZERO",
          ),
        )
      )
    assertTrue(
      WebScriptCodeEmitter.unsupportedWebPropertyErrors(model, webOptions).isEmpty(),
      "the supported fixtures must pass the Web property guard",
    )

    val emitter = WebScriptCodeEmitter(listOf(WebScriptInput(model, "res://Task64Script.kt")))
    val proxy = emitter.proxySources().single { it.sourceResourcePath.isNotEmpty() }.source

    // Declarations: NodePath with its literal default, RANGE hints as @export_range (numeric
    // parts bare, option flags quoted), Vector3 typed with the Kotlin literal honored.
    assertTrue(proxy.contains("@export var view: NodePath = NodePath(\"../View\")"))
    assertTrue(proxy.contains("@export_range(0, 100, 1) var number_of_jumps: int = 2"))
    assertTrue(
      proxy.contains("@export_range(0.0, 1.0, 0.01, \"or_greater\") var sensitivity: float = 0.25")
    )
    assertTrue(proxy.contains("@export var spawn_offset: Vector3 = Vector3(1.0, 2.0, 3.0)"))
    assertTrue(proxy.contains("@export var rest_point: Vector3 = Vector3.ZERO"))

    // Push (engine -> Kotlin at hydration): NodePath rides the string channel as its path.
    assertTrue(proxy.contains("_kanama_bridge.setStringProperty(_kanama_handle, 1, String(view))"))
    assertTrue(
      proxy.contains(
        "_kanama_bridge.setVector3Property(_kanama_handle, 4, spawn_offset.x, spawn_offset.y, spawn_offset.z)"
      )
    )

    // Pull (save-time sync): the packed string rewraps into a GDScript NodePath.
    assertTrue(proxy.contains("view = NodePath(_kanama_packed_1)"))

    // Kotlin registry: setter rewraps into the web NodePath value class; getter and packed
    // getter unwrap the path.
    val registry = emitter.registrySource()
    assertTrue(
      registry.contains(
        "(script as Task64Script).viewPath = net.multigesture.kanama.types.NodePath(value)"
      )
    )
    assertTrue(registry.contains("(script as Task64Script).viewPath.path"))
  }

  @Test
  fun rejectsExpressionDefaultsOnWebTargets() {
    val model =
      task64Model(listOf(task64Property("tiltUpperLimit", "tilt_upper_limit", TypeMapping.FLOAT)))
    val errors = WebScriptCodeEmitter.unsupportedWebPropertyErrors(model, webOptions)
    assertEquals(1, errors.size)
    assertTrue(errors.single().contains("Task64Script.tiltUpperLimit"))
    assertTrue(errors.single().contains("Spell the default as a literal"))
    // Non-Web targets keep the old behavior (the JVM/iOS emitters do not consume the
    // proxy default), so the guard must stay silent there.
    assertTrue(WebScriptCodeEmitter.unsupportedWebPropertyErrors(model, emptyMap()).isEmpty())
  }

  @Test
  fun rejectsPropertyTypesWithoutFullWebArmSets() {
    val unsupported =
      listOf(
        task64Property("stats", "stats", TypeMapping.DICTIONARY, defaultLiteral = "emptyMap()"),
        task64Property("gridCell", "grid_cell", TypeMapping.VECTOR3I, defaultLiteral = "x"),
        task64Property(
          "mode",
          "mode",
          TypeMapping.INT,
          defaultLiteral = "0",
          enumFqName = "demo.Mode",
        ),
        task64Property(
          "narrowValue",
          "narrow_value",
          TypeMapping.FLOAT,
          defaultLiteral = "1f",
          narrow = NarrowScalar.FLOAT32,
        ),
      )
    for (property in unsupported) {
      val errors =
        WebScriptCodeEmitter.unsupportedWebPropertyErrors(task64Model(listOf(property)), webOptions)
      assertEquals(1, errors.size, "expected exactly one error for ${property.kotlinName}")
      assertTrue(errors.single().contains("Task64Script.${property.kotlinName}"))
      assertTrue(errors.single().contains("no full Kanama Web property arm set"))
    }
  }

  @Test
  fun rejectsInexpressibleHintsLoudly() {
    // RANGE on a non-numeric export.
    val rangeOnString =
      task64Model(
        listOf(
          task64Property(
            "label",
            "label",
            TypeMapping.STRING,
            hint = 1,
            hintString = "0,1",
            defaultLiteral = "\"x\"",
          )
        )
      )
    assertTrue(
      WebScriptCodeEmitter.unsupportedWebPropertyErrors(rangeOnString, webOptions)
        .single()
        .contains("only expressible for int/float")
    )

    // RANGE without a numeric min,max prefix.
    val badRange =
      task64Model(
        listOf(
          task64Property(
            "jumps",
            "jumps",
            TypeMapping.INT,
            hint = 1,
            hintString = "lots",
            defaultLiteral = "1",
          )
        )
      )
    assertTrue(
      WebScriptCodeEmitter.unsupportedWebPropertyErrors(badRange, webOptions)
        .single()
        .contains("cannot be emitted as")
    )

    // A hint with no Web emission at all (e.g. MULTILINE_TEXT = 4) must never be dropped
    // silently.
    val unknownHint =
      task64Model(
        listOf(
          task64Property(
            "notes",
            "notes",
            TypeMapping.STRING,
            hint = 4,
            hintString = "",
            defaultLiteral = "\"\"",
          )
        )
      )
    assertTrue(
      WebScriptCodeEmitter.unsupportedWebPropertyErrors(unknownHint, webOptions)
        .single()
        .contains("no Kanama Web proxy emission")
    )
    assertTrue(WebScriptCodeEmitter.unsupportedWebPropertyErrors(unknownHint, emptyMap()).isEmpty())
  }

  @Test
  fun parsesRangeHintStrings() {
    assertEquals(listOf("0", "100", "1"), WebScriptCodeEmitter.rangeExportArguments("0,100,1"))
    assertEquals(
      listOf("0.0", "1.0", "0.01", "\"or_greater\""),
      WebScriptCodeEmitter.rangeExportArguments("0.0,1.0,0.01,or_greater"),
    )
    assertEquals(listOf("-4", "4"), WebScriptCodeEmitter.rangeExportArguments("-4,4"))
    assertEquals(null, WebScriptCodeEmitter.rangeExportArguments("lots"))
    assertEquals(null, WebScriptCodeEmitter.rangeExportArguments("1"))
    assertEquals(null, WebScriptCodeEmitter.rangeExportArguments("1,,2"))
  }
}
