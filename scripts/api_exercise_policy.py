"""The hand-kept half of the API exercise inventory: every judgement call, in one reviewed file.

`scripts/generate_api_exercise.py` derives everything else (which helpers the shared tree calls,
from which Godot method, with which receiver, and the case each one gets) from
`extension_api.json`, the generator's own tables and the committed tree. What it cannot derive
lives here, and only here:

  * EXCLUDE_EXACT / EXCLUDE_SUBSTRING / EXCLUDE_PREFIX -- classes an exercise must not construct or
    call headless (a window, a GPU device, the network, the host OS, threads, the editor), each
    with its reason. Matched against the class and every ancestor: an entry excludes the subtree.
    Exact names and substrings, never a short prefix (a prefix `Input` would also exclude every
    `InputEvent*`); the two prefixes kept are unambiguous. `api_type != "core"` (the editor
    classes) is derived and needs no entry.
  * STATELESS_SINGLETONS -- singletons whose every non-void method is a pure function of its
    arguments, so a non-const method can still be compared against the Variant path.
  * RECEIVER_SOURCES -- abstract classes whose rows use the engine's own instance (TextServer from
    TextServerManager) instead of constructing a descendant.
  * METHOD_OVERRIDES -- methods the class-level rules would give a value row but whose result cannot
    be compared (a clock read, a new object per call: F) or which must not run (E), each with its reason.
  * NON_BIND_HELPERS -- the referenced `ObjectCalls` helpers that no generated method calls with a
    MethodBind: the runtime plumbing every row uses implicitly. A new one is unclassified until it
    is listed here.
  * STATIC_ARGS -- the argument list of each value-asserting static row (a made-up path makes
    `FileAccess.open` return null; a zero size makes `Image.create` return null). The skeleton
    holds the `extension_api.json` default where one exists and `None` where a value is still
    to be chosen.
  * SCRATCH_DIR / SCRATCH_POLICY -- the one place an exercise row may touch the filesystem.

`generate_api_exercise.py --check` fails when an entry here names a class or method the API lacks,
when an entry matches nothing or no longer applies (a METHOD_OVERRIDES entry whose case the class
rules already give every call of the method), when a STATIC_ARGS default differs from
extension_api.json, and when a referenced helper (or one call of it) is classified by nothing.
"""

from __future__ import annotations

# ---- exclusions -------------------------------------------------------------------------------------

_WINDOW = "needs a window or the display server (headless has a dummy one that errors or no-ops)"
_THREADS = "blocks the calling thread or starts threads"
_NETWORK = "opens sockets or talks to the network"
_RD = "needs a RenderingDevice (the headless and Compatibility renderers have none)"
_FRAME = "only meaningful inside a frame being rendered by a RenderingDevice renderer"

EXCLUDE_EXACT: dict[str, str] = {
    # window / display
    "Window": _WINDOW,
    "DisplayServer": _WINDOW,
    "NativeMenu": "the host OS menu bar",
    "StatusIndicator": "an OS tray icon",
    "Input": "process-wide input state backed by the host's input devices",
    "InputMap": "the running project's process-wide action map",
    # (AcceptDialog, ConfirmationDialog, FileDialog and every other dialog inherit Window.)
    "CameraServer": "camera hardware",
    "CameraFeed": "camera hardware",
    # host / process
    "OS": "host process control (execute, kill, shell_open, environment)",
    "EngineDebugger": "the debugger connection of the running engine",
    "GDExtensionManager": "loads and unloads native extensions",
    "ResourceSaver": "writes resources outside the scratch directory",
    "MovieWriter": "movie-maker output files",
    "IP": "DNS resolution against the host network",
    "Performance": "monitor values change between the two reads an oracle compares",
    # blocking
    "Semaphore": _THREADS,
    "Thread": _THREADS,
    "WorkerThreadPool": _THREADS,
    "Mutex": _THREADS,
    # network, by name (each is also covered by a substring below; listed so the intent survives a rename)
    "PacketPeerUDP": _NETWORK,
    "UDPServer": _NETWORK,
    "StreamPeerTCP": _NETWORK,
    "TCPServer": _NETWORK,
    "PacketPeerDTLS": _NETWORK,
    "StreamPeerTLS": _NETWORK,
    "DTLSServer": _NETWORK,
    # rendering-device caches whose statics need a device
    "FramebufferCacheRD": _RD,
    "UniformSetCacheRD": _RD,
}

