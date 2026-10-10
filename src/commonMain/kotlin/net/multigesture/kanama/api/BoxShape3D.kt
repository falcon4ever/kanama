package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * A 3D box shape used for physics collision.
 *
 * Generated from Godot docs: BoxShape3D
 */
class BoxShape3D(handle: GodotHandle) : Shape3D(handle) {
    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    /**
     * The box's width, height and depth.
     *
     * Generated from Godot docs: BoxShape3D.set_size
     */
    fun setSize(size: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setSizeBind, segment, size)
    }

    /**
     * The box's width, height and depth.
     *
     * Generated from Godot docs: BoxShape3D.get_size
     */
    fun getSize(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getSizeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): BoxShape3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): BoxShape3D? =
            if (handle.address() == 0L) null else RefCounted.owned(BoxShape3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): BoxShape3D? =
            if (handle.address() == 0L) null else BoxShape3D(GodotHandle(handle))

        // Instantiate a BoxShape3D.
        @JvmStatic
        fun create(): BoxShape3D =
            RefCounted.owned(BoxShape3D(GodotHandle(ObjectCalls.constructObject("BoxShape3D"))))

        // Downcast a Resource to BoxShape3D (null if not).
        @JvmStatic
        fun fromResource(value: Resource): BoxShape3D? =
            if (value.isClass("BoxShape3D")) RefCounted.retained(BoxShape3D(value.handle)) else null
    }

    private object Binds {
        private const val SET_SIZE_HASH = 3460891852L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("BoxShape3D", "set_size", SET_SIZE_HASH)

        private const val GET_SIZE_HASH = 3360562783L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("BoxShape3D", "get_size", GET_SIZE_HASH)
    }
}
