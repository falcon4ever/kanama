package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.GlobalClass
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle

/**
 * Task 131 review probe: a plain `@GlobalClass` script type (no `KanamaScript` base, so no `self`):
 * its owner's liveness comes from the instance id the runtime captured when it created the script.
 */
@ScriptClass(attachTo = "Node") @GlobalClass class FreedPlainTarget(val godotObject: GodotHandle)
