#!/usr/bin/env python3
"""Generate conservative Kotlin API wrapper drafts from extension_api.json."""

from __future__ import annotations

import argparse
import sys
from pathlib import Path

from api_wrapper_candidates import CALL_SHAPES, CallShape, camel_name, const_name
from wrapper_model import (
    ApiClass,
    ApiMethod,
    ancestors,
    load_api_classes,
    load_api_singletons,
    object_type_names,
    scan_wrapper_classes,
)


OWNERSHIP_SENSITIVE_OBJECT_TYPES = {"Callable"}
SPECIAL_OBJECT_WRAPPER_TYPES = {
    "DirAccess": "DirAccessHandle",
    "FileAccess": "FileAccessHandle",
    "SceneTree": "SceneTreeHandle",
}
OWNERSHIP_SENSITIVE_METHODS = {
    ("RefCounted", "init_ref"),
    ("RefCounted", "reference"),
}
# Per-method by-design skips with their recorded rationale (task 28). These are NOT missing
# shapes: each was reviewed and deliberately left ungenerated; the reason lands verbatim in
# the generator report so the skip stays self-documenting. Cross-referenced in
# docs/contributing/wrapper-maintenance.md ("Long-tail triage").
BY_DESIGN_METHOD_SKIPS = {
    ("GDScriptTextDocument", "resolve"): (
        "by design: Dictionary->Dictionary passthrough via the generic Map<String, Any?> helper "
        "silently drops non-String keys; shape reverted in 27b06fe6 (editor-LSP only)"
    ),
    ("GDScriptTextDocument", "rename"): (
        "by design: Dictionary->Dictionary passthrough via the generic Map<String, Any?> helper "
        "silently drops non-String keys; shape reverted in 27b06fe6 (editor-LSP only)"
    ),
}

SCALAR_KOTLIN_TYPES = {
    "bool": "Boolean",
    "int32": "Int",
    "int64": "Long",
    "uint32": "Long",
    "enum": "Long",
    "bitfield": "Long",
    "float": "Double",
    "String": "String",
    "StringName": "String",
    "NodePath": "NodePath",
    "Variant": "Any?",
    "RID": "RID",
    "Basis": "Basis",
    "Color": "Color",
    "Plane": "Plane",
    "Projection": "Projection",
    "Vector2": "Vector2",
    "Vector2i": "Vector2i",
    "Vector3": "Vector3",
    "Vector3i": "Vector3i",
    "Vector4": "Vector4",
    "Rect2": "Rect2",
    "AABB": "AABB",
    "Transform2D": "Transform2D",
    "Transform3D": "Transform3D",
    "Quaternion": "Quaternion",
    "Array": "List<Any?>",
    "Dictionary": "Map<String, Any?>",
    "PackedStringArray": "List<String>",
    "PackedByteArray": "ByteArray",
    "PackedInt32Array": "List<Int>",
    "PackedInt64Array": "List<Long>",
    "PackedFloat32Array": "List<Float>",
    "PackedFloat64Array": "List<Double>",
    "PackedVector2Array": "List<Vector2>",
    "PackedVector3Array": "List<Vector3>",
    "PackedColorArray": "List<Color>",
    "PackedVector4Array": "List<Vector4>",
    "Rect2i": "Rect2i",
    "TypedRIDArray": "List<RID>",
    "TypedStringArray": "List<String>",
    "TypedIntArray": "List<Long>",
    "TypedNodePathArray": "List<NodePath>",
    "TypedArrayArray": "List<List<Any?>>",
    "TypedPackedByteArray": "List<ByteArray>",
    "TypedPackedStringArray": "List<List<String>>",
    "TypedPackedVector2Array": "List<List<Vector2>>",
    "TypedPlaneArray": "List<Plane>",
    "TypedRect2Array": "List<Rect2>",
    "TypedTransform3DArray": "List<Transform3D>",
    "TypedVector2Array": "List<Vector2>",
    "TypedVector3Array": "List<Vector3>",
    "TypedNodeArray": "List<Node>",
    "TypedNode2DArray": "List<Node2D>",
    "TypedNode3DArray": "List<Node3D>",
    "TypedMaterialArray": "List<Material>",
    "TypedArea2DArray": "List<Area2D>",
    "TypedArea3DArray": "List<Area3D>",
    "TypedBaseButtonArray": "List<BaseButton>",
    "TypedPhysicsBody3DArray": "List<PhysicsBody3D>",
    "TypedVector2iArray": "List<Vector2i>",
    "TypedVector3iArray": "List<Vector3i>",
    "TypedStringNameArray": "List<String>",
    "TypedDictionaryArray": "List<Map<String, Any?>>",
    "ConstVoidPtr": "MemorySegment",
    "ConstGDExtensionInitializationFunctionPtr": "MemorySegment",
    "Callable": "GodotCallable",
    "Signal": "GodotSignal",
}

DEFAULT_IMPORTS = {
    "ObjectCalls": "net.multigesture.kanama.binding.runtime.ObjectCalls",
    "MemorySegment": "java.lang.foreign.MemorySegment",
    "JvmName": "kotlin.jvm.JvmName",
    "Vector2": "net.multigesture.kanama.types.Vector2",
    "Vector2i": "net.multigesture.kanama.types.Vector2i",
    "Vector3": "net.multigesture.kanama.types.Vector3",
    "Vector3i": "net.multigesture.kanama.types.Vector3i",
    "Vector4": "net.multigesture.kanama.types.Vector4",
    "NodePath": "net.multigesture.kanama.types.NodePath",
    "Plane": "net.multigesture.kanama.types.Plane",
    "Projection": "net.multigesture.kanama.types.Projection",
    "RID": "net.multigesture.kanama.types.RID",
    "Basis": "net.multigesture.kanama.types.Basis",
    "Color": "net.multigesture.kanama.types.Color",
    "Rect2": "net.multigesture.kanama.types.Rect2",
    "Rect2i": "net.multigesture.kanama.types.Rect2i",
    "AABB": "net.multigesture.kanama.types.AABB",
    "Transform2D": "net.multigesture.kanama.types.Transform2D",
    "Transform3D": "net.multigesture.kanama.types.Transform3D",
    "Quaternion": "net.multigesture.kanama.types.Quaternion",
}

RESERVED_WORDS = {
    "as",
    "break",
    "class",
    "continue",
    "do",
    "else",
    "false",
    "for",
    "fun",
    "if",
    "in",
    "interface",
    "internal",
    "is",
    "null",
    "object",
    "package",
    "return",
    "super",
    "this",
    "throw",
    "true",
    "try",
    "typealias",
    "typeof",
    "val",
    "var",
    "when",
    "while",
}
VARARG_RETURN_TYPES = {"void", "Variant", "enum"}

# --- iOS emission target (T3.1) -------------------------------------------------
# The iOS backend reuses the SAME platform-agnostic wrapper output as desktop, but
# its ObjectCalls helper bodies marshal through the single generic C ptrcall
# dispatch (`kanama_ios_godot_ptrcall`). Conservative guardrail: emit ONLY the
# arg/return kinds whose ptrcall width + layout are audited AND validated on device
# (the self-test matrix + ObjectCalls probe). Un-audited shapes are skipped, never
# guessed — see docs/internals/reference/ios-backend-architecture.md §"Rules".
#
# Set by main() when any --ios-* flag is passed; when True, `unsupported_reason`
# additionally rejects any method the iOS helper generator can't marshal, so the
# emitted iOS wrapper only contains methods whose ObjectCalls helper is also emitted.
IOS_AUDIT_ONLY = False

# The set of class names emitted in this iOS run. A generated wrapper references its
# peer wrapper types (Object arg/return types call `Peer.wrap(...)` / use `Peer` as the
# param/return type), so a method is only emittable if EVERY object type it touches is
# also emitted (or is the root Object -> GodotObject, which always exists on iOS).
# Otherwise the generated island won't compile (unresolved peer type / missing
# `.wrap`). None = no constraint (desktop / single-class fixture default is set in main).
IOS_EMIT_CLASSES: set[str] | None = None

# Real Godot classes that are deliberately hand-written on iOS (inside IosGodotApi.kt or a
# bespoke single-class file), so the generator must NOT emit a wrapper for them — a generated
# `<Class>.kt` would duplicate-declare the class and break the compile. `scan_wrapper_classes`
# only sees classes that own a `.kt` file, so the ones nested in IosGodotApi.kt are invisible to
# it; without this registry, `--ios-emit-class SceneTree` would silently produce a colliding file.
# main() logs + skips any requested class listed here, making the collision explicit (task 11,
# generator policy). Retire an entry only when the class moves to a real generated wrapper (as
# Time/InputMap/PhysicsServer3D did in task 10) — then delete it here so generation is allowed.
# Classes the iOS generator refuses to emit because their generated draft cannot compile on
# iOS (task 30 breadth pass). Unlike IOS_HANDWRITTEN_COLLISION_CLASSES these are not hosted
# anywhere on iOS — requesting one logs an `unsupported:` line and skips it, so a bulk regen
# stays fail-loud instead of writing a file that breaks compileKotlinIosArm64. Retire an entry
# by porting the desktop policy surface it depends on.
IOS_UNSUPPORTED_CLASSES = {
    "DirAccess": "desktop hosts DirAccess as a hand-shaped static facade; the generated draft "
                 "references the hand-authored DirAccessHandle alias class iOS does not carry",
    "MethodTweener": "generated setTrans/setEase clash with the hand-written iOS Tweener fluent "
                     "glue (IosGodotApi.kt) the class must subclass",
}

IOS_HANDWRITTEN_COLLISION_CLASSES = {
    "FileAccess": "hand-written static facade + FileAccessHandle glue in FileAccess.kt (static-method "
                  "dispatch subset); the generated draft would clash and still references the desktop "
                  "hand-shaped FileAccessHandle surface",
    "SceneTree": "hand-written Node subclass (createTween F2 fix + coroutine/companion glue) in IosGodotApi.kt",
    "Tween": "hand-written Variant tween_property runtime in IosGodotApi.kt",
    "Tweener": "hand-written Tween chaining glue in IosGodotApi.kt",
    "PropertyTweener": "hand-written Tween chaining glue in IosGodotApi.kt",
    "CallbackTweener": "hand-written Tween chaining glue in IosGodotApi.kt",
    "ResourceLoader": "hand-written typed-loader glue (loadTexture2D/AudioStream/PackedScene) in IosGodotApi.kt",
    "Engine": "hand-written singleton (get_main_loop -> MainLoop, no wrapper class) in Engine.kt",
    "ProjectSettings": "hand-written singleton (getSettingDouble Variant->Double coercion) in ProjectSettings.kt",
    "StaticBody3D": "hand-written thin Node3D subclass in IosGodotApi.kt",
    "AudioStreamPlayer": "hand-written cinterop-glue Node subclass in IosGodotApi.kt",
    "InputEventMouseButton": "hand-written cinterop-glue event wrapper in IosGodotApi.kt",
}

# Logical arg kinds the iOS generic dispatch + Kotlin layout marshal today. Scalars
# widen per the width table (int*->int64/8B, float->double/8B); Vector2/3 components
# are float32; Object is an 8B handle; StringName is constructed C-side from a C
# string. Extended as new types gain a width-sensitive self-test matrix row.
IOS_ARG_KINDS = {
    "bool",
    "int32",
    "int64",
    "uint32",
    "enum",
    "bitfield",
    "float",
    "Object",
    "Vector2",
    "Vector2i",
    "Vector3",
    "Vector3i",
    "Color",
    "Rect2",
    "StringName",
    "String",
    "NodePath",
    "Basis",
    "Transform2D",
    "Transform3D",
    "RID",
    "Quaternion",
    "AABB",
    "PackedFloat32Array",
    "PackedVector2Array",
    "PackedColorArray",
    # Callable args: object+method form only (GodotCallable). The iOS ptrcall dispatch builds the
    # Callable via constructor index 2 (PT_CALLABLE / KanamaIosCallableArgDesc) and destroys it after
    # the call — no Kotlin-side state outlives the call, so nothing leaks regardless of engine
    # retention (callable-args ownership design, Decisions 1-3, 5; record in the internal task repo). Callable
    # *return* and Callable-as-Variant-arg stay unsupported (no emitted iOS method needs them).
    "Callable",
}
# Return shapes the iOS helpers can read back (keyed by CallShape.kotlin_return, the
# stable per-helper return-type token). StringName/String/RID/List/Map returns
# are intentionally absent until their read-back is wired + validated.
IOS_RET_KOTLIN = {"Unit", "Boolean", "Int", "Long", "Double", "Vector2", "Vector2i", "Vector3", "Vector3i", "Color", "Rect2", "Rect2i", "MemorySegment", "String", "NodePath", "Basis", "Transform2D", "Transform3D", "Projection", "RID", "Quaternion", "AABB", "List<Int>", "List<Float>", "List<Vector2>", "List<Color>", "List<String>", "List<NodePath>", "List<Long>", "List<Plane>", "List<Any?>", "Any?"}

# Helpers already hand-written in ios-runtime ObjectCalls.kt (the reference template +
# override set). The generator must NOT re-emit these (they'd clash with the members).
IOS_HANDWRITTEN_HELPERS = {
    "ptrcallNoArgs",
    "ptrcallNoArgsRetBool",
    "ptrcallNoArgsRetInt",
    "ptrcallNoArgsRetLong",
    "ptrcallNoArgsRetDouble",
    "ptrcallNoArgsRetObject",
    "ptrcallNoArgsRetVector2",
    "ptrcallNoArgsRetVector2i",
    "ptrcallNoArgsRetVector3",
    "ptrcallNoArgsRetVector3i",
    "ptrcallNoArgsRetColor",
    "ptrcallNoArgsRetRect2",
    "ptrcallWithBoolArg",
    "ptrcallWithIntArg",
    "ptrcallWithLongArg",
    "ptrcallWithDoubleArg",
    "ptrcallWithVector2Arg",
    "ptrcallWithVector2iArg",
    "ptrcallWithVector3Arg",
    "ptrcallWithVector3iArg",
    "ptrcallWithColorArg",
    "ptrcallWithRect2Arg",
    "ptrcallWithObjectArgs",
    # issue #81 — hand-written so the iOS save path carries the same RefCounted guard as desktop
    # (ObjectCalls.ptrcallWithObjectStringLongArgsRetLong). This is the sole wrapper of its shape
    # (ResourceSaver.save); the guard keeps a freshly created resource alive across save's transient
    # Ref<Resource> instead of letting it free the object out from under its wrapper.
    "ptrcallWithObjectStringLongArgsRetLong",
    "ptrcallWithStringNameAndBoolArgRetBool",
    "ptrcallNoArgsRetString",
    "ptrcallNoArgsRetStringName",
    "ptrcallNoArgsRetNodePath",
    "ptrcallNoArgsRetPackedInt32List",
    "ptrcallNoArgsRetPackedFloat32List",
    "ptrcallNoArgsRetPackedVector2List",
    "ptrcallNoArgsRetPackedColorList",
    "ptrcallNoArgsRetPackedStringList",
    "ptrcallWithPackedFloat32ListArg",
    "ptrcallWithPackedVector2ListColorDoubleAndBoolArgs",
    "ptrcallWithPackedVector2ListPackedColorListDoubleAndBoolArgs",
    "ptrcallWithPackedVector2ListPackedColorListPackedVector2ListAndObjectArgs",
    "ptrcallWithPackedVector2ListColorPackedVector2ListAndObjectArgs",
    "ptrcallNoArgsRetTypedObjectList",
    "ptrcallWithBoolArgRetTypedObjectList",
    "ptrcallWithTwoStringAndTwoBoolArgsRetTypedObjectList",
    "ptrcallNoArgsRetVariantScalar",
    "ptrcallWithStringNameArgRetVariantScalar",
    # task 43: owned decode for ClassDB.instantiate (retain-before-Variant-destroy needs the
    # dedicated C entry kanama_ios_classdb_instantiate_owned; see METHOD_CALL_SHAPE_OVERRIDES)
    "ptrcallWithStringNameArgRetVariantScalarOwned",
    "ptrcallWithVector2ArgRetString",
    "ptrcallWithStringArgRetString",
    "ptrcallWithStringAndStringNameArgRetString",
    "ptrcallWithStringStringNameIntStringNameArgsRetString",
    "ptrcallWithStringNameArgRetStringName",
    "ptrcallWithLongArgRetNodePath",
    "ptrcallWithObjectAndBoolArgRetNodePath",
    "ptrcallNoArgsRetStringNameList",
    "ptrcallNoArgsRetNodePathList",
    "ptrcallNoArgsRetLongList",
    "ptrcallNoArgsRetPlaneList",
    "ptrcallNoArgsRetArray",
    "ptrcallWithNodePathArgRetArray",
    # hand-written in ObjectCalls.kt to host SceneTree.create_timer (hand-written collision class)
    "ptrcallWithDoubleAndThreeBoolArgsRetObject",
}
PARAMETER_NAME_OVERRIDES = {
    ("Time", "get_datetime_dict_from_unix_time", "unix_time_val"): "unixTime",
    ("Time", "get_date_dict_from_unix_time", "unix_time_val"): "unixTime",
    ("Time", "get_time_dict_from_unix_time", "unix_time_val"): "unixTime",
    ("Time", "get_datetime_string_from_unix_time", "unix_time_val"): "unixTime",
    ("Time", "get_date_string_from_unix_time", "unix_time_val"): "unixTime",
    ("Time", "get_time_string_from_unix_time", "unix_time_val"): "unixTime",
    ("Time", "get_datetime_dict_from_datetime_string", "datetime"): "value",
    ("Time", "get_datetime_string_from_datetime_dict", "datetime"): "values",
    ("Time", "get_unix_time_from_datetime_dict", "datetime"): "values",
    ("Time", "get_unix_time_from_datetime_string", "datetime"): "value",
    ("Time", "get_offset_string_from_offset_minutes", "offset_minutes"): "minutes",
}
PROPERTY_NAME_OVERRIDES = {
    ("Curve3D", "closed"): "curveClosed",
    ("OccluderPolygon2D", "closed"): "polygonClosed",
}
# (class, godot_property) pairs whose iOS property emission is suppressed because a
# bespoke writable SUGAR override owns it. iOS-only. Currently empty: Label.text
# graduated to a fully generated `var text` in Phase 2.4 once PT_STRING args made
# set_text real (it was suppressed in 2.3a when only the getter was wired). Kept as a
# mechanism for the next property whose setter outpaces its iOS arg support.
IOS_PROPERTY_SUPPRESS: set[tuple[str, str]] = set()
METHOD_NAME_OVERRIDES = {
    ("Curve3D", "set_closed"): "setCurveClosed",
    ("EditorDock", "close"): "closeDock",
    ("HTTPClient", "close"): "closeConnection",
    ("MultiplayerPeer", "close"): "closeConnection",
    ("OccluderPolygon2D", "is_closed"): "isPolygonClosed",
    ("OccluderPolygon2D", "set_closed"): "setPolygonClosed",
    ("PacketPeerUDP", "close"): "closeConnection",
    ("PacketPeerUDP", "wait"): "waitBlocking",
    ("Semaphore", "wait"): "waitBlocking",
    ("WebSocketPeer", "close"): "closeConnection",
    ("WebRTCDataChannel", "close"): "closeConnection",
    ("WebRTCPeerConnection", "close"): "closeConnection",
    ("ZIPPacker", "close"): "closeArchive",
    ("ZIPReader", "close"): "closeArchive",
}
DEFAULT_VALUE_OVERRIDES = {
    ("Time", "get_datetime_string_from_datetime_dict", "use_space"): "false",
}

