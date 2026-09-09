package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for JavaClassWrapper (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP JavaClassWrapper waits on: ptrcallWithObjectAndPackedStringListArgsRetObject
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates a `JavaObject` implementing the given Java interfaces using the given `Object` as the
 * implementation. The `object` must contain methods signatures matching the methods signatures
 * from the passed Java `interfaces`. Invoking methods from the Java `interfaces` will route to the
 * matching `object` method.
 *
 * Generated from Godot docs: JavaClassWrapper.create_proxy
 */
fun JavaClassWrapper.createProxy(objectValue: GodotObject, interfaces: List<String>): JavaObject? {
    return JavaObject.wrap(ObjectCalls.ptrcallWithObjectAndPackedStringListArgsRetObject(createProxyBind, javaClassWrapperSingleton, objectValue.handle, interfaces))
}

private val javaClassWrapperSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("JavaClassWrapper")
}

private const val CREATE_PROXY_HASH = 2694931752L
private val createProxyBind by lazy {
    ObjectCalls.getMethodBind("JavaClassWrapper", "create_proxy", CREATE_PROXY_HASH)
}
