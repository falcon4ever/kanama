package net.multigesture.kanama.api

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import net.multigesture.kanama.types.NodePath

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
  /** A view of [handle] as this class: no reference taken (a `Node`, or a holder that keeps one). */
  fun wrap(handle: GodotHandle): GodotObject = table.wrap(index, handle)

  /** [wrap] for a cast result: a `RefCounted` one takes its own `+1` (task 132), as `from*` does. */
  fun wrapRetained(handle: GodotHandle): GodotObject =
    when (val view = table.wrap(index, handle)) {
      is RefCounted -> RefCounted.retained(view)
      else -> view
    }
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

  fun token(type: KClass<out GodotObject>): GodotClassToken = token(type, null)

  /** [token]; [use] (for example `Main.scoreTimer`) is named in the error. */
  fun token(type: KClass<out GodotObject>, use: String?): GodotClassToken =
    tokens[type]
      ?: throw IllegalArgumentException(
        (if (use != null) "$use: " else "") +
          "${type.simpleName} is not a Kanama wrapper of a Godot class; checked casts, node<T>() and " +
          "preload<T>() take a generated wrapper (Node, Timer, PackedScene, ...), and script<T>() takes " +
          "a Kotlin script class"
      )

  /**
   * Always asks Godot (`Object.is_class`), except for `GodotObject`, which every object is: a wrapper
   * whose Kotlin class already is a `T` may have been minted unchecked (`Timer(node.handle)`), so its
   * Kotlin type proves nothing. A `RefCounted` result is a new wrapper that takes a reference of its
   * own, as the `from*` downcasts do (task 132): kept in a field it keeps the object alive, closed
   * or forgotten it releases only that reference. Any other result is the same wrapper when it
   * already is a `T` and Godot agrees, else a new view.
   */
  @Suppress("UNCHECKED_CAST")
  fun <T : GodotObject> castOrNull(value: GodotObject, type: KClass<T>): T? {
    if (type == GodotObject::class) return value as T
    val token = token(type)
    if (!value.isClass(token.godotName)) return null
    if (value !is RefCounted && type.isInstance(value)) return value as T
    return token.wrapRetained(value.handle) as T
  }

  fun <T : GodotObject> cast(value: GodotObject, type: KClass<T>): T =
    castOrNull(value, type)
      ?: throw ClassCastException(
        "${describeObject(value)} (${value.getClassName()}) is not a ${token(type).godotName}"
      )

  fun <T : Node> getNodeAs(owner: Node, path: NodePath, type: KClass<T>): T? =
    owner.getNodeOrNull(path)?.let { castOrNull(it, type) }

  fun <T : Node> requireNodeAs(owner: Node, path: NodePath, type: KClass<T>): T {
    val node =
      owner.getNodeOrNull(path)
        ?: throw IllegalStateException("No node at \"${path.path}\" under ${describeObject(owner)}")
    return castOrNull(node, type)
      ?: throw IllegalStateException(
        "Node \"${path.path}\" under ${describeObject(owner)} is a ${node.getClassName()}, not a ${token(type).godotName}"
      )
  }

  fun <T : Node> instantiateAs(scene: PackedScene, type: KClass<T>): T {
    val token = token(type)
    val root = instantiateRoot(scene)
    return castOrNull(root, type)
      ?: run {
        val found = root.getClassName()
        root.queueFree()
        throw IllegalStateException(
          "${describeScene(scene)} has a $found root, not a ${token.godotName}"
        )
      }
  }

  /**
   * The Kotlin script of type [type] on a new instance of [scene]. Any other outcome (no script, a
   * script of another class, or a [type] that is no script class at all) frees the instance and
   * throws.
   */
  @Suppress("UNCHECKED_CAST")
  fun <T : Any> instantiateScript(scene: PackedScene, type: KClass<T>): T {
    val root = instantiateRoot(scene)
    val script = ScriptRuntime.scriptInstanceOf(root)
    if (script != null && type.isInstance(script)) return script as T
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
  return if (node.isInsideTree()) node.getPath().path else describeDetached(node)
}

/** `Node "Sub"`: a node outside the tree, by class and name. */
internal fun describeDetached(node: Node): String = "${node.getClassName()} \"${node.getName()}\""

// ── Typed checked casts (F3) ────────────────────────────────────────────────────────────────────

/**
 * This object as a `T` when Godot says it is one (`Object.is_class`, asked every time except for
 * `T = GodotObject`), else `null`: GDScript's `x as Camera3D`. A `RefCounted` result (a resource
 * cast: `res.castOrNull<Texture2D>()`) is a new wrapper with a reference of its own, like the
 * `from*` downcasts: close it to release that reference early, or let the GC release it; the
 * original is unaffected. Any other result is the same wrapper when it already is a `T`, else a new
 * view of the same object (a `Node` has no reference to release).
 */
inline fun <reified T : GodotObject> GodotObject.castOrNull(): T? = GodotClasses.castOrNull(this, T::class)

/** [castOrNull] that throws a `ClassCastException` naming both classes instead of returning `null`. */
inline fun <reified T : GodotObject> GodotObject.cast(): T = GodotClasses.cast(this, T::class)

/**
 * The node at [path] as a `T`, or `null` when the path is missing or the node is another class
 * (GDScript: `get_node_or_null(path) as T`).
 */
inline fun <reified T : Node> Node.getNodeAs(path: String): T? =
  GodotClasses.getNodeAs(this, NodePath(path), T::class)

/** [getNodeAs] for a [NodePath]. */
inline fun <reified T : Node> Node.getNodeAs(path: NodePath): T? = GodotClasses.getNodeAs(this, path, T::class)

