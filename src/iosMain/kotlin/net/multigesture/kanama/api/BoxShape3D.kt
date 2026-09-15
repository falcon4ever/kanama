package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: BoxShape3D
 */
class BoxShape3D(handle: GodotHandle) : Shape3D(handle) {
    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    fun setSize(size: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, segment, size)
    }

    fun getSize(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, segment)
    }

    companion object {
        // KANAMA-IOS-SUGAR: [glue] desktop-parity constructor sugar (the desktop wrapper's
        // MemorySegment constructor is internal, so shared game code uses create()).
        fun create(): BoxShape3D =
            BoxShape3D(GodotHandle(ObjectCalls.constructObject("BoxShape3D")))

        fun fromHandle(handle: GodotHandle): BoxShape3D? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): BoxShape3D? =
            if (handle.address() == 0L) null else BoxShape3D(GodotHandle(handle))

        private const val SET_SIZE_HASH = 3460891852L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("BoxShape3D", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3360562783L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("BoxShape3D", "get_size", GET_SIZE_HASH)
        }
    }
}
