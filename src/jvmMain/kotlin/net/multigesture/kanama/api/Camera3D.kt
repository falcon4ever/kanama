package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Projection
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3
import kotlin.jvm.JvmName

/**
 * Camera node, displays from a point of view.
 *
 * Generated from Godot docs: Camera3D
 */
open class Camera3D(handle: GodotHandle) : Node3D(handle) {
    var keepAspect: Long
        @JvmName("keepAspectProperty")
        get() = getKeepAspectMode()
        @JvmName("setKeepAspectProperty")
        set(value) = setKeepAspectMode(value)

    var cullMask: Long
        @JvmName("cullMaskProperty")
        get() = getCullMask()
        @JvmName("setCullMaskProperty")
        set(value) = setCullMask(value)

    var environment: Environment?
        @JvmName("environmentProperty")
        get() = getEnvironment()
        @JvmName("setEnvironmentProperty")
        set(value) = setEnvironment(value)

    var attributes: CameraAttributes?
        @JvmName("attributesProperty")
        get() = getAttributes()
        @JvmName("setAttributesProperty")
        set(value) = setAttributes(value)

    var compositor: Compositor?
        @JvmName("compositorProperty")
        get() = getCompositor()
        @JvmName("setCompositorProperty")
        set(value) = setCompositor(value)

    var hOffset: Double
        @JvmName("hOffsetProperty")
        get() = getHOffset()
        @JvmName("setHOffsetProperty")
        set(value) = setHOffset(value)

    var vOffset: Double
        @JvmName("vOffsetProperty")
        get() = getVOffset()
        @JvmName("setVOffsetProperty")
        set(value) = setVOffset(value)

    var dopplerTracking: Long
        @JvmName("dopplerTrackingProperty")
        get() = getDopplerTracking()
        @JvmName("setDopplerTrackingProperty")
        set(value) = setDopplerTracking(value)

    var projection: Long
        @JvmName("projectionProperty")
        get() = getProjection()
        @JvmName("setProjectionProperty")
        set(value) = setProjection(value)

    var current: Boolean
        @JvmName("currentProperty")
        get() = isCurrent()
        @JvmName("setCurrentProperty")
        set(value) = setCurrent(value)

    var fov: Double
        @JvmName("fovProperty")
        get() = getFov()
        @JvmName("setFovProperty")
        set(value) = setFov(value)

    var size: Double
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var frustumOffset: Vector2
        @JvmName("frustumOffsetProperty")
        get() = getFrustumOffset()
        @JvmName("setFrustumOffsetProperty")
        set(value) = setFrustumOffset(value)

    var near: Double
        @JvmName("nearProperty")
        get() = getNear()
        @JvmName("setNearProperty")
        set(value) = setNear(value)

    var far: Double
        @JvmName("farProperty")
        get() = getFar()
        @JvmName("setFarProperty")
        set(value) = setFar(value)

