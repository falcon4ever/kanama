package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: CSGPolygon3D
 */
class CSGPolygon3D(handle: MemorySegment) : CSGPrimitive3D(handle) {
    var polygon: List<Vector2>
        @JvmName("polygonProperty")
        get() = getPolygon()
        @JvmName("setPolygonProperty")
        set(value) = setPolygon(value)

    var mode: Long
        @JvmName("modeProperty")
        get() = getMode()
        @JvmName("setModeProperty")
        set(value) = setMode(value)

    var depth: Double
        @JvmName("depthProperty")
        get() = getDepth()
        @JvmName("setDepthProperty")
        set(value) = setDepth(value)

    var spinDegrees: Double
        @JvmName("spinDegreesProperty")
        get() = getSpinDegrees()
        @JvmName("setSpinDegreesProperty")
        set(value) = setSpinDegrees(value)

    var spinSides: Int
        @JvmName("spinSidesProperty")
        get() = getSpinSides()
        @JvmName("setSpinSidesProperty")
        set(value) = setSpinSides(value)

    var pathNode: NodePath
        @JvmName("pathNodeProperty")
        get() = getPathNode()
        @JvmName("setPathNodeProperty")
        set(value) = setPathNode(value)

    var pathIntervalType: Long
        @JvmName("pathIntervalTypeProperty")
        get() = getPathIntervalType()
        @JvmName("setPathIntervalTypeProperty")
        set(value) = setPathIntervalType(value)

    var pathInterval: Double
        @JvmName("pathIntervalProperty")
        get() = getPathInterval()
        @JvmName("setPathIntervalProperty")
        set(value) = setPathInterval(value)

    var pathSimplifyAngle: Double
        @JvmName("pathSimplifyAngleProperty")
        get() = getPathSimplifyAngle()
        @JvmName("setPathSimplifyAngleProperty")
        set(value) = setPathSimplifyAngle(value)

    var pathRotation: Long
        @JvmName("pathRotationProperty")
        get() = getPathRotation()
        @JvmName("setPathRotationProperty")
        set(value) = setPathRotation(value)

    var pathRotationAccurate: Boolean
        @JvmName("pathRotationAccurateProperty")
        get() = getPathRotationAccurate()
        @JvmName("setPathRotationAccurateProperty")
        set(value) = setPathRotationAccurate(value)

    var pathLocal: Boolean
        @JvmName("pathLocalProperty")
        get() = isPathLocal()
        @JvmName("setPathLocalProperty")
        set(value) = setPathLocal(value)

    var pathContinuousU: Boolean
        @JvmName("pathContinuousUProperty")
        get() = isPathContinuousU()
        @JvmName("setPathContinuousUProperty")
        set(value) = setPathContinuousU(value)

    var pathUDistance: Double
        @JvmName("pathUDistanceProperty")
        get() = getPathUDistance()
        @JvmName("setPathUDistanceProperty")
        set(value) = setPathUDistance(value)

    var pathJoined: Boolean
        @JvmName("pathJoinedProperty")
        get() = isPathJoined()
        @JvmName("setPathJoinedProperty")
        set(value) = setPathJoined(value)

    var smoothFaces: Boolean
        @JvmName("smoothFacesProperty")
        get() = getSmoothFaces()
        @JvmName("setSmoothFacesProperty")
        set(value) = setSmoothFaces(value)

    var material: Material?
        @JvmName("materialProperty")
        get() = getMaterial()
        @JvmName("setMaterialProperty")
        set(value) = setMaterial(value)

