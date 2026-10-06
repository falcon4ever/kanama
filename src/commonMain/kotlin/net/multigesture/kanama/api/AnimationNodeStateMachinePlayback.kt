package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Provides playback control for an `AnimationNodeStateMachine`.
 *
 * Generated from Godot docs: AnimationNodeStateMachinePlayback
 */
class AnimationNodeStateMachinePlayback(handle: GodotHandle) : Resource(handle) {
    /**
     * Transitions from the current state to another one, following the shortest path. If the path does
     * not connect from the current state, the animation will play after the state teleports. If
     * `reset_on_teleport` is `true`, the animation is played from the beginning when the travel cause
     * a teleportation.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.travel
     */
    fun travel(toNode: String, resetOnTeleport: Boolean = true) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndBoolArg(Binds.travelBind, segment, toNode, resetOnTeleport)
    }

    /**
     * Starts playing the given animation. If `reset` is `true`, the animation is played from the
     * beginning.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.start
     */
    fun start(node: String, reset: Boolean = true) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndBoolArg(Binds.startBind, segment, node, reset)
    }

    /**
     * If there is a next path by travel or auto advance, immediately transitions from the current
     * state to the next state.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.next
     */
    fun next() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.nextBind, segment)
    }

    /**
     * Stops the currently playing animation.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.stop
     */
    fun stop() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.stopBind, segment)
    }

    /**
     * Returns `true` if an animation is playing.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.is_playing
     */
    fun isPlaying(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPlayingBind, segment)
    }

    /**
     * Returns the currently playing animation state. Note: When using a cross-fade, the current state
     * changes to the next state immediately after the cross-fade begins.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.get_current_node
     */
    fun getCurrentNode(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getCurrentNodeBind, segment)
    }

    /**
     * Returns the playback position within the current animation state.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.get_current_play_position
     */
    fun getCurrentPlayPosition(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCurrentPlayPositionBind, segment)
    }

    /**
     * Returns the current state length. Note: It is possible that any `AnimationRootNode` can be nodes
     * as well as animations. This means that there can be multiple animations within a single state.
     * Which animation length has priority depends on the nodes connected inside it. Also, if a
     * transition does not reset, the remaining length at that point will be returned.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.get_current_length
     */
    fun getCurrentLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCurrentLengthBind, segment)
    }

    /**
     * Returns the starting state of currently fading animation.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.get_fading_from_node
     */
    fun getFadingFromNode(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getFadingFromNodeBind, segment)
    }

    /**
     * Returns the playback position of the node from `get_fading_from_node`. Returns `0` if no
     * animation fade is occurring.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.get_fading_from_play_position
     */
    fun getFadingFromPlayPosition(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFadingFromPlayPositionBind, segment)
    }

    /**
     * Returns the playback state length of the node from `get_fading_from_node`. Returns `0` if no
     * animation fade is occurring.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.get_fading_from_length
     */
    fun getFadingFromLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFadingFromLengthBind, segment)
    }

    /**
     * Returns the playback position of the current fade animation. Returns `0` if no animation fade is
     * occurring.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.get_fading_position
     */
    fun getFadingPosition(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFadingPositionBind, segment)
    }

    /**
     * Returns the length of the current fade animation. Returns `0` if no animation fade is occurring.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.get_fading_length
     */
    fun getFadingLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFadingLengthBind, segment)
    }

    /**
     * Returns the current travel path as computed internally by the A* algorithm.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.get_travel_path
     */
    fun getTravelPath(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringNameList(Binds.getTravelPathBind, segment)
    }

    /** Signal `state_started(state: StringName)`; see [TypedSignal]. */
    val stateStarted: Signal1<String>
        @JvmName("stateStartedTypedSignal")
        get() = Signal1(this, "state_started", SignalArgType.STRING)

    /** Signal `state_finished(state: StringName)`; see [TypedSignal]. */
    val stateFinished: Signal1<String>
        @JvmName("stateFinishedTypedSignal")
        get() = Signal1(this, "state_finished", SignalArgType.STRING)

    object Signals {
        const val stateStarted: String = "state_started"
        const val stateFinished: String = "state_finished"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeStateMachinePlayback? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AnimationNodeStateMachinePlayback? =
            if (handle.address() == 0L) null else RefCounted.owned(AnimationNodeStateMachinePlayback(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AnimationNodeStateMachinePlayback? =
            if (handle.address() == 0L) null else AnimationNodeStateMachinePlayback(GodotHandle(handle))
    }

    private object Binds {
        private const val TRAVEL_HASH = 3823612587L
        @JvmField
        val travelBind =
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "travel", TRAVEL_HASH)

        private const val START_HASH = 3823612587L
        @JvmField
        val startBind =
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "start", START_HASH)

        private const val NEXT_HASH = 3218959716L
        @JvmField
        val nextBind =
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "next", NEXT_HASH)

        private const val STOP_HASH = 3218959716L
        @JvmField
        val stopBind =
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "stop", STOP_HASH)

        private const val IS_PLAYING_HASH = 36873697L
        @JvmField
        val isPlayingBind =
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "is_playing", IS_PLAYING_HASH)

        private const val GET_CURRENT_NODE_HASH = 2002593661L
        @JvmField
        val getCurrentNodeBind =
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_current_node", GET_CURRENT_NODE_HASH)

        private const val GET_CURRENT_PLAY_POSITION_HASH = 1740695150L
        @JvmField
        val getCurrentPlayPositionBind =
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_current_play_position", GET_CURRENT_PLAY_POSITION_HASH)

        private const val GET_CURRENT_LENGTH_HASH = 1740695150L
        @JvmField
        val getCurrentLengthBind =
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_current_length", GET_CURRENT_LENGTH_HASH)

        private const val GET_FADING_FROM_NODE_HASH = 2002593661L
        @JvmField
        val getFadingFromNodeBind =
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_fading_from_node", GET_FADING_FROM_NODE_HASH)

        private const val GET_FADING_FROM_PLAY_POSITION_HASH = 1740695150L
        @JvmField
        val getFadingFromPlayPositionBind =
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_fading_from_play_position", GET_FADING_FROM_PLAY_POSITION_HASH)

        private const val GET_FADING_FROM_LENGTH_HASH = 1740695150L
        @JvmField
        val getFadingFromLengthBind =
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_fading_from_length", GET_FADING_FROM_LENGTH_HASH)

        private const val GET_FADING_POSITION_HASH = 1740695150L
        @JvmField
        val getFadingPositionBind =
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_fading_position", GET_FADING_POSITION_HASH)

        private const val GET_FADING_LENGTH_HASH = 1740695150L
        @JvmField
        val getFadingLengthBind =
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_fading_length", GET_FADING_LENGTH_HASH)

        private const val GET_TRAVEL_PATH_HASH = 3995934104L
        @JvmField
        val getTravelPathBind =
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_travel_path", GET_TRAVEL_PATH_HASH)
    }
}