EXCLUDE_SUBSTRING: dict[str, str] = {
    "HTTP": _NETWORK,
    "UDP": _NETWORK,
    "ENet": _NETWORK,
    "WebRTC": _NETWORK,
    "WebSocket": _NETWORK,
    "UPNP": _NETWORK,
    "Multiplayer": _NETWORK,
    "XR": "an XR runtime and its devices",
    "MobileVR": "an XR interface",
    "JavaClass": "the Android Java bridge",
    "JavaScript": "the browser JavaScript bridge",
    "JNI": "the Android Java bridge",
    "RenderingDevice": _RD,
    "RenderSceneBuffers": _FRAME,
    "RenderData": _FRAME,
    "RenderSceneData": _FRAME,
    "Popup": _WINDOW,
    "GDScript": "GDScript language internals",
    "ScriptLanguage": "script-language plumbing the running languages own",
    "ScriptExtension": "script-language plumbing the running languages own",
    "Extension": "a base a script or extension implements (TextServerExtension, PhysicsServer3DExtension, ...): its methods are hooks, not engine behaviour",
}

# (No `RD` prefix and no `Compositor` substring: the `RD*` descriptions, `Compositor` and
# `CompositorEffect` are plain data resources, safe to construct and set headless; the
# RenderingDevice itself and the per-frame render objects stay excluded above. The `TCP` substring
# also matched `WebRTCPeerConnection`; the TCP classes are named exactly instead.)
EXCLUDE_PREFIX: dict[str, str] = {
    "Editor": "editor-only classes",
}

# ---- singletons -------------------------------------------------------------------------------------

STATELESS_SINGLETONS: dict[str, str] = {
    "Geometry2D": "pure 2D geometry functions of their arguments",
    "Geometry3D": "pure 3D geometry functions of their arguments",
    "Marshalls": "pure encoders and decoders of their arguments",
}

# ---- where a row gets its receiver ------------------------------------------------------------------

# An abstract class whose instances come from an engine factory rather than construction:
# class -> (singleton, method returning the instance, reason). Without an entry, an abstract class's
# rows construct its nearest instantiable non-excluded descendant.
RECEIVER_SOURCES: dict[str, tuple[str, str, str]] = {
    "TextServer": (
        "TextServerManager",
        "get_primary_interface",
        "the running text server (TextServerAdvanced or TextServerDummy inherit TextServerExtension, which "
        "`*Extension*` excludes as a construction target, but the engine's own instance is the class under test)",
    ),
}

# ---- method-level cases --------------------------------------------------------------------------------

_CLOCK = "reads the clock: two reads an oracle compares can differ"
_NEW_OBJECT = "returns a new object per call: an oracle would compare two different objects"

