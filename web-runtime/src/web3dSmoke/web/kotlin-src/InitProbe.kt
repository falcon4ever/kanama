package web3d

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.ConfigFile
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.api.RenderingServer
import net.multigesture.kanama.types.Vector3

/**
 * Harness-only (script-initializer engine calls): a script whose PROPERTY INITIALIZERS call the
 * engine and read `self`, the way GDScript's `var x = ...` initializers do. tps-demo's Settings
 * autoload built a ConfigFile in an initializer and its menu never became ready on Web: the
 * constructor ran before the proxy's callbacks were installed, so the calls had no owner.
 *
 * Main's `init_probe` asks this node for its readback, one bit per row (127 when all worked):
 *  - 1: an engine singleton (`RenderingServer.getCurrentRenderingDriverName()`) answered;
 *  - 2: a RefCounted (`ConfigFile.create()`) built in an initializer round-trips a value;
 *  - 4: `@OnReady` ran. A script that failed to construct and was rebuilt later by a lookup never
 *    gets its `_ready`, so this bit is red on a retried construction;
 *  - 8: `self.getTree()` answered; 16: `self.position` read the scene's transform (1, 2, 3);
 *  - 32: `RenderingServer.getCurrentRenderingMethod()` (a handle-keyed snapshot) answered;
 *  - 64: `self.getNodeOrNull("../InitSibling")` resolved a sibling (a node of its own, not ShareTarget).
 * Each initializer row records its own failure instead of throwing, so one gap shows as one bit.
 */
@ScriptClass(attachTo = "Node3D")
class InitProbe(godotObject: GodotHandle) : KanamaScript<Node3D>(godotObject, ::Node3D) {
  private val renderingDriver: String = RenderingServer.getCurrentRenderingDriverName()
  private val config: ConfigFile = ConfigFile.create()
  private val treeAnswered: Boolean = runCatching { self.getTree() }.isSuccess
  private val position: Vector3? = runCatching { self.position }.getOrNull()
  private val renderingMethod: String? = runCatching { RenderingServer.getCurrentRenderingMethod() }.getOrNull()
  private val sibling: Node? = runCatching { self.getNodeOrNull("../InitSibling") }.getOrNull()
  private var ready = false

  @OnReady
  fun onReady() {
    ready = true
  }

  fun initProbe(value: Long): Long {
    var mask = 0L
    if (renderingDriver.isNotEmpty()) mask = mask or 1L
    config.setValue("probe", "answer", 42L)
    if ((config.getValue("probe", "answer") as? Number)?.toLong() == 42L) mask = mask or 2L
    if (ready) mask = mask or 4L
    if (treeAnswered) mask = mask or 8L
    if (position == Vector3(1.0, 2.0, 3.0)) mask = mask or 16L
    if (!renderingMethod.isNullOrEmpty()) mask = mask or 32L
    if (sibling != null) mask = mask or 64L
    // The RefCounted was built by an initializer, so the script owns its release.
    config.close()
    return mask
  }
}
