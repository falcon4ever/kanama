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

    assertTrue(source.contains("const val PROTOCOL_VERSION: Int = 6"))
    assertTrue(source.contains("1 -> FirstScript(WebObjectId(objectId))"))
    assertTrue(source.contains("2 -> SecondScript(WebObjectId(objectId))"))
    assertTrue(source.contains("WebMemberDescriptor(1, \"greeting\")"))
    assertTrue(source.contains("(script as FirstScript).process(delta)"))
    assertTrue(source.contains("fun draw(scriptId: Int, script: KanamaWebScript)"))
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
    val proxy = emitter.proxySources().single()

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
        .single()
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
        .single()
        .source
    assertTrue(parentProxy.contains("opcode == 43 and target_object is GPUParticles2D"))
  }

  @Test
  fun emitsExactAudioPlayerCommandDecodersInEveryProxy() {
    val proxy =
      WebScriptCodeEmitter(listOf(WebScriptInput(model("Main"), "res://kotlin-src/Main.kt")))
        .proxySources()
        .single()
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
        .single()
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

    val protocol = emitter.protocolManifest()
    assertTrue(protocol.contains("\"protocolVersion\": 6"))
    assertTrue(protocol.contains("\"attachTo\": \"Area2D\""))
    assertTrue(protocol.contains("\"type\": \"List<net.multigesture.kanama.api.Texture2D>\""))
    assertTrue(protocol.contains("\"type\": \"net.multigesture.kanama.types.Vector2i\""))
    assertTrue(protocol.contains("\"name\": \"_input_event\""))

    val constants = emitter.constantsSource()
    assertTrue(constants.contains("object TileSignals"))
    assertTrue(constants.contains("fun tilePressed("))
    assertTrue(constants.contains("const val setTileType: String = \"set_tile_type\""))
    assertTrue(emitter.compatibilitySources().containsKey("net.multigesture.kanama.demos.match3"))
    assertTrue(emitter.proxyManifest().startsWith("# kanama-web-protocol=6\n"))

    val registry = emitter.registrySource()
    assertTrue(registry.contains("(script as Main).width = value"))
    assertTrue(registry.contains("(script as Main).tileScene = handle?.let"))
    assertTrue(registry.contains("(script as Main).textures = values.map"))
    assertTrue(registry.contains("(script as Main).input("))
    assertTrue(registry.contains("(script as Main).onTilePressed(Vector2i(x, y))"))
    assertTrue(registry.contains("(script as Tile).inputEvent("))
  }
}
