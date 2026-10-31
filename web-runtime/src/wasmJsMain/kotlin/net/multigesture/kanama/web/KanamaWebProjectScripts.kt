@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.web

import kotlin.reflect.KClass
import net.multigesture.kanama.backend.InternalKanamaBackendApi

// The seam between the Kanama Web runtime and a project's generated code (task 135 item 5). A kit
// project compiles its scripts, and the registry and `main()` KSP generates for them, in a module
// of its own against the prebuilt runtime klib: generated code names only the public declarations
// in this file, and the runtime names no generated declaration. All of them are for generated code
// only, behind the InternalKanamaBackendApi opt-in that the generated files declare.

/**
 * A project's Kotlin scripts as the Web runtime dispatches into them: the object
 * `KanamaWebProjectRegistry` that KSP generates implements it, one `when` per member over the
 * project's scripts. The entry points in `Main.kt` call it and nothing else of the project.
 */
@InternalKanamaBackendApi
interface KanamaWebProjectScripts {
  /**
   * The bridge contract the generated proxies and this registry speak (`kanamaWebProtocolVersion`).
   */
  val protocolVersion: Int
  val scripts: List<WebScriptDescriptor>

  fun create(scriptId: Int, objectId: Int): KanamaWebScript

  fun enterTree(scriptId: Int, script: KanamaWebScript)

  fun ready(scriptId: Int, script: KanamaWebScript)

  fun process(scriptId: Int, script: KanamaWebScript, delta: Double)

  fun physicsProcess(scriptId: Int, script: KanamaWebScript, delta: Double)

  fun draw(scriptId: Int, script: KanamaWebScript)

  fun exitTree(scriptId: Int, script: KanamaWebScript)

  fun input(scriptId: Int, script: KanamaWebScript, eventHandle: Int)

  fun unhandledInput(scriptId: Int, script: KanamaWebScript, eventHandle: Int)

  fun callNoArgs(scriptId: Int, methodId: Int, script: KanamaWebScript)

  fun getStringProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript): String

  fun setStringProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript, value: String)

  fun setLongProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript, value: Long)

  fun setDoubleProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript, value: Double)

  fun setVector2Property(
    scriptId: Int,
    propertyId: Int,
    script: KanamaWebScript,
    x: Double,
    y: Double,
  )

  fun setVector2iProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript, x: Int, y: Int)

  fun setVector3Property(
    scriptId: Int,
    propertyId: Int,
    script: KanamaWebScript,
    x: Double,
    y: Double,
    z: Double,
  )

  fun setColorProperty(
    scriptId: Int,
    propertyId: Int,
    script: KanamaWebScript,
    r: Double,
    g: Double,
    b: Double,
    a: Double,
  )

  fun setPackedValueProperty(
    scriptId: Int,
    propertyId: Int,
    script: KanamaWebScript,
    packed: String,
  )

  fun setObjectProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript, value: Int)

  fun getPackedProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript): String

  fun setObjectArrayProperty(
    scriptId: Int,
    propertyId: Int,
    script: KanamaWebScript,
    values: IntArray,
  )

  fun setStringArrayProperty(
    scriptId: Int,
    propertyId: Int,
    script: KanamaWebScript,
    values: List<String>,
  )

  fun callLong(scriptId: Int, methodId: Int, script: KanamaWebScript, value: Long): Long

  fun callLongVoid(scriptId: Int, methodId: Int, script: KanamaWebScript, value: Long)

  fun callString(scriptId: Int, methodId: Int, script: KanamaWebScript, value: String)

  fun callVector2i(scriptId: Int, methodId: Int, script: KanamaWebScript, x: Int, y: Int)

  fun callObject(scriptId: Int, methodId: Int, script: KanamaWebScript, objectHandle: Int)

  fun callObjectObjectLong(
    scriptId: Int,
    methodId: Int,
    script: KanamaWebScript,
    firstHandle: Int,
    secondHandle: Int,
    value: Long,
  )

  /** The numeric crossing: six double slots (the processor's `NUMERIC_ARG_SLOTS`). */
  fun callDoubles(
    scriptId: Int,
    methodId: Int,
    script: KanamaWebScript,
    a0: Double,
    a1: Double,
    a2: Double,
    a3: Double,
    a4: Double,
    a5: Double,
  )

  fun callPacked(scriptId: Int, methodId: Int, script: KanamaWebScript): String

  fun callPackedArgs(scriptId: Int, methodId: Int, script: KanamaWebScript, value: String): String
}

/**
 * The runtime helpers generated code calls (the value and float text forms, a script by its
 * handle). They stay internal to the runtime; this object is their one public door.
 */
@InternalKanamaBackendApi
object KanamaWebGenerated {
  fun encodeFloat(value: Double): String = WebPackedFloats.encode(value)

  fun decodeFloat(text: String): Double = WebPackedFloats.decode(text)

  fun encodeValue(value: Any): String = WebPackedValues.encode(value)

  fun decodeValue(packed: String, type: KClass<*>): Any = WebPackedValues.decode(packed, type)

  fun valueFromComponents(type: KClass<*>, components: DoubleArray): Any =
    WebPackedValues.fromComponents(type, components)

  fun scriptInstance(objectId: Int): Any? = webScriptInstance(objectId)
}

private var startedProjectScripts: KanamaWebProjectScripts? = null

/**
 * The project's scripts, once [startKanamaWeb] ran. A Kotlin/Wasm module runs only its own `main()`
 * (a dependency klib's never runs), so a module whose `main()` did not start the runtime would load
 * with no backend and no scripts: every entry fails here, naming the cause, instead.
 */
internal val projectScripts: KanamaWebProjectScripts
  get() = startedProjectScripts ?: throw IllegalStateException(NOT_STARTED)

internal const val NOT_STARTED =
  "Kanama Web runtime not started: the module's main() must call " +
    "net.multigesture.kanama.web.startKanamaWeb(KanamaWebProjectRegistry). The generated " +
    "KanamaWebProjectRegistry.generated.kt has that main(); a build without it did not run KSP " +
    "with kanamaRuntimeTarget=web"

/**
 * Starts the Kanama Web runtime with a project's scripts: the generated `main()` calls it once,
 * before the bridge reaches any entry point. It installs the script-error report and the Godot
 * backend, the work the runtime's own `main()` did when the runtime and the scripts were one
 * module.
 */
@InternalKanamaBackendApi
fun startKanamaWeb(scripts: KanamaWebProjectScripts) {
  check(startedProjectScripts == null) { "startKanamaWeb was called twice" }
  WebScriptErrors.sink = ::jsReportScriptError
  installWebCommonGodotBackend()
  startedProjectScripts = scripts
}
