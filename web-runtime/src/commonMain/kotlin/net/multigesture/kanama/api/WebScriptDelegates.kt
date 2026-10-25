package net.multigesture.kanama.api

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.web.webScriptInstance

// Task 142: the script-authoring sugar of the shared ScriptAccess.kt and KanamaScript (desktop,
// Android, iOS) on Web, with the same spelling: the delegates behind KanamaScript's `node`, `script`
// and `preload` members (WebKanamaScript.kt) and the `instantiate*` extensions. A node delegate resolves on its first read after `_ready` and is cached
// until the next `_ready` the bridge dispatches (`KanamaWebScript.webReadyGeneration`, the Web form
// of desktop's ready generation), through the checked casts of WebCheckedCasts.kt.

/** Instantiates the scene and returns its root as a `T`; a root of another class is freed and named. */
inline fun <reified T : Node> PackedScene.instantiateAs(): T = webInstantiateAs(this, T::class)

/** Instantiates the scene and returns the Kotlin script of type [T] on its root. */
inline fun <reified T : Any> PackedScene.instantiateScript(): T = webInstantiateScript(this, T::class)

private fun KanamaScript<*>.scriptName(): String = this::class.simpleName ?: "KanamaScript"

/** The script's node, once its `_ready` ran; [what] names the delegate in the error. */
private fun KanamaScript<*>.readyOwner(property: KProperty<*>, what: String): Node {
  val owner =
    self as? Node
      ?: GodotObject(godotObject).let { target ->
        check(target.isClass("Node")) {
          "${scriptName()}.${property.name} needs a Node, but the script is attached to a ${webClassName(target)}"
        }
        Node(godotObject)
      }
  if (webReadyGeneration == 0) {
    throw IllegalStateException(
      "${scriptName()}.${property.name}: $what was read before ${webDescribeObject(owner)} was ready; " +
        "read it in @OnReady or later (GDScript assigns @onready variables just before _ready)"
    )
  }
  return owner
}

@PublishedApi
internal class WebNodeDelegate<T : Node>(
  private val script: KanamaScript<*>,
  private val path: String,
  private val type: KClass<T>,
) : ReadOnlyProperty<Any?, T> {
  private var cached: T? = null
  private var cachedGeneration = -1

  override fun getValue(thisRef: Any?, property: KProperty<*>): T {
    cached?.let { if (cachedGeneration == script.webReadyGeneration) return it }
    val use = "${script.scriptName()}.${property.name}"
    val token = webClassToken(type)
    val owner = script.readyOwner(property, "node(\"$path\")")
    val node =
      owner.getNodeOrNull(path)
        ?: throw IllegalStateException("$use: no node at \"$path\" under ${webDescribeObject(owner)}")
    val typed =
      webCastOrNull(node, type)
        ?: throw IllegalStateException(
          "$use: node \"$path\" is a ${webClassName(node)}, not a ${token.godotName}"
        )
    cached = typed
    cachedGeneration = script.webReadyGeneration
    return typed
  }
}

@PublishedApi
internal class WebScriptDelegate<T : Any>(
  private val script: KanamaScript<*>,
  private val path: String,
  private val type: KClass<T>,
) : ReadOnlyProperty<Any?, T> {
  private var cached: T? = null
  private var cachedGeneration = -1

  @Suppress("UNCHECKED_CAST")
  override fun getValue(thisRef: Any?, property: KProperty<*>): T {
    cached?.let { if (cachedGeneration == script.webReadyGeneration) return it }
    val use = "${script.scriptName()}.${property.name}"
    val owner = script.readyOwner(property, "script(\"$path\")")
    val node =
      owner.getNodeOrNull(path)
        ?: throw IllegalStateException("$use: no node at \"$path\" under ${webDescribeObject(owner)}")
    val instance = webScriptInstance(node.handle.value)
    if (instance == null || !type.isInstance(instance)) {
      val found = instance?.let { "the script ${it::class.simpleName}" } ?: "no Kotlin script"
      throw IllegalStateException(
        "$use: node \"$path\" (${webClassName(node)}) has $found, not ${type.simpleName}"
      )
    }
    val typed = instance as T
    cached = typed
    cachedGeneration = script.webReadyGeneration
    return typed
  }
}

/**
 * The page-wide preload cache: one loaded resource per path, kept as a `preload` constant keeps it.
 * A Web handle can go while the cache holds it: the bridge releases the handles a script's proxy
 * obtained when the last proxy that could route them is freed (a scene change with no Kotlin
 * autoload left). A cached resource whose handle is gone is loaded again, under the script that
 * asks now.
 */
@PublishedApi
internal object WebPreloads {
  private val loaded = HashMap<String, Resource>()

  fun get(path: String): Resource {
    loaded[path]?.let { if (it.isInstanceLive()) return it }
    val resource =
      ResourceLoader.load(path)
        ?: throw IllegalStateException("preload(\"$path\"): no resource could be loaded from that path")
    loaded[path] = resource
    return resource
  }
}

@PublishedApi
internal class WebPreloadDelegate<T : Resource>(
  private val path: String,
  private val type: KClass<T>,
) : ReadOnlyProperty<Any?, T> {
  private var cached: T? = null

  override fun getValue(thisRef: Any?, property: KProperty<*>): T {
    cached?.let { if (it.isInstanceLive()) return it }
    val token = webClassToken(type)
    val resource = WebPreloads.get(path)
    val typed =
      webCastOrNull(resource, type)
        ?: throw IllegalStateException(
          "${property.name}: preload(\"$path\") is a ${webClassName(resource)}, not a ${token.godotName}"
        )
    cached = typed
    return typed
  }
}

private fun webInstantiateRoot(scene: PackedScene): Node =
  scene.instantiate() ?: throw IllegalStateException("${webDescribeScene(scene)} did not instantiate")

private fun webDescribeScene(scene: PackedScene): String =
  (scene.call("get_path") as? String).orEmpty().let { if (it.isEmpty()) "PackedScene" else "PackedScene $it" }

@PublishedApi
internal fun <T : Node> webInstantiateAs(scene: PackedScene, type: KClass<T>): T {
  val token = webClassToken(type)
  val root = webInstantiateRoot(scene)
  return webCastOrNull(root, type)
    ?: run {
      val found = webClassName(root)
      root.queueFree()
      throw IllegalStateException("${webDescribeScene(scene)} has a $found root, not a ${token.godotName}")
    }
}

@PublishedApi
@Suppress("UNCHECKED_CAST")
internal fun <T : Any> webInstantiateScript(scene: PackedScene, type: KClass<T>): T {
  val root = webInstantiateRoot(scene)
  val script = webScriptInstance(root.handle.value)
  if (script != null && type.isInstance(script)) return script as T
  val found = script?.let { "the script ${it::class.simpleName}" } ?: "no Kotlin script"
  val rootClass = webClassName(root)
  root.queueFree()
  throw IllegalStateException("${webDescribeScene(scene)} root $rootClass has $found, not ${type.simpleName}")
}
