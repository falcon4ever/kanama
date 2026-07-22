package net.multigesture.kanama.processor

import com.google.devtools.ksp.getDeclaredFunctions
import com.google.devtools.ksp.getDeclaredProperties
import com.google.devtools.ksp.isPublic
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.Modifier
import com.google.devtools.ksp.symbol.Nullability
import java.io.File

/**
 * Phase 5 KSP processor.
 *
 * For each `@RegisterClass` type, emits a complete `<ClassName>Registrar` into
 * `build/generated/ksp/main/kotlin/net/multigesture/kanama/generated`. The registrar covers:
 * - class registration (create/free/getVirtual upcalls + ClassDB.registerClass)
 * - `@RegisterFunction` methods (call/ptrcall upcalls + registerMethod)
 * - `@RegisterProperty` properties (synthetic get_/set_ methods + registerProperty)
 * - `@OnReady` / `@OnEnterTree` / `@OnExitTree` virtuals (per-virtual dispatch keyed by interned
 *   StringName storage)
 *
 * A single `KanamaRegistry.registerAll(library)` aggregator is emitted at the end of the KSP round
 * so `KanamaBinding` only calls one entry point regardless of how many classes exist.
 *
 * Current type-marshaling scope: `kotlin.Long` (→ `VariantType.INT`) for arguments, returns, and
 * properties. `kotlin.Unit` for void returns. Richer types land in a later iteration.
 */
class KanamaProcessor(private val env: SymbolProcessorEnvironment) : SymbolProcessor {

  private val registrarSimpleNames = linkedSetOf<String>()
  private val classRegistrarSimpleNames = linkedSetOf<String>()
  private val scriptRegistrarSimpleNames = linkedSetOf<String>()
  private val aggregatorSources = mutableListOf<KSFile>()
  private val classAggregatorSources = mutableListOf<KSFile>()
  private val scriptAggregatorSources = mutableListOf<KSFile>()
  private var scriptClassTypes: Map<String, ScriptClassTypeInfo> = emptyMap()

  // Phase 3.2: on the iOS (Kotlin/Native) target the processor emits the iOS @ScriptClass
  // registry Kotlin itself (replacing the parseIosScript regex parser). It accumulates the
  // platform-neutral models here and emits the aggregated iOS sources in finish() via
  // IosScriptCodeEmitter. The `res://…` resource path each script reports to Godot is derived
  // from its source path relative to the configured script roots (passed as a KSP option).
  private val iosScripts = mutableListOf<IosScriptInput>()
  private val webScripts = mutableListOf<WebScriptInput>()
  private val scriptRoots: List<String> =
    (env.options["kanamaScriptRoots"] ?: "")
      .split(File.pathSeparator, ",")
      .map { it.trim() }
      .filter { it.isNotEmpty() }

  // The JVM registrars/aggregators emit MemorySegment/Panama code that only compiles on
  // the JVM target. Non-JVM targets emit only their platform-specific static registry plus the
  // platform-neutral .script-model.json; keep JVM code out of both iOS and Web outputs.
  private val emitJvmCode: Boolean =
    env.platforms.isEmpty() ||
      env.platforms.any { it.platformName.equals("JVM", ignoreCase = true) }
  private val emitWebCode: Boolean = env.options["kanamaRuntimeTarget"] == "web"
  private val emitIosCode: Boolean = !emitJvmCode && !emitWebCode

  override fun process(resolver: Resolver): List<KSAnnotated> {
    // Phase 5: @RegisterClass → full ClassDB type registration.
    val symbols = resolver.getSymbolsWithAnnotation(REGISTER_CLASS_FQN)
    for (symbol in symbols) {
      if (symbol !is KSClassDeclaration) continue
      val fqName = symbol.qualifiedName?.asString() ?: continue
      val simpleName = symbol.simpleName.asString()
      val registrarName = "${simpleName}Registrar"
      if (!registrarSimpleNames.add(registrarName)) continue
      classRegistrarSimpleNames += registrarName

      val model =
        try {
          buildClassModel(symbol, fqName)
        } catch (e: IllegalArgumentException) {
          env.logger.error("[kanama:ksp] ${e.message}", symbol)
          continue
        }
      symbol.containingFile?.let {
        aggregatorSources += it
        classAggregatorSources += it
      }
      emitRegistrar(model, symbol.containingFile!!)
    }

    // @ScriptClass -> ScriptInstance-backed attach-to-node registration.
    val scriptSymbols =
      resolver
        .getSymbolsWithAnnotation(SCRIPT_CLASS_FQN)
        .filterIsInstance<KSClassDeclaration>()
        .toList()
    scriptClassTypes = buildScriptClassTypeMap(scriptSymbols)
    for (symbol in scriptSymbols) {
      val fqName = symbol.qualifiedName?.asString() ?: continue
      val simpleName = symbol.simpleName.asString()
      val registrarName = "${simpleName}ScriptRegistrar"
      if (!registrarSimpleNames.add(registrarName)) continue
      scriptRegistrarSimpleNames += registrarName

      val model =
        try {
          buildScriptModel(symbol, fqName)
        } catch (e: IllegalArgumentException) {
          env.logger.error("[kanama:ksp] ${e.message}", symbol)
          continue
        }
      symbol.containingFile?.let {
        aggregatorSources += it
        scriptAggregatorSources += it
      }
      emitScriptRegistrar(model, symbol.containingFile!!)
      if (emitIosCode) {
        iosScripts += IosScriptInput(model, scriptResourcePath(symbol.containingFile!!))
      }
      if (emitWebCode) {
        webScripts += WebScriptInput(model, scriptResourcePath(symbol.containingFile!!))
      }
    }

    return emptyList()
  }

  /** The `res://…` path Godot reports for [file], relative to the configured script roots. */
  private fun scriptResourcePath(file: KSFile): String {
    val norm = file.filePath.replace(File.separatorChar, '/')
    val root =
      scriptRoots
        .map { it.replace(File.separatorChar, '/').removeSuffix("/") }
        .filter { norm.startsWith("$it/") }
        .maxByOrNull { it.length }
    if (root != null) {
      val rel = norm.removePrefix("$root/")
      return if (root.substringAfterLast('/') == "kotlin-src") "res://kotlin-src/$rel"
      else "res://$rel"
    }
    // Fallback when roots are not provided: anchor at a kotlin-src segment if present.
    val idx = norm.indexOf("/kotlin-src/")
    return if (idx >= 0) "res://" + norm.substring(idx + 1)
    else "res://" + norm.substringAfterLast('/')
  }

  private fun buildScriptClassTypeMap(
    scriptSymbols: List<KSClassDeclaration>
  ): Map<String, ScriptClassTypeInfo> {
    val byName = linkedMapOf<String, ScriptClassTypeInfo>()
    for (symbol in scriptSymbols) {
      val fqName = symbol.qualifiedName?.asString() ?: continue
      val simpleName = symbol.simpleName.asString()
      val attachTo =
        symbol.annotations
          .firstOrNull { it.shortName.asString() == "ScriptClass" }
          ?.arguments
          ?.firstOrNull { it.name?.asString() == "attachTo" }
          ?.value as? String ?: "Node"
      val isGlobalClass =
        symbol.annotations.any {
          val name = it.shortName.asString()
          name == "GlobalClass" || name == "ClassName"
        }
      val info = ScriptClassTypeInfo(fqName, simpleName, attachTo, isGlobalClass)
      byName[fqName] = info
    }
    return byName
  }

  override fun finish() {
    if (emitWebCode) {
      emitWebScriptRegistry()
      return
    }
    // On the iOS (Kotlin/Native) target, emit the aggregated @ScriptClass registry Kotlin
    // (Phase 3.2). During the parallel-run gate these go out as `.kt.txt` RESOURCES so they
    // are not compiled and cannot collide with the still-active regex-generated registry;
    // the cutover flips them to real `.kt`. There are no JVM aggregators to emit on Native.
    if (emitIosCode) {
      emitIosScriptRegistry()
      return
    }
    if (registrarSimpleNames.isEmpty()) return
    emitAggregator(
      fileName = "KanamaRegistry",
      objectName = "KanamaRegistry",
      registrars = registrarSimpleNames,
      sources = aggregatorSources,
    )
    if (classRegistrarSimpleNames.isNotEmpty()) {
      emitAggregator(
        fileName = "KanamaClassRegistry",
        objectName = "KanamaClassRegistry",
        registrars = classRegistrarSimpleNames,
        sources = classAggregatorSources,
      )
    }
    if (scriptRegistrarSimpleNames.isNotEmpty()) {
      emitAggregator(
        fileName = "KanamaScriptRegistry",
        objectName = "KanamaScriptRegistry",
        registrars = scriptRegistrarSimpleNames,
        sources = scriptAggregatorSources,
      )
    }
  }

  private fun emitAggregator(
    fileName: String,
    objectName: String,
    registrars: Iterable<String>,
    sources: List<KSFile>,
  ) {
    val registrarList = registrars.toList()
    val source = buildString {
      appendLine("// Generated by KanamaProcessor — do not edit.")
      appendLine("package $GENERATED_PACKAGE")
      appendLine()
      appendLine("import java.lang.foreign.MemorySegment")
      appendLine()
      appendLine("object $objectName {")
      appendLine("    fun registerAll(library: MemorySegment) {")
      for (name in registrarList) {
        appendLine("        $name.register(library)")
      }
      appendLine("    }")
      appendLine("}")
    }
    env.codeGenerator
      .createNewFile(
        dependencies = Dependencies(aggregating = true, *sources.toTypedArray()),
        packageName = GENERATED_PACKAGE,
        fileName = fileName,
      )
      .use { it.write(source.toByteArray(Charsets.UTF_8)) }
    env.logger.warn(
      "[kanama:ksp] generated $GENERATED_PACKAGE.$objectName " +
        "with ${registrarList.size} class(es)"
    )
  }

  // ---------- Model building ----------

  private fun buildClassModel(cls: KSClassDeclaration, fqName: String): ClassModel {
    val simpleName = cls.simpleName.asString()
    failOnGeneratedWrapperSupertype(cls, simpleName)
    val parent =
      cls.annotations
        .firstOrNull { it.shortName.asString() == "RegisterClass" }
        ?.arguments
        ?.firstOrNull { it.name?.asString() == "parentClassName" }
        ?.value as? String ?: "Object"

    val isTool = cls.annotations.any { it.shortName.asString() == "Tool" }

    val methods = mutableListOf<MethodModel>()
    val properties = mutableListOf<PropertyModel>()
    val virtuals = mutableListOf<VirtualModel>()
    val signals = mutableListOf<SignalModel>()

    for (fn in cls.getDeclaredFunctions()) {
      if (!fn.isPublic()) continue
      val annotationNames = fn.annotations.map { it.shortName.asString() }.toSet()
      if ("Rpc" in annotationNames) {
        throw IllegalArgumentException(
          "$simpleName.${fn.simpleName.asString()}: @Rpc is only supported on @ScriptClass methods"
        )
      }
      for (ann in fn.annotations) {
        val callName = "call${capitalize(fn.simpleName.asString())}"
        val kotlinName = fn.simpleName.asString()
        when (ann.shortName.asString()) {
          "RegisterFunction",
          "Method" -> methods += buildMethodModel(fn, ann, simpleName)
          "OnReady",
          "Ready" -> virtuals += VirtualModel("_ready", callName, kotlinName)
          "OnEnterTree",
          "EnterTree" -> virtuals += VirtualModel("_enter_tree", callName, kotlinName)
          "OnExitTree",
          "ExitTree" -> virtuals += VirtualModel("_exit_tree", callName, kotlinName)
          "OnProcess",
          "Process" ->
            virtuals +=
              VirtualModel(
                "_process",
                callName,
                kotlinName,
                args = listOf(ArgModel("delta", TypeMapping.FLOAT)),
              )
          "OnPhysicsProcess",
          "PhysicsProcess" ->
            virtuals +=
              VirtualModel(
                "_physics_process",
                callName,
                kotlinName,
                args = listOf(ArgModel("delta", TypeMapping.FLOAT)),
              )
          "OnInput",
          "Input" ->
            virtuals +=
              VirtualModel(
                "_input",
                callName,
                kotlinName,
                args =
                  listOf(
                    ArgModel("event", TypeMapping.OBJECT, "net.multigesture.kanama.api.GodotObject")
                  ),
              )
          "OnUnhandledInput",
          "UnhandledInput" ->
            virtuals +=
              VirtualModel(
                "_unhandled_input",
                callName,
                kotlinName,
                args =
                  listOf(
                    ArgModel("event", TypeMapping.OBJECT, "net.multigesture.kanama.api.GodotObject")
                  ),
              )
          "OnShortcutInput",
          "ShortcutInput" ->
            virtuals +=
              VirtualModel(
                "_shortcut_input",
                callName,
                kotlinName,
                args =
                  listOf(
                    ArgModel("event", TypeMapping.OBJECT, "net.multigesture.kanama.api.GodotObject")
                  ),
              )
          "OnUnhandledKeyInput",
          "UnhandledKeyInput" ->
            virtuals +=
              VirtualModel(
                "_unhandled_key_input",
                callName,
                kotlinName,
                args =
                  listOf(
                    ArgModel("event", TypeMapping.OBJECT, "net.multigesture.kanama.api.GodotObject")
                  ),
              )
          "Signal" -> signals += buildSignalModel(fn, ann, simpleName)
        }
      }
    }

    for (prop in cls.getDeclaredProperties()) {
      if (
        prop.annotations.none {
          it.shortName.asString() == "RegisterProperty" || it.shortName.asString() == "Export"
        }
      )
        continue
      properties += buildPropertyModel(prop, simpleName)
    }

    return ClassModel(
      simpleName = simpleName,
      fqName = fqName,
      parentClassName = parent,
      isTool = isTool,
      methods = methods,
      properties = properties,
      virtuals = virtuals,
      signals = signals,
    )
  }

  private fun buildSignalModel(
    fn: KSFunctionDeclaration,
    ann: com.google.devtools.ksp.symbol.KSAnnotation,
    ownerSimpleName: String,
  ): SignalModel {
    val kotlinName = fn.simpleName.asString()
    val nameOverride = ann.arguments.firstOrNull { it.name?.asString() == "name" }?.value as? String
    val godotName = if (nameOverride.isNullOrEmpty()) camelToSnake(kotlinName) else nameOverride
    val args =
      fn.parameters.map { p ->
        val name = p.name?.asString() ?: "arg"
        val type = p.type.resolve()
        val arg =
          fqToArgModel(name, type)
            ?: throw IllegalArgumentException(
              "$ownerSimpleName.$kotlinName: unsupported signal arg type '${type.declaration.qualifiedName?.asString()}' for '$name'"
            )
        arg
      }
    return SignalModel(godotName = godotName, args = args)
  }

  /**
   * Build a [VirtualModel] for an `@OverrideVirtual("_x")` declaration. Resolves the Kotlin
   * signature (args + return) like a method, then validates it against the canonical engine
   * signature from [VirtualSignatureTable] for the attach-to class hierarchy. Fails the build
   * (fail-closed) on an unknown virtual, an arity mismatch, or a void/value-return mismatch.
   */
  private fun buildVirtualOverrideModel(
    fn: KSFunctionDeclaration,
    attachTo: String,
    ownerSimpleName: String,
  ): VirtualModel {
    val kotlinName = fn.simpleName.asString()
    // The function name IS the engine virtual name (GDScript-style `fun _draw()`). KSP2 over
    // Kotlin/Native does not expose function-annotation argument values, but the function name
    // is available on every target — so the name comes from the method, not an annotation arg.
    val virtualName = kotlinName
    if (!virtualName.startsWith("_")) {
      throw IllegalArgumentException(
        "$ownerSimpleName.$kotlinName: @OverrideVirtual function name must be the engine " +
          "virtual name (e.g. `fun _draw()`, `fun _get_minimum_size()`)."
      )
    }

    val canonical =
      VirtualSignatureTable.resolve(attachTo, virtualName)
        ?: throw IllegalArgumentException(
          "$ownerSimpleName.$kotlinName: @OverrideVirtual(\"$virtualName\") — no such virtual on " +
            "'$attachTo' or its ancestors. Check the name against extension_api.json."
        )

    val returnType =
      fn.returnType?.resolve()?.let { type ->
        val fq = type.declaration.qualifiedName?.asString()
        if (fq == "kotlin.Unit") null
        else
          virtualReturnTypeMapping(type, fq)
            ?: throw IllegalArgumentException(
              "$ownerSimpleName.$kotlinName: unsupported @OverrideVirtual return type '$fq'"
            )
      }
    val args =
      fn.parameters.map { p ->
        val name = p.name?.asString() ?: "arg"
        val type = p.type.resolve()
        fqToArgModel(name, type)
          ?: throw IllegalArgumentException(
            "$ownerSimpleName.$kotlinName: unsupported @OverrideVirtual parameter type " +
              "'${type.declaration.qualifiedName?.asString()}' for '$name'"
          )
      }

    // Fail-closed validation against the canonical engine signature.
    if (args.size != canonical.argTypes.size) {
      throw IllegalArgumentException(
        "$ownerSimpleName.$kotlinName: @OverrideVirtual(\"$virtualName\") arity mismatch — " +
          "engine declares ${canonical.argTypes.size} arg(s) (${canonical.argTypes.joinToString(", ")}), " +
          "Kotlin has ${args.size}."
      )
    }
    val canonicalVoid = canonical.returnType == null
    if (canonicalVoid != (returnType == null)) {
      throw IllegalArgumentException(
        "$ownerSimpleName.$kotlinName: @OverrideVirtual(\"$virtualName\") return mismatch — " +
          "engine returns ${canonical.returnType ?: "void"}, Kotlin returns " +
          "${returnType?.name ?: "Unit"}."
      )
    }

    return VirtualModel(
      virtualName = virtualName,
      callFunctionName = kotlinName,
      kotlinMethodName = kotlinName,
      args = args,
      returnType = returnType,
    )
  }

  private fun buildMethodModel(
    fn: KSFunctionDeclaration,
    ann: com.google.devtools.ksp.symbol.KSAnnotation,
    ownerSimpleName: String,
  ): MethodModel {
    val kotlinName = fn.simpleName.asString()
    val nameOverride = ann.arguments.firstOrNull { it.name?.asString() == "name" }?.value as? String
    val godotName = if (nameOverride.isNullOrEmpty()) camelToSnake(kotlinName) else nameOverride

    val returnType =
      fn.returnType?.resolve()?.let { type ->
        val fq = type.declaration.qualifiedName?.asString()
        if (fq == "kotlin.Unit") null
        else
          fqToTypeMapping(fq)
            ?: throw IllegalArgumentException(
              "$ownerSimpleName.$kotlinName: unsupported return type '$fq'"
            )
      }

    val args =
      fn.parameters.map { p ->
        val name = p.name?.asString() ?: "arg"
        val type = p.type.resolve()
        val arg =
          fqToArgModel(name, type)
            ?: throw IllegalArgumentException(
              "$ownerSimpleName.$kotlinName: unsupported parameter type '${type.declaration.qualifiedName?.asString()}' for '$name'"
            )
        arg.copy(hasDefault = p.hasDefault)
      }
    val firstDefault = args.indexOfFirst { it.hasDefault }
    if (firstDefault >= 0 && args.drop(firstDefault).any { !it.hasDefault }) {
      throw IllegalArgumentException(
        "$ownerSimpleName.$kotlinName: @RegisterFunction default arguments must be trailing"
      )
    }

    return MethodModel(
      kotlinName = kotlinName,
      godotName = godotName,
      returnType = returnType,
      args = args,
      kind = MethodKind.REGULAR,
      rpc = buildRpcModel(fn),
    )
  }

  private fun buildRpcModel(fn: KSFunctionDeclaration): RpcModel? {
    val ann = fn.annotations.firstOrNull { it.shortName.asString() == "Rpc" } ?: return null
    val mode = ann.arguments.firstOrNull { it.name?.asString() == "mode" }?.value as? Int ?: 2
    val callLocal =
      ann.arguments.firstOrNull { it.name?.asString() == "callLocal" }?.value as? Boolean ?: false
    val transferMode =
      ann.arguments.firstOrNull { it.name?.asString() == "transferMode" }?.value as? Int ?: 2
    val channel = ann.arguments.firstOrNull { it.name?.asString() == "channel" }?.value as? Int ?: 0
    return RpcModel(mode, callLocal, transferMode, channel)
  }

  private fun buildPropertyModel(
    prop: KSPropertyDeclaration,
    ownerSimpleName: String,
  ): PropertyModel {
    val kotlinName = prop.simpleName.asString()
    val ann =
      prop.annotations.firstOrNull {
        it.shortName.asString() == "RegisterProperty" || it.shortName.asString() == "Export"
      }
    val nameOverride =
      ann?.arguments?.firstOrNull { it.name?.asString() == "name" }?.value as? String
    val godotName = if (nameOverride.isNullOrEmpty()) camelToSnake(kotlinName) else nameOverride
    val fq = prop.type.resolve().declaration.qualifiedName?.asString()
    val type =
      fqToTypeMapping(fq)
        ?: throw IllegalArgumentException(
          "$ownerSimpleName.$kotlinName: unsupported property type '$fq'"
        )
    val hint = ann?.arguments?.firstOrNull { it.name?.asString() == "hint" }?.value as? Int ?: 0
    val hintString =
      ann?.arguments?.firstOrNull { it.name?.asString() == "hintString" }?.value as? String ?: ""
    val usage = ann?.arguments?.firstOrNull { it.name?.asString() == "usage" }?.value as? Int ?: 6
    return PropertyModel(
      kotlinName = kotlinName,
      godotName = godotName,
      type = type,
      isMutable = prop.isMutable,
      hint = hint,
      hintString = hintString,
      usage = usage,
    )
  }

  // ---------- Emission ----------

  private fun emitRegistrar(model: ClassModel, sourceFile: KSFile) {
    // @RegisterClass emits JVM-only ClassDB registration; no iOS counterpart yet.
    if (!emitJvmCode) return
    val registrarName = "${model.simpleName}Registrar"
    val source = CodeEmitter(model, registrarName).emit()

    env.codeGenerator
      .createNewFile(
        dependencies = Dependencies(aggregating = false, sourceFile),
        packageName = GENERATED_PACKAGE,
        fileName = registrarName,
      )
      .use { it.write(source.toByteArray(Charsets.UTF_8)) }

    env.logger.warn(
      "[kanama:ksp] generated $GENERATED_PACKAGE.$registrarName " +
        "for ${model.fqName} " +
        "(parent=${model.parentClassName}, methods=${model.methods.size}, " +
        "properties=${model.properties.size}, virtuals=${model.virtuals.size}, " +
        "signals=${model.signals.size})"
    )
  }