# Per-(class, method) CallShape overrides — for the rare method whose default helper has the
# wrong ownership semantics. The `.function` names the helper the generated wrapper calls; for
# a ptrcall method it replaces the shape-derived helper, and for a vararg method
# `render_vararg_method` uses it in place of the default `callWithVariantArgs`.
#
# Both entries fix the same hazard: a call that mints a fresh object whose SOLE reference lives
# in the return Variant, where the borrowed decode extracts the handle and then destroys the
# Variant — freeing a RefCounted before the caller sees it (use-after-free, GitHub PR #42). The
# owned helpers retain RefCounted results before the Variant destroy and return the owning
# RefCounted wrapper (task-31 return-ownership):
#   - ClassDB.instantiate (ptrcall) -> ptrcallWithStringNameArgRetVariantScalarOwned
#   - ClassDB.class_call_static (vararg static factory, e.g. RegEx.create_from_string /
#     Image.create) -> callWithVariantArgsOwned, which delegates to
#     callWithVariantArgs(owned = true). On iOS that retain happens inside the shared
#     kanama_ios_godot_object_call shim via its out_is_refcounted out-param.
METHOD_CALL_SHAPE_OVERRIDES = {
    ("ClassDB", "instantiate"): CallShape("ptrcallWithStringNameArgRetVariantScalarOwned", "Any?", "null"),
    ("ClassDB", "class_call_static"): CallShape("callWithVariantArgsOwned", "Any?", "null"),
}

# Final Kotlin default expressions injected by (class, method, arg), bypassing
# kotlin_default_expression. Use for composite defaults the generic renderer can't express but that
# a wrapper needs — kept surgical (per exact arg) so no other method silently gains a default.
# Node3D.look_at up defaults to Godot's Vector3(0, 1, 0); demos call the 1-arg lookAt(target) form
# and rely on the named Vector3.UP default, so reproduce it here rather than dropping it on regen.
KOTLIN_DEFAULT_EXPRESSION_OVERRIDES = {
    ("Node3D", "look_at", "up"): "Vector3.UP",
    ("Node3D", "look_at_from_position", "up"): "Vector3.UP",
}
# (class, method, argument) object parameters that accept Kotlin `null` even though they are not
# Resource/RefCounted-derived (which are nullable by legacy policy) and are not marked
# `meta: "required"`. Each entry must cite the pinned Godot 4.7-stable source where null is a valid,
# handled value. Task 52a (issue #60).
#   Node.set_owner(null): Godot cleans up the previous owner then returns when p_owner is null, and
#   the engine itself calls child->set_owner(nullptr) while replacing nodes.
#   scene/main/node.cpp#L2271-L2280 and #L3243-L3249 (4.7-stable).
NULLABLE_OBJECT_PARAM_OVERRIDES = {
    ("Node", "set_owner", "owner"),
}
CUSTOM_MEMBER_SECTIONS = {
    "AnimationMixer": """
    fun setParameter(path: String, value: Any?) {
        setIndexed(path, value)
    }

    fun getParameter(path: String): Any? =
        getIndexed(path)

    fun getStateMachinePlayback(path: String): AnimationNodeStateMachinePlayback {
        val value = getParameter(path)
        val playback = when (value) {
            is Resource -> AnimationNodeStateMachinePlayback.fromHandle(value.handle)
            is GodotObject -> AnimationNodeStateMachinePlayback.fromHandle(value.handle)
            else -> null
        }
        return playback ?: error("AnimationMixer parameter '$path' is not an AnimationNodeStateMachinePlayback")
    }
""".strip("\n"),
    "ProjectSettings": """
    @JvmStatic
    fun getSettingString(name: String, defaultValue: String = ""): String =
        getSetting(name, defaultValue) as? String ?: defaultValue

    @JvmStatic
    fun getSettingBool(name: String, defaultValue: Boolean = false): Boolean =
        getSetting(name, defaultValue) as? Boolean ?: defaultValue

    @JvmStatic
    fun getSettingLong(name: String, defaultValue: Long = 0): Long =
        (getSetting(name, defaultValue) as? Number)?.toLong() ?: defaultValue

    @JvmStatic
    fun getSettingDouble(name: String, defaultValue: Double = 0.0): Double =
        (getSetting(name, defaultValue) as? Number)?.toDouble() ?: defaultValue

    @JvmStatic
    fun getSettingStringList(name: String, defaultValue: List<String> = emptyList()): List<String> =
        stringListOrDefault(getSetting(name, defaultValue), defaultValue)

    @JvmStatic
    fun getSettingDictionary(name: String, defaultValue: Map<String, Any?> = emptyMap()): Map<String, Any?> =
        dictionaryOrDefault(getSetting(name, defaultValue), defaultValue)

    @JvmStatic
    fun getSettingWithOverrideString(name: String, defaultValue: String = ""): String =
        getSettingWithOverride(name) as? String ?: defaultValue

    @JvmStatic
    fun getSettingWithOverrideBool(name: String, defaultValue: Boolean = false): Boolean =
        getSettingWithOverride(name) as? Boolean ?: defaultValue

    @JvmStatic
    fun getSettingWithOverrideLong(name: String, defaultValue: Long = 0): Long =
        (getSettingWithOverride(name) as? Number)?.toLong() ?: defaultValue

    @JvmStatic
    fun getSettingWithOverrideDouble(name: String, defaultValue: Double = 0.0): Double =
        (getSettingWithOverride(name) as? Number)?.toDouble() ?: defaultValue

    @JvmStatic
    fun getSettingWithOverrideAndCustomFeaturesString(
        name: String,
        customFeatures: List<String>,
        defaultValue: String = "",
    ): String =
        getSettingWithOverrideAndCustomFeatures(name, customFeatures) as? String ?: defaultValue

    @JvmStatic
    fun getSettingWithOverrideAndCustomFeaturesBool(
        name: String,
        customFeatures: List<String>,
        defaultValue: Boolean = false,
    ): Boolean =
        getSettingWithOverrideAndCustomFeatures(name, customFeatures) as? Boolean ?: defaultValue

    @JvmStatic
    fun getSettingWithOverrideAndCustomFeaturesLong(
        name: String,
        customFeatures: List<String>,
        defaultValue: Long = 0,
    ): Long =
        (getSettingWithOverrideAndCustomFeatures(name, customFeatures) as? Number)?.toLong() ?: defaultValue

    @JvmStatic
    fun getSettingWithOverrideAndCustomFeaturesDouble(
        name: String,
        customFeatures: List<String>,
        defaultValue: Double = 0.0,
    ): Double =
        (getSettingWithOverrideAndCustomFeatures(name, customFeatures) as? Number)?.toDouble() ?: defaultValue

    private fun stringListOrDefault(value: Any?, defaultValue: List<String>): List<String> =
        (value as? List<*>)?.mapNotNull { it as? String } ?: defaultValue

    private fun dictionaryOrDefault(value: Any?, defaultValue: Map<String, Any?>): Map<String, Any?> =
        (value as? Map<*, *>)?.entries?.associate { (key, mapValue) -> key.toString() to mapValue } ?: defaultValue
""".strip("\n"),
}
CUSTOM_COMPANION_MEMBER_SECTIONS = {
    "Sprite2D": """
        @JvmStatic
        fun create(): Sprite2D =
            Sprite2D(ObjectCalls.constructObject("Sprite2D"))
""".strip("\n"),
    "FastNoiseLite": """
        @JvmStatic
        fun create(): FastNoiseLite =
            FastNoiseLite(ObjectCalls.constructObject("FastNoiseLite"))

        @JvmStatic
        fun fromResource(value: Resource): FastNoiseLite? =
            if (value.isClass("FastNoiseLite")) FastNoiseLite(value.handle) else null
""".strip("\n"),
    "OfflineMultiplayerPeer": """
        @JvmStatic
        fun create(): OfflineMultiplayerPeer =
            OfflineMultiplayerPeer(ObjectCalls.constructObject("OfflineMultiplayerPeer"))
""".strip("\n"),
    "ParticleProcessMaterial": """
        @JvmStatic
        fun fromResource(value: Resource): ParticleProcessMaterial? =
            if (value.isClass("ParticleProcessMaterial")) ParticleProcessMaterial(value.handle) else null
""".strip("\n"),
    "PlaneMesh": """
        @JvmStatic
        fun fromResource(value: Resource): PlaneMesh? =
            if (value.isClass("PlaneMesh")) PlaneMesh(value.handle) else null
""".strip("\n"),
    "ProceduralSkyMaterial": """
        @JvmStatic
        fun fromResource(value: Resource): ProceduralSkyMaterial? =
            if (value.isClass("ProceduralSkyMaterial")) ProceduralSkyMaterial(value.handle) else null
""".strip("\n"),
    "SphereMesh": """
        @JvmStatic
        fun fromResource(value: Resource): SphereMesh? =
            if (value.isClass("SphereMesh")) SphereMesh(value.handle) else null
""".strip("\n"),
}

# iOS-only hand-written body members emitted into the generated wrapper as a stable
# custom-section (Phase 4.2). These are Kanama ergonomics that can't come from
# extension_api.json (SceneTree/Tween/IosGodot facade glue, generic node-cast helpers);
# they used to live behind a `// KANAMA-IOS-SUGAR` marker that had to be hand-re-added
# after every regen. Emitting them here makes regeneration lossless. Referenced types
# (SceneTree, Tween, IosGodot, Node, NodePath) are all in the same package, so no extra
# imports are needed. Gated to IOS_AUDIT_ONLY in render_wrapper.
IOS_CUSTOM_MEMBER_SECTIONS = {
    "RefCounted": """
    // ── Kanama iOS RefCounted ownership (generator custom-section; task 31 mirror) ─────
    // A wrapper returned from a RefCounted-typed ptrcall method owns the +1 reference the
    // engine hands through the return slot (meta:"required" included). close() releases it:
    // unreference() + destroy at zero. Wrappers minted from Variant-path returns or
    // fromHandle casts borrow — do not close those (see wrapper-maintenance.md
    // "RefCounted Return Ownership").
    private var wrapperReferenceReleased = false

    @ManualGodotLifetimeApi
    override fun close() {
        if (wrapperReferenceReleased) return
        wrapperReferenceReleased = true
        if (unreference()) {
            ObjectCalls.destroyObject(handle)
        }
    }
""".strip("\n"),
    "Node": """
    // ── Kanama iOS sugar (generator custom-section, not from Godot docs) ───────
    // getViewport() is generated above (Rect2 kind widening made Viewport an emitted
    // return type), so it is intentionally not duplicated here.

    fun getTree(): SceneTree =
        SceneTree(MemorySegment.ofAddress(IosGodot.nodeGetTree(handle.address())))

    fun getNodeOrNull(path: String): Node? =
        IosGodot.nodeGetNodeOrNull(handle.address(), path).takeIf { it != 0L }?.let {
            Node(MemorySegment.ofAddress(it))
        }

    fun <T : Node> getAsOrNull(path: String, ctor: (MemorySegment) -> T): T? =
        getNodeOrNull(path)?.let { ctor(it.handle) }

    fun <T : Node> getAsOrNull(path: NodePath, ctor: (MemorySegment) -> T): T? =
        getAsOrNull(path.path, ctor)

    fun <T : Node> requireAs(path: String, ctor: (MemorySegment) -> T): T =
        getAsOrNull(path, ctor) ?: error("Required node '$path' was not found")

    fun <T : Node> requireAs(path: NodePath, ctor: (MemorySegment) -> T): T =
        requireAs(path.path, ctor)

    fun <T : Node> getNodeAsOrNull(path: String, className: String, ctor: (MemorySegment) -> T): T? =
        getNodeOrNull(path)?.takeIf { it.isClass(className) }?.let { ctor(it.handle) }

    // `open` so the hand-written SceneTree subclass (IosGodotApi.kt) can override createTween() with
    // the correct SceneTree.create_tween bind — the FPS F2 fix. Generated here so a regen preserves
    // the openness instead of silently dropping it (which would re-break the SIGSEGV path).
    open fun createTween(): Tween? =
        IosGodot.nodeCreateTween(handle.address()).takeIf { it != 0L }?.let {
            Tween(MemorySegment.ofAddress(it))
        }

    // Node.propagate_call(method, args, parent_first) via the Variant call path (Array arg boxed
    // through callWithVariantArgs). Matches desktop Node.propagateCall.
    fun propagateCall(method: String, args: List<Any?> = emptyList(), parentFirst: Boolean = false) {
        call("propagate_call", method, args, parentFirst)
    }

    // String overloads for the NodePath-typed accessors (desktop exposes both), so demo code can
    // pass a plain path literal.
    fun hasNode(path: String): Boolean = hasNode(NodePath(path))

    fun getNode(path: String): Node? = getNode(NodePath(path))

    fun hasNodeAndResource(path: String): Boolean = hasNodeAndResource(NodePath(path))

    // Node.callLocalRpc — send the RPC and also run it locally if the send was a no-op (matches
    // desktop; used by the generated <Class>Rpcs.callLocal* helpers).
    fun callLocalRpc(method: String, vararg extraArgs: Any?) {
        if (rpc(method, *extraArgs) != 0L) {
            call(method, *extraArgs)
        }
    }
""".strip("\n"),
    "ConfigFile": """
    // ConfigFile.set_value / get_value are (StringName, StringName, Variant) methods the generator
    // skips on iOS (Variant value); route through the call() Variant path. Matches desktop's
    // hand-written Variant-coercion helpers.
    fun setValue(section: String, key: String, value: Any?) {
        call("set_value", section, key, value)
    }

    fun getValue(section: String, key: String, default: Any? = null): Any? =
        call("get_value", section, key, default)
""".strip("\n"),
    "ShaderMaterial": """
    // ShaderMaterial.set_shader_parameter(param, value) via the Variant call path (iOS has no
    // (StringName, Variant) ptrcall shape; call() boxes the value). Matches desktop.
    fun setShaderParameter(param: String, value: Any?) {
        call("set_shader_parameter", param, value)
    }
""".strip("\n"),
    "PhysicsDirectSpaceState3D": """
    // intersect_ray returns a Godot Dictionary, decoded via the fixed-schema raycast C-shim
    // (kanama_ios_godot_ptrcall_ret_raycast_dict). Empty map = no hit. "collider" is wrapped from the
    // raw handle into a GodotObject so scripts can `hit["collider"] as? GodotObject`.
    fun intersectRay(parameters: PhysicsRayQueryParameters3D?): Map<String, Any?> {
        val query = parameters ?: return emptyMap()
        val raw = ObjectCalls.ptrcallIntersectRay(intersectRayBind, handle, query.handle)
        if (raw.isEmpty()) return emptyMap()
        val result = raw.toMutableMap()
        (raw["collider"] as? MemorySegment)?.let { result["collider"] = GodotObject(it) }
        return result
    }
""".strip("\n"),
    "PhysicsRayQueryParameters3D": """
    // The RID list excluded from collisions (e.g. the caster's own body). Marshalled to a Godot
    // Array[RID] by the C-shim. set_exclude takes an Array[RID] arg the generator otherwise skips.
    fun setExclude(exclude: List<net.multigesture.kanama.types.RID>) {
        ObjectCalls.ptrcallWithRIDListArg(setExcludeBind, handle, exclude)
    }
""".strip("\n"),
    "ShapeCast3D": """
    // Long-index overload (desktop ShapeCast3D exposes both Int and Long), so loops over the now-Long
    // getCollisionCount() (`for (i in 0 until getCollisionCount())`) pass a Long index straight through.
    fun getCollisionPoint(index: Long): Vector3 = getCollisionPoint(index.toInt())
""".strip("\n"),
    "SurfaceTool": """
    // No-arg commit() — the generated commit(existing, flags) doesn't default the nullable `existing`
    // ArrayMesh; this overload matches the desktop/Android commit() default-arg call.
    fun commit(): ArrayMesh? = commit(null)
""".strip("\n"),
    "Viewport": """
    // getCamera3D/getCamera2D camelCase aliases (the generator emits getCamera3d/getCamera2d);
    // match the Android aliases so shared demo code resolves on both backends.
    fun getCamera3D(): Camera3D? = getCamera3d()

    fun getCamera2D(): Camera2D? = getCamera2d()
""".strip("\n"),
    "AnimationMixer": """
    // AnimationTree parameters are exposed as `parameters/...` engine properties, so route through
    // set()/get() (no NodePath set_indexed needed). Matches the desktop AnimationMixer helpers.
    fun setParameter(path: String, value: Any?) {
        set(path, value)
    }

    fun getParameter(path: String): Any? =
        get(path)

    fun getStateMachinePlayback(path: String): AnimationNodeStateMachinePlayback {
        val value = getParameter(path)
        val playback = when (value) {
            is AnimationNodeStateMachinePlayback -> value
            // iOS decodes a Variant Object return as a raw handle (MemorySegment), not a wrapper.
            is MemorySegment -> if (value.address() != 0L) AnimationNodeStateMachinePlayback(value) else null
            is Resource -> AnimationNodeStateMachinePlayback.fromHandle(value.handle)
            is GodotObject -> AnimationNodeStateMachinePlayback.fromHandle(value.handle)
            else -> null
        }
        return playback ?: error("AnimationMixer parameter '$path' is not an AnimationNodeStateMachinePlayback")
    }
""".strip("\n"),
}

