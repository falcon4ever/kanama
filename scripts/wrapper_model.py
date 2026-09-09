#!/usr/bin/env python3
"""Shared metadata helpers for Kanama API wrapper tooling."""

from __future__ import annotations

import json
import re
from dataclasses import dataclass
from pathlib import Path


METHOD_BIND_RE = re.compile(
    r"ObjectCalls\.getMethodBind\(\s*"
    r'"([^"]+)"\s*,\s*"([^"]+)"\s*,\s*([A-Z0-9_]+|\d+L?)',
    re.DOTALL,
)

WRAPPER_CLASS_ALIASES = {
    "GodotObject": "Object",
}

POLICY_WRAPPED_METHODS = {
    # GodotObject exposes these root Object operations through higher-level
    # runtime policies instead of direct Object MethodBind calls.
    "GodotObject": {"emit_signal", "get", "set"},
}


@dataclass(frozen=True)
class ApiMethod:
    name: str
    hash: int
    return_type: str
    return_meta: str
    argument_types: tuple[str, ...]
    argument_metas: tuple[str, ...]
    argument_names: tuple[str, ...]
    argument_defaults: tuple[str | None, ...]
    is_static: bool
    is_const: bool
    is_vararg: bool
    is_virtual: bool = False

    @property
    def signature(self) -> str:
        args = ", ".join(
            f"{arg_type}[{meta}]" if meta else arg_type
            for arg_type, meta in zip(self.argument_types, self.argument_metas, strict=True)
        )
        return_type = f"{self.return_type}[{self.return_meta}]" if self.return_meta else self.return_type
        return f"{return_type} {self.name}({args})"

    @property
    def abi_return_kind(self) -> str:
        return abi_kind(self.return_type, self.return_meta)

    @property
    def abi_arg_kinds(self) -> tuple[str, ...]:
        return tuple(
            abi_kind(arg_type, meta)
            for arg_type, meta in zip(self.argument_types, self.argument_metas, strict=True)
        )

    # `int32`-meta returns map to Kotlin `Int` and read exactly 4 bytes (`...RetInt`). Do NOT widen
    # them to `Long`/`...RetLong`: on the ptrcall return ABI an `int32` return writes only 4 bytes, so
    # an 8-byte read pulls 4 bytes of return-scratch. That is benign on desktop (JVM arenas are
    # zero-filled) but not guaranteed on the mobile ptrcall scratch, and both the signature and exact
    # ABI-policy audits reject it. A prior widening of `get_collision_count` -> Long (task 21) tripped
    # those audits and the fresh-clone pre-release gate; it was reverted for 0.3.0 to keep the return
    # width exact. (Any future "widen all int32 returns to Long" idea needs zero-extending helpers first,
    # audited per platform — it is not ABI-safe as a bare cast.)
    _INT32_RETURN_WIDEN_TO_LONG: frozenset[str] = frozenset()

    def logical_return_kind(self, object_types: set[str]) -> str:
        kind = logical_type(self.return_type, self.return_meta, object_types)
        if kind == "int32" and self.name in self._INT32_RETURN_WIDEN_TO_LONG:
            return "int64"
        return kind

    def logical_arg_kinds(self, object_types: set[str]) -> tuple[str, ...]:
        return tuple(
            logical_type(arg_type, meta, object_types)
            for arg_type, meta in zip(self.argument_types, self.argument_metas, strict=True)
        )

    def to_json(self) -> dict[str, object]:
        return {
            "name": self.name,
            "hash": self.hash,
            "return_type": self.return_type,
            "return_meta": self.return_meta,
            "argument_types": list(self.argument_types),
            "argument_metas": list(self.argument_metas),
            "argument_names": list(self.argument_names),
            "argument_defaults": list(self.argument_defaults),
            "is_static": self.is_static,
            "is_const": self.is_const,
            "is_vararg": self.is_vararg,
            "is_virtual": self.is_virtual,
            "signature": self.signature,
        }


