package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2i

/**
 * Generated from Godot docs: Image
 */
class Image(handle: MemorySegment) : Resource(handle) {
    fun getWidth(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getWidthBind, handle)
    }

    fun getHeight(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getHeightBind, handle)
    }

    fun getSize(): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2i(getSizeBind, handle)
    }

    fun hasMipmaps(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasMipmapsBind, handle)
    }

    fun getFormat(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFormatBind, handle)
    }

    fun getDataSize(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getDataSizeBind, handle)
    }

    // KANAMA-IOS-SUGAR: [runtime] get_data / load_png_from_buffer are PackedByteArray shapes
    // the generator's audited table doesn't emit yet; hand-wired through the byte-array
    // dispatch helpers. Re-add after regeneration.
    fun getData(): ByteArray =
        ObjectCalls.ptrcallNoArgsRetByteArray(getDataBind, handle, getDataSize())

    fun loadPngFromBuffer(buffer: ByteArray): Long =
        ObjectCalls.ptrcallWithByteArrayArgRetLong(loadPngFromBufferBind, handle, buffer)

    fun convert(format: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(convertBind, handle, format)
    }

    fun getMipmapCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getMipmapCountBind, handle)
    }

    fun getMipmapOffset(mipmap: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(getMipmapOffsetBind, handle, mipmap)
    }

    fun resizeToPo2(square: Boolean = false, interpolation: Long = 1L) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolAndLongArgs(resizeToPo2Bind, handle, square, interpolation)
    }

    fun resize(width: Int, height: Int, interpolation: Long = 1L) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndLongArgs(resizeBind, handle, width, height, interpolation)
    }

    fun shrinkX2() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(shrinkX2Bind, handle)
    }

    fun crop(width: Int, height: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(cropBind, handle, width, height)
    }

    fun flipX() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(flipXBind, handle)
    }

    fun flipY() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(flipYBind, handle)
    }

    fun generateMipmaps(renormalize: Boolean = false): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithBoolArgRetLong(generateMipmapsBind, handle, renormalize)
    }

    fun clearMipmaps() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearMipmapsBind, handle)
    }

    fun isEmpty(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isEmptyBind, handle)
    }

    fun load(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(loadBind, handle, path)
    }

    fun savePng(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(savePngBind, handle, path)
    }

    fun saveJpg(path: String, quality: Double = 0.75): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndDoubleArgRetLong(saveJpgBind, handle, path, quality)
    }

    fun saveExr(path: String, grayscale: Boolean = false, colorImage: Boolean = false, maxLinearValue: Double = -1.0): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringTwoBoolAndDoubleArgRetLong(saveExrBind, handle, path, grayscale, colorImage, maxLinearValue)
    }

    fun saveDds(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(saveDdsBind, handle, path)
    }

    fun saveWebp(path: String, lossy: Boolean = false, quality: Double = 0.75): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringBoolDoubleArgsRetLong(saveWebpBind, handle, path, lossy, quality)
    }

    fun detectAlpha(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(detectAlphaBind, handle)
    }

    fun isInvisible(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isInvisibleBind, handle)
    }

    fun detectUsedChannels(source: Long = 0L): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetLong(detectUsedChannelsBind, handle, source)
    }

    fun compress(mode: Long, source: Long = 0L, astcFormat: Long = 0L): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithThreeLongArgsRetLong(compressBind, handle, mode, source, astcFormat)
    }

    fun compressFromChannels(mode: Long, channels: Long, astcFormat: Long = 0L): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithThreeLongArgsRetLong(compressFromChannelsBind, handle, mode, channels, astcFormat)
    }

    fun decompress(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(decompressBind, handle)
    }

    fun isCompressed(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isCompressedBind, handle)
    }

    fun rotate90(direction: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(rotate90Bind, handle, direction)
    }

    fun rotate180() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(rotate180Bind, handle)
    }

    fun fixAlphaEdges() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(fixAlphaEdgesBind, handle)
    }

    fun premultiplyAlpha() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(premultiplyAlphaBind, handle)
    }

    fun srgbToLinear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(srgbToLinearBind, handle)
    }

    fun linearToSrgb() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(linearToSrgbBind, handle)
    }

    fun normalMapToXy() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(normalMapToXyBind, handle)
    }

    fun rgbeToSrgb(): Image? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(rgbeToSrgbBind, handle)
        if (ret.address() == handle.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Image.wrap(ret)
    }

    fun bumpMapToNormalMap(bumpScale: Double = 1.0) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(bumpMapToNormalMapBind, handle, bumpScale)
    }

    fun fill(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(fillBind, handle, color)
    }

    fun getUsedRect(): Rect2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRect2i(getUsedRectBind, handle)
    }

    fun copyFrom(src: Image?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(copyFromBind, handle, listOf(src?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getPixelv(point: Vector2i): Color {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2iArgRetColor(getPixelvBind, handle, point)
    }

    fun getPixel(x: Int, y: Int): Color {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetColor(getPixelBind, handle, x, y)
    }

    fun setPixelv(point: Vector2i, color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iAndColorArg(setPixelvBind, handle, point, color)
    }

    fun setPixel(x: Int, y: Int, color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndColorArg(setPixelBind, handle, x, y, color)
    }

    fun adjustBcs(brightness: Double, contrast: Double, saturation: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithThreeDoubleArgs(adjustBcsBind, handle, brightness, contrast, saturation)
    }

    fun loadSvgFromString(svgStr: String, scale: Double = 1.0): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndDoubleArgRetLong(loadSvgFromStringBind, handle, svgStr, scale)
    }

    companion object {
        fun create(width: Int, height: Int, useMipmaps: Boolean, format: Long): Image? {
            return Image.wrap(ObjectCalls.ptrcallWithTwoIntBoolLongArgsRetObject(createBind, MemorySegment.NULL, width, height, useMipmaps, format))
        }

        // KANAMA-IOS-SUGAR: [runtime] create_from_data is STATIC + PackedByteArray — both
        // outside the generator's current emission (statics must dispatch with a NULL
        // instance via ptrcallStatic*). Re-add after regeneration.
        fun createFromData(width: Int, height: Int, useMipmaps: Boolean, format: Long, data: ByteArray): Image? =
            Image.wrap(
                ObjectCalls.ptrcallStaticWithTwoLongBoolLongByteArrayArgsRetObject(
                    createFromDataBind, width.toLong(), height.toLong(), useMipmaps, format, data,
                ),
            )

        private val getDataBind by lazy {
            ObjectCalls.getMethodBind("Image", "get_data", 2362200018L)
        }
        private val loadPngFromBufferBind by lazy {
            ObjectCalls.getMethodBind("Image", "load_png_from_buffer", 680677267L)
        }
        private val createFromDataBind by lazy {
            ObjectCalls.getMethodBind("Image", "create_from_data", 299398494L)
        }

        fun createEmpty(width: Int, height: Int, useMipmaps: Boolean, format: Long): Image? {
            return Image.wrap(ObjectCalls.ptrcallWithTwoIntBoolLongArgsRetObject(createEmptyBind, MemorySegment.NULL, width, height, useMipmaps, format))
        }

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

        private const val SAVE_JPG_HASH = 2800019068L
        private val saveJpgBind by lazy {
            ObjectCalls.getMethodBind("Image", "save_jpg", SAVE_JPG_HASH)
        }

        private const val SAVE_EXR_HASH = 2018602448L
        private val saveExrBind by lazy {
            ObjectCalls.getMethodBind("Image", "save_exr", SAVE_EXR_HASH)
        }

        private const val SAVE_DDS_HASH = 2113323047L
        private val saveDdsBind by lazy {
            ObjectCalls.getMethodBind("Image", "save_dds", SAVE_DDS_HASH)
        }

        private const val SAVE_WEBP_HASH = 2781156876L
        private val saveWebpBind by lazy {
            ObjectCalls.getMethodBind("Image", "save_webp", SAVE_WEBP_HASH)
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

        private const val FILL_HASH = 2920490490L
        private val fillBind by lazy {
            ObjectCalls.getMethodBind("Image", "fill", FILL_HASH)
        }

        private const val GET_USED_RECT_HASH = 410525958L
        private val getUsedRectBind by lazy {
            ObjectCalls.getMethodBind("Image", "get_used_rect", GET_USED_RECT_HASH)
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

        private const val LOAD_SVG_FROM_STRING_HASH = 3254053600L
        private val loadSvgFromStringBind by lazy {
            ObjectCalls.getMethodBind("Image", "load_svg_from_string", LOAD_SVG_FROM_STRING_HASH)
        }
    }
}