# iOS-only companion-object custom sections (mirrors CUSTOM_COMPANION_MEMBER_SECTIONS for the
# IOS_AUDIT_ONLY path). Members are emitted inside the generated companion object (8-space indent).
IOS_CUSTOM_COMPANION_MEMBER_SECTIONS = {
    "Sprite2D": """
        // Instantiate a Sprite2D node without exposing platform FFI construction to game code.
        fun create(): Sprite2D =
            Sprite2D(MemorySegment.ofAddress(IosGodot.constructObject("Sprite2D")))
""".strip("\n"),
    "RefCounted": """
        // Releases the +1 return-slot reference carried by `handle` without minting a
        // wrapper — the generated self-return-collapse pattern calls this before
        // returning `this` (task 31 mirror; matches desktop RefCounted.releaseHandle).
        internal fun releaseHandle(handle: MemorySegment) {
            if (handle.address() != 0L && ObjectCalls.ptrcallNoArgsRetBool(unreferenceBind, handle)) {
                ObjectCalls.destroyObject(handle)
            }
        }
""".strip("\n"),
    "InputEventKey": """
        // Godot Key enum constants (subset used by gameplay code; values match @GlobalScope.Key).
        const val KEY_ESCAPE = 4194305L
        const val KEY_TAB = 4194306L
        const val KEY_ENTER = 4194309L
        const val KEY_F10 = 4194341L
        const val KEY_F11 = 4194342L
        const val KEY_SPACE = 32L
        const val KEY_A = 65L
        const val KEY_D = 68L
        const val KEY_E = 69L
        const val KEY_F = 70L
        const val KEY_Q = 81L
        const val KEY_R = 82L
        const val KEY_S = 83L
        const val KEY_W = 87L

        // Instantiate a blank InputEventKey (for synthesizing input events / InputMap actions).
        fun create(): InputEventKey =
            InputEventKey(MemorySegment.ofAddress(IosGodot.constructObject("InputEventKey")))

        // Cast a generic event to InputEventKey (null if not), mirroring the desktop helper.
        fun from(value: GodotObject): InputEventKey? =
            if (value.isClass("InputEventKey")) InputEventKey(value.handle) else null
""".strip("\n"),
    "Camera3D": """
        // Instantiate a Camera3D (e.g. the debug free-camera).
        fun create(): Camera3D =
            Camera3D(MemorySegment.ofAddress(IosGodot.constructObject("Camera3D")))
""".strip("\n"),
    "SurfaceTool": """
        // Instantiate a SurfaceTool (RefCounted; used to build meshes procedurally).
        fun create(): SurfaceTool =
            SurfaceTool(MemorySegment.ofAddress(IosGodot.constructObject("SurfaceTool")))
""".strip("\n"),
    "MeshDataTool": """
        // Instantiate a MeshDataTool (RefCounted; used to read mesh vertex/face data).
        fun create(): MeshDataTool =
            MeshDataTool(MemorySegment.ofAddress(IosGodot.constructObject("MeshDataTool")))
""".strip("\n"),
    "ArrayMesh": """
        // Downcast a Resource/Mesh to ArrayMesh (null if not), mirroring the desktop helper.
        fun fromResource(value: Resource): ArrayMesh? =
            if (value.isClass("ArrayMesh")) ArrayMesh(value.handle) else null
""".strip("\n"),
    "PhysicsBody3D": """
        // PhysicsServer3D.BodyAxis flags, exposed on PhysicsBody3D to match the desktop/Android API
        // (used by set_axis_lock). Values are @GlobalScope PhysicsServer3D.BODY_AXIS_* bit flags.
        const val BODY_AXIS_LINEAR_X = 1L
        const val BODY_AXIS_LINEAR_Y = 2L
        const val BODY_AXIS_LINEAR_Z = 4L
        const val BODY_AXIS_ANGULAR_X = 8L
        const val BODY_AXIS_ANGULAR_Y = 16L
        const val BODY_AXIS_ANGULAR_Z = 32L
""".strip("\n"),
    "InputEventMouseMotion": """
        // Cast a generic event to InputEventMouseMotion (null if not), mirroring the desktop helper.
        fun from(value: GodotObject): InputEventMouseMotion? =
            if (value.isClass("InputEventMouseMotion")) InputEventMouseMotion(value.handle) else null
""".strip("\n"),
    "BaseMaterial3D": """
        // Downcast a Material to BaseMaterial3D (null if not), mirroring the desktop helper.
        fun fromMaterial(value: Material): BaseMaterial3D? =
            if (value.isClass("BaseMaterial3D")) BaseMaterial3D(value.handle) else null
""".strip("\n"),
    "FastNoiseLite": """
        // Instantiate a FastNoiseLite (RefCounted noise generator).
        fun create(): FastNoiseLite =
            FastNoiseLite(MemorySegment.ofAddress(IosGodot.constructObject("FastNoiseLite")))
""".strip("\n"),
    "LightmapGI": """
        // Instantiate a LightmapGI node.
        fun create(): LightmapGI =
            LightmapGI(MemorySegment.ofAddress(IosGodot.constructObject("LightmapGI")))
""".strip("\n"),
    "Material": """
        // Downcast a Resource to Material (null if not), mirroring the desktop helper.
        fun fromResource(value: Resource?): Material? =
            value?.takeIf { it.isClass("Material") }?.let { Material(it.handle) }
""".strip("\n"),
    "SceneMultiplayer": """
        // Downcast a MultiplayerAPI to SceneMultiplayer (null if not), mirroring the desktop helper.
        fun fromApi(api: MultiplayerAPI?): SceneMultiplayer? =
            api?.takeIf { it.isClass("SceneMultiplayer") }?.let { SceneMultiplayer(it.handle) }
""".strip("\n"),
    "ConfigFile": """
        // Instantiate a ConfigFile (RefCounted key/value store).
        fun create(): ConfigFile =
            ConfigFile(MemorySegment.ofAddress(IosGodot.constructObject("ConfigFile")))
""".strip("\n"),
    "OfflineMultiplayerPeer": """
        // Instantiate an OfflineMultiplayerPeer (single-player multiplayer stub).
        fun create(): OfflineMultiplayerPeer =
            OfflineMultiplayerPeer(MemorySegment.ofAddress(IosGodot.constructObject("OfflineMultiplayerPeer")))
""".strip("\n"),
    "ENetMultiplayerPeer": """
        // Instantiate an ENetMultiplayerPeer.
        fun create(): ENetMultiplayerPeer =
            ENetMultiplayerPeer(MemorySegment.ofAddress(IosGodot.constructObject("ENetMultiplayerPeer")))
""".strip("\n"),
    "ButtonGroup": """
        // Instantiate a ButtonGroup (RefCounted radio-button grouping).
        fun create(): ButtonGroup =
            ButtonGroup(MemorySegment.ofAddress(IosGodot.constructObject("ButtonGroup")))
""".strip("\n"),
    "ShaderMaterial": """
        // Downcast a Resource to ShaderMaterial (null if not), mirroring the desktop helper.
        fun fromResource(value: Resource?): ShaderMaterial? =
            value?.takeIf { it.isClass("ShaderMaterial") }?.let { ShaderMaterial(it.handle) }
""".strip("\n"),
    "PhysicsRayQueryParameters3D": """
        // Build a ray query: instantiate and set the scalar/Vector3 properties + the exclude RID-list
        // (marshalled through the Array[RID] C-shim so intersect_ray skips the caster's own collider).
        fun create(
            from: Vector3,
            to: Vector3,
            collisionMask: Long = 4294967295L,
            exclude: List<net.multigesture.kanama.types.RID> = emptyList(),
        ): PhysicsRayQueryParameters3D {
            val query = PhysicsRayQueryParameters3D(MemorySegment.ofAddress(IosGodot.constructObject("PhysicsRayQueryParameters3D")))
            query.from = from
            query.to = to
            query.collisionMask = collisionMask
            if (exclude.isNotEmpty()) query.setExclude(exclude)
            return query
        }

        private const val SET_EXCLUDE_HASH = 381264803L
        private val setExcludeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "set_exclude", SET_EXCLUDE_HASH)
        }
""".strip("\n"),
    "PhysicsDirectSpaceState3D": """
        private const val INTERSECT_RAY_HASH = 3957970750L
        private val intersectRayBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "intersect_ray", INTERSECT_RAY_HASH)
        }
""".strip("\n"),
}


def wrapper_has_wrap(api_dir: Path, class_name: str) -> bool:
    # GodotObject is the hand-written universal base — it always exists on every platform with a
    # `wrap(handle): GodotObject?` helper, but it does not live in a GodotObject.kt file on iOS
    # (it is declared inside IosGodotApi.kt). Treat it as always-present so bare-`Object` returns
    # (get_collider, shape_owner_get_owner, ...) stay emittable on iOS instead of being dropped on
    # regen — matching desktop/Android, where GodotObject.kt makes the file check pass.
    if class_name == "GodotObject":
        return True
    path = api_dir / f"{class_name}.kt"
    if not path.exists():
        return False
    content = path.read_text(encoding="utf-8")
    return "fun wrap(handle: MemorySegment)" in content


def api_object_wrapper_type(type_name: str, wrapper_classes: set[str]) -> str | None:
    if type_name == "Object":
        return "GodotObject"
    special_wrapper = SPECIAL_OBJECT_WRAPPER_TYPES.get(type_name)
    if special_wrapper in wrapper_classes:
        return special_wrapper
    if type_name in wrapper_classes:
        return type_name
    return None


def typed_object_array_element(logical_type: str) -> str | None:
    prefix = "TypedObjectArray::"
    return logical_type.removeprefix(prefix) if logical_type.startswith(prefix) else None


# typedarray::Node-family element types get a dedicated logical kind (e.g. "TypedNodeArray")
# instead of the generic "TypedObjectArray::Node" form, so typed_object_array_element misses them.
# This maps each named kind back to its element wrapper for the iOS typed-object-array plumbing.
NAMED_TYPED_OBJECT_ARRAY_KINDS = {
    "TypedNodeArray": "Node",
    "TypedNode2DArray": "Node2D",
    "TypedNode3DArray": "Node3D",
    "TypedMaterialArray": "Material",
    "TypedArea2DArray": "Area2D",
    "TypedArea3DArray": "Area3D",
    "TypedBaseButtonArray": "BaseButton",
    "TypedPhysicsBody3DArray": "PhysicsBody3D",
}


def typed_object_array_element_any(logical_type: str) -> str | None:
    """Element wrapper for ANY typed-object-array return kind — both the dedicated named kinds
    (TypedNodeArray, ...) and the generic TypedObjectArray::X form."""
    named = NAMED_TYPED_OBJECT_ARRAY_KINDS.get(logical_type)
    if named is not None:
        return named
    return typed_object_array_element(logical_type)


DIRECT_TYPED_OBJECT_LIST_HELPERS = {
    "ptrcallNoArgsRetTypedNodeList",
    "ptrcallNoArgsRetTypedNode2DList",
    "ptrcallNoArgsRetTypedNode3DList",
    "ptrcallNoArgsRetTypedMaterialList",
    "ptrcallNoArgsRetTypedArea2DList",
    "ptrcallNoArgsRetTypedArea3DList",
    "ptrcallNoArgsRetTypedBaseButtonList",
    "ptrcallNoArgsRetTypedPhysicsBody3DList",
    "ptrcallWithBoolArgRetTypedNodeList",
    "ptrcallWithStringNameArgRetTypedNodeList",
    "ptrcallWithTwoStringAndTwoBoolArgsRetTypedNodeList",
}


NO_ARG_TYPED_OBJECT_LIST_HELPERS = {
    "Node": "ptrcallNoArgsRetTypedNodeList",
    "Node2D": "ptrcallNoArgsRetTypedNode2DList",
    "Node3D": "ptrcallNoArgsRetTypedNode3DList",
    "Material": "ptrcallNoArgsRetTypedMaterialList",
    "Area2D": "ptrcallNoArgsRetTypedArea2DList",
    "Area3D": "ptrcallNoArgsRetTypedArea3DList",
    "BaseButton": "ptrcallNoArgsRetTypedBaseButtonList",
    "PhysicsBody3D": "ptrcallNoArgsRetTypedPhysicsBody3DList",
}


# iOS marshals typed-object-array returns through GENERIC helpers that take a
# `fromHandle: (MemorySegment) -> T?` factory supplied by the api-layer caller (e.g.
# Node::fromHandle). The DIRECT desktop/Android helpers (ptrcall*RetTypedNodeList etc.) return
# List<Node> by referencing api.Node from binding.runtime — a dependency inversion the iOS island
# can't take. So under IOS_AUDIT_ONLY, candidate_for remaps each direct helper to its arg-shape's
# generic equivalent (render_method then appends `{wrapper}::fromHandle` automatically, since the
# generic name is not in DIRECT_TYPED_OBJECT_LIST_HELPERS).
IOS_DIRECT_TO_GENERIC_TYPED_OBJECT_LIST = {
    "ptrcallNoArgsRetTypedNodeList": "ptrcallNoArgsRetTypedObjectList",
    "ptrcallNoArgsRetTypedNode2DList": "ptrcallNoArgsRetTypedObjectList",
    "ptrcallNoArgsRetTypedNode3DList": "ptrcallNoArgsRetTypedObjectList",
    "ptrcallNoArgsRetTypedMaterialList": "ptrcallNoArgsRetTypedObjectList",
    "ptrcallNoArgsRetTypedArea2DList": "ptrcallNoArgsRetTypedObjectList",
    "ptrcallNoArgsRetTypedArea3DList": "ptrcallNoArgsRetTypedObjectList",
    "ptrcallNoArgsRetTypedBaseButtonList": "ptrcallNoArgsRetTypedObjectList",
    "ptrcallNoArgsRetTypedPhysicsBody3DList": "ptrcallNoArgsRetTypedObjectList",
    "ptrcallWithBoolArgRetTypedNodeList": "ptrcallWithBoolArgRetTypedObjectList",
    "ptrcallWithStringNameArgRetTypedNodeList": "ptrcallWithStringNameArgRetTypedObjectList",
    "ptrcallWithTwoStringAndTwoBoolArgsRetTypedNodeList": "ptrcallWithTwoStringAndTwoBoolArgsRetTypedObjectList",
}

# The generic typed-object-array-return helpers actually hand-written + audited in ios-runtime
# ObjectCalls.kt. The iOS gate admits a typed-object-array return only when its (remapped) shape is
# one of these — mirrors the per-helper Packed*Array gates. Grow this as each arg-shape's helper
# lands (2.7d widens to the no-arg and String-arg shapes).
IOS_WIRED_TYPED_OBJECT_LIST_HELPERS = {
    "ptrcallNoArgsRetTypedObjectList",
    "ptrcallWithBoolArgRetTypedObjectList",
    "ptrcallWithTwoStringAndTwoBoolArgsRetTypedObjectList",
}


def is_resource_like(type_name: str, api_classes: dict[str, ApiClass]) -> bool:
    return type_name in {"Resource", "RefCounted"} or "Resource" in ancestors(type_name, api_classes) or "RefCounted" in ancestors(type_name, api_classes)


def arg_name(name: str, index: int) -> str:
    candidate = camel_name(name) or f"arg{index + 1}"
    return f"{candidate}Value" if candidate in RESERVED_WORDS else candidate


def arg_name_for_method(class_name: str, method_name: str, name: str, index: int) -> str:
    return PARAMETER_NAME_OVERRIDES.get((class_name, method_name, name), arg_name(name, index))


def method_function_name(class_name: str, method_name: str) -> str:
    return METHOD_NAME_OVERRIDES.get((class_name, method_name), camel_name(method_name).removeprefix("_"))


def upper_first(name: str) -> str:
    return name[:1].upper() + name[1:] if name else name


def kotlin_default_expression(default_value: str | None, logical_kind: str) -> str | None:
    if default_value is None:
        return None
    if logical_kind == "bool" and default_value in {"true", "false"}:
        return default_value
    if logical_kind in {"int32"}:
        try:
            return str(int(default_value, 0))
        except ValueError:
            return None
    if logical_kind in {"int64", "uint32", "enum", "bitfield"}:
        try:
            return f"{int(default_value, 0)}L"
        except ValueError:
            return None
    if logical_kind == "float":
        try:
            value = float(default_value)
        except ValueError:
            return None
        rendered = repr(value)
        # repr() of e.g. 1e-05 has no "." but is already a valid Kotlin Double literal;
        # only bare integers (repr of 2.0 -> "2.0" always has ".", but be safe) need ".0".
        if "." in rendered or "e" in rendered or "E" in rendered:
            return rendered
        return f"{rendered}.0"
    if logical_kind == "String" and len(default_value) >= 2 and default_value[0] == default_value[-1] == '"':
        return default_value
    if logical_kind == "StringName":
        # StringName defaults appear as a string literal ("...") or a StringName literal (&"...").
        # Both render to the same Kotlin String default (StringName params are typed `String`).
        text = default_value[1:] if default_value.startswith("&") else default_value
        if len(text) >= 2 and text[0] == text[-1] == '"':
            return text
        return None
    if logical_kind == "Variant" and default_value == "null":
        return "null"
    if logical_kind in {"Array", "PackedStringArray"} and default_value == "[]":
        return "emptyList()"
    if logical_kind == "Dictionary" and default_value == "{}":
        return "emptyMap()"
    if logical_kind == "Vector2" and default_value in {"Vector2(0, 0)", "Vector2(0.0, 0.0)"}:
        return "Vector2(0f, 0f)"
    return None


