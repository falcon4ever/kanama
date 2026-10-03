package net.multigesture.kanama.iosgatefixture

import net.multigesture.kanama.annotations.OnEnterTree
import net.multigesture.kanama.annotations.OnExitTree
import net.multigesture.kanama.annotations.OnInput
import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.OnShortcutInput
import net.multigesture.kanama.annotations.OnUnhandledInput
import net.multigesture.kanama.annotations.OnUnhandledKeyInput
import net.multigesture.kanama.annotations.Rpc
import net.multigesture.kanama.annotations.RpcMode
import net.multigesture.kanama.annotations.RpcTransferMode
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.InputEvent
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Label
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

// Parallel-run gate fixture (Phase 3.2). Exercises the common iOS bridge shapes so the
// `checkIosScriptRegistryParity` task can prove the KSP processor emits the same registry as
// the legacy regex parser: ZERO/DOUBLE/OBJECT/LONG bridge kinds, a zero-arg helper method, a
// scalar Long + String + List<String> @Export, and a @Signal. Uses only iOS-available
// wrappers.
@ScriptClass(attachTo = "Label")
class GateFixtureScript(godotObject: GodotHandle) : KanamaScript<Label>(godotObject, ::Label) {
    @Export
    var score: Long = 0L

    @Export
    var title: String = ""

    @Export
    var forceLoop: List<String> = emptyList()

    // Value-type @Export delivery (Phase 3.2 Step 5 / 2.6). The motivating case is the
    // platformer `view: NodePath`; offset/aim exercise the Vector2/Vector3 set-property paths.
    @Export
    var view: NodePath = NodePath.EMPTY

    @Export
    var offset: Vector2 = Vector2(0f, 0f)

    @Export
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
    fun input(event: InputEvent) {
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
    fun unhandledInput(event: InputEvent) {
        score += 2L
    }

    @OnShortcutInput
    fun shortcutInput(event: InputEvent) {
        score += 3L
    }

    @OnUnhandledKeyInput
    fun unhandledKeyInput(event: InputEvent) {
        score += 4L
    }

    fun addPoints(amount: Long) {
        score += amount
    }

    fun resetScore() {
        score = 0L
    }

    // Phase 3.3: arg signatures the old enumerated bridge dropped as UNSUPPORTED. The generated
    // callV must dispatch each — (Long, Double), String, Vector3, NodePath, (Object, Object).
    fun configure(count: Long, weight: Double) {
        score += count + weight.toLong()
    }

    fun setLabel(text: String) {
        title = text
    }

    fun aimAt(target: Vector3) {
        aim = target
    }

    fun bindView(path: NodePath) {
        view = path
    }

    fun linkNodes(first: GodotObject, second: GodotObject) {
        score += 1L
    }

    // @Rpc rides on a registered (public) function; the processor captures its RpcModel, keeps the
    // method dispatchable on iOS via generic callV, and delivers _get_rpc_config to Godot. Explicit,
    // mutually-distinct mode/transferMode/channel values (rather than all-default) make this an
    // honest delivery example whose fields are individually observable.
    @Rpc(mode = RpcMode.ANY_PEER, callLocal = true, transferMode = RpcTransferMode.UNRELIABLE, channel = 4)
    fun netScore(points: Long) {
        score += points
    }

    @Signal
    fun scored() {}
}
