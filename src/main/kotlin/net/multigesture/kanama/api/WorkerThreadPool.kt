package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A singleton that allocates some `Thread`s on startup, used to offload tasks to these threads.
 *
 * Generated from Godot docs: WorkerThreadPool
 */
object WorkerThreadPool {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("WorkerThreadPool")
    }

    /**
     * Adds `action` as a task to be executed by a worker thread. `high_priority` determines if the
     * task has a high priority or a low priority (default). You can optionally provide a `description`
     * to help with debugging. Returns a task ID that can be used by other methods. Warning: Every task
     * must be waited for completion using `wait_for_task_completion` or
     * `wait_for_group_task_completion` at some point so that any allocated resources inside the task
     * can be cleaned up.
     *
     * Generated from Godot docs: WorkerThreadPool.add_task
     */
    @JvmStatic
    fun addTask(action: GodotCallable, highPriority: Boolean = false, description: String = ""): Long {
        return ObjectCalls.ptrcallWithCallableBoolStringArgsRetLong(addTaskBind, singleton, action.target.handle, action.method, highPriority, description)
    }

    /**
     * Returns `true` if the task with the given ID is completed. Note: You should only call this
     * method between adding the task and awaiting its completion.
     *
     * Generated from Godot docs: WorkerThreadPool.is_task_completed
     */
    @JvmStatic
    fun isTaskCompleted(taskId: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isTaskCompletedBind, singleton, taskId)
    }

    /**
     * Pauses the thread that calls this method until the task with the given ID is completed. Returns
     * `@GlobalScope.OK` if the task could be successfully awaited. Returns
     * `@GlobalScope.ERR_INVALID_PARAMETER` if a task with the passed ID does not exist (maybe because
     * it was already awaited and disposed of). Returns `@GlobalScope.ERR_BUSY` if the call is made
     * from another running task and, due to task scheduling, there's potential for deadlocking (e.g.,
     * the task to await may be at a lower level in the call stack and therefore can't progress). This
     * is an advanced situation that should only matter when some tasks depend on others (in the
     * current implementation, the tricky case is a task trying to wait on an older one).
     *
     * Generated from Godot docs: WorkerThreadPool.wait_for_task_completion
     */
    @JvmStatic
    fun waitForTaskCompletion(taskId: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(waitForTaskCompletionBind, singleton, taskId)
    }

    /**
     * Returns the task ID of the current thread calling this method, or `-1` if the task is a group
     * task, invalid or the current thread is not part of the thread pool (e.g. the main thread). Can
     * be used by a task to get its own task ID, or to determine whether the current code is running
     * inside the worker thread pool. Note: Group tasks have their own IDs, so this method will return
     * `-1` for group tasks.
     *
     * Generated from Godot docs: WorkerThreadPool.get_caller_task_id
     */
    @JvmStatic
    fun getCallerTaskId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCallerTaskIdBind, singleton)
    }

    /**
     * Adds `action` as a group task to be executed by the worker threads. The `Callable` will be
     * called a number of times based on `elements`, with the first thread calling it with the value
     * `0` as a parameter, and each consecutive execution incrementing this value by 1 until it reaches
     * `element - 1`. The number of threads the task is distributed to is defined by `tasks_needed`,
     * where the default value `-1` means it is distributed to all worker threads. `high_priority`
     * determines if the task has a high priority or a low priority (default). You can optionally
     * provide a `description` to help with debugging. Returns a group task ID that can be used by
     * other methods. Warning: Every task must be waited for completion using
     * `wait_for_task_completion` or `wait_for_group_task_completion` at some point so that any
     * allocated resources inside the task can be cleaned up.
     *
     * Generated from Godot docs: WorkerThreadPool.add_group_task
     */
    @JvmStatic
    fun addGroupTask(action: GodotCallable, elements: Int, tasksNeeded: Int = -1, highPriority: Boolean = false, description: String = ""): Long {
        return ObjectCalls.ptrcallWithCallableTwoIntBoolStringArgsRetLong(addGroupTaskBind, singleton, action.target.handle, action.method, elements, tasksNeeded, highPriority, description)
    }

    /**
     * Returns `true` if the group task with the given ID is completed. Note: You should only call this
     * method between adding the group task and awaiting its completion.
     *
     * Generated from Godot docs: WorkerThreadPool.is_group_task_completed
     */
    @JvmStatic
    fun isGroupTaskCompleted(groupId: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isGroupTaskCompletedBind, singleton, groupId)
    }

    /**
     * Returns how many times the `Callable` of the group task with the given ID has already been
     * executed by the worker threads. Note: If a thread has started executing the `Callable` but is
     * yet to finish, it won't be counted.
     *
     * Generated from Godot docs: WorkerThreadPool.get_group_processed_element_count
     */
    @JvmStatic
    fun getGroupProcessedElementCount(groupId: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetUInt32(getGroupProcessedElementCountBind, singleton, groupId)
    }

    /**
     * Pauses the thread that calls this method until the group task with the given ID is completed.
     *
     * Generated from Godot docs: WorkerThreadPool.wait_for_group_task_completion
     */
    @JvmStatic
    fun waitForGroupTaskCompletion(groupId: Long) {
        ObjectCalls.ptrcallWithLongArg(waitForGroupTaskCompletionBind, singleton, groupId)
    }

    /**
     * Returns the task group ID of the current thread calling this method, or `-1` if invalid or the
     * current thread is not part of a task group.
     *
     * Generated from Godot docs: WorkerThreadPool.get_caller_group_id
     */
    @JvmStatic
    fun getCallerGroupId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCallerGroupIdBind, singleton)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): WorkerThreadPool? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): WorkerThreadPool? =
        if (handle.address() == 0L) null else this

    private const val ADD_TASK_HASH = 3745067146L
    private val addTaskBind by lazy {
        ObjectCalls.getMethodBind("WorkerThreadPool", "add_task", ADD_TASK_HASH)
    }

    private const val IS_TASK_COMPLETED_HASH = 1116898809L
    private val isTaskCompletedBind by lazy {
        ObjectCalls.getMethodBind("WorkerThreadPool", "is_task_completed", IS_TASK_COMPLETED_HASH)
    }

    private const val WAIT_FOR_TASK_COMPLETION_HASH = 844576869L
    private val waitForTaskCompletionBind by lazy {
        ObjectCalls.getMethodBind("WorkerThreadPool", "wait_for_task_completion", WAIT_FOR_TASK_COMPLETION_HASH)
    }

    private const val GET_CALLER_TASK_ID_HASH = 3905245786L
    private val getCallerTaskIdBind by lazy {
        ObjectCalls.getMethodBind("WorkerThreadPool", "get_caller_task_id", GET_CALLER_TASK_ID_HASH)
    }

    private const val ADD_GROUP_TASK_HASH = 1801953219L
    private val addGroupTaskBind by lazy {
        ObjectCalls.getMethodBind("WorkerThreadPool", "add_group_task", ADD_GROUP_TASK_HASH)
    }

    private const val IS_GROUP_TASK_COMPLETED_HASH = 1116898809L
    private val isGroupTaskCompletedBind by lazy {
        ObjectCalls.getMethodBind("WorkerThreadPool", "is_group_task_completed", IS_GROUP_TASK_COMPLETED_HASH)
    }

    private const val GET_GROUP_PROCESSED_ELEMENT_COUNT_HASH = 923996154L
    private val getGroupProcessedElementCountBind by lazy {
        ObjectCalls.getMethodBind("WorkerThreadPool", "get_group_processed_element_count", GET_GROUP_PROCESSED_ELEMENT_COUNT_HASH)
    }

    private const val WAIT_FOR_GROUP_TASK_COMPLETION_HASH = 1286410249L
    private val waitForGroupTaskCompletionBind by lazy {
        ObjectCalls.getMethodBind("WorkerThreadPool", "wait_for_group_task_completion", WAIT_FOR_GROUP_TASK_COMPLETION_HASH)
    }

    private const val GET_CALLER_GROUP_ID_HASH = 3905245786L
    private val getCallerGroupIdBind by lazy {
        ObjectCalls.getMethodBind("WorkerThreadPool", "get_caller_group_id", GET_CALLER_GROUP_ID_HASH)
    }
}
