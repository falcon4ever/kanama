package net.multigesture.kanama.api

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import net.multigesture.kanama.binding.ScriptBridge
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.ScriptErrors

/** Desktop/Android actual of the script-authoring seam (`ScriptRuntime.expect.kt`, task 133). */
internal actual object ScriptRuntime {
  actual fun scriptInstanceOf(owner: GodotObject): Any? {
    val instance = ScriptBridge.kotlinObjectForOwner(owner.handle.segment)
    // Task 138 item 6: an owner-link rebuild (ScriptOwnerLinks.materialize) reads the owner.
    ObjectCalls.keepReachable(owner)
    return instance
  }

  actual fun loadResource(path: String): Resource? = ResourceLoader.load(path)

  actual fun isEditorHint(): Boolean = Engine.isEditorHint()

  actual fun mainDispatcher(): CoroutineDispatcher = MainThreadDispatcher

  actual fun reportCoroutineFailure(throwable: Throwable, where: String) {
    ScriptErrors.report(throwable, where)
    runCatching {
      System.err.println(
        "[kanama:kt] coroutine failed ($where): ${throwable.javaClass.name}: ${throwable.message}"
      )
      throwable.printStackTrace(System.err)
    }
  }

  actual fun platformClasses(): GodotClassTable = PlatformGodotClasses
}

/** Runs each block through the engine main-thread queue ([MainThread.post]). */
private object MainThreadDispatcher : CoroutineDispatcher() {
  override fun dispatch(context: CoroutineContext, block: Runnable) {
    MainThread.post { block.run() }
  }
}
