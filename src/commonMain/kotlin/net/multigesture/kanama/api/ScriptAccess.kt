package net.multigesture.kanama.api

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

// Typed checked casts, script checks, tree accessors and the delegates behind
// `KanamaScript.node`/`script`/`preload` (task 133). Hand-written, common to desktop, Android and iOS;
// the class tokens come from the generated tables (`SharedGodotClasses` in `GodotClasses.kt`, and
// each platform's `PlatformGodotClasses`, written by `scripts/generate_api_wrapper.py --write-tree`).

/**
 * One generated table of wrapper classes, index-aligned: `classes[i]` is the Kotlin wrapper of the
 * Godot class `names[i]`, and `wrap(i, handle)` constructs it. Built from class literals and
 * constructor calls, never from names, so it survives R8 renaming and needs no reflection.
 */
internal abstract class GodotClassTable {
  abstract val classes: Array<KClass<out GodotObject>>
  abstract val names: Array<String>

  abstract fun wrap(index: Int, handle: GodotHandle): GodotObject
}

/** A wrapper class: its Godot class name and how to wrap a handle as it. */
@PublishedApi
internal class GodotClassToken(
  val godotName: String,
  private val table: GodotClassTable,
  private val index: Int,
) {
  fun wrap(handle: GodotHandle): GodotObject = table.wrap(index, handle)
}

/** The lookup from a wrapper `KClass` to its [GodotClassToken], built on first use. */
@PublishedApi
internal object GodotClasses {
  private val tokens: Map<KClass<*>, GodotClassToken> by lazy {
    val map = HashMap<KClass<*>, GodotClassToken>(2048)
    for (table in listOf(SharedGodotClasses, ScriptRuntime.platformClasses())) {
      for (i in table.classes.indices) {
        map[table.classes[i]] = GodotClassToken(table.names[i], table, i)
      }
    }
    map
  }

  /** The number of wrapper classes the tables list (the shared tree plus this platform's own). */
  val size: Int
    get() = tokens.size

  fun token(type: KClass<out GodotObject>): GodotClassToken =
    tokens[type]
      ?: throw IllegalArgumentException(
        "${type.simpleName} is not a Kanama wrapper of a Godot class; checked casts and node<T>() " +
          "take a generated wrapper (Node, Timer, PackedScene, ...), and script<T>() takes a Kotlin script class"
      )

  @Suppress("UNCHECKED_CAST")
  fun <T : GodotObject> castOrNull(value: GodotObject, type: KClass<T>): T? {
    if (type.isInstance(value)) return value as T
    val token = token(type)
    return if (value.isClass(token.godotName)) token.wrap(value.handle) as T else null
  }

  fun <T : GodotObject> cast(value: GodotObject, type: KClass<T>): T =
    castOrNull(value, type)
      ?: throw ClassCastException("${describeObject(value)} is not a ${token(type).godotName}")

  fun <T : Node> getNodeAs(owner: Node, path: String, type: KClass<T>): T? =
    owner.getNodeOrNull(path)?.let { castOrNull(it, type) }

  fun <T : Node> requireNodeAs(owner: Node, path: String, type: KClass<T>): T {
    val node =
      owner.getNodeOrNull(path)
        ?: throw IllegalStateException("No node at \"$path\" under ${describeObject(owner)}")
    return castOrNull(node, type)
      ?: throw IllegalStateException(
        "Node \"$path\" under ${describeObject(owner)} is a ${node.getClassName()}, not a ${token(type).godotName}"
      )
  }

  fun <T : Node> instantiateAs(scene: PackedScene, type: KClass<T>): T {
    val root = instantiateRoot(scene)
    return castOrNull(root, type)
      ?: run {
        val found = root.getClassName()
        root.queueFree()
        throw IllegalStateException(
          "${describeScene(scene)} has a $found root, not a ${token(type).godotName}"
        )
      }
  }

  @Suppress("UNCHECKED_CAST")
  fun <T : Any> instantiateScript(scene: PackedScene, type: KClass<T>): T {
    val root = instantiateRoot(scene)
    val script = ScriptRuntime.scriptInstanceOf(root)
    if (type.isInstance(script)) return script as T
    val found = script?.let { "the script ${it::class.simpleName}" } ?: "no Kotlin script"
    root.queueFree()
    throw IllegalStateException(
      "${describeScene(scene)} root ${root.getClassName()} has $found, not ${type.simpleName}"
    )
  }

