package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i

/**
 * Server for anything visible.
 *
 * Generated from Godot docs: RenderingServer
 */
object RenderingServer {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("RenderingServer")
    }

    const val NO_INDEX_ARRAY: Long = -1L
    const val ARRAY_WEIGHTS_SIZE: Long = 4L
    const val CANVAS_ITEM_Z_MIN: Long = -4096L
    const val CANVAS_ITEM_Z_MAX: Long = 4096L
    const val CANVAS_LAYER_MIN: Long = -2147483648L
    const val CANVAS_LAYER_MAX: Long = 2147483647L
    const val MAX_GLOW_LEVELS: Long = 7L
    const val MAX_CURSORS: Long = 8L
    const val MAX_2D_DIRECTIONAL_LIGHTS: Long = 8L
    const val MAX_MESH_SURFACES: Long = 256L
    const val MATERIAL_RENDER_PRIORITY_MIN: Long = -128L
    const val MATERIAL_RENDER_PRIORITY_MAX: Long = 127L
    const val ARRAY_CUSTOM_COUNT: Long = 4L
    const val PARTICLES_EMIT_FLAG_POSITION: Long = 1L
    const val PARTICLES_EMIT_FLAG_ROTATION_SCALE: Long = 2L
    const val PARTICLES_EMIT_FLAG_VELOCITY: Long = 4L
    const val PARTICLES_EMIT_FLAG_COLOR: Long = 8L
    const val PARTICLES_EMIT_FLAG_CUSTOM: Long = 16L
    const val TEXTURE_TYPE_2D: Long = 0L
    const val TEXTURE_TYPE_LAYERED: Long = 1L
    const val TEXTURE_TYPE_3D: Long = 2L
    const val TEXTURE_LAYERED_2D_ARRAY: Long = 0L
    const val TEXTURE_LAYERED_CUBEMAP: Long = 1L
    const val TEXTURE_LAYERED_CUBEMAP_ARRAY: Long = 2L
    const val CUBEMAP_LAYER_LEFT: Long = 0L
    const val CUBEMAP_LAYER_RIGHT: Long = 1L
    const val CUBEMAP_LAYER_BOTTOM: Long = 2L
    const val CUBEMAP_LAYER_TOP: Long = 3L
    const val CUBEMAP_LAYER_FRONT: Long = 4L
    const val CUBEMAP_LAYER_BACK: Long = 5L
    const val TEXTURE_DRAWABLE_FORMAT_RGBA8: Long = 0L
    const val TEXTURE_DRAWABLE_FORMAT_RGBA8_SRGB: Long = 1L
    const val TEXTURE_DRAWABLE_FORMAT_RGBAH: Long = 2L
    const val TEXTURE_DRAWABLE_FORMAT_RGBAF: Long = 3L
    const val SHADER_SPATIAL: Long = 0L
    const val SHADER_CANVAS_ITEM: Long = 1L
    const val SHADER_PARTICLES: Long = 2L
    const val SHADER_SKY: Long = 3L
    const val SHADER_FOG: Long = 4L
    const val SHADER_TEXTURE_BLIT: Long = 5L
    const val SHADER_MAX: Long = 6L
    const val ARRAY_VERTEX: Long = 0L
    const val ARRAY_NORMAL: Long = 1L
    const val ARRAY_TANGENT: Long = 2L
    const val ARRAY_COLOR: Long = 3L
    const val ARRAY_TEX_UV: Long = 4L
    const val ARRAY_TEX_UV2: Long = 5L
    const val ARRAY_CUSTOM0: Long = 6L
    const val ARRAY_CUSTOM1: Long = 7L
    const val ARRAY_CUSTOM2: Long = 8L
    const val ARRAY_CUSTOM3: Long = 9L
    const val ARRAY_BONES: Long = 10L
    const val ARRAY_WEIGHTS: Long = 11L
    const val ARRAY_INDEX: Long = 12L
    const val ARRAY_MAX: Long = 13L
    const val ARRAY_CUSTOM_RGBA8_UNORM: Long = 0L
    const val ARRAY_CUSTOM_RGBA8_SNORM: Long = 1L
    const val ARRAY_CUSTOM_RG_HALF: Long = 2L
    const val ARRAY_CUSTOM_RGBA_HALF: Long = 3L
    const val ARRAY_CUSTOM_R_FLOAT: Long = 4L
    const val ARRAY_CUSTOM_RG_FLOAT: Long = 5L
    const val ARRAY_CUSTOM_RGB_FLOAT: Long = 6L
    const val ARRAY_CUSTOM_RGBA_FLOAT: Long = 7L
    const val ARRAY_CUSTOM_MAX: Long = 8L
    const val ARRAY_FORMAT_VERTEX: Long = 1L
    const val ARRAY_FORMAT_NORMAL: Long = 2L
    const val ARRAY_FORMAT_TANGENT: Long = 4L
    const val ARRAY_FORMAT_COLOR: Long = 8L
    const val ARRAY_FORMAT_TEX_UV: Long = 16L
    const val ARRAY_FORMAT_TEX_UV2: Long = 32L
    const val ARRAY_FORMAT_CUSTOM0: Long = 64L
    const val ARRAY_FORMAT_CUSTOM1: Long = 128L
    const val ARRAY_FORMAT_CUSTOM2: Long = 256L
    const val ARRAY_FORMAT_CUSTOM3: Long = 512L
    const val ARRAY_FORMAT_BONES: Long = 1024L
    const val ARRAY_FORMAT_WEIGHTS: Long = 2048L
    const val ARRAY_FORMAT_INDEX: Long = 4096L
    const val ARRAY_FORMAT_BLEND_SHAPE_MASK: Long = 7L
    const val ARRAY_FORMAT_CUSTOM_BASE: Long = 13L
    const val ARRAY_FORMAT_CUSTOM_BITS: Long = 3L
    const val ARRAY_FORMAT_CUSTOM0_SHIFT: Long = 13L
    const val ARRAY_FORMAT_CUSTOM1_SHIFT: Long = 16L
    const val ARRAY_FORMAT_CUSTOM2_SHIFT: Long = 19L
    const val ARRAY_FORMAT_CUSTOM3_SHIFT: Long = 22L
    const val ARRAY_FORMAT_CUSTOM_MASK: Long = 7L
    const val ARRAY_COMPRESS_FLAGS_BASE: Long = 25L
    const val ARRAY_FLAG_USE_2D_VERTICES: Long = 33554432L
    const val ARRAY_FLAG_USE_DYNAMIC_UPDATE: Long = 67108864L
    const val ARRAY_FLAG_USE_8_BONE_WEIGHTS: Long = 134217728L
    const val ARRAY_FLAG_USES_EMPTY_VERTEX_ARRAY: Long = 268435456L
    const val ARRAY_FLAG_COMPRESS_ATTRIBUTES: Long = 536870912L
    const val ARRAY_FLAG_FORMAT_VERSION_BASE: Long = 35L
    const val ARRAY_FLAG_FORMAT_VERSION_SHIFT: Long = 35L
    const val ARRAY_FLAG_FORMAT_VERSION_1: Long = 0L
    const val ARRAY_FLAG_FORMAT_VERSION_2: Long = 34359738368L
    const val ARRAY_FLAG_FORMAT_CURRENT_VERSION: Long = 34359738368L
    const val ARRAY_FLAG_FORMAT_VERSION_MASK: Long = 255L
    const val PRIMITIVE_POINTS: Long = 0L
    const val PRIMITIVE_LINES: Long = 1L
    const val PRIMITIVE_LINE_STRIP: Long = 2L
    const val PRIMITIVE_TRIANGLES: Long = 3L
    const val PRIMITIVE_TRIANGLE_STRIP: Long = 4L
    const val PRIMITIVE_MAX: Long = 5L
    const val BLEND_SHAPE_MODE_NORMALIZED: Long = 0L
    const val BLEND_SHAPE_MODE_RELATIVE: Long = 1L
    const val MULTIMESH_TRANSFORM_2D: Long = 0L
    const val MULTIMESH_TRANSFORM_3D: Long = 1L
    const val MULTIMESH_INTERP_QUALITY_FAST: Long = 0L
    const val MULTIMESH_INTERP_QUALITY_HIGH: Long = 1L
    const val LIGHT_PROJECTOR_FILTER_NEAREST: Long = 0L
    const val LIGHT_PROJECTOR_FILTER_LINEAR: Long = 1L
    const val LIGHT_PROJECTOR_FILTER_NEAREST_MIPMAPS: Long = 2L
    const val LIGHT_PROJECTOR_FILTER_LINEAR_MIPMAPS: Long = 3L
    const val LIGHT_PROJECTOR_FILTER_NEAREST_MIPMAPS_ANISOTROPIC: Long = 4L
    const val LIGHT_PROJECTOR_FILTER_LINEAR_MIPMAPS_ANISOTROPIC: Long = 5L
    const val LIGHT_DIRECTIONAL: Long = 0L
    const val LIGHT_OMNI: Long = 1L
    const val LIGHT_SPOT: Long = 2L
    const val LIGHT_AREA: Long = 3L
    const val LIGHT_PARAM_ENERGY: Long = 0L
    const val LIGHT_PARAM_INDIRECT_ENERGY: Long = 1L
    const val LIGHT_PARAM_VOLUMETRIC_FOG_ENERGY: Long = 2L
    const val LIGHT_PARAM_SPECULAR: Long = 3L
    const val LIGHT_PARAM_RANGE: Long = 4L
    const val LIGHT_PARAM_SIZE: Long = 5L
    const val LIGHT_PARAM_ATTENUATION: Long = 6L
    const val LIGHT_PARAM_SPOT_ANGLE: Long = 7L
    const val LIGHT_PARAM_SPOT_ATTENUATION: Long = 8L
    const val LIGHT_PARAM_SHADOW_MAX_DISTANCE: Long = 9L
    const val LIGHT_PARAM_SHADOW_SPLIT_1_OFFSET: Long = 10L
    const val LIGHT_PARAM_SHADOW_SPLIT_2_OFFSET: Long = 11L
    const val LIGHT_PARAM_SHADOW_SPLIT_3_OFFSET: Long = 12L
    const val LIGHT_PARAM_SHADOW_FADE_START: Long = 13L
    const val LIGHT_PARAM_SHADOW_NORMAL_BIAS: Long = 14L
    const val LIGHT_PARAM_SHADOW_BIAS: Long = 15L
    const val LIGHT_PARAM_SHADOW_PANCAKE_SIZE: Long = 16L
    const val LIGHT_PARAM_SHADOW_OPACITY: Long = 17L
    const val LIGHT_PARAM_SHADOW_BLUR: Long = 18L
    const val LIGHT_PARAM_TRANSMITTANCE_BIAS: Long = 19L
    const val LIGHT_PARAM_INTENSITY: Long = 20L
    const val LIGHT_PARAM_MAX: Long = 21L
    const val LIGHT_BAKE_DISABLED: Long = 0L
    const val LIGHT_BAKE_STATIC: Long = 1L
    const val LIGHT_BAKE_DYNAMIC: Long = 2L
    const val LIGHT_OMNI_SHADOW_DUAL_PARABOLOID: Long = 0L
    const val LIGHT_OMNI_SHADOW_CUBE: Long = 1L
    const val LIGHT_DIRECTIONAL_SHADOW_ORTHOGONAL: Long = 0L
    const val LIGHT_DIRECTIONAL_SHADOW_PARALLEL_2_SPLITS: Long = 1L
    const val LIGHT_DIRECTIONAL_SHADOW_PARALLEL_4_SPLITS: Long = 2L
    const val LIGHT_DIRECTIONAL_SKY_MODE_LIGHT_AND_SKY: Long = 0L
    const val LIGHT_DIRECTIONAL_SKY_MODE_LIGHT_ONLY: Long = 1L
    const val LIGHT_DIRECTIONAL_SKY_MODE_SKY_ONLY: Long = 2L
    const val SHADOW_QUALITY_HARD: Long = 0L
    const val SHADOW_QUALITY_SOFT_VERY_LOW: Long = 1L
    const val SHADOW_QUALITY_SOFT_LOW: Long = 2L
    const val SHADOW_QUALITY_SOFT_MEDIUM: Long = 3L
    const val SHADOW_QUALITY_SOFT_HIGH: Long = 4L
    const val SHADOW_QUALITY_SOFT_ULTRA: Long = 5L
    const val SHADOW_QUALITY_MAX: Long = 6L
    const val REFLECTION_PROBE_UPDATE_ONCE: Long = 0L
    const val REFLECTION_PROBE_UPDATE_ALWAYS: Long = 1L
    const val REFLECTION_PROBE_AMBIENT_DISABLED: Long = 0L
    const val REFLECTION_PROBE_AMBIENT_ENVIRONMENT: Long = 1L
    const val REFLECTION_PROBE_AMBIENT_COLOR: Long = 2L
    const val DECAL_TEXTURE_ALBEDO: Long = 0L
    const val DECAL_TEXTURE_NORMAL: Long = 1L
    const val DECAL_TEXTURE_ORM: Long = 2L
    const val DECAL_TEXTURE_EMISSION: Long = 3L
    const val DECAL_TEXTURE_MAX: Long = 4L
    const val DECAL_FILTER_NEAREST: Long = 0L
    const val DECAL_FILTER_LINEAR: Long = 1L
    const val DECAL_FILTER_NEAREST_MIPMAPS: Long = 2L
    const val DECAL_FILTER_LINEAR_MIPMAPS: Long = 3L
    const val DECAL_FILTER_NEAREST_MIPMAPS_ANISOTROPIC: Long = 4L
    const val DECAL_FILTER_LINEAR_MIPMAPS_ANISOTROPIC: Long = 5L
    const val VOXEL_GI_QUALITY_LOW: Long = 0L
    const val VOXEL_GI_QUALITY_HIGH: Long = 1L
    const val PARTICLES_MODE_2D: Long = 0L
    const val PARTICLES_MODE_3D: Long = 1L
    const val PARTICLES_TRANSFORM_ALIGN_DISABLED: Long = 0L
    const val PARTICLES_TRANSFORM_ALIGN_Z_BILLBOARD: Long = 1L
    const val PARTICLES_TRANSFORM_ALIGN_Y_TO_VELOCITY: Long = 2L
    const val PARTICLES_TRANSFORM_ALIGN_Z_BILLBOARD_Y_TO_VELOCITY: Long = 3L
    const val PARTICLES_TRANSFORM_ALIGN_LOCAL_BILLBOARD: Long = 4L
    const val PARTICLES_ALIGN_CHANNEL_FILTER_DISABLED: Long = 0L
    const val PARTICLES_ALIGN_CHANNEL_FILTER_X: Long = 1L
    const val PARTICLES_ALIGN_CHANNEL_FILTER_Y: Long = 2L
    const val PARTICLES_ALIGN_CHANNEL_FILTER_Z: Long = 3L
    const val PARTICLES_ALIGN_CHANNEL_FILTER_W: Long = 4L
    const val PARTICLES_ALIGN_AXIS_X: Long = 0L
    const val PARTICLES_ALIGN_AXIS_Y: Long = 1L
    const val PARTICLES_DRAW_ORDER_INDEX: Long = 0L
    const val PARTICLES_DRAW_ORDER_LIFETIME: Long = 1L
    const val PARTICLES_DRAW_ORDER_REVERSE_LIFETIME: Long = 2L
    const val PARTICLES_DRAW_ORDER_VIEW_DEPTH: Long = 3L
    const val PARTICLES_COLLISION_TYPE_SPHERE_ATTRACT: Long = 0L
    const val PARTICLES_COLLISION_TYPE_BOX_ATTRACT: Long = 1L
    const val PARTICLES_COLLISION_TYPE_VECTOR_FIELD_ATTRACT: Long = 2L
    const val PARTICLES_COLLISION_TYPE_SPHERE_COLLIDE: Long = 3L
    const val PARTICLES_COLLISION_TYPE_BOX_COLLIDE: Long = 4L
    const val PARTICLES_COLLISION_TYPE_SDF_COLLIDE: Long = 5L
    const val PARTICLES_COLLISION_TYPE_HEIGHTFIELD_COLLIDE: Long = 6L
    const val PARTICLES_COLLISION_HEIGHTFIELD_RESOLUTION_256: Long = 0L
    const val PARTICLES_COLLISION_HEIGHTFIELD_RESOLUTION_512: Long = 1L
    const val PARTICLES_COLLISION_HEIGHTFIELD_RESOLUTION_1024: Long = 2L
    const val PARTICLES_COLLISION_HEIGHTFIELD_RESOLUTION_2048: Long = 3L
    const val PARTICLES_COLLISION_HEIGHTFIELD_RESOLUTION_4096: Long = 4L
    const val PARTICLES_COLLISION_HEIGHTFIELD_RESOLUTION_8192: Long = 5L
    const val PARTICLES_COLLISION_HEIGHTFIELD_RESOLUTION_MAX: Long = 6L
    const val FOG_VOLUME_SHAPE_ELLIPSOID: Long = 0L
    const val FOG_VOLUME_SHAPE_CONE: Long = 1L
    const val FOG_VOLUME_SHAPE_CYLINDER: Long = 2L
    const val FOG_VOLUME_SHAPE_BOX: Long = 3L
    const val FOG_VOLUME_SHAPE_WORLD: Long = 4L
    const val FOG_VOLUME_SHAPE_MAX: Long = 5L
    const val VIEWPORT_SCALING_3D_MODE_BILINEAR: Long = 0L
    const val VIEWPORT_SCALING_3D_MODE_FSR: Long = 1L
    const val VIEWPORT_SCALING_3D_MODE_FSR2: Long = 2L
    const val VIEWPORT_SCALING_3D_MODE_METALFX_SPATIAL: Long = 3L
    const val VIEWPORT_SCALING_3D_MODE_METALFX_TEMPORAL: Long = 4L
    const val VIEWPORT_SCALING_3D_MODE_NEAREST: Long = 5L
    const val VIEWPORT_SCALING_3D_MODE_MAX: Long = 6L
    const val VIEWPORT_UPDATE_DISABLED: Long = 0L
    const val VIEWPORT_UPDATE_ONCE: Long = 1L
    const val VIEWPORT_UPDATE_WHEN_VISIBLE: Long = 2L
    const val VIEWPORT_UPDATE_WHEN_PARENT_VISIBLE: Long = 3L
    const val VIEWPORT_UPDATE_ALWAYS: Long = 4L
    const val VIEWPORT_CLEAR_ALWAYS: Long = 0L
    const val VIEWPORT_CLEAR_NEVER: Long = 1L
    const val VIEWPORT_CLEAR_ONLY_NEXT_FRAME: Long = 2L
    const val VIEWPORT_ENVIRONMENT_DISABLED: Long = 0L
    const val VIEWPORT_ENVIRONMENT_ENABLED: Long = 1L
    const val VIEWPORT_ENVIRONMENT_INHERIT: Long = 2L
    const val VIEWPORT_ENVIRONMENT_MAX: Long = 3L
    const val VIEWPORT_SDF_OVERSIZE_100_PERCENT: Long = 0L
    const val VIEWPORT_SDF_OVERSIZE_120_PERCENT: Long = 1L
    const val VIEWPORT_SDF_OVERSIZE_150_PERCENT: Long = 2L
    const val VIEWPORT_SDF_OVERSIZE_200_PERCENT: Long = 3L
    const val VIEWPORT_SDF_OVERSIZE_MAX: Long = 4L
    const val VIEWPORT_SDF_SCALE_100_PERCENT: Long = 0L
    const val VIEWPORT_SDF_SCALE_50_PERCENT: Long = 1L
    const val VIEWPORT_SDF_SCALE_25_PERCENT: Long = 2L
    const val VIEWPORT_SDF_SCALE_MAX: Long = 3L
    const val VIEWPORT_MSAA_DISABLED: Long = 0L
    const val VIEWPORT_MSAA_2X: Long = 1L
    const val VIEWPORT_MSAA_4X: Long = 2L
    const val VIEWPORT_MSAA_8X: Long = 3L
    const val VIEWPORT_MSAA_MAX: Long = 4L
    const val VIEWPORT_ANISOTROPY_DISABLED: Long = 0L
    const val VIEWPORT_ANISOTROPY_2X: Long = 1L
    const val VIEWPORT_ANISOTROPY_4X: Long = 2L
    const val VIEWPORT_ANISOTROPY_8X: Long = 3L
    const val VIEWPORT_ANISOTROPY_16X: Long = 4L
    const val VIEWPORT_ANISOTROPY_MAX: Long = 5L
    const val VIEWPORT_SCREEN_SPACE_AA_DISABLED: Long = 0L
    const val VIEWPORT_SCREEN_SPACE_AA_FXAA: Long = 1L
    const val VIEWPORT_SCREEN_SPACE_AA_SMAA: Long = 2L
    const val VIEWPORT_SCREEN_SPACE_AA_MAX: Long = 3L
    const val VIEWPORT_OCCLUSION_BUILD_QUALITY_LOW: Long = 0L
    const val VIEWPORT_OCCLUSION_BUILD_QUALITY_MEDIUM: Long = 1L
    const val VIEWPORT_OCCLUSION_BUILD_QUALITY_HIGH: Long = 2L
    const val VIEWPORT_RENDER_INFO_OBJECTS_IN_FRAME: Long = 0L
    const val VIEWPORT_RENDER_INFO_PRIMITIVES_IN_FRAME: Long = 1L
    const val VIEWPORT_RENDER_INFO_DRAW_CALLS_IN_FRAME: Long = 2L
    const val VIEWPORT_RENDER_INFO_MAX: Long = 3L
    const val VIEWPORT_RENDER_INFO_TYPE_VISIBLE: Long = 0L
    const val VIEWPORT_RENDER_INFO_TYPE_SHADOW: Long = 1L
    const val VIEWPORT_RENDER_INFO_TYPE_CANVAS: Long = 2L
    const val VIEWPORT_RENDER_INFO_TYPE_MAX: Long = 3L
    const val VIEWPORT_DEBUG_DRAW_DISABLED: Long = 0L
    const val VIEWPORT_DEBUG_DRAW_UNSHADED: Long = 1L
    const val VIEWPORT_DEBUG_DRAW_LIGHTING: Long = 2L
    const val VIEWPORT_DEBUG_DRAW_OVERDRAW: Long = 3L
    const val VIEWPORT_DEBUG_DRAW_WIREFRAME: Long = 4L
    const val VIEWPORT_DEBUG_DRAW_NORMAL_BUFFER: Long = 5L
    const val VIEWPORT_DEBUG_DRAW_VOXEL_GI_ALBEDO: Long = 6L
    const val VIEWPORT_DEBUG_DRAW_VOXEL_GI_LIGHTING: Long = 7L
    const val VIEWPORT_DEBUG_DRAW_VOXEL_GI_EMISSION: Long = 8L
    const val VIEWPORT_DEBUG_DRAW_SHADOW_ATLAS: Long = 9L
    const val VIEWPORT_DEBUG_DRAW_DIRECTIONAL_SHADOW_ATLAS: Long = 10L
    const val VIEWPORT_DEBUG_DRAW_SCENE_LUMINANCE: Long = 11L
    const val VIEWPORT_DEBUG_DRAW_SSAO: Long = 12L
    const val VIEWPORT_DEBUG_DRAW_SSIL: Long = 13L
    const val VIEWPORT_DEBUG_DRAW_PSSM_SPLITS: Long = 14L
    const val VIEWPORT_DEBUG_DRAW_DECAL_ATLAS: Long = 15L
    const val VIEWPORT_DEBUG_DRAW_SDFGI: Long = 16L
    const val VIEWPORT_DEBUG_DRAW_SDFGI_PROBES: Long = 17L
    const val VIEWPORT_DEBUG_DRAW_GI_BUFFER: Long = 18L
    const val VIEWPORT_DEBUG_DRAW_DISABLE_LOD: Long = 19L
    const val VIEWPORT_DEBUG_DRAW_CLUSTER_OMNI_LIGHTS: Long = 20L
    const val VIEWPORT_DEBUG_DRAW_CLUSTER_SPOT_LIGHTS: Long = 21L
    const val VIEWPORT_DEBUG_DRAW_CLUSTER_DECALS: Long = 22L
    const val VIEWPORT_DEBUG_DRAW_CLUSTER_REFLECTION_PROBES: Long = 23L
    const val VIEWPORT_DEBUG_DRAW_OCCLUDERS: Long = 24L
    const val VIEWPORT_DEBUG_DRAW_MOTION_VECTORS: Long = 25L
    const val VIEWPORT_DEBUG_DRAW_INTERNAL_BUFFER: Long = 26L
    const val VIEWPORT_VRS_DISABLED: Long = 0L
    const val VIEWPORT_VRS_TEXTURE: Long = 1L
    const val VIEWPORT_VRS_XR: Long = 2L
    const val VIEWPORT_VRS_MAX: Long = 3L
    const val VIEWPORT_VRS_UPDATE_DISABLED: Long = 0L
    const val VIEWPORT_VRS_UPDATE_ONCE: Long = 1L
    const val VIEWPORT_VRS_UPDATE_ALWAYS: Long = 2L
    const val VIEWPORT_VRS_UPDATE_MAX: Long = 3L
    const val SKY_MODE_AUTOMATIC: Long = 0L
    const val SKY_MODE_QUALITY: Long = 1L
    const val SKY_MODE_INCREMENTAL: Long = 2L
    const val SKY_MODE_REALTIME: Long = 3L
    const val COMPOSITOR_EFFECT_FLAG_ACCESS_RESOLVED_COLOR: Long = 1L
    const val COMPOSITOR_EFFECT_FLAG_ACCESS_RESOLVED_DEPTH: Long = 2L
    const val COMPOSITOR_EFFECT_FLAG_NEEDS_MOTION_VECTORS: Long = 4L
    const val COMPOSITOR_EFFECT_FLAG_NEEDS_ROUGHNESS: Long = 8L
    const val COMPOSITOR_EFFECT_FLAG_NEEDS_SEPARATE_SPECULAR: Long = 16L
    const val COMPOSITOR_EFFECT_CALLBACK_TYPE_PRE_OPAQUE: Long = 0L
    const val COMPOSITOR_EFFECT_CALLBACK_TYPE_POST_OPAQUE: Long = 1L
    const val COMPOSITOR_EFFECT_CALLBACK_TYPE_POST_SKY: Long = 2L
    const val COMPOSITOR_EFFECT_CALLBACK_TYPE_PRE_TRANSPARENT: Long = 3L
    const val COMPOSITOR_EFFECT_CALLBACK_TYPE_POST_TRANSPARENT: Long = 4L
    const val COMPOSITOR_EFFECT_CALLBACK_TYPE_ANY: Long = -1L
    const val ENV_BG_CLEAR_COLOR: Long = 0L
    const val ENV_BG_COLOR: Long = 1L
    const val ENV_BG_SKY: Long = 2L
    const val ENV_BG_CANVAS: Long = 3L
    const val ENV_BG_KEEP: Long = 4L
    const val ENV_BG_CAMERA_FEED: Long = 5L
    const val ENV_BG_MAX: Long = 6L
    const val ENV_AMBIENT_SOURCE_BG: Long = 0L
    const val ENV_AMBIENT_SOURCE_DISABLED: Long = 1L
    const val ENV_AMBIENT_SOURCE_COLOR: Long = 2L
    const val ENV_AMBIENT_SOURCE_SKY: Long = 3L
    const val ENV_REFLECTION_SOURCE_BG: Long = 0L
    const val ENV_REFLECTION_SOURCE_DISABLED: Long = 1L
    const val ENV_REFLECTION_SOURCE_SKY: Long = 2L
    const val ENV_GLOW_BLEND_MODE_ADDITIVE: Long = 0L
    const val ENV_GLOW_BLEND_MODE_SCREEN: Long = 1L
    const val ENV_GLOW_BLEND_MODE_SOFTLIGHT: Long = 2L
    const val ENV_GLOW_BLEND_MODE_REPLACE: Long = 3L
    const val ENV_GLOW_BLEND_MODE_MIX: Long = 4L
    const val ENV_FOG_MODE_EXPONENTIAL: Long = 0L
    const val ENV_FOG_MODE_DEPTH: Long = 1L
    const val ENV_TONE_MAPPER_LINEAR: Long = 0L
    const val ENV_TONE_MAPPER_REINHARD: Long = 1L
    const val ENV_TONE_MAPPER_FILMIC: Long = 2L
    const val ENV_TONE_MAPPER_ACES: Long = 3L
    const val ENV_TONE_MAPPER_AGX: Long = 4L
    const val ENV_SSR_ROUGHNESS_QUALITY_DISABLED: Long = 0L
    const val ENV_SSR_ROUGHNESS_QUALITY_LOW: Long = 1L
    const val ENV_SSR_ROUGHNESS_QUALITY_MEDIUM: Long = 2L
    const val ENV_SSR_ROUGHNESS_QUALITY_HIGH: Long = 3L
    const val ENV_SSAO_QUALITY_VERY_LOW: Long = 0L
    const val ENV_SSAO_QUALITY_LOW: Long = 1L
    const val ENV_SSAO_QUALITY_MEDIUM: Long = 2L
    const val ENV_SSAO_QUALITY_HIGH: Long = 3L
    const val ENV_SSAO_QUALITY_ULTRA: Long = 4L
    const val ENV_SSIL_QUALITY_VERY_LOW: Long = 0L
    const val ENV_SSIL_QUALITY_LOW: Long = 1L
    const val ENV_SSIL_QUALITY_MEDIUM: Long = 2L
    const val ENV_SSIL_QUALITY_HIGH: Long = 3L
    const val ENV_SSIL_QUALITY_ULTRA: Long = 4L
    const val ENV_SDFGI_Y_SCALE_50_PERCENT: Long = 0L
    const val ENV_SDFGI_Y_SCALE_75_PERCENT: Long = 1L
    const val ENV_SDFGI_Y_SCALE_100_PERCENT: Long = 2L
    const val ENV_SDFGI_RAY_COUNT_4: Long = 0L
    const val ENV_SDFGI_RAY_COUNT_8: Long = 1L
    const val ENV_SDFGI_RAY_COUNT_16: Long = 2L
    const val ENV_SDFGI_RAY_COUNT_32: Long = 3L
    const val ENV_SDFGI_RAY_COUNT_64: Long = 4L
    const val ENV_SDFGI_RAY_COUNT_96: Long = 5L
    const val ENV_SDFGI_RAY_COUNT_128: Long = 6L
    const val ENV_SDFGI_RAY_COUNT_MAX: Long = 7L
    const val ENV_SDFGI_CONVERGE_IN_5_FRAMES: Long = 0L
    const val ENV_SDFGI_CONVERGE_IN_10_FRAMES: Long = 1L
    const val ENV_SDFGI_CONVERGE_IN_15_FRAMES: Long = 2L
    const val ENV_SDFGI_CONVERGE_IN_20_FRAMES: Long = 3L
    const val ENV_SDFGI_CONVERGE_IN_25_FRAMES: Long = 4L
    const val ENV_SDFGI_CONVERGE_IN_30_FRAMES: Long = 5L
    const val ENV_SDFGI_CONVERGE_MAX: Long = 6L
    const val ENV_SDFGI_UPDATE_LIGHT_IN_1_FRAME: Long = 0L
    const val ENV_SDFGI_UPDATE_LIGHT_IN_2_FRAMES: Long = 1L
    const val ENV_SDFGI_UPDATE_LIGHT_IN_4_FRAMES: Long = 2L
    const val ENV_SDFGI_UPDATE_LIGHT_IN_8_FRAMES: Long = 3L
    const val ENV_SDFGI_UPDATE_LIGHT_IN_16_FRAMES: Long = 4L
    const val ENV_SDFGI_UPDATE_LIGHT_MAX: Long = 5L
    const val SUB_SURFACE_SCATTERING_QUALITY_DISABLED: Long = 0L
    const val SUB_SURFACE_SCATTERING_QUALITY_LOW: Long = 1L
    const val SUB_SURFACE_SCATTERING_QUALITY_MEDIUM: Long = 2L
    const val SUB_SURFACE_SCATTERING_QUALITY_HIGH: Long = 3L
    const val DOF_BOKEH_BOX: Long = 0L
    const val DOF_BOKEH_HEXAGON: Long = 1L
    const val DOF_BOKEH_CIRCLE: Long = 2L
    const val DOF_BLUR_QUALITY_VERY_LOW: Long = 0L
    const val DOF_BLUR_QUALITY_LOW: Long = 1L
    const val DOF_BLUR_QUALITY_MEDIUM: Long = 2L
    const val DOF_BLUR_QUALITY_HIGH: Long = 3L
    const val INSTANCE_NONE: Long = 0L
    const val INSTANCE_MESH: Long = 1L
    const val INSTANCE_MULTIMESH: Long = 2L
    const val INSTANCE_PARTICLES: Long = 3L
    const val INSTANCE_PARTICLES_COLLISION: Long = 4L
    const val INSTANCE_LIGHT: Long = 5L
    const val INSTANCE_REFLECTION_PROBE: Long = 6L
    const val INSTANCE_DECAL: Long = 7L
    const val INSTANCE_VOXEL_GI: Long = 8L
    const val INSTANCE_LIGHTMAP: Long = 9L
    const val INSTANCE_OCCLUDER: Long = 10L
    const val INSTANCE_VISIBLITY_NOTIFIER: Long = 11L
    const val INSTANCE_FOG_VOLUME: Long = 12L
    const val INSTANCE_MAX: Long = 13L
    const val INSTANCE_GEOMETRY_MASK: Long = 14L
    const val INSTANCE_FLAG_USE_BAKED_LIGHT: Long = 0L
    const val INSTANCE_FLAG_USE_DYNAMIC_GI: Long = 1L
    const val INSTANCE_FLAG_DRAW_NEXT_FRAME_IF_VISIBLE: Long = 2L
    const val INSTANCE_FLAG_IGNORE_OCCLUSION_CULLING: Long = 3L
    const val INSTANCE_FLAG_MAX: Long = 4L
    const val SHADOW_CASTING_SETTING_OFF: Long = 0L
    const val SHADOW_CASTING_SETTING_ON: Long = 1L
    const val SHADOW_CASTING_SETTING_DOUBLE_SIDED: Long = 2L
    const val SHADOW_CASTING_SETTING_SHADOWS_ONLY: Long = 3L
    const val VISIBILITY_RANGE_FADE_DISABLED: Long = 0L
    const val VISIBILITY_RANGE_FADE_SELF: Long = 1L
    const val VISIBILITY_RANGE_FADE_DEPENDENCIES: Long = 2L
    const val BAKE_CHANNEL_ALBEDO_ALPHA: Long = 0L
    const val BAKE_CHANNEL_NORMAL: Long = 1L
    const val BAKE_CHANNEL_ORM: Long = 2L
    const val BAKE_CHANNEL_EMISSION: Long = 3L
    const val CANVAS_TEXTURE_CHANNEL_DIFFUSE: Long = 0L
    const val CANVAS_TEXTURE_CHANNEL_NORMAL: Long = 1L
    const val CANVAS_TEXTURE_CHANNEL_SPECULAR: Long = 2L
    const val NINE_PATCH_STRETCH: Long = 0L
    const val NINE_PATCH_TILE: Long = 1L
    const val NINE_PATCH_TILE_FIT: Long = 2L
    const val CANVAS_ITEM_TEXTURE_FILTER_DEFAULT: Long = 0L
    const val CANVAS_ITEM_TEXTURE_FILTER_NEAREST: Long = 1L
    const val CANVAS_ITEM_TEXTURE_FILTER_LINEAR: Long = 2L
    const val CANVAS_ITEM_TEXTURE_FILTER_NEAREST_WITH_MIPMAPS: Long = 3L
    const val CANVAS_ITEM_TEXTURE_FILTER_LINEAR_WITH_MIPMAPS: Long = 4L
    const val CANVAS_ITEM_TEXTURE_FILTER_NEAREST_WITH_MIPMAPS_ANISOTROPIC: Long = 5L
    const val CANVAS_ITEM_TEXTURE_FILTER_LINEAR_WITH_MIPMAPS_ANISOTROPIC: Long = 6L
    const val CANVAS_ITEM_TEXTURE_FILTER_MAX: Long = 7L
    const val CANVAS_ITEM_TEXTURE_REPEAT_DEFAULT: Long = 0L
    const val CANVAS_ITEM_TEXTURE_REPEAT_DISABLED: Long = 1L
    const val CANVAS_ITEM_TEXTURE_REPEAT_ENABLED: Long = 2L
    const val CANVAS_ITEM_TEXTURE_REPEAT_MIRROR: Long = 3L
    const val CANVAS_ITEM_TEXTURE_REPEAT_MAX: Long = 4L
    const val CANVAS_GROUP_MODE_DISABLED: Long = 0L
    const val CANVAS_GROUP_MODE_CLIP_ONLY: Long = 1L
    const val CANVAS_GROUP_MODE_CLIP_AND_DRAW: Long = 2L
    const val CANVAS_GROUP_MODE_TRANSPARENT: Long = 3L
    const val CANVAS_LIGHT_MODE_POINT: Long = 0L
    const val CANVAS_LIGHT_MODE_DIRECTIONAL: Long = 1L
    const val CANVAS_LIGHT_BLEND_MODE_ADD: Long = 0L
    const val CANVAS_LIGHT_BLEND_MODE_SUB: Long = 1L
    const val CANVAS_LIGHT_BLEND_MODE_MIX: Long = 2L
    const val CANVAS_LIGHT_FILTER_NONE: Long = 0L
    const val CANVAS_LIGHT_FILTER_PCF5: Long = 1L
    const val CANVAS_LIGHT_FILTER_PCF13: Long = 2L
    const val CANVAS_LIGHT_FILTER_MAX: Long = 3L
    const val CANVAS_OCCLUDER_POLYGON_CULL_DISABLED: Long = 0L
    const val CANVAS_OCCLUDER_POLYGON_CULL_CLOCKWISE: Long = 1L
    const val CANVAS_OCCLUDER_POLYGON_CULL_COUNTER_CLOCKWISE: Long = 2L
    const val GLOBAL_VAR_TYPE_BOOL: Long = 0L
    const val GLOBAL_VAR_TYPE_BVEC2: Long = 1L
    const val GLOBAL_VAR_TYPE_BVEC3: Long = 2L
    const val GLOBAL_VAR_TYPE_BVEC4: Long = 3L
    const val GLOBAL_VAR_TYPE_INT: Long = 4L
    const val GLOBAL_VAR_TYPE_IVEC2: Long = 5L
    const val GLOBAL_VAR_TYPE_IVEC3: Long = 6L
    const val GLOBAL_VAR_TYPE_IVEC4: Long = 7L
    const val GLOBAL_VAR_TYPE_RECT2I: Long = 8L
    const val GLOBAL_VAR_TYPE_UINT: Long = 9L
    const val GLOBAL_VAR_TYPE_UVEC2: Long = 10L
    const val GLOBAL_VAR_TYPE_UVEC3: Long = 11L
    const val GLOBAL_VAR_TYPE_UVEC4: Long = 12L
    const val GLOBAL_VAR_TYPE_FLOAT: Long = 13L
    const val GLOBAL_VAR_TYPE_VEC2: Long = 14L
    const val GLOBAL_VAR_TYPE_VEC3: Long = 15L
    const val GLOBAL_VAR_TYPE_VEC4: Long = 16L
    const val GLOBAL_VAR_TYPE_COLOR: Long = 17L
    const val GLOBAL_VAR_TYPE_RECT2: Long = 18L
    const val GLOBAL_VAR_TYPE_MAT2: Long = 19L
    const val GLOBAL_VAR_TYPE_MAT3: Long = 20L
    const val GLOBAL_VAR_TYPE_MAT4: Long = 21L
    const val GLOBAL_VAR_TYPE_TRANSFORM_2D: Long = 22L
    const val GLOBAL_VAR_TYPE_TRANSFORM: Long = 23L
    const val GLOBAL_VAR_TYPE_SAMPLER2D: Long = 24L
    const val GLOBAL_VAR_TYPE_SAMPLER2DARRAY: Long = 25L
    const val GLOBAL_VAR_TYPE_SAMPLER3D: Long = 26L
    const val GLOBAL_VAR_TYPE_SAMPLERCUBE: Long = 27L
    const val GLOBAL_VAR_TYPE_SAMPLEREXT: Long = 28L
    const val GLOBAL_VAR_TYPE_MAX: Long = 29L
    const val RENDERING_INFO_TOTAL_OBJECTS_IN_FRAME: Long = 0L
    const val RENDERING_INFO_TOTAL_PRIMITIVES_IN_FRAME: Long = 1L
    const val RENDERING_INFO_TOTAL_DRAW_CALLS_IN_FRAME: Long = 2L
    const val RENDERING_INFO_TEXTURE_MEM_USED: Long = 3L
    const val RENDERING_INFO_BUFFER_MEM_USED: Long = 4L
    const val RENDERING_INFO_VIDEO_MEM_USED: Long = 5L
    const val RENDERING_INFO_PIPELINE_COMPILATIONS_CANVAS: Long = 6L
    const val RENDERING_INFO_PIPELINE_COMPILATIONS_MESH: Long = 7L
    const val RENDERING_INFO_PIPELINE_COMPILATIONS_SURFACE: Long = 8L
    const val RENDERING_INFO_PIPELINE_COMPILATIONS_DRAW: Long = 9L
    const val RENDERING_INFO_PIPELINE_COMPILATIONS_SPECIALIZATION: Long = 10L
    const val PIPELINE_SOURCE_CANVAS: Long = 0L
    const val PIPELINE_SOURCE_MESH: Long = 1L
    const val PIPELINE_SOURCE_SURFACE: Long = 2L
    const val PIPELINE_SOURCE_DRAW: Long = 3L
    const val PIPELINE_SOURCE_SPECIALIZATION: Long = 4L
    const val PIPELINE_SOURCE_MAX: Long = 5L
    const val SPLASH_STRETCH_MODE_DISABLED: Long = 0L
    const val SPLASH_STRETCH_MODE_KEEP: Long = 1L
    const val SPLASH_STRETCH_MODE_KEEP_WIDTH: Long = 2L
    const val SPLASH_STRETCH_MODE_KEEP_HEIGHT: Long = 3L
    const val SPLASH_STRETCH_MODE_COVER: Long = 4L
    const val SPLASH_STRETCH_MODE_IGNORE: Long = 5L
    const val FEATURE_SHADERS: Long = 0L
    const val FEATURE_MULTITHREADED: Long = 1L

    var renderLoopEnabled: Boolean
        @JvmName("renderLoopEnabledProperty")
        get() = isRenderLoopEnabled()
        @JvmName("setRenderLoopEnabledProperty")
        set(value) = setRenderLoopEnabled(value)

    /**
     * Creates a 2-dimensional texture and adds it to the RenderingServer. It can be accessed with the
     * RID that is returned. This RID will be used in all `texture_2d_*` RenderingServer functions.
     * Once finished with your RID, you will want to free the RID using the RenderingServer's
     * `free_rid` method. Note: The equivalent resource is `Texture2D`. Note: Not to be confused with
     * `RenderingDevice.texture_create`, which creates the graphics API's own texture type as opposed
     * to the Godot-specific `Texture2D` resource.
     *
     * Generated from Godot docs: RenderingServer.texture_2d_create
     */
    @JvmStatic
    fun texture2dCreate(image: Image?): RID {
        return ObjectCalls.ptrcallWithObjectArgRetRID(texture2dCreateBind, singleton, image?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Creates a 2-dimensional layered texture and adds it to the RenderingServer. It can be accessed
     * with the RID that is returned. This RID will be used in all `texture_2d_layered_*`
     * RenderingServer functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. Note: The equivalent resource is `TextureLayered`.
     *
     * Generated from Godot docs: RenderingServer.texture_2d_layered_create
     */
    @JvmStatic
    fun texture2dLayeredCreate(layers: List<Image>, layeredType: Long): RID {
        return ObjectCalls.ptrcallWithObjectListLongArgsRetRID(texture2dLayeredCreateBind, singleton, layers, layeredType)
    }

    /**
     * Note: The equivalent resource is `Texture3D`.
     *
     * Generated from Godot docs: RenderingServer.texture_3d_create
     */
    @JvmStatic
    fun texture3dCreate(format: Long, width: Int, height: Int, depth: Int, mipmaps: Boolean, data: List<Image>): RID {
        return ObjectCalls.ptrcallWithLongThreeIntBoolObjectListArgsRetRID(texture3dCreateBind, singleton, format, width, height, depth, mipmaps, data)
    }

    /**
     * This method does nothing and always returns an invalid `RID`.
     *
     * Generated from Godot docs: RenderingServer.texture_proxy_create
     */
    @JvmStatic
    fun textureProxyCreate(base: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(textureProxyCreateBind, singleton, base)
    }

    /**
     * Creates a texture based on a native handle that was created outside of Godot's renderer. Note:
     * If using only the rendering device renderer, it's recommend to use
     * `RenderingDevice.texture_create_from_extension` together with
     * `RenderingServer.texture_rd_create`, rather than this method. This way, the texture's format and
     * usage can be controlled more effectively.
     *
     * Generated from Godot docs: RenderingServer.texture_create_from_native_handle
     */
    @JvmStatic
    fun textureCreateFromNativeHandle(type: Long, format: Long, nativeHandle: Long, width: Int, height: Int, depth: Int, layers: Int = 1, layeredType: Long = 0L): RID {
        return ObjectCalls.ptrcallWithThreeLongFourIntLongArgsRetRID(textureCreateFromNativeHandleBind, singleton, type, format, nativeHandle, width, height, depth, layers, layeredType)
    }

    /**
     * Creates a 2-dimensional texture and adds it to the RenderingServer. It can be accessed with the
     * RID that is returned. This RID will be used in all `texture_drawable*` RenderingServer
     * functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. Note: The equivalent resource is `DrawableTexture2D`.
     *
     * Generated from Godot docs: RenderingServer.texture_drawable_create
     */
    @JvmStatic
    fun textureDrawableCreate(width: Int, height: Int, format: Long, color: Color, withMipmaps: Boolean = false): RID {
        return ObjectCalls.ptrcallWithTwoIntLongColorBoolArgsRetRID(textureDrawableCreateBind, singleton, width, height, format, color, withMipmaps)
    }

    /**
     * Updates the texture specified by the `texture` `RID` with the data in `image`. A `layer` must
     * also be specified, which should be `0` when updating a single-layer texture (`Texture2D`). Note:
     * The `image` must have the same width, height and format as the current `texture` data.
     * Otherwise, an error will be printed and the original texture won't be modified. If you need to
     * use different width, height or format, use `texture_replace` instead.
     *
     * Generated from Godot docs: RenderingServer.texture_2d_update
     */
    @JvmStatic
    fun texture2dUpdate(texture: RID, image: Image?, layer: Int) {
        ObjectCalls.ptrcallWithRIDObjectIntArgs(texture2dUpdateBind, singleton, texture, image?.requireOpenHandle() ?: MemorySegment.NULL, layer)
    }

    /**
     * Updates the texture specified by the `texture` `RID`'s data with the data in `data`. All the
     * texture's layers must be replaced at once. Note: The `texture` must have the same width, height,
     * depth and format as the current texture data. Otherwise, an error will be printed and the
     * original texture won't be modified. If you need to use different width, height, depth or format,
     * use `texture_replace` instead.
     *
     * Generated from Godot docs: RenderingServer.texture_3d_update
     */
    @JvmStatic
    fun texture3dUpdate(texture: RID, data: List<Image>) {
        ObjectCalls.ptrcallWithRIDAndObjectListArgs(texture3dUpdateBind, singleton, texture, data)
    }

    /**
     * This method does nothing.
     *
     * Generated from Godot docs: RenderingServer.texture_proxy_update
     */
    @JvmStatic
    fun textureProxyUpdate(texture: RID, proxyTo: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(textureProxyUpdateBind, singleton, texture, proxyTo)
    }

    /**
     * Creates a placeholder for a 2-dimensional layered texture and adds it to the RenderingServer. It
     * can be accessed with the RID that is returned. This RID will be used in all
     * `texture_2d_layered_*` RenderingServer functions, although it does nothing when used. See also
     * `texture_2d_layered_placeholder_create`. Once finished with your RID, you will want to free the
     * RID using the RenderingServer's `free_rid` method. Note: The equivalent resource is
     * `PlaceholderTexture2D`.
     *
     * Generated from Godot docs: RenderingServer.texture_2d_placeholder_create
     */
    @JvmStatic
    fun texture2dPlaceholderCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(texture2dPlaceholderCreateBind, singleton)
    }

    /**
     * Creates a placeholder for a 2-dimensional layered texture and adds it to the RenderingServer. It
     * can be accessed with the RID that is returned. This RID will be used in all
     * `texture_2d_layered_*` RenderingServer functions, although it does nothing when used. See also
     * `texture_2d_placeholder_create`. Note: The equivalent resource is `PlaceholderTextureLayered`.
     *
     * Generated from Godot docs: RenderingServer.texture_2d_layered_placeholder_create
     */
    @JvmStatic
    fun texture2dLayeredPlaceholderCreate(layeredType: Long): RID {
        return ObjectCalls.ptrcallWithLongArgRetRID(texture2dLayeredPlaceholderCreateBind, singleton, layeredType)
    }

    /**
     * Creates a placeholder for a 3-dimensional texture and adds it to the RenderingServer. It can be
     * accessed with the RID that is returned. This RID will be used in all `texture_3d_*`
     * RenderingServer functions, although it does nothing when used. Once finished with your RID, you
     * will want to free the RID using the RenderingServer's `free_rid` method. Note: The equivalent
     * resource is `PlaceholderTexture3D`.
     *
     * Generated from Godot docs: RenderingServer.texture_3d_placeholder_create
     */
    @JvmStatic
    fun texture3dPlaceholderCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(texture3dPlaceholderCreateBind, singleton)
    }

    /**
     * Returns an `Image` instance from the given `texture` `RID`.
     *
     * Generated from Godot docs: RenderingServer.texture_2d_get
     */
    @JvmStatic
    fun texture2dGet(texture: RID): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithRIDArgRetObject(texture2dGetBind, singleton, texture))
    }

    /**
     * Returns an `Image` instance from the given `texture` `RID` and `layer`.
     *
     * Generated from Godot docs: RenderingServer.texture_2d_layer_get
     */
    @JvmStatic
    fun texture2dLayerGet(texture: RID, layer: Int): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithRIDAndIntArgRetObject(texture2dLayerGetBind, singleton, texture, layer))
    }

    /**
     * Returns 3D texture data as an array of `Image`s for the specified texture `RID`.
     *
     * Generated from Godot docs: RenderingServer.texture_3d_get
     */
    @JvmStatic
    fun texture3dGet(texture: RID): List<Image> {
        return ObjectCalls.ptrcallWithRIDArgRetTypedObjectList(texture3dGetBind, singleton, texture, Image::fromHandle)
    }

    /**
     * Calculates new MipMaps for the given Drawable `texture`.
     *
     * Generated from Godot docs: RenderingServer.texture_drawable_generate_mipmaps
     */
    @JvmStatic
    fun textureDrawableGenerateMipmaps(texture: RID) {
        ObjectCalls.ptrcallWithRIDArg(textureDrawableGenerateMipmapsBind, singleton, texture)
    }

    /**
     * Returns a ShaderMaterial with the default texture_blit Shader.
     *
     * Generated from Godot docs: RenderingServer.texture_drawable_get_default_material
     */
    @JvmStatic
    fun textureDrawableGetDefaultMaterial(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(textureDrawableGetDefaultMaterialBind, singleton)
    }

    /**
     * Replaces `texture`'s texture data by the texture specified by the `by_texture` RID, without
     * changing `texture`'s RID.
     *
     * Generated from Godot docs: RenderingServer.texture_replace
     */
    @JvmStatic
    fun textureReplace(texture: RID, byTexture: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(textureReplaceBind, singleton, texture, byTexture)
    }

    /**
     * Sets the size at which the texture should be displayed in 2D, ignoring its original size. This
     * does not rescale the texture data itself, only how it is drawn in 2D. Set `width` and `height`
     * to 0 to disable the size override.
     *
     * Generated from Godot docs: RenderingServer.texture_set_size_override
     */
    @JvmStatic
    fun textureSetSizeOverride(texture: RID, width: Int, height: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(textureSetSizeOverrideBind, singleton, texture, width, height)
    }

    /**
     * Sets the resource path for this texture RID. See also `texture_get_path`. Note: This is purely a
     * hint and does not cause the texture to be automatically saved when set to a `res://` path.
     *
     * Generated from Godot docs: RenderingServer.texture_set_path
     */
    @JvmStatic
    fun textureSetPath(texture: RID, path: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(textureSetPathBind, singleton, texture, path)
    }

    /**
     * Returns the resource path (starting with `res://` or `uid://`) for the specified texture RID.
     * Returns an empty `String` if the resource is built-in. See also `texture_set_path`.
     *
     * Generated from Godot docs: RenderingServer.texture_get_path
     */
    @JvmStatic
    fun textureGetPath(texture: RID): String {
        return ObjectCalls.ptrcallWithRIDArgRetString(textureGetPathBind, singleton, texture)
    }

    /**
     * Returns the format for the texture.
     *
     * Generated from Godot docs: RenderingServer.texture_get_format
     */
    @JvmStatic
    fun textureGetFormat(texture: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(textureGetFormatBind, singleton, texture)
    }

    /**
     * Sets whether the texture RID should force redrawing when it's visible on screen when
     * `OS.low_processor_usage_mode` is `true`. This is used by `AnimatedTexture` to force redrawing.
     *
     * Generated from Godot docs: RenderingServer.texture_set_force_redraw_if_visible
     */
    @JvmStatic
    fun textureSetForceRedrawIfVisible(texture: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(textureSetForceRedrawIfVisibleBind, singleton, texture, enable)
    }

    /**
     * Creates a new texture object based on a texture created directly on the `RenderingDevice`. If
     * the texture contains layers, `layer_type` is used to define the layer type. Once finished with
     * your RID, you will want to free the RID using the RenderingServer's `free_rid` method. Note: The
     * RenderingServer's `free_rid` won't free the underlying `rd_texture`, you will want to free the
     * `rd_texture` using `RenderingDevice.free_rid`.
     *
     * Generated from Godot docs: RenderingServer.texture_rd_create
     */
    @JvmStatic
    fun textureRdCreate(rdTexture: RID, layerType: Long = 0L): RID {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetRID(textureRdCreateBind, singleton, rdTexture, layerType)
    }

    /**
     * Returns a texture `RID` that can be used with `RenderingDevice`. `srgb` should be `true` when
     * the texture uses nonlinear sRGB encoding and `false` when the texture uses linear encoding.
     *
     * Generated from Godot docs: RenderingServer.texture_get_rd_texture
     */
    @JvmStatic
    fun textureGetRdTexture(texture: RID, srgb: Boolean = false): RID {
        return ObjectCalls.ptrcallWithRIDAndBoolArgRetRID(textureGetRdTextureBind, singleton, texture, srgb)
    }

    /**
     * Returns the internal graphics handle for this texture object. For use when communicating with
     * third-party APIs mostly with GDExtension. `srgb` should be `true` when the texture uses
     * nonlinear sRGB encoding and `false` when the texture uses linear encoding. Note: This function
     * returns a `uint64_t` which internally maps to a `GLuint` (OpenGL) or `VkImage` (Vulkan).
     *
     * Generated from Godot docs: RenderingServer.texture_get_native_handle
     */
    @JvmStatic
    fun textureGetNativeHandle(texture: RID, srgb: Boolean = false): Long {
        return ObjectCalls.ptrcallWithRIDAndBoolArgRetLong(textureGetNativeHandleBind, singleton, texture, srgb)
    }

    /**
     * Creates an empty shader and adds it to the RenderingServer. It can be accessed with the RID that
     * is returned. This RID will be used in all `shader_*` RenderingServer functions. Once finished
     * with your RID, you will want to free the RID using the RenderingServer's `free_rid` method.
     * Note: The equivalent resource is `Shader`.
     *
     * Generated from Godot docs: RenderingServer.shader_create
     */
    @JvmStatic
    fun shaderCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(shaderCreateBind, singleton)
    }

    /**
     * Sets the shader's source code (which triggers recompilation after being changed).
     *
     * Generated from Godot docs: RenderingServer.shader_set_code
     */
    @JvmStatic
    fun shaderSetCode(shader: RID, code: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(shaderSetCodeBind, singleton, shader, code)
    }

    /**
     * Sets the path hint for the specified shader. This should generally match the `Shader` resource's
     * `Resource.resource_path`.
     *
     * Generated from Godot docs: RenderingServer.shader_set_path_hint
     */
    @JvmStatic
    fun shaderSetPathHint(shader: RID, path: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(shaderSetPathHintBind, singleton, shader, path)
    }

    /**
     * Returns a shader's source code as a string.
     *
     * Generated from Godot docs: RenderingServer.shader_get_code
     */
    @JvmStatic
    fun shaderGetCode(shader: RID): String {
        return ObjectCalls.ptrcallWithRIDArgRetString(shaderGetCodeBind, singleton, shader)
    }

    /**
     * Returns the parameters of a shader.
     *
     * Generated from Godot docs: RenderingServer.get_shader_parameter_list
     */
    @JvmStatic
    fun getShaderParameterList(shader: RID): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(getShaderParameterListBind, singleton, shader)
    }

    /**
     * Returns the default value for the specified shader uniform. This is usually the value written in
     * the shader source code.
     *
     * Generated from Godot docs: RenderingServer.shader_get_parameter_default
     */
    @JvmStatic
    fun shaderGetParameterDefault(shader: RID, name: String): Any? {
        return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(shaderGetParameterDefaultBind, singleton, shader, name)
    }

    /**
     * Sets a shader's default texture. Overwrites the texture given by name. Note: If the sampler
     * array is used use `index` to access the specified texture.
     *
     * Generated from Godot docs: RenderingServer.shader_set_default_texture_parameter
     */
    @JvmStatic
    fun shaderSetDefaultTextureParameter(shader: RID, name: String, texture: RID, index: Int = 0) {
        ObjectCalls.ptrcallWithRIDStringNameRIDIntArgs(shaderSetDefaultTextureParameterBind, singleton, shader, name, texture, index)
    }

    /**
     * Returns a default texture from a shader searched by name. Note: If the sampler array is used use
     * `index` to access the specified texture.
     *
     * Generated from Godot docs: RenderingServer.shader_get_default_texture_parameter
     */
    @JvmStatic
    fun shaderGetDefaultTextureParameter(shader: RID, name: String, index: Int = 0): RID {
        return ObjectCalls.ptrcallWithRIDStringNameAndIntArgRetRID(shaderGetDefaultTextureParameterBind, singleton, shader, name, index)
    }

    /**
     * Creates an empty material and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID will be used in all `material_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. Note: The equivalent resource is `Material`.
     *
     * Generated from Godot docs: RenderingServer.material_create
     */
    @JvmStatic
    fun materialCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(materialCreateBind, singleton)
    }

    /**
     * Sets a shader material's shader.
     *
     * Generated from Godot docs: RenderingServer.material_set_shader
     */
    @JvmStatic
    fun materialSetShader(shaderMaterial: RID, shader: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(materialSetShaderBind, singleton, shaderMaterial, shader)
    }

    /**
     * Sets a material's parameter.
     *
     * Generated from Godot docs: RenderingServer.material_set_param
     */
    @JvmStatic
    fun materialSetParam(material: RID, parameter: String, value: Any?) {
        ObjectCalls.ptrcallWithRIDStringNameAndVariantArgs(materialSetParamBind, singleton, material, parameter, value)
    }

    /**
     * Returns the value of a certain material's parameter.
     *
     * Generated from Godot docs: RenderingServer.material_get_param
     */
    @JvmStatic
    fun materialGetParam(material: RID, parameter: String): Any? {
        return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(materialGetParamBind, singleton, material, parameter)
    }

    /**
     * Sets a material's render priority.
     *
     * Generated from Godot docs: RenderingServer.material_set_render_priority
     */
    @JvmStatic
    fun materialSetRenderPriority(material: RID, priority: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(materialSetRenderPriorityBind, singleton, material, priority)
    }

    /**
     * Sets an object's next material.
     *
     * Generated from Godot docs: RenderingServer.material_set_next_pass
     */
    @JvmStatic
    fun materialSetNextPass(material: RID, nextMaterial: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(materialSetNextPassBind, singleton, material, nextMaterial)
    }

    /**
     * When using the Mobile renderer, `material_set_use_debanding` can be used to enable or disable
     * the debanding feature of 3D materials (`BaseMaterial3D` and `ShaderMaterial`).
     * `material_set_use_debanding` has no effect when using the Compatibility or Forward+ renderer. In
     * Forward+, `Viewport` debanding can be used instead. See also
     * `ProjectSettings.rendering/anti_aliasing/quality/use_debanding` and
     * `RenderingServer.viewport_set_use_debanding`.
     *
     * Generated from Godot docs: RenderingServer.material_set_use_debanding
     */
    @JvmStatic
    fun materialSetUseDebanding(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(materialSetUseDebandingBind, singleton, enable)
    }

    /**
     * Creates a new mesh with predefined surfaces for it and adds the mesh to the RenderingServer. It
     * can be accessed with the RID that is returned. This RID will be used in all `mesh_*`
     * RenderingServer functions. This method is more efficient for creating meshes with multiple
     * surfaces compared to creating an empty mesh with `mesh_create` and adding surfaces one by one
     * with `mesh_add_surface`. Each element in the `surfaces` array must follow the same structure as
     * described in `mesh_add_surface`. The `blend_shape_count` parameter must match the blend shape
     * data defined in all surfaces. Once finished with your RID, you will want to free the RID using
     * the RenderingServer's `free_rid` method. To place in a scene, attach this mesh to an instance
     * using `instance_set_base` using the returned RID. Note: The equivalent resource is `Mesh`.
     *
     * Generated from Godot docs: RenderingServer.mesh_create_from_surfaces
     */
    @JvmStatic
    fun meshCreateFromSurfaces(surfaces: List<Map<String, Any?>>, blendShapeCount: Int = 0): RID {
        return ObjectCalls.ptrcallWithDictionaryListIntArgsRetRID(meshCreateFromSurfacesBind, singleton, surfaces, blendShapeCount)
    }

    /**
     * Creates a new mesh and adds it to the RenderingServer. It can be accessed with the RID that is
     * returned. This RID will be used in all `mesh_*` RenderingServer functions. Once finished with
     * your RID, you will want to free the RID using the RenderingServer's `free_rid` method. To place
     * in a scene, attach this mesh to an instance using `instance_set_base` using the returned RID.
     * Note: The equivalent resource is `Mesh`.
     *
     * Generated from Godot docs: RenderingServer.mesh_create
     */
    @JvmStatic
    fun meshCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(meshCreateBind, singleton)
    }

    /**
     * Returns the offset of a given attribute by `array_index` in the start of its respective buffer.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_format_offset
     */
    @JvmStatic
    fun meshSurfaceGetFormatOffset(format: Long, vertexCount: Int, arrayIndex: Int): Long {
        return ObjectCalls.ptrcallWithLongAndTwoIntArgsRetUInt32(meshSurfaceGetFormatOffsetBind, singleton, format, vertexCount, arrayIndex)
    }

    /**
     * Returns the stride of the vertex positions for a mesh with given `format`. Note importantly that
     * vertex positions are stored consecutively and are not interleaved with the other attributes in
     * the vertex buffer (normals and tangents).
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_format_vertex_stride
     */
    @JvmStatic
    fun meshSurfaceGetFormatVertexStride(format: Long, vertexCount: Int): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetUInt32(meshSurfaceGetFormatVertexStrideBind, singleton, format, vertexCount)
    }

    /**
     * Returns the stride of the combined normals and tangents for a mesh with given `format`. Note
     * importantly that, while normals and tangents are in the vertex buffer with vertices, they are
     * only interleaved with each other and so have a different stride than vertex positions.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_format_normal_tangent_stride
     */
    @JvmStatic
    fun meshSurfaceGetFormatNormalTangentStride(format: Long, vertexCount: Int): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetUInt32(meshSurfaceGetFormatNormalTangentStrideBind, singleton, format, vertexCount)
    }

    /**
     * Returns the stride of the attribute buffer for a mesh with given `format`.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_format_attribute_stride
     */
    @JvmStatic
    fun meshSurfaceGetFormatAttributeStride(format: Long, vertexCount: Int): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetUInt32(meshSurfaceGetFormatAttributeStrideBind, singleton, format, vertexCount)
    }

    /**
     * Returns the stride of the skin buffer for a mesh with given `format`.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_format_skin_stride
     */
    @JvmStatic
    fun meshSurfaceGetFormatSkinStride(format: Long, vertexCount: Int): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetUInt32(meshSurfaceGetFormatSkinStrideBind, singleton, format, vertexCount)
    }

    /**
     * Returns the stride of the index buffer for a mesh with the given `format`.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_format_index_stride
     */
    @JvmStatic
    fun meshSurfaceGetFormatIndexStride(format: Long, vertexCount: Int): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetUInt32(meshSurfaceGetFormatIndexStrideBind, singleton, format, vertexCount)
    }

    /**
     * Creates a new surface on the given `mesh`. Equivalent to `mesh_add_surface_from_arrays`, but
     * takes a single `Dictionary` argument instead of separate arguments. The dictionary must follow
     * this structure:
     *
     * Generated from Godot docs: RenderingServer.mesh_add_surface
     */
    @JvmStatic
    fun meshAddSurface(mesh: RID, surface: Map<String, Any?>) {
        ObjectCalls.ptrcallWithRIDAndDictionaryArg(meshAddSurfaceBind, singleton, mesh, surface)
    }

    /**
     * Creates a new surface on the given `mesh`. `mesh_get_surface_count` will become the surface
     * index for this new surface. Surfaces are created to be rendered using a `primitive`, which may
     * be any of the values defined in `Mesh.PrimitiveType`. The `arrays` argument is an array of
     * arrays. Each of the `Mesh.ARRAY_MAX` elements contains an array with some of the mesh data for
     * this surface as described by the corresponding member of `Mesh.ArrayType` or `null` if it is not
     * used by the surface. For example, `arrays[0]` is the array of vertices. That first vertex
     * sub-array is always required; the others are optional. Adding an index array puts this surface
     * into "index mode" where the vertex and other arrays become the sources of data and the index
     * array defines the vertex order. All sub-arrays must have the same length as the vertex array (or
     * be an exact multiple of the vertex array's length, when multiple elements of a sub-array
     * correspond to a single vertex) or be empty, except for `Mesh.ARRAY_INDEX` if it is used. The
     * `blend_shapes` argument is an array of vertex data for each blend shape. Each element is an
     * array of the same structure as `arrays`, but `Mesh.ARRAY_VERTEX`, `Mesh.ARRAY_NORMAL`, and
     * `Mesh.ARRAY_TANGENT` are set if and only if they are set in `arrays` and all other entries are
     * `null`. The `lods` argument is a dictionary with `float` keys and `PackedInt32Array` values.
     * Each entry in the dictionary represents an LOD level of the surface, where the value is the
     * `Mesh.ARRAY_INDEX` array to use for the LOD level and the key is roughly proportional to the
     * distance at which the LOD stats being used. I.e., increasing the key of an LOD also increases
     * the distance that the objects has to be from the camera before the LOD is used. The
     * `compress_format` argument is the bitwise OR of, as required: One value of `ArrayFormat` left
     * shifted by `ARRAY_FORMAT_CUSTOMn_SHIFT` for each custom channel in use,
     * `ARRAY_FLAG_USE_DYNAMIC_UPDATE`, `ARRAY_FLAG_USE_8_BONE_WEIGHTS`, or
     * `ARRAY_FLAG_USES_EMPTY_VERTEX_ARRAY`. See `ArrayMesh.add_surface_from_arrays` and
     * `ImporterMesh.add_surface` for higher-level equivalents of this method. Note: When using
     * indices, it is recommended to only use points, lines, or triangles.
     *
     * Generated from Godot docs: RenderingServer.mesh_add_surface_from_arrays
     */
    @JvmStatic
    fun meshAddSurfaceFromArrays(mesh: RID, primitive: Long, arrays: List<Any?>, blendShapes: List<Any?> = emptyList(), lods: Map<String, Any?> = emptyMap(), compressFormat: Long = 0L) {
        ObjectCalls.ptrcallWithRIDLongTwoArrayDictionaryLongArgs(meshAddSurfaceFromArraysBind, singleton, mesh, primitive, arrays, blendShapes, lods, compressFormat)
    }

    /**
     * Returns a mesh's blend shape count.
     *
     * Generated from Godot docs: RenderingServer.mesh_get_blend_shape_count
     */
    @JvmStatic
    fun meshGetBlendShapeCount(mesh: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(meshGetBlendShapeCountBind, singleton, mesh)
    }

    /**
     * Sets a mesh's blend shape mode.
     *
     * Generated from Godot docs: RenderingServer.mesh_set_blend_shape_mode
     */
    @JvmStatic
    fun meshSetBlendShapeMode(mesh: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(meshSetBlendShapeModeBind, singleton, mesh, mode)
    }

    /**
     * Returns a mesh's blend shape mode.
     *
     * Generated from Godot docs: RenderingServer.mesh_get_blend_shape_mode
     */
    @JvmStatic
    fun meshGetBlendShapeMode(mesh: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(meshGetBlendShapeModeBind, singleton, mesh)
    }

    /**
     * Sets a mesh's surface's material.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_set_material
     */
    @JvmStatic
    fun meshSurfaceSetMaterial(mesh: RID, surface: Int, material: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(meshSurfaceSetMaterialBind, singleton, mesh, surface, material)
    }

    /**
     * Returns a mesh's surface's material.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_material
     */
    @JvmStatic
    fun meshSurfaceGetMaterial(mesh: RID, surface: Int): RID {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetRID(meshSurfaceGetMaterialBind, singleton, mesh, surface)
    }

    /**
     * Returns a mesh's surface as a dictionary following the same structure as described in
     * `mesh_add_surface`.
     *
     * Generated from Godot docs: RenderingServer.mesh_get_surface
     */
    @JvmStatic
    fun meshGetSurface(mesh: RID, surface: Int): Map<String, Any?> {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetDictionary(meshGetSurfaceBind, singleton, mesh, surface)
    }

    /**
     * Returns a mesh's surface's buffer arrays.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_arrays
     */
    @JvmStatic
    fun meshSurfaceGetArrays(mesh: RID, surface: Int): List<Any?> {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetArray(meshSurfaceGetArraysBind, singleton, mesh, surface)
    }

    /**
     * Returns a mesh's surface's arrays for blend shapes.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_blend_shape_arrays
     */
    @JvmStatic
    fun meshSurfaceGetBlendShapeArrays(mesh: RID, surface: Int): List<List<Any?>> {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetArrayList(meshSurfaceGetBlendShapeArraysBind, singleton, mesh, surface)
    }

    /**
     * Returns a mesh's number of surfaces.
     *
     * Generated from Godot docs: RenderingServer.mesh_get_surface_count
     */
    @JvmStatic
    fun meshGetSurfaceCount(mesh: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(meshGetSurfaceCountBind, singleton, mesh)
    }

    /**
     * Sets a mesh's custom aabb.
     *
     * Generated from Godot docs: RenderingServer.mesh_set_custom_aabb
     */
    @JvmStatic
    fun meshSetCustomAabb(mesh: RID, aabb: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(meshSetCustomAabbBind, singleton, mesh, aabb)
    }

    /**
     * Returns a mesh's custom aabb.
     *
     * Generated from Godot docs: RenderingServer.mesh_get_custom_aabb
     */
    @JvmStatic
    fun meshGetCustomAabb(mesh: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(meshGetCustomAabbBind, singleton, mesh)
    }

    /**
     * Removes the surface at the given index from the Mesh, shifting surfaces with higher index down
     * by one.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_remove
     */
    @JvmStatic
    fun meshSurfaceRemove(mesh: RID, surface: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(meshSurfaceRemoveBind, singleton, mesh, surface)
    }

    /**
     * Removes all surfaces from a mesh.
     *
     * Generated from Godot docs: RenderingServer.mesh_clear
     */
    @JvmStatic
    fun meshClear(mesh: RID) {
        ObjectCalls.ptrcallWithRIDArg(meshClearBind, singleton, mesh)
    }

    /**
     * Updates the vertex buffer of the mesh surface with the given `data`. The expected data per
     * vertex is 8 or 12 bytes (4 bytes per float, 2 floats per `Vector2`, and 3 floats per `Vector3`)
     * depending on if the mesh is using `Vector2` or `Vector3` vertices. This value can be determined
     * with `mesh_surface_get_format_vertex_stride` instead. The starting point of the updates can be
     * changed with `offset`. The value of `offset` should be a multiple of 12 bytes in most cases to
     * align to each vertex. A `PackedVector3Array` of vertex locations can be converted into a
     * `PackedByteArray` using `PackedVector3Array.to_byte_array` for use in `data`.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_update_vertex_region
     */
    @JvmStatic
    fun meshSurfaceUpdateVertexRegion(mesh: RID, surface: Int, offset: Int, data: ByteArray) {
        ObjectCalls.ptrcallWithRIDIntIntAndByteArrayArgs(meshSurfaceUpdateVertexRegionBind, singleton, mesh, surface, offset, data)
    }

    /**
     * Updates the attribute buffer of the mesh surface with the given `data`. The expected data per
     * attribute is 8 or 12 bytes (4 bytes per float, 2 floats per `Vector2`, and 3 floats per
     * `Vector3`) depending on if the mesh is using `Vector2` or `Vector3` vertices. This value can be
     * determined with `mesh_surface_get_format_attribute_stride` instead. The starting point of the
     * updates can be changed with `offset`. The value of `offset` should be a multiple of 12 bytes in
     * most cases to align to each attribute. A `PackedVector3Array` of attribute locations can be
     * converted into a `PackedByteArray` using `PackedVector3Array.to_byte_array` for use in `data`.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_update_attribute_region
     */
    @JvmStatic
    fun meshSurfaceUpdateAttributeRegion(mesh: RID, surface: Int, offset: Int, data: ByteArray) {
        ObjectCalls.ptrcallWithRIDIntIntAndByteArrayArgs(meshSurfaceUpdateAttributeRegionBind, singleton, mesh, surface, offset, data)
    }

    /**
     * Updates the skin buffer of the mesh surface with the given `data`. The expected data per skin is
     * 8 or 12 bytes (4 bytes per float, 2 floats per `Vector2`, and 3 floats per `Vector3`) depending
     * on if the mesh is using `Vector2` or `Vector3` vertices. This value can be determined with
     * `mesh_surface_get_format_skin_stride` instead. The starting point of the updates can be changed
     * with `offset`. The value of `offset` should be a multiple of 12 bytes in most cases to align to
     * each skin. A `PackedVector3Array` of skin locations can be converted into a `PackedByteArray`
     * using `PackedVector3Array.to_byte_array` for use in `data`.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_update_skin_region
     */
    @JvmStatic
    fun meshSurfaceUpdateSkinRegion(mesh: RID, surface: Int, offset: Int, data: ByteArray) {
        ObjectCalls.ptrcallWithRIDIntIntAndByteArrayArgs(meshSurfaceUpdateSkinRegionBind, singleton, mesh, surface, offset, data)
    }

    /**
     * Updates the index buffer of the mesh surface with the given `data`. The expected data are 16 or
     * 32-bit unsigned integers, which can be determined with `mesh_surface_get_format_index_stride`.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_update_index_region
     */
    @JvmStatic
    fun meshSurfaceUpdateIndexRegion(mesh: RID, surface: Int, offset: Int, data: ByteArray) {
        ObjectCalls.ptrcallWithRIDIntIntAndByteArrayArgs(meshSurfaceUpdateIndexRegionBind, singleton, mesh, surface, offset, data)
    }

    /**
     * Sets an optional second mesh which can be used for rendering shadows and the depth prepass. Can
     * be used to increase performance by supplying a mesh with fused vertices and only vertex position
     * data (without normals, UVs, colors, etc.). Note: This mesh must have exactly the same vertex
     * positions as the source mesh (including the source mesh's LODs, if present). If vertex positions
     * differ, then the mesh will not draw correctly.
     *
     * Generated from Godot docs: RenderingServer.mesh_set_shadow_mesh
     */
    @JvmStatic
    fun meshSetShadowMesh(mesh: RID, shadowMesh: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(meshSetShadowMeshBind, singleton, mesh, shadowMesh)
    }

    /**
     * Creates a new multimesh on the RenderingServer and returns an `RID` handle. This RID will be
     * used in all `multimesh_*` RenderingServer functions. Once finished with your RID, you will want
     * to free the RID using the RenderingServer's `free_rid` method. To place in a scene, attach this
     * multimesh to an instance using `instance_set_base` using the returned RID. Note: The equivalent
     * resource is `MultiMesh`.
     *
     * Generated from Godot docs: RenderingServer.multimesh_create
     */
    @JvmStatic
    fun multimeshCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(multimeshCreateBind, singleton)
    }

    /**
     * Sets up the multimesh using the specified data. The number of instances is set by `instances`.
     * The format of the instance transforms is set by `transform_format`, which should be set
     * according to whether the multimesh is meant to be rendered in 2D or 3D. If `color_format` is
     * `true`, each instance will have a color associated with it. If `custom_data_format` is `true`,
     * each instance will have a custom data vector associated with it. If `use_indirect` is `true`, an
     * indirect command buffer will be created for this multimesh, allowing the instance count to be
     * modified directly on the GPU. See also `multimesh_get_command_buffer_rd_rid`.
     *
     * Generated from Godot docs: RenderingServer.multimesh_allocate_data
     */
    @JvmStatic
    fun multimeshAllocateData(multimesh: RID, instances: Int, transformFormat: Long, colorFormat: Boolean = false, customDataFormat: Boolean = false, useIndirect: Boolean = false) {
        ObjectCalls.ptrcallWithRIDIntLongThreeBoolArgs(multimeshAllocateDataBind, singleton, multimesh, instances, transformFormat, colorFormat, customDataFormat, useIndirect)
    }

    /**
     * Returns the number of instances allocated for this multimesh.
     *
     * Generated from Godot docs: RenderingServer.multimesh_get_instance_count
     */
    @JvmStatic
    fun multimeshGetInstanceCount(multimesh: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(multimeshGetInstanceCountBind, singleton, multimesh)
    }

    /**
     * Sets the mesh to be drawn by the multimesh. Equivalent to `MultiMesh.mesh`.
     *
     * Generated from Godot docs: RenderingServer.multimesh_set_mesh
     */
    @JvmStatic
    fun multimeshSetMesh(multimesh: RID, mesh: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(multimeshSetMeshBind, singleton, multimesh, mesh)
    }

    /**
     * Sets the `Transform3D` for this instance. Equivalent to `MultiMesh.set_instance_transform`.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_set_transform
     */
    @JvmStatic
    fun multimeshInstanceSetTransform(multimesh: RID, index: Int, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform3DArg(multimeshInstanceSetTransformBind, singleton, multimesh, index, transform)
    }

    /**
     * Sets the `Transform2D` for this instance. For use when multimesh is used in 2D. Equivalent to
     * `MultiMesh.set_instance_transform_2d`.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_set_transform_2d
     */
    @JvmStatic
    fun multimeshInstanceSetTransform2d(multimesh: RID, index: Int, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform2DArg(multimeshInstanceSetTransform2dBind, singleton, multimesh, index, transform)
    }

    /**
     * Sets the color by which this instance will be modulated. Equivalent to
     * `MultiMesh.set_instance_color`.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_set_color
     */
    @JvmStatic
    fun multimeshInstanceSetColor(multimesh: RID, index: Int, color: Color) {
        ObjectCalls.ptrcallWithRIDIntAndColorArgs(multimeshInstanceSetColorBind, singleton, multimesh, index, color)
    }

    /**
     * Sets the custom data for this instance. Custom data is passed as a `Color`, but is interpreted
     * as a `vec4` in the shader. Equivalent to `MultiMesh.set_instance_custom_data`.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_set_custom_data
     */
    @JvmStatic
    fun multimeshInstanceSetCustomData(multimesh: RID, index: Int, customData: Color) {
        ObjectCalls.ptrcallWithRIDIntAndColorArgs(multimeshInstanceSetCustomDataBind, singleton, multimesh, index, customData)
    }

    /**
     * Returns the RID of the mesh that will be used in drawing this multimesh.
     *
     * Generated from Godot docs: RenderingServer.multimesh_get_mesh
     */
    @JvmStatic
    fun multimeshGetMesh(multimesh: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(multimeshGetMeshBind, singleton, multimesh)
    }

    /**
     * Calculates and returns the axis-aligned bounding box that encloses all instances within the
     * multimesh.
     *
     * Generated from Godot docs: RenderingServer.multimesh_get_aabb
     */
    @JvmStatic
    fun multimeshGetAabb(multimesh: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(multimeshGetAabbBind, singleton, multimesh)
    }

    /**
     * Sets the custom AABB for this MultiMesh resource.
     *
     * Generated from Godot docs: RenderingServer.multimesh_set_custom_aabb
     */
    @JvmStatic
    fun multimeshSetCustomAabb(multimesh: RID, aabb: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(multimeshSetCustomAabbBind, singleton, multimesh, aabb)
    }

    /**
     * Returns the custom AABB defined for this MultiMesh resource.
     *
     * Generated from Godot docs: RenderingServer.multimesh_get_custom_aabb
     */
    @JvmStatic
    fun multimeshGetCustomAabb(multimesh: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(multimeshGetCustomAabbBind, singleton, multimesh)
    }

    /**
     * Returns the `Transform3D` of the specified instance.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_get_transform
     */
    @JvmStatic
    fun multimeshInstanceGetTransform(multimesh: RID, index: Int): Transform3D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform3D(multimeshInstanceGetTransformBind, singleton, multimesh, index)
    }

    /**
     * Returns the `Transform2D` of the specified instance. For use when the multimesh is set to use 2D
     * transforms.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_get_transform_2d
     */
    @JvmStatic
    fun multimeshInstanceGetTransform2d(multimesh: RID, index: Int): Transform2D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform2D(multimeshInstanceGetTransform2dBind, singleton, multimesh, index)
    }

    /**
     * Returns the color by which the specified instance will be modulated.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_get_color
     */
    @JvmStatic
    fun multimeshInstanceGetColor(multimesh: RID, index: Int): Color {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetColor(multimeshInstanceGetColorBind, singleton, multimesh, index)
    }

    /**
     * Returns the custom data associated with the specified instance.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_get_custom_data
     */
    @JvmStatic
    fun multimeshInstanceGetCustomData(multimesh: RID, index: Int): Color {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetColor(multimeshInstanceGetCustomDataBind, singleton, multimesh, index)
    }

    /**
     * Sets the number of instances visible at a given time. If -1, all instances that have been
     * allocated are drawn. Equivalent to `MultiMesh.visible_instance_count`.
     *
     * Generated from Godot docs: RenderingServer.multimesh_set_visible_instances
     */
    @JvmStatic
    fun multimeshSetVisibleInstances(multimesh: RID, visible: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(multimeshSetVisibleInstancesBind, singleton, multimesh, visible)
    }

    /**
     * Returns the number of visible instances for this multimesh.
     *
     * Generated from Godot docs: RenderingServer.multimesh_get_visible_instances
     */
    @JvmStatic
    fun multimeshGetVisibleInstances(multimesh: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(multimeshGetVisibleInstancesBind, singleton, multimesh)
    }

    /**
     * Set the entire data to use for drawing the `multimesh` at once to `buffer` (such as instance
     * transforms and colors). `buffer`'s size must match the number of instances multiplied by the
     * per-instance data size (which depends on the enabled MultiMesh fields). Otherwise, an error
     * message is printed and nothing is rendered. See also `multimesh_get_buffer`. The per-instance
     * data size and expected data order is:
     *
     * Generated from Godot docs: RenderingServer.multimesh_set_buffer
     */
    @JvmStatic
    fun multimeshSetBuffer(multimesh: RID, buffer: List<Float>) {
        ObjectCalls.ptrcallWithRIDAndPackedFloat32ListArg(multimeshSetBufferBind, singleton, multimesh, buffer)
    }

    /**
     * Returns the `RenderingDevice` `RID` handle of the `MultiMesh` command buffer. This `RID` is only
     * valid if `use_indirect` is set to `true` when allocating data through `multimesh_allocate_data`.
     * It can be used to directly modify the instance count via buffer. The data structure is dependent
     * on both how many surfaces the mesh contains and whether it is indexed or not, the buffer has 5
     * integers in it, with the last unused if the mesh is not indexed. Each of the values in the
     * buffer correspond to these options:
     *
     * Generated from Godot docs: RenderingServer.multimesh_get_command_buffer_rd_rid
     */
    @JvmStatic
    fun multimeshGetCommandBufferRdRid(multimesh: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(multimeshGetCommandBufferRdRidBind, singleton, multimesh)
    }

    /**
     * Returns the `RenderingDevice` `RID` handle of the `MultiMesh`, which can be used as any other
     * buffer on the Rendering Device.
     *
     * Generated from Godot docs: RenderingServer.multimesh_get_buffer_rd_rid
     */
    @JvmStatic
    fun multimeshGetBufferRdRid(multimesh: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(multimeshGetBufferRdRidBind, singleton, multimesh)
    }

    /**
     * Returns the MultiMesh data (such as instance transforms, colors, etc.). See
     * `multimesh_set_buffer` for details on the returned data. Note: If the buffer is in the engine's
     * internal cache, it will have to be fetched from GPU memory and possibly decompressed. This means
     * `multimesh_get_buffer` is potentially a slow operation and should be avoided whenever possible.
     *
     * Generated from Godot docs: RenderingServer.multimesh_get_buffer
     */
    @JvmStatic
    fun multimeshGetBuffer(multimesh: RID): List<Float> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedFloat32List(multimeshGetBufferBind, singleton, multimesh)
    }

    /**
     * Alternative version of `multimesh_set_buffer` for use with physics interpolation. Takes both an
     * array of current data and an array of data for the previous physics tick.
     *
     * Generated from Godot docs: RenderingServer.multimesh_set_buffer_interpolated
     */
    @JvmStatic
    fun multimeshSetBufferInterpolated(multimesh: RID, buffer: List<Float>, bufferPrevious: List<Float>) {
        ObjectCalls.ptrcallWithRIDAndTwoPackedFloat32ListArgs(multimeshSetBufferInterpolatedBind, singleton, multimesh, buffer, bufferPrevious)
    }

    /**
     * Turns on and off physics interpolation for this MultiMesh resource.
     *
     * Generated from Godot docs: RenderingServer.multimesh_set_physics_interpolated
     */
    @JvmStatic
    fun multimeshSetPhysicsInterpolated(multimesh: RID, interpolated: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(multimeshSetPhysicsInterpolatedBind, singleton, multimesh, interpolated)
    }

    /**
     * Sets the physics interpolation quality for the `MultiMesh`. A value of
     * `MULTIMESH_INTERP_QUALITY_FAST` gives fast but low quality interpolation, a value of
     * `MULTIMESH_INTERP_QUALITY_HIGH` gives slower but higher quality interpolation.
     *
     * Generated from Godot docs: RenderingServer.multimesh_set_physics_interpolation_quality
     */
    @JvmStatic
    fun multimeshSetPhysicsInterpolationQuality(multimesh: RID, quality: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(multimeshSetPhysicsInterpolationQualityBind, singleton, multimesh, quality)
    }

    /**
     * Prevents physics interpolation for the specified instance during the current physics tick. This
     * is useful when moving an instance to a new location, to give an instantaneous change rather than
     * interpolation from the previous location.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_reset_physics_interpolation
     */
    @JvmStatic
    fun multimeshInstanceResetPhysicsInterpolation(multimesh: RID, index: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(multimeshInstanceResetPhysicsInterpolationBind, singleton, multimesh, index)
    }

    /**
     * Prevents physics interpolation for all instances during the current physics tick. This is useful
     * when moving all instances to new locations, to give instantaneous changes rather than
     * interpolation from the previous locations.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instances_reset_physics_interpolation
     */
    @JvmStatic
    fun multimeshInstancesResetPhysicsInterpolation(multimesh: RID) {
        ObjectCalls.ptrcallWithRIDArg(multimeshInstancesResetPhysicsInterpolationBind, singleton, multimesh)
    }

    /**
     * Creates a skeleton and adds it to the RenderingServer. It can be accessed with the RID that is
     * returned. This RID will be used in all `skeleton_*` RenderingServer functions. Once finished
     * with your RID, you will want to free the RID using the RenderingServer's `free_rid` method.
     *
     * Generated from Godot docs: RenderingServer.skeleton_create
     */
    @JvmStatic
    fun skeletonCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(skeletonCreateBind, singleton)
    }

    /**
     * Allocates data for this skeleton using the number of bones specified in `bones`. If
     * `is_2d_skeleton` is `true`, the skeleton will be treated as a 2D skeleton instead of a 3D
     * skeleton. See also `skeleton_get_bone_count`.
     *
     * Generated from Godot docs: RenderingServer.skeleton_allocate_data
     */
    @JvmStatic
    fun skeletonAllocateData(skeleton: RID, bones: Int, is2dSkeleton: Boolean = false) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(skeletonAllocateDataBind, singleton, skeleton, bones, is2dSkeleton)
    }

    /**
     * Returns the number of bones allocated for this skeleton. See also `skeleton_allocate_data`.
     *
     * Generated from Godot docs: RenderingServer.skeleton_get_bone_count
     */
    @JvmStatic
    fun skeletonGetBoneCount(skeleton: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(skeletonGetBoneCountBind, singleton, skeleton)
    }

    /**
     * Sets the `Transform3D` for a specific bone of this skeleton.
     *
     * Generated from Godot docs: RenderingServer.skeleton_bone_set_transform
     */
    @JvmStatic
    fun skeletonBoneSetTransform(skeleton: RID, bone: Int, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform3DArg(skeletonBoneSetTransformBind, singleton, skeleton, bone, transform)
    }

    /**
     * Returns the `Transform3D` set for a specific bone of this skeleton.
     *
     * Generated from Godot docs: RenderingServer.skeleton_bone_get_transform
     */
    @JvmStatic
    fun skeletonBoneGetTransform(skeleton: RID, bone: Int): Transform3D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform3D(skeletonBoneGetTransformBind, singleton, skeleton, bone)
    }

    /**
     * Sets the `Transform2D` for a specific bone of this skeleton.
     *
     * Generated from Godot docs: RenderingServer.skeleton_bone_set_transform_2d
     */
    @JvmStatic
    fun skeletonBoneSetTransform2d(skeleton: RID, bone: Int, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform2DArg(skeletonBoneSetTransform2dBind, singleton, skeleton, bone, transform)
    }

    /**
     * Returns the `Transform2D` set for a specific bone of this skeleton.
     *
     * Generated from Godot docs: RenderingServer.skeleton_bone_get_transform_2d
     */
    @JvmStatic
    fun skeletonBoneGetTransform2d(skeleton: RID, bone: Int): Transform2D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform2D(skeletonBoneGetTransform2dBind, singleton, skeleton, bone)
    }

    /**
     * Sets the base `Transform2D` to use for the specified skeleton.
     *
     * Generated from Godot docs: RenderingServer.skeleton_set_base_transform_2d
     */
    @JvmStatic
    fun skeletonSetBaseTransform2d(skeleton: RID, baseTransform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(skeletonSetBaseTransform2dBind, singleton, skeleton, baseTransform)
    }

    /**
     * Creates a directional light and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID can be used in most `light_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. To place in a scene, attach this directional light to an instance using
     * `instance_set_base` using the returned RID. Note: The equivalent node is `DirectionalLight3D`.
     *
     * Generated from Godot docs: RenderingServer.directional_light_create
     */
    @JvmStatic
    fun directionalLightCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(directionalLightCreateBind, singleton)
    }

    /**
     * Creates a new omni light and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID can be used in most `light_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. To place in a scene, attach this omni light to an instance using `instance_set_base`
     * using the returned RID. Note: The equivalent node is `OmniLight3D`.
     *
     * Generated from Godot docs: RenderingServer.omni_light_create
     */
    @JvmStatic
    fun omniLightCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(omniLightCreateBind, singleton)
    }

    /**
     * Creates a spot light and adds it to the RenderingServer. It can be accessed with the RID that is
     * returned. This RID can be used in most `light_*` RenderingServer functions. Once finished with
     * your RID, you will want to free the RID using the RenderingServer's `free_rid` method. To place
     * in a scene, attach this spot light to an instance using `instance_set_base` using the returned
     * RID.
     *
     * Generated from Godot docs: RenderingServer.spot_light_create
     */
    @JvmStatic
    fun spotLightCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(spotLightCreateBind, singleton)
    }

    /**
     * Creates a new area light and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID can be used in most `light_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. To place in a scene, attach this area light to an instance using `instance_set_base`
     * using the returned RID. Note: The equivalent node is `AreaLight3D`.
     *
     * Generated from Godot docs: RenderingServer.area_light_create
     */
    @JvmStatic
    fun areaLightCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(areaLightCreateBind, singleton)
    }

    /**
     * Sets the color of the light. Equivalent to `Light3D.light_color`.
     *
     * Generated from Godot docs: RenderingServer.light_set_color
     */
    @JvmStatic
    fun lightSetColor(light: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(lightSetColorBind, singleton, light, color)
    }

    /**
     * Sets the specified 3D light parameter. Equivalent to `Light3D.set_param`.
     *
     * Generated from Godot docs: RenderingServer.light_set_param
     */
    @JvmStatic
    fun lightSetParam(light: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(lightSetParamBind, singleton, light, param, value)
    }

    /**
     * If `true`, light will cast shadows. Equivalent to `Light3D.shadow_enabled`.
     *
     * Generated from Godot docs: RenderingServer.light_set_shadow
     */
    @JvmStatic
    fun lightSetShadow(light: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightSetShadowBind, singleton, light, enabled)
    }

    /**
     * Sets the projector texture to use for the specified 3D light. Equivalent to
     * `Light3D.light_projector`.
     *
     * Generated from Godot docs: RenderingServer.light_set_projector
     */
    @JvmStatic
    fun lightSetProjector(light: RID, texture: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(lightSetProjectorBind, singleton, light, texture)
    }

    /**
     * If `true`, the 3D light will subtract light instead of adding light. Equivalent to
     * `Light3D.light_negative`.
     *
     * Generated from Godot docs: RenderingServer.light_set_negative
     */
    @JvmStatic
    fun lightSetNegative(light: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightSetNegativeBind, singleton, light, enable)
    }

    /**
     * Sets the cull mask for this 3D light. Lights only affect objects in the selected layers.
     * Equivalent to `Light3D.light_cull_mask`.
     *
     * Generated from Godot docs: RenderingServer.light_set_cull_mask
     */
    @JvmStatic
    fun lightSetCullMask(light: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(lightSetCullMaskBind, singleton, light, mask)
    }

    /**
     * Sets the distance fade for this 3D light. This acts as a form of level of detail (LOD) and can
     * be used to improve performance. Equivalent to `Light3D.distance_fade_enabled`,
     * `Light3D.distance_fade_begin`, `Light3D.distance_fade_shadow`, and
     * `Light3D.distance_fade_length`.
     *
     * Generated from Godot docs: RenderingServer.light_set_distance_fade
     */
    @JvmStatic
    fun lightSetDistanceFade(decal: RID, enabled: Boolean, begin: Double, shadow: Double, length: Double) {
        ObjectCalls.ptrcallWithRIDBoolThreeDoubleArgs(lightSetDistanceFadeBind, singleton, decal, enabled, begin, shadow, length)
    }

    /**
     * If `true`, reverses the backface culling of the mesh. This can be useful when you have a flat
     * mesh that has a light behind it. If you need to cast a shadow on both sides of the mesh, set the
     * mesh to use double-sided shadows with `instance_geometry_set_cast_shadows_setting`. Equivalent
     * to `Light3D.shadow_reverse_cull_face`.
     *
     * Generated from Godot docs: RenderingServer.light_set_reverse_cull_face_mode
     */
    @JvmStatic
    fun lightSetReverseCullFaceMode(light: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightSetReverseCullFaceModeBind, singleton, light, enabled)
    }

    /**
     * Sets the shadow caster mask for this 3D light. Shadows will only be cast using objects in the
     * selected layers. Equivalent to `Light3D.shadow_caster_mask`.
     *
     * Generated from Godot docs: RenderingServer.light_set_shadow_caster_mask
     */
    @JvmStatic
    fun lightSetShadowCasterMask(light: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(lightSetShadowCasterMaskBind, singleton, light, mask)
    }

    /**
     * Sets the bake mode to use for the specified 3D light. Equivalent to `Light3D.light_bake_mode`.
     *
     * Generated from Godot docs: RenderingServer.light_set_bake_mode
     */
    @JvmStatic
    fun lightSetBakeMode(light: RID, bakeMode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(lightSetBakeModeBind, singleton, light, bakeMode)
    }

    /**
     * Sets the maximum SDFGI cascade in which the 3D light's indirect lighting is rendered. Higher
     * values allow the light to be rendered in SDFGI further away from the camera.
     *
     * Generated from Godot docs: RenderingServer.light_set_max_sdfgi_cascade
     */
    @JvmStatic
    fun lightSetMaxSdfgiCascade(light: RID, cascade: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(lightSetMaxSdfgiCascadeBind, singleton, light, cascade)
    }

    /**
     * Sets whether to use a dual paraboloid or a cubemap for the shadow map. Dual paraboloid is faster
     * but may suffer from artifacts. Equivalent to `OmniLight3D.omni_shadow_mode`.
     *
     * Generated from Godot docs: RenderingServer.light_omni_set_shadow_mode
     */
    @JvmStatic
    fun lightOmniSetShadowMode(light: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(lightOmniSetShadowModeBind, singleton, light, mode)
    }

    /**
     * Sets the shadow mode for this directional light. Equivalent to
     * `DirectionalLight3D.directional_shadow_mode`.
     *
     * Generated from Godot docs: RenderingServer.light_directional_set_shadow_mode
     */
    @JvmStatic
    fun lightDirectionalSetShadowMode(light: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(lightDirectionalSetShadowModeBind, singleton, light, mode)
    }

    /**
     * If `true`, this directional light will blend between shadow map splits resulting in a smoother
     * transition between them. Equivalent to `DirectionalLight3D.directional_shadow_blend_splits`.
     *
     * Generated from Godot docs: RenderingServer.light_directional_set_blend_splits
     */
    @JvmStatic
    fun lightDirectionalSetBlendSplits(light: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightDirectionalSetBlendSplitsBind, singleton, light, enable)
    }

    /**
     * If `true`, this light will not be used for anything except sky shaders. Use this for lights that
     * impact your sky shader that you may want to hide from affecting the rest of the scene. For
     * example, you may want to enable this when the sun in your sky shader falls below the horizon.
     *
     * Generated from Godot docs: RenderingServer.light_directional_set_sky_mode
     */
    @JvmStatic
    fun lightDirectionalSetSkyMode(light: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(lightDirectionalSetSkyModeBind, singleton, light, mode)
    }

    /**
     * Sets the extents (width and height) in meters for this area light. Equivalent to
     * `AreaLight3D.area_size`.
     *
     * Generated from Godot docs: RenderingServer.light_area_set_size
     */
    @JvmStatic
    fun lightAreaSetSize(light: RID, size: Vector2) {
        ObjectCalls.ptrcallWithRIDAndVector2Arg(lightAreaSetSizeBind, singleton, light, size)
    }

    /**
     * Defines whether the energy of an `AreaLight3D` is normalized (divided) by its area. If set to
     * `true`, changing the size does not affect the total energy output. Equivalent to
     * `AreaLight3D.area_normalize_energy`.
     *
     * Generated from Godot docs: RenderingServer.light_area_set_normalize_energy
     */
    @JvmStatic
    fun lightAreaSetNormalizeEnergy(light: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightAreaSetNormalizeEnergyBind, singleton, light, enable)
    }

    /**
     * Sets the texture filter mode to use when rendering light projectors. This parameter is global
     * and cannot be set on a per-light basis.
     *
     * Generated from Godot docs: RenderingServer.light_projectors_set_filter
     */
    @JvmStatic
    fun lightProjectorsSetFilter(filter: Long) {
        ObjectCalls.ptrcallWithLongArg(lightProjectorsSetFilterBind, singleton, filter)
    }

    /**
     * Toggles whether a bicubic filter should be used when lightmaps are sampled. This smoothens their
     * appearance at a performance cost.
     *
     * Generated from Godot docs: RenderingServer.lightmaps_set_bicubic_filter
     */
    @JvmStatic
    fun lightmapsSetBicubicFilter(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(lightmapsSetBicubicFilterBind, singleton, enable)
    }

    /**
     * Sets the filter quality for omni and spot light shadows in 3D. See also
     * `ProjectSettings.rendering/lights_and_shadows/positional_shadow/soft_shadow_filter_quality`.
     * This parameter is global and cannot be set on a per-viewport basis.
     *
     * Generated from Godot docs: RenderingServer.positional_soft_shadow_filter_set_quality
     */
    @JvmStatic
    fun positionalSoftShadowFilterSetQuality(quality: Long) {
        ObjectCalls.ptrcallWithLongArg(positionalSoftShadowFilterSetQualityBind, singleton, quality)
    }

    /**
     * Sets the filter `quality` for directional light shadows in 3D. See also
     * `ProjectSettings.rendering/lights_and_shadows/directional_shadow/soft_shadow_filter_quality`.
     * This parameter is global and cannot be set on a per-viewport basis.
     *
     * Generated from Godot docs: RenderingServer.directional_soft_shadow_filter_set_quality
     */
    @JvmStatic
    fun directionalSoftShadowFilterSetQuality(quality: Long) {
        ObjectCalls.ptrcallWithLongArg(directionalSoftShadowFilterSetQualityBind, singleton, quality)
    }

    /**
     * Sets the `size` of the directional light shadows in 3D. See also
     * `ProjectSettings.rendering/lights_and_shadows/directional_shadow/size`. This parameter is global
     * and cannot be set on a per-viewport basis.
     *
     * Generated from Godot docs: RenderingServer.directional_shadow_atlas_set_size
     */
    @JvmStatic
    fun directionalShadowAtlasSetSize(size: Int, is16bits: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(directionalShadowAtlasSetSizeBind, singleton, size, is16bits)
    }

    /**
     * Creates a reflection probe and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID will be used in all `reflection_probe_*` RenderingServer functions.
     * Once finished with your RID, you will want to free the RID using the RenderingServer's
     * `free_rid` method. To place in a scene, attach this reflection probe to an instance using
     * `instance_set_base` using the returned RID. Note: The equivalent node is `ReflectionProbe`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_create
     */
    @JvmStatic
    fun reflectionProbeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(reflectionProbeCreateBind, singleton)
    }

    /**
     * Sets how often the reflection probe updates. Can either be once or every frame.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_update_mode
     */
    @JvmStatic
    fun reflectionProbeSetUpdateMode(probe: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(reflectionProbeSetUpdateModeBind, singleton, probe, mode)
    }

    /**
     * Sets the intensity of the reflection probe. Intensity modulates the strength of the reflection.
     * Equivalent to `ReflectionProbe.intensity`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_intensity
     */
    @JvmStatic
    fun reflectionProbeSetIntensity(probe: RID, intensity: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(reflectionProbeSetIntensityBind, singleton, probe, intensity)
    }

    /**
     * Sets the distance in meters over which a probe blends into the scene.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_blend_distance
     */
    @JvmStatic
    fun reflectionProbeSetBlendDistance(probe: RID, blendDistance: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(reflectionProbeSetBlendDistanceBind, singleton, probe, blendDistance)
    }

    /**
     * Sets the reflection probe's ambient light mode. Equivalent to `ReflectionProbe.ambient_mode`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_ambient_mode
     */
    @JvmStatic
    fun reflectionProbeSetAmbientMode(probe: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(reflectionProbeSetAmbientModeBind, singleton, probe, mode)
    }

    /**
     * Sets the reflection probe's custom ambient light color. Equivalent to
     * `ReflectionProbe.ambient_color`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_ambient_color
     */
    @JvmStatic
    fun reflectionProbeSetAmbientColor(probe: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(reflectionProbeSetAmbientColorBind, singleton, probe, color)
    }

    /**
     * Sets the reflection probe's custom ambient light energy. Equivalent to
     * `ReflectionProbe.ambient_color_energy`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_ambient_energy
     */
    @JvmStatic
    fun reflectionProbeSetAmbientEnergy(probe: RID, energy: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(reflectionProbeSetAmbientEnergyBind, singleton, probe, energy)
    }

    /**
     * Sets the max distance away from the probe an object can be before it is culled. Equivalent to
     * `ReflectionProbe.max_distance`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_max_distance
     */
    @JvmStatic
    fun reflectionProbeSetMaxDistance(probe: RID, distance: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(reflectionProbeSetMaxDistanceBind, singleton, probe, distance)
    }

    /**
     * Sets the size of the area that the reflection probe will capture. Equivalent to
     * `ReflectionProbe.size`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_size
     */
    @JvmStatic
    fun reflectionProbeSetSize(probe: RID, size: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(reflectionProbeSetSizeBind, singleton, probe, size)
    }

    /**
     * Sets the origin offset to be used when this reflection probe is in box project mode. Equivalent
     * to `ReflectionProbe.origin_offset`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_origin_offset
     */
    @JvmStatic
    fun reflectionProbeSetOriginOffset(probe: RID, offset: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(reflectionProbeSetOriginOffsetBind, singleton, probe, offset)
    }

    /**
     * If `true`, reflections will ignore sky contribution. Equivalent to `ReflectionProbe.interior`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_as_interior
     */
    @JvmStatic
    fun reflectionProbeSetAsInterior(probe: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(reflectionProbeSetAsInteriorBind, singleton, probe, enable)
    }

    /**
     * If `true`, uses box projection. This can make reflections look more correct in certain
     * situations. Equivalent to `ReflectionProbe.box_projection`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_enable_box_projection
     */
    @JvmStatic
    fun reflectionProbeSetEnableBoxProjection(probe: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(reflectionProbeSetEnableBoxProjectionBind, singleton, probe, enable)
    }

    /**
     * If `true`, computes shadows in the reflection probe. This makes the reflection much slower to
     * compute. Equivalent to `ReflectionProbe.enable_shadows`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_enable_shadows
     */
    @JvmStatic
    fun reflectionProbeSetEnableShadows(probe: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(reflectionProbeSetEnableShadowsBind, singleton, probe, enable)
    }

    /**
     * Sets the render cull mask for this reflection probe. Only instances with a matching layer will
     * be reflected by this probe. Equivalent to `ReflectionProbe.cull_mask`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_cull_mask
     */
    @JvmStatic
    fun reflectionProbeSetCullMask(probe: RID, layers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(reflectionProbeSetCullMaskBind, singleton, probe, layers)
    }

    /**
     * Sets the render reflection mask for this reflection probe. Only instances with a matching layer
     * will have reflections applied from this probe. Equivalent to `ReflectionProbe.reflection_mask`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_reflection_mask
     */
    @JvmStatic
    fun reflectionProbeSetReflectionMask(probe: RID, layers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(reflectionProbeSetReflectionMaskBind, singleton, probe, layers)
    }

    /**
     * Deprecated. This method does nothing.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_resolution
     */
    @JvmStatic
    fun reflectionProbeSetResolution(probe: RID, resolution: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(reflectionProbeSetResolutionBind, singleton, probe, resolution)
    }

    /**
     * Sets the mesh level of detail to use in the reflection probe rendering. Higher values will use
     * less detailed versions of meshes that have LOD variations generated, which can improve
     * performance. Equivalent to `ReflectionProbe.mesh_lod_threshold`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_mesh_lod_threshold
     */
    @JvmStatic
    fun reflectionProbeSetMeshLodThreshold(probe: RID, pixels: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(reflectionProbeSetMeshLodThresholdBind, singleton, probe, pixels)
    }

    /**
     * Creates a decal and adds it to the RenderingServer. It can be accessed with the RID that is
     * returned. This RID will be used in all `decal_*` RenderingServer functions. Once finished with
     * your RID, you will want to free the RID using the RenderingServer's `free_rid` method. To place
     * in a scene, attach this decal to an instance using `instance_set_base` using the returned RID.
     * Note: The equivalent node is `Decal`.
     *
     * Generated from Godot docs: RenderingServer.decal_create
     */
    @JvmStatic
    fun decalCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(decalCreateBind, singleton)
    }

    /**
     * Sets the `size` of the decal specified by the `decal` RID. Equivalent to `Decal.size`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_size
     */
    @JvmStatic
    fun decalSetSize(decal: RID, size: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(decalSetSizeBind, singleton, decal, size)
    }

    /**
     * Sets the `texture` in the given texture `type` slot for the specified decal. Equivalent to
     * `Decal.set_texture`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_texture
     */
    @JvmStatic
    fun decalSetTexture(decal: RID, type: Long, texture: RID) {
        ObjectCalls.ptrcallWithRIDLongAndRIDArgs(decalSetTextureBind, singleton, decal, type, texture)
    }

    /**
     * Sets the emission `energy` in the decal specified by the `decal` RID. Equivalent to
     * `Decal.emission_energy`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_emission_energy
     */
    @JvmStatic
    fun decalSetEmissionEnergy(decal: RID, energy: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(decalSetEmissionEnergyBind, singleton, decal, energy)
    }

    /**
     * Sets the `albedo_mix` in the decal specified by the `decal` RID. Equivalent to
     * `Decal.albedo_mix`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_albedo_mix
     */
    @JvmStatic
    fun decalSetAlbedoMix(decal: RID, albedoMix: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(decalSetAlbedoMixBind, singleton, decal, albedoMix)
    }

    /**
     * Sets the color multiplier in the decal specified by the `decal` RID to `color`. Equivalent to
     * `Decal.modulate`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_modulate
     */
    @JvmStatic
    fun decalSetModulate(decal: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(decalSetModulateBind, singleton, decal, color)
    }

    /**
     * Sets the cull `mask` in the decal specified by the `decal` RID. Equivalent to `Decal.cull_mask`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_cull_mask
     */
    @JvmStatic
    fun decalSetCullMask(decal: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(decalSetCullMaskBind, singleton, decal, mask)
    }

    /**
     * Sets the distance fade parameters in the decal specified by the `decal` RID. Equivalent to
     * `Decal.distance_fade_enabled`, `Decal.distance_fade_begin` and `Decal.distance_fade_length`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_distance_fade
     */
    @JvmStatic
    fun decalSetDistanceFade(decal: RID, enabled: Boolean, begin: Double, length: Double) {
        ObjectCalls.ptrcallWithRIDBoolTwoDoubleArgs(decalSetDistanceFadeBind, singleton, decal, enabled, begin, length)
    }

    /**
     * Sets the upper fade (`above`) and lower fade (`below`) in the decal specified by the `decal`
     * RID. Equivalent to `Decal.upper_fade` and `Decal.lower_fade`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_fade
     */
    @JvmStatic
    fun decalSetFade(decal: RID, above: Double, below: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(decalSetFadeBind, singleton, decal, above, below)
    }

    /**
     * Sets the normal `fade` in the decal specified by the `decal` RID. Equivalent to
     * `Decal.normal_fade`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_normal_fade
     */
    @JvmStatic
    fun decalSetNormalFade(decal: RID, fade: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(decalSetNormalFadeBind, singleton, decal, fade)
    }

    /**
     * Sets the texture `filter` mode to use when rendering decals. This parameter is global and cannot
     * be set on a per-decal basis.
     *
     * Generated from Godot docs: RenderingServer.decals_set_filter
     */
    @JvmStatic
    fun decalsSetFilter(filter: Long) {
        ObjectCalls.ptrcallWithLongArg(decalsSetFilterBind, singleton, filter)
    }

    /**
     * If `half_resolution` is `true`, renders `VoxelGI` and SDFGI (`Environment.sdfgi_enabled`)
     * buffers at halved resolution on each axis (e.g. 960×540 when the viewport size is 1920×1080).
     * This improves performance significantly when VoxelGI or SDFGI is enabled, at the cost of
     * artifacts that may be visible on polygon edges. The loss in quality becomes less noticeable as
     * the viewport resolution increases. `LightmapGI` rendering is not affected by this setting.
     * Equivalent to `ProjectSettings.rendering/global_illumination/gi/use_half_resolution`.
     *
     * Generated from Godot docs: RenderingServer.gi_set_use_half_resolution
     */
    @JvmStatic
    fun giSetUseHalfResolution(halfResolution: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(giSetUseHalfResolutionBind, singleton, halfResolution)
    }

    /**
     * Creates a new voxel-based global illumination object and adds it to the RenderingServer. It can
     * be accessed with the RID that is returned. This RID will be used in all `voxel_gi_*`
     * RenderingServer functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. Note: The equivalent node is `VoxelGI`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_create
     */
    @JvmStatic
    fun voxelGiCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(voxelGiCreateBind, singleton)
    }

    /**
     * Allocates and initializes the voxel GI data for the specified `voxel_gi` RID. `octree_cells`
     * must be a multiple of 32. `octree_cells` must be double the size of `data_cells`. The allocated
     * data can be retrieved later using the various `voxel_gi_get_*` methods.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_allocate_data
     */
    @JvmStatic
    fun voxelGiAllocateData(voxelGi: RID, toCellXform: Transform3D, aabb: AABB, octreeSize: Vector3i, octreeCells: ByteArray, dataCells: ByteArray, distanceField: ByteArray, levelCounts: List<Int>) {
        ObjectCalls.ptrcallWithRIDTransform3DAABBVector3iThreeByteArrayPackedInt32ListArgs(voxelGiAllocateDataBind, singleton, voxelGi, toCellXform, aabb, octreeSize, octreeCells, dataCells, distanceField, levelCounts)
    }

    /**
     * Returns the octree size for the specified voxel GI data instance, which corresponds to the
     * number of subdivisions per axis. This can be viewed in the editor by hovering the Bake VoxelGI
     * button at the top of the 3D editor viewport when a `VoxelGI` node is selected and looking at the
     * Subdivisions field in the tooltip.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_get_octree_size
     */
    @JvmStatic
    fun voxelGiGetOctreeSize(voxelGi: RID): Vector3i {
        return ObjectCalls.ptrcallWithRIDArgRetVector3i(voxelGiGetOctreeSizeBind, singleton, voxelGi)
    }

    /**
     * Returns the octree cell data for the specified voxel GI data instance. See also
     * `voxel_gi_allocate_data`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_get_octree_cells
     */
    @JvmStatic
    fun voxelGiGetOctreeCells(voxelGi: RID): ByteArray {
        return ObjectCalls.ptrcallWithRIDArgRetByteArray(voxelGiGetOctreeCellsBind, singleton, voxelGi)
    }

    /**
     * Returns the data cells for the specified voxel GI data instance. See also
     * `voxel_gi_allocate_data`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_get_data_cells
     */
    @JvmStatic
    fun voxelGiGetDataCells(voxelGi: RID): ByteArray {
        return ObjectCalls.ptrcallWithRIDArgRetByteArray(voxelGiGetDataCellsBind, singleton, voxelGi)
    }

    /**
     * Returns the distance field data for the specified voxel GI data instance. See also
     * `voxel_gi_allocate_data`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_get_distance_field
     */
    @JvmStatic
    fun voxelGiGetDistanceField(voxelGi: RID): ByteArray {
        return ObjectCalls.ptrcallWithRIDArgRetByteArray(voxelGiGetDistanceFieldBind, singleton, voxelGi)
    }

    /**
     * Returns the level counts for the specified voxel GI data instance. See also
     * `voxel_gi_allocate_data`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_get_level_counts
     */
    @JvmStatic
    fun voxelGiGetLevelCounts(voxelGi: RID): List<Int> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedInt32List(voxelGiGetLevelCountsBind, singleton, voxelGi)
    }

    /**
     * Returns the transform to cell space for the specified voxel GI data instance. See also
     * `voxel_gi_allocate_data`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_get_to_cell_xform
     */
    @JvmStatic
    fun voxelGiGetToCellXform(voxelGi: RID): Transform3D {
        return ObjectCalls.ptrcallWithRIDArgRetTransform3D(voxelGiGetToCellXformBind, singleton, voxelGi)
    }

    /**
     * Sets the `VoxelGIData.dynamic_range` value to use on the specified `voxel_gi`'s `RID`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_dynamic_range
     */
    @JvmStatic
    fun voxelGiSetDynamicRange(voxelGi: RID, range: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetDynamicRangeBind, singleton, voxelGi, range)
    }

    /**
     * Sets the `VoxelGIData.propagation` value to use on the specified `voxel_gi`'s `RID`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_propagation
     */
    @JvmStatic
    fun voxelGiSetPropagation(voxelGi: RID, amount: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetPropagationBind, singleton, voxelGi, amount)
    }

    /**
     * Sets the `VoxelGIData.energy` value to use on the specified `voxel_gi`'s `RID`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_energy
     */
    @JvmStatic
    fun voxelGiSetEnergy(voxelGi: RID, energy: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetEnergyBind, singleton, voxelGi, energy)
    }

    /**
     * Used to inform the renderer what exposure normalization value was used while baking the voxel
     * gi. This value will be used and modulated at run time to ensure that the voxel gi maintains a
     * consistent level of exposure even if the scene-wide exposure normalization is changed at run
     * time. For more information see `camera_attributes_set_exposure`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_baked_exposure_normalization
     */
    @JvmStatic
    fun voxelGiSetBakedExposureNormalization(voxelGi: RID, bakedExposure: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetBakedExposureNormalizationBind, singleton, voxelGi, bakedExposure)
    }

    /**
     * Sets the `VoxelGIData.bias` value to use on the specified `voxel_gi`'s `RID`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_bias
     */
    @JvmStatic
    fun voxelGiSetBias(voxelGi: RID, bias: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetBiasBind, singleton, voxelGi, bias)
    }

    /**
     * Sets the `VoxelGIData.normal_bias` value to use on the specified `voxel_gi`'s `RID`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_normal_bias
     */
    @JvmStatic
    fun voxelGiSetNormalBias(voxelGi: RID, bias: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetNormalBiasBind, singleton, voxelGi, bias)
    }

    /**
     * Sets the `VoxelGIData.interior` value to use on the specified `voxel_gi`'s `RID`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_interior
     */
    @JvmStatic
    fun voxelGiSetInterior(voxelGi: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(voxelGiSetInteriorBind, singleton, voxelGi, enable)
    }

    /**
     * Sets the `VoxelGIData.use_two_bounces` value to use on the specified `voxel_gi`'s `RID`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_use_two_bounces
     */
    @JvmStatic
    fun voxelGiSetUseTwoBounces(voxelGi: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(voxelGiSetUseTwoBouncesBind, singleton, voxelGi, enable)
    }

    /**
     * Sets the `ProjectSettings.rendering/global_illumination/voxel_gi/quality` value to use when
     * rendering. This parameter is global and cannot be set on a per-VoxelGI basis.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_quality
     */
    @JvmStatic
    fun voxelGiSetQuality(quality: Long) {
        ObjectCalls.ptrcallWithLongArg(voxelGiSetQualityBind, singleton, quality)
    }

    /**
     * Creates a new lightmap global illumination instance and adds it to the RenderingServer. It can
     * be accessed with the RID that is returned. This RID will be used in all `lightmap_*`
     * RenderingServer functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. Note: The equivalent node is `LightmapGI`.
     *
     * Generated from Godot docs: RenderingServer.lightmap_create
     */
    @JvmStatic
    fun lightmapCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(lightmapCreateBind, singleton)
    }

    /**
     * Set the textures on the given `lightmap` GI instance to the texture array pointed to by the
     * `light` RID. If the lightmap texture was baked with `LightmapGI.directional` set to `true`, then
     * `uses_sh` must also be `true`.
     *
     * Generated from Godot docs: RenderingServer.lightmap_set_textures
     */
    @JvmStatic
    fun lightmapSetTextures(lightmap: RID, light: RID, usesSh: Boolean) {
        ObjectCalls.ptrcallWithTwoRIDBoolArgs(lightmapSetTexturesBind, singleton, lightmap, light, usesSh)
    }

    /**
     * Sets the bounds that this lightmap instance should visually affect, both in terms of static
     * lightmap baking and probe-based global illumination.
     *
     * Generated from Godot docs: RenderingServer.lightmap_set_probe_bounds
     */
    @JvmStatic
    fun lightmapSetProbeBounds(lightmap: RID, bounds: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(lightmapSetProbeBoundsBind, singleton, lightmap, bounds)
    }

    /**
     * Sets whether the lightmap instance should be considered as interior (when `interior` is `true`).
     * If the lightmap is marked as interior, environment lighting is ignored when baking lightmaps.
     *
     * Generated from Godot docs: RenderingServer.lightmap_set_probe_interior
     */
    @JvmStatic
    fun lightmapSetProbeInterior(lightmap: RID, interior: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightmapSetProbeInteriorBind, singleton, lightmap, interior)
    }

    /**
     * Sets the probe capture data for the given lightmap instance. See
     * `lightmap_get_probe_capture_points`, `lightmap_get_probe_capture_sh`,
     * `lightmap_get_probe_capture_tetrahedra`, and `lightmap_get_probe_capture_bsp_tree` for the
     * expected data formats.
     *
     * Generated from Godot docs: RenderingServer.lightmap_set_probe_capture_data
     */
    @JvmStatic
    fun lightmapSetProbeCaptureData(lightmap: RID, points: List<Vector3>, pointSh: List<Color>, tetrahedra: List<Int>, bspTree: List<Int>) {
        ObjectCalls.ptrcallWithRIDPackedVector3ListPackedColorListTwoPackedInt32ListArgs(lightmapSetProbeCaptureDataBind, singleton, lightmap, points, pointSh, tetrahedra, bspTree)
    }

    /**
     * Returns the local space positions of each lightmap probe capture point. Keep in mind the
     * lightmap instance may have a non-zero transform, which will affect the position of the probe
     * capture points. See also `lightmap_set_probe_capture_data`.
     *
     * Generated from Godot docs: RenderingServer.lightmap_get_probe_capture_points
     */
    @JvmStatic
    fun lightmapGetProbeCapturePoints(lightmap: RID): List<Vector3> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedVector3List(lightmapGetProbeCapturePointsBind, singleton, lightmap)
    }

    /**
     * Returns the L0, L1, and L2 spherical harmonics
     * (https://en.wikipedia.org/wiki/Spherical_harmonics) data for each lightmap probe capture point.
     * This is specified as 9 `Color` values per probe, which means the size of the returned data is
     * always 9 times the number of probe points. See also `lightmap_set_probe_capture_data`.
     *
     * Generated from Godot docs: RenderingServer.lightmap_get_probe_capture_sh
     */
    @JvmStatic
    fun lightmapGetProbeCaptureSh(lightmap: RID): List<Color> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedColorList(lightmapGetProbeCaptureShBind, singleton, lightmap)
    }

    /**
     * Returns the tetrahedralization data used for interpolating between lightmap probe capture
     * points. Each tetrahedron is specified as a series of 4 numbers, each being an index into the
     * probe capture points array returned by `lightmap_get_probe_capture_points`. See also
     * `lightmap_set_probe_capture_data`.
     *
     * Generated from Godot docs: RenderingServer.lightmap_get_probe_capture_tetrahedra
     */
    @JvmStatic
    fun lightmapGetProbeCaptureTetrahedra(lightmap: RID): List<Int> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedInt32List(lightmapGetProbeCaptureTetrahedraBind, singleton, lightmap)
    }

    /**
     * Returns the BSP tree data used for accelerating probe lookups. The BSP data is structured as a
     * series of six signed 32-bit values per BSP node in this order: `float plane_x`, `float plane_y`,
     * `float plane_z`, `float plane_distance`, `int32_t over`, `int32_t under`. An empty leaf is
     * denoted by the value `-2147483648` (the minimum 32-bit signed integer). See also
     * `lightmap_set_probe_capture_data`.
     *
     * Generated from Godot docs: RenderingServer.lightmap_get_probe_capture_bsp_tree
     */
    @JvmStatic
    fun lightmapGetProbeCaptureBspTree(lightmap: RID): List<Int> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedInt32List(lightmapGetProbeCaptureBspTreeBind, singleton, lightmap)
    }

    /**
     * Used to inform the renderer what exposure normalization value was used while baking the
     * lightmap. This value will be used and modulated at run time to ensure that the lightmap
     * maintains a consistent level of exposure even if the scene-wide exposure normalization is
     * changed at run time. For more information see `camera_attributes_set_exposure`.
     *
     * Generated from Godot docs: RenderingServer.lightmap_set_baked_exposure_normalization
     */
    @JvmStatic
    fun lightmapSetBakedExposureNormalization(lightmap: RID, bakedExposure: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(lightmapSetBakedExposureNormalizationBind, singleton, lightmap, bakedExposure)
    }

    /**
     * The framerate-independent update speed when representing dynamic object lighting from
     * `LightmapProbe`s. Higher values make dynamic object lighting update faster. Higher values can
     * prevent fast-moving objects from having "outdated" indirect lighting displayed on them, at the
     * cost of possible flickering when an object moves from a bright area to a shaded area. See also
     * `ProjectSettings.rendering/lightmapping/probe_capture/update_speed`.
     *
     * Generated from Godot docs: RenderingServer.lightmap_set_probe_capture_update_speed
     */
    @JvmStatic
    fun lightmapSetProbeCaptureUpdateSpeed(speed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(lightmapSetProbeCaptureUpdateSpeedBind, singleton, speed)
    }

    /**
     * Creates a GPU-based particle system and adds it to the RenderingServer. It can be accessed with
     * the RID that is returned. This RID will be used in all `particles_*` RenderingServer functions.
     * Once finished with your RID, you will want to free the RID using the RenderingServer's
     * `free_rid` method. To place in a scene, attach these particles to an instance using
     * `instance_set_base` using the returned RID. Note: The equivalent nodes are `GPUParticles2D` and
     * `GPUParticles3D`. Note: All `particles_*` methods only apply to GPU-based particles, not
     * CPU-based particles. `CPUParticles2D` and `CPUParticles3D` do not have equivalent
     * RenderingServer functions available, as these use `MultiMeshInstance2D` and
     * `MultiMeshInstance3D` under the hood (see `multimesh_*` methods).
     *
     * Generated from Godot docs: RenderingServer.particles_create
     */
    @JvmStatic
    fun particlesCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(particlesCreateBind, singleton)
    }

    /**
     * Sets whether the GPU particles specified by the `particles` RID should be rendered in 2D or 3D
     * according to `mode`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_mode
     */
    @JvmStatic
    fun particlesSetMode(particles: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesSetModeBind, singleton, particles, mode)
    }

    /**
     * If `true`, particles will emit over time. Setting to `false` does not reset the particles, but
     * only stops their emission. Equivalent to `GPUParticles3D.emitting`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_emitting
     */
    @JvmStatic
    fun particlesSetEmitting(particles: RID, emitting: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(particlesSetEmittingBind, singleton, particles, emitting)
    }

    /**
     * Returns `true` if particles are currently set to emitting.
     *
     * Generated from Godot docs: RenderingServer.particles_get_emitting
     */
    @JvmStatic
    fun particlesGetEmitting(particles: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(particlesGetEmittingBind, singleton, particles)
    }

    /**
     * Sets the number of particles to be drawn and allocates the memory for them. Equivalent to
     * `GPUParticles3D.amount`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_amount
     */
    @JvmStatic
    fun particlesSetAmount(particles: RID, amount: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(particlesSetAmountBind, singleton, particles, amount)
    }

    /**
     * Sets the amount ratio for particles to be emitted. Equivalent to `GPUParticles3D.amount_ratio`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_amount_ratio
     */
    @JvmStatic
    fun particlesSetAmountRatio(particles: RID, ratio: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetAmountRatioBind, singleton, particles, ratio)
    }

    /**
     * Sets the lifetime of each particle in the system. Equivalent to `GPUParticles3D.lifetime`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_lifetime
     */
    @JvmStatic
    fun particlesSetLifetime(particles: RID, lifetime: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetLifetimeBind, singleton, particles, lifetime)
    }

    /**
     * If `true`, particles will emit once and then stop. Equivalent to `GPUParticles3D.one_shot`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_one_shot
     */
    @JvmStatic
    fun particlesSetOneShot(particles: RID, oneShot: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(particlesSetOneShotBind, singleton, particles, oneShot)
    }

    /**
     * Sets the preprocess time for the particles' animation. This lets you delay starting an animation
     * until after the particles have begun emitting. Equivalent to `GPUParticles3D.preprocess`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_pre_process_time
     */
    @JvmStatic
    fun particlesSetPreProcessTime(particles: RID, time: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetPreProcessTimeBind, singleton, particles, time)
    }

    /**
     * Requests the particles to process for extra process time during a single frame. `process_time`
     * defines the time that the particles will process while emitting is on. `process_time_residual`
     * defines the time that particles will process with emitting turned off for the simulation. When
     * combined with the particles' speed scale set to `0.0`, this is useful to be able to seek a
     * particle system timeline.
     *
     * Generated from Godot docs: RenderingServer.particles_request_process_time
     */
    @JvmStatic
    fun particlesRequestProcessTime(particles: RID, processTime: Double, processTimeResidual: Double = 0.0) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(particlesRequestProcessTimeBind, singleton, particles, processTime, processTimeResidual)
    }

    /**
     * Sets the explosiveness ratio. Equivalent to `GPUParticles3D.explosiveness`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_explosiveness_ratio
     */
    @JvmStatic
    fun particlesSetExplosivenessRatio(particles: RID, ratio: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetExplosivenessRatioBind, singleton, particles, ratio)
    }

    /**
     * Sets the emission randomness ratio. This randomizes the emission of particles within their
     * phase. Equivalent to `GPUParticles3D.randomness`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_randomness_ratio
     */
    @JvmStatic
    fun particlesSetRandomnessRatio(particles: RID, ratio: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetRandomnessRatioBind, singleton, particles, ratio)
    }

    /**
     * Sets the value that informs a `ParticleProcessMaterial` to rush all particles towards the end of
     * their lifetime.
     *
     * Generated from Godot docs: RenderingServer.particles_set_interp_to_end
     */
    @JvmStatic
    fun particlesSetInterpToEnd(particles: RID, factor: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetInterpToEndBind, singleton, particles, factor)
    }

    /**
     * Sets the velocity of a particle node, that will be used by
     * `ParticleProcessMaterial.inherit_velocity_ratio`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_emitter_velocity
     */
    @JvmStatic
    fun particlesSetEmitterVelocity(particles: RID, velocity: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(particlesSetEmitterVelocityBind, singleton, particles, velocity)
    }

    /**
     * Sets a custom axis-aligned bounding box for the particle system. Equivalent to
     * `GPUParticles3D.visibility_aabb`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_custom_aabb
     */
    @JvmStatic
    fun particlesSetCustomAabb(particles: RID, aabb: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(particlesSetCustomAabbBind, singleton, particles, aabb)
    }

    /**
     * Sets the speed scale of the particle system. Equivalent to `GPUParticles3D.speed_scale`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_speed_scale
     */
    @JvmStatic
    fun particlesSetSpeedScale(particles: RID, scale: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetSpeedScaleBind, singleton, particles, scale)
    }

    /**
     * If `true`, particles use local coordinates. If `false` they use global coordinates. Equivalent
     * to `GPUParticles3D.local_coords`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_use_local_coordinates
     */
    @JvmStatic
    fun particlesSetUseLocalCoordinates(particles: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(particlesSetUseLocalCoordinatesBind, singleton, particles, enable)
    }

    /**
     * Sets the material for processing the particles. Note: This is not the material used to draw the
     * materials. Equivalent to `GPUParticles3D.process_material`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_process_material
     */
    @JvmStatic
    fun particlesSetProcessMaterial(particles: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(particlesSetProcessMaterialBind, singleton, particles, material)
    }

    /**
     * Sets the frame rate that the particle system rendering will be fixed to. Equivalent to
     * `GPUParticles3D.fixed_fps`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_fixed_fps
     */
    @JvmStatic
    fun particlesSetFixedFps(particles: RID, fps: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(particlesSetFixedFpsBind, singleton, particles, fps)
    }

    /**
     * Sets whether particles should use interpolation between fixed steps. Equivalent to
     * `GPUParticles3D.interpolate`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_interpolate
     */
    @JvmStatic
    fun particlesSetInterpolate(particles: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(particlesSetInterpolateBind, singleton, particles, enable)
    }

    /**
     * If `true`, uses fractional delta which smooths the movement of the particles. Equivalent to
     * `GPUParticles3D.fract_delta`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_fractional_delta
     */
    @JvmStatic
    fun particlesSetFractionalDelta(particles: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(particlesSetFractionalDeltaBind, singleton, particles, enable)
    }

    /**
     * Sets the base size for particle collision. Equivalent to `GPUParticles3D.collision_base_size`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_collision_base_size
     */
    @JvmStatic
    fun particlesSetCollisionBaseSize(particles: RID, size: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetCollisionBaseSizeBind, singleton, particles, size)
    }

    /**
     * Sets the transform alignment for the particle system. Equivalent to
     * `GPUParticles3D.transform_align`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_transform_align
     */
    @JvmStatic
    fun particlesSetTransformAlign(particles: RID, align: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesSetTransformAlignBind, singleton, particles, align)
    }

    /**
     * When using Z-Billboarding, which CUSTOM channel to read from.
     *
     * Generated from Godot docs: RenderingServer.particles_set_transform_align_channel_filter
     */
    @JvmStatic
    fun particlesSetTransformAlignChannelFilter(particles: RID, channelFilter: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesSetTransformAlignChannelFilterBind, singleton, particles, channelFilter)
    }

    /**
     * Sets which axis to use for transform alignment.
     *
     * Generated from Godot docs: RenderingServer.particles_set_transform_align_axis
     */
    @JvmStatic
    fun particlesSetTransformAlignAxis(particles: RID, rotationAxis: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesSetTransformAlignAxisBind, singleton, particles, rotationAxis)
    }

    /**
     * If `enable` is `true`, enables trails for the `particles` with the specified `length_sec` in
     * seconds. Equivalent to `GPUParticles3D.trail_enabled` and `GPUParticles3D.trail_lifetime`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_trails
     */
    @JvmStatic
    fun particlesSetTrails(particles: RID, enable: Boolean, lengthSec: Double) {
        ObjectCalls.ptrcallWithRIDBoolDoubleArgs(particlesSetTrailsBind, singleton, particles, enable, lengthSec)
    }

    /**
     * Sets the trail bind poses for the particle system. This specified as an array of `Transform3D`s
     * representing the bind pose for each draw pass. See `GPUParticles3D.draw_skin`,
     * `Skin.get_bind_count`, and `Skin.get_bind_pose`. Set the value for each draw pass to
     * `Transform3D.IDENTITY` to use the default behavior, which is what built-in trails use
     * (`RibbonTrailMesh` and `TubeTrailMesh`).
     *
     * Generated from Godot docs: RenderingServer.particles_set_trail_bind_poses
     */
    @JvmStatic
    fun particlesSetTrailBindPoses(particles: RID, bindPoses: List<Transform3D>) {
        ObjectCalls.ptrcallWithRIDAndTransform3DListArgs(particlesSetTrailBindPosesBind, singleton, particles, bindPoses)
    }

    /**
     * Returns `true` if particles are not emitting and particles are set to inactive.
     *
     * Generated from Godot docs: RenderingServer.particles_is_inactive
     */
    @JvmStatic
    fun particlesIsInactive(particles: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(particlesIsInactiveBind, singleton, particles)
    }

    /**
     * Add particle system to list of particle systems that need to be updated. Update will take place
     * on the next frame, or on the next call to `instances_cull_aabb`, `instances_cull_convex`, or
     * `instances_cull_ray`.
     *
     * Generated from Godot docs: RenderingServer.particles_request_process
     */
    @JvmStatic
    fun particlesRequestProcess(particles: RID) {
        ObjectCalls.ptrcallWithRIDArg(particlesRequestProcessBind, singleton, particles)
    }

    /**
     * Reset the particles on the next update. Equivalent to `GPUParticles3D.restart`.
     *
     * Generated from Godot docs: RenderingServer.particles_restart
     */
    @JvmStatic
    fun particlesRestart(particles: RID) {
        ObjectCalls.ptrcallWithRIDArg(particlesRestartBind, singleton, particles)
    }

    /**
     * Sets the subemitter particles for the particle system. Equivalent to
     * `GPUParticles3D.sub_emitter`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_subemitter
     */
    @JvmStatic
    fun particlesSetSubemitter(particles: RID, subemitterParticles: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(particlesSetSubemitterBind, singleton, particles, subemitterParticles)
    }

    /**
     * Manually emits particles from the `particles` instance.
     *
     * Generated from Godot docs: RenderingServer.particles_emit
     */
    @JvmStatic
    fun particlesEmit(particles: RID, transform: Transform3D, velocity: Vector3, color: Color, custom: Color, emitFlags: Long) {
        ObjectCalls.ptrcallWithRIDTransform3DVector3TwoColorUInt32Args(particlesEmitBind, singleton, particles, transform, velocity, color, custom, emitFlags)
    }

    /**
     * Sets the draw order of the particles. Equivalent to `GPUParticles3D.draw_order`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_draw_order
     */
    @JvmStatic
    fun particlesSetDrawOrder(particles: RID, order: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesSetDrawOrderBind, singleton, particles, order)
    }

    /**
     * Sets the number of draw passes to use. Equivalent to `GPUParticles3D.draw_passes`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_draw_passes
     */
    @JvmStatic
    fun particlesSetDrawPasses(particles: RID, count: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(particlesSetDrawPassesBind, singleton, particles, count)
    }

    /**
     * Sets the mesh to be used for the specified draw pass. Equivalent to
     * `GPUParticles3D.draw_pass_1`, `GPUParticles3D.draw_pass_2`, `GPUParticles3D.draw_pass_3`, and
     * `GPUParticles3D.draw_pass_4`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_draw_pass_mesh
     */
    @JvmStatic
    fun particlesSetDrawPassMesh(particles: RID, pass: Int, mesh: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(particlesSetDrawPassMeshBind, singleton, particles, pass, mesh)
    }

    /**
     * Calculates and returns the axis-aligned bounding box that contains all the particles. Equivalent
     * to `GPUParticles3D.capture_aabb`.
     *
     * Generated from Godot docs: RenderingServer.particles_get_current_aabb
     */
    @JvmStatic
    fun particlesGetCurrentAabb(particles: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(particlesGetCurrentAabbBind, singleton, particles)
    }

    /**
     * Sets the `Transform3D` that will be used by the particles when they first emit.
     *
     * Generated from Godot docs: RenderingServer.particles_set_emission_transform
     */
    @JvmStatic
    fun particlesSetEmissionTransform(particles: RID, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDAndTransform3DArg(particlesSetEmissionTransformBind, singleton, particles, transform)
    }

    /**
     * Creates a new 3D GPU particle collision or attractor and adds it to the RenderingServer. It can
     * be accessed with the RID that is returned. This RID can be used in most `particles_collision_*`
     * RenderingServer functions. Note: The equivalent nodes are `GPUParticlesCollision3D` and
     * `GPUParticlesAttractor3D`.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_create
     */
    @JvmStatic
    fun particlesCollisionCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(particlesCollisionCreateBind, singleton)
    }

    /**
     * Sets the collision or attractor shape `type` for the 3D GPU particles collision or attractor
     * specified by the `particles_collision` RID.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_collision_type
     */
    @JvmStatic
    fun particlesCollisionSetCollisionType(particlesCollision: RID, type: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesCollisionSetCollisionTypeBind, singleton, particlesCollision, type)
    }

    /**
     * Sets the cull `mask` for the 3D GPU particles collision or attractor specified by the
     * `particles_collision` RID. Equivalent to `GPUParticlesCollision3D.cull_mask` or
     * `GPUParticlesAttractor3D.cull_mask` depending on the `particles_collision` type.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_cull_mask
     */
    @JvmStatic
    fun particlesCollisionSetCullMask(particlesCollision: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(particlesCollisionSetCullMaskBind, singleton, particlesCollision, mask)
    }

    /**
     * Sets the `radius` for the 3D GPU particles sphere collision or attractor specified by the
     * `particles_collision` RID. Equivalent to `GPUParticlesCollisionSphere3D.radius` or
     * `GPUParticlesAttractorSphere3D.radius` depending on the `particles_collision` type.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_sphere_radius
     */
    @JvmStatic
    fun particlesCollisionSetSphereRadius(particlesCollision: RID, radius: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesCollisionSetSphereRadiusBind, singleton, particlesCollision, radius)
    }

    /**
     * Sets the `extents` for the 3D GPU particles collision by the `particles_collision` RID.
     * Equivalent to `GPUParticlesCollisionBox3D.size`, `GPUParticlesCollisionSDF3D.size`,
     * `GPUParticlesCollisionHeightField3D.size`, `GPUParticlesAttractorBox3D.size` or
     * `GPUParticlesAttractorVectorField3D.size` depending on the `particles_collision` type.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_box_extents
     */
    @JvmStatic
    fun particlesCollisionSetBoxExtents(particlesCollision: RID, extents: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(particlesCollisionSetBoxExtentsBind, singleton, particlesCollision, extents)
    }

    /**
     * Sets the `strength` for the 3D GPU particles attractor specified by the `particles_collision`
     * RID. Only used for attractors, not colliders. Equivalent to `GPUParticlesAttractor3D.strength`.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_attractor_strength
     */
    @JvmStatic
    fun particlesCollisionSetAttractorStrength(particlesCollision: RID, strength: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesCollisionSetAttractorStrengthBind, singleton, particlesCollision, strength)
    }

    /**
     * Sets the directionality `amount` for the 3D GPU particles attractor specified by the
     * `particles_collision` RID. Only used for attractors, not colliders. Equivalent to
     * `GPUParticlesAttractor3D.directionality`.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_attractor_directionality
     */
    @JvmStatic
    fun particlesCollisionSetAttractorDirectionality(particlesCollision: RID, amount: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesCollisionSetAttractorDirectionalityBind, singleton, particlesCollision, amount)
    }

    /**
     * Sets the attenuation `curve` for the 3D GPU particles attractor specified by the
     * `particles_collision` RID. Only used for attractors, not colliders. Equivalent to
     * `GPUParticlesAttractor3D.attenuation`.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_attractor_attenuation
     */
    @JvmStatic
    fun particlesCollisionSetAttractorAttenuation(particlesCollision: RID, curve: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesCollisionSetAttractorAttenuationBind, singleton, particlesCollision, curve)
    }

    /**
     * Sets the signed distance field `texture` for the 3D GPU particles collision specified by the
     * `particles_collision` RID. Equivalent to `GPUParticlesCollisionSDF3D.texture` or
     * `GPUParticlesAttractorVectorField3D.texture` depending on the `particles_collision` type.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_field_texture
     */
    @JvmStatic
    fun particlesCollisionSetFieldTexture(particlesCollision: RID, texture: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(particlesCollisionSetFieldTextureBind, singleton, particlesCollision, texture)
    }

    /**
     * Requests an update for the 3D GPU particle collision heightfield. This may be automatically
     * called by the 3D GPU particle collision heightfield depending on its
     * `GPUParticlesCollisionHeightField3D.update_mode`.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_height_field_update
     */
    @JvmStatic
    fun particlesCollisionHeightFieldUpdate(particlesCollision: RID) {
        ObjectCalls.ptrcallWithRIDArg(particlesCollisionHeightFieldUpdateBind, singleton, particlesCollision)
    }

    /**
     * Sets the heightmap `resolution` for the 3D GPU particles heightfield collision specified by the
     * `particles_collision` RID. Equivalent to `GPUParticlesCollisionHeightField3D.resolution`.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_height_field_resolution
     */
    @JvmStatic
    fun particlesCollisionSetHeightFieldResolution(particlesCollision: RID, resolution: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesCollisionSetHeightFieldResolutionBind, singleton, particlesCollision, resolution)
    }

    /**
     * Sets the heightfield `mask` for the 3D GPU particles heightfield collision specified by the
     * `particles_collision` RID. Equivalent to `GPUParticlesCollisionHeightField3D.heightfield_mask`.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_height_field_mask
     */
    @JvmStatic
    fun particlesCollisionSetHeightFieldMask(particlesCollision: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(particlesCollisionSetHeightFieldMaskBind, singleton, particlesCollision, mask)
    }

    /**
     * Creates a new fog volume and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID will be used in all `fog_volume_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. Note: The equivalent node is `FogVolume`.
     *
     * Generated from Godot docs: RenderingServer.fog_volume_create
     */
    @JvmStatic
    fun fogVolumeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(fogVolumeCreateBind, singleton)
    }

    /**
     * Sets the shape of the fog volume to either `RenderingServer.FOG_VOLUME_SHAPE_ELLIPSOID`,
     * `RenderingServer.FOG_VOLUME_SHAPE_CONE`, `RenderingServer.FOG_VOLUME_SHAPE_CYLINDER`,
     * `RenderingServer.FOG_VOLUME_SHAPE_BOX` or `RenderingServer.FOG_VOLUME_SHAPE_WORLD`.
     *
     * Generated from Godot docs: RenderingServer.fog_volume_set_shape
     */
    @JvmStatic
    fun fogVolumeSetShape(fogVolume: RID, shape: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(fogVolumeSetShapeBind, singleton, fogVolume, shape)
    }

    /**
     * Sets the size of the fog volume when shape is `RenderingServer.FOG_VOLUME_SHAPE_ELLIPSOID`,
     * `RenderingServer.FOG_VOLUME_SHAPE_CONE`, `RenderingServer.FOG_VOLUME_SHAPE_CYLINDER` or
     * `RenderingServer.FOG_VOLUME_SHAPE_BOX`.
     *
     * Generated from Godot docs: RenderingServer.fog_volume_set_size
     */
    @JvmStatic
    fun fogVolumeSetSize(fogVolume: RID, size: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(fogVolumeSetSizeBind, singleton, fogVolume, size)
    }

    /**
     * Sets the `Material` of the fog volume. Can be either a `FogMaterial` or a custom
     * `ShaderMaterial`.
     *
     * Generated from Godot docs: RenderingServer.fog_volume_set_material
     */
    @JvmStatic
    fun fogVolumeSetMaterial(fogVolume: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(fogVolumeSetMaterialBind, singleton, fogVolume, material)
    }

    /**
     * Creates a new 3D visibility notifier object and adds it to the RenderingServer. It can be
     * accessed with the RID that is returned. This RID will be used in all `visibility_notifier_*`
     * RenderingServer functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. To place in a scene, attach this notifier to an instance
     * using `instance_set_base` using the returned RID. Note: The equivalent node is
     * `VisibleOnScreenNotifier3D`.
     *
     * Generated from Godot docs: RenderingServer.visibility_notifier_create
     */
    @JvmStatic
    fun visibilityNotifierCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(visibilityNotifierCreateBind, singleton)
    }

    /**
     * Sets the AABB of the specified visibility notifier.
     *
     * Generated from Godot docs: RenderingServer.visibility_notifier_set_aabb
     */
    @JvmStatic
    fun visibilityNotifierSetAabb(notifier: RID, aabb: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(visibilityNotifierSetAabbBind, singleton, notifier, aabb)
    }

    /**
     * Sets the methods to be called when the notifier enters or exits the view.
     *
     * Generated from Godot docs: RenderingServer.visibility_notifier_set_callbacks
     */
    @JvmStatic
    fun visibilityNotifierSetCallbacks(notifier: RID, enterCallable: GodotCallable, exitCallable: GodotCallable) {
        ObjectCalls.ptrcallWithRIDTwoCallableArgs(visibilityNotifierSetCallbacksBind, singleton, notifier, enterCallable.target.handle, enterCallable.method, exitCallable.target.handle, exitCallable.method)
    }

    /**
     * Creates an occluder instance and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID will be used in all `occluder_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. Note: The equivalent resource is `Occluder3D` (not to be confused with the
     * `OccluderInstance3D` node).
     *
     * Generated from Godot docs: RenderingServer.occluder_create
     */
    @JvmStatic
    fun occluderCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(occluderCreateBind, singleton)
    }

    /**
     * Sets the mesh data for the given occluder RID, which controls the shape of the occlusion culling
     * that will be performed.
     *
     * Generated from Godot docs: RenderingServer.occluder_set_mesh
     */
    @JvmStatic
    fun occluderSetMesh(occluder: RID, vertices: List<Vector3>, indices: List<Int>) {
        ObjectCalls.ptrcallWithRIDPackedVector3ListPackedInt32ListArgs(occluderSetMeshBind, singleton, occluder, vertices, indices)
    }

    /**
     * Creates a 3D camera and adds it to the RenderingServer. It can be accessed with the RID that is
     * returned. This RID will be used in all `camera_*` RenderingServer functions. Once finished with
     * your RID, you will want to free the RID using the RenderingServer's `free_rid` method. Note: The
     * equivalent node is `Camera3D`.
     *
     * Generated from Godot docs: RenderingServer.camera_create
     */
    @JvmStatic
    fun cameraCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(cameraCreateBind, singleton)
    }

    /**
     * Sets camera to use perspective projection. Objects on the screen becomes smaller when they are
     * far away.
     *
     * Generated from Godot docs: RenderingServer.camera_set_perspective
     */
    @JvmStatic
    fun cameraSetPerspective(camera: RID, fovyDegrees: Double, zNear: Double, zFar: Double) {
        ObjectCalls.ptrcallWithRIDAndThreeDoubleArgs(cameraSetPerspectiveBind, singleton, camera, fovyDegrees, zNear, zFar)
    }

    /**
     * Sets camera to use orthogonal projection, also known as orthographic projection. Objects remain
     * the same size on the screen no matter how far away they are.
     *
     * Generated from Godot docs: RenderingServer.camera_set_orthogonal
     */
    @JvmStatic
    fun cameraSetOrthogonal(camera: RID, size: Double, zNear: Double, zFar: Double) {
        ObjectCalls.ptrcallWithRIDAndThreeDoubleArgs(cameraSetOrthogonalBind, singleton, camera, size, zNear, zFar)
    }

    /**
     * Sets camera to use frustum projection. This mode allows adjusting the `offset` argument to
     * create "tilted frustum" effects.
     *
     * Generated from Godot docs: RenderingServer.camera_set_frustum
     */
    @JvmStatic
    fun cameraSetFrustum(camera: RID, size: Double, offset: Vector2, zNear: Double, zFar: Double) {
        ObjectCalls.ptrcallWithRIDDoubleVector2TwoDoubleArgs(cameraSetFrustumBind, singleton, camera, size, offset, zNear, zFar)
    }

    /**
     * Sets `Transform3D` of camera.
     *
     * Generated from Godot docs: RenderingServer.camera_set_transform
     */
    @JvmStatic
    fun cameraSetTransform(camera: RID, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDAndTransform3DArg(cameraSetTransformBind, singleton, camera, transform)
    }

    /**
     * Sets the cull mask associated with this camera. The cull mask describes which 3D layers are
     * rendered by this camera. Equivalent to `Camera3D.cull_mask`.
     *
     * Generated from Godot docs: RenderingServer.camera_set_cull_mask
     */
    @JvmStatic
    fun cameraSetCullMask(camera: RID, layers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(cameraSetCullMaskBind, singleton, camera, layers)
    }

    /**
     * Sets the environment used by this camera. Equivalent to `Camera3D.environment`.
     *
     * Generated from Godot docs: RenderingServer.camera_set_environment
     */
    @JvmStatic
    fun cameraSetEnvironment(camera: RID, env: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(cameraSetEnvironmentBind, singleton, camera, env)
    }

    /**
     * Sets the camera_attributes created with `camera_attributes_create` to the given camera.
     *
     * Generated from Godot docs: RenderingServer.camera_set_camera_attributes
     */
    @JvmStatic
    fun cameraSetCameraAttributes(camera: RID, effects: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(cameraSetCameraAttributesBind, singleton, camera, effects)
    }

    /**
     * Sets the compositor used by this camera. Equivalent to `Camera3D.compositor`.
     *
     * Generated from Godot docs: RenderingServer.camera_set_compositor
     */
    @JvmStatic
    fun cameraSetCompositor(camera: RID, compositor: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(cameraSetCompositorBind, singleton, camera, compositor)
    }

    /**
     * If `true`, preserves the horizontal aspect ratio which is equivalent to `Camera3D.KEEP_WIDTH`.
     * If `false`, preserves the vertical aspect ratio which is equivalent to `Camera3D.KEEP_HEIGHT`.
     *
     * Generated from Godot docs: RenderingServer.camera_set_use_vertical_aspect
     */
    @JvmStatic
    fun cameraSetUseVerticalAspect(camera: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(cameraSetUseVerticalAspectBind, singleton, camera, enable)
    }

    /**
     * Creates an empty viewport and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID will be used in all `viewport_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. Note: The equivalent node is `Viewport`.
     *
     * Generated from Godot docs: RenderingServer.viewport_create
     */
    @JvmStatic
    fun viewportCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(viewportCreateBind, singleton)
    }

    /**
     * If `true`, the viewport uses augmented or virtual reality technologies. See `XRInterface`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_use_xr
     */
    @JvmStatic
    fun viewportSetUseXr(viewport: RID, useXr: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetUseXrBind, singleton, viewport, useXr)
    }

    /**
     * Sets the viewport's `width` and `height` in pixels. Optionally the `view_count` can be set to
     * increase the number of view layers for stereo rendering.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_size
     */
    @JvmStatic
    fun viewportSetSize(viewport: RID, width: Int, height: Int, viewCount: Int = 1) {
        ObjectCalls.ptrcallWithRIDAndThreeIntArgs(viewportSetSizeBind, singleton, viewport, width, height, viewCount)
    }

    /**
     * If `true`, sets the viewport active, else sets it inactive.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_active
     */
    @JvmStatic
    fun viewportSetActive(viewport: RID, active: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetActiveBind, singleton, viewport, active)
    }

    /**
     * Sets the viewport's parent to the viewport specified by the `parent_viewport` RID.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_parent_viewport
     */
    @JvmStatic
    fun viewportSetParentViewport(viewport: RID, parentViewport: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportSetParentViewportBind, singleton, viewport, parentViewport)
    }

    /**
     * Copies the viewport to a region of the screen specified by `rect`. If
     * `viewport_set_render_direct_to_screen` is `true`, then the viewport does not use a framebuffer
     * and the contents of the viewport are rendered directly to screen. However, note that the root
     * viewport is drawn last, therefore it will draw over the screen. Accordingly, you must set the
     * root viewport to an area that does not cover the area that you have attached this viewport to.
     * For example, you can set the root viewport to not render at all with the following code:
     *
     * Generated from Godot docs: RenderingServer.viewport_attach_to_screen
     */
    @JvmStatic
    fun viewportAttachToScreen(viewport: RID, rect: Rect2, screen: Int = 0) {
        ObjectCalls.ptrcallWithRIDRect2IntArgs(viewportAttachToScreenBind, singleton, viewport, rect, screen)
    }

    /**
     * If `true`, render the contents of the viewport directly to screen. This allows a low-level
     * optimization where you can skip drawing a viewport to the root viewport. While this optimization
     * can result in a significant increase in speed (especially on older devices), it comes at a cost
     * of usability. When this is enabled, you cannot read from the viewport or from the
     * screen_texture. You also lose the benefit of certain window settings, such as the various
     * stretch modes. Another consequence to be aware of is that in 2D the rendering happens in window
     * coordinates, so if you have a viewport that is double the size of the window, and you set this,
     * then only the portion that fits within the window will be drawn, no automatic scaling is
     * possible, even if your game scene is significantly larger than the window size.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_render_direct_to_screen
     */
    @JvmStatic
    fun viewportSetRenderDirectToScreen(viewport: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetRenderDirectToScreenBind, singleton, viewport, enabled)
    }

    /**
     * Sets the rendering mask associated with this `Viewport`. Only `CanvasItem` nodes with a matching
     * rendering visibility layer will be rendered by this `Viewport`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_canvas_cull_mask
     */
    @JvmStatic
    fun viewportSetCanvasCullMask(viewport: RID, canvasCullMask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(viewportSetCanvasCullMaskBind, singleton, viewport, canvasCullMask)
    }

    /**
     * Sets the 3D resolution scaling mode. Bilinear scaling renders at different resolution to either
     * undersample or supersample the viewport. FidelityFX Super Resolution 1.0, abbreviated to FSR, is
     * an upscaling technology that produces high quality images at fast framerates by using a
     * spatially aware upscaling algorithm. FSR is slightly more expensive than bilinear, but it
     * produces significantly higher image quality. FSR should be used where possible.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_scaling_3d_mode
     */
    @JvmStatic
    fun viewportSetScaling3dMode(viewport: RID, scaling3dMode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetScaling3dModeBind, singleton, viewport, scaling3dMode)
    }

    /**
     * Scales the 3D render buffer based on the viewport size uses an image filter specified in
     * `ViewportScaling3DMode` to scale the output image to the full viewport size. Values lower than
     * `1.0` can be used to speed up 3D rendering at the cost of quality (undersampling). Values
     * greater than `1.0` are only valid for bilinear mode and can be used to improve 3D rendering
     * quality at a high performance cost (supersampling). See also `ViewportMSAA` for multi-sample
     * antialiasing, which is significantly cheaper but only smoothens the edges of polygons. When
     * using FSR upscaling, AMD recommends exposing the following values as preset options to users
     * "Ultra Quality: 0.77", "Quality: 0.67", "Balanced: 0.59", "Performance: 0.5" instead of exposing
     * the entire scale.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_scaling_3d_scale
     */
    @JvmStatic
    fun viewportSetScaling3dScale(viewport: RID, scale: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(viewportSetScaling3dScaleBind, singleton, viewport, scale)
    }

    /**
     * Determines how sharp the upscaled image will be when using the FSR upscaling mode. Sharpness
     * halves with every whole number. Values go from 0.0 (sharpest) to 2.0. Values above 2.0 won't
     * make a visible difference.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_fsr_sharpness
     */
    @JvmStatic
    fun viewportSetFsrSharpness(viewport: RID, sharpness: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(viewportSetFsrSharpnessBind, singleton, viewport, sharpness)
    }

    /**
     * Affects the final texture sharpness by reading from a lower or higher mipmap (also called
     * "texture LOD bias"). Negative values make mipmapped textures sharper but grainier when viewed at
     * a distance, while positive values make mipmapped textures blurrier (even when up close). To get
     * sharper textures at a distance without introducing too much graininess, set this between `-0.75`
     * and `0.0`. Enabling temporal antialiasing
     * (`ProjectSettings.rendering/anti_aliasing/quality/use_taa`) can help reduce the graininess
     * visible when using negative mipmap bias. Note: When the 3D scaling mode is set to FSR 1.0, this
     * value is used to adjust the automatic mipmap bias which is calculated internally based on the
     * scale factor. The formula for this is `-log2(1.0 / scale) + mipmap_bias`. Note: This method is
     * only supported in the Forward+ and Mobile renderers, not Compatibility. In Compatibility, this
     * method is always treated as if `mipmap_bias` was set to `0.0`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_texture_mipmap_bias
     */
    @JvmStatic
    fun viewportSetTextureMipmapBias(viewport: RID, mipmapBias: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(viewportSetTextureMipmapBiasBind, singleton, viewport, mipmapBias)
    }

    /**
     * Sets the maximum number of samples to take when using anisotropic filtering on textures (as a
     * power of two). A higher sample count will result in sharper textures at oblique angles, but is
     * more expensive to compute. A value of `0` forcibly disables anisotropic filtering, even on
     * materials where it is enabled. The anisotropic filtering level also affects decals and light
     * projectors if they are configured to use anisotropic filtering. See
     * `ProjectSettings.rendering/textures/decals/filter` and
     * `ProjectSettings.rendering/textures/light_projectors/filter`. Note: In 3D, for this setting to
     * have an effect, set `BaseMaterial3D.texture_filter` to
     * `BaseMaterial3D.TEXTURE_FILTER_LINEAR_WITH_MIPMAPS_ANISOTROPIC` or
     * `BaseMaterial3D.TEXTURE_FILTER_NEAREST_WITH_MIPMAPS_ANISOTROPIC` on materials. Note: In 2D, for
     * this setting to have an effect, set `CanvasItem.texture_filter` to
     * `CanvasItem.TEXTURE_FILTER_LINEAR_WITH_MIPMAPS_ANISOTROPIC` or
     * `CanvasItem.TEXTURE_FILTER_NEAREST_WITH_MIPMAPS_ANISOTROPIC` on the `CanvasItem` node displaying
     * the texture (or in `CanvasTexture`). However, anisotropic filtering is rarely useful in 2D, so
     * only enable it for textures in 2D if it makes a meaningful visual difference.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_anisotropic_filtering_level
     */
    @JvmStatic
    fun viewportSetAnisotropicFilteringLevel(viewport: RID, anisotropicFilteringLevel: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetAnisotropicFilteringLevelBind, singleton, viewport, anisotropicFilteringLevel)
    }

    /**
     * Sets when the viewport should be updated.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_update_mode
     */
    @JvmStatic
    fun viewportSetUpdateMode(viewport: RID, updateMode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetUpdateModeBind, singleton, viewport, updateMode)
    }

    /**
     * Returns the viewport's update mode. Warning: Calling this from any thread other than the
     * rendering thread will be detrimental to performance.
     *
     * Generated from Godot docs: RenderingServer.viewport_get_update_mode
     */
    @JvmStatic
    fun viewportGetUpdateMode(viewport: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(viewportGetUpdateModeBind, singleton, viewport)
    }

    /**
     * Sets the clear mode of a viewport.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_clear_mode
     */
    @JvmStatic
    fun viewportSetClearMode(viewport: RID, clearMode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetClearModeBind, singleton, viewport, clearMode)
    }

    /**
     * Returns the render target for the viewport.
     *
     * Generated from Godot docs: RenderingServer.viewport_get_render_target
     */
    @JvmStatic
    fun viewportGetRenderTarget(viewport: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(viewportGetRenderTargetBind, singleton, viewport)
    }

    /**
     * Returns the viewport's last rendered frame.
     *
     * Generated from Godot docs: RenderingServer.viewport_get_texture
     */
    @JvmStatic
    fun viewportGetTexture(viewport: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(viewportGetTextureBind, singleton, viewport)
    }

    /**
     * If `true`, the viewport's 3D elements are not rendered.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_disable_3d
     */
    @JvmStatic
    fun viewportSetDisable3d(viewport: RID, disable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetDisable3dBind, singleton, viewport, disable)
    }

    /**
     * If `true`, the viewport's canvas (i.e. 2D and GUI elements) is not rendered.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_disable_2d
     */
    @JvmStatic
    fun viewportSetDisable2d(viewport: RID, disable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetDisable2dBind, singleton, viewport, disable)
    }

    /**
     * Sets the viewport's environment mode which allows enabling or disabling rendering of 3D
     * environment over 2D canvas. When disabled, 2D will not be affected by the environment. When
     * enabled, 2D will be affected by the environment if the environment background mode is
     * `ENV_BG_CANVAS`. The default behavior is to inherit the setting from the viewport's parent. If
     * the topmost parent is also set to `VIEWPORT_ENVIRONMENT_INHERIT`, then the behavior will be the
     * same as if it was set to `VIEWPORT_ENVIRONMENT_ENABLED`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_environment_mode
     */
    @JvmStatic
    fun viewportSetEnvironmentMode(viewport: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetEnvironmentModeBind, singleton, viewport, mode)
    }

    /**
     * Sets a viewport's camera.
     *
     * Generated from Godot docs: RenderingServer.viewport_attach_camera
     */
    @JvmStatic
    fun viewportAttachCamera(viewport: RID, camera: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportAttachCameraBind, singleton, viewport, camera)
    }

    /**
     * Sets a viewport's scenario. The scenario contains information about environment information,
     * reflection atlas, etc.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_scenario
     */
    @JvmStatic
    fun viewportSetScenario(viewport: RID, scenario: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportSetScenarioBind, singleton, viewport, scenario)
    }

    /**
     * Sets a viewport's canvas.
     *
     * Generated from Godot docs: RenderingServer.viewport_attach_canvas
     */
    @JvmStatic
    fun viewportAttachCanvas(viewport: RID, canvas: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportAttachCanvasBind, singleton, viewport, canvas)
    }

    /**
     * Detaches a viewport from a canvas.
     *
     * Generated from Godot docs: RenderingServer.viewport_remove_canvas
     */
    @JvmStatic
    fun viewportRemoveCanvas(viewport: RID, canvas: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportRemoveCanvasBind, singleton, viewport, canvas)
    }

    /**
     * If `true`, canvas item transforms (i.e. origin position) are snapped to the nearest pixel when
     * rendering. This can lead to a crisper appearance at the cost of less smooth movement, especially
     * when `Camera2D` smoothing is enabled. Equivalent to
     * `ProjectSettings.rendering/2d/snap/snap_2d_transforms_to_pixel`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_snap_2d_transforms_to_pixel
     */
    @JvmStatic
    fun viewportSetSnap2dTransformsToPixel(viewport: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetSnap2dTransformsToPixelBind, singleton, viewport, enabled)
    }

    /**
     * If `true`, canvas item vertices (i.e. polygon points) are snapped to the nearest pixel when
     * rendering. This can lead to a crisper appearance at the cost of less smooth movement, especially
     * when `Camera2D` smoothing is enabled. Equivalent to
     * `ProjectSettings.rendering/2d/snap/snap_2d_vertices_to_pixel`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_snap_2d_vertices_to_pixel
     */
    @JvmStatic
    fun viewportSetSnap2dVerticesToPixel(viewport: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetSnap2dVerticesToPixelBind, singleton, viewport, enabled)
    }

    /**
     * Sets the default texture filtering mode for the specified `viewport` RID.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_default_canvas_item_texture_filter
     */
    @JvmStatic
    fun viewportSetDefaultCanvasItemTextureFilter(viewport: RID, filter: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetDefaultCanvasItemTextureFilterBind, singleton, viewport, filter)
    }

    /**
     * Sets the default texture repeat mode for the specified `viewport` RID.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_default_canvas_item_texture_repeat
     */
    @JvmStatic
    fun viewportSetDefaultCanvasItemTextureRepeat(viewport: RID, repeat: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetDefaultCanvasItemTextureRepeatBind, singleton, viewport, repeat)
    }

    /**
     * Sets the transformation of a viewport's canvas.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_canvas_transform
     */
    @JvmStatic
    fun viewportSetCanvasTransform(viewport: RID, canvas: RID, offset: Transform2D) {
        ObjectCalls.ptrcallWithTwoRIDAndTransform2DArg(viewportSetCanvasTransformBind, singleton, viewport, canvas, offset)
    }

    /**
     * Sets the stacking order for a viewport's canvas. `layer` is the actual canvas layer, while
     * `sublayer` specifies the stacking order of the canvas among those in the same layer. Note:
     * `layer` should be between `CANVAS_LAYER_MIN` and `CANVAS_LAYER_MAX` (inclusive). Any other value
     * will wrap around.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_canvas_stacking
     */
    @JvmStatic
    fun viewportSetCanvasStacking(viewport: RID, canvas: RID, layer: Int, sublayer: Int) {
        ObjectCalls.ptrcallWithTwoRIDTwoIntArgs(viewportSetCanvasStackingBind, singleton, viewport, canvas, layer, sublayer)
    }

    /**
     * If `true`, the viewport renders its background as transparent.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_transparent_background
     */
    @JvmStatic
    fun viewportSetTransparentBackground(viewport: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetTransparentBackgroundBind, singleton, viewport, enabled)
    }

    /**
     * Sets the viewport's global transformation matrix.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_global_canvas_transform
     */
    @JvmStatic
    fun viewportSetGlobalCanvasTransform(viewport: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(viewportSetGlobalCanvasTransformBind, singleton, viewport, transform)
    }

    /**
     * Sets the viewport's 2D signed distance field `ProjectSettings.rendering/2d/sdf/oversize` and
     * `ProjectSettings.rendering/2d/sdf/scale`. This is used when sampling the signed distance field
     * in `CanvasItem` shaders as well as `GPUParticles2D` collision. This is not used by SDFGI in 3D
     * rendering.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_sdf_oversize_and_scale
     */
    @JvmStatic
    fun viewportSetSdfOversizeAndScale(viewport: RID, oversize: Long, scale: Long) {
        ObjectCalls.ptrcallWithRIDAndTwoLongArgs(viewportSetSdfOversizeAndScaleBind, singleton, viewport, oversize, scale)
    }

    /**
     * Sets the `size` of the shadow atlas's images (used for omni and spot lights) on the viewport
     * specified by the `viewport` RID. The value is rounded up to the nearest power of 2. If
     * `use_16_bits` is `true`, use 16 bits for the omni/spot shadow depth map. Enabling this results
     * in shadows having less precision and may result in shadow acne, but can lead to performance
     * improvements on some devices. Note: If this is set to `0`, no positional shadows will be visible
     * at all. This can improve performance significantly on low-end systems by reducing both the CPU
     * and GPU load (as fewer draw calls are needed to draw the scene without shadows).
     *
     * Generated from Godot docs: RenderingServer.viewport_set_positional_shadow_atlas_size
     */
    @JvmStatic
    fun viewportSetPositionalShadowAtlasSize(viewport: RID, size: Int, use16Bits: Boolean = false) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(viewportSetPositionalShadowAtlasSizeBind, singleton, viewport, size, use16Bits)
    }

    /**
     * Sets the number of subdivisions to use in the specified shadow atlas `quadrant` for omni and
     * spot shadows. See also `Viewport.set_positional_shadow_atlas_quadrant_subdiv`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_positional_shadow_atlas_quadrant_subdivision
     */
    @JvmStatic
    fun viewportSetPositionalShadowAtlasQuadrantSubdivision(viewport: RID, quadrant: Int, subdivision: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(viewportSetPositionalShadowAtlasQuadrantSubdivisionBind, singleton, viewport, quadrant, subdivision)
    }

    /**
     * Sets the multisample antialiasing mode for 3D on the specified `viewport` RID. Equivalent to
     * `ProjectSettings.rendering/anti_aliasing/quality/msaa_3d` or `Viewport.msaa_3d`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_msaa_3d
     */
    @JvmStatic
    fun viewportSetMsaa3d(viewport: RID, msaa: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetMsaa3dBind, singleton, viewport, msaa)
    }

    /**
     * Sets the multisample antialiasing mode for 2D/Canvas on the specified `viewport` RID. Equivalent
     * to `ProjectSettings.rendering/anti_aliasing/quality/msaa_2d` or `Viewport.msaa_2d`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_msaa_2d
     */
    @JvmStatic
    fun viewportSetMsaa2d(viewport: RID, msaa: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetMsaa2dBind, singleton, viewport, msaa)
    }

    /**
     * If `true`, 2D rendering will use a high dynamic range (HDR) `RGBA16` format framebuffer.
     * Additionally, 2D rendering will be performed on linear values and will be converted using the
     * appropriate transfer function immediately before blitting to the screen (if the Viewport is
     * attached to the screen). Practically speaking, this means that the end result of the Viewport
     * will not be clamped to the `0-1` range and can be used in 3D rendering without color encoding
     * adjustments. This allows 2D rendering to take advantage of effects requiring high dynamic range
     * (e.g. 2D glow) as well as substantially improves the appearance of effects requiring highly
     * detailed gradients. This setting has the same effect as `Viewport.use_hdr_2d`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_use_hdr_2d
     */
    @JvmStatic
    fun viewportSetUseHdr2d(viewport: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetUseHdr2dBind, singleton, viewport, enabled)
    }

    /**
     * Sets the viewport's screen-space antialiasing mode. Equivalent to
     * `ProjectSettings.rendering/anti_aliasing/quality/screen_space_aa` or `Viewport.screen_space_aa`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_screen_space_aa
     */
    @JvmStatic
    fun viewportSetScreenSpaceAa(viewport: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetScreenSpaceAaBind, singleton, viewport, mode)
    }

    /**
     * If `true`, use temporal antialiasing. Equivalent to
     * `ProjectSettings.rendering/anti_aliasing/quality/use_taa` or `Viewport.use_taa`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_use_taa
     */
    @JvmStatic
    fun viewportSetUseTaa(viewport: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetUseTaaBind, singleton, viewport, enable)
    }

    /**
     * Equivalent to `Viewport.use_debanding`. See also
     * `ProjectSettings.rendering/anti_aliasing/quality/use_debanding`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_use_debanding
     */
    @JvmStatic
    fun viewportSetUseDebanding(viewport: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetUseDebandingBind, singleton, viewport, enable)
    }

    /**
     * If `true`, enables occlusion culling on the specified viewport. Equivalent to
     * `ProjectSettings.rendering/occlusion_culling/use_occlusion_culling`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_use_occlusion_culling
     */
    @JvmStatic
    fun viewportSetUseOcclusionCulling(viewport: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetUseOcclusionCullingBind, singleton, viewport, enable)
    }

    /**
     * Sets the `ProjectSettings.rendering/occlusion_culling/occlusion_rays_per_thread` to use for
     * occlusion culling. This parameter is global and cannot be set on a per-viewport basis.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_occlusion_rays_per_thread
     */
    @JvmStatic
    fun viewportSetOcclusionRaysPerThread(raysPerThread: Int) {
        ObjectCalls.ptrcallWithIntArg(viewportSetOcclusionRaysPerThreadBind, singleton, raysPerThread)
    }

    /**
     * Sets the `ProjectSettings.rendering/occlusion_culling/bvh_build_quality` to use for occlusion
     * culling. This parameter is global and cannot be set on a per-viewport basis.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_occlusion_culling_build_quality
     */
    @JvmStatic
    fun viewportSetOcclusionCullingBuildQuality(quality: Long) {
        ObjectCalls.ptrcallWithLongArg(viewportSetOcclusionCullingBuildQualityBind, singleton, quality)
    }

    /**
     * Returns a statistic about the rendering engine which can be used for performance profiling. This
     * is separated into render pass `type`s, each of them having the same `info`s you can query
     * (different passes will return different values). See also `get_rendering_info`, which returns
     * global information across all viewports. Note: Viewport rendering information is not available
     * until at least 2 frames have been rendered by the engine. If rendering information is not
     * available, `viewport_get_render_info` returns `0`. To print rendering information in `_ready()`
     * successfully, use the following:
     *
     * Generated from Godot docs: RenderingServer.viewport_get_render_info
     */
    @JvmStatic
    fun viewportGetRenderInfo(viewport: RID, type: Long, info: Long): Int {
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetInt(viewportGetRenderInfoBind, singleton, viewport, type, info)
    }

    /**
     * Sets the debug draw mode of a viewport.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_debug_draw
     */
    @JvmStatic
    fun viewportSetDebugDraw(viewport: RID, draw: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetDebugDrawBind, singleton, viewport, draw)
    }

    /**
     * Sets the measurement for the given `viewport` RID (obtained using `Viewport.get_viewport_rid`).
     * Once enabled, `viewport_get_measured_render_time_cpu` and
     * `viewport_get_measured_render_time_gpu` will return values greater than `0.0` when queried with
     * the given `viewport`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_measure_render_time
     */
    @JvmStatic
    fun viewportSetMeasureRenderTime(viewport: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetMeasureRenderTimeBind, singleton, viewport, enable)
    }

    /**
     * Returns the CPU time taken to render the last frame in milliseconds. This only includes time
     * spent in rendering-related operations; scripts' `_process` functions and other engine subsystems
     * are not included in this readout. To get a complete readout of CPU time spent to render the
     * scene, sum the render times of all viewports that are drawn every frame plus
     * `get_frame_setup_time_cpu`. Unlike `Engine.get_frames_per_second`, this method will accurately
     * reflect CPU utilization even if framerate is capped via V-Sync or `Engine.max_fps`. See also
     * `viewport_get_measured_render_time_gpu`. Note: Requires measurements to be enabled on the
     * specified `viewport` using `viewport_set_measure_render_time`. Otherwise, this method returns
     * `0.0`.
     *
     * Generated from Godot docs: RenderingServer.viewport_get_measured_render_time_cpu
     */
    @JvmStatic
    fun viewportGetMeasuredRenderTimeCpu(viewport: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(viewportGetMeasuredRenderTimeCpuBind, singleton, viewport)
    }

    /**
     * Returns the GPU time taken to render the last frame in milliseconds. To get a complete readout
     * of GPU time spent to render the scene, sum the render times of all viewports that are drawn
     * every frame. Unlike `Engine.get_frames_per_second`, this method accurately reflects GPU
     * utilization even if framerate is capped via V-Sync or `Engine.max_fps`. See also
     * `viewport_get_measured_render_time_cpu`. Note: Requires measurements to be enabled on the
     * specified `viewport` using `viewport_set_measure_render_time`. Otherwise, this method returns
     * `0.0`. Note: When GPU utilization is low enough during a certain period of time, GPUs will
     * decrease their power state (which in turn decreases core and memory clock speeds). This can
     * cause the reported GPU time to increase if GPU utilization is kept low enough by a framerate cap
     * (compared to what it would be at the GPU's highest power state). Keep this in mind when
     * benchmarking using `viewport_get_measured_render_time_gpu`. This behavior can be overridden in
     * the graphics driver settings at the cost of higher power usage.
     *
     * Generated from Godot docs: RenderingServer.viewport_get_measured_render_time_gpu
     */
    @JvmStatic
    fun viewportGetMeasuredRenderTimeGpu(viewport: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(viewportGetMeasuredRenderTimeGpuBind, singleton, viewport)
    }

    /**
     * Sets the Variable Rate Shading (VRS) mode for the viewport. If the GPU does not support VRS,
     * this property is ignored. Equivalent to `ProjectSettings.rendering/vrs/mode`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_vrs_mode
     */
    @JvmStatic
    fun viewportSetVrsMode(viewport: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetVrsModeBind, singleton, viewport, mode)
    }

    /**
     * Sets the update mode for Variable Rate Shading (VRS) for the viewport. VRS requires the input
     * texture to be converted to the format usable by the VRS method supported by the hardware. The
     * update mode defines how often this happens. If the GPU does not support VRS, or VRS is not
     * enabled, this property is ignored. If set to `RenderingServer.VIEWPORT_VRS_UPDATE_ONCE`, the
     * input texture is copied once and the mode is changed to
     * `RenderingServer.VIEWPORT_VRS_UPDATE_DISABLED`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_vrs_update_mode
     */
    @JvmStatic
    fun viewportSetVrsUpdateMode(viewport: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetVrsUpdateModeBind, singleton, viewport, mode)
    }

    /**
     * The texture to use when the VRS mode is set to `RenderingServer.VIEWPORT_VRS_TEXTURE`.
     * Equivalent to `ProjectSettings.rendering/vrs/texture`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_vrs_texture
     */
    @JvmStatic
    fun viewportSetVrsTexture(viewport: RID, texture: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportSetVrsTextureBind, singleton, viewport, texture)
    }

    /**
     * Creates an empty sky and adds it to the RenderingServer. It can be accessed with the RID that is
     * returned. This RID will be used in all `sky_*` RenderingServer functions. Once finished with
     * your RID, you will want to free the RID using the RenderingServer's `free_rid` method.
     *
     * Generated from Godot docs: RenderingServer.sky_create
     */
    @JvmStatic
    fun skyCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(skyCreateBind, singleton)
    }

    /**
     * Sets the `radiance_size` of the sky specified by the `sky` RID (in pixels). Equivalent to
     * `Sky.radiance_size`.
     *
     * Generated from Godot docs: RenderingServer.sky_set_radiance_size
     */
    @JvmStatic
    fun skySetRadianceSize(sky: RID, radianceSize: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(skySetRadianceSizeBind, singleton, sky, radianceSize)
    }

    /**
     * Sets the process `mode` of the sky specified by the `sky` RID. Equivalent to `Sky.process_mode`.
     *
     * Generated from Godot docs: RenderingServer.sky_set_mode
     */
    @JvmStatic
    fun skySetMode(sky: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(skySetModeBind, singleton, sky, mode)
    }

    /**
     * Sets the material that the sky uses to render the background, ambient and reflection maps.
     *
     * Generated from Godot docs: RenderingServer.sky_set_material
     */
    @JvmStatic
    fun skySetMaterial(sky: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(skySetMaterialBind, singleton, sky, material)
    }

    /**
     * Generates and returns an `Image` containing the radiance map for the specified `sky` RID. This
     * supports built-in sky material and custom sky shaders. If `bake_irradiance` is `true`, the
     * irradiance map is saved instead of the radiance map. The radiance map is used to render
     * reflected light, while the irradiance map is used to render ambient light. See also
     * `environment_bake_panorama`. Note: The image is saved using linear encoding without any
     * tonemapping performed, which means it will look too dark if viewed directly in an image editor.
     * `energy` values above `1.0` can be used to brighten the resulting image. Note: `size` should be
     * a 2:1 aspect ratio for the generated panorama to have square pixels. For radiance maps, there is
     * no point in using a height greater than `Sky.radiance_size`, as it won't increase detail.
     * Irradiance maps only contain low-frequency data, so there is usually no point in going past a
     * size of 128×64 pixels when saving an irradiance map.
     *
     * Generated from Godot docs: RenderingServer.sky_bake_panorama
     */
    @JvmStatic
    fun skyBakePanorama(sky: RID, energy: Double, bakeIrradiance: Boolean, size: Vector2i): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithRIDDoubleBoolVector2iArgsRetObject(skyBakePanoramaBind, singleton, sky, energy, bakeIrradiance, size))
    }

    /**
     * Creates a new rendering effect and adds it to the RenderingServer. It can be accessed with the
     * RID that is returned. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method.
     *
     * Generated from Godot docs: RenderingServer.compositor_effect_create
     */
    @JvmStatic
    fun compositorEffectCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(compositorEffectCreateBind, singleton)
    }

    /**
     * Enables/disables this rendering effect.
     *
     * Generated from Godot docs: RenderingServer.compositor_effect_set_enabled
     */
    @JvmStatic
    fun compositorEffectSetEnabled(effect: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(compositorEffectSetEnabledBind, singleton, effect, enabled)
    }

    /**
     * Sets the callback type (`callback_type`) and callback method(`callback`) for this rendering
     * effect.
     *
     * Generated from Godot docs: RenderingServer.compositor_effect_set_callback
     */
    @JvmStatic
    fun compositorEffectSetCallback(effect: RID, callbackType: Long, callback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDLongCallableArgs(compositorEffectSetCallbackBind, singleton, effect, callbackType, callback.target.handle, callback.method)
    }

    /**
     * Sets the flag (`flag`) for this rendering effect to `true` or `false` (`set`).
     *
     * Generated from Godot docs: RenderingServer.compositor_effect_set_flag
     */
    @JvmStatic
    fun compositorEffectSetFlag(effect: RID, flag: Long, set: Boolean) {
        ObjectCalls.ptrcallWithRIDLongAndBoolArgs(compositorEffectSetFlagBind, singleton, effect, flag, set)
    }

    /**
     * Creates a new compositor and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method.
     *
     * Generated from Godot docs: RenderingServer.compositor_create
     */
    @JvmStatic
    fun compositorCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(compositorCreateBind, singleton)
    }

    /**
     * Sets the compositor effects for the specified compositor RID. `effects` should be an array
     * containing RIDs created with `compositor_effect_create`.
     *
     * Generated from Godot docs: RenderingServer.compositor_set_compositor_effects
     */
    @JvmStatic
    fun compositorSetCompositorEffects(compositor: RID, effects: List<RID>) {
        ObjectCalls.ptrcallWithRIDAndRIDListArgs(compositorSetCompositorEffectsBind, singleton, compositor, effects)
    }

    /**
     * Creates an environment and adds it to the RenderingServer. It can be accessed with the RID that
     * is returned. This RID will be used in all `environment_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. Note: The equivalent resource is `Environment`.
     *
     * Generated from Godot docs: RenderingServer.environment_create
     */
    @JvmStatic
    fun environmentCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(environmentCreateBind, singleton)
    }

    /**
     * Sets the environment's background mode. Equivalent to `Environment.background_mode`.
     *
     * Generated from Godot docs: RenderingServer.environment_set_background
     */
    @JvmStatic
    fun environmentSetBackground(env: RID, bg: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(environmentSetBackgroundBind, singleton, env, bg)
    }

    /**
     * Sets the camera ID to be used as environment background.
     *
     * Generated from Godot docs: RenderingServer.environment_set_camera_id
     */
    @JvmStatic
    fun environmentSetCameraId(env: RID, id: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(environmentSetCameraIdBind, singleton, env, id)
    }

    /**
     * Sets the `Sky` to be used as the environment's background when using BGMode sky. Equivalent to
     * `Environment.sky`.
     *
     * Generated from Godot docs: RenderingServer.environment_set_sky
     */
    @JvmStatic
    fun environmentSetSky(env: RID, sky: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(environmentSetSkyBind, singleton, env, sky)
    }

    /**
     * Sets a custom field of view for the background `Sky`. Equivalent to
     * `Environment.sky_custom_fov`.
     *
     * Generated from Godot docs: RenderingServer.environment_set_sky_custom_fov
     */
    @JvmStatic
    fun environmentSetSkyCustomFov(env: RID, scale: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(environmentSetSkyCustomFovBind, singleton, env, scale)
    }

    /**
     * Sets the rotation of the background `Sky` expressed as a `Basis`. Equivalent to
     * `Environment.sky_rotation`, where the rotation vector is used to construct the `Basis`.
     *
     * Generated from Godot docs: RenderingServer.environment_set_sky_orientation
     */
    @JvmStatic
    fun environmentSetSkyOrientation(env: RID, orientation: Basis) {
        ObjectCalls.ptrcallWithRIDAndBasisArg(environmentSetSkyOrientationBind, singleton, env, orientation)
    }

    /**
     * Color displayed for clear areas of the scene. Only effective if using the `ENV_BG_COLOR`
     * background mode.
     *
     * Generated from Godot docs: RenderingServer.environment_set_bg_color
     */
    @JvmStatic
    fun environmentSetBgColor(env: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(environmentSetBgColorBind, singleton, env, color)
    }

    /**
     * Sets the intensity of the background color.
     *
     * Generated from Godot docs: RenderingServer.environment_set_bg_energy
     */
    @JvmStatic
    fun environmentSetBgEnergy(env: RID, multiplier: Double, exposureValue: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(environmentSetBgEnergyBind, singleton, env, multiplier, exposureValue)
    }

    /**
     * Sets the maximum layer to use if using Canvas background mode.
     *
     * Generated from Godot docs: RenderingServer.environment_set_canvas_max_layer
     */
    @JvmStatic
    fun environmentSetCanvasMaxLayer(env: RID, maxLayer: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(environmentSetCanvasMaxLayerBind, singleton, env, maxLayer)
    }

    /**
     * Sets the values to be used for ambient light rendering. See `Environment` for more details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_ambient_light
     */
    @JvmStatic
    fun environmentSetAmbientLight(env: RID, color: Color, ambient: Long = 0L, energy: Double = 1.0, skyContribution: Double = 0.0, reflectionSource: Long = 0L) {
        ObjectCalls.ptrcallWithRIDColorLongTwoDoubleLongArgs(environmentSetAmbientLightBind, singleton, env, color, ambient, energy, skyContribution, reflectionSource)
    }

    /**
     * Configures glow for the specified environment RID. See `glow_*` properties in `Environment` for
     * more information.
     *
     * Generated from Godot docs: RenderingServer.environment_set_glow
     */
    @JvmStatic
    fun environmentSetGlow(env: RID, enable: Boolean, levels: List<Float>, intensity: Double, strength: Double, mix: Double, bloomThreshold: Double, blendMode: Long, hdrBleedThreshold: Double, hdrBleedScale: Double, hdrLuminanceCap: Double, glowMapStrength: Double, glowMap: RID) {
        ObjectCalls.ptrcallWithRIDBoolPackedFloat32ListFourDoubleLongFourDoubleRIDArgs(environmentSetGlowBind, singleton, env, enable, levels, intensity, strength, mix, bloomThreshold, blendMode, hdrBleedThreshold, hdrBleedScale, hdrLuminanceCap, glowMapStrength, glowMap)
    }

    /**
     * Sets the variables to be used with the "tonemap" post-process effect. See `Environment` for more
     * details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_tonemap
     */
    @JvmStatic
    fun environmentSetTonemap(env: RID, toneMapper: Long, exposure: Double, white: Double) {
        ObjectCalls.ptrcallWithRIDLongTwoDoubleArgs(environmentSetTonemapBind, singleton, env, toneMapper, exposure, white)
    }

    /**
     * See `Environment.tonemap_agx_contrast` for more details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_tonemap_agx_contrast
     */
    @JvmStatic
    fun environmentSetTonemapAgxContrast(env: RID, agxContrast: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(environmentSetTonemapAgxContrastBind, singleton, env, agxContrast)
    }

    /**
     * Sets the values to be used with the "adjustments" post-process effect. See `Environment` for
     * more details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_adjustment
     */
    @JvmStatic
    fun environmentSetAdjustment(env: RID, enable: Boolean, brightness: Double, contrast: Double, saturation: Double, use1dColorCorrection: Boolean, colorCorrection: RID) {
        ObjectCalls.ptrcallWithRIDBoolThreeDoubleBoolRIDArgs(environmentSetAdjustmentBind, singleton, env, enable, brightness, contrast, saturation, use1dColorCorrection, colorCorrection)
    }

    /**
     * Sets the variables to be used with the screen-space reflections (SSR) post-process effect. See
     * `Environment` for more details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_ssr
     */
    @JvmStatic
    fun environmentSetSsr(env: RID, enable: Boolean, maxSteps: Int, fadeIn: Double, fadeOut: Double, depthTolerance: Double) {
        ObjectCalls.ptrcallWithRIDBoolIntThreeDoubleArgs(environmentSetSsrBind, singleton, env, enable, maxSteps, fadeIn, fadeOut, depthTolerance)
    }

    /**
     * Sets the variables to be used with the screen-space ambient occlusion (SSAO) post-process
     * effect. See `Environment` for more details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_ssao
     */
    @JvmStatic
    fun environmentSetSsao(env: RID, enable: Boolean, radius: Double, intensity: Double, power: Double, detail: Double, horizon: Double, sharpness: Double, lightAffect: Double, aoChannelAffect: Double) {
        ObjectCalls.ptrcallWithRIDBoolDoubleDoubleDoubleDoubleDoubleDoubleDoubleDoubleArgs(environmentSetSsaoBind, singleton, env, enable, radius, intensity, power, detail, horizon, sharpness, lightAffect, aoChannelAffect)
    }

    /**
     * Configures fog for the specified environment RID. See `fog_*` properties in `Environment` for
     * more information.
     *
     * Generated from Godot docs: RenderingServer.environment_set_fog
     */
    @JvmStatic
    fun environmentSetFog(env: RID, enable: Boolean, lightColor: Color, lightEnergy: Double, sunScatter: Double, density: Double, height: Double, heightDensity: Double, aerialPerspective: Double, skyAffect: Double, fogMode: Long = 0L) {
        ObjectCalls.ptrcallWithRIDBoolColorDoubleDoubleDoubleDoubleDoubleDoubleDoubleLongArgs(environmentSetFogBind, singleton, env, enable, lightColor, lightEnergy, sunScatter, density, height, heightDensity, aerialPerspective, skyAffect, fogMode)
    }

    /**
     * Configures fog depth for the specified environment RID. Only has an effect when the fog mode of
     * the environment is `ENV_FOG_MODE_DEPTH`. See `fog_depth_*` properties in `Environment` for more
     * information.
     *
     * Generated from Godot docs: RenderingServer.environment_set_fog_depth
     */
    @JvmStatic
    fun environmentSetFogDepth(env: RID, curve: Double, begin: Double, end: Double) {
        ObjectCalls.ptrcallWithRIDAndThreeDoubleArgs(environmentSetFogDepthBind, singleton, env, curve, begin, end)
    }

    /**
     * Configures signed distance field global illumination for the specified environment RID. See
     * `sdfgi_*` properties in `Environment` for more information.
     *
     * Generated from Godot docs: RenderingServer.environment_set_sdfgi
     */
    @JvmStatic
    fun environmentSetSdfgi(env: RID, enable: Boolean, cascades: Int, minCellSize: Double, yScale: Long, useOcclusion: Boolean, bounceFeedback: Double, readSky: Boolean, energy: Double, normalBias: Double, probeBias: Double) {
        ObjectCalls.ptrcallWithRIDBoolIntDoubleLongBoolDoubleBoolThreeDoubleArgs(environmentSetSdfgiBind, singleton, env, enable, cascades, minCellSize, yScale, useOcclusion, bounceFeedback, readSky, energy, normalBias, probeBias)
    }

    /**
     * Sets the variables to be used with the volumetric fog post-process effect. See `Environment` for
     * more details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_volumetric_fog
     */
    @JvmStatic
    fun environmentSetVolumetricFog(env: RID, enable: Boolean, density: Double, albedo: Color, emission: Color, emissionEnergy: Double, anisotropy: Double, length: Double, detailSpread: Double, giInject: Double, temporalReprojection: Boolean, temporalReprojectionAmount: Double, ambientInject: Double, skyAffect: Double) {
        ObjectCalls.ptrcallWithRIDBoolDoubleTwoColorDoubleDoubleDoubleDoubleDoubleBoolThreeDoubleArgs(environmentSetVolumetricFogBind, singleton, env, enable, density, albedo, emission, emissionEnergy, anisotropy, length, detailSpread, giInject, temporalReprojection, temporalReprojectionAmount, ambientInject, skyAffect)
    }

    /**
     * If `enable` is `true`, enables bicubic upscaling for glow which improves quality at the cost of
     * performance. Equivalent to `ProjectSettings.rendering/environment/glow/upscale_mode`. Note: This
     * setting is only effective when using the Forward+ or Mobile rendering methods, as Compatibility
     * uses a different glow implementation.
     *
     * Generated from Godot docs: RenderingServer.environment_glow_set_use_bicubic_upscale
     */
    @JvmStatic
    fun environmentGlowSetUseBicubicUpscale(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(environmentGlowSetUseBicubicUpscaleBind, singleton, enable)
    }

    /**
     * Sets whether screen-space reflections will be rendered at full or half size. Half size is
     * faster, but may look pixelated or cause flickering.
     *
     * Generated from Godot docs: RenderingServer.environment_set_ssr_half_size
     */
    @JvmStatic
    fun environmentSetSsrHalfSize(halfSize: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(environmentSetSsrHalfSizeBind, singleton, halfSize)
    }

    @JvmStatic
    fun environmentSetSsrRoughnessQuality(quality: Long) {
        ObjectCalls.ptrcallWithLongArg(environmentSetSsrRoughnessQualityBind, singleton, quality)
    }

    /**
     * Sets the quality level of the screen-space ambient occlusion (SSAO) post-process effect. See
     * `Environment` for more details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_ssao_quality
     */
    @JvmStatic
    fun environmentSetSsaoQuality(quality: Long, halfSize: Boolean, adaptiveTarget: Double, blurPasses: Int, fadeoutFrom: Double, fadeoutTo: Double) {
        ObjectCalls.ptrcallWithLongBoolDoubleIntAndTwoDoubleArgs(environmentSetSsaoQualityBind, singleton, quality, halfSize, adaptiveTarget, blurPasses, fadeoutFrom, fadeoutTo)
    }

    /**
     * Sets the quality level of the screen-space indirect lighting (SSIL) post-process effect. See
     * `Environment` for more details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_ssil_quality
     */
    @JvmStatic
    fun environmentSetSsilQuality(quality: Long, halfSize: Boolean, adaptiveTarget: Double, blurPasses: Int, fadeoutFrom: Double, fadeoutTo: Double) {
        ObjectCalls.ptrcallWithLongBoolDoubleIntAndTwoDoubleArgs(environmentSetSsilQualityBind, singleton, quality, halfSize, adaptiveTarget, blurPasses, fadeoutFrom, fadeoutTo)
    }

    /**
     * Sets the number of rays to throw per frame when computing signed distance field global
     * illumination. Equivalent to
     * `ProjectSettings.rendering/global_illumination/sdfgi/probe_ray_count`.
     *
     * Generated from Godot docs: RenderingServer.environment_set_sdfgi_ray_count
     */
    @JvmStatic
    fun environmentSetSdfgiRayCount(rayCount: Long) {
        ObjectCalls.ptrcallWithLongArg(environmentSetSdfgiRayCountBind, singleton, rayCount)
    }

    /**
     * Sets the number of frames to use for converging signed distance field global illumination.
     * Equivalent to `ProjectSettings.rendering/global_illumination/sdfgi/frames_to_converge`.
     *
     * Generated from Godot docs: RenderingServer.environment_set_sdfgi_frames_to_converge
     */
    @JvmStatic
    fun environmentSetSdfgiFramesToConverge(frames: Long) {
        ObjectCalls.ptrcallWithLongArg(environmentSetSdfgiFramesToConvergeBind, singleton, frames)
    }

    /**
     * Sets the update speed for dynamic lights' indirect lighting when computing signed distance field
     * global illumination. Equivalent to
     * `ProjectSettings.rendering/global_illumination/sdfgi/frames_to_update_lights`.
     *
     * Generated from Godot docs: RenderingServer.environment_set_sdfgi_frames_to_update_light
     */
    @JvmStatic
    fun environmentSetSdfgiFramesToUpdateLight(frames: Long) {
        ObjectCalls.ptrcallWithLongArg(environmentSetSdfgiFramesToUpdateLightBind, singleton, frames)
    }

    /**
     * Sets the resolution of the volumetric fog's froxel buffer. `size` is modified by the screen's
     * aspect ratio and then used to set the width and height of the buffer. While `depth` is directly
     * used to set the depth of the buffer.
     *
     * Generated from Godot docs: RenderingServer.environment_set_volumetric_fog_volume_size
     */
    @JvmStatic
    fun environmentSetVolumetricFogVolumeSize(size: Int, depth: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(environmentSetVolumetricFogVolumeSizeBind, singleton, size, depth)
    }

    /**
     * Enables filtering of the volumetric fog scattering buffer. This results in much smoother volumes
     * with very few under-sampling artifacts.
     *
     * Generated from Godot docs: RenderingServer.environment_set_volumetric_fog_filter_active
     */
    @JvmStatic
    fun environmentSetVolumetricFogFilterActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(environmentSetVolumetricFogFilterActiveBind, singleton, active)
    }

    /**
     * Generates and returns an `Image` containing the radiance map for the specified `environment`
     * RID's sky. This supports built-in sky material and custom sky shaders. If `bake_irradiance` is
     * `true`, the irradiance map is saved instead of the radiance map. The radiance map is used to
     * render reflected light, while the irradiance map is used to render ambient light. See also
     * `sky_bake_panorama`. Note: The image is saved using linear encoding without any tonemapping
     * performed, which means it will look too dark if viewed directly in an image editor. Note: `size`
     * should be a 2:1 aspect ratio for the generated panorama to have square pixels. For radiance
     * maps, there is no point in using a height greater than `Sky.radiance_size`, as it won't increase
     * detail. Irradiance maps only contain low-frequency data, so there is usually no point in going
     * past a size of 128×64 pixels when saving an irradiance map.
     *
     * Generated from Godot docs: RenderingServer.environment_bake_panorama
     */
    @JvmStatic
    fun environmentBakePanorama(environment: RID, bakeIrradiance: Boolean, size: Vector2i): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithRIDBoolVector2iArgsRetObject(environmentBakePanoramaBind, singleton, environment, bakeIrradiance, size))
    }

    /**
     * Sets the screen-space roughness limiter parameters, such as whether it should be enabled and its
     * thresholds. Equivalent to
     * `ProjectSettings.rendering/anti_aliasing/screen_space_roughness_limiter/enabled`,
     * `ProjectSettings.rendering/anti_aliasing/screen_space_roughness_limiter/amount` and
     * `ProjectSettings.rendering/anti_aliasing/screen_space_roughness_limiter/limit`.
     *
     * Generated from Godot docs: RenderingServer.screen_space_roughness_limiter_set_active
     */
    @JvmStatic
    fun screenSpaceRoughnessLimiterSetActive(enable: Boolean, amount: Double, limit: Double) {
        ObjectCalls.ptrcallWithBoolTwoDoubleArgs(screenSpaceRoughnessLimiterSetActiveBind, singleton, enable, amount, limit)
    }

    /**
     * Sets `ProjectSettings.rendering/environment/subsurface_scattering/subsurface_scattering_quality`
     * to use when rendering materials that have subsurface scattering enabled.
     *
     * Generated from Godot docs: RenderingServer.sub_surface_scattering_set_quality
     */
    @JvmStatic
    fun subSurfaceScatteringSetQuality(quality: Long) {
        ObjectCalls.ptrcallWithLongArg(subSurfaceScatteringSetQualityBind, singleton, quality)
    }

    /**
     * Sets the
     * `ProjectSettings.rendering/environment/subsurface_scattering/subsurface_scattering_scale` and
     * `ProjectSettings.rendering/environment/subsurface_scattering/subsurface_scattering_depth_scale`
     * to use when rendering materials that have subsurface scattering enabled.
     *
     * Generated from Godot docs: RenderingServer.sub_surface_scattering_set_scale
     */
    @JvmStatic
    fun subSurfaceScatteringSetScale(scale: Double, depthScale: Double) {
        ObjectCalls.ptrcallWithTwoDoubleArgs(subSurfaceScatteringSetScaleBind, singleton, scale, depthScale)
    }

    /**
     * Creates a camera attributes object and adds it to the RenderingServer. It can be accessed with
     * the RID that is returned. This RID will be used in all `camera_attributes_` RenderingServer
     * functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. Note: The equivalent resource is `CameraAttributes`.
     *
     * Generated from Godot docs: RenderingServer.camera_attributes_create
     */
    @JvmStatic
    fun cameraAttributesCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(cameraAttributesCreateBind, singleton)
    }

    /**
     * Sets the quality level of the DOF blur effect to `quality`. `use_jitter` can be used to jitter
     * samples taken during the blur pass to hide artifacts at the cost of looking more fuzzy.
     *
     * Generated from Godot docs: RenderingServer.camera_attributes_set_dof_blur_quality
     */
    @JvmStatic
    fun cameraAttributesSetDofBlurQuality(quality: Long, useJitter: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(cameraAttributesSetDofBlurQualityBind, singleton, quality, useJitter)
    }

    /**
     * Sets the shape of the DOF bokeh pattern to `shape`. Different shapes may be used to achieve
     * artistic effect, or to meet performance targets.
     *
     * Generated from Godot docs: RenderingServer.camera_attributes_set_dof_blur_bokeh_shape
     */
    @JvmStatic
    fun cameraAttributesSetDofBlurBokehShape(shape: Long) {
        ObjectCalls.ptrcallWithLongArg(cameraAttributesSetDofBlurBokehShapeBind, singleton, shape)
    }

    /**
     * Sets the parameters to use with the DOF blur effect. These parameters take on the same meaning
     * as their counterparts in `CameraAttributesPractical`.
     *
     * Generated from Godot docs: RenderingServer.camera_attributes_set_dof_blur
     */
    @JvmStatic
    fun cameraAttributesSetDofBlur(cameraAttributes: RID, farEnable: Boolean, farDistance: Double, farTransition: Double, nearEnable: Boolean, nearDistance: Double, nearTransition: Double, amount: Double) {
        ObjectCalls.ptrcallWithRIDBoolTwoDoubleBoolThreeDoubleArgs(cameraAttributesSetDofBlurBind, singleton, cameraAttributes, farEnable, farDistance, farTransition, nearEnable, nearDistance, nearTransition, amount)
    }

    /**
     * Sets the exposure values that will be used by the renderers. The normalization amount is used to
     * bake a given Exposure Value (EV) into rendering calculations to reduce the dynamic range of the
     * scene. The normalization factor can be calculated from exposure value (EV100) as follows:
     *
     * Generated from Godot docs: RenderingServer.camera_attributes_set_exposure
     */
    @JvmStatic
    fun cameraAttributesSetExposure(cameraAttributes: RID, multiplier: Double, normalization: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(cameraAttributesSetExposureBind, singleton, cameraAttributes, multiplier, normalization)
    }

    /**
     * Sets the parameters to use with the auto-exposure effect. These parameters take on the same
     * meaning as their counterparts in `CameraAttributes` and `CameraAttributesPractical`.
     *
     * Generated from Godot docs: RenderingServer.camera_attributes_set_auto_exposure
     */
    @JvmStatic
    fun cameraAttributesSetAutoExposure(cameraAttributes: RID, enable: Boolean, minSensitivity: Double, maxSensitivity: Double, speed: Double, scale: Double) {
        ObjectCalls.ptrcallWithRIDBoolFourDoubleArgs(cameraAttributesSetAutoExposureBind, singleton, cameraAttributes, enable, minSensitivity, maxSensitivity, speed, scale)
    }

    /**
     * Creates a scenario and adds it to the RenderingServer. It can be accessed with the RID that is
     * returned. This RID will be used in all `scenario_*` RenderingServer functions. Once finished
     * with your RID, you will want to free the RID using the RenderingServer's `free_rid` method. The
     * scenario is the 3D world that all the visual instances exist in.
     *
     * Generated from Godot docs: RenderingServer.scenario_create
     */
    @JvmStatic
    fun scenarioCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(scenarioCreateBind, singleton)
    }

    /**
     * Sets the environment that will be used with this scenario. See also `Environment`.
     *
     * Generated from Godot docs: RenderingServer.scenario_set_environment
     */
    @JvmStatic
    fun scenarioSetEnvironment(scenario: RID, environment: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(scenarioSetEnvironmentBind, singleton, scenario, environment)
    }

    /**
     * Sets the fallback environment to be used by this scenario. The fallback environment is used if
     * no environment is set. Internally, this is used by the editor to provide a default environment.
     *
     * Generated from Godot docs: RenderingServer.scenario_set_fallback_environment
     */
    @JvmStatic
    fun scenarioSetFallbackEnvironment(scenario: RID, environment: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(scenarioSetFallbackEnvironmentBind, singleton, scenario, environment)
    }

    /**
     * Sets the camera attributes (`effects`) that will be used with this scenario. See also
     * `CameraAttributes`.
     *
     * Generated from Godot docs: RenderingServer.scenario_set_camera_attributes
     */
    @JvmStatic
    fun scenarioSetCameraAttributes(scenario: RID, effects: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(scenarioSetCameraAttributesBind, singleton, scenario, effects)
    }

    /**
     * Sets the compositor (`compositor`) that will be used with this scenario. See also `Compositor`.
     *
     * Generated from Godot docs: RenderingServer.scenario_set_compositor
     */
    @JvmStatic
    fun scenarioSetCompositor(scenario: RID, compositor: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(scenarioSetCompositorBind, singleton, scenario, compositor)
    }

    /**
     * Creates a visual instance, adds it to the RenderingServer, and sets both base and scenario. It
     * can be accessed with the RID that is returned. This RID will be used in all `instance_*`
     * RenderingServer functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. This is a shorthand for using `instance_create` and setting
     * the base and scenario manually.
     *
     * Generated from Godot docs: RenderingServer.instance_create2
     */
    @JvmStatic
    fun instanceCreate2(base: RID, scenario: RID): RID {
        return ObjectCalls.ptrcallWithTwoRIDArgsRetRID(instanceCreate2Bind, singleton, base, scenario)
    }

    /**
     * Creates a visual instance and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID will be used in all `instance_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. An instance is a way of placing a 3D object in the scenario. Objects like particles,
     * meshes, reflection probes and decals need to be associated with an instance to be visible in the
     * scenario using `instance_set_base`. Note: The equivalent node is `VisualInstance3D`.
     *
     * Generated from Godot docs: RenderingServer.instance_create
     */
    @JvmStatic
    fun instanceCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(instanceCreateBind, singleton)
    }

    /**
     * Sets the base of the instance. A base can be any of the 3D objects that are created in the
     * RenderingServer that can be displayed. For example, any of the light types, mesh, multimesh,
     * particle system, reflection probe, decal, lightmap, voxel GI and visibility notifiers are all
     * types that can be set as the base of an instance in order to be displayed in the scenario.
     *
     * Generated from Godot docs: RenderingServer.instance_set_base
     */
    @JvmStatic
    fun instanceSetBase(instance: RID, base: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceSetBaseBind, singleton, instance, base)
    }

    /**
     * Sets the scenario that the instance is in. The scenario is the 3D world that the objects will be
     * displayed in.
     *
     * Generated from Godot docs: RenderingServer.instance_set_scenario
     */
    @JvmStatic
    fun instanceSetScenario(instance: RID, scenario: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceSetScenarioBind, singleton, instance, scenario)
    }

    /**
     * Sets the render layers that this instance will be drawn to. Equivalent to
     * `VisualInstance3D.layers`.
     *
     * Generated from Godot docs: RenderingServer.instance_set_layer_mask
     */
    @JvmStatic
    fun instanceSetLayerMask(instance: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(instanceSetLayerMaskBind, singleton, instance, mask)
    }

    /**
     * Sets the sorting offset and switches between using the bounding box or instance origin for depth
     * sorting.
     *
     * Generated from Godot docs: RenderingServer.instance_set_pivot_data
     */
    @JvmStatic
    fun instanceSetPivotData(instance: RID, sortingOffset: Double, useAabbCenter: Boolean) {
        ObjectCalls.ptrcallWithRIDDoubleBoolArgs(instanceSetPivotDataBind, singleton, instance, sortingOffset, useAabbCenter)
    }

    /**
     * Sets the world space transform of the instance. Equivalent to `Node3D.global_transform`.
     *
     * Generated from Godot docs: RenderingServer.instance_set_transform
     */
    @JvmStatic
    fun instanceSetTransform(instance: RID, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDAndTransform3DArg(instanceSetTransformBind, singleton, instance, transform)
    }

    /**
     * Attaches a unique Object ID to instance. Object ID must be attached to instance for proper
     * culling with `instances_cull_aabb`, `instances_cull_convex`, and `instances_cull_ray`.
     *
     * Generated from Godot docs: RenderingServer.instance_attach_object_instance_id
     */
    @JvmStatic
    fun instanceAttachObjectInstanceId(instance: RID, id: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(instanceAttachObjectInstanceIdBind, singleton, instance, id)
    }

    /**
     * Sets the weight for a given blend shape associated with this instance.
     *
     * Generated from Godot docs: RenderingServer.instance_set_blend_shape_weight
     */
    @JvmStatic
    fun instanceSetBlendShapeWeight(instance: RID, shape: Int, weight: Double) {
        ObjectCalls.ptrcallWithRIDIntDoubleArgs(instanceSetBlendShapeWeightBind, singleton, instance, shape, weight)
    }

    /**
     * Sets the override material of a specific surface. Equivalent to
     * `MeshInstance3D.set_surface_override_material`.
     *
     * Generated from Godot docs: RenderingServer.instance_set_surface_override_material
     */
    @JvmStatic
    fun instanceSetSurfaceOverrideMaterial(instance: RID, surface: Int, material: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(instanceSetSurfaceOverrideMaterialBind, singleton, instance, surface, material)
    }

    /**
     * Sets whether an instance is drawn or not. Equivalent to `Node3D.visible`.
     *
     * Generated from Godot docs: RenderingServer.instance_set_visible
     */
    @JvmStatic
    fun instanceSetVisible(instance: RID, visible: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(instanceSetVisibleBind, singleton, instance, visible)
    }

    /**
     * Sets the transparency for the given geometry instance. Equivalent to
     * `GeometryInstance3D.transparency`. A transparency of `0.0` is fully opaque, while `1.0` is fully
     * transparent. Values greater than `0.0` (exclusive) will force the geometry's materials to go
     * through the transparent pipeline, which is slower to render and can exhibit rendering issues due
     * to incorrect transparency sorting. However, unlike using a transparent material, setting
     * `transparency` to a value greater than `0.0` (exclusive) will not disable shadow rendering. In
     * spatial shaders, `1.0 - transparency` is set as the default value of the `ALPHA` built-in. Note:
     * `transparency` is clamped between `0.0` and `1.0`, so this property cannot be used to make
     * transparent materials more opaque than they originally are.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_transparency
     */
    @JvmStatic
    fun instanceGeometrySetTransparency(instance: RID, transparency: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(instanceGeometrySetTransparencyBind, singleton, instance, transparency)
    }

    /**
     * Resets motion vectors and other interpolated values. Use this after teleporting a mesh from one
     * position to another to avoid ghosting artifacts.
     *
     * Generated from Godot docs: RenderingServer.instance_teleport
     */
    @JvmStatic
    fun instanceTeleport(instance: RID) {
        ObjectCalls.ptrcallWithRIDArg(instanceTeleportBind, singleton, instance)
    }

    /**
     * Sets a custom AABB to use when culling objects from the view frustum. Equivalent to setting
     * `GeometryInstance3D.custom_aabb`.
     *
     * Generated from Godot docs: RenderingServer.instance_set_custom_aabb
     */
    @JvmStatic
    fun instanceSetCustomAabb(instance: RID, aabb: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(instanceSetCustomAabbBind, singleton, instance, aabb)
    }

    /**
     * Attaches a skeleton to an instance. Removes the previous skeleton from the instance.
     *
     * Generated from Godot docs: RenderingServer.instance_attach_skeleton
     */
    @JvmStatic
    fun instanceAttachSkeleton(instance: RID, skeleton: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceAttachSkeletonBind, singleton, instance, skeleton)
    }

    /**
     * Sets a margin to increase the size of the AABB when culling objects from the view frustum. This
     * allows you to avoid culling objects that fall outside the view frustum. Equivalent to
     * `GeometryInstance3D.extra_cull_margin`.
     *
     * Generated from Godot docs: RenderingServer.instance_set_extra_visibility_margin
     */
    @JvmStatic
    fun instanceSetExtraVisibilityMargin(instance: RID, margin: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(instanceSetExtraVisibilityMarginBind, singleton, instance, margin)
    }

    /**
     * Sets the visibility parent for the given instance. Equivalent to `Node3D.visibility_parent`.
     *
     * Generated from Godot docs: RenderingServer.instance_set_visibility_parent
     */
    @JvmStatic
    fun instanceSetVisibilityParent(instance: RID, parent: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceSetVisibilityParentBind, singleton, instance, parent)
    }

    /**
     * If `true`, ignores all culling on the specified 3D geometry instance, including frustum culling,
     * occlusion culling, and layer culling. This is not the same as
     * `GeometryInstance3D.ignore_occlusion_culling`, which only ignores occlusion culling but leaves
     * frustum and layer culling intact.
     *
     * Generated from Godot docs: RenderingServer.instance_set_ignore_culling
     */
    @JvmStatic
    fun instanceSetIgnoreCulling(instance: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(instanceSetIgnoreCullingBind, singleton, instance, enabled)
    }

    /**
     * Sets the `flag` for a given `instance` to `enabled`.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_flag
     */
    @JvmStatic
    fun instanceGeometrySetFlag(instance: RID, flag: Long, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDLongAndBoolArgs(instanceGeometrySetFlagBind, singleton, instance, flag, enabled)
    }

    /**
     * Sets the shadow casting setting. Equivalent to `GeometryInstance3D.cast_shadow`.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_cast_shadows_setting
     */
    @JvmStatic
    fun instanceGeometrySetCastShadowsSetting(instance: RID, shadowCastingSetting: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(instanceGeometrySetCastShadowsSettingBind, singleton, instance, shadowCastingSetting)
    }

    /**
     * Sets a material that will override the material for all surfaces on the mesh associated with
     * this instance. Equivalent to `GeometryInstance3D.material_override`.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_material_override
     */
    @JvmStatic
    fun instanceGeometrySetMaterialOverride(instance: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceGeometrySetMaterialOverrideBind, singleton, instance, material)
    }

    /**
     * Sets a material that will be rendered for all surfaces on top of active materials for the mesh
     * associated with this instance. Equivalent to `GeometryInstance3D.material_overlay`.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_material_overlay
     */
    @JvmStatic
    fun instanceGeometrySetMaterialOverlay(instance: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceGeometrySetMaterialOverlayBind, singleton, instance, material)
    }

    /**
     * Sets the visibility range values for the given geometry instance. Equivalent to
     * `GeometryInstance3D.visibility_range_begin` and related properties.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_visibility_range
     */
    @JvmStatic
    fun instanceGeometrySetVisibilityRange(instance: RID, min: Double, max: Double, minMargin: Double, maxMargin: Double, fadeMode: Long) {
        ObjectCalls.ptrcallWithRIDFourDoubleLongArgs(instanceGeometrySetVisibilityRangeBind, singleton, instance, min, max, minMargin, maxMargin, fadeMode)
    }

    /**
     * Sets the lightmap GI instance to use for the specified 3D geometry instance. The lightmap UV
     * scale for the specified instance (equivalent to `GeometryInstance3D.gi_lightmap_scale`) and
     * lightmap atlas slice must also be specified.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_lightmap
     */
    @JvmStatic
    fun instanceGeometrySetLightmap(instance: RID, lightmap: RID, lightmapUvScale: Rect2, lightmapSlice: Int) {
        ObjectCalls.ptrcallWithTwoRIDRect2IntArgs(instanceGeometrySetLightmapBind, singleton, instance, lightmap, lightmapUvScale, lightmapSlice)
    }

    /**
     * Sets the level of detail bias to use when rendering the specified 3D geometry instance. Higher
     * values result in higher detail from further away. Equivalent to `GeometryInstance3D.lod_bias`.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_lod_bias
     */
    @JvmStatic
    fun instanceGeometrySetLodBias(instance: RID, lodBias: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(instanceGeometrySetLodBiasBind, singleton, instance, lodBias)
    }

    /**
     * Sets the per-instance shader uniform on the specified 3D geometry instance. Equivalent to
     * `GeometryInstance3D.set_instance_shader_parameter`.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_shader_parameter
     */
    @JvmStatic
    fun instanceGeometrySetShaderParameter(instance: RID, parameter: String, value: Any?) {
        ObjectCalls.ptrcallWithRIDStringNameAndVariantArgs(instanceGeometrySetShaderParameterBind, singleton, instance, parameter, value)
    }

    /**
     * Returns the value of the per-instance shader uniform from the specified 3D geometry instance.
     * Equivalent to `GeometryInstance3D.get_instance_shader_parameter`. Note: Per-instance shader
     * parameter names are case-sensitive.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_get_shader_parameter
     */
    @JvmStatic
    fun instanceGeometryGetShaderParameter(instance: RID, parameter: String): Any? {
        return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(instanceGeometryGetShaderParameterBind, singleton, instance, parameter)
    }

    /**
     * Returns the default value of the per-instance shader uniform from the specified 3D geometry
     * instance. Equivalent to `GeometryInstance3D.get_instance_shader_parameter`.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_get_shader_parameter_default_value
     */
    @JvmStatic
    fun instanceGeometryGetShaderParameterDefaultValue(instance: RID, parameter: String): Any? {
        return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(instanceGeometryGetShaderParameterDefaultValueBind, singleton, instance, parameter)
    }

    /**
     * Returns a dictionary of per-instance shader uniform names of the per-instance shader uniform
     * from the specified 3D geometry instance. The returned dictionary is in PropertyInfo format, with
     * the keys `name`, `class_name`, `type`, `hint`, `hint_string` and `usage`. Equivalent to
     * `GeometryInstance3D.get_instance_shader_parameter`.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_get_shader_parameter_list
     */
    @JvmStatic
    fun instanceGeometryGetShaderParameterList(instance: RID): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(instanceGeometryGetShaderParameterListBind, singleton, instance)
    }

    /**
     * Returns an array of object IDs intersecting with the provided AABB. Only 3D nodes that inherit
     * from `VisualInstance3D` are considered, such as `MeshInstance3D` or `DirectionalLight3D`. Use
     * `@GlobalScope.instance_from_id` to obtain the actual nodes. A scenario RID must be provided,
     * which is available in the `World3D` you want to query. This forces an update for all resources
     * queued to update. Warning: This function is primarily intended for editor usage. For in-game use
     * cases, prefer physics collision.
     *
     * Generated from Godot docs: RenderingServer.instances_cull_aabb
     */
    @JvmStatic
    fun instancesCullAabb(aabb: AABB, scenario: RID): List<Long> {
        return ObjectCalls.ptrcallWithAABBRIDArgsRetPackedInt64List(instancesCullAabbBind, singleton, aabb, scenario)
    }

    /**
     * Returns an array of object IDs intersecting with the provided 3D ray. Only 3D nodes that inherit
     * from `VisualInstance3D` are considered, such as `MeshInstance3D` or `DirectionalLight3D`. Use
     * `@GlobalScope.instance_from_id` to obtain the actual nodes. A scenario RID must be provided,
     * which is available in the `World3D` you want to query. This forces an update for all resources
     * queued to update. Warning: This function is primarily intended for editor usage. For in-game use
     * cases, prefer physics collision.
     *
     * Generated from Godot docs: RenderingServer.instances_cull_ray
     */
    @JvmStatic
    fun instancesCullRay(from: Vector3, to: Vector3, scenario: RID): List<Long> {
        return ObjectCalls.ptrcallWithTwoVector3RIDArgsRetPackedInt64List(instancesCullRayBind, singleton, from, to, scenario)
    }

    /**
     * Returns an array of object IDs intersecting with the provided convex shape. Only 3D nodes that
     * inherit from `VisualInstance3D` are considered, such as `MeshInstance3D` or
     * `DirectionalLight3D`. Use `@GlobalScope.instance_from_id` to obtain the actual nodes. A scenario
     * RID must be provided, which is available in the `World3D` you want to query. This forces an
     * update for all resources queued to update. Warning: This function is primarily intended for
     * editor usage. For in-game use cases, prefer physics collision.
     *
     * Generated from Godot docs: RenderingServer.instances_cull_convex
     */
    @JvmStatic
    fun instancesCullConvex(convex: List<Plane>, scenario: RID): List<Long> {
        return ObjectCalls.ptrcallWithPlaneListAndRIDArgsRetPackedInt64List(instancesCullConvexBind, singleton, convex, scenario)
    }

    /**
     * Bakes the material data of the Mesh passed in the `base` parameter with optional
     * `material_overrides` to a set of `Image`s of size `image_size`. Returns an array of `Image`s
     * containing material properties as specified in `BakeChannels`.
     *
     * Generated from Godot docs: RenderingServer.bake_render_uv2
     */
    @JvmStatic
    fun bakeRenderUv2(base: RID, materialOverrides: List<RID>, imageSize: Vector2i): List<Image> {
        return ObjectCalls.ptrcallWithRIDRIDListVector2iArgsRetTypedObjectList(bakeRenderUv2Bind, singleton, base, materialOverrides, imageSize, Image::fromHandle)
    }

    /**
     * Creates a canvas and returns the assigned `RID`. It can be accessed with the RID that is
     * returned. This RID will be used in all `canvas_*` RenderingServer functions. Once finished with
     * your RID, you will want to free the RID using the RenderingServer's `free_rid` method. Canvas
     * has no `Resource` or `Node` equivalent.
     *
     * Generated from Godot docs: RenderingServer.canvas_create
     */
    @JvmStatic
    fun canvasCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasCreateBind, singleton)
    }

    /**
     * A copy of the canvas item will be drawn with a local offset of the `mirroring`. Note: This is
     * equivalent to calling `canvas_set_item_repeat` like `canvas_set_item_repeat(item, mirroring,
     * 1)`, with an additional check ensuring `canvas` is a parent of `item`.
     *
     * Generated from Godot docs: RenderingServer.canvas_set_item_mirroring
     */
    @JvmStatic
    fun canvasSetItemMirroring(canvas: RID, item: RID, mirroring: Vector2) {
        ObjectCalls.ptrcallWithTwoRIDAndVector2Arg(canvasSetItemMirroringBind, singleton, canvas, item, mirroring)
    }

    /**
     * A copy of the canvas item will be drawn with a local offset of the `repeat_size` by the number
     * of times of the `repeat_times`. As the `repeat_times` increases, the copies will spread away
     * from the origin texture.
     *
     * Generated from Godot docs: RenderingServer.canvas_set_item_repeat
     */
    @JvmStatic
    fun canvasSetItemRepeat(item: RID, repeatSize: Vector2, repeatTimes: Int) {
        ObjectCalls.ptrcallWithRIDVector2IntArgs(canvasSetItemRepeatBind, singleton, item, repeatSize, repeatTimes)
    }

    /**
     * Modulates all colors in the given canvas.
     *
     * Generated from Godot docs: RenderingServer.canvas_set_modulate
     */
    @JvmStatic
    fun canvasSetModulate(canvas: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(canvasSetModulateBind, singleton, canvas, color)
    }

    /**
     * If `disable` is `true`, makes 2D rendering ignore the canvas scale defined for each canvas
     * layer. This affects `CanvasLayer`s with the `CanvasLayer.follow_viewport_enabled` property set
     * to `true`. In the editor, this is set to `true` by default, and set to `false` when View >
     * Preview Canvas Scale is enabled at the top of the 2D editor viewport. Note: Setting this to
     * `true` does not impact the behavior of `CanvasLayer.scale`, `Node2D.scale`, or `Control.scale`.
     *
     * Generated from Godot docs: RenderingServer.canvas_set_disable_scale
     */
    @JvmStatic
    fun canvasSetDisableScale(disable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(canvasSetDisableScaleBind, singleton, disable)
    }

    /**
     * Creates a canvas texture and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID will be used in all `canvas_texture_*` RenderingServer functions.
     * Once finished with your RID, you will want to free the RID using the RenderingServer's
     * `free_rid` method. See also `texture_2d_create`. Note: The equivalent resource is
     * `CanvasTexture` and is only meant to be used in 2D rendering, not 3D.
     *
     * Generated from Godot docs: RenderingServer.canvas_texture_create
     */
    @JvmStatic
    fun canvasTextureCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasTextureCreateBind, singleton)
    }

    /**
     * Sets the `channel`'s `texture` for the canvas texture specified by the `canvas_texture` RID.
     * Equivalent to `CanvasTexture.diffuse_texture`, `CanvasTexture.normal_texture` and
     * `CanvasTexture.specular_texture`.
     *
     * Generated from Godot docs: RenderingServer.canvas_texture_set_channel
     */
    @JvmStatic
    fun canvasTextureSetChannel(canvasTexture: RID, channel: Long, texture: RID) {
        ObjectCalls.ptrcallWithRIDLongAndRIDArgs(canvasTextureSetChannelBind, singleton, canvasTexture, channel, texture)
    }

    /**
     * Sets the `base_color` and `shininess` to use for the canvas texture specified by the
     * `canvas_texture` RID. Equivalent to `CanvasTexture.specular_color` and
     * `CanvasTexture.specular_shininess`.
     *
     * Generated from Godot docs: RenderingServer.canvas_texture_set_shading_parameters
     */
    @JvmStatic
    fun canvasTextureSetShadingParameters(canvasTexture: RID, baseColor: Color, shininess: Double) {
        ObjectCalls.ptrcallWithRIDColorDoubleArgs(canvasTextureSetShadingParametersBind, singleton, canvasTexture, baseColor, shininess)
    }

    /**
     * Sets the texture `filter` mode to use for the canvas texture specified by the `canvas_texture`
     * RID.
     *
     * Generated from Godot docs: RenderingServer.canvas_texture_set_texture_filter
     */
    @JvmStatic
    fun canvasTextureSetTextureFilter(canvasTexture: RID, filter: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasTextureSetTextureFilterBind, singleton, canvasTexture, filter)
    }

    /**
     * Sets the texture `repeat` mode to use for the canvas texture specified by the `canvas_texture`
     * RID.
     *
     * Generated from Godot docs: RenderingServer.canvas_texture_set_texture_repeat
     */
    @JvmStatic
    fun canvasTextureSetTextureRepeat(canvasTexture: RID, repeat: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasTextureSetTextureRepeatBind, singleton, canvasTexture, repeat)
    }

    /**
     * Creates a new CanvasItem instance and returns its `RID`. It can be accessed with the RID that is
     * returned. This RID will be used in all `canvas_item_*` RenderingServer functions. Once finished
     * with your RID, you will want to free the RID using the RenderingServer's `free_rid` method.
     * Note: The equivalent node is `CanvasItem`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_create
     */
    @JvmStatic
    fun canvasItemCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasItemCreateBind, singleton)
    }

    /**
     * Sets a parent `CanvasItem` to the `CanvasItem`. The item will inherit transform, modulation and
     * visibility from its parent, like `CanvasItem` nodes in the scene tree.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_parent
     */
    @JvmStatic
    fun canvasItemSetParent(item: RID, parent: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasItemSetParentBind, singleton, item, parent)
    }

    /**
     * Sets the default texture filter mode for the canvas item specified by the `item` RID. Equivalent
     * to `CanvasItem.texture_filter`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_default_texture_filter
     */
    @JvmStatic
    fun canvasItemSetDefaultTextureFilter(item: RID, filter: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasItemSetDefaultTextureFilterBind, singleton, item, filter)
    }

    /**
     * Sets the default texture repeat mode for the canvas item specified by the `item` RID. Equivalent
     * to `CanvasItem.texture_repeat`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_default_texture_repeat
     */
    @JvmStatic
    fun canvasItemSetDefaultTextureRepeat(item: RID, repeat: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasItemSetDefaultTextureRepeatBind, singleton, item, repeat)
    }

    /**
     * Sets the visibility of the `CanvasItem`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_visible
     */
    @JvmStatic
    fun canvasItemSetVisible(item: RID, visible: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetVisibleBind, singleton, item, visible)
    }

    /**
     * Sets the light `mask` for the canvas item specified by the `item` RID. Equivalent to
     * `CanvasItem.light_mask`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_light_mask
     */
    @JvmStatic
    fun canvasItemSetLightMask(item: RID, mask: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasItemSetLightMaskBind, singleton, item, mask)
    }

    /**
     * Sets the rendering visibility layer associated with this `CanvasItem`. Only `Viewport` nodes
     * with a matching rendering mask will render this `CanvasItem`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_visibility_layer
     */
    @JvmStatic
    fun canvasItemSetVisibilityLayer(item: RID, visibilityLayer: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(canvasItemSetVisibilityLayerBind, singleton, item, visibilityLayer)
    }

    /**
     * Sets the `transform` of the canvas item specified by the `item` RID. This affects where and how
     * the item will be drawn. Child canvas items' transforms are multiplied by their parent's
     * transform. Equivalent to `Node2D.transform`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_transform
     */
    @JvmStatic
    fun canvasItemSetTransform(item: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasItemSetTransformBind, singleton, item, transform)
    }

    /**
     * If `clip` is `true`, makes the canvas item specified by the `item` RID not draw anything outside
     * of its rect's coordinates. This clipping is fast, but works only with axis-aligned rectangles.
     * This means that rotation is ignored by the clipping rectangle. For more advanced clipping
     * shapes, use `canvas_item_set_canvas_group_mode` instead. Note: The equivalent node functionality
     * is found in `Label.clip_text`, `RichTextLabel` (always enabled) and more.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_clip
     */
    @JvmStatic
    fun canvasItemSetClip(item: RID, clip: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetClipBind, singleton, item, clip)
    }

    /**
     * If `enabled` is `true`, enables multichannel signed distance field rendering mode for the canvas
     * item specified by the `item` RID. This is meant to be used for font rendering, or with specially
     * generated images using msdfgen (https://github.com/Chlumsky/msdfgen).
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_distance_field_mode
     */
    @JvmStatic
    fun canvasItemSetDistanceFieldMode(item: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetDistanceFieldModeBind, singleton, item, enabled)
    }

    /**
     * If `use_custom_rect` is `true`, sets the custom visibility rectangle (used for culling) to
     * `rect` for the canvas item specified by `item`. Setting a custom visibility rect can reduce CPU
     * load when drawing lots of 2D instances. If `use_custom_rect` is `false`, automatically computes
     * a visibility rectangle based on the canvas item's draw commands.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_custom_rect
     */
    @JvmStatic
    fun canvasItemSetCustomRect(item: RID, useCustomRect: Boolean, rect: Rect2) {
        ObjectCalls.ptrcallWithRIDBoolRect2Args(canvasItemSetCustomRectBind, singleton, item, useCustomRect, rect)
    }

    /**
     * Multiplies the color of the canvas item specified by the `item` RID, while affecting its
     * children. See also `canvas_item_set_self_modulate`. Equivalent to `CanvasItem.modulate`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_modulate
     */
    @JvmStatic
    fun canvasItemSetModulate(item: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(canvasItemSetModulateBind, singleton, item, color)
    }

    /**
     * Multiplies the color of the canvas item specified by the `item` RID, without affecting its
     * children. See also `canvas_item_set_modulate`. Equivalent to `CanvasItem.self_modulate`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_self_modulate
     */
    @JvmStatic
    fun canvasItemSetSelfModulate(item: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(canvasItemSetSelfModulateBind, singleton, item, color)
    }

    /**
     * If `enabled` is `true`, draws the canvas item specified by the `item` RID behind its parent.
     * Equivalent to `CanvasItem.show_behind_parent`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_draw_behind_parent
     */
    @JvmStatic
    fun canvasItemSetDrawBehindParent(item: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetDrawBehindParentBind, singleton, item, enabled)
    }

    /**
     * If `interpolated` is `true`, turns on physics interpolation for the canvas item.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_interpolated
     */
    @JvmStatic
    fun canvasItemSetInterpolated(item: RID, interpolated: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetInterpolatedBind, singleton, item, interpolated)
    }

    /**
     * Prevents physics interpolation for the current physics tick. This is useful when moving a canvas
     * item to a new location, to give an instantaneous change rather than interpolation from the
     * previous location.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_reset_physics_interpolation
     */
    @JvmStatic
    fun canvasItemResetPhysicsInterpolation(item: RID) {
        ObjectCalls.ptrcallWithRIDArg(canvasItemResetPhysicsInterpolationBind, singleton, item)
    }

    /**
     * Transforms both the current and previous stored transform for a canvas item. This allows
     * transforming a canvas item without creating a "glitch" in the interpolation, which is
     * particularly useful for large worlds utilizing a shifting origin.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_transform_physics_interpolation
     */
    @JvmStatic
    fun canvasItemTransformPhysicsInterpolation(item: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasItemTransformPhysicsInterpolationBind, singleton, item, transform)
    }

    /**
     * Draws a line on the `CanvasItem` pointed to by the `item` `RID`. See also
     * `CanvasItem.draw_line`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_line
     */
    @JvmStatic
    fun canvasItemAddLine(item: RID, from: Vector2, to: Vector2, color: Color, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRIDTwoVector2ColorDoubleBoolArgs(canvasItemAddLineBind, singleton, item, from, to, color, width, antialiased)
    }

    /**
     * Draws a 2D polyline on the `CanvasItem` pointed to by the `item` `RID`. See also
     * `CanvasItem.draw_polyline` and `CanvasItem.draw_polyline_colors`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_polyline
     */
    @JvmStatic
    fun canvasItemAddPolyline(item: RID, points: List<Vector2>, colors: List<Color>, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRIDPackedVector2ListPackedColorListDoubleAndBoolArgs(canvasItemAddPolylineBind, singleton, item, points, colors, width, antialiased)
    }

    /**
     * Draws a 2D multiline on the `CanvasItem` pointed to by the `item` `RID`. See also
     * `CanvasItem.draw_multiline` and `CanvasItem.draw_multiline_colors`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_multiline
     */
    @JvmStatic
    fun canvasItemAddMultiline(item: RID, points: List<Vector2>, colors: List<Color>, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRIDPackedVector2ListPackedColorListDoubleAndBoolArgs(canvasItemAddMultilineBind, singleton, item, points, colors, width, antialiased)
    }

    /**
     * Draws a rectangle on the `CanvasItem` pointed to by the `item` `RID`. See also
     * `CanvasItem.draw_rect`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_rect
     */
    @JvmStatic
    fun canvasItemAddRect(item: RID, rect: Rect2, color: Color, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRIDRect2ColorBoolArgs(canvasItemAddRectBind, singleton, item, rect, color, antialiased)
    }

    /**
     * Draws a circle on the `CanvasItem` pointed to by the `item` `RID`. See also
     * `CanvasItem.draw_circle`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_circle
     */
    @JvmStatic
    fun canvasItemAddCircle(item: RID, pos: Vector2, radius: Double, color: Color, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRIDVector2DoubleColorBoolArgs(canvasItemAddCircleBind, singleton, item, pos, radius, color, antialiased)
    }

    /**
     * Draws an ellipse with semi-major axis `major` and semi-minor axis `minor` on the `CanvasItem`
     * pointed to by the `item` `RID`. See also `CanvasItem.draw_ellipse`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_ellipse
     */
    @JvmStatic
    fun canvasItemAddEllipse(item: RID, pos: Vector2, major: Double, minor: Double, color: Color, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRIDVector2TwoDoubleColorBoolArgs(canvasItemAddEllipseBind, singleton, item, pos, major, minor, color, antialiased)
    }

    /**
     * Draws a 2D textured rectangle on the `CanvasItem` pointed to by the `item` `RID`. See also
     * `CanvasItem.draw_texture_rect` and `Texture2D.draw_rect`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_texture_rect
     */
    @JvmStatic
    fun canvasItemAddTextureRect(item: RID, rect: Rect2, texture: RID, tile: Boolean = false, modulate: Color, transpose: Boolean = false) {
        ObjectCalls.ptrcallWithRIDRect2RIDBoolColorBoolArgs(canvasItemAddTextureRectBind, singleton, item, rect, texture, tile, modulate, transpose)
    }

    /**
     * See also `CanvasItem.draw_msdf_texture_rect_region`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_msdf_texture_rect_region
     */
    @JvmStatic
    fun canvasItemAddMsdfTextureRectRegion(item: RID, rect: Rect2, texture: RID, srcRect: Rect2, modulate: Color, outlineSize: Int = 0, pxRange: Double = 1.0, scale: Double = 1.0) {
        ObjectCalls.ptrcallWithRIDRect2RIDRect2ColorIntTwoDoubleArgs(canvasItemAddMsdfTextureRectRegionBind, singleton, item, rect, texture, srcRect, modulate, outlineSize, pxRange, scale)
    }

    /**
     * See also `CanvasItem.draw_lcd_texture_rect_region`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_lcd_texture_rect_region
     */
    @JvmStatic
    fun canvasItemAddLcdTextureRectRegion(item: RID, rect: Rect2, texture: RID, srcRect: Rect2, modulate: Color) {
        ObjectCalls.ptrcallWithRIDRect2RIDRect2ColorArgs(canvasItemAddLcdTextureRectRegionBind, singleton, item, rect, texture, srcRect, modulate)
    }

    /**
     * Draws the specified region of a 2D textured rectangle on the `CanvasItem` pointed to by the
     * `item` `RID`. See also `CanvasItem.draw_texture_rect_region` and `Texture2D.draw_rect_region`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_texture_rect_region
     */
    @JvmStatic
    fun canvasItemAddTextureRectRegion(item: RID, rect: Rect2, texture: RID, srcRect: Rect2, modulate: Color, transpose: Boolean = false, clipUv: Boolean = true) {
        ObjectCalls.ptrcallWithRIDRect2RIDRect2ColorTwoBoolArgs(canvasItemAddTextureRectRegionBind, singleton, item, rect, texture, srcRect, modulate, transpose, clipUv)
    }

    /**
     * Draws a nine-patch rectangle on the `CanvasItem` pointed to by the `item` `RID`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_nine_patch
     */
    @JvmStatic
    fun canvasItemAddNinePatch(item: RID, rect: Rect2, source: Rect2, texture: RID, topleft: Vector2, bottomright: Vector2, xAxisMode: Long = 0L, yAxisMode: Long = 0L, drawCenter: Boolean = true, modulate: Color) {
        ObjectCalls.ptrcallWithRIDTwoRect2RIDTwoVector2TwoLongBoolColorArgs(canvasItemAddNinePatchBind, singleton, item, rect, source, texture, topleft, bottomright, xAxisMode, yAxisMode, drawCenter, modulate)
    }

    /**
     * Draws a 2D primitive on the `CanvasItem` pointed to by the `item` `RID`. See also
     * `CanvasItem.draw_primitive`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_primitive
     */
    @JvmStatic
    fun canvasItemAddPrimitive(item: RID, points: List<Vector2>, colors: List<Color>, uvs: List<Vector2>, texture: RID) {
        ObjectCalls.ptrcallWithRIDPackedVector2ListPackedColorListPackedVector2ListAndRIDArgs(canvasItemAddPrimitiveBind, singleton, item, points, colors, uvs, texture)
    }

    /**
     * Draws a 2D polygon on the `CanvasItem` pointed to by the `item` `RID`. If you need more
     * flexibility (such as being able to use bones), use `canvas_item_add_triangle_array` instead. See
     * also `CanvasItem.draw_polygon`. Note: If you frequently redraw the same polygon with a large
     * number of vertices, consider pre-calculating the triangulation with
     * `Geometry2D.triangulate_polygon` and using `CanvasItem.draw_mesh`, `CanvasItem.draw_multimesh`,
     * or `canvas_item_add_triangle_array`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_polygon
     */
    @JvmStatic
    fun canvasItemAddPolygon(item: RID, points: List<Vector2>, colors: List<Color>, uvs: List<Vector2>, texture: RID) {
        ObjectCalls.ptrcallWithRIDPackedVector2ListPackedColorListPackedVector2ListAndRIDArgs(canvasItemAddPolygonBind, singleton, item, points, colors, uvs, texture)
    }

    /**
     * Draws a triangle array on the `CanvasItem` pointed to by the `item` `RID`. This is internally
     * used by `Line2D` and `StyleBoxFlat` for rendering. `canvas_item_add_triangle_array` is highly
     * flexible, but more complex to use than `canvas_item_add_polygon`. Note: If `count` is set to a
     * non-negative value, only the first `count * 3` indices (corresponding to `count` triangles) will
     * be drawn. Otherwise, all indices are drawn.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_triangle_array
     */
    @JvmStatic
    fun canvasItemAddTriangleArray(item: RID, indices: List<Int>, points: List<Vector2>, colors: List<Color>, uvs: List<Vector2>, bones: List<Int>, weights: List<Float>, texture: RID, count: Int = -1) {
        ObjectCalls.ptrcallWithRIDPackedInt32ListPackedVector2ListPackedColorListPackedVector2ListPackedInt32ListPackedFloat32ListRIDIntArgs(canvasItemAddTriangleArrayBind, singleton, item, indices, points, colors, uvs, bones, weights, texture, count)
    }

    /**
     * Draws a mesh created with `mesh_create` with given `transform`, `modulate` color, and `texture`.
     * This is used internally by `MeshInstance2D`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_mesh
     */
    @JvmStatic
    fun canvasItemAddMesh(item: RID, mesh: RID, transform: Transform2D, modulate: Color, texture: RID) {
        ObjectCalls.ptrcallWithTwoRIDTransform2DColorRIDArgs(canvasItemAddMeshBind, singleton, item, mesh, transform, modulate, texture)
    }

    /**
     * Draws a 2D `MultiMesh` on the `CanvasItem` pointed to by the `item` `RID`. See also
     * `CanvasItem.draw_multimesh`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_multimesh
     */
    @JvmStatic
    fun canvasItemAddMultimesh(item: RID, mesh: RID, texture: RID) {
        ObjectCalls.ptrcallWithThreeRIDArgs(canvasItemAddMultimeshBind, singleton, item, mesh, texture)
    }

    /**
     * Draws particles on the `CanvasItem` pointed to by the `item` `RID`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_particles
     */
    @JvmStatic
    fun canvasItemAddParticles(item: RID, particles: RID, texture: RID) {
        ObjectCalls.ptrcallWithThreeRIDArgs(canvasItemAddParticlesBind, singleton, item, particles, texture)
    }

    /**
     * Sets a `Transform2D` that will be used to transform subsequent canvas item commands.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_set_transform
     */
    @JvmStatic
    fun canvasItemAddSetTransform(item: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasItemAddSetTransformBind, singleton, item, transform)
    }

    /**
     * If `ignore` is `true`, ignore clipping on items drawn with this canvas item until this is called
     * again with `ignore` set to `false`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_clip_ignore
     */
    @JvmStatic
    fun canvasItemAddClipIgnore(item: RID, ignore: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemAddClipIgnoreBind, singleton, item, ignore)
    }

    /**
     * Subsequent drawing commands will be ignored unless they fall within the specified animation
     * slice. This is a faster way to implement animations that loop on background rather than
     * redrawing constantly.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_animation_slice
     */
    @JvmStatic
    fun canvasItemAddAnimationSlice(item: RID, animationLength: Double, sliceBegin: Double, sliceEnd: Double, offset: Double = 0.0) {
        ObjectCalls.ptrcallWithRIDFourDoubleArgs(canvasItemAddAnimationSliceBind, singleton, item, animationLength, sliceBegin, sliceEnd, offset)
    }

    /**
     * If `enabled` is `true`, child nodes with the lowest Y position are drawn before those with a
     * higher Y position. Y-sorting only affects children that inherit from the canvas item specified
     * by the `item` RID, not the canvas item itself. Equivalent to `CanvasItem.y_sort_enabled`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_sort_children_by_y
     */
    @JvmStatic
    fun canvasItemSetSortChildrenByY(item: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetSortChildrenByYBind, singleton, item, enabled)
    }

    /**
     * Sets the `CanvasItem`'s Z index, i.e. its draw order (lower indexes are drawn first).
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_z_index
     */
    @JvmStatic
    fun canvasItemSetZIndex(item: RID, zIndex: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasItemSetZIndexBind, singleton, item, zIndex)
    }

    /**
     * If this is enabled, the Z index of the parent will be added to the children's Z index.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_z_as_relative_to_parent
     */
    @JvmStatic
    fun canvasItemSetZAsRelativeToParent(item: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetZAsRelativeToParentBind, singleton, item, enabled)
    }

    /**
     * Sets the `CanvasItem` to copy a rect to the backbuffer.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_copy_to_backbuffer
     */
    @JvmStatic
    fun canvasItemSetCopyToBackbuffer(item: RID, enabled: Boolean, rect: Rect2) {
        ObjectCalls.ptrcallWithRIDBoolRect2Args(canvasItemSetCopyToBackbufferBind, singleton, item, enabled, rect)
    }

    /**
     * Attaches a skeleton to the `CanvasItem`. Removes the previous skeleton.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_attach_skeleton
     */
    @JvmStatic
    fun canvasItemAttachSkeleton(item: RID, skeleton: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasItemAttachSkeletonBind, singleton, item, skeleton)
    }

    /**
     * Clears the `CanvasItem` and removes all commands in it.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_clear
     */
    @JvmStatic
    fun canvasItemClear(item: RID) {
        ObjectCalls.ptrcallWithRIDArg(canvasItemClearBind, singleton, item)
    }

    /**
     * Sets the index for the `CanvasItem`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_draw_index
     */
    @JvmStatic
    fun canvasItemSetDrawIndex(item: RID, index: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasItemSetDrawIndexBind, singleton, item, index)
    }

    /**
     * Sets a new `material` to the canvas item specified by the `item` RID. Equivalent to
     * `CanvasItem.material`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_material
     */
    @JvmStatic
    fun canvasItemSetMaterial(item: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasItemSetMaterialBind, singleton, item, material)
    }

    /**
     * Sets if the `CanvasItem` uses its parent's material.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_use_parent_material
     */
    @JvmStatic
    fun canvasItemSetUseParentMaterial(item: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetUseParentMaterialBind, singleton, item, enabled)
    }

    /**
     * Sets the per-instance shader uniform on the specified canvas item instance. Equivalent to
     * `CanvasItem.set_instance_shader_parameter`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_instance_shader_parameter
     */
    @JvmStatic
    fun canvasItemSetInstanceShaderParameter(instance: RID, parameter: String, value: Any?) {
        ObjectCalls.ptrcallWithRIDStringNameAndVariantArgs(canvasItemSetInstanceShaderParameterBind, singleton, instance, parameter, value)
    }

    /**
     * Returns the value of the per-instance shader uniform from the specified canvas item instance.
     * Equivalent to `CanvasItem.get_instance_shader_parameter`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_get_instance_shader_parameter
     */
    @JvmStatic
    fun canvasItemGetInstanceShaderParameter(instance: RID, parameter: String): Any? {
        return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(canvasItemGetInstanceShaderParameterBind, singleton, instance, parameter)
    }

    /**
     * Returns the default value of the per-instance shader uniform from the specified canvas item
     * instance. Equivalent to `CanvasItem.get_instance_shader_parameter`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_get_instance_shader_parameter_default_value
     */
    @JvmStatic
    fun canvasItemGetInstanceShaderParameterDefaultValue(instance: RID, parameter: String): Any? {
        return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(canvasItemGetInstanceShaderParameterDefaultValueBind, singleton, instance, parameter)
    }

    /**
     * Returns a dictionary of per-instance shader uniform names of the per-instance shader uniform
     * from the specified canvas item instance. The returned dictionary is in PropertyInfo format, with
     * the keys `name`, `class_name`, `type`, `hint`, `hint_string`, and `usage`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_get_instance_shader_parameter_list
     */
    @JvmStatic
    fun canvasItemGetInstanceShaderParameterList(instance: RID): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(canvasItemGetInstanceShaderParameterListBind, singleton, instance)
    }

    /**
     * Sets the given `CanvasItem` as visibility notifier. `area` defines the area of detecting
     * visibility. `enter_callable` is called when the `CanvasItem` enters the screen, `exit_callable`
     * is called when the `CanvasItem` exits the screen. If `enable` is `false`, the item will no
     * longer function as notifier. This method can be used to manually mimic
     * `VisibleOnScreenNotifier2D`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_visibility_notifier
     */
    @JvmStatic
    fun canvasItemSetVisibilityNotifier(item: RID, enable: Boolean, area: Rect2, enterCallable: GodotCallable, exitCallable: GodotCallable) {
        ObjectCalls.ptrcallWithRIDBoolRect2TwoCallableArgs(canvasItemSetVisibilityNotifierBind, singleton, item, enable, area, enterCallable.target.handle, enterCallable.method, exitCallable.target.handle, exitCallable.method)
    }

    /**
     * Sets the canvas group mode used during 2D rendering for the canvas item specified by the `item`
     * RID. For faster but more limited clipping, use `canvas_item_set_clip` instead. Note: The
     * equivalent node functionality is found in `CanvasGroup` and `CanvasItem.clip_children`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_canvas_group_mode
     */
    @JvmStatic
    fun canvasItemSetCanvasGroupMode(item: RID, mode: Long, clearMargin: Double = 5.0, fitEmpty: Boolean = false, fitMargin: Double = 0.0, blurMipmaps: Boolean = false) {
        ObjectCalls.ptrcallWithRIDLongDoubleBoolDoubleBoolArgs(canvasItemSetCanvasGroupModeBind, singleton, item, mode, clearMargin, fitEmpty, fitMargin, blurMipmaps)
    }

    /**
     * Returns the bounding rectangle for a canvas item in local space, as calculated by the renderer.
     * This bound is used internally for culling. Warning: This function is intended for debugging in
     * the editor, and will pass through and return a zero `Rect2` in exported projects.
     *
     * Generated from Godot docs: RenderingServer.debug_canvas_item_get_rect
     */
    @JvmStatic
    fun debugCanvasItemGetRect(item: RID): Rect2 {
        return ObjectCalls.ptrcallWithRIDArgRetRect2(debugCanvasItemGetRectBind, singleton, item)
    }

    /**
     * Creates a canvas light and adds it to the RenderingServer. It can be accessed with the RID that
     * is returned. This RID will be used in all `canvas_light_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. Note: The equivalent node is `Light2D`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_create
     */
    @JvmStatic
    fun canvasLightCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasLightCreateBind, singleton)
    }

    /**
     * Attaches the canvas light to the canvas. Removes it from its previous canvas.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_attach_to_canvas
     */
    @JvmStatic
    fun canvasLightAttachToCanvas(light: RID, canvas: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasLightAttachToCanvasBind, singleton, light, canvas)
    }

    /**
     * Enables or disables a canvas light.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_enabled
     */
    @JvmStatic
    fun canvasLightSetEnabled(light: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightSetEnabledBind, singleton, light, enabled)
    }

    /**
     * Sets the scale factor of a `PointLight2D`'s texture. Equivalent to `PointLight2D.texture_scale`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_texture_scale
     */
    @JvmStatic
    fun canvasLightSetTextureScale(light: RID, scale: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(canvasLightSetTextureScaleBind, singleton, light, scale)
    }

    /**
     * Sets the canvas light's `Transform2D`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_transform
     */
    @JvmStatic
    fun canvasLightSetTransform(light: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasLightSetTransformBind, singleton, light, transform)
    }

    /**
     * Sets the texture to be used by a `PointLight2D`. Equivalent to `PointLight2D.texture`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_texture
     */
    @JvmStatic
    fun canvasLightSetTexture(light: RID, texture: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasLightSetTextureBind, singleton, light, texture)
    }

    /**
     * Sets the offset of a `PointLight2D`'s texture. Equivalent to `PointLight2D.offset`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_texture_offset
     */
    @JvmStatic
    fun canvasLightSetTextureOffset(light: RID, offset: Vector2) {
        ObjectCalls.ptrcallWithRIDAndVector2Arg(canvasLightSetTextureOffsetBind, singleton, light, offset)
    }

    /**
     * Sets the color for a light.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_color
     */
    @JvmStatic
    fun canvasLightSetColor(light: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(canvasLightSetColorBind, singleton, light, color)
    }

    /**
     * Sets a canvas light's height.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_height
     */
    @JvmStatic
    fun canvasLightSetHeight(light: RID, height: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(canvasLightSetHeightBind, singleton, light, height)
    }

    /**
     * Sets a canvas light's energy.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_energy
     */
    @JvmStatic
    fun canvasLightSetEnergy(light: RID, energy: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(canvasLightSetEnergyBind, singleton, light, energy)
    }

    /**
     * Sets the Z range of objects that will be affected by this light. Equivalent to
     * `Light2D.range_z_min` and `Light2D.range_z_max`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_z_range
     */
    @JvmStatic
    fun canvasLightSetZRange(light: RID, minZ: Int, maxZ: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(canvasLightSetZRangeBind, singleton, light, minZ, maxZ)
    }

    /**
     * The layer range that gets rendered with this light.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_layer_range
     */
    @JvmStatic
    fun canvasLightSetLayerRange(light: RID, minLayer: Int, maxLayer: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(canvasLightSetLayerRangeBind, singleton, light, minLayer, maxLayer)
    }

    /**
     * The light mask. See `LightOccluder2D` for more information on light masks.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_item_cull_mask
     */
    @JvmStatic
    fun canvasLightSetItemCullMask(light: RID, mask: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasLightSetItemCullMaskBind, singleton, light, mask)
    }

    /**
     * The binary mask used to determine which layers this canvas light's shadows affects. See
     * `LightOccluder2D` for more information on light masks.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_item_shadow_cull_mask
     */
    @JvmStatic
    fun canvasLightSetItemShadowCullMask(light: RID, mask: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasLightSetItemShadowCullMaskBind, singleton, light, mask)
    }

    /**
     * Sets the mode of the canvas light.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_mode
     */
    @JvmStatic
    fun canvasLightSetMode(light: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasLightSetModeBind, singleton, light, mode)
    }

    /**
     * Enables or disables the canvas light's shadow.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_shadow_enabled
     */
    @JvmStatic
    fun canvasLightSetShadowEnabled(light: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightSetShadowEnabledBind, singleton, light, enabled)
    }

    /**
     * Sets the canvas light's shadow's filter.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_shadow_filter
     */
    @JvmStatic
    fun canvasLightSetShadowFilter(light: RID, filter: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasLightSetShadowFilterBind, singleton, light, filter)
    }

    /**
     * Sets the color of the canvas light's shadow.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_shadow_color
     */
    @JvmStatic
    fun canvasLightSetShadowColor(light: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(canvasLightSetShadowColorBind, singleton, light, color)
    }

    /**
     * Smoothens the shadow. The lower, the smoother.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_shadow_smooth
     */
    @JvmStatic
    fun canvasLightSetShadowSmooth(light: RID, smooth: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(canvasLightSetShadowSmoothBind, singleton, light, smooth)
    }

    /**
     * Sets the blend mode for the given canvas light to `mode`. Equivalent to `Light2D.blend_mode`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_blend_mode
     */
    @JvmStatic
    fun canvasLightSetBlendMode(light: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasLightSetBlendModeBind, singleton, light, mode)
    }

    /**
     * If `interpolated` is `true`, turns on physics interpolation for the canvas light.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_interpolated
     */
    @JvmStatic
    fun canvasLightSetInterpolated(light: RID, interpolated: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightSetInterpolatedBind, singleton, light, interpolated)
    }

    /**
     * Prevents physics interpolation for the current physics tick. This is useful when moving a canvas
     * item to a new location, to give an instantaneous change rather than interpolation from the
     * previous location.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_reset_physics_interpolation
     */
    @JvmStatic
    fun canvasLightResetPhysicsInterpolation(light: RID) {
        ObjectCalls.ptrcallWithRIDArg(canvasLightResetPhysicsInterpolationBind, singleton, light)
    }

    /**
     * Transforms both the current and previous stored transform for a canvas light. This allows
     * transforming a light without creating a "glitch" in the interpolation, which is particularly
     * useful for large worlds utilizing a shifting origin.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_transform_physics_interpolation
     */
    @JvmStatic
    fun canvasLightTransformPhysicsInterpolation(light: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasLightTransformPhysicsInterpolationBind, singleton, light, transform)
    }

    /**
     * Creates a light occluder and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID will be used in all `canvas_light_occluder_*` RenderingServer
     * functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. Note: The equivalent node is `LightOccluder2D`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_create
     */
    @JvmStatic
    fun canvasLightOccluderCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasLightOccluderCreateBind, singleton)
    }

    /**
     * Attaches a light occluder to the canvas. Removes it from its previous canvas.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_attach_to_canvas
     */
    @JvmStatic
    fun canvasLightOccluderAttachToCanvas(occluder: RID, canvas: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasLightOccluderAttachToCanvasBind, singleton, occluder, canvas)
    }

    /**
     * Enables or disables light occluder.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_set_enabled
     */
    @JvmStatic
    fun canvasLightOccluderSetEnabled(occluder: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightOccluderSetEnabledBind, singleton, occluder, enabled)
    }

    /**
     * Sets a light occluder's polygon.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_set_polygon
     */
    @JvmStatic
    fun canvasLightOccluderSetPolygon(occluder: RID, polygon: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasLightOccluderSetPolygonBind, singleton, occluder, polygon)
    }

    /**
     * Enables or disables using the light occluder as a signed distance field for 2D particle
     * collision.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_set_as_sdf_collision
     */
    @JvmStatic
    fun canvasLightOccluderSetAsSdfCollision(occluder: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightOccluderSetAsSdfCollisionBind, singleton, occluder, enable)
    }

    /**
     * Sets a light occluder's `Transform2D`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_set_transform
     */
    @JvmStatic
    fun canvasLightOccluderSetTransform(occluder: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasLightOccluderSetTransformBind, singleton, occluder, transform)
    }

    /**
     * The light mask. See `LightOccluder2D` for more information on light masks.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_set_light_mask
     */
    @JvmStatic
    fun canvasLightOccluderSetLightMask(occluder: RID, mask: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasLightOccluderSetLightMaskBind, singleton, occluder, mask)
    }

    /**
     * If `interpolated` is `true`, turns on physics interpolation for the light occluder.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_set_interpolated
     */
    @JvmStatic
    fun canvasLightOccluderSetInterpolated(occluder: RID, interpolated: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightOccluderSetInterpolatedBind, singleton, occluder, interpolated)
    }

    /**
     * Prevents physics interpolation for the current physics tick. This is useful when moving an
     * occluder to a new location, to give an instantaneous change rather than interpolation from the
     * previous location.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_reset_physics_interpolation
     */
    @JvmStatic
    fun canvasLightOccluderResetPhysicsInterpolation(occluder: RID) {
        ObjectCalls.ptrcallWithRIDArg(canvasLightOccluderResetPhysicsInterpolationBind, singleton, occluder)
    }

    /**
     * Transforms both the current and previous stored transform for a light occluder. This allows
     * transforming an occluder without creating a "glitch" in the interpolation, which is particularly
     * useful for large worlds utilizing a shifting origin.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_transform_physics_interpolation
     */
    @JvmStatic
    fun canvasLightOccluderTransformPhysicsInterpolation(occluder: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasLightOccluderTransformPhysicsInterpolationBind, singleton, occluder, transform)
    }

    /**
     * Creates a new light occluder polygon and adds it to the RenderingServer. It can be accessed with
     * the RID that is returned. This RID will be used in all `canvas_occluder_polygon_*`
     * RenderingServer functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. Note: The equivalent resource is `OccluderPolygon2D`.
     *
     * Generated from Godot docs: RenderingServer.canvas_occluder_polygon_create
     */
    @JvmStatic
    fun canvasOccluderPolygonCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasOccluderPolygonCreateBind, singleton)
    }

    /**
     * Sets the shape of the occluder polygon.
     *
     * Generated from Godot docs: RenderingServer.canvas_occluder_polygon_set_shape
     */
    @JvmStatic
    fun canvasOccluderPolygonSetShape(occluderPolygon: RID, shape: List<Vector2>, closed: Boolean) {
        ObjectCalls.ptrcallWithRIDPackedVector2ListAndBoolArg(canvasOccluderPolygonSetShapeBind, singleton, occluderPolygon, shape, closed)
    }

    /**
     * Sets an occluder polygon's cull mode.
     *
     * Generated from Godot docs: RenderingServer.canvas_occluder_polygon_set_cull_mode
     */
    @JvmStatic
    fun canvasOccluderPolygonSetCullMode(occluderPolygon: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasOccluderPolygonSetCullModeBind, singleton, occluderPolygon, mode)
    }

    /**
     * Sets the `ProjectSettings.rendering/2d/shadow_atlas/size` to use for `Light2D` shadow rendering
     * (in pixels). The value is rounded up to the nearest power of 2.
     *
     * Generated from Godot docs: RenderingServer.canvas_set_shadow_texture_size
     */
    @JvmStatic
    fun canvasSetShadowTextureSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(canvasSetShadowTextureSizeBind, singleton, size)
    }

    /**
     * Creates a new global shader uniform. Note: Global shader parameter names are case-sensitive.
     *
     * Generated from Godot docs: RenderingServer.global_shader_parameter_add
     */
    @JvmStatic
    fun globalShaderParameterAdd(name: String, type: Long, defaultValue: Any?) {
        ObjectCalls.ptrcallWithStringNameLongVariantArgs(globalShaderParameterAddBind, singleton, name, type, defaultValue)
    }

    /**
     * Removes the global shader uniform specified by `name`.
     *
     * Generated from Godot docs: RenderingServer.global_shader_parameter_remove
     */
    @JvmStatic
    fun globalShaderParameterRemove(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(globalShaderParameterRemoveBind, singleton, name)
    }

    /**
     * Returns the list of global shader uniform names. Note: `global_shader_parameter_get` has a large
     * performance penalty as the rendering thread needs to synchronize with the calling thread, which
     * is slow. Do not use this method during gameplay to avoid stuttering. If you need to read values
     * in a script after setting them, consider creating an autoload where you store the values you
     * need to query at the same time you're setting them as global parameters.
     *
     * Generated from Godot docs: RenderingServer.global_shader_parameter_get_list
     */
    @JvmStatic
    fun globalShaderParameterGetList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(globalShaderParameterGetListBind, singleton)
    }

    /**
     * Sets the global shader uniform `name` to `value`.
     *
     * Generated from Godot docs: RenderingServer.global_shader_parameter_set
     */
    @JvmStatic
    fun globalShaderParameterSet(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(globalShaderParameterSetBind, singleton, name, value)
    }

    /**
     * Overrides the global shader uniform `name` with `value`. Equivalent to the
     * `ShaderGlobalsOverride` node.
     *
     * Generated from Godot docs: RenderingServer.global_shader_parameter_set_override
     */
    @JvmStatic
    fun globalShaderParameterSetOverride(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(globalShaderParameterSetOverrideBind, singleton, name, value)
    }

    /**
     * Returns the value of the global shader uniform specified by `name`. Note:
     * `global_shader_parameter_get` has a large performance penalty as the rendering thread needs to
     * synchronize with the calling thread, which is slow. Do not use this method during gameplay to
     * avoid stuttering. If you need to read values in a script after setting them, consider creating
     * an autoload where you store the values you need to query at the same time you're setting them as
     * global parameters.
     *
     * Generated from Godot docs: RenderingServer.global_shader_parameter_get
     */
    @JvmStatic
    fun globalShaderParameterGet(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(globalShaderParameterGetBind, singleton, name)
    }

    /**
     * Returns the type associated to the global shader uniform specified by `name`. Note:
     * `global_shader_parameter_get` has a large performance penalty as the rendering thread needs to
     * synchronize with the calling thread, which is slow. Do not use this method during gameplay to
     * avoid stuttering. If you need to read values in a script after setting them, consider creating
     * an autoload where you store the values you need to query at the same time you're setting them as
     * global parameters.
     *
     * Generated from Godot docs: RenderingServer.global_shader_parameter_get_type
     */
    @JvmStatic
    fun globalShaderParameterGetType(name: String): Long {
        return ObjectCalls.ptrcallWithStringNameArgRetLong(globalShaderParameterGetTypeBind, singleton, name)
    }

    /**
     * Tries to free an object in the RenderingServer. To avoid memory leaks, this should be called
     * after using an object as memory management does not occur automatically when using
     * RenderingServer directly.
     *
     * Generated from Godot docs: RenderingServer.free_rid
     */
    @JvmStatic
    fun freeRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(freeRidBind, singleton, rid)
    }

    /**
     * Schedules a callback to the given callable after a frame has been drawn.
     *
     * Generated from Godot docs: RenderingServer.request_frame_drawn_callback
     */
    @JvmStatic
    fun requestFrameDrawnCallback(callable: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(requestFrameDrawnCallbackBind, singleton, callable.target.handle, callable.method)
    }

    /**
     * Returns `true` if changes have been made to the RenderingServer's data. `force_draw` is usually
     * called if this happens.
     *
     * Generated from Godot docs: RenderingServer.has_changed
     */
    @JvmStatic
    fun hasChanged(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasChangedBind, singleton)
    }

    /**
     * Returns a statistic about the rendering engine which can be used for performance profiling. See
     * also `viewport_get_render_info`, which returns information specific to a viewport. Note: Only 3D
     * rendering is currently taken into account by some of these values, such as the number of draw
     * calls. Note: Rendering information is not available until at least 2 frames have been rendered
     * by the engine. If rendering information is not available, `get_rendering_info` returns `0`. To
     * print rendering information in `_ready()` successfully, use the following:
     *
     * Generated from Godot docs: RenderingServer.get_rendering_info
     */
    @JvmStatic
    fun getRenderingInfo(info: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(getRenderingInfoBind, singleton, info)
    }

    /**
     * Returns the name of the video adapter (e.g. "GeForce GTX 1080/PCIe/SSE2"). Note: When running a
     * headless or server binary, this function returns an empty string. Note: On the web platform,
     * some browsers such as Firefox may report a different, fixed GPU name such as "GeForce GTX 980"
     * (regardless of the user's actual GPU model). This is done to make fingerprinting more difficult.
     *
     * Generated from Godot docs: RenderingServer.get_video_adapter_name
     */
    @JvmStatic
    fun getVideoAdapterName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getVideoAdapterNameBind, singleton)
    }

    /**
     * Returns the vendor of the video adapter (e.g. "NVIDIA Corporation"). Note: When running a
     * headless or server binary, this function returns an empty string.
     *
     * Generated from Godot docs: RenderingServer.get_video_adapter_vendor
     */
    @JvmStatic
    fun getVideoAdapterVendor(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getVideoAdapterVendorBind, singleton)
    }

    /**
     * Returns the type of the video adapter. Since dedicated graphics cards from a given generation
     * will usually be significantly faster than integrated graphics made in the same generation, the
     * device type can be used as a basis for automatic graphics settings adjustment. However, this is
     * not always true, so make sure to provide users with a way to manually override graphics
     * settings. Note: When using the OpenGL rendering driver or when running in headless mode, this
     * function always returns `RenderingDevice.DEVICE_TYPE_OTHER`.
     *
     * Generated from Godot docs: RenderingServer.get_video_adapter_type
     */
    @JvmStatic
    fun getVideoAdapterType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVideoAdapterTypeBind, singleton)
    }

    /**
     * Returns the version of the graphics video adapter currently in use (e.g. "1.2.189" for Vulkan,
     * "3.3.0 NVIDIA 510.60.02" for OpenGL). This version may be different from the actual latest
     * version supported by the hardware, as Godot may not always request the latest version. See also
     * `OS.get_video_adapter_driver_info`. Note: When running a headless or server binary, this
     * function returns an empty string.
     *
     * Generated from Godot docs: RenderingServer.get_video_adapter_api_version
     */
    @JvmStatic
    fun getVideoAdapterApiVersion(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getVideoAdapterApiVersionBind, singleton)
    }

    /**
     * Returns the name of the current rendering driver. This can be `vulkan`, `d3d12`, `metal`,
     * `opengl3`, `opengl3_es`, or `opengl3_angle`. See also `get_current_rendering_method`. When
     * `ProjectSettings.rendering/renderer/rendering_method` is `forward_plus` or `mobile`, the
     * rendering driver is determined by `ProjectSettings.rendering/rendering_device/driver`. When
     * `ProjectSettings.rendering/renderer/rendering_method` is `gl_compatibility`, the rendering
     * driver is determined by `ProjectSettings.rendering/gl_compatibility/driver`. The rendering
     * driver is also determined by the `--rendering-driver` command line argument that overrides this
     * project setting, or an automatic fallback that is applied depending on the hardware.
     *
     * Generated from Godot docs: RenderingServer.get_current_rendering_driver_name
     */
    @JvmStatic
    fun getCurrentRenderingDriverName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentRenderingDriverNameBind, singleton)
    }

    /**
     * Returns the name of the current rendering method. This can be `forward_plus`, `mobile`, or
     * `gl_compatibility`. See also `get_current_rendering_driver_name`. The rendering method is
     * determined by `ProjectSettings.rendering/renderer/rendering_method`, the `--rendering-method`
     * command line argument that overrides this project setting, or an automatic fallback that is
     * applied depending on the hardware.
     *
     * Generated from Godot docs: RenderingServer.get_current_rendering_method
     */
    @JvmStatic
    fun getCurrentRenderingMethod(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentRenderingMethodBind, singleton)
    }

    /**
     * Returns a mesh of a sphere with the given number of horizontal subdivisions, vertical
     * subdivisions and radius. See also `get_test_cube`.
     *
     * Generated from Godot docs: RenderingServer.make_sphere_mesh
     */
    @JvmStatic
    fun makeSphereMesh(latitudes: Int, longitudes: Int, radius: Double): RID {
        return ObjectCalls.ptrcallWithTwoIntDoubleArgsRetRID(makeSphereMeshBind, singleton, latitudes, longitudes, radius)
    }

    /**
     * Returns the RID of the test cube. This mesh will be created and returned on the first call to
     * `get_test_cube`, then it will be cached for subsequent calls. See also `make_sphere_mesh`.
     *
     * Generated from Godot docs: RenderingServer.get_test_cube
     */
    @JvmStatic
    fun getTestCube(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getTestCubeBind, singleton)
    }

    /**
     * Returns the RID of a 256×256 texture with a testing pattern on it (in `Image.FORMAT_RGB8`
     * format). This texture will be created and returned on the first call to `get_test_texture`, then
     * it will be cached for subsequent calls. See also `get_white_texture`.
     *
     * Generated from Godot docs: RenderingServer.get_test_texture
     */
    @JvmStatic
    fun getTestTexture(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getTestTextureBind, singleton)
    }

    /**
     * Returns the ID of a 4×4 white texture (in `Image.FORMAT_RGB8` format). This texture will be
     * created and returned on the first call to `get_white_texture`, then it will be cached for
     * subsequent calls. See also `get_test_texture`.
     *
     * Generated from Godot docs: RenderingServer.get_white_texture
     */
    @JvmStatic
    fun getWhiteTexture(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getWhiteTextureBind, singleton)
    }

    /**
     * Sets a boot image. The `color` defines the background color. The value of `stretch_mode`
     * indicates how the image will be stretched (see `SplashStretchMode` for possible values). If
     * `use_filter` is `true`, the image will be scaled with linear interpolation. If `use_filter` is
     * `false`, the image will be scaled with nearest-neighbor interpolation.
     *
     * Generated from Godot docs: RenderingServer.set_boot_image_with_stretch
     */
    @JvmStatic
    fun setBootImageWithStretch(image: Image?, color: Color, stretchMode: Long, useFilter: Boolean = true) {
        ObjectCalls.ptrcallWithObjectColorLongBoolArgs(setBootImageWithStretchBind, singleton, image?.requireOpenHandle() ?: MemorySegment.NULL, color, stretchMode, useFilter)
    }

    /**
     * Sets a boot image. The `color` defines the background color. The value of `scale` indicates if
     * the image will be scaled to fit the screen size. If `use_filter` is `true`, the image will be
     * scaled with linear interpolation. If `use_filter` is `false`, the image will be scaled with
     * nearest-neighbor interpolation.
     *
     * Generated from Godot docs: RenderingServer.set_boot_image
     */
    @JvmStatic
    fun setBootImage(image: Image?, color: Color, scale: Boolean, useFilter: Boolean = true) {
        ObjectCalls.ptrcallWithObjectColorTwoBoolArgs(setBootImageBind, singleton, image?.requireOpenHandle() ?: MemorySegment.NULL, color, scale, useFilter)
    }

    /**
     * Returns the default clear color which is used when a specific clear color has not been selected.
     * See also `set_default_clear_color`.
     *
     * Generated from Godot docs: RenderingServer.get_default_clear_color
     */
    @JvmStatic
    fun getDefaultClearColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getDefaultClearColorBind, singleton)
    }

    /**
     * Sets the default clear color which is used when a specific clear color has not been selected.
     * See also `get_default_clear_color`.
     *
     * Generated from Godot docs: RenderingServer.set_default_clear_color
     */
    @JvmStatic
    fun setDefaultClearColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setDefaultClearColorBind, singleton, color)
    }

    /**
     * Returns `true` if the OS supports a certain `feature`. Features might be `s3tc`, `etc`, and
     * `etc2`.
     *
     * Generated from Godot docs: RenderingServer.has_os_feature
     */
    @JvmStatic
    fun hasOsFeature(feature: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasOsFeatureBind, singleton, feature)
    }

    /**
     * If `generate` is `true`, generates debug wireframes for all meshes that are loaded when using
     * the Compatibility renderer. By default, the engine does not generate debug wireframes at
     * runtime, since they slow down loading of assets and take up VRAM. Note: You must call this
     * method before loading any meshes when using the Compatibility renderer. Otherwise, wireframes
     * will not be used.
     *
     * Generated from Godot docs: RenderingServer.set_debug_generate_wireframes
     */
    @JvmStatic
    fun setDebugGenerateWireframes(generate: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDebugGenerateWireframesBind, singleton, generate)
    }

    /**
     * If `false`, disables rendering completely, but the engine logic is still being processed. You
     * can call `force_draw` to draw a frame even with rendering disabled.
     *
     * Generated from Godot docs: RenderingServer.is_render_loop_enabled
     */
    @JvmStatic
    fun isRenderLoopEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRenderLoopEnabledBind, singleton)
    }

    /**
     * If `false`, disables rendering completely, but the engine logic is still being processed. You
     * can call `force_draw` to draw a frame even with rendering disabled.
     *
     * Generated from Godot docs: RenderingServer.set_render_loop_enabled
     */
    @JvmStatic
    fun setRenderLoopEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRenderLoopEnabledBind, singleton, enabled)
    }

    /**
     * Returns the time taken to setup rendering on the CPU in milliseconds. This value is shared
     * across all viewports and does not require `viewport_set_measure_render_time` to be enabled on a
     * viewport to be queried. See also `viewport_get_measured_render_time_cpu`.
     *
     * Generated from Godot docs: RenderingServer.get_frame_setup_time_cpu
     */
    @JvmStatic
    fun getFrameSetupTimeCpu(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFrameSetupTimeCpuBind, singleton)
    }

    /**
     * Forces a synchronization between the CPU and GPU, which may be required in certain cases. Only
     * call this when needed, as CPU-GPU synchronization has a performance cost.
     *
     * Generated from Godot docs: RenderingServer.force_sync
     */
    @JvmStatic
    fun forceSync() {
        ObjectCalls.ptrcallNoArgs(forceSyncBind, singleton)
    }

    /**
     * Forces redrawing of all viewports at once. Must be called from the main thread.
     *
     * Generated from Godot docs: RenderingServer.force_draw
     */
    @JvmStatic
    fun forceDraw(swapBuffers: Boolean = true, frameStep: Double = 0.0) {
        ObjectCalls.ptrcallWithBoolAndDoubleArgs(forceDrawBind, singleton, swapBuffers, frameStep)
    }

    /**
     * Returns the global RenderingDevice. Note: When using the OpenGL rendering driver or when running
     * in headless mode, this function always returns `null`.
     *
     * Generated from Godot docs: RenderingServer.get_rendering_device
     */
    @JvmStatic
    fun getRenderingDevice(): RenderingDevice? {
        return RenderingDevice.wrap(ObjectCalls.ptrcallNoArgsRetObject(getRenderingDeviceBind, singleton))
    }

    /**
     * Creates a RenderingDevice that can be used to do draw and compute operations on a separate
     * thread. Cannot draw to the screen nor share data with the global RenderingDevice. Note: When
     * using the OpenGL rendering driver or when running in headless mode, this function always returns
     * `null`.
     *
     * Generated from Godot docs: RenderingServer.create_local_rendering_device
     */
    @JvmStatic
    fun createLocalRenderingDevice(): RenderingDevice? {
        return RenderingDevice.wrap(ObjectCalls.ptrcallNoArgsRetObject(createLocalRenderingDeviceBind, singleton))
    }

    /**
     * Returns `true` if our code is currently executing on the rendering thread.
     *
     * Generated from Godot docs: RenderingServer.is_on_render_thread
     */
    @JvmStatic
    fun isOnRenderThread(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnRenderThreadBind, singleton)
    }

    /**
     * As the RenderingServer actual logic may run on a separate thread, accessing its internals from
     * the main (or any other) thread will result in errors. To make it easier to run code that can
     * safely access the rendering internals (such as `RenderingDevice` and similar RD classes), push a
     * callable via this function so it will be executed on the render thread.
     *
     * Generated from Godot docs: RenderingServer.call_on_render_thread
     */
    @JvmStatic
    fun callOnRenderThread(callable: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(callOnRenderThreadBind, singleton, callable.target.handle, callable.method)
    }

    /**
     * This method does nothing and always returns `false`.
     *
     * Generated from Godot docs: RenderingServer.has_feature
     */
    @JvmStatic
    fun hasFeature(feature: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(hasFeatureBind, singleton, feature)
    }

    object Signals {
        const val framePreDraw: String = "frame_pre_draw"
        const val framePostDraw: String = "frame_post_draw"
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): RenderingServer? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): RenderingServer? =
        if (handle.address() == 0L) null else this

    private const val TEXTURE_2D_CREATE_HASH = 2010018390L
    private val texture2dCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_2d_create", TEXTURE_2D_CREATE_HASH)
    }

    private const val TEXTURE_2D_LAYERED_CREATE_HASH = 913689023L
    private val texture2dLayeredCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_2d_layered_create", TEXTURE_2D_LAYERED_CREATE_HASH)
    }

    private const val TEXTURE_3D_CREATE_HASH = 4036838706L
    private val texture3dCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_3d_create", TEXTURE_3D_CREATE_HASH)
    }

    private const val TEXTURE_PROXY_CREATE_HASH = 41030802L
    private val textureProxyCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_proxy_create", TEXTURE_PROXY_CREATE_HASH)
    }

    private const val TEXTURE_CREATE_FROM_NATIVE_HANDLE_HASH = 1682977582L
    private val textureCreateFromNativeHandleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_create_from_native_handle", TEXTURE_CREATE_FROM_NATIVE_HANDLE_HASH)
    }

    private const val TEXTURE_DRAWABLE_CREATE_HASH = 1993613667L
    private val textureDrawableCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_drawable_create", TEXTURE_DRAWABLE_CREATE_HASH)
    }

    private const val TEXTURE_2D_UPDATE_HASH = 999539803L
    private val texture2dUpdateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_2d_update", TEXTURE_2D_UPDATE_HASH)
    }

    private const val TEXTURE_3D_UPDATE_HASH = 684822712L
    private val texture3dUpdateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_3d_update", TEXTURE_3D_UPDATE_HASH)
    }

    private const val TEXTURE_PROXY_UPDATE_HASH = 395945892L
    private val textureProxyUpdateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_proxy_update", TEXTURE_PROXY_UPDATE_HASH)
    }

    private const val TEXTURE_2D_PLACEHOLDER_CREATE_HASH = 529393457L
    private val texture2dPlaceholderCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_2d_placeholder_create", TEXTURE_2D_PLACEHOLDER_CREATE_HASH)
    }

    private const val TEXTURE_2D_LAYERED_PLACEHOLDER_CREATE_HASH = 1394585590L
    private val texture2dLayeredPlaceholderCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_2d_layered_placeholder_create", TEXTURE_2D_LAYERED_PLACEHOLDER_CREATE_HASH)
    }

    private const val TEXTURE_3D_PLACEHOLDER_CREATE_HASH = 529393457L
    private val texture3dPlaceholderCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_3d_placeholder_create", TEXTURE_3D_PLACEHOLDER_CREATE_HASH)
    }

    private const val TEXTURE_2D_GET_HASH = 4206205781L
    private val texture2dGetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_2d_get", TEXTURE_2D_GET_HASH)
    }

    private const val TEXTURE_2D_LAYER_GET_HASH = 2705440895L
    private val texture2dLayerGetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_2d_layer_get", TEXTURE_2D_LAYER_GET_HASH)
    }

    private const val TEXTURE_3D_GET_HASH = 2684255073L
    private val texture3dGetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_3d_get", TEXTURE_3D_GET_HASH)
    }

    private const val TEXTURE_DRAWABLE_GENERATE_MIPMAPS_HASH = 2722037293L
    private val textureDrawableGenerateMipmapsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_drawable_generate_mipmaps", TEXTURE_DRAWABLE_GENERATE_MIPMAPS_HASH)
    }

    private const val TEXTURE_DRAWABLE_GET_DEFAULT_MATERIAL_HASH = 2944877500L
    private val textureDrawableGetDefaultMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_drawable_get_default_material", TEXTURE_DRAWABLE_GET_DEFAULT_MATERIAL_HASH)
    }

    private const val TEXTURE_REPLACE_HASH = 395945892L
    private val textureReplaceBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_replace", TEXTURE_REPLACE_HASH)
    }

    private const val TEXTURE_SET_SIZE_OVERRIDE_HASH = 4288446313L
    private val textureSetSizeOverrideBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_set_size_override", TEXTURE_SET_SIZE_OVERRIDE_HASH)
    }

    private const val TEXTURE_SET_PATH_HASH = 2726140452L
    private val textureSetPathBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_set_path", TEXTURE_SET_PATH_HASH)
    }

    private const val TEXTURE_GET_PATH_HASH = 642473191L
    private val textureGetPathBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_get_path", TEXTURE_GET_PATH_HASH)
    }

    private const val TEXTURE_GET_FORMAT_HASH = 1932918979L
    private val textureGetFormatBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_get_format", TEXTURE_GET_FORMAT_HASH)
    }

    private const val TEXTURE_SET_FORCE_REDRAW_IF_VISIBLE_HASH = 1265174801L
    private val textureSetForceRedrawIfVisibleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_set_force_redraw_if_visible", TEXTURE_SET_FORCE_REDRAW_IF_VISIBLE_HASH)
    }

    private const val TEXTURE_RD_CREATE_HASH = 1434128712L
    private val textureRdCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_rd_create", TEXTURE_RD_CREATE_HASH)
    }

    private const val TEXTURE_GET_RD_TEXTURE_HASH = 2790148051L
    private val textureGetRdTextureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_get_rd_texture", TEXTURE_GET_RD_TEXTURE_HASH)
    }

    private const val TEXTURE_GET_NATIVE_HANDLE_HASH = 1834114100L
    private val textureGetNativeHandleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_get_native_handle", TEXTURE_GET_NATIVE_HANDLE_HASH)
    }

    private const val SHADER_CREATE_HASH = 529393457L
    private val shaderCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "shader_create", SHADER_CREATE_HASH)
    }

    private const val SHADER_SET_CODE_HASH = 2726140452L
    private val shaderSetCodeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "shader_set_code", SHADER_SET_CODE_HASH)
    }

    private const val SHADER_SET_PATH_HINT_HASH = 2726140452L
    private val shaderSetPathHintBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "shader_set_path_hint", SHADER_SET_PATH_HINT_HASH)
    }

    private const val SHADER_GET_CODE_HASH = 642473191L
    private val shaderGetCodeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "shader_get_code", SHADER_GET_CODE_HASH)
    }

    private const val GET_SHADER_PARAMETER_LIST_HASH = 2684255073L
    private val getShaderParameterListBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_shader_parameter_list", GET_SHADER_PARAMETER_LIST_HASH)
    }

    private const val SHADER_GET_PARAMETER_DEFAULT_HASH = 2621281810L
    private val shaderGetParameterDefaultBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "shader_get_parameter_default", SHADER_GET_PARAMETER_DEFAULT_HASH)
    }

    private const val SHADER_SET_DEFAULT_TEXTURE_PARAMETER_HASH = 4094001817L
    private val shaderSetDefaultTextureParameterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "shader_set_default_texture_parameter", SHADER_SET_DEFAULT_TEXTURE_PARAMETER_HASH)
    }

    private const val SHADER_GET_DEFAULT_TEXTURE_PARAMETER_HASH = 1464608890L
    private val shaderGetDefaultTextureParameterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "shader_get_default_texture_parameter", SHADER_GET_DEFAULT_TEXTURE_PARAMETER_HASH)
    }

    private const val MATERIAL_CREATE_HASH = 529393457L
    private val materialCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "material_create", MATERIAL_CREATE_HASH)
    }

    private const val MATERIAL_SET_SHADER_HASH = 395945892L
    private val materialSetShaderBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "material_set_shader", MATERIAL_SET_SHADER_HASH)
    }

    private const val MATERIAL_SET_PARAM_HASH = 3477296213L
    private val materialSetParamBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "material_set_param", MATERIAL_SET_PARAM_HASH)
    }

    private const val MATERIAL_GET_PARAM_HASH = 2621281810L
    private val materialGetParamBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "material_get_param", MATERIAL_GET_PARAM_HASH)
    }

    private const val MATERIAL_SET_RENDER_PRIORITY_HASH = 3411492887L
    private val materialSetRenderPriorityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "material_set_render_priority", MATERIAL_SET_RENDER_PRIORITY_HASH)
    }

    private const val MATERIAL_SET_NEXT_PASS_HASH = 395945892L
    private val materialSetNextPassBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "material_set_next_pass", MATERIAL_SET_NEXT_PASS_HASH)
    }

    private const val MATERIAL_SET_USE_DEBANDING_HASH = 2586408642L
    private val materialSetUseDebandingBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "material_set_use_debanding", MATERIAL_SET_USE_DEBANDING_HASH)
    }

    private const val MESH_CREATE_FROM_SURFACES_HASH = 4291747531L
    private val meshCreateFromSurfacesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_create_from_surfaces", MESH_CREATE_FROM_SURFACES_HASH)
    }

    private const val MESH_CREATE_HASH = 529393457L
    private val meshCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_create", MESH_CREATE_HASH)
    }

    private const val MESH_SURFACE_GET_FORMAT_OFFSET_HASH = 2981368685L
    private val meshSurfaceGetFormatOffsetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_format_offset", MESH_SURFACE_GET_FORMAT_OFFSET_HASH)
    }

    private const val MESH_SURFACE_GET_FORMAT_VERTEX_STRIDE_HASH = 3188363337L
    private val meshSurfaceGetFormatVertexStrideBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_format_vertex_stride", MESH_SURFACE_GET_FORMAT_VERTEX_STRIDE_HASH)
    }

    private const val MESH_SURFACE_GET_FORMAT_NORMAL_TANGENT_STRIDE_HASH = 3188363337L
    private val meshSurfaceGetFormatNormalTangentStrideBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_format_normal_tangent_stride", MESH_SURFACE_GET_FORMAT_NORMAL_TANGENT_STRIDE_HASH)
    }

    private const val MESH_SURFACE_GET_FORMAT_ATTRIBUTE_STRIDE_HASH = 3188363337L
    private val meshSurfaceGetFormatAttributeStrideBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_format_attribute_stride", MESH_SURFACE_GET_FORMAT_ATTRIBUTE_STRIDE_HASH)
    }

    private const val MESH_SURFACE_GET_FORMAT_SKIN_STRIDE_HASH = 3188363337L
    private val meshSurfaceGetFormatSkinStrideBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_format_skin_stride", MESH_SURFACE_GET_FORMAT_SKIN_STRIDE_HASH)
    }

    private const val MESH_SURFACE_GET_FORMAT_INDEX_STRIDE_HASH = 3188363337L
    private val meshSurfaceGetFormatIndexStrideBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_format_index_stride", MESH_SURFACE_GET_FORMAT_INDEX_STRIDE_HASH)
    }

    private const val MESH_ADD_SURFACE_HASH = 1217542888L
    private val meshAddSurfaceBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_add_surface", MESH_ADD_SURFACE_HASH)
    }

    private const val MESH_ADD_SURFACE_FROM_ARRAYS_HASH = 2342446560L
    private val meshAddSurfaceFromArraysBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_add_surface_from_arrays", MESH_ADD_SURFACE_FROM_ARRAYS_HASH)
    }

    private const val MESH_GET_BLEND_SHAPE_COUNT_HASH = 2198884583L
    private val meshGetBlendShapeCountBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_get_blend_shape_count", MESH_GET_BLEND_SHAPE_COUNT_HASH)
    }

    private const val MESH_SET_BLEND_SHAPE_MODE_HASH = 1294662092L
    private val meshSetBlendShapeModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_set_blend_shape_mode", MESH_SET_BLEND_SHAPE_MODE_HASH)
    }

    private const val MESH_GET_BLEND_SHAPE_MODE_HASH = 4282291819L
    private val meshGetBlendShapeModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_get_blend_shape_mode", MESH_GET_BLEND_SHAPE_MODE_HASH)
    }

    private const val MESH_SURFACE_SET_MATERIAL_HASH = 2310537182L
    private val meshSurfaceSetMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_set_material", MESH_SURFACE_SET_MATERIAL_HASH)
    }

    private const val MESH_SURFACE_GET_MATERIAL_HASH = 1066463050L
    private val meshSurfaceGetMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_material", MESH_SURFACE_GET_MATERIAL_HASH)
    }

    private const val MESH_GET_SURFACE_HASH = 186674697L
    private val meshGetSurfaceBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_get_surface", MESH_GET_SURFACE_HASH)
    }

    private const val MESH_SURFACE_GET_ARRAYS_HASH = 1778388067L
    private val meshSurfaceGetArraysBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_arrays", MESH_SURFACE_GET_ARRAYS_HASH)
    }

    private const val MESH_SURFACE_GET_BLEND_SHAPE_ARRAYS_HASH = 1778388067L
    private val meshSurfaceGetBlendShapeArraysBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_blend_shape_arrays", MESH_SURFACE_GET_BLEND_SHAPE_ARRAYS_HASH)
    }

    private const val MESH_GET_SURFACE_COUNT_HASH = 2198884583L
    private val meshGetSurfaceCountBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_get_surface_count", MESH_GET_SURFACE_COUNT_HASH)
    }

    private const val MESH_SET_CUSTOM_AABB_HASH = 3696536120L
    private val meshSetCustomAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_set_custom_aabb", MESH_SET_CUSTOM_AABB_HASH)
    }

    private const val MESH_GET_CUSTOM_AABB_HASH = 974181306L
    private val meshGetCustomAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_get_custom_aabb", MESH_GET_CUSTOM_AABB_HASH)
    }

    private const val MESH_SURFACE_REMOVE_HASH = 3411492887L
    private val meshSurfaceRemoveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_remove", MESH_SURFACE_REMOVE_HASH)
    }

    private const val MESH_CLEAR_HASH = 2722037293L
    private val meshClearBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_clear", MESH_CLEAR_HASH)
    }

    private const val MESH_SURFACE_UPDATE_VERTEX_REGION_HASH = 2900195149L
    private val meshSurfaceUpdateVertexRegionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_update_vertex_region", MESH_SURFACE_UPDATE_VERTEX_REGION_HASH)
    }

    private const val MESH_SURFACE_UPDATE_ATTRIBUTE_REGION_HASH = 2900195149L
    private val meshSurfaceUpdateAttributeRegionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_update_attribute_region", MESH_SURFACE_UPDATE_ATTRIBUTE_REGION_HASH)
    }

    private const val MESH_SURFACE_UPDATE_SKIN_REGION_HASH = 2900195149L
    private val meshSurfaceUpdateSkinRegionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_update_skin_region", MESH_SURFACE_UPDATE_SKIN_REGION_HASH)
    }

    private const val MESH_SURFACE_UPDATE_INDEX_REGION_HASH = 2900195149L
    private val meshSurfaceUpdateIndexRegionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_update_index_region", MESH_SURFACE_UPDATE_INDEX_REGION_HASH)
    }

    private const val MESH_SET_SHADOW_MESH_HASH = 395945892L
    private val meshSetShadowMeshBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_set_shadow_mesh", MESH_SET_SHADOW_MESH_HASH)
    }

    private const val MULTIMESH_CREATE_HASH = 529393457L
    private val multimeshCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_create", MULTIMESH_CREATE_HASH)
    }

    private const val MULTIMESH_ALLOCATE_DATA_HASH = 557240154L
    private val multimeshAllocateDataBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_allocate_data", MULTIMESH_ALLOCATE_DATA_HASH)
    }

    private const val MULTIMESH_GET_INSTANCE_COUNT_HASH = 2198884583L
    private val multimeshGetInstanceCountBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_instance_count", MULTIMESH_GET_INSTANCE_COUNT_HASH)
    }

    private const val MULTIMESH_SET_MESH_HASH = 395945892L
    private val multimeshSetMeshBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_set_mesh", MULTIMESH_SET_MESH_HASH)
    }

    private const val MULTIMESH_INSTANCE_SET_TRANSFORM_HASH = 675327471L
    private val multimeshInstanceSetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_set_transform", MULTIMESH_INSTANCE_SET_TRANSFORM_HASH)
    }

    private const val MULTIMESH_INSTANCE_SET_TRANSFORM_2D_HASH = 736082694L
    private val multimeshInstanceSetTransform2dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_set_transform_2d", MULTIMESH_INSTANCE_SET_TRANSFORM_2D_HASH)
    }

    private const val MULTIMESH_INSTANCE_SET_COLOR_HASH = 176975443L
    private val multimeshInstanceSetColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_set_color", MULTIMESH_INSTANCE_SET_COLOR_HASH)
    }

    private const val MULTIMESH_INSTANCE_SET_CUSTOM_DATA_HASH = 176975443L
    private val multimeshInstanceSetCustomDataBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_set_custom_data", MULTIMESH_INSTANCE_SET_CUSTOM_DATA_HASH)
    }

    private const val MULTIMESH_GET_MESH_HASH = 3814569979L
    private val multimeshGetMeshBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_mesh", MULTIMESH_GET_MESH_HASH)
    }

    private const val MULTIMESH_GET_AABB_HASH = 974181306L
    private val multimeshGetAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_aabb", MULTIMESH_GET_AABB_HASH)
    }

    private const val MULTIMESH_SET_CUSTOM_AABB_HASH = 3696536120L
    private val multimeshSetCustomAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_set_custom_aabb", MULTIMESH_SET_CUSTOM_AABB_HASH)
    }

    private const val MULTIMESH_GET_CUSTOM_AABB_HASH = 974181306L
    private val multimeshGetCustomAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_custom_aabb", MULTIMESH_GET_CUSTOM_AABB_HASH)
    }

    private const val MULTIMESH_INSTANCE_GET_TRANSFORM_HASH = 1050775521L
    private val multimeshInstanceGetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_get_transform", MULTIMESH_INSTANCE_GET_TRANSFORM_HASH)
    }

    private const val MULTIMESH_INSTANCE_GET_TRANSFORM_2D_HASH = 1324854622L
    private val multimeshInstanceGetTransform2dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_get_transform_2d", MULTIMESH_INSTANCE_GET_TRANSFORM_2D_HASH)
    }

    private const val MULTIMESH_INSTANCE_GET_COLOR_HASH = 2946315076L
    private val multimeshInstanceGetColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_get_color", MULTIMESH_INSTANCE_GET_COLOR_HASH)
    }

    private const val MULTIMESH_INSTANCE_GET_CUSTOM_DATA_HASH = 2946315076L
    private val multimeshInstanceGetCustomDataBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_get_custom_data", MULTIMESH_INSTANCE_GET_CUSTOM_DATA_HASH)
    }

    private const val MULTIMESH_SET_VISIBLE_INSTANCES_HASH = 3411492887L
    private val multimeshSetVisibleInstancesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_set_visible_instances", MULTIMESH_SET_VISIBLE_INSTANCES_HASH)
    }

    private const val MULTIMESH_GET_VISIBLE_INSTANCES_HASH = 2198884583L
    private val multimeshGetVisibleInstancesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_visible_instances", MULTIMESH_GET_VISIBLE_INSTANCES_HASH)
    }

    private const val MULTIMESH_SET_BUFFER_HASH = 2960552364L
    private val multimeshSetBufferBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_set_buffer", MULTIMESH_SET_BUFFER_HASH)
    }

    private const val MULTIMESH_GET_COMMAND_BUFFER_RD_RID_HASH = 3814569979L
    private val multimeshGetCommandBufferRdRidBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_command_buffer_rd_rid", MULTIMESH_GET_COMMAND_BUFFER_RD_RID_HASH)
    }

    private const val MULTIMESH_GET_BUFFER_RD_RID_HASH = 3814569979L
    private val multimeshGetBufferRdRidBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_buffer_rd_rid", MULTIMESH_GET_BUFFER_RD_RID_HASH)
    }

    private const val MULTIMESH_GET_BUFFER_HASH = 3964669176L
    private val multimeshGetBufferBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_buffer", MULTIMESH_GET_BUFFER_HASH)
    }

    private const val MULTIMESH_SET_BUFFER_INTERPOLATED_HASH = 659844711L
    private val multimeshSetBufferInterpolatedBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_set_buffer_interpolated", MULTIMESH_SET_BUFFER_INTERPOLATED_HASH)
    }

    private const val MULTIMESH_SET_PHYSICS_INTERPOLATED_HASH = 1265174801L
    private val multimeshSetPhysicsInterpolatedBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_set_physics_interpolated", MULTIMESH_SET_PHYSICS_INTERPOLATED_HASH)
    }

    private const val MULTIMESH_SET_PHYSICS_INTERPOLATION_QUALITY_HASH = 3934808223L
    private val multimeshSetPhysicsInterpolationQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_set_physics_interpolation_quality", MULTIMESH_SET_PHYSICS_INTERPOLATION_QUALITY_HASH)
    }

    private const val MULTIMESH_INSTANCE_RESET_PHYSICS_INTERPOLATION_HASH = 3411492887L
    private val multimeshInstanceResetPhysicsInterpolationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_reset_physics_interpolation", MULTIMESH_INSTANCE_RESET_PHYSICS_INTERPOLATION_HASH)
    }

    private const val MULTIMESH_INSTANCES_RESET_PHYSICS_INTERPOLATION_HASH = 2722037293L
    private val multimeshInstancesResetPhysicsInterpolationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instances_reset_physics_interpolation", MULTIMESH_INSTANCES_RESET_PHYSICS_INTERPOLATION_HASH)
    }

    private const val SKELETON_CREATE_HASH = 529393457L
    private val skeletonCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "skeleton_create", SKELETON_CREATE_HASH)
    }

    private const val SKELETON_ALLOCATE_DATA_HASH = 1904426712L
    private val skeletonAllocateDataBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "skeleton_allocate_data", SKELETON_ALLOCATE_DATA_HASH)
    }

    private const val SKELETON_GET_BONE_COUNT_HASH = 2198884583L
    private val skeletonGetBoneCountBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "skeleton_get_bone_count", SKELETON_GET_BONE_COUNT_HASH)
    }

    private const val SKELETON_BONE_SET_TRANSFORM_HASH = 675327471L
    private val skeletonBoneSetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "skeleton_bone_set_transform", SKELETON_BONE_SET_TRANSFORM_HASH)
    }

    private const val SKELETON_BONE_GET_TRANSFORM_HASH = 1050775521L
    private val skeletonBoneGetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "skeleton_bone_get_transform", SKELETON_BONE_GET_TRANSFORM_HASH)
    }

    private const val SKELETON_BONE_SET_TRANSFORM_2D_HASH = 736082694L
    private val skeletonBoneSetTransform2dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "skeleton_bone_set_transform_2d", SKELETON_BONE_SET_TRANSFORM_2D_HASH)
    }

    private const val SKELETON_BONE_GET_TRANSFORM_2D_HASH = 1324854622L
    private val skeletonBoneGetTransform2dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "skeleton_bone_get_transform_2d", SKELETON_BONE_GET_TRANSFORM_2D_HASH)
    }

    private const val SKELETON_SET_BASE_TRANSFORM_2D_HASH = 1246044741L
    private val skeletonSetBaseTransform2dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "skeleton_set_base_transform_2d", SKELETON_SET_BASE_TRANSFORM_2D_HASH)
    }

    private const val DIRECTIONAL_LIGHT_CREATE_HASH = 529393457L
    private val directionalLightCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "directional_light_create", DIRECTIONAL_LIGHT_CREATE_HASH)
    }

    private const val OMNI_LIGHT_CREATE_HASH = 529393457L
    private val omniLightCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "omni_light_create", OMNI_LIGHT_CREATE_HASH)
    }

    private const val SPOT_LIGHT_CREATE_HASH = 529393457L
    private val spotLightCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "spot_light_create", SPOT_LIGHT_CREATE_HASH)
    }

    private const val AREA_LIGHT_CREATE_HASH = 529393457L
    private val areaLightCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "area_light_create", AREA_LIGHT_CREATE_HASH)
    }

    private const val LIGHT_SET_COLOR_HASH = 2948539648L
    private val lightSetColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_color", LIGHT_SET_COLOR_HASH)
    }

    private const val LIGHT_SET_PARAM_HASH = 501936875L
    private val lightSetParamBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_param", LIGHT_SET_PARAM_HASH)
    }

    private const val LIGHT_SET_SHADOW_HASH = 1265174801L
    private val lightSetShadowBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_shadow", LIGHT_SET_SHADOW_HASH)
    }

    private const val LIGHT_SET_PROJECTOR_HASH = 395945892L
    private val lightSetProjectorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_projector", LIGHT_SET_PROJECTOR_HASH)
    }

    private const val LIGHT_SET_NEGATIVE_HASH = 1265174801L
    private val lightSetNegativeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_negative", LIGHT_SET_NEGATIVE_HASH)
    }

    private const val LIGHT_SET_CULL_MASK_HASH = 3411492887L
    private val lightSetCullMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_cull_mask", LIGHT_SET_CULL_MASK_HASH)
    }

    private const val LIGHT_SET_DISTANCE_FADE_HASH = 1622292572L
    private val lightSetDistanceFadeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_distance_fade", LIGHT_SET_DISTANCE_FADE_HASH)
    }

    private const val LIGHT_SET_REVERSE_CULL_FACE_MODE_HASH = 1265174801L
    private val lightSetReverseCullFaceModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_reverse_cull_face_mode", LIGHT_SET_REVERSE_CULL_FACE_MODE_HASH)
    }

    private const val LIGHT_SET_SHADOW_CASTER_MASK_HASH = 3411492887L
    private val lightSetShadowCasterMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_shadow_caster_mask", LIGHT_SET_SHADOW_CASTER_MASK_HASH)
    }

    private const val LIGHT_SET_BAKE_MODE_HASH = 1048525260L
    private val lightSetBakeModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_bake_mode", LIGHT_SET_BAKE_MODE_HASH)
    }

    private const val LIGHT_SET_MAX_SDFGI_CASCADE_HASH = 3411492887L
    private val lightSetMaxSdfgiCascadeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_max_sdfgi_cascade", LIGHT_SET_MAX_SDFGI_CASCADE_HASH)
    }

    private const val LIGHT_OMNI_SET_SHADOW_MODE_HASH = 2552677200L
    private val lightOmniSetShadowModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_omni_set_shadow_mode", LIGHT_OMNI_SET_SHADOW_MODE_HASH)
    }

    private const val LIGHT_DIRECTIONAL_SET_SHADOW_MODE_HASH = 380462970L
    private val lightDirectionalSetShadowModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_directional_set_shadow_mode", LIGHT_DIRECTIONAL_SET_SHADOW_MODE_HASH)
    }

    private const val LIGHT_DIRECTIONAL_SET_BLEND_SPLITS_HASH = 1265174801L
    private val lightDirectionalSetBlendSplitsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_directional_set_blend_splits", LIGHT_DIRECTIONAL_SET_BLEND_SPLITS_HASH)
    }

    private const val LIGHT_DIRECTIONAL_SET_SKY_MODE_HASH = 2559740754L
    private val lightDirectionalSetSkyModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_directional_set_sky_mode", LIGHT_DIRECTIONAL_SET_SKY_MODE_HASH)
    }

    private const val LIGHT_AREA_SET_SIZE_HASH = 3201125042L
    private val lightAreaSetSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_area_set_size", LIGHT_AREA_SET_SIZE_HASH)
    }

    private const val LIGHT_AREA_SET_NORMALIZE_ENERGY_HASH = 1265174801L
    private val lightAreaSetNormalizeEnergyBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_area_set_normalize_energy", LIGHT_AREA_SET_NORMALIZE_ENERGY_HASH)
    }

    private const val LIGHT_PROJECTORS_SET_FILTER_HASH = 43944325L
    private val lightProjectorsSetFilterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_projectors_set_filter", LIGHT_PROJECTORS_SET_FILTER_HASH)
    }

    private const val LIGHTMAPS_SET_BICUBIC_FILTER_HASH = 2586408642L
    private val lightmapsSetBicubicFilterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmaps_set_bicubic_filter", LIGHTMAPS_SET_BICUBIC_FILTER_HASH)
    }

    private const val POSITIONAL_SOFT_SHADOW_FILTER_SET_QUALITY_HASH = 3613045266L
    private val positionalSoftShadowFilterSetQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "positional_soft_shadow_filter_set_quality", POSITIONAL_SOFT_SHADOW_FILTER_SET_QUALITY_HASH)
    }

    private const val DIRECTIONAL_SOFT_SHADOW_FILTER_SET_QUALITY_HASH = 3613045266L
    private val directionalSoftShadowFilterSetQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "directional_soft_shadow_filter_set_quality", DIRECTIONAL_SOFT_SHADOW_FILTER_SET_QUALITY_HASH)
    }

    private const val DIRECTIONAL_SHADOW_ATLAS_SET_SIZE_HASH = 300928843L
    private val directionalShadowAtlasSetSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "directional_shadow_atlas_set_size", DIRECTIONAL_SHADOW_ATLAS_SET_SIZE_HASH)
    }

    private const val REFLECTION_PROBE_CREATE_HASH = 529393457L
    private val reflectionProbeCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_create", REFLECTION_PROBE_CREATE_HASH)
    }

    private const val REFLECTION_PROBE_SET_UPDATE_MODE_HASH = 3853670147L
    private val reflectionProbeSetUpdateModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_update_mode", REFLECTION_PROBE_SET_UPDATE_MODE_HASH)
    }

    private const val REFLECTION_PROBE_SET_INTENSITY_HASH = 1794382983L
    private val reflectionProbeSetIntensityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_intensity", REFLECTION_PROBE_SET_INTENSITY_HASH)
    }

    private const val REFLECTION_PROBE_SET_BLEND_DISTANCE_HASH = 1794382983L
    private val reflectionProbeSetBlendDistanceBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_blend_distance", REFLECTION_PROBE_SET_BLEND_DISTANCE_HASH)
    }

    private const val REFLECTION_PROBE_SET_AMBIENT_MODE_HASH = 184163074L
    private val reflectionProbeSetAmbientModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_ambient_mode", REFLECTION_PROBE_SET_AMBIENT_MODE_HASH)
    }

    private const val REFLECTION_PROBE_SET_AMBIENT_COLOR_HASH = 2948539648L
    private val reflectionProbeSetAmbientColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_ambient_color", REFLECTION_PROBE_SET_AMBIENT_COLOR_HASH)
    }

    private const val REFLECTION_PROBE_SET_AMBIENT_ENERGY_HASH = 1794382983L
    private val reflectionProbeSetAmbientEnergyBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_ambient_energy", REFLECTION_PROBE_SET_AMBIENT_ENERGY_HASH)
    }

    private const val REFLECTION_PROBE_SET_MAX_DISTANCE_HASH = 1794382983L
    private val reflectionProbeSetMaxDistanceBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_max_distance", REFLECTION_PROBE_SET_MAX_DISTANCE_HASH)
    }

    private const val REFLECTION_PROBE_SET_SIZE_HASH = 3227306858L
    private val reflectionProbeSetSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_size", REFLECTION_PROBE_SET_SIZE_HASH)
    }

    private const val REFLECTION_PROBE_SET_ORIGIN_OFFSET_HASH = 3227306858L
    private val reflectionProbeSetOriginOffsetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_origin_offset", REFLECTION_PROBE_SET_ORIGIN_OFFSET_HASH)
    }

    private const val REFLECTION_PROBE_SET_AS_INTERIOR_HASH = 1265174801L
    private val reflectionProbeSetAsInteriorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_as_interior", REFLECTION_PROBE_SET_AS_INTERIOR_HASH)
    }

    private const val REFLECTION_PROBE_SET_ENABLE_BOX_PROJECTION_HASH = 1265174801L
    private val reflectionProbeSetEnableBoxProjectionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_enable_box_projection", REFLECTION_PROBE_SET_ENABLE_BOX_PROJECTION_HASH)
    }

    private const val REFLECTION_PROBE_SET_ENABLE_SHADOWS_HASH = 1265174801L
    private val reflectionProbeSetEnableShadowsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_enable_shadows", REFLECTION_PROBE_SET_ENABLE_SHADOWS_HASH)
    }

    private const val REFLECTION_PROBE_SET_CULL_MASK_HASH = 3411492887L
    private val reflectionProbeSetCullMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_cull_mask", REFLECTION_PROBE_SET_CULL_MASK_HASH)
    }

    private const val REFLECTION_PROBE_SET_REFLECTION_MASK_HASH = 3411492887L
    private val reflectionProbeSetReflectionMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_reflection_mask", REFLECTION_PROBE_SET_REFLECTION_MASK_HASH)
    }

    private const val REFLECTION_PROBE_SET_RESOLUTION_HASH = 3411492887L
    private val reflectionProbeSetResolutionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_resolution", REFLECTION_PROBE_SET_RESOLUTION_HASH)
    }

    private const val REFLECTION_PROBE_SET_MESH_LOD_THRESHOLD_HASH = 1794382983L
    private val reflectionProbeSetMeshLodThresholdBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_mesh_lod_threshold", REFLECTION_PROBE_SET_MESH_LOD_THRESHOLD_HASH)
    }

    private const val DECAL_CREATE_HASH = 529393457L
    private val decalCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_create", DECAL_CREATE_HASH)
    }

    private const val DECAL_SET_SIZE_HASH = 3227306858L
    private val decalSetSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_size", DECAL_SET_SIZE_HASH)
    }

    private const val DECAL_SET_TEXTURE_HASH = 3953344054L
    private val decalSetTextureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_texture", DECAL_SET_TEXTURE_HASH)
    }

    private const val DECAL_SET_EMISSION_ENERGY_HASH = 1794382983L
    private val decalSetEmissionEnergyBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_emission_energy", DECAL_SET_EMISSION_ENERGY_HASH)
    }

    private const val DECAL_SET_ALBEDO_MIX_HASH = 1794382983L
    private val decalSetAlbedoMixBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_albedo_mix", DECAL_SET_ALBEDO_MIX_HASH)
    }

    private const val DECAL_SET_MODULATE_HASH = 2948539648L
    private val decalSetModulateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_modulate", DECAL_SET_MODULATE_HASH)
    }

    private const val DECAL_SET_CULL_MASK_HASH = 3411492887L
    private val decalSetCullMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_cull_mask", DECAL_SET_CULL_MASK_HASH)
    }

    private const val DECAL_SET_DISTANCE_FADE_HASH = 2972769666L
    private val decalSetDistanceFadeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_distance_fade", DECAL_SET_DISTANCE_FADE_HASH)
    }

    private const val DECAL_SET_FADE_HASH = 2513314492L
    private val decalSetFadeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_fade", DECAL_SET_FADE_HASH)
    }

    private const val DECAL_SET_NORMAL_FADE_HASH = 1794382983L
    private val decalSetNormalFadeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_normal_fade", DECAL_SET_NORMAL_FADE_HASH)
    }

    private const val DECALS_SET_FILTER_HASH = 3519875702L
    private val decalsSetFilterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decals_set_filter", DECALS_SET_FILTER_HASH)
    }

    private const val GI_SET_USE_HALF_RESOLUTION_HASH = 2586408642L
    private val giSetUseHalfResolutionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "gi_set_use_half_resolution", GI_SET_USE_HALF_RESOLUTION_HASH)
    }

    private const val VOXEL_GI_CREATE_HASH = 529393457L
    private val voxelGiCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_create", VOXEL_GI_CREATE_HASH)
    }

    private const val VOXEL_GI_ALLOCATE_DATA_HASH = 4108223027L
    private val voxelGiAllocateDataBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_allocate_data", VOXEL_GI_ALLOCATE_DATA_HASH)
    }

    private const val VOXEL_GI_GET_OCTREE_SIZE_HASH = 2607699645L
    private val voxelGiGetOctreeSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_octree_size", VOXEL_GI_GET_OCTREE_SIZE_HASH)
    }

    private const val VOXEL_GI_GET_OCTREE_CELLS_HASH = 3348040486L
    private val voxelGiGetOctreeCellsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_octree_cells", VOXEL_GI_GET_OCTREE_CELLS_HASH)
    }

    private const val VOXEL_GI_GET_DATA_CELLS_HASH = 3348040486L
    private val voxelGiGetDataCellsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_data_cells", VOXEL_GI_GET_DATA_CELLS_HASH)
    }

    private const val VOXEL_GI_GET_DISTANCE_FIELD_HASH = 3348040486L
    private val voxelGiGetDistanceFieldBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_distance_field", VOXEL_GI_GET_DISTANCE_FIELD_HASH)
    }

    private const val VOXEL_GI_GET_LEVEL_COUNTS_HASH = 788230395L
    private val voxelGiGetLevelCountsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_level_counts", VOXEL_GI_GET_LEVEL_COUNTS_HASH)
    }

    private const val VOXEL_GI_GET_TO_CELL_XFORM_HASH = 1128465797L
    private val voxelGiGetToCellXformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_to_cell_xform", VOXEL_GI_GET_TO_CELL_XFORM_HASH)
    }

    private const val VOXEL_GI_SET_DYNAMIC_RANGE_HASH = 1794382983L
    private val voxelGiSetDynamicRangeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_dynamic_range", VOXEL_GI_SET_DYNAMIC_RANGE_HASH)
    }

    private const val VOXEL_GI_SET_PROPAGATION_HASH = 1794382983L
    private val voxelGiSetPropagationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_propagation", VOXEL_GI_SET_PROPAGATION_HASH)
    }

    private const val VOXEL_GI_SET_ENERGY_HASH = 1794382983L
    private val voxelGiSetEnergyBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_energy", VOXEL_GI_SET_ENERGY_HASH)
    }

    private const val VOXEL_GI_SET_BAKED_EXPOSURE_NORMALIZATION_HASH = 1794382983L
    private val voxelGiSetBakedExposureNormalizationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_baked_exposure_normalization", VOXEL_GI_SET_BAKED_EXPOSURE_NORMALIZATION_HASH)
    }

    private const val VOXEL_GI_SET_BIAS_HASH = 1794382983L
    private val voxelGiSetBiasBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_bias", VOXEL_GI_SET_BIAS_HASH)
    }

    private const val VOXEL_GI_SET_NORMAL_BIAS_HASH = 1794382983L
    private val voxelGiSetNormalBiasBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_normal_bias", VOXEL_GI_SET_NORMAL_BIAS_HASH)
    }

    private const val VOXEL_GI_SET_INTERIOR_HASH = 1265174801L
    private val voxelGiSetInteriorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_interior", VOXEL_GI_SET_INTERIOR_HASH)
    }

    private const val VOXEL_GI_SET_USE_TWO_BOUNCES_HASH = 1265174801L
    private val voxelGiSetUseTwoBouncesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_use_two_bounces", VOXEL_GI_SET_USE_TWO_BOUNCES_HASH)
    }

    private const val VOXEL_GI_SET_QUALITY_HASH = 1538689978L
    private val voxelGiSetQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_quality", VOXEL_GI_SET_QUALITY_HASH)
    }

    private const val LIGHTMAP_CREATE_HASH = 529393457L
    private val lightmapCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_create", LIGHTMAP_CREATE_HASH)
    }

    private const val LIGHTMAP_SET_TEXTURES_HASH = 2646464759L
    private val lightmapSetTexturesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_set_textures", LIGHTMAP_SET_TEXTURES_HASH)
    }

    private const val LIGHTMAP_SET_PROBE_BOUNDS_HASH = 3696536120L
    private val lightmapSetProbeBoundsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_set_probe_bounds", LIGHTMAP_SET_PROBE_BOUNDS_HASH)
    }

    private const val LIGHTMAP_SET_PROBE_INTERIOR_HASH = 1265174801L
    private val lightmapSetProbeInteriorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_set_probe_interior", LIGHTMAP_SET_PROBE_INTERIOR_HASH)
    }

    private const val LIGHTMAP_SET_PROBE_CAPTURE_DATA_HASH = 3217845880L
    private val lightmapSetProbeCaptureDataBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_set_probe_capture_data", LIGHTMAP_SET_PROBE_CAPTURE_DATA_HASH)
    }

    private const val LIGHTMAP_GET_PROBE_CAPTURE_POINTS_HASH = 808965560L
    private val lightmapGetProbeCapturePointsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_get_probe_capture_points", LIGHTMAP_GET_PROBE_CAPTURE_POINTS_HASH)
    }

    private const val LIGHTMAP_GET_PROBE_CAPTURE_SH_HASH = 1569415609L
    private val lightmapGetProbeCaptureShBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_get_probe_capture_sh", LIGHTMAP_GET_PROBE_CAPTURE_SH_HASH)
    }

    private const val LIGHTMAP_GET_PROBE_CAPTURE_TETRAHEDRA_HASH = 788230395L
    private val lightmapGetProbeCaptureTetrahedraBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_get_probe_capture_tetrahedra", LIGHTMAP_GET_PROBE_CAPTURE_TETRAHEDRA_HASH)
    }

    private const val LIGHTMAP_GET_PROBE_CAPTURE_BSP_TREE_HASH = 788230395L
    private val lightmapGetProbeCaptureBspTreeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_get_probe_capture_bsp_tree", LIGHTMAP_GET_PROBE_CAPTURE_BSP_TREE_HASH)
    }

    private const val LIGHTMAP_SET_BAKED_EXPOSURE_NORMALIZATION_HASH = 1794382983L
    private val lightmapSetBakedExposureNormalizationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_set_baked_exposure_normalization", LIGHTMAP_SET_BAKED_EXPOSURE_NORMALIZATION_HASH)
    }

    private const val LIGHTMAP_SET_PROBE_CAPTURE_UPDATE_SPEED_HASH = 373806689L
    private val lightmapSetProbeCaptureUpdateSpeedBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_set_probe_capture_update_speed", LIGHTMAP_SET_PROBE_CAPTURE_UPDATE_SPEED_HASH)
    }

    private const val PARTICLES_CREATE_HASH = 529393457L
    private val particlesCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_create", PARTICLES_CREATE_HASH)
    }

    private const val PARTICLES_SET_MODE_HASH = 3492270028L
    private val particlesSetModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_mode", PARTICLES_SET_MODE_HASH)
    }

    private const val PARTICLES_SET_EMITTING_HASH = 1265174801L
    private val particlesSetEmittingBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_emitting", PARTICLES_SET_EMITTING_HASH)
    }

    private const val PARTICLES_GET_EMITTING_HASH = 3521089500L
    private val particlesGetEmittingBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_get_emitting", PARTICLES_GET_EMITTING_HASH)
    }

    private const val PARTICLES_SET_AMOUNT_HASH = 3411492887L
    private val particlesSetAmountBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_amount", PARTICLES_SET_AMOUNT_HASH)
    }

    private const val PARTICLES_SET_AMOUNT_RATIO_HASH = 1794382983L
    private val particlesSetAmountRatioBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_amount_ratio", PARTICLES_SET_AMOUNT_RATIO_HASH)
    }

    private const val PARTICLES_SET_LIFETIME_HASH = 1794382983L
    private val particlesSetLifetimeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_lifetime", PARTICLES_SET_LIFETIME_HASH)
    }

    private const val PARTICLES_SET_ONE_SHOT_HASH = 1265174801L
    private val particlesSetOneShotBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_one_shot", PARTICLES_SET_ONE_SHOT_HASH)
    }

    private const val PARTICLES_SET_PRE_PROCESS_TIME_HASH = 1794382983L
    private val particlesSetPreProcessTimeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_pre_process_time", PARTICLES_SET_PRE_PROCESS_TIME_HASH)
    }

    private const val PARTICLES_REQUEST_PROCESS_TIME_HASH = 1515254041L
    private val particlesRequestProcessTimeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_request_process_time", PARTICLES_REQUEST_PROCESS_TIME_HASH)
    }

    private const val PARTICLES_SET_EXPLOSIVENESS_RATIO_HASH = 1794382983L
    private val particlesSetExplosivenessRatioBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_explosiveness_ratio", PARTICLES_SET_EXPLOSIVENESS_RATIO_HASH)
    }

    private const val PARTICLES_SET_RANDOMNESS_RATIO_HASH = 1794382983L
    private val particlesSetRandomnessRatioBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_randomness_ratio", PARTICLES_SET_RANDOMNESS_RATIO_HASH)
    }

    private const val PARTICLES_SET_INTERP_TO_END_HASH = 1794382983L
    private val particlesSetInterpToEndBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_interp_to_end", PARTICLES_SET_INTERP_TO_END_HASH)
    }

    private const val PARTICLES_SET_EMITTER_VELOCITY_HASH = 3227306858L
    private val particlesSetEmitterVelocityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_emitter_velocity", PARTICLES_SET_EMITTER_VELOCITY_HASH)
    }

    private const val PARTICLES_SET_CUSTOM_AABB_HASH = 3696536120L
    private val particlesSetCustomAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_custom_aabb", PARTICLES_SET_CUSTOM_AABB_HASH)
    }

    private const val PARTICLES_SET_SPEED_SCALE_HASH = 1794382983L
    private val particlesSetSpeedScaleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_speed_scale", PARTICLES_SET_SPEED_SCALE_HASH)
    }

    private const val PARTICLES_SET_USE_LOCAL_COORDINATES_HASH = 1265174801L
    private val particlesSetUseLocalCoordinatesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_use_local_coordinates", PARTICLES_SET_USE_LOCAL_COORDINATES_HASH)
    }

    private const val PARTICLES_SET_PROCESS_MATERIAL_HASH = 395945892L
    private val particlesSetProcessMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_process_material", PARTICLES_SET_PROCESS_MATERIAL_HASH)
    }

    private const val PARTICLES_SET_FIXED_FPS_HASH = 3411492887L
    private val particlesSetFixedFpsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_fixed_fps", PARTICLES_SET_FIXED_FPS_HASH)
    }

    private const val PARTICLES_SET_INTERPOLATE_HASH = 1265174801L
    private val particlesSetInterpolateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_interpolate", PARTICLES_SET_INTERPOLATE_HASH)
    }

    private const val PARTICLES_SET_FRACTIONAL_DELTA_HASH = 1265174801L
    private val particlesSetFractionalDeltaBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_fractional_delta", PARTICLES_SET_FRACTIONAL_DELTA_HASH)
    }

    private const val PARTICLES_SET_COLLISION_BASE_SIZE_HASH = 1794382983L
    private val particlesSetCollisionBaseSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_collision_base_size", PARTICLES_SET_COLLISION_BASE_SIZE_HASH)
    }

    private const val PARTICLES_SET_TRANSFORM_ALIGN_HASH = 3264971368L
    private val particlesSetTransformAlignBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_transform_align", PARTICLES_SET_TRANSFORM_ALIGN_HASH)
    }

    private const val PARTICLES_SET_TRANSFORM_ALIGN_CHANNEL_FILTER_HASH = 1303285813L
    private val particlesSetTransformAlignChannelFilterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_transform_align_channel_filter", PARTICLES_SET_TRANSFORM_ALIGN_CHANNEL_FILTER_HASH)
    }

    private const val PARTICLES_SET_TRANSFORM_ALIGN_AXIS_HASH = 3065310065L
    private val particlesSetTransformAlignAxisBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_transform_align_axis", PARTICLES_SET_TRANSFORM_ALIGN_AXIS_HASH)
    }

    private const val PARTICLES_SET_TRAILS_HASH = 2010054925L
    private val particlesSetTrailsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_trails", PARTICLES_SET_TRAILS_HASH)
    }

    private const val PARTICLES_SET_TRAIL_BIND_POSES_HASH = 684822712L
    private val particlesSetTrailBindPosesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_trail_bind_poses", PARTICLES_SET_TRAIL_BIND_POSES_HASH)
    }

    private const val PARTICLES_IS_INACTIVE_HASH = 3521089500L
    private val particlesIsInactiveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_is_inactive", PARTICLES_IS_INACTIVE_HASH)
    }

    private const val PARTICLES_REQUEST_PROCESS_HASH = 2722037293L
    private val particlesRequestProcessBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_request_process", PARTICLES_REQUEST_PROCESS_HASH)
    }

    private const val PARTICLES_RESTART_HASH = 2722037293L
    private val particlesRestartBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_restart", PARTICLES_RESTART_HASH)
    }

    private const val PARTICLES_SET_SUBEMITTER_HASH = 395945892L
    private val particlesSetSubemitterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_subemitter", PARTICLES_SET_SUBEMITTER_HASH)
    }

    private const val PARTICLES_EMIT_HASH = 4043136117L
    private val particlesEmitBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_emit", PARTICLES_EMIT_HASH)
    }

    private const val PARTICLES_SET_DRAW_ORDER_HASH = 935028487L
    private val particlesSetDrawOrderBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_draw_order", PARTICLES_SET_DRAW_ORDER_HASH)
    }

    private const val PARTICLES_SET_DRAW_PASSES_HASH = 3411492887L
    private val particlesSetDrawPassesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_draw_passes", PARTICLES_SET_DRAW_PASSES_HASH)
    }

    private const val PARTICLES_SET_DRAW_PASS_MESH_HASH = 2310537182L
    private val particlesSetDrawPassMeshBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_draw_pass_mesh", PARTICLES_SET_DRAW_PASS_MESH_HASH)
    }

    private const val PARTICLES_GET_CURRENT_AABB_HASH = 3952830260L
    private val particlesGetCurrentAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_get_current_aabb", PARTICLES_GET_CURRENT_AABB_HASH)
    }

    private const val PARTICLES_SET_EMISSION_TRANSFORM_HASH = 3935195649L
    private val particlesSetEmissionTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_emission_transform", PARTICLES_SET_EMISSION_TRANSFORM_HASH)
    }

    private const val PARTICLES_COLLISION_CREATE_HASH = 529393457L
    private val particlesCollisionCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_create", PARTICLES_COLLISION_CREATE_HASH)
    }

    private const val PARTICLES_COLLISION_SET_COLLISION_TYPE_HASH = 1497044930L
    private val particlesCollisionSetCollisionTypeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_collision_type", PARTICLES_COLLISION_SET_COLLISION_TYPE_HASH)
    }

    private const val PARTICLES_COLLISION_SET_CULL_MASK_HASH = 3411492887L
    private val particlesCollisionSetCullMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_cull_mask", PARTICLES_COLLISION_SET_CULL_MASK_HASH)
    }

    private const val PARTICLES_COLLISION_SET_SPHERE_RADIUS_HASH = 1794382983L
    private val particlesCollisionSetSphereRadiusBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_sphere_radius", PARTICLES_COLLISION_SET_SPHERE_RADIUS_HASH)
    }

    private const val PARTICLES_COLLISION_SET_BOX_EXTENTS_HASH = 3227306858L
    private val particlesCollisionSetBoxExtentsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_box_extents", PARTICLES_COLLISION_SET_BOX_EXTENTS_HASH)
    }

    private const val PARTICLES_COLLISION_SET_ATTRACTOR_STRENGTH_HASH = 1794382983L
    private val particlesCollisionSetAttractorStrengthBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_attractor_strength", PARTICLES_COLLISION_SET_ATTRACTOR_STRENGTH_HASH)
    }

    private const val PARTICLES_COLLISION_SET_ATTRACTOR_DIRECTIONALITY_HASH = 1794382983L
    private val particlesCollisionSetAttractorDirectionalityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_attractor_directionality", PARTICLES_COLLISION_SET_ATTRACTOR_DIRECTIONALITY_HASH)
    }

    private const val PARTICLES_COLLISION_SET_ATTRACTOR_ATTENUATION_HASH = 1794382983L
    private val particlesCollisionSetAttractorAttenuationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_attractor_attenuation", PARTICLES_COLLISION_SET_ATTRACTOR_ATTENUATION_HASH)
    }

    private const val PARTICLES_COLLISION_SET_FIELD_TEXTURE_HASH = 395945892L
    private val particlesCollisionSetFieldTextureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_field_texture", PARTICLES_COLLISION_SET_FIELD_TEXTURE_HASH)
    }

    private const val PARTICLES_COLLISION_HEIGHT_FIELD_UPDATE_HASH = 2722037293L
    private val particlesCollisionHeightFieldUpdateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_height_field_update", PARTICLES_COLLISION_HEIGHT_FIELD_UPDATE_HASH)
    }

    private const val PARTICLES_COLLISION_SET_HEIGHT_FIELD_RESOLUTION_HASH = 962977297L
    private val particlesCollisionSetHeightFieldResolutionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_height_field_resolution", PARTICLES_COLLISION_SET_HEIGHT_FIELD_RESOLUTION_HASH)
    }

    private const val PARTICLES_COLLISION_SET_HEIGHT_FIELD_MASK_HASH = 3411492887L
    private val particlesCollisionSetHeightFieldMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_height_field_mask", PARTICLES_COLLISION_SET_HEIGHT_FIELD_MASK_HASH)
    }

    private const val FOG_VOLUME_CREATE_HASH = 529393457L
    private val fogVolumeCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "fog_volume_create", FOG_VOLUME_CREATE_HASH)
    }

    private const val FOG_VOLUME_SET_SHAPE_HASH = 3818703106L
    private val fogVolumeSetShapeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "fog_volume_set_shape", FOG_VOLUME_SET_SHAPE_HASH)
    }

    private const val FOG_VOLUME_SET_SIZE_HASH = 3227306858L
    private val fogVolumeSetSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "fog_volume_set_size", FOG_VOLUME_SET_SIZE_HASH)
    }

    private const val FOG_VOLUME_SET_MATERIAL_HASH = 395945892L
    private val fogVolumeSetMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "fog_volume_set_material", FOG_VOLUME_SET_MATERIAL_HASH)
    }

    private const val VISIBILITY_NOTIFIER_CREATE_HASH = 529393457L
    private val visibilityNotifierCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "visibility_notifier_create", VISIBILITY_NOTIFIER_CREATE_HASH)
    }

    private const val VISIBILITY_NOTIFIER_SET_AABB_HASH = 3696536120L
    private val visibilityNotifierSetAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "visibility_notifier_set_aabb", VISIBILITY_NOTIFIER_SET_AABB_HASH)
    }

    private const val VISIBILITY_NOTIFIER_SET_CALLBACKS_HASH = 2689735388L
    private val visibilityNotifierSetCallbacksBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "visibility_notifier_set_callbacks", VISIBILITY_NOTIFIER_SET_CALLBACKS_HASH)
    }

    private const val OCCLUDER_CREATE_HASH = 529393457L
    private val occluderCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "occluder_create", OCCLUDER_CREATE_HASH)
    }

    private const val OCCLUDER_SET_MESH_HASH = 3854404263L
    private val occluderSetMeshBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "occluder_set_mesh", OCCLUDER_SET_MESH_HASH)
    }

    private const val CAMERA_CREATE_HASH = 529393457L
    private val cameraCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_create", CAMERA_CREATE_HASH)
    }

    private const val CAMERA_SET_PERSPECTIVE_HASH = 157498339L
    private val cameraSetPerspectiveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_perspective", CAMERA_SET_PERSPECTIVE_HASH)
    }

    private const val CAMERA_SET_ORTHOGONAL_HASH = 157498339L
    private val cameraSetOrthogonalBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_orthogonal", CAMERA_SET_ORTHOGONAL_HASH)
    }

    private const val CAMERA_SET_FRUSTUM_HASH = 1889878953L
    private val cameraSetFrustumBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_frustum", CAMERA_SET_FRUSTUM_HASH)
    }

    private const val CAMERA_SET_TRANSFORM_HASH = 3935195649L
    private val cameraSetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_transform", CAMERA_SET_TRANSFORM_HASH)
    }

    private const val CAMERA_SET_CULL_MASK_HASH = 3411492887L
    private val cameraSetCullMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_cull_mask", CAMERA_SET_CULL_MASK_HASH)
    }

    private const val CAMERA_SET_ENVIRONMENT_HASH = 395945892L
    private val cameraSetEnvironmentBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_environment", CAMERA_SET_ENVIRONMENT_HASH)
    }

    private const val CAMERA_SET_CAMERA_ATTRIBUTES_HASH = 395945892L
    private val cameraSetCameraAttributesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_camera_attributes", CAMERA_SET_CAMERA_ATTRIBUTES_HASH)
    }

    private const val CAMERA_SET_COMPOSITOR_HASH = 395945892L
    private val cameraSetCompositorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_compositor", CAMERA_SET_COMPOSITOR_HASH)
    }

    private const val CAMERA_SET_USE_VERTICAL_ASPECT_HASH = 1265174801L
    private val cameraSetUseVerticalAspectBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_use_vertical_aspect", CAMERA_SET_USE_VERTICAL_ASPECT_HASH)
    }

    private const val VIEWPORT_CREATE_HASH = 529393457L
    private val viewportCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_create", VIEWPORT_CREATE_HASH)
    }

    private const val VIEWPORT_SET_USE_XR_HASH = 1265174801L
    private val viewportSetUseXrBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_use_xr", VIEWPORT_SET_USE_XR_HASH)
    }

    private const val VIEWPORT_SET_SIZE_HASH = 3313592705L
    private val viewportSetSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_size", VIEWPORT_SET_SIZE_HASH)
    }

    private const val VIEWPORT_SET_ACTIVE_HASH = 1265174801L
    private val viewportSetActiveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_active", VIEWPORT_SET_ACTIVE_HASH)
    }

    private const val VIEWPORT_SET_PARENT_VIEWPORT_HASH = 395945892L
    private val viewportSetParentViewportBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_parent_viewport", VIEWPORT_SET_PARENT_VIEWPORT_HASH)
    }

    private const val VIEWPORT_ATTACH_TO_SCREEN_HASH = 1062245816L
    private val viewportAttachToScreenBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_attach_to_screen", VIEWPORT_ATTACH_TO_SCREEN_HASH)
    }

    private const val VIEWPORT_SET_RENDER_DIRECT_TO_SCREEN_HASH = 1265174801L
    private val viewportSetRenderDirectToScreenBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_render_direct_to_screen", VIEWPORT_SET_RENDER_DIRECT_TO_SCREEN_HASH)
    }

    private const val VIEWPORT_SET_CANVAS_CULL_MASK_HASH = 3411492887L
    private val viewportSetCanvasCullMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_canvas_cull_mask", VIEWPORT_SET_CANVAS_CULL_MASK_HASH)
    }

    private const val VIEWPORT_SET_SCALING_3D_MODE_HASH = 2386524376L
    private val viewportSetScaling3dModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_scaling_3d_mode", VIEWPORT_SET_SCALING_3D_MODE_HASH)
    }

    private const val VIEWPORT_SET_SCALING_3D_SCALE_HASH = 1794382983L
    private val viewportSetScaling3dScaleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_scaling_3d_scale", VIEWPORT_SET_SCALING_3D_SCALE_HASH)
    }

    private const val VIEWPORT_SET_FSR_SHARPNESS_HASH = 1794382983L
    private val viewportSetFsrSharpnessBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_fsr_sharpness", VIEWPORT_SET_FSR_SHARPNESS_HASH)
    }

    private const val VIEWPORT_SET_TEXTURE_MIPMAP_BIAS_HASH = 1794382983L
    private val viewportSetTextureMipmapBiasBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_texture_mipmap_bias", VIEWPORT_SET_TEXTURE_MIPMAP_BIAS_HASH)
    }

    private const val VIEWPORT_SET_ANISOTROPIC_FILTERING_LEVEL_HASH = 3953214029L
    private val viewportSetAnisotropicFilteringLevelBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_anisotropic_filtering_level", VIEWPORT_SET_ANISOTROPIC_FILTERING_LEVEL_HASH)
    }

    private const val VIEWPORT_SET_UPDATE_MODE_HASH = 3161116010L
    private val viewportSetUpdateModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_update_mode", VIEWPORT_SET_UPDATE_MODE_HASH)
    }

    private const val VIEWPORT_GET_UPDATE_MODE_HASH = 3803901472L
    private val viewportGetUpdateModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_get_update_mode", VIEWPORT_GET_UPDATE_MODE_HASH)
    }

    private const val VIEWPORT_SET_CLEAR_MODE_HASH = 3628367896L
    private val viewportSetClearModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_clear_mode", VIEWPORT_SET_CLEAR_MODE_HASH)
    }

    private const val VIEWPORT_GET_RENDER_TARGET_HASH = 3814569979L
    private val viewportGetRenderTargetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_get_render_target", VIEWPORT_GET_RENDER_TARGET_HASH)
    }

    private const val VIEWPORT_GET_TEXTURE_HASH = 3814569979L
    private val viewportGetTextureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_get_texture", VIEWPORT_GET_TEXTURE_HASH)
    }

    private const val VIEWPORT_SET_DISABLE_3D_HASH = 1265174801L
    private val viewportSetDisable3dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_disable_3d", VIEWPORT_SET_DISABLE_3D_HASH)
    }

    private const val VIEWPORT_SET_DISABLE_2D_HASH = 1265174801L
    private val viewportSetDisable2dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_disable_2d", VIEWPORT_SET_DISABLE_2D_HASH)
    }

    private const val VIEWPORT_SET_ENVIRONMENT_MODE_HASH = 2196892182L
    private val viewportSetEnvironmentModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_environment_mode", VIEWPORT_SET_ENVIRONMENT_MODE_HASH)
    }

    private const val VIEWPORT_ATTACH_CAMERA_HASH = 395945892L
    private val viewportAttachCameraBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_attach_camera", VIEWPORT_ATTACH_CAMERA_HASH)
    }

    private const val VIEWPORT_SET_SCENARIO_HASH = 395945892L
    private val viewportSetScenarioBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_scenario", VIEWPORT_SET_SCENARIO_HASH)
    }

    private const val VIEWPORT_ATTACH_CANVAS_HASH = 395945892L
    private val viewportAttachCanvasBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_attach_canvas", VIEWPORT_ATTACH_CANVAS_HASH)
    }

    private const val VIEWPORT_REMOVE_CANVAS_HASH = 395945892L
    private val viewportRemoveCanvasBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_remove_canvas", VIEWPORT_REMOVE_CANVAS_HASH)
    }

    private const val VIEWPORT_SET_SNAP_2D_TRANSFORMS_TO_PIXEL_HASH = 1265174801L
    private val viewportSetSnap2dTransformsToPixelBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_snap_2d_transforms_to_pixel", VIEWPORT_SET_SNAP_2D_TRANSFORMS_TO_PIXEL_HASH)
    }

    private const val VIEWPORT_SET_SNAP_2D_VERTICES_TO_PIXEL_HASH = 1265174801L
    private val viewportSetSnap2dVerticesToPixelBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_snap_2d_vertices_to_pixel", VIEWPORT_SET_SNAP_2D_VERTICES_TO_PIXEL_HASH)
    }

    private const val VIEWPORT_SET_DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_HASH = 1155129294L
    private val viewportSetDefaultCanvasItemTextureFilterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_default_canvas_item_texture_filter", VIEWPORT_SET_DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_HASH)
    }

    private const val VIEWPORT_SET_DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_HASH = 1652956681L
    private val viewportSetDefaultCanvasItemTextureRepeatBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_default_canvas_item_texture_repeat", VIEWPORT_SET_DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_HASH)
    }

    private const val VIEWPORT_SET_CANVAS_TRANSFORM_HASH = 3608606053L
    private val viewportSetCanvasTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_canvas_transform", VIEWPORT_SET_CANVAS_TRANSFORM_HASH)
    }

    private const val VIEWPORT_SET_CANVAS_STACKING_HASH = 3713930247L
    private val viewportSetCanvasStackingBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_canvas_stacking", VIEWPORT_SET_CANVAS_STACKING_HASH)
    }

    private const val VIEWPORT_SET_TRANSPARENT_BACKGROUND_HASH = 1265174801L
    private val viewportSetTransparentBackgroundBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_transparent_background", VIEWPORT_SET_TRANSPARENT_BACKGROUND_HASH)
    }

    private const val VIEWPORT_SET_GLOBAL_CANVAS_TRANSFORM_HASH = 1246044741L
    private val viewportSetGlobalCanvasTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_global_canvas_transform", VIEWPORT_SET_GLOBAL_CANVAS_TRANSFORM_HASH)
    }

    private const val VIEWPORT_SET_SDF_OVERSIZE_AND_SCALE_HASH = 1329198632L
    private val viewportSetSdfOversizeAndScaleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_sdf_oversize_and_scale", VIEWPORT_SET_SDF_OVERSIZE_AND_SCALE_HASH)
    }

    private const val VIEWPORT_SET_POSITIONAL_SHADOW_ATLAS_SIZE_HASH = 1904426712L
    private val viewportSetPositionalShadowAtlasSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_positional_shadow_atlas_size", VIEWPORT_SET_POSITIONAL_SHADOW_ATLAS_SIZE_HASH)
    }

    private const val VIEWPORT_SET_POSITIONAL_SHADOW_ATLAS_QUADRANT_SUBDIVISION_HASH = 4288446313L
    private val viewportSetPositionalShadowAtlasQuadrantSubdivisionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_positional_shadow_atlas_quadrant_subdivision", VIEWPORT_SET_POSITIONAL_SHADOW_ATLAS_QUADRANT_SUBDIVISION_HASH)
    }

    private const val VIEWPORT_SET_MSAA_3D_HASH = 3764433340L
    private val viewportSetMsaa3dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_msaa_3d", VIEWPORT_SET_MSAA_3D_HASH)
    }

    private const val VIEWPORT_SET_MSAA_2D_HASH = 3764433340L
    private val viewportSetMsaa2dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_msaa_2d", VIEWPORT_SET_MSAA_2D_HASH)
    }

    private const val VIEWPORT_SET_USE_HDR_2D_HASH = 1265174801L
    private val viewportSetUseHdr2dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_use_hdr_2d", VIEWPORT_SET_USE_HDR_2D_HASH)
    }

    private const val VIEWPORT_SET_SCREEN_SPACE_AA_HASH = 1447279591L
    private val viewportSetScreenSpaceAaBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_screen_space_aa", VIEWPORT_SET_SCREEN_SPACE_AA_HASH)
    }

    private const val VIEWPORT_SET_USE_TAA_HASH = 1265174801L
    private val viewportSetUseTaaBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_use_taa", VIEWPORT_SET_USE_TAA_HASH)
    }

    private const val VIEWPORT_SET_USE_DEBANDING_HASH = 1265174801L
    private val viewportSetUseDebandingBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_use_debanding", VIEWPORT_SET_USE_DEBANDING_HASH)
    }

    private const val VIEWPORT_SET_USE_OCCLUSION_CULLING_HASH = 1265174801L
    private val viewportSetUseOcclusionCullingBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_use_occlusion_culling", VIEWPORT_SET_USE_OCCLUSION_CULLING_HASH)
    }

    private const val VIEWPORT_SET_OCCLUSION_RAYS_PER_THREAD_HASH = 1286410249L
    private val viewportSetOcclusionRaysPerThreadBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_occlusion_rays_per_thread", VIEWPORT_SET_OCCLUSION_RAYS_PER_THREAD_HASH)
    }

    private const val VIEWPORT_SET_OCCLUSION_CULLING_BUILD_QUALITY_HASH = 2069725696L
    private val viewportSetOcclusionCullingBuildQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_occlusion_culling_build_quality", VIEWPORT_SET_OCCLUSION_CULLING_BUILD_QUALITY_HASH)
    }

    private const val VIEWPORT_GET_RENDER_INFO_HASH = 2041262392L
    private val viewportGetRenderInfoBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_get_render_info", VIEWPORT_GET_RENDER_INFO_HASH)
    }

    private const val VIEWPORT_SET_DEBUG_DRAW_HASH = 2089420930L
    private val viewportSetDebugDrawBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_debug_draw", VIEWPORT_SET_DEBUG_DRAW_HASH)
    }

    private const val VIEWPORT_SET_MEASURE_RENDER_TIME_HASH = 1265174801L
    private val viewportSetMeasureRenderTimeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_measure_render_time", VIEWPORT_SET_MEASURE_RENDER_TIME_HASH)
    }

    private const val VIEWPORT_GET_MEASURED_RENDER_TIME_CPU_HASH = 866169185L
    private val viewportGetMeasuredRenderTimeCpuBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_get_measured_render_time_cpu", VIEWPORT_GET_MEASURED_RENDER_TIME_CPU_HASH)
    }

    private const val VIEWPORT_GET_MEASURED_RENDER_TIME_GPU_HASH = 866169185L
    private val viewportGetMeasuredRenderTimeGpuBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_get_measured_render_time_gpu", VIEWPORT_GET_MEASURED_RENDER_TIME_GPU_HASH)
    }

    private const val VIEWPORT_SET_VRS_MODE_HASH = 398809874L
    private val viewportSetVrsModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_vrs_mode", VIEWPORT_SET_VRS_MODE_HASH)
    }

    private const val VIEWPORT_SET_VRS_UPDATE_MODE_HASH = 2696154815L
    private val viewportSetVrsUpdateModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_vrs_update_mode", VIEWPORT_SET_VRS_UPDATE_MODE_HASH)
    }

    private const val VIEWPORT_SET_VRS_TEXTURE_HASH = 395945892L
    private val viewportSetVrsTextureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_vrs_texture", VIEWPORT_SET_VRS_TEXTURE_HASH)
    }

    private const val SKY_CREATE_HASH = 529393457L
    private val skyCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "sky_create", SKY_CREATE_HASH)
    }

    private const val SKY_SET_RADIANCE_SIZE_HASH = 3411492887L
    private val skySetRadianceSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "sky_set_radiance_size", SKY_SET_RADIANCE_SIZE_HASH)
    }

    private const val SKY_SET_MODE_HASH = 3279019937L
    private val skySetModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "sky_set_mode", SKY_SET_MODE_HASH)
    }

    private const val SKY_SET_MATERIAL_HASH = 395945892L
    private val skySetMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "sky_set_material", SKY_SET_MATERIAL_HASH)
    }

    private const val SKY_BAKE_PANORAMA_HASH = 3875285818L
    private val skyBakePanoramaBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "sky_bake_panorama", SKY_BAKE_PANORAMA_HASH)
    }

    private const val COMPOSITOR_EFFECT_CREATE_HASH = 529393457L
    private val compositorEffectCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "compositor_effect_create", COMPOSITOR_EFFECT_CREATE_HASH)
    }

    private const val COMPOSITOR_EFFECT_SET_ENABLED_HASH = 1265174801L
    private val compositorEffectSetEnabledBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "compositor_effect_set_enabled", COMPOSITOR_EFFECT_SET_ENABLED_HASH)
    }

    private const val COMPOSITOR_EFFECT_SET_CALLBACK_HASH = 487412485L
    private val compositorEffectSetCallbackBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "compositor_effect_set_callback", COMPOSITOR_EFFECT_SET_CALLBACK_HASH)
    }

    private const val COMPOSITOR_EFFECT_SET_FLAG_HASH = 3659527075L
    private val compositorEffectSetFlagBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "compositor_effect_set_flag", COMPOSITOR_EFFECT_SET_FLAG_HASH)
    }

    private const val COMPOSITOR_CREATE_HASH = 529393457L
    private val compositorCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "compositor_create", COMPOSITOR_CREATE_HASH)
    }

    private const val COMPOSITOR_SET_COMPOSITOR_EFFECTS_HASH = 684822712L
    private val compositorSetCompositorEffectsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "compositor_set_compositor_effects", COMPOSITOR_SET_COMPOSITOR_EFFECTS_HASH)
    }

    private const val ENVIRONMENT_CREATE_HASH = 529393457L
    private val environmentCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_create", ENVIRONMENT_CREATE_HASH)
    }

    private const val ENVIRONMENT_SET_BACKGROUND_HASH = 3937328877L
    private val environmentSetBackgroundBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_background", ENVIRONMENT_SET_BACKGROUND_HASH)
    }

    private const val ENVIRONMENT_SET_CAMERA_ID_HASH = 3411492887L
    private val environmentSetCameraIdBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_camera_id", ENVIRONMENT_SET_CAMERA_ID_HASH)
    }

    private const val ENVIRONMENT_SET_SKY_HASH = 395945892L
    private val environmentSetSkyBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_sky", ENVIRONMENT_SET_SKY_HASH)
    }

    private const val ENVIRONMENT_SET_SKY_CUSTOM_FOV_HASH = 1794382983L
    private val environmentSetSkyCustomFovBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_sky_custom_fov", ENVIRONMENT_SET_SKY_CUSTOM_FOV_HASH)
    }

    private const val ENVIRONMENT_SET_SKY_ORIENTATION_HASH = 1735850857L
    private val environmentSetSkyOrientationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_sky_orientation", ENVIRONMENT_SET_SKY_ORIENTATION_HASH)
    }

    private const val ENVIRONMENT_SET_BG_COLOR_HASH = 2948539648L
    private val environmentSetBgColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_bg_color", ENVIRONMENT_SET_BG_COLOR_HASH)
    }

    private const val ENVIRONMENT_SET_BG_ENERGY_HASH = 2513314492L
    private val environmentSetBgEnergyBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_bg_energy", ENVIRONMENT_SET_BG_ENERGY_HASH)
    }

    private const val ENVIRONMENT_SET_CANVAS_MAX_LAYER_HASH = 3411492887L
    private val environmentSetCanvasMaxLayerBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_canvas_max_layer", ENVIRONMENT_SET_CANVAS_MAX_LAYER_HASH)
    }

    private const val ENVIRONMENT_SET_AMBIENT_LIGHT_HASH = 1214961493L
    private val environmentSetAmbientLightBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_ambient_light", ENVIRONMENT_SET_AMBIENT_LIGHT_HASH)
    }

    private const val ENVIRONMENT_SET_GLOW_HASH = 2421724940L
    private val environmentSetGlowBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_glow", ENVIRONMENT_SET_GLOW_HASH)
    }

    private const val ENVIRONMENT_SET_TONEMAP_HASH = 2914312638L
    private val environmentSetTonemapBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_tonemap", ENVIRONMENT_SET_TONEMAP_HASH)
    }

    private const val ENVIRONMENT_SET_TONEMAP_AGX_CONTRAST_HASH = 1794382983L
    private val environmentSetTonemapAgxContrastBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_tonemap_agx_contrast", ENVIRONMENT_SET_TONEMAP_AGX_CONTRAST_HASH)
    }

    private const val ENVIRONMENT_SET_ADJUSTMENT_HASH = 876799838L
    private val environmentSetAdjustmentBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_adjustment", ENVIRONMENT_SET_ADJUSTMENT_HASH)
    }

    private const val ENVIRONMENT_SET_SSR_HASH = 3607294374L
    private val environmentSetSsrBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_ssr", ENVIRONMENT_SET_SSR_HASH)
    }

    private const val ENVIRONMENT_SET_SSAO_HASH = 3994732740L
    private val environmentSetSsaoBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_ssao", ENVIRONMENT_SET_SSAO_HASH)
    }

    private const val ENVIRONMENT_SET_FOG_HASH = 105051629L
    private val environmentSetFogBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_fog", ENVIRONMENT_SET_FOG_HASH)
    }

    private const val ENVIRONMENT_SET_FOG_DEPTH_HASH = 157498339L
    private val environmentSetFogDepthBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_fog_depth", ENVIRONMENT_SET_FOG_DEPTH_HASH)
    }

    private const val ENVIRONMENT_SET_SDFGI_HASH = 3519144388L
    private val environmentSetSdfgiBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_sdfgi", ENVIRONMENT_SET_SDFGI_HASH)
    }

    private const val ENVIRONMENT_SET_VOLUMETRIC_FOG_HASH = 1553633833L
    private val environmentSetVolumetricFogBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_volumetric_fog", ENVIRONMENT_SET_VOLUMETRIC_FOG_HASH)
    }

    private const val ENVIRONMENT_GLOW_SET_USE_BICUBIC_UPSCALE_HASH = 2586408642L
    private val environmentGlowSetUseBicubicUpscaleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_glow_set_use_bicubic_upscale", ENVIRONMENT_GLOW_SET_USE_BICUBIC_UPSCALE_HASH)
    }

    private const val ENVIRONMENT_SET_SSR_HALF_SIZE_HASH = 2586408642L
    private val environmentSetSsrHalfSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_ssr_half_size", ENVIRONMENT_SET_SSR_HALF_SIZE_HASH)
    }

    private const val ENVIRONMENT_SET_SSR_ROUGHNESS_QUALITY_HASH = 1190026788L
    private val environmentSetSsrRoughnessQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_ssr_roughness_quality", ENVIRONMENT_SET_SSR_ROUGHNESS_QUALITY_HASH)
    }

    private const val ENVIRONMENT_SET_SSAO_QUALITY_HASH = 189753569L
    private val environmentSetSsaoQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_ssao_quality", ENVIRONMENT_SET_SSAO_QUALITY_HASH)
    }

    private const val ENVIRONMENT_SET_SSIL_QUALITY_HASH = 1713836683L
    private val environmentSetSsilQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_ssil_quality", ENVIRONMENT_SET_SSIL_QUALITY_HASH)
    }

    private const val ENVIRONMENT_SET_SDFGI_RAY_COUNT_HASH = 340137951L
    private val environmentSetSdfgiRayCountBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_sdfgi_ray_count", ENVIRONMENT_SET_SDFGI_RAY_COUNT_HASH)
    }

    private const val ENVIRONMENT_SET_SDFGI_FRAMES_TO_CONVERGE_HASH = 2182444374L
    private val environmentSetSdfgiFramesToConvergeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_sdfgi_frames_to_converge", ENVIRONMENT_SET_SDFGI_FRAMES_TO_CONVERGE_HASH)
    }

    private const val ENVIRONMENT_SET_SDFGI_FRAMES_TO_UPDATE_LIGHT_HASH = 1251144068L
    private val environmentSetSdfgiFramesToUpdateLightBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_sdfgi_frames_to_update_light", ENVIRONMENT_SET_SDFGI_FRAMES_TO_UPDATE_LIGHT_HASH)
    }

    private const val ENVIRONMENT_SET_VOLUMETRIC_FOG_VOLUME_SIZE_HASH = 3937882851L
    private val environmentSetVolumetricFogVolumeSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_volumetric_fog_volume_size", ENVIRONMENT_SET_VOLUMETRIC_FOG_VOLUME_SIZE_HASH)
    }

    private const val ENVIRONMENT_SET_VOLUMETRIC_FOG_FILTER_ACTIVE_HASH = 2586408642L
    private val environmentSetVolumetricFogFilterActiveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_volumetric_fog_filter_active", ENVIRONMENT_SET_VOLUMETRIC_FOG_FILTER_ACTIVE_HASH)
    }

    private const val ENVIRONMENT_BAKE_PANORAMA_HASH = 2452908646L
    private val environmentBakePanoramaBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_bake_panorama", ENVIRONMENT_BAKE_PANORAMA_HASH)
    }

    private const val SCREEN_SPACE_ROUGHNESS_LIMITER_SET_ACTIVE_HASH = 916716790L
    private val screenSpaceRoughnessLimiterSetActiveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "screen_space_roughness_limiter_set_active", SCREEN_SPACE_ROUGHNESS_LIMITER_SET_ACTIVE_HASH)
    }

    private const val SUB_SURFACE_SCATTERING_SET_QUALITY_HASH = 64571803L
    private val subSurfaceScatteringSetQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "sub_surface_scattering_set_quality", SUB_SURFACE_SCATTERING_SET_QUALITY_HASH)
    }

    private const val SUB_SURFACE_SCATTERING_SET_SCALE_HASH = 1017552074L
    private val subSurfaceScatteringSetScaleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "sub_surface_scattering_set_scale", SUB_SURFACE_SCATTERING_SET_SCALE_HASH)
    }

    private const val CAMERA_ATTRIBUTES_CREATE_HASH = 529393457L
    private val cameraAttributesCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_attributes_create", CAMERA_ATTRIBUTES_CREATE_HASH)
    }

    private const val CAMERA_ATTRIBUTES_SET_DOF_BLUR_QUALITY_HASH = 2220136795L
    private val cameraAttributesSetDofBlurQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_attributes_set_dof_blur_quality", CAMERA_ATTRIBUTES_SET_DOF_BLUR_QUALITY_HASH)
    }

    private const val CAMERA_ATTRIBUTES_SET_DOF_BLUR_BOKEH_SHAPE_HASH = 1205058394L
    private val cameraAttributesSetDofBlurBokehShapeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_attributes_set_dof_blur_bokeh_shape", CAMERA_ATTRIBUTES_SET_DOF_BLUR_BOKEH_SHAPE_HASH)
    }

    private const val CAMERA_ATTRIBUTES_SET_DOF_BLUR_HASH = 316272616L
    private val cameraAttributesSetDofBlurBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_attributes_set_dof_blur", CAMERA_ATTRIBUTES_SET_DOF_BLUR_HASH)
    }

    private const val CAMERA_ATTRIBUTES_SET_EXPOSURE_HASH = 2513314492L
    private val cameraAttributesSetExposureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_attributes_set_exposure", CAMERA_ATTRIBUTES_SET_EXPOSURE_HASH)
    }

    private const val CAMERA_ATTRIBUTES_SET_AUTO_EXPOSURE_HASH = 4266986332L
    private val cameraAttributesSetAutoExposureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_attributes_set_auto_exposure", CAMERA_ATTRIBUTES_SET_AUTO_EXPOSURE_HASH)
    }

    private const val SCENARIO_CREATE_HASH = 529393457L
    private val scenarioCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "scenario_create", SCENARIO_CREATE_HASH)
    }

    private const val SCENARIO_SET_ENVIRONMENT_HASH = 395945892L
    private val scenarioSetEnvironmentBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "scenario_set_environment", SCENARIO_SET_ENVIRONMENT_HASH)
    }

    private const val SCENARIO_SET_FALLBACK_ENVIRONMENT_HASH = 395945892L
    private val scenarioSetFallbackEnvironmentBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "scenario_set_fallback_environment", SCENARIO_SET_FALLBACK_ENVIRONMENT_HASH)
    }

    private const val SCENARIO_SET_CAMERA_ATTRIBUTES_HASH = 395945892L
    private val scenarioSetCameraAttributesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "scenario_set_camera_attributes", SCENARIO_SET_CAMERA_ATTRIBUTES_HASH)
    }

    private const val SCENARIO_SET_COMPOSITOR_HASH = 395945892L
    private val scenarioSetCompositorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "scenario_set_compositor", SCENARIO_SET_COMPOSITOR_HASH)
    }

    private const val INSTANCE_CREATE2_HASH = 746547085L
    private val instanceCreate2Bind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_create2", INSTANCE_CREATE2_HASH)
    }

    private const val INSTANCE_CREATE_HASH = 529393457L
    private val instanceCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_create", INSTANCE_CREATE_HASH)
    }

    private const val INSTANCE_SET_BASE_HASH = 395945892L
    private val instanceSetBaseBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_base", INSTANCE_SET_BASE_HASH)
    }

    private const val INSTANCE_SET_SCENARIO_HASH = 395945892L
    private val instanceSetScenarioBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_scenario", INSTANCE_SET_SCENARIO_HASH)
    }

    private const val INSTANCE_SET_LAYER_MASK_HASH = 3411492887L
    private val instanceSetLayerMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_layer_mask", INSTANCE_SET_LAYER_MASK_HASH)
    }

    private const val INSTANCE_SET_PIVOT_DATA_HASH = 1280615259L
    private val instanceSetPivotDataBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_pivot_data", INSTANCE_SET_PIVOT_DATA_HASH)
    }

    private const val INSTANCE_SET_TRANSFORM_HASH = 3935195649L
    private val instanceSetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_transform", INSTANCE_SET_TRANSFORM_HASH)
    }

    private const val INSTANCE_ATTACH_OBJECT_INSTANCE_ID_HASH = 3411492887L
    private val instanceAttachObjectInstanceIdBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_attach_object_instance_id", INSTANCE_ATTACH_OBJECT_INSTANCE_ID_HASH)
    }

    private const val INSTANCE_SET_BLEND_SHAPE_WEIGHT_HASH = 1892459533L
    private val instanceSetBlendShapeWeightBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_blend_shape_weight", INSTANCE_SET_BLEND_SHAPE_WEIGHT_HASH)
    }

    private const val INSTANCE_SET_SURFACE_OVERRIDE_MATERIAL_HASH = 2310537182L
    private val instanceSetSurfaceOverrideMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_surface_override_material", INSTANCE_SET_SURFACE_OVERRIDE_MATERIAL_HASH)
    }

    private const val INSTANCE_SET_VISIBLE_HASH = 1265174801L
    private val instanceSetVisibleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_visible", INSTANCE_SET_VISIBLE_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_TRANSPARENCY_HASH = 1794382983L
    private val instanceGeometrySetTransparencyBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_transparency", INSTANCE_GEOMETRY_SET_TRANSPARENCY_HASH)
    }

    private const val INSTANCE_TELEPORT_HASH = 2722037293L
    private val instanceTeleportBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_teleport", INSTANCE_TELEPORT_HASH)
    }

    private const val INSTANCE_SET_CUSTOM_AABB_HASH = 3696536120L
    private val instanceSetCustomAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_custom_aabb", INSTANCE_SET_CUSTOM_AABB_HASH)
    }

    private const val INSTANCE_ATTACH_SKELETON_HASH = 395945892L
    private val instanceAttachSkeletonBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_attach_skeleton", INSTANCE_ATTACH_SKELETON_HASH)
    }

    private const val INSTANCE_SET_EXTRA_VISIBILITY_MARGIN_HASH = 1794382983L
    private val instanceSetExtraVisibilityMarginBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_extra_visibility_margin", INSTANCE_SET_EXTRA_VISIBILITY_MARGIN_HASH)
    }

    private const val INSTANCE_SET_VISIBILITY_PARENT_HASH = 395945892L
    private val instanceSetVisibilityParentBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_visibility_parent", INSTANCE_SET_VISIBILITY_PARENT_HASH)
    }

    private const val INSTANCE_SET_IGNORE_CULLING_HASH = 1265174801L
    private val instanceSetIgnoreCullingBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_ignore_culling", INSTANCE_SET_IGNORE_CULLING_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_FLAG_HASH = 1014989537L
    private val instanceGeometrySetFlagBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_flag", INSTANCE_GEOMETRY_SET_FLAG_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_CAST_SHADOWS_SETTING_HASH = 3768836020L
    private val instanceGeometrySetCastShadowsSettingBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_cast_shadows_setting", INSTANCE_GEOMETRY_SET_CAST_SHADOWS_SETTING_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_MATERIAL_OVERRIDE_HASH = 395945892L
    private val instanceGeometrySetMaterialOverrideBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_material_override", INSTANCE_GEOMETRY_SET_MATERIAL_OVERRIDE_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_MATERIAL_OVERLAY_HASH = 395945892L
    private val instanceGeometrySetMaterialOverlayBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_material_overlay", INSTANCE_GEOMETRY_SET_MATERIAL_OVERLAY_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_VISIBILITY_RANGE_HASH = 4263925858L
    private val instanceGeometrySetVisibilityRangeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_visibility_range", INSTANCE_GEOMETRY_SET_VISIBILITY_RANGE_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_LIGHTMAP_HASH = 536974962L
    private val instanceGeometrySetLightmapBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_lightmap", INSTANCE_GEOMETRY_SET_LIGHTMAP_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_LOD_BIAS_HASH = 1794382983L
    private val instanceGeometrySetLodBiasBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_lod_bias", INSTANCE_GEOMETRY_SET_LOD_BIAS_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_SHADER_PARAMETER_HASH = 3477296213L
    private val instanceGeometrySetShaderParameterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_shader_parameter", INSTANCE_GEOMETRY_SET_SHADER_PARAMETER_HASH)
    }

    private const val INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_HASH = 2621281810L
    private val instanceGeometryGetShaderParameterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_get_shader_parameter", INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_HASH)
    }

    private const val INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_DEFAULT_VALUE_HASH = 2621281810L
    private val instanceGeometryGetShaderParameterDefaultValueBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_get_shader_parameter_default_value", INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_DEFAULT_VALUE_HASH)
    }

    private const val INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_LIST_HASH = 2684255073L
    private val instanceGeometryGetShaderParameterListBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_get_shader_parameter_list", INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_LIST_HASH)
    }

    private const val INSTANCES_CULL_AABB_HASH = 2570105777L
    private val instancesCullAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instances_cull_aabb", INSTANCES_CULL_AABB_HASH)
    }

    private const val INSTANCES_CULL_RAY_HASH = 2208759584L
    private val instancesCullRayBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instances_cull_ray", INSTANCES_CULL_RAY_HASH)
    }

    private const val INSTANCES_CULL_CONVEX_HASH = 2488539944L
    private val instancesCullConvexBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instances_cull_convex", INSTANCES_CULL_CONVEX_HASH)
    }

    private const val BAKE_RENDER_UV2_HASH = 1904608558L
    private val bakeRenderUv2Bind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "bake_render_uv2", BAKE_RENDER_UV2_HASH)
    }

    private const val CANVAS_CREATE_HASH = 529393457L
    private val canvasCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_create", CANVAS_CREATE_HASH)
    }

    private const val CANVAS_SET_ITEM_MIRRORING_HASH = 2343975398L
    private val canvasSetItemMirroringBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_set_item_mirroring", CANVAS_SET_ITEM_MIRRORING_HASH)
    }

    private const val CANVAS_SET_ITEM_REPEAT_HASH = 1739512717L
    private val canvasSetItemRepeatBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_set_item_repeat", CANVAS_SET_ITEM_REPEAT_HASH)
    }

    private const val CANVAS_SET_MODULATE_HASH = 2948539648L
    private val canvasSetModulateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_set_modulate", CANVAS_SET_MODULATE_HASH)
    }

    private const val CANVAS_SET_DISABLE_SCALE_HASH = 2586408642L
    private val canvasSetDisableScaleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_set_disable_scale", CANVAS_SET_DISABLE_SCALE_HASH)
    }

    private const val CANVAS_TEXTURE_CREATE_HASH = 529393457L
    private val canvasTextureCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_texture_create", CANVAS_TEXTURE_CREATE_HASH)
    }

    private const val CANVAS_TEXTURE_SET_CHANNEL_HASH = 3822119138L
    private val canvasTextureSetChannelBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_texture_set_channel", CANVAS_TEXTURE_SET_CHANNEL_HASH)
    }

    private const val CANVAS_TEXTURE_SET_SHADING_PARAMETERS_HASH = 2124967469L
    private val canvasTextureSetShadingParametersBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_texture_set_shading_parameters", CANVAS_TEXTURE_SET_SHADING_PARAMETERS_HASH)
    }

    private const val CANVAS_TEXTURE_SET_TEXTURE_FILTER_HASH = 1155129294L
    private val canvasTextureSetTextureFilterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_texture_set_texture_filter", CANVAS_TEXTURE_SET_TEXTURE_FILTER_HASH)
    }

    private const val CANVAS_TEXTURE_SET_TEXTURE_REPEAT_HASH = 1652956681L
    private val canvasTextureSetTextureRepeatBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_texture_set_texture_repeat", CANVAS_TEXTURE_SET_TEXTURE_REPEAT_HASH)
    }

    private const val CANVAS_ITEM_CREATE_HASH = 529393457L
    private val canvasItemCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_create", CANVAS_ITEM_CREATE_HASH)
    }

    private const val CANVAS_ITEM_SET_PARENT_HASH = 395945892L
    private val canvasItemSetParentBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_parent", CANVAS_ITEM_SET_PARENT_HASH)
    }

    private const val CANVAS_ITEM_SET_DEFAULT_TEXTURE_FILTER_HASH = 1155129294L
    private val canvasItemSetDefaultTextureFilterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_default_texture_filter", CANVAS_ITEM_SET_DEFAULT_TEXTURE_FILTER_HASH)
    }

    private const val CANVAS_ITEM_SET_DEFAULT_TEXTURE_REPEAT_HASH = 1652956681L
    private val canvasItemSetDefaultTextureRepeatBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_default_texture_repeat", CANVAS_ITEM_SET_DEFAULT_TEXTURE_REPEAT_HASH)
    }

    private const val CANVAS_ITEM_SET_VISIBLE_HASH = 1265174801L
    private val canvasItemSetVisibleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_visible", CANVAS_ITEM_SET_VISIBLE_HASH)
    }

    private const val CANVAS_ITEM_SET_LIGHT_MASK_HASH = 3411492887L
    private val canvasItemSetLightMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_light_mask", CANVAS_ITEM_SET_LIGHT_MASK_HASH)
    }

    private const val CANVAS_ITEM_SET_VISIBILITY_LAYER_HASH = 3411492887L
    private val canvasItemSetVisibilityLayerBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_visibility_layer", CANVAS_ITEM_SET_VISIBILITY_LAYER_HASH)
    }

    private const val CANVAS_ITEM_SET_TRANSFORM_HASH = 1246044741L
    private val canvasItemSetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_transform", CANVAS_ITEM_SET_TRANSFORM_HASH)
    }

    private const val CANVAS_ITEM_SET_CLIP_HASH = 1265174801L
    private val canvasItemSetClipBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_clip", CANVAS_ITEM_SET_CLIP_HASH)
    }

    private const val CANVAS_ITEM_SET_DISTANCE_FIELD_MODE_HASH = 1265174801L
    private val canvasItemSetDistanceFieldModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_distance_field_mode", CANVAS_ITEM_SET_DISTANCE_FIELD_MODE_HASH)
    }

    private const val CANVAS_ITEM_SET_CUSTOM_RECT_HASH = 1333997032L
    private val canvasItemSetCustomRectBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_custom_rect", CANVAS_ITEM_SET_CUSTOM_RECT_HASH)
    }

    private const val CANVAS_ITEM_SET_MODULATE_HASH = 2948539648L
    private val canvasItemSetModulateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_modulate", CANVAS_ITEM_SET_MODULATE_HASH)
    }

    private const val CANVAS_ITEM_SET_SELF_MODULATE_HASH = 2948539648L
    private val canvasItemSetSelfModulateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_self_modulate", CANVAS_ITEM_SET_SELF_MODULATE_HASH)
    }

    private const val CANVAS_ITEM_SET_DRAW_BEHIND_PARENT_HASH = 1265174801L
    private val canvasItemSetDrawBehindParentBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_draw_behind_parent", CANVAS_ITEM_SET_DRAW_BEHIND_PARENT_HASH)
    }

    private const val CANVAS_ITEM_SET_INTERPOLATED_HASH = 1265174801L
    private val canvasItemSetInterpolatedBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_interpolated", CANVAS_ITEM_SET_INTERPOLATED_HASH)
    }

    private const val CANVAS_ITEM_RESET_PHYSICS_INTERPOLATION_HASH = 2722037293L
    private val canvasItemResetPhysicsInterpolationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_reset_physics_interpolation", CANVAS_ITEM_RESET_PHYSICS_INTERPOLATION_HASH)
    }

    private const val CANVAS_ITEM_TRANSFORM_PHYSICS_INTERPOLATION_HASH = 1246044741L
    private val canvasItemTransformPhysicsInterpolationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_transform_physics_interpolation", CANVAS_ITEM_TRANSFORM_PHYSICS_INTERPOLATION_HASH)
    }

    private const val CANVAS_ITEM_ADD_LINE_HASH = 1819681853L
    private val canvasItemAddLineBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_line", CANVAS_ITEM_ADD_LINE_HASH)
    }

    private const val CANVAS_ITEM_ADD_POLYLINE_HASH = 3098767073L
    private val canvasItemAddPolylineBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_polyline", CANVAS_ITEM_ADD_POLYLINE_HASH)
    }

    private const val CANVAS_ITEM_ADD_MULTILINE_HASH = 3098767073L
    private val canvasItemAddMultilineBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_multiline", CANVAS_ITEM_ADD_MULTILINE_HASH)
    }

    private const val CANVAS_ITEM_ADD_RECT_HASH = 3523446176L
    private val canvasItemAddRectBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_rect", CANVAS_ITEM_ADD_RECT_HASH)
    }

    private const val CANVAS_ITEM_ADD_CIRCLE_HASH = 333077949L
    private val canvasItemAddCircleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_circle", CANVAS_ITEM_ADD_CIRCLE_HASH)
    }

    private const val CANVAS_ITEM_ADD_ELLIPSE_HASH = 4188642757L
    private val canvasItemAddEllipseBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_ellipse", CANVAS_ITEM_ADD_ELLIPSE_HASH)
    }

    private const val CANVAS_ITEM_ADD_TEXTURE_RECT_HASH = 324864032L
    private val canvasItemAddTextureRectBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_texture_rect", CANVAS_ITEM_ADD_TEXTURE_RECT_HASH)
    }

    private const val CANVAS_ITEM_ADD_MSDF_TEXTURE_RECT_REGION_HASH = 97408773L
    private val canvasItemAddMsdfTextureRectRegionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_msdf_texture_rect_region", CANVAS_ITEM_ADD_MSDF_TEXTURE_RECT_REGION_HASH)
    }

    private const val CANVAS_ITEM_ADD_LCD_TEXTURE_RECT_REGION_HASH = 359793297L
    private val canvasItemAddLcdTextureRectRegionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_lcd_texture_rect_region", CANVAS_ITEM_ADD_LCD_TEXTURE_RECT_REGION_HASH)
    }

    private const val CANVAS_ITEM_ADD_TEXTURE_RECT_REGION_HASH = 485157892L
    private val canvasItemAddTextureRectRegionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_texture_rect_region", CANVAS_ITEM_ADD_TEXTURE_RECT_REGION_HASH)
    }

    private const val CANVAS_ITEM_ADD_NINE_PATCH_HASH = 389957886L
    private val canvasItemAddNinePatchBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_nine_patch", CANVAS_ITEM_ADD_NINE_PATCH_HASH)
    }

    private const val CANVAS_ITEM_ADD_PRIMITIVE_HASH = 3731601077L
    private val canvasItemAddPrimitiveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_primitive", CANVAS_ITEM_ADD_PRIMITIVE_HASH)
    }

    private const val CANVAS_ITEM_ADD_POLYGON_HASH = 3580000528L
    private val canvasItemAddPolygonBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_polygon", CANVAS_ITEM_ADD_POLYGON_HASH)
    }

    private const val CANVAS_ITEM_ADD_TRIANGLE_ARRAY_HASH = 660261329L
    private val canvasItemAddTriangleArrayBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_triangle_array", CANVAS_ITEM_ADD_TRIANGLE_ARRAY_HASH)
    }

    private const val CANVAS_ITEM_ADD_MESH_HASH = 316450961L
    private val canvasItemAddMeshBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_mesh", CANVAS_ITEM_ADD_MESH_HASH)
    }

    private const val CANVAS_ITEM_ADD_MULTIMESH_HASH = 2131855138L
    private val canvasItemAddMultimeshBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_multimesh", CANVAS_ITEM_ADD_MULTIMESH_HASH)
    }

    private const val CANVAS_ITEM_ADD_PARTICLES_HASH = 2575754278L
    private val canvasItemAddParticlesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_particles", CANVAS_ITEM_ADD_PARTICLES_HASH)
    }

    private const val CANVAS_ITEM_ADD_SET_TRANSFORM_HASH = 1246044741L
    private val canvasItemAddSetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_set_transform", CANVAS_ITEM_ADD_SET_TRANSFORM_HASH)
    }

    private const val CANVAS_ITEM_ADD_CLIP_IGNORE_HASH = 1265174801L
    private val canvasItemAddClipIgnoreBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_clip_ignore", CANVAS_ITEM_ADD_CLIP_IGNORE_HASH)
    }

    private const val CANVAS_ITEM_ADD_ANIMATION_SLICE_HASH = 2646834499L
    private val canvasItemAddAnimationSliceBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_animation_slice", CANVAS_ITEM_ADD_ANIMATION_SLICE_HASH)
    }

    private const val CANVAS_ITEM_SET_SORT_CHILDREN_BY_Y_HASH = 1265174801L
    private val canvasItemSetSortChildrenByYBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_sort_children_by_y", CANVAS_ITEM_SET_SORT_CHILDREN_BY_Y_HASH)
    }

    private const val CANVAS_ITEM_SET_Z_INDEX_HASH = 3411492887L
    private val canvasItemSetZIndexBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_z_index", CANVAS_ITEM_SET_Z_INDEX_HASH)
    }

    private const val CANVAS_ITEM_SET_Z_AS_RELATIVE_TO_PARENT_HASH = 1265174801L
    private val canvasItemSetZAsRelativeToParentBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_z_as_relative_to_parent", CANVAS_ITEM_SET_Z_AS_RELATIVE_TO_PARENT_HASH)
    }

    private const val CANVAS_ITEM_SET_COPY_TO_BACKBUFFER_HASH = 2429202503L
    private val canvasItemSetCopyToBackbufferBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_copy_to_backbuffer", CANVAS_ITEM_SET_COPY_TO_BACKBUFFER_HASH)
    }

    private const val CANVAS_ITEM_ATTACH_SKELETON_HASH = 395945892L
    private val canvasItemAttachSkeletonBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_attach_skeleton", CANVAS_ITEM_ATTACH_SKELETON_HASH)
    }

    private const val CANVAS_ITEM_CLEAR_HASH = 2722037293L
    private val canvasItemClearBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_clear", CANVAS_ITEM_CLEAR_HASH)
    }

    private const val CANVAS_ITEM_SET_DRAW_INDEX_HASH = 3411492887L
    private val canvasItemSetDrawIndexBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_draw_index", CANVAS_ITEM_SET_DRAW_INDEX_HASH)
    }

    private const val CANVAS_ITEM_SET_MATERIAL_HASH = 395945892L
    private val canvasItemSetMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_material", CANVAS_ITEM_SET_MATERIAL_HASH)
    }

    private const val CANVAS_ITEM_SET_USE_PARENT_MATERIAL_HASH = 1265174801L
    private val canvasItemSetUseParentMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_use_parent_material", CANVAS_ITEM_SET_USE_PARENT_MATERIAL_HASH)
    }

    private const val CANVAS_ITEM_SET_INSTANCE_SHADER_PARAMETER_HASH = 3477296213L
    private val canvasItemSetInstanceShaderParameterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_instance_shader_parameter", CANVAS_ITEM_SET_INSTANCE_SHADER_PARAMETER_HASH)
    }

    private const val CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_HASH = 2621281810L
    private val canvasItemGetInstanceShaderParameterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_get_instance_shader_parameter", CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_HASH)
    }

    private const val CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_DEFAULT_VALUE_HASH = 2621281810L
    private val canvasItemGetInstanceShaderParameterDefaultValueBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_get_instance_shader_parameter_default_value", CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_DEFAULT_VALUE_HASH)
    }

    private const val CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_LIST_HASH = 2684255073L
    private val canvasItemGetInstanceShaderParameterListBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_get_instance_shader_parameter_list", CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_LIST_HASH)
    }

    private const val CANVAS_ITEM_SET_VISIBILITY_NOTIFIER_HASH = 3568945579L
    private val canvasItemSetVisibilityNotifierBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_visibility_notifier", CANVAS_ITEM_SET_VISIBILITY_NOTIFIER_HASH)
    }

    private const val CANVAS_ITEM_SET_CANVAS_GROUP_MODE_HASH = 3973586316L
    private val canvasItemSetCanvasGroupModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_canvas_group_mode", CANVAS_ITEM_SET_CANVAS_GROUP_MODE_HASH)
    }

    private const val DEBUG_CANVAS_ITEM_GET_RECT_HASH = 624227424L
    private val debugCanvasItemGetRectBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "debug_canvas_item_get_rect", DEBUG_CANVAS_ITEM_GET_RECT_HASH)
    }

    private const val CANVAS_LIGHT_CREATE_HASH = 529393457L
    private val canvasLightCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_create", CANVAS_LIGHT_CREATE_HASH)
    }

    private const val CANVAS_LIGHT_ATTACH_TO_CANVAS_HASH = 395945892L
    private val canvasLightAttachToCanvasBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_attach_to_canvas", CANVAS_LIGHT_ATTACH_TO_CANVAS_HASH)
    }

    private const val CANVAS_LIGHT_SET_ENABLED_HASH = 1265174801L
    private val canvasLightSetEnabledBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_enabled", CANVAS_LIGHT_SET_ENABLED_HASH)
    }

    private const val CANVAS_LIGHT_SET_TEXTURE_SCALE_HASH = 1794382983L
    private val canvasLightSetTextureScaleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_texture_scale", CANVAS_LIGHT_SET_TEXTURE_SCALE_HASH)
    }

    private const val CANVAS_LIGHT_SET_TRANSFORM_HASH = 1246044741L
    private val canvasLightSetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_transform", CANVAS_LIGHT_SET_TRANSFORM_HASH)
    }

    private const val CANVAS_LIGHT_SET_TEXTURE_HASH = 395945892L
    private val canvasLightSetTextureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_texture", CANVAS_LIGHT_SET_TEXTURE_HASH)
    }

    private const val CANVAS_LIGHT_SET_TEXTURE_OFFSET_HASH = 3201125042L
    private val canvasLightSetTextureOffsetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_texture_offset", CANVAS_LIGHT_SET_TEXTURE_OFFSET_HASH)
    }

    private const val CANVAS_LIGHT_SET_COLOR_HASH = 2948539648L
    private val canvasLightSetColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_color", CANVAS_LIGHT_SET_COLOR_HASH)
    }

    private const val CANVAS_LIGHT_SET_HEIGHT_HASH = 1794382983L
    private val canvasLightSetHeightBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_height", CANVAS_LIGHT_SET_HEIGHT_HASH)
    }

    private const val CANVAS_LIGHT_SET_ENERGY_HASH = 1794382983L
    private val canvasLightSetEnergyBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_energy", CANVAS_LIGHT_SET_ENERGY_HASH)
    }

    private const val CANVAS_LIGHT_SET_Z_RANGE_HASH = 4288446313L
    private val canvasLightSetZRangeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_z_range", CANVAS_LIGHT_SET_Z_RANGE_HASH)
    }

    private const val CANVAS_LIGHT_SET_LAYER_RANGE_HASH = 4288446313L
    private val canvasLightSetLayerRangeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_layer_range", CANVAS_LIGHT_SET_LAYER_RANGE_HASH)
    }

    private const val CANVAS_LIGHT_SET_ITEM_CULL_MASK_HASH = 3411492887L
    private val canvasLightSetItemCullMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_item_cull_mask", CANVAS_LIGHT_SET_ITEM_CULL_MASK_HASH)
    }

    private const val CANVAS_LIGHT_SET_ITEM_SHADOW_CULL_MASK_HASH = 3411492887L
    private val canvasLightSetItemShadowCullMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_item_shadow_cull_mask", CANVAS_LIGHT_SET_ITEM_SHADOW_CULL_MASK_HASH)
    }

    private const val CANVAS_LIGHT_SET_MODE_HASH = 2957564891L
    private val canvasLightSetModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_mode", CANVAS_LIGHT_SET_MODE_HASH)
    }

    private const val CANVAS_LIGHT_SET_SHADOW_ENABLED_HASH = 1265174801L
    private val canvasLightSetShadowEnabledBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_shadow_enabled", CANVAS_LIGHT_SET_SHADOW_ENABLED_HASH)
    }

    private const val CANVAS_LIGHT_SET_SHADOW_FILTER_HASH = 393119659L
    private val canvasLightSetShadowFilterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_shadow_filter", CANVAS_LIGHT_SET_SHADOW_FILTER_HASH)
    }

    private const val CANVAS_LIGHT_SET_SHADOW_COLOR_HASH = 2948539648L
    private val canvasLightSetShadowColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_shadow_color", CANVAS_LIGHT_SET_SHADOW_COLOR_HASH)
    }

    private const val CANVAS_LIGHT_SET_SHADOW_SMOOTH_HASH = 1794382983L
    private val canvasLightSetShadowSmoothBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_shadow_smooth", CANVAS_LIGHT_SET_SHADOW_SMOOTH_HASH)
    }

    private const val CANVAS_LIGHT_SET_BLEND_MODE_HASH = 804895945L
    private val canvasLightSetBlendModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_blend_mode", CANVAS_LIGHT_SET_BLEND_MODE_HASH)
    }

    private const val CANVAS_LIGHT_SET_INTERPOLATED_HASH = 1265174801L
    private val canvasLightSetInterpolatedBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_interpolated", CANVAS_LIGHT_SET_INTERPOLATED_HASH)
    }

    private const val CANVAS_LIGHT_RESET_PHYSICS_INTERPOLATION_HASH = 2722037293L
    private val canvasLightResetPhysicsInterpolationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_reset_physics_interpolation", CANVAS_LIGHT_RESET_PHYSICS_INTERPOLATION_HASH)
    }

    private const val CANVAS_LIGHT_TRANSFORM_PHYSICS_INTERPOLATION_HASH = 1246044741L
    private val canvasLightTransformPhysicsInterpolationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_transform_physics_interpolation", CANVAS_LIGHT_TRANSFORM_PHYSICS_INTERPOLATION_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_CREATE_HASH = 529393457L
    private val canvasLightOccluderCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_create", CANVAS_LIGHT_OCCLUDER_CREATE_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_ATTACH_TO_CANVAS_HASH = 395945892L
    private val canvasLightOccluderAttachToCanvasBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_attach_to_canvas", CANVAS_LIGHT_OCCLUDER_ATTACH_TO_CANVAS_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_SET_ENABLED_HASH = 1265174801L
    private val canvasLightOccluderSetEnabledBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_set_enabled", CANVAS_LIGHT_OCCLUDER_SET_ENABLED_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_SET_POLYGON_HASH = 395945892L
    private val canvasLightOccluderSetPolygonBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_set_polygon", CANVAS_LIGHT_OCCLUDER_SET_POLYGON_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_SET_AS_SDF_COLLISION_HASH = 1265174801L
    private val canvasLightOccluderSetAsSdfCollisionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_set_as_sdf_collision", CANVAS_LIGHT_OCCLUDER_SET_AS_SDF_COLLISION_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_SET_TRANSFORM_HASH = 1246044741L
    private val canvasLightOccluderSetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_set_transform", CANVAS_LIGHT_OCCLUDER_SET_TRANSFORM_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_SET_LIGHT_MASK_HASH = 3411492887L
    private val canvasLightOccluderSetLightMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_set_light_mask", CANVAS_LIGHT_OCCLUDER_SET_LIGHT_MASK_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_SET_INTERPOLATED_HASH = 1265174801L
    private val canvasLightOccluderSetInterpolatedBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_set_interpolated", CANVAS_LIGHT_OCCLUDER_SET_INTERPOLATED_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_RESET_PHYSICS_INTERPOLATION_HASH = 2722037293L
    private val canvasLightOccluderResetPhysicsInterpolationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_reset_physics_interpolation", CANVAS_LIGHT_OCCLUDER_RESET_PHYSICS_INTERPOLATION_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_TRANSFORM_PHYSICS_INTERPOLATION_HASH = 1246044741L
    private val canvasLightOccluderTransformPhysicsInterpolationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_transform_physics_interpolation", CANVAS_LIGHT_OCCLUDER_TRANSFORM_PHYSICS_INTERPOLATION_HASH)
    }

    private const val CANVAS_OCCLUDER_POLYGON_CREATE_HASH = 529393457L
    private val canvasOccluderPolygonCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_occluder_polygon_create", CANVAS_OCCLUDER_POLYGON_CREATE_HASH)
    }

    private const val CANVAS_OCCLUDER_POLYGON_SET_SHAPE_HASH = 2103882027L
    private val canvasOccluderPolygonSetShapeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_occluder_polygon_set_shape", CANVAS_OCCLUDER_POLYGON_SET_SHAPE_HASH)
    }

    private const val CANVAS_OCCLUDER_POLYGON_SET_CULL_MODE_HASH = 1839404663L
    private val canvasOccluderPolygonSetCullModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_occluder_polygon_set_cull_mode", CANVAS_OCCLUDER_POLYGON_SET_CULL_MODE_HASH)
    }

    private const val CANVAS_SET_SHADOW_TEXTURE_SIZE_HASH = 1286410249L
    private val canvasSetShadowTextureSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_set_shadow_texture_size", CANVAS_SET_SHADOW_TEXTURE_SIZE_HASH)
    }

    private const val GLOBAL_SHADER_PARAMETER_ADD_HASH = 463390080L
    private val globalShaderParameterAddBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_add", GLOBAL_SHADER_PARAMETER_ADD_HASH)
    }

    private const val GLOBAL_SHADER_PARAMETER_REMOVE_HASH = 3304788590L
    private val globalShaderParameterRemoveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_remove", GLOBAL_SHADER_PARAMETER_REMOVE_HASH)
    }

    private const val GLOBAL_SHADER_PARAMETER_GET_LIST_HASH = 3995934104L
    private val globalShaderParameterGetListBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_get_list", GLOBAL_SHADER_PARAMETER_GET_LIST_HASH)
    }

    private const val GLOBAL_SHADER_PARAMETER_SET_HASH = 3776071444L
    private val globalShaderParameterSetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_set", GLOBAL_SHADER_PARAMETER_SET_HASH)
    }

    private const val GLOBAL_SHADER_PARAMETER_SET_OVERRIDE_HASH = 3776071444L
    private val globalShaderParameterSetOverrideBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_set_override", GLOBAL_SHADER_PARAMETER_SET_OVERRIDE_HASH)
    }

    private const val GLOBAL_SHADER_PARAMETER_GET_HASH = 2760726917L
    private val globalShaderParameterGetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_get", GLOBAL_SHADER_PARAMETER_GET_HASH)
    }

    private const val GLOBAL_SHADER_PARAMETER_GET_TYPE_HASH = 1601414142L
    private val globalShaderParameterGetTypeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_get_type", GLOBAL_SHADER_PARAMETER_GET_TYPE_HASH)
    }

    private const val FREE_RID_HASH = 2722037293L
    private val freeRidBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "free_rid", FREE_RID_HASH)
    }

    private const val REQUEST_FRAME_DRAWN_CALLBACK_HASH = 1611583062L
    private val requestFrameDrawnCallbackBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "request_frame_drawn_callback", REQUEST_FRAME_DRAWN_CALLBACK_HASH)
    }

    private const val HAS_CHANGED_HASH = 36873697L
    private val hasChangedBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "has_changed", HAS_CHANGED_HASH)
    }

    private const val GET_RENDERING_INFO_HASH = 3763192241L
    private val getRenderingInfoBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_rendering_info", GET_RENDERING_INFO_HASH)
    }

    private const val GET_VIDEO_ADAPTER_NAME_HASH = 201670096L
    private val getVideoAdapterNameBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_video_adapter_name", GET_VIDEO_ADAPTER_NAME_HASH)
    }

    private const val GET_VIDEO_ADAPTER_VENDOR_HASH = 201670096L
    private val getVideoAdapterVendorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_video_adapter_vendor", GET_VIDEO_ADAPTER_VENDOR_HASH)
    }

    private const val GET_VIDEO_ADAPTER_TYPE_HASH = 3099547011L
    private val getVideoAdapterTypeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_video_adapter_type", GET_VIDEO_ADAPTER_TYPE_HASH)
    }

    private const val GET_VIDEO_ADAPTER_API_VERSION_HASH = 201670096L
    private val getVideoAdapterApiVersionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_video_adapter_api_version", GET_VIDEO_ADAPTER_API_VERSION_HASH)
    }

    private const val GET_CURRENT_RENDERING_DRIVER_NAME_HASH = 201670096L
    private val getCurrentRenderingDriverNameBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_current_rendering_driver_name", GET_CURRENT_RENDERING_DRIVER_NAME_HASH)
    }

    private const val GET_CURRENT_RENDERING_METHOD_HASH = 201670096L
    private val getCurrentRenderingMethodBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_current_rendering_method", GET_CURRENT_RENDERING_METHOD_HASH)
    }

    private const val MAKE_SPHERE_MESH_HASH = 2251015897L
    private val makeSphereMeshBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "make_sphere_mesh", MAKE_SPHERE_MESH_HASH)
    }

    private const val GET_TEST_CUBE_HASH = 529393457L
    private val getTestCubeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_test_cube", GET_TEST_CUBE_HASH)
    }

    private const val GET_TEST_TEXTURE_HASH = 529393457L
    private val getTestTextureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_test_texture", GET_TEST_TEXTURE_HASH)
    }

    private const val GET_WHITE_TEXTURE_HASH = 529393457L
    private val getWhiteTextureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_white_texture", GET_WHITE_TEXTURE_HASH)
    }

    private const val SET_BOOT_IMAGE_WITH_STRETCH_HASH = 1104470771L
    private val setBootImageWithStretchBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "set_boot_image_with_stretch", SET_BOOT_IMAGE_WITH_STRETCH_HASH)
    }

    private const val SET_BOOT_IMAGE_HASH = 3759744527L
    private val setBootImageBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "set_boot_image", SET_BOOT_IMAGE_HASH)
    }

    private const val GET_DEFAULT_CLEAR_COLOR_HASH = 3200896285L
    private val getDefaultClearColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_default_clear_color", GET_DEFAULT_CLEAR_COLOR_HASH)
    }

    private const val SET_DEFAULT_CLEAR_COLOR_HASH = 2920490490L
    private val setDefaultClearColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "set_default_clear_color", SET_DEFAULT_CLEAR_COLOR_HASH)
    }

    private const val HAS_OS_FEATURE_HASH = 3927539163L
    private val hasOsFeatureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "has_os_feature", HAS_OS_FEATURE_HASH)
    }

    private const val SET_DEBUG_GENERATE_WIREFRAMES_HASH = 2586408642L
    private val setDebugGenerateWireframesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "set_debug_generate_wireframes", SET_DEBUG_GENERATE_WIREFRAMES_HASH)
    }

    private const val IS_RENDER_LOOP_ENABLED_HASH = 36873697L
    private val isRenderLoopEnabledBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "is_render_loop_enabled", IS_RENDER_LOOP_ENABLED_HASH)
    }

    private const val SET_RENDER_LOOP_ENABLED_HASH = 2586408642L
    private val setRenderLoopEnabledBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "set_render_loop_enabled", SET_RENDER_LOOP_ENABLED_HASH)
    }

    private const val GET_FRAME_SETUP_TIME_CPU_HASH = 1740695150L
    private val getFrameSetupTimeCpuBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_frame_setup_time_cpu", GET_FRAME_SETUP_TIME_CPU_HASH)
    }

    private const val FORCE_SYNC_HASH = 3218959716L
    private val forceSyncBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "force_sync", FORCE_SYNC_HASH)
    }

    private const val FORCE_DRAW_HASH = 1076185472L
    private val forceDrawBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "force_draw", FORCE_DRAW_HASH)
    }

    private const val GET_RENDERING_DEVICE_HASH = 1405107940L
    private val getRenderingDeviceBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_rendering_device", GET_RENDERING_DEVICE_HASH)
    }

    private const val CREATE_LOCAL_RENDERING_DEVICE_HASH = 1405107940L
    private val createLocalRenderingDeviceBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "create_local_rendering_device", CREATE_LOCAL_RENDERING_DEVICE_HASH)
    }

    private const val IS_ON_RENDER_THREAD_HASH = 2240911060L
    private val isOnRenderThreadBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "is_on_render_thread", IS_ON_RENDER_THREAD_HASH)
    }

    private const val CALL_ON_RENDER_THREAD_HASH = 1611583062L
    private val callOnRenderThreadBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "call_on_render_thread", CALL_ON_RENDER_THREAD_HASH)
    }

    private const val HAS_FEATURE_HASH = 598462696L
    private val hasFeatureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "has_feature", HAS_FEATURE_HASH)
    }
}