@dataclass(frozen=True)
class ApiClass:
    name: str
    inherits: str
    methods: dict[str, list[ApiMethod]]
    properties: tuple[dict[str, object], ...]
    signals: tuple[dict[str, object], ...]
    constants: tuple[dict[str, object], ...]
    enums: tuple[dict[str, object], ...]


@dataclass(frozen=True)
class ApiValuePolicy:
    """Exact ABI policy for one extension_api.json value slot.

    This is intentionally stricter than the older `logical_type()` /
    `abi_kind()` helpers. Those helpers group signatures into generator helper
    families; this policy keeps the raw API type and metadata visible so audits
    can catch width drift such as `uint32` being treated as a generic `int64`.
    """

    api_type: str
    meta: str
    exact_kind: str
    storage_kind: str
    public_kotlin_type: str
    helper_token: str
    variant_type: str
    nullable: bool = False
    ownership: str = "value"

    @property
    def is_unsigned_integer(self) -> bool:
        return self.exact_kind in {"uint8", "uint16", "uint32", "uint64"}

    @property
    def is_signed_integer(self) -> bool:
        return self.exact_kind in {"int8", "int16", "int32", "int64", "char32"}


SIGNED_INT_POLICIES = {
    "int8": ("int8", "int32", "Int", "Int", "INT"),
    "int16": ("int16", "int32", "Int", "Int", "INT"),
    "int32": ("int32", "int32", "Int", "Int", "INT"),
    "char32": ("char32", "int32", "Int", "Int", "INT"),
    "int64": ("int64", "int64", "Long", "Long", "INT"),
    "": ("int64", "int64", "Long", "Long", "INT"),
}

UNSIGNED_INT_POLICIES = {
    "uint8": ("uint8", "int32", "Int", "UInt8", "INT"),
    "uint16": ("uint16", "int32", "Int", "UInt16", "INT"),
    "uint32": ("uint32", "uint32", "Long", "UInt32", "INT"),
    "uint64": ("uint64", "int64", "Long", "UInt64", "INT"),
}

FLOAT_POLICIES = {
    "float": ("float32_scalar", "float64_scalar", "Double", "Double", "FLOAT"),
    "double": ("float64_scalar", "float64_scalar", "Double", "Double", "FLOAT"),
    "": ("float64_scalar", "float64_scalar", "Double", "Double", "FLOAT"),
}

PACKED_ARRAY_POLICIES = {
    "PackedByteArray": ("packed_byte_array", "ByteArray", "ByteArray", "PACKED_BYTE_ARRAY"),
    "PackedStringArray": ("packed_string_array", "List<String>", "PackedStringList", "PACKED_STRING_ARRAY"),
    "PackedInt32Array": ("packed_int32_array", "List<Int>", "PackedInt32List", "PACKED_INT32_ARRAY"),
    "PackedInt64Array": ("packed_int64_array", "List<Long>", "PackedInt64List", "PACKED_INT64_ARRAY"),
    "PackedFloat32Array": ("packed_float32_array", "List<Float>", "PackedFloat32List", "PACKED_FLOAT32_ARRAY"),
    "PackedFloat64Array": ("packed_float64_array", "List<Double>", "PackedFloat64List", "PACKED_FLOAT64_ARRAY"),
    "PackedVector2Array": ("packed_vector2_array", "List<Vector2>", "PackedVector2List", "PACKED_VECTOR2_ARRAY"),
    "PackedVector3Array": ("packed_vector3_array", "List<Vector3>", "PackedVector3List", "PACKED_VECTOR3_ARRAY"),
    "PackedColorArray": ("packed_color_array", "List<Color>", "PackedColorList", "PACKED_COLOR_ARRAY"),
    "PackedVector4Array": ("packed_vector4_array", "List<Vector4>", "PackedVector4List", "PACKED_VECTOR4_ARRAY"),
}

RAW_POINTER_POLICIES = {
    "const void*": ("const_void_pointer", "ConstVoidPtr"),
    "const GDExtensionInitializationFunction*": (
        "const_gdextension_initialization_function_pointer",
        "ConstGDExtensionInitializationFunctionPtr",
    ),
}