  private fun buildScriptModel(cls: KSClassDeclaration, fqName: String): ScriptModel {
    val simpleName = cls.simpleName.asString()
    failOnGeneratedWrapperSupertype(cls, simpleName)
    val attachTo =
      cls.annotations
        .firstOrNull { it.shortName.asString() == "ScriptClass" }
        ?.arguments
        ?.firstOrNull { it.name?.asString() == "attachTo" }
        ?.value as? String ?: "Node"
    val isTool = cls.annotations.any { it.shortName.asString() == "Tool" }
    val isGlobalClass =
      cls.annotations.any {
        val name = it.shortName.asString()
        name == "GlobalClass" || name == "ClassName"
      }
    val fileName = cls.containingFile?.fileName
    if (isGlobalClass && fileName != null && fileName != "$simpleName.kt") {
      // The runtime maps a script path back to its global class by the
      // <ClassName>.kt convention (KanamaScript.inferGlobalClassNameFromPath);
      // a mismatched file name silently drops the class from the editor's
      // global class list (Create Resource dialog, typed-slot matching).
      env.logger.warn(
        "[kanama:ksp] $simpleName: @GlobalClass requires the file to be named " +
          "$simpleName.kt (found $fileName); the class will not register as a " +
          "global class in the editor.",
        cls,
      )
    }
    warnOnKanamaScriptSelfMismatch(cls, attachTo)

    val properties = mutableListOf<ScriptPropertyModel>()
    val virtuals = mutableListOf<VirtualModel>()
    val methods = mutableListOf<MethodModel>()
    val signals = mutableListOf<SignalModel>()
    val toolButtons = mutableListOf<ToolButtonModel>()

    for (fn in cls.getDeclaredFunctions()) {
      if (!fn.isPublic()) continue
      val annotationNames = fn.annotations.map { it.shortName.asString() }.toSet()
      warnOnLikelyUnregisteredSceneCallback(cls, fn, annotationNames)
      if (
        "Rpc" in annotationNames &&
          annotationNames.none { it == "RegisterFunction" || it == "Method" }
      ) {
        throw IllegalArgumentException(
          "$simpleName.${fn.simpleName.asString()}: @Rpc requires @RegisterFunction/@Method"
        )
      }
      if (
        annotationNames.any { it == "ToolButton" || it == "ExportToolButton" } &&
          annotationNames.any { it == "RegisterFunction" || it == "Method" }
      ) {
        throw IllegalArgumentException(
          "$simpleName.${fn.simpleName.asString()}: @ToolButton cannot be combined with @RegisterFunction/@Method"
        )
      }
      for (ann in fn.annotations) {
        val kotlinName = fn.simpleName.asString()
        when (ann.shortName.asString()) {
          "OnReady",
          "Ready" -> virtuals += VirtualModel("_ready", kotlinName, kotlinName)
          "OnEnterTree",
          "EnterTree" -> virtuals += VirtualModel("_enter_tree", kotlinName, kotlinName)
          "OnExitTree",
          "ExitTree" -> virtuals += VirtualModel("_exit_tree", kotlinName, kotlinName)
          "OnProcess",
          "Process" ->
            virtuals +=
              VirtualModel(
                "_process",
                kotlinName,
                kotlinName,
                args = listOf(ArgModel("delta", TypeMapping.FLOAT)),
              )
          "OnPhysicsProcess",
          "PhysicsProcess" ->
            virtuals +=
              VirtualModel(
                "_physics_process",
                kotlinName,
                kotlinName,
                args = listOf(ArgModel("delta", TypeMapping.FLOAT)),
              )
          "OnInput",
          "Input" ->
            virtuals +=
              VirtualModel(
                "_input",
                kotlinName,
                kotlinName,
                args =
                  listOf(
                    ArgModel("event", TypeMapping.OBJECT, "net.multigesture.kanama.api.GodotObject")
                  ),
              )
          "OnUnhandledInput",
          "UnhandledInput" ->
            virtuals +=
              VirtualModel(
                "_unhandled_input",
                kotlinName,
                kotlinName,
                args =
                  listOf(
                    ArgModel("event", TypeMapping.OBJECT, "net.multigesture.kanama.api.GodotObject")
                  ),
              )
          "OnShortcutInput",
          "ShortcutInput" ->
            virtuals +=
              VirtualModel(
                "_shortcut_input",
                kotlinName,
                kotlinName,
                args =
                  listOf(
                    ArgModel("event", TypeMapping.OBJECT, "net.multigesture.kanama.api.GodotObject")
                  ),
              )
          "OnUnhandledKeyInput",
          "UnhandledKeyInput" ->
            virtuals +=
              VirtualModel(
                "_unhandled_key_input",
                kotlinName,
                kotlinName,
                args =
                  listOf(
                    ArgModel("event", TypeMapping.OBJECT, "net.multigesture.kanama.api.GodotObject")
                  ),
              )
          "OverrideVirtual" -> virtuals += buildVirtualOverrideModel(fn, attachTo, simpleName)
          "RegisterFunction",
          "Method" -> methods += buildMethodModel(fn, ann, simpleName)
          "Signal" -> signals += buildSignalModel(fn, ann, simpleName)
          "ToolButton",
          "ExportToolButton" -> {
            if (!isTool) {
              throw IllegalArgumentException(
                "$simpleName.$kotlinName: @ToolButton requires @Tool on the script class"
              )
            }
            val button = buildToolButtonModel(fn, ann, simpleName)
            toolButtons += button
            methods += button.method
          }
        }
      }
    }

    for (prop in cls.getDeclaredProperties()) {
      if (
        prop.annotations.none {
          it.shortName.asString() == "ScriptProperty" || it.shortName.asString() == "Export"
        }
      )
        continue
      val kotlinName = prop.simpleName.asString()
      if (Modifier.LATEINIT in prop.modifiers) {
        // A lateinit export has no inspector default, and a get before the field
        // is assigned throws UninitializedPropertyAccessException into the engine.
        env.logger.warn(
          "[kanama:ksp] $simpleName.$kotlinName: lateinit @ScriptProperty has no default and " +
            "crashes if Godot reads it before assignment; prefer a nullable type with '= null'.",
          prop,
        )
      }
      val ann =
        prop.annotations.firstOrNull {
          it.shortName.asString() == "ScriptProperty" || it.shortName.asString() == "Export"
        }
      val nameOverride =
        ann?.arguments?.firstOrNull { it.name?.asString() == "name" }?.value as? String
      val godotName = if (nameOverride.isNullOrEmpty()) camelToSnake(kotlinName) else nameOverride
      val resolvedType = prop.type.resolve()
      val fq = resolvedType.declaration.qualifiedName?.asString()
      // On the iOS (Kotlin/Native) target a @ScriptProperty may reference an API wrapper
      // that exists only in the desktop source set (the hand-curated iosMain api/ subset is
      // smaller), so its type fails to resolve here. Degrade gracefully — skip the property
      // with a warning, like the old regex path did — instead of failing the whole script.
      // On the JVM target an unresolved type is a real user error and still throws.
      val scriptType =
        try {
          scriptPropertyTypeModel(resolvedType, simpleName, kotlinName, scriptClassTypes)
        } catch (e: IllegalArgumentException) {
          if (emitJvmCode) throw e
          env.logger.warn(
            "[kanama:ksp] $simpleName.$kotlinName: ${e.message}; skipping on iOS (type not available on Kotlin/Native)"
          )
          continue
        }
      val hint = ann?.arguments?.firstOrNull { it.name?.asString() == "hint" }?.value as? Int ?: 0
      val hintString =
        ann?.arguments?.firstOrNull { it.name?.asString() == "hintString" }?.value as? String ?: ""
      val usage = ann?.arguments?.firstOrNull { it.name?.asString() == "usage" }?.value as? Int ?: 6
      val exportCategory =
        prop.annotations
          .firstOrNull { it.shortName.asString() == "ExportCategory" }
          ?.let { category ->
            ScriptPropertyGroupModel(
              name =
                category.arguments.firstOrNull { it.name?.asString() == "name" }?.value as? String
                  ?: "",
              prefix = "",
              usage = PROPERTY_USAGE_CATEGORY,
            )
          }
      val defaultLiteral =
        scriptPropertyDefaultLiteral(
          prop,
          scriptType.type,
          scriptType.narrow,
          scriptType.enumFqName,
          scriptType.enumEntries,
        )
      val exportGroup =
        prop.annotations
          .firstOrNull { it.shortName.asString() == "ExportGroup" }
          ?.let { group ->
            ScriptPropertyGroupModel(
              name =
                group.arguments.firstOrNull { it.name?.asString() == "name" }?.value as? String
                  ?: "",
              prefix =
                group.arguments.firstOrNull { it.name?.asString() == "prefix" }?.value as? String
                  ?: "",
              usage = PROPERTY_USAGE_GROUP,
            )
          }
      val exportSubgroup =
        prop.annotations
          .firstOrNull { it.shortName.asString() == "ExportSubgroup" }
          ?.let { group ->
            ScriptPropertyGroupModel(
              name =
                group.arguments.firstOrNull { it.name?.asString() == "name" }?.value as? String
                  ?: "",
              prefix =
                group.arguments.firstOrNull { it.name?.asString() == "prefix" }?.value as? String
                  ?: "",
              usage = PROPERTY_USAGE_SUBGROUP,
            )
          }
      properties +=
        ScriptPropertyModel(
          kotlinName,
          godotName,
          scriptType.type,
          prop.isMutable,
          if (hint == 0) scriptType.hint else hint,
          if (hintString.isEmpty()) scriptType.hintString else hintString,
          defaultLiteral,
          exportCategory,
          exportGroup,
          exportSubgroup,
          usage,
          scriptType.objectWrapperFqName,
          scriptType.arrayElementWrapperFqName,
          scriptType.customScriptFqName,
          scriptType.arrayElementCustomScriptFqName,
          scriptType.customScriptIsResource,
          scriptType.arrayElementCustomScriptIsResource,
          scriptType.arrayElementString,
          resolvedType.nullability == Nullability.NULLABLE,
          scriptType.narrow,
          scriptType.enumFqName,
          scriptType.enumEntries,
          scriptType.arrayElementEnumFqName,
          scriptType.arrayElementEnumEntries,
          scriptType.mapKeyKotlinType,
          scriptType.mapKeyEnumEntries,
          scriptType.mapValueKotlinType,
          scriptType.mapValueWrapperFqName,
          scriptType.mapValueCustomScriptFqName,
          scriptType.mapValueCustomScriptIsResource,
          scriptType.mapValueEnumFqName,
          scriptType.mapValueEnumEntries,
          scriptType.mapValueNullable,
          scriptType.isMutableMap,
          scriptType.isMutableList,
        )
    }

    return ScriptModel(
      simpleName,
      fqName,
      attachTo,
      isTool,
      isGlobalClass,
      properties,
      toolButtons,
      virtuals,
      methods,
      signals,
    )
  }

  private fun buildToolButtonModel(
    fn: KSFunctionDeclaration,
    ann: com.google.devtools.ksp.symbol.KSAnnotation,
    ownerSimpleName: String,
  ): ToolButtonModel {
    val kotlinName = fn.simpleName.asString()
    if (fn.parameters.isNotEmpty()) {
      throw IllegalArgumentException(
        "$ownerSimpleName.$kotlinName: @ToolButton functions must not take parameters"
      )
    }
    val returnFq = fn.returnType?.resolve()?.declaration?.qualifiedName?.asString()
    if (returnFq != null && returnFq != "kotlin.Unit") {
      throw IllegalArgumentException(
        "$ownerSimpleName.$kotlinName: @ToolButton functions must return Unit"
      )
    }
    val sourceArgs = toolButtonAnnotationArgsFromSource(fn)
    val nameOverride =
      (ann.arguments.firstOrNull { it.name?.asString() == "name" }?.value as? String)
        ?: sourceArgs.name
    val methodName = camelToSnake(kotlinName)
    val propertyName = if (nameOverride.isNullOrEmpty()) "${methodName}_button" else nameOverride
    val text =
      (ann.arguments.firstOrNull { it.name?.asString() == "text" }?.value as? String)
        ?: (ann.arguments.firstOrNull { it.name?.asString() == "value" }?.value as? String)
        ?: (ann.arguments.firstOrNull()?.value as? String)
        ?: sourceArgs.text
        ?: humanizeGodotName(methodName)
    val icon =
      (ann.arguments.firstOrNull { it.name?.asString() == "icon" }?.value as? String)
        ?: sourceArgs.icon
        ?: ""
    return ToolButtonModel(
      propertyName = propertyName,
      text = text.ifEmpty { propertyName },
      icon = icon,
      method =
        MethodModel(
          kotlinName = kotlinName,
          godotName = methodName,
          returnType = null,
          args = emptyList(),
          kind = MethodKind.REGULAR,
        ),
    )
  }

  // Generated wrappers are non-owning views with internal constructors — subclassing one
  // would drift the wrapper surface from the script surface (issue #36). Without this check
  // the user only sees Kotlin's "cannot access '<init>': it is internal" error, which does
  // not name the supported pattern.
  private fun failOnGeneratedWrapperSupertype(cls: KSClassDeclaration, simpleName: String) {
    for (superTypeRef in cls.superTypes) {
      val superDecl = runCatching { superTypeRef.resolve() }.getOrNull()?.declaration ?: continue
      val superFq = superDecl.qualifiedName?.asString() ?: continue
      if (!superFq.startsWith("net.multigesture.kanama.api.")) continue
      // KanamaScript is the supported script base; api-package interfaces
      // (e.g. KanamaCoroutineOwner) are implementable by design.
      if (superFq == KANAMA_API_SCRIPT_FQN) continue
      if ((superDecl as? KSClassDeclaration)?.classKind != ClassKind.CLASS) continue
      val wrapperName = superDecl.simpleName.asString()
      throw IllegalArgumentException(
        "$simpleName extends the generated wrapper $superFq. Generated wrappers are " +
          "non-owning views and cannot be subclassed. Declare a plain class and attach " +
          "it instead: @ScriptClass(attachTo = \"$wrapperName\") @GlobalClass " +
          "class $simpleName(val godotObject: MemorySegment) { @Export var ... }. " +
          "See the \"Custom Resources\" section of docs/game-dev/properties-resources.md."
      )
    }
  }

  private fun warnOnKanamaScriptSelfMismatch(cls: KSClassDeclaration, attachTo: String) {
    for (superTypeRef in cls.superTypes) {
      val superType = runCatching { superTypeRef.resolve() }.getOrNull() ?: continue
      val superFqName = superType.declaration.qualifiedName?.asString() ?: continue
      if (superFqName != KANAMA_API_SCRIPT_FQN) continue

      val selfType = superType.arguments.firstOrNull()?.type?.resolve() ?: continue
      val selfName = selfType.declaration.simpleName.asString()
      if (selfName == attachTo) return

      env.logger.warn(
        "[kanama:ksp] ${cls.simpleName.asString()} extends KanamaScript<$selfName> " +
          "but @ScriptClass attaches to $attachTo. Prefer " +
          "KanamaScript<$attachTo>(godotObject, ::$attachTo) when `self` should match " +
          "the Godot base class.",
        cls,
      )
      return
    }
  }

  private fun warnOnLikelyUnregisteredSceneCallback(
    cls: KSClassDeclaration,
    fn: KSFunctionDeclaration,
    annotationNames: Set<String>,
  ) {
    val callableAnnotations =
      setOf(
        "RegisterFunction",
        "Method",
        "OnReady",
        "Ready",
        "OnEnterTree",
        "EnterTree",
        "OnExitTree",
        "ExitTree",
        "OnProcess",
        "Process",
        "OnPhysicsProcess",
        "PhysicsProcess",
        "OnInput",
        "Input",
        "OnUnhandledInput",
        "UnhandledInput",
        "OnShortcutInput",
        "ShortcutInput",
        "OnUnhandledKeyInput",
        "UnhandledKeyInput",
        "Signal",
      )
    if (annotationNames.any { it in callableAnnotations }) return

    val kotlinName = fn.simpleName.asString()
    if (!kotlinName.startsWith("on") || kotlinName.length <= 2 || !kotlinName[2].isUpperCase())
      return

    env.logger.warn(
      "[kanama:ksp] ${cls.simpleName.asString()}.$kotlinName looks like a scene signal callback " +
        "but is not exposed to Godot. Saved .tscn connections require " +
        "@RegisterFunction(\"_${camelToSnake(kotlinName)}\") or an explicit matching method name.",
      fn,
    )
  }

  private fun emitScriptRegistrar(model: ScriptModel, sourceFile: KSFile) {
    // Phase 3.1: always emit the platform-neutral serialized model. The iOS build
    // (Option B) consumes these instead of regex-parsing the source; on the JVM target
    // it is an additive artifact alongside the registrar. See
    // script-model-unification-design.md.
    env.codeGenerator
      .createNewFile(
        dependencies = Dependencies(aggregating = false, sourceFile),
        packageName = GENERATED_PACKAGE,
        fileName = "${model.simpleName}ScriptModel",
        extensionName = "script-model.json",
      )
      .use { it.write(scriptModelToJson(model).toByteArray(Charsets.UTF_8)) }

    // The JVM registrar (MemorySegment/Panama) only compiles on the JVM target.
    if (!emitJvmCode) return
    val registrarName = "${model.simpleName}ScriptRegistrar"
    val source = ScriptCodeEmitter(model, registrarName).emit()

    env.codeGenerator
      .createNewFile(
        dependencies = Dependencies(aggregating = false, sourceFile),
        packageName = GENERATED_PACKAGE,
        fileName = registrarName,
      )
      .use { it.write(source.toByteArray(Charsets.UTF_8)) }

    env.logger.warn(
      "[kanama:ksp] generated $GENERATED_PACKAGE.$registrarName " +
        "for ${model.fqName} " +
        "(attachTo=${model.attachTo}, props=${model.properties.size}, " +
        "virtuals=${model.virtuals.size}, methods=${model.methods.size}, signals=${model.signals.size})"
    )
  }

  // ---------- iOS @ScriptClass registry emission (Kotlin/Native target, Phase 3.2) ----------

  private fun emitIosScriptRegistry() {
    // Always emit the registry (even with zero scripts) so the `actual` always exists for the
    // `expect registerKanamaIosProjectScripts()` in the shared iosMain source set.
    // Gate phase: emit as `.kt.txt` resources (not compiled) so the regex-generated registry
    // still drives the build and a verify task can diff the two. Cutover flips this to `.kt`
    // via -Pkanama…=false → the KSP option below. Default stays resource until the gate passes.
    val asResource = env.options["kanamaIosRegistryAsResource"]?.toBoolean() ?: true
    val extension = if (asResource) "kt.txt" else "kt"
    val emitter =
      IosScriptCodeEmitter(
        iosScripts,
        warn = { env.logger.warn("[kanama:ksp] $it") },
        error = { env.logger.error("[kanama:ksp] $it") },
      )
    val deps = Dependencies(aggregating = true, *scriptAggregatorSources.toTypedArray())

    fun write(packageName: String, fileName: String, content: String) {
      env.codeGenerator
        .createNewFile(
          dependencies = deps,
          packageName = packageName,
          fileName = fileName,
          extensionName = extension,
        )
        .use { it.write(content.toByteArray(Charsets.UTF_8)) }
    }

    write(
      "net.multigesture.kanama.ios",
      "KanamaIosProjectRegistry.generated",
      emitter.registrySource(),
    )
    write(
      "net.multigesture.kanama.generated",
      "KanamaIosScriptConstants.generated",
      emitter.constantsSource(),
    )
    emitter.compatibilitySources().forEach { (relativePath, source) ->
      // relativePath = "<pkg as dirs>/KanamaIosCompatibility.generated.kt"
      val packageName = relativePath.substringBeforeLast('/').replace('/', '.')
      write(packageName, "KanamaIosCompatibility.generated", source)
    }
    env.logger.warn(
      "[kanama:ksp] generated iOS @ScriptClass registry for ${iosScripts.size} script(s) " +
        "(asResource=$asResource)"
    )
  }

  // ---------- Web @ScriptClass registry/proxy emission (Kotlin/Wasm target, task 57) ----------

  private fun emitWebScriptRegistry() {
    val emitter = WebScriptCodeEmitter(webScripts)
    val dependencies = Dependencies(aggregating = true, *scriptAggregatorSources.toTypedArray())
    env.codeGenerator
      .createNewFile(
        dependencies = dependencies,
        packageName = "net.multigesture.kanama.web.generated",
        fileName = "KanamaWebProjectRegistry.generated",
      )
      .use { it.write(emitter.registrySource().toByteArray(Charsets.UTF_8)) }
    env.codeGenerator
      .createNewFile(
        dependencies = dependencies,
        packageName = "net.multigesture.kanama.generated",
        fileName = "KanamaWebScriptConstants.generated",
      )
      .use { it.write(emitter.constantsSource().toByteArray(Charsets.UTF_8)) }
    emitter.compatibilitySources().forEach { (packageName, source) ->
      env.codeGenerator
        .createNewFile(
          dependencies = dependencies,
          packageName = packageName,
          fileName = "KanamaWebCompatibility.generated",
        )
        .use { it.write(source.toByteArray(Charsets.UTF_8)) }
    }
    emitter.proxySources().forEach { proxy ->
      env.codeGenerator
        .createNewFile(
          dependencies = dependencies,
          packageName = "net.multigesture.kanama.web.generated.proxies",
          fileName = proxy.fileName,
          extensionName = "gd",
        )
        .use { it.write(proxy.source.toByteArray(Charsets.UTF_8)) }
    }
    env.codeGenerator
      .createNewFile(
        dependencies = dependencies,
        packageName = "net.multigesture.kanama.web.generated.proxies",
        fileName = "KanamaWebProxyManifest.generated",
        extensionName = "tsv",
      )
      .use { it.write(emitter.proxyManifest().toByteArray(Charsets.UTF_8)) }
    env.codeGenerator
      .createNewFile(
        dependencies = dependencies,
        packageName = "net.multigesture.kanama.web.generated.proxies",
        fileName = "KanamaWebProtocol.generated",
        extensionName = "json",
      )
      .use { it.write(emitter.protocolManifest().toByteArray(Charsets.UTF_8)) }
    env.logger.warn(
      "[kanama:ksp] generated Web @ScriptClass registry for ${webScripts.size} script(s)"
    )
  }

