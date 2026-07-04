package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OverrideVirtual
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.Control
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.types.Vector2
import java.lang.foreign.MemorySegment

/**
 * Phase 5.2 probe: exercises `@OverrideVirtual` for arbitrary, value-returning
 * engine virtuals beyond the fixed lifecycle set. Attaches to `Control` and
 * overrides several of its virtuals — a `Vector2` return, a `Vector2`-in/`Bool`
 * return, and (task 13) a `Vector2`-in/`String` return — so the generated
 * registrar must validate each signature against the engine table and emit the
 * matching return marshalling, including the non-POD `String` path.
 */
@ScriptClass(attachTo = "Control")
class VirtualOverrideProbe(godotObject: MemorySegment) : KanamaScript<Control>(godotObject, ::Control) {

    @OverrideVirtual
    fun _get_minimum_size(): Vector2 = Vector2(64.0f, 32.0f)

    @OverrideVirtual
    fun _has_point(at: Vector2): Boolean = at.x >= 0.0f && at.y >= 0.0f

    // task 13 — non-POD (String) virtual return: exercises variantWriteRetExpr's STRING path
    // (init/destroy-after-read) on desktop and the callVReturning String encode on iOS.
    @OverrideVirtual
    fun _get_tooltip(at: Vector2): String = "tip@${at.x.toInt()},${at.y.toInt()}"

    // task 13 — non-POD (PackedStringArray) virtual return: List<String> -> PackedStringArray via
    // BuiltinTypes.initPackedStringArray on desktop; the iOS encode ships a length-prefixed blob the
    // C shim rebuilds. `_get_configuration_warnings` is a Node virtual (Control inherits Node).
    @OverrideVirtual
    fun _get_configuration_warnings(): List<String> = listOf("kanama-warn-a", "kanama-warn-b")

    // task 13 — non-POD (Variant) virtual return: Any? boxed via initVariantFromAny on desktop; on
    // iOS the per-runtime-type encodeIosReturn dispatch handles the concrete value (here a String).
    // `_get_drag_data` is a Control virtual returning Variant (drag-and-drop payload).
    @OverrideVirtual
    fun _get_drag_data(at: Vector2): Any? = if (at.x < 0f) null else "drag:${at.x.toInt()}"
}