BUILTIN_POLICIES = {
    "bool": ("bool", "Boolean", "Bool", "BOOL"),
    "String": ("string", "String", "String", "STRING"),
    "StringName": ("string_name", "String", "StringName", "STRING_NAME"),
    "NodePath": ("node_path", "NodePath", "NodePath", "NODE_PATH"),
    "RID": ("rid", "RID", "RID", "RID"),
    "Vector2": ("vector2", "Vector2", "Vector2", "VECTOR2"),
    "Vector2i": ("vector2i", "Vector2i", "Vector2i", "VECTOR2I"),
    "Vector3": ("vector3", "Vector3", "Vector3", "VECTOR3"),
    "Vector3i": ("vector3i", "Vector3i", "Vector3i", "VECTOR3I"),
    "Vector4": ("vector4", "Vector4", "Vector4", "VECTOR4"),
    "Vector4i": ("vector4i", "Vector4i", "Vector4i", "VECTOR4I"),
    "Rect2": ("rect2", "Rect2", "Rect2", "RECT2"),
    "Rect2i": ("rect2i", "Rect2i", "Rect2i", "RECT2I"),
    "Color": ("color", "Color", "Color", "COLOR"),
    "Quaternion": ("quaternion", "Quaternion", "Quaternion", "QUATERNION"),
    "Basis": ("basis", "Basis", "Basis", "BASIS"),
    "Transform2D": ("transform2d", "Transform2D", "Transform2D", "TRANSFORM2D"),
    "Transform3D": ("transform3d", "Transform3D", "Transform3D", "TRANSFORM3D"),
    "Projection": ("projection", "Projection", "Projection", "PROJECTION"),
    "AABB": ("aabb", "AABB", "AABB", "AABB"),
    "Plane": ("plane", "Plane", "Plane", "PLANE"),
}


def load_api_classes(path: Path) -> dict[str, ApiClass]:
    data = json.loads(path.read_text(encoding="utf-8"))
    classes: dict[str, ApiClass] = {}
    for cls in data.get("classes", []):
        class_name = cls.get("name")
        if not class_name:
            continue
        methods: dict[str, list[ApiMethod]] = {}
        for method in cls.get("methods", []):
            name = method.get("name")
            method_hash = method.get("hash")
            if not name or method_hash is None:
                continue
            return_value = method.get("return_value", {})
            arguments = method.get("arguments") or []
            methods.setdefault(name, []).append(
                ApiMethod(
                    name=name,
                    hash=int(method_hash),
                    return_type=return_value.get("type", "void"),
                    return_meta=return_value.get("meta", ""),
                    argument_types=tuple(arg.get("type", "Variant") for arg in arguments),
                    argument_metas=tuple(arg.get("meta", "") for arg in arguments),
                    argument_names=tuple(arg.get("name", f"arg{index + 1}") for index, arg in enumerate(arguments)),
                    argument_defaults=tuple(
                        str(arg["default_value"]) if "default_value" in arg else None
                        for arg in arguments
                    ),
                    is_static=bool(method.get("is_static", False)),
                    is_const=bool(method.get("is_const", False)),
                    is_vararg=bool(method.get("is_vararg", False)),
                    is_virtual=bool(method.get("is_virtual", False)),
                ),
            )
        classes[class_name] = ApiClass(
            name=class_name,
            inherits=cls.get("inherits") or "",
            methods=methods,
            properties=tuple(cls.get("properties") or ()),
            signals=tuple(cls.get("signals") or ()),
            constants=tuple(cls.get("constants") or ()),
            enums=tuple(cls.get("enums") or ()),
        )
    return classes


def load_api_singletons(path: Path) -> set[str]:
    data = json.loads(path.read_text(encoding="utf-8"))
    return {
        str(singleton.get("type") or singleton.get("name"))
        for singleton in data.get("singletons", [])
        if singleton.get("type") or singleton.get("name")
    }


