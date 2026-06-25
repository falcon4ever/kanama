package net.multigesture.kanama.iosgatefixture

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.OnEnterTree
import net.multigesture.kanama.annotations.OnExitTree
import net.multigesture.kanama.annotations.OnInput
import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.OnShortcutInput
import net.multigesture.kanama.annotations.OnUnhandledInput
import net.multigesture.kanama.annotations.OnUnhandledKeyInput
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.Rpc
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Label
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

// Parallel-run gate fixture (Phase 3.2). Exercises the common iOS bridge shapes so the
// `checkIosScriptRegistryParity` task can prove the KSP processor emits the same registry as
// the legacy regex parser: ZERO/DOUBLE/OBJECT/LONG bridge kinds, a zero-arg helper method, a
// scalar Long + String + List<String> @ScriptProperty, and a @Signal. Uses only iOS-available
// wrappers.
@ScriptClass(attachTo = "Label")
class GateFixtureScript(godotObject: MemorySegment) : KanamaScript<Label>(godotObject, ::Label) {
    @ScriptProperty
    var score: Long = 0L

    @ScriptProperty
    var title: String = ""

    @ScriptProperty
    var forceLoop: List<String> = emptyList()

    // Value-type @ScriptProperty delivery (Phase 3.2 Step 5 / 2.6). The motivating case is the
    // platformer `view: NodePath`; offset/aim exercise the Vector2/Vector3 set-property paths.
    @ScriptProperty
    var view: NodePath = NodePath.EMPTY

    @ScriptProperty
    var offset: Vector2 = Vector2(0f, 0f)

    @ScriptProperty
    var aim: Vector3 = Vector3(0f, 0f, 0f)

    @OnReady
    fun ready() {
        self.text = "gate fixture ready"
    }

    @OnProcess
    fun process(delta: Double) {
        score += delta.toLong()
    }

    @OnInput
    fun input(event: GodotObject) {
        score += 1L
    }

    // Phase 3.4: the remaining lifecycle/input virtuals — tree virtuals dispatch via the
    // notification callback, the input virtuals via the generic call callback once the C side
    // enables the matching processing flag. All must appear in the emitted iOS method list.
    @OnEnterTree
    fun enterTree() {
        score += 10L
    }

    @OnExitTree
    fun exitTree() {
        score -= 10L
    }

    @OnUnhandledInput
    fun unhandledInput(event: GodotObject) {
        score += 2L
    }

    @OnShortcutInput
    fun shortcutInput(event: GodotObject) {
        score += 3L
    }

    @OnUnhandledKeyInput
    fun unhandledKeyInput(event: GodotObject) {
        score += 4L
    }

    @RegisterFunction("add_points")
    fun addPoints(amount: Long) {
        score += amount
    }

    @RegisterFunction
    fun resetScore() {
        score = 0L
    }

    // Phase 3.3: arg signatures the old enumerated bridge dropped as UNSUPPORTED. The generated
    // callV must dispatch each — (Long, Double), String, Vector3, NodePath, (Object, Object).
    @RegisterFunction
    fun configure(count: Long, weight: Double) {
        score += count + weight.toLong()
    }

    @RegisterFunction
    fun setLabel(text: String) {
        title = text
    }

    @RegisterFunction
    fun aimAt(target: Vector3) {
        aim = target
    }

    @RegisterFunction
    fun bindView(path: NodePath) {
        view = path
    }

    @RegisterFunction
    fun linkNodes(first: GodotObject, second: GodotObject) {
        score += 1L
    }

    // Phase 3.4 @Rpc parse-side: @Rpc rides on a @RegisterFunction method; the processor captures
    // its RpcModel and the method stays dispatchable on iOS via the generic callV (full
    // _get_rpc_config delivery to Godot multiplayer is a later phase).
    @Rpc(callLocal = true)
    @RegisterFunction("net_score")
    fun netScore(points: Long) {
        score += points
    }

    @Signal
    fun scored() {}
}
