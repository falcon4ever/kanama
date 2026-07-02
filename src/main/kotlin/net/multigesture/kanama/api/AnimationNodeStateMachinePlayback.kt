package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Provides playback control for an `AnimationNodeStateMachine`.
 *
 * Generated from Godot docs: AnimationNodeStateMachinePlayback
 */
class AnimationNodeStateMachinePlayback(handle: MemorySegment) : Resource(handle) {
    /**
     * Transitions from the current state to another one, following the shortest path. If the path does
     * not connect from the current state, the animation will play after the state teleports. If
     * `reset_on_teleport` is `true`, the animation is played from the beginning when the travel cause
     * a teleportation.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.travel
     */
    fun travel(toNode: String, resetOnTeleport: Boolean = true) {
        ObjectCalls.ptrcallWithStringNameAndBoolArg(travelBind, handle, toNode, resetOnTeleport)
    }

    /**
     * Starts playing the given animation. If `reset` is `true`, the animation is played from the
     * beginning.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.start
     */
    fun start(node: String, reset: Boolean = true) {
        ObjectCalls.ptrcallWithStringNameAndBoolArg(startBind, handle, node, reset)
    }

    /**
     * If there is a next path by travel or auto advance, immediately transitions from the current
     * state to the next state.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.next
     */
    fun next() {
        ObjectCalls.ptrcallNoArgs(nextBind, handle)
    }

    /**
     * Stops the currently playing animation.
     *
     * Generated from Godot docs: AnimationNodeStateMachinePlayback.stop
     */
    fun stop() {
        ObjectCalls.ptrcallNoArgs(stopBind, handle)
    }

    fun isPlaying(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPlayingBind, handle)
    }

    fun getCurrentNode(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getCurrentNodeBind, handle)
    }

    fun getCurrentPlayPosition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCurrentPlayPositionBind, handle)
    }

    fun getCurrentLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCurrentLengthBind, handle)
    }

    fun getFadingFromNode(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getFadingFromNodeBind, handle)
    }

    fun getFadingFromPlayPosition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFadingFromPlayPositionBind, handle)
    }

    fun getFadingFromLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFadingFromLengthBind, handle)
    }

    fun getFadingPosition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFadingPositionBind, handle)
    }

    fun getFadingLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFadingLengthBind, handle)
    }

    fun getTravelPath(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(getTravelPathBind, handle)
    }

    object Signals {
        const val stateStarted: String = "state_started"
        const val stateFinished: String = "state_finished"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimationNodeStateMachinePlayback? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationNodeStateMachinePlayback? =
            if (handle.address() == 0L) null else AnimationNodeStateMachinePlayback(handle)

        private const val TRAVEL_HASH = 3823612587L
        private val travelBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "travel", TRAVEL_HASH)
        }

        private const val START_HASH = 3823612587L
        private val startBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "start", START_HASH)
        }

        private const val NEXT_HASH = 3218959716L
        private val nextBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "next", NEXT_HASH)
        }

        private const val STOP_HASH = 3218959716L
        private val stopBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "stop", STOP_HASH)
        }

        private const val IS_PLAYING_HASH = 36873697L
        private val isPlayingBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "is_playing", IS_PLAYING_HASH)
        }

        private const val GET_CURRENT_NODE_HASH = 2002593661L
        private val getCurrentNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_current_node", GET_CURRENT_NODE_HASH)
        }

        private const val GET_CURRENT_PLAY_POSITION_HASH = 1740695150L
        private val getCurrentPlayPositionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_current_play_position", GET_CURRENT_PLAY_POSITION_HASH)
        }

        private const val GET_CURRENT_LENGTH_HASH = 1740695150L
        private val getCurrentLengthBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_current_length", GET_CURRENT_LENGTH_HASH)
        }

        private const val GET_FADING_FROM_NODE_HASH = 2002593661L
        private val getFadingFromNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_fading_from_node", GET_FADING_FROM_NODE_HASH)
        }

        private const val GET_FADING_FROM_PLAY_POSITION_HASH = 1740695150L
        private val getFadingFromPlayPositionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_fading_from_play_position", GET_FADING_FROM_PLAY_POSITION_HASH)
        }

        private const val GET_FADING_FROM_LENGTH_HASH = 1740695150L
        private val getFadingFromLengthBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_fading_from_length", GET_FADING_FROM_LENGTH_HASH)
        }

        private const val GET_FADING_POSITION_HASH = 1740695150L
        private val getFadingPositionBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_fading_position", GET_FADING_POSITION_HASH)
        }

        private const val GET_FADING_LENGTH_HASH = 1740695150L
        private val getFadingLengthBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_fading_length", GET_FADING_LENGTH_HASH)
        }

        private const val GET_TRAVEL_PATH_HASH = 3995934104L
        private val getTravelPathBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeStateMachinePlayback", "get_travel_path", GET_TRAVEL_PATH_HASH)
        }
    }
}
