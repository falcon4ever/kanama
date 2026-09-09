package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for ImageTextureLayered (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ImageTextureLayered waits on: ptrcallWithObjectListArgRetLong
// Index: docs/contributing/ios-shape-gap.md

/**
 * Creates an `ImageTextureLayered` from an array of `Image`s. See `Image.create` for the expected
 * data format. The first image decides the width, height, image format and mipmapping setting. The
 * other images must have the same width, height, image format and mipmapping setting. Each `Image`
 * represents one `layer`.
 *
 * Generated from Godot docs: ImageTextureLayered.create_from_images
 */
fun ImageTextureLayered.createFromImages(images: List<Image>): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithObjectListArgRetLong(createFromImagesBind, handle, images)
}

private const val CREATE_FROM_IMAGES_HASH = 2785773503L
private val createFromImagesBind by lazy {
    ObjectCalls.getMethodBind("ImageTextureLayered", "create_from_images", CREATE_FROM_IMAGES_HASH)
}