    /**
     * Returns a normal vector in world space, that is the result of projecting a point on the
     * `Viewport` rectangle by the inverse camera projection. This is useful for casting rays in the
     * form of (origin, normal) for object intersection or picking.
     *
     * Generated from Godot docs: Camera3D.project_ray_normal
     */
    fun projectRayNormal(screenPoint: Vector2): Vector3 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector3(projectRayNormalBind, segment, screenPoint)
    }

    /**
     * Returns a normal vector from the screen point location directed along the camera. Orthogonal
     * cameras are normalized. Perspective cameras account for perspective, screen width/height, etc.
     *
     * Generated from Godot docs: Camera3D.project_local_ray_normal
     */
    fun projectLocalRayNormal(screenPoint: Vector2): Vector3 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector3(projectLocalRayNormalBind, segment, screenPoint)
    }

    /**
     * Returns a 3D position in world space, that is the result of projecting a point on the `Viewport`
     * rectangle by the inverse camera projection. This is useful for casting rays in the form of
     * (origin, normal) for object intersection or picking.
     *
     * Generated from Godot docs: Camera3D.project_ray_origin
     */
    fun projectRayOrigin(screenPoint: Vector2): Vector3 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector3(projectRayOriginBind, segment, screenPoint)
    }

    /**
     * Returns the 2D coordinate in the `Viewport` rectangle that maps to the given 3D point in world
     * space. Note: When using this to position GUI elements over a 3D viewport, use
     * `is_position_behind` to prevent them from appearing if the 3D point is behind the camera:
     *
     * Generated from Godot docs: Camera3D.unproject_position
     */
    fun unprojectPosition(worldPoint: Vector3): Vector2 {
        return ObjectCalls.ptrcallWithVector3ArgRetVector2(unprojectPositionBind, segment, worldPoint)
    }

    /**
     * Returns `true` if the given position is behind the camera (the blue part of the linked diagram).
     * See this diagram
     * (https://raw.githubusercontent.com/godotengine/godot-docs/master/img/camera3d_position_frustum.png)
     * for an overview of position query methods. Note: A position which returns `false` may still be
     * outside the camera's field of view.
     *
     * Generated from Godot docs: Camera3D.is_position_behind
     */
    fun isPositionBehind(worldPoint: Vector3): Boolean {
        return ObjectCalls.ptrcallWithVector3ArgRetBool(isPositionBehindBind, segment, worldPoint)
    }

    /**
     * Returns the 3D point in world space that maps to the given 2D coordinate in the `Viewport`
     * rectangle on a plane that is the given `z_depth` distance into the scene away from the camera.
     *
     * Generated from Godot docs: Camera3D.project_position
     */
    fun projectPosition(screenPoint: Vector2, zDepth: Double): Vector3 {
        return ObjectCalls.ptrcallWithVector2AndDoubleArgRetVector3(projectPositionBind, segment, screenPoint, zDepth)
    }

    /**
     * Sets the camera projection to perspective mode (see `PROJECTION_PERSPECTIVE`), by specifying a
     * `fov` (field of view) angle in degrees, and the `z_near` and `z_far` clip planes in world space
     * units.
     *
     * Generated from Godot docs: Camera3D.set_perspective
     */
    fun setPerspective(fov: Double, zNear: Double, zFar: Double) {
        ObjectCalls.ptrcallWithThreeDoubleArgs(setPerspectiveBind, segment, fov, zNear, zFar)
    }

    /**
     * Sets the camera projection to orthogonal mode (see `PROJECTION_ORTHOGONAL`), by specifying a
     * `size`, and the `z_near` and `z_far` clip planes in world space units. As a hint, 3D games that
     * look 2D often use this projection, with `size` specified in pixels.
     *
     * Generated from Godot docs: Camera3D.set_orthogonal
     */
    fun setOrthogonal(size: Double, zNear: Double, zFar: Double) {
        ObjectCalls.ptrcallWithThreeDoubleArgs(setOrthogonalBind, segment, size, zNear, zFar)
    }

    /**
     * Sets the camera projection to frustum mode (see `PROJECTION_FRUSTUM`), by specifying a `size`,
     * an `offset`, and the `z_near` and `z_far` clip planes in world space units. The `size` parameter
     * represents the size of the near plane, either its width or height depending on the value of
     * `keep_aspect`. See also `frustum_offset`.
     *
     * Generated from Godot docs: Camera3D.set_frustum
     */
    fun setFrustum(size: Double, offset: Vector2, zNear: Double, zFar: Double) {
        ObjectCalls.ptrcallWithDoubleVector2TwoDoubleArgs(setFrustumBind, segment, size, offset, zNear, zFar)
    }

    /**
     * Makes this camera the current camera for the `Viewport` (see class description). If the camera
     * node is outside the scene tree, it will attempt to become current once it's added.
     *
     * Generated from Godot docs: Camera3D.make_current
     */
    fun makeCurrent() {
        ObjectCalls.ptrcallNoArgs(makeCurrentBind, segment)
    }

    /**
     * If this is the current camera, remove it from being current. If `enable_next` is `true`, request
     * to make the next camera current, if any.
     *
     * Generated from Godot docs: Camera3D.clear_current
     */
    fun clearCurrent(enableNext: Boolean = true) {
        ObjectCalls.ptrcallWithBoolArg(clearCurrentBind, segment, enableNext)
    }

    /**
     * If `true`, the ancestor `Viewport` is currently using this camera. If multiple cameras are in
     * the scene, one will always be made current. For example, if two `Camera3D` nodes are present in
     * the scene and only one is current, setting one camera's `current` to `false` will cause the
     * other camera to be made current.
     *
     * Generated from Godot docs: Camera3D.set_current
     */
    fun setCurrent(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCurrentBind, segment, enabled)
    }

    /**
     * If `true`, the ancestor `Viewport` is currently using this camera. If multiple cameras are in
     * the scene, one will always be made current. For example, if two `Camera3D` nodes are present in
     * the scene and only one is current, setting one camera's `current` to `false` will cause the
     * other camera to be made current.
     *
     * Generated from Godot docs: Camera3D.is_current
     */
    fun isCurrent(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCurrentBind, segment)
    }

    /**
     * Returns the transform of the camera plus the vertical (`v_offset`) and horizontal (`h_offset`)
     * offsets; and any other adjustments made to the position and orientation of the camera by
     * subclassed cameras such as `XRCamera3D`.
     *
     * Generated from Godot docs: Camera3D.get_camera_transform
     */
    fun getCameraTransform(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getCameraTransformBind, segment)
    }

    /**
     * Returns the projection matrix that this camera uses to render to its associated viewport. The
     * camera must be part of the scene tree to function.
     *
     * Generated from Godot docs: Camera3D.get_camera_projection
     */
    fun getCameraProjection(): Projection {
        return ObjectCalls.ptrcallNoArgsRetProjection(getCameraProjectionBind, segment)
    }

    /**
     * The camera's field of view angle (in degrees). Only applicable in perspective mode. Since
     * `keep_aspect` locks one axis, `fov` sets the other axis' field of view angle. For reference, the
     * default vertical field of view value (`75.0`) is equivalent to a horizontal FOV of: - ~91.31
     * degrees in a 4:3 viewport - ~101.67 degrees in a 16:10 viewport - ~107.51 degrees in a 16:9
     * viewport - ~121.63 degrees in a 21:9 viewport
     *
     * Generated from Godot docs: Camera3D.get_fov
     */
    fun getFov(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFovBind, segment)
    }

    /**
     * The camera's frustum offset. This can be changed from the default to create "tilted frustum"
     * effects such as Y-shearing (https://zdoom.org/wiki/Y-shearing). Note: Only effective if
     * `projection` is `PROJECTION_FRUSTUM`.
     *
     * Generated from Godot docs: Camera3D.get_frustum_offset
     */
    fun getFrustumOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getFrustumOffsetBind, segment)
    }

    /**
     * The camera's size in meters measured as the diameter of the width or height, depending on
     * `keep_aspect`. Only applicable in orthogonal and frustum modes.
     *
     * Generated from Godot docs: Camera3D.get_size
     */
    fun getSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSizeBind, segment)
    }

    /**
     * The distance to the far culling boundary for this camera relative to its local Z axis. Higher
     * values allow the camera to see further away, while decreasing `far` can improve performance if
     * it results in objects being partially or fully culled.
     *
     * Generated from Godot docs: Camera3D.get_far
     */
    fun getFar(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFarBind, segment)
    }

    /**
     * The distance to the near culling boundary for this camera relative to its local Z axis. Lower
     * values allow the camera to see objects more up close to its origin, at the cost of lower
     * precision across the entire range. Values lower than the default can lead to increased
     * Z-fighting.
     *
     * Generated from Godot docs: Camera3D.get_near
     */
    fun getNear(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getNearBind, segment)
    }

    /**
     * The camera's field of view angle (in degrees). Only applicable in perspective mode. Since
     * `keep_aspect` locks one axis, `fov` sets the other axis' field of view angle. For reference, the
     * default vertical field of view value (`75.0`) is equivalent to a horizontal FOV of: - ~91.31
     * degrees in a 4:3 viewport - ~101.67 degrees in a 16:10 viewport - ~107.51 degrees in a 16:9
     * viewport - ~121.63 degrees in a 21:9 viewport
     *
     * Generated from Godot docs: Camera3D.set_fov
     */
    fun setFov(fov: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFovBind, segment, fov)
    }

    /**
     * The camera's frustum offset. This can be changed from the default to create "tilted frustum"
     * effects such as Y-shearing (https://zdoom.org/wiki/Y-shearing). Note: Only effective if
     * `projection` is `PROJECTION_FRUSTUM`.
     *
     * Generated from Godot docs: Camera3D.set_frustum_offset
     */
    fun setFrustumOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setFrustumOffsetBind, segment, offset)
    }

    /**
     * The camera's size in meters measured as the diameter of the width or height, depending on
     * `keep_aspect`. Only applicable in orthogonal and frustum modes.
     *
     * Generated from Godot docs: Camera3D.set_size
     */
    fun setSize(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSizeBind, segment, size)
    }

    /**
     * The distance to the far culling boundary for this camera relative to its local Z axis. Higher
     * values allow the camera to see further away, while decreasing `far` can improve performance if
     * it results in objects being partially or fully culled.
     *
     * Generated from Godot docs: Camera3D.set_far
     */
    fun setFar(far: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFarBind, segment, far)
    }

    /**
     * The distance to the near culling boundary for this camera relative to its local Z axis. Lower
     * values allow the camera to see objects more up close to its origin, at the cost of lower
     * precision across the entire range. Values lower than the default can lead to increased
     * Z-fighting.
     *
     * Generated from Godot docs: Camera3D.set_near
     */
    fun setNear(near: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setNearBind, segment, near)
    }

    /**
     * The camera's projection mode. In `PROJECTION_PERSPECTIVE` mode, objects' Z distance from the
     * camera's local space scales their perceived size.
     *
     * Generated from Godot docs: Camera3D.get_projection
     */
    fun getProjection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getProjectionBind, segment)
    }

    /**
     * The camera's projection mode. In `PROJECTION_PERSPECTIVE` mode, objects' Z distance from the
     * camera's local space scales their perceived size.
     *
     * Generated from Godot docs: Camera3D.set_projection
     */
    fun setProjection(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setProjectionBind, segment, mode)
    }

    /**
     * The horizontal (X) offset of the camera viewport.
     *
     * Generated from Godot docs: Camera3D.set_h_offset
     */
    fun setHOffset(offset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHOffsetBind, segment, offset)
    }

    /**
     * The horizontal (X) offset of the camera viewport.
     *
     * Generated from Godot docs: Camera3D.get_h_offset
     */
    fun getHOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHOffsetBind, segment)
    }

    /**
     * The vertical (Y) offset of the camera viewport.
     *
     * Generated from Godot docs: Camera3D.set_v_offset
     */
    fun setVOffset(offset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVOffsetBind, segment, offset)
    }

    /**
     * The vertical (Y) offset of the camera viewport.
     *
     * Generated from Godot docs: Camera3D.get_v_offset
     */
    fun getVOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVOffsetBind, segment)
    }

    /**
     * The culling mask that describes which `VisualInstance3D.layers` are rendered by this camera. By
     * default, all 20 user-visible layers are rendered. Note: Since the `cull_mask` allows for 32
     * layers to be stored in total, there are an additional 12 layers that are only used internally by
     * the engine and aren't exposed in the editor. Setting `cull_mask` using a script allows you to
     * toggle those reserved layers, which can be useful for editor plugins. To adjust `cull_mask` more
     * easily using a script, use `get_cull_mask_value` and `set_cull_mask_value`. Note: `VoxelGI`,
     * SDFGI and `LightmapGI` will always take all layers into account to determine what contributes to
     * global illumination. If this is an issue, set `GeometryInstance3D.gi_mode` to
     * `GeometryInstance3D.GI_MODE_DISABLED` for meshes and `Light3D.light_bake_mode` to
     * `Light3D.BAKE_DISABLED` for lights to exclude them from global illumination.
     *
     * Generated from Godot docs: Camera3D.set_cull_mask
     */
    fun setCullMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCullMaskBind, segment, mask)
    }

    /**
     * The culling mask that describes which `VisualInstance3D.layers` are rendered by this camera. By
     * default, all 20 user-visible layers are rendered. Note: Since the `cull_mask` allows for 32
     * layers to be stored in total, there are an additional 12 layers that are only used internally by
     * the engine and aren't exposed in the editor. Setting `cull_mask` using a script allows you to
     * toggle those reserved layers, which can be useful for editor plugins. To adjust `cull_mask` more
     * easily using a script, use `get_cull_mask_value` and `set_cull_mask_value`. Note: `VoxelGI`,
     * SDFGI and `LightmapGI` will always take all layers into account to determine what contributes to
     * global illumination. If this is an issue, set `GeometryInstance3D.gi_mode` to
     * `GeometryInstance3D.GI_MODE_DISABLED` for meshes and `Light3D.light_bake_mode` to
     * `Light3D.BAKE_DISABLED` for lights to exclude them from global illumination.
     *
     * Generated from Godot docs: Camera3D.get_cull_mask
     */
    fun getCullMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCullMaskBind, segment)
    }

    /**
     * The `Environment` to use for this camera.
     *
     * Generated from Godot docs: Camera3D.set_environment
     */
    fun setEnvironment(env: Environment?) {
        ObjectCalls.ptrcallWithObjectArgs(setEnvironmentBind, segment, listOf(env?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `Environment` to use for this camera.
     *
     * Generated from Godot docs: Camera3D.get_environment
     */
    fun getEnvironment(): Environment? {
        return Environment.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEnvironmentBind, segment))
    }

    /**
     * The `CameraAttributes` to use for this camera.
     *
     * Generated from Godot docs: Camera3D.set_attributes
     */
    fun setAttributes(env: CameraAttributes?) {
        ObjectCalls.ptrcallWithObjectArgs(setAttributesBind, segment, listOf(env?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `CameraAttributes` to use for this camera.
     *
     * Generated from Godot docs: Camera3D.get_attributes
     */
    fun getAttributes(): CameraAttributes? {
        return CameraAttributes.wrap(ObjectCalls.ptrcallNoArgsRetObject(getAttributesBind, segment))
    }

    /**
     * The `Compositor` to use for this camera.
     *
     * Generated from Godot docs: Camera3D.set_compositor
     */
    fun setCompositor(compositor: Compositor?) {
        ObjectCalls.ptrcallWithObjectArgs(setCompositorBind, segment, listOf(compositor?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `Compositor` to use for this camera.
     *
     * Generated from Godot docs: Camera3D.get_compositor
     */
    fun getCompositor(): Compositor? {
        return Compositor.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCompositorBind, segment))
    }

    /**
     * The axis to lock during `fov`/`size` adjustments. Can be either `KEEP_WIDTH` or `KEEP_HEIGHT`.
     *
     * Generated from Godot docs: Camera3D.set_keep_aspect_mode
     */
    fun setKeepAspectMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setKeepAspectModeBind, segment, mode)
    }

    /**
     * The axis to lock during `fov`/`size` adjustments. Can be either `KEEP_WIDTH` or `KEEP_HEIGHT`.
     *
     * Generated from Godot docs: Camera3D.get_keep_aspect_mode
     */
    fun getKeepAspectMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getKeepAspectModeBind, segment)
    }

    /**
     * If not `DOPPLER_TRACKING_DISABLED`, this camera will simulate the Doppler effect
     * (https://en.wikipedia.org/wiki/Doppler_effect) for objects changed in particular `_process`
     * methods. Note: The Doppler effect will only be heard on `AudioStreamPlayer3D`s if
     * `AudioStreamPlayer3D.doppler_tracking` is not set to
     * `AudioStreamPlayer3D.DOPPLER_TRACKING_DISABLED`.
     *
     * Generated from Godot docs: Camera3D.set_doppler_tracking
     */
    fun setDopplerTracking(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDopplerTrackingBind, segment, mode)
    }

    /**
     * If not `DOPPLER_TRACKING_DISABLED`, this camera will simulate the Doppler effect
     * (https://en.wikipedia.org/wiki/Doppler_effect) for objects changed in particular `_process`
     * methods. Note: The Doppler effect will only be heard on `AudioStreamPlayer3D`s if
     * `AudioStreamPlayer3D.doppler_tracking` is not set to
     * `AudioStreamPlayer3D.DOPPLER_TRACKING_DISABLED`.
     *
     * Generated from Godot docs: Camera3D.get_doppler_tracking
     */
    fun getDopplerTracking(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDopplerTrackingBind, segment)
    }

    /**
     * Returns the camera's frustum planes in world space units as an array of `Plane`s in the
     * following order: near, far, left, top, right, bottom. Not to be confused with `frustum_offset`.
     *
     * Generated from Godot docs: Camera3D.get_frustum
     */
    fun getFrustum(): List<Plane> {
        return ObjectCalls.ptrcallNoArgsRetPlaneList(getFrustumBind, segment)
    }

    /**
     * Returns `true` if the given position is inside the camera's frustum (the green part of the
     * linked diagram). See this diagram
     * (https://raw.githubusercontent.com/godotengine/godot-docs/master/img/camera3d_position_frustum.png)
     * for an overview of position query methods.
     *
     * Generated from Godot docs: Camera3D.is_position_in_frustum
     */
    fun isPositionInFrustum(worldPoint: Vector3): Boolean {
        return ObjectCalls.ptrcallWithVector3ArgRetBool(isPositionInFrustumBind, segment, worldPoint)
    }

    /**
     * Returns the camera's RID from the `RenderingServer`.
     *
     * Generated from Godot docs: Camera3D.get_camera_rid
     */
    fun getCameraRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getCameraRidBind, segment)
    }

    /**
     * Returns the RID of a pyramid shape encompassing the camera's view frustum, ignoring the camera's
     * near plane. The tip of the pyramid represents the position of the camera.
     *
     * Generated from Godot docs: Camera3D.get_pyramid_shape_rid
     */
    fun getPyramidShapeRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getPyramidShapeRidBind, segment)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `cull_mask`, given a
     * `layer_number` between 1 and 20.
     *
     * Generated from Godot docs: Camera3D.set_cull_mask_value
     */
    fun setCullMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCullMaskValueBind, segment, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `cull_mask` is enabled, given a `layer_number`
     * between 1 and 20.
     *
     * Generated from Godot docs: Camera3D.get_cull_mask_value
     */
    fun getCullMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCullMaskValueBind, segment, layerNumber)
    }

    companion object {
        const val PROJECTION_PERSPECTIVE: Long = 0L
        const val PROJECTION_ORTHOGONAL: Long = 1L
        const val PROJECTION_FRUSTUM: Long = 2L
        const val KEEP_WIDTH: Long = 0L
        const val KEEP_HEIGHT: Long = 1L
        const val DOPPLER_TRACKING_DISABLED: Long = 0L
        const val DOPPLER_TRACKING_IDLE_STEP: Long = 1L
        const val DOPPLER_TRACKING_PHYSICS_STEP: Long = 2L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): Camera3D? =
            wrap(handle.segment)

        @JvmStatic
        fun create(): Camera3D =
            Camera3D(GodotHandle(ObjectCalls.constructObject("Camera3D")))

        internal fun wrap(handle: RawSegment): Camera3D? =
            if (handle.address() == 0L) null else Camera3D(GodotHandle(handle))

        private const val PROJECT_RAY_NORMAL_HASH = 1718073306L
        private val projectRayNormalBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "project_ray_normal", PROJECT_RAY_NORMAL_HASH)
        }

        private const val PROJECT_LOCAL_RAY_NORMAL_HASH = 1718073306L
        private val projectLocalRayNormalBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "project_local_ray_normal", PROJECT_LOCAL_RAY_NORMAL_HASH)
        }

        private const val PROJECT_RAY_ORIGIN_HASH = 1718073306L
        private val projectRayOriginBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "project_ray_origin", PROJECT_RAY_ORIGIN_HASH)
        }

        private const val UNPROJECT_POSITION_HASH = 3758901831L
        private val unprojectPositionBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "unproject_position", UNPROJECT_POSITION_HASH)
        }

        private const val IS_POSITION_BEHIND_HASH = 3108956480L
        private val isPositionBehindBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "is_position_behind", IS_POSITION_BEHIND_HASH)
        }

        private const val PROJECT_POSITION_HASH = 2171975744L
        private val projectPositionBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "project_position", PROJECT_POSITION_HASH)
        }

        private const val SET_PERSPECTIVE_HASH = 2385087082L
        private val setPerspectiveBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_perspective", SET_PERSPECTIVE_HASH)
        }

        private const val SET_ORTHOGONAL_HASH = 2385087082L
        private val setOrthogonalBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_orthogonal", SET_ORTHOGONAL_HASH)
        }

        private const val SET_FRUSTUM_HASH = 354890663L
        private val setFrustumBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_frustum", SET_FRUSTUM_HASH)
        }

        private const val MAKE_CURRENT_HASH = 3218959716L
        private val makeCurrentBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "make_current", MAKE_CURRENT_HASH)
        }

        private const val CLEAR_CURRENT_HASH = 3216645846L
        private val clearCurrentBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "clear_current", CLEAR_CURRENT_HASH)
        }

        private const val SET_CURRENT_HASH = 2586408642L
        private val setCurrentBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_current", SET_CURRENT_HASH)
        }

        private const val IS_CURRENT_HASH = 36873697L
        private val isCurrentBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "is_current", IS_CURRENT_HASH)
        }

        private const val GET_CAMERA_TRANSFORM_HASH = 3229777777L
        private val getCameraTransformBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_camera_transform", GET_CAMERA_TRANSFORM_HASH)
        }

        private const val GET_CAMERA_PROJECTION_HASH = 2910717950L
        private val getCameraProjectionBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_camera_projection", GET_CAMERA_PROJECTION_HASH)
        }

        private const val GET_FOV_HASH = 1740695150L
        private val getFovBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_fov", GET_FOV_HASH)
        }

        private const val GET_FRUSTUM_OFFSET_HASH = 3341600327L
        private val getFrustumOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_frustum_offset", GET_FRUSTUM_OFFSET_HASH)
        }

        private const val GET_SIZE_HASH = 1740695150L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_size", GET_SIZE_HASH)
        }

        private const val GET_FAR_HASH = 1740695150L
        private val getFarBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_far", GET_FAR_HASH)
        }

        private const val GET_NEAR_HASH = 1740695150L
        private val getNearBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_near", GET_NEAR_HASH)
        }

        private const val SET_FOV_HASH = 373806689L
        private val setFovBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_fov", SET_FOV_HASH)
        }

        private const val SET_FRUSTUM_OFFSET_HASH = 743155724L
        private val setFrustumOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_frustum_offset", SET_FRUSTUM_OFFSET_HASH)
        }

        private const val SET_SIZE_HASH = 373806689L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_size", SET_SIZE_HASH)
        }

        private const val SET_FAR_HASH = 373806689L
        private val setFarBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_far", SET_FAR_HASH)
        }

        private const val SET_NEAR_HASH = 373806689L
        private val setNearBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_near", SET_NEAR_HASH)
        }

        private const val GET_PROJECTION_HASH = 2624185235L
        private val getProjectionBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_projection", GET_PROJECTION_HASH)
        }

        private const val SET_PROJECTION_HASH = 4218540108L
        private val setProjectionBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_projection", SET_PROJECTION_HASH)
        }

        private const val SET_H_OFFSET_HASH = 373806689L
        private val setHOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_h_offset", SET_H_OFFSET_HASH)
        }

        private const val GET_H_OFFSET_HASH = 1740695150L
        private val getHOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_h_offset", GET_H_OFFSET_HASH)
        }

        private const val SET_V_OFFSET_HASH = 373806689L
        private val setVOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_v_offset", SET_V_OFFSET_HASH)
        }

        private const val GET_V_OFFSET_HASH = 1740695150L
        private val getVOffsetBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_v_offset", GET_V_OFFSET_HASH)
        }

        private const val SET_CULL_MASK_HASH = 1286410249L
        private val setCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_cull_mask", SET_CULL_MASK_HASH)
        }

        private const val GET_CULL_MASK_HASH = 3905245786L
        private val getCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_cull_mask", GET_CULL_MASK_HASH)
        }

        private const val SET_ENVIRONMENT_HASH = 4143518816L
        private val setEnvironmentBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_environment", SET_ENVIRONMENT_HASH)
        }

        private const val GET_ENVIRONMENT_HASH = 3082064660L
        private val getEnvironmentBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_environment", GET_ENVIRONMENT_HASH)
        }

        private const val SET_ATTRIBUTES_HASH = 2817810567L
        private val setAttributesBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_attributes", SET_ATTRIBUTES_HASH)
        }

        private const val GET_ATTRIBUTES_HASH = 3921283215L
        private val getAttributesBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_attributes", GET_ATTRIBUTES_HASH)
        }

        private const val SET_COMPOSITOR_HASH = 1586754307L
        private val setCompositorBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_compositor", SET_COMPOSITOR_HASH)
        }

        private const val GET_COMPOSITOR_HASH = 3647707413L
        private val getCompositorBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_compositor", GET_COMPOSITOR_HASH)
        }

        private const val SET_KEEP_ASPECT_MODE_HASH = 1740651252L
        private val setKeepAspectModeBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_keep_aspect_mode", SET_KEEP_ASPECT_MODE_HASH)
        }

        private const val GET_KEEP_ASPECT_MODE_HASH = 2790278316L
        private val getKeepAspectModeBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_keep_aspect_mode", GET_KEEP_ASPECT_MODE_HASH)
        }

        private const val SET_DOPPLER_TRACKING_HASH = 3109431270L
        private val setDopplerTrackingBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_doppler_tracking", SET_DOPPLER_TRACKING_HASH)
        }

        private const val GET_DOPPLER_TRACKING_HASH = 1584483649L
        private val getDopplerTrackingBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_doppler_tracking", GET_DOPPLER_TRACKING_HASH)
        }

        private const val GET_FRUSTUM_HASH = 3995934104L
        private val getFrustumBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_frustum", GET_FRUSTUM_HASH)
        }

        private const val IS_POSITION_IN_FRUSTUM_HASH = 3108956480L
        private val isPositionInFrustumBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "is_position_in_frustum", IS_POSITION_IN_FRUSTUM_HASH)
        }

        private const val GET_CAMERA_RID_HASH = 2944877500L
        private val getCameraRidBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_camera_rid", GET_CAMERA_RID_HASH)
        }

        private const val GET_PYRAMID_SHAPE_RID_HASH = 529393457L
        private val getPyramidShapeRidBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_pyramid_shape_rid", GET_PYRAMID_SHAPE_RID_HASH)
        }

        private const val SET_CULL_MASK_VALUE_HASH = 300928843L
        private val setCullMaskValueBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "set_cull_mask_value", SET_CULL_MASK_VALUE_HASH)
        }

        private const val GET_CULL_MASK_VALUE_HASH = 1116898809L
        private val getCullMaskValueBind by lazy {
            ObjectCalls.getMethodBind("Camera3D", "get_cull_mask_value", GET_CULL_MASK_VALUE_HASH)
        }
    }
}
