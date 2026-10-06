package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: CSGPolygon3D
 */
class CSGPolygon3D(handle: GodotHandle) : CSGPrimitive3D(handle) {
    var polygon: List<Vector2>
        @JvmName("polygonProperty")
        get() = getPolygon()
        @JvmName("setPolygonProperty")
        set(value) = setPolygon(value)

    var mode: CSGPolygon3D.Mode
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

    var pathIntervalType: CSGPolygon3D.PathIntervalType
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

    var pathRotation: CSGPolygon3D.PathRotation
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
        ObjectCalls.ptrcallWithPackedVector2ListArg(Binds.setPolygonBind, segment, polygon)
    }

    fun getPolygon(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(Binds.getPolygonBind, segment)
    }

    fun setMode(mode: CSGPolygon3D.Mode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setModeBind, segment, mode.value)
    }

    fun getMode(): CSGPolygon3D.Mode {
        return CSGPolygon3D.Mode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getModeBind, segment))
    }

    fun setDepth(depth: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDepthBind, segment, depth)
    }

    fun getDepth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDepthBind, segment)
    }

    fun setSpinDegrees(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSpinDegreesBind, segment, degrees)
    }

    fun getSpinDegrees(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSpinDegreesBind, segment)
    }

    fun setSpinSides(spinSides: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSpinSidesBind, segment, spinSides)
    }

    fun getSpinSides(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSpinSidesBind, segment)
    }

    fun setPathNode(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(Binds.setPathNodeBind, segment, path)
    }

    fun getPathNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getPathNodeBind, segment)
    }

    fun setPathIntervalType(intervalType: CSGPolygon3D.PathIntervalType) {
        ObjectCalls.ptrcallWithLongArg(Binds.setPathIntervalTypeBind, segment, intervalType.value)
    }

    fun getPathIntervalType(): CSGPolygon3D.PathIntervalType {
        return CSGPolygon3D.PathIntervalType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPathIntervalTypeBind, segment))
    }

    fun setPathInterval(interval: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPathIntervalBind, segment, interval)
    }

    fun getPathInterval(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPathIntervalBind, segment)
    }

    fun setPathSimplifyAngle(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPathSimplifyAngleBind, segment, degrees)
    }

    fun getPathSimplifyAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPathSimplifyAngleBind, segment)
    }

    fun setPathRotation(pathRotation: CSGPolygon3D.PathRotation) {
        ObjectCalls.ptrcallWithLongArg(Binds.setPathRotationBind, segment, pathRotation.value)
    }

    fun getPathRotation(): CSGPolygon3D.PathRotation {
        return CSGPolygon3D.PathRotation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPathRotationBind, segment))
    }

    fun setPathRotationAccurate(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setPathRotationAccurateBind, segment, enable)
    }

    fun getPathRotationAccurate(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getPathRotationAccurateBind, segment)
    }

    fun setPathLocal(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setPathLocalBind, segment, enable)
    }

    fun isPathLocal(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPathLocalBind, segment)
    }

    fun setPathContinuousU(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setPathContinuousUBind, segment, enable)
    }

    fun isPathContinuousU(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPathContinuousUBind, segment)
    }

    fun setPathUDistance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPathUDistanceBind, segment, distance)
    }

    fun getPathUDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPathUDistanceBind, segment)
    }

    fun setPathJoined(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setPathJoinedBind, segment, enable)
    }

    fun isPathJoined(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPathJoinedBind, segment)
    }

    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getMaterial(): Material? {
        return Material.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMaterialBind, segment))
    }

    fun setSmoothFaces(smoothFaces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSmoothFacesBind, segment, smoothFaces)
    }

    fun getSmoothFaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getSmoothFacesBind, segment)
    }

    @JvmInline
    value class Mode(override val value: Long) : GodotEnumValue {
        companion object {
            val DEPTH: Mode get() = Mode(0L)
            val SPIN: Mode get() = Mode(1L)
            val PATH: Mode get() = Mode(2L)
        }
    }

    @JvmInline
    value class PathRotation(override val value: Long) : GodotEnumValue {
        companion object {
            val POLYGON: PathRotation get() = PathRotation(0L)
            val PATH: PathRotation get() = PathRotation(1L)
            val PATH_FOLLOW: PathRotation get() = PathRotation(2L)
        }
    }

    @JvmInline
    value class PathIntervalType(override val value: Long) : GodotEnumValue {
        companion object {
            val DISTANCE: PathIntervalType get() = PathIntervalType(0L)
            val SUBDIVIDE: PathIntervalType get() = PathIntervalType(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CSGPolygon3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CSGPolygon3D? =
            if (handle.address() == 0L) null else CSGPolygon3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_POLYGON_HASH = 1509147220L
        @JvmField
        val setPolygonBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_polygon", SET_POLYGON_HASH)

        private const val GET_POLYGON_HASH = 2961356807L
        @JvmField
        val getPolygonBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_polygon", GET_POLYGON_HASH)

        private const val SET_MODE_HASH = 3158377035L
        @JvmField
        val setModeBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_mode", SET_MODE_HASH)

        private const val GET_MODE_HASH = 1201612222L
        @JvmField
        val getModeBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_mode", GET_MODE_HASH)

        private const val SET_DEPTH_HASH = 373806689L
        @JvmField
        val setDepthBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_depth", SET_DEPTH_HASH)

        private const val GET_DEPTH_HASH = 1740695150L
        @JvmField
        val getDepthBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_depth", GET_DEPTH_HASH)

        private const val SET_SPIN_DEGREES_HASH = 373806689L
        @JvmField
        val setSpinDegreesBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_spin_degrees", SET_SPIN_DEGREES_HASH)

        private const val GET_SPIN_DEGREES_HASH = 1740695150L
        @JvmField
        val getSpinDegreesBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_spin_degrees", GET_SPIN_DEGREES_HASH)

        private const val SET_SPIN_SIDES_HASH = 1286410249L
        @JvmField
        val setSpinSidesBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_spin_sides", SET_SPIN_SIDES_HASH)

        private const val GET_SPIN_SIDES_HASH = 3905245786L
        @JvmField
        val getSpinSidesBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_spin_sides", GET_SPIN_SIDES_HASH)

        private const val SET_PATH_NODE_HASH = 1348162250L
        @JvmField
        val setPathNodeBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_node", SET_PATH_NODE_HASH)

        private const val GET_PATH_NODE_HASH = 4075236667L
        @JvmField
        val getPathNodeBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_path_node", GET_PATH_NODE_HASH)

        private const val SET_PATH_INTERVAL_TYPE_HASH = 3744240707L
        @JvmField
        val setPathIntervalTypeBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_interval_type", SET_PATH_INTERVAL_TYPE_HASH)

        private const val GET_PATH_INTERVAL_TYPE_HASH = 3434618397L
        @JvmField
        val getPathIntervalTypeBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_path_interval_type", GET_PATH_INTERVAL_TYPE_HASH)

        private const val SET_PATH_INTERVAL_HASH = 373806689L
        @JvmField
        val setPathIntervalBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_interval", SET_PATH_INTERVAL_HASH)

        private const val GET_PATH_INTERVAL_HASH = 1740695150L
        @JvmField
        val getPathIntervalBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_path_interval", GET_PATH_INTERVAL_HASH)

        private const val SET_PATH_SIMPLIFY_ANGLE_HASH = 373806689L
        @JvmField
        val setPathSimplifyAngleBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_simplify_angle", SET_PATH_SIMPLIFY_ANGLE_HASH)

        private const val GET_PATH_SIMPLIFY_ANGLE_HASH = 1740695150L
        @JvmField
        val getPathSimplifyAngleBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_path_simplify_angle", GET_PATH_SIMPLIFY_ANGLE_HASH)

        private const val SET_PATH_ROTATION_HASH = 1412947288L
        @JvmField
        val setPathRotationBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_rotation", SET_PATH_ROTATION_HASH)

        private const val GET_PATH_ROTATION_HASH = 647219346L
        @JvmField
        val getPathRotationBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_path_rotation", GET_PATH_ROTATION_HASH)

        private const val SET_PATH_ROTATION_ACCURATE_HASH = 2586408642L
        @JvmField
        val setPathRotationAccurateBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_rotation_accurate", SET_PATH_ROTATION_ACCURATE_HASH)

        private const val GET_PATH_ROTATION_ACCURATE_HASH = 36873697L
        @JvmField
        val getPathRotationAccurateBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_path_rotation_accurate", GET_PATH_ROTATION_ACCURATE_HASH)

        private const val SET_PATH_LOCAL_HASH = 2586408642L
        @JvmField
        val setPathLocalBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_local", SET_PATH_LOCAL_HASH)

        private const val IS_PATH_LOCAL_HASH = 36873697L
        @JvmField
        val isPathLocalBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "is_path_local", IS_PATH_LOCAL_HASH)

        private const val SET_PATH_CONTINUOUS_U_HASH = 2586408642L
        @JvmField
        val setPathContinuousUBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_continuous_u", SET_PATH_CONTINUOUS_U_HASH)

        private const val IS_PATH_CONTINUOUS_U_HASH = 36873697L
        @JvmField
        val isPathContinuousUBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "is_path_continuous_u", IS_PATH_CONTINUOUS_U_HASH)

        private const val SET_PATH_U_DISTANCE_HASH = 373806689L
        @JvmField
        val setPathUDistanceBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_u_distance", SET_PATH_U_DISTANCE_HASH)

        private const val GET_PATH_U_DISTANCE_HASH = 1740695150L
        @JvmField
        val getPathUDistanceBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_path_u_distance", GET_PATH_U_DISTANCE_HASH)

        private const val SET_PATH_JOINED_HASH = 2586408642L
        @JvmField
        val setPathJoinedBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_path_joined", SET_PATH_JOINED_HASH)

        private const val IS_PATH_JOINED_HASH = 36873697L
        @JvmField
        val isPathJoinedBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "is_path_joined", IS_PATH_JOINED_HASH)

        private const val SET_MATERIAL_HASH = 2757459619L
        @JvmField
        val setMaterialBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_material", SET_MATERIAL_HASH)

        private const val GET_MATERIAL_HASH = 5934680L
        @JvmField
        val getMaterialBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_material", GET_MATERIAL_HASH)

        private const val SET_SMOOTH_FACES_HASH = 2586408642L
        @JvmField
        val setSmoothFacesBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "set_smooth_faces", SET_SMOOTH_FACES_HASH)

        private const val GET_SMOOTH_FACES_HASH = 36873697L
        @JvmField
        val getSmoothFacesBind =
            ObjectCalls.getMethodBind("CSGPolygon3D", "get_smooth_faces", GET_SMOOTH_FACES_HASH)
    }
}