def _candidate_for_impl(method: ApiMethod, object_types: set[str]) -> CallShape | None:
    logical_args = method.logical_arg_kinds(object_types)
    logical_return = method.logical_return_kind(object_types)
    return_array_element = typed_object_array_element(logical_return)
    if return_array_element:
        if not logical_args:
            direct_helper = NO_ARG_TYPED_OBJECT_LIST_HELPERS.get(return_array_element)
            if direct_helper:
                return CallShape(direct_helper, "List")
            return CallShape("ptrcallNoArgsRetTypedObjectList", "List")
        if logical_args == ("StringName",):
            if return_array_element == "Node":
                return CallShape("ptrcallWithStringNameArgRetTypedNodeList", "List")
            return CallShape("ptrcallWithStringNameArgRetTypedObjectList", "List")
        if logical_args == ("bool",):
            if return_array_element == "Node":
                return CallShape("ptrcallWithBoolArgRetTypedNodeList", "List")
            return CallShape("ptrcallWithBoolArgRetTypedObjectList", "List")
        if logical_args == ("String", "bool"):
            return CallShape("ptrcallWithStringAndBoolArgRetTypedObjectList", "List")
        if logical_args == ("String", "int32", "int32"):
            return CallShape("ptrcallWithStringTwoIntArgsRetTypedObjectList", "List")
        if logical_args == ("int32", "int32", "int32", "bool", "bool"):
            return CallShape("ptrcallWithThreeIntTwoBoolArgsRetTypedObjectList", "List")
        if logical_args == ("int32", "int32", "int32", "bool", "float", "bool"):
            return CallShape("ptrcallWithThreeIntBoolDoubleBoolArgsRetTypedObjectList", "List")
        if logical_args == ("RID",):
            return CallShape("ptrcallWithRIDArgRetTypedObjectList", "List")
        if logical_args == ("RID", "TypedRIDArray", "Vector2i"):
            return CallShape("ptrcallWithRIDRIDListVector2iArgsRetTypedObjectList", "List")
        if len(logical_args) == 2 and typed_object_array_element(logical_args[0]) and logical_args[1] == "int32":
            return CallShape("ptrcallWithObjectListIntArgsRetTypedObjectList", "List")
    if logical_return == "void" and logical_args and typed_object_array_element(logical_args[0]):
        if len(logical_args) == 1:
            return CallShape("ptrcallWithObjectListArg", "Unit")
    if logical_return == "void" and len(logical_args) == 2 and typed_object_array_element(logical_args[1]):
        if logical_args[0] == "String":
            return CallShape("ptrcallWithStringAndObjectListArgs", "Unit")
        if logical_args[0] == "RID":
            return CallShape("ptrcallWithRIDAndObjectListArgs", "Unit")
    if len(logical_args) == 1 and typed_object_array_element(logical_args[0]) and logical_return == "enum":
        return CallShape("ptrcallWithObjectListArgRetLong", "Long", "0L")
    if len(logical_args) == 1 and typed_object_array_element(logical_args[0]) and logical_return == "int64":
        return CallShape("ptrcallWithObjectListArgRetLong", "Long", "0L")
    # enum and bitfield share the int64 ptrcall ABI (wrapper_model value_policy), so the
    # Long-arg helper covers both (bitfield: RenderingDevice.blas_create flags).
    if len(logical_args) == 2 and typed_object_array_element(logical_args[0]) and logical_args[1] in ("enum", "bitfield") and logical_return == "RID":
        return CallShape("ptrcallWithObjectListLongArgsRetRID", "RID", "RID.EMPTY")
    if len(logical_args) == 2 and typed_object_array_element(logical_args[0]) and logical_args[1] == "uint32" and logical_return == "int64":
        return CallShape("ptrcallWithObjectListUInt32ArgsRetLong", "Long", "0L")
    if (
        len(logical_args) == 3
        and typed_object_array_element(logical_args[0])
        and typed_object_array_element(logical_args[1])
        and logical_args[2] == "uint32"
        and logical_return == "int64"
    ):
        return CallShape("ptrcallWithTwoObjectListUInt32ArgsRetLong", "Long", "0L")
    if (
        len(logical_args) == 3
        and logical_args[0] == "TypedRIDArray"
        and typed_object_array_element(logical_args[1])
        and logical_args[2] == "uint32"
        and logical_return == "RID"
    ):
        return CallShape("ptrcallWithRIDListObjectListUInt32ArgsRetRID", "RID", "RID.EMPTY")
    if logical_args == ("TypedRIDArray", "int64", "uint32") and logical_return == "RID":
        return CallShape("ptrcallWithRIDListLongUInt32ArgsRetRID", "RID", "RID.EMPTY")
    if (
        len(logical_args) == 4
        and logical_args[0] == "TypedRIDArray"
        and typed_object_array_element(logical_args[1])
        and logical_args[2:] == ("int64", "uint32")
        and logical_return == "RID"
    ):
        return CallShape("ptrcallWithRIDListObjectListLongUInt32ArgsRetRID", "RID", "RID.EMPTY")
    if logical_args == ("uint32", "int64", "TypedRIDArray", "PackedInt64Array") and logical_return == "RID":
        return CallShape("ptrcallWithUInt32LongRIDListPackedInt64ListArgsRetRID", "RID", "RID.EMPTY")
    if (
        len(logical_args) == 3
        and typed_object_array_element(logical_args[0])
        and logical_args[1:] == ("RID", "uint32")
        and logical_return == "RID"
    ):
        return CallShape("ptrcallWithObjectListRIDUInt32ArgsRetRID", "RID", "RID.EMPTY")
    if (
        len(logical_args) == 3
        and logical_args[0] == "RID"
        and logical_args[1] == "uint32"
        and typed_object_array_element(logical_args[2])
        and logical_return == "RID"
    ):
        return CallShape("ptrcallWithRIDUInt32ObjectListArgsRetRID", "RID", "RID.EMPTY")
    if (
        len(logical_args) == 2
        and logical_args[0] == "RID"
        and typed_object_array_element(logical_args[1])
        and logical_return == "RID"
    ):
        return CallShape("ptrcallWithRIDObjectListArgsRetRID", "RID", "RID.EMPTY")
    if (
        len(logical_args) == 2
        and logical_args[0] == "RID"
        and typed_object_array_element(logical_args[1])
        and logical_return == "enum"
    ):
        return CallShape("ptrcallWithRIDAndObjectListArgsRetLong", "Long", "0L")
    if (
        len(logical_args) == 4
        and typed_object_array_element(logical_args[0])
        and typed_object_array_element(logical_args[1])
        and typed_object_array_element(logical_args[2])
        and logical_args[3] == "uint32"
        and logical_return == "RID"
    ):
        return CallShape("ptrcallWithThreeObjectListUInt32ArgsRetRID", "RID", "RID.EMPTY")
    if (
        len(logical_args) == 4
        and logical_args[0] == "RID"
        and typed_object_array_element(logical_args[1])
        and logical_args[2:] == ("Object", "Object")
        and logical_return == "void"
    ):
        return CallShape("ptrcallWithRIDObjectListTwoObjectArgs", "Unit")
    if (
        len(logical_args) == 6
        and logical_args[0] == "Rect2i"
        and typed_object_array_element(logical_args[1])
        and typed_object_array_element(logical_args[2])
        and logical_args[3:] == ("Color", "int32", "Object")
        and logical_return == "void"
    ):
        return CallShape("ptrcallWithRect2iTwoObjectListColorIntObjectArgs", "Unit")
    if (
        len(logical_args) == 3
        and typed_object_array_element(logical_args[0])
        and logical_args[1] == "TypedTransform3DArray"
        and logical_args[2] == "bool"
        and logical_return == "Object"
    ):
        return CallShape("ptrcallWithObjectListTransform3DListBoolArgsRetObject", "MemorySegment", "MemorySegment.NULL")
    if (
        len(logical_args) == 3
        and logical_args[0] == "RID"
        and typed_object_array_element(logical_args[1])
        and logical_args[2] == "Object"
        and logical_return == "bool"
    ):
        return CallShape("ptrcallWithRIDObjectListObjectArgsRetBool", "Boolean", "false")
    if (
        len(logical_args) == 11
        and logical_args[:4] == ("RID", "int64", "int64", "enum")
        and logical_args[4:8] == ("Object", "Object", "Object", "Object")
        and logical_args[8] == "bitfield"
        and logical_args[9] == "uint32"
        and typed_object_array_element(logical_args[10])
        and logical_return == "RID"
    ):
        return CallShape("ptrcallWithRIDThreeLongFourObjectLongUInt32ObjectListArgsRetRID", "RID", "RID.EMPTY")
    if logical_args == ("enum", "int32", "int32", "int32", "bool", "TypedObjectArray::Image"):
        if logical_return == "enum":
            return CallShape("ptrcallWithLongThreeIntBoolObjectListArgsRetLong", "Long", "0L")
        if logical_return == "RID":
            return CallShape("ptrcallWithLongThreeIntBoolObjectListArgsRetRID", "RID", "RID.EMPTY")
    if logical_return == "Object" and logical_args == ("Variant",) and method.return_type in {"Node", "PropertyTweener"}:
        return CallShape("ptrcallWithVariantArgRetObject", "MemorySegment", "MemorySegment.NULL")
    if (
        logical_return == "Object"
        and logical_args == ("Object", "NodePath", "Variant", "float")
        and method.return_type == "PropertyTweener"
    ):
        return CallShape("ptrcallWithObjectNodePathVariantDoubleArgsRetObject", "MemorySegment", "MemorySegment.NULL")
    if (
        logical_return == "Dictionary"
        and logical_args == ("Dictionary", "TypedDictionaryArray")
        and method.name in {"add_diff_hunks_into_diff_file", "add_line_diffs_into_diff_hunk"}
    ):
        return CallShape("ptrcallWithDictionaryDictionaryListArgsRetDictionary", "Map<String, Any?>", "emptyMap()")
    if logical_return == "Transform3D" and logical_args == ("ConstVoidPtr",) and method.name == "transform_from_pose":
        return CallShape("ptrcallWithConstVoidPtrArgRetTransform3D", "Transform3D", "Transform3D.IDENTITY")
    if logical_return == "void" and logical_args == ("ConstVoidPtr",) and method.name == "set_custom_play_space":
        return CallShape("ptrcallWithConstVoidPtrArg", "Unit")
    if (
        logical_return == "enum"
        and logical_args == ("String", "ConstGDExtensionInitializationFunctionPtr")
        and method.name == "load_extension_from_function"
    ):
        return CallShape("ptrcallWithStringConstGDExtensionInitializationFunctionPtrArgsRetLong", "Long", "0L")
    if (
        logical_return == "Object"
        and logical_args == ("Callable",)
        and method.return_type in {"CallbackTweener", "JavaScriptObject", "OpenXRFutureResult", "PropertyTweener"}
    ):
        return CallShape("ptrcallWithCallableArgRetObject", "MemorySegment", "MemorySegment.NULL")
    # Signal is ownership-sensitive like Callable (never in the generic CALL_SHAPES table);
    # admit it per-method. Signal(Object, StringName) is a plain ObjectID+StringName value,
    # so the helper constructs it for the call and destroys it after — the engine keeps its
    # own copy and dead targets degrade to the invalid-ObjectID no-op, no Kotlin state retained.
    if (
        logical_return == "Object"
        and logical_args == ("Signal",)
        and method.return_type == "AwaitTweener"
        and method.name == "tween_await"
    ):
        return CallShape("ptrcallWithSignalArgRetObject", "MemorySegment", "MemorySegment.NULL")
    if (
        logical_return == "Object"
        and logical_args == ("String", "Callable")
        and method.return_type == "JavaObject"
    ):
        return CallShape("ptrcallWithStringCallableArgsRetObject", "MemorySegment", "MemorySegment.NULL")
    if (
        logical_return == "Object"
        and logical_args == ("Callable", "Variant", "Variant", "float")
        and method.return_type == "MethodTweener"
    ):
        return CallShape("ptrcallWithCallableVariantVariantDoubleArgsRetObject", "MemorySegment", "MemorySegment.NULL")
    if logical_return == "enum" and logical_args == ("StringName", "Callable", "uint32") and method.name == "connect":
        return CallShape("ptrcallWithStringNameCallableAndUInt32ArgsRetLong", "Long", "0L")
    if logical_return == "void" and logical_args == ("StringName", "Callable") and method.name == "disconnect":
        return CallShape("ptrcallWithStringNameAndCallableArgs", "Unit")
    if logical_return == "bool" and logical_args == ("StringName", "Callable") and method.name == "is_connected":
        return CallShape("ptrcallWithStringNameAndCallableArgsRetBool", "Boolean", "false")
    if logical_return == "void":
        callable_void_helpers = {
            ("Callable",): "ptrcallWithCallableArg",
            ("RID", "Callable"): "ptrcallWithRIDCallableArgs",
            ("String", "Callable"): "ptrcallWithStringCallableArgs",
            ("Object", "Callable"): "ptrcallWithObjectCallableArgs",
            ("Callable", "int32"): "ptrcallWithCallableIntArgs",
            ("int32", "Callable"): "ptrcallWithIntCallableArgs",
            ("StringName", "Callable"): "ptrcallWithStringNameAndCallableArgs",
            ("enum", "Callable"): "ptrcallWithLongCallableArgs",
            ("RID", "enum", "Callable"): "ptrcallWithRIDLongCallableArgs",
            ("String", "int32", "Callable"): "ptrcallWithStringIntCallableArgs",
            ("RID", "int32", "Callable"): "ptrcallWithRIDIntCallableArgs",
            ("Object", "Object", "Callable"): "ptrcallWithTwoObjectCallableArgs",
            ("Object", "Object", "Object", "Callable"): "ptrcallWithThreeObjectCallableArgs",
            ("String", "Callable", "Object"): "ptrcallWithStringCallableObjectArgs",
            ("Object", "Callable", "String"): "ptrcallWithObjectCallableStringArgs",
            ("Callable", "Callable"): "ptrcallWithTwoCallableArgs",
            ("Callable", "Callable", "Callable"): "ptrcallWithThreeCallableArgs",
            ("String", "Callable", "Callable"): "ptrcallWithStringTwoCallableArgs",
            ("RID", "Callable", "Callable"): "ptrcallWithRIDTwoCallableArgs",
            ("RID", "Callable", "Variant"): "ptrcallWithRIDCallableVariantArgs",
            ("String", "String", "Callable", "String"): "ptrcallWithTwoStringCallableStringArgs",
            ("RID", "bool", "Rect2", "Callable", "Callable"): "ptrcallWithRIDBoolRect2TwoCallableArgs",
            ("StringName", "Callable", "Array", "enum"): "ptrcallWithStringNameCallableArrayLongArgs",
            ("Callable", "TypedStringNameArray"): "ptrcallWithCallableStringNameListArgs",
            ("Callable", "TypedStringNameArray", "Object"): "ptrcallWithCallableStringNameListObjectArgs",
            ("Callable", "StringName", "String", "String", "TypedStringNameArray"): (
                "ptrcallWithCallableStringNameTwoStringStringNameListArgs"
            ),
            ("Object", "Callable", "PackedInt32Array", "String"): "ptrcallWithObjectCallablePackedInt32ListStringArgs",
        }
        if logical_args in callable_void_helpers:
            return CallShape(callable_void_helpers[logical_args], "Unit")
    if logical_return == "int64":
        callable_long_helpers = {
            ("Callable", "bool", "String"): "ptrcallWithCallableBoolStringArgsRetLong",
            ("Callable", "int32", "int32", "bool", "String"): "ptrcallWithCallableTwoIntBoolStringArgsRetLong",
        }
        if logical_args in callable_long_helpers:
            return CallShape(callable_long_helpers[logical_args], "Long", "0L")
    if logical_return == "bool" and logical_args == ("Callable",):
        return CallShape("ptrcallWithCallableArgRetBool", "Boolean", "false")
    if logical_return == "enum" and logical_args == ("Callable", "enum"):
        return CallShape("ptrcallWithCallableLongArgsRetLong", "Long", "0L")
    if logical_return == "Callable":
        callable_return_helpers = {
            (): "ptrcallNoArgsRetCallable",
            ("int32",): "ptrcallWithIntArgRetCallable",
            ("RID",): "ptrcallWithRIDArgRetCallable",
            ("RID", "int32"): "ptrcallWithRIDIntArgsRetCallable",
            ("String", "int32"): "ptrcallWithStringIntArgsRetCallable",
        }
        if logical_args in callable_return_helpers:
            return CallShape(callable_return_helpers[logical_args], "GodotCallable?", "null")
    if logical_return == "Object" and method.return_type == "FileAccess":
        file_access_helpers = {
            ("String", "enum"): "ptrcallWithStringAndLongArgsRetObject",
            ("String", "enum", "PackedByteArray", "PackedByteArray"): (
                "ptrcallWithStringLongByteArrayByteArrayArgsRetObject"
            ),
            ("String", "enum", "String"): "ptrcallWithStringLongStringArgsRetObject",
            ("String", "enum", "enum"): "ptrcallWithStringTwoLongArgsRetObject",
            ("enum", "String", "String", "bool"): "ptrcallWithLongTwoStringBoolArgsRetObject",
        }
        if logical_args in file_access_helpers:
            return CallShape(file_access_helpers[logical_args], "MemorySegment", "MemorySegment.NULL")
    if logical_return == "enum":
        callable_enum_helpers = {
            ("Object", "bool", "Callable", "Callable"): "ptrcallWithObjectBoolTwoCallableArgsRetLong",
            ("RID", "uint32", "Callable"): "ptrcallWithRIDUInt32CallableArgsRetLong",
            ("RID", "Callable", "uint32", "uint32"): "ptrcallWithRIDCallableTwoUInt32ArgsRetLong",
            ("String", "String", "PackedStringArray", "Callable"): "ptrcallWithTwoStringPackedStringListCallableArgsRetLong",
            ("String", "String", "String", "Callable"): "ptrcallWithThreeStringCallableArgsRetLong",
            ("String", "String", "String", "bool", "enum", "PackedStringArray", "Callable", "int32"): (
                "ptrcallWithThreeStringBoolLongPackedStringListCallableIntArgsRetLong"
            ),
            (
                "String",
                "String",
                "String",
                "String",
                "bool",
                "enum",
                "PackedStringArray",
                "TypedDictionaryArray",
                "Callable",
                "int32",
            ): "ptrcallWithFourStringBoolLongPackedStringListDictionaryListCallableIntArgsRetLong",
        }
        if logical_args in callable_enum_helpers:
            return CallShape(callable_enum_helpers[logical_args], "Long", "0L")
    if logical_return == "int32":
        callable_int_helpers = {
            ("Object", "String", "Callable"): "ptrcallWithObjectStringCallableArgsRetInt",
            ("String", "String", "Callable", "Callable", "Variant", "enum", "int32"): (
                "ptrcallWithTwoStringTwoCallableVariantLongIntArgsRetInt"
            ),
            ("String", "Object", "String", "Callable", "Callable", "Variant", "enum", "int32"): (
                "ptrcallWithStringObjectStringTwoCallableVariantLongIntArgsRetInt"
            ),
            ("String", "String", "int32", "int32", "Callable", "Callable", "Variant", "enum", "int32"): (
                "ptrcallWithTwoStringTwoIntTwoCallableVariantLongIntArgsRetInt"
            ),
            ("RID", "String", "Callable", "Callable", "Variant", "enum", "int32"): (
                "ptrcallWithRIDStringTwoCallableVariantLongIntArgsRetInt"
            ),
            ("RID", "Object", "String", "Callable", "Callable", "Variant", "enum", "int32"): (
                "ptrcallWithRIDObjectStringTwoCallableVariantLongIntArgsRetInt"
            ),
            ("RID", "String", "int32", "int32", "Callable", "Callable", "Variant", "enum", "int32"): (
                "ptrcallWithRIDStringTwoIntTwoCallableVariantLongIntArgsRetInt"
            ),
        }
        if logical_args in callable_int_helpers:
            return CallShape(callable_int_helpers[logical_args], "Int", "0")
    if (
        logical_return == "Object"
        and logical_args in {("int64", "Callable"), ("enum", "Callable")}
        and method.return_type == "OpenXRFutureResult"
    ):
        return CallShape("ptrcallWithLongCallableArgsRetObject", "MemorySegment", "MemorySegment.NULL")
    if (
        logical_return == "Object"
        and logical_args == ("Object", "RID", "Callable")
        and method.return_type == "OpenXRFutureResult"
    ):
        return CallShape("ptrcallWithObjectRIDCallableArgsRetObject", "MemorySegment", "MemorySegment.NULL")
    if logical_return == "Object" and method.return_type == "OpenXRFutureResult":
        if (
            len(logical_args) == 3
            and typed_object_array_element(logical_args[0])
            and logical_args[1:] == ("Object", "Callable")
        ):
            return CallShape("ptrcallWithObjectListObjectCallableArgsRetObject", "MemorySegment", "MemorySegment.NULL")
        if logical_args == ("RID", "PackedInt64Array", "Object", "Callable"):
            return CallShape("ptrcallWithRIDPackedInt64ListObjectCallableArgsRetObject", "MemorySegment", "MemorySegment.NULL")
        if (
            len(logical_args) == 4
            and logical_args[0] == "RID"
            and typed_object_array_element(logical_args[1])
            and logical_args[2:] == ("Object", "Callable")
        ):
            return CallShape("ptrcallWithRIDObjectListObjectCallableArgsRetObject", "MemorySegment", "MemorySegment.NULL")
        if (
            len(logical_args) == 5
            and logical_args[0] == "RID"
            and typed_object_array_element(logical_args[1])
            and logical_args[2:] == ("Object", "Object", "Callable")
        ):
            return CallShape("ptrcallWithRIDObjectListTwoObjectCallableArgsRetObject", "MemorySegment", "MemorySegment.NULL")
    return CALL_SHAPES.get((logical_args, logical_return))


