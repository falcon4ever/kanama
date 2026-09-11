package net.multigesture.kanama.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

/** Coroutine surface over the frame scheduler; see `WebFrameScheduler.kt` for the pump itself. */
internal object WebFrameCoroutineDispatcher : CoroutineDispatcher() {
  val pendingCount: Int
    get() = WebFrameScheduler.pendingCount

  override fun dispatch(context: kotlin.coroutines.CoroutineContext, block: Runnable) {
    WebFrameScheduler.dispatch(
      context[Job],
      block,
      scopeOwnerHandle = context[WebScopeOwner]?.ownerHandle ?: 0,
    )
  }
}

/**
 * Carries the script that owns a [KanamaScope] through the coroutine context.
 *
 * Without it, a coroutine's owner is whoever happened to be on the stack at `launch`, which is
 * routinely the wrong script: one script calling another's method (`Main.game_over` ->
 * `hud.showGameOver()`) launches on the callee's scope from the caller's callback.
 */
internal class WebScopeOwner(val ownerHandle: Int) :
  kotlin.coroutines.AbstractCoroutineContextElement(Key) {
  companion object Key : kotlin.coroutines.CoroutineContext.Key<WebScopeOwner>
}

/**
 * A script's coroutine scope.
 *
 * The owner defaults to the script being constructed: script instances are built inside their own
 * owner scope, so `override val kanamaScope = KanamaScope()` binds to the right script with no
 * ceremony at the call site. A scope built outside any script callback (handle 0) keeps the old
 * behaviour and is attributed to the ambient callback at dispatch time.
 */
class KanamaScope(private val ownerHandle: Int = WebFrameScheduler.currentOwnerOrZero()) :
  CoroutineScope {
  private val job = SupervisorJob()
  override val coroutineContext =
    WebFrameCoroutineDispatcher + job + WebScopeOwner(ownerHandle)

  fun cancel() {
    job.cancel()
  }
}

interface KanamaCoroutineOwner {
  val kanamaScope: KanamaScope
}

object MainThread {
  fun post(block: () -> Unit) {
    WebFrameScheduler.post(block)
  }

  /**
   * Runs [block] after [frames] frame pumps, like desktop's `MainThread.postAfterFrames` (task 64,
   * the DemoPage set): each hop is one scheduler post, so `frames = 0` is a plain [post]. Must be
   * called from a script callback (the scheduler binds the work to the current owner).
   */
  fun postAfterFrames(frames: Int, block: () -> Unit) {
    require(frames >= 0) { "MainThread.postAfterFrames requires a non-negative frame count" }
    if (frames <= 1) {
      post(block)
    } else {
      post { postAfterFrames(frames - 1, block) }
    }
  }
}

/** Frame-deferred work; the Web scheduler already runs posts on the next frame. */
fun MainThread.postNextFrame(block: () -> Unit) = post(block)
