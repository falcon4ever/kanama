package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2i

/**
 * Image datatype.
 *
 * Generated from Godot docs: Image
 */
class Image(handle: MemorySegment) : Resource(handle) {
    /**
     * Returns the image's width.
     *
     * Generated from Godot docs: Image.get_width
     */
    fun getWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getWidthBind, handle)
    }

    /**
     * Returns the image's height.
     *
     * Generated from Godot docs: Image.get_height
     */
    fun getHeight(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getHeightBind, handle)
    }

    /**
     * Returns the image's size (width and height).
     *
     * Generated from Godot docs: Image.get_size
     */
    fun getSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getSizeBind, handle)
    }

    /**
     * Returns `true` if the image has generated mipmaps.
     *
     * Generated from Godot docs: Image.has_mipmaps
     */
    fun hasMipmaps(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasMipmapsBind, handle)
    }

    /**
     * Returns this image's format.
     *
     * Generated from Godot docs: Image.get_format
     */
    fun getFormat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFormatBind, handle)
    }

    /**
     * Returns a copy of the image's raw data.
     *
     * Generated from Godot docs: Image.get_data
     */
    fun getData(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(getDataBind, handle)
    }

    /**
     * Returns size (in bytes) of the image's raw data.
     *
     * Generated from Godot docs: Image.get_data_size
     */
    fun getDataSize(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDataSizeBind, handle)
    }

    /**
     * Converts this image's format to the given `format`.
     *
     * Generated from Godot docs: Image.convert
     */
    fun convert(format: Long) {
        ObjectCalls.ptrcallWithLongArg(convertBind, handle, format)
    }

    /**
     * Returns the number of mipmap levels or 0 if the image has no mipmaps. The largest main level
     * image is not counted as a mipmap level by this method, so if you want to include it you can add
     * 1 to this count.
     *
     * Generated from Godot docs: Image.get_mipmap_count
     */
    fun getMipmapCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMipmapCountBind, handle)
    }

    /**
     * Returns the offset where the image's mipmap with index `mipmap` is stored in the `data`
     * dictionary.
     *
     * Generated from Godot docs: Image.get_mipmap_offset
     */
    fun getMipmapOffset(mipmap: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getMipmapOffsetBind, handle, mipmap)
    }

    /**
     * Resizes the image to the nearest power of 2 for the width and height. If `square` is `true`,
     * sets width and height to be the same. New pixels are calculated using the `interpolation` mode
     * defined via `Interpolation` constants.
     *
     * Generated from Godot docs: Image.resize_to_po2
     */
    fun resizeToPo2(square: Boolean = false, interpolation: Long = 1L) {
        ObjectCalls.ptrcallWithBoolAndLongArgs(resizeToPo2Bind, handle, square, interpolation)
    }

    /**
     * Resizes the image to the given `width` and `height`. New pixels are calculated using the
     * `interpolation` mode defined via `Interpolation` constants. Note: If the image's format is
     * `FORMAT_RGBA4444`, `FORMAT_RGB565`, or `FORMAT_RGBE9995`, it will be temporarily converted to
     * either `FORMAT_RGBA8` or `FORMAT_RGBAH`. This can affect the quality of the resized image.
     *
     * Generated from Godot docs: Image.resize
     */
    fun resize(width: Int, height: Int, interpolation: Long = 1L) {
        ObjectCalls.ptrcallWithTwoIntAndLongArgs(resizeBind, handle, width, height, interpolation)
    }

    /**
     * Shrinks the image by a factor of 2 on each axis (this divides the pixel count by 4).
     *
     * Generated from Godot docs: Image.shrink_x2
     */
    fun shrinkX2() {
        ObjectCalls.ptrcallNoArgs(shrinkX2Bind, handle)
    }

    /**
     * Crops the image to the given `width` and `height`. If the specified size is larger than the
     * current size, the extra area is filled with black pixels.
     *
     * Generated from Godot docs: Image.crop
     */
    fun crop(width: Int, height: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(cropBind, handle, width, height)
    }

    /**
     * Flips the image horizontally.
     *
     * Generated from Godot docs: Image.flip_x
     */
    fun flipX() {
        ObjectCalls.ptrcallNoArgs(flipXBind, handle)
    }

    /**
     * Flips the image vertically.
     *
     * Generated from Godot docs: Image.flip_y
     */
    fun flipY() {
        ObjectCalls.ptrcallNoArgs(flipYBind, handle)
    }

    /**
     * Generates mipmaps for the image. Mipmaps are precalculated lower-resolution copies of the image
     * that are automatically used if the image needs to be scaled down when rendered. They help
     * improve image quality and performance when rendering. This method returns an error if the image
     * is compressed, in a custom format, or if the image's width/height is `0`. Enabling `renormalize`
     * when generating mipmaps for normal map textures will make sure all resulting vector values are
     * normalized. It is possible to check if the image has mipmaps by calling `has_mipmaps` or
     * `get_mipmap_count`. Calling `generate_mipmaps` on an image that already has mipmaps will replace
     * existing mipmaps in the image.
     *
     * Generated from Godot docs: Image.generate_mipmaps
     */
    fun generateMipmaps(renormalize: Boolean = false): Long {
        return ObjectCalls.ptrcallWithBoolArgRetLong(generateMipmapsBind, handle, renormalize)
    }

    /**
     * Removes the image's mipmaps.
     *
     * Generated from Godot docs: Image.clear_mipmaps
     */
    fun clearMipmaps() {
        ObjectCalls.ptrcallNoArgs(clearMipmapsBind, handle)
    }

    /**
     * Overwrites data of an existing `Image`. Non-static equivalent of `create_from_data`.
     *
     * Generated from Godot docs: Image.set_data
     */
    fun setData(width: Int, height: Int, useMipmaps: Boolean, format: Long, data: ByteArray) {
        ObjectCalls.ptrcallWithTwoIntBoolLongByteArrayArgs(setDataBind, handle, width, height, useMipmaps, format, data)
    }

    /**
     * Returns `true` if the image has no data.
     *
     * Generated from Godot docs: Image.is_empty
     */
    fun isEmpty(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmptyBind, handle)
    }

    /**
     * Loads an image from file `path`. See Supported image formats
     * ($DOCS_URL/tutorials/assets_pipeline/importing_images.html#supported-image-formats) for a list
     * of supported image formats and limitations. Warning: This method should only be used in the
     * editor or in cases when you need to load external images at run-time, such as images located at
     * the `user://` directory, and may not work in exported projects. See also `ImageTexture`
     * description for usage examples.
     *
     * Generated from Godot docs: Image.load
     */
    fun load(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(loadBind, handle, path)
    }

    /**
     * Saves the image as a PNG file to the file at `path`.
     *
     * Generated from Godot docs: Image.save_png
     */
    fun savePng(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(savePngBind, handle, path)
    }

    /**
     * Saves the image as a PNG file to a byte array.
     *
     * Generated from Godot docs: Image.save_png_to_buffer
     */
    fun savePngToBuffer(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(savePngToBufferBind, handle)
    }

    /**
     * Saves the image as a JPEG file to `path` with the specified `quality` between `0.01` and `1.0`
     * (inclusive). Higher `quality` values result in better-looking output at the cost of larger file
     * sizes. Recommended `quality` values are between `0.75` and `0.90`. Even at quality `1.00`, JPEG
     * compression remains lossy. Note: JPEG does not save an alpha channel. If the `Image` contains an
     * alpha channel, the image will still be saved, but the resulting JPEG file won't contain the
     * alpha channel.
     *
     * Generated from Godot docs: Image.save_jpg
     */
    fun saveJpg(path: String, quality: Double = 0.75): Long {
        return ObjectCalls.ptrcallWithStringAndDoubleArgRetLong(saveJpgBind, handle, path, quality)
    }

    /**
     * Saves the image as a JPEG file to a byte array with the specified `quality` between `0.01` and
     * `1.0` (inclusive). Higher `quality` values result in better-looking output at the cost of larger
     * byte array sizes (and therefore memory usage). Recommended `quality` values are between `0.75`
     * and `0.90`. Even at quality `1.00`, JPEG compression remains lossy. Note: JPEG does not save an
     * alpha channel. If the `Image` contains an alpha channel, the image will still be saved, but the
     * resulting byte array won't contain the alpha channel.
     *
     * Generated from Godot docs: Image.save_jpg_to_buffer
     */
    fun saveJpgToBuffer(quality: Double = 0.75): ByteArray {
        return ObjectCalls.ptrcallWithDoubleArgRetByteArray(saveJpgToBufferBind, handle, quality)
    }

    /**
     * Saves the image as an EXR file to `path`. If `grayscale` is `true` and the image has only one
     * channel, it will be saved explicitly as monochrome rather than one red channel. Set
     * `color_image` to `true` when saving a color image, such as a screenshot. Negative values will be
     * included when `color_image` is `false`, which may be useful for saving raw floating point data
     * such as a lightmap that includes negative light information. Color component values in the
     * resulting EXR file will not exceed `max_linear_value` if `max_linear_value` is not negative.
     * This function will return `ERR_UNAVAILABLE` if Godot was compiled without the TinyEXR module.
     * When saving screenshots of a project that uses HDR output, use
     * `Window.get_output_max_linear_value` for `max_linear_value`.
     *
     * Generated from Godot docs: Image.save_exr
     */
    fun saveExr(path: String, grayscale: Boolean = false, colorImage: Boolean = false, maxLinearValue: Double = -1.0): Long {
        return ObjectCalls.ptrcallWithStringTwoBoolAndDoubleArgRetLong(saveExrBind, handle, path, grayscale, colorImage, maxLinearValue)
    }

    /**
     * Saves the image as an EXR file to a byte array. If `grayscale` is `true` and the image has only
     * one channel, it will be saved explicitly as monochrome rather than one red channel. Set
     * `color_image` to `true` when saving a color image, such as a screenshot. Negative values will be
     * included when `color_image` is `false`, which may be useful for saving raw floating point data
     * such as a lightmap that includes negative light information. Color component values in the
     * resulting EXR file will not exceed `max_linear_value` if `max_linear_value` is not negative.
     * This function will return an empty byte array if Godot was compiled without the TinyEXR module.
     * When saving screenshots of a project that uses HDR output, use
     * `Window.get_output_max_linear_value` for `max_linear_value`.
     *
     * Generated from Godot docs: Image.save_exr_to_buffer
     */
    fun saveExrToBuffer(grayscale: Boolean = false, colorImage: Boolean = false, maxLinearValue: Double = -1.0): ByteArray {
        return ObjectCalls.ptrcallWithTwoBoolAndDoubleArgRetByteArray(saveExrToBufferBind, handle, grayscale, colorImage, maxLinearValue)
    }

    /**
     * Saves the image as a DDS (DirectDraw Surface) file to `path`. DDS is a container format that can
     * store textures in various compression formats, such as DXT1, DXT5, or BC7. This function will
     * return `ERR_UNAVAILABLE` if Godot was compiled without the DDS module. Note: The DDS module may
     * be disabled in certain builds, which means `save_dds` will return `ERR_UNAVAILABLE` when it is
     * called from an exported project.
     *
     * Generated from Godot docs: Image.save_dds
     */
    fun saveDds(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(saveDdsBind, handle, path)
    }

    /**
     * Saves the image as a DDS (DirectDraw Surface) file to a byte array. DDS is a container format
     * that can store textures in various compression formats, such as DXT1, DXT5, or BC7. This
     * function will return an empty byte array if Godot was compiled without the DDS module. Note: The
     * DDS module may be disabled in certain builds, which means `save_dds_to_buffer` will return an
     * empty byte array when it is called from an exported project.
     *
     * Generated from Godot docs: Image.save_dds_to_buffer
     */
    fun saveDdsToBuffer(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(saveDdsToBufferBind, handle)
    }

    /**
     * Saves the image as a WebP (Web Picture) file to the file at `path`. By default it will save
     * lossless. If `lossy` is `true`, the image will be saved lossy, using the `quality` setting
     * between `0.0` and `1.0` (inclusive). Lossless WebP offers more efficient compression than PNG.
     * Note: The WebP format is limited to a size of 16383×16383 pixels, while PNG can save larger
     * images.
     *
     * Generated from Godot docs: Image.save_webp
     */
    fun saveWebp(path: String, lossy: Boolean = false, quality: Double = 0.75): Long {
        return ObjectCalls.ptrcallWithStringBoolDoubleArgsRetLong(saveWebpBind, handle, path, lossy, quality)
    }

    /**
     * Saves the image as a WebP (Web Picture) file to a byte array. By default it will save lossless.
     * If `lossy` is `true`, the image will be saved lossy, using the `quality` setting between `0.0`
     * and `1.0` (inclusive). Lossless WebP offers more efficient compression than PNG. Note: The WebP
     * format is limited to a size of 16383×16383 pixels, while PNG can save larger images.
     *
     * Generated from Godot docs: Image.save_webp_to_buffer
     */
    fun saveWebpToBuffer(lossy: Boolean = false, quality: Double = 0.75): ByteArray {
        return ObjectCalls.ptrcallWithBoolAndDoubleArgRetByteArray(saveWebpToBufferBind, handle, lossy, quality)
    }

    /**
     * Returns `ALPHA_BLEND` if the image has data for alpha values. Returns `ALPHA_BIT` if all the
     * alpha values are stored in a single bit. Returns `ALPHA_NONE` if no data for alpha values is
     * found.
     *
     * Generated from Godot docs: Image.detect_alpha
     */
    fun detectAlpha(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(detectAlphaBind, handle)
    }

    /**
     * Returns `true` if all the image's pixels have an alpha value of 0. Returns `false` if any pixel
     * has an alpha value higher than 0.
     *
     * Generated from Godot docs: Image.is_invisible
     */
    fun isInvisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInvisibleBind, handle)
    }

    /**
     * Returns the color channels used by this image. If the image is compressed, the original `source`
     * must be specified.
     *
     * Generated from Godot docs: Image.detect_used_channels
     */
    fun detectUsedChannels(source: Long = 0L): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(detectUsedChannelsBind, handle, source)
    }

    /**
     * Compresses the image with a VRAM-compressed format to use less memory. Can not directly access
     * pixel data while the image is compressed. Returns error if the chosen compression mode is not
     * available. The `source` parameter helps to pick the best compression method for DXT and ETC2
     * formats. It is ignored for ASTC compression. The `astc_format` parameter is only taken into
     * account when using ASTC compression; it is ignored for all other formats. Note: `compress` is
     * only supported in editor builds. When run in an exported project, this method always returns
     * `ERR_UNAVAILABLE`.
     *
     * Generated from Godot docs: Image.compress
     */
    fun compress(mode: Long, source: Long = 0L, astcFormat: Long = 0L): Long {
        return ObjectCalls.ptrcallWithThreeLongArgsRetLong(compressBind, handle, mode, source, astcFormat)
    }

    /**
     * Compresses the image with a VRAM-compressed format to use less memory. Can not directly access
     * pixel data while the image is compressed. Returns error if the chosen compression mode is not
     * available. This is an alternative to `compress` that lets the user supply the channels used in
     * order for the compressor to pick the best DXT and ETC2 formats. For other formats (non DXT or
     * ETC2), this argument is ignored. The `astc_format` parameter is only taken into account when
     * using ASTC compression; it is ignored for all other formats. Note: `compress_from_channels` is
     * only supported in editor builds. When run in an exported project, this method always returns
     * `ERR_UNAVAILABLE`.
     *
     * Generated from Godot docs: Image.compress_from_channels
     */
    fun compressFromChannels(mode: Long, channels: Long, astcFormat: Long = 0L): Long {
        return ObjectCalls.ptrcallWithThreeLongArgsRetLong(compressFromChannelsBind, handle, mode, channels, astcFormat)
    }

    /**
     * Decompresses the image if it is VRAM-compressed in a supported format. This increases memory
     * utilization, but allows modifying the image. Returns `OK` if the format is supported, otherwise
     * `ERR_UNAVAILABLE`. All VRAM-compressed formats supported by Godot can be decompressed with this
     * method, except `FORMAT_ETC2_R11S`, `FORMAT_ETC2_RG11S`, and `FORMAT_ETC2_RGB8A1`.
     *
     * Generated from Godot docs: Image.decompress
     */
    fun decompress(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(decompressBind, handle)
    }

    /**
     * Returns `true` if the image is compressed.
     *
     * Generated from Godot docs: Image.is_compressed
     */
    fun isCompressed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCompressedBind, handle)
    }

    /**
     * Rotates the image in the specified `direction` by `90` degrees. The width and height of the
     * image must be greater than `1`. If the width and height are not equal, the image will be
     * resized.
     *
     * Generated from Godot docs: Image.rotate_90
     */
    fun rotate90(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(rotate90Bind, handle, direction)
    }

    /**
     * Rotates the image by `180` degrees. The width and height of the image must be greater than `1`.
     *
     * Generated from Godot docs: Image.rotate_180
     */
    fun rotate180() {
        ObjectCalls.ptrcallNoArgs(rotate180Bind, handle)
    }

    /**
     * Blends low-alpha pixels with nearby pixels.
     *
     * Generated from Godot docs: Image.fix_alpha_edges
     */
    fun fixAlphaEdges() {
        ObjectCalls.ptrcallNoArgs(fixAlphaEdgesBind, handle)
    }

    /**
     * Multiplies color values with alpha values. Resulting color values for a pixel are `(color *
     * alpha)/256`. See also `CanvasItemMaterial.blend_mode`.
     *
     * Generated from Godot docs: Image.premultiply_alpha
     */
    fun premultiplyAlpha() {
        ObjectCalls.ptrcallNoArgs(premultiplyAlphaBind, handle)
    }

    /**
     * Converts the raw data from nonlinear sRGB encoding to linear encoding using a lookup table. Only
     * works on images with `FORMAT_RGB8` or `FORMAT_RGBA8` formats. Note: The 8-bit formats required
     * by this method are not suitable for storing linearly encoded values; a significant amount of
     * color information will be lost in darker values. To maintain image quality, this method should
     * not be used.
     *
     * Generated from Godot docs: Image.srgb_to_linear
     */
    fun srgbToLinear() {
        ObjectCalls.ptrcallNoArgs(srgbToLinearBind, handle)
    }

    /**
     * Converts the entire image from linear encoding to nonlinear sRGB encoding by using a lookup
     * table. Only works on images with `FORMAT_RGB8` or `FORMAT_RGBA8` formats.
     *
     * Generated from Godot docs: Image.linear_to_srgb
     */
    fun linearToSrgb() {
        ObjectCalls.ptrcallNoArgs(linearToSrgbBind, handle)
    }

    /**
     * Converts the image's data to represent coordinates on a 3D plane. This is used when the image
     * represents a normal map. A normal map can add lots of detail to a 3D surface without increasing
     * the polygon count.
     *
     * Generated from Godot docs: Image.normal_map_to_xy
     */
    fun normalMapToXy() {
        ObjectCalls.ptrcallNoArgs(normalMapToXyBind, handle)
    }

    /**
     * Converts a standard linear RGBE (Red Green Blue Exponent) image to an image that uses nonlinear
     * sRGB encoding.
     *
     * Generated from Godot docs: Image.rgbe_to_srgb
     */
    fun rgbeToSrgb(): Image? {
        return Image.wrap(ObjectCalls.ptrcallNoArgsRetObject(rgbeToSrgbBind, handle))
    }

    /**
     * Converts a bump map to a normal map. A bump map provides a height offset per-pixel, while a
     * normal map provides a normal direction per pixel.
     *
     * Generated from Godot docs: Image.bump_map_to_normal_map
     */
    fun bumpMapToNormalMap(bumpScale: Double = 1.0) {
        ObjectCalls.ptrcallWithDoubleArg(bumpMapToNormalMapBind, handle, bumpScale)
    }

    /**
     * Compute image metrics on the current image and the compared image. This can be used to calculate
     * the similarity between two images. The dictionary contains `max`, `mean`, `mean_squared`,
     * `root_mean_squared` and `peak_snr`.
     *
     * Generated from Godot docs: Image.compute_image_metrics
     */
    fun computeImageMetrics(comparedImage: Image?, useLuma: Boolean): Map<String, Any?> {
        return ObjectCalls.ptrcallWithObjectAndBoolArgRetDictionary(computeImageMetricsBind, handle, comparedImage?.requireOpenHandle() ?: MemorySegment.NULL, useLuma)
    }

    /**
     * Copies `src_rect` from `src` image to this image at coordinates `dst`, clipped accordingly to
     * both image bounds. This image and `src` image must have the same format. `src_rect` with
     * non-positive size is treated as empty. Note: The alpha channel data in `src` will overwrite the
     * corresponding data in this image at the target position. To blend alpha channels, use
     * `blend_rect` instead.
     *
     * Generated from Godot docs: Image.blit_rect
     */
    fun blitRect(src: Image?, srcRect: Rect2i, dst: Vector2i) {
        ObjectCalls.ptrcallWithObjectRect2iAndVector2iArgs(blitRectBind, handle, src?.requireOpenHandle() ?: MemorySegment.NULL, srcRect, dst)
    }

    /**
     * Blits `src_rect` area from `src` image to this image at the coordinates given by `dst`, clipped
     * accordingly to both image bounds. `src` pixel is copied onto `dst` if the corresponding `mask`
     * pixel's alpha value is not 0. This image and `src` image must have the same format. `src` image
     * and `mask` image must have the same size (width and height) but they can have different formats.
     * `src_rect` with non-positive size is treated as empty.
     *
     * Generated from Godot docs: Image.blit_rect_mask
     */
    fun blitRectMask(src: Image?, mask: Image?, srcRect: Rect2i, dst: Vector2i) {
        ObjectCalls.ptrcallWithTwoObjectRect2iAndVector2iArgs(blitRectMaskBind, handle, src?.requireOpenHandle() ?: MemorySegment.NULL, mask?.requireOpenHandle() ?: MemorySegment.NULL, srcRect, dst)
    }

    /**
     * Alpha-blends `src_rect` from `src` image to this image at coordinates `dst`, clipped accordingly
     * to both image bounds. This image and `src` image must have the same format. `src_rect` with
     * non-positive size is treated as empty.
     *
     * Generated from Godot docs: Image.blend_rect
     */
    fun blendRect(src: Image?, srcRect: Rect2i, dst: Vector2i) {
        ObjectCalls.ptrcallWithObjectRect2iAndVector2iArgs(blendRectBind, handle, src?.requireOpenHandle() ?: MemorySegment.NULL, srcRect, dst)
    }

    /**
     * Alpha-blends `src_rect` from `src` image to this image using `mask` image at coordinates `dst`,
     * clipped accordingly to both image bounds. Alpha channels are required for both `src` and `mask`.
     * `dst` pixels and `src` pixels will blend if the corresponding mask pixel's alpha value is not 0.
     * This image and `src` image must have the same format. `src` image and `mask` image must have the
     * same size (width and height) but they can have different formats. `src_rect` with non-positive
     * size is treated as empty.
     *
     * Generated from Godot docs: Image.blend_rect_mask
     */
    fun blendRectMask(src: Image?, mask: Image?, srcRect: Rect2i, dst: Vector2i) {
        ObjectCalls.ptrcallWithTwoObjectRect2iAndVector2iArgs(blendRectMaskBind, handle, src?.requireOpenHandle() ?: MemorySegment.NULL, mask?.requireOpenHandle() ?: MemorySegment.NULL, srcRect, dst)
    }

    /**
     * Fills the image with `color`.
     *
     * Generated from Godot docs: Image.fill
     */
    fun fill(color: Color) {
        ObjectCalls.ptrcallWithColorArg(fillBind, handle, color)
    }

    /**
     * Fills `rect` with `color`.
     *
     * Generated from Godot docs: Image.fill_rect
     */
    fun fillRect(rect: Rect2i, color: Color) {
        ObjectCalls.ptrcallWithRect2iAndColorArg(fillRectBind, handle, rect, color)
    }

    /**
     * Returns a `Rect2i` enclosing the visible portion of the image, considering each pixel with a
     * non-zero alpha channel as visible.
     *
     * Generated from Godot docs: Image.get_used_rect
     */
    fun getUsedRect(): Rect2i {
        return ObjectCalls.ptrcallNoArgsRetRect2i(getUsedRectBind, handle)
    }

    /**
     * Returns a new `Image` that is a copy of this `Image`'s area specified with `region`.
     *
     * Generated from Godot docs: Image.get_region
     */
    fun getRegion(region: Rect2i): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithRect2iArgRetObject(getRegionBind, handle, region))
    }

    /**
     * Copies `src` image to this image.
     *
     * Generated from Godot docs: Image.copy_from
     */
    fun copyFrom(src: Image?) {
        ObjectCalls.ptrcallWithObjectArgs(copyFromBind, handle, listOf(src?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Returns the color of the pixel at `point`. This is the same as `get_pixel`, but with a
     * `Vector2i` argument instead of two integer arguments.
     *
     * Generated from Godot docs: Image.get_pixelv
     */
    fun getPixelv(point: Vector2i): Color {
        return ObjectCalls.ptrcallWithVector2iArgRetColor(getPixelvBind, handle, point)
    }

    /**
     * Returns the color of the pixel at `(x, y)`. This is the same as `get_pixelv`, but with two
     * integer arguments instead of a `Vector2i` argument.
     *
     * Generated from Godot docs: Image.get_pixel
     */
    fun getPixel(x: Int, y: Int): Color {
        return ObjectCalls.ptrcallWithTwoIntArgsRetColor(getPixelBind, handle, x, y)
    }

    /**
     * Sets the `Color` of the pixel at `point` to `color`.
     *
     * Generated from Godot docs: Image.set_pixelv
     */
    fun setPixelv(point: Vector2i, color: Color) {
        ObjectCalls.ptrcallWithVector2iAndColorArg(setPixelvBind, handle, point, color)
    }

    /**
     * Sets the `Color` of the pixel at `(x, y)` to `color`.
     *
     * Generated from Godot docs: Image.set_pixel
     */
    fun setPixel(x: Int, y: Int, color: Color) {
        ObjectCalls.ptrcallWithTwoIntAndColorArg(setPixelBind, handle, x, y, color)
    }

    /**
     * Adjusts this image's `brightness`, `contrast`, and `saturation` by the given values. Does not
     * work if the image is compressed (see `is_compressed`).
     *
     * Generated from Godot docs: Image.adjust_bcs
     */
    fun adjustBcs(brightness: Double, contrast: Double, saturation: Double) {
        ObjectCalls.ptrcallWithThreeDoubleArgs(adjustBcsBind, handle, brightness, contrast, saturation)
    }

    /**
     * Loads an image from the binary contents of a PNG file.
     *
     * Generated from Godot docs: Image.load_png_from_buffer
     */
    fun loadPngFromBuffer(buffer: ByteArray): Long {
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(loadPngFromBufferBind, handle, buffer)
    }

    /**
     * Loads an image from the binary contents of a JPEG file.
     *
     * Generated from Godot docs: Image.load_jpg_from_buffer
     */
    fun loadJpgFromBuffer(buffer: ByteArray): Long {
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(loadJpgFromBufferBind, handle, buffer)
    }

    /**
     * Loads an image from the binary contents of a WebP file.
     *
     * Generated from Godot docs: Image.load_webp_from_buffer
     */
    fun loadWebpFromBuffer(buffer: ByteArray): Long {
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(loadWebpFromBufferBind, handle, buffer)
    }

    /**
     * Loads an image from the binary contents of a TGA file. Note: This method is only available in
     * engine builds with the TGA module enabled. By default, the TGA module is enabled, but it can be
     * disabled at build-time using the `module_tga_enabled=no` SCons option.
     *
     * Generated from Godot docs: Image.load_tga_from_buffer
     */
    fun loadTgaFromBuffer(buffer: ByteArray): Long {
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(loadTgaFromBufferBind, handle, buffer)
    }

    /**
     * Loads an image from the binary contents of a BMP file. Note: Godot's BMP module doesn't support
     * 16-bit per pixel images. Only 1-bit, 4-bit, 8-bit, 24-bit, and 32-bit per pixel images are
     * supported. Note: This method is only available in engine builds with the BMP module enabled. By
     * default, the BMP module is enabled, but it can be disabled at build-time using the
     * `module_bmp_enabled=no` SCons option.
     *
     * Generated from Godot docs: Image.load_bmp_from_buffer
     */
    fun loadBmpFromBuffer(buffer: ByteArray): Long {
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(loadBmpFromBufferBind, handle, buffer)
    }

    /**
     * Loads an image from the binary contents of a KTX (https://github.com/KhronosGroup/KTX-Software)
     * file. Unlike most image formats, KTX can store VRAM-compressed data and embed mipmaps. Note:
     * Godot's libktx implementation only supports 2D images. Cubemaps, texture arrays, and de-padding
     * are not supported. Note: This method is only available in engine builds with the KTX module
     * enabled. By default, the KTX module is enabled, but it can be disabled at build-time using the
     * `module_ktx_enabled=no` SCons option.
     *
     * Generated from Godot docs: Image.load_ktx_from_buffer
     */
    fun loadKtxFromBuffer(buffer: ByteArray): Long {
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(loadKtxFromBufferBind, handle, buffer)
    }

    /**
     * Loads an image from the binary contents of a DDS file. Note: This method is only available in
     * engine builds with the DDS module enabled. By default, the DDS module is enabled, but it can be
     * disabled at build-time using the `module_dds_enabled=no` SCons option.
     *
     * Generated from Godot docs: Image.load_dds_from_buffer
     */
    fun loadDdsFromBuffer(buffer: ByteArray): Long {
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(loadDdsFromBufferBind, handle, buffer)
    }

    /**
     * Loads an image from the binary contents of an OpenEXR file.
     *
     * Generated from Godot docs: Image.load_exr_from_buffer
     */
    fun loadExrFromBuffer(buffer: ByteArray): Long {
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(loadExrFromBufferBind, handle, buffer)
    }

    /**
     * Loads an image from the UTF-8 binary contents of an uncompressed SVG file (.svg). Note: Beware
     * when using compressed SVG files (like .svgz), they need to be `decompressed` before loading.
     * Note: This method is only available in engine builds with the SVG module enabled. By default,
     * the SVG module is enabled, but it can be disabled at build-time using the
     * `module_svg_enabled=no` SCons option.
     *
     * Generated from Godot docs: Image.load_svg_from_buffer
     */
    fun loadSvgFromBuffer(buffer: ByteArray, scale: Double = 1.0): Long {
        return ObjectCalls.ptrcallWithByteArrayAndDoubleArgRetLong(loadSvgFromBufferBind, handle, buffer, scale)
    }

    /**
     * Loads an image from the string contents of an SVG file (.svg). Note: This method is only
     * available in engine builds with the SVG module enabled. By default, the SVG module is enabled,
     * but it can be disabled at build-time using the `module_svg_enabled=no` SCons option.
     *
     * Generated from Godot docs: Image.load_svg_from_string
     */
    fun loadSvgFromString(svgStr: String, scale: Double = 1.0): Long {
        return ObjectCalls.ptrcallWithStringAndDoubleArgRetLong(loadSvgFromStringBind, handle, svgStr, scale)
    }

    companion object {
        /**
         * Creates an empty image of the given size and format. If `use_mipmaps` is `true`, generates
         * mipmaps for this image (see `generate_mipmaps`).
         *
         * Generated from Godot docs: Image.create
         */
        fun create(width: Int, height: Int, useMipmaps: Boolean, format: Long): Image? {
            return Image.wrap(ObjectCalls.ptrcallWithTwoIntBoolLongArgsRetObject(createBind, MemorySegment.NULL, width, height, useMipmaps, format))
        }

        /**
         * Creates an empty image of the given size and format. If `use_mipmaps` is `true`, generates
         * mipmaps for this image (see `generate_mipmaps`).
         *
         * Generated from Godot docs: Image.create_empty
         */
        fun createEmpty(width: Int, height: Int, useMipmaps: Boolean, format: Long): Image? {
            return Image.wrap(ObjectCalls.ptrcallWithTwoIntBoolLongArgsRetObject(createEmptyBind, MemorySegment.NULL, width, height, useMipmaps, format))
        }

        /**
         * Creates a new image of the given size and format. Fills the image with the given raw data. If
         * `use_mipmaps` is `true`, loads the mipmaps for this image from `data`. See `generate_mipmaps`.
         *
         * Generated from Godot docs: Image.create_from_data
         */
        fun createFromData(width: Int, height: Int, useMipmaps: Boolean, format: Long, data: ByteArray): Image? {
            return Image.wrap(ObjectCalls.ptrcallWithTwoIntBoolLongByteArrayArgsRetObject(createFromDataBind, MemorySegment.NULL, width, height, useMipmaps, format, data))
        }

        /**
         * Creates a new `Image` and loads data from the specified file.
         *
         * Generated from Godot docs: Image.load_from_file
         */
        fun loadFromFile(path: String): Image? {
            return Image.wrap(ObjectCalls.ptrcallWithStringArgRetObject(loadFromFileBind, MemorySegment.NULL, path))
        }

        const val MAX_WIDTH: Long = 16777216L
        const val MAX_HEIGHT: Long = 16777216L
        const val FORMAT_L8: Long = 0L
        const val FORMAT_LA8: Long = 1L
        const val FORMAT_R8: Long = 2L
        const val FORMAT_RG8: Long = 3L
        const val FORMAT_RGB8: Long = 4L
        const val FORMAT_RGBA8: Long = 5L
        const val FORMAT_RGBA4444: Long = 6L
        const val FORMAT_RGB565: Long = 7L
        const val FORMAT_RF: Long = 8L
        const val FORMAT_RGF: Long = 9L
        const val FORMAT_RGBF: Long = 10L
        const val FORMAT_RGBAF: Long = 11L
        const val FORMAT_RH: Long = 12L
        const val FORMAT_RGH: Long = 13L
        const val FORMAT_RGBH: Long = 14L
        const val FORMAT_RGBAH: Long = 15L
        const val FORMAT_RGBE9995: Long = 16L
        const val FORMAT_DXT1: Long = 17L
        const val FORMAT_DXT3: Long = 18L
        const val FORMAT_DXT5: Long = 19L
        const val FORMAT_RGTC_R: Long = 20L
        const val FORMAT_RGTC_RG: Long = 21L
        const val FORMAT_BPTC_RGBA: Long = 22L
        const val FORMAT_BPTC_RGBF: Long = 23L
        const val FORMAT_BPTC_RGBFU: Long = 24L
        const val FORMAT_ETC: Long = 25L
        const val FORMAT_ETC2_R11: Long = 26L
        const val FORMAT_ETC2_R11S: Long = 27L
        const val FORMAT_ETC2_RG11: Long = 28L
        const val FORMAT_ETC2_RG11S: Long = 29L
        const val FORMAT_ETC2_RGB8: Long = 30L
        const val FORMAT_ETC2_RGBA8: Long = 31L
        const val FORMAT_ETC2_RGB8A1: Long = 32L
        const val FORMAT_ETC2_RA_AS_RG: Long = 33L
        const val FORMAT_DXT5_RA_AS_RG: Long = 34L
        const val FORMAT_ASTC_4x4: Long = 35L
        const val FORMAT_ASTC_4x4_HDR: Long = 36L
        const val FORMAT_ASTC_8x8: Long = 37L
        const val FORMAT_ASTC_8x8_HDR: Long = 38L
        const val FORMAT_R16: Long = 39L
        const val FORMAT_RG16: Long = 40L
        const val FORMAT_RGB16: Long = 41L
        const val FORMAT_RGBA16: Long = 42L
        const val FORMAT_R16I: Long = 43L
        const val FORMAT_RG16I: Long = 44L
        const val FORMAT_RGB16I: Long = 45L
        const val FORMAT_RGBA16I: Long = 46L
        const val FORMAT_MAX: Long = 47L
        const val INTERPOLATE_NEAREST: Long = 0L
        const val INTERPOLATE_BILINEAR: Long = 1L
        const val INTERPOLATE_CUBIC: Long = 2L
        const val INTERPOLATE_TRILINEAR: Long = 3L
        const val INTERPOLATE_LANCZOS: Long = 4L
        const val ALPHA_NONE: Long = 0L
        const val ALPHA_BIT: Long = 1L
        const val ALPHA_BLEND: Long = 2L
        const val COMPRESS_S3TC: Long = 0L
        const val COMPRESS_ETC: Long = 1L
        const val COMPRESS_ETC2: Long = 2L
        const val COMPRESS_BPTC: Long = 3L
        const val COMPRESS_ASTC: Long = 4L
        const val COMPRESS_MAX: Long = 5L
        const val USED_CHANNELS_L: Long = 0L
        const val USED_CHANNELS_LA: Long = 1L
        const val USED_CHANNELS_R: Long = 2L
        const val USED_CHANNELS_RG: Long = 3L
        const val USED_CHANNELS_RGB: Long = 4L
        const val USED_CHANNELS_RGBA: Long = 5L
        const val COMPRESS_SOURCE_GENERIC: Long = 0L
        const val COMPRESS_SOURCE_SRGB: Long = 1L
        const val COMPRESS_SOURCE_NORMAL: Long = 2L
        const val ASTC_FORMAT_4x4: Long = 0L
        const val ASTC_FORMAT_8x8: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Image? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Image? =
            if (handle.address() == 0L) null else Image(handle)

        private const val GET_WIDTH_HASH = 3905245786L
        private val getWidthBind by lazy {
            ObjectCalls.getMethodBind("Image", "get_width", GET_WIDTH_HASH)
        }

        private const val GET_HEIGHT_HASH = 3905245786L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("Image", "get_height", GET_HEIGHT_HASH)
        }

        private const val GET_SIZE_HASH = 3690982128L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("Image", "get_size", GET_SIZE_HASH)
        }

        private const val HAS_MIPMAPS_HASH = 36873697L
        private val hasMipmapsBind by lazy {
            ObjectCalls.getMethodBind("Image", "has_mipmaps", HAS_MIPMAPS_HASH)
        }

        private const val GET_FORMAT_HASH = 3847873762L
        private val getFormatBind by lazy {
            ObjectCalls.getMethodBind("Image", "get_format", GET_FORMAT_HASH)
        }

        private const val GET_DATA_HASH = 2362200018L
        private val getDataBind by lazy {
            ObjectCalls.getMethodBind("Image", "get_data", GET_DATA_HASH)
        }

        private const val GET_DATA_SIZE_HASH = 3905245786L
        private val getDataSizeBind by lazy {
            ObjectCalls.getMethodBind("Image", "get_data_size", GET_DATA_SIZE_HASH)
        }

        private const val CONVERT_HASH = 2120693146L
        private val convertBind by lazy {
            ObjectCalls.getMethodBind("Image", "convert", CONVERT_HASH)
        }

        private const val GET_MIPMAP_COUNT_HASH = 3905245786L
        private val getMipmapCountBind by lazy {
            ObjectCalls.getMethodBind("Image", "get_mipmap_count", GET_MIPMAP_COUNT_HASH)
        }

        private const val GET_MIPMAP_OFFSET_HASH = 923996154L
        private val getMipmapOffsetBind by lazy {
            ObjectCalls.getMethodBind("Image", "get_mipmap_offset", GET_MIPMAP_OFFSET_HASH)
        }

        private const val RESIZE_TO_PO2_HASH = 4189212329L
        private val resizeToPo2Bind by lazy {
            ObjectCalls.getMethodBind("Image", "resize_to_po2", RESIZE_TO_PO2_HASH)
        }

        private const val RESIZE_HASH = 994498151L
        private val resizeBind by lazy {
            ObjectCalls.getMethodBind("Image", "resize", RESIZE_HASH)
        }

        private const val SHRINK_X2_HASH = 3218959716L
        private val shrinkX2Bind by lazy {
            ObjectCalls.getMethodBind("Image", "shrink_x2", SHRINK_X2_HASH)
        }

        private const val CROP_HASH = 3937882851L
        private val cropBind by lazy {
            ObjectCalls.getMethodBind("Image", "crop", CROP_HASH)
        }

        private const val FLIP_X_HASH = 3218959716L
        private val flipXBind by lazy {
            ObjectCalls.getMethodBind("Image", "flip_x", FLIP_X_HASH)
        }

        private const val FLIP_Y_HASH = 3218959716L
        private val flipYBind by lazy {
            ObjectCalls.getMethodBind("Image", "flip_y", FLIP_Y_HASH)
        }

        private const val GENERATE_MIPMAPS_HASH = 1633102583L
        private val generateMipmapsBind by lazy {
            ObjectCalls.getMethodBind("Image", "generate_mipmaps", GENERATE_MIPMAPS_HASH)
        }

        private const val CLEAR_MIPMAPS_HASH = 3218959716L
        private val clearMipmapsBind by lazy {
            ObjectCalls.getMethodBind("Image", "clear_mipmaps", CLEAR_MIPMAPS_HASH)
        }

        private const val CREATE_HASH = 986942177L
        private val createBind by lazy {
            ObjectCalls.getMethodBind("Image", "create", CREATE_HASH)
        }

        private const val CREATE_EMPTY_HASH = 986942177L
        private val createEmptyBind by lazy {
            ObjectCalls.getMethodBind("Image", "create_empty", CREATE_EMPTY_HASH)
        }

        private const val CREATE_FROM_DATA_HASH = 299398494L
        private val createFromDataBind by lazy {
            ObjectCalls.getMethodBind("Image", "create_from_data", CREATE_FROM_DATA_HASH)
        }

        private const val SET_DATA_HASH = 2740482212L
        private val setDataBind by lazy {
            ObjectCalls.getMethodBind("Image", "set_data", SET_DATA_HASH)
        }

        private const val IS_EMPTY_HASH = 36873697L
        private val isEmptyBind by lazy {
            ObjectCalls.getMethodBind("Image", "is_empty", IS_EMPTY_HASH)
        }

        private const val LOAD_HASH = 166001499L
        private val loadBind by lazy {
            ObjectCalls.getMethodBind("Image", "load", LOAD_HASH)
        }

        private const val LOAD_FROM_FILE_HASH = 736337515L
        private val loadFromFileBind by lazy {
            ObjectCalls.getMethodBind("Image", "load_from_file", LOAD_FROM_FILE_HASH)
        }

        private const val SAVE_PNG_HASH = 2113323047L
        private val savePngBind by lazy {
            ObjectCalls.getMethodBind("Image", "save_png", SAVE_PNG_HASH)
        }

        private const val SAVE_PNG_TO_BUFFER_HASH = 2362200018L
        private val savePngToBufferBind by lazy {
            ObjectCalls.getMethodBind("Image", "save_png_to_buffer", SAVE_PNG_TO_BUFFER_HASH)
        }

        private const val SAVE_JPG_HASH = 2800019068L
        private val saveJpgBind by lazy {
            ObjectCalls.getMethodBind("Image", "save_jpg", SAVE_JPG_HASH)
        }

        private const val SAVE_JPG_TO_BUFFER_HASH = 592235273L
        private val saveJpgToBufferBind by lazy {
            ObjectCalls.getMethodBind("Image", "save_jpg_to_buffer", SAVE_JPG_TO_BUFFER_HASH)
        }

        private const val SAVE_EXR_HASH = 2018602448L
        private val saveExrBind by lazy {
            ObjectCalls.getMethodBind("Image", "save_exr", SAVE_EXR_HASH)
        }

        private const val SAVE_EXR_TO_BUFFER_HASH = 1477518536L
        private val saveExrToBufferBind by lazy {
            ObjectCalls.getMethodBind("Image", "save_exr_to_buffer", SAVE_EXR_TO_BUFFER_HASH)
        }

        private const val SAVE_DDS_HASH = 2113323047L
        private val saveDdsBind by lazy {
            ObjectCalls.getMethodBind("Image", "save_dds", SAVE_DDS_HASH)
        }

        private const val SAVE_DDS_TO_BUFFER_HASH = 2362200018L
        private val saveDdsToBufferBind by lazy {
            ObjectCalls.getMethodBind("Image", "save_dds_to_buffer", SAVE_DDS_TO_BUFFER_HASH)
        }

        private const val SAVE_WEBP_HASH = 2781156876L
        private val saveWebpBind by lazy {
            ObjectCalls.getMethodBind("Image", "save_webp", SAVE_WEBP_HASH)
        }

        private const val SAVE_WEBP_TO_BUFFER_HASH = 1214628238L
        private val saveWebpToBufferBind by lazy {
            ObjectCalls.getMethodBind("Image", "save_webp_to_buffer", SAVE_WEBP_TO_BUFFER_HASH)
        }

        private const val DETECT_ALPHA_HASH = 2030116505L
        private val detectAlphaBind by lazy {
            ObjectCalls.getMethodBind("Image", "detect_alpha", DETECT_ALPHA_HASH)
        }

        private const val IS_INVISIBLE_HASH = 36873697L
        private val isInvisibleBind by lazy {
            ObjectCalls.getMethodBind("Image", "is_invisible", IS_INVISIBLE_HASH)
        }

        private const val DETECT_USED_CHANNELS_HASH = 2703139984L
        private val detectUsedChannelsBind by lazy {
            ObjectCalls.getMethodBind("Image", "detect_used_channels", DETECT_USED_CHANNELS_HASH)
        }

        private const val COMPRESS_HASH = 2975424957L
        private val compressBind by lazy {
            ObjectCalls.getMethodBind("Image", "compress", COMPRESS_HASH)
        }

        private const val COMPRESS_FROM_CHANNELS_HASH = 4212890953L
        private val compressFromChannelsBind by lazy {
            ObjectCalls.getMethodBind("Image", "compress_from_channels", COMPRESS_FROM_CHANNELS_HASH)
        }

        private const val DECOMPRESS_HASH = 166280745L
        private val decompressBind by lazy {
            ObjectCalls.getMethodBind("Image", "decompress", DECOMPRESS_HASH)
        }

        private const val IS_COMPRESSED_HASH = 36873697L
        private val isCompressedBind by lazy {
            ObjectCalls.getMethodBind("Image", "is_compressed", IS_COMPRESSED_HASH)
        }

        private const val ROTATE_90_HASH = 1901204267L
        private val rotate90Bind by lazy {
            ObjectCalls.getMethodBind("Image", "rotate_90", ROTATE_90_HASH)
        }

        private const val ROTATE_180_HASH = 3218959716L
        private val rotate180Bind by lazy {
            ObjectCalls.getMethodBind("Image", "rotate_180", ROTATE_180_HASH)
        }

        private const val FIX_ALPHA_EDGES_HASH = 3218959716L
        private val fixAlphaEdgesBind by lazy {
            ObjectCalls.getMethodBind("Image", "fix_alpha_edges", FIX_ALPHA_EDGES_HASH)
        }

        private const val PREMULTIPLY_ALPHA_HASH = 3218959716L
        private val premultiplyAlphaBind by lazy {
            ObjectCalls.getMethodBind("Image", "premultiply_alpha", PREMULTIPLY_ALPHA_HASH)
        }

        private const val SRGB_TO_LINEAR_HASH = 3218959716L
        private val srgbToLinearBind by lazy {
            ObjectCalls.getMethodBind("Image", "srgb_to_linear", SRGB_TO_LINEAR_HASH)
        }

        private const val LINEAR_TO_SRGB_HASH = 3218959716L
        private val linearToSrgbBind by lazy {
            ObjectCalls.getMethodBind("Image", "linear_to_srgb", LINEAR_TO_SRGB_HASH)
        }

        private const val NORMAL_MAP_TO_XY_HASH = 3218959716L
        private val normalMapToXyBind by lazy {
            ObjectCalls.getMethodBind("Image", "normal_map_to_xy", NORMAL_MAP_TO_XY_HASH)
        }

        private const val RGBE_TO_SRGB_HASH = 564927088L
        private val rgbeToSrgbBind by lazy {
            ObjectCalls.getMethodBind("Image", "rgbe_to_srgb", RGBE_TO_SRGB_HASH)
        }

        private const val BUMP_MAP_TO_NORMAL_MAP_HASH = 3423495036L
        private val bumpMapToNormalMapBind by lazy {
            ObjectCalls.getMethodBind("Image", "bump_map_to_normal_map", BUMP_MAP_TO_NORMAL_MAP_HASH)
        }

        private const val COMPUTE_IMAGE_METRICS_HASH = 3080961247L
        private val computeImageMetricsBind by lazy {
            ObjectCalls.getMethodBind("Image", "compute_image_metrics", COMPUTE_IMAGE_METRICS_HASH)
        }

        private const val BLIT_RECT_HASH = 2903928755L
        private val blitRectBind by lazy {
            ObjectCalls.getMethodBind("Image", "blit_rect", BLIT_RECT_HASH)
        }

        private const val BLIT_RECT_MASK_HASH = 3383581145L
        private val blitRectMaskBind by lazy {
            ObjectCalls.getMethodBind("Image", "blit_rect_mask", BLIT_RECT_MASK_HASH)
        }

        private const val BLEND_RECT_HASH = 2903928755L
        private val blendRectBind by lazy {
            ObjectCalls.getMethodBind("Image", "blend_rect", BLEND_RECT_HASH)
        }

        private const val BLEND_RECT_MASK_HASH = 3383581145L
        private val blendRectMaskBind by lazy {
            ObjectCalls.getMethodBind("Image", "blend_rect_mask", BLEND_RECT_MASK_HASH)
        }

        private const val FILL_HASH = 2920490490L
        private val fillBind by lazy {
            ObjectCalls.getMethodBind("Image", "fill", FILL_HASH)
        }

        private const val FILL_RECT_HASH = 514693913L
        private val fillRectBind by lazy {
            ObjectCalls.getMethodBind("Image", "fill_rect", FILL_RECT_HASH)
        }

        private const val GET_USED_RECT_HASH = 410525958L
        private val getUsedRectBind by lazy {
            ObjectCalls.getMethodBind("Image", "get_used_rect", GET_USED_RECT_HASH)
        }

        private const val GET_REGION_HASH = 2601441065L
        private val getRegionBind by lazy {
            ObjectCalls.getMethodBind("Image", "get_region", GET_REGION_HASH)
        }

        private const val COPY_FROM_HASH = 532598488L
        private val copyFromBind by lazy {
            ObjectCalls.getMethodBind("Image", "copy_from", COPY_FROM_HASH)
        }

        private const val GET_PIXELV_HASH = 1532707496L
        private val getPixelvBind by lazy {
            ObjectCalls.getMethodBind("Image", "get_pixelv", GET_PIXELV_HASH)
        }

        private const val GET_PIXEL_HASH = 2165839948L
        private val getPixelBind by lazy {
            ObjectCalls.getMethodBind("Image", "get_pixel", GET_PIXEL_HASH)
        }

        private const val SET_PIXELV_HASH = 287851464L
        private val setPixelvBind by lazy {
            ObjectCalls.getMethodBind("Image", "set_pixelv", SET_PIXELV_HASH)
        }

        private const val SET_PIXEL_HASH = 3733378741L
        private val setPixelBind by lazy {
            ObjectCalls.getMethodBind("Image", "set_pixel", SET_PIXEL_HASH)
        }

        private const val ADJUST_BCS_HASH = 2385087082L
        private val adjustBcsBind by lazy {
            ObjectCalls.getMethodBind("Image", "adjust_bcs", ADJUST_BCS_HASH)
        }

        private const val LOAD_PNG_FROM_BUFFER_HASH = 680677267L
        private val loadPngFromBufferBind by lazy {
            ObjectCalls.getMethodBind("Image", "load_png_from_buffer", LOAD_PNG_FROM_BUFFER_HASH)
        }

        private const val LOAD_JPG_FROM_BUFFER_HASH = 680677267L
        private val loadJpgFromBufferBind by lazy {
            ObjectCalls.getMethodBind("Image", "load_jpg_from_buffer", LOAD_JPG_FROM_BUFFER_HASH)
        }

        private const val LOAD_WEBP_FROM_BUFFER_HASH = 680677267L
        private val loadWebpFromBufferBind by lazy {
            ObjectCalls.getMethodBind("Image", "load_webp_from_buffer", LOAD_WEBP_FROM_BUFFER_HASH)
        }

        private const val LOAD_TGA_FROM_BUFFER_HASH = 680677267L
        private val loadTgaFromBufferBind by lazy {
            ObjectCalls.getMethodBind("Image", "load_tga_from_buffer", LOAD_TGA_FROM_BUFFER_HASH)
        }

        private const val LOAD_BMP_FROM_BUFFER_HASH = 680677267L
        private val loadBmpFromBufferBind by lazy {
            ObjectCalls.getMethodBind("Image", "load_bmp_from_buffer", LOAD_BMP_FROM_BUFFER_HASH)
        }

        private const val LOAD_KTX_FROM_BUFFER_HASH = 680677267L
        private val loadKtxFromBufferBind by lazy {
            ObjectCalls.getMethodBind("Image", "load_ktx_from_buffer", LOAD_KTX_FROM_BUFFER_HASH)
        }

        private const val LOAD_DDS_FROM_BUFFER_HASH = 680677267L
        private val loadDdsFromBufferBind by lazy {
            ObjectCalls.getMethodBind("Image", "load_dds_from_buffer", LOAD_DDS_FROM_BUFFER_HASH)
        }

        private const val LOAD_EXR_FROM_BUFFER_HASH = 680677267L
        private val loadExrFromBufferBind by lazy {
            ObjectCalls.getMethodBind("Image", "load_exr_from_buffer", LOAD_EXR_FROM_BUFFER_HASH)
        }

        private const val LOAD_SVG_FROM_BUFFER_HASH = 311853421L
        private val loadSvgFromBufferBind by lazy {
            ObjectCalls.getMethodBind("Image", "load_svg_from_buffer", LOAD_SVG_FROM_BUFFER_HASH)
        }

        private const val LOAD_SVG_FROM_STRING_HASH = 3254053600L
        private val loadSvgFromStringBind by lazy {
            ObjectCalls.getMethodBind("Image", "load_svg_from_string", LOAD_SVG_FROM_STRING_HASH)
        }
    }
}
