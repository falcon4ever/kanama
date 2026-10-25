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
 * Extrudes a 2D polygon shape to create a 3D mesh.
 *
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

    /**
     * The point array that defines the 2D polygon that is extruded. This can be a convex or concave
     * polygon with 3 or more points. The polygon must not have any intersecting edges. Otherwise,
     * triangulation will fail and no mesh will be generated. Note: If only 1 or 2 points are defined
     * in `polygon`, no mesh will be generated.
     *
     * Generated from Godot docs: CSGPolygon3D.set_polygon
     */
    fun setPolygon(polygon: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(Binds.setPolygonBind, segment, polygon)
    }

    /**
     * The point array that defines the 2D polygon that is extruded. This can be a convex or concave
     * polygon with 3 or more points. The polygon must not have any intersecting edges. Otherwise,
     * triangulation will fail and no mesh will be generated. Note: If only 1 or 2 points are defined
     * in `polygon`, no mesh will be generated.
     *
     * Generated from Godot docs: CSGPolygon3D.get_polygon
     */
    fun getPolygon(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(Binds.getPolygonBind, segment)
    }

    /**
     * The `mode` used to extrude the `polygon`.
     *
     * Generated from Godot docs: CSGPolygon3D.set_mode
     */
    fun setMode(mode: CSGPolygon3D.Mode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setModeBind, segment, mode.value)
    }

    /**
     * The `mode` used to extrude the `polygon`.
     *
     * Generated from Godot docs: CSGPolygon3D.get_mode
     */
    fun getMode(): CSGPolygon3D.Mode {
        return CSGPolygon3D.Mode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getModeBind, segment))
    }

    /**
     * When `mode` is `Mode.DEPTH`, the depth of the extrusion.
     *
     * Generated from Godot docs: CSGPolygon3D.set_depth
     */
    fun setDepth(depth: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDepthBind, segment, depth)
    }

    /**
     * When `mode` is `Mode.DEPTH`, the depth of the extrusion.
     *
     * Generated from Godot docs: CSGPolygon3D.get_depth
     */
    fun getDepth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDepthBind, segment)
    }

    /**
     * When `mode` is `Mode.SPIN`, the total number of degrees the `polygon` is rotated when extruding.
     *
     * Generated from Godot docs: CSGPolygon3D.set_spin_degrees
     */
    fun setSpinDegrees(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSpinDegreesBind, segment, degrees)
    }

    /**
     * When `mode` is `Mode.SPIN`, the total number of degrees the `polygon` is rotated when extruding.
     *
     * Generated from Godot docs: CSGPolygon3D.get_spin_degrees
     */
    fun getSpinDegrees(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSpinDegreesBind, segment)
    }

    /**
     * When `mode` is `Mode.SPIN`, the number of extrusions made.
     *
     * Generated from Godot docs: CSGPolygon3D.set_spin_sides
     */
    fun setSpinSides(spinSides: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSpinSidesBind, segment, spinSides)
    }

    /**
     * When `mode` is `Mode.SPIN`, the number of extrusions made.
     *
     * Generated from Godot docs: CSGPolygon3D.get_spin_sides
     */
    fun getSpinSides(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSpinSidesBind, segment)
    }

    /**
     * When `mode` is `Mode.PATH`, the location of the `Path3D` object used to extrude the `polygon`.
     *
     * Generated from Godot docs: CSGPolygon3D.set_path_node
     */
    fun setPathNode(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(Binds.setPathNodeBind, segment, path)
    }

    /**
     * When `mode` is `Mode.PATH`, the location of the `Path3D` object used to extrude the `polygon`.
     *
     * Generated from Godot docs: CSGPolygon3D.get_path_node
     */
    fun getPathNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getPathNodeBind, segment)
    }

    /**
     * When `mode` is `Mode.PATH`, this will determine if the interval should be by distance
     * (`PathIntervalType.DISTANCE`) or subdivision fractions (`PathIntervalType.SUBDIVIDE`).
     *
     * Generated from Godot docs: CSGPolygon3D.set_path_interval_type
     */
    fun setPathIntervalType(intervalType: CSGPolygon3D.PathIntervalType) {
        ObjectCalls.ptrcallWithLongArg(Binds.setPathIntervalTypeBind, segment, intervalType.value)
    }

    /**
     * When `mode` is `Mode.PATH`, this will determine if the interval should be by distance
     * (`PathIntervalType.DISTANCE`) or subdivision fractions (`PathIntervalType.SUBDIVIDE`).
     *
     * Generated from Godot docs: CSGPolygon3D.get_path_interval_type
     */
    fun getPathIntervalType(): CSGPolygon3D.PathIntervalType {
        return CSGPolygon3D.PathIntervalType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPathIntervalTypeBind, segment))
    }

    /**
     * When `mode` is `Mode.PATH`, the path interval or ratio of path points to extrusions.
     *
     * Generated from Godot docs: CSGPolygon3D.set_path_interval
     */
    fun setPathInterval(interval: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPathIntervalBind, segment, interval)
    }

    /**
     * When `mode` is `Mode.PATH`, the path interval or ratio of path points to extrusions.
     *
     * Generated from Godot docs: CSGPolygon3D.get_path_interval
     */
    fun getPathInterval(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPathIntervalBind, segment)
    }

    /**
     * When `mode` is `Mode.PATH`, extrusions that are less than this angle, will be merged together to
     * reduce polygon count.
     *
     * Generated from Godot docs: CSGPolygon3D.set_path_simplify_angle
     */
    fun setPathSimplifyAngle(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPathSimplifyAngleBind, segment, degrees)
    }

    /**
     * When `mode` is `Mode.PATH`, extrusions that are less than this angle, will be merged together to
     * reduce polygon count.
     *
     * Generated from Godot docs: CSGPolygon3D.get_path_simplify_angle
     */
    fun getPathSimplifyAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPathSimplifyAngleBind, segment)
    }

    /**
     * When `mode` is `Mode.PATH`, the path rotation method used to rotate the `polygon` as it is
     * extruded.
     *
     * Generated from Godot docs: CSGPolygon3D.set_path_rotation
     */
    fun setPathRotation(pathRotation: CSGPolygon3D.PathRotation) {
        ObjectCalls.ptrcallWithLongArg(Binds.setPathRotationBind, segment, pathRotation.value)
    }

    /**
     * When `mode` is `Mode.PATH`, the path rotation method used to rotate the `polygon` as it is
     * extruded.
     *
     * Generated from Godot docs: CSGPolygon3D.get_path_rotation
     */
    fun getPathRotation(): CSGPolygon3D.PathRotation {
        return CSGPolygon3D.PathRotation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPathRotationBind, segment))
    }

    /**
     * When `mode` is `Mode.PATH`, if `true` the polygon will be rotated according to the proper
     * tangent of the path at the sampled points. If `false` an approximation is used, which decreases
     * in accuracy as the number of subdivisions decreases.
     *
     * Generated from Godot docs: CSGPolygon3D.set_path_rotation_accurate
     */
    fun setPathRotationAccurate(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setPathRotationAccurateBind, segment, enable)
    }

    /**
     * When `mode` is `Mode.PATH`, if `true` the polygon will be rotated according to the proper
     * tangent of the path at the sampled points. If `false` an approximation is used, which decreases
     * in accuracy as the number of subdivisions decreases.
     *
     * Generated from Godot docs: CSGPolygon3D.get_path_rotation_accurate
     */
    fun getPathRotationAccurate(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getPathRotationAccurateBind, segment)
    }

    /**
     * When `mode` is `Mode.PATH`, if `true` the `Transform3D` of the `CSGPolygon3D` is used as the
     * starting point for the extrusions, not the `Transform3D` of the `path_node`.
     *
     * Generated from Godot docs: CSGPolygon3D.set_path_local
     */
    fun setPathLocal(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setPathLocalBind, segment, enable)
    }

    /**
     * When `mode` is `Mode.PATH`, if `true` the `Transform3D` of the `CSGPolygon3D` is used as the
     * starting point for the extrusions, not the `Transform3D` of the `path_node`.
     *
     * Generated from Godot docs: CSGPolygon3D.is_path_local
     */
    fun isPathLocal(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPathLocalBind, segment)
    }

    /**
     * When `mode` is `Mode.PATH`, by default, the top half of the `material` is stretched along the
     * entire length of the extruded shape. If `false` the top half of the material is repeated every
     * step of the extrusion.
     *
     * Generated from Godot docs: CSGPolygon3D.set_path_continuous_u
     */
    fun setPathContinuousU(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setPathContinuousUBind, segment, enable)
    }

    /**
     * When `mode` is `Mode.PATH`, by default, the top half of the `material` is stretched along the
     * entire length of the extruded shape. If `false` the top half of the material is repeated every
     * step of the extrusion.
     *
     * Generated from Godot docs: CSGPolygon3D.is_path_continuous_u
     */
    fun isPathContinuousU(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPathContinuousUBind, segment)
    }

    /**
     * When `mode` is `Mode.PATH`, this is the distance along the path, in meters, the texture
     * coordinates will tile. When set to 0, texture coordinates will match geometry exactly with no
     * tiling.
     *
     * Generated from Godot docs: CSGPolygon3D.set_path_u_distance
     */
    fun setPathUDistance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPathUDistanceBind, segment, distance)
    }

    /**
     * When `mode` is `Mode.PATH`, this is the distance along the path, in meters, the texture
     * coordinates will tile. When set to 0, texture coordinates will match geometry exactly with no
     * tiling.
     *
     * Generated from Godot docs: CSGPolygon3D.get_path_u_distance
     */
    fun getPathUDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPathUDistanceBind, segment)
    }

    /**
     * When `mode` is `Mode.PATH`, if `true` the ends of the path are joined, by adding an extrusion
     * between the last and first points of the path.
     *
     * Generated from Godot docs: CSGPolygon3D.set_path_joined
     */
    fun setPathJoined(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setPathJoinedBind, segment, enable)
    }

    /**
     * When `mode` is `Mode.PATH`, if `true` the ends of the path are joined, by adding an extrusion
     * between the last and first points of the path.
     *
     * Generated from Godot docs: CSGPolygon3D.is_path_joined
     */
    fun isPathJoined(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPathJoinedBind, segment)
    }

    /**
     * Material to use for the resulting mesh. The UV maps the top half of the material to the extruded
     * shape (U along the length of the extrusions and V around the outline of the `polygon`), the
     * bottom-left quarter to the front end face, and the bottom-right quarter to the back end face.
     *
     * Generated from Godot docs: CSGPolygon3D.set_material
     */
    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(material)
    }

    /**
     * Material to use for the resulting mesh. The UV maps the top half of the material to the extruded
     * shape (U along the length of the extrusions and V around the outline of the `polygon`), the
     * bottom-left quarter to the front end face, and the bottom-right quarter to the back end face.
     *
     * Generated from Godot docs: CSGPolygon3D.get_material
     */
    fun getMaterial(): Material? {
        return Material.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMaterialBind, segment))
    }

    /**
     * If `true`, applies smooth shading to the extrusions.
     *
     * Generated from Godot docs: CSGPolygon3D.set_smooth_faces
     */
    fun setSmoothFaces(smoothFaces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSmoothFacesBind, segment, smoothFaces)
    }

    /**
     * If `true`, applies smooth shading to the extrusions.
     *
     * Generated from Godot docs: CSGPolygon3D.get_smooth_faces
     */
    fun getSmoothFaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getSmoothFacesBind, segment)
    }

    /**
     * Godot's `CSGPolygon3D.Mode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`CSGPolygon3D.Mode.<NAME>`).
     *
     * Generated from Godot docs: CSGPolygon3D.Mode
     */
    @JvmInline
    value class Mode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The `polygon` shape is extruded along the negative Z axis.
             *
             * Generated from Godot docs: CSGPolygon3D.MODE_DEPTH
             */
            val DEPTH: Mode get() = Mode(0L)
            /**
             * The `polygon` shape is extruded by rotating it around the Y axis.
             *
             * Generated from Godot docs: CSGPolygon3D.MODE_SPIN
             */
            val SPIN: Mode get() = Mode(1L)
            /**
             * The `polygon` shape is extruded along the `Path3D` specified in `path_node`.
             *
             * Generated from Godot docs: CSGPolygon3D.MODE_PATH
             */
            val PATH: Mode get() = Mode(2L)
        }
    }

    /**
     * Godot's `CSGPolygon3D.PathRotation` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`CSGPolygon3D.PathRotation.<NAME>`).
     *
     * Generated from Godot docs: CSGPolygon3D.PathRotation
     */
    @JvmInline
    value class PathRotation(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The `polygon` shape is not rotated. Note: Requires the path Z coordinates to continually
             * decrease to ensure viable shapes.
             *
             * Generated from Godot docs: CSGPolygon3D.PATH_ROTATION_POLYGON
             */
            val POLYGON: PathRotation get() = PathRotation(0L)
            /**
             * The `polygon` shape is rotated along the path, but it is not rotated around the path axis. Note:
             * Requires the path Z coordinates to continually decrease to ensure viable shapes.
             *
             * Generated from Godot docs: CSGPolygon3D.PATH_ROTATION_PATH
             */
            val PATH: PathRotation get() = PathRotation(1L)
            /**
             * The `polygon` shape follows the path and its rotations around the path axis.
             *
             * Generated from Godot docs: CSGPolygon3D.PATH_ROTATION_PATH_FOLLOW
             */
            val PATH_FOLLOW: PathRotation get() = PathRotation(2L)
        }
    }

    /**
     * Godot's `CSGPolygon3D.PathIntervalType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`CSGPolygon3D.PathIntervalType.<NAME>`).
     *
     * Generated from Godot docs: CSGPolygon3D.PathIntervalType
     */
    @JvmInline
    value class PathIntervalType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * When `mode` is set to `Mode.PATH`, `path_interval` will determine the distance, in meters, each
             * interval of the path will extrude.
             *
             * Generated from Godot docs: CSGPolygon3D.PATH_INTERVAL_DISTANCE
             */
            val DISTANCE: PathIntervalType get() = PathIntervalType(0L)
            /**
             * When `mode` is set to `Mode.PATH`, `path_interval` will subdivide the polygons along the path.
             *
             * Generated from Godot docs: CSGPolygon3D.PATH_INTERVAL_SUBDIVIDE
             */
            val SUBDIVIDE: PathIntervalType get() = PathIntervalType(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CSGPolygon3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CSGPolygon3D? =
            if (handle.address() == 0L) null else CSGPolygon3D(GodotHandle(handle))

        /** A new CSGPolygon3D, GDScript's `CSGPolygon3D.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): CSGPolygon3D =
            CSGPolygon3D(GodotHandle(ObjectCalls.constructObject("CSGPolygon3D")))
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