def load_api(path: Path) -> dict[str, dict[str, list[ApiMethod]]]:
    return {name: cls.methods for name, cls in load_api_classes(path).items()}


def load_api_method_index(path: Path) -> dict[tuple[str, str, int], ApiMethod]:
    methods: dict[tuple[str, str, int], ApiMethod] = {}
    for cls in load_api_classes(path).values():
        for method_list in cls.methods.values():
            for method in method_list:
                methods[(cls.name, method.name, method.hash)] = method
    return methods


def object_type_names(classes: dict[str, ApiClass]) -> set[str]:
    return {
        "Callable",
        "Node",
        "Object",
        "RefCounted",
        "Resource",
        "Script",
        *(classes.keys()),
    }


def value_policy(type_name: str, meta: str = "", object_types: set[str] | None = None) -> ApiValuePolicy:
    """Return exact ABI policy for an extension_api.json value slot."""
    if type_name == "void":
        return ApiValuePolicy(type_name, meta, "void", "void", "Unit", "Void", "NIL")

    if type_name in RAW_POINTER_POLICIES:
        exact, helper = RAW_POINTER_POLICIES[type_name]
        return ApiValuePolicy(type_name, meta, exact, "raw_pointer", "MemorySegment", helper, "NIL")

    if type_name == "int":
        if meta in UNSIGNED_INT_POLICIES:
            exact, storage, public_type, helper, variant_type = UNSIGNED_INT_POLICIES[meta]
        else:
            exact, storage, public_type, helper, variant_type = SIGNED_INT_POLICIES.get(
                meta,
                SIGNED_INT_POLICIES[""],
            )
        return ApiValuePolicy(type_name, meta, exact, storage, public_type, helper, variant_type)

    if type_name.startswith("enum::"):
        return ApiValuePolicy(type_name, meta, "enum", "int64", "Long", "Long", "INT")

    if type_name.startswith("bitfield::"):
        return ApiValuePolicy(type_name, meta, "bitfield", "int64", "Long", "Long", "INT")

    if type_name == "float":
        exact, storage, public_type, helper, variant_type = FLOAT_POLICIES.get(meta, FLOAT_POLICIES[""])
        return ApiValuePolicy(type_name, meta, exact, storage, public_type, helper, variant_type)

    if type_name in PACKED_ARRAY_POLICIES:
        exact, public_type, helper, variant_type = PACKED_ARRAY_POLICIES[type_name]
        return ApiValuePolicy(type_name, meta, exact, exact, public_type, helper, variant_type)

    if type_name in BUILTIN_POLICIES:
        exact, public_type, helper, variant_type = BUILTIN_POLICIES[type_name]
        return ApiValuePolicy(type_name, meta, exact, exact, public_type, helper, variant_type)

    if type_name == "Variant":
        return ApiValuePolicy(type_name, meta, "variant", "variant", "Any?", "Variant", "NIL")

    if type_name == "Array":
        return ApiValuePolicy(type_name, meta, "array", "array", "List<Any?>", "Array", "ARRAY")

    if type_name == "Dictionary":
        return ApiValuePolicy(type_name, meta, "dictionary", "dictionary", "Map<String, Any?>", "Dictionary", "DICTIONARY")

    if type_name == "Callable":
        return ApiValuePolicy(type_name, meta, "callable", "callable", "GodotCallable", "Callable", "CALLABLE")

    if type_name.startswith("typedarray::"):
        element = type_name.removeprefix("typedarray::")
        public_type = f"List<{element}>"
        if object_types and element in object_types:
            exact = f"typed_object_array::{element}"
            storage = "typed_object_array"
            public_type = f"List<{element}?>"
        else:
            exact = abi_kind(type_name, meta)
            storage = exact
        return ApiValuePolicy(type_name, meta, exact, storage, public_type, "Array", "ARRAY")

    if object_types and type_name in object_types:
        public_type = "GodotObject?" if type_name == "Object" else f"{type_name}?"
        return ApiValuePolicy(type_name, meta, f"object::{type_name}", "object", public_type, "Object", "OBJECT", nullable=True, ownership="borrowed")

    return ApiValuePolicy(type_name, meta, f"object::{type_name}", "object", f"{type_name}?", "Object", "OBJECT", nullable=True, ownership="borrowed")


