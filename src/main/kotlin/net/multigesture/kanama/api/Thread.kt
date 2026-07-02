package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A unit of execution in a process.
 *
 * Generated from Godot docs: Thread
 */
class Thread(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Starts a new `Thread` that calls `callable`. If the method takes some arguments, you can pass
     * them using `Callable.bind`. The `priority` of the `Thread` can be changed by passing a value
     * from the `Priority` enum. Returns `OK` on success, or `ERR_CANT_CREATE` on failure.
     *
     * Generated from Godot docs: Thread.start
     */
    fun start(callable: GodotCallable, priority: Long = 1L): Long {
        return ObjectCalls.ptrcallWithCallableLongArgsRetLong(startBind, handle, callable.target.handle, callable.method, priority)
    }

    fun getId(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getIdBind, handle)
    }

    fun isStarted(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isStartedBind, handle)
    }

    fun isAlive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAliveBind, handle)
    }

    fun waitToFinish(): Any? {
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(waitToFinishBind, handle)
    }

    companion object {
        fun setThreadSafetyChecksEnabled(enabled: Boolean) {
            ObjectCalls.ptrcallWithBoolArg(setThreadSafetyChecksEnabledBind, MemorySegment.NULL, enabled)
        }

        fun isMainThread(): Boolean {
            return ObjectCalls.ptrcallNoArgsRetBool(isMainThreadBind, MemorySegment.NULL)
        }

        const val PRIORITY_LOW: Long = 0L
        const val PRIORITY_NORMAL: Long = 1L
        const val PRIORITY_HIGH: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Thread? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Thread? =
            if (handle.address() == 0L) null else Thread(handle)

        private const val START_HASH = 1327203254L
        private val startBind by lazy {
            ObjectCalls.getMethodBind("Thread", "start", START_HASH)
        }

        private const val GET_ID_HASH = 201670096L
        private val getIdBind by lazy {
            ObjectCalls.getMethodBind("Thread", "get_id", GET_ID_HASH)
        }

        private const val IS_STARTED_HASH = 36873697L
        private val isStartedBind by lazy {
            ObjectCalls.getMethodBind("Thread", "is_started", IS_STARTED_HASH)
        }

        private const val IS_ALIVE_HASH = 36873697L
        private val isAliveBind by lazy {
            ObjectCalls.getMethodBind("Thread", "is_alive", IS_ALIVE_HASH)
        }

        private const val WAIT_TO_FINISH_HASH = 1460262497L
        private val waitToFinishBind by lazy {
            ObjectCalls.getMethodBind("Thread", "wait_to_finish", WAIT_TO_FINISH_HASH)
        }

        private const val SET_THREAD_SAFETY_CHECKS_ENABLED_HASH = 2586408642L
        private val setThreadSafetyChecksEnabledBind by lazy {
            ObjectCalls.getMethodBind("Thread", "set_thread_safety_checks_enabled", SET_THREAD_SAFETY_CHECKS_ENABLED_HASH)
        }

        private const val IS_MAIN_THREAD_HASH = 2240911060L
        private val isMainThreadBind by lazy {
            ObjectCalls.getMethodBind("Thread", "is_main_thread", IS_MAIN_THREAD_HASH)
        }
    }
}
