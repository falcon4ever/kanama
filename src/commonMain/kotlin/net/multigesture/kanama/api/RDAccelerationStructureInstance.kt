package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D

/**
 * Acceleration structure instance (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDAccelerationStructureInstance
 */
class RDAccelerationStructureInstance(handle: GodotHandle) : RefCounted(handle) {
    var transform: Transform3D
        @JvmName("transformProperty")
        get() = getTransform()
        @JvmName("setTransformProperty")
        set(value) = setTransform(value)

    var id: Long
        @JvmName("idProperty")
        get() = getId()
        @JvmName("setIdProperty")
        set(value) = setId(value)

    var mask: Int
        @JvmName("maskProperty")
        get() = getMask()
        @JvmName("setMaskProperty")
        set(value) = setMask(value)

    var hitSbtRange: Long
        @JvmName("hitSbtRangeProperty")
        get() = getHitSbtRange()
        @JvmName("setHitSbtRangeProperty")
        set(value) = setHitSbtRange(value)

    var flags: RenderingDevice.AccelerationStructureInstanceFlagBits
        @JvmName("flagsProperty")
        get() = getFlags()
        @JvmName("setFlagsProperty")
        set(value) = setFlags(value)

    var blas: RID
        @JvmName("blasProperty")
        get() = getBlas()
        @JvmName("setBlasProperty")
        set(value) = setBlas(value)

    /**
     * Transform applied to the referenced BLAS for this instance.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.set_transform
     */
    fun setTransform(pMember: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithTransform3DArg(Binds.setTransformBind, segment, pMember)
    }

    /**
     * Transform applied to the referenced BLAS for this instance.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.get_transform
     */
    fun getTransform(): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTransform3D(Binds.getTransformBind, segment)
    }

    /**
     * Custom instance ID that can be accessed in GLSL using `gl_InstanceCustomIndexEXT`.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.set_id
     */
    fun setId(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setIdBind, segment, pMember)
    }

    /**
     * Custom instance ID that can be accessed in GLSL using `gl_InstanceCustomIndexEXT`.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.get_id
     */
    fun getId(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getIdBind, segment)
    }

    /**
     * Visibility mask used to control which rays can intersect this instance.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.set_mask
     */
    fun setMask(pMember: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMaskBind, segment, pMember)
    }

    /**
     * Visibility mask used to control which rays can intersect this instance.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.get_mask
     */
    fun getMask(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaskBind, segment)
    }

    /**
     * Hit shader binding table range used for this instance, allocated using the
     * `RenderingDevice.hit_sbt_range_alloc` method.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.set_hit_sbt_range
     */
    fun setHitSbtRange(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setHitSbtRangeBind, segment, pMember)
    }

    /**
     * Hit shader binding table range used for this instance, allocated using the
     * `RenderingDevice.hit_sbt_range_alloc` method.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.get_hit_sbt_range
     */
    fun getHitSbtRange(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getHitSbtRangeBind, segment)
    }

    /**
     * Flags for the instance.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.set_flags
     */
    fun setFlags(pMember: RenderingDevice.AccelerationStructureInstanceFlagBits) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFlagsBind, segment, pMember.value)
    }

    /**
     * Flags for the instance.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.get_flags
     */
    fun getFlags(): RenderingDevice.AccelerationStructureInstanceFlagBits {
        checkOpen()
        return RenderingDevice.AccelerationStructureInstanceFlagBits(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFlagsBind, segment))
    }

    /**
     * The BLAS referenced by this instance. If `null`, the instance is treated as a placeholder but
     * still contributes to `gl_InstanceIndex` in GLSL.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.set_blas
     */
    fun setBlas(pMember: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.setBlasBind, segment, pMember)
    }

    /**
     * The BLAS referenced by this instance. If `null`, the instance is treated as a placeholder but
     * still contributes to `gl_InstanceIndex` in GLSL.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.get_blas
     */
    fun getBlas(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getBlasBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDAccelerationStructureInstance? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RDAccelerationStructureInstance? =
            if (handle.address() == 0L) null else RefCounted.owned(RDAccelerationStructureInstance(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RDAccelerationStructureInstance? =
            if (handle.address() == 0L) null else RDAccelerationStructureInstance(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TRANSFORM_HASH = 2952846383L
        @JvmField
        val setTransformBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "set_transform", SET_TRANSFORM_HASH)

        private const val GET_TRANSFORM_HASH = 3229777777L
        @JvmField
        val getTransformBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "get_transform", GET_TRANSFORM_HASH)

        private const val SET_ID_HASH = 1286410249L
        @JvmField
        val setIdBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "set_id", SET_ID_HASH)

        private const val GET_ID_HASH = 3905245786L
        @JvmField
        val getIdBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "get_id", GET_ID_HASH)

        private const val SET_MASK_HASH = 1286410249L
        @JvmField
        val setMaskBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "set_mask", SET_MASK_HASH)

        private const val GET_MASK_HASH = 3905245786L
        @JvmField
        val getMaskBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "get_mask", GET_MASK_HASH)

        private const val SET_HIT_SBT_RANGE_HASH = 1286410249L
        @JvmField
        val setHitSbtRangeBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "set_hit_sbt_range", SET_HIT_SBT_RANGE_HASH)

        private const val GET_HIT_SBT_RANGE_HASH = 3905245786L
        @JvmField
        val getHitSbtRangeBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "get_hit_sbt_range", GET_HIT_SBT_RANGE_HASH)

        private const val SET_FLAGS_HASH = 2971840141L
        @JvmField
        val setFlagsBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "set_flags", SET_FLAGS_HASH)

        private const val GET_FLAGS_HASH = 2410182637L
        @JvmField
        val getFlagsBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "get_flags", GET_FLAGS_HASH)

        private const val SET_BLAS_HASH = 2722037293L
        @JvmField
        val setBlasBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "set_blas", SET_BLAS_HASH)

        private const val GET_BLAS_HASH = 2944877500L
        @JvmField
        val getBlasBind =
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "get_blas", GET_BLAS_HASH)
    }
}
