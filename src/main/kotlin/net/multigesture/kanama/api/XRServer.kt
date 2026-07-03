package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D

/**
 * Server for AR and VR features.
 *
 * Generated from Godot docs: XRServer
 */
object XRServer {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("XRServer")
    }

    const val TRACKER_HEAD: Long = 1L
    const val TRACKER_CONTROLLER: Long = 2L
    const val TRACKER_BASESTATION: Long = 4L
    const val TRACKER_ANCHOR: Long = 8L
    const val TRACKER_HAND: Long = 16L
    const val TRACKER_BODY: Long = 32L
    const val TRACKER_FACE: Long = 64L
    const val TRACKER_ANY_KNOWN: Long = 127L
    const val TRACKER_UNKNOWN: Long = 128L
    const val TRACKER_ANY: Long = 255L
    const val RESET_FULL_ROTATION: Long = 0L
    const val RESET_BUT_KEEP_TILT: Long = 1L
    const val DONT_RESET_ROTATION: Long = 2L

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
    fun centerOnHmd(rotationMode: Long, keepHeight: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(centerOnHmdBind, singleton, rotationMode, keepHeight)
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
        ObjectCalls.ptrcallWithObjectArgs(addInterfaceBind, singleton, listOf(interfaceValue?.requireOpenHandle() ?: MemorySegment.NULL))
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
        ObjectCalls.ptrcallWithObjectArgs(removeInterfaceBind, singleton, listOf(interfaceValue?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Returns the interface registered at the given `idx` index in the list of interfaces.
     *
     * Generated from Godot docs: XRServer.get_interface
     */
    @JvmStatic
    fun getInterface(idx: Int): XRInterface? {
        return XRInterface.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getInterfaceBind, singleton, idx))
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
        return XRInterface.wrap(ObjectCalls.ptrcallWithStringArgRetObject(findInterfaceBind, singleton, name))
    }

    /**
     * Registers a new `XRTracker` that tracks a physical object.
     *
     * Generated from Godot docs: XRServer.add_tracker
     */
    @JvmStatic
    fun addTracker(tracker: XRTracker?) {
        ObjectCalls.ptrcallWithObjectArgs(addTrackerBind, singleton, listOf(tracker?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes this `tracker`.
     *
     * Generated from Godot docs: XRServer.remove_tracker
     */
    @JvmStatic
    fun removeTracker(tracker: XRTracker?) {
        ObjectCalls.ptrcallWithObjectArgs(removeTrackerBind, singleton, listOf(tracker?.requireOpenHandle() ?: MemorySegment.NULL))
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
        return XRTracker.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(getTrackerBind, singleton, trackerName))
    }

    /**
     * The primary `XRInterface` currently bound to the `XRServer`.
     *
     * Generated from Godot docs: XRServer.get_primary_interface
     */
    @JvmStatic
    fun getPrimaryInterface(): XRInterface? {
        return XRInterface.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPrimaryInterfaceBind, singleton))
    }

    /**
     * The primary `XRInterface` currently bound to the `XRServer`.
     *
     * Generated from Godot docs: XRServer.set_primary_interface
     */
    @JvmStatic
    fun setPrimaryInterface(interfaceValue: XRInterface?) {
        ObjectCalls.ptrcallWithObjectArgs(setPrimaryInterfaceBind, singleton, listOf(interfaceValue?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    object Signals {
        const val referenceFrameChanged: String = "reference_frame_changed"
        const val interfaceAdded: String = "interface_added"
        const val interfaceRemoved: String = "interface_removed"
        const val trackerAdded: String = "tracker_added"
        const val trackerUpdated: String = "tracker_updated"
        const val trackerRemoved: String = "tracker_removed"
        const val worldOriginChanged: String = "world_origin_changed"
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): XRServer? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): XRServer? =
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