  companion object {
    private const val GENERATED_PACKAGE = "net.multigesture.kanama.generated"
    private const val REGISTER_CLASS_FQN = "net.multigesture.kanama.annotations.RegisterClass"
    private const val SCRIPT_CLASS_FQN = "net.multigesture.kanama.annotations.ScriptClass"
    private const val KANAMA_API_SCRIPT_FQN = "net.multigesture.kanama.api.KanamaScript"

    fun camelToSnake(name: String): String = buildString {
      for ((i, ch) in name.withIndex()) {
        if (i > 0 && ch.isUpperCase()) append('_')
        append(ch.lowercaseChar())
      }
    }

    fun capitalize(s: String): String =
      if (s.isEmpty()) s else s[0].uppercaseChar() + s.substring(1)

    // Return-type resolution for @OverrideVirtual, extending fqToTypeMapping with the generic
    // container returns that carry a type argument (task 13) and the remaining return families
    // (task 29). `List<T>` element type selects the packed family (String -> PackedStringArray,
    // Vector2/Vector3/Color -> the matching Packed*Array, Any -> generic Array — also the shape
    // for the engine's typedarray::* returns); Kotlin primitive arrays are the width-unambiguous
    // int/float packed families; `Map<String, *>` is a Dictionary. Anything else falls through
    // to the scalar/POD fqToTypeMapping.
    internal fun virtualReturnTypeMapping(type: KSType, fq: String?): TypeMapping? {
      if (fq == "kotlin.collections.List" || fq == "kotlin.collections.MutableList") {
        val elementFq =
          type.arguments.firstOrNull()?.type?.resolve()?.declaration?.qualifiedName?.asString()
        return when (elementFq) {
          "kotlin.String" -> TypeMapping.PACKED_STRING_ARRAY
          "net.multigesture.kanama.types.Vector2" -> TypeMapping.PACKED_VECTOR2_ARRAY
          "net.multigesture.kanama.types.Vector3" -> TypeMapping.PACKED_VECTOR3_ARRAY
          "net.multigesture.kanama.types.Color" -> TypeMapping.PACKED_COLOR_ARRAY
          "kotlin.Any" -> TypeMapping.ARRAY
          else -> null
        }
      }
      if (fq == "kotlin.collections.Map" || fq == "kotlin.collections.MutableMap") {
        val keyFq =
          type.arguments.firstOrNull()?.type?.resolve()?.declaration?.qualifiedName?.asString()
        // String keys only — the same audited policy as the non-virtual Dictionary helpers.
        return if (keyFq == "kotlin.String") TypeMapping.DICTIONARY else null
      }
      // `Any`/`Any?` return -> Variant (the flexible catch-all; e.g. Control._get_drag_data).
      if (fq == "kotlin.Any") return TypeMapping.VARIANT
      return when (fq) {
        "kotlin.ByteArray" -> TypeMapping.PACKED_BYTE_ARRAY
        "kotlin.IntArray" -> TypeMapping.PACKED_INT32_ARRAY
        "kotlin.LongArray" -> TypeMapping.PACKED_INT64_ARRAY
        "kotlin.FloatArray" -> TypeMapping.PACKED_FLOAT32_ARRAY
        "kotlin.DoubleArray" -> TypeMapping.PACKED_FLOAT64_ARRAY
        "net.multigesture.kanama.types.RID" -> TypeMapping.RID
        "net.multigesture.kanama.types.Rect2" -> TypeMapping.RECT2
        "net.multigesture.kanama.types.AABB" -> TypeMapping.AABB
        "net.multigesture.kanama.types.Transform2D" -> TypeMapping.TRANSFORM2D
        "net.multigesture.kanama.types.Transform3D" -> TypeMapping.TRANSFORM3D
        "net.multigesture.kanama.types.Projection" -> TypeMapping.PROJECTION
        else -> fqToTypeMapping(fq)
      }
    }

    internal fun fqToTypeMapping(fq: String?): TypeMapping? =
      when (fq) {
        "kotlin.Long" -> TypeMapping.INT
        "kotlin.Double" -> TypeMapping.FLOAT
        "kotlin.Boolean" -> TypeMapping.BOOL
        "kotlin.String" -> TypeMapping.STRING
        "net.multigesture.kanama.types.Vector2" -> TypeMapping.VECTOR2
        "net.multigesture.kanama.types.Vector2i" -> TypeMapping.VECTOR2I
        "net.multigesture.kanama.types.Vector3" -> TypeMapping.VECTOR3
        "net.multigesture.kanama.types.Vector3i" -> TypeMapping.VECTOR3I
        "net.multigesture.kanama.types.Quaternion" -> TypeMapping.QUATERNION
        "net.multigesture.kanama.types.Basis" -> TypeMapping.BASIS
        "net.multigesture.kanama.types.NodePath" -> TypeMapping.NODE_PATH
        "net.multigesture.kanama.api.GodotObject" -> TypeMapping.OBJECT
        else -> null
      }

    internal fun fqToArgModel(name: String, type: KSType): ArgModel? {
      val fq = type.declaration.qualifiedName?.asString()
      val nullable = type.nullability == Nullability.NULLABLE
      val objectWrapper = fq?.takeIf { it in SUPPORTED_OBJECT_WRAPPERS }
      return if (objectWrapper != null) {
        ArgModel(name, TypeMapping.OBJECT, objectWrapper, nullable = nullable)
      } else {
        fqToTypeMapping(fq)?.let { ArgModel(name, it, nullable = nullable) }
      }
    }

    private val SUPPORTED_OBJECT_WRAPPERS =
      setOf(
        "net.multigesture.kanama.api.GodotObject",
        "net.multigesture.kanama.api.Node",
        "net.multigesture.kanama.api.Node2D",
        "net.multigesture.kanama.api.Node3D",
        "net.multigesture.kanama.api.Control",
        "net.multigesture.kanama.api.ColorRect",
        "net.multigesture.kanama.api.CanvasLayer",
        "net.multigesture.kanama.api.Label",
        "net.multigesture.kanama.api.Button",
        "net.multigesture.kanama.api.TextureRect",
        "net.multigesture.kanama.api.Area2D",
        "net.multigesture.kanama.api.Area3D",
        "net.multigesture.kanama.api.PhysicsBody3D",
        "net.multigesture.kanama.api.CharacterBody3D",
        "net.multigesture.kanama.api.RigidBody3D",
        "net.multigesture.kanama.api.StaticBody3D",
        "net.multigesture.kanama.api.VehicleBody3D",
        "net.multigesture.kanama.api.VehicleWheel3D",
        "net.multigesture.kanama.api.Sprite2D",
        "net.multigesture.kanama.api.SpriteBase3D",
        "net.multigesture.kanama.api.Sprite3D",
        "net.multigesture.kanama.api.AnimatedSprite3D",
        "net.multigesture.kanama.api.Camera3D",
        "net.multigesture.kanama.api.Marker3D",
        "net.multigesture.kanama.api.SubViewport",
        "net.multigesture.kanama.api.GridMap",
        "net.multigesture.kanama.api.RayCast3D",
        "net.multigesture.kanama.api.MeshInstance3D",
        "net.multigesture.kanama.api.CollisionShape3D",
        "net.multigesture.kanama.api.AnimationPlayer",
        "net.multigesture.kanama.api.AudioStreamPlayer",
        "net.multigesture.kanama.api.AudioStreamPlayer3D",
        "net.multigesture.kanama.api.GPUParticles2D",
        "net.multigesture.kanama.api.GPUParticles3D",
        "net.multigesture.kanama.api.CPUParticles3D",
        "net.multigesture.kanama.api.Timer",
        "net.multigesture.kanama.api.WorldEnvironment",
        "net.multigesture.kanama.api.PackedScene",
        "net.multigesture.kanama.api.Texture2D",
        "net.multigesture.kanama.api.NoiseTexture2D",
        "net.multigesture.kanama.api.ShaderMaterial",
        "net.multigesture.kanama.api.Curve",
        "net.multigesture.kanama.api.LightmapGIData",
        "net.multigesture.kanama.api.Material",
        "net.multigesture.kanama.api.ButtonGroup",
        "net.multigesture.kanama.api.FastNoiseLite",
        // task 33 (issue #36): common resource-slot base classes.
        "net.multigesture.kanama.api.AudioStream",
        "net.multigesture.kanama.api.Texture",
        "net.multigesture.kanama.api.Mesh",
        "net.multigesture.kanama.api.Shape3D",
        "net.multigesture.kanama.api.Shape2D",
        "net.multigesture.kanama.api.Font",
        "net.multigesture.kanama.api.Animation",
        "net.multigesture.kanama.api.StyleBox",
      )

    private fun scriptPropertyTypeModel(
      type: KSType,
      className: String,
      propertyName: String,
      scriptClassTypes: Map<String, ScriptClassTypeInfo>,
    ): ScriptPropertyTypeModel {
      val fq = type.declaration.qualifiedName?.asString()
      // Narrow Kotlin scalars widen to Godot's 64-bit Variant slots; the
      // JVM emitter inserts the conversions at get/set.
      when (fq) {
        "kotlin.Float" ->
          return ScriptPropertyTypeModel(TypeMapping.FLOAT, narrow = NarrowScalar.FLOAT32)
        "kotlin.Int" -> return ScriptPropertyTypeModel(TypeMapping.INT, narrow = NarrowScalar.INT32)
      }
      // Kotlin enum class (task 32, issue #37): C# export parity — an INT Variant
      // slot carrying the ordinal, registered with PROPERTY_HINT_ENUM and the entry
      // names so the inspector renders a dropdown.
      val enumDecl =
        (type.declaration as? KSClassDeclaration)?.takeIf { it.classKind == ClassKind.ENUM_CLASS }
      if (enumDecl != null && fq != null) {
        val entries =
          enumDecl.declarations
            .filterIsInstance<KSClassDeclaration>()
            .filter { it.classKind == ClassKind.ENUM_ENTRY }
            .map { it.simpleName.asString() }
            .toList()
        if (entries.isEmpty()) {
          throw IllegalArgumentException(
            "$className.$propertyName: enum class '$fq' has no entries to export"
          )
        }
        return ScriptPropertyTypeModel(
          type = TypeMapping.INT,
          hint = PROPERTY_HINT_ENUM,
          hintString = entries.joinToString(","),
          enumFqName = fq,
          enumEntries = entries,
        )
      }
      val objectWrapper = fq?.takeIf { it in SUPPORTED_OBJECT_WRAPPERS }
      if (objectWrapper != null) {
        return ScriptPropertyTypeModel(
          type = TypeMapping.OBJECT,
          hint =
            when (objectWrapper) {
              in SUPPORTED_RESOURCE_WRAPPERS -> PROPERTY_HINT_RESOURCE_TYPE
              in SUPPORTED_NODE_WRAPPERS -> PROPERTY_HINT_NODE_TYPE
              else -> 0
            },
          hintString =
            godotClassName(objectWrapper).takeIf {
              objectWrapper in SUPPORTED_RESOURCE_WRAPPERS ||
                objectWrapper in SUPPORTED_NODE_WRAPPERS
            } ?: "",
          objectWrapperFqName = objectWrapper,
        )
      }
      val customScript = fq?.let { scriptClassTypes[it] }?.takeIf { it.isExportableGlobal }
      if (customScript != null) {
        return ScriptPropertyTypeModel(
          type = TypeMapping.OBJECT,
          hint = customScript.propertyHint,
          hintString = customScript.simpleName,
          customScriptFqName = customScript.fqName,
          customScriptIsResource = customScript.isExportableResource,
        )
      }

      if (fq == "kotlin.collections.List" || fq == "kotlin.collections.MutableList") {
        val isMutable = fq == "kotlin.collections.MutableList"
        val elementDecl = type.arguments.firstOrNull()?.type?.resolve()?.declaration
        val elementFq = elementDecl?.qualifiedName?.asString()
        if (elementFq == "kotlin.String") {
          return ScriptPropertyTypeModel(
            type = TypeMapping.ARRAY,
            hint = PROPERTY_HINT_TYPE_STRING,
            hintString = "4:",
            arrayElementString = true,
            isMutableList = isMutable,
          )
        }
        val elementWrapper = elementFq?.takeIf { it in SUPPORTED_OBJECT_WRAPPERS }
        if (elementWrapper != null) {
          val elementHint =
            if (elementWrapper in SUPPORTED_RESOURCE_WRAPPERS) {
              "24/${PROPERTY_HINT_RESOURCE_TYPE}:${godotClassName(elementWrapper)}"
            } else {
              "24:"
            }
          return ScriptPropertyTypeModel(
            type = TypeMapping.ARRAY,
            hint = PROPERTY_HINT_TYPE_STRING,
            hintString = elementHint,
            arrayElementWrapperFqName = elementWrapper,
            isMutableList = isMutable,
          )
        }
        val customElement =
          elementFq?.let { scriptClassTypes[it] }?.takeIf { it.isExportableGlobal }
        if (customElement != null) {
          return ScriptPropertyTypeModel(
            type = TypeMapping.ARRAY,
            hint = PROPERTY_HINT_TYPE_STRING,
            hintString = "24/${customElement.propertyHint}:${customElement.simpleName}",
            arrayElementCustomScriptFqName = customElement.fqName,
            arrayElementCustomScriptIsResource = customElement.isExportableResource,
            isMutableList = isMutable,
          )
        }
        // task 38 (issue #40): List<enum class> — a typed int Array whose elements
        // carry PROPERTY_HINT_ENUM ("2/2:<entries>"), the array form of the task-32
        // scalar enum export (C# enum-array parity). Elements are stored as ordinals.
        val elementEnum =
          (elementDecl as? KSClassDeclaration)?.takeIf { it.classKind == ClassKind.ENUM_CLASS }
        if (elementEnum != null && elementFq != null) {
          val entries =
            elementEnum.declarations
              .filterIsInstance<KSClassDeclaration>()
              .filter { it.classKind == ClassKind.ENUM_ENTRY }
              .map { it.simpleName.asString() }
              .toList()
          if (entries.isEmpty()) {
            throw IllegalArgumentException(
              "$className.$propertyName: enum class '$elementFq' has no entries to export"
            )
          }
          return ScriptPropertyTypeModel(
            type = TypeMapping.ARRAY,
            hint = PROPERTY_HINT_TYPE_STRING,
            hintString = "2/$PROPERTY_HINT_ENUM:" + entries.joinToString(","),
            arrayElementEnumFqName = elementFq,
            arrayElementEnumEntries = entries,
            isMutableList = isMutable,
          )
        }
      }

      // Typed `Map<K, V>` export (issue #40): a DICTIONARY Variant slot registered with
      // PROPERTY_HINT_DICTIONARY_TYPE and a "<keyEncoding>;<valueEncoding>" hint string, the
      // two-sided analogue of the List<T> typed-array branch above. Keys are the scalar set
      // (String/Long/Int/enum); values reach List<T> element parity plus scalars/value types.
      if (fq == "kotlin.collections.Map" || fq == "kotlin.collections.MutableMap") {
        val isMutable = fq == "kotlin.collections.MutableMap"
        val keyType = type.arguments.getOrNull(0)?.type?.resolve()
        val valueType = type.arguments.getOrNull(1)?.type?.resolve()
        val keyFq = keyType?.declaration?.qualifiedName?.asString()
        val valueFq = valueType?.declaration?.qualifiedName?.asString()
        // Plain `Map<String, Any?>` stays untyped (hint 0) and rides the generic
        // DICTIONARY read/write path (String keys only — the audited runtime policy).
        if (keyFq == "kotlin.String" && (valueFq == "kotlin.Any" || valueType == null)) {
          return ScriptPropertyTypeModel(TypeMapping.DICTIONARY, isMutableMap = isMutable)
        }
        if (valueFq == "kotlin.Any" || valueType == null) {
          throw IllegalArgumentException(
            "$className.$propertyName: an untyped Map value (Any?) is only supported with String keys; " +
              "give the value a concrete type for a '$keyFq'-keyed Map"
          )
        }
        val key = resolveMapKey(keyType, keyFq, className, propertyName)
        val value = resolveMapValue(valueType, valueFq, className, propertyName, scriptClassTypes)
        // Nullable object/resource/script map values (`Map<K, Texture2D?>`) are rejected
        // rather than silently miscompiled: the object-valued Dictionary readers drop nil
        // handles, so a null-preserving `Map<K, V?>` for objects would need a parallel
        // reader path we don't yet have. Nullable *scalar/value/enum* values ARE supported
        // (they preserve the key with null, matching C#). Declare the object value non-null;
        // its nil entries drop, which is the type-consistent behaviour for a non-null slot.
        if (
          valueType.isMarkedNullable &&
            (value.wrapperFqName != null || value.customScriptFqName != null)
        ) {
          throw IllegalArgumentException(
            "$className.$propertyName: a nullable object/resource Map value ('$valueFq?') " +
              "is not supported. Declare it non-null ('Map<$keyFq, $valueFq>') — engine " +
              "object dictionaries drop nil values, so a cleared entry's key is dropped. " +
              "Nullable scalar/value-type/enum Map values are supported and preserve the key."
          )
        }
        return ScriptPropertyTypeModel(
          type = TypeMapping.DICTIONARY,
          hint = PROPERTY_HINT_DICTIONARY_TYPE,
          hintString = "${key.hintFragment};${value.hintFragment}",
          mapKeyKotlinType = key.kotlinType,
          mapKeyEnumEntries = key.enumEntries,
          mapValueKotlinType = value.kotlinType,
          mapValueWrapperFqName = value.wrapperFqName,
          mapValueCustomScriptFqName = value.customScriptFqName,
          mapValueCustomScriptIsResource = value.customScriptIsResource,
          mapValueEnumFqName = value.enumFqName,
          mapValueEnumEntries = value.enumEntries,
          // `Map<K, V?>`: mirror C#'s nil-preserving Dictionary — keep the key, decode a
          // nil/wrong-typed value to null. Non-null `V` cannot hold null, so it drops.
          mapValueNullable = valueType.isMarkedNullable,
          isMutableMap = isMutable,
        )
      }

      return fqToTypeMapping(fq)?.let { ScriptPropertyTypeModel(it) }
        ?: throw IllegalArgumentException(
          "$className.$propertyName: unsupported ScriptProperty type '$fq'"
        )
    }

    /** Godot `Variant::Type` ids for the scalar/value types usable as typed-Map keys and values. */
    private fun scalarVariantEncoding(fq: String?): Pair<Int, String>? =
      when (fq) {
        "kotlin.Long" -> 2 to "Long"
        "kotlin.Int" -> 2 to "Int"
        "kotlin.Double" -> 3 to "Double"
        "kotlin.Float" -> 3 to "Float"
        "kotlin.Boolean" -> 1 to "Boolean"
        "kotlin.String" -> 4 to "String"
        "net.multigesture.kanama.types.Vector2" -> 5 to "net.multigesture.kanama.types.Vector2"
        "net.multigesture.kanama.types.Vector2i" -> 6 to "net.multigesture.kanama.types.Vector2i"
        "net.multigesture.kanama.types.Vector3" -> 9 to "net.multigesture.kanama.types.Vector3"
        "net.multigesture.kanama.types.Vector3i" -> 10 to "net.multigesture.kanama.types.Vector3i"
        "net.multigesture.kanama.types.Color" -> 20 to "net.multigesture.kanama.types.Color"
        else -> null
      }

    private data class MapKeyResolution(
      val hintFragment: String,
      val kotlinType: String,
      val enumEntries: List<String> = emptyList(),
    )

    private data class MapValueResolution(
      val hintFragment: String,
      val kotlinType: String? = null,
      val wrapperFqName: String? = null,
      val customScriptFqName: String? = null,
      val customScriptIsResource: Boolean = false,
      val enumFqName: String? = null,
      val enumEntries: List<String> = emptyList(),
    )

    /** Keys are scalar-only: String, Long/Int, or an enum class (stored as its ordinal). */
    private fun resolveMapKey(
      keyType: KSType?,
      keyFq: String?,
      className: String,
      propertyName: String,
    ): MapKeyResolution {
      val keyDecl = keyType?.declaration
      val keyEnum =
        (keyDecl as? KSClassDeclaration)?.takeIf { it.classKind == ClassKind.ENUM_CLASS }
      if (keyEnum != null && keyFq != null) {
        val entries = enumEntryNames(keyEnum, className, propertyName, keyFq)
        return MapKeyResolution(
          "2/$PROPERTY_HINT_ENUM:" + entries.joinToString(","),
          keyFq,
          entries,
        )
      }
      val scalar = scalarVariantEncoding(keyFq)
      if (scalar != null) {
        return MapKeyResolution("${scalar.first}:", scalar.second)
      }
      throw IllegalArgumentException(
        "$className.$propertyName: unsupported Map key type '$keyFq' (supported: String, Long, " +
          "Int, Double, Float, Boolean, Vector2/Vector2i/Vector3/Vector3i, Color, or an enum class)"
      )
    }

    /**
     * Values reach List<T> element parity (String/wrapper/custom-script/enum) plus scalars/value
     * types.
     */
    private fun resolveMapValue(
      valueType: KSType,
      valueFq: String?,
      className: String,
      propertyName: String,
      scriptClassTypes: Map<String, ScriptClassTypeInfo>,
    ): MapValueResolution {
      val scalar = scalarVariantEncoding(valueFq)
      if (scalar != null) {
        return MapValueResolution(hintFragment = "${scalar.first}:", kotlinType = scalar.second)
      }
      val valueDecl = valueType.declaration
      val wrapper = valueFq?.takeIf { it in SUPPORTED_OBJECT_WRAPPERS }
      if (wrapper != null) {
        val fragment =
          if (wrapper in SUPPORTED_RESOURCE_WRAPPERS) {
            "24/${PROPERTY_HINT_RESOURCE_TYPE}:${godotClassName(wrapper)}"
          } else {
            "24/${PROPERTY_HINT_NODE_TYPE}:${godotClassName(wrapper)}"
          }
        return MapValueResolution(hintFragment = fragment, wrapperFqName = wrapper)
      }
      val customScript = valueFq?.let { scriptClassTypes[it] }?.takeIf { it.isExportableGlobal }
      if (customScript != null) {
        return MapValueResolution(
          hintFragment = "24/${customScript.propertyHint}:${customScript.simpleName}",
          customScriptFqName = customScript.fqName,
          customScriptIsResource = customScript.isExportableResource,
        )
      }
      val valueEnum =
        (valueDecl as? KSClassDeclaration)?.takeIf { it.classKind == ClassKind.ENUM_CLASS }
      if (valueEnum != null && valueFq != null) {
        val entries = enumEntryNames(valueEnum, className, propertyName, valueFq)
        return MapValueResolution(
          hintFragment = "2/$PROPERTY_HINT_ENUM:" + entries.joinToString(","),
          enumFqName = valueFq,
          enumEntries = entries,
        )
      }
      throw IllegalArgumentException(
        "$className.$propertyName: unsupported Map value type '$valueFq'"
      )
    }

    private fun enumEntryNames(
      enumDecl: KSClassDeclaration,
      className: String,
      propertyName: String,
      fq: String,
    ): List<String> {
      val entries =
        enumDecl.declarations
          .filterIsInstance<KSClassDeclaration>()
          .filter { it.classKind == ClassKind.ENUM_ENTRY }
          .map { it.simpleName.asString() }
          .toList()
      if (entries.isEmpty()) {
        throw IllegalArgumentException(
          "$className.$propertyName: enum class '$fq' has no entries to export"
        )
      }
      return entries
    }

    private const val PROPERTY_HINT_ENUM = 2
    private const val PROPERTY_HINT_RESOURCE_TYPE = 17
    private const val PROPERTY_HINT_TYPE_STRING = 23
    private const val PROPERTY_HINT_NODE_TYPE = 34
    private const val PROPERTY_HINT_DICTIONARY_TYPE = 38

    private val SUPPORTED_RESOURCE_WRAPPERS =
      setOf(
        "net.multigesture.kanama.api.PackedScene",
        "net.multigesture.kanama.api.Texture2D",
        "net.multigesture.kanama.api.NoiseTexture2D",
        "net.multigesture.kanama.api.ShaderMaterial",
        "net.multigesture.kanama.api.Curve",
        "net.multigesture.kanama.api.LightmapGIData",
        "net.multigesture.kanama.api.Material",
        "net.multigesture.kanama.api.ButtonGroup",
        "net.multigesture.kanama.api.FastNoiseLite",
        // task 33 (issue #36): common resource-slot base classes.
        "net.multigesture.kanama.api.AudioStream",
        "net.multigesture.kanama.api.Texture",
        "net.multigesture.kanama.api.Mesh",
        "net.multigesture.kanama.api.Shape3D",
        "net.multigesture.kanama.api.Shape2D",
        "net.multigesture.kanama.api.Font",
        "net.multigesture.kanama.api.Animation",
        "net.multigesture.kanama.api.StyleBox",
      )

    private val SUPPORTED_NODE_WRAPPERS =
      setOf(
        "net.multigesture.kanama.api.Node",
        "net.multigesture.kanama.api.Node2D",
        "net.multigesture.kanama.api.Node3D",
        "net.multigesture.kanama.api.Control",
        "net.multigesture.kanama.api.ColorRect",
        "net.multigesture.kanama.api.CanvasLayer",
        "net.multigesture.kanama.api.Label",
        "net.multigesture.kanama.api.Button",
        "net.multigesture.kanama.api.TextureRect",
        "net.multigesture.kanama.api.Area2D",
        "net.multigesture.kanama.api.Area3D",
        "net.multigesture.kanama.api.PhysicsBody3D",
        "net.multigesture.kanama.api.CharacterBody3D",
        "net.multigesture.kanama.api.RigidBody3D",
        "net.multigesture.kanama.api.StaticBody3D",
        "net.multigesture.kanama.api.VehicleBody3D",
        "net.multigesture.kanama.api.VehicleWheel3D",
        "net.multigesture.kanama.api.Sprite2D",
        "net.multigesture.kanama.api.SpriteBase3D",
        "net.multigesture.kanama.api.Sprite3D",
        "net.multigesture.kanama.api.AnimatedSprite3D",
        "net.multigesture.kanama.api.Camera3D",
        "net.multigesture.kanama.api.Marker3D",
        "net.multigesture.kanama.api.SubViewport",
        "net.multigesture.kanama.api.GridMap",
        "net.multigesture.kanama.api.RayCast3D",
        "net.multigesture.kanama.api.MeshInstance3D",
        "net.multigesture.kanama.api.CollisionShape3D",
        "net.multigesture.kanama.api.AnimationPlayer",
        "net.multigesture.kanama.api.AudioStreamPlayer",
        "net.multigesture.kanama.api.AudioStreamPlayer3D",
        "net.multigesture.kanama.api.GPUParticles2D",
        "net.multigesture.kanama.api.GPUParticles3D",
        "net.multigesture.kanama.api.CPUParticles3D",
        "net.multigesture.kanama.api.Timer",
        "net.multigesture.kanama.api.WorldEnvironment",
      )

    private fun godotClassName(fqName: String): String = fqName.substringAfterLast('.')
  }
}

class KanamaProcessorProvider : SymbolProcessorProvider {
  override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor =
    KanamaProcessor(environment)
}

private val KOTLIN_KEYWORDS =
  setOf(
    "as",
    "break",
    "class",
    "continue",
    "do",
    "else",
    "false",
    "for",
    "fun",
    "if",
    "in",
    "interface",
    "is",
    "null",
    "object",
    "package",
    "return",
    "super",
    "this",
    "throw",
    "true",
    "try",
    "typealias",
    "typeof",
    "val",
    "var",
    "when",
    "while",
  )

private fun kotlinStringLiteral(value: String): String =
  value.replace("\\", "\\\\").replace("\"", "\\\"")

// internal (not private): referenced by ArgModel's JVM codegen helpers in ScriptModel.kt.
internal val RESOURCE_WRAPPERS_WITH_FROM_HANDLE =
  setOf(
    "net.multigesture.kanama.api.PackedScene",
    "net.multigesture.kanama.api.Texture2D",
    "net.multigesture.kanama.api.NoiseTexture2D",
    "net.multigesture.kanama.api.ShaderMaterial",
    "net.multigesture.kanama.api.Curve",
    "net.multigesture.kanama.api.LightmapGIData",
    "net.multigesture.kanama.api.Material",
    "net.multigesture.kanama.api.ButtonGroup",
    "net.multigesture.kanama.api.FastNoiseLite",
    // task 33 (issue #36): common resource-slot base classes. Base-typed slots
    // accept engine subtypes (an AudioStreamWAV lands in an AudioStream slot).
    "net.multigesture.kanama.api.AudioStream",
    "net.multigesture.kanama.api.Texture",
    "net.multigesture.kanama.api.Mesh",
    "net.multigesture.kanama.api.Shape3D",
    "net.multigesture.kanama.api.Shape2D",
    "net.multigesture.kanama.api.Font",
    "net.multigesture.kanama.api.Animation",
    "net.multigesture.kanama.api.StyleBox",
  )

// JVM-emitter codegen for ArgModel — string-builds MemorySegment/ptrcall read expressions.
// Extension functions (not members on ArgModel) so the model stays a pure, serializable
// data holder; the iOS emitter re-derives its own marshalling from the arg's neutral type.
internal fun ArgModel.readFromScratch(s: String): String =
  if (objectWrapperFqName != null) {
    if (objectWrapperFqName in RESOURCE_WRAPPERS_WITH_FROM_HANDLE) {
      val value = "$objectWrapperFqName.fromHandle(${s}.get(ADDRESS, 0))"
      if (nullable) value else "$value ?: error(\"Expected $objectWrapperFqName argument '$name'\")"
    } else {
      val handle = "${s}.get(ADDRESS, 0)"
      if (nullable) "if ($handle.address() == 0L) null else $objectWrapperFqName($handle)"
      else "$objectWrapperFqName($handle)"
    }
  } else {
    type.readFromScratch(s)
  }

internal fun ArgModel.readPtrcallArg(ptr: String): String =
  if (objectWrapperFqName != null) {
    if (objectWrapperFqName in RESOURCE_WRAPPERS_WITH_FROM_HANDLE) {
      val value =
        "$objectWrapperFqName.fromHandle($ptr.reinterpret(${type.ptrcallSizeBytesExpr}).get(ADDRESS, 0))"
      if (nullable) value else "$value ?: error(\"Expected $objectWrapperFqName argument '$name'\")"
    } else {
      val handle = "$ptr.reinterpret(${type.ptrcallSizeBytesExpr}).get(ADDRESS, 0)"
      if (nullable) "if ($handle.address() == 0L) null else $objectWrapperFqName($handle)"
      else "$objectWrapperFqName($handle)"
    }
  } else {
    type.readPtrcallArg(ptr)
  }

internal fun ArgModel.signalEmitValueExpr(): String =
  if (type == TypeMapping.NODE_PATH) "$name.path" else name

private fun constantIdentifier(name: String): String {
  val parts = name.trim('_').split('_', '-', ' ', '.', ':', '/').filter { it.isNotEmpty() }
  val base =
    if (parts.isEmpty()) {
        "name"
      } else {
        parts
          .mapIndexed { index, part ->
            val cleaned = part.filter { it.isLetterOrDigit() || it == '_' }
            if (cleaned.isEmpty()) {
              ""
            } else if (index == 0) {
              cleaned.replaceFirstChar { it.lowercase() }
            } else {
              cleaned.replaceFirstChar { it.uppercase() }
            }
          }
          .joinToString("")
      }
      .ifEmpty { "name" }
  val validStart = base.first().isLetter() || base.first() == '_'
  val identifier = if (validStart) base else "name${base.replaceFirstChar { it.uppercase() }}"
  return if (identifier in KOTLIN_KEYWORDS) "`$identifier`" else identifier
}

private fun uniqueConstantIdentifier(name: String, seen: MutableSet<String>): String {
  val base = constantIdentifier(name)
  if (seen.add(base)) return base
  val bare = base.removeSurrounding("`")
  var index = 2
  while (true) {
    val candidateBare = "$bare$index"
    val candidate = if (candidateBare in KOTLIN_KEYWORDS) "`$candidateBare`" else candidateBare
    if (seen.add(candidate)) return candidate
    index++
  }
}

private fun signalHelperSuffix(godotName: String): String {
  val id = constantIdentifier(godotName).removeSurrounding("`")
  return id.replaceFirstChar { it.uppercase() }
}

private fun signalCallbackType(args: List<ArgModel>): String =
  args.joinToString(prefix = "(", postfix = ") -> Unit") { it.kotlinType }

private fun signalCallbackInvocation(args: List<ArgModel>): String =
  if (args.isEmpty()) {
    "callback()"
  } else {
    "callback(${args.indices.joinToString(", ") { signalArgumentValueExpr(args[it], it) }})"
  }

private fun signalArgumentValueExpr(arg: ArgModel, index: Int): String =
  when {
    arg.objectWrapperFqName != null -> {
      val fromGodotObject =
        if (arg.objectWrapperFqName in RESOURCE_WRAPPERS_WITH_FROM_HANDLE) {
          "(args.getOrNull($index) as? net.multigesture.kanama.api.GodotObject)?.let { ${arg.objectWrapperFqName}.fromHandle(it.handle) }"
        } else {
          "(args.getOrNull($index) as? net.multigesture.kanama.api.GodotObject)?.let { ${arg.objectWrapperFqName}(it.handle) }"
        }
      "((args.getOrNull($index) as? ${arg.objectWrapperFqName}) ?: $fromGodotObject ?: error(\"Signal argument '${arg.name}' was not ${arg.kotlinType}\"))"
    }
    arg.type == TypeMapping.INT -> "(args.getOrNull($index) as? Long ?: 0L)"
    arg.type == TypeMapping.FLOAT -> "((args.getOrNull($index) as? Number)?.toDouble() ?: 0.0)"
    arg.type == TypeMapping.BOOL -> "(args.getOrNull($index) as? Boolean ?: false)"
    arg.type == TypeMapping.STRING -> "(args.getOrNull($index) as? String ?: \"\")"
    arg.type == TypeMapping.NODE_PATH ->
      "(args.getOrNull($index) as? net.multigesture.kanama.types.NodePath ?: (args.getOrNull($index) as? String)?.let { net.multigesture.kanama.types.NodePath(it) } ?: net.multigesture.kanama.types.NodePath.EMPTY)"
    else -> "args.getOrNull($index) as ${arg.kotlinType}"
  }

private fun signalAwaitReturnType(args: List<ArgModel>): String =
  when (args.size) {
    0 -> "Unit"
    1 -> args.single().kotlinType
    else -> "List<Any?>"
  }

private fun signalAwaitReturnExpr(args: List<ArgModel>): String =
  when (args.size) {
    0 -> "Unit"
    1 -> signalArgumentValueExpr(args.single(), 0)
    else -> "args"
  }

private fun StringBuilder.appendMethodHelpers(simpleName: String, methods: List<MethodModel>) {
  val regularMethods = methods.filter { it.kind == MethodKind.REGULAR }
  if (regularMethods.isEmpty()) return

  appendLine()
  appendLine("object ${simpleName}Methods {")
  for (method in regularMethods) {
    val kotlinParams = method.args.joinToString(", ") { "${it.name}: ${it.kotlinType}" }
    val params = if (kotlinParams.isNotEmpty()) ", $kotlinParams" else ""
    val argNames = method.args.joinToString(", ") { it.name }
    val helperArgs = if (argNames.isNotEmpty()) ", $argNames" else ""
    val directArgs = argNames
    val returnType = method.returnType?.kotlinType

    if (returnType == null) {
      appendLine("    fun ${method.kotlinName}(instance: $simpleName$params) {")
      appendLine("        instance.${method.kotlinName}($directArgs)")
      appendLine("    }")
      appendLine()
      appendLine(
        "    fun ${method.kotlinName}(target: net.multigesture.kanama.api.GodotObject$params): Boolean {"
      )
      appendLine(
        "        val instance = target.kotlinScriptInstance<$simpleName>() ?: return false"
      )
      appendLine("        ${method.kotlinName}(instance$helperArgs)")
      appendLine("        return true")
      appendLine("    }")
    } else {
      appendLine("    fun ${method.kotlinName}(instance: $simpleName$params): $returnType =")
      appendLine("        instance.${method.kotlinName}($directArgs)")
      appendLine()
      appendLine(
        "    fun ${method.kotlinName}(target: net.multigesture.kanama.api.GodotObject$params): $returnType? ="
      )
      appendLine(
        "        target.kotlinScriptInstance<$simpleName>()?.let { ${method.kotlinName}(it$helperArgs) }"
      )
    }
    appendLine()
  }
  appendLine("}")
}

