package net.multigesture.kanama.spike

import kotlin.time.TimeSource
import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.types.Vector3

// Task 75 spike — JVM vs Kotlin/Native wrapper-call benchmark.
// ONE source file, compiled unchanged for both backends. Each frame runs a
// fixed number of Vector3 set/get round-trips through the generated Node3D
// wrapper, so the measured cost is the ptrcall marshalling path (Panama/FFM on
// desktop JVM, the C shim on Kotlin/Native) and not engine work.
@ScriptClass(attachTo = "Node3D")
class BenchScript(godotObject: GodotHandle) : KanamaScript<Node3D>(godotObject, ::Node3D) {
    private var frame = 0

    @OnReady
    fun ready() {
        GD.print("[bench] READY iterations=$ITERATIONS frames=$FRAMES")
    }

    @OnProcess
    fun process(delta: Double) {
        frame++
        val mark = TimeSource.Monotonic.markNow()
        var acc = 0f
        var i = 0
        while (i < ITERATIONS) {
            self.setPosition(Vector3(i.toFloat(), 0f, 0f))
            acc += self.getPosition().x
            i++
        }
        val ns = mark.elapsedNow().inWholeNanoseconds
        // 2 wrapper calls per iteration (one set, one get).
        val perCallNs = ns.toDouble() / (ITERATIONS * 2).toDouble()
        GD.print("[bench] frame=$frame totalUs=${ns / 1000} perCallNs=$perCallNs checksum=$acc")
    }

    companion object {
        const val ITERATIONS = 50_000
        const val FRAMES = 120
    }
}
