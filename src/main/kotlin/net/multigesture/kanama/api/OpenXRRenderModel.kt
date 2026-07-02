package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: OpenXRRenderModel
 */
class OpenXRRenderModel(handle: MemorySegment) : Node3D(handle) {
    var renderModel: RID
        @JvmName("renderModelProperty")
        get() = getRenderModel()
        @JvmName("setRenderModelProperty")
        set(value) = setRenderModel(value)

    fun getTopLevelPath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTopLevelPathBind, handle)
    }

    fun getRenderModel(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getRenderModelBind, handle)
    }

    fun setRenderModel(renderModel: RID) {
        ObjectCalls.ptrcallWithRIDArg(setRenderModelBind, handle, renderModel)
    }

    object Signals {
        const val renderModelTopLevelPathChanged: String = "render_model_top_level_path_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRRenderModel? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRRenderModel? =
            if (handle.address() == 0L) null else OpenXRRenderModel(handle)

        private const val GET_TOP_LEVEL_PATH_HASH = 201670096L
        private val getTopLevelPathBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModel", "get_top_level_path", GET_TOP_LEVEL_PATH_HASH)
        }

        private const val GET_RENDER_MODEL_HASH = 2944877500L
        private val getRenderModelBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModel", "get_render_model", GET_RENDER_MODEL_HASH)
        }

        private const val SET_RENDER_MODEL_HASH = 2722037293L
        private val setRenderModelBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModel", "set_render_model", SET_RENDER_MODEL_HASH)
        }
    }
}