private fun StringBuilder.appendRpcHelpers(simpleName: String, methods: List<MethodModel>) {
  val rpcMethods = methods.filter { it.kind == MethodKind.REGULAR && it.rpc != null }
  if (rpcMethods.isEmpty()) return

  appendLine()
  appendLine("object ${simpleName}Rpcs {")
  for (method in rpcMethods) {
    val helperSuffix = signalHelperSuffix(method.godotName)
    val kotlinParams = method.args.joinToString(", ") { "${it.name}: ${it.kotlinType}" }
    val params = if (kotlinParams.isNotEmpty()) ", $kotlinParams" else ""
    val args = method.args.joinToString(", ") { it.name }
    val rpcArgs = if (args.isNotEmpty()) ", $args" else ""
    val godotName = kotlinStringLiteral(method.godotName)

    appendLine("    fun rpc$helperSuffix(instance: $simpleName$params): Long =")
    appendLine(
      "        net.multigesture.kanama.api.Node(instance.godotObject).rpc(\"$godotName\"$rpcArgs)"
    )
    appendLine()
    appendLine("    fun rpcId$helperSuffix(instance: $simpleName, peerId: Long$params): Long =")
    appendLine(
      "        net.multigesture.kanama.api.Node(instance.godotObject).rpcId(peerId, \"$godotName\"$rpcArgs)"
    )

    if (method.rpc?.callLocal == true) {
      appendLine()
      appendLine("    fun callLocal$helperSuffix(instance: $simpleName$params) {")
      appendLine(
        "        net.multigesture.kanama.api.Node(instance.godotObject).callLocalRpc(\"$godotName\"$rpcArgs)"
      )
      appendLine("    }")
    }
    appendLine()
  }
  appendLine("}")
}

private data class ToolButtonAnnotationArgs(
  val text: String? = null,
  val icon: String? = null,
  val name: String? = null,
)

private fun toolButtonAnnotationArgsFromSource(
  fn: KSFunctionDeclaration
): ToolButtonAnnotationArgs {
  val location = fn.location as? FileLocation ?: return ToolButtonAnnotationArgs()
  val sourceLines =
    runCatching { File(location.filePath).readLines() }.getOrNull()
      ?: return ToolButtonAnnotationArgs()
  val start = (location.lineNumber - 8).coerceAtLeast(0)
  val end = (location.lineNumber - 1).coerceIn(0, sourceLines.lastIndex)
  val annotation =
    (start..end)
      .map { sourceLines[it].trim() }
      .firstOrNull { it.startsWith("@ToolButton(") || it.startsWith("@ExportToolButton(") }
      ?: return ToolButtonAnnotationArgs()
  val body = annotation.substringAfter('(', "").substringBeforeLast(')', "")
  fun namedString(name: String): String? =
    Regex("""\b${Regex.escape(name)}\s*=\s*($kotlinStringLiteralPattern)""")
      .find(body)
      ?.groupValues
      ?.get(1)
      ?.let(::unquoteKotlinStringLiteral)
  val firstString =
    Regex(kotlinStringLiteralPattern).find(body)?.value?.let(::unquoteKotlinStringLiteral)
  return ToolButtonAnnotationArgs(
    text = namedString("text") ?: namedString("value") ?: firstString,
    icon = namedString("icon"),
    name = namedString("name"),
  )
}

private fun unquoteKotlinStringLiteral(value: String): String =
  value.removePrefix("\"").removeSuffix("\"").replace("\\\"", "\"").replace("\\\\", "\\")

private fun humanizeGodotName(name: String): String =
  name
    .trim('_')
    .split('_')
    .filter { it.isNotEmpty() }
    .joinToString(" ") { part -> part.replaceFirstChar { it.uppercase() } }
    .ifEmpty { name }

// ---------- Data models ----------

private const val PROPERTY_USAGE_GROUP = 64
private const val PROPERTY_USAGE_CATEGORY = 128
private const val PROPERTY_USAGE_SUBGROUP = 256
private const val PROPERTY_USAGE_EDITOR = 4
private const val PROPERTY_HINT_TOOL_BUTTON = 39

private val kotlinStringLiteralPattern = "\"(?:\\\\.|[^\"\\\\])*\""

private fun scriptPropertyDefaultLiteral(
  prop: KSPropertyDeclaration,
  type: TypeMapping,
  narrow: NarrowScalar? = null,
  enumFqName: String? = null,
  enumEntries: List<String> = emptyList(),
): String? {
  val location = prop.location as? FileLocation ?: return null
  val sourceLines = runCatching { File(location.filePath).readLines() }.getOrNull() ?: return null
  if (sourceLines.isEmpty()) return null

  val propertyName = prop.simpleName.asString()
  val start = (location.lineNumber - 1).coerceIn(0, sourceLines.lastIndex)
  val declarationLine = findPropertyDeclarationLine(sourceLines, start, propertyName) ?: return null
  val declaration = collectPropertyDeclaration(sourceLines, declarationLine)
  val initializer = extractPropertyInitializer(declaration) ?: return null
  enumFqName?.let {
    return normalizeEnumDefaultLiteral(initializer, it, enumEntries)
  }
  narrow?.let {
    return normalizeNarrowDefaultLiteral(initializer, it)
  }
  return normalizeScriptPropertyDefaultLiteral(initializer, type)
}

/**
 * Default literals for enum-typed properties: a `MyEnum.ENTRY` (or fully qualified) initializer,
 * normalized to the fully qualified entry reference. The generated default field is typed as the
 * enum; the emitter reports the ordinal to Godot.
 */
private fun normalizeEnumDefaultLiteral(
  initializer: String,
  enumFqName: String,
  entries: List<String>,
): String? {
  val simpleName = enumFqName.substringAfterLast('.')
  val entryRef = Regex("""(?:${Regex.escape(enumFqName)}|${Regex.escape(simpleName)})\.(\w+)""")
  val entry = entryRef.matchEntire(initializer)?.groupValues?.get(1) ?: return null
  return if (entry in entries) "$enumFqName.$entry" else null
}

/**
 * Default literals for kotlin.Float / kotlin.Int properties. The literal is kept in its source
 * (narrow) form — the generated default field is typed Float/Int, and the emitter widens when
 * reporting it to Godot.
 */
private fun normalizeNarrowDefaultLiteral(initializer: String, narrow: NarrowScalar): String? =
  when (narrow) {
    NarrowScalar.FLOAT32 ->
      initializer.takeIf {
        Regex("""[-+]?(?:\d+\.\d*|\.\d+|\d+)(?:[eE][-+]?\d+)?[fF]""").matches(it)
      }
    NarrowScalar.INT32 -> initializer.takeIf { Regex("""[-+]?\d+""").matches(it) }
  }

private fun findPropertyDeclarationLine(
  lines: List<String>,
  start: Int,
  propertyName: String,
): Int? {
  val declarationPattern = Regex("""\b(?:var|val)\s+${Regex.escape(propertyName)}\b""")
  val first = (start - 8).coerceAtLeast(0)
  val last = (start + 16).coerceAtMost(lines.lastIndex)
  return (first..last).firstOrNull { declarationPattern.containsMatchIn(lines[it]) }
}

private fun collectPropertyDeclaration(lines: List<String>, start: Int): String {
  val parts = mutableListOf<String>()
  var parenDepth = 0
  for (i in start..(start + 8).coerceAtMost(lines.lastIndex)) {
    val line = stripLineComment(lines[i]).trim()
    if (line.isEmpty()) continue
    parts += line
    parenDepth += line.count { it == '(' } - line.count { it == ')' }
    if (line.contains("=") && parenDepth <= 0) break
  }
  return parts.joinToString(" ")
}

private fun stripLineComment(line: String): String {
  var inString = false
  var escaped = false
  for (i in 0 until line.lastIndex) {
    val c = line[i]
    when {
      escaped -> escaped = false
      c == '\\' && inString -> escaped = true
      c == '"' -> inString = !inString
      !inString && c == '/' && line[i + 1] == '/' -> return line.substring(0, i)
    }
  }
  return line
}

private fun extractPropertyInitializer(declaration: String): String? {
  val eq = declaration.indexOf('=')
  if (eq < 0) return null
  return declaration.substring(eq + 1).trim().removeSuffix(";").trim().takeIf { it.isNotEmpty() }
}

private fun normalizeScriptPropertyDefaultLiteral(initializer: String, type: TypeMapping): String? {
  val intLiteral = Regex("""[-+]?\d+[lL]?""")
  val doubleLiteral = Regex("""[-+]?(?:\d+\.\d*|\.\d+)(?:[eE][-+]?\d+)?[dD]?""")
  val numberLiteral = Regex("""[-+]?(?:\d+\.\d*|\.\d+|\d+)(?:[eE][-+]?\d+)?[fFdDlL]?""")
  val boolLiteral = Regex("""true|false""")
  val stringLiteral = Regex(kotlinStringLiteralPattern)
  val nodePathLiteral =
    Regex(
      """(?:net\.multigesture\.kanama\.types\.)?NodePath\(\s*($kotlinStringLiteralPattern)\s*\)"""
    )
  val mathToRadiansLiteral = Regex("""(?:java\.lang\.)?Math\.toRadians\(\s*($numberLiteral)\s*\)""")

  return when (type) {
    TypeMapping.INT -> initializer.takeIf { intLiteral.matches(it) }
    TypeMapping.FLOAT ->
      initializer.takeIf { doubleLiteral.matches(it) }
        ?: mathToRadiansLiteral.matchEntire(initializer)?.groupValues?.get(1)?.let {
          "Math.toRadians($it)"
        }
    TypeMapping.BOOL -> initializer.takeIf { boolLiteral.matches(it) }
    TypeMapping.STRING -> initializer.takeIf { stringLiteral.matches(it) }
    TypeMapping.OBJECT -> initializer.takeIf { it == "null" }
    TypeMapping.ARRAY ->
      initializer.takeIf { it == "emptyList()" || it == "listOf()" }?.let { "emptyList()" }
    // Typed `Map<K, V>` export (issue #40): only the empty default is surfaced; populate
    // initial entries in the inspector, the same policy as ARRAY above.
    TypeMapping.DICTIONARY ->
      initializer.takeIf { it == "emptyMap()" || it == "mapOf()" }?.let { "emptyMap()" }
    // Return-only virtual shapes (task 13/29); not @ScriptProperty default literals.
    TypeMapping.PACKED_STRING_ARRAY -> null
    TypeMapping.VARIANT -> null
    TypeMapping.PACKED_BYTE_ARRAY,
    TypeMapping.PACKED_INT32_ARRAY,
    TypeMapping.PACKED_INT64_ARRAY,
    TypeMapping.PACKED_FLOAT32_ARRAY,
    TypeMapping.PACKED_FLOAT64_ARRAY,
    TypeMapping.PACKED_VECTOR2_ARRAY,
    TypeMapping.PACKED_VECTOR3_ARRAY,
    TypeMapping.PACKED_COLOR_ARRAY,
    TypeMapping.RID,
    TypeMapping.RECT2,
    TypeMapping.AABB,
    TypeMapping.TRANSFORM2D,
    TypeMapping.TRANSFORM3D,
    TypeMapping.PROJECTION -> null
    TypeMapping.NODE_PATH ->
      nodePathLiteral.matchEntire(initializer)?.groupValues?.get(1)?.let {
        "net.multigesture.kanama.types.NodePath($it)"
      }
    TypeMapping.VECTOR2 ->
      normalizeVectorDefaultLiteral(
        initializer = initializer,
        packageClass = "net.multigesture.kanama.types.Vector2",
        simpleClass = "Vector2",
        components = 2,
        componentPattern = numberLiteral,
      )
    TypeMapping.VECTOR2I ->
      normalizeVectorDefaultLiteral(
        initializer = initializer,
        packageClass = "net.multigesture.kanama.types.Vector2i",
        simpleClass = "Vector2i",
        components = 2,
        componentPattern = intLiteral,
      )
    TypeMapping.VECTOR3 ->
      normalizeVectorDefaultLiteral(
        initializer = initializer,
        packageClass = "net.multigesture.kanama.types.Vector3",
        simpleClass = "Vector3",
        components = 3,
        componentPattern = numberLiteral,
      )
    TypeMapping.VECTOR3I ->
      normalizeVectorDefaultLiteral(
        initializer = initializer,
        packageClass = "net.multigesture.kanama.types.Vector3i",
        simpleClass = "Vector3i",
        components = 3,
        componentPattern = intLiteral,
      )
    TypeMapping.QUATERNION,
    TypeMapping.BASIS -> null
  }
}

private fun normalizeVectorDefaultLiteral(
  initializer: String,
  packageClass: String,
  simpleClass: String,
  components: Int,
  componentPattern: Regex,
): String? {
  val qualifiedPrefix = Regex.escape(packageClass)
  val simplePrefix = Regex.escape(simpleClass)
  val zero = Regex("""(?:$qualifiedPrefix|$simplePrefix)\.ZERO""")
  if (zero.matches(initializer)) return "$packageClass.ZERO"

  val constructor = Regex("""(?:$qualifiedPrefix|$simplePrefix)\((.*)\)""")
  val args =
    constructor.matchEntire(initializer)?.groupValues?.get(1)?.split(",")?.map { it.trim() }
      ?: return null
  if (args.size != components || args.any { !componentPattern.matches(it) }) return null
  return "$packageClass(${args.joinToString(", ")})"
}

internal enum class TypeMapping(
  val variantTypeEnum: String,
  val valueLayout: String,
  /** Number of bytes the raw value occupies in a ptrcall arg/return slot. */
  val ptrcallSizeBytes: Int,
  val kotlinLiteralZero: String,
  val kotlinType: String,
  /**
   * True for types (STRING) whose scratch segment holds a heap-allocated Godot object. The emitter
   * must emit GodotStrings.destroyString() after reading the scratch value or after variantFromType
   * copies from it.
   */
  val needsScratchDestroy: Boolean = false,
  val scratchAllocationExpr: String = valueLayout,
  val ptrcallSizeBytesExpr: String = ptrcallSizeBytes.toString(),
) {
  INT("INT", "JAVA_LONG", 8, "0L", "Long"),
  FLOAT("FLOAT", "JAVA_DOUBLE", 8, "0.0", "Double"),
  BOOL("BOOL", "JAVA_BYTE", 1, "false", "Boolean"),
  STRING("STRING", "JAVA_LONG", 8, "\"\"", "String", needsScratchDestroy = true),
  VECTOR2(
    "VECTOR2",
    "JAVA_FLOAT",
    8,
    "net.multigesture.kanama.types.Vector2(0f, 0f)",
    "net.multigesture.kanama.types.Vector2",
    scratchAllocationExpr =
      "net.multigesture.kanama.types.GodotReal.SIZE_BYTES * 2L, net.multigesture.kanama.types.GodotReal.ALIGN_BYTES",
    ptrcallSizeBytesExpr = "net.multigesture.kanama.types.GodotReal.SIZE_BYTES * 2L",
  ),
  VECTOR2I(
    "VECTOR2I",
    "JAVA_INT",
    8,
    "net.multigesture.kanama.types.Vector2i(0, 0)",
    "net.multigesture.kanama.types.Vector2i",
  ),
  VECTOR3(
    "VECTOR3",
    "JAVA_FLOAT",
    12,
    "net.multigesture.kanama.types.Vector3(0f, 0f, 0f)",
    "net.multigesture.kanama.types.Vector3",
    scratchAllocationExpr =
      "net.multigesture.kanama.types.GodotReal.SIZE_BYTES * 3L, net.multigesture.kanama.types.GodotReal.ALIGN_BYTES",
    ptrcallSizeBytesExpr = "net.multigesture.kanama.types.GodotReal.SIZE_BYTES * 3L",
  ),
  VECTOR3I(
    "VECTOR3I",
    "JAVA_INT",
    12,
    "net.multigesture.kanama.types.Vector3i(0, 0, 0)",
    "net.multigesture.kanama.types.Vector3i",
  ),
  QUATERNION(
    "QUATERNION",
    "JAVA_FLOAT",
    16,
    "net.multigesture.kanama.types.Quaternion.IDENTITY",
    "net.multigesture.kanama.types.Quaternion",
    scratchAllocationExpr =
      "net.multigesture.kanama.types.GodotReal.SIZE_BYTES * 4L, net.multigesture.kanama.types.GodotReal.ALIGN_BYTES",
    ptrcallSizeBytesExpr = "net.multigesture.kanama.types.GodotReal.SIZE_BYTES * 4L",
  ),
  BASIS(
    "BASIS",
    "JAVA_FLOAT",
    36,
    "net.multigesture.kanama.types.Basis.IDENTITY",
    "net.multigesture.kanama.types.Basis",
    scratchAllocationExpr =
      "net.multigesture.kanama.types.GodotReal.SIZE_BYTES * 9L, net.multigesture.kanama.types.GodotReal.ALIGN_BYTES",
    ptrcallSizeBytesExpr = "net.multigesture.kanama.types.GodotReal.SIZE_BYTES * 9L",
  ),
  NODE_PATH(
    "STRING",
    "JAVA_LONG",
    8,
    "net.multigesture.kanama.types.NodePath.EMPTY",
    "net.multigesture.kanama.types.NodePath",
    needsScratchDestroy = true,
  ),
  OBJECT(
    "OBJECT",
    "ADDRESS",
    8,
    "net.multigesture.kanama.api.GodotObject(MemorySegment.ofAddress(1L))",
    "net.multigesture.kanama.api.GodotObject",
  ),
  ARRAY("ARRAY", "JAVA_LONG", 8, "emptyList<Any?>()", "List<Any?>"),
  // task 13 — non-POD virtual return: a `List<String>` marshalled as a Godot PackedStringArray.
  // Variant-only (like ARRAY): it is not a ptrcall scratch/arg shape, so the scratch/ptrcall
  // helpers below fall through to the ARRAY-style safe defaults; the real marshalling is the
  // dedicated PACKED_STRING_ARRAY case in variantWriteRetExpr (desktop) and the iOS encode path.
  PACKED_STRING_ARRAY("PACKED_STRING_ARRAY", "JAVA_LONG", 8, "emptyList<String>()", "List<String>"),
  // task 13 — non-POD virtual return: a Variant, mapped to Kotlin `Any?`. Variant-only, boxed on
  // desktop via BuiltinTypes.initVariantFromAny (broad inner-type set) and on iOS via the existing
  // per-runtime-type encodeIosReturn dispatch (audited inner types; unaudited -> nil).
  // variantTypeEnum
  // NIL is the method-info return type for a Variant ("any").
  VARIANT("NIL", "JAVA_LONG", 8, "null", "Any?"),
  // task 29 — remaining virtual-return families. All Variant-only return shapes like
  // PACKED_STRING_ARRAY: never validated as args/properties, the scratch/ptrcall helpers below
  // fall through to the safe defaults, and the real marshalling is the per-family case in
  // variantWriteRetExpr (desktop) / encodeIosReturn (iOS mirror, where audited).
  //
  // Fixed-element packed arrays map to Kotlin primitive arrays (width-unambiguous: IntArray is
  // int32, LongArray int64, FloatArray float32, DoubleArray float64) or List<value type>.
  PACKED_BYTE_ARRAY("PACKED_BYTE_ARRAY", "JAVA_LONG", 8, "ByteArray(0)", "ByteArray"),
  PACKED_INT32_ARRAY("PACKED_INT32_ARRAY", "JAVA_LONG", 8, "IntArray(0)", "IntArray"),
  PACKED_INT64_ARRAY("PACKED_INT64_ARRAY", "JAVA_LONG", 8, "LongArray(0)", "LongArray"),
  PACKED_FLOAT32_ARRAY("PACKED_FLOAT32_ARRAY", "JAVA_LONG", 8, "FloatArray(0)", "FloatArray"),
  PACKED_FLOAT64_ARRAY("PACKED_FLOAT64_ARRAY", "JAVA_LONG", 8, "DoubleArray(0)", "DoubleArray"),
  PACKED_VECTOR2_ARRAY(
    "PACKED_VECTOR2_ARRAY",
    "JAVA_LONG",
    8,
    "emptyList<net.multigesture.kanama.types.Vector2>()",
    "List<net.multigesture.kanama.types.Vector2>",
  ),
  PACKED_VECTOR3_ARRAY(
    "PACKED_VECTOR3_ARRAY",
    "JAVA_LONG",
    8,
    "emptyList<net.multigesture.kanama.types.Vector3>()",
    "List<net.multigesture.kanama.types.Vector3>",
  ),
  PACKED_COLOR_ARRAY(
    "PACKED_COLOR_ARRAY",
    "JAVA_LONG",
    8,
    "emptyList<net.multigesture.kanama.types.Color>()",
    "List<net.multigesture.kanama.types.Color>",
  ),
  // Dictionary return, Kotlin Map<String, Any?> (String keys only — the same audited policy as
  // the non-virtual Dictionary helpers; values box via initVariantFromAny's inner-type set).
  DICTIONARY("DICTIONARY", "JAVA_LONG", 8, "emptyMap<String, Any?>()", "Map<String, Any?>"),
  // Value-type returns that box through BuiltinTypes.initVariantFromAny on desktop.
  RID(
    "RID",
    "JAVA_LONG",
    8,
    "net.multigesture.kanama.types.RID.EMPTY",
    "net.multigesture.kanama.types.RID",
  ),
  RECT2(
    "RECT2",
    "JAVA_LONG",
    8,
    "net.multigesture.kanama.types.Rect2.ZERO",
    "net.multigesture.kanama.types.Rect2",
  ),
  AABB(
    "AABB",
    "JAVA_LONG",
    8,
    "net.multigesture.kanama.types.AABB.ZERO",
    "net.multigesture.kanama.types.AABB",
  ),
  TRANSFORM2D(
    "TRANSFORM2D",
    "JAVA_LONG",
    8,
    "net.multigesture.kanama.types.Transform2D.IDENTITY",
    "net.multigesture.kanama.types.Transform2D",
  ),
  TRANSFORM3D(
    "TRANSFORM3D",
    "JAVA_LONG",
    8,
    "net.multigesture.kanama.types.Transform3D.IDENTITY",
    "net.multigesture.kanama.types.Transform3D",
  ),
  PROJECTION(
    "PROJECTION",
    "JAVA_LONG",
    8,
    "net.multigesture.kanama.types.Projection.IDENTITY",
    "net.multigesture.kanama.types.Projection",
  );

  /** Expression to read a Kotlin value from a scratch MemorySegment named [s]. */
  fun readFromScratch(s: String): String =
    when (this) {
      BOOL -> "$s.get(JAVA_BYTE, 0) != 0.toByte()"
      STRING -> "GodotStrings.readString($s)"
      VECTOR2 ->
        "net.multigesture.kanama.types.Vector2(net.multigesture.kanama.types.GodotReal.readIndex($s, 0), net.multigesture.kanama.types.GodotReal.readIndex($s, 1))"
      VECTOR2I -> "net.multigesture.kanama.types.Vector2i($s.get(JAVA_INT, 0), $s.get(JAVA_INT, 4))"
      VECTOR3 ->
        "net.multigesture.kanama.types.Vector3(net.multigesture.kanama.types.GodotReal.readIndex($s, 0), net.multigesture.kanama.types.GodotReal.readIndex($s, 1), net.multigesture.kanama.types.GodotReal.readIndex($s, 2))"
      VECTOR3I ->
        "net.multigesture.kanama.types.Vector3i($s.get(JAVA_INT, 0), $s.get(JAVA_INT, 4), $s.get(JAVA_INT, 8))"
      QUATERNION ->
        "net.multigesture.kanama.types.Quaternion(net.multigesture.kanama.types.GodotReal.readIndex($s, 0), net.multigesture.kanama.types.GodotReal.readIndex($s, 1), net.multigesture.kanama.types.GodotReal.readIndex($s, 2), net.multigesture.kanama.types.GodotReal.readIndex($s, 3))"
      BASIS ->
        "net.multigesture.kanama.types.Basis(net.multigesture.kanama.types.Vector3(net.multigesture.kanama.types.GodotReal.readIndex($s, 0), net.multigesture.kanama.types.GodotReal.readIndex($s, 3), net.multigesture.kanama.types.GodotReal.readIndex($s, 6)), net.multigesture.kanama.types.Vector3(net.multigesture.kanama.types.GodotReal.readIndex($s, 1), net.multigesture.kanama.types.GodotReal.readIndex($s, 4), net.multigesture.kanama.types.GodotReal.readIndex($s, 7)), net.multigesture.kanama.types.Vector3(net.multigesture.kanama.types.GodotReal.readIndex($s, 2), net.multigesture.kanama.types.GodotReal.readIndex($s, 5), net.multigesture.kanama.types.GodotReal.readIndex($s, 8)))"
      NODE_PATH -> "net.multigesture.kanama.types.NodePath(GodotStrings.readString($s))"
      OBJECT -> "net.multigesture.kanama.api.GodotObject($s.get(ADDRESS, 0))"
      in VARIANT_ONLY_RETURN_SHAPES -> kotlinLiteralZero
      else -> "$s.get($valueLayout, 0)"
    }

  /** Statement to write Kotlin value [v] into a scratch MemorySegment named [s]. */
  fun writeToScratch(s: String, v: String): String =
    when (this) {
      BOOL -> "$s.set(JAVA_BYTE, 0, if ($v) 1.toByte() else 0.toByte())"
      STRING -> "GodotStrings.initString($s, $v)"
      VECTOR2 ->
        "{ net.multigesture.kanama.types.GodotReal.writeIndex($s, 0, $v.x); net.multigesture.kanama.types.GodotReal.writeIndex($s, 1, $v.y) }"
      VECTOR2I -> "{ $s.set(JAVA_INT, 0, $v.x); $s.set(JAVA_INT, 4, $v.y) }"
      VECTOR3 ->
        "{ net.multigesture.kanama.types.GodotReal.writeIndex($s, 0, $v.x); net.multigesture.kanama.types.GodotReal.writeIndex($s, 1, $v.y); net.multigesture.kanama.types.GodotReal.writeIndex($s, 2, $v.z) }"
      VECTOR3I ->
        "{ $s.set(JAVA_INT, 0, $v.x); $s.set(JAVA_INT, 4, $v.y); $s.set(JAVA_INT, 8, $v.z) }"
      QUATERNION ->
        "{ net.multigesture.kanama.types.GodotReal.writeIndex($s, 0, $v.x); net.multigesture.kanama.types.GodotReal.writeIndex($s, 1, $v.y); net.multigesture.kanama.types.GodotReal.writeIndex($s, 2, $v.z); net.multigesture.kanama.types.GodotReal.writeIndex($s, 3, $v.w) }"
      BASIS ->
        "{ net.multigesture.kanama.types.GodotReal.writeIndex($s, 0, $v.x.x); net.multigesture.kanama.types.GodotReal.writeIndex($s, 1, $v.y.x); net.multigesture.kanama.types.GodotReal.writeIndex($s, 2, $v.z.x); net.multigesture.kanama.types.GodotReal.writeIndex($s, 3, $v.x.y); net.multigesture.kanama.types.GodotReal.writeIndex($s, 4, $v.y.y); net.multigesture.kanama.types.GodotReal.writeIndex($s, 5, $v.z.y); net.multigesture.kanama.types.GodotReal.writeIndex($s, 6, $v.x.z); net.multigesture.kanama.types.GodotReal.writeIndex($s, 7, $v.y.z); net.multigesture.kanama.types.GodotReal.writeIndex($s, 8, $v.z.z) }"
      NODE_PATH -> "GodotStrings.initString($s, $v.path)"
      OBJECT -> "$s.set(ADDRESS, 0, $v.handle)"
      in VARIANT_ONLY_RETURN_SHAPES -> "{}"
      else -> "$s.set($valueLayout, 0, $v)"
    }

  /**
   * Expression to read a Kotlin value from a ptrcall arg pointer [ptr]. For STRING the ptr IS the
   * GDExtensionConstStringPtr; no reinterpret needed. String ptrcall args are borrowed (not owned)
   * — no destroy.
   */
  fun readPtrcallArg(ptr: String): String =
    when (this) {
      BOOL -> "$ptr.reinterpret($ptrcallSizeBytesExpr).get(JAVA_BYTE, 0) != 0.toByte()"
      STRING -> "GodotStrings.readString($ptr)"
      VECTOR2 ->
        "run { val p = $ptr.reinterpret($ptrcallSizeBytesExpr); net.multigesture.kanama.types.Vector2(net.multigesture.kanama.types.GodotReal.readIndex(p, 0), net.multigesture.kanama.types.GodotReal.readIndex(p, 1)) }"
      VECTOR2I ->
        "net.multigesture.kanama.types.Vector2i($ptr.reinterpret($ptrcallSizeBytesExpr).get(JAVA_INT, 0), $ptr.reinterpret($ptrcallSizeBytesExpr).get(JAVA_INT, 4))"
      VECTOR3 ->
        "run { val p = $ptr.reinterpret($ptrcallSizeBytesExpr); net.multigesture.kanama.types.Vector3(net.multigesture.kanama.types.GodotReal.readIndex(p, 0), net.multigesture.kanama.types.GodotReal.readIndex(p, 1), net.multigesture.kanama.types.GodotReal.readIndex(p, 2)) }"
      VECTOR3I ->
        "net.multigesture.kanama.types.Vector3i($ptr.reinterpret($ptrcallSizeBytesExpr).get(JAVA_INT, 0), $ptr.reinterpret($ptrcallSizeBytesExpr).get(JAVA_INT, 4), $ptr.reinterpret($ptrcallSizeBytesExpr).get(JAVA_INT, 8))"
      QUATERNION ->
        "run { val p = $ptr.reinterpret($ptrcallSizeBytesExpr); net.multigesture.kanama.types.Quaternion(net.multigesture.kanama.types.GodotReal.readIndex(p, 0), net.multigesture.kanama.types.GodotReal.readIndex(p, 1), net.multigesture.kanama.types.GodotReal.readIndex(p, 2), net.multigesture.kanama.types.GodotReal.readIndex(p, 3)) }"
      BASIS ->
        "run { val p = $ptr.reinterpret($ptrcallSizeBytesExpr); net.multigesture.kanama.types.Basis(net.multigesture.kanama.types.Vector3(net.multigesture.kanama.types.GodotReal.readIndex(p, 0), net.multigesture.kanama.types.GodotReal.readIndex(p, 3), net.multigesture.kanama.types.GodotReal.readIndex(p, 6)), net.multigesture.kanama.types.Vector3(net.multigesture.kanama.types.GodotReal.readIndex(p, 1), net.multigesture.kanama.types.GodotReal.readIndex(p, 4), net.multigesture.kanama.types.GodotReal.readIndex(p, 7)), net.multigesture.kanama.types.Vector3(net.multigesture.kanama.types.GodotReal.readIndex(p, 2), net.multigesture.kanama.types.GodotReal.readIndex(p, 5), net.multigesture.kanama.types.GodotReal.readIndex(p, 8))) }"
      NODE_PATH -> "net.multigesture.kanama.types.NodePath(GodotStrings.readString($ptr))"
      OBJECT ->
        "net.multigesture.kanama.api.GodotObject($ptr.reinterpret($ptrcallSizeBytesExpr).get(ADDRESS, 0))"
      in VARIANT_ONLY_RETURN_SHAPES -> kotlinLiteralZero
      else -> "$ptr.reinterpret($ptrcallSizeBytesExpr).get($valueLayout, 0)"
    }

  /**
   * Statement to write Kotlin value [v] into ptrcall return pointer `rRet`. For STRING, the caller
   * (Godot) owns the returned String; no destroy.
   */
  fun writePtrcallReturn(v: String): String =
    when (this) {
      BOOL ->
        "rRet.reinterpret($ptrcallSizeBytesExpr).set(JAVA_BYTE, 0, if ($v) 1.toByte() else 0.toByte())"
      STRING -> "GodotStrings.initString(rRet, $v)"
      VECTOR2 ->
        "{ val p = rRet.reinterpret($ptrcallSizeBytesExpr); net.multigesture.kanama.types.GodotReal.writeIndex(p, 0, $v.x); net.multigesture.kanama.types.GodotReal.writeIndex(p, 1, $v.y) }"
      VECTOR2I ->
        "{ rRet.reinterpret($ptrcallSizeBytesExpr).set(JAVA_INT, 0, $v.x); rRet.reinterpret($ptrcallSizeBytesExpr).set(JAVA_INT, 4, $v.y) }"
      VECTOR3 ->
        "{ val p = rRet.reinterpret($ptrcallSizeBytesExpr); net.multigesture.kanama.types.GodotReal.writeIndex(p, 0, $v.x); net.multigesture.kanama.types.GodotReal.writeIndex(p, 1, $v.y); net.multigesture.kanama.types.GodotReal.writeIndex(p, 2, $v.z) }"
      VECTOR3I ->
        "{ rRet.reinterpret($ptrcallSizeBytesExpr).set(JAVA_INT, 0, $v.x); rRet.reinterpret($ptrcallSizeBytesExpr).set(JAVA_INT, 4, $v.y); rRet.reinterpret($ptrcallSizeBytesExpr).set(JAVA_INT, 8, $v.z) }"
      QUATERNION ->
        "{ val p = rRet.reinterpret($ptrcallSizeBytesExpr); net.multigesture.kanama.types.GodotReal.writeIndex(p, 0, $v.x); net.multigesture.kanama.types.GodotReal.writeIndex(p, 1, $v.y); net.multigesture.kanama.types.GodotReal.writeIndex(p, 2, $v.z); net.multigesture.kanama.types.GodotReal.writeIndex(p, 3, $v.w) }"
      BASIS ->
        "{ val p = rRet.reinterpret($ptrcallSizeBytesExpr); net.multigesture.kanama.types.GodotReal.writeIndex(p, 0, $v.x.x); net.multigesture.kanama.types.GodotReal.writeIndex(p, 1, $v.y.x); net.multigesture.kanama.types.GodotReal.writeIndex(p, 2, $v.z.x); net.multigesture.kanama.types.GodotReal.writeIndex(p, 3, $v.x.y); net.multigesture.kanama.types.GodotReal.writeIndex(p, 4, $v.y.y); net.multigesture.kanama.types.GodotReal.writeIndex(p, 5, $v.z.y); net.multigesture.kanama.types.GodotReal.writeIndex(p, 6, $v.x.z); net.multigesture.kanama.types.GodotReal.writeIndex(p, 7, $v.y.z); net.multigesture.kanama.types.GodotReal.writeIndex(p, 8, $v.z.z) }"
      NODE_PATH -> "GodotStrings.initString(rRet, $v.path)"
      OBJECT -> "rRet.reinterpret($ptrcallSizeBytesExpr).set(ADDRESS, 0, $v.handle)"
      in VARIANT_ONLY_RETURN_SHAPES -> "{}"
      else -> "rRet.reinterpret($ptrcallSizeBytesExpr).set($valueLayout, 0, $v)"
    }

  companion object {
    /**
     * Variant-only virtual-return shapes (task 13 + task 29): never validated as ptrcall
     * arg/scratch/property shapes, so the helpers above return safe placeholders (the real
     * marshalling is variantWriteRetExpr / the iOS encode path). VARIANT itself is excluded — it
     * predates this set and keeps its raw-else fallthrough.
     */
    val VARIANT_ONLY_RETURN_SHAPES: Set<TypeMapping> =
      setOf(
        ARRAY,
        PACKED_STRING_ARRAY,
        PACKED_BYTE_ARRAY,
        PACKED_INT32_ARRAY,
        PACKED_INT64_ARRAY,
        PACKED_FLOAT32_ARRAY,
        PACKED_FLOAT64_ARRAY,
        PACKED_VECTOR2_ARRAY,
        PACKED_VECTOR3_ARRAY,
        PACKED_COLOR_ARRAY,
        DICTIONARY,
        RID,
        RECT2,
        AABB,
        TRANSFORM2D,
        TRANSFORM3D,
        PROJECTION,
      )
  }
}

