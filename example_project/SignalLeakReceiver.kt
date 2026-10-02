package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * The receiver of `SignalLeakSmoke`'s lambda connection. A lambda connection dispatches through the
 * receiver's Kanama script, so the receiver needs one; this one has no members of its own.
 */
@ScriptClass(attachTo = "Node")
class SignalLeakReceiver(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node)