# (class, method) -> (case, reason): a method the class-level rules would give a value row, but whose
# result cannot be compared (F) or which must not run at all (E).
METHOD_OVERRIDES: dict[tuple[str, str], tuple[str, str]] = {
    ("Time", "get_datetime_dict_from_system"): ("F", _CLOCK),
    ("Time", "get_date_dict_from_system"): ("F", _CLOCK),
    ("Time", "get_time_dict_from_system"): ("F", _CLOCK),
    ("Time", "get_datetime_string_from_system"): ("F", _CLOCK),
    ("Time", "get_date_string_from_system"): ("F", _CLOCK),
    ("Time", "get_time_string_from_system"): ("F", _CLOCK),
    ("Time", "get_unix_time_from_system"): ("F", _CLOCK),
    ("Time", "get_ticks_msec"): ("F", _CLOCK),
    ("Time", "get_ticks_usec"): ("F", _CLOCK),
    ("ClassDB", "instantiate"): ("F", _NEW_OBJECT),
    ("Resource", "duplicate"): ("F", _NEW_OBJECT),
    ("Mesh", "create_outline"): ("F", _NEW_OBJECT),
    ("Mesh", "create_convex_shape"): ("F", _NEW_OBJECT),
    ("Image", "get_region"): ("F", _NEW_OBJECT),
    ("Noise", "get_image"): ("F", _NEW_OBJECT),
    ("Noise", "get_seamless_image"): ("F", _NEW_OBJECT),
    ("Noise", "get_image_3d"): ("F", _NEW_OBJECT),
    ("Noise", "get_seamless_image_3d"): ("F", _NEW_OBJECT),
    ("RenderingServer", "texture_2d_get"): ("F", _NEW_OBJECT),
    ("RenderingServer", "texture_2d_layer_get"): ("F", _NEW_OBJECT),
    ("InputEvent", "xformed_by"): ("F", _NEW_OBJECT),
    ("RegEx", "search"): ("F", _NEW_OBJECT),
    ("ProjectSettings", "load_resource_pack"): ("E", "mounts a resource pack into res:// for the rest of the process"),
    ("GLTFDocument", "export_object_model_property"): (
        "E",
        "indexes `state.nodes[gltf_node_index]` unchecked for a property on a node: on a GLTFState without "
        "nodes it aborts the process (cowdata FATAL index, found by the headless sweep)",
    ),
}

# ---- referenced helpers outside the generated MethodBind calls ----------------------------------------

NON_BIND_HELPERS: dict[str, str] = {
    "constructObject": "constructs the fresh instance of every A/B/F row; a row that constructs is its check",
    "destroyObject": "frees what a row constructed (a RefCounted's last release); part of every row's teardown",
    "getMethodBind": "resolves the MethodBind every row's call passes; a wrong bind fails the row that uses it",
    "getSingleton": "resolves the singleton handle every D row calls",
    "keepReachable": "the reachability fence after each call (empty on iOS); it has no result to observe",
}

# ---- property-pair rows -----------------------------------------------------------------------------

# (class, property) -> (Godot literal for the value a row sets, or None, reason). The generator picks the
# value an A row sets from the property's type (a recognisable non-default: 7, 0.75, "kanama125",
# Vector3(0.25, 0.5, 0.75), a fresh instance, the bool opposite to the current value). An entry here
# replaces that choice where the property only keeps a value of a certain kind (an MP3 stream keeps
# only bytes that decode), or, with None, says the property is no pair at all: a set value does not
# read back as itself (it is clamped, normalised or validated against state a fresh object lacks),
# and its setter and getter fall back to their method's own case (a setter is F, a const getter B).
_LOADED_PATH = "set by load(path), which keeps the path only once the file loads"
_CLAMPED_BY_FRAMES = "validated against the node's SpriteFrames, which a fresh node lacks: the set value is dropped"
PAIR_OVERRIDES: dict[tuple[str, str], tuple[str | None, str]] = {
    ("AnimatedSprite2D", "animation"): (None, _CLAMPED_BY_FRAMES),
    ("AnimatedSprite2D", "frame"): (None, _CLAMPED_BY_FRAMES),
    ("AnimatedSprite3D", "animation"): (None, _CLAMPED_BY_FRAMES),
    ("AnimatedSprite3D", "frame"): (None, _CLAMPED_BY_FRAMES),
    ("AnimatedTexture", "current_frame"): (None, "clamped to the texture's frame count (1 on a fresh AnimatedTexture)"),
    ("AudioStreamMP3", "data"): ('fixture("mp3")', "keeps only bytes that decode as MP3"),
    ("CodeEdit", "indent_automatic_prefixes"): ('["@"]', "each prefix is one character (the setter keeps the first)"),
    ("CompressedTexture2D", "load_path"): (None, _LOADED_PATH),
    ("CompressedTexture3D", "load_path"): (None, _LOADED_PATH),
    ("CompressedTextureLayered", "load_path"): (None, _LOADED_PATH),
    (
        "GraphEdit",
        "connections",
    ): (
        '[{"from_node": "kanama_a", "from_port": 1, "to_node": "kanama_b", "to_port": 2, "keep_alive": true}]',
        "the setter reads the five connection keys and fills the missing ones with defaults",
    ),
    ("OggPacketSequence", "packet_data"): ("[[PackedByteArray(1, 2, 3)]]", "each packet is a PackedByteArray"),
}

