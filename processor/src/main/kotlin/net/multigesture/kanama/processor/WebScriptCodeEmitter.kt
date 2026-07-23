package net.multigesture.kanama.processor

/** One Web script model paired with its Godot resource path. */
internal data class WebScriptInput(val model: ScriptModel, val resourcePath: String)

/** Static Kotlin/Wasm registry, protocol manifest, and Godot proxy emitter for Task 57. */
internal class WebScriptCodeEmitter(inputs: List<WebScriptInput>) {
  private val scripts = inputs.sortedWith(compareBy({ it.resourcePath }, { it.model.fqName }))

  companion object {
    const val PROTOCOL_VERSION = 6
    const val PROTOCOL_SCHEMA_VERSION = 1
  }

  data class ProxySource(
    val sourceResourcePath: String,
    val proxyResourcePath: String,
    val fileName: String,
    val source: String,
  )

  private data class ProtocolMethod(
    val name: String,
    val args: List<ArgModel>,
    val returnType: TypeMapping?,
  )

  fun proxySources(): List<ProxySource> =
    scripts.mapIndexed { index, input ->
      val fileName = input.model.simpleName
      ProxySource(
        sourceResourcePath = input.resourcePath,
        proxyResourcePath = "res://kanama-web/generated/$fileName.gd",
        fileName = fileName,
        source = proxySource(index + 1, input.model),
      )
    }

  fun proxyManifest(): String = buildString {
    appendLine("# kanama-web-protocol=$PROTOCOL_VERSION")
    appendLine("# source-resource\tproxy-resource\tgenerated-file")
    proxySources().forEach { proxy ->
      appendLine("${proxy.sourceResourcePath}\t${proxy.proxyResourcePath}\t${proxy.fileName}.gd")
    }
  }

  fun protocolManifest(): String = buildString {
    appendLine("{")
    appendLine("  \"schemaVersion\": $PROTOCOL_SCHEMA_VERSION,")
    appendLine("  \"protocolVersion\": $PROTOCOL_VERSION,")
    appendLine("  \"scripts\": [")
    scripts.forEachIndexed { scriptIndex, input ->
      val model = input.model
      appendLine("    {")
      appendLine("      \"id\": ${scriptIndex + 1},")
      appendLine("      \"resourcePath\": ${quote(input.resourcePath)},")
      appendLine("      \"className\": ${quote(model.fqName)},")
      appendLine("      \"attachTo\": ${quote(model.attachTo)},")
      appendLine("      \"properties\": [")
      model.properties.forEachIndexed { index, property ->
        append("        {\"id\": ${index + 1}, \"name\": ${quote(property.godotName)}, ")
        append("\"type\": ${quote(protocolPropertyType(property))}, ")
        append("\"nullable\": ${property.nullable}, \"hint\": ${property.hint}, ")
        append("\"hintString\": ${quote(property.hintString)}, \"usage\": ${property.usage}}")
        appendLine(if (index == model.properties.lastIndex) "" else ",")
      }
      appendLine("      ],")
      appendProtocolMethods(
        "virtuals",
        model.virtuals.map { virtual ->
          ProtocolMethod(virtual.virtualName, virtual.args, virtual.returnType)
        },
      )
      appendLine(",")
      appendProtocolMethods(
        "methods",
        model.methods.map { method ->
          ProtocolMethod(method.godotName, method.args, method.returnType)
        },
      )
      appendLine(",")
      appendLine("      \"signals\": [")
      model.signals.forEachIndexed { index, signal ->
        append("        {\"id\": ${index + 1}, \"name\": ${quote(signal.godotName)}, ")
        append("\"arguments\": ${protocolArgs(signal.args)}}")
        appendLine(if (index == model.signals.lastIndex) "" else ",")
      }
      appendLine("      ]")
      append("    }")
      appendLine(if (scriptIndex == scripts.lastIndex) "" else ",")
    }
    appendLine("  ]")
    appendLine("}")
  }

  fun constantsSource(): String = buildString {
    appendLine("package net.multigesture.kanama.generated")
    appendLine()
    appendLine("@Suppress(\"unused\")")
    appendLine(
      "private fun emitWebSignal(instance: Any, signalName: String, args: Array<out Any?>) {"
    )
    appendLine(
      "  val script = instance as? net.multigesture.kanama.api.KanamaScript<*> ?: error(\"Signal target is not a Kanama script\")"
    )
    appendLine(
      "  net.multigesture.kanama.api.GodotObject(script.godotObject).emitSignal(signalName, *args)"
    )
    appendLine("}")
    scripts.forEach { input ->
      val model = input.model
      if (model.signals.isNotEmpty()) {
        appendLine()
        appendLine("object ${model.simpleName}Signals {")
        model.signals.forEach { signal ->
          val params =
            signal.args.joinToString("") { arg ->
              ", ${constantIdentifier(arg.name)}: ${arg.kotlinType}"
            }
          val args = signal.args.joinToString(", ") { constantIdentifier(it.name) }
          appendLine(
            "  fun ${constantIdentifier(signal.godotName)}(instance: ${model.fqName}$params) {"
          )
          appendLine("    emitWebSignal(instance, ${quote(signal.godotName)}, arrayOf($args))")
          appendLine("  }")
        }
        appendLine("}")
      }
      if (
        model.methods.isNotEmpty() || model.properties.isNotEmpty() || model.signals.isNotEmpty()
      ) {
        appendLine()
        appendLine("object ${model.simpleName}Names {")
        if (model.methods.isNotEmpty()) {
          appendLine("  object Methods {")
          model.methods
            .distinctBy { constantIdentifier(it.kotlinName) }
            .forEach { method ->
              appendLine(
                "    const val ${constantIdentifier(method.kotlinName)}: String = ${quote(method.godotName)}"
              )
            }
          appendLine("  }")
        }
        if (model.properties.isNotEmpty()) {
          appendLine("  object Properties {")
          model.properties.forEach { property ->
            appendLine(
              "    const val ${constantIdentifier(property.kotlinName)}: String = ${quote(property.godotName)}"
            )
          }
          appendLine("  }")
        }
        if (model.signals.isNotEmpty()) {
          appendLine("  object Signals {")
          model.signals.forEach { signal ->
            appendLine(
              "    const val ${constantIdentifier(signal.godotName)}: String = ${quote(signal.godotName)}"
            )
          }
          appendLine("  }")
        }
        appendLine("}")
      }
    }
  }

  fun compatibilitySources(): Map<String, String> =
    scripts
      .map { it.model.fqName.substringBeforeLast('.', missingDelimiterValue = "") }
      .filter { it.isNotBlank() }
      .distinct()
      .sorted()
      .associateWith { packageName ->
        buildString {
          appendLine("package $packageName")
          appendLine()
          appendLine("@Suppress(\"unused\")")
          appendLine(
            "internal fun <T> MutableCollection<T>.removeIf(predicate: (T) -> Boolean): Boolean {"
          )
          appendLine("  val originalSize = size")
          appendLine("  removeAll(predicate)")
          appendLine("  return size != originalSize")
          appendLine("}")
          appendLine()
          appendLine("@Suppress(\"unused\")")
          appendLine("internal object System {")
          appendLine("  fun getenv(name: String): String? = null")
          appendLine("}")
        }
      }

