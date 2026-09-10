package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2

// GENERATED desktop/Android companion for RichTextLabel (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RichTextLabel waits on: ptrcallWithArrayArg, ptrcallWithObjectAndDictionaryArg,
//   ptrcallWithObjectTwoDoubleColorLongRect2VariantBoolStringTwoLongStringArgs,
//   ptrcallWithPackedStringListArgRetDictionary, ptrcallWithVariantArg,
//   ptrcallWithVariantLongObjectTwoDoubleColorLongRect2BoolStringTwoLongArgs,
//   ptrcallWithVariantLongStringArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Adds an image's opening and closing tags to the tag stack, optionally providing a `width` and
 * `height` to resize the image, a `color` to tint the image and a `region` to only use parts of
 * the image. If `width` or `height` is set to 0, the image size will be adjusted in order to keep
 * the original aspect ratio. If `width` and `height` are not set, but `region` is, the region's
 * rect will be used. `key` is an optional identifier, that can be used to modify the image via
 * `update_image`. If `pad` is set, and the image is smaller than the size specified by `width` and
 * `height`, the image padding is added to match the size instead of upscaling. Parameters
 * `width_unit` and `height_unit` determine the units used to calculate the image width and height,
 * respectively. `alt_text` is used as the image description for assistive apps.
 *
 * Generated from Godot docs: RichTextLabel.add_image
 */
fun RichTextLabel.addImage(image: Texture2D?, width: Double = 0.0, height: Double = 0.0, color: Color, inlineAlign: Long = 5L, region: Rect2, key: Any? = null, pad: Boolean = false, tooltip: String = "", widthUnit: Long = 0L, heightUnit: Long = 0L, altText: String = "") {
    ObjectCalls.ptrcallWithObjectTwoDoubleColorLongRect2VariantBoolStringTwoLongStringArgs(addImageBind, handle, image?.requireOpenHandle() ?: MemorySegment.NULL, width, height, color, inlineAlign, region, key, pad, tooltip, widthUnit, heightUnit, altText)
}

/**
 * Updates the existing images with the key `key`. Only properties specified by `mask` bits are
 * updated. See `add_image`.
 *
 * Generated from Godot docs: RichTextLabel.update_image
 */
fun RichTextLabel.updateImage(key: Any?, mask: Long, image: Texture2D?, width: Double = 0.0, height: Double = 0.0, color: Color, inlineAlign: Long = 5L, region: Rect2, pad: Boolean = false, tooltip: String = "", widthUnit: Long = 0L, heightUnit: Long = 0L) {
    ObjectCalls.ptrcallWithVariantLongObjectTwoDoubleColorLongRect2BoolStringTwoLongArgs(updateImageBind, handle, key, mask, image?.requireOpenHandle() ?: MemorySegment.NULL, width, height, color, inlineAlign, region, pad, tooltip, widthUnit, heightUnit)
}

/**
 * Adds a meta tag to the tag stack. Similar to the BBCode `{text} (something)`, but supports
 * non-`String` metadata types. If `meta_underlined` is `true`, meta tags display an underline.
 * This behavior can be customized with `underline_mode`. Note: Meta tags do nothing by default
 * when clicked. To assign behavior when clicked, connect `meta_clicked` to a function that is
 * called when the meta tag is clicked.
 *
 * Generated from Godot docs: RichTextLabel.push_meta
 */
fun RichTextLabel.pushMeta(data: Any?, underlineMode: Long = 1L, tooltip: String = "") {
    ObjectCalls.ptrcallWithVariantLongStringArgs(pushMetaBind, handle, data, underlineMode, tooltip)
}

/**
 * Adds a custom effect tag to the tag stack. The effect does not need to be in `custom_effects`.
 * The environment is directly passed to the effect.
 *
 * Generated from Godot docs: RichTextLabel.push_customfx
 */
fun RichTextLabel.pushCustomfx(effect: RichTextEffect?, env: Map<String, Any?>) {
    ObjectCalls.ptrcallWithObjectAndDictionaryArg(pushCustomfxBind, handle, effect?.requireOpenHandle() ?: MemorySegment.NULL, env)
}

/**
 * Set additional options for BiDi override.
 *
 * Generated from Godot docs: RichTextLabel.set_structured_text_bidi_override_options
 */
fun RichTextLabel.setStructuredTextBidiOverrideOptions(args: List<Any?>) {
    ObjectCalls.ptrcallWithArrayArg(setStructuredTextBidiOverrideOptionsBind, handle, args)
}

/**
 * Parses BBCode parameter `expressions` into a dictionary.
 *
 * Generated from Godot docs: RichTextLabel.parse_expressions_for_values
 */
fun RichTextLabel.parseExpressionsForValues(expressions: List<String>): Map<String, Any?> {
    return ObjectCalls.ptrcallWithPackedStringListArgRetDictionary(parseExpressionsForValuesBind, handle, expressions)
}

/**
 * The currently installed custom effects. This is an array of `RichTextEffect`s. To add a custom
 * effect, it's more convenient to use `install_effect`.
 *
 * Generated from Godot docs: RichTextLabel.set_effects
 */
fun RichTextLabel.setEffects(effects: List<Any?>) {
    ObjectCalls.ptrcallWithArrayArg(setEffectsBind, handle, effects)
}

/**
 * Installs a custom effect. This can also be done in the Inspector through the `custom_effects`
 * property. `effect` should be a valid `RichTextEffect`.
 *
 * Generated from Godot docs: RichTextLabel.install_effect
 */
fun RichTextLabel.installEffect(effect: Any?) {
    ObjectCalls.ptrcallWithVariantArg(installEffectBind, handle, effect)
}

private const val ADD_IMAGE_HASH = 1980227702L
private val addImageBind by lazy {
    ObjectCalls.getMethodBind("RichTextLabel", "add_image", ADD_IMAGE_HASH)
}

private const val UPDATE_IMAGE_HASH = 202998225L
private val updateImageBind by lazy {
    ObjectCalls.getMethodBind("RichTextLabel", "update_image", UPDATE_IMAGE_HASH)
}

private const val PUSH_META_HASH = 3765356747L
private val pushMetaBind by lazy {
    ObjectCalls.getMethodBind("RichTextLabel", "push_meta", PUSH_META_HASH)
}

private const val PUSH_CUSTOMFX_HASH = 2337942958L
private val pushCustomfxBind by lazy {
    ObjectCalls.getMethodBind("RichTextLabel", "push_customfx", PUSH_CUSTOMFX_HASH)
}

private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 381264803L
private val setStructuredTextBidiOverrideOptionsBind by lazy {
    ObjectCalls.getMethodBind("RichTextLabel", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
}

private const val PARSE_EXPRESSIONS_FOR_VALUES_HASH = 1522900837L
private val parseExpressionsForValuesBind by lazy {
    ObjectCalls.getMethodBind("RichTextLabel", "parse_expressions_for_values", PARSE_EXPRESSIONS_FOR_VALUES_HASH)
}

private const val SET_EFFECTS_HASH = 381264803L
private val setEffectsBind by lazy {
    ObjectCalls.getMethodBind("RichTextLabel", "set_effects", SET_EFFECTS_HASH)
}

private const val INSTALL_EFFECT_HASH = 1114965689L
private val installEffectBind by lazy {
    ObjectCalls.getMethodBind("RichTextLabel", "install_effect", INSTALL_EFFECT_HASH)
}
