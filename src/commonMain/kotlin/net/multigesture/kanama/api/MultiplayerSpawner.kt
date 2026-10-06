package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * Generated from Godot docs: MultiplayerSpawner
 */
class MultiplayerSpawner(handle: GodotHandle) : Node(handle) {
    var spawnPath: NodePath
        @JvmName("spawnPathProperty")
        get() = getSpawnPath()
        @JvmName("setSpawnPathProperty")
        set(value) = setSpawnPath(value)

    var spawnLimit: Long
        @JvmName("spawnLimitProperty")
        get() = getSpawnLimit()
        @JvmName("setSpawnLimitProperty")
        set(value) = setSpawnLimit(value)

    val spawnFunction: GodotCallable?
        @JvmName("spawnFunctionProperty")
        get() = getSpawnFunction()

    fun addSpawnableScene(path: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.addSpawnableSceneBind, segment, path)
    }

    fun getSpawnableSceneCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSpawnableSceneCountBind, segment)
    }

    fun getSpawnableScene(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getSpawnableSceneBind, segment, index)
    }

    fun clearSpawnableScenes() {
        ObjectCalls.ptrcallNoArgs(Binds.clearSpawnableScenesBind, segment)
    }

    fun spawn(data: Any? = null): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithVariantArgRetObject(Binds.spawnBind, segment, data))
    }

    fun getSpawnPath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getSpawnPathBind, segment)
    }

    fun setSpawnPath(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(Binds.setSpawnPathBind, segment, path)
    }

    fun getSpawnLimit(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getSpawnLimitBind, segment)
    }

    fun setSpawnLimit(limit: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setSpawnLimitBind, segment, limit)
    }

    fun getSpawnFunction(): GodotCallable? {
        return ObjectCalls.ptrcallNoArgsRetCallable(Binds.getSpawnFunctionBind, segment)
    }

    fun setSpawnFunction(spawnFunction: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(Binds.setSpawnFunctionBind, segment, spawnFunction.target.segment, spawnFunction.method)
    }

    /** Signal `despawned(node: Node)`; see [TypedSignal]. */
    val despawned: Signal1<Node>
        @JvmName("despawnedTypedSignal")
        get() = Signal1(this, "despawned", SignalArgType.objectOf("Node") { Node(it) })

    /** Signal `spawned(node: Node)`; see [TypedSignal]. */
    val spawned: Signal1<Node>
        @JvmName("spawnedTypedSignal")
        get() = Signal1(this, "spawned", SignalArgType.objectOf("Node") { Node(it) })

    object Signals {
        const val despawned: String = "despawned"
        const val spawned: String = "spawned"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): MultiplayerSpawner? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): MultiplayerSpawner? =
            if (handle.address() == 0L) null else MultiplayerSpawner(GodotHandle(handle))
    }

    private object Binds {
        private const val ADD_SPAWNABLE_SCENE_HASH = 83702148L
        @JvmField
        val addSpawnableSceneBind =
            ObjectCalls.getMethodBind("MultiplayerSpawner", "add_spawnable_scene", ADD_SPAWNABLE_SCENE_HASH)

        private const val GET_SPAWNABLE_SCENE_COUNT_HASH = 3905245786L
        @JvmField
        val getSpawnableSceneCountBind =
            ObjectCalls.getMethodBind("MultiplayerSpawner", "get_spawnable_scene_count", GET_SPAWNABLE_SCENE_COUNT_HASH)

        private const val GET_SPAWNABLE_SCENE_HASH = 844755477L
        @JvmField
        val getSpawnableSceneBind =
            ObjectCalls.getMethodBind("MultiplayerSpawner", "get_spawnable_scene", GET_SPAWNABLE_SCENE_HASH)

        private const val CLEAR_SPAWNABLE_SCENES_HASH = 3218959716L
        @JvmField
        val clearSpawnableScenesBind =
            ObjectCalls.getMethodBind("MultiplayerSpawner", "clear_spawnable_scenes", CLEAR_SPAWNABLE_SCENES_HASH)

        private const val SPAWN_HASH = 1991184589L
        @JvmField
        val spawnBind =
            ObjectCalls.getMethodBind("MultiplayerSpawner", "spawn", SPAWN_HASH)

        private const val GET_SPAWN_PATH_HASH = 4075236667L
        @JvmField
        val getSpawnPathBind =
            ObjectCalls.getMethodBind("MultiplayerSpawner", "get_spawn_path", GET_SPAWN_PATH_HASH)

        private const val SET_SPAWN_PATH_HASH = 1348162250L
        @JvmField
        val setSpawnPathBind =
            ObjectCalls.getMethodBind("MultiplayerSpawner", "set_spawn_path", SET_SPAWN_PATH_HASH)

        private const val GET_SPAWN_LIMIT_HASH = 3905245786L
        @JvmField
        val getSpawnLimitBind =
            ObjectCalls.getMethodBind("MultiplayerSpawner", "get_spawn_limit", GET_SPAWN_LIMIT_HASH)

        private const val SET_SPAWN_LIMIT_HASH = 1286410249L
        @JvmField
        val setSpawnLimitBind =
            ObjectCalls.getMethodBind("MultiplayerSpawner", "set_spawn_limit", SET_SPAWN_LIMIT_HASH)

        private const val GET_SPAWN_FUNCTION_HASH = 1307783378L
        @JvmField
        val getSpawnFunctionBind =
            ObjectCalls.getMethodBind("MultiplayerSpawner", "get_spawn_function", GET_SPAWN_FUNCTION_HASH)

        private const val SET_SPAWN_FUNCTION_HASH = 1611583062L
        @JvmField
        val setSpawnFunctionBind =
            ObjectCalls.getMethodBind("MultiplayerSpawner", "set_spawn_function", SET_SPAWN_FUNCTION_HASH)
    }
}
