package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GLTFMesh (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFMesh waits on: ptrcallWithStringNameAndVariantArg,
//   ptrcallWithTypedMaterialListArg
// Index: docs/contributing/ios-shape-gap.md

fun GLTFMesh.setInstanceMaterials(instanceMaterials: List<Material>) {
    checkOpen()
    ObjectCalls.ptrcallWithTypedMaterialListArg(setInstanceMaterialsBind, handle, instanceMaterials)
}

fun GLTFMesh.setAdditionalData(extensionName: String, additionalData: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithStringNameAndVariantArg(setAdditionalDataBind, handle, extensionName, additionalData)
}

private const val SET_INSTANCE_MATERIALS_HASH = 381264803L
private val setInstanceMaterialsBind by lazy {
    ObjectCalls.getMethodBind("GLTFMesh", "set_instance_materials", SET_INSTANCE_MATERIALS_HASH)
}

private const val SET_ADDITIONAL_DATA_HASH = 3776071444L
private val setAdditionalDataBind by lazy {
    ObjectCalls.getMethodBind("GLTFMesh", "set_additional_data", SET_ADDITIONAL_DATA_HASH)
}
