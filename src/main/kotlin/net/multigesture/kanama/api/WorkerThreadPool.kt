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

    @JvmStatic
    fun addTask(action: GodotCallable, highPriority: Boolean = false, description: String = ""): Long {
        return ObjectCalls.ptrcallWithCallableBoolStringArgsRetLong(addTaskBind, singleton, action.target.handle, action.method, highPriority, description)
    }

    @JvmStatic
    fun isTaskCompleted(taskId: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isTaskCompletedBind, singleton, taskId)
    }

    @JvmStatic
    fun waitForTaskCompletion(taskId: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(waitForTaskCompletionBind, singleton, taskId)
    }

    @JvmStatic
    fun getCallerTaskId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCallerTaskIdBind, singleton)
    }

    @JvmStatic
    fun addGroupTask(action: GodotCallable, elements: Int, tasksNeeded: Int = -1, highPriority: Boolean = false, description: String = ""): Long {
        return ObjectCalls.ptrcallWithCallableTwoIntBoolStringArgsRetLong(addGroupTaskBind, singleton, action.target.handle, action.method, elements, tasksNeeded, highPriority, description)
    }

    @JvmStatic
    fun isGroupTaskCompleted(groupId: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isGroupTaskCompletedBind, singleton, groupId)
    }

    @JvmStatic
    fun getGroupProcessedElementCount(groupId: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetUInt32(getGroupProcessedElementCountBind, singleton, groupId)
    }

    @JvmStatic
    fun waitForGroupTaskCompletion(groupId: Long) {
        ObjectCalls.ptrcallWithLongArg(waitForGroupTaskCompletionBind, singleton, groupId)
    }

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