# ---- static rows -------------------------------------------------------------------------------------

SCRATCH_DIR = "user://kanama-api-exercise/"
SCRATCH_POLICY = (
    "The exercise runner creates `user://kanama-api-exercise/` before its first row (with the fixture "
    "files the rows read: `fixture.txt` with an extended attribute, `fixture.mp3`, `fixture.wav`, and the "
    "directory `sub/`) and removes it after its last. Every row whose method takes a path, of any case, "
    "takes one under that directory: the `FileAccess` and `DirAccess` statics (`open*`, `make_dir*`, "
    "`rename*`, `copy*`, `set_*attribute`, `set_unix_permissions`, `get_file_as_*`) and the instance and "
    "singleton rows listed below (`Image.save_*`, `GLTFDocument.write_to_filesystem`, ...). The one "
    "exception is `FileAccess.create_temp` / `DirAccess.create_temp`, which take no path: they create "
    "their file or directory in the OS temp directory, named `kanama-api-exercise-*`, and remove it when "
    "the returned object is freed (`keep` is false). No row reads or writes anywhere else; a method that "
    "cannot be pointed there is excluded in `METHOD_OVERRIDES`."
)


class Arg(str):
    """A STATIC_ARGS argument that deliberately differs from the `extension_api.json` default, with the
    reason. A plain string where the argument has a default must equal it (--check compares)."""

    reason: str

    def __new__(cls, value: str, reason: str) -> "Arg":
        made = super().__new__(cls, value)
        made.reason = reason
        return made