def candidate_for(method: ApiMethod, object_types: set[str], class_name: str | None = None) -> CallShape | None:
    if class_name is not None:
        override = METHOD_CALL_SHAPE_OVERRIDES.get((class_name, method.name))
        if override is not None:
            return override
    shape = _candidate_for_impl(method, object_types)
    # iOS remap: typed-object-array returns use the GENERIC fromHandle helpers, not the DIRECT
    # List<Node> ones (dependency inversion — see IOS_DIRECT_TO_GENERIC_TYPED_OBJECT_LIST). Keep
    # desktop/Android (IOS_AUDIT_ONLY == False) on the direct helpers.
    if shape is not None and IOS_AUDIT_ONLY and typed_object_array_element_any(method.logical_return_kind(object_types)):
        generic = IOS_DIRECT_TO_GENERIC_TYPED_OBJECT_LIST.get(shape.function, shape.function)
        if generic != shape.function:
            shape = CallShape(generic, shape.kotlin_return, shape.fallback)
    return shape


def is_supported_vararg_method(method: ApiMethod, object_types: set[str]) -> bool:
    if not method.is_vararg:
        return False
    logical_return = method.logical_return_kind(object_types)
    if logical_return not in VARARG_RETURN_TYPES:
        return False
    logical_args = method.logical_arg_kinds(object_types)
    return "Callable" not in logical_args and logical_return != "Callable"


def ios_method_supported(method: ApiMethod, object_types: set[str], class_name: str | None = None) -> bool:
    """True iff the iOS ObjectCalls helper generator can marshal this method.

    Independent of desktop emittability — callers AND `unsupported_reason` together
    gate emission. Varargs marshal through the hand-written `callWithVariantArgs` path
    (not a generated ptrcall helper), so `unsupported_reason` clears them before this
    ptrcall-helper gate is consulted; here they are treated as unsupported.
    """
    if method.is_vararg:
        return False
    shape = candidate_for(method, object_types, class_name)
    if shape is None:
        return False
    # Typed-object-array returns: candidate_for has already remapped the DIRECT List<Node> helper
    # to its generic fromHandle equivalent on iOS. Admit only the arg-shapes whose generic helper is
    # hand-written + audited, and only when the element wrapper is itself emitted on iOS (else
    # `{Element}::fromHandle` wouldn't compile). Detected by element (covers both the dedicated named
    # kinds and the generic TypedObjectArray::X form), not by the kotlin_return token. Mirrors the
    # per-helper Packed*Array gates.
    typed_array_element = typed_object_array_element_any(method.logical_return_kind(object_types))
    if typed_array_element is not None:
        if shape.function not in IOS_WIRED_TYPED_OBJECT_LIST_HELPERS:
            return False
        if IOS_EMIT_CLASSES is not None and typed_array_element != "Object" and typed_array_element not in IOS_EMIT_CLASSES:
            return False
    elif shape.kotlin_return not in IOS_RET_KOTLIN:
        return False
    # iOS String read-back is wired for the two no-arg getters: ptrcallNoArgsRetString
    # (String return) and ptrcallNoArgsRetStringName (StringName return → String via the
    # String-from-StringName ctor hop). Arg+String/StringName shapes share the "String"
    # kotlin_return token but route through helpers not audited on iOS yet — gate on the
    # concrete helper so only the no-arg getters emit a real call.
    if shape.kotlin_return == "String" and shape.function not in (
        "ptrcallNoArgsRetString",
        "ptrcallNoArgsRetStringName",
        # 2.7f-1 arg-bearing String returns — route through the device-proven Object-call STRING
        # decode (callWithVariantArgs), same as 2.7e Variant. No new C/header.
        "ptrcallWithVector2ArgRetString",
        "ptrcallWithStringArgRetString",
        "ptrcallWithStringAndStringNameArgRetString",
        "ptrcallWithStringStringNameIntStringNameArgsRetString",
        # 2.7f-2 arg-bearing StringName returns (kotlin_return "String") — Object-call decode now
        # converts a STRING_NAME Variant return to utf8.
        "ptrcallWithStringNameArgRetStringName",
    ):
        return False
    # Variant (scalar) return: iOS routes these through the device-proven Object-call decode
    # (callWithVariantArgs over kanama_ios_godot_object_call — bool/int/float/String/Object
    # scalars; complex types surface null, matching desktop's RetVariantScalar). Only the no-arg
    # and StringName-arg getters are wired; other Variant arg-shapes (Dictionary-arg, etc.) share
    # the "Any?" token but route through helpers not audited on iOS yet — gate on the concrete helper.
    if shape.kotlin_return == "Any?" and shape.function not in (
        "ptrcallNoArgsRetVariantScalar",
        "ptrcallWithStringNameArgRetVariantScalar",
        # task 43: hand-written owned decode over the dedicated C entry (retain must happen
        # before the C shim destroys the return Variant — see kanama_ios_classdb_instantiate_owned).
        "ptrcallWithStringNameArgRetVariantScalarOwned",
    ):
        return False
    # NodePath read-back is wired only for the no-arg getter (ptrcallNoArgsRetNodePath, the
    # hand-written C helper + two-call length protocol). Arg-bearing NodePath returns would
    # need a generated read-back path that doesn't exist yet — keep them skipped.
    if shape.kotlin_return == "NodePath" and shape.function not in (
        "ptrcallNoArgsRetNodePath",
        # 2.7f-2 arg-bearing NodePath returns — Object-call decode now converts a NODE_PATH Variant
        # return to utf8, which the helper re-wraps in NodePath.
        "ptrcallWithLongArgRetNodePath",
        "ptrcallWithObjectAndBoolArgRetNodePath",
    ):
        return False
    # PackedInt32Array read-back is wired only for the no-arg getter
    # (ptrcallNoArgsRetPackedInt32List → List<Int> via the size + operator_index_const C
    # helper, two-call length protocol). Other shapes share the "List<Int>" return token
    # (arg-bearing PackedInt32 returns) but route through helpers not audited yet.
    if shape.kotlin_return == "List<Int>" and shape.function != "ptrcallNoArgsRetPackedInt32List":
        return False
    # PackedFloat32Array read-back: same gate, only the no-arg getter is wired.
    if shape.kotlin_return == "List<Float>" and shape.function != "ptrcallNoArgsRetPackedFloat32List":
        return False
    # PackedVector2Array / PackedColorArray read-back: same gate, no-arg getter only.
    if shape.kotlin_return == "List<Vector2>" and shape.function != "ptrcallNoArgsRetPackedVector2List":
        return False
    if shape.kotlin_return == "List<Color>" and shape.function != "ptrcallNoArgsRetPackedColorList":
        return False
    # PackedStringArray read-back (variable-length blob): "List<String>" is shared with the typed
    # string-array shapes, so gate on the concrete wired helpers — the no-arg PackedString getter and
    # (2.7g) the typed Array[StringName] getter (both use a blob read-back).
    if shape.kotlin_return == "List<String>" and shape.function not in (
        "ptrcallNoArgsRetPackedStringList",
        "ptrcallNoArgsRetStringNameList",
    ):
        return False
    # Typed Array[NodePath] / Array[int] read-back (2.7g blob helpers) — only the no-arg getters.
    if shape.kotlin_return == "List<NodePath>" and shape.function != "ptrcallNoArgsRetNodePathList":
        return False
    if shape.kotlin_return == "List<Long>" and shape.function != "ptrcallNoArgsRetLongList":
        return False
    # Typed Array[Plane] read-back (2.7i blob helper, 16-byte float32 records) — no-arg getter only.
    if shape.kotlin_return == "List<Plane>" and shape.function != "ptrcallNoArgsRetPlaneList":
        return False
    # Generic Array -> List<Any?> (2.7j variant-array blob): the no-arg getter + the NodePath-arg one.
    if shape.kotlin_return == "List<Any?>" and shape.function not in (
        "ptrcallNoArgsRetArray",
        "ptrcallWithNodePathArgRetArray",
    ):
        return False
    logical_args = method.logical_arg_kinds(object_types)
    if not all(kind in IOS_ARG_KINDS for kind in logical_args):
        return False
    # PackedFloat32Array arg (build-from-list): only the single-arg void shape is wired.
    # Multi-arg shapes (e.g. CanvasItem.draw_*) share the kind but route through unbuilt helpers.
    if "PackedFloat32Array" in logical_args and shape.function != "ptrcallWithPackedFloat32ListArg":
        return False
    # PackedVector2Array / PackedColorArray args are wired only for the two no-Object CanvasItem
    # draw_* shapes (draw_polyline/draw_multiline and the *_colors variants). Other shapes that use
    # these kinds (single-arg setters on non-emitted classes, RID-prefixed RenderingServer combos,
    # the Texture2D-arg polygon/primitive shapes) route through helpers not yet built.
    if ("PackedVector2Array" in logical_args or "PackedColorArray" in logical_args) and shape.function not in (
        "ptrcallWithPackedVector2ListColorDoubleAndBoolArgs",
        "ptrcallWithPackedVector2ListPackedColorListDoubleAndBoolArgs",
        "ptrcallWithPackedVector2ListPackedColorListPackedVector2ListAndObjectArgs",
        "ptrcallWithPackedVector2ListColorPackedVector2ListAndObjectArgs",
    ):
        return False
    # Every referenced object wrapper type must also be emitted on iOS (or be the root
    # Object -> GodotObject). Keeps the generated island self-contained / compilable.
    if IOS_EMIT_CLASSES is not None:
        for kind, arg_type in zip(logical_args, method.argument_types, strict=True):
            if kind == "Object" and arg_type != "Object" and arg_type not in IOS_EMIT_CLASSES:
                return False
        if method.logical_return_kind(object_types) == "Object":
            return_type = method.return_type
            if return_type != "Object" and return_type not in IOS_EMIT_CLASSES:
                return False
    return True


def unsupported_reason(
    class_name: str,
    method: ApiMethod,
    object_types: set[str],
    wrapper_classes: set[str],
    api_classes: dict[str, ApiClass],
    api_dir: Path,
) -> str | None:
    if class_name == "Object":
        return "root Object methods are exposed through the hand-shaped GodotObject policy"
    if (class_name, method.name) in OWNERSHIP_SENSITIVE_METHODS:
        return "ownership-sensitive RefCounted lifetime method is hand-shaped"
    by_design = BY_DESIGN_METHOD_SKIPS.get((class_name, method.name))
    if by_design:
        return by_design
    if method.name.startswith("_"):
        return "internal/virtual callback methods are not emitted as public wrappers"
    if method.is_vararg and is_supported_vararg_method(method, object_types):
        # Supported varargs (void/Variant/enum return, no Callable) render the same on every
        # platform: ObjectCalls.callWithVariantArgs boxes the fixed + vararg args and drives the
        # Variant call path. On iOS that path (kanama_ios_godot_object_call + encodeVariantArgs) is
        # device-proven, so varargs are emitted there too (Phase 1.4 iOS vararg).
        return None
    if method.is_vararg:
        return "vararg methods must use dynamic Object.call policy"
    logical_args = method.logical_arg_kinds(object_types)
    logical_return = method.logical_return_kind(object_types)
    for arg_kind, arg_type in zip(logical_args, method.argument_types, strict=True):
        array_element = typed_object_array_element(arg_kind)
        if array_element:
            wrapper_type = api_object_wrapper_type(array_element, wrapper_classes)
            if wrapper_type is None:
                return f"typed object-array argument wrapper is missing for {array_element}"
    return_array_element = typed_object_array_element(logical_return)
    if return_array_element:
        return_wrapper = api_object_wrapper_type(return_array_element, wrapper_classes)
        if return_wrapper is None:
            return f"typed object-array return wrapper is missing for {return_array_element}"
        if not wrapper_has_wrap(api_dir, return_wrapper):
            return f"typed object-array return wrapper {return_wrapper} has no nullable wrap(handle) helper"
    if "Object" in logical_args:
        for arg_type in method.argument_types:
            if arg_type == "Callable" and candidate_for(method, object_types, class_name) is not None:
                continue
            if arg_type in OWNERSHIP_SENSITIVE_OBJECT_TYPES:
                return f"object argument wrapper {arg_type} is ownership/builtin-sensitive and must stay unsupported"
            if arg_type in object_types and api_object_wrapper_type(arg_type, wrapper_classes) is None:
                return f"object argument wrapper is missing for {arg_type}"
    if logical_return == "Object":
        if method.return_type in OWNERSHIP_SENSITIVE_OBJECT_TYPES:
            return f"object return wrapper {method.return_type} is ownership/builtin-sensitive and must stay unsupported"
        return_wrapper = api_object_wrapper_type(method.return_type, wrapper_classes)
        if return_wrapper is None:
            return f"object return wrapper is missing for {method.return_type}"
        if not wrapper_has_wrap(api_dir, return_wrapper):
            return f"object return wrapper {return_wrapper} has no nullable wrap(handle) helper"
    if "Callable" in logical_args and candidate_for(method, object_types, class_name) is None:
        return "object argument wrapper Callable is ownership/builtin-sensitive and must stay unsupported"
    if logical_return == "Callable" and candidate_for(method, object_types, class_name) is None:
        return "object return wrapper Callable is ownership/builtin-sensitive and must stay unsupported"
    if candidate_for(method, object_types, class_name) is None:
        return f"unsupported helper shape args={logical_args} return={logical_return}"
    if IOS_AUDIT_ONLY and not ios_method_supported(method, object_types, class_name):
        return (
            f"iOS: un-audited helper shape args={logical_args} return={logical_return} "
            "(conservative iOS guardrail — only audited ptrcall widths are emitted)"
        )
    return None


def kotlin_type(logical_type: str, type_name: str, wrapper_classes: set[str], api_classes: dict[str, ApiClass]) -> str:
    array_element = typed_object_array_element(logical_type)
    if array_element:
        wrapper_type = api_object_wrapper_type(array_element, wrapper_classes)
        if wrapper_type is None:
            raise ValueError(f"unsupported typed object-array wrapper for {array_element}")
        return f"List<{wrapper_type}>"
    if logical_type == "Object":
        wrapper_type = api_object_wrapper_type(type_name, wrapper_classes)
        if wrapper_type is None:
            raise ValueError(f"unsupported object wrapper for {type_name}")
        return f"{wrapper_type}?" if is_resource_like(type_name, api_classes) else wrapper_type
    if logical_type not in SCALAR_KOTLIN_TYPES:
        raise ValueError(f"unsupported Kotlin type for {logical_type}")
    return SCALAR_KOTLIN_TYPES[logical_type]


def kotlin_return_type(
    logical_type: str,
    type_name: str,
    wrapper_classes: set[str],
    api_classes: dict[str, ApiClass],
    api_dir: Path,
) -> str:
    if logical_type == "Callable":
        return "GodotCallable?"
    if logical_type == "Object":
        wrapper_type = api_object_wrapper_type(type_name, wrapper_classes)
        if wrapper_type is None:
            raise ValueError(f"unsupported object wrapper for {type_name}")
        return f"{wrapper_type}?" if wrapper_has_wrap(api_dir, wrapper_type) else wrapper_type
    return kotlin_type(logical_type, type_name, wrapper_classes, api_classes)


def object_param_is_nullable(
    class_name: str,
    method_name: str,
    arg_name: str,
    type_name: str,
    arg_meta: str,
    api_classes: dict[str, ApiClass],
) -> bool:
    """Whether a generated Object parameter should be Kotlin-nullable (accept `null`).

    Single decision point, consumed by both the type renderer and the marshalling so they cannot
    drift. Order matters:

      1. `meta: "required"` -> non-null. Godot's RequiredParam marker wins over everything,
         including resource ancestry; an audited override cannot beat it (a Godot upgrade marking
         an old override required must stop admitting null). This is the task-52b tightening.
      2. resource-like (Resource/RefCounted-derived) -> nullable (existing legacy policy for
         *unmarked* resource params).
      3. exact (class, method, arg) in NULLABLE_OBJECT_PARAM_OVERRIDES -> nullable (audited source).
      4. otherwise -> non-null (conservative legacy default).

    Task 52a kept resource-like nullable regardless of `required` (non-breaking). Task 52b moves
    the `required` check ABOVE the resource-like line, turning the 65 explicitly-required
    resource-like parameters non-null — a source-breaking narrowing of public signatures.
    """
    if arg_meta == "required":
        return False
    if is_resource_like(type_name, api_classes):
        return True
    return (class_name, method_name, arg_name) in NULLABLE_OBJECT_PARAM_OVERRIDES


def kotlin_parameter_type(
    class_name: str,
    method_name: str,
    arg_name: str,
    arg_meta: str,
    logical_type: str,
    type_name: str,
    wrapper_classes: set[str],
    api_classes: dict[str, ApiClass],
) -> str:
    """Parameter type with method/argument context. Object params consult [object_param_is_nullable];
    all other kinds fall back to the context-free [kotlin_type]. Marshalling uses the same decision."""
    base = kotlin_type(logical_type, type_name, wrapper_classes, api_classes)
    if logical_type != "Object":
        return base
    non_null = base[:-1] if base.endswith("?") else base
    nullable = object_param_is_nullable(class_name, method_name, arg_name, type_name, arg_meta, api_classes)
    return f"{non_null}?" if nullable else non_null


def object_arg_expression(
    name: str,
    type_name: str,
    is_nullable: bool,
    api_classes: dict[str, ApiClass],
) -> str:
    """Marshal an Object parameter to its MemorySegment handle. [is_nullable] is the decision from
    [object_param_is_nullable] so the type and the marshalling stay in lockstep. Resource-like keeps
    the closed-handle check via requireOpenHandle()."""
    resource_like = is_resource_like(type_name, api_classes)
    if resource_like:
        return f"{name}?.requireOpenHandle() ?: MemorySegment.NULL" if is_nullable else f"{name}.requireOpenHandle()"
    return f"{name}?.handle ?: MemorySegment.NULL" if is_nullable else f"{name}.handle"


def call_argument_expressions(
    class_name: str,
    method: ApiMethod,
    object_types: set[str],
    api_classes: dict[str, ApiClass],
    names: list[str] | None = None,
) -> list[str]:
    names = names or [arg_name(name, index) for index, name in enumerate(method.argument_names)]
    expressions: list[str] = []
    for name, raw_name, type_name, arg_meta, logical_kind in zip(
        names,
        method.argument_names,
        method.argument_types,
        method.argument_metas,
        method.logical_arg_kinds(object_types),
        strict=True,
    ):
        if logical_kind == "Object":
            is_nullable = object_param_is_nullable(
                class_name, method.name, raw_name, type_name, arg_meta, api_classes
            )
            expressions.append(object_arg_expression(name, type_name, is_nullable, api_classes))
        elif logical_kind == "Callable":
            expressions.extend([f"{name}.target.handle", f"{name}.method"])
        elif logical_kind == "Signal":
            # Signal args marshal as (owner handle, signal name); the helper constructs the
            # Signal builtin via Signal(Object, StringName) and destroys it after the call.
            expressions.extend([f"{name}.owner.handle", f"{name}.name"])
        else:
            expressions.append(name)
    return expressions