// ---------- Code emission ----------

internal class CodeEmitter(private val model: ClassModel, private val registrarName: String) {
  private val sb = StringBuilder()

  fun emit(): String {
    header()
    classOpen()
    privateFields()
    registerFunction()
    createInstance()
    freeInstance()
    getVirtualFunction()
    virtualCallFunctions()
    coroutineScopeHelpers()
    methodUpcalls()
    propertyUpcalls()
    classClose()
    signalHelpers()
    sb.appendMethodHelpers(model.simpleName, model.methods)
    sb.appendRpcHelpers(model.simpleName, model.methods)
    nameConstants()
    return sb.toString()
  }

  private fun signalHelpers() {
    if (model.signals.isEmpty()) return
    sb.appendLine()
    sb.appendLine("object ${model.simpleName}Signals {")
    val seen = mutableSetOf<String>()
    for (s in model.signals) {
      val functionName = uniqueConstantIdentifier(s.godotName, seen)
      val helperSuffix = signalHelperSuffix(s.godotName)
      val kotlinParams = s.args.joinToString(", ") { "${it.name}: ${it.kotlinType}" }
      val argList =
        s.args.joinToString(", ") {
          "Signals.Arg(VariantType.${it.type.variantTypeEnum}, ${it.signalEmitValueExpr()})"
        }
      val argsExpr = if (s.args.isEmpty()) "emptyList()" else "listOf($argList)"
      sb.appendLine(
        "    fun $functionName(instance: ${model.simpleName}${if (kotlinParams.isNotEmpty()) ", $kotlinParams" else ""}) {"
      )
      sb.appendLine(
        "        Signals.emit(instance.godotObject, \"${kotlinStringLiteral(s.godotName)}\", $argsExpr)"
      )
      sb.appendLine("    }")
      sb.appendLine()
      sb.appendLine(
        "    fun signal$helperSuffix(instance: ${model.simpleName}): net.multigesture.kanama.api.GodotSignal ="
      )
      sb.appendLine(
        "        net.multigesture.kanama.api.GodotObject(instance.godotObject).signal(\"${kotlinStringLiteral(s.godotName)}\")"
      )
      sb.appendLine()
      sb.appendLine("    fun connect$helperSuffix(")
      sb.appendLine("        instance: ${model.simpleName},")
      sb.appendLine("        target: net.multigesture.kanama.api.GodotObject,")
      sb.appendLine(
        "        flags: Long = net.multigesture.kanama.api.GodotObject.CONNECT_DEFAULT,"
      )
      sb.appendLine("        callback: ${signalCallbackType(s.args)},")
      sb.appendLine("    ): net.multigesture.kanama.api.SignalConnection =")
      sb.appendLine(
        "        signal$helperSuffix(instance).connect(target, argumentCount = ${s.args.size}, flags = flags) { args ->"
      )
      sb.appendLine("            ${signalCallbackInvocation(s.args)}")
      sb.appendLine("        }")
      sb.appendLine()
      sb.appendLine("    suspend fun await$helperSuffix(")
      sb.appendLine("        instance: ${model.simpleName},")
      sb.appendLine("        target: net.multigesture.kanama.api.GodotObject,")
      sb.appendLine("    ): ${signalAwaitReturnType(s.args)} {")
      sb.appendLine(
        "        val args = signal$helperSuffix(instance).await(target, argumentCount = ${s.args.size})"
      )
      sb.appendLine("        return ${signalAwaitReturnExpr(s.args)}")
      sb.appendLine("    }")
    }
    sb.appendLine("}")
  }

  private fun nameConstants() {
    val methods = model.methods.filter { it.kind == MethodKind.REGULAR }
    if (methods.isEmpty() && model.properties.isEmpty() && model.signals.isEmpty()) return
    sb.appendLine()
    sb.appendLine("object ${model.simpleName}Names {")
    if (methods.isNotEmpty()) {
      sb.appendLine("    object Methods {")
      val seen = mutableSetOf<String>()
      for (m in methods) {
        sb.appendLine(
          "        const val ${uniqueConstantIdentifier(m.godotName, seen)}: String = \"${kotlinStringLiteral(m.godotName)}\""
        )
      }
      sb.appendLine("    }")
    }
    if (model.properties.isNotEmpty()) {
      sb.appendLine("    object Properties {")
      val seen = mutableSetOf<String>()
      for (p in model.properties) {
        sb.appendLine(
          "        const val ${uniqueConstantIdentifier(p.godotName, seen)}: String = \"${kotlinStringLiteral(p.godotName)}\""
        )
      }
      sb.appendLine("    }")
    }
    if (model.signals.isNotEmpty()) {
      sb.appendLine("    object Signals {")
      val seen = mutableSetOf<String>()
      for (s in model.signals) {
        sb.appendLine(
          "        const val ${uniqueConstantIdentifier(s.godotName, seen)}: String = \"${kotlinStringLiteral(s.godotName)}\""
        )
      }
      sb.appendLine("    }")
    }
    sb.appendLine("}")
  }

  private fun header() {
    sb.appendLine("// Generated by KanamaProcessor — do not edit.")
    sb.appendLine("// Source: ${model.fqName}")
    sb.appendLine("package net.multigesture.kanama.generated")
    sb.appendLine()
    sb.appendLine("import net.multigesture.kanama.binding.ObjectRegistry")
    sb.appendLine("import net.multigesture.kanama.binding.runtime.ClassDB")
    sb.appendLine("import net.multigesture.kanama.binding.runtime.GodotStrings")
    sb.appendLine("import net.multigesture.kanama.binding.runtime.Signals")
    sb.appendLine("import net.multigesture.kanama.binding.runtime.Upcalls")
    sb.appendLine("import net.multigesture.kanama.binding.runtime.VariantConverters")
    sb.appendLine("import net.multigesture.kanama.binding.runtime.VariantType")
    sb.appendLine("import net.multigesture.kanama.ffi.GodotFFI")
    sb.appendLine("import net.multigesture.kanama.api.kotlinScriptInstance")
    sb.appendLine("import ${model.fqName}")
    sb.appendLine("import java.lang.foreign.Arena")
    sb.appendLine("import java.lang.foreign.FunctionDescriptor")
    sb.appendLine("import java.lang.foreign.MemorySegment")
    sb.appendLine("import java.lang.foreign.ValueLayout.ADDRESS")
    sb.appendLine("import java.lang.foreign.ValueLayout.JAVA_BYTE")
    sb.appendLine("import java.lang.foreign.ValueLayout.JAVA_FLOAT")
    sb.appendLine("import java.lang.foreign.ValueLayout.JAVA_DOUBLE")
    sb.appendLine("import java.lang.foreign.ValueLayout.JAVA_INT")
    sb.appendLine("import java.lang.foreign.ValueLayout.JAVA_LONG")
    sb.appendLine("import java.lang.invoke.MethodType")
    sb.appendLine()
  }

  private fun classOpen() {
    sb.appendLine("object $registrarName {")
    sb.appendLine()
  }

  private fun classClose() {
    sb.appendLine("}")
  }

  private fun privateFields() {
    sb.appendLine("    private lateinit var cls: ClassDB.RegisteredClass")
    for (v in model.virtuals) {
      sb.appendLine("    private var ${v.virtualName.trimStart('_')}NameValue: Long = 0L")
      sb.appendLine(
        "    private lateinit var ${v.virtualName.trimStart('_')}CallStub: MemorySegment"
      )
    }
    sb.appendLine()
  }

  private fun registerFunction() {
    sb.appendLine("    fun register(library: MemorySegment) {")
    // Pre-cache interned virtual name longs + build per-virtual stubs.
    for (v in model.virtuals) {
      val slot = v.virtualName.trimStart('_')
      sb.appendLine(
        "        ${slot}NameValue = GodotStrings.stringNameStorage(\"${v.virtualName}\")"
      )
      sb.appendLine("        ${slot}CallStub = Upcalls.stub(")
      sb.appendLine("            $registrarName::class.java, \"${v.callFunctionName}\",")
      sb.appendLine("            MethodType.methodType(")
      sb.appendLine("                Void.TYPE,")
      sb.appendLine("                MemorySegment::class.java,")
      sb.appendLine("                MemorySegment::class.java,")
      sb.appendLine("                MemorySegment::class.java,")
      sb.appendLine("            ),")
      sb.appendLine("            FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS),")
      sb.appendLine("        )")
    }
    sb.appendLine("        val createInstanceStub = Upcalls.stub(")
    sb.appendLine("            $registrarName::class.java, \"createInstance\",")
    sb.appendLine("            MethodType.methodType(")
    sb.appendLine("                MemorySegment::class.java,")
    sb.appendLine("                MemorySegment::class.java,")
    sb.appendLine("                java.lang.Byte.TYPE,")
    sb.appendLine("            ),")
    sb.appendLine("            FunctionDescriptor.of(ADDRESS, ADDRESS, JAVA_BYTE),")
    sb.appendLine("        )")
    sb.appendLine("        val freeInstanceStub = Upcalls.stub(")
    sb.appendLine("            $registrarName::class.java, \"freeInstance\",")
    sb.appendLine("            MethodType.methodType(")
    sb.appendLine("                Void.TYPE,")
    sb.appendLine("                MemorySegment::class.java,")
    sb.appendLine("                MemorySegment::class.java,")
    sb.appendLine("            ),")
    sb.appendLine("            FunctionDescriptor.ofVoid(ADDRESS, ADDRESS),")
    sb.appendLine("        )")
    sb.appendLine("        val getVirtualStub = Upcalls.stub(")
    sb.appendLine("            $registrarName::class.java, \"getVirtual\",")
    sb.appendLine("            MethodType.methodType(")
    sb.appendLine("                MemorySegment::class.java,")
    sb.appendLine("                MemorySegment::class.java,")
    sb.appendLine("                MemorySegment::class.java,")
    sb.appendLine("                java.lang.Integer.TYPE,")
    sb.appendLine("            ),")
    sb.appendLine("            FunctionDescriptor.of(ADDRESS, ADDRESS, ADDRESS, JAVA_INT),")
    sb.appendLine("        )")
    sb.appendLine("        cls = ClassDB.registerClass(")
    sb.appendLine("            library,")
    sb.appendLine("            ClassDB.ClassSpec(")
    sb.appendLine("                name = \"${model.simpleName}\",")
    sb.appendLine("                parentName = \"${model.parentClassName}\",")
    sb.appendLine("                isTool = ${model.isTool},")
    sb.appendLine("                createInstance = createInstanceStub,")
    sb.appendLine("                freeInstance = freeInstanceStub,")
    sb.appendLine("                getVirtual = getVirtualStub,")
    sb.appendLine("            ),")
    sb.appendLine("        )")
    sb.appendLine(
      "        System.err.println(\"[kanama:kt] registered class ${model.simpleName} : ${model.parentClassName}\")"
    )

    // Emit method registration calls for @RegisterFunction methods.
    for (m in model.methods) {
      emitMethodRegistration(m)
    }
    // Emit synthetic get_/set_ methods + property registration.
    for (p in model.properties) {
      emitPropertyAccessorRegistration(p)
      sb.appendLine("        ClassDB.registerProperty(")
      sb.appendLine("            library,")
      sb.appendLine("            cls,")
      sb.appendLine("            propertyName = \"${p.godotName}\",")
      sb.appendLine("            type = VariantType.${p.type.variantTypeEnum},")
      sb.appendLine("            setterName = \"set_${p.godotName}\",")
      sb.appendLine("            getterName = \"get_${p.godotName}\",")
      if (p.hint != 0) sb.appendLine("            hint = ${p.hint},")
      if (p.hintString.isNotEmpty()) sb.appendLine("            hintString = \"${p.hintString}\",")
      if (p.usage != 6) sb.appendLine("            usage = ${p.usage},")
      sb.appendLine("        )")
      sb.appendLine(
        "        System.err.println(\"[kanama:kt] registered property ${model.simpleName}.${p.godotName}\")"
      )
    }
    for (s in model.signals) {
      val argsExpr =
        if (s.args.isEmpty()) {
          "emptyList()"
        } else {
          s.args.joinToString(prefix = "listOf(", postfix = ")") {
            """ClassDB.SignalArg("${it.name}", VariantType.${it.type.variantTypeEnum})"""
          }
        }
      sb.appendLine("        ClassDB.registerSignal(")
      sb.appendLine("            library,")
      sb.appendLine("            cls,")
      sb.appendLine("            signalName = \"${s.godotName}\",")
      sb.appendLine("            args = $argsExpr,")
      sb.appendLine("        )")
      sb.appendLine(
        "        System.err.println(\"[kanama:kt] registered signal ${model.simpleName}.${s.godotName}\")"
      )
    }

    sb.appendLine("    }")
    sb.appendLine()
  }

  private fun emitMethodRegistration(m: MethodModel) {
    val returnExpr =
      if (m.returnType != null) {
        "VariantType.${m.returnType.variantTypeEnum}"
      } else {
        "null"
      }
    val argsExpr =
      if (m.args.isEmpty()) {
        "emptyList()"
      } else {
        m.args.joinToString(prefix = "listOf(", postfix = ")") {
          """ClassDB.MethodArg("${it.name}", VariantType.${it.type.variantTypeEnum})"""
        }
      }
    sb.appendLine("        ClassDB.registerMethod(")
    sb.appendLine("            library,")
    sb.appendLine("            cls,")
    sb.appendLine("            ClassDB.MethodSpec(")
    sb.appendLine("                name = \"${m.godotName}\",")
    sb.appendLine("                returnType = $returnExpr,")
    sb.appendLine("                args = $argsExpr,")
    sb.appendLine("                callStub = Upcalls.stub(")
    sb.appendLine("                    $registrarName::class.java, \"${callFunctionName(m)}\",")
    sb.appendLine("                    methodCallType, methodCallDescriptor,")
    sb.appendLine("                ),")
    sb.appendLine("                ptrcallStub = Upcalls.stub(")
    sb.appendLine("                    $registrarName::class.java, \"${ptrcallFunctionName(m)}\",")
    sb.appendLine("                    methodPtrcallType, methodPtrcallDescriptor,")
    sb.appendLine("                ),")
    sb.appendLine("            ),")
    sb.appendLine("        )")
    sb.appendLine(
      "        System.err.println(\"[kanama:kt] registered method ${model.simpleName}.${m.godotName}\")"
    )
  }

  private fun emitPropertyAccessorRegistration(p: PropertyModel) {
    // Generate a GETTER method model
    val getter =
      MethodModel(
        kotlinName = "get_${p.godotName}",
        godotName = "get_${p.godotName}",
        returnType = p.type,
        args = emptyList(),
        kind = MethodKind.PROPERTY_GETTER,
        propertyKotlinName = p.kotlinName,
      )
    emitMethodRegistration(getter)
    if (p.isMutable) {
      val setter =
        MethodModel(
          kotlinName = "set_${p.godotName}",
          godotName = "set_${p.godotName}",
          returnType = null,
          args = listOf(ArgModel("value", p.type)),
          kind = MethodKind.PROPERTY_SETTER,
          propertyKotlinName = p.kotlinName,
        )
      emitMethodRegistration(setter)
    }
  }

  private fun createInstance() {
    sb.appendLine("    @JvmStatic")
    sb.appendLine(
      "    fun createInstance(userdata: MemorySegment, notifyPostinitialize: Byte): MemorySegment {"
    )
    sb.appendLine("        val classdbConstruct = GodotFFI.lookup(")
    sb.appendLine("            \"classdb_construct_object2\",")
    sb.appendLine("            FunctionDescriptor.of(ADDRESS, ADDRESS),")
    sb.appendLine("        )")
    sb.appendLine("        val objectSetInstance = GodotFFI.lookup(")
    sb.appendLine("            \"object_set_instance\",")
    sb.appendLine("            FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS),")
    sb.appendLine("        )")
    sb.appendLine("        val obj = classdbConstruct.invoke(cls.parentName) as MemorySegment")
    sb.appendLine("        val kotlinInstance = ${model.simpleName}(obj)")
    sb.appendLine("        val handle = ObjectRegistry.register(kotlinInstance)")
    sb.appendLine(
      "        objectSetInstance.invoke(obj, cls.className, MemorySegment.ofAddress(handle))"
    )
    sb.appendLine("        System.err.println(")
    sb.appendLine(
      "            \"[kanama:kt] ${model.simpleName}.createInstance handle=\" + handle +"
    )
    sb.appendLine("                \" obj=0x\" + obj.address().toString(16)")
    sb.appendLine("        )")
    sb.appendLine("        return obj")
    sb.appendLine("    }")
    sb.appendLine()
  }

  private fun freeInstance() {
    sb.appendLine("    @JvmStatic")
    sb.appendLine("    fun freeInstance(userdata: MemorySegment, instance: MemorySegment) {")
    sb.appendLine("        val handle = instance.address()")
    sb.appendLine("        ObjectRegistry.unregister(handle)")
    sb.appendLine(
      "        System.err.println(\"[kanama:kt] ${model.simpleName}.freeInstance handle=\" + handle)"
    )
    sb.appendLine("    }")
    sb.appendLine()
  }

