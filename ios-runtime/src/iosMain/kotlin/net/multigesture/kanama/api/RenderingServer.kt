package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i

/**
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

    fun texture2dCreate(image: Image?): RID {
        return ObjectCalls.ptrcallWithObjectArgRetRID(texture2dCreateBind, singleton, image?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun textureProxyCreate(base: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(textureProxyCreateBind, singleton, base)
    }

    fun textureCreateFromNativeHandle(type: Long, format: Long, nativeHandle: Long, width: Int, height: Int, depth: Int, layers: Int = 1, layeredType: Long = 0L): RID {
        return ObjectCalls.ptrcallWithThreeLongFourIntLongArgsRetRID(textureCreateFromNativeHandleBind, singleton, type, format, nativeHandle, width, height, depth, layers, layeredType)
    }

    fun textureDrawableCreate(width: Int, height: Int, format: Long, color: Color, withMipmaps: Boolean = false): RID {
        return ObjectCalls.ptrcallWithTwoIntLongColorBoolArgsRetRID(textureDrawableCreateBind, singleton, width, height, format, color, withMipmaps)
    }

    fun texture2dUpdate(texture: RID, image: Image?, layer: Int) {
        ObjectCalls.ptrcallWithRIDObjectIntArgs(texture2dUpdateBind, singleton, texture, image?.requireOpenHandle() ?: MemorySegment.NULL, layer)
    }

    fun textureProxyUpdate(texture: RID, proxyTo: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(textureProxyUpdateBind, singleton, texture, proxyTo)
    }

    fun texture2dPlaceholderCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(texture2dPlaceholderCreateBind, singleton)
    }

    fun texture2dLayeredPlaceholderCreate(layeredType: Long): RID {
        return ObjectCalls.ptrcallWithLongArgRetRID(texture2dLayeredPlaceholderCreateBind, singleton, layeredType)
    }

    fun texture3dPlaceholderCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(texture3dPlaceholderCreateBind, singleton)
    }

    fun texture2dGet(texture: RID): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithRIDArgRetObject(texture2dGetBind, singleton, texture))
    }

    fun texture2dLayerGet(texture: RID, layer: Int): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithRIDAndIntArgRetObject(texture2dLayerGetBind, singleton, texture, layer))
    }

    fun textureDrawableGenerateMipmaps(texture: RID) {
        ObjectCalls.ptrcallWithRIDArg(textureDrawableGenerateMipmapsBind, singleton, texture)
    }

    fun textureDrawableGetDefaultMaterial(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(textureDrawableGetDefaultMaterialBind, singleton)
    }

    fun textureReplace(texture: RID, byTexture: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(textureReplaceBind, singleton, texture, byTexture)
    }

    fun textureSetSizeOverride(texture: RID, width: Int, height: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(textureSetSizeOverrideBind, singleton, texture, width, height)
    }

    fun textureSetPath(texture: RID, path: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(textureSetPathBind, singleton, texture, path)
    }

    fun textureGetFormat(texture: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(textureGetFormatBind, singleton, texture)
    }

    fun textureSetForceRedrawIfVisible(texture: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(textureSetForceRedrawIfVisibleBind, singleton, texture, enable)
    }

    fun textureRdCreate(rdTexture: RID, layerType: Long = 0L): RID {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetRID(textureRdCreateBind, singleton, rdTexture, layerType)
    }

    fun textureGetRdTexture(texture: RID, srgb: Boolean = false): RID {
        return ObjectCalls.ptrcallWithRIDAndBoolArgRetRID(textureGetRdTextureBind, singleton, texture, srgb)
    }

    fun textureGetNativeHandle(texture: RID, srgb: Boolean = false): Long {
        return ObjectCalls.ptrcallWithRIDAndBoolArgRetLong(textureGetNativeHandleBind, singleton, texture, srgb)
    }

    fun shaderCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(shaderCreateBind, singleton)
    }

    fun shaderSetCode(shader: RID, code: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(shaderSetCodeBind, singleton, shader, code)
    }

    fun shaderSetPathHint(shader: RID, path: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(shaderSetPathHintBind, singleton, shader, path)
    }

    fun shaderSetDefaultTextureParameter(shader: RID, name: String, texture: RID, index: Int = 0) {
        ObjectCalls.ptrcallWithRIDStringNameRIDIntArgs(shaderSetDefaultTextureParameterBind, singleton, shader, name, texture, index)
    }

    fun shaderGetDefaultTextureParameter(shader: RID, name: String, index: Int = 0): RID {
        return ObjectCalls.ptrcallWithRIDStringNameAndIntArgRetRID(shaderGetDefaultTextureParameterBind, singleton, shader, name, index)
    }

    fun materialCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(materialCreateBind, singleton)
    }

    fun materialSetShader(shaderMaterial: RID, shader: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(materialSetShaderBind, singleton, shaderMaterial, shader)
    }

    fun materialSetRenderPriority(material: RID, priority: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(materialSetRenderPriorityBind, singleton, material, priority)
    }

    fun materialSetNextPass(material: RID, nextMaterial: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(materialSetNextPassBind, singleton, material, nextMaterial)
    }

    fun materialSetUseDebanding(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(materialSetUseDebandingBind, singleton, enable)
    }

    fun meshCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(meshCreateBind, singleton)
    }

    fun meshSurfaceGetFormatOffset(format: Long, vertexCount: Int, arrayIndex: Int): Long {
        return ObjectCalls.ptrcallWithLongAndTwoIntArgsRetUInt32(meshSurfaceGetFormatOffsetBind, singleton, format, vertexCount, arrayIndex)
    }

    fun meshSurfaceGetFormatVertexStride(format: Long, vertexCount: Int): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetUInt32(meshSurfaceGetFormatVertexStrideBind, singleton, format, vertexCount)
    }

    fun meshSurfaceGetFormatNormalTangentStride(format: Long, vertexCount: Int): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetUInt32(meshSurfaceGetFormatNormalTangentStrideBind, singleton, format, vertexCount)
    }

    fun meshSurfaceGetFormatAttributeStride(format: Long, vertexCount: Int): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetUInt32(meshSurfaceGetFormatAttributeStrideBind, singleton, format, vertexCount)
    }

    fun meshSurfaceGetFormatSkinStride(format: Long, vertexCount: Int): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetUInt32(meshSurfaceGetFormatSkinStrideBind, singleton, format, vertexCount)
    }

    fun meshSurfaceGetFormatIndexStride(format: Long, vertexCount: Int): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetUInt32(meshSurfaceGetFormatIndexStrideBind, singleton, format, vertexCount)
    }

    fun meshGetBlendShapeCount(mesh: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(meshGetBlendShapeCountBind, singleton, mesh)
    }

    fun meshSetBlendShapeMode(mesh: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(meshSetBlendShapeModeBind, singleton, mesh, mode)
    }

    fun meshGetBlendShapeMode(mesh: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(meshGetBlendShapeModeBind, singleton, mesh)
    }

    fun meshSurfaceSetMaterial(mesh: RID, surface: Int, material: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(meshSurfaceSetMaterialBind, singleton, mesh, surface, material)
    }

    fun meshSurfaceGetMaterial(mesh: RID, surface: Int): RID {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetRID(meshSurfaceGetMaterialBind, singleton, mesh, surface)
    }

    fun meshGetSurfaceCount(mesh: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(meshGetSurfaceCountBind, singleton, mesh)
    }

    fun meshSetCustomAabb(mesh: RID, aabb: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(meshSetCustomAabbBind, singleton, mesh, aabb)
    }

    fun meshGetCustomAabb(mesh: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(meshGetCustomAabbBind, singleton, mesh)
    }

    fun meshSurfaceRemove(mesh: RID, surface: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(meshSurfaceRemoveBind, singleton, mesh, surface)
    }

    fun meshClear(mesh: RID) {
        ObjectCalls.ptrcallWithRIDArg(meshClearBind, singleton, mesh)
    }

    fun meshSetShadowMesh(mesh: RID, shadowMesh: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(meshSetShadowMeshBind, singleton, mesh, shadowMesh)
    }

    fun multimeshCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(multimeshCreateBind, singleton)
    }

    fun multimeshAllocateData(multimesh: RID, instances: Int, transformFormat: Long, colorFormat: Boolean = false, customDataFormat: Boolean = false, useIndirect: Boolean = false) {
        ObjectCalls.ptrcallWithRIDIntLongThreeBoolArgs(multimeshAllocateDataBind, singleton, multimesh, instances, transformFormat, colorFormat, customDataFormat, useIndirect)
    }

    fun multimeshGetInstanceCount(multimesh: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(multimeshGetInstanceCountBind, singleton, multimesh)
    }

    fun multimeshSetMesh(multimesh: RID, mesh: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(multimeshSetMeshBind, singleton, multimesh, mesh)
    }

    fun multimeshInstanceSetTransform(multimesh: RID, index: Int, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform3DArg(multimeshInstanceSetTransformBind, singleton, multimesh, index, transform)
    }

    fun multimeshInstanceSetTransform2d(multimesh: RID, index: Int, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform2DArg(multimeshInstanceSetTransform2dBind, singleton, multimesh, index, transform)
    }

    fun multimeshInstanceSetColor(multimesh: RID, index: Int, color: Color) {
        ObjectCalls.ptrcallWithRIDIntAndColorArgs(multimeshInstanceSetColorBind, singleton, multimesh, index, color)
    }

    fun multimeshInstanceSetCustomData(multimesh: RID, index: Int, customData: Color) {
        ObjectCalls.ptrcallWithRIDIntAndColorArgs(multimeshInstanceSetCustomDataBind, singleton, multimesh, index, customData)
    }

    fun multimeshGetMesh(multimesh: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(multimeshGetMeshBind, singleton, multimesh)
    }

    fun multimeshGetAabb(multimesh: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(multimeshGetAabbBind, singleton, multimesh)
    }

    fun multimeshSetCustomAabb(multimesh: RID, aabb: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(multimeshSetCustomAabbBind, singleton, multimesh, aabb)
    }

    fun multimeshGetCustomAabb(multimesh: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(multimeshGetCustomAabbBind, singleton, multimesh)
    }

    fun multimeshInstanceGetTransform(multimesh: RID, index: Int): Transform3D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform3D(multimeshInstanceGetTransformBind, singleton, multimesh, index)
    }

    fun multimeshInstanceGetTransform2d(multimesh: RID, index: Int): Transform2D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform2D(multimeshInstanceGetTransform2dBind, singleton, multimesh, index)
    }

    fun multimeshInstanceGetColor(multimesh: RID, index: Int): Color {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetColor(multimeshInstanceGetColorBind, singleton, multimesh, index)
    }

    fun multimeshInstanceGetCustomData(multimesh: RID, index: Int): Color {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetColor(multimeshInstanceGetCustomDataBind, singleton, multimesh, index)
    }

    fun multimeshSetVisibleInstances(multimesh: RID, visible: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(multimeshSetVisibleInstancesBind, singleton, multimesh, visible)
    }

    fun multimeshGetVisibleInstances(multimesh: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(multimeshGetVisibleInstancesBind, singleton, multimesh)
    }

    fun multimeshGetCommandBufferRdRid(multimesh: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(multimeshGetCommandBufferRdRidBind, singleton, multimesh)
    }

    fun multimeshGetBufferRdRid(multimesh: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(multimeshGetBufferRdRidBind, singleton, multimesh)
    }

    fun multimeshSetPhysicsInterpolated(multimesh: RID, interpolated: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(multimeshSetPhysicsInterpolatedBind, singleton, multimesh, interpolated)
    }

    fun multimeshSetPhysicsInterpolationQuality(multimesh: RID, quality: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(multimeshSetPhysicsInterpolationQualityBind, singleton, multimesh, quality)
    }

    fun multimeshInstanceResetPhysicsInterpolation(multimesh: RID, index: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(multimeshInstanceResetPhysicsInterpolationBind, singleton, multimesh, index)
    }

    fun multimeshInstancesResetPhysicsInterpolation(multimesh: RID) {
        ObjectCalls.ptrcallWithRIDArg(multimeshInstancesResetPhysicsInterpolationBind, singleton, multimesh)
    }

    fun skeletonCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(skeletonCreateBind, singleton)
    }

    fun skeletonAllocateData(skeleton: RID, bones: Int, is2dSkeleton: Boolean = false) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(skeletonAllocateDataBind, singleton, skeleton, bones, is2dSkeleton)
    }

    fun skeletonGetBoneCount(skeleton: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(skeletonGetBoneCountBind, singleton, skeleton)
    }

    fun skeletonBoneSetTransform(skeleton: RID, bone: Int, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform3DArg(skeletonBoneSetTransformBind, singleton, skeleton, bone, transform)
    }

    fun skeletonBoneGetTransform(skeleton: RID, bone: Int): Transform3D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform3D(skeletonBoneGetTransformBind, singleton, skeleton, bone)
    }

    fun skeletonBoneSetTransform2d(skeleton: RID, bone: Int, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform2DArg(skeletonBoneSetTransform2dBind, singleton, skeleton, bone, transform)
    }

    fun skeletonBoneGetTransform2d(skeleton: RID, bone: Int): Transform2D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform2D(skeletonBoneGetTransform2dBind, singleton, skeleton, bone)
    }

    fun skeletonSetBaseTransform2d(skeleton: RID, baseTransform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(skeletonSetBaseTransform2dBind, singleton, skeleton, baseTransform)
    }

    fun directionalLightCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(directionalLightCreateBind, singleton)
    }

    fun omniLightCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(omniLightCreateBind, singleton)
    }

    fun spotLightCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(spotLightCreateBind, singleton)
    }

    fun areaLightCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(areaLightCreateBind, singleton)
    }

    fun lightSetColor(light: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(lightSetColorBind, singleton, light, color)
    }

    fun lightSetParam(light: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(lightSetParamBind, singleton, light, param, value)
    }

    fun lightSetShadow(light: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightSetShadowBind, singleton, light, enabled)
    }

    fun lightSetProjector(light: RID, texture: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(lightSetProjectorBind, singleton, light, texture)
    }

    fun lightSetNegative(light: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightSetNegativeBind, singleton, light, enable)
    }

    fun lightSetCullMask(light: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(lightSetCullMaskBind, singleton, light, mask)
    }

    fun lightSetDistanceFade(decal: RID, enabled: Boolean, begin: Double, shadow: Double, length: Double) {
        ObjectCalls.ptrcallWithRIDBoolThreeDoubleArgs(lightSetDistanceFadeBind, singleton, decal, enabled, begin, shadow, length)
    }

    fun lightSetReverseCullFaceMode(light: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightSetReverseCullFaceModeBind, singleton, light, enabled)
    }

    fun lightSetShadowCasterMask(light: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(lightSetShadowCasterMaskBind, singleton, light, mask)
    }

    fun lightSetBakeMode(light: RID, bakeMode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(lightSetBakeModeBind, singleton, light, bakeMode)
    }

    fun lightSetMaxSdfgiCascade(light: RID, cascade: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(lightSetMaxSdfgiCascadeBind, singleton, light, cascade)
    }

    fun lightOmniSetShadowMode(light: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(lightOmniSetShadowModeBind, singleton, light, mode)
    }

    fun lightDirectionalSetShadowMode(light: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(lightDirectionalSetShadowModeBind, singleton, light, mode)
    }

    fun lightDirectionalSetBlendSplits(light: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightDirectionalSetBlendSplitsBind, singleton, light, enable)
    }

    fun lightDirectionalSetSkyMode(light: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(lightDirectionalSetSkyModeBind, singleton, light, mode)
    }

    fun lightAreaSetSize(light: RID, size: Vector2) {
        ObjectCalls.ptrcallWithRIDAndVector2Arg(lightAreaSetSizeBind, singleton, light, size)
    }

    fun lightAreaSetNormalizeEnergy(light: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightAreaSetNormalizeEnergyBind, singleton, light, enable)
    }

    fun lightProjectorsSetFilter(filter: Long) {
        ObjectCalls.ptrcallWithLongArg(lightProjectorsSetFilterBind, singleton, filter)
    }

    fun lightmapsSetBicubicFilter(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(lightmapsSetBicubicFilterBind, singleton, enable)
    }

    fun positionalSoftShadowFilterSetQuality(quality: Long) {
        ObjectCalls.ptrcallWithLongArg(positionalSoftShadowFilterSetQualityBind, singleton, quality)
    }

    fun directionalSoftShadowFilterSetQuality(quality: Long) {
        ObjectCalls.ptrcallWithLongArg(directionalSoftShadowFilterSetQualityBind, singleton, quality)
    }

    fun directionalShadowAtlasSetSize(size: Int, is16bits: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(directionalShadowAtlasSetSizeBind, singleton, size, is16bits)
    }

    fun reflectionProbeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(reflectionProbeCreateBind, singleton)
    }

    fun reflectionProbeSetUpdateMode(probe: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(reflectionProbeSetUpdateModeBind, singleton, probe, mode)
    }

    fun reflectionProbeSetIntensity(probe: RID, intensity: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(reflectionProbeSetIntensityBind, singleton, probe, intensity)
    }

    fun reflectionProbeSetBlendDistance(probe: RID, blendDistance: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(reflectionProbeSetBlendDistanceBind, singleton, probe, blendDistance)
    }

    fun reflectionProbeSetAmbientMode(probe: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(reflectionProbeSetAmbientModeBind, singleton, probe, mode)
    }

    fun reflectionProbeSetAmbientColor(probe: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(reflectionProbeSetAmbientColorBind, singleton, probe, color)
    }

    fun reflectionProbeSetAmbientEnergy(probe: RID, energy: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(reflectionProbeSetAmbientEnergyBind, singleton, probe, energy)
    }

    fun reflectionProbeSetMaxDistance(probe: RID, distance: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(reflectionProbeSetMaxDistanceBind, singleton, probe, distance)
    }

    fun reflectionProbeSetSize(probe: RID, size: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(reflectionProbeSetSizeBind, singleton, probe, size)
    }

    fun reflectionProbeSetOriginOffset(probe: RID, offset: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(reflectionProbeSetOriginOffsetBind, singleton, probe, offset)
    }

    fun reflectionProbeSetAsInterior(probe: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(reflectionProbeSetAsInteriorBind, singleton, probe, enable)
    }

    fun reflectionProbeSetEnableBoxProjection(probe: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(reflectionProbeSetEnableBoxProjectionBind, singleton, probe, enable)
    }

    fun reflectionProbeSetEnableShadows(probe: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(reflectionProbeSetEnableShadowsBind, singleton, probe, enable)
    }

    fun reflectionProbeSetCullMask(probe: RID, layers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(reflectionProbeSetCullMaskBind, singleton, probe, layers)
    }

    fun reflectionProbeSetReflectionMask(probe: RID, layers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(reflectionProbeSetReflectionMaskBind, singleton, probe, layers)
    }

    fun reflectionProbeSetResolution(probe: RID, resolution: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(reflectionProbeSetResolutionBind, singleton, probe, resolution)
    }

    fun reflectionProbeSetMeshLodThreshold(probe: RID, pixels: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(reflectionProbeSetMeshLodThresholdBind, singleton, probe, pixels)
    }

    fun decalCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(decalCreateBind, singleton)
    }

    fun decalSetSize(decal: RID, size: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(decalSetSizeBind, singleton, decal, size)
    }

    fun decalSetTexture(decal: RID, type: Long, texture: RID) {
        ObjectCalls.ptrcallWithRIDLongAndRIDArgs(decalSetTextureBind, singleton, decal, type, texture)
    }

    fun decalSetEmissionEnergy(decal: RID, energy: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(decalSetEmissionEnergyBind, singleton, decal, energy)
    }

    fun decalSetAlbedoMix(decal: RID, albedoMix: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(decalSetAlbedoMixBind, singleton, decal, albedoMix)
    }

    fun decalSetModulate(decal: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(decalSetModulateBind, singleton, decal, color)
    }

    fun decalSetCullMask(decal: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(decalSetCullMaskBind, singleton, decal, mask)
    }

    fun decalSetDistanceFade(decal: RID, enabled: Boolean, begin: Double, length: Double) {
        ObjectCalls.ptrcallWithRIDBoolTwoDoubleArgs(decalSetDistanceFadeBind, singleton, decal, enabled, begin, length)
    }

    fun decalSetFade(decal: RID, above: Double, below: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(decalSetFadeBind, singleton, decal, above, below)
    }

    fun decalSetNormalFade(decal: RID, fade: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(decalSetNormalFadeBind, singleton, decal, fade)
    }

    fun decalsSetFilter(filter: Long) {
        ObjectCalls.ptrcallWithLongArg(decalsSetFilterBind, singleton, filter)
    }

    fun giSetUseHalfResolution(halfResolution: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(giSetUseHalfResolutionBind, singleton, halfResolution)
    }

    fun voxelGiCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(voxelGiCreateBind, singleton)
    }

    fun voxelGiGetOctreeSize(voxelGi: RID): Vector3i {
        return ObjectCalls.ptrcallWithRIDArgRetVector3i(voxelGiGetOctreeSizeBind, singleton, voxelGi)
    }

    fun voxelGiGetToCellXform(voxelGi: RID): Transform3D {
        return ObjectCalls.ptrcallWithRIDArgRetTransform3D(voxelGiGetToCellXformBind, singleton, voxelGi)
    }

    fun voxelGiSetDynamicRange(voxelGi: RID, range: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetDynamicRangeBind, singleton, voxelGi, range)
    }

    fun voxelGiSetPropagation(voxelGi: RID, amount: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetPropagationBind, singleton, voxelGi, amount)
    }

    fun voxelGiSetEnergy(voxelGi: RID, energy: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetEnergyBind, singleton, voxelGi, energy)
    }

    fun voxelGiSetBakedExposureNormalization(voxelGi: RID, bakedExposure: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetBakedExposureNormalizationBind, singleton, voxelGi, bakedExposure)
    }

    fun voxelGiSetBias(voxelGi: RID, bias: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetBiasBind, singleton, voxelGi, bias)
    }

    fun voxelGiSetNormalBias(voxelGi: RID, bias: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetNormalBiasBind, singleton, voxelGi, bias)
    }

    fun voxelGiSetInterior(voxelGi: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(voxelGiSetInteriorBind, singleton, voxelGi, enable)
    }

    fun voxelGiSetUseTwoBounces(voxelGi: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(voxelGiSetUseTwoBouncesBind, singleton, voxelGi, enable)
    }

    fun voxelGiSetQuality(quality: Long) {
        ObjectCalls.ptrcallWithLongArg(voxelGiSetQualityBind, singleton, quality)
    }

    fun lightmapCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(lightmapCreateBind, singleton)
    }

    fun lightmapSetTextures(lightmap: RID, light: RID, usesSh: Boolean) {
        ObjectCalls.ptrcallWithTwoRIDBoolArgs(lightmapSetTexturesBind, singleton, lightmap, light, usesSh)
    }

    fun lightmapSetProbeBounds(lightmap: RID, bounds: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(lightmapSetProbeBoundsBind, singleton, lightmap, bounds)
    }

    fun lightmapSetProbeInterior(lightmap: RID, interior: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightmapSetProbeInteriorBind, singleton, lightmap, interior)
    }

    fun lightmapSetBakedExposureNormalization(lightmap: RID, bakedExposure: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(lightmapSetBakedExposureNormalizationBind, singleton, lightmap, bakedExposure)
    }

    fun lightmapSetProbeCaptureUpdateSpeed(speed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(lightmapSetProbeCaptureUpdateSpeedBind, singleton, speed)
    }

    fun particlesCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(particlesCreateBind, singleton)
    }

    fun particlesSetMode(particles: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesSetModeBind, singleton, particles, mode)
    }

    fun particlesSetEmitting(particles: RID, emitting: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(particlesSetEmittingBind, singleton, particles, emitting)
    }

    fun particlesGetEmitting(particles: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(particlesGetEmittingBind, singleton, particles)
    }

    fun particlesSetAmount(particles: RID, amount: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(particlesSetAmountBind, singleton, particles, amount)
    }

    fun particlesSetAmountRatio(particles: RID, ratio: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetAmountRatioBind, singleton, particles, ratio)
    }

    fun particlesSetLifetime(particles: RID, lifetime: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetLifetimeBind, singleton, particles, lifetime)
    }

    fun particlesSetOneShot(particles: RID, oneShot: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(particlesSetOneShotBind, singleton, particles, oneShot)
    }

    fun particlesSetPreProcessTime(particles: RID, time: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetPreProcessTimeBind, singleton, particles, time)
    }

    fun particlesRequestProcessTime(particles: RID, processTime: Double, processTimeResidual: Double = 0.0) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(particlesRequestProcessTimeBind, singleton, particles, processTime, processTimeResidual)
    }

    fun particlesSetExplosivenessRatio(particles: RID, ratio: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetExplosivenessRatioBind, singleton, particles, ratio)
    }

    fun particlesSetRandomnessRatio(particles: RID, ratio: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetRandomnessRatioBind, singleton, particles, ratio)
    }

    fun particlesSetInterpToEnd(particles: RID, factor: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetInterpToEndBind, singleton, particles, factor)
    }

    fun particlesSetEmitterVelocity(particles: RID, velocity: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(particlesSetEmitterVelocityBind, singleton, particles, velocity)
    }

    fun particlesSetCustomAabb(particles: RID, aabb: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(particlesSetCustomAabbBind, singleton, particles, aabb)
    }

    fun particlesSetSpeedScale(particles: RID, scale: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetSpeedScaleBind, singleton, particles, scale)
    }

    fun particlesSetUseLocalCoordinates(particles: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(particlesSetUseLocalCoordinatesBind, singleton, particles, enable)
    }

    fun particlesSetProcessMaterial(particles: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(particlesSetProcessMaterialBind, singleton, particles, material)
    }

    fun particlesSetFixedFps(particles: RID, fps: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(particlesSetFixedFpsBind, singleton, particles, fps)
    }

    fun particlesSetInterpolate(particles: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(particlesSetInterpolateBind, singleton, particles, enable)
    }

    fun particlesSetFractionalDelta(particles: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(particlesSetFractionalDeltaBind, singleton, particles, enable)
    }

    fun particlesSetCollisionBaseSize(particles: RID, size: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetCollisionBaseSizeBind, singleton, particles, size)
    }

    fun particlesSetTransformAlign(particles: RID, align: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesSetTransformAlignBind, singleton, particles, align)
    }

    fun particlesSetTransformAlignChannelFilter(particles: RID, channelFilter: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesSetTransformAlignChannelFilterBind, singleton, particles, channelFilter)
    }

    fun particlesSetTransformAlignAxis(particles: RID, rotationAxis: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesSetTransformAlignAxisBind, singleton, particles, rotationAxis)
    }

    fun particlesSetTrails(particles: RID, enable: Boolean, lengthSec: Double) {
        ObjectCalls.ptrcallWithRIDBoolDoubleArgs(particlesSetTrailsBind, singleton, particles, enable, lengthSec)
    }

    fun particlesIsInactive(particles: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(particlesIsInactiveBind, singleton, particles)
    }

    fun particlesRequestProcess(particles: RID) {
        ObjectCalls.ptrcallWithRIDArg(particlesRequestProcessBind, singleton, particles)
    }

    fun particlesRestart(particles: RID) {
        ObjectCalls.ptrcallWithRIDArg(particlesRestartBind, singleton, particles)
    }

    fun particlesSetSubemitter(particles: RID, subemitterParticles: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(particlesSetSubemitterBind, singleton, particles, subemitterParticles)
    }

    fun particlesEmit(particles: RID, transform: Transform3D, velocity: Vector3, color: Color, custom: Color, emitFlags: Long) {
        ObjectCalls.ptrcallWithRIDTransform3DVector3TwoColorUInt32Args(particlesEmitBind, singleton, particles, transform, velocity, color, custom, emitFlags)
    }

    fun particlesSetDrawOrder(particles: RID, order: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesSetDrawOrderBind, singleton, particles, order)
    }

    fun particlesSetDrawPasses(particles: RID, count: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(particlesSetDrawPassesBind, singleton, particles, count)
    }

    fun particlesSetDrawPassMesh(particles: RID, pass: Int, mesh: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(particlesSetDrawPassMeshBind, singleton, particles, pass, mesh)
    }

    fun particlesGetCurrentAabb(particles: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(particlesGetCurrentAabbBind, singleton, particles)
    }

    fun particlesSetEmissionTransform(particles: RID, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDAndTransform3DArg(particlesSetEmissionTransformBind, singleton, particles, transform)
    }

    fun particlesCollisionCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(particlesCollisionCreateBind, singleton)
    }

    fun particlesCollisionSetCollisionType(particlesCollision: RID, type: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesCollisionSetCollisionTypeBind, singleton, particlesCollision, type)
    }

    fun particlesCollisionSetCullMask(particlesCollision: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(particlesCollisionSetCullMaskBind, singleton, particlesCollision, mask)
    }

    fun particlesCollisionSetSphereRadius(particlesCollision: RID, radius: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesCollisionSetSphereRadiusBind, singleton, particlesCollision, radius)
    }

    fun particlesCollisionSetBoxExtents(particlesCollision: RID, extents: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(particlesCollisionSetBoxExtentsBind, singleton, particlesCollision, extents)
    }

    fun particlesCollisionSetAttractorStrength(particlesCollision: RID, strength: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesCollisionSetAttractorStrengthBind, singleton, particlesCollision, strength)
    }

    fun particlesCollisionSetAttractorDirectionality(particlesCollision: RID, amount: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesCollisionSetAttractorDirectionalityBind, singleton, particlesCollision, amount)
    }

    fun particlesCollisionSetAttractorAttenuation(particlesCollision: RID, curve: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesCollisionSetAttractorAttenuationBind, singleton, particlesCollision, curve)
    }

    fun particlesCollisionSetFieldTexture(particlesCollision: RID, texture: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(particlesCollisionSetFieldTextureBind, singleton, particlesCollision, texture)
    }

    fun particlesCollisionHeightFieldUpdate(particlesCollision: RID) {
        ObjectCalls.ptrcallWithRIDArg(particlesCollisionHeightFieldUpdateBind, singleton, particlesCollision)
    }

    fun particlesCollisionSetHeightFieldResolution(particlesCollision: RID, resolution: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesCollisionSetHeightFieldResolutionBind, singleton, particlesCollision, resolution)
    }

    fun particlesCollisionSetHeightFieldMask(particlesCollision: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(particlesCollisionSetHeightFieldMaskBind, singleton, particlesCollision, mask)
    }

    fun fogVolumeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(fogVolumeCreateBind, singleton)
    }

    fun fogVolumeSetShape(fogVolume: RID, shape: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(fogVolumeSetShapeBind, singleton, fogVolume, shape)
    }

    fun fogVolumeSetSize(fogVolume: RID, size: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(fogVolumeSetSizeBind, singleton, fogVolume, size)
    }

    fun fogVolumeSetMaterial(fogVolume: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(fogVolumeSetMaterialBind, singleton, fogVolume, material)
    }

    fun visibilityNotifierCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(visibilityNotifierCreateBind, singleton)
    }

    fun visibilityNotifierSetAabb(notifier: RID, aabb: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(visibilityNotifierSetAabbBind, singleton, notifier, aabb)
    }

    fun visibilityNotifierSetCallbacks(notifier: RID, enterCallable: GodotCallable, exitCallable: GodotCallable) {
        ObjectCalls.ptrcallWithRIDTwoCallableArgs(visibilityNotifierSetCallbacksBind, singleton, notifier, enterCallable.target.handle, enterCallable.method, exitCallable.target.handle, exitCallable.method)
    }

    fun occluderCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(occluderCreateBind, singleton)
    }

    fun cameraCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(cameraCreateBind, singleton)
    }

    fun cameraSetPerspective(camera: RID, fovyDegrees: Double, zNear: Double, zFar: Double) {
        ObjectCalls.ptrcallWithRIDAndThreeDoubleArgs(cameraSetPerspectiveBind, singleton, camera, fovyDegrees, zNear, zFar)
    }

    fun cameraSetOrthogonal(camera: RID, size: Double, zNear: Double, zFar: Double) {
        ObjectCalls.ptrcallWithRIDAndThreeDoubleArgs(cameraSetOrthogonalBind, singleton, camera, size, zNear, zFar)
    }

    fun cameraSetFrustum(camera: RID, size: Double, offset: Vector2, zNear: Double, zFar: Double) {
        ObjectCalls.ptrcallWithRIDDoubleVector2TwoDoubleArgs(cameraSetFrustumBind, singleton, camera, size, offset, zNear, zFar)
    }

    fun cameraSetTransform(camera: RID, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDAndTransform3DArg(cameraSetTransformBind, singleton, camera, transform)
    }

    fun cameraSetCullMask(camera: RID, layers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(cameraSetCullMaskBind, singleton, camera, layers)
    }

    fun cameraSetEnvironment(camera: RID, env: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(cameraSetEnvironmentBind, singleton, camera, env)
    }

    fun cameraSetCameraAttributes(camera: RID, effects: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(cameraSetCameraAttributesBind, singleton, camera, effects)
    }

    fun cameraSetCompositor(camera: RID, compositor: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(cameraSetCompositorBind, singleton, camera, compositor)
    }

    fun cameraSetUseVerticalAspect(camera: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(cameraSetUseVerticalAspectBind, singleton, camera, enable)
    }

    fun viewportCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(viewportCreateBind, singleton)
    }

    fun viewportSetUseXr(viewport: RID, useXr: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetUseXrBind, singleton, viewport, useXr)
    }

    fun viewportSetSize(viewport: RID, width: Int, height: Int, viewCount: Int = 1) {
        ObjectCalls.ptrcallWithRIDAndThreeIntArgs(viewportSetSizeBind, singleton, viewport, width, height, viewCount)
    }

    fun viewportSetActive(viewport: RID, active: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetActiveBind, singleton, viewport, active)
    }

    fun viewportSetParentViewport(viewport: RID, parentViewport: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportSetParentViewportBind, singleton, viewport, parentViewport)
    }

    fun viewportAttachToScreen(viewport: RID, rect: Rect2, screen: Int = 0) {
        ObjectCalls.ptrcallWithRIDRect2IntArgs(viewportAttachToScreenBind, singleton, viewport, rect, screen)
    }

    fun viewportSetRenderDirectToScreen(viewport: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetRenderDirectToScreenBind, singleton, viewport, enabled)
    }

    fun viewportSetCanvasCullMask(viewport: RID, canvasCullMask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(viewportSetCanvasCullMaskBind, singleton, viewport, canvasCullMask)
    }

    fun viewportSetScaling3dMode(viewport: RID, scaling3dMode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetScaling3dModeBind, singleton, viewport, scaling3dMode)
    }

    fun viewportSetScaling3dScale(viewport: RID, scale: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(viewportSetScaling3dScaleBind, singleton, viewport, scale)
    }

    fun viewportSetFsrSharpness(viewport: RID, sharpness: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(viewportSetFsrSharpnessBind, singleton, viewport, sharpness)
    }

    fun viewportSetTextureMipmapBias(viewport: RID, mipmapBias: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(viewportSetTextureMipmapBiasBind, singleton, viewport, mipmapBias)
    }

    fun viewportSetAnisotropicFilteringLevel(viewport: RID, anisotropicFilteringLevel: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetAnisotropicFilteringLevelBind, singleton, viewport, anisotropicFilteringLevel)
    }

    fun viewportSetUpdateMode(viewport: RID, updateMode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetUpdateModeBind, singleton, viewport, updateMode)
    }

    fun viewportGetUpdateMode(viewport: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(viewportGetUpdateModeBind, singleton, viewport)
    }

    fun viewportSetClearMode(viewport: RID, clearMode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetClearModeBind, singleton, viewport, clearMode)
    }

    fun viewportGetRenderTarget(viewport: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(viewportGetRenderTargetBind, singleton, viewport)
    }

    fun viewportGetTexture(viewport: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(viewportGetTextureBind, singleton, viewport)
    }

    fun viewportSetDisable3d(viewport: RID, disable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetDisable3dBind, singleton, viewport, disable)
    }

    fun viewportSetDisable2d(viewport: RID, disable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetDisable2dBind, singleton, viewport, disable)
    }

    fun viewportSetEnvironmentMode(viewport: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetEnvironmentModeBind, singleton, viewport, mode)
    }

    fun viewportAttachCamera(viewport: RID, camera: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportAttachCameraBind, singleton, viewport, camera)
    }

    fun viewportSetScenario(viewport: RID, scenario: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportSetScenarioBind, singleton, viewport, scenario)
    }

    fun viewportAttachCanvas(viewport: RID, canvas: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportAttachCanvasBind, singleton, viewport, canvas)
    }

    fun viewportRemoveCanvas(viewport: RID, canvas: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportRemoveCanvasBind, singleton, viewport, canvas)
    }

    fun viewportSetSnap2dTransformsToPixel(viewport: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetSnap2dTransformsToPixelBind, singleton, viewport, enabled)
    }

    fun viewportSetSnap2dVerticesToPixel(viewport: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetSnap2dVerticesToPixelBind, singleton, viewport, enabled)
    }

    fun viewportSetDefaultCanvasItemTextureFilter(viewport: RID, filter: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetDefaultCanvasItemTextureFilterBind, singleton, viewport, filter)
    }

    fun viewportSetDefaultCanvasItemTextureRepeat(viewport: RID, repeat: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetDefaultCanvasItemTextureRepeatBind, singleton, viewport, repeat)
    }

    fun viewportSetCanvasTransform(viewport: RID, canvas: RID, offset: Transform2D) {
        ObjectCalls.ptrcallWithTwoRIDAndTransform2DArg(viewportSetCanvasTransformBind, singleton, viewport, canvas, offset)
    }

    fun viewportSetCanvasStacking(viewport: RID, canvas: RID, layer: Int, sublayer: Int) {
        ObjectCalls.ptrcallWithTwoRIDTwoIntArgs(viewportSetCanvasStackingBind, singleton, viewport, canvas, layer, sublayer)
    }

    fun viewportSetTransparentBackground(viewport: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetTransparentBackgroundBind, singleton, viewport, enabled)
    }

    fun viewportSetGlobalCanvasTransform(viewport: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(viewportSetGlobalCanvasTransformBind, singleton, viewport, transform)
    }

    fun viewportSetSdfOversizeAndScale(viewport: RID, oversize: Long, scale: Long) {
        ObjectCalls.ptrcallWithRIDAndTwoLongArgs(viewportSetSdfOversizeAndScaleBind, singleton, viewport, oversize, scale)
    }

    fun viewportSetPositionalShadowAtlasSize(viewport: RID, size: Int, use16Bits: Boolean = false) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(viewportSetPositionalShadowAtlasSizeBind, singleton, viewport, size, use16Bits)
    }

    fun viewportSetPositionalShadowAtlasQuadrantSubdivision(viewport: RID, quadrant: Int, subdivision: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(viewportSetPositionalShadowAtlasQuadrantSubdivisionBind, singleton, viewport, quadrant, subdivision)
    }

    fun viewportSetMsaa3d(viewport: RID, msaa: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetMsaa3dBind, singleton, viewport, msaa)
    }

    fun viewportSetMsaa2d(viewport: RID, msaa: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetMsaa2dBind, singleton, viewport, msaa)
    }

    fun viewportSetUseHdr2d(viewport: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetUseHdr2dBind, singleton, viewport, enabled)
    }

    fun viewportSetScreenSpaceAa(viewport: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetScreenSpaceAaBind, singleton, viewport, mode)
    }

    fun viewportSetUseTaa(viewport: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetUseTaaBind, singleton, viewport, enable)
    }

    fun viewportSetUseDebanding(viewport: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetUseDebandingBind, singleton, viewport, enable)
    }

    fun viewportSetUseOcclusionCulling(viewport: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetUseOcclusionCullingBind, singleton, viewport, enable)
    }

    fun viewportSetOcclusionRaysPerThread(raysPerThread: Int) {
        ObjectCalls.ptrcallWithIntArg(viewportSetOcclusionRaysPerThreadBind, singleton, raysPerThread)
    }

    fun viewportSetOcclusionCullingBuildQuality(quality: Long) {
        ObjectCalls.ptrcallWithLongArg(viewportSetOcclusionCullingBuildQualityBind, singleton, quality)
    }

    fun viewportGetRenderInfo(viewport: RID, type: Long, info: Long): Int {
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetInt(viewportGetRenderInfoBind, singleton, viewport, type, info)
    }

    fun viewportSetDebugDraw(viewport: RID, draw: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetDebugDrawBind, singleton, viewport, draw)
    }

    fun viewportSetMeasureRenderTime(viewport: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetMeasureRenderTimeBind, singleton, viewport, enable)
    }

    fun viewportGetMeasuredRenderTimeCpu(viewport: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(viewportGetMeasuredRenderTimeCpuBind, singleton, viewport)
    }

    fun viewportGetMeasuredRenderTimeGpu(viewport: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(viewportGetMeasuredRenderTimeGpuBind, singleton, viewport)
    }

    fun viewportSetVrsMode(viewport: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetVrsModeBind, singleton, viewport, mode)
    }

    fun viewportSetVrsUpdateMode(viewport: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetVrsUpdateModeBind, singleton, viewport, mode)
    }

    fun viewportSetVrsTexture(viewport: RID, texture: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportSetVrsTextureBind, singleton, viewport, texture)
    }

    fun skyCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(skyCreateBind, singleton)
    }

    fun skySetRadianceSize(sky: RID, radianceSize: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(skySetRadianceSizeBind, singleton, sky, radianceSize)
    }

    fun skySetMode(sky: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(skySetModeBind, singleton, sky, mode)
    }

    fun skySetMaterial(sky: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(skySetMaterialBind, singleton, sky, material)
    }

    fun skyBakePanorama(sky: RID, energy: Double, bakeIrradiance: Boolean, size: Vector2i): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithRIDDoubleBoolVector2iArgsRetObject(skyBakePanoramaBind, singleton, sky, energy, bakeIrradiance, size))
    }

    fun compositorEffectCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(compositorEffectCreateBind, singleton)
    }

    fun compositorEffectSetEnabled(effect: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(compositorEffectSetEnabledBind, singleton, effect, enabled)
    }

    fun compositorEffectSetCallback(effect: RID, callbackType: Long, callback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDLongCallableArgs(compositorEffectSetCallbackBind, singleton, effect, callbackType, callback.target.handle, callback.method)
    }

    fun compositorEffectSetFlag(effect: RID, flag: Long, set: Boolean) {
        ObjectCalls.ptrcallWithRIDLongAndBoolArgs(compositorEffectSetFlagBind, singleton, effect, flag, set)
    }

    fun compositorCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(compositorCreateBind, singleton)
    }

    fun environmentCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(environmentCreateBind, singleton)
    }

    fun environmentSetBackground(env: RID, bg: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(environmentSetBackgroundBind, singleton, env, bg)
    }

    fun environmentSetCameraId(env: RID, id: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(environmentSetCameraIdBind, singleton, env, id)
    }

    fun environmentSetSky(env: RID, sky: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(environmentSetSkyBind, singleton, env, sky)
    }

    fun environmentSetSkyCustomFov(env: RID, scale: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(environmentSetSkyCustomFovBind, singleton, env, scale)
    }

    fun environmentSetSkyOrientation(env: RID, orientation: Basis) {
        ObjectCalls.ptrcallWithRIDAndBasisArg(environmentSetSkyOrientationBind, singleton, env, orientation)
    }

    fun environmentSetBgColor(env: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(environmentSetBgColorBind, singleton, env, color)
    }

    fun environmentSetBgEnergy(env: RID, multiplier: Double, exposureValue: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(environmentSetBgEnergyBind, singleton, env, multiplier, exposureValue)
    }

    fun environmentSetCanvasMaxLayer(env: RID, maxLayer: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(environmentSetCanvasMaxLayerBind, singleton, env, maxLayer)
    }

    fun environmentSetAmbientLight(env: RID, color: Color, ambient: Long = 0L, energy: Double = 1.0, skyContribution: Double = 0.0, reflectionSource: Long = 0L) {
        ObjectCalls.ptrcallWithRIDColorLongTwoDoubleLongArgs(environmentSetAmbientLightBind, singleton, env, color, ambient, energy, skyContribution, reflectionSource)
    }

    fun environmentSetTonemap(env: RID, toneMapper: Long, exposure: Double, white: Double) {
        ObjectCalls.ptrcallWithRIDLongTwoDoubleArgs(environmentSetTonemapBind, singleton, env, toneMapper, exposure, white)
    }

    fun environmentSetTonemapAgxContrast(env: RID, agxContrast: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(environmentSetTonemapAgxContrastBind, singleton, env, agxContrast)
    }

    fun environmentSetAdjustment(env: RID, enable: Boolean, brightness: Double, contrast: Double, saturation: Double, use1dColorCorrection: Boolean, colorCorrection: RID) {
        ObjectCalls.ptrcallWithRIDBoolThreeDoubleBoolRIDArgs(environmentSetAdjustmentBind, singleton, env, enable, brightness, contrast, saturation, use1dColorCorrection, colorCorrection)
    }

    fun environmentSetSsr(env: RID, enable: Boolean, maxSteps: Int, fadeIn: Double, fadeOut: Double, depthTolerance: Double) {
        ObjectCalls.ptrcallWithRIDBoolIntThreeDoubleArgs(environmentSetSsrBind, singleton, env, enable, maxSteps, fadeIn, fadeOut, depthTolerance)
    }

    fun environmentSetSsao(env: RID, enable: Boolean, radius: Double, intensity: Double, power: Double, detail: Double, horizon: Double, sharpness: Double, lightAffect: Double, aoChannelAffect: Double) {
        ObjectCalls.ptrcallWithRIDBoolDoubleDoubleDoubleDoubleDoubleDoubleDoubleDoubleArgs(environmentSetSsaoBind, singleton, env, enable, radius, intensity, power, detail, horizon, sharpness, lightAffect, aoChannelAffect)
    }

    fun environmentSetFog(env: RID, enable: Boolean, lightColor: Color, lightEnergy: Double, sunScatter: Double, density: Double, height: Double, heightDensity: Double, aerialPerspective: Double, skyAffect: Double, fogMode: Long = 0L) {
        ObjectCalls.ptrcallWithRIDBoolColorDoubleDoubleDoubleDoubleDoubleDoubleDoubleLongArgs(environmentSetFogBind, singleton, env, enable, lightColor, lightEnergy, sunScatter, density, height, heightDensity, aerialPerspective, skyAffect, fogMode)
    }

    fun environmentSetFogDepth(env: RID, curve: Double, begin: Double, end: Double) {
        ObjectCalls.ptrcallWithRIDAndThreeDoubleArgs(environmentSetFogDepthBind, singleton, env, curve, begin, end)
    }

    fun environmentSetSdfgi(env: RID, enable: Boolean, cascades: Int, minCellSize: Double, yScale: Long, useOcclusion: Boolean, bounceFeedback: Double, readSky: Boolean, energy: Double, normalBias: Double, probeBias: Double) {
        ObjectCalls.ptrcallWithRIDBoolIntDoubleLongBoolDoubleBoolThreeDoubleArgs(environmentSetSdfgiBind, singleton, env, enable, cascades, minCellSize, yScale, useOcclusion, bounceFeedback, readSky, energy, normalBias, probeBias)
    }

    fun environmentSetVolumetricFog(env: RID, enable: Boolean, density: Double, albedo: Color, emission: Color, emissionEnergy: Double, anisotropy: Double, length: Double, detailSpread: Double, giInject: Double, temporalReprojection: Boolean, temporalReprojectionAmount: Double, ambientInject: Double, skyAffect: Double) {
        ObjectCalls.ptrcallWithRIDBoolDoubleTwoColorDoubleDoubleDoubleDoubleDoubleBoolThreeDoubleArgs(environmentSetVolumetricFogBind, singleton, env, enable, density, albedo, emission, emissionEnergy, anisotropy, length, detailSpread, giInject, temporalReprojection, temporalReprojectionAmount, ambientInject, skyAffect)
    }

    fun environmentGlowSetUseBicubicUpscale(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(environmentGlowSetUseBicubicUpscaleBind, singleton, enable)
    }

    fun environmentSetSsrHalfSize(halfSize: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(environmentSetSsrHalfSizeBind, singleton, halfSize)
    }

    fun environmentSetSsrRoughnessQuality(quality: Long) {
        ObjectCalls.ptrcallWithLongArg(environmentSetSsrRoughnessQualityBind, singleton, quality)
    }

    fun environmentSetSsaoQuality(quality: Long, halfSize: Boolean, adaptiveTarget: Double, blurPasses: Int, fadeoutFrom: Double, fadeoutTo: Double) {
        ObjectCalls.ptrcallWithLongBoolDoubleIntAndTwoDoubleArgs(environmentSetSsaoQualityBind, singleton, quality, halfSize, adaptiveTarget, blurPasses, fadeoutFrom, fadeoutTo)
    }

    fun environmentSetSsilQuality(quality: Long, halfSize: Boolean, adaptiveTarget: Double, blurPasses: Int, fadeoutFrom: Double, fadeoutTo: Double) {
        ObjectCalls.ptrcallWithLongBoolDoubleIntAndTwoDoubleArgs(environmentSetSsilQualityBind, singleton, quality, halfSize, adaptiveTarget, blurPasses, fadeoutFrom, fadeoutTo)
    }

    fun environmentSetSdfgiRayCount(rayCount: Long) {
        ObjectCalls.ptrcallWithLongArg(environmentSetSdfgiRayCountBind, singleton, rayCount)
    }

    fun environmentSetSdfgiFramesToConverge(frames: Long) {
        ObjectCalls.ptrcallWithLongArg(environmentSetSdfgiFramesToConvergeBind, singleton, frames)
    }

    fun environmentSetSdfgiFramesToUpdateLight(frames: Long) {
        ObjectCalls.ptrcallWithLongArg(environmentSetSdfgiFramesToUpdateLightBind, singleton, frames)
    }

    fun environmentSetVolumetricFogVolumeSize(size: Int, depth: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(environmentSetVolumetricFogVolumeSizeBind, singleton, size, depth)
    }

    fun environmentSetVolumetricFogFilterActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(environmentSetVolumetricFogFilterActiveBind, singleton, active)
    }

    fun environmentBakePanorama(environment: RID, bakeIrradiance: Boolean, size: Vector2i): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithRIDBoolVector2iArgsRetObject(environmentBakePanoramaBind, singleton, environment, bakeIrradiance, size))
    }

    fun screenSpaceRoughnessLimiterSetActive(enable: Boolean, amount: Double, limit: Double) {
        ObjectCalls.ptrcallWithBoolTwoDoubleArgs(screenSpaceRoughnessLimiterSetActiveBind, singleton, enable, amount, limit)
    }

    fun subSurfaceScatteringSetQuality(quality: Long) {
        ObjectCalls.ptrcallWithLongArg(subSurfaceScatteringSetQualityBind, singleton, quality)
    }

    fun subSurfaceScatteringSetScale(scale: Double, depthScale: Double) {
        ObjectCalls.ptrcallWithTwoDoubleArgs(subSurfaceScatteringSetScaleBind, singleton, scale, depthScale)
    }

    fun cameraAttributesCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(cameraAttributesCreateBind, singleton)
    }

    fun cameraAttributesSetDofBlurQuality(quality: Long, useJitter: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(cameraAttributesSetDofBlurQualityBind, singleton, quality, useJitter)
    }

    fun cameraAttributesSetDofBlurBokehShape(shape: Long) {
        ObjectCalls.ptrcallWithLongArg(cameraAttributesSetDofBlurBokehShapeBind, singleton, shape)
    }

    fun cameraAttributesSetDofBlur(cameraAttributes: RID, farEnable: Boolean, farDistance: Double, farTransition: Double, nearEnable: Boolean, nearDistance: Double, nearTransition: Double, amount: Double) {
        ObjectCalls.ptrcallWithRIDBoolTwoDoubleBoolThreeDoubleArgs(cameraAttributesSetDofBlurBind, singleton, cameraAttributes, farEnable, farDistance, farTransition, nearEnable, nearDistance, nearTransition, amount)
    }

    fun cameraAttributesSetExposure(cameraAttributes: RID, multiplier: Double, normalization: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(cameraAttributesSetExposureBind, singleton, cameraAttributes, multiplier, normalization)
    }

    fun cameraAttributesSetAutoExposure(cameraAttributes: RID, enable: Boolean, minSensitivity: Double, maxSensitivity: Double, speed: Double, scale: Double) {
        ObjectCalls.ptrcallWithRIDBoolFourDoubleArgs(cameraAttributesSetAutoExposureBind, singleton, cameraAttributes, enable, minSensitivity, maxSensitivity, speed, scale)
    }

    fun scenarioCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(scenarioCreateBind, singleton)
    }

    fun scenarioSetEnvironment(scenario: RID, environment: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(scenarioSetEnvironmentBind, singleton, scenario, environment)
    }

    fun scenarioSetFallbackEnvironment(scenario: RID, environment: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(scenarioSetFallbackEnvironmentBind, singleton, scenario, environment)
    }

    fun scenarioSetCameraAttributes(scenario: RID, effects: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(scenarioSetCameraAttributesBind, singleton, scenario, effects)
    }

    fun scenarioSetCompositor(scenario: RID, compositor: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(scenarioSetCompositorBind, singleton, scenario, compositor)
    }

    fun instanceCreate2(base: RID, scenario: RID): RID {
        return ObjectCalls.ptrcallWithTwoRIDArgsRetRID(instanceCreate2Bind, singleton, base, scenario)
    }

    fun instanceCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(instanceCreateBind, singleton)
    }

    fun instanceSetBase(instance: RID, base: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceSetBaseBind, singleton, instance, base)
    }

    fun instanceSetScenario(instance: RID, scenario: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceSetScenarioBind, singleton, instance, scenario)
    }

    fun instanceSetLayerMask(instance: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(instanceSetLayerMaskBind, singleton, instance, mask)
    }

    fun instanceSetPivotData(instance: RID, sortingOffset: Double, useAabbCenter: Boolean) {
        ObjectCalls.ptrcallWithRIDDoubleBoolArgs(instanceSetPivotDataBind, singleton, instance, sortingOffset, useAabbCenter)
    }

    fun instanceSetTransform(instance: RID, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDAndTransform3DArg(instanceSetTransformBind, singleton, instance, transform)
    }

    fun instanceAttachObjectInstanceId(instance: RID, id: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(instanceAttachObjectInstanceIdBind, singleton, instance, id)
    }

    fun instanceSetBlendShapeWeight(instance: RID, shape: Int, weight: Double) {
        ObjectCalls.ptrcallWithRIDIntDoubleArgs(instanceSetBlendShapeWeightBind, singleton, instance, shape, weight)
    }

    fun instanceSetSurfaceOverrideMaterial(instance: RID, surface: Int, material: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(instanceSetSurfaceOverrideMaterialBind, singleton, instance, surface, material)
    }

    fun instanceSetVisible(instance: RID, visible: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(instanceSetVisibleBind, singleton, instance, visible)
    }

    fun instanceGeometrySetTransparency(instance: RID, transparency: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(instanceGeometrySetTransparencyBind, singleton, instance, transparency)
    }

    fun instanceTeleport(instance: RID) {
        ObjectCalls.ptrcallWithRIDArg(instanceTeleportBind, singleton, instance)
    }

    fun instanceSetCustomAabb(instance: RID, aabb: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(instanceSetCustomAabbBind, singleton, instance, aabb)
    }

    fun instanceAttachSkeleton(instance: RID, skeleton: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceAttachSkeletonBind, singleton, instance, skeleton)
    }

    fun instanceSetExtraVisibilityMargin(instance: RID, margin: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(instanceSetExtraVisibilityMarginBind, singleton, instance, margin)
    }

    fun instanceSetVisibilityParent(instance: RID, parent: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceSetVisibilityParentBind, singleton, instance, parent)
    }

    fun instanceSetIgnoreCulling(instance: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(instanceSetIgnoreCullingBind, singleton, instance, enabled)
    }

    fun instanceGeometrySetFlag(instance: RID, flag: Long, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDLongAndBoolArgs(instanceGeometrySetFlagBind, singleton, instance, flag, enabled)
    }

    fun instanceGeometrySetCastShadowsSetting(instance: RID, shadowCastingSetting: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(instanceGeometrySetCastShadowsSettingBind, singleton, instance, shadowCastingSetting)
    }

    fun instanceGeometrySetMaterialOverride(instance: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceGeometrySetMaterialOverrideBind, singleton, instance, material)
    }

    fun instanceGeometrySetMaterialOverlay(instance: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceGeometrySetMaterialOverlayBind, singleton, instance, material)
    }

    fun instanceGeometrySetVisibilityRange(instance: RID, min: Double, max: Double, minMargin: Double, maxMargin: Double, fadeMode: Long) {
        ObjectCalls.ptrcallWithRIDFourDoubleLongArgs(instanceGeometrySetVisibilityRangeBind, singleton, instance, min, max, minMargin, maxMargin, fadeMode)
    }

    fun instanceGeometrySetLightmap(instance: RID, lightmap: RID, lightmapUvScale: Rect2, lightmapSlice: Int) {
        ObjectCalls.ptrcallWithTwoRIDRect2IntArgs(instanceGeometrySetLightmapBind, singleton, instance, lightmap, lightmapUvScale, lightmapSlice)
    }

    fun instanceGeometrySetLodBias(instance: RID, lodBias: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(instanceGeometrySetLodBiasBind, singleton, instance, lodBias)
    }

    fun canvasCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasCreateBind, singleton)
    }

    fun canvasSetItemMirroring(canvas: RID, item: RID, mirroring: Vector2) {
        ObjectCalls.ptrcallWithTwoRIDAndVector2Arg(canvasSetItemMirroringBind, singleton, canvas, item, mirroring)
    }

    fun canvasSetItemRepeat(item: RID, repeatSize: Vector2, repeatTimes: Int) {
        ObjectCalls.ptrcallWithRIDVector2IntArgs(canvasSetItemRepeatBind, singleton, item, repeatSize, repeatTimes)
    }

    fun canvasSetModulate(canvas: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(canvasSetModulateBind, singleton, canvas, color)
    }

    fun canvasSetDisableScale(disable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(canvasSetDisableScaleBind, singleton, disable)
    }

    fun canvasTextureCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasTextureCreateBind, singleton)
    }

    fun canvasTextureSetChannel(canvasTexture: RID, channel: Long, texture: RID) {
        ObjectCalls.ptrcallWithRIDLongAndRIDArgs(canvasTextureSetChannelBind, singleton, canvasTexture, channel, texture)
    }

    fun canvasTextureSetShadingParameters(canvasTexture: RID, baseColor: Color, shininess: Double) {
        ObjectCalls.ptrcallWithRIDColorDoubleArgs(canvasTextureSetShadingParametersBind, singleton, canvasTexture, baseColor, shininess)
    }

    fun canvasTextureSetTextureFilter(canvasTexture: RID, filter: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasTextureSetTextureFilterBind, singleton, canvasTexture, filter)
    }

    fun canvasTextureSetTextureRepeat(canvasTexture: RID, repeat: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasTextureSetTextureRepeatBind, singleton, canvasTexture, repeat)
    }

    fun canvasItemCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasItemCreateBind, singleton)
    }

    fun canvasItemSetParent(item: RID, parent: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasItemSetParentBind, singleton, item, parent)
    }

    fun canvasItemSetDefaultTextureFilter(item: RID, filter: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasItemSetDefaultTextureFilterBind, singleton, item, filter)
    }

    fun canvasItemSetDefaultTextureRepeat(item: RID, repeat: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasItemSetDefaultTextureRepeatBind, singleton, item, repeat)
    }

    fun canvasItemSetVisible(item: RID, visible: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetVisibleBind, singleton, item, visible)
    }

    fun canvasItemSetLightMask(item: RID, mask: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasItemSetLightMaskBind, singleton, item, mask)
    }

    fun canvasItemSetVisibilityLayer(item: RID, visibilityLayer: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(canvasItemSetVisibilityLayerBind, singleton, item, visibilityLayer)
    }

    fun canvasItemSetTransform(item: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasItemSetTransformBind, singleton, item, transform)
    }

    fun canvasItemSetClip(item: RID, clip: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetClipBind, singleton, item, clip)
    }

    fun canvasItemSetDistanceFieldMode(item: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetDistanceFieldModeBind, singleton, item, enabled)
    }

    fun canvasItemSetCustomRect(item: RID, useCustomRect: Boolean, rect: Rect2) {
        ObjectCalls.ptrcallWithRIDBoolRect2Args(canvasItemSetCustomRectBind, singleton, item, useCustomRect, rect)
    }

    fun canvasItemSetModulate(item: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(canvasItemSetModulateBind, singleton, item, color)
    }

    fun canvasItemSetSelfModulate(item: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(canvasItemSetSelfModulateBind, singleton, item, color)
    }

    fun canvasItemSetDrawBehindParent(item: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetDrawBehindParentBind, singleton, item, enabled)
    }

    fun canvasItemSetInterpolated(item: RID, interpolated: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetInterpolatedBind, singleton, item, interpolated)
    }

    fun canvasItemResetPhysicsInterpolation(item: RID) {
        ObjectCalls.ptrcallWithRIDArg(canvasItemResetPhysicsInterpolationBind, singleton, item)
    }

    fun canvasItemTransformPhysicsInterpolation(item: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasItemTransformPhysicsInterpolationBind, singleton, item, transform)
    }

    fun canvasItemAddLine(item: RID, from: Vector2, to: Vector2, color: Color, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRIDTwoVector2ColorDoubleBoolArgs(canvasItemAddLineBind, singleton, item, from, to, color, width, antialiased)
    }

    fun canvasItemAddRect(item: RID, rect: Rect2, color: Color, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRIDRect2ColorBoolArgs(canvasItemAddRectBind, singleton, item, rect, color, antialiased)
    }

    fun canvasItemAddCircle(item: RID, pos: Vector2, radius: Double, color: Color, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRIDVector2DoubleColorBoolArgs(canvasItemAddCircleBind, singleton, item, pos, radius, color, antialiased)
    }

    fun canvasItemAddEllipse(item: RID, pos: Vector2, major: Double, minor: Double, color: Color, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRIDVector2TwoDoubleColorBoolArgs(canvasItemAddEllipseBind, singleton, item, pos, major, minor, color, antialiased)
    }

    fun canvasItemAddTextureRect(item: RID, rect: Rect2, texture: RID, tile: Boolean = false, modulate: Color, transpose: Boolean = false) {
        ObjectCalls.ptrcallWithRIDRect2RIDBoolColorBoolArgs(canvasItemAddTextureRectBind, singleton, item, rect, texture, tile, modulate, transpose)
    }

    fun canvasItemAddMsdfTextureRectRegion(item: RID, rect: Rect2, texture: RID, srcRect: Rect2, modulate: Color, outlineSize: Int = 0, pxRange: Double = 1.0, scale: Double = 1.0) {
        ObjectCalls.ptrcallWithRIDRect2RIDRect2ColorIntTwoDoubleArgs(canvasItemAddMsdfTextureRectRegionBind, singleton, item, rect, texture, srcRect, modulate, outlineSize, pxRange, scale)
    }

    fun canvasItemAddLcdTextureRectRegion(item: RID, rect: Rect2, texture: RID, srcRect: Rect2, modulate: Color) {
        ObjectCalls.ptrcallWithRIDRect2RIDRect2ColorArgs(canvasItemAddLcdTextureRectRegionBind, singleton, item, rect, texture, srcRect, modulate)
    }

    fun canvasItemAddTextureRectRegion(item: RID, rect: Rect2, texture: RID, srcRect: Rect2, modulate: Color, transpose: Boolean = false, clipUv: Boolean = true) {
        ObjectCalls.ptrcallWithRIDRect2RIDRect2ColorTwoBoolArgs(canvasItemAddTextureRectRegionBind, singleton, item, rect, texture, srcRect, modulate, transpose, clipUv)
    }

    fun canvasItemAddNinePatch(item: RID, rect: Rect2, source: Rect2, texture: RID, topleft: Vector2, bottomright: Vector2, xAxisMode: Long = 0L, yAxisMode: Long = 0L, drawCenter: Boolean = true, modulate: Color) {
        ObjectCalls.ptrcallWithRIDTwoRect2RIDTwoVector2TwoLongBoolColorArgs(canvasItemAddNinePatchBind, singleton, item, rect, source, texture, topleft, bottomright, xAxisMode, yAxisMode, drawCenter, modulate)
    }

    fun canvasItemAddMesh(item: RID, mesh: RID, transform: Transform2D, modulate: Color, texture: RID) {
        ObjectCalls.ptrcallWithTwoRIDTransform2DColorRIDArgs(canvasItemAddMeshBind, singleton, item, mesh, transform, modulate, texture)
    }

    fun canvasItemAddMultimesh(item: RID, mesh: RID, texture: RID) {
        ObjectCalls.ptrcallWithThreeRIDArgs(canvasItemAddMultimeshBind, singleton, item, mesh, texture)
    }

    fun canvasItemAddParticles(item: RID, particles: RID, texture: RID) {
        ObjectCalls.ptrcallWithThreeRIDArgs(canvasItemAddParticlesBind, singleton, item, particles, texture)
    }

    fun canvasItemAddSetTransform(item: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasItemAddSetTransformBind, singleton, item, transform)
    }

    fun canvasItemAddClipIgnore(item: RID, ignore: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemAddClipIgnoreBind, singleton, item, ignore)
    }

    fun canvasItemAddAnimationSlice(item: RID, animationLength: Double, sliceBegin: Double, sliceEnd: Double, offset: Double = 0.0) {
        ObjectCalls.ptrcallWithRIDFourDoubleArgs(canvasItemAddAnimationSliceBind, singleton, item, animationLength, sliceBegin, sliceEnd, offset)
    }

    fun canvasItemSetSortChildrenByY(item: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetSortChildrenByYBind, singleton, item, enabled)
    }

    fun canvasItemSetZIndex(item: RID, zIndex: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasItemSetZIndexBind, singleton, item, zIndex)
    }

    fun canvasItemSetZAsRelativeToParent(item: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetZAsRelativeToParentBind, singleton, item, enabled)
    }

    fun canvasItemSetCopyToBackbuffer(item: RID, enabled: Boolean, rect: Rect2) {
        ObjectCalls.ptrcallWithRIDBoolRect2Args(canvasItemSetCopyToBackbufferBind, singleton, item, enabled, rect)
    }

    fun canvasItemAttachSkeleton(item: RID, skeleton: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasItemAttachSkeletonBind, singleton, item, skeleton)
    }

    fun canvasItemClear(item: RID) {
        ObjectCalls.ptrcallWithRIDArg(canvasItemClearBind, singleton, item)
    }

    fun canvasItemSetDrawIndex(item: RID, index: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasItemSetDrawIndexBind, singleton, item, index)
    }

    fun canvasItemSetMaterial(item: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasItemSetMaterialBind, singleton, item, material)
    }

    fun canvasItemSetUseParentMaterial(item: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetUseParentMaterialBind, singleton, item, enabled)
    }

    fun canvasItemSetVisibilityNotifier(item: RID, enable: Boolean, area: Rect2, enterCallable: GodotCallable, exitCallable: GodotCallable) {
        ObjectCalls.ptrcallWithRIDBoolRect2TwoCallableArgs(canvasItemSetVisibilityNotifierBind, singleton, item, enable, area, enterCallable.target.handle, enterCallable.method, exitCallable.target.handle, exitCallable.method)
    }

    fun canvasItemSetCanvasGroupMode(item: RID, mode: Long, clearMargin: Double = 5.0, fitEmpty: Boolean = false, fitMargin: Double = 0.0, blurMipmaps: Boolean = false) {
        ObjectCalls.ptrcallWithRIDLongDoubleBoolDoubleBoolArgs(canvasItemSetCanvasGroupModeBind, singleton, item, mode, clearMargin, fitEmpty, fitMargin, blurMipmaps)
    }

    fun debugCanvasItemGetRect(item: RID): Rect2 {
        return ObjectCalls.ptrcallWithRIDArgRetRect2(debugCanvasItemGetRectBind, singleton, item)
    }

    fun canvasLightCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasLightCreateBind, singleton)
    }

    fun canvasLightAttachToCanvas(light: RID, canvas: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasLightAttachToCanvasBind, singleton, light, canvas)
    }

    fun canvasLightSetEnabled(light: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightSetEnabledBind, singleton, light, enabled)
    }

    fun canvasLightSetTextureScale(light: RID, scale: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(canvasLightSetTextureScaleBind, singleton, light, scale)
    }

    fun canvasLightSetTransform(light: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasLightSetTransformBind, singleton, light, transform)
    }

    fun canvasLightSetTexture(light: RID, texture: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasLightSetTextureBind, singleton, light, texture)
    }

    fun canvasLightSetTextureOffset(light: RID, offset: Vector2) {
        ObjectCalls.ptrcallWithRIDAndVector2Arg(canvasLightSetTextureOffsetBind, singleton, light, offset)
    }

    fun canvasLightSetColor(light: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(canvasLightSetColorBind, singleton, light, color)
    }

    fun canvasLightSetHeight(light: RID, height: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(canvasLightSetHeightBind, singleton, light, height)
    }

    fun canvasLightSetEnergy(light: RID, energy: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(canvasLightSetEnergyBind, singleton, light, energy)
    }

    fun canvasLightSetZRange(light: RID, minZ: Int, maxZ: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(canvasLightSetZRangeBind, singleton, light, minZ, maxZ)
    }

    fun canvasLightSetLayerRange(light: RID, minLayer: Int, maxLayer: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(canvasLightSetLayerRangeBind, singleton, light, minLayer, maxLayer)
    }

    fun canvasLightSetItemCullMask(light: RID, mask: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasLightSetItemCullMaskBind, singleton, light, mask)
    }

    fun canvasLightSetItemShadowCullMask(light: RID, mask: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasLightSetItemShadowCullMaskBind, singleton, light, mask)
    }

    fun canvasLightSetMode(light: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasLightSetModeBind, singleton, light, mode)
    }

    fun canvasLightSetShadowEnabled(light: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightSetShadowEnabledBind, singleton, light, enabled)
    }

    fun canvasLightSetShadowFilter(light: RID, filter: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasLightSetShadowFilterBind, singleton, light, filter)
    }

    fun canvasLightSetShadowColor(light: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(canvasLightSetShadowColorBind, singleton, light, color)
    }

    fun canvasLightSetShadowSmooth(light: RID, smooth: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(canvasLightSetShadowSmoothBind, singleton, light, smooth)
    }

    fun canvasLightSetBlendMode(light: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasLightSetBlendModeBind, singleton, light, mode)
    }

    fun canvasLightSetInterpolated(light: RID, interpolated: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightSetInterpolatedBind, singleton, light, interpolated)
    }

    fun canvasLightResetPhysicsInterpolation(light: RID) {
        ObjectCalls.ptrcallWithRIDArg(canvasLightResetPhysicsInterpolationBind, singleton, light)
    }

    fun canvasLightTransformPhysicsInterpolation(light: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasLightTransformPhysicsInterpolationBind, singleton, light, transform)
    }

    fun canvasLightOccluderCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasLightOccluderCreateBind, singleton)
    }

    fun canvasLightOccluderAttachToCanvas(occluder: RID, canvas: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasLightOccluderAttachToCanvasBind, singleton, occluder, canvas)
    }

    fun canvasLightOccluderSetEnabled(occluder: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightOccluderSetEnabledBind, singleton, occluder, enabled)
    }

    fun canvasLightOccluderSetPolygon(occluder: RID, polygon: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasLightOccluderSetPolygonBind, singleton, occluder, polygon)
    }

    fun canvasLightOccluderSetAsSdfCollision(occluder: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightOccluderSetAsSdfCollisionBind, singleton, occluder, enable)
    }

    fun canvasLightOccluderSetTransform(occluder: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasLightOccluderSetTransformBind, singleton, occluder, transform)
    }

    fun canvasLightOccluderSetLightMask(occluder: RID, mask: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasLightOccluderSetLightMaskBind, singleton, occluder, mask)
    }

    fun canvasLightOccluderSetInterpolated(occluder: RID, interpolated: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightOccluderSetInterpolatedBind, singleton, occluder, interpolated)
    }

    fun canvasLightOccluderResetPhysicsInterpolation(occluder: RID) {
        ObjectCalls.ptrcallWithRIDArg(canvasLightOccluderResetPhysicsInterpolationBind, singleton, occluder)
    }

    fun canvasLightOccluderTransformPhysicsInterpolation(occluder: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasLightOccluderTransformPhysicsInterpolationBind, singleton, occluder, transform)
    }

    fun canvasOccluderPolygonCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasOccluderPolygonCreateBind, singleton)
    }

    fun canvasOccluderPolygonSetCullMode(occluderPolygon: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasOccluderPolygonSetCullModeBind, singleton, occluderPolygon, mode)
    }

    fun canvasSetShadowTextureSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(canvasSetShadowTextureSizeBind, singleton, size)
    }

    fun globalShaderParameterRemove(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(globalShaderParameterRemoveBind, singleton, name)
    }

    fun globalShaderParameterGetList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(globalShaderParameterGetListBind, singleton)
    }

    fun globalShaderParameterGet(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(globalShaderParameterGetBind, singleton, name)
    }

    fun globalShaderParameterGetType(name: String): Long {
        return ObjectCalls.ptrcallWithStringNameArgRetLong(globalShaderParameterGetTypeBind, singleton, name)
    }

    fun freeRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(freeRidBind, singleton, rid)
    }

    fun requestFrameDrawnCallback(callable: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(requestFrameDrawnCallbackBind, singleton, callable.target.handle, callable.method)
    }

    fun hasChanged(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasChangedBind, singleton)
    }

    fun getRenderingInfo(info: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(getRenderingInfoBind, singleton, info)
    }

    fun getVideoAdapterName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getVideoAdapterNameBind, singleton)
    }

    fun getVideoAdapterVendor(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getVideoAdapterVendorBind, singleton)
    }

    fun getVideoAdapterType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVideoAdapterTypeBind, singleton)
    }

    fun getVideoAdapterApiVersion(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getVideoAdapterApiVersionBind, singleton)
    }

    fun getCurrentRenderingDriverName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentRenderingDriverNameBind, singleton)
    }

    fun getCurrentRenderingMethod(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentRenderingMethodBind, singleton)
    }

    fun makeSphereMesh(latitudes: Int, longitudes: Int, radius: Double): RID {
        return ObjectCalls.ptrcallWithTwoIntDoubleArgsRetRID(makeSphereMeshBind, singleton, latitudes, longitudes, radius)
    }

    fun getTestCube(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getTestCubeBind, singleton)
    }

    fun getTestTexture(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getTestTextureBind, singleton)
    }

    fun getWhiteTexture(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getWhiteTextureBind, singleton)
    }

    fun setBootImageWithStretch(image: Image?, color: Color, stretchMode: Long, useFilter: Boolean = true) {
        ObjectCalls.ptrcallWithObjectColorLongBoolArgs(setBootImageWithStretchBind, singleton, image?.requireOpenHandle() ?: MemorySegment.NULL, color, stretchMode, useFilter)
    }

    fun setBootImage(image: Image?, color: Color, scale: Boolean, useFilter: Boolean = true) {
        ObjectCalls.ptrcallWithObjectColorTwoBoolArgs(setBootImageBind, singleton, image?.requireOpenHandle() ?: MemorySegment.NULL, color, scale, useFilter)
    }

    fun getDefaultClearColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getDefaultClearColorBind, singleton)
    }

    fun setDefaultClearColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setDefaultClearColorBind, singleton, color)
    }

    fun hasOsFeature(feature: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasOsFeatureBind, singleton, feature)
    }

    fun setDebugGenerateWireframes(generate: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDebugGenerateWireframesBind, singleton, generate)
    }

    fun isRenderLoopEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRenderLoopEnabledBind, singleton)
    }

    fun setRenderLoopEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRenderLoopEnabledBind, singleton, enabled)
    }

    fun getFrameSetupTimeCpu(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFrameSetupTimeCpuBind, singleton)
    }

    fun forceSync() {
        ObjectCalls.ptrcallNoArgs(forceSyncBind, singleton)
    }

    fun forceDraw(swapBuffers: Boolean = true, frameStep: Double = 0.0) {
        ObjectCalls.ptrcallWithBoolAndDoubleArgs(forceDrawBind, singleton, swapBuffers, frameStep)
    }

    fun isOnRenderThread(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnRenderThreadBind, singleton)
    }

    fun callOnRenderThread(callable: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(callOnRenderThreadBind, singleton, callable.target.handle, callable.method)
    }

    fun hasFeature(feature: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(hasFeatureBind, singleton, feature)
    }

    object Signals {
        const val framePreDraw: String = "frame_pre_draw"
        const val framePostDraw: String = "frame_post_draw"
    }

    fun fromHandle(handle: MemorySegment): RenderingServer? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): RenderingServer? =
        if (handle.address() == 0L) null else this

    private const val TEXTURE_2D_CREATE_HASH = 2010018390L
    private val texture2dCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_2d_create", TEXTURE_2D_CREATE_HASH)
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

    private const val MULTIMESH_GET_COMMAND_BUFFER_RD_RID_HASH = 3814569979L
    private val multimeshGetCommandBufferRdRidBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_command_buffer_rd_rid", MULTIMESH_GET_COMMAND_BUFFER_RD_RID_HASH)
    }

    private const val MULTIMESH_GET_BUFFER_RD_RID_HASH = 3814569979L
    private val multimeshGetBufferRdRidBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_buffer_rd_rid", MULTIMESH_GET_BUFFER_RD_RID_HASH)
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

    private const val VOXEL_GI_GET_OCTREE_SIZE_HASH = 2607699645L
    private val voxelGiGetOctreeSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_octree_size", VOXEL_GI_GET_OCTREE_SIZE_HASH)
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

    private const val CANVAS_OCCLUDER_POLYGON_SET_CULL_MODE_HASH = 1839404663L
    private val canvasOccluderPolygonSetCullModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_occluder_polygon_set_cull_mode", CANVAS_OCCLUDER_POLYGON_SET_CULL_MODE_HASH)
    }

    private const val CANVAS_SET_SHADOW_TEXTURE_SIZE_HASH = 1286410249L
    private val canvasSetShadowTextureSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_set_shadow_texture_size", CANVAS_SET_SHADOW_TEXTURE_SIZE_HASH)
    }

    private const val GLOBAL_SHADER_PARAMETER_REMOVE_HASH = 3304788590L
    private val globalShaderParameterRemoveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_remove", GLOBAL_SHADER_PARAMETER_REMOVE_HASH)
    }

    private const val GLOBAL_SHADER_PARAMETER_GET_LIST_HASH = 3995934104L
    private val globalShaderParameterGetListBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_get_list", GLOBAL_SHADER_PARAMETER_GET_LIST_HASH)
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
