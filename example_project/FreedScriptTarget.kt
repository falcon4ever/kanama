package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.GlobalClass
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Task 131 review probe: a `@GlobalClass` script type (KanamaScript, so it carries a `self`
 * wrapper) that `FreedObjectSmoke` holds in exported properties after its node was freed.
 */
@ScriptClass(attachTo = "Node")
@GlobalClass
class FreedScriptTarget(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node)