# (class, method) -> one Godot literal per argument. The literals are GDScript expressions: numbers,
# `true`/`false`/`null`, "strings", `{"k": 7}`, `[...]`, `PackedByteArray(...)`, the value-type
# constructors (`Vector2(1, 2)`, `Transform3D()`), class constants (`FileAccess.READ`), `Class.new()`
# (a fresh instance the runner frees after the row), and two runner names: `scratch("name")` (a path
# under SCRATCH_DIR) and `fixture("name")` (bytes or text the runner builds: `mp3`, `wav`, `svg`,
# `key32`, `rgba8_4x4`). Where extension_api.json has a default, the literal is that default
# (--check compares, so an API refresh that changes a default fails here) or an `Arg` with its reason.
# A static row without arguments needs no entry.
#
# A static returning `Error` returns OK = 0 on success, which a no-op also returns: its arguments make
# it FAIL with a known non-OK Error (a missing path, a directory that already exists), so the row
# asserts a value a silent no-op cannot produce.
_FAILS = "returns a known non-OK Error"
STATIC_ARGS: dict[tuple[str, str], tuple[str, ...]] = {
    # FileAccess / DirAccess: every path is under SCRATCH_DIR (see SCRATCH_POLICY).
    # from == to: ERR_INVALID_PARAMETER (31), before any file is opened.
    ("DirAccess", "copy_absolute"): ('scratch("fixture.txt")', 'scratch("fixture.txt")', "-1"),
    ("DirAccess", "create_temp"): (
        Arg('"kanama-api-exercise"', "a recognisable name for the OS temp directory the call creates (removed on free)"),
        "false",
    ),
    ("DirAccess", "dir_exists_absolute"): ('scratch("sub")',),
    ("DirAccess", "get_directories_at"): ('scratch("")',),
    # The first volume: never "" on a desktop or iOS host. Android (P4) needs a look: DirAccessJAndroid can
    # report no drive at all, and then the row reads "" and fails.
    ("DirAccess", "get_drive_name"): ("0",),
    ("DirAccess", "make_dir_absolute"): ('scratch("sub")',),  # exists: ERR_ALREADY_EXISTS (32)
    ("DirAccess", "rename_absolute"): ('scratch("missing")', 'scratch("renamed")'),  # FAILED (1)
    ("FileAccess", "create_temp"): (
        "FileAccess.WRITE",
        Arg('"kanama-api-exercise"', "a recognisable name for the OS temp file the call creates (removed on free)"),
        '""',
        "false",
    ),
    ("FileAccess", "get_extended_attribute"): ('scratch("fixture.txt")', '"kanama"'),
    ("FileAccess", "get_extended_attribute_string"): ('scratch("fixture.txt")', '"kanama"'),
    ("FileAccess", "get_file_as_bytes"): ('scratch("fixture.txt")',),
    ("FileAccess", "get_file_as_string"): ('scratch("fixture.txt")',),
    ("FileAccess", "open"): ('scratch("fixture.txt")', "FileAccess.READ"),
    ("FileAccess", "open_compressed"): ('scratch("compressed.bin")', "FileAccess.WRITE", "0"),
    ("FileAccess", "open_encrypted"): ('scratch("encrypted.bin")', "FileAccess.WRITE", 'fixture("key32")', "PackedByteArray()"),
    ("FileAccess", "open_encrypted_with_pass"): ('scratch("encrypted_pass.bin")', "FileAccess.WRITE", '"kanama125"'),
    # A missing file: FAILED (1), or ERR_UNAVAILABLE where the host has no such attribute.
    ("FileAccess", "set_extended_attribute"): ('scratch("missing")', '"kanama"', "PackedByteArray(1, 2, 3)"),
    ("FileAccess", "set_extended_attribute_string"): ('scratch("missing")', '"kanama"', '"kanama125"'),
    ("FileAccess", "set_hidden_attribute"): ('scratch("missing")', "true"),
    ("FileAccess", "set_unix_permissions"): ('scratch("missing")', "FileAccess.UNIX_READ_OWNER"),
    # Resources from bytes, files, strings or dictionaries: a value the static accepts.
    ("AudioStreamMP3", "load_from_buffer"): ('fixture("mp3")',),
    ("AudioStreamMP3", "load_from_file"): ('scratch("fixture.mp3")',),
    ("AudioStreamWAV", "load_from_buffer"): ('fixture("wav")', "{}"),
    ("AudioStreamWAV", "load_from_file"): ('scratch("fixture.wav")', "{}"),
    ("DPITexture", "create_from_string"): (
        'fixture("svg")',
        Arg("2.0", "a scale unlike the default, so STATIC_OBJECT_CHECKS can tell it from the saturation"),
        Arg("0.5", "a saturation unlike the default and the scale"),
        "{}",
    ),
    ("GLTFAccessor", "from_dictionary"): ('{"type": "SCALAR", "componentType": 5126, "count": 3}',),
    ("GLTFCamera", "from_node"): ("Camera3D.new()",),
    # A node pointer: the property object is made before the (empty) state's node lookup fails and is
    # returned with the error printed; a pointer outside the core object model returns null.
    ("GLTFDocument", "import_object_model_property"): ("GLTFState.new()", '"/nodes/0/translation"'),
    # 4 x 2, not square: a helper that swaps width and height reads back 2 x 4.
    ("Image", "create"): ("4", "2", "false", "Image.FORMAT_RGBA8"),
    ("Image", "create_from_data"): ("4", "2", "false", "Image.FORMAT_RGBA8", 'fixture("rgba8_4x2")'),
    ("ImporterMesh", "merge_importer_meshes"): ("[ImporterMesh.new()]", "[Transform3D()]", "true"),
    ("JSON", "from_native"): ("Vector2(1, 2)", "false"),
    ("JSON", "parse_string"): ('"{\\"kanama\\": 125}"',),
    ("JSON", "stringify"): ('{"kanama": 125}', '""', "true", "false"),
    ("TLSOptions", "server"): ("CryptoKey.new()", "X509Certificate.new()"),
    # Pure functions of their arguments.
    # A quarter turn about Z with an origin: no axis or component equals another, so a swapped cell shows.
    ("PathFollow3D", "correct_posture"): (
        "Transform3D(Vector3(0, 1, 0), Vector3(-1, 0, 0), Vector3(0, 0, 1), Vector3(1, 2, 3))",
        "PathFollow3D.ROTATION_ORIENTED",
    ),
    ("PhysicsRayQueryParameters2D", "create"): (
        "Vector2(1, 2)",
        "Vector2(3, 4)",
        Arg("5", "a mask unlike the default, so STATIC_OBJECT_CHECKS reads back a value the call chose"),
        "Array[RID]([])",
    ),
    ("PhysicsRayQueryParameters3D", "create"): (
        "Vector3(1, 2, 3)",
        "Vector3(4, 5, 6)",
        Arg("5", "a mask unlike the default, so STATIC_OBJECT_CHECKS reads back a value the call chose"),
        "Array[RID]([])",
    ),
    ("Tween", "interpolate_value"): ("0.0", "10.0", "0.5", "1.0", "Tween.TRANS_LINEAR", "Tween.EASE_IN"),
}