def exact_abi_kind(type_name: str, meta: str = "", object_types: set[str] | None = None) -> str:
    return value_policy(type_name, meta, object_types).exact_kind


def storage_abi_kind(type_name: str, meta: str = "", object_types: set[str] | None = None) -> str:
    return value_policy(type_name, meta, object_types).storage_kind


def logical_type(type_name: str, meta: str = "", object_types: set[str] | None = None) -> str:
    if type_name.startswith("enum::"):
        return "enum"
    if type_name.startswith("bitfield::"):
        return "bitfield"
    if type_name in RAW_POINTER_POLICIES:
        return RAW_POINTER_POLICIES[type_name][1]
    if type_name == "typedarray::RID":
        return "TypedRIDArray"
    if type_name == "typedarray::StringName":
        return "TypedStringNameArray"
    if type_name == "typedarray::String":
        return "TypedStringArray"
    if type_name == "typedarray::int":
        return "TypedIntArray"
    if type_name == "typedarray::NodePath":
        return "TypedNodePathArray"
    if type_name == "typedarray::Array":
        return "TypedArrayArray"
    if type_name == "typedarray::PackedByteArray":
        return "TypedPackedByteArray"
    if type_name == "typedarray::PackedStringArray":
        return "TypedPackedStringArray"
    if type_name == "typedarray::PackedVector2Array":
        return "TypedPackedVector2Array"
    if type_name == "typedarray::Plane":
        return "TypedPlaneArray"
    if type_name == "typedarray::Rect2":
        return "TypedRect2Array"
    if type_name == "typedarray::Transform3D":
        return "TypedTransform3DArray"
    if type_name == "typedarray::Vector2":
        return "TypedVector2Array"
    if type_name == "typedarray::Vector3":
        return "TypedVector3Array"
    if type_name == "typedarray::Node":
        return "TypedNodeArray"
    if type_name == "typedarray::Node2D":
        return "TypedNode2DArray"
    if type_name == "typedarray::Node3D":
        return "TypedNode3DArray"
    if type_name == "typedarray::Material":
        return "TypedMaterialArray"
    if type_name == "typedarray::Area2D":
        return "TypedArea2DArray"
    if type_name == "typedarray::Area3D":
        return "TypedArea3DArray"
    if type_name == "typedarray::BaseButton":
        return "TypedBaseButtonArray"
    if type_name == "typedarray::PhysicsBody3D":
        return "TypedPhysicsBody3DArray"
    if type_name == "typedarray::Vector2i":
        return "TypedVector2iArray"
    if type_name == "typedarray::Vector3i":
        return "TypedVector3iArray"
    if type_name == "typedarray::Dictionary":
        return "TypedDictionaryArray"
    if type_name.startswith("typedarray::") and object_types:
        element_type = type_name.removeprefix("typedarray::")
        if element_type in object_types:
            return f"TypedObjectArray::{element_type}"
    if type_name == "int":
        return storage_abi_kind(type_name, meta, object_types)
    if type_name == "Callable":
        return "Callable"
    if object_types and type_name in object_types:
        return "Object"
    return type_name


