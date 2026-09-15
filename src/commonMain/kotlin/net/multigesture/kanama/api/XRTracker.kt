package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A tracked object.
 *
 * Generated from Godot docs: XRTracker
 */
open class XRTracker(handle: GodotHandle) : RefCounted(handle) {
    var type: Long
        @JvmName("typeProperty")
        get() = getTrackerType()
        @JvmName("setTypeProperty")
        set(value) = setTrackerType(value)

    var name: String
        @JvmName("nameProperty")
        get() = getTrackerName()
        @JvmName("setNameProperty")
        set(value) = setTrackerName(value)

    var description: String
        @JvmName("descriptionProperty")
        get() = getTrackerDesc()
        @JvmName("setDescriptionProperty")
        set(value) = setTrackerDesc(value)

    /**
     * The type of tracker.
     *
     * Generated from Godot docs: XRTracker.get_tracker_type
     */
    fun getTrackerType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getTrackerTypeBind, segment)
    }

    /**
     * The type of tracker.
     *
     * Generated from Godot docs: XRTracker.set_tracker_type
     */
    fun setTrackerType(type: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setTrackerTypeBind, segment, type)
    }

    /**
     * The unique name of this tracker. The trackers that are available differ between various XR
     * runtimes and can often be configured by the user. Godot maintains a number of reserved names
     * that it expects the `XRInterface` to implement if applicable: - `"head"` identifies the
     * `XRPositionalTracker` of the player's head - `"left_hand"` identifies the `XRControllerTracker`
     * in the player's left hand - `"right_hand"` identifies the `XRControllerTracker` in the player's
     * right hand - `"/user/hand_tracker/left"` identifies the `XRHandTracker` for the player's left
     * hand - `"/user/hand_tracker/right"` identifies the `XRHandTracker` for the player's right hand -
     * `"/user/body_tracker"` identifies the `XRBodyTracker` for the player's body -
     * `"/user/face_tracker"` identifies the `XRFaceTracker` for the player's face
     *
     * Generated from Godot docs: XRTracker.get_tracker_name
     */
    fun getTrackerName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringName(getTrackerNameBind, segment)
    }

    /**
     * The unique name of this tracker. The trackers that are available differ between various XR
     * runtimes and can often be configured by the user. Godot maintains a number of reserved names
     * that it expects the `XRInterface` to implement if applicable: - `"head"` identifies the
     * `XRPositionalTracker` of the player's head - `"left_hand"` identifies the `XRControllerTracker`
     * in the player's left hand - `"right_hand"` identifies the `XRControllerTracker` in the player's
     * right hand - `"/user/hand_tracker/left"` identifies the `XRHandTracker` for the player's left
     * hand - `"/user/hand_tracker/right"` identifies the `XRHandTracker` for the player's right hand -
     * `"/user/body_tracker"` identifies the `XRBodyTracker` for the player's body -
     * `"/user/face_tracker"` identifies the `XRFaceTracker` for the player's face
     *
     * Generated from Godot docs: XRTracker.set_tracker_name
     */
    fun setTrackerName(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameArg(setTrackerNameBind, segment, name)
    }

    /**
     * The description of this tracker.
     *
     * Generated from Godot docs: XRTracker.get_tracker_desc
     */
    fun getTrackerDesc(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getTrackerDescBind, segment)
    }

    /**
     * The description of this tracker.
     *
     * Generated from Godot docs: XRTracker.set_tracker_desc
     */
    fun setTrackerDesc(description: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setTrackerDescBind, segment, description)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRTracker? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): XRTracker? =
            if (handle.address() == 0L) null else XRTracker(GodotHandle(handle))

        private const val GET_TRACKER_TYPE_HASH = 2784508102L
        private val getTrackerTypeBind by lazy {
            ObjectCalls.getMethodBind("XRTracker", "get_tracker_type", GET_TRACKER_TYPE_HASH)
        }

        private const val SET_TRACKER_TYPE_HASH = 3055763575L
        private val setTrackerTypeBind by lazy {
            ObjectCalls.getMethodBind("XRTracker", "set_tracker_type", SET_TRACKER_TYPE_HASH)
        }

        private const val GET_TRACKER_NAME_HASH = 2002593661L
        private val getTrackerNameBind by lazy {
            ObjectCalls.getMethodBind("XRTracker", "get_tracker_name", GET_TRACKER_NAME_HASH)
        }

        private const val SET_TRACKER_NAME_HASH = 3304788590L
        private val setTrackerNameBind by lazy {
            ObjectCalls.getMethodBind("XRTracker", "set_tracker_name", SET_TRACKER_NAME_HASH)
        }

        private const val GET_TRACKER_DESC_HASH = 201670096L
        private val getTrackerDescBind by lazy {
            ObjectCalls.getMethodBind("XRTracker", "get_tracker_desc", GET_TRACKER_DESC_HASH)
        }

        private const val SET_TRACKER_DESC_HASH = 83702148L
        private val setTrackerDescBind by lazy {
            ObjectCalls.getMethodBind("XRTracker", "set_tracker_desc", SET_TRACKER_DESC_HASH)
        }
    }
}
