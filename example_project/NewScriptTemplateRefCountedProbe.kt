// Task 131 item 5 (F15): exactly what the editor's New Script dialog writes for this script,
// which extends RefCounted (KanamaScriptTemplateTest holds the template to this file, below this
// comment). Here so the example build compiles that base's template through the KSP processor.
package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.RefCounted
import net.multigesture.kanama.types.*

@ScriptClass(attachTo = "RefCounted")
class NewScriptTemplateRefCountedProbe(godotObject: GodotHandle) :
  KanamaScript<RefCounted>(godotObject, { RefCounted.fromHandle(it)!! })