def render_return_expression(call: str, method: ApiMethod, wrapper_classes: set[str]) -> str:
    return_wrapper = api_object_wrapper_type(method.return_type, wrapper_classes)
    if return_wrapper:
        return f"{return_wrapper}.wrap({call})"
    return call


def self_return_collapse_wrapper(
    class_name: str,
    method: ApiMethod,
    object_types: set[str],
    wrapper_classes: set[str],
    api_classes: dict[str, ApiClass],
    singleton: bool,
) -> str | None:
    """Wrapper type for methods that must collapse an engine self-return, else None.

    Engine methods with a RefCounted-derived return type hand the ptrcall caller a
    +1 reference through the return slot (`PtrToArg<Ref<T>>::encode` /
    `PtrToArg<RequiredResult<T>>::encode` both net +1 — `meta:"required"` does NOT
    change ownership; measured on Godot 4.7-stable, task 31). The Kotlin wrapper
    adopts that reference and `RefCounted.close()` releases it. Fluent
    self-returning methods (e.g. AwaitTweener.set_timeout) would mint a duplicate
    owning wrapper per call that chain users never close, so when the returned
    address equals the receiver the duplicate reference is released and `this` is
    returned instead — the policy the hand-shaped Tween/Tweener classes set.

    All platforms: iOS gained the release primitive (ObjectCalls.destroyObject over
    the C-shim object_destroy) and the RefCounted close()/releaseHandle custom
    sections in the task-30 ownership mirror, so the collapse pattern is emitted
    under --ios-emit-class too. Varargs can never hit this path (VARARG_RETURN_TYPES
    excludes Object), so the release always sits on a ptrcall object-return helper,
    never on callWithVariantArgs.
    """
    if singleton or method.is_static:
        return None
    if method.logical_return_kind(object_types) != "Object":
        return None
    if not is_resource_like(method.return_type, api_classes):
        return None
    if method.return_type != class_name and method.return_type not in ancestors(class_name, api_classes):
        return None
    return_wrapper = api_object_wrapper_type(method.return_type, wrapper_classes)
    if return_wrapper != method.return_type:
        return None
    return return_wrapper


def render_method(
    class_name: str,
    method: ApiMethod,
    shape: CallShape,
    object_types: set[str],
    wrapper_classes: set[str],
    api_classes: dict[str, ApiClass],
    api_dir: Path,
    singleton: bool = False,
) -> str:
    function_name = method_function_name(class_name, method.name)
    bind_name = f"{function_name}Bind"
    logical_args = method.logical_arg_kinds(object_types)
    param_names = [
        arg_name_for_method(class_name, method.name, name, index)
        for index, name in enumerate(method.argument_names)
    ]
    param_texts = []
    for name, raw_name, kind, type_name, arg_meta, default_value in zip(
        param_names,
        method.argument_names,
        logical_args,
        method.argument_types,
        method.argument_metas,
        method.argument_defaults,
        strict=True,
    ):
        default_value = DEFAULT_VALUE_OVERRIDES.get((class_name, method.name, raw_name), default_value)
        default_expression = KOTLIN_DEFAULT_EXPRESSION_OVERRIDES.get(
            (class_name, method.name, raw_name),
        ) or kotlin_default_expression(default_value, kind)
        type_text = kotlin_parameter_type(
            class_name, method.name, raw_name, arg_meta, kind, type_name, wrapper_classes, api_classes
        )
        param_texts.append(
            f"{name}: {type_text} = {default_expression}" if default_expression is not None else f"{name}: {type_text}",
        )
    params = ", ".join(param_texts)
    call_args = call_argument_expressions(class_name, method, object_types, api_classes, param_names)
    return_array_element = typed_object_array_element_any(method.logical_return_kind(object_types))
    if return_array_element and shape.function not in DIRECT_TYPED_OBJECT_LIST_HELPERS:
        return_wrapper = api_object_wrapper_type(return_array_element, wrapper_classes)
        if return_wrapper is None:
            raise ValueError(f"unsupported typed object-array wrapper for {return_array_element}")
        call_args.append(f"{return_wrapper}::fromHandle")
    if shape.function == "ptrcallWithObjectArgs":
        receiver = "singleton" if singleton else ("MemorySegment.NULL" if method.is_static else "handle")
        call = f"ObjectCalls.{shape.function}({bind_name}, {receiver}, listOf({', '.join(call_args)}))"
    else:
        receiver = "singleton" if singleton else ("MemorySegment.NULL" if method.is_static else "handle")
        call = f"ObjectCalls.{shape.function}({', '.join([bind_name, receiver, *call_args])})"
    return_expression = render_return_expression(call, method, wrapper_classes)
    return_kind = method.logical_return_kind(object_types)
    return_type_text = (
        ""
        if shape.kotlin_return == "Unit"
        else f": {kotlin_return_type(return_kind, method.return_type, wrapper_classes, api_classes, api_dir)}"
    )
    collapse_wrapper = self_return_collapse_wrapper(
        class_name, method, object_types, wrapper_classes, api_classes, singleton,
    )
    lines = []
    if singleton:
        (None if IOS_AUDIT_ONLY else lines.append("    @JvmStatic"))
    if collapse_wrapper is not None:
        lines.extend(
            [
                f"    fun {function_name}({params}){return_type_text} {{",
                f"        val ret = {call}",
                "        if (ret.address() == handle.address()) {",
                "            RefCounted.releaseHandle(ret)",
                "            return this",
                "        }",
                f"        return {collapse_wrapper}.wrap(ret)",
                "    }",
            ],
        )
    else:
        lines.extend(
            [
                f"    fun {function_name}({params}){return_type_text} {{",
                f"        {call}" if return_type_text == "" else f"        return {return_expression}",
                "    }",
            ],
        )
    return "\n".join(lines)


def render_vararg_method(
    class_name: str,
    method: ApiMethod,
    object_types: set[str],
    wrapper_classes: set[str],
    api_classes: dict[str, ApiClass],
    api_dir: Path,
    singleton: bool = False,
) -> str:
    function_name = method_function_name(class_name, method.name)
    bind_name = f"{function_name}Bind"
    logical_args = method.logical_arg_kinds(object_types)
    param_names = [
        arg_name_for_method(class_name, method.name, name, index)
        for index, name in enumerate(method.argument_names)
    ]
    fixed_params = []
    for name, raw_name, kind, type_name, arg_meta, default_value in zip(
        param_names,
        method.argument_names,
        logical_args,
        method.argument_types,
        method.argument_metas,
        method.argument_defaults,
        strict=True,
    ):
        default_value = DEFAULT_VALUE_OVERRIDES.get((class_name, method.name, raw_name), default_value)
        default_expression = KOTLIN_DEFAULT_EXPRESSION_OVERRIDES.get(
            (class_name, method.name, raw_name),
        ) or kotlin_default_expression(default_value, kind)
        type_text = kotlin_parameter_type(
            class_name, method.name, raw_name, arg_meta, kind, type_name, wrapper_classes, api_classes
        )
        fixed_params.append(
            f"{name}: {type_text} = {default_expression}" if default_expression is not None else f"{name}: {type_text}",
        )
    params = ", ".join([*fixed_params, "vararg extraArgs: Any?"])
    fixed_args = ", ".join(param_names)
    if fixed_args:
        call_args = f"listOf({fixed_args}, *extraArgs)"
    else:
        call_args = "listOf(*extraArgs)"
    receiver = "singleton" if singleton else ("MemorySegment.NULL" if method.is_static else "handle")
    # A METHOD_CALL_SHAPE_OVERRIDES entry names an alternate dispatch helper (e.g.
    # callWithVariantArgsOwned for the owned return decode); default is callWithVariantArgs.
    override = METHOD_CALL_SHAPE_OVERRIDES.get((class_name, method.name))
    helper = override.function if override is not None else "callWithVariantArgs"
    call = f"ObjectCalls.{helper}({bind_name}, {receiver}, {call_args})"
    return_kind = method.logical_return_kind(object_types)
    if return_kind == "void":
        lines = []
        if singleton:
            (None if IOS_AUDIT_ONLY else lines.append("    @JvmStatic"))
        lines.extend(
            [
                f"    fun {function_name}({params}) {{",
                f"        {call}",
                "    }",
            ],
        )
        return "\n".join(lines)
    return_type_text = kotlin_return_type(return_kind, method.return_type, wrapper_classes, api_classes, api_dir)
    return_expression = f"({call} as Number).toLong()" if return_kind == "enum" else call
    lines = []
    if singleton:
        (None if IOS_AUDIT_ONLY else lines.append("    @JvmStatic"))
    lines.extend(
        [
            f"    fun {function_name}({params}): {return_type_text} {{",
            f"        return {return_expression}",
            "    }",
        ],
    )
    return "\n".join(lines)


def rendered_method_names(
    cls: ApiClass,
    object_types: set[str],
    wrapper_classes: set[str],
    api_classes: dict[str, ApiClass],
    api_dir: Path,
) -> set[str]:
    names: set[str] = set()
    for method_list in cls.methods.values():
        for method in method_list:
            if unsupported_reason(cls.name, method, object_types, wrapper_classes, api_classes, api_dir) is None:
                names.add(method.name)
    return names


def first_method(cls: ApiClass, name: str) -> ApiMethod | None:
    methods = cls.methods.get(name)
    return methods[0] if methods else None


def property_setter_type(
    class_name: str,
    setter_method: ApiMethod,
    object_types: set[str],
    wrapper_classes: set[str],
    api_classes: dict[str, ApiClass],
    value_slot: int = 0,
) -> str | None:
    # `value_slot` is the argument index that carries the property's value. It is 0 for plain
    # setters and 1 for indexed setters (set_texture(param, texture)), where slot 0 is the bound
    # index. Arguments past the value slot must all default (nothing extra to pass).
    if len(setter_method.argument_types) <= value_slot:
        return None
    if any(default is None for default in setter_method.argument_defaults[value_slot + 1 :]):
        return None
    if setter_method.logical_return_kind(object_types) != "void":
        return None
    setter_kind = setter_method.logical_arg_kinds(object_types)[value_slot]
    # Route through the same contextual policy the method renderer uses, so a nullable Object setter
    # (e.g. Node.set_owner(owner: Node?)) yields a nullable property type — otherwise render_property
    # sees a Node? getter and a Node setter, the types mismatch, and it suppresses the setter (the
    # property becomes read-only). Task 52a.
    return kotlin_parameter_type(
        class_name,
        setter_method.name,
        setter_method.argument_names[value_slot],
        setter_method.argument_metas[value_slot],
        setter_kind,
        setter_method.argument_types[value_slot],
        wrapper_classes,
        api_classes,
    )


def render_property(
    cls: ApiClass,
    prop: dict[str, object],
    emitted_methods: set[str],
    object_types: set[str],
    wrapper_classes: set[str],
    api_classes: dict[str, ApiClass],
    api_dir: Path,
) -> str | None:
    getter = str(prop.get("getter") or "")
    setter = str(prop.get("setter") or "")
    if setter and setter not in emitted_methods and setter.startswith("_") and setter[1:] in emitted_methods:
        setter = setter[1:]
    if not getter or getter not in emitted_methods:
        return None
    getter_method = first_method(cls, getter)
    if getter_method is None:
        return None
    # Indexed properties (Godot idiom: albedo_texture -> get_texture(param)/set_texture(param, tex),
    # light_energy -> get_param/set_param, ...) carry an "index" bound as the accessor's first
    # argument. The getter then takes exactly the index (1 arg) and the setter takes index + value
    # (2 args); we pre-bind the index literal and treat the trailing slot as the property value.
    # Without an index a getter that takes arguments is not a property accessor we can express.
    prop_index = prop.get("index")
    has_index = isinstance(prop_index, int)
    if has_index:
        if len(getter_method.argument_types) != 1:
            return None
    elif getter_method.argument_types:
        return None
    raw_property_name = str(prop.get("name") or "")
    if IOS_AUDIT_ONLY and (cls.name, raw_property_name) in IOS_PROPERTY_SUPPRESS:
        return None
    property_name = PROPERTY_NAME_OVERRIDES.get((cls.name, raw_property_name), camel_name(raw_property_name))
    if not property_name:
        return None

    getter_kind = getter_method.logical_return_kind(object_types)
    property_type = kotlin_return_type(
        getter_kind,
        getter_method.return_type,
        wrapper_classes,
        api_classes,
        api_dir,
    )
    # The bound index literal must match the accessor's first-arg Kotlin type: enum/int64 indices
    # are `Long` (getTexture(0L)), plain int32 indices are `Int` (getStream(0)). Plain accessors
    # take no index.
    if has_index:
        index_kotlin = kotlin_parameter_type(
            cls.name,
            getter,
            getter_method.argument_names[0],
            getter_method.argument_metas[0],
            getter_method.logical_arg_kinds(object_types)[0],
            getter_method.argument_types[0],
            wrapper_classes,
            api_classes,
        )
        index_arg = f"{prop_index}" if index_kotlin == "Int" else f"{prop_index}L"
    else:
        index_arg = ""
    value_slot = 1 if has_index else 0
    getter_call = f"{method_function_name(cls.name, getter)}({index_arg})"

    if setter and setter in emitted_methods:
        setter_method = first_method(cls, setter)
        # property_setter_type gates validity: it needs an argument at `value_slot` (so an indexed
        # setter must carry index+value), a void return, and defaults on every argument past the
        # value — which allows plain setters with trailing defaulted args (set_position(pos,
        # keep_offsets = false)) to stay writable.
        setter_type = (
            property_setter_type(
                cls.name, setter_method, object_types, wrapper_classes, api_classes, value_slot
            )
            if setter_method is not None
            else None
        )
        if setter_type != property_type:
            setter = ""

    if setter and setter in emitted_methods:
        setter_args = f"{index_arg}, value" if has_index else "value"
        setter_call = f"{method_function_name(cls.name, setter)}({setter_args})"
        return "\n".join(
            [
                f"    var {property_name}: {property_type}",
                f'        @JvmName("{property_name}Property")',
                f"        get() = {getter_call}",
                f'        @JvmName("set{upper_first(property_name)}Property")',
                f"        set(value) = {setter_call}",
            ],
        )

    return "\n".join(
        [
            f"    val {property_name}: {property_type}",
            f'        @JvmName("{property_name}Property")',
            f"        get() = {getter_call}",
        ],
    )


def render_signal_constants(cls: ApiClass) -> str | None:
    if not cls.signals:
        return None
    lines = ["    object Signals {"]
    for signal in cls.signals:
        name = str(signal.get("name") or "")
        constant_name = camel_name(name)
        if not name or not constant_name:
            continue
        lines.append(f'        const val {constant_name}: String = "{name}"')
    lines.append("    }")
    return "\n".join(lines) if len(lines) > 2 else None


def render_companion_constants(cls: ApiClass) -> str | None:
    lines: list[str] = []
    seen: set[str] = set()
    for constant in cls.constants:
        name = str(constant.get("name") or "")
        if not name or name in seen:
            continue
        value = constant.get("value")
        if not isinstance(value, int):
            continue
        seen.add(name)
        lines.append(f"        const val {name}: Long = {value}L")
    for enum in cls.enums:
        for value_spec in enum.get("values") or ():
            name = str(value_spec.get("name") or "")
            if not name or name in seen:
                continue
            value = value_spec.get("value")
            if not isinstance(value, int):
                continue
            seen.add(name)
            lines.append(f"        const val {name}: Long = {value}L")
    return "\n".join(lines) if lines else None


def outdent_companion_member(section: str) -> str:
    return "\n".join(line[4:] if line.startswith("    ") else line for line in section.splitlines())


def has_api_subclasses(class_name: str, api_classes: dict[str, ApiClass]) -> bool:
    return any(candidate.inherits == class_name for candidate in api_classes.values())


# Classes whose `fromHandle` must return a NON-NULL wrapper. KanamaScript's selfFactory is
# `(MemorySegment) -> Self` (non-null); a `@ScriptClass(attachTo = "Resource")` script uses
# `Resource::fromHandle` as that factory, so `Resource.fromHandle` must be `-> Resource`, not
# `-> Resource?`. The nullable `wrap` helper stays for the general object-return path. Keep this
# minimal — only classes actually used as a non-null script selfFactory base belong here.
NON_NULL_FROM_HANDLE_CLASSES = {"Resource"}


def render_wrap_helpers(class_name: str) -> str:
    # @JvmStatic is a JVM-only annotation; Kotlin/Native (iOS) rejects it. Omit it for
    # the iOS target — fromHandle works the same without it.
    lines = [] if IOS_AUDIT_ONLY else ["        @JvmStatic"]
    if class_name in NON_NULL_FROM_HANDLE_CLASSES:
        lines.extend(
            [
                f"        fun fromHandle(handle: MemorySegment): {class_name} =",
                f"            {class_name}(handle)",
                "",
                f"        internal fun wrap(handle: MemorySegment): {class_name}? =",
                f"            if (handle.address() == 0L) null else {class_name}(handle)",
            ],
        )
    else:
        lines.extend(
            [
                f"        fun fromHandle(handle: MemorySegment): {class_name}? =",
                "            wrap(handle)",
                "",
                f"        internal fun wrap(handle: MemorySegment): {class_name}? =",
                f"            if (handle.address() == 0L) null else {class_name}(handle)",
            ],
        )
    return "\n".join(lines)


def render_singleton_wrap_helpers(class_name: str) -> str:
    lines = [] if IOS_AUDIT_ONLY else ["    @JvmStatic"]
    lines.extend(
        [
            f"    fun fromHandle(handle: MemorySegment): {class_name}? =",
            "        wrap(handle)",
            "",
            f"    internal fun wrap(handle: MemorySegment): {class_name}? =",
            "        if (handle.address() == 0L) null else this",
        ],
    )
    return "\n".join(lines)