  private fun instantiateRoot(scene: PackedScene): Node =
    scene.instantiate() ?: throw IllegalStateException("${describeScene(scene)} did not instantiate")

  private fun describeScene(scene: PackedScene): String =
    scene.getPath().let { if (it.isEmpty()) "PackedScene" else "PackedScene $it" }
}

/** `Kotlin script object attached to [value]`, for the inline script checks. */
@PublishedApi internal fun scriptInstanceOf(value: GodotObject): Any? = ScriptRuntime.scriptInstanceOf(value)

/** How errors name an object: its node path inside the tree, else its class and name. */
internal fun describeObject(value: GodotObject): String {
  if (!value.isClass("Node")) return value.getClassName()
  val node = value as? Node ?: Node(value.handle)
  return if (node.isInsideTree()) node.getPath().path
  else "${node.getClassName()} \"${node.getName()}\" (outside the tree)"
}

// ── Typed checked casts (F3) ────────────────────────────────────────────────────────────────────

/**
 * This object as a `T` when Godot says it is one (`Object.is_class`), else `null`: GDScript's
 * `x as Camera3D`. The result is another non-owning view of the same object, so a `RefCounted`
 * stays owned by the wrapper you cast from.
 */
inline fun <reified T : GodotObject> GodotObject.castOrNull(): T? = GodotClasses.castOrNull(this, T::class)

/** [castOrNull] that throws a `ClassCastException` naming both classes instead of returning `null`. */
inline fun <reified T : GodotObject> GodotObject.cast(): T = GodotClasses.cast(this, T::class)

/**
 * The node at [path] as a `T`, or `null` when the path is missing or the node is another class
 * (GDScript: `get_node_or_null(path) as T`).
 */
inline fun <reified T : Node> Node.getNodeAs(path: String): T? = GodotClasses.getNodeAs(this, path, T::class)

/**
 * The node at [path] as a `T`; throws an `IllegalStateException` naming the path when it is missing
 * or the class found when it is not a `T` (GDScript: `$Path` into a typed variable). Unlike the
 * older `requireAs(path, ::T)`, the class is checked.
 */
inline fun <reified T : Node> Node.requireAs(path: String): T = GodotClasses.requireNodeAs(this, path, T::class)

// ── Script checks (F11) ─────────────────────────────────────────────────────────────────────────

/** True when a Kotlin script of type [T] is attached to this object: GDScript's `body is Player`. */
inline fun <reified T : Any> GodotObject.isScript(): Boolean = scriptInstanceOf(this) is T

/** The Kotlin script of type [T] attached to this object, or `null`: GDScript's `body as Player`. */
inline fun <reified T : Any> GodotObject.asScript(): T? = scriptInstanceOf(this) as? T

// ── Instancing (F12) ────────────────────────────────────────────────────────────────────────────

/**
 * Instantiates the scene and returns its root as a `T`. A root of another class is freed and an
 * `IllegalStateException` names both classes.
 */
inline fun <reified T : Node> PackedScene.instantiateAs(): T = GodotClasses.instantiateAs(this, T::class)

/**
 * Instantiates the scene and returns the Kotlin script of type [T] on its root (reach the node with
 * the script's `self`). A root without that script is freed and an `IllegalStateException` names
 * what it has.
 */
inline fun <reified T : Any> PackedScene.instantiateScript(): T = GodotClasses.instantiateScript(this, T::class)

// ── Tree accessors (F17) ────────────────────────────────────────────────────────────────────────

/**
 * The `SceneTree` this node is in. Throws an `IllegalStateException` when the node is not inside
 * the tree, where GDScript's `get_tree()` fails the same way; `getTree()` is the nullable form.
 */
val Node.tree: SceneTree
  get() = getTree() ?: throw IllegalStateException("${describeObject(this)} is not inside the tree")

/** The `Viewport` this node is in; throws when the node is not inside the tree (`getViewport()` is nullable). */
val Node.viewport: Viewport
  get() =
    getViewport() ?: throw IllegalStateException("${describeObject(this)} is not inside the tree")

/** This node's parent; throws when it has none (`getParent()` is the nullable form). */
val Node.parentNode: Node
  get() = getParent() ?: throw IllegalStateException("${describeObject(this)} has no parent")

