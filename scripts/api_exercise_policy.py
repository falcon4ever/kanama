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
  * NON_BIND_HELPERS -- the referenced `ObjectCalls` helpers that no generated method calls with a
    MethodBind: the runtime plumbing every row uses implicitly. A new one is unclassified until it
    is listed here.
  * STATIC_ARGS -- the argument list of each value-asserting static row (a made-up path makes
    `FileAccess.open` return null; a zero size makes `Image.create` return null). The skeleton
    holds the `extension_api.json` default where one exists and `None` where a value is still
    to be chosen.
  * SCRATCH_DIR / SCRATCH_POLICY -- the one place an exercise row may touch the filesystem.

`generate_api_exercise.py --check` fails when an entry here names a class or method the API lacks,
when an entry matches nothing, and when a referenced helper is classified by nothing.
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
    "AcceptDialog": _WINDOW,
    "ConfirmationDialog": _WINDOW,
    "FileDialog": _WINDOW + "; browses the host filesystem",
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
    "TCP": _NETWORK,
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
    "Compositor": _FRAME,
    "Popup": _WINDOW,
    "GDScript": "GDScript language internals",
    "ScriptLanguage": "script-language plumbing the running languages own",
    "ScriptExtension": "script-language plumbing the running languages own",
    "Extension": "a base a script or extension implements (TextServerExtension, PhysicsServer3DExtension, ...): its methods are hooks, not engine behaviour",
}

EXCLUDE_PREFIX: dict[str, str] = {
    "RD": "RenderingDevice descriptions; " + _RD,
    "Editor": "editor-only classes",
}

# ---- singletons -------------------------------------------------------------------------------------

STATELESS_SINGLETONS: dict[str, str] = {
    "Geometry2D": "pure 2D geometry functions of their arguments",
    "Geometry3D": "pure 3D geometry functions of their arguments",
    "Marshalls": "pure encoders and decoders of their arguments",
}

# ---- referenced helpers outside the generated MethodBind calls ----------------------------------------

NON_BIND_HELPERS: dict[str, str] = {
    "constructObject": "constructs the fresh instance of every A/B/F row; a row that constructs is its check",
    "destroyObject": "frees what a row constructed (a RefCounted's last release); part of every row's teardown",
    "getMethodBind": "resolves the MethodBind every row's call passes; a wrong bind fails the row that uses it",
    "getSingleton": "resolves the singleton handle every D row calls",
    "keepReachable": "the reachability fence after each call (empty on iOS); it has no result to observe",
}

# ---- static rows -------------------------------------------------------------------------------------

SCRATCH_DIR = "user://kanama-api-exercise/"
SCRATCH_POLICY = (
    "The exercise runner creates `user://kanama-api-exercise/` before its first row and removes it after "
    "its last. A row that takes a path (the `FileAccess` and `DirAccess` statics: `open*`, `create_temp`, "
    "`make_dir*`, `rename*`, `copy*`, `set_*attribute`, `set_unix_permissions`, `get_file_as_*`) takes one "
    "under that directory, and no row reads or writes anywhere else."
)

# (class, method) -> one Godot-literal string per argument, or None where a value is still to be chosen.
# The skeleton: extension_api.json's default where the argument has one, None where a value is
# still to be chosen. A static row without arguments needs no entry.
STATIC_ARGS: dict[tuple[str, str], tuple[str | None, ...]] = {
    # FileAccess / DirAccess: every path is under SCRATCH_DIR (see SCRATCH_POLICY).
    ("DirAccess", "copy_absolute"): (None, None, "-1"),
    ("DirAccess", "create_temp"): ('""', "false"),
    ("DirAccess", "dir_exists_absolute"): (None,),
    ("DirAccess", "get_directories_at"): (None,),
    ("DirAccess", "get_drive_label"): (None,),
    ("DirAccess", "make_dir_absolute"): (None,),
    ("DirAccess", "rename_absolute"): (None, None),
    ("FileAccess", "create_temp"): (None, '""', '""', "false"),
    ("FileAccess", "get_extended_attribute"): (None, None),
    ("FileAccess", "get_extended_attribute_string"): (None, None),
    ("FileAccess", "get_file_as_bytes"): (None,),
    ("FileAccess", "get_file_as_string"): (None,),
    ("FileAccess", "open"): (None, None),
    ("FileAccess", "open_compressed"): (None, None, "0"),
    ("FileAccess", "open_encrypted"): (None, None, None, "PackedByteArray()"),
    ("FileAccess", "open_encrypted_with_pass"): (None, None, None),
    ("FileAccess", "set_extended_attribute"): (None, None, None),
    ("FileAccess", "set_extended_attribute_string"): (None, None, None),
    ("FileAccess", "set_hidden_attribute"): (None, None),
    ("FileAccess", "set_unix_permissions"): (None, None),
    # Resources from bytes, files, strings or dictionaries: a value the static accepts.
    ("AudioStreamMP3", "load_from_buffer"): (None,),
    ("AudioStreamMP3", "load_from_file"): (None,),
    ("AudioStreamWAV", "load_from_buffer"): (None, "{}"),
    ("AudioStreamWAV", "load_from_file"): (None, "{}"),
    ("DPITexture", "create_from_string"): (None, "1.0", "1.0", "{}"),
    ("GLTFAccessor", "from_dictionary"): (None,),
    ("GLTFCamera", "from_node"): (None,),
    ("GLTFDocument", "export_object_model_property"): (None, None, None, None),
    ("GLTFDocument", "import_object_model_property"): (None, None),
    ("Image", "create"): (None, None, None, None),
    ("Image", "create_from_data"): (None, None, None, None, None),
    ("ImporterMesh", "merge_importer_meshes"): (None, None, "true"),
    ("JSON", "from_native"): (None, "false"),
    ("JSON", "parse_string"): (None,),
    ("JSON", "stringify"): (None, '""', "true", "false"),
    ("TLSOptions", "server"): (None, None),
    # Pure functions of their arguments.
    ("PathFollow3D", "correct_posture"): (None, None),
    ("PhysicsRayQueryParameters2D", "create"): (None, None, "4294967295", "Array[RID]([])"),
    ("PhysicsRayQueryParameters3D", "create"): (None, None, "4294967295", "Array[RID]([])"),
    ("Tween", "interpolate_value"): (None, None, None, None, None, None),
}
