package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls

// KANAMA-IOS-HANDWRITTEN: [selftest] task 133 rows of the OBJECTCALLS SELFTEST frame-1 phase: class tokens, checked casts, script checks, tree accessors, preload errors and the script scope on the device runtime.
private class ScriptAccessSelfTestScript(handle: GodotHandle) : KanamaScript<Node>(handle, ::Node)

/**
 * Task 133 self-test rows, run by `kanamaIosRuntimeObjectCallsSelfTestFrame` once the SceneTree is
 * up. [check] records one row each; nothing here may raise an ObjectCalls fault (the phase asserts
 * the exact fault count).
 */
internal fun scriptAccessSelfTestRows(check: (String, Boolean) -> Unit) {
  // Class tokens: the shared table and this platform's own (AudioStreamPlayer is hand-written here).
  check("class-token(Node -> Node)", GodotClasses.token(Node::class).godotName == "Node")
  check("class-token(GodotObject -> Object)", GodotClasses.token(GodotObject::class).godotName == "Object")
  check(
    "class-token(platform AudioStreamPlayer)",
    GodotClasses.token(AudioStreamPlayer::class).godotName == "AudioStreamPlayer",
  )

  // Checked casts against a real Timer.
  val timerHandle = GodotHandle(ObjectCalls.constructObject("Timer"))
  val asObject = GodotObject(timerHandle)
  check("castOrNull<Timer>", asObject.castOrNull<Timer>()?.isSameInstance(asObject) == true)
  check("castOrNull<Node> (superclass)", asObject.castOrNull<Node>() != null)
  check("castOrNull<Camera3D> == null", asObject.castOrNull<Camera3D>() == null)
  check(
    "cast<Node2D> throws",
    try {
      asObject.cast<Node2D>()
      false
    } catch (e: ClassCastException) {
      e.message.orEmpty().contains("is not a Node2D")
    },
  )

  // Script checks on an object without a Kanama script.
  check("isScript on a plain object == false", !asObject.isScript<ScriptAccessSelfTestScript>())
  check("asScript on a plain object == null", asObject.asScript<ScriptAccessSelfTestScript>() == null)

  // Tree accessors outside and inside the tree.
  val orphan = Node(timerHandle)
  check(
    "tree outside the tree throws",
    try {
      orphan.parentNode
      false
    } catch (e: IllegalStateException) {
      e.message.orEmpty().contains("has no parent")
    },
  )
  val root = SceneTree.active().getRoot()
  check("tree inside the tree", root.tree.isSameInstance(SceneTree.active()))
  ObjectCalls.destroyObject(timerHandle.segment)

  // A preload of a missing path is a clear error, not a crash.
  check(
    "preload(missing) throws",
    try {
      Preloads.get("res://__kanama_selftest_missing__.tres")
      false
    } catch (e: IllegalStateException) {
      e.message.orEmpty().contains("no resource could be loaded")
    },
  )

  // The script scope: launch, then the free path cancels it.
  val scriptNode = GodotHandle(ObjectCalls.constructObject("Node"))
  val script = ScriptAccessSelfTestScript(scriptNode)
  val job = script.launch { script.nextFrame() }
  script.disposeScriptScope()
  check("script scope cancelled by the free path", job.isCancelled)
  ObjectCalls.destroyObject(scriptNode.segment)
}
