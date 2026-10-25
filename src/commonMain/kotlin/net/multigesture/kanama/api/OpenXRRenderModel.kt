package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * This node will display an OpenXR render model.
 *
 * Generated from Godot docs: OpenXRRenderModel
 */
class OpenXRRenderModel(handle: GodotHandle) : Node3D(handle) {
    var renderModel: RID
        @JvmName("renderModelProperty")
        get() = getRenderModel()
        @JvmName("setRenderModelProperty")
        set(value) = setRenderModel(value)

    /**
     * Returns the top level path related to this render model.
     *
     * Generated from Godot docs: OpenXRRenderModel.get_top_level_path
     */
    fun getTopLevelPath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getTopLevelPathBind, segment)
    }

    /**
     * The render model RID for the render model to load, as returned by
     * `OpenXRRenderModelExtension.render_model_create` or
     * `OpenXRRenderModelExtension.render_model_get_all`.
     *
     * Generated from Godot docs: OpenXRRenderModel.get_render_model
     */
    fun getRenderModel(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getRenderModelBind, segment)
    }

    /**
     * The render model RID for the render model to load, as returned by
     * `OpenXRRenderModelExtension.render_model_create` or
     * `OpenXRRenderModelExtension.render_model_get_all`.
     *
     * Generated from Godot docs: OpenXRRenderModel.set_render_model
     */
    fun setRenderModel(renderModel: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.setRenderModelBind, segment, renderModel)
    }

    /** Signal `render_model_top_level_path_changed()`; see [TypedSignal]. */
    val renderModelTopLevelPathChanged: Signal0
        @JvmName("renderModelTopLevelPathChangedTypedSignal")
        get() = Signal0(this, "render_model_top_level_path_changed")

    object Signals {
        const val renderModelTopLevelPathChanged: String = "render_model_top_level_path_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRRenderModel? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRRenderModel? =
            if (handle.address() == 0L) null else OpenXRRenderModel(GodotHandle(handle))

        /** A new OpenXRRenderModel, GDScript's `OpenXRRenderModel.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): OpenXRRenderModel =
            OpenXRRenderModel(GodotHandle(ObjectCalls.constructObject("OpenXRRenderModel")))
    }

    private object Binds {
        private const val GET_TOP_LEVEL_PATH_HASH = 201670096L
        @JvmField
        val getTopLevelPathBind =
            ObjectCalls.getMethodBind("OpenXRRenderModel", "get_top_level_path", GET_TOP_LEVEL_PATH_HASH)

        private const val GET_RENDER_MODEL_HASH = 2944877500L
        @JvmField
        val getRenderModelBind =
            ObjectCalls.getMethodBind("OpenXRRenderModel", "get_render_model", GET_RENDER_MODEL_HASH)

        private const val SET_RENDER_MODEL_HASH = 2722037293L
        @JvmField
        val setRenderModelBind =
            ObjectCalls.getMethodBind("OpenXRRenderModel", "set_render_model", SET_RENDER_MODEL_HASH)
    }
}