  private fun getVirtualFunction() {
    sb.appendLine("    @JvmStatic")
    sb.appendLine(
      "    fun getVirtual(userdata: MemorySegment, name: MemorySegment, hash: Int): MemorySegment {"
    )
    if (model.virtuals.isEmpty()) {
      sb.appendLine("        return MemorySegment.NULL")
    } else {
      sb.appendLine("        val v = name.reinterpret(8).get(JAVA_LONG, 0)")
      sb.appendLine("        return when (v) {")
      for (virt in model.virtuals) {
        val slot = virt.virtualName.trimStart('_')
        sb.appendLine("            ${slot}NameValue -> ${slot}CallStub")
      }
      sb.appendLine("            else -> MemorySegment.NULL")
      sb.appendLine("        }")
    }
    sb.appendLine("    }")
    sb.appendLine()
  }

  private fun virtualCallFunctions() {
    for (v in model.virtuals) {
      sb.appendLine("    @JvmStatic")
      sb.appendLine(
        "    fun ${v.callFunctionName}(instance: MemorySegment, args: MemorySegment, ret: MemorySegment) {"
      )
      sb.appendLine(
        "        val kotlinInstance = ObjectRegistry.get(instance.address()) as? ${model.simpleName} ?: return"
      )
      if (v.args.isEmpty()) {
        sb.appendLine("        kotlinInstance.${v.kotlinMethodName}()")
      } else {
        // Virtual args use the ptrcall convention: args is an array of typed pointers.
        sb.appendLine("        val argsArray = args.reinterpret(${v.args.size * 8}L)")
        val argVars =
          v.args.mapIndexed { i, arg ->
            val varName = "arg${i}Value"
            sb.appendLine(
              "        val $varName = ${arg.readPtrcallArg("argsArray.get(ADDRESS, ${i * 8}L)")}"
            )
            varName
          }
        sb.appendLine("        kotlinInstance.${v.kotlinMethodName}(${argVars.joinToString(", ")})")
      }
      if (v.virtualName == "_exit_tree") {
        sb.appendLine("        cancelKanamaScope(kotlinInstance)")
      }
      sb.appendLine("    }")
      sb.appendLine()
    }
  }

  private fun coroutineScopeHelpers() {
    if (model.virtuals.none { it.virtualName == "_exit_tree" }) return
    sb.appendLine("    private fun cancelKanamaScope(instance: Any) {")
    sb.appendLine(
      "        (instance as? net.multigesture.kanama.api.KanamaCoroutineOwner)?.kanamaScope?.cancel()"
    )
    sb.appendLine("    }")
    sb.appendLine()
  }

  private fun methodUpcalls() {
    // Shared method-call / ptrcall type + descriptor constants.
    if (model.methods.isNotEmpty() || model.properties.isNotEmpty()) {
      sb.appendLine("    private val methodCallType: MethodType = MethodType.methodType(")
      sb.appendLine("        Void.TYPE,")
      sb.appendLine("        MemorySegment::class.java,")
      sb.appendLine("        MemorySegment::class.java,")
      sb.appendLine("        MemorySegment::class.java,")
      sb.appendLine("        java.lang.Long.TYPE,")
      sb.appendLine("        MemorySegment::class.java,")
      sb.appendLine("        MemorySegment::class.java,")
      sb.appendLine("    )")
      sb.appendLine(
        "    private val methodCallDescriptor: FunctionDescriptor = FunctionDescriptor.ofVoid("
      )
      sb.appendLine("        ADDRESS, ADDRESS, ADDRESS, JAVA_LONG, ADDRESS, ADDRESS,")
      sb.appendLine("    )")
      sb.appendLine("    private val methodPtrcallType: MethodType = MethodType.methodType(")
      sb.appendLine("        Void.TYPE,")
      sb.appendLine("        MemorySegment::class.java,")
      sb.appendLine("        MemorySegment::class.java,")
      sb.appendLine("        MemorySegment::class.java,")
      sb.appendLine("        MemorySegment::class.java,")
      sb.appendLine("    )")
      sb.appendLine(
        "    private val methodPtrcallDescriptor: FunctionDescriptor = FunctionDescriptor.ofVoid("
      )
      sb.appendLine("        ADDRESS, ADDRESS, ADDRESS, ADDRESS,")
      sb.appendLine("    )")
      sb.appendLine()
    }
    for (m in model.methods) {
      emitMethodCallUpcall(m)
      emitMethodPtrcallUpcall(m)
    }
  }

  private fun propertyUpcalls() {
    for (p in model.properties) {
      val getter =
        MethodModel(
          kotlinName = "get_${p.godotName}",
          godotName = "get_${p.godotName}",
          returnType = p.type,
          args = emptyList(),
          kind = MethodKind.PROPERTY_GETTER,
          propertyKotlinName = p.kotlinName,
        )
      emitMethodCallUpcall(getter)
      emitMethodPtrcallUpcall(getter)
      if (p.isMutable) {
        val setter =
          MethodModel(
            kotlinName = "set_${p.godotName}",
            godotName = "set_${p.godotName}",
            returnType = null,
            args = listOf(ArgModel("value", p.type)),
            kind = MethodKind.PROPERTY_SETTER,
            propertyKotlinName = p.kotlinName,
          )
        emitMethodCallUpcall(setter)
        emitMethodPtrcallUpcall(setter)
      }
    }
  }

  /** The Kotlin expression that invokes this method on a user-class instance. */
  private fun invocationExpr(m: MethodModel, argVars: List<String>): String =
    when (m.kind) {
      MethodKind.REGULAR -> "kotlinInstance.${m.kotlinName}(${argVars.joinToString(", ")})"
      MethodKind.PROPERTY_GETTER -> "kotlinInstance.${m.propertyKotlinName}"
      MethodKind.PROPERTY_SETTER -> "kotlinInstance.${m.propertyKotlinName} = ${argVars.single()}"
    }

  private fun callFunctionName(m: MethodModel): String =
    when (m.kind) {
      MethodKind.REGULAR -> "call_${m.godotName}"
      MethodKind.PROPERTY_GETTER,
      MethodKind.PROPERTY_SETTER -> "call_${m.godotName}"
    }

  private fun ptrcallFunctionName(m: MethodModel): String = "ptrcall_${m.godotName}"

  private fun emitMethodCallUpcall(m: MethodModel) {
    sb.appendLine("    @JvmStatic")
    sb.appendLine("    fun ${callFunctionName(m)}(")
    sb.appendLine("        methodUserdata: MemorySegment,")
    sb.appendLine("        instance: MemorySegment,")
    sb.appendLine("        args: MemorySegment,")
    sb.appendLine("        argCount: Long,")
    sb.appendLine("        rReturn: MemorySegment,")
    sb.appendLine("        rError: MemorySegment,")
    sb.appendLine("    ) {")
    sb.appendLine(
      "        val kotlinInstance = ObjectRegistry.get(instance.address()) as? ${model.simpleName} ?: return"
    )
    if (m.args.isEmpty() && m.returnType == null) {
      sb.appendLine("        ${invocationExpr(m, emptyList())}")
    } else {
      sb.appendLine("        Arena.ofConfined().use { arena ->")
      val argVars = mutableListOf<String>()
      if (m.args.isNotEmpty()) {
        sb.appendLine("            val argsArray = args.reinterpret(${m.args.size * 8}L)")
        m.args.forEachIndexed { i, arg ->
          val scratch = "arg${i}Scratch"
          val value = "arg${i}Value"
          sb.appendLine(
            "            val $scratch = arena.allocate(${arg.type.scratchAllocationExpr})"
          )
          sb.appendLine(
            "            VariantConverters.variantToType(VariantType.${arg.type.variantTypeEnum})"
          )
          sb.appendLine("                .invoke($scratch, argsArray.get(ADDRESS, ${i * 8}L))")
          sb.appendLine("            val $value = ${arg.readFromScratch(scratch)}")
          if (arg.type.needsScratchDestroy)
            sb.appendLine("            GodotStrings.destroyString($scratch)")
          argVars += value
        }
      }
      val invocation = invocationExpr(m, argVars)
      if (m.returnType != null) {
        sb.appendLine("            val result = $invocation")
        sb.appendLine(
          "            val retScratch = arena.allocate(${m.returnType.scratchAllocationExpr})"
        )
        sb.appendLine("            ${m.returnType.writeToScratch("retScratch", "result")}")
        sb.appendLine(
          "            VariantConverters.variantFromType(VariantType.${m.returnType.variantTypeEnum}).invoke(rReturn, retScratch)"
        )
        if (m.returnType.needsScratchDestroy)
          sb.appendLine("            GodotStrings.destroyString(retScratch)")
      } else {
        sb.appendLine("            $invocation")
      }
      sb.appendLine("        }")
    }
    sb.appendLine("    }")
    sb.appendLine()
  }

  private fun emitMethodPtrcallUpcall(m: MethodModel) {
    sb.appendLine("    @JvmStatic")
    sb.appendLine("    fun ${ptrcallFunctionName(m)}(")
    sb.appendLine("        methodUserdata: MemorySegment,")
    sb.appendLine("        instance: MemorySegment,")
    sb.appendLine("        args: MemorySegment,")
    sb.appendLine("        rRet: MemorySegment,")
    sb.appendLine("    ) {")
    sb.appendLine(
      "        val kotlinInstance = ObjectRegistry.get(instance.address()) as? ${model.simpleName} ?: return"
    )
    val argVars = mutableListOf<String>()
    if (m.args.isNotEmpty()) {
      sb.appendLine("        val argsArray = args.reinterpret(${m.args.size * 8}L)")
      m.args.forEachIndexed { i, arg ->
        val value = "arg${i}Value"
        sb.appendLine(
          "        val $value = ${arg.readPtrcallArg("argsArray.get(ADDRESS, ${i * 8}L)")}"
        )
        argVars += value
      }
    }
    val invocation = invocationExpr(m, argVars)
    if (m.returnType != null) {
      sb.appendLine("        val result = $invocation")
      sb.appendLine("        ${m.returnType.writePtrcallReturn("result")}")
    } else {
      sb.appendLine("        $invocation")
    }
    sb.appendLine("    }")
    sb.appendLine()
  }
}

// ---------- ScriptCodeEmitter ----------