def abi_kind(type_name: str, meta: str | None = None) -> str:
    if type_name == "void":
        return "void"
    if type_name == "bool":
        return "bool"
    if type_name == "int":
        if meta in {"int8", "int16", "int32", "char32", "uint8", "uint16"}:
            return "int32"
        if meta == "uint32":
            return "uint32"
        return "int64"
    if type_name.startswith("enum::"):
        return "enum"
    if type_name.startswith("bitfield::"):
        return "bitfield"
    if type_name == "float":
        return "float"
    if type_name in RAW_POINTER_POLICIES:
        return "raw_pointer"
    if type_name in {"String", "StringName", "NodePath", "RID", "Callable", "Dictionary", "Array"}:
        return {
            "String": "string",
            "StringName": "string_name",
            "NodePath": "node_path",
            "RID": "rid",
            "Callable": "callable",
            "Dictionary": "dictionary",
            "Array": "array",
        }[type_name]
    if type_name == "typedarray::RID":
        return "typed_rid_array"
    if type_name == "typedarray::StringName":
        return "typed_string_name_array"
    if type_name == "typedarray::String":
        return "typed_string_array"
    if type_name == "typedarray::int":
        return "typed_int_array"
    if type_name == "typedarray::NodePath":
        return "typed_node_path_array"
    if type_name == "typedarray::Array":
        return "typed_array_array"
    if type_name == "typedarray::PackedByteArray":
        return "typed_packed_byte_array"
    if type_name == "typedarray::PackedStringArray":
        return "typed_packed_string_array"
    if type_name == "typedarray::PackedVector2Array":
        return "typed_packed_vector2_array"
    if type_name == "typedarray::Plane":
        return "typed_plane_array"
    if type_name == "typedarray::Rect2":
        return "typed_rect2_array"
    if type_name == "typedarray::Transform3D":
        return "typed_transform3d_array"
    if type_name == "typedarray::Vector2":
        return "typed_vector2_array"
    if type_name == "typedarray::Vector3":
        return "typed_vector3_array"
    if type_name == "typedarray::Node":
        return "typed_node_array"
    if type_name == "typedarray::Node2D":
        return "typed_node2d_array"
    if type_name == "typedarray::Node3D":
        return "typed_node3d_array"
    if type_name == "typedarray::Material":
        return "typed_material_array"
    if type_name == "typedarray::Area2D":
        return "typed_area2d_array"
    if type_name == "typedarray::Area3D":
        return "typed_area3d_array"
    if type_name == "typedarray::BaseButton":
        return "typed_base_button_array"
    if type_name == "typedarray::PhysicsBody3D":
        return "typed_physics_body3d_array"
    if type_name == "typedarray::Vector2i":
        return "typed_vector2i_array"
    if type_name == "typedarray::Vector3i":
        return "typed_vector3i_array"
    if type_name == "typedarray::Dictionary":
        return "typed_dictionary_array"
    if type_name.startswith("typedarray::"):
        return "typed_object_array"
    if type_name == "PackedStringArray":
        return "packed_string_array"
    if type_name == "PackedByteArray":
        return "packed_byte_array"
    if type_name == "PackedInt32Array":
        return "packed_int32_array"
    if type_name == "PackedInt64Array":
        return "packed_int64_array"
    if type_name == "PackedFloat32Array":
        return "packed_float32_array"
    if type_name == "PackedFloat64Array":
        return "packed_float64_array"
    if type_name == "PackedVector2Array":
        return "packed_vector2_array"
    if type_name == "PackedVector3Array":
        return "packed_vector3_array"
    if type_name == "PackedColorArray":
        return "packed_color_array"
    if type_name == "PackedVector4Array":
        return "packed_vector4_array"
    lower = type_name.lower()
    if lower in {
        "vector2",
        "vector2i",
        "vector3",
        "vector3i",
        "vector4",
        "vector4i",
        "quaternion",
        "basis",
        "aabb",
        "plane",
        "projection",
        "transform2d",
        "transform3d",
        "rect2",
        "rect2i",
        "color",
    }:
        return lower
    if type_name == "Variant":
        return "variant"
    return "object"


def scan_wrappers(api_dir: Path) -> dict[str, set[str]]:
    wrapped: dict[str, set[str]] = {}
    for path in sorted(api_dir.glob("*.kt")):
        content = path.read_text(encoding="utf-8")
        wrapper_class = path.stem
        api_class = WRAPPER_CLASS_ALIASES.get(wrapper_class, wrapper_class)
        for class_name, method_name, _token in METHOD_BIND_RE.findall(content):
            if class_name != api_class:
                continue
            wrapped.setdefault(class_name, set()).add(method_name)
        policy_methods = POLICY_WRAPPED_METHODS.get(wrapper_class)
        if policy_methods:
            wrapped.setdefault(api_class, set()).update(policy_methods)
    return wrapped


