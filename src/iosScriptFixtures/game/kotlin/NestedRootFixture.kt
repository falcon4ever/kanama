package net.multigesture.kanama.iosgatefixture

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

// A script in a folder not named kotlin-src. The CI iOS job passes the fixtures folder as the
// project (-PkanamaIosProjectDir) and checks that this script registers as
// res://game/kotlin/NestedRootFixture.kt, its path in the project, not res://NestedRootFixture.kt.
@ScriptClass(attachTo = "Node")
class NestedRootFixture(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
    fun answer(): Long = 42
}