  fun registrySource(): String = buildString {
    appendLine("// Generated by KanamaProcessor — do not edit.")
    appendLine("@file:OptIn(net.multigesture.kanama.backend.InternalKanamaBackendApi::class)")
    appendLine("package net.multigesture.kanama.web.generated")
    appendLine()
    appendLine("import net.multigesture.kanama.web.KanamaWebScript")
    appendLine("import net.multigesture.kanama.web.WebMemberDescriptor")
    appendLine("import net.multigesture.kanama.web.WebObjectId")
    appendLine("import net.multigesture.kanama.web.WebScriptDescriptor")
    appendLine("import net.multigesture.kanama.backend.GodotHandle")
    appendLine("import net.multigesture.kanama.types.Vector2i")
    scripts.forEach { appendLine("import ${it.model.fqName}") }
    appendLine()
    appendLine("object KanamaWebProjectRegistry {")
    appendLine("  const val PROTOCOL_VERSION: Int = $PROTOCOL_VERSION")
    appendLine()
    appendLine("  val scripts: List<WebScriptDescriptor> =")
    appendLine("    listOf(")
    scripts.forEachIndexed { index, input -> appendDescriptor(index + 1, input) }
    appendLine("    )")
    appendLine()
    appendLine("  fun create(scriptId: Int, objectId: Int): KanamaWebScript =")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { index, input ->
      appendLine("      ${index + 1} -> ${input.model.simpleName}(WebObjectId(objectId))")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine()
    appendReadyDispatcher()
    appendProcessDispatcher()
    appendDrawDispatcher()
    appendExitTreeDispatcher()
    appendInputDispatcher()
    appendNoArgsMethodDispatcher()
    appendStringPropertyGetter()
    appendStringPropertySetter()
    appendLongPropertySetter()
    appendObjectPropertySetter()
    appendObjectArrayPropertySetter()
    appendLongMethodDispatcher()
    appendVector2iMethodDispatcher()
    appendObjectObjectLongMethodDispatcher()
    appendLine("  private fun unknown(kind: String, id: Int): Nothing =")
    appendLine("    error(\"Unknown Kanama Web \$kind id=\$id\")")
    appendLine("}")
  }

  private fun StringBuilder.appendDescriptor(scriptId: Int, input: WebScriptInput) {
    val model = input.model
    appendLine("      WebScriptDescriptor(")
    appendLine("        id = $scriptId,")
    appendLine("        resourcePath = ${quote(input.resourcePath)},")
    appendLine("        className = ${quote(model.fqName)},")
    appendLine("        attachTo = ${quote(model.attachTo)},")
    appendMemberList("properties", model.properties.map { it.godotName })
    appendMemberList("virtuals", model.virtuals.map { it.virtualName })
    appendMemberList("methods", model.methods.map { it.godotName })
    appendMemberList("signals", model.signals.map { it.godotName })
    appendLine("      ),")
  }

  private fun StringBuilder.appendMemberList(label: String, names: List<String>) {
    val members =
      names.mapIndexed { index, name -> "WebMemberDescriptor(${index + 1}, ${quote(name)})" }
    appendLine("        $label = listOf(${members.joinToString(", ")}),")
  }

  private fun StringBuilder.appendReadyDispatcher() {
    appendLine("  fun ready(scriptId: Int, script: KanamaWebScript) {")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { index, input ->
      val ready = input.model.virtuals.firstOrNull { it.virtualName == "_ready" }
      val body =
        if (ready == null) "Unit"
        else "(script as ${input.model.simpleName}).${ready.kotlinMethodName}()"
      appendLine("      ${index + 1} -> $body")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendProcessDispatcher() {
    appendLine("  fun process(scriptId: Int, script: KanamaWebScript, delta: Double) {")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { index, input ->
      val process = input.model.virtuals.firstOrNull { it.virtualName == "_process" }
      val body =
        if (process == null) "Unit"
        else "(script as ${input.model.simpleName}).${process.kotlinMethodName}(delta)"
      appendLine("      ${index + 1} -> $body")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendDrawDispatcher() {
    appendLine("  fun draw(scriptId: Int, script: KanamaWebScript) {")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { index, input ->
      val virtual = input.model.virtuals.firstOrNull { it.virtualName == "_draw" }
      val method =
        input.model.methods.firstOrNull {
          it.godotName == "_draw" && it.args.isEmpty() && it.returnType == null
        }
      val body =
        when {
          virtual != null -> "(script as ${input.model.simpleName}).${virtual.kotlinMethodName}()"
          method != null -> "(script as ${input.model.simpleName}).${method.kotlinName}()"
          else -> "Unit"
        }
      appendLine("      ${index + 1} -> $body")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendExitTreeDispatcher() {
    appendLine("  fun exitTree(scriptId: Int, script: KanamaWebScript) {")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { index, input ->
      val exitTree = input.model.virtuals.firstOrNull { it.virtualName == "_exit_tree" }
      val body =
        if (exitTree == null) "Unit"
        else "(script as ${input.model.simpleName}).${exitTree.kotlinMethodName}()"
      appendLine("      ${index + 1} -> $body")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendInputDispatcher() {
    appendLine("  fun input(scriptId: Int, script: KanamaWebScript, eventHandle: Int) {")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { index, input ->
      val virtual = input.model.virtuals.firstOrNull { it.virtualName == "_input" }
      val argument = virtual?.args?.singleOrNull()
      val wrapper = argument?.objectWrapperFqName
      val body =
        if (virtual == null || argument?.type != TypeMapping.OBJECT || wrapper == null) "Unit"
        else
          "(script as ${input.model.simpleName}).${virtual.kotlinMethodName}($wrapper(GodotHandle.fromBackendToken(eventHandle.toLong())))"
      appendLine("      ${index + 1} -> $body")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendNoArgsMethodDispatcher() {
    appendLine("  fun callNoArgs(scriptId: Int, methodId: Int, script: KanamaWebScript) {")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (methodId) {")
      input.model.methods.forEachIndexed { methodIndex, method ->
        if (method.args.isEmpty() && method.returnType == null) {
          appendLine(
            "        ${methodIndex + 1} -> (script as ${input.model.simpleName}).${method.kotlinName}()"
          )
        }
      }
      appendLine("        else -> unknown(\"method\", methodId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendStringPropertyGetter() {
    appendLine(
      "  fun getStringProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript): String ="
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (propertyId) {")
      input.model.properties.forEachIndexed { propertyIndex, property ->
        if (property.type == TypeMapping.STRING) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName}"
          )
        }
      }
      appendLine("        else -> unknown(\"property\", propertyId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine()
  }

  private fun StringBuilder.appendStringPropertySetter() {
    appendLine(
      "  fun setStringProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript, value: String) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (propertyId) {")
      input.model.properties.forEachIndexed { propertyIndex, property ->
        if (property.type == TypeMapping.STRING && property.isMutable) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = value"
          )
        }
      }
      appendLine("        else -> unknown(\"property\", propertyId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendLongMethodDispatcher() {
    appendLine(
      "  fun callLong(scriptId: Int, methodId: Int, script: KanamaWebScript, value: Long): Long ="
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (methodId) {")
      input.model.methods.forEachIndexed { methodIndex, method ->
        if (
          method.returnType == TypeMapping.INT &&
            method.args.size == 1 &&
            method.args.single().type == TypeMapping.INT
        ) {
          appendLine(
            "        ${methodIndex + 1} -> (script as ${input.model.simpleName}).${method.kotlinName}(value)"
          )
        }
      }
      appendLine("        else -> unknown(\"method\", methodId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine()
  }

  private fun StringBuilder.appendVector2iMethodDispatcher() {
    appendLine(
      "  fun callVector2i(scriptId: Int, methodId: Int, script: KanamaWebScript, x: Int, y: Int) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (methodId) {")
      input.model.methods.forEachIndexed { methodIndex, method ->
        if (
          method.returnType == null &&
            method.args.size == 1 &&
            method.args.single().type == TypeMapping.VECTOR2I
        ) {
          appendLine(
            "        ${methodIndex + 1} -> (script as ${input.model.simpleName}).${method.kotlinName}(Vector2i(x, y))"
          )
        }
      }
      appendLine("        else -> unknown(\"method\", methodId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendObjectObjectLongMethodDispatcher() {
    appendLine(
      "  fun callObjectObjectLong(scriptId: Int, methodId: Int, script: KanamaWebScript, firstHandle: Int, secondHandle: Int, value: Long) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (methodId) {")
      input.model.methods.forEachIndexed { methodIndex, method ->
        val first = method.args.getOrNull(0)
        val second = method.args.getOrNull(1)
        val third = method.args.getOrNull(2)
        val firstWrapper = first?.objectWrapperFqName
        val secondWrapper = second?.objectWrapperFqName
        if (
          method.returnType == null &&
            method.args.size == 3 &&
            first?.type == TypeMapping.OBJECT &&
            second?.type == TypeMapping.OBJECT &&
            third?.type == TypeMapping.INT &&
            firstWrapper != null &&
            secondWrapper != null
        ) {
          appendLine(
            "        ${methodIndex + 1} -> (script as ${input.model.simpleName}).${method.kotlinName}($firstWrapper(GodotHandle.fromBackendToken(firstHandle.toLong())), $secondWrapper(GodotHandle.fromBackendToken(secondHandle.toLong())), value)"
          )
        }
      }
      appendLine("        else -> unknown(\"method\", methodId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendLongPropertySetter() {
    appendLine(
      "  fun setLongProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript, value: Long) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (propertyId) {")
      input.model.properties.forEachIndexed { propertyIndex, property ->
        if (property.type == TypeMapping.INT && property.isMutable) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = value"
          )
        }
      }
      appendLine("        else -> unknown(\"property\", propertyId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendObjectPropertySetter() {
    appendLine(
      "  fun setObjectProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript, value: Int) {"
    )
    appendLine(
      "    val handle = value.takeIf { it != 0 }?.let { GodotHandle.fromBackendToken(it.toLong()) }"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (propertyId) {")
      input.model.properties.forEachIndexed { propertyIndex, property ->
        val wrapper = property.objectWrapperFqName
        if (property.type == TypeMapping.OBJECT && property.isMutable && wrapper != null) {
          val wrapped =
            if (property.nullable) "handle?.let { $wrapper(it) }"
            else
              "$wrapper(checkNotNull(handle) { \"Property ${property.godotName} is not nullable\" })"
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = $wrapped"
          )
        }
      }
      appendLine("        else -> unknown(\"property\", propertyId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendObjectArrayPropertySetter() {
    appendLine(
      "  fun setObjectArrayProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript, values: IntArray) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (propertyId) {")
      input.model.properties.forEachIndexed { propertyIndex, property ->
        val wrapper = property.arrayElementWrapperFqName
        if (property.type == TypeMapping.ARRAY && property.isMutable && wrapper != null) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = values.map { $wrapper(GodotHandle.fromBackendToken(it.toLong())) }"
          )
        }
      }
      appendLine("        else -> unknown(\"property\", propertyId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun proxySource(scriptId: Int, model: ScriptModel): String = buildString {
    val node2dAttachment = model.attachTo in setOf("Node2D", "Area2D", "Sprite2D", "GPUParticles2D")
    val particlesAttachment = model.attachTo == "GPUParticles2D"
    appendLine("# Generated by KanamaProcessor — do not edit.")
    appendLine("extends ${model.attachTo}")
    appendLine()
    model.signals.forEach { signal ->
      val args = signal.args.joinToString(", ") { "${it.name}: ${gdType(it)}" }
      appendLine("signal ${signal.godotName}($args)")
    }
    if (model.signals.isNotEmpty()) appendLine()
    model.properties.forEach { property ->
      appendPropertyGroup(property)
      appendLine("@export var ${property.godotName}: ${gdType(property)} = ${gdDefault(property)}")
    }

    if (model.attachTo == "Resource") return@buildString

    appendLine()
    appendLine("const _KANAMA_SCRIPT_ID: int = $scriptId")
    appendLine("const _KANAMA_PROTOCOL_VERSION: int = $PROTOCOL_VERSION")
    appendLine("var _kanama_bridge")
    appendLine("var _kanama_handle: int = 0")
    appendLine("var _kanama_apply_callback")
    appendLine("var _kanama_immediate_callback")
    appendLine("var _kanama_resource_callback")
    appendLine("var _kanama_signal_callback")
    appendLine("var _kanama_resource_release_callback")
    appendLine("var _kanama_construct_callback")
    appendLine("var _kanama_node_lookup_callback")
    appendLine("var _kanama_packed_scene_callback")
    appendLine("var _kanama_noargs_object_callback")
    appendLine("var _kanama_input_cursor_callback")
    appendLine("var _kanama_connect_callback")
    appendLine("var _kanama_object_query_callback")
    appendLine("var _kanama_noargs_vector2_callback")
    appendLine("var _kanama_signal_vector2i_callback")
    appendLine("var _kanama_tween_callback")
    appendLine("var _kanama_ready_dispatched: bool = false")
    appendLine("var _kanama_object_handles: Dictionary = {}")
    appendLine("var _kanama_tween_children: Dictionary = {}")
    appendLine("var _kanama_tween_targets: Dictionary = {}")
    appendLine()
    appendLine("func _kanama_ensure_created() -> int:")
    appendLine("\tif not OS.has_feature(\"web\"):")
    appendLine("\t\treturn 0")
    appendLine("\tif _kanama_handle != 0:")
    appendLine("\t\treturn _kanama_handle")
    appendLine("\t_kanama_bridge = JavaScriptBridge.get_interface(\"KanamaWebBridge\")")
    appendLine("\tif _kanama_bridge == null:")
    appendLine("\t\tpush_error(\"Kanama Web bridge was not initialized before Godot\")")
    appendLine("\t\treturn 0")
    appendLine("\tif int(_kanama_bridge.protocolVersion) != _KANAMA_PROTOCOL_VERSION:")
    appendLine(
      "\t\tpush_error(\"Kanama Web proxy protocol mismatch: expected %d, received %d\" % [_KANAMA_PROTOCOL_VERSION, int(_kanama_bridge.protocolVersion)])"
    )
    appendLine("\t\treturn 0")
    appendLine("\t_kanama_handle = int(_kanama_bridge.create(_KANAMA_SCRIPT_ID))")
    appendLine("\tif _kanama_handle == 0:")
    appendLine("\t\tpush_error(\"Kanama Web script construction failed\")")
    appendLine("\t\treturn 0")
    appendLine(
      "\t_kanama_apply_callback = JavaScriptBridge.create_callback(_kanama_apply_commands)"
    )
    appendLine(
      "\t_kanama_immediate_callback = JavaScriptBridge.create_callback(_kanama_immediate_call)"
    )
    appendLine(
      "\t_kanama_resource_callback = JavaScriptBridge.create_callback(_kanama_resource_load)"
    )
    appendLine("\t_kanama_signal_callback = JavaScriptBridge.create_callback(_kanama_signal_emit)")
    appendLine(
      "\t_kanama_resource_release_callback = JavaScriptBridge.create_callback(_kanama_resource_release)"
    )
    appendLine(
      "\t_kanama_construct_callback = JavaScriptBridge.create_callback(_kanama_construct_object)"
    )
    appendLine(
      "\t_kanama_node_lookup_callback = JavaScriptBridge.create_callback(_kanama_node_lookup)"
    )
    appendLine(
      "\t_kanama_packed_scene_callback = JavaScriptBridge.create_callback(_kanama_packed_scene_instantiate)"
    )
    appendLine(
      "\t_kanama_noargs_object_callback = JavaScriptBridge.create_callback(_kanama_noargs_object)"
    )
    appendLine(
      "\t_kanama_input_cursor_callback = JavaScriptBridge.create_callback(_kanama_input_cursor)"
    )
    appendLine("\t_kanama_connect_callback = JavaScriptBridge.create_callback(_kanama_connect)")
    appendLine(
      "\t_kanama_object_query_callback = JavaScriptBridge.create_callback(_kanama_object_query)"
    )
    appendLine(
      "\t_kanama_noargs_vector2_callback = JavaScriptBridge.create_callback(_kanama_noargs_vector2)"
    )
    appendLine(
      "\t_kanama_signal_vector2i_callback = JavaScriptBridge.create_callback(_kanama_signal_emit_vector2i)"
    )
    appendLine("\t_kanama_tween_callback = JavaScriptBridge.create_callback(_kanama_tween_call)")
    appendLine("\t_kanama_bridge.installProxyCallbacks(")
    appendLine("\t\t_kanama_handle,")
    appendLine("\t\t_kanama_apply_callback,")
    appendLine("\t\t_kanama_immediate_callback,")
    appendLine("\t\t_kanama_resource_callback,")
    appendLine("\t\t_kanama_signal_callback,")
    appendLine("\t\t_kanama_resource_release_callback,")
    appendLine("\t\t_kanama_construct_callback,")
    appendLine("\t\t_kanama_node_lookup_callback,")
    appendLine("\t\t_kanama_packed_scene_callback,")
    appendLine("\t\t_kanama_noargs_object_callback,")
    appendLine("\t\t_kanama_input_cursor_callback,")
    appendLine("\t\t_kanama_connect_callback,")
    appendLine("\t\t_kanama_object_query_callback,")
    appendLine("\t\t_kanama_noargs_vector2_callback,")
    appendLine("\t\t_kanama_signal_vector2i_callback,")
    appendLine("\t\t_kanama_tween_callback)")
    if (node2dAttachment) {
      appendLine("\tvar target: Node2D = self")
      appendLine(
        "\t_kanama_bridge.refreshNode2DSnapshot(_kanama_handle, target.position.x, target.position.y, target.scale.x, target.scale.y, target.modulate.r, target.modulate.g, target.modulate.b, target.modulate.a)"
      )
    }
    if (particlesAttachment) {
      appendLine("\t_kanama_bridge.refreshParticlesSnapshot(_kanama_handle, emitting, lifetime)")
    }
    model.properties.forEachIndexed { index, property ->
      when (property.type) {
        TypeMapping.STRING ->
          appendLine(
            "\t_kanama_bridge.setStringProperty(_kanama_handle, ${index + 1}, ${property.godotName})"
          )
        TypeMapping.INT ->
          appendLine(
            "\t_kanama_bridge.setLongProperty(_kanama_handle, ${index + 1}, ${property.godotName})"
          )
        TypeMapping.OBJECT -> {
          appendLine("\tvar property_handle_${index + 1}: int = 0")
          appendLine("\tif ${property.godotName} != null:")
          appendLine(
            "\t\tproperty_handle_${index + 1} = int(_kanama_bridge.allocateBrowserHandle(\"Resource\", _kanama_handle))"
          )
          appendLine(
            "\t\t_kanama_object_handles[property_handle_${index + 1}] = ${property.godotName}"
          )
          appendLine(
            "\t_kanama_bridge.setObjectProperty(_kanama_handle, ${index + 1}, property_handle_${index + 1})"
          )
        }
        TypeMapping.ARRAY -> {
          appendLine("\tvar property_handles_${index + 1}: String = \"\"")
          appendLine("\tfor property_value in ${property.godotName}:")
          appendLine(
            "\t\tvar property_value_handle := int(_kanama_bridge.allocateBrowserHandle(\"Resource\", _kanama_handle))"
          )
          appendLine("\t\t_kanama_object_handles[property_value_handle] = property_value")
          appendLine(
            "\t\tproperty_handles_${index + 1} += (\",\" if not property_handles_${index + 1}.is_empty() else \"\") + str(property_value_handle)"
          )
          appendLine(
            "\t_kanama_bridge.setObjectArrayProperty(_kanama_handle, ${index + 1}, property_handles_${index + 1})"
          )
        }
        else -> Unit
      }
    }
    appendLine("\treturn _kanama_handle")
    appendLine()
    appendLine("func _ready() -> void:")
    appendLine("\tif _kanama_ensure_created() == 0 or _kanama_ready_dispatched:")
    appendLine("\t\treturn")
    appendLine("\t_kanama_ready_dispatched = true")
    if (node2dAttachment) {
      appendLine("\tvar target: Node2D = self")
      appendLine("\tvar viewport_rect := target.get_viewport_rect()")
      appendLine(
        "\t_kanama_bridge.refreshViewportRectSnapshot(_kanama_handle, viewport_rect.position.x, viewport_rect.position.y, viewport_rect.size.x, viewport_rect.size.y)"
      )
    }
    appendLine("\tif _kanama_bridge.shouldDeferReady(${quote(model.fqName)}):")
    appendLine("\t\t_kanama_bridge.recordDeferredReady(${quote(model.fqName)})")
    appendLine(
      "\t\t_kanama_bridge.recordReady(_kanama_handle, _KANAMA_SCRIPT_ID, ${quote(model.fqName)})"
    )
    appendLine("\t\treturn")
    appendLine("\t_kanama_bridge.ready(_kanama_handle)")
    val immediateMethod =
      model.methods.withIndex().firstOrNull { (_, method) ->
        method.returnType == TypeMapping.INT &&
          method.args.size == 1 &&
          method.args.single().type == TypeMapping.INT
      }
    if (immediateMethod != null) {
      appendLine(
        "\tvar immediate_result := int(_kanama_bridge.callInt(_kanama_handle, ${immediateMethod.index + 1}, 47))"
      )
      appendLine("\t_kanama_bridge.recordImmediateResult(immediate_result)")
    }
    appendLine(
      "\t_kanama_bridge.recordReady(_kanama_handle, _KANAMA_SCRIPT_ID, ${quote(model.fqName)})"
    )
    appendLine()
    appendLine("func _process(delta: float) -> void:")
    appendLine("\tif _kanama_handle != 0:")
    if (node2dAttachment) {
      appendLine("\t\tvar target: Node2D = self")
      appendLine("\t\tvar viewport_rect := target.get_viewport_rect()")
      appendLine(
        "\t\t_kanama_bridge.refreshViewportRectSnapshot(_kanama_handle, viewport_rect.position.x, viewport_rect.position.y, viewport_rect.size.x, viewport_rect.size.y)"
      )
    }
    if (particlesAttachment) {
      appendLine("\t\t_kanama_bridge.refreshParticlesSnapshot(_kanama_handle, emitting, lifetime)")
    }
    appendLine("\t\t_kanama_bridge.frame(_kanama_handle, delta)")
    if (model.virtuals.any { it.virtualName == "_input" }) {
      appendLine()
      appendLine("func _input(event: InputEvent) -> void:")
      appendLine("\tif _kanama_handle != 0:")
      appendLine(
        "\t\tvar event_handle := int(_kanama_bridge.allocateTransientObjectHandle(_kanama_handle))"
      )
      appendLine("\t\t_kanama_object_handles[event_handle] = event")
      appendLine("\t\t_kanama_bridge.input(_kanama_handle, event_handle)")
      appendLine("\t\t_kanama_object_handles.erase(event_handle)")
      appendLine("\t\t_kanama_bridge.releaseTransientObjectHandle(event_handle)")
    }
    appendLine()
    appendLine("func _draw() -> void:")
    appendLine("\tif _kanama_handle != 0:")
    appendLine("\t\t_kanama_bridge.draw(_kanama_handle)")
    appendLine()
    appendLine("func _exit_tree() -> void:")
    appendLine("\tif _kanama_handle == 0:")
    appendLine("\t\t_kanama_clear_callbacks()")
    appendLine("\t\treturn")
    appendLine("\tvar freed_handle := _kanama_handle")
    appendLine("\t_kanama_bridge.free(_kanama_handle)")
    appendLine("\t_kanama_clear_callbacks()")
    appendLine("\t_kanama_handle = 0")
    appendLine("\t_kanama_object_handles.clear()")
    appendLine("\t_kanama_tween_children.clear()")
    appendLine("\t_kanama_tween_targets.clear()")
    appendLine("\t_kanama_bridge.recordFreed(freed_handle)")
    appendLine()
    appendLine("func _kanama_clear_callbacks() -> void:")
    appendLine("\tif _kanama_bridge == null:")
    appendLine("\t\treturn")
    appendLine("\tif _kanama_handle != 0:")
    appendLine("\t\t_kanama_bridge.clearProxyCallbacks(_kanama_handle)")
    appendLine("\t_kanama_apply_callback = null")
    appendLine("\t_kanama_immediate_callback = null")
    appendLine("\t_kanama_resource_callback = null")
    appendLine("\t_kanama_signal_callback = null")
    appendLine("\t_kanama_resource_release_callback = null")
    appendLine("\t_kanama_construct_callback = null")
    appendLine("\t_kanama_node_lookup_callback = null")
    appendLine("\t_kanama_packed_scene_callback = null")
    appendLine("\t_kanama_noargs_object_callback = null")
    appendLine("\t_kanama_input_cursor_callback = null")
    appendLine("\t_kanama_connect_callback = null")
    appendLine("\t_kanama_object_query_callback = null")
    appendLine("\t_kanama_noargs_vector2_callback = null")
    appendLine("\t_kanama_signal_vector2i_callback = null")
    appendLine("\t_kanama_tween_callback = null")
    appendLine()
    appendLine("func _kanama_apply_commands(args: Array) -> int:")
    appendLine(
      "\tvar bytes: PackedByteArray = JavaScriptBridge.js_buffer_to_packed_byte_array(args[0])"
    )
    appendLine("\tvar command_count := int(args[1])")
    appendLine("\tvar applied := 0")
    appendLine("\tvar last_value := 0")
    appendLine("\tvar offset := 0")
    appendLine("\tfor command_index in range(command_count):")
    appendLine("\t\tvar opcode := bytes.decode_s32(offset)")
    appendLine("\t\tvar object_handle := bytes.decode_s32(offset + 4)")
    appendLine(
      "\t\tvar target_object: Object = self if object_handle == _kanama_handle else _kanama_object_handles.get(object_handle)"
    )
    appendLine("\t\tif opcode == 100 and object_handle == _kanama_handle:")
    appendLine("\t\t\tlast_value = bytes.decode_s32(offset + 8)")
    appendLine("\t\t\tset_meta(\"kanama_web_scalar\", last_value)")
    if (node2dAttachment) {
      appendLine("\t\t\tvar target: Node2D = self")
      appendLine("\t\t\ttarget.position = Vector2(float(last_value % 640), target.position.y)")
    }
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 3 and target_object is Node2D:")
    appendLine("\t\t\tvar target := target_object as Node2D")
    appendLine("\t\t\tvar position_x := bytes.decode_float(offset + 8)")
    appendLine("\t\t\tvar position_y := bytes.decode_float(offset + 12)")
    appendLine("\t\t\ttarget.position = Vector2(position_x, position_y)")
    appendLine("\t\t\tlast_value = int(position_x)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 30 and target_object is Node2D:")
    appendLine("\t\t\tvar target := target_object as Node2D")
    appendLine(
      "\t\t\ttarget.scale = Vector2(bytes.decode_float(offset + 8), bytes.decode_float(offset + 12))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 32 and target_object is CanvasItem:")
    appendLine("\t\t\tvar target := target_object as CanvasItem")
    appendLine(
      "\t\t\ttarget.modulate = Color(bytes.decode_float(offset + 8), bytes.decode_float(offset + 12), bytes.decode_float(offset + 16), bytes.decode_float(offset + 20))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 24")
    appendLine("\t\telif opcode == 43 and target_object is GPUParticles2D:")
    appendLine(
      "\t\t\t(target_object as GPUParticles2D).emitting = bytes.decode_s32(offset + 8) != 0"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 46 and target_object is AudioStreamPlayer:")
    appendLine("\t\t\tvar stream_handle := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\tvar stream: AudioStream = null if stream_handle == 0 else _kanama_object_handles.get(stream_handle) as AudioStream"
    )
    appendLine("\t\t\tif stream_handle != 0 and stream == null:")
    appendLine("\t\t\t\tpush_error(\"Unknown Kanama Web AudioStream handle: %d\" % stream_handle)")
    appendLine("\t\t\t\tbreak")
    appendLine("\t\t\t(target_object as AudioStreamPlayer).set_stream(stream)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 47 and target_object is AudioStreamPlayer:")
    appendLine("\t\t\tvar bus_id := bytes.decode_s32(offset + 8)")
    appendLine("\t\t\tvar bus_name := String(_kanama_bridge.resolveCommandStringName(bus_id))")
    appendLine("\t\t\t(target_object as AudioStreamPlayer).set_bus(StringName(bus_name))")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 48 and target_object is AudioStreamPlayer:")
    appendLine(
      "\t\t\t(target_object as AudioStreamPlayer).set_volume_db(bytes.decode_double(offset + 8))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 49 and target_object is AudioStreamPlayer:")
    appendLine(
      "\t\t\t(target_object as AudioStreamPlayer).set_pitch_scale(bytes.decode_double(offset + 8))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 50 and target_object is AudioStreamPlayer:")
    appendLine("\t\t\t(target_object as AudioStreamPlayer).play(bytes.decode_double(offset + 8))")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 52 and target_object is SceneTree:")
    appendLine("\t\t\t(target_object as SceneTree).quit(bytes.decode_s32(offset + 8))")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    if (node2dAttachment) {
      appendLine("\t\telif opcode == 5 and object_handle == _kanama_handle:")
      appendLine("\t\t\tvar canvas_target: CanvasItem = self")
      appendLine("\t\t\tcanvas_target.queue_redraw()")
      appendLine("\t\t\tapplied += 1")
      appendLine("\t\t\toffset += 8")
      appendLine("\t\telif opcode == 6 and object_handle == _kanama_handle:")
      appendLine("\t\t\tvar texture_handle := bytes.decode_s32(offset + 8)")
      appendLine("\t\t\tvar texture := _kanama_object_handles.get(texture_handle) as Texture2D")
      appendLine("\t\t\tif texture == null:")
      appendLine("\t\t\t\tpush_error(\"Unknown Kanama Web texture handle: %d\" % texture_handle)")
      appendLine("\t\t\t\tbreak")
      appendLine(
        "\t\t\tvar draw_position := Vector2(bytes.decode_float(offset + 12), bytes.decode_float(offset + 16))"
      )
      appendLine(
        "\t\t\tvar modulate := Color(bytes.decode_float(offset + 20), bytes.decode_float(offset + 24), bytes.decode_float(offset + 28), bytes.decode_float(offset + 32))"
      )
      appendLine("\t\t\tvar canvas_target: CanvasItem = self")
      appendLine("\t\t\tcanvas_target.draw_texture(texture, draw_position, modulate)")
      appendLine("\t\t\tapplied += 1")
      appendLine("\t\t\toffset += 36")
    }
    appendLine("\t\telif opcode == 13 and target_object is Node:")
    appendLine("\t\t\tvar child_handle := bytes.decode_s32(offset + 8)")
    appendLine("\t\t\tvar child := _kanama_object_handles.get(child_handle) as Node")
    appendLine("\t\t\tif child == null:")
    appendLine("\t\t\t\tpush_error(\"Unknown Kanama Web child handle: %d\" % child_handle)")
    appendLine("\t\t\t\tbreak")
    appendLine(
      "\t\t\t(target_object as Node).add_child(child, bytes.decode_s32(offset + 12) != 0, bytes.decode_s32(offset + 16))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 14 and target_object is Node:")
    appendLine("\t\t\tvar child_handle := bytes.decode_s32(offset + 8)")
    appendLine("\t\t\tvar child := _kanama_object_handles.get(child_handle) as Node")
    appendLine("\t\t\tif child == null:")
    appendLine("\t\t\t\tpush_error(\"Unknown Kanama Web child handle: %d\" % child_handle)")
    appendLine("\t\t\t\tbreak")
    appendLine("\t\t\t(target_object as Node).remove_child(child)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 15 and target_object is Node:")
    appendLine("\t\t\t_kanama_object_handles.erase(object_handle)")
    appendLine("\t\t\t(target_object as Node).queue_free()")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 8")
    appendLine("\t\telif opcode == 16 and target_object is Sprite2D:")
    appendLine("\t\t\tvar texture_handle := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\tvar texture: Texture2D = null if texture_handle == 0 else _kanama_object_handles.get(texture_handle) as Texture2D"
    )
    appendLine("\t\t\tif texture_handle != 0 and texture == null:")
    appendLine("\t\t\t\tpush_error(\"Unknown Kanama Web texture handle: %d\" % texture_handle)")
    appendLine("\t\t\t\tbreak")
    appendLine("\t\t\t(target_object as Sprite2D).set_texture(texture)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telse:")
    appendLine(
      "\t\t\tpush_error(\"Invalid Kanama Web command opcode/object: %d/%d\" % [opcode, object_handle])"
    )
    appendLine("\t\t\tbreak")
    appendLine("\t_kanama_bridge.recordApplied(applied, last_value)")
    appendLine("\treturn applied")
    appendLine()
    appendLine("func _kanama_immediate_call(args: Array) -> int:")
    appendLine("\tvar object_handle := int(args[0])")
    appendLine("\tvar result := -1")
    appendLine("\tif object_handle == _kanama_handle:")
    appendLine("\t\tresult = get_child_count(bool(args[1]))")
    appendLine("\t_kanama_bridge.recordImmediateChildCount(result)")
    appendLine("\treturn result")
    appendLine()
    appendLine("func _kanama_resource_load(args: Array) -> int:")
    appendLine("\tvar resource_handle := int(args[0])")
    appendLine("\tvar path := String(args[1])")
    appendLine("\tvar type_hint := String(args[2])")
    appendLine("\tvar cache_mode := int(args[3])")
    appendLine("\tvar resource := ResourceLoader.load(path, type_hint, cache_mode)")
    appendLine("\tif resource == null:")
    appendLine("\t\t_kanama_bridge.recordImmediateResourceHandle(0)")
    appendLine("\t\treturn 0")
    appendLine("\t_kanama_object_handles[resource_handle] = resource")
    appendLine("\t_kanama_bridge.recordImmediateResourceHandle(resource_handle)")
    appendLine("\treturn resource_handle")
    appendLine()
    appendLine("func _kanama_signal_emit(args: Array) -> int:")
    appendLine("\tvar object_handle := int(args[0])")
    appendLine(
      "\tvar value: Object = self if object_handle == _kanama_handle else _kanama_object_handles.get(object_handle)"
    )
    appendLine("\tvar result := ERR_INVALID_PARAMETER")
    appendLine("\tif value != null:")
    appendLine("\t\tif args.size() == 2:")
    appendLine("\t\t\tresult = value.emit_signal(StringName(String(args[1])))")
    appendLine("\t\telse:")
    appendLine("\t\t\tresult = value.emit_signal(StringName(String(args[1])), int(args[2]))")
    appendLine("\t_kanama_bridge.recordImmediateSignalResult(result)")
    appendLine("\treturn result")
    appendLine()
    appendLine("func _kanama_resource_release(args: Array) -> int:")
    appendLine("\tvar resource_handle := int(args[0])")
    appendLine("\tvar released := int(_kanama_object_handles.erase(resource_handle))")
    appendLine("\t_kanama_bridge.recordImmediateResourceRelease(released)")
    appendLine("\treturn released")
    appendLine()
    appendLine("func _kanama_construct_object(args: Array) -> int:")
    appendLine("\tvar object_handle := int(args[0])")
    appendLine("\tvar requested_class := String(args[1])")
    appendLine("\tvar value: Object = ClassDB.instantiate(requested_class)")
    appendLine("\tif value == null or not value is Node:")
    appendLine("\t\t_kanama_bridge.recordImmediateConstructHandle(0)")
    appendLine("\t\treturn 0")
    appendLine("\t_kanama_object_handles[object_handle] = value")
    appendLine("\t_kanama_bridge.recordImmediateConstructHandle(object_handle)")
    appendLine("\treturn object_handle")
    appendLine()
    appendLine("func _kanama_node_lookup(args: Array) -> int:")
    appendLine("\tvar receiver_handle := int(args[0])")
    appendLine("\tvar result_handle := int(args[1])")
    appendLine(
      "\tvar receiver: Node = self if receiver_handle == _kanama_handle else _kanama_object_handles.get(receiver_handle) as Node"
    )
    appendLine(
      "\tvar value: Node = null if receiver == null else receiver.get_node_or_null(String(args[2]))"
    )
    appendLine("\tif value == null:")
    appendLine("\t\t_kanama_bridge.recordImmediateObjectHandle(0)")
    appendLine("\t\treturn 0")
    appendLine("\tvar script_handle := 0")
    appendLine("\tif value.has_method(\"_kanama_ensure_created\"):")
    appendLine("\t\tscript_handle = int(value.call(\"_kanama_ensure_created\"))")
    appendLine("\tif script_handle != 0:")
    appendLine("\t\t_kanama_bridge.recordImmediateObjectHandle(script_handle)")
    appendLine("\t\treturn script_handle")
    appendLine("\tfor existing_handle in _kanama_object_handles:")
    appendLine("\t\tif is_same(_kanama_object_handles[existing_handle], value):")
    appendLine("\t\t\t_kanama_bridge.recordImmediateObjectHandle(int(existing_handle))")
    appendLine("\t\t\treturn int(existing_handle)")
    appendLine("\t_kanama_object_handles[result_handle] = value")
    appendLine("\tif value is Node2D:")
    appendLine("\t\tvar node_2d := value as Node2D")
    appendLine(
      "\t\t_kanama_bridge.refreshNode2DSnapshot(result_handle, node_2d.position.x, node_2d.position.y, node_2d.scale.x, node_2d.scale.y, node_2d.modulate.r, node_2d.modulate.g, node_2d.modulate.b, node_2d.modulate.a)"
    )
    appendLine("\t_kanama_bridge.recordImmediateObjectHandle(result_handle)")
    appendLine("\treturn result_handle")
    appendLine()
    appendLine("func _kanama_packed_scene_instantiate(args: Array) -> int:")
    appendLine("\tvar resource_handle := int(args[0])")
    appendLine("\tvar proposed_handle := int(args[1])")
    appendLine("\tvar scene := _kanama_object_handles.get(resource_handle) as PackedScene")
    appendLine("\tvar value: Node = null if scene == null else scene.instantiate(int(args[2]))")
    appendLine("\tif value == null:")
    appendLine("\t\t_kanama_bridge.recordImmediateObjectHandle(0)")
    appendLine("\t\treturn 0")
    appendLine("\tvar result_handle := proposed_handle")
    appendLine("\tif value.has_method(\"_kanama_ensure_created\"):")
    appendLine("\t\tresult_handle = int(value.call(\"_kanama_ensure_created\"))")
    appendLine("\tif result_handle == 0:")
    appendLine("\t\tvalue.queue_free()")
    appendLine("\t\t_kanama_bridge.recordImmediateObjectHandle(0)")
    appendLine("\t\treturn 0")
    appendLine("\t_kanama_object_handles[result_handle] = value")
    appendLine("\tif value is Node2D:")
    appendLine("\t\tvar node_2d := value as Node2D")
    appendLine(
      "\t\t_kanama_bridge.refreshNode2DSnapshot(result_handle, node_2d.position.x, node_2d.position.y, node_2d.scale.x, node_2d.scale.y, node_2d.modulate.r, node_2d.modulate.g, node_2d.modulate.b, node_2d.modulate.a)"
    )
    appendLine("\t_kanama_bridge.recordImmediateObjectHandle(result_handle)")
    appendLine("\treturn result_handle")
    appendLine()
    appendLine("func _kanama_noargs_object(args: Array) -> int:")
    appendLine("\tvar opcode := int(args[0])")
    appendLine("\tvar receiver_handle := int(args[1])")
    appendLine("\tvar result_handle := int(args[2])")
    appendLine(
      "\tvar receiver: Node = self if receiver_handle == _kanama_handle else _kanama_object_handles.get(receiver_handle) as Node"
    )
    appendLine("\tvar value: Object = null")
    appendLine("\tif opcode == 19 and receiver != null:")
    appendLine("\t\tvalue = receiver.get_viewport()")
    appendLine("\telif opcode == 36 and receiver != null:")
    appendLine("\t\tvalue = receiver.create_tween()")
    appendLine("\telif opcode == 51 and receiver != null:")
    appendLine("\t\tvalue = receiver.get_tree()")
    appendLine("\tif value == null:")
    appendLine("\t\t_kanama_bridge.recordImmediateObjectHandle(0)")
    appendLine("\t\treturn 0")
    appendLine("\t_kanama_object_handles[result_handle] = value")
    appendLine("\tif value is Tween:")
    appendLine("\t\t_kanama_tween_children[result_handle] = []")
    appendLine("\t\t_kanama_tween_targets[result_handle] = []")
    appendLine(
      "\t\t(value as Tween).finished.connect(_kanama_tween_finished.bind(result_handle), CONNECT_ONE_SHOT)"
    )
    appendLine("\tif value is Viewport:")
    appendLine("\t\tvar viewport_rect := (value as Viewport).get_visible_rect()")
    appendLine(
      "\t\t_kanama_bridge.refreshViewportRectSnapshot(result_handle, viewport_rect.position.x, viewport_rect.position.y, viewport_rect.size.x, viewport_rect.size.y)"
    )
    appendLine("\t_kanama_bridge.recordImmediateObjectHandle(result_handle)")
    appendLine("\treturn result_handle")
    appendLine()
    appendLine("func _kanama_tween_call(args: Array) -> int:")
    appendLine("\tvar opcode := int(args[0])")
    appendLine("\tvar receiver_handle := int(args[1])")
    appendLine("\tvar receiver: Object = _kanama_object_handles.get(receiver_handle)")
    appendLine("\tif opcode == 37 and receiver is Tween:")
    appendLine("\t\t(receiver as Tween).kill()")
    appendLine("\t\t_kanama_release_tween(receiver_handle)")
    appendLine("\t\t_kanama_bridge.recordImmediateLongResult(1)")
    appendLine("\t\treturn 1")
    appendLine("\tvar result_handle := 0")
    appendLine("\tif opcode == 38 and receiver is Tween:")
    appendLine("\t\t(receiver as Tween).set_parallel(bool(args[2]))")
    appendLine("\t\tresult_handle = receiver_handle")
    appendLine("\telif (opcode == 39 or opcode == 40) and receiver is Tween:")
    appendLine("\t\tvar proposed_handle := int(args[2])")
    appendLine("\t\tvar target_handle := int(args[3])")
    appendLine(
      "\t\tvar target: Object = self if target_handle == _kanama_handle else _kanama_object_handles.get(target_handle)"
    )
    appendLine("\t\tvar tweener: PropertyTweener = null")
    appendLine("\t\tif target != null and opcode == 39:")
    appendLine(
      "\t\t\ttweener = (receiver as Tween).tween_property(target, NodePath(String(args[4])), Vector2(float(args[5]), float(args[6])), float(args[7]))"
    )
    appendLine("\t\telif target != null and opcode == 40:")
    appendLine(
      "\t\t\ttweener = (receiver as Tween).tween_property(target, NodePath(String(args[4])), Color(float(args[5]), float(args[6]), float(args[7]), float(args[8])), float(args[9]))"
    )
    appendLine("\t\tif tweener != null:")
    appendLine("\t\t\t_kanama_object_handles[proposed_handle] = tweener")
    appendLine("\t\t\tvar children: Array = _kanama_tween_children.get(receiver_handle, [])")
    appendLine("\t\t\tchildren.append(proposed_handle)")
    appendLine("\t\t\t_kanama_tween_children[receiver_handle] = children")
    appendLine("\t\t\tvar targets: Array = _kanama_tween_targets.get(receiver_handle, [])")
    appendLine("\t\t\tif not targets.has(target_handle):")
    appendLine("\t\t\t\ttargets.append(target_handle)")
    appendLine("\t\t\t_kanama_tween_targets[receiver_handle] = targets")
    appendLine("\t\t\tresult_handle = proposed_handle")
    appendLine("\telif opcode == 41 and receiver is PropertyTweener:")
    appendLine("\t\t(receiver as PropertyTweener).set_trans(int(args[2]))")
    appendLine("\t\tresult_handle = receiver_handle")
    appendLine("\telif opcode == 42 and receiver is PropertyTweener:")
    appendLine("\t\t(receiver as PropertyTweener).set_ease(int(args[2]))")
    appendLine("\t\tresult_handle = receiver_handle")
    appendLine("\t_kanama_bridge.recordImmediateObjectHandle(result_handle)")
    appendLine("\treturn int(result_handle != 0)")
    appendLine()
    appendLine("func _kanama_tween_finished(tween_handle: int) -> void:")
    appendLine("\t_kanama_release_tween(tween_handle)")
    appendLine()
    appendLine("func _kanama_release_tween(tween_handle: int) -> void:")
    appendLine("\tif not _kanama_tween_children.has(tween_handle):")
    appendLine("\t\treturn")
    appendLine("\tvar targets: Array = _kanama_tween_targets.get(tween_handle, [])")
    appendLine("\tfor target_handle in targets:")
    appendLine(
      "\t\tvar target: Object = self if int(target_handle) == _kanama_handle else _kanama_object_handles.get(int(target_handle))"
    )
    appendLine("\t\tif target is Node2D:")
    appendLine("\t\t\tvar node_2d := target as Node2D")
    appendLine(
      "\t\t\t_kanama_bridge.refreshNode2DSnapshot(int(target_handle), node_2d.position.x, node_2d.position.y, node_2d.scale.x, node_2d.scale.y, node_2d.modulate.r, node_2d.modulate.g, node_2d.modulate.b, node_2d.modulate.a)"
    )
    appendLine("\tvar children: Array = _kanama_tween_children.get(tween_handle, [])")
    appendLine("\tfor child_handle in children:")
    appendLine("\t\t_kanama_object_handles.erase(int(child_handle))")
    appendLine("\t_kanama_object_handles.erase(tween_handle)")
    appendLine("\t_kanama_tween_children.erase(tween_handle)")
    appendLine("\t_kanama_tween_targets.erase(tween_handle)")
    appendLine("\t_kanama_bridge.releaseTweenGraph(tween_handle)")
    appendLine()
    appendLine("func _kanama_input_cursor(args: Array) -> int:")
    appendLine("\tvar resource_handle := int(args[0])")
    appendLine(
      "\tvar texture: Texture2D = null if resource_handle == 0 else _kanama_object_handles.get(resource_handle) as Texture2D"
    )
    appendLine("\tif resource_handle != 0 and texture == null:")
    appendLine("\t\tpush_error(\"Unknown Kanama Web cursor texture handle: %d\" % resource_handle)")
    appendLine("\t\treturn 0")
    appendLine(
      "\tInput.set_custom_mouse_cursor(texture, int(args[1]), Vector2(float(args[2]), float(args[3])))"
    )
    appendLine("\treturn 1")
    appendLine()
    appendLine("func _kanama_connect(args: Array) -> int:")
    appendLine("\tvar source_handle := int(args[0])")
    appendLine("\tvar target_handle := int(args[2])")
    appendLine(
      "\tvar source: Object = self if source_handle == _kanama_handle else _kanama_object_handles.get(source_handle)"
    )
    appendLine(
      "\tvar target: Object = self if target_handle == _kanama_handle else _kanama_object_handles.get(target_handle)"
    )
    appendLine("\tvar result := ERR_INVALID_PARAMETER")
    appendLine("\tif source != null and target != null:")
    appendLine("\t\tvar callable := Callable(target, StringName(String(args[3])))")
    appendLine("\t\tif args.size() > 5:")
    appendLine("\t\t\tcallable = callable.bind(int(args[5]))")
    appendLine("\t\tresult = source.connect(StringName(String(args[1])), callable, int(args[4]))")
    appendLine("\t_kanama_bridge.recordImmediateConnectResult(result)")
    appendLine("\treturn result")
    appendLine()
    appendLine("func _kanama_web_signal_dispatch0(callback_id: int) -> void:")
    appendLine("\t_kanama_bridge.dispatchSignal0(_kanama_handle, callback_id)")
    appendLine()
    appendLine("func _kanama_object_query(args: Array) -> int:")
    appendLine("\tvar opcode := int(args[0])")
    appendLine("\tvar object_handle := int(args[1])")
    appendLine(
      "\tvar value: Object = self if object_handle == _kanama_handle else _kanama_object_handles.get(object_handle)"
    )
    appendLine("\tvar result := 0")
    appendLine("\tif value != null:")
    appendLine("\t\tif opcode == 23:")
    appendLine("\t\t\tresult = int(value.is_class(StringName(String(args[2]))))")
    appendLine("\t\telif opcode == 24 and value is InputEvent:")
    appendLine("\t\t\tresult = int((value as InputEvent).is_pressed())")
    appendLine("\t\telif opcode == 25 and value is InputEvent:")
    appendLine("\t\t\tresult = int((value as InputEvent).is_released())")
    appendLine("\t\telif opcode == 26 and value is InputEventMouseButton:")
    appendLine("\t\t\tresult = int((value as InputEventMouseButton).button_index)")
    appendLine("\t_kanama_bridge.recordImmediateLongResult(result)")
    appendLine("\treturn result")
    appendLine()
    appendLine("func _kanama_noargs_vector2(args: Array) -> int:")
    appendLine("\tvar opcode := int(args[0])")
    appendLine("\tvar object_handle := int(args[1])")
    appendLine(
      "\tvar value: Object = self if object_handle == _kanama_handle else _kanama_object_handles.get(object_handle)"
    )
    appendLine("\tvar result := Vector2.ZERO")
    appendLine("\tif opcode == 27 and value is CanvasItem:")
    appendLine("\t\tresult = (value as CanvasItem).get_local_mouse_position()")
    appendLine("\t_kanama_bridge.recordImmediateVector2(result.x, result.y)")
    appendLine("\treturn 1")
    appendLine()
    appendLine("func _kanama_signal_emit_vector2i(args: Array) -> int:")
    appendLine("\tvar object_handle := int(args[0])")
    appendLine(
      "\tvar value: Object = self if object_handle == _kanama_handle else _kanama_object_handles.get(object_handle)"
    )
    appendLine("\tvar result := ERR_INVALID_PARAMETER")
    appendLine("\tif value != null:")
    appendLine(
      "\t\tresult = value.emit_signal(StringName(String(args[1])), Vector2i(int(args[2]), int(args[3])))"
    )
    appendLine("\t_kanama_bridge.recordImmediateSignalResult(result)")
    appendLine("\treturn result")

    model.methods.forEachIndexed { index, method ->
      if (method.godotName == "_draw") return@forEachIndexed
      appendLine()
      val args = method.args.joinToString(", ") { "${it.name}: ${gdType(it)}" }
      val returnType = " -> ${method.returnType?.let(::gdType) ?: "void"}"
      appendLine("func ${method.godotName}($args)$returnType:")
      appendLine(
        "\tif _kanama_bridge.shouldDeferGameplayMethod(${quote(model.fqName)}, ${quote(method.godotName)}):"
      )
      appendLine(
        "\t\t_kanama_bridge.recordDeferredGameplayMethod(${quote(model.fqName)}, ${quote(method.godotName)})"
      )
      appendLine("\t\treturn${method.returnType?.let { " ${gdDefault(it)}" } ?: ""}")
      when {
        method.args.isEmpty() && method.returnType == null ->
          appendLine("\t_kanama_bridge.callNoArgs(_kanama_handle, ${index + 1})")
        method.returnType == TypeMapping.INT &&
          method.args.size == 1 &&
          method.args.single().type == TypeMapping.INT -> {
          val arg = method.args.single()
          appendLine(
            "\treturn int(_kanama_bridge.callInt(_kanama_handle, ${index + 1}, ${arg.name}))"
          )
        }
        method.returnType == null &&
          method.args.size == 1 &&
          method.args.single().type == TypeMapping.VECTOR2I -> {
          val arg = method.args.single()
          appendLine(
            "\t_kanama_bridge.callVector2i(_kanama_handle, ${index + 1}, ${arg.name}.x, ${arg.name}.y)"
          )
        }
        method.returnType == null &&
          method.args.size == 3 &&
          method.args[0].type == TypeMapping.OBJECT &&
          method.args[1].type == TypeMapping.OBJECT &&
          method.args[2].type == TypeMapping.INT -> {
          val first = method.args[0]
          val second = method.args[1]
          val value = method.args[2]
          appendLine(
            "\tvar first_handle := int(_kanama_bridge.allocateTransientObjectHandle(_kanama_handle))"
          )
          appendLine(
            "\tvar second_handle := int(_kanama_bridge.allocateTransientObjectHandle(_kanama_handle))"
          )
          appendLine("\t_kanama_object_handles[first_handle] = ${first.name}")
          appendLine("\t_kanama_object_handles[second_handle] = ${second.name}")
          appendLine(
            "\t_kanama_bridge.callObjectObjectLong(_kanama_handle, ${index + 1}, first_handle, second_handle, ${value.name})"
          )
          appendLine("\t_kanama_object_handles.erase(first_handle)")
          appendLine("\t_kanama_object_handles.erase(second_handle)")
          appendLine("\t_kanama_bridge.releaseTransientObjectHandle(first_handle)")
          appendLine("\t_kanama_bridge.releaseTransientObjectHandle(second_handle)")
        }
        else -> {
          appendLine(
            "\t_kanama_bridge.unsupportedGameplayMethod(_KANAMA_SCRIPT_ID, ${index + 1}, ${quote(method.godotName)})"
          )
          method.returnType?.let { appendLine("\treturn ${gdDefault(it)}") }
        }
      }
    }
  }

  private fun StringBuilder.appendProtocolMethods(label: String, methods: List<ProtocolMethod>) {
    appendLine("      \"$label\": [")
    methods.forEachIndexed { index, method ->
      append("        {\"id\": ${index + 1}, \"name\": ${quote(method.name)}, ")
      append("\"arguments\": ${protocolArgs(method.args)}, \"returnType\": ")
      append(method.returnType?.let { quote(it.kotlinType) } ?: "null")
      append("}")
      appendLine(if (index == methods.lastIndex) "" else ",")
    }
    append("      ]")
  }

  private fun protocolArgs(args: List<ArgModel>): String = buildString {
    append('[')
    args.forEachIndexed { index, arg ->
      if (index > 0) append(", ")
      append("{\"name\": ${quote(arg.name)}, \"type\": ${quote(protocolArgType(arg))}, ")
      append("\"nullable\": ${arg.nullable}, \"hasDefault\": ${arg.hasDefault}}")
    }
    append(']')
  }

  private fun protocolArgType(arg: ArgModel): String =
    arg.objectWrapperFqName ?: arg.type.kotlinType

  private fun protocolPropertyType(property: ScriptPropertyModel): String =
    when {
      property.type == TypeMapping.ARRAY && property.arrayElementWrapperFqName != null ->
        "List<${property.arrayElementWrapperFqName}>"
      property.type == TypeMapping.ARRAY && property.arrayElementCustomScriptFqName != null ->
        "List<${property.arrayElementCustomScriptFqName}>"
      property.type == TypeMapping.ARRAY && property.arrayElementString -> "List<String>"
      property.objectWrapperFqName != null -> property.objectWrapperFqName
      property.customScriptFqName != null -> property.customScriptFqName
      else -> property.type.kotlinType
    }

  private fun StringBuilder.appendPropertyGroup(property: ScriptPropertyModel) {
    fun appendGroup(annotation: String, group: ScriptPropertyGroupModel?) {
      if (group == null) return
      val prefix = if (group.prefix.isBlank()) "" else ", ${quote(group.prefix)}"
      appendLine("@$annotation(${quote(group.name)}$prefix)")
    }
    appendGroup("export_category", property.exportCategory)
    appendGroup("export_group", property.exportGroup)
    appendGroup("export_subgroup", property.exportSubgroup)
  }

  private fun gdType(arg: ArgModel): String =
    arg.objectWrapperFqName?.let(::gdObjectType) ?: gdType(arg.type)

  private fun gdType(property: ScriptPropertyModel): String =
    when {
      property.type == TypeMapping.ARRAY && property.arrayElementWrapperFqName != null ->
        "Array[${property.arrayElementWrapperFqName.substringAfterLast('.')}]"
      property.type == TypeMapping.ARRAY && property.arrayElementCustomScriptFqName != null ->
        "Array[${property.arrayElementCustomScriptFqName.substringAfterLast('.')}]"
      property.type == TypeMapping.ARRAY && property.arrayElementString -> "Array[String]"
      property.type == TypeMapping.ARRAY -> "Array"
      property.objectWrapperFqName != null -> gdObjectType(property.objectWrapperFqName)
      property.customScriptFqName != null -> property.customScriptFqName.substringAfterLast('.')
      else -> gdType(property.type)
    }

  private fun gdObjectType(fqName: String): String =
    if (fqName == "net.multigesture.kanama.api.GodotObject") "Object"
    else fqName.substringAfterLast('.')

  private fun gdType(type: TypeMapping): String =
    when (type) {
      TypeMapping.STRING -> "String"
      TypeMapping.INT -> "int"
      TypeMapping.FLOAT -> "float"
      TypeMapping.BOOL -> "bool"
      TypeMapping.VECTOR2 -> "Vector2"
      TypeMapping.VECTOR2I -> "Vector2i"
      TypeMapping.OBJECT -> "Object"
      TypeMapping.ARRAY -> "Array"
      else -> "Variant"
    }

  private fun gdDefault(property: ScriptPropertyModel): String =
    when (property.type) {
      TypeMapping.STRING -> property.defaultLiteral ?: "\"\""
      TypeMapping.INT -> property.defaultLiteral?.removeSuffix("L") ?: "0"
      TypeMapping.FLOAT -> property.defaultLiteral?.removeSuffix("f")?.removeSuffix("F") ?: "0.0"
      TypeMapping.BOOL -> property.defaultLiteral ?: "false"
      TypeMapping.VECTOR2 -> "Vector2.ZERO"
      TypeMapping.VECTOR2I -> "Vector2i.ZERO"
      TypeMapping.ARRAY -> "[]"
      else -> "null"
    }

  private fun gdDefault(type: TypeMapping): String =
    when (type) {
      TypeMapping.STRING -> "\"\""
      TypeMapping.INT -> "0"
      TypeMapping.FLOAT -> "0.0"
      TypeMapping.BOOL -> "false"
      TypeMapping.VECTOR2 -> "Vector2.ZERO"
      TypeMapping.VECTOR2I -> "Vector2i.ZERO"
      TypeMapping.ARRAY -> "[]"
      else -> "null"
    }

  private fun quote(value: String): String = buildString {
    append('"')
    value.forEach { char ->
      when (char) {
        '\\' -> append("\\\\")
        '"' -> append("\\\"")
        '\n' -> append("\\n")
        '\r' -> append("\\r")
        '\t' -> append("\\t")
        else -> append(char)
      }
    }
    append('"')
  }

  private fun constantIdentifier(name: String): String {
    val stripped = name.trim('_')
    if (stripped.isBlank()) return "value"
    val words = stripped.split('_', '-', ' ', '.', ':', '/').filter { it.isNotBlank() }
    val candidate =
      words.drop(1).fold(words.first()) { acc, part ->
        acc + part.replaceFirstChar { it.uppercaseChar() }
      }
    return candidate.replace(Regex("""[^A-Za-z0-9_]"""), "_").let {
      if (it.firstOrNull()?.isDigit() == true) "_$it" else it
    }
  }
}
