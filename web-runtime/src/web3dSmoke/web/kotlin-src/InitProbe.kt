package web3d

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.ConfigFile
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.RenderingServer

/**
 * Harness-only (script-initializer engine calls): a script whose PROPERTY INITIALIZERS call the
 * engine, the way GDScript's `var driver = RenderingServer.get_current_rendering_driver_name()` and
 * `var config = ConfigFile.new()` do. One call reaches an engine singleton, the other constructs a
 * RefCounted. tps-demo's Settings autoload did exactly this and its menu never became ready on Web:
 * the constructor ran before the proxy's callbacks were installed, so the engine calls had no owner.
 * Main's `init_probe` asks this node for its readback (1 + 2 = 3 when both initializers worked).
 */
@ScriptClass(attachTo = "Node")
class InitProbe(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  private val renderingDriver: String = RenderingServer.getCurrentRenderingDriverName()
  private val config: ConfigFile = ConfigFile.create()

  fun initProbe(value: Long): Long {
    var mask = 0L
    if (renderingDriver.isNotEmpty()) mask = mask or 1L
    config.setValue("probe", "answer", 42L)
    if ((config.getValue("probe", "answer") as? Number)?.toLong() == 42L) mask = mask or 2L
    // The RefCounted was built by an initializer, so the script owns its release.
    config.close()
    return mask
  }
}
