package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A tracked object.
 *
 * Generated from Godot docs: XRTracker
 */
open class XRTracker(handle: GodotHandle) : RefCounted(handle) {
    var type: XRServer.TrackerType
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
    fun getTrackerType(): XRServer.TrackerType {
        checkOpen()
        return XRServer.TrackerType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTrackerTypeBind, segment))
    }

    /**
     * The type of tracker.
     *
     * Generated from Godot docs: XRTracker.set_tracker_type
     */
    fun setTrackerType(type: XRServer.TrackerType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTrackerTypeBind, segment, type.value)
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
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getTrackerNameBind, segment)
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
        ObjectCalls.ptrcallWithStringNameArg(Binds.setTrackerNameBind, segment, name)
    }

    /**
     * The description of this tracker.
     *
     * Generated from Godot docs: XRTracker.get_tracker_desc
     */
    fun getTrackerDesc(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getTrackerDescBind, segment)
    }

    /**
     * The description of this tracker.
     *
     * Generated from Godot docs: XRTracker.set_tracker_desc
     */
    fun setTrackerDesc(description: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setTrackerDescBind, segment, description)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRTracker? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): XRTracker? =
            if (handle.address() == 0L) null else RefCounted.owned(XRTracker(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): XRTracker? =
            if (handle.address() == 0L) null else XRTracker(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_TRACKER_TYPE_HASH = 2784508102L
        @JvmField
        val getTrackerTypeBind =
            ObjectCalls.getMethodBind("XRTracker", "get_tracker_type", GET_TRACKER_TYPE_HASH)

        private const val SET_TRACKER_TYPE_HASH = 3055763575L
        @JvmField
        val setTrackerTypeBind =
            ObjectCalls.getMethodBind("XRTracker", "set_tracker_type", SET_TRACKER_TYPE_HASH)

        private const val GET_TRACKER_NAME_HASH = 2002593661L
        @JvmField
        val getTrackerNameBind =
            ObjectCalls.getMethodBind("XRTracker", "get_tracker_name", GET_TRACKER_NAME_HASH)

        private const val SET_TRACKER_NAME_HASH = 3304788590L
        @JvmField
        val setTrackerNameBind =
            ObjectCalls.getMethodBind("XRTracker", "set_tracker_name", SET_TRACKER_NAME_HASH)

        private const val GET_TRACKER_DESC_HASH = 201670096L
        @JvmField
        val getTrackerDescBind =
            ObjectCalls.getMethodBind("XRTracker", "get_tracker_desc", GET_TRACKER_DESC_HASH)

        private const val SET_TRACKER_DESC_HASH = 83702148L
        @JvmField
        val setTrackerDescBind =
            ObjectCalls.getMethodBind("XRTracker", "set_tracker_desc", SET_TRACKER_DESC_HASH)
    }
}
