package net.multigesture.kanama.iosgatefixture

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.Rpc
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.generated.GodotEnumFixtureScriptSignals

// Task 128 B compile fixture: every Godot-enum script-member shape the iOS emitter marshals (scalar
// enum + bitfield properties, an enum list, @RegisterFunction enum arg/return, @Signal and @Rpc enum
// args). Compiled by the CI iOS job (`-PkanamaIosProjectScriptsDir=src/iosScriptFixtures/kotlin-src`);
// the desktop twin runs in the example project (GodotEnumExportSmoke).
@ScriptClass(attachTo = "Node")
class GodotEnumFixtureScript(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
    @ScriptProperty
    var mode: Node.ProcessMode = Node.ProcessMode.ALWAYS

    @ScriptProperty
    var messages: Node.ProcessThreadMessages =
        Node.ProcessThreadMessages.MESSAGES or
            Node.ProcessThreadMessages.MESSAGES_PHYSICS

    @ScriptProperty
    var modes: List<Node.ProcessMode> = emptyList()

    @Signal
    fun modeChanged(mode: Node.ProcessMode) = Unit

    @RegisterFunction
    fun nextMode(mode: Node.ProcessMode): Node.ProcessMode = Node.ProcessMode(mode.value + 1)

    @RegisterFunction
    fun hasPhysicsMessages(flags: Node.ProcessThreadMessages): Boolean =
        Node.ProcessThreadMessages.MESSAGES_PHYSICS in flags

    @Rpc(callLocal = true)
    @RegisterFunction
    fun syncMode(mode: Node.ProcessMode) {
        this.mode = mode
    }

    @OnReady
    fun ready() {
        GodotEnumFixtureScriptSignals.modeChanged(this, mode)
    }
}
