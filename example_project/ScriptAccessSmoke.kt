package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnEnterTree
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.Camera3D
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Node2D
import net.multigesture.kanama.api.PackedScene
import net.multigesture.kanama.api.Texture2D
import net.multigesture.kanama.api.Time
import net.multigesture.kanama.api.Timer
import net.multigesture.kanama.api.asScript
import net.multigesture.kanama.api.cast
import net.multigesture.kanama.api.castOrNull
import net.multigesture.kanama.api.getNodeAs
import net.multigesture.kanama.api.instantiateAs
import net.multigesture.kanama.api.instantiateScript
import net.multigesture.kanama.api.isScript
import net.multigesture.kanama.api.parentNode
import net.multigesture.kanama.api.requireAs
import net.multigesture.kanama.api.tree
import net.multigesture.kanama.api.viewport
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Task 133 probe, run by `scripts/runtime_smoke.sh` as its own scene (`script_access_smoke.tscn`):
 * node and script delegates before and after ready, wrong-type and missing-node errors, checked
 * casts, `isScript`/`asScript`, `preload` and checked instancing, the tree accessors inside and
 * outside the tree, and the script coroutine scope (`wait`, `nextFrame`, cancellation on free).
 * Each row prints `name=true` when it behaves; the async rows print on a later frame, then the
 * script quits the tree.
 */
@ScriptClass(attachTo = "Node")
class ScriptAccessSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  private val scoreTimer by node<Timer>("ScoreTimer")
  private val scoreTimerAgain by node<Timer>("ScoreTimer")
  private val wrongType by node<Camera3D>("ScoreTimer")
  private val missing by node<Node>("Nope")
  private val target by script<ScriptAccessTarget>("Target")
  private val plainAsTarget by script<ScriptAccessTarget>("Plain")
  private val childScene by preload<PackedScene>("res://script_access_child.tscn")
  private val childSceneAgain by preload<PackedScene>("res://script_access_child.tscn")
  private val childSceneAsTexture by preload<Texture2D>("res://script_access_child.tscn")

  private var beforeReady = false

  private fun failsWith(fragment: String, block: () -> Any?): Boolean =
    try {
      block()
      false
    } catch (e: IllegalStateException) {
      (e.message ?: "").contains(fragment).also {
        if (!it)
          System.err.println("[kanama:kt] ScriptAccessSmoke unexpected message: ${e.message}")
      }
    }

  @OnEnterTree
  fun enterTree() {
    beforeReady = failsWith("was read before") { scoreTimer }
  }

  @OnReady
  fun ready() {
    val timerNode = self.getNodeOrNull("ScoreTimer")!!
    val nodeRow =
      scoreTimer.getClassName() == "Timer" &&
        scoreTimer.isSameInstance(timerNode) &&
        scoreTimer === scoreTimer &&
        scoreTimerAgain.isSameInstance(scoreTimer)
    val wrongTypeRow = failsWith("node \"ScoreTimer\" is a Timer, not a Camera3D") { wrongType }
    val missingRow = failsWith("no node at \"Nope\"") { missing }

    val scriptRow = target.ping() == "pong"
    val noScriptRow = failsWith("has no Kotlin script, not ScriptAccessTarget") { plainAsTarget }

    val targetNode = self.getNodeOrNull("Target")!!
    val plainNode = self.getNodeOrNull("Plain")!!
    val isScriptRow =
      targetNode.isScript<ScriptAccessTarget>() &&
        !plainNode.isScript<ScriptAccessTarget>() &&
        !targetNode.isScript<ScriptAccessSmoke>()
    val asScriptRow =
      targetNode.asScript<ScriptAccessTarget>() === target &&
        plainNode.asScript<ScriptAccessTarget>() == null

    val castRow =
      timerNode.castOrNull<Timer>() != null &&
        timerNode.castOrNull<Node>() != null &&
        timerNode.castOrNull<Camera3D>() == null &&
        (try {
          timerNode.cast<Node2D>()
          false
        } catch (e: ClassCastException) {
          e.message!!.contains("is not a Node2D")
        })
    val requireAsRow =
      self.requireAs<Timer>("ScoreTimer").isSameInstance(timerNode) &&
        self.getNodeAs<Camera3D>("ScoreTimer") == null &&
        self.getNodeAs<Timer>("Nope") == null &&
        failsWith("is a Node2D, not a Timer") { self.requireAs<Timer>("Marker") } &&
        failsWith("No node at \"Nope\"") { self.requireAs<Timer>("Nope") }

    val preloadRow =
      childScene.isSameInstance(childSceneAgain) && childScene.getClassName() == "PackedScene"
    val preloadWrongRow = failsWith("is a PackedScene, not a Texture2D") { childSceneAsTexture }
    val asNode2D = childScene.instantiateAs<Node2D>()
    val spawned = childScene.instantiateScript<ScriptAccessTarget>()
    val instantiateRow =
      asNode2D.getClassName() == "Node2D" &&
        spawned.ping() == "pong" &&
        failsWith("root, not a Camera3D") { childScene.instantiateAs<Camera3D>() } &&
        failsWith("has the script ScriptAccessTarget, not ScriptAccessSmoke") {
          childScene.instantiateScript<ScriptAccessSmoke>()
        }
    asNode2D.queueFree()

    val orphan = Node(GodotHandle(ObjectCalls.constructObject("Node")))
    val treeRow =
      self.tree.isSameInstance(self.getTree()!!) &&
        self.viewport.isSameInstance(self.getViewport()!!) &&
        self.parentNode.isSameInstance(self.getParent()!!)
    val orphanRow =
      failsWith("is not inside the tree") { orphan.tree } &&
        failsWith("is not inside the tree") { orphan.viewport } &&
        failsWith("has no parent") { orphan.parentNode }
    ObjectCalls.destroyObject(orphan.handle.segment)

    System.err.println(
      "[kanama:kt] ScriptAccessSmoke sync before_ready=$beforeReady node=$nodeRow wrong_type=$wrongTypeRow " +
        "missing=$missingRow script=$scriptRow no_script=$noScriptRow is_script=$isScriptRow " +
        "as_script=$asScriptRow cast=$castRow require_as=$requireAsRow preload=$preloadRow " +
        "preload_wrong=$preloadWrongRow instantiate=$instantiateRow tree=$treeRow orphan_tree=$orphanRow"
    )

    // The spawned script's coroutines must end when its node is freed (the free path).
    self.addChild(spawned.self)
    val counting =
      spawned.launch {
        while (true) {
          spawned.nextFrame()
          spawned.frames++
        }
      }
    val waitingLong = spawned.launch { spawned.wait(30.0) }

    launch {
      // Past the loading frame first: its long delta would count against a timer started in it,
      // in GDScript as here.
      nextFrame()
      nextFrame()
      val start = Time.getTicksMsec()
      wait(0.25)
      val waited = Time.getTicksMsec() - start
      val countedBeforeFree = spawned.frames
      spawned.self.queueFree()
      nextFrame()
      nextFrame()
      val framesAtFree = spawned.frames
      nextFrame()
      nextFrame()
      val stopped = spawned.frames == framesAtFree
      System.err.println(
        "[kanama:kt] ScriptAccessSmoke async wait=${waited >= 200} next_frame=${countedBeforeFree > 0} " +
          "freed_cancelled=${stopped && counting.isCancelled && waitingLong.isCancelled} waited_ms=$waited"
      )
      self.tree.quit()
    }
  }
}