def render_draft(
    cls: ApiClass,
    object_types: set[str],
    wrapper_classes: set[str],
    api_classes: dict[str, ApiClass],
    api_dir: Path,
    singleton_names: set[str] | None = None,
) -> tuple[str, list[str]]:
    imports = {"ObjectCalls", "MemorySegment"}
    singleton = cls.name in (singleton_names or set())
    emitted_methods = rendered_method_names(cls, object_types, wrapper_classes, api_classes, api_dir)
    properties: list[str] = []
    methods: list[str] = []
    static_methods: list[str] = []
    binds: list[str] = []
    skips: list[str] = []

    for prop in cls.properties:
        rendered_property = render_property(
            cls,
            prop,
            emitted_methods,
            object_types,
            wrapper_classes,
            api_classes,
            api_dir,
        )
        if rendered_property:
            properties.append(rendered_property)
            imports.add("JvmName")

    for method_list in cls.methods.values():
        for method in method_list:
            reason = unsupported_reason(cls.name, method, object_types, wrapper_classes, api_classes, api_dir)
            if reason:
                skips.append(f"{method.signature} hash={method.hash}: {reason}")
                continue
            for kind in (*method.logical_arg_kinds(object_types), method.logical_return_kind(object_types)):
                if kind in {
                    "AABB",
                    "Basis",
                    "Callable",
                    "Color",
                    "NodePath",
                    "Plane",
                    "Projection",
                    "PackedVector2Array",
                    "PackedVector3Array",
                    "PackedColorArray",
                    "PackedVector4Array",
                    "TypedRIDArray",
                    "TypedStringArray",
                    "TypedIntArray",
                    "TypedNodePathArray",
                    "TypedPackedVector2Array",
                    "TypedPlaneArray",
                    "TypedTransform3DArray",
                    "TypedVector2Array",
                    "TypedVector3Array",
                    "TypedNodeArray",
                    "TypedNode2DArray",
                    "TypedNode3DArray",
                    "TypedMaterialArray",
                    "TypedVector2iArray",
                    "TypedVector3iArray",
                    "TypedStringNameArray",
                    "TypedDictionaryArray",
                    "Rect2",
                    "Rect2i",
                    "RID",
                    "Quaternion",
                    "Transform2D",
                    "Transform3D",
                    "Vector2",
                    "Vector2i",
                    "Vector3",
                    "Vector3i",
                    "Vector4",
                }:
                    if kind == "PackedVector2Array":
                        imports.add("Vector2")
                    elif kind == "Callable":
                        pass
                    elif kind == "PackedVector3Array":
                        imports.add("Vector3")
                    elif kind == "PackedColorArray":
                        imports.add("Color")
                    elif kind == "PackedVector4Array":
                        imports.add("Vector4")
                    elif kind == "TypedRIDArray":
                        imports.add("RID")
                    elif kind == "TypedNodePathArray":
                        imports.add("NodePath")
                    elif kind == "TypedPackedVector2Array":
                        imports.add("Vector2")
                    elif kind == "TypedPlaneArray":
                        imports.add("Plane")
                    elif kind == "TypedTransform3DArray":
                        imports.add("Transform3D")
                    elif kind in {"TypedVector2Array", "TypedVector3Array"}:
                        imports.add(kind.removeprefix("Typed").removesuffix("Array"))
                    elif kind in {
                        "TypedNodeArray",
                        "TypedNode2DArray",
                        "TypedNode3DArray",
                        "TypedMaterialArray",
                        "TypedArea2DArray",
                        "TypedArea3DArray",
                        "TypedBaseButtonArray",
                        "TypedPhysicsBody3DArray",
                    }:
                        pass
                    elif kind in {"TypedVector2iArray", "TypedVector3iArray"}:
                        imports.add(kind.removeprefix("Typed").removesuffix("Array"))
                    elif kind in {
                        "TypedStringArray",
                        "TypedIntArray",
                        "TypedStringNameArray",
                        "TypedDictionaryArray",
                    }:
                        pass
                    else:
                        imports.add(kind)
                if kind == "Object":
                    imports.add("MemorySegment")
            if method.is_vararg:
                rendered_method = render_vararg_method(
                    cls.name,
                    method,
                    object_types,
                    wrapper_classes,
                    api_classes,
                    api_dir,
                    singleton=singleton,
                )
            else:
                shape = candidate_for(method, object_types, cls.name)
                assert shape is not None
                rendered_method = render_method(
                    cls.name,
                    method,
                    shape,
                    object_types,
                    wrapper_classes,
                    api_classes,
                    api_dir,
                    singleton=singleton,
                )
            if method.is_static and not singleton:
                static_methods.append("\n".join(f"    {line}" if line else line for line in rendered_method.splitlines()))
            else:
                methods.append(rendered_method)
            binds.append(
                "\n".join(
                    [
                        f"        private const val {const_name(method)} = {method.hash}L",
                        f"        private val {method_function_name(cls.name, method.name)}Bind by lazy {{",
                        f'            ObjectCalls.getMethodBind("{cls.name}", "{method.name}", {const_name(method)})',
                        "        }",
                    ],
                ),
            )

    singleton_parent = cls.inherits in (singleton_names or set())
    parent = "GodotObject" if cls.inherits in {"", "Object"} or singleton_parent else cls.inherits
    class_keyword = "open class" if has_api_subclasses(cls.name, api_classes) else "class"
    import_lines = sorted(f"import {DEFAULT_IMPORTS[name]}" for name in imports)
    body_sections = []
    if properties:
        body_sections.append("\n\n".join(properties))
    body_sections.append("\n\n".join(methods) if methods else "    // No conservative instance methods emitted yet.")
    # Desktop hand-written custom sections reference desktop-only types/methods
    # (e.g. AnimationMixer.getStateMachinePlayback -> AnimationNodeStateMachinePlayback,
    # getIndexed/setIndexed). They are not part of the conservative iOS surface, so the
    # iOS target omits them; any iOS equivalent lives in the bespoke sugar layer.
    custom_members = IOS_CUSTOM_MEMBER_SECTIONS.get(cls.name) if IOS_AUDIT_ONLY else CUSTOM_MEMBER_SECTIONS.get(cls.name)
    if custom_members:
        body_sections.append(custom_members)
    signal_constants = render_signal_constants(cls)
    if signal_constants:
        body_sections.append(signal_constants)
    if singleton:
        singleton_constants = render_companion_constants(cls)
        if singleton_constants:
            body_sections.insert(0, outdent_companion_member(singleton_constants))
        body_sections.append(render_singleton_wrap_helpers(cls.name))
        bind_sections = [outdent_companion_member(section) for section in binds]
        content = "\n".join(
            [
                "package net.multigesture.kanama.api",
                "",
                *import_lines,
                "",
                "/**",
                f" * Generated from Godot docs: {cls.name}",
                " */",
                f"object {cls.name} {{",
                "    private val singleton: MemorySegment by lazy {",
                f'        ObjectCalls.getSingleton("{cls.name}")',
                "    }",
                "",
                "\n\n".join(body_sections),
                "",
                "\n\n".join(bind_sections) if bind_sections else "    // No MethodBinds emitted yet.",
                "}",
                "",
            ],
        )
    else:
        companion_sections = []
        if static_methods:
            companion_sections.append("\n\n".join(static_methods))
        companion_constants = render_companion_constants(cls)
        if companion_constants:
            companion_sections.append(companion_constants)
        companion_sections.append(render_wrap_helpers(cls.name))
        custom_companion_members = (
            IOS_CUSTOM_COMPANION_MEMBER_SECTIONS.get(cls.name)
            if IOS_AUDIT_ONLY
            else CUSTOM_COMPANION_MEMBER_SECTIONS.get(cls.name)
        )
        if custom_companion_members:
            companion_sections.append(custom_companion_members)
        companion_sections.append("\n\n".join(binds) if binds else "        // No MethodBinds emitted yet.")
        content = "\n".join(
            [
                "package net.multigesture.kanama.api",
                "",
                *import_lines,
                "",
                "/**",
                f" * Generated from Godot docs: {cls.name}",
                " */",
                f"{class_keyword} {cls.name}(handle: MemorySegment) : {parent}(handle) {{",
                "\n\n".join(body_sections),
                "",
                "    companion object {",
                "\n\n".join(companion_sections),
                "    }",
                "}",
                "",
            ],
        )
    return content, skips


# --- iOS ObjectCalls helper-body generation (T3.1) -----------------------------

IOS_OBJECTCALLS_HEADER = '''@file:OptIn(ExperimentalForeignApi::class)

package net.multigesture.kanama.binding.runtime

import java.lang.foreign.MemorySegment
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointed
import kotlinx.cinterop.COpaquePointerVar
import kotlinx.cinterop.DoubleVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.FloatVar
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.cstr
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.set
import kotlinx.cinterop.value
import net.multigesture.kanama.ios.cinterop.KanamaIosCallableArgDesc
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_ptrcall
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.GodotReal
import net.multigesture.kanama.types.GodotRealVar
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Projection
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i
import net.multigesture.kanama.types.Vector4

/**
 * GENERATED iOS ObjectCalls helper bodies (scripts/generate_api_wrapper.py --ios-*).
 * DO NOT EDIT BY HAND. Re-run the generator instead.
 *
 * Each helper is an extension on the hand-written `object ObjectCalls`, so the
 * generated Godot API wrappers' `ObjectCalls.<helper>(...)` calls resolve here. Every
 * helper marshals through the single generic C dispatch `kanama_ios_godot_ptrcall`,
 * applying the authoritative ptrcall width table (scalar float->double/8B, scalar
 * int->int64/8B, Vector components->GodotReal, Object->8B handle, StringName built
 * C-side). Helpers already hand-written in ObjectCalls.kt are the override set and
 * are NOT regenerated here.
 */
'''

# ptrcall type tag name -> numeric value (must match KANAMA_IOS_PT_* in the C shim
# and the PT_* constants in ios-runtime ObjectCalls.kt).
IOS_PT_TAG_VALUES = {
    "PT_VOID": 0,
    "PT_BOOL": 1,
    "PT_INT32": 2,
    "PT_INT64": 3,
    "PT_FLOAT32": 4,
    "PT_FLOAT64": 5,
    "PT_VECTOR2": 6,
    "PT_VECTOR2I": 7,
    "PT_VECTOR3": 8,
    "PT_VECTOR3I": 9,
    "PT_COLOR": 11,
    "PT_RECT2": 12,
    "PT_OBJECT": 13,
    "PT_STRING_NAME": 15,
    "PT_STRING": 16,
    "PT_NODE_PATH": 17,
    "PT_BASIS": 18,
    "PT_TRANSFORM3D": 19,
    "PT_QUATERNION": 20,
    "PT_AABB": 21,
    "PT_TRANSFORM2D": 22,
    "PT_RID": 14,
    # 23/24 are the Packed*Array build tags (KanamaIosPackedArgDesc path, not in this scalar
    # dict). PT_PROJECTION is appended at 25 to match the C enum's end (no renumbering).
    "PT_PROJECTION": 25,
    # 26 is PT_PLANE (typed-array element selector, not an arg/ret tag). PT_CALLABLE is the
    # object+method Callable arg (KanamaIosCallableArgDesc path); value matches the C enum.
    "PT_CALLABLE": 27,
    # Rect2i return (4x int32 POD passthrough; the generic dispatch hands ret_out to the
    # engine untyped, so the tag is documentation + self-test selector). Appended at the
    # C enum's end (37) — never renumber existing tags.
    "PT_RECT2I": 37,
}


def ios_arg_layout(kind: str, index: int) -> tuple[str, str, list[str], str]:
    """(kotlin_param_type, pt_tag, cell_decl_lines, arg_ptr_expr) for one arg.

    `arg_ptr_expr` is what gets stored into the ptrs[] cell (a COpaquePointer).
    """
    a = f"a{index}"
    c = f"c{index}"
    if kind == "bool":
        return ("Boolean", "PT_BOOL", [f"val {c} = alloc<ByteVar>(); {c}.value = if ({a}) 1 else 0"], f"{c}.ptr.reinterpret<CPointed>()")
    if kind == "int32":
        return ("Int", "PT_INT64", [f"val {c} = alloc<LongVar>(); {c}.value = {a}.toLong()"], f"{c}.ptr.reinterpret<CPointed>()")
    if kind in {"int64", "uint32", "enum", "bitfield"}:
        return ("Long", "PT_INT64", [f"val {c} = alloc<LongVar>(); {c}.value = {a}"], f"{c}.ptr.reinterpret<CPointed>()")
    if kind == "float":
        return ("Double", "PT_FLOAT64", [f"val {c} = alloc<DoubleVar>(); {c}.value = {a}"], f"{c}.ptr.reinterpret<CPointed>()")
    if kind == "Object":
        return ("MemorySegment", "PT_OBJECT", [f"val {c} = alloc<LongVar>(); {c}.value = {a}.address()"], f"{c}.ptr.reinterpret<CPointed>()")
    if kind == "Vector2":
        return (
            "Vector2",
            "PT_VECTOR2",
            [f"val {c} = allocArray<GodotRealVar>(2); {c}[0] = GodotReal.toC({a}.x); {c}[1] = GodotReal.toC({a}.y)"],
            f"{c}.reinterpret<CPointed>()",
        )
    if kind == "Vector2i":
        # int32 components — NOT widened (PtrToArgDirect<Vector2i> lays 2×int32 = 8B)
        return (
            "Vector2i",
            "PT_VECTOR2I",
            [f"val {c} = allocArray<IntVar>(2); {c}[0] = {a}.x; {c}[1] = {a}.y"],
            f"{c}.reinterpret<CPointed>()",
        )
    if kind == "Vector3":
        return (
            "Vector3",
            "PT_VECTOR3",
            [f"val {c} = allocArray<GodotRealVar>(3); {c}[0] = GodotReal.toC({a}.x); {c}[1] = GodotReal.toC({a}.y); {c}[2] = GodotReal.toC({a}.z)"],
            f"{c}.reinterpret<CPointed>()",
        )
    if kind == "Vector3i":
        # int32 components — NOT widened (PtrToArgDirect<Vector3i> lays 3×int32 = 12B)
        return (
            "Vector3i",
            "PT_VECTOR3I",
            [f"val {c} = allocArray<IntVar>(3); {c}[0] = {a}.x; {c}[1] = {a}.y; {c}[2] = {a}.z"],
            f"{c}.reinterpret<CPointed>()",
        )
    if kind == "Color":
        # 4x float32 = 16 bytes. Color components are always float32 (never real_t).
        return (
            "Color",
            "PT_COLOR",
            [f"val {c} = allocArray<FloatVar>(4); {c}[0] = {a}.r; {c}[1] = {a}.g; {c}[2] = {a}.b; {c}[3] = {a}.a"],
            f"{c}.reinterpret<CPointed>()",
        )
    if kind == "Rect2":
        # 4x real_t (position.x, position.y, size.x, size.y).
        return (
            "Rect2",
            "PT_RECT2",
            [f"val {c} = allocArray<GodotRealVar>(4); {c}[0] = GodotReal.toC({a}.position.x); {c}[1] = GodotReal.toC({a}.position.y); {c}[2] = GodotReal.toC({a}.size.x); {c}[3] = GodotReal.toC({a}.size.y)"],
            f"{c}.reinterpret<CPointed>()",
        )
    if kind == "StringName":
        # CONSTRUCT tag: the dispatch builds a StringName from this C string.
        return ("String", "PT_STRING_NAME", [], f"{a}.cstr.ptr.reinterpret<CPointed>()")
    if kind == "String":
        # CONSTRUCT tag: the dispatch builds a String from this C string.
        return ("String", "PT_STRING", [], f"{a}.cstr.ptr.reinterpret<CPointed>()")
    if kind == "NodePath":
        # CONSTRUCT tag: the dispatch builds a NodePath from the path C string.
        return ("NodePath", "PT_NODE_PATH", [], f"{a}.path.cstr.ptr.reinterpret<CPointed>()")
    if kind == "Basis":
        # 9x real_t, column-major: [x.x, y.x, z.x, x.y, y.y, z.y, x.z, y.z, z.z].
        return (
            "Basis",
            "PT_BASIS",
            [
                f"val {c} = allocArray<GodotRealVar>(9); "
                f"{c}[0] = GodotReal.toC({a}.x.x); {c}[1] = GodotReal.toC({a}.y.x); {c}[2] = GodotReal.toC({a}.z.x); "
                f"{c}[3] = GodotReal.toC({a}.x.y); {c}[4] = GodotReal.toC({a}.y.y); {c}[5] = GodotReal.toC({a}.z.y); "
                f"{c}[6] = GodotReal.toC({a}.x.z); {c}[7] = GodotReal.toC({a}.y.z); {c}[8] = GodotReal.toC({a}.z.z)"
            ],
            f"{c}.reinterpret<CPointed>()",
        )
    if kind == "Transform2D":
        # 6x real_t: the three columns (x axis, y axis, origin), each a Vector2.
        return (
            "Transform2D",
            "PT_TRANSFORM2D",
            [
                f"val {c} = allocArray<GodotRealVar>(6); "
                f"{c}[0] = GodotReal.toC({a}.x.x); {c}[1] = GodotReal.toC({a}.x.y); "
                f"{c}[2] = GodotReal.toC({a}.y.x); {c}[3] = GodotReal.toC({a}.y.y); "
                f"{c}[4] = GodotReal.toC({a}.origin.x); {c}[5] = GodotReal.toC({a}.origin.y)"
            ],
            f"{c}.reinterpret<CPointed>()",
        )
    if kind == "Transform3D":
        # 12x real_t: 9 column-major basis components then the 3 origin components.
        return (
            "Transform3D",
            "PT_TRANSFORM3D",
            [
                f"val {c} = allocArray<GodotRealVar>(12); "
                f"{c}[0] = GodotReal.toC({a}.basis.x.x); {c}[1] = GodotReal.toC({a}.basis.y.x); {c}[2] = GodotReal.toC({a}.basis.z.x); "
                f"{c}[3] = GodotReal.toC({a}.basis.x.y); {c}[4] = GodotReal.toC({a}.basis.y.y); {c}[5] = GodotReal.toC({a}.basis.z.y); "
                f"{c}[6] = GodotReal.toC({a}.basis.x.z); {c}[7] = GodotReal.toC({a}.basis.y.z); {c}[8] = GodotReal.toC({a}.basis.z.z); "
                f"{c}[9] = GodotReal.toC({a}.origin.x); {c}[10] = GodotReal.toC({a}.origin.y); {c}[11] = GodotReal.toC({a}.origin.z)"
            ],
            f"{c}.reinterpret<CPointed>()",
        )
    if kind == "RID":
        # POD passthrough: a Godot RID is a single uint64 (8 bytes).
        return ("RID", "PT_RID", [f"val {c} = alloc<LongVar>(); {c}.value = {a}.value"], f"{c}.ptr.reinterpret<CPointed>()")
    if kind == "Quaternion":
        # 4x real_t [x, y, z, w] — POD passthrough.
        return (
            "Quaternion",
            "PT_QUATERNION",
            [f"val {c} = allocArray<GodotRealVar>(4); {c}[0] = GodotReal.toC({a}.x); {c}[1] = GodotReal.toC({a}.y); {c}[2] = GodotReal.toC({a}.z); {c}[3] = GodotReal.toC({a}.w)"],
            f"{c}.reinterpret<CPointed>()",
        )
    if kind == "AABB":
        # 6x real_t: position xyz then size xyz — POD passthrough.
        return (
            "AABB",
            "PT_AABB",
            [
                f"val {c} = allocArray<GodotRealVar>(6); "
                f"{c}[0] = GodotReal.toC({a}.position.x); {c}[1] = GodotReal.toC({a}.position.y); {c}[2] = GodotReal.toC({a}.position.z); "
                f"{c}[3] = GodotReal.toC({a}.size.x); {c}[4] = GodotReal.toC({a}.size.y); {c}[5] = GodotReal.toC({a}.size.z)"
            ],
            f"{c}.reinterpret<CPointed>()",
        )
    # Callable is handled directly in render_ios_helper: a Callable arg is expanded at the wrapper
    # call site into (target.handle, method) — a (MemorySegment, String) pair — matching the desktop
    # helper contract, so it maps to TWO helper params, not one. See render_ios_helper.
    raise ValueError(f"iOS arg kind not audited: {kind}")


