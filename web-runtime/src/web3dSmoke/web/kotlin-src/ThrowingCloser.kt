package web3d

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.ButtonGroup
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.web.KanamaWebScript

/**
 * Harness-only (OwnProbe's constructor-rollback row): a scripted resource whose constructor applies
 * the command queue (`close()` on a RefCounted flushes it first) and then throws. OwnProbe makes it
 * with a call of its own still queued; the failed construction must not hand that call back to the
 * queue, which already applied it.
 */
@ScriptClass(attachTo = "Resource")
class ThrowingCloser(objectId: GodotHandle) : KanamaWebScript(objectId) {
  init {
    ButtonGroup.create().close()
    throw IllegalStateException("web3d deliberate script error (closer)")
  }
}