# One shared generated wrapper tree, compiled by every native backend (task 103): the root JVM
# module and :ios-runtime add SHARED_API_DIR as a source root, and the Android plugin copies it
# next to the desktop sources. The per-platform directories hold only what is not shared: the
# hand-shaped classes, the classes generated for one platform only, and the generated companion
# files (`<Class>.jvm.kt` desktop-only members, `<Class>.ios.kt` iOS-only sugar).
ROOT = Path(__file__).resolve().parents[1]
SHARED_API_DIR = ROOT / "src/commonMain/kotlin/net/multigesture/kanama/api"
DESKTOP_API_DIR = ROOT / "src/main/kotlin/net/multigesture/kanama/api"
IOS_API_DIR = ROOT / "ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api"
PLATFORM_API_DIRS = (DESKTOP_API_DIR, IOS_API_DIR)


def is_companion_file(path: Path) -> bool:
    """`<Class>.jvm.kt` / `<Class>.ios.kt`: generated platform companions, not class files."""
    return "." in path.stem


def wrapper_source_files(api_dir: Path, *, companions: bool = False) -> list[Path]:
    """Every wrapper source the platform owning `api_dir` compiles: the shared tree plus its own
    directory. A directory that is not one of the platform roots (a scratch/output dir) is listed
    alone. Companion files are excluded unless asked for, since most audits expect one class per
    file."""
    api_dir = api_dir.resolve() if api_dir.exists() else api_dir
    dirs = [SHARED_API_DIR, api_dir] if api_dir in PLATFORM_API_DIRS else [api_dir]
    files: list[Path] = []
    for directory in dirs:
        for path in sorted(directory.glob("*.kt")):
            if companions or not is_companion_file(path):
                files.append(path)
    return files


def scan_wrapper_classes(api_dir: Path) -> set[str]:
    return {WRAPPER_CLASS_ALIASES.get(path.stem, path.stem) for path in wrapper_source_files(api_dir)}


def ancestors(class_name: str, classes: dict[str, ApiClass]) -> set[str]:
    result: set[str] = set()
    current = classes.get(class_name)
    while current and current.inherits:
        parent = current.inherits
        if parent in result:
            break
        result.add(parent)
        current = classes.get(parent)
    return result


def category_for(class_name: str, classes: dict[str, ApiClass]) -> str:
    chain = ancestors(class_name, classes)
    if class_name in {"AudioStream", "AudioStreamPlayer", "AudioStreamPlayer2D", "AudioStreamPlayer3D"} or "AudioStream" in chain:
        return "Audio"
    if class_name.startswith("Editor") or class_name in {"EditorInterface", "EditorPlugin"}:
        return "Editor"
    if class_name.startswith("Multiplayer") or class_name in {"ENetMultiplayerPeer", "OfflineMultiplayerPeer"}:
        return "Multiplayer"
    if class_name.startswith("Input") or class_name in {"Shortcut"}:
        return "Input"
    if "Control" in chain or class_name in {"Control", "Button", "Label", "TextureRect", "LineEdit", "SpinBox"}:
        return "UI"
    if class_name.endswith("2D") or any(parent.endswith("2D") for parent in chain):
        return "2D"
    if class_name.endswith("3D") or any(parent.endswith("3D") for parent in chain):
        return "3D"
    if "Physics" in class_name or any("Physics" in parent for parent in chain):
        return "Physics"
    if "Resource" in chain or class_name in {"Resource", "PackedScene"}:
        return "Resources"
    if any(token in class_name for token in ("Rendering", "Renderer", "Shader", "Material", "Mesh", "Texture", "Image", "Light")):
        return "Rendering"
    if "Node" in chain or class_name in {"Node", "SceneTree", "Window", "Viewport"}:
        return "Scene"
    return "Core"
