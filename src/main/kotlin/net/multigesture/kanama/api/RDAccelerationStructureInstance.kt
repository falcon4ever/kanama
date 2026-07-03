package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D

/**
 * Acceleration structure instance (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDAccelerationStructureInstance
 */
class RDAccelerationStructureInstance(handle: MemorySegment) : RefCounted(handle) {
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

    var flags: Long
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
        ObjectCalls.ptrcallWithTransform3DArg(setTransformBind, handle, pMember)
    }

    /**
     * Transform applied to the referenced BLAS for this instance.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.get_transform
     */
    fun getTransform(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getTransformBind, handle)
    }

    /**
     * Custom instance ID that can be accessed in GLSL using `gl_InstanceCustomIndexEXT`.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.set_id
     */
    fun setId(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setIdBind, handle, pMember)
    }

    /**
     * Custom instance ID that can be accessed in GLSL using `gl_InstanceCustomIndexEXT`.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.get_id
     */
    fun getId(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getIdBind, handle)
    }

    /**
     * Visibility mask used to control which rays can intersect this instance.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.set_mask
     */
    fun setMask(pMember: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaskBind, handle, pMember)
    }

    /**
     * Visibility mask used to control which rays can intersect this instance.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.get_mask
     */
    fun getMask(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaskBind, handle)
    }

    /**
     * Hit shader binding table range used for this instance, allocated using the
     * `RenderingDevice.hit_sbt_range_alloc` method.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.set_hit_sbt_range
     */
    fun setHitSbtRange(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setHitSbtRangeBind, handle, pMember)
    }

    /**
     * Hit shader binding table range used for this instance, allocated using the
     * `RenderingDevice.hit_sbt_range_alloc` method.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.get_hit_sbt_range
     */
    fun getHitSbtRange(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHitSbtRangeBind, handle)
    }

    /**
     * Flags for the instance.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.set_flags
     */
    fun setFlags(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setFlagsBind, handle, pMember)
    }

    /**
     * Flags for the instance.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.get_flags
     */
    fun getFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFlagsBind, handle)
    }

    /**
     * The BLAS referenced by this instance. If `null`, the instance is treated as a placeholder but
     * still contributes to `gl_InstanceIndex` in GLSL.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.set_blas
     */
    fun setBlas(pMember: RID) {
        ObjectCalls.ptrcallWithRIDArg(setBlasBind, handle, pMember)
    }

    /**
     * The BLAS referenced by this instance. If `null`, the instance is treated as a placeholder but
     * still contributes to `gl_InstanceIndex` in GLSL.
     *
     * Generated from Godot docs: RDAccelerationStructureInstance.get_blas
     */
    fun getBlas(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getBlasBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDAccelerationStructureInstance? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDAccelerationStructureInstance? =
            if (handle.address() == 0L) null else RDAccelerationStructureInstance(handle)

        private const val SET_TRANSFORM_HASH = 2952846383L
        private val setTransformBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "set_transform", SET_TRANSFORM_HASH)
        }

        private const val GET_TRANSFORM_HASH = 3229777777L
        private val getTransformBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "get_transform", GET_TRANSFORM_HASH)
        }

        private const val SET_ID_HASH = 1286410249L
        private val setIdBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "set_id", SET_ID_HASH)
        }

        private const val GET_ID_HASH = 3905245786L
        private val getIdBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "get_id", GET_ID_HASH)
        }

        private const val SET_MASK_HASH = 1286410249L
        private val setMaskBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "set_mask", SET_MASK_HASH)
        }

        private const val GET_MASK_HASH = 3905245786L
        private val getMaskBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "get_mask", GET_MASK_HASH)
        }

        private const val SET_HIT_SBT_RANGE_HASH = 1286410249L
        private val setHitSbtRangeBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "set_hit_sbt_range", SET_HIT_SBT_RANGE_HASH)
        }

        private const val GET_HIT_SBT_RANGE_HASH = 3905245786L
        private val getHitSbtRangeBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "get_hit_sbt_range", GET_HIT_SBT_RANGE_HASH)
        }

        private const val SET_FLAGS_HASH = 2971840141L
        private val setFlagsBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "set_flags", SET_FLAGS_HASH)
        }

        private const val GET_FLAGS_HASH = 2410182637L
        private val getFlagsBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "get_flags", GET_FLAGS_HASH)
        }

        private const val SET_BLAS_HASH = 2722037293L
        private val setBlasBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "set_blas", SET_BLAS_HASH)
        }

        private const val GET_BLAS_HASH = 2944877500L
        private val getBlasBind by lazy {
            ObjectCalls.getMethodBind("RDAccelerationStructureInstance", "get_blas", GET_BLAS_HASH)
        }
    }
}