def ios_ret_layout(kotlin_return: str) -> tuple[str | None, str, list[str], str, str | None]:
    """(kotlin_ret_type|None, pt_tag, ret_decl_lines, ret_ptr_expr, read_expr|None)."""
    if kotlin_return == "Unit":
        return (None, "PT_VOID", [], "null", None)
    if kotlin_return == "Boolean":
        return ("Boolean", "PT_BOOL", ["val ret = alloc<ByteVar>()"], "ret.ptr", "ret.value.toInt() != 0")
    if kotlin_return == "Int":
        return ("Int", "PT_INT64", ["val ret = alloc<LongVar>()"], "ret.ptr", "ret.value.toInt()")
    if kotlin_return == "Long":
        return ("Long", "PT_INT64", ["val ret = alloc<LongVar>()"], "ret.ptr", "ret.value")
    if kotlin_return == "Double":
        return ("Double", "PT_FLOAT64", ["val ret = alloc<DoubleVar>()"], "ret.ptr", "ret.value")
    if kotlin_return == "Vector2":
        return ("Vector2", "PT_VECTOR2", ["val ret = allocArray<GodotRealVar>(2)"], "ret", "Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]))")
    if kotlin_return == "Vector2i":
        return ("Vector2i", "PT_VECTOR2I", ["val ret = allocArray<IntVar>(2)"], "ret", "Vector2i(ret[0], ret[1])")
    if kotlin_return == "Vector3":
        return (
            "Vector3",
            "PT_VECTOR3",
            ["val ret = allocArray<GodotRealVar>(3)"],
            "ret",
            "Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]))",
        )
    if kotlin_return == "Vector3i":
        return ("Vector3i", "PT_VECTOR3I", ["val ret = allocArray<IntVar>(3)"], "ret", "Vector3i(ret[0], ret[1], ret[2])")
    if kotlin_return == "Color":
        # 4x float32 = 16 bytes. Color components are always float32 (never real_t).
        return ("Color", "PT_COLOR", ["val ret = allocArray<FloatVar>(4)"], "ret", "Color(ret[0], ret[1], ret[2], ret[3])")
    if kotlin_return == "Rect2":
        # 4x float32 = 16 bytes (position.x, position.y, size.x, size.y).
        return (
            "Rect2",
            "PT_RECT2",
            ["val ret = allocArray<GodotRealVar>(4)"],
            "ret",
            "Rect2(Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1])), "
            "Vector2(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3])))",
        )
    if kotlin_return == "Rect2i":
        # 4x int32 = 16 bytes (position.x, position.y, size.x, size.y) — the int twin of
        # Rect2, always int32 (never real_t). Unlocks DisplayServer.get_display_safe_area
        # and the other Rect2i getters on iOS (task 26 safe-area work).
        return (
            "Rect2i",
            "PT_RECT2I",
            ["val ret = allocArray<IntVar>(4)"],
            "ret",
            "Rect2i(Vector2i(ret[0], ret[1]), Vector2i(ret[2], ret[3]))",
        )
    if kotlin_return == "Basis":
        # 9x real_t, column-major (see ios_arg_layout). Reassemble the column axes.
        return (
            "Basis",
            "PT_BASIS",
            ["val ret = allocArray<GodotRealVar>(9)"],
            "ret",
            "Basis(Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[3]), GodotReal.fromC(ret[6])), "
            "Vector3(GodotReal.fromC(ret[1]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[7])), "
            "Vector3(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[5]), GodotReal.fromC(ret[8])))",
        )
    if kotlin_return == "Transform2D":
        # 6x real_t: the three columns (x axis, y axis, origin), each a Vector2.
        return (
            "Transform2D",
            "PT_TRANSFORM2D",
            ["val ret = allocArray<GodotRealVar>(6)"],
            "ret",
            "Transform2D(Vector2(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1])), "
            "Vector2(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3])), "
            "Vector2(GodotReal.fromC(ret[4]), GodotReal.fromC(ret[5])))",
        )
    if kotlin_return == "Transform3D":
        # 12x real_t: 9 column-major basis + 3 origin.
        return (
            "Transform3D",
            "PT_TRANSFORM3D",
            ["val ret = allocArray<GodotRealVar>(12)"],
            "ret",
            "Transform3D(Basis(Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[3]), GodotReal.fromC(ret[6])), "
            "Vector3(GodotReal.fromC(ret[1]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[7])), "
            "Vector3(GodotReal.fromC(ret[2]), GodotReal.fromC(ret[5]), GodotReal.fromC(ret[8]))), "
            "Vector3(GodotReal.fromC(ret[9]), GodotReal.fromC(ret[10]), GodotReal.fromC(ret[11])))",
        )
    if kotlin_return == "Projection":
        # 16x float32, column-major: 4 Vector4 columns (x, y, z, w).
        return (
            "Projection",
            "PT_PROJECTION",
            ["val ret = allocArray<GodotRealVar>(16)"],
            "ret",
            "Projection(Vector4(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3])), "
            "Vector4(GodotReal.fromC(ret[4]), GodotReal.fromC(ret[5]), GodotReal.fromC(ret[6]), GodotReal.fromC(ret[7])), "
            "Vector4(GodotReal.fromC(ret[8]), GodotReal.fromC(ret[9]), GodotReal.fromC(ret[10]), GodotReal.fromC(ret[11])), "
            "Vector4(GodotReal.fromC(ret[12]), GodotReal.fromC(ret[13]), GodotReal.fromC(ret[14]), GodotReal.fromC(ret[15])))",
        )
    if kotlin_return == "RID":
        return ("RID", "PT_RID", ["val ret = alloc<LongVar>(); ret.value = 0"], "ret.ptr", "RID(ret.value)")
    if kotlin_return == "Quaternion":
        return (
            "Quaternion",
            "PT_QUATERNION",
            ["val ret = allocArray<GodotRealVar>(4)"],
            "ret",
            "Quaternion(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2]), GodotReal.fromC(ret[3]))",
        )
    if kotlin_return == "AABB":
        return (
            "AABB",
            "PT_AABB",
            ["val ret = allocArray<GodotRealVar>(6)"],
            "ret",
            "AABB(Vector3(GodotReal.fromC(ret[0]), GodotReal.fromC(ret[1]), GodotReal.fromC(ret[2])), "
            "Vector3(GodotReal.fromC(ret[3]), GodotReal.fromC(ret[4]), GodotReal.fromC(ret[5])))",
        )
    if kotlin_return == "MemorySegment":
        return ("MemorySegment", "PT_OBJECT", ["val ret = alloc<LongVar>(); ret.value = 0"], "ret.ptr", "MemorySegment.ofAddress(ret.value)")
    raise ValueError(f"iOS return kind not audited: {kotlin_return}")


def render_ios_helper(function: str, logical_args: tuple[str, ...], kotlin_return: str, tags_used: set[str]) -> str:
    ret_type, ret_tag, ret_decl, ret_ptr, read_expr = ios_ret_layout(kotlin_return)
    tags_used.add(ret_tag)
    params = ["methodBind: MemorySegment", "instance: MemorySegment"]
    arg_tags: list[str] = []
    arg_ptr_exprs: list[str] = []
    cell_decls: list[str] = []
    for i, kind in enumerate(logical_args):
        if kind == "Callable":
            # The wrapper call site expands a GodotCallable into (target.handle, method) — a
            # (MemorySegment, String) pair (call_argument_expressions, same as desktop). Build a
            # KanamaIosCallableArgDesc from that pair; the PT_CALLABLE ptrcall dispatch constructs the
            # object+method Callable (constructor index 2) and destroys it after the call.
            tags_used.add("PT_CALLABLE")
            params.append(f"a{i}Object: MemorySegment")
            params.append(f"a{i}Method: String")
            cell_decls.append(
                f"val c{i} = alloc<KanamaIosCallableArgDesc>(); "
                f"c{i}.object_handle = a{i}Object.address(); "
                f"c{i}.method = a{i}Method.cstr.ptr"
            )
            arg_tags.append("PT_CALLABLE")
            arg_ptr_exprs.append(f"c{i}.ptr.reinterpret<CPointed>()")
            continue
        param_type, tag, decls, ptr_expr = ios_arg_layout(kind, i)
        tags_used.add(tag)
        params.append(f"a{i}: {param_type}")
        arg_tags.append(tag)
        arg_ptr_exprs.append(ptr_expr)
        cell_decls.extend(decls)
    n = len(logical_args)

    body: list[str] = list(ret_decl)
    body.extend(cell_decls)
    if n > 0:
        types_inits = "; ".join(f"types[{i}] = {tag}" for i, tag in enumerate(arg_tags))
        ptrs_inits = "; ".join(f"ptrs[{i}] = {expr}" for i, expr in enumerate(arg_ptr_exprs))
        body.append(f"val types = allocArray<IntVar>({n}); {types_inits}")
        body.append(f"val ptrs = allocArray<COpaquePointerVar>({n}); {ptrs_inits}")
        types_arg, ptrs_arg = "types", "ptrs"
    else:
        types_arg, ptrs_arg = "null", "null"
    body.append(
        f"kanama_ios_godot_ptrcall(methodBind.address(), instance.address(), "
        f"{types_arg}, {ptrs_arg}, {n}, {ret_tag}, {ret_ptr})"
    )
    body.append("Unit" if read_expr is None else read_expr)

    signature_ret = "" if ret_type is None else f": {ret_type}"
    indented_body = "\n".join(f"        {line}" for line in body)
    return (
        f"fun ObjectCalls.{function}({', '.join(params)}){signature_ret} =\n"
        f"    memScoped {{\n{indented_body}\n    }}"
    )


def collect_ios_shapes(
    classes: list[ApiClass],
    object_types: set[str],
    wrapper_classes: set[str],
    api_classes: dict[str, ApiClass],
    api_dir: Path,
) -> dict[str, tuple[tuple[str, ...], str]]:
    """shape.function -> (logical_args, kotlin_return) for the emitted iOS wrappers.

    Relies on IOS_AUDIT_ONLY being set: `unsupported_reason` then guarantees every
    surviving method is iOS-marshalable, so the collected shapes are too. Hand-written
    override helpers are excluded.
    """
    registry: dict[str, tuple[tuple[str, ...], str]] = {}
    for cls in classes:
        for method_list in cls.methods.values():
            for method in method_list:
                if method.is_vararg:
                    # Supported varargs render through the hand-written
                    # callWithVariantArgs path, never a generated ptrcall helper.
                    continue
                if unsupported_reason(cls.name, method, object_types, wrapper_classes, api_classes, api_dir) is not None:
                    continue
                shape = candidate_for(method, object_types, cls.name)
                if shape is None or shape.function in IOS_HANDWRITTEN_HELPERS:
                    continue
                registry.setdefault(shape.function, (method.logical_arg_kinds(object_types), shape.kotlin_return))
    return registry


def render_ios_objectcalls(registry: dict[str, tuple[tuple[str, ...], str]]) -> str:
    tags_used: set[str] = set()
    helpers = [
        render_ios_helper(function, logical_args, kotlin_return, tags_used)
        for function, (logical_args, kotlin_return) in sorted(registry.items())
    ]
    const_lines = [
        f"private const val {tag} = {IOS_PT_TAG_VALUES[tag]}"
        for tag in sorted(tags_used, key=lambda name: IOS_PT_TAG_VALUES[name])
    ]
    sections = [IOS_OBJECTCALLS_HEADER.rstrip("\n")]
    if const_lines:
        sections.append("\n".join(const_lines))
    if helpers:
        sections.append("\n\n".join(helpers))
    else:
        sections.append("// No conservative iOS helper shapes emitted.")
    return "\n\n".join(sections) + "\n"


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--api", type=Path, default=Path("extension_api.json"))
    parser.add_argument("--api-dir", type=Path, default=Path("src/main/kotlin/net/multigesture/kanama/api"))
    parser.add_argument("--class", dest="classes", action="append", default=[], help="Class to generate; repeatable.")
    parser.add_argument(
        "--class-list-file",
        type=Path,
        help="File with one class name per line, appended to --class (Windows-safe batch "
             "form: a full-union --class command line exceeds the 32K CreateProcess limit).",
    )
    parser.add_argument(
        "--emit-class",
        dest="emit_classes",
        action="append",
        default=[],
        help="Class to write into --api-dir as an adopted generated source; repeatable.",
    )
    parser.add_argument("--allow-overwrite", action="store_true", help="Allow --emit-class to replace an existing source file.")
    parser.add_argument("--output-dir", type=Path, help="Write drafts to this directory. Defaults to stdout.")
    parser.add_argument("--skip-report", type=Path, help="Write unsupported method reasons to this file.")
    parser.add_argument(
        "--ios-emit-class",
        dest="ios_classes",
        action="append",
        default=[],
        help="Class to render as an iOS wrapper (conservative iOS guardrail); repeatable.",
    )
    parser.add_argument(
        "--ios-class-list-file",
        type=Path,
        help="File with one class name per line, appended to --ios-emit-class (Windows-safe "
             "batch form; the iOS emit union must stay a single invocation — see the "
             "Generator Gotcha — so it cannot be chunked instead).",
    )
    parser.add_argument(
        "--ios-output-dir",
        type=Path,
        help="Write iOS wrapper drafts here (staging; do not point at the compiled facade). Defaults to stdout.",
    )
    parser.add_argument(
        "--ios-objectcalls",
        type=Path,
        help="Write the generated iOS ObjectCalls helper bodies for the --ios-emit-class shape set to this file.",
    )
    parser.add_argument("--ios-skip-report", type=Path, help="Write iOS unsupported method reasons to this file.")
    args = parser.parse_args()
    if args.class_list_file is not None:
        args.classes += args.class_list_file.read_text(encoding="utf-8").split()
    if args.ios_class_list_file is not None:
        args.ios_classes += args.ios_class_list_file.read_text(encoding="utf-8").split()
    if not args.classes and not args.emit_classes and not args.ios_classes:
        parser.error("at least one --class, --emit-class, or --ios-emit-class is required")

    api_classes = load_api_classes(args.api)
    singleton_names = load_api_singletons(args.api)
    object_types = object_type_names(api_classes)
    wrapper_classes = scan_wrapper_classes(args.api_dir)
    skip_lines: list[str] = []

    for class_name in args.classes:
        cls = api_classes.get(class_name)
        if cls is None:
            raise SystemExit(f"{class_name}: not found in {args.api}")
        content, skips = render_draft(cls, object_types, wrapper_classes, api_classes, args.api_dir, singleton_names)
        skip_lines.extend(f"{class_name}: {skip}" for skip in skips)
        if args.output_dir:
            args.output_dir.mkdir(parents=True, exist_ok=True)
            (args.output_dir / f"{class_name}.kt").write_text(content, encoding="utf-8")
        else:
            print(content)

    for class_name in args.emit_classes:
        cls = api_classes.get(class_name)
        if cls is None:
            raise SystemExit(f"{class_name}: not found in {args.api}")
        content, skips = render_draft(cls, object_types, wrapper_classes, api_classes, args.api_dir, singleton_names)
        skip_lines.extend(f"{class_name}: {skip}" for skip in skips)
        target = args.api_dir / f"{class_name}.kt"
        if target.exists() and not args.allow_overwrite and target.read_text(encoding="utf-8") != content:
            raise SystemExit(f"{target}: exists and differs; pass --allow-overwrite to replace it")
        target.parent.mkdir(parents=True, exist_ok=True)
        target.write_text(content, encoding="utf-8")

    if args.skip_report:
        args.skip_report.parent.mkdir(parents=True, exist_ok=True)
        args.skip_report.write_text("\n".join(skip_lines) + ("\n" if skip_lines else ""), encoding="utf-8")
    elif skip_lines:
        print("// Unsupported methods:")
        for line in skip_lines:
            print(f"// - {line}")

    if args.ios_classes:
        global IOS_AUDIT_ONLY, IOS_EMIT_CLASSES
        IOS_AUDIT_ONLY = True
        # Explicit class-collision handling (task 11): a class that is deliberately hand-written on
        # iOS must not also be generated, or the two declarations collide at compile time. Log the
        # collision and drop the request instead of silently emitting a clashing file.
        requested = list(dict.fromkeys(args.ios_classes))
        emit_names: list[str] = []
        for class_name in requested:
            reason = IOS_HANDWRITTEN_COLLISION_CLASSES.get(class_name)
            if reason is not None:
                print(
                    f"[generate_api_wrapper] collision: {class_name} is hand-written on iOS "
                    f"({reason}); skipping generation.",
                    file=sys.stderr,
                )
                continue
            unsupported = IOS_UNSUPPORTED_CLASSES.get(class_name)
            if unsupported is not None:
                print(
                    f"[generate_api_wrapper] unsupported: {class_name} cannot compile on iOS "
                    f"({unsupported}); skipping generation.",
                    file=sys.stderr,
                )
                continue
            emit_names.append(class_name)
        IOS_EMIT_CLASSES = set(emit_names)
        ios_skip_lines: list[str] = []
        ios_classes: list[ApiClass] = []
        for class_name in emit_names:
            cls = api_classes.get(class_name)
            if cls is None:
                raise SystemExit(f"{class_name}: not found in {args.api}")
            ios_classes.append(cls)
            content, skips = render_draft(cls, object_types, wrapper_classes, api_classes, args.api_dir, singleton_names)
            # The generated wrappers are byte-identical to desktop except the
            # ObjectCalls import must also pull in the generated extension helpers.
            content = content.replace(
                "import net.multigesture.kanama.binding.runtime.ObjectCalls\n",
                "import net.multigesture.kanama.binding.runtime.ObjectCalls\n"
                "import net.multigesture.kanama.binding.runtime.*\n",
            )
            ios_skip_lines.extend(f"{class_name}: {skip}" for skip in skips)
            if args.ios_output_dir:
                args.ios_output_dir.mkdir(parents=True, exist_ok=True)
                (args.ios_output_dir / f"{class_name}.kt").write_text(content, encoding="utf-8")
            else:
                print(content)

        if args.ios_objectcalls:
            registry = collect_ios_shapes(ios_classes, object_types, wrapper_classes, api_classes, args.api_dir)
            args.ios_objectcalls.parent.mkdir(parents=True, exist_ok=True)
            args.ios_objectcalls.write_text(render_ios_objectcalls(registry), encoding="utf-8")

        if args.ios_skip_report:
            args.ios_skip_report.parent.mkdir(parents=True, exist_ok=True)
            args.ios_skip_report.write_text("\n".join(ios_skip_lines) + ("\n" if ios_skip_lines else ""), encoding="utf-8")

    return 0


if __name__ == "__main__":
    raise SystemExit(main())
