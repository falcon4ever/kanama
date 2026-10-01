package net.multigesture.kanama.api

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
        ObjectCalls.ptrcallWithStringArg(addSpawnableSceneBind, segment, path)
    }

    fun getSpawnableSceneCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSpawnableSceneCountBind, segment)
    }

    fun getSpawnableScene(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getSpawnableSceneBind, segment, index)
    }

    fun clearSpawnableScenes() {
        ObjectCalls.ptrcallNoArgs(clearSpawnableScenesBind, segment)
    }

    fun spawn(data: Any? = null): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithVariantArgRetObject(spawnBind, segment, data))
    }

    fun getSpawnPath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getSpawnPathBind, segment)
    }

    fun setSpawnPath(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setSpawnPathBind, segment, path)
    }

    fun getSpawnLimit(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getSpawnLimitBind, segment)
    }

    fun setSpawnLimit(limit: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setSpawnLimitBind, segment, limit)
    }

    fun getSpawnFunction(): GodotCallable? {
        return ObjectCalls.ptrcallNoArgsRetCallable(getSpawnFunctionBind, segment)
    }

    fun setSpawnFunction(spawnFunction: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(setSpawnFunctionBind, segment, spawnFunction.target.segment, spawnFunction.method)
    }

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

        private const val ADD_SPAWNABLE_SCENE_HASH = 83702148L
        private val addSpawnableSceneBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSpawner", "add_spawnable_scene", ADD_SPAWNABLE_SCENE_HASH)
        }

        private const val GET_SPAWNABLE_SCENE_COUNT_HASH = 3905245786L
        private val getSpawnableSceneCountBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSpawner", "get_spawnable_scene_count", GET_SPAWNABLE_SCENE_COUNT_HASH)
        }

        private const val GET_SPAWNABLE_SCENE_HASH = 844755477L
        private val getSpawnableSceneBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSpawner", "get_spawnable_scene", GET_SPAWNABLE_SCENE_HASH)
        }

        private const val CLEAR_SPAWNABLE_SCENES_HASH = 3218959716L
        private val clearSpawnableScenesBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSpawner", "clear_spawnable_scenes", CLEAR_SPAWNABLE_SCENES_HASH)
        }

        private const val SPAWN_HASH = 1991184589L
        private val spawnBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSpawner", "spawn", SPAWN_HASH)
        }

        private const val GET_SPAWN_PATH_HASH = 4075236667L
        private val getSpawnPathBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSpawner", "get_spawn_path", GET_SPAWN_PATH_HASH)
        }

        private const val SET_SPAWN_PATH_HASH = 1348162250L
        private val setSpawnPathBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSpawner", "set_spawn_path", SET_SPAWN_PATH_HASH)
        }

        private const val GET_SPAWN_LIMIT_HASH = 3905245786L
        private val getSpawnLimitBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSpawner", "get_spawn_limit", GET_SPAWN_LIMIT_HASH)
        }

        private const val SET_SPAWN_LIMIT_HASH = 1286410249L
        private val setSpawnLimitBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSpawner", "set_spawn_limit", SET_SPAWN_LIMIT_HASH)
        }

        private const val GET_SPAWN_FUNCTION_HASH = 1307783378L
        private val getSpawnFunctionBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSpawner", "get_spawn_function", GET_SPAWN_FUNCTION_HASH)
        }

        private const val SET_SPAWN_FUNCTION_HASH = 1611583062L
        private val setSpawnFunctionBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerSpawner", "set_spawn_function", SET_SPAWN_FUNCTION_HASH)
        }
    }
}