# (class, method) -> (names, reason) for every static row that returns an object. A new object per call
# cannot be compared with the Variant path's object, so the row reads one to three of its properties
# (`name`) or argument-free const methods (`name()`) through `Object.get` / `Object.call` on both results
# and requires them equal: the arguments the call was given show in them (a swapped `from`/`to`, a 2 x 4
# image for a 4 x 2 one). An empty tuple says why nothing can be read (the reason is then required).
_FRESH_PATH = "a new temporary name per call: nothing the two results share besides their class"
STATIC_OBJECT_CHECKS: dict[tuple[str, str], tuple[tuple[str, ...], str]] = {
    ("AudioStreamMP3", "load_from_buffer"): (("data",), "the decoded stream keeps the bytes it was given"),
    ("AudioStreamMP3", "load_from_file"): (("data",), "the bytes of the fixture file"),
    ("AudioStreamWAV", "load_from_buffer"): (("mix_rate", "stereo", "format"), "the fixture's header: 8 kHz mono 16-bit"),
    ("AudioStreamWAV", "load_from_file"): (("mix_rate", "stereo", "format"), "the fixture's header: 8 kHz mono 16-bit"),
    ("DPITexture", "create_from_string"): (("base_scale", "saturation"), "the scale and saturation it was given"),
    ("DirAccess", "create_temp"): ((), _FRESH_PATH),
    ("FileAccess", "create_temp"): ((), _FRESH_PATH),
    ("FileAccess", "open"): (("get_path()", "get_length()"), "the fixture file it opened"),
    ("FileAccess", "open_compressed"): (("get_path()",), "the path it opened"),
    ("FileAccess", "open_encrypted"): (("get_path()",), "the path it opened"),
    ("FileAccess", "open_encrypted_with_pass"): (("get_path()",), "the path it opened"),
    ("GLTFAccessor", "from_dictionary"): (("count", "component_type"), "the dictionary's count and component type"),
    ("GLTFCamera", "from_node"): (("fov", "depth_far", "depth_near"), "the camera node's projection"),
    ("GLTFDocument", "import_object_model_property"): (("json_pointers",), "the pointer it was given"),
    ("Image", "create"): (("get_width()", "get_height()", "get_format()"), "the size and format it was given"),
    ("Image", "create_from_data"): (("get_width()", "get_height()", "get_data()"), "the size and bytes it was given"),
    ("ImporterMesh", "merge_importer_meshes"): (("get_surface_count()",), "the surfaces of the merged meshes"),
    ("PhysicsRayQueryParameters2D", "create"): (("from", "to", "collision_mask"), "the ray it was given"),
    ("PhysicsRayQueryParameters3D", "create"): (("from", "to", "collision_mask"), "the ray it was given"),
    ("TLSOptions", "server"): (("is_server()",), "a server configuration"),
}

# (class, method) -> (kind, reason) for a static row whose result cannot be held to "equals the Variant
# path and is not its type's zero": `weak` (it can be its type's zero on a healthy host: the row checks
# the two paths agree and is reported weak, not failed) or `fresh` (a new value per call: the row checks
# both paths return a non-zero value of one type, not that they are equal). The argument-free statics a
# zero can come from are weak by rule and need no entry.
STATIC_RESULTS: dict[tuple[str, str], tuple[str, str]] = {
    ("DirAccess", "get_drive_label"): ("weak", "a drive label exists only on Windows; every other host returns \"\""),
    ("Resource", "generate_scene_unique_id"): ("fresh", "a new random id per call"),
}