internal class ScriptCodeEmitter(
  private val model: ScriptModel,
  private val registrarName: String,
) {
  private val sb = StringBuilder()
  private val hasInspectableProperties: Boolean
    get() = model.properties.isNotEmpty() || model.toolButtons.isNotEmpty()

  fun emit(): String {
    header()
    objectOpen()
    internedNames()
    registerFunction()
    factory()
    coroutineScopeHelpers()
    emitCleanupHelpers()
    objectClose()
    signalHelpers()
    sb.appendMethodHelpers(model.simpleName, model.methods)
    sb.appendRpcHelpers(model.simpleName, model.methods)
    nameConstants()
    return sb.toString()
  }

  private fun signalHelpers() {
    if (model.signals.isEmpty()) return
    sb.appendLine()
    sb.appendLine("object ${model.simpleName}Signals {")
    val seen = mutableSetOf<String>()
    for (s in model.signals) {
      val functionName = uniqueConstantIdentifier(s.godotName, seen)
      val helperSuffix = signalHelperSuffix(s.godotName)
      val kotlinParams = s.args.joinToString(", ") { "${it.name}: ${it.kotlinType}" }
      val argList =
        s.args.joinToString(", ") {
          "Signals.Arg(VariantType.${it.type.variantTypeEnum}, ${it.signalEmitValueExpr()})"
        }
      val argsExpr = if (s.args.isEmpty()) "emptyList()" else "listOf($argList)"
      sb.appendLine(
        "    fun $functionName(instance: ${model.simpleName}${if (kotlinParams.isNotEmpty()) ", $kotlinParams" else ""}) {"
      )
      sb.appendLine(
        "        Signals.emit(instance.godotObject, \"${kotlinStringLiteral(s.godotName)}\", $argsExpr)"
      )
      sb.appendLine("    }")
      sb.appendLine()
      sb.appendLine(
        "    fun signal$helperSuffix(instance: ${model.simpleName}): net.multigesture.kanama.api.GodotSignal ="
      )
      sb.appendLine(
        "        net.multigesture.kanama.api.GodotObject(instance.godotObject).signal(\"${kotlinStringLiteral(s.godotName)}\")"
      )
      sb.appendLine()
      sb.appendLine("    fun connect$helperSuffix(")
      sb.appendLine("        instance: ${model.simpleName},")
      sb.appendLine("        target: net.multigesture.kanama.api.GodotObject,")
      sb.appendLine(
        "        flags: Long = net.multigesture.kanama.api.GodotObject.CONNECT_DEFAULT,"
      )
      sb.appendLine("        callback: ${signalCallbackType(s.args)},")
      sb.appendLine("    ): net.multigesture.kanama.api.SignalConnection =")
      sb.appendLine(
        "        signal$helperSuffix(instance).connect(target, argumentCount = ${s.args.size}, flags = flags) { args ->"
      )
      sb.appendLine("            ${signalCallbackInvocation(s.args)}")
      sb.appendLine("        }")
      sb.appendLine()
      sb.appendLine("    suspend fun await$helperSuffix(")
      sb.appendLine("        instance: ${model.simpleName},")
      sb.appendLine("        target: net.multigesture.kanama.api.GodotObject,")
      sb.appendLine("    ): ${signalAwaitReturnType(s.args)} {")
      sb.appendLine(
        "        val args = signal$helperSuffix(instance).await(target, argumentCount = ${s.args.size})"
      )
      sb.appendLine("        return ${signalAwaitReturnExpr(s.args)}")
      sb.appendLine("    }")
    }
    sb.appendLine("}")
  }

  private fun nameConstants() {
    if (
      model.virtuals.isEmpty() &&
        model.methods.isEmpty() &&
        model.properties.isEmpty() &&
        model.toolButtons.isEmpty() &&
        model.signals.isEmpty()
    )
      return
    sb.appendLine()
    sb.appendLine("object ${model.simpleName}Names {")
    if (model.virtuals.isNotEmpty() || model.methods.isNotEmpty()) {
      sb.appendLine("    object Methods {")
      val seen = mutableSetOf<String>()
      for (v in model.virtuals) {
        sb.appendLine(
          "        const val ${uniqueConstantIdentifier(v.virtualName, seen)}: String = \"${kotlinStringLiteral(v.virtualName)}\""
        )
      }
      for (m in model.methods) {
        sb.appendLine(
          "        const val ${uniqueConstantIdentifier(m.godotName, seen)}: String = \"${kotlinStringLiteral(m.godotName)}\""
        )
      }
      sb.appendLine("    }")
    }
    if (model.properties.isNotEmpty()) {
      sb.appendLine("    object Properties {")
      val seen = mutableSetOf<String>()
      for (p in model.properties) {
        sb.appendLine(
          "        const val ${uniqueConstantIdentifier(p.godotName, seen)}: String = \"${kotlinStringLiteral(p.godotName)}\""
        )
      }
      for (button in model.toolButtons) {
        sb.appendLine(
          "        const val ${uniqueConstantIdentifier(button.propertyName, seen)}: String = \"${kotlinStringLiteral(button.propertyName)}\""
        )
      }
      sb.appendLine("    }")
    }
    if (model.signals.isNotEmpty()) {
      sb.appendLine("    object Signals {")
      val seen = mutableSetOf<String>()
      for (s in model.signals) {
        sb.appendLine(
          "        const val ${uniqueConstantIdentifier(s.godotName, seen)}: String = \"${kotlinStringLiteral(s.godotName)}\""
        )
      }
      sb.appendLine("    }")
    }
    sb.appendLine("}")
  }

  private fun header() {
    sb.appendLine("// Generated by KanamaProcessor — do not edit.")
    sb.appendLine("// Source: ${model.fqName}")
    sb.appendLine("package net.multigesture.kanama.generated")
    sb.appendLine()
    sb.appendLine("import net.multigesture.kanama.binding.KanamaScript")
    sb.appendLine("import net.multigesture.kanama.binding.KanamaScriptInstance")
    sb.appendLine("import net.multigesture.kanama.binding.ScriptBridge")
    sb.appendLine("import net.multigesture.kanama.binding.runtime.BuiltinTypes")
    sb.appendLine("import net.multigesture.kanama.binding.runtime.ClassDB")
    sb.appendLine("import net.multigesture.kanama.binding.runtime.GodotStrings")
    sb.appendLine("import net.multigesture.kanama.binding.runtime.SignalCallbackRegistry")
    sb.appendLine("import net.multigesture.kanama.binding.runtime.Signals")
    sb.appendLine("import net.multigesture.kanama.binding.runtime.VariantConverters")
    sb.appendLine("import net.multigesture.kanama.binding.runtime.VariantType")
    sb.appendLine("import net.multigesture.kanama.api.kotlinScriptInstance")
    sb.appendLine("import ${model.fqName}")
    sb.appendLine("import java.lang.foreign.Arena")
    sb.appendLine("import java.lang.foreign.MemorySegment")
    sb.appendLine("import java.lang.foreign.ValueLayout.ADDRESS")
    sb.appendLine("import java.lang.foreign.ValueLayout.JAVA_BYTE")
    sb.appendLine("import java.lang.foreign.ValueLayout.JAVA_FLOAT")
    sb.appendLine("import java.lang.foreign.ValueLayout.JAVA_DOUBLE")
    sb.appendLine("import java.lang.foreign.ValueLayout.JAVA_INT")
    sb.appendLine("import java.lang.foreign.ValueLayout.JAVA_LONG")
    sb.appendLine()
  }

  private fun objectOpen() {
    sb.appendLine("object $registrarName {")
    sb.appendLine()
  }

  private fun objectClose() {
    sb.appendLine("}")
  }

  private fun internedNames() {
    // Lifecycle virtuals
    for (i in 0..3) {
      sb.appendLine("    private var ${nameVar("__kanama_signal_dispatch$i")}: Long = 0L")
    }
    for (v in model.virtuals) {
      sb.appendLine("    private var ${nameVar(v.virtualName)}: Long = 0L")
    }
    // @RegisterFunction methods
    for (m in model.methods) {
      sb.appendLine("    private var ${nameVar(m.godotName)}: Long = 0L")
    }
    // @Signal declarations
    for (s in model.signals) {
      sb.appendLine("    private var ${nameVar(s.godotName)}: Long = 0L")
    }
    // @ScriptProperty properties
    for (p in model.properties) {
      sb.appendLine("    private var ${nameVar(p.godotName)}: Long = 0L")
    }
    // @ToolButton callable properties
    for (button in model.toolButtons) {
      sb.appendLine("    private var ${nameVar(button.propertyName)}: Long = 0L")
    }
    if (hasInspectableProperties) {
      sb.appendLine("    private var propertyListPtr: MemorySegment = MemorySegment.NULL")
      for (p in model.properties) {
        val defaultName = p.kotlinName.replaceFirstChar { it.uppercase() }
        sb.appendLine(
          "    private var default$defaultName: ${scriptPropertyKotlinType(p)} = ${p.defaultLiteral ?: scriptPropertyZeroLiteral(p)}"
        )
        sb.appendLine(
          "    private var default${defaultName}Available: Boolean = ${p.defaultLiteral != null}"
        )
      }
    }
    sb.appendLine()
  }

  private fun registerFunction() {
    sb.appendLine("    fun register(library: MemorySegment) {")
    for (i in 0..3) {
      sb.appendLine(
        "        ${nameVar("__kanama_signal_dispatch$i")} = GodotStrings.stringNameStorage(\"__kanama_signal_dispatch$i\")"
      )
    }
    for (v in model.virtuals) {
      sb.appendLine(
        "        ${nameVar(v.virtualName)} = GodotStrings.stringNameStorage(\"${v.virtualName}\")"
      )
    }
    for (m in model.methods) {
      sb.appendLine(
        "        ${nameVar(m.godotName)} = GodotStrings.stringNameStorage(\"${m.godotName}\")"
      )
    }
    for (s in model.signals) {
      sb.appendLine(
        "        ${nameVar(s.godotName)} = GodotStrings.stringNameStorage(\"${s.godotName}\")"
      )
    }
    for (p in model.properties) {
      sb.appendLine(
        "        ${nameVar(p.godotName)} = GodotStrings.stringNameStorage(\"${p.godotName}\")"
      )
    }
    for (button in model.toolButtons) {
      sb.appendLine(
        "        ${nameVar(button.propertyName)} = GodotStrings.stringNameStorage(\"${button.propertyName}\")"
      )
    }
    if (hasInspectableProperties) {
      val specs = scriptPropertyListSpecExpressions().joinToString(",\n            ")
      sb.appendLine("        propertyListPtr = ClassDB.buildPropertyList(listOf(")
      sb.appendLine("            $specs,")
      sb.appendLine("        ))")
    }
    sb.appendLine("        KanamaScript.construct(")
    sb.appendLine("            instanceBaseType = \"${model.attachTo}\",")
    sb.appendLine("            isTool = ${model.isTool},")
    sb.appendLine("            kotlinClassName = \"${model.fqName}\",")
    sb.appendLine(
      "            globalName = \"${if (model.isGlobalClass) model.simpleName else ""}\","
    )
    sb.appendLine("            factory = $registrarName::factory,")
    sb.appendLine("            hasMethod = $registrarName::hasMethod,")
    sb.appendLine("            getMethodArgumentCount = $registrarName::getMethodArgumentCount,")
    sb.appendLine("            hasScriptSignal = $registrarName::hasScriptSignal,")
    sb.appendLine("            writeScriptMethodList = $registrarName::writeScriptMethodList,")
    sb.appendLine("            writeScriptPropertyList = $registrarName::writeScriptPropertyList,")
    sb.appendLine("            writeScriptSignalList = $registrarName::writeScriptSignalList,")
    sb.appendLine("            writeRpcConfig = $registrarName::writeRpcConfig,")
    sb.appendLine("            writeMethodInfo = $registrarName::writeMethodInfo,")
    if (model.properties.isNotEmpty()) {
      sb.appendLine("            hasPropertyDefault = $registrarName::hasPropertyDefault,")
      sb.appendLine("            writePropertyDefault = $registrarName::writePropertyDefault,")
    }
    if (hasInspectableProperties) {
      // Hand the script-level property list to KanamaScript so the
      // editor placeholder instance (used for non-@Tool scripts in
      // editor mode) can expose @ScriptProperty fields in the
      // inspector without invoking the user's factory.
      sb.appendLine("            propertyListPtr = propertyListPtr,")
      sb.appendLine("            propertyCount = ${scriptPropertyListEntryCount()},")
    }
    sb.appendLine("        )")
    sb.appendLine("    }")
    sb.appendLine()
    emitMethodMetadataHelpers()
    emitScriptMetadataWriters()
    if (model.properties.isNotEmpty()) {
      emitPropertyDefaultHelpers()
    }
  }

  private fun emitMethodMetadataHelpers() {
    val methodCases = linkedSetOf<Pair<String, Int>>()
    for (i in 0..3) {
      methodCases += (nameVar("__kanama_signal_dispatch$i") to i + 1)
    }
    for (v in model.virtuals) {
      methodCases += (nameVar(v.virtualName) to v.args.size)
    }
    for (m in model.methods) {
      methodCases += (nameVar(m.godotName) to m.args.size)
    }

    sb.appendLine("    private fun hasMethod(name: Long): Boolean = when (name) {")
    if (methodCases.isEmpty()) {
      sb.appendLine("        else -> false")
    } else {
      for ((nameVar, _) in methodCases) {
        sb.appendLine("        $nameVar -> true")
      }
      sb.appendLine("        else -> false")
    }
    sb.appendLine("    }")
    sb.appendLine()

    sb.appendLine("    private fun getMethodArgumentCount(name: Long): Int = when (name) {")
    if (methodCases.isEmpty()) {
      sb.appendLine("        else -> -1")
    } else {
      for ((nameVar, count) in methodCases) {
        sb.appendLine("        $nameVar -> $count")
      }
      sb.appendLine("        else -> -1")
    }
    sb.appendLine("    }")
    sb.appendLine()
  }

  private fun emitScriptMetadataWriters() {
    data class MethodMetaRow(val godotName: String, val argCount: Int)
    val methodRows = mutableListOf<MethodMetaRow>()
    for (v in model.virtuals) {
      methodRows += MethodMetaRow(v.virtualName, v.args.size)
    }
    for (m in model.methods) {
      methodRows += MethodMetaRow(m.godotName, m.args.size)
    }
    val methodReturnExprByName = linkedMapOf<String, String>()
    for (v in model.virtuals) {
      methodReturnExprByName[v.virtualName] =
        v.returnType?.let { "VariantType.${it.variantTypeEnum}.id" } ?: "VariantType.NIL.id"
    }
    for (m in model.methods) {
      methodReturnExprByName[m.godotName] =
        m.returnType?.let { "VariantType.${it.variantTypeEnum}.id" } ?: "VariantType.NIL.id"
    }

    sb.appendLine("    private fun hasScriptSignal(name: Long): Boolean = when (name) {")
    if (model.signals.isEmpty()) {
      sb.appendLine("        else -> false")
    } else {
      for (s in model.signals) {
        sb.appendLine("        ${nameVar(s.godotName)} -> true")
      }
      sb.appendLine("        else -> false")
    }
    sb.appendLine("    }")
    sb.appendLine()

    sb.appendLine("    private fun writeScriptMethodList(ret: MemorySegment) {")
    if (methodRows.isEmpty()) {
      sb.appendLine("        BuiltinTypes.construct(VariantType.ARRAY, ret)")
    } else {
      sb.appendLine("        BuiltinTypes.initArrayOfDictionaries(ret, listOf(")
      methodRows.forEachIndexed { index, row ->
        val comma = if (index == methodRows.lastIndex) "" else ","
        val retExpr = methodReturnExprByName[row.godotName] ?: "VariantType.NIL.id"
        sb.appendLine(
          "            mapOf(\"name\" to \"${row.godotName}\", \"args_count\" to ${row.argCount}, \"flags\" to 1, \"id\" to $index, \"return_type\" to $retExpr)$comma"
        )
      }
      sb.appendLine("        ))")
    }
    sb.appendLine("    }")
    sb.appendLine()

    sb.appendLine("    private fun writeScriptPropertyList(ret: MemorySegment) {")
    if (!hasInspectableProperties) {
      sb.appendLine("        BuiltinTypes.construct(VariantType.ARRAY, ret)")
    } else {
      val rows = scriptPropertyListDictionaryExpressions()
      sb.appendLine("        BuiltinTypes.initArrayOfDictionaries(ret, listOf(")
      rows.forEachIndexed { index, row ->
        val comma = if (index == rows.lastIndex) "" else ","
        sb.appendLine("            $row$comma")
      }
      sb.appendLine("        ))")
    }
    sb.appendLine("    }")
    sb.appendLine()

    sb.appendLine("    private fun writeScriptSignalList(ret: MemorySegment) {")
    if (model.signals.isEmpty()) {
      sb.appendLine("        BuiltinTypes.construct(VariantType.ARRAY, ret)")
    } else {
      sb.appendLine("        BuiltinTypes.initArrayOfDictionaries(ret, listOf(")
      model.signals.forEachIndexed { index, s ->
        val comma = if (index == model.signals.lastIndex) "" else ","
        sb.appendLine(
          "            mapOf(\"name\" to \"${s.godotName}\", \"args_count\" to ${s.args.size}, \"id\" to $index)$comma"
        )
      }
      sb.appendLine("        ))")
    }
    sb.appendLine("    }")
    sb.appendLine()

    val rpcMethods = model.methods.filter { it.rpc != null }
    sb.appendLine("    private fun writeRpcConfig(ret: MemorySegment) {")
    if (rpcMethods.isEmpty()) {
      sb.appendLine("        BuiltinTypes.initNilVariant(ret)")
    } else {
      sb.appendLine("        BuiltinTypes.initVariantDictionary(ret, mapOf(")
      rpcMethods.forEachIndexed { index, method ->
        val rpc = method.rpc ?: return@forEachIndexed
        val comma = if (index == rpcMethods.lastIndex) "" else ","
        sb.appendLine(
          "            \"${method.godotName}\" to mapOf(\"rpc_mode\" to ${rpc.mode}L, \"call_local\" to ${rpc.callLocal}, \"transfer_mode\" to ${rpc.transferMode}L, \"channel\" to ${rpc.channel}L)$comma"
        )
      }
      sb.appendLine("        ))")
    }
    sb.appendLine("    }")
    sb.appendLine()

    sb.appendLine(
      "    private fun writeMethodInfo(name: Long, ret: MemorySegment): Boolean = when (name) {"
    )
    if (methodRows.isEmpty()) {
      sb.appendLine("        else -> false")
    } else {
      methodRows.forEachIndexed { index, row ->
        val retExpr = methodReturnExprByName[row.godotName] ?: "VariantType.NIL.id"
        sb.appendLine("        ${nameVar(row.godotName)} -> {")
        sb.appendLine(
          "            BuiltinTypes.initDictionary(ret, mapOf(\"name\" to \"${row.godotName}\", \"args_count\" to ${row.argCount}, \"flags\" to 1, \"id\" to $index, \"return_type\" to $retExpr))"
        )
        sb.appendLine("            true")
        sb.appendLine("        }")
      }
      sb.appendLine("        else -> false")
    }
    sb.appendLine("    }")
    sb.appendLine()
  }

  private fun scriptPropertyListEntryCount(): Int =
    model.properties.sumOf { property ->
      1 +
        (if (property.exportCategory != null) 1 else 0) +
        (if (property.exportGroup != null) 1 else 0) +
        (if (property.exportSubgroup != null) 1 else 0)
    } + model.toolButtons.size

  private fun scriptPropertyListSpecExpressions(): List<String> = buildList {
    for (p in model.properties) {
      p.exportCategory?.let { add(scriptGroupPropertySpecExpression(it)) }
      p.exportGroup?.let { add(scriptGroupPropertySpecExpression(it)) }
      p.exportSubgroup?.let { add(scriptGroupPropertySpecExpression(it)) }
      add(scriptPropertySpecExpression(p))
    }
    for (button in model.toolButtons) {
      add(toolButtonPropertySpecExpression(button))
    }
  }

  private fun scriptPropertyListDictionaryExpressions(): List<String> = buildList {
    for (p in model.properties) {
      p.exportCategory?.let { add(scriptGroupPropertyDictionaryExpression(it)) }
      p.exportGroup?.let { add(scriptGroupPropertyDictionaryExpression(it)) }
      p.exportSubgroup?.let { add(scriptGroupPropertyDictionaryExpression(it)) }
      add(scriptPropertyDictionaryExpression(p))
    }
    for (button in model.toolButtons) {
      add(toolButtonPropertyDictionaryExpression(button))
    }
  }

  private fun scriptGroupPropertySpecExpression(group: ScriptPropertyGroupModel): String =
    "ClassDB.PropertySpec(\"${kotlinStringLiteral(group.name)}\", VariantType.NIL, 0, \"${kotlinStringLiteral(group.prefix)}\", ${group.usage})"

  private fun scriptPropertySpecExpression(p: ScriptPropertyModel): String {
    val declaredType = scriptPropertyDeclaredVariantType(p)
    return "ClassDB.PropertySpec(\"${kotlinStringLiteral(p.godotName)}\", VariantType.$declaredType, ${p.hint}, \"${kotlinStringLiteral(p.hintString)}\", ${p.usage})"
  }

  private fun toolButtonPropertySpecExpression(button: ToolButtonModel): String =
    "ClassDB.PropertySpec(\"${kotlinStringLiteral(button.propertyName)}\", VariantType.CALLABLE, $PROPERTY_HINT_TOOL_BUTTON, \"${kotlinStringLiteral(button.hintString)}\", $PROPERTY_USAGE_EDITOR)"

  private fun scriptGroupPropertyDictionaryExpression(group: ScriptPropertyGroupModel): String =
    "mapOf(\"name\" to \"${kotlinStringLiteral(group.name)}\", \"type\" to VariantType.NIL.id, \"hint\" to 0, \"hint_string\" to \"${kotlinStringLiteral(group.prefix)}\", \"usage\" to ${group.usage})"

  private fun scriptPropertyDictionaryExpression(p: ScriptPropertyModel): String {
    val declaredType = scriptPropertyDeclaredVariantType(p)
    return "mapOf(\"name\" to \"${kotlinStringLiteral(p.godotName)}\", \"type\" to VariantType.$declaredType.id, \"hint\" to ${p.hint}, \"hint_string\" to \"${kotlinStringLiteral(p.hintString)}\", \"usage\" to ${p.usage})"
  }

  private fun toolButtonPropertyDictionaryExpression(button: ToolButtonModel): String =
    "mapOf(\"name\" to \"${kotlinStringLiteral(button.propertyName)}\", \"type\" to VariantType.CALLABLE.id, \"hint\" to $PROPERTY_HINT_TOOL_BUTTON, \"hint_string\" to \"${kotlinStringLiteral(button.hintString)}\", \"usage\" to $PROPERTY_USAGE_EDITOR)"

  private fun scriptPropertyDeclaredVariantType(p: ScriptPropertyModel): String =
    // Property declaration type vs marshal type can diverge. NodePath uses
    // STRING marshaling internally, but the inspector declaration must be
    // NODE_PATH so Godot renders a node picker and `.tscn` NodePath values
    // deserialize into the slot.
    if (p.type == TypeMapping.NODE_PATH) "NODE_PATH" else p.type.variantTypeEnum

  private fun emitPropertyDefaultHelpers() {
    sb.appendLine("    private fun hasPropertyDefault(name: Long): Boolean = when (name) {")
    for (p in model.properties) {
      sb.appendLine(
        "        ${nameVar(p.godotName)} -> default${p.kotlinName.replaceFirstChar { it.uppercase() }}Available"
      )
    }
    sb.appendLine("        else -> false")
    sb.appendLine("    }")
    sb.appendLine()

    sb.appendLine(
      "    private fun writePropertyDefault(name: Long, ret: MemorySegment): Boolean = when (name) {"
    )
    for (p in model.properties) {
      sb.appendLine("        ${nameVar(p.godotName)} -> {")
      sb.appendLine(
        "            ${variantWritePropertyRetExpr(p, "default${p.kotlinName.replaceFirstChar { it.uppercase() }}${scriptPropertyToWideSuffix(p)}")}"
      )
      sb.appendLine("            true")
      sb.appendLine("        }")
    }
    sb.appendLine("        else -> false")
    sb.appendLine("    }")
    sb.appendLine()
  }

  private fun factory() {
    sb.appendLine("    private fun factory(godotObject: MemorySegment): KanamaScriptInstance {")
    sb.appendLine("        val kt = ${model.simpleName}(godotObject)")
    sb.appendLine("        return KanamaScriptInstance(")
    sb.appendLine("            kotlinObject = kt,")
    sb.appendLine("            ownerObject = godotObject,")
    if (hasInspectableProperties) {
      sb.appendLine("            propertyListPtr = propertyListPtr,")
      sb.appendLine("            propertyCount = ${scriptPropertyListEntryCount()},")
    }
    emitDispatchCall()
    emitDispatchSet()
    emitDispatchGet()
    emitDispatchHasMethod()
    emitDirectLifecycleDispatch()
    sb.appendLine("            cleanup = { cleanupKanamaOwnedProperties(kt) },")
    sb.appendLine("        )")
    sb.appendLine("    }")
  }

  private fun emitDispatchCall() {
    val allCallable =
      model.virtuals + model.methods.map { VirtualModel(it.godotName, "", it.kotlinName, it.args) }
    sb.appendLine("            dispatchCall = { name, args, argCount, ret, error ->")
    sb.appendLine("                when (name) {")
    for (i in 0..3) {
      sb.appendLine("                    ${nameVar("__kanama_signal_dispatch$i")} -> {")
      sb.appendLine("                        if (argCount < ${i + 1}) { false } else {")
      sb.appendLine("                            val argsArray = args.reinterpret(${(i + 1) * 8}L)")
      sb.appendLine("                            val callbackId = Arena.ofConfined().use { a ->")
      sb.appendLine("                                val idScratch = a.allocate(JAVA_LONG)")
      sb.appendLine(
        "                                VariantConverters.variantToType(VariantType.INT).invoke(idScratch, argsArray.get(ADDRESS, ${i * 8}L))"
      )
      sb.appendLine("                                idScratch.get(JAVA_LONG, 0)")
      sb.appendLine("                            }")
      if (i == 0) {
        sb.appendLine(
          "                            SignalCallbackRegistry.invoke(callbackId, emptyList())"
        )
      } else {
        sb.appendLine("                            val signalArgs = Arena.ofConfined().use { a ->")
        sb.appendLine("                                listOf(")
        for (argIndex in 0 until i) {
          val comma = if (argIndex == i - 1) "" else ","
          sb.appendLine(
            "                                    BuiltinTypes.readVariantScalar(argsArray.get(ADDRESS, ${argIndex * 8}L), a)$comma"
          )
        }
        sb.appendLine("                                )")
        sb.appendLine("                            }")
        sb.appendLine(
          "                            SignalCallbackRegistry.invoke(callbackId, signalArgs)"
        )
      }
      sb.appendLine("                            true")
      sb.appendLine("                        }")
      sb.appendLine("                    }")
    }
    for (v in model.virtuals) {
      sb.append("                    ${nameVar(v.virtualName)} -> { ")
      // Extract Variant args (call_func receives Variant pointers, not ptrcall)
      val callArgs =
        if (v.args.isEmpty()) {
          ""
        } else {
          val argExprs =
            v.args.mapIndexed { i, arg ->
              variantReadArgExpr(
                arg,
                "args.reinterpret(${(i + 1) * 8}L).get(ADDRESS, ${i * 8}L)",
                "arg$i",
              )
            }
          sb.append(argExprs.joinToString("; ") + "; ")
          v.args.indices.joinToString(", ") { "arg$it" }
        }
      if (v.returnType != null) {
        // Value-returning virtual (e.g. _get_minimum_size): marshal the
        // Kotlin result back into the engine's return Variant slot.
        sb.append(
          "val vret = kt.${v.kotlinMethodName}($callArgs); ${variantWriteRetExpr(v.returnType, "vret")}"
        )
      } else {
        sb.append("kt.${v.kotlinMethodName}($callArgs)")
      }
      if (v.virtualName == "_exit_tree") {
        sb.append("; cancelKanamaScope(kt)")
      }
      sb.appendLine("; true }")
    }
    for (m in model.methods) {
      emitMethodDispatchCase(m)
    }
    sb.appendLine("                    else -> false")
    sb.appendLine("                }")
    sb.appendLine("            },")
  }

  private fun emitMethodDispatchCase(m: MethodModel) {
    val defaultStart = m.args.indexOfFirst { it.hasDefault }
    val requiredCount = if (defaultStart < 0) m.args.size else defaultStart
    sb.appendLine("                    ${nameVar(m.godotName)} -> {")
    sb.appendLine("                        when {")
    if (defaultStart < 0) {
      emitMethodDispatchBranch(m, m.args.size, "argCount >= ${m.args.size}")
    } else {
      for (count in requiredCount..m.args.size) {
        emitMethodDispatchBranch(m, count, "argCount == ${count}L")
      }
    }
    sb.appendLine("                            else -> false")
    sb.appendLine("                        }")
    sb.appendLine("                    }")
  }

  private fun emitMethodDispatchBranch(m: MethodModel, argCount: Int, condition: String) {
    sb.appendLine("                            $condition -> {")
    val argExprs =
      m.args.take(argCount).mapIndexed { i, arg ->
        variantReadArgExpr(
          arg,
          "args.reinterpret(${(i + 1) * 8}L).get(ADDRESS, ${i * 8}L)",
          "marg$i",
        )
      }
    for (expr in argExprs) {
      sb.appendLine("                                $expr")
    }
    val callArgs = (0 until argCount).joinToString(", ") { "marg$it" }
    if (m.returnType != null) {
      sb.appendLine("                                val r = kt.${m.kotlinName}($callArgs)")
      sb.appendLine("                                ${variantWriteRetExpr(m.returnType, "r")}")
    } else {
      sb.appendLine("                                kt.${m.kotlinName}($callArgs)")
    }
    sb.appendLine("                                true")
    sb.appendLine("                            }")
  }

  private fun coroutineScopeHelpers() {
    if (model.virtuals.none { it.virtualName == "_exit_tree" }) return
    sb.appendLine("    private fun cancelKanamaScope(instance: Any) {")
    sb.appendLine(
      "        (instance as? net.multigesture.kanama.api.KanamaCoroutineOwner)?.kanamaScope?.cancel()"
    )
    sb.appendLine("    }")
    sb.appendLine()
  }

  private fun emitDispatchSet() {
    val mutable = model.properties.filter { it.isMutable }
    if (mutable.isEmpty()) {
      sb.appendLine("            dispatchSet = { _, _ -> false },")
      return
    }
    sb.appendLine("            dispatchSet = { name, value ->")
    sb.appendLine("                when (name) {")
    for (p in mutable) {
      sb.appendLine("                    ${nameVar(p.godotName)} -> {")
      sb.appendLine("                        ${variantReadPropertyExpr(p, "value", "v")}")
      sb.appendLine(
        "                        ${cleanupPropertyExpr(p, "kt.${p.kotlinName}", "mutableSetOf()")}"
      )
      sb.appendLine(
        "                        kt.${p.kotlinName} = v${scriptPropertyFromWideSuffix(p)}"
      )
      sb.appendLine("                        true")
      sb.appendLine("                    }")
    }
    sb.appendLine("                    else -> false")
    sb.appendLine("                }")
    sb.appendLine("            },")
  }

  private fun emitCleanupHelpers() {
    sb.appendLine("    private fun closeKanamaOwned(name: String, value: Any?) {")
    sb.appendLine("        when (value) {")
    sb.appendLine("            is AutoCloseable -> {")
    sb.appendLine(
      "                if (System.getenv(\"KANAMA_TRACE_SCRIPT_PROPERTY_CLEANUP\") == \"1\") {"
    )
    sb.appendLine(
      "                    System.err.println(\"[kanama:kt] script property cleanup \" + name + \" type=\" + value::class.simpleName)"
    )
    sb.appendLine("                }")
    sb.appendLine("                value.close()")
    sb.appendLine("            }")
    sb.appendLine(
      "            is Iterable<*> -> value.forEachIndexed { index, item -> closeKanamaOwned(\"\$name[\$index]\", item) }"
    )
    sb.appendLine(
      "            is Map<*, *> -> value.values.forEachIndexed { index, item -> closeKanamaOwned(\"\$name[\$index]\", item) }"
    )
    sb.appendLine("        }")
    sb.appendLine("    }")
    sb.appendLine()
    sb.appendLine("    internal fun cleanupKanamaOwnedProperties(kt: ${model.simpleName}) {")
    sb.appendLine("        cleanupKanamaOwnedProperties(kt, mutableSetOf())")
    sb.appendLine("    }")
    sb.appendLine()
    sb.appendLine(
      "    internal fun cleanupKanamaOwnedProperties(kt: ${model.simpleName}, visited: MutableSet<Int>) {"
    )
    sb.appendLine("        if (!visited.add(System.identityHashCode(kt))) return")
    for (p in model.properties) {
      sb.appendLine("        ${cleanupPropertyExpr(p, "kt.${p.kotlinName}", "visited")}")
    }
    sb.appendLine("    }")
    sb.appendLine()
  }

  private fun cleanupPropertyExpr(
    property: ScriptPropertyModel,
    valueExpr: String,
    visitedExpr: String,
  ): String =
    when {
      // A script property that references another script instance owns only
      // the retained reference to that object. The referenced script's own
      // properties are cleaned up by its script instance free path.
      property.customScriptFqName != null -> {
        if (property.customScriptIsResource) {
          "$valueExpr?.let { BuiltinTypes.releaseRefCounted(it.godotObject) }"
        } else {
          "Unit"
        }
      }
      property.arrayElementCustomScriptFqName != null -> {
        if (property.arrayElementCustomScriptIsResource) {
          "$valueExpr.forEach { BuiltinTypes.releaseRefCounted(it.godotObject) }"
        } else {
          "Unit"
        }
      }
      // Typed Map with custom resource-script values: release each retained value on free,
      // mirroring the array custom-script-resource case above.
      property.mapValueCustomScriptFqName != null -> {
        if (property.mapValueCustomScriptIsResource) {
          "$valueExpr.values.forEach { BuiltinTypes.releaseRefCounted(it.godotObject) }"
        } else {
          "Unit"
        }
      }
      else -> "closeKanamaOwned(\"${kotlinStringLiteral(property.godotName)}\", $valueExpr)"
    }

  private fun emitDispatchGet() {
    if (model.properties.isEmpty() && model.toolButtons.isEmpty()) {
      sb.appendLine("            dispatchGet = { _, _ -> false },")
      return
    }
    sb.appendLine("            dispatchGet = { name, ret ->")
    sb.appendLine("                when (name) {")
    for (p in model.properties) {
      sb.appendLine("                    ${nameVar(p.godotName)} -> {")
      sb.appendLine(
        "                        ${variantWritePropertyRetExpr(p, "kt.${p.kotlinName}${scriptPropertyToWideSuffix(p)}")}"
      )
      sb.appendLine("                        true")
      sb.appendLine("                    }")
    }
    for (button in model.toolButtons) {
      sb.appendLine("                    ${nameVar(button.propertyName)} -> {")
      sb.appendLine("                        ${variantWriteToolButtonRetExpr(button)}")
      sb.appendLine("                        true")
      sb.appendLine("                    }")
    }
    sb.appendLine("                    else -> false")
    sb.appendLine("                }")
    sb.appendLine("            },")
  }

  private fun emitDispatchHasMethod() {
    val allCallable =
      (0..3).map { "__kanama_signal_dispatch$it" } +
        model.virtuals.map { it.virtualName } +
        model.methods.map { it.godotName }
    val checks = allCallable.joinToString(" || ") { "name == ${nameVar(it)}" }
    sb.appendLine("            dispatchHasMethod = { name -> $checks },")
  }

  private fun emitDirectLifecycleDispatch() {
    model.virtuals
      .firstOrNull { it.virtualName == "_process" }
      ?.let { virtual ->
        sb.appendLine(
          "            dispatchProcess = { delta -> kt.${virtual.kotlinMethodName}(delta) },"
        )
      }
    model.virtuals
      .firstOrNull { it.virtualName == "_physics_process" }
      ?.let { virtual ->
        sb.appendLine(
          "            dispatchPhysicsProcess = { delta -> kt.${virtual.kotlinMethodName}(delta) },"
        )
      }
  }

  // ---- Variant helpers ----

  /**
   * Generates an expression that reads [variantPtr] into a local `val [localName]` of the
   * appropriate Kotlin type. For simple scalars this is inline; for String or multi-step types it
   * uses a block form.
   */
  private fun variantReadExpr(type: TypeMapping, variantPtr: String, localName: String): String =
    when (type) {
      TypeMapping.INT ->
        "val $localName = Arena.ofConfined().use { a -> val d = a.allocate(JAVA_LONG); VariantConverters.variantToType(VariantType.INT).invoke(d, $variantPtr); d.get(JAVA_LONG, 0) }"
      TypeMapping.FLOAT ->
        "val $localName = Arena.ofConfined().use { a -> val d = a.allocate(JAVA_DOUBLE); VariantConverters.variantToType(VariantType.FLOAT).invoke(d, $variantPtr); d.get(JAVA_DOUBLE, 0) }"
      TypeMapping.BOOL ->
        "val $localName = Arena.ofConfined().use { a -> val d = a.allocate(JAVA_BYTE); VariantConverters.variantToType(VariantType.BOOL).invoke(d, $variantPtr); d.get(JAVA_BYTE, 0) != 0.toByte() }"
      TypeMapping.STRING ->
        "val $localName = Arena.ofConfined().use { a -> val d = a.allocate(8L, 8L); VariantConverters.variantToType(VariantType.STRING).invoke(d, $variantPtr); val s = GodotStrings.readString(d); GodotStrings.destroyString(d); s }"
      TypeMapping.NODE_PATH ->
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantNodePath($variantPtr, a) }"
      TypeMapping.VECTOR2 ->
        "val $localName = Arena.ofConfined().use { a -> val d = a.allocate(net.multigesture.kanama.types.GodotReal.SIZE_BYTES * 2L, net.multigesture.kanama.types.GodotReal.ALIGN_BYTES); VariantConverters.variantToType(VariantType.VECTOR2).invoke(d, $variantPtr); net.multigesture.kanama.types.Vector2(net.multigesture.kanama.types.GodotReal.readIndex(d, 0), net.multigesture.kanama.types.GodotReal.readIndex(d, 1)) }"
      TypeMapping.VECTOR2I ->
        "val $localName = Arena.ofConfined().use { a -> val d = a.allocate(8L, 4L); VariantConverters.variantToType(VariantType.VECTOR2I).invoke(d, $variantPtr); net.multigesture.kanama.types.Vector2i(d.get(JAVA_INT, 0), d.get(JAVA_INT, 4)) }"
      TypeMapping.VECTOR3 ->
        "val $localName = Arena.ofConfined().use { a -> val d = a.allocate(net.multigesture.kanama.types.GodotReal.SIZE_BYTES * 3L, net.multigesture.kanama.types.GodotReal.ALIGN_BYTES); VariantConverters.variantToType(VariantType.VECTOR3).invoke(d, $variantPtr); net.multigesture.kanama.types.Vector3(net.multigesture.kanama.types.GodotReal.readIndex(d, 0), net.multigesture.kanama.types.GodotReal.readIndex(d, 1), net.multigesture.kanama.types.GodotReal.readIndex(d, 2)) }"
      TypeMapping.VECTOR3I ->
        "val $localName = Arena.ofConfined().use { a -> val d = a.allocate(12L, 4L); VariantConverters.variantToType(VariantType.VECTOR3I).invoke(d, $variantPtr); net.multigesture.kanama.types.Vector3i(d.get(JAVA_INT, 0), d.get(JAVA_INT, 4), d.get(JAVA_INT, 8)) }"
      TypeMapping.QUATERNION ->
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantScalar($variantPtr, a) as? net.multigesture.kanama.types.Quaternion ?: net.multigesture.kanama.types.Quaternion.IDENTITY }"
      TypeMapping.BASIS ->
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantScalar($variantPtr, a) as? net.multigesture.kanama.types.Basis ?: net.multigesture.kanama.types.Basis.IDENTITY }"
      TypeMapping.OBJECT ->
        "val $localName = Arena.ofConfined().use { a -> val d = a.allocate(ADDRESS); VariantConverters.variantToType(VariantType.OBJECT).invoke(d, $variantPtr); net.multigesture.kanama.api.GodotObject(d.get(ADDRESS, 0)) }"
      TypeMapping.ARRAY ->
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantScalar($variantPtr, a) as? List<Any?> ?: emptyList() }"
      // PackedStringArray is a virtual RETURN shape (task 13), never validated as an arg, but the
      // when must be exhaustive — read as List<String> for completeness.
      TypeMapping.PACKED_STRING_ARRAY ->
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantScalar($variantPtr, a) as? List<String> ?: emptyList() }"
      // Variant is a virtual RETURN shape (task 13); the raw decoded Any? is the natural read.
      TypeMapping.VARIANT ->
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantScalar($variantPtr, a) }"
      // task 29 return-only shapes: never validated as args, but the when must be exhaustive —
      // decode via readVariantScalar with the family's typed cast (zero value on mismatch).
      TypeMapping.PACKED_BYTE_ARRAY ->
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantScalar($variantPtr, a) as? ByteArray ?: ByteArray(0) }"
      TypeMapping.PACKED_INT32_ARRAY ->
        "val $localName = Arena.ofConfined().use { a -> (BuiltinTypes.readVariantScalar($variantPtr, a) as? List<*>)?.mapNotNull { (it as? Number)?.toInt() }?.toIntArray() ?: IntArray(0) }"
      TypeMapping.PACKED_INT64_ARRAY ->
        "val $localName = Arena.ofConfined().use { a -> (BuiltinTypes.readVariantScalar($variantPtr, a) as? List<*>)?.mapNotNull { (it as? Number)?.toLong() }?.toLongArray() ?: LongArray(0) }"
      TypeMapping.PACKED_FLOAT32_ARRAY ->
        "val $localName = Arena.ofConfined().use { a -> (BuiltinTypes.readVariantScalar($variantPtr, a) as? List<*>)?.mapNotNull { (it as? Number)?.toFloat() }?.toFloatArray() ?: FloatArray(0) }"
      TypeMapping.PACKED_FLOAT64_ARRAY ->
        "val $localName = Arena.ofConfined().use { a -> (BuiltinTypes.readVariantScalar($variantPtr, a) as? List<*>)?.mapNotNull { (it as? Number)?.toDouble() }?.toDoubleArray() ?: DoubleArray(0) }"
      TypeMapping.PACKED_VECTOR2_ARRAY ->
        "val $localName = Arena.ofConfined().use { a -> (BuiltinTypes.readVariantScalar($variantPtr, a) as? List<*>)?.filterIsInstance<net.multigesture.kanama.types.Vector2>() ?: emptyList() }"
      TypeMapping.PACKED_VECTOR3_ARRAY ->
        "val $localName = Arena.ofConfined().use { a -> (BuiltinTypes.readVariantScalar($variantPtr, a) as? List<*>)?.filterIsInstance<net.multigesture.kanama.types.Vector3>() ?: emptyList() }"
      TypeMapping.PACKED_COLOR_ARRAY ->
        "val $localName = Arena.ofConfined().use { a -> (BuiltinTypes.readVariantScalar($variantPtr, a) as? List<*>)?.filterIsInstance<net.multigesture.kanama.types.Color>() ?: emptyList() }"
      TypeMapping.DICTIONARY ->
        "val $localName = Arena.ofConfined().use { a -> @Suppress(\"UNCHECKED_CAST\") (BuiltinTypes.readVariantScalar($variantPtr, a) as? Map<String, Any?> ?: emptyMap()) }"
      TypeMapping.RID ->
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantScalar($variantPtr, a) as? net.multigesture.kanama.types.RID ?: net.multigesture.kanama.types.RID.EMPTY }"
      TypeMapping.RECT2 ->
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantScalar($variantPtr, a) as? net.multigesture.kanama.types.Rect2 ?: net.multigesture.kanama.types.Rect2.ZERO }"
      TypeMapping.AABB ->
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantScalar($variantPtr, a) as? net.multigesture.kanama.types.AABB ?: net.multigesture.kanama.types.AABB.ZERO }"
      TypeMapping.TRANSFORM2D ->
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantScalar($variantPtr, a) as? net.multigesture.kanama.types.Transform2D ?: net.multigesture.kanama.types.Transform2D.IDENTITY }"
      TypeMapping.TRANSFORM3D ->
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantScalar($variantPtr, a) as? net.multigesture.kanama.types.Transform3D ?: net.multigesture.kanama.types.Transform3D.IDENTITY }"
      TypeMapping.PROJECTION ->
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantScalar($variantPtr, a) as? net.multigesture.kanama.types.Projection ?: net.multigesture.kanama.types.Projection.IDENTITY }"
    }

  private fun variantReadPropertyExpr(
    property: ScriptPropertyModel,
    variantPtr: String,
    localName: String,
  ): String {
    // The decode helpers return immutable List/Map; convert to a mutable copy when the property
    // was declared MutableList/MutableMap so the generated `val` matches the field's Kotlin type.
    val mutableSuffix =
      when {
        property.isMutableList -> ".toMutableList()"
        property.isMutableMap -> ".toMutableMap()"
        else -> ""
      }
    return variantReadPropertyBaseExpr(property, variantPtr, localName) + mutableSuffix
  }

  private fun variantReadPropertyBaseExpr(
    property: ScriptPropertyModel,
    variantPtr: String,
    localName: String,
  ): String =
    when {
      property.objectWrapperFqName != null -> {
        val wrapperExpr =
          if (property.objectWrapperFqName in RESOURCE_WRAPPER_FROM_HANDLE) {
            "${property.objectWrapperFqName}::fromHandle"
          } else {
            "{ handle -> ${property.objectWrapperFqName}(handle) }"
          }
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantObjectRetained($variantPtr, a, $wrapperExpr) }"
      }
      property.arrayElementWrapperFqName != null ->
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantObjectArrayRetained($variantPtr, a, ${property.arrayElementWrapperFqName}::fromHandle) }"
      property.customScriptFqName != null ->
        if (property.customScriptIsResource) {
          "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantObjectRetainedHandle($variantPtr, a) { handle -> ScriptBridge.kotlinObjectForOwner(handle) as? ${property.customScriptFqName} ?: ${property.customScriptFqName}(handle) } }"
        } else {
          "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantObject($variantPtr, a) { handle -> ScriptBridge.kotlinObjectForOwner(handle) as? ${property.customScriptFqName} ?: ${property.customScriptFqName}(handle) } }"
        }
      property.arrayElementCustomScriptFqName != null ->
        if (property.arrayElementCustomScriptIsResource) {
          "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantObjectArrayRetainedHandles($variantPtr, a) { handle -> ScriptBridge.kotlinObjectForOwner(handle) as? ${property.arrayElementCustomScriptFqName} ?: ${property.arrayElementCustomScriptFqName}(handle) } }"
        } else {
          "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantObjectArray($variantPtr, a) { handle -> ScriptBridge.kotlinObjectForOwner(handle) as? ${property.arrayElementCustomScriptFqName} ?: ${property.arrayElementCustomScriptFqName}(handle) } }"
        }
      property.arrayElementString ->
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantStringList($variantPtr, a) }"
      // Stale stored ordinals (e.g. after entry removal) clamp into the entry
      // range instead of indexing raw — same policy as the scalar enum export.
      property.arrayElementEnumFqName != null -> {
        val fq = property.arrayElementEnumFqName
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantLongList($variantPtr, a).map { i -> $fq.entries[i.toInt().coerceIn(0, $fq.entries.lastIndex)] } }"
      }
      property.mapKeyKotlinType != null -> mapReadPropertyExpr(property, variantPtr, localName)
      else -> variantReadExpr(property.type, variantPtr, localName)
    }

  /**
   * Read expression for a typed `Map<K, V>` property (issue #40).
   *
   * **Non-throwing by construction.** GDScript can hand the engine a Dictionary whose keys/values
   * do not match the declared types — a wrong-typed literal (`{1: 2}` into `Map<String, Long>`) or
   * a stale `.tscn` saved before the types changed. The decode must not `ClassCastException`: that
   * escapes an FFM upcall and aborts the process (the very hole task 50's containment now catches,
   * but containment is defence in depth — the read is fail-soft here *by construction*, like
   * `variantReadExpr`'s `as?`/fallback and the typed-array/`readDictionaryScalars` drops). A key
   * that can't be decoded skips the entry; a value that can't be decoded is nulled for a nullable
   * `V?` (C#-parity: preserve the key) or drops the entry for a non-null `V` (Kotlin cannot hold
   * the null). Nullable *object* values are rejected earlier in KSP, so only scalar/value/enum
   * values reach the nullable-preserving branch.
   */
  private fun mapReadPropertyExpr(
    property: ScriptPropertyModel,
    variantPtr: String,
    localName: String,
  ): String {
    val keySafe = mapKeyReadTransformSafe(property, "entry.key")
    return when {
      // Engine object/resource wrapper values: retained read (fromHandle), like typed arrays.
      // The reader drops nil (0-handle) values; keys are decoded fail-soft here. Value type is
      // non-null (nullable object values rejected in KSP), so a dropped nil is C#-consistent.
      property.mapValueWrapperFqName != null -> {
        val wrapper = property.mapValueWrapperFqName
        val wrapperLambda =
          if (wrapper in RESOURCE_WRAPPER_FROM_HANDLE) {
            "$wrapper::fromHandle"
          } else {
            "{ handle -> $wrapper(handle) }"
          }
        val reader =
          if (wrapper in RESOURCE_WRAPPER_FROM_HANDLE) {
            "readVariantDictionaryObjectValuesRetained"
          } else {
            "readVariantDictionaryObjectValues"
          }
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.$reader($variantPtr, a, $wrapperLambda).entries.mapNotNull { entry -> val k = $keySafe ?: return@mapNotNull null; k to entry.value }.toMap() }"
      }
      // Custom @ScriptClass instances: resolve the live Kotlin script, retaining resource scripts.
      property.mapValueCustomScriptFqName != null -> {
        val fq = property.mapValueCustomScriptFqName
        val resolver =
          "{ handle -> ScriptBridge.kotlinObjectForOwner(handle) as? $fq ?: $fq(handle) }"
        val reader =
          if (property.mapValueCustomScriptIsResource) {
            "readVariantDictionaryObjectValuesRetainedHandles"
          } else {
            "readVariantDictionaryObjectValues"
          }
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.$reader($variantPtr, a, $resolver).entries.mapNotNull { entry -> val k = $keySafe ?: return@mapNotNull null; k to entry.value }.toMap() }"
      }
      // Scalar / String / value-type / enum values ride the generic scalar decode.
      else -> {
        val valueSafe = mapValueReadTransformSafe(property, "entry.value")
        val bind: String
        val keepNulls: String
        if (property.mapValueNullable) {
          // Map<K, V?>: preserve the key, decode a nil/wrong-typed value to null (C# parity).
          // keepNullValues=true so the reader itself does not drop the nil-valued entry.
          bind = "k to ($valueSafe)"
          keepNulls = ", keepNullValues = true"
        } else {
          // Map<K, V>: Kotlin cannot hold null there, so drop a nil/wrong-typed value.
          bind = "val v = $valueSafe ?: return@mapNotNull null; k to v"
          keepNulls = ""
        }
        "val $localName = Arena.ofConfined().use { a -> BuiltinTypes.readVariantDictionaryAny($variantPtr, a$keepNulls).entries.mapNotNull { entry -> val k = $keySafe ?: return@mapNotNull null; $bind }.toMap() }"
      }
    }
  }

  /**
   * Fail-soft key transform: yields `K?`, null when the raw key can't be decoded (skips the entry).
   */
  private fun mapKeyReadTransformSafe(property: ScriptPropertyModel, varExpr: String): String {
    val fq = property.mapKeyKotlinType
    if (property.mapKeyEnumEntries.isNotEmpty() && fq != null) {
      return enumOrdinalReadCastSafe(fq, varExpr)
    }
    return scalarReadCastSafe(fq, varExpr)
  }

  /**
   * Fail-soft value transform: yields `V?`, null when the raw scalar/enum value can't be decoded.
   */
  private fun mapValueReadTransformSafe(property: ScriptPropertyModel, varExpr: String): String {
    property.mapValueEnumFqName?.let { fq ->
      return enumOrdinalReadCastSafe(fq, varExpr)
    }
    return scalarReadCastSafe(property.mapValueKotlinType, varExpr)
  }

  /**
   * Fail-soft cast of a raw decoded Variant scalar/value-type ([varExpr]) to its Kotlin type,
   * narrowing numbers. Yields a nullable expression: `null` on a type mismatch rather than a
   * `ClassCastException` (see [mapReadPropertyExpr]).
   */
  private fun scalarReadCastSafe(kotlinType: String?, varExpr: String): String =
    when (kotlinType) {
      "String" -> "($varExpr as? String)"
      "Long" -> "(($varExpr as? Number)?.toLong())"
      "Int" -> "(($varExpr as? Number)?.toInt())"
      "Double" -> "(($varExpr as? Number)?.toDouble())"
      "Float" -> "(($varExpr as? Number)?.toFloat())"
      "Boolean" -> "($varExpr as? Boolean)"
      else -> "($varExpr as? $kotlinType)"
    }

  /**
   * Fail-soft ordinal decode: clamps a valid stored ordinal into an enum entry (matching the
   * scalar/list enum policy), but yields `null` for a non-numeric value instead of throwing.
   */
  private fun enumOrdinalReadCastSafe(enumFq: String, varExpr: String): String =
    "(($varExpr as? Number)?.toInt()?.let { $enumFq.entries[it.coerceIn(0, $enumFq.entries.lastIndex)] })"

  private fun variantReadArgExpr(arg: ArgModel, variantPtr: String, localName: String): String =
    if (arg.objectWrapperFqName != null) {
      if (arg.objectWrapperFqName in RESOURCE_WRAPPER_FROM_HANDLE) {
        val value =
          "BuiltinTypes.readVariantObject($variantPtr, a, ${arg.objectWrapperFqName}::fromHandle)"
        if (arg.nullable) {
          "val $localName = Arena.ofConfined().use { a -> $value }"
        } else {
          "val $localName = Arena.ofConfined().use { a -> $value ?: error(\"Expected ${arg.objectWrapperFqName} argument '${arg.name}'\") }"
        }
      } else {
        val value =
          "BuiltinTypes.readVariantObject($variantPtr, a) { handle -> ${arg.objectWrapperFqName}(handle) }"
        if (arg.nullable) {
          "val $localName = Arena.ofConfined().use { a -> $value }"
        } else {
          "val $localName = Arena.ofConfined().use { a -> $value ?: error(\"Expected ${arg.objectWrapperFqName} argument '${arg.name}'\") }"
        }
      }
    } else {
      variantReadExpr(arg.type, variantPtr, localName)
    }

  /**
   * Generates a statement that writes [valueExpr] into `ret` (a GDExtensionVariantPtr) using the
   * from-type constructor.
   */
  private fun variantWriteRetExpr(type: TypeMapping, valueExpr: String): String =
    when (type) {
      TypeMapping.INT ->
        "Arena.ofConfined().use { a -> val s = a.allocate(JAVA_LONG); s.set(JAVA_LONG, 0, $valueExpr); VariantConverters.variantFromType(VariantType.INT).invoke(ret, s) }"
      TypeMapping.FLOAT ->
        "Arena.ofConfined().use { a -> val s = a.allocate(JAVA_DOUBLE); s.set(JAVA_DOUBLE, 0, $valueExpr); VariantConverters.variantFromType(VariantType.FLOAT).invoke(ret, s) }"
      TypeMapping.BOOL ->
        "Arena.ofConfined().use { a -> val s = a.allocate(JAVA_BYTE); s.set(JAVA_BYTE, 0, if ($valueExpr) 1.toByte() else 0.toByte()); VariantConverters.variantFromType(VariantType.BOOL).invoke(ret, s) }"
      TypeMapping.STRING ->
        "Arena.ofConfined().use { a -> val s = a.allocate(8L, 8L); GodotStrings.initString(s, $valueExpr); VariantConverters.variantFromType(VariantType.STRING).invoke(ret, s); GodotStrings.destroyString(s) }"
      TypeMapping.NODE_PATH ->
        "Arena.ofConfined().use { a -> BuiltinTypes.initVariantFromAny(ret, $valueExpr, a) }"
      TypeMapping.VECTOR2 ->
        "Arena.ofConfined().use { a -> val s = a.allocate(net.multigesture.kanama.types.GodotReal.SIZE_BYTES * 2L, net.multigesture.kanama.types.GodotReal.ALIGN_BYTES); net.multigesture.kanama.types.GodotReal.writeIndex(s, 0, $valueExpr.x); net.multigesture.kanama.types.GodotReal.writeIndex(s, 1, $valueExpr.y); VariantConverters.variantFromType(VariantType.VECTOR2).invoke(ret, s) }"
      TypeMapping.VECTOR2I ->
        "Arena.ofConfined().use { a -> val s = a.allocate(8L, 4L); s.set(JAVA_INT, 0, $valueExpr.x); s.set(JAVA_INT, 4, $valueExpr.y); VariantConverters.variantFromType(VariantType.VECTOR2I).invoke(ret, s) }"
      TypeMapping.VECTOR3 ->
        "Arena.ofConfined().use { a -> val s = a.allocate(net.multigesture.kanama.types.GodotReal.SIZE_BYTES * 3L, net.multigesture.kanama.types.GodotReal.ALIGN_BYTES); net.multigesture.kanama.types.GodotReal.writeIndex(s, 0, $valueExpr.x); net.multigesture.kanama.types.GodotReal.writeIndex(s, 1, $valueExpr.y); net.multigesture.kanama.types.GodotReal.writeIndex(s, 2, $valueExpr.z); VariantConverters.variantFromType(VariantType.VECTOR3).invoke(ret, s) }"
      TypeMapping.VECTOR3I ->
        "Arena.ofConfined().use { a -> val s = a.allocate(12L, 4L); s.set(JAVA_INT, 0, $valueExpr.x); s.set(JAVA_INT, 4, $valueExpr.y); s.set(JAVA_INT, 8, $valueExpr.z); VariantConverters.variantFromType(VariantType.VECTOR3I).invoke(ret, s) }"
      TypeMapping.QUATERNION ->
        "Arena.ofConfined().use { a -> BuiltinTypes.initVariantFromAny(ret, $valueExpr, a) }"
      TypeMapping.BASIS ->
        "Arena.ofConfined().use { a -> BuiltinTypes.initVariantFromAny(ret, $valueExpr, a) }"
      TypeMapping.OBJECT ->
        "Arena.ofConfined().use { a -> val s = a.allocate(ADDRESS); s.set(ADDRESS, 0, $valueExpr.handle); VariantConverters.variantFromType(VariantType.OBJECT).invoke(ret, s) }"
      TypeMapping.ARRAY ->
        "Arena.ofConfined().use { a -> BuiltinTypes.initVariantFromAny(ret, $valueExpr, a) }"
      // task 13 — non-POD virtual return: build a Godot PackedStringArray from the List<String>,
      // write it into the engine return Variant, then destroy the temporary (destroy-after-read).
      TypeMapping.PACKED_STRING_ARRAY ->
        "Arena.ofConfined().use { a -> val s = BuiltinTypes.allocatePackedArray(a); BuiltinTypes.initPackedStringArray(s, $valueExpr); VariantConverters.variantFromType(VariantType.PACKED_STRING_ARRAY).invoke(ret, s); BuiltinTypes.destroyTyped(VariantType.PACKED_STRING_ARRAY, s) }"
      // task 13 — Variant virtual return: box the Any? result (null -> nil) via the broad
      // initVariantFromAny path, which owns and releases any temporary builtin it constructs.
      TypeMapping.VARIANT ->
        "Arena.ofConfined().use { a -> BuiltinTypes.initVariantFromAny(ret, $valueExpr, a) }"
      // task 29 — remaining packed-array virtual returns: same allocate -> init -> variantFromType
      // -> destroyTyped shape as PACKED_STRING_ARRAY, per element family.
      TypeMapping.PACKED_BYTE_ARRAY ->
        packedWriteRetExpr("PACKED_BYTE_ARRAY", "initPackedByteArray", valueExpr)
      TypeMapping.PACKED_INT32_ARRAY ->
        packedWriteRetExpr("PACKED_INT32_ARRAY", "initPackedInt32Array", "$valueExpr.toList()")
      TypeMapping.PACKED_INT64_ARRAY ->
        packedWriteRetExpr("PACKED_INT64_ARRAY", "initPackedInt64Array", "$valueExpr.toList()")
      TypeMapping.PACKED_FLOAT32_ARRAY ->
        packedWriteRetExpr("PACKED_FLOAT32_ARRAY", "initPackedFloat32Array", "$valueExpr.toList()")
      TypeMapping.PACKED_FLOAT64_ARRAY ->
        packedWriteRetExpr("PACKED_FLOAT64_ARRAY", "initPackedFloat64Array", "$valueExpr.toList()")
      TypeMapping.PACKED_VECTOR2_ARRAY ->
        packedWriteRetExpr("PACKED_VECTOR2_ARRAY", "initPackedVector2Array", valueExpr)
      TypeMapping.PACKED_VECTOR3_ARRAY ->
        packedWriteRetExpr("PACKED_VECTOR3_ARRAY", "initPackedVector3Array", valueExpr)
      TypeMapping.PACKED_COLOR_ARRAY ->
        packedWriteRetExpr("PACKED_COLOR_ARRAY", "initPackedColorArray", valueExpr)
      // task 29 — Dictionary / value-type virtual returns: initVariantFromAny already builds the
      // matching Variant (Map -> Dictionary, RID/Rect2/AABB/Transform2D/Transform3D/Projection ->
      // their builtin types) and owns/releases any temporary it constructs.
      TypeMapping.DICTIONARY,
      TypeMapping.RID,
      TypeMapping.RECT2,
      TypeMapping.AABB,
      TypeMapping.TRANSFORM2D,
      TypeMapping.TRANSFORM3D,
      TypeMapping.PROJECTION ->
        "Arena.ofConfined().use { a -> BuiltinTypes.initVariantFromAny(ret, $valueExpr, a) }"
    }

  /**
   * The allocate -> init -> variantFromType -> destroyTyped return-write shape shared by all
   * Packed*Array families (task 13/29).
   */
  private fun packedWriteRetExpr(
    variantType: String,
    initHelper: String,
    valueExpr: String,
  ): String =
    "Arena.ofConfined().use { a -> val s = BuiltinTypes.allocatePackedArray(a); BuiltinTypes.$initHelper(s, $valueExpr); VariantConverters.variantFromType(VariantType.$variantType).invoke(ret, s); BuiltinTypes.destroyTyped(VariantType.$variantType, s) }"

  private fun variantWritePropertyRetExpr(
    property: ScriptPropertyModel,
    valueExpr: String,
  ): String =
    when {
      property.objectWrapperFqName != null ->
        "Arena.ofConfined().use { a -> BuiltinTypes.initVariantFromAny(ret, $valueExpr, a) }"
      property.arrayElementWrapperFqName != null ->
        "Arena.ofConfined().use { a -> BuiltinTypes.initVariantFromAny(ret, $valueExpr, a) }"
      property.customScriptFqName != null ->
        "Arena.ofConfined().use { a -> BuiltinTypes.initVariantFromAny(ret, $valueExpr?.let { net.multigesture.kanama.api.GodotObject(it.godotObject) }, a) }"
      property.arrayElementCustomScriptFqName != null ->
        "Arena.ofConfined().use { a -> BuiltinTypes.initVariantFromAny(ret, $valueExpr.map { net.multigesture.kanama.api.GodotObject(it.godotObject) }, a) }"
      property.arrayElementString ->
        "Arena.ofConfined().use { a -> BuiltinTypes.initVariantFromAny(ret, $valueExpr, a) }"
      property.arrayElementEnumFqName != null ->
        "Arena.ofConfined().use { a -> BuiltinTypes.initVariantFromAny(ret, $valueExpr.map { it.ordinal.toLong() }, a) }"
      property.mapKeyKotlinType != null -> mapWritePropertyRetExpr(property, valueExpr)
      else -> variantWriteRetExpr(property.type, valueExpr)
    }

  /**
   * Write expression for a typed `Map<K, V>` property. Enum keys/values marshal as their ordinal
   * (Long) and custom-script values as their owner handle; everything else (scalars, value types,
   * engine wrappers) is handled by [BuiltinTypes.initVariantDictionaryAny] — the typed-Map any-key
   * writer, kept separate from the generic (String-key-only) [BuiltinTypes.initVariantFromAny] Map
   * path so generic Dictionary reads and writes stay symmetrical (issue #40).
   */
  private fun mapWritePropertyRetExpr(property: ScriptPropertyModel, valueExpr: String): String {
    val keyNeedsConv = property.mapKeyEnumEntries.isNotEmpty()
    val valueNeedsConv =
      property.mapValueEnumFqName != null || property.mapValueCustomScriptFqName != null
    val source =
      if (keyNeedsConv || valueNeedsConv) {
        val keyConv = if (keyNeedsConv) "k.ordinal.toLong()" else "k"
        val valueConv =
          when {
            property.mapValueEnumFqName != null -> "v.ordinal.toLong()"
            property.mapValueCustomScriptFqName != null ->
              "net.multigesture.kanama.api.GodotObject(v.godotObject)"
            else -> "v"
          }
        "$valueExpr.entries.associate { (k, v) -> $keyConv to $valueConv }"
      } else {
        valueExpr
      }
    return "BuiltinTypes.initVariantDictionaryAny(ret, $source)"
  }

  private fun variantWriteToolButtonRetExpr(button: ToolButtonModel): String =
    "Arena.ofConfined().use { a -> val callable = BuiltinTypes.allocateCallable(a); BuiltinTypes.initCallable(callable, godotObject, \"${kotlinStringLiteral(button.method.godotName)}\"); VariantConverters.variantFromType(VariantType.CALLABLE).invoke(ret, callable); BuiltinTypes.destroyTyped(VariantType.CALLABLE, callable) }"

  /** Conversion appended when the property's Kotlin value flows out to its 64-bit Variant slot. */
  private fun scriptPropertyToWideSuffix(p: ScriptPropertyModel): String =
    p.narrow?.toWide ?: p.enumFqName?.let { ".ordinal.toLong()" } ?: ""

  /** Conversion appended when a 64-bit Variant slot value is assigned into the Kotlin field. */
  private fun scriptPropertyFromWideSuffix(p: ScriptPropertyModel): String =
    p.narrow?.fromWide
      // Stale `.tscn` ints (e.g. after entry removal) must not crash script
      // load: clamp the ordinal into the entry range instead of indexing raw.
      ?: p.enumFqName?.let { fq ->
        ".toInt().let { i -> $fq.entries[i.coerceIn(0, $fq.entries.lastIndex)] }"
      }
      ?: ""

  // The collection keyword / empty literal for a property, keyed on its declared mutability.
  // Extracted so the MutableList/List (and MutableMap/Map) choice lives in one place instead of
  // being repeated across every list/map arm below (task 49 item 2).
  private val ScriptPropertyModel.listKeyword: String
    get() = if (isMutableList) "MutableList" else "List"

  private val ScriptPropertyModel.mapKeyword: String
    get() = if (isMutableMap) "MutableMap" else "Map"

  private val ScriptPropertyModel.emptyListLiteral: String
    get() = if (isMutableList) "mutableListOf()" else "emptyList()"

  private val ScriptPropertyModel.emptyMapLiteral: String
    get() = if (isMutableMap) "mutableMapOf()" else "emptyMap()"

  private fun scriptPropertyKotlinType(property: ScriptPropertyModel): String =
    when {
      property.objectWrapperFqName != null -> "${property.objectWrapperFqName}?"
      property.arrayElementWrapperFqName != null ->
        "${property.listKeyword}<${property.arrayElementWrapperFqName}>"
      property.customScriptFqName != null -> "${property.customScriptFqName}?"
      property.arrayElementCustomScriptFqName != null ->
        "${property.listKeyword}<${property.arrayElementCustomScriptFqName}>"
      property.arrayElementString -> "${property.listKeyword}<String>"
      property.arrayElementEnumFqName != null ->
        "${property.listKeyword}<${property.arrayElementEnumFqName}>"
      property.mapKeyKotlinType != null ->
        "${property.mapKeyword}<${property.mapKeyKotlinType}, ${mapValueKotlinType(property)}>"
      property.enumFqName != null -> property.enumFqName
      else -> property.narrow?.kotlinType ?: property.type.kotlinType
    }

  /** Emitted Kotlin value type for a typed `Map<K, V>` property. */
  private fun mapValueKotlinType(property: ScriptPropertyModel): String =
    property.mapValueWrapperFqName
      ?: property.mapValueCustomScriptFqName
      ?: property.mapValueEnumFqName
      ?: property.mapValueKotlinType
      ?: "Any?"

  private fun scriptPropertyZeroLiteral(property: ScriptPropertyModel): String =
    when {
      property.objectWrapperFqName != null -> "null"
      property.arrayElementWrapperFqName != null -> property.emptyListLiteral
      property.customScriptFqName != null -> "null"
      property.arrayElementCustomScriptFqName != null -> property.emptyListLiteral
      property.arrayElementString -> property.emptyListLiteral
      property.arrayElementEnumFqName != null -> property.emptyListLiteral
      property.mapKeyKotlinType != null -> property.emptyMapLiteral
      property.enumFqName != null -> "${property.enumFqName}.entries.first()"
      else -> property.narrow?.zeroLiteral ?: property.type.kotlinLiteralZero
    }

  companion object {
    private val RESOURCE_WRAPPER_FROM_HANDLE = RESOURCE_WRAPPERS_WITH_FROM_HANDLE

    /** Turns a godot name like "_ready" or "my_speed" into a valid Kotlin field name. */
    private fun nameVar(godotName: String): String =
      godotName
        .trimStart('_')
        .split('_')
        .mapIndexed { i, part -> if (i == 0) part else part.replaceFirstChar { it.uppercase() } }
        .joinToString("") + "NameValue"
  }
}
