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

    /**
     * Returns the current `Thread`'s ID, uniquely identifying it among all threads. If the `Thread`
     * has not started running or if `wait_to_finish` has been called, this returns an empty string.
     *
     * Generated from Godot docs: Thread.get_id
     */
    fun getId(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getIdBind, handle)
    }

    /**
     * Returns `true` if this `Thread` has been started. Once started, this will return `true` until it
     * is joined using `wait_to_finish`. For checking if a `Thread` is still executing its task, use
     * `is_alive`.
     *
     * Generated from Godot docs: Thread.is_started
     */
    fun isStarted(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isStartedBind, handle)
    }

    /**
     * Returns `true` if this `Thread` is currently running the provided function. This is useful for
     * determining if `wait_to_finish` can be called without blocking the calling thread. To check if a
     * `Thread` is joinable, use `is_started`.
     *
     * Generated from Godot docs: Thread.is_alive
     */
    fun isAlive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAliveBind, handle)
    }

    /**
     * Joins the `Thread` and waits for it to finish. Returns the output of the `Callable` passed to
     * `start`. Should either be used when you want to retrieve the value returned from the method
     * called by the `Thread` or before freeing the instance that contains the `Thread`. To determine
     * if this can be called without blocking the calling thread, check if `is_alive` is `false`.
     *
     * Generated from Godot docs: Thread.wait_to_finish
     */
    fun waitToFinish(): Any? {
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(waitToFinishBind, handle)
    }

    companion object {
        /**
         * Sets whether the thread safety checks the engine normally performs in methods of certain classes
         * (e.g., `Node`) should happen on the current thread. The default, for every thread, is that they
         * are enabled (as if called with `enabled` being `true`). Those checks are conservative. That
         * means that they will only succeed in considering a call thread-safe (and therefore allow it to
         * happen) if the engine can guarantee such safety. Because of that, there may be cases where the
         * user may want to disable them (`enabled` being `false`) to make certain operations allowed
         * again. By doing so, it becomes the user's responsibility to ensure thread safety (e.g., by using
         * `Mutex`) for those objects that are otherwise protected by the engine. Note: This is an advanced
         * usage of the engine. You are advised to use it only if you know what you are doing and there is
         * no safer way. Note: This is useful for scripts running on either arbitrary `Thread` objects or
         * tasks submitted to the `WorkerThreadPool`. It doesn't apply to code running during `Node` group
         * processing, where the checks will be always performed. Note: Even in the case of having disabled
         * the checks in a `WorkerThreadPool` task, there's no need to re-enable them at the end. The
         * engine will do so.
         *
         * Generated from Godot docs: Thread.set_thread_safety_checks_enabled
         */
        fun setThreadSafetyChecksEnabled(enabled: Boolean) {
            ObjectCalls.ptrcallWithBoolArg(setThreadSafetyChecksEnabledBind, MemorySegment.NULL, enabled)
        }

        /**
         * Returns `true` if the thread this method was called from is the main thread. Note: This is a
         * static method and isn't associated with a specific `Thread` object.
         *
         * Generated from Godot docs: Thread.is_main_thread
         */
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
