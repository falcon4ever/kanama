package web3d

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Harness-only (task 138 item 22): a script-backed node FreedHolder holds while its parent is
 * freed. Its handle is its script's (bit 30 clear), not a browser handle.
 */
@ScriptClass(attachTo = "Node")
class FreedScriptTarget(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node)
