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
  * SINGLETON_PAIRS / SINGLETON_KEYED_PAIRS -- the singletons whose process-wide state a row may set,
    read back and restore.
  * RID_FACTORIES / RID_ARG_SOURCES -- the servers whose RID factories run as create/read/free rows,
    and where a B or D row's RID argument comes from.
  * PAIR_GROW / ROW_ARGS / ROW_RECEIVER_SETS -- a count a row may only raise, and the chosen arguments
    and receiver set-up of the B and D rows the per-type values would leave weak.
  * NON_BIND_HELPERS -- the referenced `ObjectCalls` helpers that no generated method calls with a
    MethodBind: the runtime plumbing every row uses implicitly. A new one is unclassified until it
    is listed here.
  * STATIC_ARGS -- the argument list of each value-asserting static row (a made-up path makes
    `FileAccess.open` return null; a zero size makes `Image.create` return null). The skeleton
    holds the `extension_api.json` default where one exists and `None` where a value is still
    to be chosen.
  * SCRATCH_DIR / SCRATCH_POLICY -- the one place an exercise row may touch the filesystem.
  * VARIANT_TYPE_VALUES -- one value per Godot Variant type (GDScript literal + the Kotlin value the
    Variant path answers) for the T rows, and the paths a type has no row on, with the reason.
  * REACH_RECEIVERS / HAND_ROOT_REACH / REACH_AFTER -- the reach (F) rows' valid receivers where a fresh
    instance is not one, the calls of the hand-written GodotObject root, and what a row does after its call.
  * SCENE_ROWS -- methods no rule can reach or answer on a fresh object (a query needs a world, a target
    rotation a real joint), or on a class the exclusions remove but that is safe to call on this one
    method: each spells its row's Kotlin, against the hand-written worlds in exercise/ApiExerciseScenes.kt.

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
_DEVICE = "the desktop smoke's dummy renderer ignores it, a phone's renderer does not"
_NEW_OBJECT = "returns a new object per call: an oracle would compare two different objects"
_SHAPE_INDEX = (
    "a shape index the area does not have aborts the process: the server indexes the area's shapes "
    "with CRASH_BAD_INDEX and no bounds check before it (found by the headless sweep)"
)

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
    ("Tree", "get_item_area_rect"): ("F", "an item's area before any layout is the zero Rect2, which a no-op answers too"),
    ("Object", "is_connected"): (
        "F",
        "the hand-written root spells it isConnected(signal, target, method), not with Godot's Callable argument",
    ),
    ("PhysicsServer2D", "area_get_shape"): ("E", _SHAPE_INDEX),
    ("PhysicsServer2D", "area_get_shape_transform"): ("E", _SHAPE_INDEX),
    ("PhysicsServer2D", "body_get_shape"): ("E", _SHAPE_INDEX),
    ("PhysicsServer2D", "body_get_shape_transform"): ("E", _SHAPE_INDEX),
    ("PhysicsServer3D", "area_get_shape"): ("E", _SHAPE_INDEX),
    ("PhysicsServer3D", "area_get_shape_transform"): ("E", _SHAPE_INDEX),
    ("PhysicsServer3D", "body_get_shape"): ("E", _SHAPE_INDEX),
    ("PhysicsServer3D", "body_get_shape_transform"): ("E", _SHAPE_INDEX),
    ("PhysicsServer3D", "custom_shape_create"): ("F", "Godot Physics 3D has no custom shapes: it always returns RID() with an error"),
    ("ProjectSettings", "load_resource_pack"): ("E", "mounts a resource pack into res:// for the rest of the process"),
    # Reach rows that would change what the running game (a device self-test build) shows or hears, with
    # no getter to put it back: the exercise runs inside the game there, not in a process of its own.
    ("AudioServer", "set_input_device_active"): (
        "E",
        "starts capture from the host's microphone (a permission prompt on a phone; desktop only warns that "
        "audio/driver/enable_input is off)",
    ),
    ("RenderingServer", "set_boot_image"): ("E", f"draws the boot splash over the running game's frame: {_DEVICE}"),
    ("RenderingServer", "set_boot_image_with_stretch"): ("E", f"draws the boot splash over the running game's frame: {_DEVICE}"),
    ("RenderingServer", "texture_create_from_native_handle"): (
        "E",
        f"wraps a native GPU texture handle: a made-up one is undefined behaviour ({_DEVICE})",
    ),
    ("RenderingServer", "screen_space_roughness_limiter_set_active"): (
        "E",
        "sets a process-wide rendering setting of the running game, with no getter to restore it",
    ),
    ("RenderingServer", "environment_set_ssao_quality"): (
        "E",
        "sets a process-wide rendering setting of the running game, with no getter to restore it",
    ),
    ("RenderingServer", "environment_set_ssil_quality"): (
        "E",
        "sets a process-wide rendering setting of the running game, with no getter to restore it",
    ),
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
_HOST_DEVICE = "validated against the host's audio devices: a name the host lacks reads back as \"Default\""
_CLAMPED_BY_FRAMES = "validated against the node's SpriteFrames, which a fresh node lacks: the set value is dropped"
PAIR_OVERRIDES: dict[tuple[str, str], tuple[str | None, str]] = {
    ("AnimatedSprite2D", "animation"): (None, _CLAMPED_BY_FRAMES),
    ("AnimatedSprite2D", "frame"): (None, _CLAMPED_BY_FRAMES),
    ("AnimatedSprite3D", "animation"): (None, _CLAMPED_BY_FRAMES),
    ("AnimatedSprite3D", "frame"): (None, _CLAMPED_BY_FRAMES),
    ("AnimatedTexture", "current_frame"): (None, "clamped to the texture's frame count (1 on a fresh AnimatedTexture)"),
    ("AudioServer", "input_device"): (None, _HOST_DEVICE),
    ("AudioServer", "output_device"): (None, _HOST_DEVICE),
    ("AudioStreamMP3", "data"): ('fixture("mp3")', "keeps only bytes that decode as MP3"),
    ("CodeEdit", "indent_automatic_prefixes"): ('["@"]', "each prefix is one character (the setter keeps the first)"),
    ("CompressedTexture2D", "load_path"): (None, _LOADED_PATH),
    ("CompressedTexture3D", "load_path"): (None, _LOADED_PATH),
    ("CompressedTextureLayered", "load_path"): (None, _LOADED_PATH),
    ("StreamedTexture2D", "load_path"): (None, _LOADED_PATH),
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

SCRATCH_DIR = "user://kanama-api-exercise/<run>/"
SCRATCH_POLICY = (
    "The exercise runner creates its own directory `user://kanama-api-exercise/<pid>-<random>/` before its "
    "first row (with the fixture "
    "files the rows read: `fixture.txt` with an extended attribute, `fixture.mp3`, `fixture.wav`, and the "
    "directory `sub/`) and removes it after its last (and the shared parent once it is empty), so two runs "
    "of one project at once never touch each other's files. Every row whose method takes a path, of any case, "
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
    # The first volume: never "" on a desktop or iOS host; Android may report none (STATIC_ZERO_ON).
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

# (class, method) -> (platforms, reason) for a static row whose result can be its type's zero on those platforms
# for a reason of the host, not of the call: there a zero from both paths counts weak; a non-zero result passes
# and a zero fails everywhere else, as usual. Platforms: `ios`, `android`, `desktop`.
STATIC_ZERO_ON: dict[tuple[str, str], tuple[tuple[str, ...], str]] = {
    ("DirAccess", "get_drive_name"): (
        ("android",),
        "Android's filesystem DirAccess (DirAccessJAndroid) names a storage volume only from API 30, and a device "
        "can list none: below API 30 every drive name is \"\"",
    ),
}

# (singleton, property) -> reason: an integer singleton property whose row sets `before + 1` instead of a
# value of its own choosing, because a smaller value deletes state the project owns.
PAIR_GROW: dict[tuple[str, str], str] = {
    ("AudioServer", "bus_count"): "fewer buses than the project has deletes the project's buses; one more is removed again by the restore",
}

# ---- B and D row arguments ------------------------------------------------------------------------------

# (class, method) -> one Godot literal per REQUIRED argument of a B, D or F row (the defaulted ones keep
# their defaults), replacing the per-type values where those make a B or D row weak (an empty answer that a
# no-op could give too) or where an F row's argument has no fresh value. The literals are those of
# STATIC_ARGS (`Class.new()` is a fresh instance), plus `receiver.<method>()` (an object the row's own
# receiver makes). `--check` requires each entry to name a B, D or F row and give one value per required
# argument.
_BOX = (
    "[Plane(1, 0, 0, 1), Plane(-1, 0, 0, 1), Plane(0, 1, 0, 1), Plane(0, -1, 0, 1), Plane(0, 0, 1, 1), "
    "Plane(0, 0, -1, 1)]"
)
ROW_ARGS: dict[tuple[str, str], tuple[str, ...]] = {
    # Real classes and members: the answer is the class's own description, not "no such class".
    ("ClassDB", "class_get_property"): ("Node2D.new()", '"scale"'),
    ("ClassDB", "class_get_method_list"): ('"Node2D"',),
    ("ClassDB", "class_get_enum_list"): ('"Node"',),
    ("ClassDB", "class_has_enum"): ('"Node"', '"ProcessMode"'),
    ("ClassDB", "class_get_method_argument_count"): ('"Node"', '"add_child"'),
    ("ClassDB", "class_get_enum_constants"): ('"Node"', '"ProcessMode"'),
    ("ClassDB", "class_get_integer_constant_enum"): ('"Node"', '"PROCESS_MODE_ALWAYS"'),
    ("ClassDB", "class_get_signal"): ('"Node"', '"ready"'),
    ("ClassDB", "class_get_integer_constant"): ('"Node"', '"NOTIFICATION_READY"'),
    ("ClassDB", "class_get_property_default_value"): ('"Node2D"', '"scale"'),
    # Valid input: the int 7 as Godot encodes a Variant, and "kanama125" in base64.
    ("Marshalls", "base64_to_variant"): ('"AgAAAAcAAAA="',),
    ("Marshalls", "base64_to_raw"): ('"a2FuYW1hMTI1"',),
    ("Time", "get_unix_time_from_datetime_dict"): ('{"year": 2026, "month": 10, "day": 17, "hour": 12, "minute": 30, "second": 15}',),
    ("TranslationServer", "compare_locales"): ('"en_US"', '"en"'),
    ("InputEvent", "is_action"): ('"ui_accept"',),
    ("InputEvent", "get_action_strength"): ('"ui_accept"',),
    ("InputEvent", "is_action_pressed"): ('"ui_accept"',),
    # Shapes that meet: an intersection, not "no intersection".
    ("Geometry2D", "line_intersects_line"): ("Vector2(0, 0)", "Vector2(1, 1)", "Vector2(0, 2)", "Vector2(1, -1)"),
    ("Geometry2D", "is_polygon_clockwise"): ("[Vector2(0, 0), Vector2(0, 4), Vector2(4, 4), Vector2(4, 0)]",),
    ("Geometry2D", "clip_polygons"): (
        "[Vector2(0, 0), Vector2(4, 0), Vector2(4, 4), Vector2(0, 4)]",
        "[Vector2(2, 2), Vector2(6, 2), Vector2(6, 6), Vector2(2, 6)]",
    ),
    ("Geometry3D", "ray_intersects_triangle"): (
        "Vector3(0.25, 0.25, 1)", "Vector3(0, 0, -1)", "Vector3(-1, -1, 0)", "Vector3(1, -1, 0)", "Vector3(0, 1, 0)",
    ),
    ("Geometry3D", "get_triangle_barycentric_coords"): (
        "Vector3(0.25, 0.25, 0)", "Vector3(-1, -1, 0)", "Vector3(1, -1, 0)", "Vector3(0, 1, 0)",
    ),
    ("Geometry3D", "segment_intersects_sphere"): ("Vector3(-2, 0.25, 0)", "Vector3(2, 0.25, 0)", "Vector3(0, 0, 0)", "1.0"),
    ("Geometry3D", "segment_intersects_cylinder"): ("Vector3(-2, 0.25, 0)", "Vector3(2, 0.25, 0)", "2.0", "1.0"),
    ("Geometry3D", "segment_intersects_convex"): ("Vector3(-2, 0.25, 0.5)", "Vector3(2, 0.25, 0.5)", _BOX),
    ("Geometry2D", "point_is_inside_triangle"): ("Vector2(1, 1)", "Vector2(0, 0)", "Vector2(4, 0)", "Vector2(0, 4)"),
    # A quarter turn about Z (index 0 is the identity) and a cell away from the origin.
    ("GridMap", "get_orthogonal_index_from_basis"): ("Basis(Vector3(0, 1, 0), Vector3(-1, 0, 0), Vector3(0, 0, 1))",),
    ("GridMap", "local_to_map"): ("Vector3(5, 7, -3)",),
    # Inside the region ROW_RECEIVER_SETS gives the grid.
    ("AStarGrid2D", "is_in_bounds"): ("7", "3"),  # (3, 7) is outside the 8 x 4 region
    ("AStarGrid2D", "is_in_boundsv"): ("Vector2i(7, 3)",),
    ("Translation", "get_message"): ('"kanama"',),
    ("TextEdit", "get_line_wrapped_text"): ("1",),  # the second line: a swapped index reads "a"
    ("CanvasItem", "get_visibility_layer_bit"): ("3",),  # layer bit 3 of 8, not the default bit 0
    # Reach rows. `receiver.<method>()` is an argument the row's own receiver makes.
    ("Tree", "get_item_area_rect"): ("receiver.create_item()",),
    # A property that exists and a value of its type: a tweener for a missing property, or a `from` value
    # of another type than the final one, is refused with null (which the required return turns into a throw).
    ("Tween", "tween_property"): ("Node.new()", 'NodePath("process_priority")', "7", "0.75"),
    ("PropertyTweener", "from"): ("3",),
}

# (class, method) -> ((property, Godot literal), ...): set on a B row's fresh receiver through the Variant
# path (`Object.set`) before the call, where a fresh object's empty state makes every answer zero.
_UI_ACCEPT = (("action", '"ui_accept"'), ("pressed", "true"))
ROW_RECEIVER_SETS: dict[tuple[str, str], tuple[tuple[str, str], ...]] = {
    ("InputEvent", "is_action"): _UI_ACCEPT,
    ("InputEvent", "get_action_strength"): _UI_ACCEPT,
    ("InputEvent", "is_action_pressed"): _UI_ACCEPT,
    ("AStarGrid2D", "is_in_bounds"): (("region", "Rect2i(0, 0, 8, 4)"),),
    ("AStarGrid2D", "is_in_boundsv"): (("region", "Rect2i(0, 0, 8, 4)"),),
    ("Translation", "get_message"): (("messages", '{"kanama": "125"}'),),
    ("TextEdit", "get_line_wrapped_text"): (("text", '"a\\nkanama125"'),),
    ("Control", "get_cursor_shape"): (("mouse_default_cursor_shape", "Control.CURSOR_IBEAM"),),
    ("Control", "get_tooltip"): (("tooltip_text", '"kanama125"'),),
    ("CanvasItem", "get_visibility_layer_bit"): (("visibility_layer", "8"),),
}

# ---- singleton rows -------------------------------------------------------------------------------------

# Singletons whose property pairs run as set/restore rows (case A on the singleton): the row reads the
# value, sets a recognisable other one, reads it back through the getter and `Object.get`, and sets the
# value it read first back in a `finally`, so a failing row leaves the process as it found it. Every
# other singleton's setters stay reach-only: their state is the running game's.
SINGLETON_PAIRS: dict[str, str] = {
    "Engine": "process-wide engine settings, restored by the row (frame rate caps, time scale, tick rate, ...)",
    "AudioServer": "the audio mix settings, restored by the row (bus count, playback speed)",
}

# (singleton, setter) -> (getter, Godot literal of the key both take, reason): a keyed set/get method pair
# of a singleton without properties, run like SINGLETON_PAIRS. The key is one no project defines, and the
# row's restore (`null`) erases it again.
SINGLETON_KEYED_PAIRS: dict[tuple[str, str], tuple[str, str, str]] = {
    ("ProjectSettings", "set_setting"): (
        "get_setting",
        '"kanama/api_exercise/probe"',
        "a project setting no project defines; setting it back to null erases it",
    ),
}

# Servers whose argument-free `*_create` / `create_*` methods returning a RID run as factory rows: create,
# require a valid (non-zero) RID, read one const `<prefix>_get_*` / `<prefix>_is_*` of it through the
# wrapper and the Variant path where the server has one, and free the RID with the named method in a
# `finally`. The same factories make the RID arguments of the server's B and D rows (RID_ARG_SOURCES), so
# those rows read a live object instead of an invalid RID. server -> (free method, reason).
RID_FACTORIES: dict[str, tuple[str, str]] = {
    "RenderingServer": ("free_rid", "the rendering server's resources: meshes, textures, shaders, viewports, canvas items, ..."),
    "NavigationServer2D": ("free_rid", "navigation maps, regions, links, agents and obstacles"),
    "NavigationServer3D": ("free_rid", "navigation maps, regions, links, agents and obstacles"),
    "PhysicsServer2D": ("free_rid", "physics spaces, shapes, bodies, areas and joints"),
    "PhysicsServer3D": ("free_rid", "physics spaces, shapes, bodies, areas and joints"),
    "TextServer": ("free_rid", "fonts and shaped texts of the running text server"),
}

# (class, argument name) -> "Server.factory": the RID argument of a B or D row that the rule (an argument
# `x` takes the class's own argument-free `x_create`) cannot name. The RID is freed after the row with the
# server's RID_FACTORIES free method.
RID_ARG_SOURCES: dict[tuple[str, str], str] = {
    ("TextServer", "font_rid"): "TextServer.create_font",
    ("TextServer", "font"): "TextServer.create_font",
    ("TextServer", "shaped"): "TextServer.create_shaped_text",
    ("RenderingServer", "texture"): "RenderingServer.texture_2d_placeholder_create",
    ("Font", "canvas_item"): "RenderingServer.canvas_item_create",
}

# ---- known findings ------------------------------------------------------------------------------------

# (case, row) -> (platforms, failure, reason, tracking): a row that fails on those platforms for a gap
# already tracked elsewhere. The row still runs there. Only a failure in the row's Variant-path half (a
# C row's `ClassDB.class_call_static` and the comparison with it, after the wrapper's own result passed
# its checks; an A row's getter and `Object.get` read-backs) whose message contains `failure` prints as
# `KNOWN:` (not `FAIL:`) and counts as `known`; any other failure of the row fails. A known row that
# PASSES fails the run ("no longer reproduces: remove it"), as a quarantine does, so an entry cannot
# outlive its gap. `row` is the label the generated row reports (`Class.method` for a static,
# `Class.property` for a pair); the platforms are `ios`, `android` and `desktop`. Example entry:
#   ("C", "Image.create_from_data"): (("ios",), "unsupported arg type ByteArray",
#       "the iOS Variant path cannot encode a PackedByteArray argument", "task 140"),
# (The nine iOS entries of the first device run went with the encoders that fixed them.)
KNOWN_FINDINGS: dict[tuple[str, str], tuple[tuple[str, ...], str, str, str]] = {}

# ---- Variant type rows ----------------------------------------------------------------------------------

# One value per Godot Variant type (the generator reads the types from `Variant.Type` in extension_api.json,
# so a type a later engine adds fails --check until it has an entry). type -> (GDScript literal, the Kotlin
# value the generic Variant path must answer for it, {path: reason} for the paths it has no row on). The
# rows (case T) run each value through every generic Variant path of the runtime, against a value the
# ENGINE built or compared (an `Expression` evaluates the literal), so an encode/decode pair that is wrong
# the same way in both directions cannot pass:
#   ret   the literal evaluated by the Expression comes back as the Kotlin value (the Variant return decode);
#   elem  the same literal inside an Array and a Dictionary (the container return decode);
#   arg   the Kotlin value passed to `set_meta` through `Object.call`, at the top level and inside an Array
#         and a Dictionary, compared with the literal by the engine (the Variant argument encoders);
#   in    the literal emitted by the engine to a Variant signal argument of a Kotlin lambda, at the top
#         level and inside an Array and a Dictionary (the inbound argument decode).
# `r.typeObject()` is the row's probe node (the Expression's base instance, `self` in the literal).
_NO_ARG_VALUE = (
    "Kotlin has no value of its own for it (a List encodes as an Array, a String as a String), so the engine's "
    "comparison with the literal would compare another type"
)
_DECODES_NULL = "decodes to null through the Variant path on every backend: there is no value to pass back"
_NO_INBOUND = (
    "a Callable or Signal signal argument decodes to null on desktop and is refused with an error naming the "
    "type on iOS (docs/contributing/backends/ios.md): the backends answer differently by design"
)
VARIANT_TYPE_VALUES: dict[str, tuple[str, str, dict[str, str]]] = {
    "Nil": ("null", "null", {"arg": "set_meta(name, null) erases the entry: nothing to compare"}),
    "bool": ("true", "true", {}),
    "int": ("7", "7L", {}),
    "float": ("0.5", "0.5", {}),
    "String": ('"kanama"', '"kanama"', {}),
    "Vector2": ("Vector2(0.5, 1.5)", "Vector2(0.5, 1.5)", {}),
    "Vector2i": ("Vector2i(-3, 4)", "Vector2i(-3, 4)", {}),
    "Rect2": ("Rect2(0.5, 1.5, 2.25, 3.125)", "Rect2(Vector2(0.5, 1.5), Vector2(2.25, 3.125))", {}),
    "Rect2i": ("Rect2i(-1, 2, 30, 40)", "Rect2i(Vector2i(-1, 2), Vector2i(30, 40))", {}),
    "Vector3": ("Vector3(1, 2.5, -3)", "Vector3(1.0, 2.5, -3.0)", {}),
    "Vector3i": ("Vector3i(7, -8, 9)", "Vector3i(7, -8, 9)", {}),
    "Transform2D": (
        "Transform2D(Vector2(1, 2), Vector2(3, 4), Vector2(5, 6))",
        "Transform2D(Vector2(1.0, 2.0), Vector2(3.0, 4.0), Vector2(5.0, 6.0))",
        {},
    ),
    "Vector4": ("Vector4(0.5, -0.25, 8, 2)", "Vector4(0.5, -0.25, 8.0, 2.0)", {}),
    "Vector4i": ("Vector4i(-2147483648, 2147483647, 0, 7)", "Vector4i(Int.MIN_VALUE, Int.MAX_VALUE, 0, 7)", {}),
    "Plane": ("Plane(0, 1, 0, 2.5)", "Plane(Vector3(0.0, 1.0, 0.0), 2.5)", {}),
    "Quaternion": ("Quaternion(0, 0.6, 0, 0.8)", "Quaternion(0.0, 0.6, 0.0, 0.8)", {}),
    "AABB": ("AABB(Vector3(-1, -2, -3), Vector3(4, 5, 6))", "AABB(Vector3(-1.0, -2.0, -3.0), Vector3(4.0, 5.0, 6.0))", {}),
    "Basis": (
        "Basis(Vector3(1, 0, 0), Vector3(0, 0, 1), Vector3(0, -1, 0))",
        "Basis(Vector3(1.0, 0.0, 0.0), Vector3(0.0, 0.0, 1.0), Vector3(0.0, -1.0, 0.0))",
        {},
    ),
    "Transform3D": (
        "Transform3D(Basis(Vector3(1, 2, 3), Vector3(4, 5, 6), Vector3(7, 8, 9)), Vector3(10, 11, 12))",
        "Transform3D(Basis(Vector3(1.0, 2.0, 3.0), Vector3(4.0, 5.0, 6.0), Vector3(7.0, 8.0, 9.0)), Vector3(10.0, 11.0, 12.0))",
        {},
    ),
    "Projection": (
        "Projection(Vector4(1, 2, 3, 4), Vector4(5, 6, 7, 8), Vector4(9, 10, 11, 12), Vector4(13, 14, 15, 16))",
        "Projection(Vector4(1.0, 2.0, 3.0, 4.0), Vector4(5.0, 6.0, 7.0, 8.0), Vector4(9.0, 10.0, 11.0, 12.0), "
        "Vector4(13.0, 14.0, 15.0, 16.0))",
        {},
    ),
    "Color": ("Color(0.5, 0.25, 0, 1)", "Color(0.5, 0.25, 0.0, 1.0)", {}),
    "StringName": ('StringName("sn")', '"sn"', {"arg": _NO_ARG_VALUE}),
    "NodePath": ('NodePath("A/B")', 'NodePath("A/B")', {}),
    "RID": ("rid_from_int64(77)", "RID(77L)", {}),
    "Object": ("self", "r.typeObject()", {}),
    "Callable": ('Callable(self, "get_name")', "null", {"arg": _DECODES_NULL, "in": _NO_INBOUND}),
    "Signal": ('Signal(self, "ready")', "null", {"arg": _DECODES_NULL, "in": _NO_INBOUND}),
    "Dictionary": ('{"k": 1}', 'mapOf("k" to 1L)', {}),
    "Array": ('[1, "s"]', 'listOf(1L, "s")', {}),
    "PackedByteArray": ("PackedByteArray([1, 2, 255])", "byteArrayOf(1, 2, -1)", {}),
    "PackedInt32Array": ("PackedInt32Array([1, -2])", "listOf(1, -2)", {"arg": _NO_ARG_VALUE}),
    "PackedInt64Array": ("PackedInt64Array([1, -2])", "listOf(1L, -2L)", {"arg": _NO_ARG_VALUE}),
    "PackedFloat32Array": ("PackedFloat32Array([0.5, 2])", "listOf(0.5f, 2f)", {"arg": _NO_ARG_VALUE}),
    "PackedFloat64Array": ("PackedFloat64Array([0.5, 2])", "listOf(0.5, 2.0)", {"arg": _NO_ARG_VALUE}),
    "PackedStringArray": ('PackedStringArray(["a", "bb"])', 'listOf("a", "bb")', {"arg": _NO_ARG_VALUE}),
    "PackedVector2Array": ("PackedVector2Array([Vector2(1, 2)])", "listOf(Vector2(1.0, 2.0))", {"arg": _NO_ARG_VALUE}),
    "PackedVector3Array": ("PackedVector3Array([Vector3(1, 2, 3)])", "listOf(Vector3(1.0, 2.0, 3.0))", {"arg": _NO_ARG_VALUE}),
    "PackedColorArray": ("PackedColorArray([Color(0.5, 0.25, 0, 1)])", "listOf(Color(0.5, 0.25, 0.0, 1.0))", {"arg": _NO_ARG_VALUE}),
    "PackedVector4Array": ("PackedVector4Array([Vector4(1, 2, 3, 4)])", "listOf(Vector4(1.0, 2.0, 3.0, 4.0))", {"arg": _NO_ARG_VALUE}),
}

# ---- reach rows -------------------------------------------------------------------------------------

# helper -> (Kotlin statements on `o`, a fresh Object, reason): the reach row of a helper only the
# hand-written GodotObject root calls, in a spelling the generator cannot derive from Godot's arguments
# (the root splits Godot's Callable into a target object and a method name). `r.typeObject()` is the run's
# probe node, a live connection target; each row connects first where it disconnects or queries.
_SPLIT_CALLABLE = "the hand-written root splits Godot's Callable argument into (target, method)"
HAND_ROOT_REACH: dict[str, tuple[tuple[str, ...], str]] = {
    "ptrcallWithStringNameCallableAndUInt32ArgsRetLong": (
        ('r.reached(o.connect("script_changed", r.typeObject(), "get_instance_id"))',),
        _SPLIT_CALLABLE,
    ),
    "ptrcallWithStringNameBoundCallableAndUInt32ArgsRetLong": (
        ('r.reached(o.connectBound("script_changed", r.typeObject(), "get_instance_id", listOf(7L)))',),
        _SPLIT_CALLABLE + "; connectBound is the bound-argument form `connect` uses",
    ),
    "ptrcallWithStringNameAndCallableArgs": (
        (
            'o.connect("script_changed", r.typeObject(), "get_instance_id")',
            'o.disconnect("script_changed", r.typeObject(), "get_instance_id")',
            "r.reached()",
        ),
        _SPLIT_CALLABLE,
    ),
    "ptrcallWithStringNameAndBoundCallableArgs": (
        (
            'o.connectBound("script_changed", r.typeObject(), "get_instance_id", listOf(7L))',
            'o.disconnectBound("script_changed", r.typeObject(), "get_instance_id", listOf(7L))',
            "r.reached()",
        ),
        _SPLIT_CALLABLE + "; disconnectBound is the bound-argument form `disconnect` uses",
    ),
    "ptrcallWithStringNameAndCallableArgsRetBool": (
        (
            'o.connect("script_changed", r.typeObject(), "get_instance_id")',
            'r.reached(o.isConnected("script_changed", r.typeObject(), "get_instance_id"))',
        ),
        _SPLIT_CALLABLE,
    ),
}

# class -> (Kotlin expression, reason): the receiver of the reach rows of a class whose fresh instance is not
# a valid one (Godot prints "can't be created directly" and its methods answer null). The expressions use
# the runner: `r.tween()` is a Tween of the running SceneTree (`create_tween()`), killed when the row ends.
_TWEEN_MADE = "can't be created directly: a fresh one is invalid and its tween_* / from() calls answer null"
REACH_RECEIVERS: dict[str, tuple[str, str]] = {
    "Tween": ("r.tween()", f"a Tween not made by create_tween() {_TWEEN_MADE}"),
    "PropertyTweener": (
        'r.tween().tweenProperty(r.typeObject(), NodePath("process_priority"), 7L, 1.0)',
        f"a PropertyTweener not made by Tween.tween_property {_TWEEN_MADE}",
    ),
    "AwaitTweener": ("r.tween().tweenAwait(r.probeSignal())", f"an AwaitTweener not made by Tween.tween_await {_TWEEN_MADE}"),
}

# (class, method) -> (Kotlin statement run after the call, with the row's arguments `a0`, `a1`, ..., reason):
# what the engine expects a caller to do after a reach row's call, so the row leaves nothing behind.
REACH_AFTER: dict[tuple[str, str], tuple[str, str]] = {
    ("RenderingServer", "global_shader_parameter_add"): (
        "RenderingServer.globalShaderParameterRemove(a0)",
        "the parameter is process-wide state of the running game: the row removes the one it added",
    ),
    ("ResourceLoader", "load_threaded_request"): (
        "r.track(ResourceLoader.loadThreadedGet(a0))",
        "a request keeps its load token (a RefCounted) until load_threaded_get collects it: without that it "
        "leaks at exit (found by the runtime smoke's leak check)",
    ),
}

# ---- scene rows ------------------------------------------------------------------------------------------


def _into(dim: str, method: str, query: str, result: str, reads: tuple[str, ...], set_reads: tuple[str, ...]) -> tuple:
    """One physics `*_into` row: the wrapper fills [result] from [query] on the row's world, `Object.call`
    fills a second one from the same query. Both hit; every read agrees; each of [set_reads] is not its type's
    zero, so a result a call left untouched fails; neither object changed its reference count."""
    camel = method.split("_")[0] + "".join(word.title() for word in method.split("_")[1:])
    lines = [
        f"val w = r.physics{dim}()",
        f"val q = w.{query}()",
        f"val a = r.track({result}.create())",
        f"val b = r.track({result}.create())",
        "val queryRefs = q.getReferenceCount()",
        "val resultRefs = a.getReferenceCount()",
        f"val got = w.state.{camel}(q, a)",
        "r.wrapperValue(got, false)",
        "r.sameReferences(q, queryRefs)",
        "r.sameReferences(a, resultRefs)",
    ]
    lines += [f"r.wrapperValue(a.{read}, false)" for read in set_reads]
    lines.append("r.oracle()")
    lines.append(f'val oracle = r.track(w.state.call("{method}", q, b))')
    lines += [f'r.readBack(a.{read}, b.{read}, "{read}")' for read in reads]
    lines.append("r.sameAsCall(got, oracle)")
    return ("B", "fills its result object from a query on a world with one static box", tuple(lines))


def _into_rows(dim: str) -> dict[tuple[str, str], tuple]:
    owner = f"PhysicsDirectSpaceState{dim}"
    collider = ("getColliderRid()", "getColliderId()", "getColliderShape()", "getCollider()")
    listed = ("getIntersectionCount()", "getColliderRid(0)", "getColliderId(0)", "getColliderShape(0)", "getCollider(0)")
    shared = ("getIntersectionCount()", "getColliderRid(0)", "getColliderId(0)", "getCollider(0)")
    ray_extra = ("getColliderFaceIndex()",) if dim == "3D" else ()
    return {
        (owner, "intersect_ray_into"): _into(
            dim, "intersect_ray_into", "rayQuery", f"PhysicsIntersectRayResult{dim}",
            ("getPosition()", "getNormal()", *collider, *ray_extra),
            ("getPosition()", "getNormal()", "getColliderRid()", "getColliderId()", "getCollider()"),
        ),
        (owner, "intersect_point_into"): _into(
            dim, "intersect_point_into", "pointQuery", f"PhysicsIntersectPointResult{dim}", listed, shared,
        ),
        (owner, "intersect_shape_into"): _into(
            dim, "intersect_shape_into", "overlapQuery", f"PhysicsIntersectShapeResult{dim}", listed, shared,
        ),
        (owner, "cast_motion_into"): _into(
            dim, "cast_motion_into", "castQuery", f"PhysicsCastMotionResult{dim}",
            ("getSafeFraction()", "getUnsafeFraction()"), ("getSafeFraction()", "getUnsafeFraction()"),
        ),
        (owner, "collide_shape_into"): _into(
            dim, "collide_shape_into", "touchQuery", f"PhysicsCollideShapeResult{dim}",
            ("getCollisionCount()", "getPointOnQueriedShape(0)", "getPointOnCollidingShape(0)"),
            ("getCollisionCount()", "getPointOnQueriedShape(0)", "getPointOnCollidingShape(0)"),
        ),
        (owner, "get_rest_info_into"): _into(
            dim, "get_rest_info_into", "touchQuery", f"PhysicsGetRestInfoResult{dim}",
            ("getPoint()", "getNormal()", *collider[:3], "getColliderVelocity()"),
            ("getPoint()", "getNormal()", "getColliderRid()", "getColliderId()"),
        ),
    }


def _xr(owner: str, method: str, args: str) -> tuple:
    """An XR read on the interface that needs no headset, initialized for the row. Godot answers two views."""
    camel = method.split("_")[0] + "".join(word.title() for word in method.split("_")[1:])
    if owner == "XRServer":
        receiver, oracle = "XRServer", 'r.singleton("XRServer")'
    else:
        receiver, oracle = "i", "i"
    return (
        "B" if owner == "XRInterface" else "D",
        "an XR read: a fresh or uninitialized interface answers an empty list",
        (
            "val i = r.xrInterface()",
            "if (i == null) {",
            '  r.weakBecause("this build has no Native mobile XR interface")',
            "} else {",
            f"  val got = {receiver}.{camel}({args})",
            "  r.wrapperValue(got, false)",
            "  r.oracle()",
            f'  val oracle = r.track({oracle}.call("{method}", {args}))',
            "  r.sameAsCall(got, oracle)",
            "}",
        ),
    )


_XR_PROJECTIONS = '"head", 1.5, 0.1, 100.0'
_XR_OFFSETS = '"head"'
_TARGET = "Quaternion(0.0, 0.0, 0.6, 0.8)"

# (class, method) -> (case, reason, the row's Kotlin). The case is the one the row's checks amount to: B or D
# compares with `Object.call`, F only returns. A row ends in the same verdict calls as its case's generated
# rows. An exclusion does not apply to a method listed here: its reason says why this method is safe where
# the class as a whole is not.
SCENE_ROWS: dict[tuple[str, str], tuple[str, str, tuple[str, ...]]] = {
    **_into_rows("2D"),
    **_into_rows("3D"),
    ("Image", "generate_mipmaps"): (
        "B",
        "a real image: the mipmaps are the result, the Error of a fresh empty image is the failure path",
        (
            "val o = r.track(checkNotNull(Image.createEmpty(8, 8, false, Image.Format.RGBA8)))",
            "val p = r.track(checkNotNull(Image.createEmpty(8, 8, false, Image.Format.RGBA8)))",
            "val got = o.generateMipmaps(true, true, 0.25)",
            "r.wrapperValue(o.hasMipmaps(), false)",
            "r.oracle()",
            'val oracle = p.call("generate_mipmaps", true, true, 0.25)',
            'r.readBack(got, oracle, "generate_mipmaps")',
            "r.sameAsCall(o.hasMipmaps(), p.hasMipmaps())",
        ),
    ),
    ("PhysicsServer3D", "generic_6dof_joint_get_angular_target_rotation"): (
        "D",
        "a real 6DOF joint: a fresh one answers with an engine error. Only Jolt keeps the target",
        (
            "val j = r.joint6dof()",
            'val s = r.singleton("PhysicsServer3D")',
            f"val target = {_TARGET}",
            "PhysicsServer3D.generic6dofJointSetAngularTargetRotation(j.joint, target)",
            "val got = PhysicsServer3D.generic6dofJointGetAngularTargetRotation(j.joint)",
            'if (ProjectSettings.getSetting("physics/3d/physics_engine") == "Jolt Physics") {',
            '  r.readBack(got, target, "the target just set")',
            "}",
            "r.oracle()",
            's.call("generic_6dof_joint_set_angular_target_rotation", j.joint, target)',
            'val oracle = s.call("generic_6dof_joint_get_angular_target_rotation", j.joint)',
            "r.sameAsCall(got, oracle)",
        ),
    ),
    ("PhysicsServer3D", "generic_6dof_joint_set_angular_target_rotation"): (
        "F",
        "a real 6DOF joint; the read-back is the getter's row",
        (
            "val j = r.joint6dof()",
            f"PhysicsServer3D.generic6dofJointSetAngularTargetRotation(j.joint, {_TARGET})",
            "r.reached()",
        ),
    ),
    ("RenderingServer", "camera_set_xr_projections"): (
        "F",
        "two views with offsets, the shape a headset uses; the server has no getter for them",
        (
            "var camera = RID(0L)",
            "try {",
            "  camera = RenderingServer.cameraCreate()",
            "  val view = Projection(Vector4(1.0, 0.0, 0.0, 0.0), Vector4(0.0, 1.0, 0.0, 0.0), "
            "Vector4(0.0, 0.0, -1.0, -1.0), Vector4(0.0, 0.0, -0.25, 0.0))",
            "  val left = Transform3D(Transform3D.IDENTITY.basis, Vector3(-0.03, 0.0, 0.0))",
            "  val right = Transform3D(Transform3D.IDENTITY.basis, Vector3(0.03, 0.0, 0.0))",
            "  RenderingServer.cameraSetXrProjections(camera, listOf(view, view), listOf(left, right))",
            "  r.reached()",
            "} finally {",
            "  if (camera.value != 0L) RenderingServer.freeRid(camera)",
            "}",
        ),
    ),
    ("XRInterface", "get_camera_projections"): _xr("XRInterface", "get_camera_projections", _XR_PROJECTIONS),
    ("XRInterface", "get_camera_offsets"): _xr("XRInterface", "get_camera_offsets", _XR_OFFSETS),
    ("XRServer", "get_camera_projections"): _xr("XRServer", "get_camera_projections", _XR_PROJECTIONS),
    ("XRServer", "get_camera_offsets"): _xr("XRServer", "get_camera_offsets", _XR_OFFSETS),
    ("DisplayServer", "get_display_cutouts"): (
        "D",
        "a read of the screen, safe on every host: empty, so weak, without a notch",
        (
            'val s = r.singleton("DisplayServer")',
            "val got = DisplayServer.getDisplayCutouts(-1)",
            "r.oracle()",
            'val oracle = r.track(s.call("get_display_cutouts", -1))',
            "r.sameAsCall(got, oracle)",
        ),
    ),
    ("DisplayServer", "window_set_ime_rect"): (
        "F",
        "a hint for where the IME draws: nothing shows while no text field has focus",
        (
            "DisplayServer.windowSetImeRect(Rect2i(Vector2i(1, 2), Vector2i(3, 4)), 0)",
            "r.reached()",
        ),
    ),
    ("Input", "get_joy_touchpad_finger_pressure"): (
        "D",
        "a read of a device no host has: -1, which a no-op cannot answer",
        (
            'val s = r.singleton("Input")',
            "val got = Input.getJoyTouchpadFingerPressure(7, 3, 5)",
            "r.oracle()",
            'val oracle = s.call("get_joy_touchpad_finger_pressure", 7, 3, 5)',
            "r.sameAsCall(got, oracle)",
        ),
    ),
    ("RenderingDevice", "texture_create_shared_from_slice"): (
        "B",
        "a slice of a real texture: weak where the renderer has no RenderingDevice (headless, Compatibility)",
        (
            "val rd = RenderingServer.getRenderingDevice()",
            "if (rd == null) {",
            '  r.weakBecause("this renderer has no RenderingDevice")',
            "} else {",
            "  val format = r.track(RDTextureFormat.create())",
            "  format.width = 4L",
            "  format.height = 4L",
            "  format.format = RenderingDevice.DataFormat.R8G8B8A8_UNORM",
            "  format.usageBits = RenderingDevice.TextureUsageBits(129L)",
            "  val view = r.track(RDTextureView.create())",
            "  val texture = rd.textureCreate(format, view, emptyList())",
            "  if (texture.value == 0L) {",
            '    r.weakBecause("the RenderingDevice made no texture")',
            "  } else {",
            "    try {",
            "      val got = rd.textureCreateSharedFromSlice(view, texture, 0L, 0L, 1L, "
            "RenderingDevice.TextureSliceType.SLICE_2D, 1L)",
            "      r.wrapperValue(got, false)",
            "      if (got.value != 0L) rd.freeRid(got)",
            "      r.oracle()",
            '      val oracle = rd.call("texture_create_shared_from_slice", view, texture, 0L, 0L, 1L, 0L, 1L) as RID',
            "      r.wrapperValue(oracle, false)",
            "      if (oracle.value != 0L) rd.freeRid(oracle)",
            "      r.sameAsCall(got.value != 0L, oracle.value != 0L)",
            "    } finally {",
            "      rd.freeRid(texture)",
            "    }",
            "  }",
            "}",
        ),
    ),
    ("EditorExportPlugin", "add_apple_embedded_platform_spm_package"): (
        "F",
        "instantiable only in the editor: weak in a game. The plugin only stores the package",
        (
            'if (!ClassDB.canInstantiate("EditorExportPlugin")) {',
            '  r.weakBecause("EditorExportPlugin can only be instantiated by the editor")',
            "} else {",
            '  val o = r.ref(EditorExportPlugin(r.construct("EditorExportPlugin")))',
            '  o.addAppleEmbeddedPlatformSpmPackage("https://example.org/kanama", "1.0.0", listOf("Kanama"))',
            "  r.reached()",
            "}",
        ),
    ),
    ("DisplayServer", "send_toast_notification"): (
        "F",
        "only Windows shows a toast, so a Windows host skips the call; elsewhere Godot answers "
        "INVALID_NOTIFICATION_ID (-1), which a no-op cannot",
        (
            'if (OS.getName() == "Windows") {',
            '  r.weakBecause("a Windows desktop would show the toast")',
            "} else {",
            '  val got = DisplayServer.sendToastNotification("Kanama", "API exercise", null, r.noopCallable())',
            '  r.readBack(got, -1, "send_toast_notification")',
            "  r.reached()",
            "}",
        ),
    ),
    ("OpenXRSpatialEntityExtension", "create_spatial_context"): (
        "F",
        "needs OpenXR on, or the singleton is missing (weak); without a running session Godot answers null",
        (
            'if (!Engine.hasSingleton("OpenXRSpatialEntityExtension")) {',
            '  r.weakBecause("OpenXR is off: no OpenXRSpatialEntityExtension singleton")',
            "} else {",
            '  val e = OpenXRSpatialEntityExtension(checkNotNull(Engine.getSingleton("OpenXRSpatialEntityExtension")).handle)',
            "  r.reached(e.createSpatialContext(emptyList(), null, r.noopCallable(), r.noopCallable()))",
            "}",
        ),
    ),
}