    fun setPolygon(polygon: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setPolygonBind, handle, polygon)
    }

    fun getPolygon(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getPolygonBind, handle)
    }

    fun setMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setModeBind, handle, mode)
    }

    fun getMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getModeBind, handle)
    }

    fun setDepth(depth: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDepthBind, handle, depth)
    }

    fun getDepth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthBind, handle)
    }

    fun setSpinDegrees(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpinDegreesBind, handle, degrees)
    }

    fun getSpinDegrees(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpinDegreesBind, handle)
    }

    fun setSpinSides(spinSides: Int) {
        ObjectCalls.ptrcallWithIntArg(setSpinSidesBind, handle, spinSides)
    }

    fun getSpinSides(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSpinSidesBind, handle)
    }

    fun setPathNode(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setPathNodeBind, handle, path)
    }

    fun getPathNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getPathNodeBind, handle)
    }

    fun setPathIntervalType(intervalType: Long) {
        ObjectCalls.ptrcallWithLongArg(setPathIntervalTypeBind, handle, intervalType)
    }

    fun getPathIntervalType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPathIntervalTypeBind, handle)
    }

    fun setPathInterval(interval: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPathIntervalBind, handle, interval)
    }

    fun getPathInterval(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathIntervalBind, handle)
    }

    fun setPathSimplifyAngle(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPathSimplifyAngleBind, handle, degrees)
    }

    fun getPathSimplifyAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathSimplifyAngleBind, handle)
    }

    fun setPathRotation(pathRotation: Long) {
        ObjectCalls.ptrcallWithLongArg(setPathRotationBind, handle, pathRotation)
    }

    fun getPathRotation(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPathRotationBind, handle)
    }

    fun setPathRotationAccurate(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPathRotationAccurateBind, handle, enable)
    }

    fun getPathRotationAccurate(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getPathRotationAccurateBind, handle)
    }

    fun setPathLocal(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPathLocalBind, handle, enable)
    }

    fun isPathLocal(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPathLocalBind, handle)
    }

    fun setPathContinuousU(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPathContinuousUBind, handle, enable)
    }

    fun isPathContinuousU(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPathContinuousUBind, handle)
    }

    fun setPathUDistance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPathUDistanceBind, handle, distance)
    }

    fun getPathUDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathUDistanceBind, handle)
    }

    fun setPathJoined(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPathJoinedBind, handle, enable)
    }

    fun isPathJoined(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPathJoinedBind, handle)
    }

    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getMaterial(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMaterialBind, handle))
    }

    fun setSmoothFaces(smoothFaces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSmoothFacesBind, handle, smoothFaces)
    }

    fun getSmoothFaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSmoothFacesBind, handle)
    }

    companion object {
        const val MODE_DEPTH: Long = 0L
        const val MODE_SPIN: Long = 1L
        const val MODE_PATH: Long = 2L
        const val PATH_ROTATION_POLYGON: Long = 0L
        const val PATH_ROTATION_PATH: Long = 1L
        const val PATH_ROTATION_PATH_FOLLOW: Long = 2L
        const val PATH_INTERVAL_DISTANCE: Long = 0L
        const val PATH_INTERVAL_SUBDIVIDE: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): CSGPolygon3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CSGPolygon3D? =
            if (handle.address() == 0L) null else CSGPolygon3D(handle)

        private const val SET_POLYGON_HASH = 1509147220L
        private val setPolygonBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_polygon", SET_POLYGON_HASH)
        }

        private const val GET_POLYGON_HASH = 2961356807L
        private val getPolygonBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_polygon", GET_POLYGON_HASH)
        }

        private const val SET_MODE_HASH = 3158377035L
        private val setModeBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_mode", SET_MODE_HASH)
        }

        private const val GET_MODE_HASH = 1201612222L
        private val getModeBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_mode", GET_MODE_HASH)
        }

        private const val SET_DEPTH_HASH = 373806689L
        private val setDepthBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_depth", SET_DEPTH_HASH)
        }

        private const val GET_DEPTH_HASH = 1740695150L
        private val getDepthBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_depth", GET_DEPTH_HASH)
        }

        private const val SET_SPIN_DEGREES_HASH = 373806689L
        private val setSpinDegreesBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_spin_degrees", SET_SPIN_DEGREES_HASH)
        }

        private const val GET_SPIN_DEGREES_HASH = 1740695150L
        private val getSpinDegreesBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_spin_degrees", GET_SPIN_DEGREES_HASH)
        }

        private const val SET_SPIN_SIDES_HASH = 1286410249L
        private val setSpinSidesBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_spin_sides", SET_SPIN_SIDES_HASH)
        }

        private const val GET_SPIN_SIDES_HASH = 3905245786L
        private val getSpinSidesBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_spin_sides", GET_SPIN_SIDES_HASH)
        }

        private const val SET_PATH_NODE_HASH = 1348162250L
        private val setPathNodeBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_node", SET_PATH_NODE_HASH)
        }

        private const val GET_PATH_NODE_HASH = 4075236667L
        private val getPathNodeBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_path_node", GET_PATH_NODE_HASH)
        }

        private const val SET_PATH_INTERVAL_TYPE_HASH = 3744240707L
        private val setPathIntervalTypeBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_interval_type", SET_PATH_INTERVAL_TYPE_HASH)
        }

        private const val GET_PATH_INTERVAL_TYPE_HASH = 3434618397L
        private val getPathIntervalTypeBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_path_interval_type", GET_PATH_INTERVAL_TYPE_HASH)
        }

        private const val SET_PATH_INTERVAL_HASH = 373806689L
        private val setPathIntervalBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_interval", SET_PATH_INTERVAL_HASH)
        }

        private const val GET_PATH_INTERVAL_HASH = 1740695150L
        private val getPathIntervalBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_path_interval", GET_PATH_INTERVAL_HASH)
        }

        private const val SET_PATH_SIMPLIFY_ANGLE_HASH = 373806689L
        private val setPathSimplifyAngleBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_simplify_angle", SET_PATH_SIMPLIFY_ANGLE_HASH)
        }

        private const val GET_PATH_SIMPLIFY_ANGLE_HASH = 1740695150L
        private val getPathSimplifyAngleBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_path_simplify_angle", GET_PATH_SIMPLIFY_ANGLE_HASH)
        }

        private const val SET_PATH_ROTATION_HASH = 1412947288L
        private val setPathRotationBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_rotation", SET_PATH_ROTATION_HASH)
        }

        private const val GET_PATH_ROTATION_HASH = 647219346L
        private val getPathRotationBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_path_rotation", GET_PATH_ROTATION_HASH)
        }

        private const val SET_PATH_ROTATION_ACCURATE_HASH = 2586408642L
        private val setPathRotationAccurateBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_rotation_accurate", SET_PATH_ROTATION_ACCURATE_HASH)
        }

        private const val GET_PATH_ROTATION_ACCURATE_HASH = 36873697L
        private val getPathRotationAccurateBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_path_rotation_accurate", GET_PATH_ROTATION_ACCURATE_HASH)
        }

        private const val SET_PATH_LOCAL_HASH = 2586408642L
        private val setPathLocalBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_local", SET_PATH_LOCAL_HASH)
        }

        private const val IS_PATH_LOCAL_HASH = 36873697L
        private val isPathLocalBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "is_path_local", IS_PATH_LOCAL_HASH)
        }

        private const val SET_PATH_CONTINUOUS_U_HASH = 2586408642L
        private val setPathContinuousUBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_continuous_u", SET_PATH_CONTINUOUS_U_HASH)
        }

        private const val IS_PATH_CONTINUOUS_U_HASH = 36873697L
        private val isPathContinuousUBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "is_path_continuous_u", IS_PATH_CONTINUOUS_U_HASH)
        }

        private const val SET_PATH_U_DISTANCE_HASH = 373806689L
        private val setPathUDistanceBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_u_distance", SET_PATH_U_DISTANCE_HASH)
        }

        private const val GET_PATH_U_DISTANCE_HASH = 1740695150L
        private val getPathUDistanceBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_path_u_distance", GET_PATH_U_DISTANCE_HASH)
        }

        private const val SET_PATH_JOINED_HASH = 2586408642L
        private val setPathJoinedBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_joined", SET_PATH_JOINED_HASH)
        }

        private const val IS_PATH_JOINED_HASH = 36873697L
        private val isPathJoinedBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "is_path_joined", IS_PATH_JOINED_HASH)
        }

        private const val SET_MATERIAL_HASH = 2757459619L
        private val setMaterialBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_material", SET_MATERIAL_HASH)
        }

        private const val GET_MATERIAL_HASH = 5934680L
        private val getMaterialBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_material", GET_MATERIAL_HASH)
        }

        private const val SET_SMOOTH_FACES_HASH = 2586408642L
        private val setSmoothFacesBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_smooth_faces", SET_SMOOTH_FACES_HASH)
        }

        private const val GET_SMOOTH_FACES_HASH = 36873697L
        private val getSmoothFacesBind by lazy {
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_smooth_faces", GET_SMOOTH_FACES_HASH)
        }
    }
}
