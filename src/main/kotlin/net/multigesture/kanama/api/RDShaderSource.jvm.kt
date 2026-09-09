package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for RDShaderSource (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RDShaderSource waits on: ptrcallWithLongArgRetString
// Index: docs/contributing/ios-shape-gap.md

/**
 * Source code for the shader's vertex stage.
 *
 * Generated from Godot docs: RDShaderSource.get_stage_source
 */
fun RDShaderSource.getStageSource(stage: Long): String {
    checkOpen()
    return ObjectCalls.ptrcallWithLongArgRetString(getStageSourceBind, handle, stage)
}

var RDShaderSource.sourceVertex: String
    @JvmName("sourceVertexProperty")
    get() = getStageSource(0L)
    @JvmName("setSourceVertexProperty")
    set(value) = setStageSource(0L, value)

var RDShaderSource.sourceFragment: String
    @JvmName("sourceFragmentProperty")
    get() = getStageSource(1L)
    @JvmName("setSourceFragmentProperty")
    set(value) = setStageSource(1L, value)

var RDShaderSource.sourceTesselationControl: String
    @JvmName("sourceTesselationControlProperty")
    get() = getStageSource(2L)
    @JvmName("setSourceTesselationControlProperty")
    set(value) = setStageSource(2L, value)

var RDShaderSource.sourceTesselationEvaluation: String
    @JvmName("sourceTesselationEvaluationProperty")
    get() = getStageSource(3L)
    @JvmName("setSourceTesselationEvaluationProperty")
    set(value) = setStageSource(3L, value)

var RDShaderSource.sourceCompute: String
    @JvmName("sourceComputeProperty")
    get() = getStageSource(4L)
    @JvmName("setSourceComputeProperty")
    set(value) = setStageSource(4L, value)

var RDShaderSource.sourceRaygen: String
    @JvmName("sourceRaygenProperty")
    get() = getStageSource(5L)
    @JvmName("setSourceRaygenProperty")
    set(value) = setStageSource(5L, value)

var RDShaderSource.sourceAnyHit: String
    @JvmName("sourceAnyHitProperty")
    get() = getStageSource(6L)
    @JvmName("setSourceAnyHitProperty")
    set(value) = setStageSource(6L, value)

var RDShaderSource.sourceClosestHit: String
    @JvmName("sourceClosestHitProperty")
    get() = getStageSource(7L)
    @JvmName("setSourceClosestHitProperty")
    set(value) = setStageSource(7L, value)

var RDShaderSource.sourceMiss: String
    @JvmName("sourceMissProperty")
    get() = getStageSource(8L)
    @JvmName("setSourceMissProperty")
    set(value) = setStageSource(8L, value)

var RDShaderSource.sourceIntersection: String
    @JvmName("sourceIntersectionProperty")
    get() = getStageSource(9L)
    @JvmName("setSourceIntersectionProperty")
    set(value) = setStageSource(9L, value)

private const val GET_STAGE_SOURCE_HASH = 3354920045L
private val getStageSourceBind by lazy {
    ObjectCalls.getMethodBind("RDShaderSource", "get_stage_source", GET_STAGE_SOURCE_HASH)
}