// ── Delegates ───────────────────────────────────────────────────────────────────────────────────

/** `KanamaScript.node`: resolved on the first read after ready, then cached. */
internal class NodeDelegate<T : Node>(
  private val script: KanamaScript<*>,
  private val path: String,
  private val token: GodotClassToken,
) : ReadOnlyProperty<Any?, T> {
  private var cached: T? = null

  @Suppress("UNCHECKED_CAST")
  override fun getValue(thisRef: Any?, property: KProperty<*>): T {
    cached?.let {
      return it
    }
    val owner = readyOwner(script, property, "node(\"$path\")")
    val node =
      owner.getNodeOrNull(path)
        ?: throw IllegalStateException(
          "${script.scriptName()}.${property.name}: no node at \"$path\" under ${describeObject(owner)}"
        )
    if (!node.isClass(token.godotName)) {
      throw IllegalStateException(
        "${script.scriptName()}.${property.name}: node \"$path\" is a ${node.getClassName()}, not a ${token.godotName}"
      )
    }
    val typed = token.wrap(node.handle) as T
    cached = typed
    return typed
  }
}

/** `KanamaScript.script`: the Kotlin script on the node at a path, resolved like [NodeDelegate]. */
internal class ScriptDelegate<T : Any>(
  private val script: KanamaScript<*>,
  private val path: String,
  private val type: KClass<T>,
) : ReadOnlyProperty<Any?, T> {
  private var cached: T? = null

  @Suppress("UNCHECKED_CAST")
  override fun getValue(thisRef: Any?, property: KProperty<*>): T {
    cached?.let {
      return it
    }
    val owner = readyOwner(script, property, "script(\"$path\")")
    val node =
      owner.getNodeOrNull(path)
        ?: throw IllegalStateException(
          "${script.scriptName()}.${property.name}: no node at \"$path\" under ${describeObject(owner)}"
        )
    val instance = ScriptRuntime.scriptInstanceOf(node)
    if (!type.isInstance(instance)) {
      val found = instance?.let { "the script ${it::class.simpleName}" } ?: "no Kotlin script"
      throw IllegalStateException(
        "${script.scriptName()}.${property.name}: node \"$path\" (${node.getClassName()}) has $found, not ${type.simpleName}"
      )
    }
    val typed = instance as T
    cached = typed
    return typed
  }
}

private fun readyOwner(script: KanamaScript<*>, property: KProperty<*>, what: String): Node {
  val owner = script.ownerNode(property.name)
  if (!owner.isNodeReady()) {
    throw IllegalStateException(
      "${script.scriptName()}.${property.name}: $what was read before ${describeObject(owner)} was ready; " +
        "read it in @OnReady or later (GDScript assigns @onready variables just before _ready)"
    )
  }
  return owner
}

/** `KanamaScript.preload`: a typed view of the process-wide [Preloads] entry. */
internal class PreloadDelegate<T : Resource>(
  private val path: String,
  private val token: GodotClassToken,
) : ReadOnlyProperty<Any?, T> {
  private var cached: T? = null

  @Suppress("UNCHECKED_CAST")
  override fun getValue(thisRef: Any?, property: KProperty<*>): T {
    cached?.let {
      return it
    }
    val resource = Preloads.get(path)
    if (!resource.isClass(token.godotName)) {
      throw IllegalStateException(
        "${property.name}: preload(\"$path\") is a ${resource.getClassName()}, not a ${token.godotName}"
      )
    }
    val typed = token.wrap(resource.handle) as T
    cached = typed
    return typed
  }
}

/**
 * The process-wide preload cache: one owned reference per path, loaded on first use and kept, as a
 * GDScript `preload` constant keeps its resource. [releaseAll] runs at engine shutdown.
 */
internal object Preloads {
  private val loaded = HashMap<String, Resource>()

  fun get(path: String): Resource =
    loaded.getOrPut(path) {
      ScriptRuntime.loadResource(path)
        ?: throw IllegalStateException("preload(\"$path\"): no resource could be loaded from that path")
    }

  val size: Int
    get() = loaded.size

  /** Releases every cached reference (engine shutdown). */
  fun releaseAll() {
    val all = loaded.values.toList()
    loaded.clear()
    for (resource in all) runCatching { resource.close() }
  }
}