/**
 * The node at [path] as a `T`; throws an `IllegalStateException` naming the path when it is missing
 * or the class found when it is not a `T` (GDScript: `$Path` into a typed variable). Unlike the
 * older `requireAs(path, ::T)`, the class is checked.
 */
inline fun <reified T : Node> Node.requireAs(path: String): T =
  GodotClasses.requireNodeAs(this, NodePath(path), T::class)

/** [requireAs] for a [NodePath]. */
inline fun <reified T : Node> Node.requireAs(path: NodePath): T =
  GodotClasses.requireNodeAs(this, path, T::class)

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
 * the script's `self`). A root without that script, with another script, or a [T] that is not a
 * Kotlin script class at all: the instance is freed and an `IllegalStateException` names what it
 * has.
 */
inline fun <reified T : Any> PackedScene.instantiateScript(): T = GodotClasses.instantiateScript(this, T::class)

// ── Tree accessors (F17) ────────────────────────────────────────────────────────────────────────

/**
 * The `SceneTree` this node is in. Throws an `IllegalStateException` (`Node "Sub" is not inside the
 * tree`) when the node is not inside the tree, where GDScript's `get_tree()` fails; `getTree()` is
 * the nullable form. The tree membership is checked first, so Godot logs no error of its own.
 */
val Node.tree: SceneTree
  get() {
    if (!isInsideTree()) throw IllegalStateException("${describeDetached(this)} is not inside the tree")
    return getTree() ?: throw IllegalStateException("${describeDetached(this)} is not inside the tree")
  }

/** The `Viewport` this node is in; throws like [tree] when the node is not inside the tree. */
val Node.viewport: Viewport
  get() {
    if (!isInsideTree()) throw IllegalStateException("${describeDetached(this)} is not inside the tree")
    return getViewport() ?: throw IllegalStateException("${describeDetached(this)} is not inside the tree")
  }

/** This node's parent; throws when it has none (`getParent()` is the nullable form). */
val Node.parentNode: Node
  get() = getParent() ?: throw IllegalStateException("${describeObject(this)} has no parent")

// ── Delegates ───────────────────────────────────────────────────────────────────────────────────

/**
 * `KanamaScript.node`: resolved on the first read after ready and cached until the next `_ready`
 * (the script's ready generation), as GDScript re-runs `@onready` initializers on every `_ready`,
 * including the one `request_ready()` schedules.
 */
internal class NodeDelegate<T : Node>(
  private val script: KanamaScript<*>,
  private val path: NodePath,
  private val type: KClass<T>,
) : ReadOnlyProperty<Any?, T> {
  private var cached: T? = null
  private var cachedGeneration = -1

  @Suppress("UNCHECKED_CAST")
  override fun getValue(thisRef: Any?, property: KProperty<*>): T {
    cached?.let {
      if (cachedGeneration == script.readyGeneration) return it
    }
    val use = "${script.scriptName()}.${property.name}"
    val token = GodotClasses.token(type, use)
    val owner = readyOwner(script, property, "node(\"${path.path}\")")
    val node =
      owner.getNodeOrNull(path)
        ?: throw IllegalStateException("$use: no node at \"${path.path}\" under ${describeObject(owner)}")
    if (!node.isClass(token.godotName)) {
      throw IllegalStateException(
        "$use: node \"${path.path}\" is a ${node.getClassName()}, not a ${token.godotName}"
      )
    }
    val typed = token.wrap(node.handle) as T
    cached = typed
    cachedGeneration = script.readyGeneration
    return typed
  }
}

/** `KanamaScript.script`: the Kotlin script on the node at a path, cached like [NodeDelegate]. */
internal class ScriptDelegate<T : Any>(
  private val script: KanamaScript<*>,
  private val path: NodePath,
  private val type: KClass<T>,
) : ReadOnlyProperty<Any?, T> {
  private var cached: T? = null
  private var cachedGeneration = -1

  @Suppress("UNCHECKED_CAST")
  override fun getValue(thisRef: Any?, property: KProperty<*>): T {
    cached?.let {
      if (cachedGeneration == script.readyGeneration) return it
    }
    val owner = readyOwner(script, property, "script(\"${path.path}\")")
    val node =
      owner.getNodeOrNull(path)
        ?: throw IllegalStateException(
          "${script.scriptName()}.${property.name}: no node at \"${path.path}\" under ${describeObject(owner)}"
        )
    val instance = ScriptRuntime.scriptInstanceOf(node)
    if (instance == null || !type.isInstance(instance)) {
      val found = instance?.let { "the script ${it::class.simpleName}" } ?: "no Kotlin script"
      throw IllegalStateException(
        "${script.scriptName()}.${property.name}: node \"${path.path}\" (${node.getClassName()}) has $found, not ${type.simpleName}"
      )
    }
    val typed = instance as T
    cached = typed
    cachedGeneration = script.readyGeneration
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
  private val type: KClass<T>,
) : ReadOnlyProperty<Any?, T> {
  private var cached: T? = null

  @Suppress("UNCHECKED_CAST")
  override fun getValue(thisRef: Any?, property: KProperty<*>): T {
    cached?.let {
      return it
    }
    val token = GodotClasses.token(type, property.name)
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
 * GDScript `preload` constant keeps its resource. [releaseAll] runs at engine shutdown. Keyed by the
 * path string: `res://a.tscn` and its `uid://...` are two entries holding two references to the one
 * resource Godot's own cache returns for both, which is harmless.
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
