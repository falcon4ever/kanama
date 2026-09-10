package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for RDShaderSPIRV (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RDShaderSPIRV waits on: ptrcallWithLongAndByteArrayArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The SPIR-V bytecode for the vertex shader stage.
 *
 * Generated from Godot docs: RDShaderSPIRV.set_stage_bytecode
 */
fun RDShaderSPIRV.setStageBytecode(stage: Long, bytecode: ByteArray) {
    checkOpen()
    ObjectCalls.ptrcallWithLongAndByteArrayArg(setStageBytecodeBind, handle, stage, bytecode)
}

private const val SET_STAGE_BYTECODE_HASH = 3514097977L
private val setStageBytecodeBind by lazy {
    ObjectCalls.getMethodBind("RDShaderSPIRV", "set_stage_bytecode", SET_STAGE_BYTECODE_HASH)
}
