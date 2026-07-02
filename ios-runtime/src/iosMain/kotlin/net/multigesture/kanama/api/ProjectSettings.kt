package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// KANAMA-IOS-HANDWRITTEN: [glue] ProjectSettings singleton. Not retired to the generated wrapper:
// the only demo-used method is getSettingDouble(name), a bespoke Variant->Double coercion over
// get_setting (a Variant return). The generator emits no coercing helper, so this stays bespoke.
object ProjectSettings {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("ProjectSettings")
    }

    /** ProjectSettings.get_setting(name) coerced to Double (e.g. physics/3d/default_gravity). */
    fun getSettingDouble(name: String): Double =
        when (val value = GodotObject(singleton).call("get_setting", name)) {
            is Double -> value
            is Float -> value.toDouble()
            is Long -> value.toDouble()
            is Int -> value.toDouble()
            else -> 0.0
        }
}
