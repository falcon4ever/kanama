package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D

/**
 * Server for AR and VR features.
 *
 * Generated from Godot docs: XRServer
 */
object XRServer {
    private val singleton: RawSegment by lazy {
        ObjectCalls.getSingleton("XRServer")
    }

    var worldScale: Double
        @JvmName("worldScaleProperty")
        get() = getWorldScale()
        @JvmName("setWorldScaleProperty")
        set(value) = setWorldScale(value)

    var worldOrigin: Transform3D
        @JvmName("worldOriginProperty")
        get() = getWorldOrigin()
        @JvmName("setWorldOriginProperty")
        set(value) = setWorldOrigin(value)

    var cameraLockedToOrigin: Boolean
        @JvmName("cameraLockedToOriginProperty")
        get() = isCameraLockedToOrigin()
        @JvmName("setCameraLockedToOriginProperty")
        set(value) = setCameraLockedToOrigin(value)

    var primaryInterface: XRInterface?
        @JvmName("primaryInterfaceProperty")
        get() = getPrimaryInterface()
        @JvmName("setPrimaryInterfaceProperty")
        set(value) = setPrimaryInterface(value)

    /**
     * The scale of the game world compared to the real world. By default, most AR/VR platforms assume
     * that 1 game unit corresponds to 1 real world meter.
     *
     * Generated from Godot docs: XRServer.get_world_scale
     */
    @JvmStatic
    fun getWorldScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWorldScaleBind, singleton)
    }

    /**
     * The scale of the game world compared to the real world. By default, most AR/VR platforms assume
     * that 1 game unit corresponds to 1 real world meter.
     *
     * Generated from Godot docs: XRServer.set_world_scale
     */
    @JvmStatic
    fun setWorldScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWorldScaleBind, singleton, scale)
    }

    /**
     * The current origin of our tracking space in the virtual world. This is used by the renderer to
     * properly position the camera with new tracking data. Note: This property is managed by the
     * current `XROrigin3D` node. It is exposed for access from GDExtensions.
     *
     * Generated from Godot docs: XRServer.get_world_origin
     */
    @JvmStatic
    fun getWorldOrigin(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getWorldOriginBind, singleton)
    }

    /**
     * The current origin of our tracking space in the virtual world. This is used by the renderer to
     * properly position the camera with new tracking data. Note: This property is managed by the
     * current `XROrigin3D` node. It is exposed for access from GDExtensions.
     *
     * Generated from Godot docs: XRServer.set_world_origin
     */
    @JvmStatic
    fun setWorldOrigin(worldOrigin: Transform3D) {
        ObjectCalls.ptrcallWithTransform3DArg(setWorldOriginBind, singleton, worldOrigin)
    }

    /**
     * Returns the reference frame transform. Mostly used internally and exposed for GDExtension build
     * interfaces.
     *
     * Generated from Godot docs: XRServer.get_reference_frame
     */
    @JvmStatic
    fun getReferenceFrame(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getReferenceFrameBind, singleton)
    }

    /**
     * Clears the reference frame that was set by previous calls to `center_on_hmd`.
     *
     * Generated from Godot docs: XRServer.clear_reference_frame
     */
    @JvmStatic
    fun clearReferenceFrame() {
        ObjectCalls.ptrcallNoArgs(clearReferenceFrameBind, singleton)
    }

    /**
     * This is an important function to understand correctly. AR and VR platforms all handle
     * positioning slightly differently. For platforms that do not offer spatial tracking, our origin
     * point `(0, 0, 0)` is the location of our HMD, but you have little control over the direction the
     * player is facing in the real world. For platforms that do offer spatial tracking, our origin
     * point depends very much on the system. For OpenVR, our origin point is usually the center of the
     * tracking space, on the ground. For other platforms, it's often the location of the tracking
     * camera. This method allows you to center your tracker on the location of the HMD. It will take
     * the current location of the HMD and use that to adjust all your tracking data; in essence,
     * realigning the real world to your player's current position in the game world. For this method
     * to produce usable results, tracking information must be available. This often takes a few frames
     * after starting your game. You should call this method after a few seconds have passed. For
     * example, when the user requests a realignment of the display holding a designated button on a
     * controller for a short period of time, or when implementing a teleport mechanism.
     *
     * Generated from Godot docs: XRServer.center_on_hmd
     */
    @JvmStatic
    fun centerOnHmd(rotationMode: XRServer.RotationMode, keepHeight: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(centerOnHmdBind, singleton, rotationMode.value, keepHeight)
    }

    /**
     * Returns the primary interface's transformation.
     *
     * Generated from Godot docs: XRServer.get_hmd_transform
     */
    @JvmStatic
    fun getHmdTransform(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getHmdTransformBind, singleton)
    }

    /**
     * If set to `true`, the scene will be rendered as if the camera is locked to the `XROrigin3D`.
     * Note: This doesn't provide a very comfortable experience for users. This setting exists for
     * doing benchmarking or automated testing, where you want to control what is rendered via code.
     *
     * Generated from Godot docs: XRServer.set_camera_locked_to_origin
     */
    @JvmStatic
    fun setCameraLockedToOrigin(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCameraLockedToOriginBind, singleton, enabled)
    }

    /**
     * If set to `true`, the scene will be rendered as if the camera is locked to the `XROrigin3D`.
     * Note: This doesn't provide a very comfortable experience for users. This setting exists for
     * doing benchmarking or automated testing, where you want to control what is rendered via code.
     *
     * Generated from Godot docs: XRServer.is_camera_locked_to_origin
     */
    @JvmStatic
    fun isCameraLockedToOrigin(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCameraLockedToOriginBind, singleton)
    }

    /**
     * Registers an `XRInterface` object.
     *
     * Generated from Godot docs: XRServer.add_interface
     */
    @JvmStatic
    fun addInterface(interfaceValue: XRInterface?) {
        ObjectCalls.ptrcallWithObjectArgs(addInterfaceBind, singleton, listOf(interfaceValue?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Returns the number of interfaces currently registered with the AR/VR server. If your project
     * supports multiple AR/VR platforms, you can look through the available interface, and either
     * present the user with a selection or simply try to initialize each interface and use the first
     * one that returns `true`.
     *
     * Generated from Godot docs: XRServer.get_interface_count
     */
    @JvmStatic
    fun getInterfaceCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInterfaceCountBind, singleton)
    }

    /**
     * Removes this `interface`.
     *
     * Generated from Godot docs: XRServer.remove_interface
     */
    @JvmStatic
    fun removeInterface(interfaceValue: XRInterface?) {
        ObjectCalls.ptrcallWithObjectArgs(removeInterfaceBind, singleton, listOf(interfaceValue?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Returns the interface registered at the given `idx` index in the list of interfaces.
     *
     * Generated from Godot docs: XRServer.get_interface
     */
    @JvmStatic
    fun getInterface(idx: Int): XRInterface? {
        return XRInterface.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(getInterfaceBind, singleton, idx))
    }

    /**
     * Returns a list of available interfaces the ID and name of each interface.
     *
     * Generated from Godot docs: XRServer.get_interfaces
     */
    @JvmStatic
    fun getInterfaces(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(getInterfacesBind, singleton)
    }

    /**
     * Finds an interface by its `name`. For example, if your project uses capabilities of an AR/VR
     * platform, you can find the interface for that platform by name and initialize it.
     *
     * Generated from Godot docs: XRServer.find_interface
     */
    @JvmStatic
    fun findInterface(name: String): XRInterface? {
        return XRInterface.wrapOwned(ObjectCalls.ptrcallWithStringArgRetObject(findInterfaceBind, singleton, name))
    }

    /**
     * Registers a new `XRTracker` that tracks a physical object.
     *
     * Generated from Godot docs: XRServer.add_tracker
     */
    @JvmStatic
    fun addTracker(tracker: XRTracker?) {
        ObjectCalls.ptrcallWithObjectArgs(addTrackerBind, singleton, listOf(tracker?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Removes this `tracker`.
     *
     * Generated from Godot docs: XRServer.remove_tracker
     */
    @JvmStatic
    fun removeTracker(tracker: XRTracker?) {
        ObjectCalls.ptrcallWithObjectArgs(removeTrackerBind, singleton, listOf(tracker?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Returns a dictionary of trackers for `tracker_types`.
     *
     * Generated from Godot docs: XRServer.get_trackers
     */
    @JvmStatic
    fun getTrackers(trackerTypes: Int): Map<String, Any?> {
        return ObjectCalls.ptrcallWithIntArgRetDictionary(getTrackersBind, singleton, trackerTypes)
    }

    /**
     * Returns the positional tracker with the given `tracker_name`.
     *
     * Generated from Godot docs: XRServer.get_tracker
     */
    @JvmStatic
    fun getTracker(trackerName: String): XRTracker? {
        return XRTracker.wrapOwned(ObjectCalls.ptrcallWithStringNameArgRetObject(getTrackerBind, singleton, trackerName))
    }

    /**
     * The primary `XRInterface` currently bound to the `XRServer`.
     *
     * Generated from Godot docs: XRServer.get_primary_interface
     */
    @JvmStatic
    fun getPrimaryInterface(): XRInterface? {
        return XRInterface.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(getPrimaryInterfaceBind, singleton))
    }

    /**
     * The primary `XRInterface` currently bound to the `XRServer`.
     *
     * Generated from Godot docs: XRServer.set_primary_interface
     */
    @JvmStatic
    fun setPrimaryInterface(interfaceValue: XRInterface?) {
        ObjectCalls.ptrcallWithObjectArgs(setPrimaryInterfaceBind, singleton, listOf(interfaceValue?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /** Signal `reference_frame_changed()`; see [TypedSignal]. */
    val referenceFrameChanged: Signal0
        @JvmName("referenceFrameChangedTypedSignal")
        get() = Signal0(GodotObject(GodotHandle(singleton)), "reference_frame_changed")

    /** Signal `interface_added(interface_name: StringName)`; see [TypedSignal]. */
    val interfaceAdded: Signal1<String>
        @JvmName("interfaceAddedTypedSignal")
        get() = Signal1(GodotObject(GodotHandle(singleton)), "interface_added", SignalArgType.STRING)

    /** Signal `interface_removed(interface_name: StringName)`; see [TypedSignal]. */
    val interfaceRemoved: Signal1<String>
        @JvmName("interfaceRemovedTypedSignal")
        get() = Signal1(GodotObject(GodotHandle(singleton)), "interface_removed", SignalArgType.STRING)

    /** Signal `tracker_added(tracker_name: StringName, type: int)`; see [TypedSignal]. */
    val trackerAdded: Signal2<String, Long>
        @JvmName("trackerAddedTypedSignal")
        get() = Signal2(GodotObject(GodotHandle(singleton)), "tracker_added", SignalArgType.STRING, SignalArgType.LONG)

    /** Signal `tracker_updated(tracker_name: StringName, type: int)`; see [TypedSignal]. */
    val trackerUpdated: Signal2<String, Long>
        @JvmName("trackerUpdatedTypedSignal")
        get() = Signal2(GodotObject(GodotHandle(singleton)), "tracker_updated", SignalArgType.STRING, SignalArgType.LONG)

    /** Signal `tracker_removed(tracker_name: StringName, type: int)`; see [TypedSignal]. */
    val trackerRemoved: Signal2<String, Long>
        @JvmName("trackerRemovedTypedSignal")
        get() = Signal2(GodotObject(GodotHandle(singleton)), "tracker_removed", SignalArgType.STRING, SignalArgType.LONG)

    /** Signal `world_origin_changed()`; see [TypedSignal]. */
    val worldOriginChanged: Signal0
        @JvmName("worldOriginChangedTypedSignal")
        get() = Signal0(GodotObject(GodotHandle(singleton)), "world_origin_changed")

    object Signals {
        const val referenceFrameChanged: String = "reference_frame_changed"
        const val interfaceAdded: String = "interface_added"
        const val interfaceRemoved: String = "interface_removed"
        const val trackerAdded: String = "tracker_added"
        const val trackerUpdated: String = "tracker_updated"
        const val trackerRemoved: String = "tracker_removed"
        const val worldOriginChanged: String = "world_origin_changed"
    }

    /**
     * Godot's `XRServer.TrackerType` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`XRServer.TrackerType.<NAME>`).
     *
     * Generated from Godot docs: XRServer.TrackerType
     */
    @JvmInline
    value class TrackerType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The tracker tracks the location of the player's head. This is usually a location centered
             * between the player's eyes. Note that for handheld AR devices this can be the current location of
             * the device.
             *
             * Generated from Godot docs: XRServer.TRACKER_HEAD
             */
            val HEAD: TrackerType get() = TrackerType(1L)
            /**
             * The tracker tracks the location of a controller.
             *
             * Generated from Godot docs: XRServer.TRACKER_CONTROLLER
             */
            val CONTROLLER: TrackerType get() = TrackerType(2L)
            /**
             * The tracker tracks the location of a base station.
             *
             * Generated from Godot docs: XRServer.TRACKER_BASESTATION
             */
            val BASESTATION: TrackerType get() = TrackerType(4L)
            /**
             * The tracker tracks the location and size of an AR anchor.
             *
             * Generated from Godot docs: XRServer.TRACKER_ANCHOR
             */
            val ANCHOR: TrackerType get() = TrackerType(8L)
            /**
             * The tracker tracks the location and joints of a hand.
             *
             * Generated from Godot docs: XRServer.TRACKER_HAND
             */
            val HAND: TrackerType get() = TrackerType(16L)
            /**
             * The tracker tracks the location and joints of a body.
             *
             * Generated from Godot docs: XRServer.TRACKER_BODY
             */
            val BODY: TrackerType get() = TrackerType(32L)
            /**
             * The tracker tracks the expressions of a face.
             *
             * Generated from Godot docs: XRServer.TRACKER_FACE
             */
            val FACE: TrackerType get() = TrackerType(64L)
            /**
             * Used internally to filter trackers of any known type.
             *
             * Generated from Godot docs: XRServer.TRACKER_ANY_KNOWN
             */
            val ANY_KNOWN: TrackerType get() = TrackerType(127L)
            /**
             * Used internally if we haven't set the tracker type yet.
             *
             * Generated from Godot docs: XRServer.TRACKER_UNKNOWN
             */
            val UNKNOWN: TrackerType get() = TrackerType(128L)
            /**
             * Used internally to select all trackers.
             *
             * Generated from Godot docs: XRServer.TRACKER_ANY
             */
            val ANY: TrackerType get() = TrackerType(255L)
        }
    }

    /**
     * Godot's `XRServer.RotationMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`XRServer.RotationMode.<NAME>`).
     *
     * Generated from Godot docs: XRServer.RotationMode
     */
    @JvmInline
    value class RotationMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Fully reset the orientation of the HMD. Regardless of what direction the user is looking to in
             * the real world. The user will look dead ahead in the virtual world.
             *
             * Generated from Godot docs: XRServer.RESET_FULL_ROTATION
             */
            val RESET_FULL_ROTATION: RotationMode get() = RotationMode(0L)
            /**
             * Resets the orientation but keeps the tilt of the device. So if we're looking down, we keep
             * looking down but heading will be reset.
             *
             * Generated from Godot docs: XRServer.RESET_BUT_KEEP_TILT
             */
            val RESET_BUT_KEEP_TILT: RotationMode get() = RotationMode(1L)
            /**
             * Does not reset the orientation of the HMD, only the position of the player gets centered.
             *
             * Generated from Godot docs: XRServer.DONT_RESET_ROTATION
             */
            val DONT_RESET_ROTATION: RotationMode get() = RotationMode(2L)
        }
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): XRServer? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): XRServer? =
        if (handle.address() == 0L) null else this

    private const val GET_WORLD_SCALE_HASH = 1740695150L
    private val getWorldScaleBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "get_world_scale", GET_WORLD_SCALE_HASH)
    }

    private const val SET_WORLD_SCALE_HASH = 373806689L
    private val setWorldScaleBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "set_world_scale", SET_WORLD_SCALE_HASH)
    }

    private const val GET_WORLD_ORIGIN_HASH = 3229777777L
    private val getWorldOriginBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "get_world_origin", GET_WORLD_ORIGIN_HASH)
    }

    private const val SET_WORLD_ORIGIN_HASH = 2952846383L
    private val setWorldOriginBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "set_world_origin", SET_WORLD_ORIGIN_HASH)
    }

    private const val GET_REFERENCE_FRAME_HASH = 3229777777L
    private val getReferenceFrameBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "get_reference_frame", GET_REFERENCE_FRAME_HASH)
    }

    private const val CLEAR_REFERENCE_FRAME_HASH = 3218959716L
    private val clearReferenceFrameBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "clear_reference_frame", CLEAR_REFERENCE_FRAME_HASH)
    }

    private const val CENTER_ON_HMD_HASH = 1450904707L
    private val centerOnHmdBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "center_on_hmd", CENTER_ON_HMD_HASH)
    }

    private const val GET_HMD_TRANSFORM_HASH = 4183770049L
    private val getHmdTransformBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "get_hmd_transform", GET_HMD_TRANSFORM_HASH)
    }

    private const val SET_CAMERA_LOCKED_TO_ORIGIN_HASH = 2586408642L
    private val setCameraLockedToOriginBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "set_camera_locked_to_origin", SET_CAMERA_LOCKED_TO_ORIGIN_HASH)
    }

    private const val IS_CAMERA_LOCKED_TO_ORIGIN_HASH = 36873697L
    private val isCameraLockedToOriginBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "is_camera_locked_to_origin", IS_CAMERA_LOCKED_TO_ORIGIN_HASH)
    }

    private const val ADD_INTERFACE_HASH = 1898711491L
    private val addInterfaceBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "add_interface", ADD_INTERFACE_HASH)
    }

    private const val GET_INTERFACE_COUNT_HASH = 3905245786L
    private val getInterfaceCountBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "get_interface_count", GET_INTERFACE_COUNT_HASH)
    }

    private const val REMOVE_INTERFACE_HASH = 1898711491L
    private val removeInterfaceBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "remove_interface", REMOVE_INTERFACE_HASH)
    }

    private const val GET_INTERFACE_HASH = 4237347919L
    private val getInterfaceBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "get_interface", GET_INTERFACE_HASH)
    }

    private const val GET_INTERFACES_HASH = 3995934104L
    private val getInterfacesBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "get_interfaces", GET_INTERFACES_HASH)
    }

    private const val FIND_INTERFACE_HASH = 1395192955L
    private val findInterfaceBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "find_interface", FIND_INTERFACE_HASH)
    }

    private const val ADD_TRACKER_HASH = 684804553L
    private val addTrackerBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "add_tracker", ADD_TRACKER_HASH)
    }

    private const val REMOVE_TRACKER_HASH = 684804553L
    private val removeTrackerBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "remove_tracker", REMOVE_TRACKER_HASH)
    }

    private const val GET_TRACKERS_HASH = 3554694381L
    private val getTrackersBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "get_trackers", GET_TRACKERS_HASH)
    }

    private const val GET_TRACKER_HASH = 147382240L
    private val getTrackerBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "get_tracker", GET_TRACKER_HASH)
    }

    private const val GET_PRIMARY_INTERFACE_HASH = 2143545064L
    private val getPrimaryInterfaceBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "get_primary_interface", GET_PRIMARY_INTERFACE_HASH)
    }

    private const val SET_PRIMARY_INTERFACE_HASH = 1898711491L
    private val setPrimaryInterfaceBind by lazy {
        ObjectCalls.getMethodBind("XRServer", "set_primary_interface", SET_PRIMARY_INTERFACE_HASH)
    }
}
