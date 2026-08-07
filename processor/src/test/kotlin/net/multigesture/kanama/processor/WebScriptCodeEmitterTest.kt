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

    assertTrue(source.contains("const val PROTOCOL_VERSION: Int = 18"))
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
    // Task 80 slice 3: match3's (STRING, OBJECT) shape used to emit a throwing stub here; it now
    // rides the packed argument list over the existing string crossing.
    assertFalse(
      tileProxy.contains("unsupportedGameplayMethod(_KANAMA_SCRIPT_ID, 1, \"set_tile_type\")")
    )
    assertTrue(
      tileProxy.contains("callString(_kanama_handle, 1, \"\\u001f\".join(_kanama_packed_args))"),
      tileProxy,
    )
    assertTrue(
      tileProxy.contains(
        "_kanama_packed_args.append(str(_kanama_web_pack_object(texture, _kanama_packed_transient)))"
      ),
      tileProxy,
    )

    // Neither match3 script declares _enter_tree, so neither proxy may emit the crossing:
    // the enter-tree path is opt-in per script, not another unconditional lifecycle hook.
    assertFalse(mainProxy.contains("func _enter_tree()"), "Main must not emit _enter_tree")
    assertFalse(tileProxy.contains("func _enter_tree()"), "Tile must not emit _enter_tree")

    val protocol = emitter.protocolManifest()
    assertTrue(protocol.contains("\"protocolVersion\": 18"))
    assertTrue(protocol.contains("\"attachTo\": \"Area2D\""))
    assertTrue(protocol.contains("\"type\": \"List<net.multigesture.kanama.api.Texture2D>\""))
    assertTrue(protocol.contains("\"type\": \"net.multigesture.kanama.types.Vector2i\""))
    assertTrue(protocol.contains("\"name\": \"_input_event\""))

    val constants = emitter.constantsSource()
    assertTrue(constants.contains("object TileSignals"))
    assertTrue(constants.contains("fun tilePressed("))
    assertTrue(constants.contains("const val setTileType: String = \"set_tile_type\""))
    assertTrue(emitter.compatibilitySources().containsKey("net.multigesture.kanama.demos.match3"))
    assertTrue(emitter.proxyManifest().startsWith("# kanama-web-protocol=18\n"))

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

  // ---------- Task 80: the generator declares its own degradations, and fills them ----------

  /**
   * The dispatch fixture: one method per interesting arm plus the three signal shapes.
   *
   * `damage(Double)` is fps's real hole (task 79) and `knockback(Vector3, Vector3)` third-person's;
   * both ride the slice-2 numeric crossing now. `aim_target`/`current_health` are the whole
   * value-returning category, which had no arm at all. `retarget(String, Object)` is match3's shape
   * and is deliberately NOT filled by slice 2 — it stays in the census with its reason, so the
   * fixture keeps proving that an unfilled shape is still declared rather than hidden.
   */
  private fun task80Model() =
    ScriptModel(
      simpleName = "Enemy",
      fqName = "net.multigesture.kanama.web.Enemy",
      attachTo = "Node3D",
      isTool = false,
      isGlobalClass = false,
      properties =
        listOf(
          ScriptPropertyModel(
            kotlinName = "health",
            godotName = "health",
            type = TypeMapping.FLOAT,
            isMutable = true,
            defaultLiteral = "100.0",
          )
        ),
      toolButtons = emptyList(),
      virtuals = listOf(VirtualModel("_ready", "callReady", "ready")),
      methods =
        listOf(
          MethodModel(
            kotlinName = "damage",
            godotName = "damage",
            returnType = null,
            args = listOf(ArgModel("amount", TypeMapping.FLOAT)),
            kind = MethodKind.REGULAR,
          ),
          MethodModel(
            kotlinName = "die",
            godotName = "die",
            returnType = null,
            args = emptyList(),
            kind = MethodKind.REGULAR,
          ),
          MethodModel(
            kotlinName = "knockback",
            godotName = "knockback",
            returnType = null,
            args =
              listOf(
                ArgModel("impactPoint", TypeMapping.VECTOR3),
                ArgModel("force", TypeMapping.VECTOR3),
              ),
            kind = MethodKind.REGULAR,
          ),
          MethodModel(
            kotlinName = "setStunned",
            godotName = "set_stunned",
            returnType = null,
            args = listOf(ArgModel("stunned", TypeMapping.BOOL)),
            kind = MethodKind.REGULAR,
          ),
          MethodModel(
            kotlinName = "aimTarget",
            godotName = "aim_target",
            returnType = TypeMapping.VECTOR3,
            args = emptyList(),
            kind = MethodKind.REGULAR,
          ),
          MethodModel(
            kotlinName = "currentHealth",
            godotName = "current_health",
            returnType = TypeMapping.FLOAT,
            args = emptyList(),
            kind = MethodKind.REGULAR,
          ),
          MethodModel(
            kotlinName = "retarget",
            godotName = "retarget",
            returnType = null,
            args =
              listOf(
                ArgModel("tag", TypeMapping.STRING),
                ArgModel("node", TypeMapping.OBJECT, "net.multigesture.kanama.api.GodotObject"),
              ),
            kind = MethodKind.REGULAR,
          ),
          // tps-demo's add_player(id, spawnPoint: Marker3D?) shape: the object is nullable and
          // rides the same packed list as the (STRING, OBJECT) one above.
          MethodModel(
            kotlinName = "addPlayer",
            godotName = "add_player",
            returnType = null,
            args =
              listOf(
                ArgModel("id", TypeMapping.INT),
                ArgModel(
                  "spawnPoint",
                  TypeMapping.OBJECT,
                  "net.multigesture.kanama.api.GodotObject",
                  nullable = true,
                ),
              ),
            kind = MethodKind.REGULAR,
          ),
          // A shape no arm covers: a FLOAT argument WITH a value return. The numeric crossing is
          // void-only, the packed return takes no arguments, and the packed argument list refuses
          // floats (they do not round-trip through GDScript's decimal text).
          MethodModel(
            kotlinName = "reload",
            godotName = "reload",
            returnType = TypeMapping.INT,
            args = listOf(ArgModel("seconds", TypeMapping.FLOAT)),
            kind = MethodKind.REGULAR,
          ),
        ),
      signals =
        listOf(
          SignalModel("died", emptyList()),
          SignalModel("hurt", listOf(ArgModel("amount", TypeMapping.FLOAT))),
          SignalModel(
            "scored",
            listOf(ArgModel("points", TypeMapping.INT), ArgModel("combo", TypeMapping.INT)),
          ),
        ),
    )

  /** [task80Model] with its two deliberately degraded members removed: the gate must pass. */
  private fun task80TypedModel() =
    task80Model()
      .copy(
        methods = task80Model().methods.filter { it.godotName != "reload" },
        signals = task80Model().signals.filter { it.godotName != "scored" },
      )

  private fun task80Proxy(): String =
    WebScriptCodeEmitter(listOf(WebScriptInput(task80Model(), "res://Enemy.kt")))
      .proxySources()
      .single { it.sourceResourcePath.isNotEmpty() }
      .source

  @Test
  fun declaresUnsupportedDispatchForAMethodArgumentWithNoArm() {
    // `(FLOAT) -> INT` falls between every arm: the numeric crossing is void-only, the packed
    // return takes no arguments, and the packed argument list refuses floats on purpose.
    val method = task80Model().methods.single { it.godotName == "reload" }
    val dispatch = WebScriptCodeEmitter.methodDispatch(method)

    assertEquals(WebMethodArm.NONE, WebScriptCodeEmitter.methodArm(method))
    assertEquals(WebDispatchStatus.UNSUPPORTED, dispatch.status)
    assertEquals("unsupported", dispatch.status.json)
    // The reason must name the shape that has no arm, not just say "unsupported".
    assertTrue(dispatch.reason!!.contains("(FLOAT) -> INT"), dispatch.reason!!)
    assertTrue(dispatch.reason!!.contains("throws"), dispatch.reason!!)

    // The claim behind the status: this is exactly the arm that emits the throwing stub.
    assertTrue(
      task80Proxy().contains("unsupportedGameplayMethod(_KANAMA_SCRIPT_ID, 9, \"reload\")")
    )
  }

  @Test
  fun declaresTypedDispatchForANoArgumentMethod() {
    val method = task80Model().methods.single { it.godotName == "die" }
    val dispatch = WebScriptCodeEmitter.methodDispatch(method)

    assertEquals(WebMethodArm.NO_ARGS, WebScriptCodeEmitter.methodArm(method))
    assertEquals(WebDispatchStatus.TYPED, dispatch.status)
    assertEquals(null, dispatch.reason, "a typed entry carries no dispatchReason")
  }

  // ---------- Task 80 slice 2: the numeric crossing ----------

  @Test
  fun dispatchesASingleFloatArgumentThroughTheNumericCrossing() {
    // Task 79's exact bug: fps Enemy.damage(amount: Double) used to emit a throwing stub.
    val method = task80Model().methods.single { it.godotName == "damage" }
    assertEquals(WebMethodArm.NUMERIC_VOID, WebScriptCodeEmitter.methodArm(method))
    assertEquals(WebDispatchStatus.TYPED, WebScriptCodeEmitter.methodDispatch(method).status)
    assertEquals(null, WebScriptCodeEmitter.methodDispatch(method).reason)

    val proxy = task80Proxy()
    assertTrue(proxy.contains("func damage(amount: float) -> void:"), proxy)
    assertTrue(
      proxy.contains(
        "_kanama_bridge.callDoubles(_kanama_handle, 1, amount, 0.0, 0.0, 0.0, 0.0, 0.0)"
      ),
      proxy,
    )
    assertFalse(proxy.contains("unsupportedGameplayMethod(_KANAMA_SCRIPT_ID, 1,"), proxy)

    val registry =
      WebScriptCodeEmitter(listOf(WebScriptInput(task80Model(), "res://Enemy.kt"))).registrySource()
    assertTrue(
      registry.contains(
        "fun callDoubles(scriptId: Int, methodId: Int, script: KanamaWebScript, " +
          "a0: Double, a1: Double, a2: Double, a3: Double, a4: Double, a5: Double)"
      ),
      registry,
    )
    assertTrue(registry.contains("1 -> (script as Enemy).damage(a0)"), registry)
  }

  @Test
  fun dispatchesAVector3PairAndABooleanThroughTheNumericCrossing() {
    // third-person's body.call("damage", impactPoint, force) shape, and the (BOOL) bucket.
    val pair = task80Model().methods.single { it.godotName == "knockback" }
    val flag = task80Model().methods.single { it.godotName == "set_stunned" }
    assertEquals(WebMethodArm.NUMERIC_VOID, WebScriptCodeEmitter.methodArm(pair))
    assertEquals(WebMethodArm.NUMERIC_VOID, WebScriptCodeEmitter.methodArm(flag))
    assertEquals(WebDispatchStatus.TYPED, WebScriptCodeEmitter.methodDispatch(pair).status)
    assertEquals(WebDispatchStatus.TYPED, WebScriptCodeEmitter.methodDispatch(flag).status)

    val proxy = task80Proxy()
    // Six slots is exactly one Vector3 pair -- nothing is padded away.
    assertTrue(
      proxy.contains(
        "_kanama_bridge.callDoubles(_kanama_handle, 3, impactPoint.x, impactPoint.y, " +
          "impactPoint.z, force.x, force.y, force.z)"
      ),
      proxy,
    )
    assertTrue(
      proxy.contains(
        "_kanama_bridge.callDoubles(_kanama_handle, 4, float(stunned), 0.0, 0.0, 0.0, 0.0, 0.0)"
      ),
      proxy,
    )

    val registry =
      WebScriptCodeEmitter(listOf(WebScriptInput(task80Model(), "res://Enemy.kt"))).registrySource()
    assertTrue(
      registry.contains(
        "3 -> (script as Enemy).knockback(net.multigesture.kanama.types.Vector3(a0, a1, a2), " +
          "net.multigesture.kanama.types.Vector3(a3, a4, a5))"
      ),
      registry,
    )
    assertTrue(registry.contains("4 -> (script as Enemy).setStunned(a0 != 0.0)"), registry)
  }

  @Test
  fun rejectsAnArgumentListWiderThanTheNumericSlots() {
    // Seven components is one past the six-slot crossing: no arm, and the census says why.
    val wide =
      MethodModel(
        kotlinName = "wide",
        godotName = "wide",
        returnType = null,
        args =
          listOf(
            ArgModel("a", TypeMapping.VECTOR3),
            ArgModel("b", TypeMapping.VECTOR3),
            ArgModel("c", TypeMapping.FLOAT),
          ),
        kind = MethodKind.REGULAR,
      )
    assertEquals(null, WebScriptCodeEmitter.numericArgSlots(wide.args))
    assertEquals(WebMethodArm.NONE, WebScriptCodeEmitter.methodArm(wide))
    assertTrue(
      WebScriptCodeEmitter.methodDispatch(wide)
        .reason!!
        .contains("(VECTOR3, VECTOR3, FLOAT) -> void")
    )
  }

  // ---------- Task 80 slice 2: value-returning methods ----------

  @Test
  fun dispatchesValueReturningMethodsThroughThePackedCrossing() {
    val vector = task80Model().methods.single { it.godotName == "aim_target" }
    val scalar = task80Model().methods.single { it.godotName == "current_health" }
    assertEquals(WebMethodArm.PACKED_RETURN, WebScriptCodeEmitter.methodArm(vector))
    assertEquals(WebMethodArm.PACKED_RETURN, WebScriptCodeEmitter.methodArm(scalar))
    assertEquals(WebDispatchStatus.TYPED, WebScriptCodeEmitter.methodDispatch(vector).status)
    assertEquals(WebDispatchStatus.TYPED, WebScriptCodeEmitter.methodDispatch(scalar).status)

    val proxy = task80Proxy()
    assertTrue(
      proxy.contains("var _kanama_packed := String(_kanama_bridge.callPacked(_kanama_handle, 5))"),
      proxy,
    )
    assertTrue(
      proxy.contains("return Vector3(_kanama_parts[0], _kanama_parts[1], _kanama_parts[2])"),
      proxy,
    )
    assertTrue(
      proxy.contains("var _kanama_packed := String(_kanama_bridge.callPacked(_kanama_handle, 6))"),
      proxy,
    )
    assertTrue(proxy.contains("return float(_kanama_packed)"), proxy)

    val registry =
      WebScriptCodeEmitter(listOf(WebScriptInput(task80Model(), "res://Enemy.kt"))).registrySource()
    assertTrue(
      registry.contains(
        "fun callPacked(scriptId: Int, methodId: Int, script: KanamaWebScript): String ="
      ),
      registry,
    )
    assertTrue(
      registry.contains(
        "5 -> (script as Enemy).aimTarget().let { \"\${it.x},\${it.y},\${it.z}\" }"
      ),
      registry,
    )
    assertTrue(registry.contains("6 -> (script as Enemy).currentHealth().toString()"), registry)
  }

  @Test
  fun packsEveryValueReturnTypeTheCrossingClaims() {
    // The packed channel is only honest if every type it claims has both an encode and a parse.
    fun armFor(type: TypeMapping) =
      WebScriptCodeEmitter.methodArm(
        MethodModel("probe", "probe", type, emptyList(), MethodKind.REGULAR)
      )
    listOf(
        TypeMapping.STRING,
        TypeMapping.NODE_PATH,
        TypeMapping.INT,
        TypeMapping.FLOAT,
        TypeMapping.BOOL,
        TypeMapping.VECTOR2,
        TypeMapping.VECTOR2I,
        TypeMapping.VECTOR3,
        TypeMapping.QUATERNION,
        TypeMapping.BASIS,
      )
      .forEach { type ->
        assertTrue(WebScriptCodeEmitter.isPackedReturn(type), type.name)
        assertEquals(WebMethodArm.PACKED_RETURN, armFor(type), type.name)
      }
    // An object return still needs handle bookkeeping: no arm, declared in the census.
    assertFalse(WebScriptCodeEmitter.isPackedReturn(TypeMapping.OBJECT))
    assertEquals(WebMethodArm.NONE, armFor(TypeMapping.OBJECT))
  }

  // ---------- Task 80 slice 2: signal payload delivery ----------

  @Test
  fun deliversScalarSignalPayloadsAndStillDropsMultiArgumentOnes() {
    // Slice 2: one emitted scalar now crosses PACKED, so the declared payload does reach Kotlin.
    val scalar =
      WebScriptCodeEmitter.signalDispatch(
        SignalModel("hurt", listOf(ArgModel("amount", TypeMapping.FLOAT)))
      )
    assertEquals(WebDispatchStatus.TYPED, scalar.status)
    assertEquals(null, scalar.reason)

    // Zero-argument signals ride dispatch0 intact; a lone object argument rides
    // dispatch_object as a handle. Neither is a degradation.
    assertEquals(
      WebDispatchStatus.TYPED,
      WebScriptCodeEmitter.signalDispatch(SignalModel("died", emptyList())).status,
    )
    assertEquals(
      WebDispatchStatus.TYPED,
      WebScriptCodeEmitter.signalDispatch(
          SignalModel(
            "body_entered",
            listOf(ArgModel("body", TypeMapping.OBJECT, "net.multigesture.kanama.api.GodotObject")),
          )
        )
        .status,
    )

    // Multi-argument payloads cannot reach a lambda at all (connect requires 0..1), but a named
    // registered-method connect can still carry them -- so they are dropped, not unsupported.
    val multi =
      WebScriptCodeEmitter.signalDispatch(
        SignalModel(
          "scored",
          listOf(ArgModel("points", TypeMapping.INT), ArgModel("combo", TypeMapping.INT)),
        )
      )
    assertEquals(WebDispatchStatus.ARGUMENT_DROPPED, multi.status)
    assertEquals("argument-dropped", multi.status.json)
    assertTrue(multi.reason!!.contains("at most 1 emitted argument"), multi.reason!!)

    // A single payload outside the scalar set still drops, and says which type.
    val array =
      WebScriptCodeEmitter.signalDispatch(
        SignalModel("loaded", listOf(ArgModel("items", TypeMapping.ARRAY)))
      )
    assertEquals(WebDispatchStatus.ARGUMENT_DROPPED, array.status)
    assertTrue(array.reason!!.contains("(ARRAY)"), array.reason!!)
  }

  @Test
  fun emitsAPackingSignalDeliveryHelper() {
    val proxy = task80Proxy()
    // The one-argument helper carries the payload instead of discarding it.
    assertTrue(
      proxy.contains("func _kanama_web_signal_dispatch1(arg: Variant, callback_id: int) -> void:"),
      proxy,
    )
    assertTrue(
      proxy.contains(
        "_kanama_bridge.dispatchSignal1(_kanama_handle, callback_id, " +
          "_kanama_web_pack_signal_arg(arg))"
      ),
      proxy,
    )
    assertFalse(proxy.contains("dropped by the Kotlin callback"), proxy)
    // The packer must agree with the property/return encoding for every scalar it claims.
    assertTrue(proxy.contains("func _kanama_web_pack_signal_arg(arg: Variant) -> String:"), proxy)
    assertTrue(proxy.contains("\t\t\treturn \"1\" if arg else \"0\""), proxy)
    assertTrue(proxy.contains("\t\t\treturn \"%s,%s\" % [arg.x, arg.y]"), proxy)
    assertTrue(proxy.contains("\t\t\treturn \"%s,%s,%s\" % [arg.x, arg.y, arg.z]"), proxy)
  }

  // ---------- Task 80 slice 3: the mixed-channel packed argument list ----------

  @Test
  fun dispatchesMixedArgumentShapesThroughThePackedArgumentCrossing() {
    // The two shapes slice 2 left unfilled, both of which mix the string and object-handle
    // channels: match3 Tile.set_tile_type(String, Texture2D) and tps Level.add_player(id,
    // Marker3D?).
    val text = task80Model().methods.single { it.godotName == "retarget" }
    val nullableObject = task80Model().methods.single { it.godotName == "add_player" }
    assertEquals(WebMethodArm.PACKED_ARGS, WebScriptCodeEmitter.methodArm(text))
    assertEquals(WebMethodArm.PACKED_ARGS, WebScriptCodeEmitter.methodArm(nullableObject))
    assertEquals(WebDispatchStatus.TYPED, WebScriptCodeEmitter.methodDispatch(text).status)
    assertEquals(null, WebScriptCodeEmitter.methodDispatch(nullableObject).reason)

    val proxy = task80Proxy()
    // The text argument is %-escaped, the object argument becomes a handle id, and the whole
    // list rides the EXISTING string crossing -- no new bridge entry point, no protocol bump.
    assertTrue(proxy.contains("_kanama_packed_args.append(_kanama_web_pack_text(tag))"), proxy)
    assertTrue(
      proxy.contains(
        "_kanama_packed_args.append(str(_kanama_web_pack_object(node, _kanama_packed_transient)))"
      ),
      proxy,
    )
    assertTrue(
      proxy.contains(
        "_kanama_bridge.callString(_kanama_handle, 7, \"\\u001f\".join(_kanama_packed_args))"
      ),
      proxy,
    )
    assertTrue(proxy.contains("_kanama_packed_args.append(str(id))"), proxy)
    assertFalse(proxy.contains("unsupportedGameplayMethod(_KANAMA_SCRIPT_ID, 7,"), proxy)
    assertFalse(proxy.contains("unsupportedGameplayMethod(_KANAMA_SCRIPT_ID, 8,"), proxy)

    // Every transient handle the call minted is erased and released after the crossing.
    assertTrue(proxy.contains("for _kanama_packed_handle in _kanama_packed_transient:"), proxy)
    assertTrue(
      proxy.contains("\t\t_kanama_bridge.releaseTransientObjectHandle(_kanama_packed_handle)"),
      proxy,
    )

    // The helpers: the escape must be the SAME spelling the generic-call transport uses, and a
    // Kanama-scripted argument must cross as its script handle so Kotlin can resolve it.
    assertTrue(proxy.contains("func _kanama_web_pack_text(value: String) -> String:"), proxy)
    assertTrue(
      proxy.contains("\treturn value.replace(\"%\", \"%25\").replace(\"\\u001f\", \"%1F\")"),
      proxy,
    )
    assertTrue(
      proxy.contains(
        "func _kanama_web_pack_object(value: Object, transient_handles: Array[int]) -> int:"
      ),
      proxy,
    )
    assertTrue(proxy.contains("\tif value.has_method(\"_kanama_ensure_created\"):"), proxy)

    val registry =
      WebScriptCodeEmitter(listOf(WebScriptInput(task80Model(), "res://Enemy.kt"))).registrySource()
    // The Kotlin side splits the same list and unescapes with the mirror of the GDScript packer.
    assertTrue(registry.contains("val packedArgs = value.split('\\u001F')"), registry)
    assertTrue(
      registry.contains(
        "(script as Enemy).retarget(packedArgs[0].replace(\"%1F\", \"\\u001F\")" +
          ".replace(\"%25\", \"%\"), net.multigesture.kanama.api.GodotObject(checkNotNull(" +
          "packedArgs[1].toInt().takeIf { it != 0 }?.let { WebObjectId(it) }) " +
          "{ \"Argument node is not nullable\" }))"
      ),
      registry,
    )
    // A nullable object argument delivers null for the 0 handle instead of throwing.
    assertTrue(
      registry.contains(
        "(script as Enemy).addPlayer(packedArgs[0].toLong(), packedArgs[1].toInt()" +
          ".takeIf { it != 0 }?.let { WebObjectId(it) }?.let " +
          "{ net.multigesture.kanama.api.GodotObject(it) })"
      ),
      registry,
    )
  }

  @Test
  fun refusesFloatArgumentsOnThePackedArgumentCrossing() {
    // The boundary that keeps the packed list honest: GDScript's str() rounds a double to 14
    // significant digits, so a float here would arrive slightly WRONG. It has no arm and says so.
    fun armFor(vararg args: ArgModel) =
      WebScriptCodeEmitter.methodArm(
        MethodModel("probe", "probe", null, args.toList(), MethodKind.REGULAR)
      )
    val objectArg = ArgModel("node", TypeMapping.OBJECT, "net.multigesture.kanama.api.GodotObject")

    // Exactly-representable argument types ride the crossing...
    assertEquals(WebMethodArm.PACKED_ARGS, armFor(ArgModel("tag", TypeMapping.STRING), objectArg))
    assertEquals(WebMethodArm.PACKED_ARGS, armFor(ArgModel("id", TypeMapping.INT), objectArg))
    assertEquals(WebMethodArm.PACKED_ARGS, armFor(ArgModel("on", TypeMapping.BOOL), objectArg))
    assertEquals(
      WebMethodArm.PACKED_ARGS,
      armFor(ArgModel("path", TypeMapping.NODE_PATH), ArgModel("tag", TypeMapping.STRING)),
    )
    // ... a float mixed with them does not, and neither does an unwrapped object.
    val lossy =
      MethodModel(
        "probe",
        "probe",
        null,
        listOf(ArgModel("tag", TypeMapping.STRING), ArgModel("amount", TypeMapping.FLOAT)),
        MethodKind.REGULAR,
      )
    assertEquals(WebMethodArm.NONE, WebScriptCodeEmitter.methodArm(lossy))
    assertTrue(
      WebScriptCodeEmitter.methodDispatch(lossy).reason!!.contains("(STRING, FLOAT) -> void"),
      WebScriptCodeEmitter.methodDispatch(lossy).reason!!,
    )
    assertEquals(
      WebMethodArm.NONE,
      armFor(ArgModel("tag", TypeMapping.STRING), ArgModel("v", TypeMapping.VECTOR3)),
    )
    assertEquals(
      WebMethodArm.NONE,
      armFor(ArgModel("tag", TypeMapping.STRING), ArgModel("node", TypeMapping.OBJECT)),
    )
    // An all-numeric list keeps its own exact crossing rather than falling through to this one.
    assertEquals(
      WebMethodArm.NUMERIC_VOID,
      armFor(ArgModel("id", TypeMapping.INT), ArgModel("amount", TypeMapping.FLOAT)),
    )
    assertFalse(WebScriptCodeEmitter.isPackedArgList(emptyList()))
  }

  // ---------- Task 80 slice 3: the gate ----------

  @Test
  fun failsTheBuildForAnyMemberThatDoesNotDispatchTyped() {
    val errors = WebScriptCodeEmitter.undispatchedMemberErrors(task80Model(), webOptions)

    // Exactly the census population -- no more (a typed member must never fail a build) and no
    // fewer (an allowlist would just be a place for the next degradation to hide).
    assertEquals(2, errors.size, errors.toString())
    val method = errors.single { it.contains("(registered function)") }
    assertTrue(method.startsWith("Enemy.reload (registered function):"), method)
    assertTrue(method.contains("(FLOAT) -> INT"), method)
    assertTrue(method.contains("the proxy emits a stub that throws"), method)
    // Naming the fix is the difference between a gate and a wall.
    assertTrue(method.contains("all-numeric argument list up to 6 scalar slots"), method)

    val signal = errors.single { it.contains("(signal)") }
    assertTrue(signal.startsWith("Enemy.scored (signal):"), signal)
    assertTrue(signal.contains("at most 1 emitted argument"), signal)

    // The gate is Web-only: the same declarations dispatch normally everywhere else.
    assertTrue(WebScriptCodeEmitter.undispatchedMemberErrors(task80Model(), emptyMap()).isEmpty())
    assertTrue(
      WebScriptCodeEmitter.undispatchedMemberErrors(
          task80Model(),
          mapOf("kanamaRuntimeTarget" to "ios"),
        )
        .isEmpty()
    )
  }

  @Test
  fun passesTheGateForAFullyTypedScript() {
    val typed = task80TypedModel()
    assertEquals(emptyList(), WebScriptCodeEmitter.undispatchedMemberErrors(typed, webOptions))
    assertEquals(
      emptyList(),
      WebScriptCodeEmitter(listOf(WebScriptInput(typed, "res://Enemy.kt"))).degradations(),
    )
  }

  @Test
  fun failsTheBuildWhenAPropertyOrVirtualGuardHasAHole() {
    // Properties and virtuals have their own guards; the gate re-checks their manifest verdict so
    // a hole in either one cannot ship as a silently non-typed manifest entry.
    val holed =
      task80TypedModel()
        .copy(
          properties =
            listOf(
              ScriptPropertyModel(
                kotlinName = "config",
                godotName = "config",
                type = TypeMapping.DICTIONARY,
                isMutable = true,
              )
            ),
          virtuals = listOf(VirtualModel("_notification", "callNotification", "notification")),
        )
    val errors = WebScriptCodeEmitter.undispatchedMemberErrors(holed, webOptions)
    assertEquals(2, errors.size, errors.toString())
    assertTrue(
      errors.any {
        it.startsWith("Enemy.config (property)") &&
          it.contains("unsupportedWebPropertyErrors has a hole")
      },
      errors.toString(),
    )
    assertTrue(
      errors.any {
        it.startsWith("Enemy.notification (virtual)") &&
          it.contains("undispatchedVirtualErrors has a hole")
      },
      errors.toString(),
    )
  }

  @Test
  fun writesDispatchVerdictsIntoTheProtocolManifest() {
    val emitter = WebScriptCodeEmitter(listOf(WebScriptInput(task80Model(), "res://Enemy.kt")))
    val protocol = emitter.protocolManifest()

    // The manifest shape is unchanged by slice 2; the bridge contract is not, so the protocol
    // version moved and the schema version did not.
    assertTrue(protocol.contains("\"schemaVersion\": 2"), protocol)
    assertTrue(protocol.contains("\"protocolVersion\": 18"), protocol)

    // Every shape slice 2 filled must read typed IN THE MANIFEST, not just in the arm table.
    assertTrue(
      protocol.contains(
        "\"name\": \"damage\", \"arguments\": [{\"name\": \"amount\", " +
          "\"type\": \"Double\", \"nullable\": false, \"hasDefault\": false}], " +
          "\"returnType\": null, \"dispatch\": \"typed\"}"
      ),
      protocol,
    )
    assertTrue(
      protocol.contains("\"name\": \"knockback\"") &&
        protocol.contains("\"returnType\": null, \"dispatch\": \"typed\"}"),
      protocol,
    )
    assertTrue(
      protocol.contains(
        "\"name\": \"aim_target\", \"arguments\": [], " +
          "\"returnType\": \"net.multigesture.kanama.types.Vector3\", \"dispatch\": \"typed\"}"
      ),
      protocol,
    )
    assertTrue(
      protocol.contains(
        "\"name\": \"current_health\", \"arguments\": [], \"returnType\": \"Double\", " +
          "\"dispatch\": \"typed\"}"
      ),
      protocol,
    )
    assertTrue(
      protocol.contains(
        "\"name\": \"die\", \"arguments\": [], \"returnType\": null, \"dispatch\": \"typed\"}"
      ),
      protocol,
    )
    assertTrue(
      protocol.contains("\"name\": \"died\", \"arguments\": [], \"dispatch\": \"typed\"}"),
      protocol,
    )
    // The scalar signal payload is delivered now; only the two-argument one still drops.
    assertTrue(
      protocol.contains("\"name\": \"hurt\"") &&
        protocol.contains(
          "{\"name\": \"amount\", \"type\": \"Double\", \"nullable\": false, " +
            "\"hasDefault\": false}], \"dispatch\": \"typed\"}"
        ),
      protocol,
    )
    assertTrue(
      protocol.contains("\"name\": \"scored\"") &&
        protocol.contains("\"dispatch\": \"argument-dropped\", \"dispatchReason\": \"signal "),
      protocol,
    )
    // Slice 3 filled the two mixed-channel shapes; both now read typed IN THE MANIFEST.
    assertTrue(
      protocol.contains("\"name\": \"retarget\"") &&
        protocol.contains(
          "{\"name\": \"node\", \"type\": \"net.multigesture.kanama.api.GodotObject\", " +
            "\"nullable\": false, \"hasDefault\": false}], \"returnType\": null, " +
            "\"dispatch\": \"typed\"}"
        ),
      protocol,
    )
    assertTrue(
      protocol.contains("\"name\": \"add_player\"") &&
        protocol.contains(
          "{\"name\": \"spawnPoint\", \"type\": \"net.multigesture.kanama.api.GodotObject\", " +
            "\"nullable\": true, \"hasDefault\": false}], \"returnType\": null, " +
            "\"dispatch\": \"typed\"}"
        ),
      protocol,
    )
    // A shape with no arm is still declared, with its reason.
    assertTrue(
      protocol.contains(
        "\"dispatch\": \"unsupported\", \"dispatchReason\": \"no arm for the registered-method " +
          "shape (FLOAT) -> INT; the proxy emits a stub that throws\""
      ),
      protocol,
    )
    // Properties and dispatched virtuals are guarded elsewhere (#148 / 66a) and must read typed.
    assertTrue(protocol.contains("\"name\": \"health\""), protocol)
    assertFalse(
      protocol.contains("\"dispatch\": \"not-emitted\""),
      "no property or virtual in this fixture may be not-emitted",
    )

    // A typed entry carries no dispatchReason at all, so its absence means "no degradation".
    assertEquals(
      2,
      Regex("\"dispatchReason\"").findAll(protocol).count(),
      "only the two remaining degraded entries may carry a reason",
    )
  }

  @Test
  fun reportsEveryNonTypedEntryWithCounts() {
    val emitter = WebScriptCodeEmitter(listOf(WebScriptInput(task80Model(), "res://Enemy.kt")))

    val degradations = emitter.degradations()
    assertEquals(2, degradations.size, degradations.toString())
    assertEquals(
      listOf("Enemy.reload (method)", "Enemy.scored (signal)"),
      degradations.map { "${it.scriptName}.${it.memberName} (${it.kind})" },
    )
    // 1 property + 1 virtual + 9 methods + 3 signals.
    assertEquals(14, emitter.memberCount())

    val report = emitter.degradationReport()
    assertTrue(
      report.first().contains("2 of 14 declared member(s) across 1 script(s)"),
      report.first(),
    )
    assertTrue(report.first().contains("method 1, signal 1, property 0, virtual 0"), report.first())
    assertTrue(report.any { it.contains("Enemy.reload (method): no arm for") }, report.toString())
    assertTrue(
      report.any { it.contains("Enemy.scored (signal): signal payload dropped") },
      report.toString(),
    )
    // Since slice 3 the census population is fatal, and the report says so.
    assertTrue(report.any { it.contains("FAIL the build (task 80 slice 3)") }, report.toString())

    // The census and the gate read one table: the same two members, no more and no fewer.
    assertEquals(
      degradations.map { "${it.scriptName}.${it.memberName}" },
      WebScriptCodeEmitter.undispatchedMemberErrors(task80Model(), webOptions).map {
        it.substringBefore(" (")
      },
    )
    // The older per-kind guards stay quiet here: this script's degradations are a method and a
    // signal, which is exactly the population they never covered.
    assertTrue(
      WebScriptCodeEmitter.unsupportedWebPropertyErrors(task80Model(), webOptions).isEmpty()
    )
    assertTrue(WebScriptCodeEmitter.undispatchedVirtualErrors(task80Model(), webOptions).isEmpty())
  }

  @Test
  fun reportsNothingForAFullyTypedScript() {
    val emitter = WebScriptCodeEmitter(listOf(WebScriptInput(task80TypedModel(), "res://Enemy.kt")))

    assertEquals(emptyList(), emitter.degradations())
    assertEquals(1, emitter.degradationReport().size, "a clean script reports only the summary")
    assertFalse(emitter.protocolManifest().contains("dispatchReason"))
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
