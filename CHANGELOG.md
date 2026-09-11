# Changelog

All notable user-facing changes will be recorded here.

This project uses a Keep a Changelog-style format and follows semantic
versioning once public releases begin.

## Unreleased

### Added — iOS: Callable returns on every audited argument shape (task 100, parcel 11)

- A method returning a `Callable` — `TreeItem.get_custom_draw_callback`,
  `MultiplayerSpawner.get_spawn_function` (and the `spawn_function` property), the four
  `NativeMenu.get_*_callback` getters and `DisplayServer.global_menu_get_item_callback` /
  `global_menu_get_item_key_callback` — was desktop-only. Every such method whose arguments are
  already audited now gets a generated helper returning `GodotCallable?`, decoded the way the
  desktop backend decodes it: the method runs once into a Callable cell, `Callable.get_object()`
  and `Callable.get_method()` are read back through the builtin-method table, the target comes
  back as a borrowed `GodotObject` over its handle and the method name travels through the
  parcel-1 UTF-8 path (inline buffer, single pending slot beyond it — never truncated, never
  re-issued). An empty or object-less Callable is `null`, as on desktop. New C entry
  `kanama_ios_godot_ptrcall_ret_callable`; no new tag. 9 members on 4 classes move from the
  desktop companions into the shared tree; the gap index goes from 34 to 25 desktop-only members
  (20 → 17 companion files, 27 → 22 helpers waited on). Five self-test rows round-trip a
  `MultiplayerSpawner` spawn function (target handle and method name; a 602-byte non-ASCII name
  through the pending slot), read the empty spawn function back as `null`, and read `NativeMenu`'s
  popup callback for an invalid RID back as `null` (no Control is constructed: a Control's
  post-initialize needs the theme contexts, which do not exist before `Main::setup2`).

### Added — iOS: typed arrays of containers and packed arrays on every audited shape (task 100, parcel 10)

- A method taking an `Array[Dictionary]`, `Array[Array]`, `Array[PackedByteArray]` or
  `Array[PackedStringArray]` argument — or returning an `Array[Array]` — was desktop-only; every such
  method whose other arguments are already audited now gets a generated helper. The typed-array
  descriptor of parcel 8 carries each element as a nested blob (a Dictionary / Array element is the
  task-29 entry blob, one container level with scalars and `ByteArray`s inside; a PackedByteArray
  element is its raw bytes; a PackedStringArray element the task-13 string blob), and the dispatch's
  blob boxer rebuilds each element before `push_back`, destroying the temporary once the Variant
  holds its own reference. `Array[Array]` returns ride the parcel-6 container blob, which now encodes
  PackedByteArray elements as their bytes (other nested packed arrays still surface `null`, the
  recorded limit). 13 members on 8 classes move from the desktop companions into the shared tree —
  `GraphEdit.set_connections`, `GLTFState.set_buffers`, `GLTFObjectModelProperty.set_json_pointers`,
  `OggPacketSequence.set_packet_data` / `get_packet_data`, `RenderingDevice.texture_create`,
  `RenderingServer.mesh_create_from_surfaces` / `mesh_surface_get_blend_shape_arrays`,
  `ImporterMesh.add_surface`, `EditorVCSInterface.add_diff_hunks_into_diff_file` /
  `add_line_diffs_into_diff_hunk`, `DisplayServer.file_dialog_with_options_show` — and the
  `connections`, `buffers` and `jsonPointers` properties are read-write on iOS again; the gap index
  goes from 34 to 21 desktop-only members (20 → 13 companion files, 27 → 16 helpers waited on, 3 → 0
  read-only properties). Five self-test rows hand an empty `Array[Dictionary]` (which the engine rejects, as asserted) to
  `set_connections` / `get_connection_list`, two OggPacketSequence pages of PackedByteArray packets
  (byte-exact, including a 300-byte packet), three GLTFState buffers (an empty one and a 5000-byte
  one) and three JSON-pointer PackedStringArrays (a non-ASCII element, an empty array, an empty
  string).

### Added — iOS: typed-object-list returns on every audited argument shape (task 100, parcel 9)

- A method returning `Array[<Object subclass>]` (`List<T>` on Kotlin) reached iOS through three
  hand-written argument layouts only (no-arg, one bool, two String + two bool); every other such
  method was desktop-only. Every audited argument layout now gets a generated helper —
  `fun <T> ObjectCalls.<shape>(..., fromHandle: (MemorySegment) -> T?): List<T>` — that lays its
  arguments out like any other helper and hands them to the shared `retTypedObjectList` body. That
  body rides a new C entry, `kanama_ios_godot_ptrcall_ret_object_handles`, which runs the method
  **once** and delivers the element handles into the caller's buffer or, past 64 elements, a
  pending slot the Kotlin side drains (the older two-call length protocol re-invoked the method,
  acceptable for `get_children`, not for `Noise.get_image_3d` or `RenderingServer.bake_render_uv2`).
  The three hand-written helpers gain the run-once behaviour too. Element handles stay borrowed,
  as on desktop. 9 members on 7 classes move from the desktop companions into the shared tree:
  `InputMap.action_get_events`, `RegEx.search_all`, `Noise.get_image_3d` / `get_seamless_image_3d`,
  `TranslationServer.find_translations`, `TranslationDomain.find_translations`,
  `RenderingServer.texture_3d_get` / `bake_render_uv2`, `EditorInterface.make_mesh_previews`; the
  gap index goes from 34 to 25 desktop-only members (20 → 14 companion files, 27 → 19 helpers
  waited on). Four self-test rows round-trip `RegEx.search_all` through the inline buffer (3
  matches) and the pending slot (200 distinct match handles), `InputMap.action_get_events` by
  handle identity and an empty `TranslationServer.find_translations`.

### Added — iOS: typed-array and Rect2i arguments on every audited shape (task 100, parcel 8)

- A method taking a typed `Array[...]` argument — `Array[RID]`, `Array[String]`, `Array[StringName]`,
  `Array[NodePath]`, `Array[int]`, `Array[Vector2i]`, `Array[Plane]`, `Array[Transform3D]`,
  `Array[PackedVector2Array]` or an `Array[<Object subclass>]` whose element wrapper is emitted on
  iOS — was desktop-only, as were `Rect2i`, `Plane` and `Vector4` arguments and `Plane` / `Vector4`
  returns. Every such method whose other arguments are already audited now gets a generated
  helper. Typed arrays ride a new BUILD-tagged descriptor: the dispatch constructs an empty Godot
  Array, makes it typed through the `array_set_typed` interface (the engine only accepts that on
  an empty array, and several setters reject an untyped one), pushes the descriptor's tagged
  elements one by one (the task-29 container layout; `push_back` validates each against the
  type) and destroys the Array after the call. `Rect2i`, `Plane` and `Vector4` are plain
  fixed-width cells the layout table lacked. 135 members on 55 classes move from the desktop
  companions into the shared tree — `PhysicsPointQueryParameters2D.set_exclude`,
  `PhysicsRayQueryParameters3D.create`, `NavigationPathQueryParameters2D.set_included_regions`,
  `GLTFState.set_nodes`, `GLTFState.set_unique_names`, `CodeEdit.set_comment_delimiters`,
  `Control.set_accessibility_controls_nodes`, `RDPipelineMultisampleState.set_sample_masks`,
  `Geometry3D.compute_convex_mesh_points`, `Geometry3D.clip_polygon`,
  `RenderingDevice.vertex_format_create`, `RenderingServer.texture_3d_create`,
  `AStarGrid2D.set_region`, `BitMap.set_bit_rect`, `Window.popup`,
  `VisualShaderNodeVec4Parameter.set_default_value`, `XRAnchor3D.get_plane` among them; the gap
  index goes from 393 to 258 desktop-only members (147 → 108 companion files, 195 → 128 helpers
  waited on). Eight self-test rows round-trip a 1000-element `Array[RID]`, `Array[String]`,
  `Array[GLTFNode]`, `Array[NodePath]`, `Array[int]` and `Array[PackedVector2Array]` through a
  generated setter and its read-back, feed the unit cube's six planes to
  `Geometry3D.compute_convex_mesh_points`, and round-trip a `Rect2i` and a `Vector4`.

### Added — iOS: Dictionary, Array and Variant arguments on every audited shape (task 100, parcel 7)

- A method taking a `Variant`, a `Dictionary` or a generic `Array` argument was callable on iOS
  only through the hand-written Object-call helpers (`callWithVariantArgs` and the few shapes
  built on it). Every such method whose other arguments are already audited now gets a generated
  helper: a Variant argument travels as a `KanamaIosVariantArgDesc {tag, ptr}` that the generic
  ptrcall dispatch boxes with the Object-call boxer into a Variant cell for the call
  (`KANAMA_IOS_PT_VARIANT`, appended), and a Dictionary or Array argument travels as the task-29
  entry blob the dispatch rebuilds into a container cell (the DICTIONARY / ARRAY tags double as
  argument tags). Every cell is destroyed after the call — the engine copies what it keeps, so
  nothing outlives the ptrcall. Inside a Variant: null, Boolean, Int/Long, Float/Double, String,
  NodePath, Vector2/2i/3, Color, RID, object handles, and one level of Map / List with scalar
  values; inside a Dictionary or Array argument: scalars — a nested container or any other Kotlin
  object throws (`IosReturnContainerScratch.taggedValue` strict mode) instead of silently passing
  nil, and the blob for each container argument lives in the call's own scope so two container
  arguments never share a buffer. 109 generated helpers use the new packers; 222 members on 97
  classes move from the desktop companions into the shared tree — `ConfigFile.set_value`,
  `ConfigFile.get_value`, `Expression.execute`, `AudioStreamWAV.set_tags`,
  `ProjectSettings.set_setting`, `JSON.stringify`, `JSON.from_native`, `RichTextLabel.push_meta`,
  `TextLine.add_string`, `PhysicsServer3D.body_set_param`, `RenderingServer.global_shader_parameter_set`,
  `CodeHighlighter.set_keyword_colors`, `DisplayServer.global_menu_add_item`,
  `EngineDebugger.send_message` among them; the gap index goes from 389 to 171 desktop-only
  members (147 → 68 companion files, 195 → 96 helpers waited on). Eleven self-test rows round-trip
  int, float, String, bool, Vector2, null (which erases a ConfigFile key), a Map and a List through
  `ConfigFile.set_value` / `get_value`, an Array element through `Expression.execute`, a Dictionary
  through `AudioStreamWAV.set_tags` / `get_tags`, and a Variant through `ProjectSettings.set_setting`.

### Added — iOS: string-list and typed-array returns on every audited argument shape (task 100, parcel 5)

- A method returning a PackedStringArray or a typed `Array[...]` of RID, Vector2i, Vector3i,
  String, StringName, NodePath, int, Plane, Vector2, Vector3, Rect2, Transform3D,
  PackedVector2Array, PackedByteArray or PackedStringArray was callable on iOS only through
  the hand-written no-arg read-backs, and only for the few element kinds those covered. Every
  such method whose arguments are already audited now gets a generated helper: the new C entry
  `kanama_ios_godot_ptrcall_ret_array_blob` ptrcalls once, encodes the array into the same
  length-prefixed blob the no-arg read-backs use (their element encoders are now one shared
  set, extended with the new element kinds) and delivers it whole — into the caller's buffer
  when it fits, otherwise parked C-side and drained by `kanama_ios_godot_take_pending_blob`.
  Nothing is truncated and the method is never re-invoked (the no-arg entries' two-call length
  protocol was only safe for pure getters). 43 helpers, 117 members on 44 classes move from the
  desktop companions into the shared tree — `ClassDB.get_inheriters_from_class`,
  `ClassDB.class_get_enum_list`, `TileMapLayer.get_used_cells`, `TileMapLayer.get_used_cells_by_id`,
  `GridMap.get_used_cells_by_item`, `Geometry3D.build_box_planes`, `Theme.get_color_list`,
  `NavigationServer3D.map_get_regions`, `NavigationPathQueryParameters3D.get_included_regions`,
  `CodeEdit.get_comment_delimiters`, `TextServer.font_get_kerning_list`,
  `DisplayServer.get_display_cutouts`, `GLTFState.get_buffers` among them; the gap index goes
  from 852 to 735 desktop-only members (229 → 224 companion files, 385 → 343 helpers waited
  on). Left in the gap on purpose: typed `Array[Dictionary]` / `Array[Array]` returns (they
  need the Dictionary / Array decode) and typed object arrays (their own gate). Eight
  self-test rows cover a PackedStringArray with an argument (short, and the >4 KiB
  `get_inheriters_from_class("Object")` through the pending slot), Array[Vector2i] with and
  without arguments, Array[StringName], Array[Plane] and Array[Vector3i].

### Added — iOS: Packed*Array arguments on every audited shape (task 100, parcel 4)

- A method taking a PackedByteArray, PackedInt32Array, PackedInt64Array, PackedFloat32Array,
  PackedFloat64Array, PackedVector2Array, PackedVector3Array, PackedColorArray or
  PackedStringArray argument was called on iOS only through five hand-written helpers (the four
  CanvasItem draw shapes and the single-arg PackedFloat32Array setter). Every such method whose
  other arguments are already audited now gets a generated helper: the generic ptrcall dispatch
  already built Vector2/Color/byte arrays from a `KanamaIosPackedArgDesc {count, data}`, and the
  task-29 virtual-return path had extended the builder to every fixed-element kind — the dispatch
  now accepts all of them as BUILD-tagged arguments, plus PackedStringArray built from the same
  `[int32 count]([int32 len][utf8])*` blob the virtual-return path rebuilds from. The generator
  lays each argument out through an `ObjectCalls.pack<Kind>Desc` helper (`IOS_PACKED_ARGS`).
  200 members on 98 classes move from the desktop companions into the shared tree —
  `Polygon2D.set_polygon`, `CPUParticles2D.set_emission_points`, `ConvexPolygonShape3D.set_points`,
  `NavigationPolygon.add_polygon`, `Crypto.encrypt`, `AESContext.update`, `AudioStreamMP3.set_data`,
  `Geometry2D.triangulate_polygon`, `RenderingServer.canvas_item_add_polygon`,
  `RenderingDevice.buffer_update`, `WebSocketPeer.set_supported_protocols`,
  `OS.create_process` among them; 76 shared-tree properties whose setter was desktop-only are
  read-write again (127 → 51 read-only); the gap index goes from 852 to 652 desktop-only members
  (229 → 173 companion files, 385 → 278 helpers waited on). Seven self-test rows round-trip a
  Vector2, byte, int32, Vector3, Color, int64, float64 and string array through a generated
  setter and its read-back, two of them longer than the 256-element inline capacity. Elements are
  still pushed one at a time on the C side (the builder predates this parcel); a bulk `resize` +
  copy is a follow-up, not a correctness concern.

### Added — iOS: Dictionary and Array returns on every audited argument shape (task 100, parcel 6)

- A method returning a `Dictionary`, a generic `Array` or an `Array[Dictionary]` reached iOS only
  through two hand-written generic-Array helpers and the fixed-shape raycast decode. Every such
  method whose arguments are already audited now gets a generated helper: the new C entry
  `kanama_ios_godot_ptrcall_ret_container_blob` runs the method once through the generic ptrcall
  dispatch and serializes the container into one self-describing blob (records of variant type,
  byte length and payload; nested Dictionary / Array values as nested blobs; String and StringName
  keys as UTF-8), parking a blob longer than the 4 KiB inline buffer C-side for
  `kanama_ios_godot_take_pending_container_blob` — nothing is truncated and nothing is re-issued.
  Decode parity with desktop: `Map<String, Any?>` keeps String / StringName keys only (as
  `BuiltinTypes.readDictionaryScalars` does) and values decode as bool, int, float, String family,
  borrowed Object handle, Vector2 / Vector2i / Vector3 / Color, nested Map / List; other value types
  surface `null`. `Array[Dictionary]` drops non-Dictionary elements like `readArrayDictionaries`.
  The existing generic-Array blob entry is now a thin wrapper over the same encoder, so its
  hand-written callers see nested containers instead of `null`. 36 helpers, 129 members on 53
  classes move into the shared tree — `Time.get_datetime_dict_from_unix_time`,
  `OS.get_memory_info`, `ClassDB.class_get_method_list`, `ClassDB.class_get_signal`,
  `Script.get_script_property_list`, `GraphEdit.get_connection_list`,
  `PhysicsDirectSpaceState2D.intersect_ray`, `RenderingServer.mesh_surface_get_arrays`,
  `StreamPeer.get_data` among them; the gap index goes from 852 to 723 desktop-only members
  (229 → 218 companion files). Six self-test rows cover a Dictionary with an int64 argument,
  a no-arg Dictionary, an Array with an int argument, an `Array[Dictionary]` far larger than the
  inline buffer with nested records, the drained pending slot, and a two-StringName-argument
  Dictionary.

### Added — Web Curve.sample (task 64, Curve + Resource-typed hydration parcel)

- **Web protocol 22 → 23.** The Kotlin/Wasm backend admits `Curve.sample(offset: Double):
  Double` (opcode 306, `DOUBLE_RET_DOUBLE` shape), generating a Web `Curve` wrapper class for
  the first time. This is the one method call third-person's shared `Bullet.kt` needs once its
  `scaleDecay: Curve?` `@ScriptProperty` hydrates — the Resource-typed property push itself
  already rode the existing generic OBJECT property arm (`objectWrapperFqName`), so no processor
  change was needed there. The in-repo `web3d` fixture proves the family delivers a VALUE, not
  merely a dispatch (`Main.curve_sample_probe`, required by the smoke gate, must return 3).
  **Existing Web exports must be rebuilt**: a protocol-22 export refuses to load against a
  protocol-23 bridge, and vice versa.
- **Fixed — Web value-returning double queries.** `Noise.get_noise_1d` (opcode 276) and the new
  `Curve.sample` rode the bridge's confirm-only double channel, which treats any result other
  than `1` as "not applied" — so every sample except 0.001 threw a boundary failure. The
  `DOUBLE_RET_DOUBLE` shape now has its own bridge path (`immediateDoubleRetDouble`) that
  accepts any published x1000 integer (0 included), and the generated GDScript applier gained
  the `Curve.sample` arm it was missing.

### Changed — macOS exported games: validated on the bundled runtime, and the signing gap written down (task 63)

- The bundled-jlink-runtime work was proven end to end on Windows and Linux from a macOS host;
  macOS itself had only the first slice's export and no current evidence. `export_game_smoke.sh`
  now has a recorded macOS arm64 pass (2026-09-10, Godot `4.7.2.stable`, Temurin 25.0.4.1+1), run
  twice: against a host-jlinked image and against one linked from the pinned `macos-arm64` Temurin
  jmods (31.7 MB either way). The exported `.app` boots headless with `JAVA_HOME` unset and `PATH`
  stripped, from `Contents/Resources/runtime/lib/server/libjvm.dylib`. The logged path is the
  proof and not the fact that it ran: with the bundled runtime deleted the same `.app` still
  starts on a developer Mac through the bootstrap's hardcoded Temurin fallback, and only the path
  assertion catches that.
- [Desktop and Packaging](docs/exporting/desktop.md) gains a macOS section for what the bundled
  runtime changes: the `.app` layout with the payload in `Contents/Resources/`, why that is the
  only location `codesign` will seal, the ad-hoc reseal (and that it replaces a Developer ID
  signature, so a signed build assembles before it signs), the three hardened-runtime entitlements
  the embedded JVM needs and what each is for, and an inventory of what signed distribution would
  additionally require — 16 Mach-O files inside the runtime image that the bundle signature only
  hashes, inside-out signing, notarize and staple. Signing and notarization remain non-goals; this
  is documentation of the gap, not an implementation.

### Added — iOS: Packed*Array returns on every audited argument shape (task 100, parcel 3)

- A method returning a PackedByteArray, PackedInt32Array, PackedInt64Array, PackedFloat32Array,
  PackedFloat64Array, PackedVector2Array, PackedVector3Array or PackedColorArray was called on iOS
  only through the hand-written no-arg read-backs (and only for the four kinds that had one).
  Every such method whose arguments are already audited now gets a generated helper: the new C
  entry `kanama_ios_godot_ptrcall_ret_packed` ptrcalls into a packed-array cell, reads the element
  count from the kind's `size` builtin and copies the contiguous elements out in one `memcpy`;
  an array longer than the caller's capacity is parked C-side and drained whole
  (`kanama_ios_godot_take_pending_packed`), so the method runs once and nothing is truncated.
  66 helpers, 154 members on 71 classes move into the shared tree — `AStar2D.get_point_path`,
  `AStar3D.get_id_path`, `Curve3D.get_baked_points`, `Curve2D.tessellate`, `FontFile.get_data`,
  `AudioStreamWAV.get_data`, `RenderingDevice.texture_get_data`,
  `RenderingServer.instances_cull_ray`, `NavigationServer2D.map_get_path`,
  `Geometry3D.segment_intersects_sphere`, `ConvexPolygonShape3D.get_points`,
  `CPUParticles3D.get_emission_points` among them; the gap index goes from 1006 to 852
  desktop-only members (250 → 230 companion files). Seven self-test rows cover int64, Vector2,
  byte (3000 elements through the pending slot) and Vector3 arrays via AStar2D, Crypto and
  Curve3D.

### Added — iOS: Variant-scalar returns on every audited argument shape (task 100, parcel 2)

- A Variant-returning method with arguments was called on iOS only through three hand-written
  helpers (the no-arg and StringName-arg getters, and the owned `ClassDB.instantiate` decode).
  Every such method whose arguments are already audited now gets a generated helper: the new C
  entry `kanama_ios_godot_ptrcall_ret_variant_scalar` ptrcalls into a Variant cell and decodes
  the scalar payload exactly as the Object-call path does (bool, int, float, String family,
  borrowed Object handle, Vector2/Vector2i/Vector3/Color; anything else surfaces `null`, matching
  desktop's `RetVariantScalar`). That decode is now one shared C function, and a String-family
  payload longer than the 1 KiB buffer is parked C-side and drained whole instead of truncated —
  which also lifts the 1 KiB cap the Object-call path (`callWithVariantArgs`) had. 18 helpers,
  56 members on 32 classes move into the shared tree — `StreamPeer.get_var`,
  `PacketPeer.get_var`, `Animation.track_get_key_value`, `PhysicsServer3D.body_get_param`,
  `RenderingServer.material_get_param`, `JSON.parse_string`, `Theme.get_theme_item`,
  `Geometry2D.segment_intersects_segment` among them; the gap index goes from 1062 to 1006
  desktop-only members (252 → 250 companion files). Seven self-test rows round-trip int, float,
  bool, short and 3000-byte Strings and null through `StreamPeerBuffer.put_var` / `get_var`.

### Added — iOS: String, StringName and NodePath returns on every audited argument shape (task 100, parcel 1)

- The iOS backend used to call a String-, StringName- or NodePath-returning method only when
  its exact shape had a hand-written helper (the no-arg getters plus a handful of arg-bearing
  ones on the Object-call decode). Every such method whose arguments are already audited now
  gets a generated helper: one new C entry, `kanama_ios_godot_ptrcall_ret_utf8`, runs the
  method once through the generic ptrcall dispatch and UTF-8 encodes the return, parking a
  value longer than the inline buffer C-side so the caller drains it whole
  (`kanama_ios_godot_take_pending_utf8`). Nothing is truncated (the Object-call decode caps at
  1 KiB) and nothing is re-issued (the no-arg helpers' two-call length protocol is only safe for
  pure getters; `StreamPeer.get_utf8_string` consumes the stream). 29 helpers, 212 members on
  26 classes move from the desktop companions into the shared tree — `TextEdit.get_line`,
  `ItemList.get_item_text`, `Skeleton3D.get_bone_name`, `Animation.track_get_path`,
  `RenderingServer.shader_get_code` among them; the generated gap index goes from 1274 to 1062
  desktop-only members (277 → 252 companion files). Four self-test rows cover the three return
  builtins and a 3000-byte String through the pending slot.

### Fixed — root `build` / `publishToMavenLocal` no longer compile ios-runtime metadata

- `ios-runtime` ships as a static xcframework and is not a Maven/KMP library, but the
  Kotlin Multiplatform plugin still registered a publication for it, and a root `./gradlew
  build` or `publishToMavenLocal` compiled its intermediate `iosMain` source set to Kotlin
  metadata for that publication. That compilation rejects the `@JvmName`/`@JvmStatic` the
  generated wrappers carry ("Declaration annotated with '@OptionalExpectation' can only be
  used in common module sources"), so those two root commands failed while every platform
  compile, the static link and the xcframework lane were green. Pre-existing (the iOS tree
  carried `@JvmName` before the shared tree); found while republishing after #220. The unused
  metadata compilation and publications are disabled; `publishKanamaToMavenLocal` (the gate's
  task) was never affected.

### Changed — one shared generated wrapper tree (task 103)

- **The generated Godot API wrappers are emitted once.** The shared tree
  `src/commonMain/kotlin/net/multigesture/kanama/api` (979 classes) is compiled by the
  desktop JVM module, by `:ios-runtime` and by the Android plugin; the iOS copies under
  `ios-runtime/.../api` are gone. A shared file holds the members both native backends
  can call; the members only desktop/Android can call (no audited iOS ptrcall helper
  yet) are generated as extensions into per-class `<Class>.jvm.kt` companions (277
  classes, 1,274 members) whose headers name the helpers they wait on, all listed in
  the new generated page `docs/reference/generated/ios-shape-gap.md`. A helper landing
  on iOS moves its members back into the shared file on the next regen.
- **Scripts calling a desktop-only member import it by name.** Those members are
  extension functions now, so a class import alone no longer brings them in: add
  `import net.multigesture.kanama.api.getUsedCells` (one line per member), as the eight
  affected demo scripts do (kanama-demos#48). Kanama code, demos and templates
  import by name, never `net.multigesture.kanama.api.*`. Call syntax is unchanged; as
  iOS helper shapes land (task 100) the members move back into the classes and the
  imports become ordinary member imports.
- **Aligned across platforms:** `@JvmStatic` is emitted on iOS too (harmless on
  Kotlin/Native); `Node.createTween()` is `open` on both; the iOS `GodotObject` is no
  longer `AutoCloseable` (like desktop; `RefCounted` still is and owns `close()`); iOS
  `RefCounted.unreference()` is `internal`; the generated iOS `close()` no longer carries
  the deprecated `@ManualGodotLifetimeApi`.
- **Tooling.** One platform-tagged table, `PER_PLATFORM_WRAPPERS`, lists the 56 classes
  that are not shared. `check_wrapper_generator.py` is a single-tree gate (every
  generated file, companion and the gap index must equal a fresh in-process regen; a
  per-platform copy of a shared class fails) and runs in about 5 s instead of 57 s.
  `generate_api_wrapper.py --write-tree` re-adopts the whole tree (`upgrade_godot.sh`
  step 5 uses it); the wrapper audits, property coverage, KDoc sync and iOS stub check
  read the shared tree.
- **Measured (one run each, same laptop, clean builds with the Gradle build cache off):**
  wrapper sources 1,053 + 1,029 files / 302,193 + 181,056 lines in two trees →
  979 shared + 351 desktop + 54 iOS files / 233,944 + 73,482 + 19,573 lines (the
  desktop figure includes the 277 companions, 22,189 lines); drift gate 57.5 s → 4.7 s;
  clean desktop `installAddonJar` 60 s → 73 s (24 % more Kotlin files to compile: the
  companions), warm 4.6 s → 5.7 s; iOS `compileKotlinIosArm64` from clean 46.5 s → 46.3 s;
  `linkDebugStaticIosArm64` 106 s → 103 s.

### Changed — Web wrappers generated from the call contract (task 96)

- **The Web API surface is generated.** Every opcode in
  `scripts/platform_backend_calls.json` now renders one member on the Godot
  class that owns it, in `web-runtime/.../api/generated/<Class>.kt` (107
  classes, one file each), with names, parameter widths and defaults taken
  from `extension_api.json` and properties derived from Godot's property
  table. The eleven demo-named `Web*Api.kt` files and the ~1,360 lines of
  probe classes in `GodotBackendContract.kt` are gone; what stays hand-written
  is explicit (`WEB_HANDSHAPED`: the handle-less `Window` mirror and the
  owner-bound ray query) or lives in the generator's per-class policy.
  `python3 scripts/generate_web_wrappers.py --check` gates drift in
  `local_ci.sh` and `:web-runtime:check`; the wire protocol is unchanged
  (still 22).
- **Source compatibility.** Existing call-site spellings keep compiling: every
  generated member also carries an import-compat extension (`import
  net.multigesture.kanama.api.setProcess` still resolves), so the 25
  hand-written `@Suppress("EXTENSION_SHADOWED_BY_MEMBER")` aliases are
  replaced, not dropped. Members now use Godot's argument names and desktop's
  int widths (`int32` → `Int`), and inherited members surface on their Godot
  owner (`Node.getLocalMousePosition` on `CanvasItem`, collision layers on
  `CollisionObject3D`, root motion on `AnimationMixer`); a few getters that
  used to throw as write-only now read the engine (`Node3D.visible`,
  `Label.text`).
- **Growth metric redefined** (`scripts/web_hand_metric.py`): the Option-B
  reconsider line in `docs/contributing/backends/web.md` now sums every
  hand-written Web family (wrappers, wrapper policy, contract, dispatch
  companion, runtime, script emitter, bridge) against the generated code —
  20,931 / 5,181 (4.04) before, 17,875 / 11,108 (1.61) after — and admission
  PRs paste it.

### Changed — docs consolidation (task 94)

- **One page owns each fact.** Requirements (Godot pin, JDKs, host platforms,
  per-workflow toolchains) and platform status/evidence are stated once, in
  `docs/reference/version-support.md` (new "Requirements" table; the Android
  demo matrix, the validated Android version floors, the iOS device-gate
  evidence and the Web browser floors moved there from the export guides).
  README.md and `docs/index.md` carry the badges (a Web badge is new) and a
  link; the getting-started introduction, the release-kit and store-addon
  template READMEs, and the export guides link instead of restating. The
  export guides (`docs/exporting/{android,ios,web}.md`) are now
  workflow-only: toolchain table, commands, troubleshooting.
- **Pages moved** (no redirects — update bookmarks; the published site keeps
  the same section names):
    - `docs/contributing/android-internals.md` → `docs/contributing/backends/android.md`
      (also gained the Android R8/PanamaPort root-cause history from the export guide)
    - `docs/contributing/web-internals.md` → `docs/contributing/backends/web.md`
    - `docs/internals/reference/ios-backend-architecture.md` → `docs/contributing/backends/ios.md`
    - `docs/internals/reference/ios-backend-handwritten.md` → `docs/reference/generated/ios-backend-handwritten.md`
    - `docs/contributing/api-coverage.md` → `docs/reference/generated/api-coverage.md`
    - `docs/contributing/wrapper-generator-report.md` → `docs/reference/generated/wrapper-generator-report.md`
    - `docs/contributing/gates.md` → `docs/reference/generated/gates.md`
    - `docs/internals/README.md` deleted (it only indexed the two files above
      and listed what had moved out of the folder in July); `docs/internals/`
      is gone.
- The generators (`scripts/api_wrapper_coverage.py`,
  `scripts/api_wrapper_generator_report.py`, `scripts/generate_gates_index.py`,
  `scripts/ios_handwritten_report.py`) write to the new paths, and the
  `local_ci.sh` docs-check stages and `scripts/upgrade_godot.sh` check them
  there. `evidence/gates.json` citations point at the consolidated pages.

### Fixed — lifetime safety (task 98)

- **`GD.isInstanceValid` no longer reads a freed object.** `GodotObject` captures
  its engine instance id once at construction (`instanceId`, via the
  `object_get_instance_id` interface function) and `isInstanceValid` asks
  `is_instance_id_valid` about that id — the same thing GDScript does with the id
  its Variant cached — instead of building an OBJECT Variant from the wrapper's
  raw pointer, which dereferenced the freed object's header. Measured cost of the
  capture on Godot 4.7.2 (macOS arm64, JIT-warm): `GodotObject(handle)` goes from
  ~4 ns to ~9 ns per mint; the `getChildren()` element decode it rides on costs
  125–220 ns per element. iOS mirrors it through the shim's
  `object_get_instance_id` / `object_get_instance_from_id`, replacing the
  "non-zero handle" approximation. Every *other* wrapper member still assumes
  the object is alive; nothing invalidates a wrapper for you.
- **Every RefCounted-derived wrapper refuses use after `close()`.** The generator
  now opens each receiver-bound method with `checkOpen()` (the guard 13
  hand-shaped classes already had), on desktop/Android and iOS, and the same line
  was inserted into the hand-shaped RefCounted classes the drift gate exempts
  (`BaseMaterial3D`, `Material`, `Font`, `SurfaceTool`, …). A call through a
  handle whose `close()` destroyed the object is
  `IllegalStateException("RefCounted handle is closed")` instead of a ptrcall on
  freed memory — 661/661 desktop and 654/654 iOS RefCounted wrappers
  (11,400 generated guard lines). `check_wrapper_generator.py` locks the policy.
- **A throwing callback can no longer take Godot down.** `Upcalls.stub` wraps
  every Godot→JVM stub in `MethodHandles.catchException`: an exception escaping a
  `@RegisterFunction`, a generated virtual dispatcher, the `.kt` resource loader
  or any ScriptInstance callback is logged
  (`[kanama] upcall <Class.method> threw: …`, stack trace once per site) and the
  engine receives the zero of the return type (NIL / `false` / `NULL`) instead of
  the JVM aborting through native frames. Containment used to be nine hand-placed
  catches for 112 upcall targets; the bespoke ones whose return value carries
  meaning (`siCall`, the property accessors) stay on top. No new native adapter
  is linked, so the prewarm gate is unaffected.

### Added — threading note and diagnostic

- `docs/game-dev/scripts.md` gains a **Threads** section: what runs on the main
  thread, that Kanama performs no thread-affinity checks, and that
  `ResourceLoader.load_threaded_request` on a `.kt` script runs the loader — and
  any script constructor it reaches — on a worker thread. `KANAMA_THREAD_DIAGNOSTICS=1`
  logs once per site when `ScriptBridge.siCall` or the `.kt` loader runs off the
  `initialize` thread (a diagnostic, not an assertion; one boolean read when
  unset). `runtime_smoke.sh` gains the `LifetimeSmoke` row (validity across
  `free()`, use-after-close at receiver, inherited, generated and argument
  positions) and the upcall-containment row.

### Fixed — CI change filter skipped the mobile and Web lanes on large PRs

- `ci.yml` and `web.yml` decide whether to run the Android/iOS lanes and the Web matrix
  by grepping the PR's changed-file list. The step runs under `pipefail`, and
  `printf | grep -q` exits on the first match, so on a diff larger than the pipe buffer
  (about 900 files) `printf` died with SIGPIPE and the filter answered **false**: the
  lanes were skipped exactly on the PRs that most needed them (kanama#217, 1,000 files,
  every mobile lane skipped with `ios-runtime/` in the list). The filter now reads the
  list from a here-string. Small PRs were never affected.

### Changed — one resource-ownership rule, and `close()` no longer needs an opt-in

- **Getters are owned; the docs now say so in one place and nowhere contradicts
  it** (task 97). Every `RefCounted`-typed return — `create()`,
  `ResourceLoader.load…`, and plain getters such as `getMesh()`,
  `getAnimation(...)` or the `Tweener` a `tweenProperty(...)` hands back — is a
  `+1` the caller closes; a wrapper you mint yourself over a handle you already
  hold (`fromHandle`/`fromObject`) and a live `Tween` are the only things you never
  close. `docs/game-dev/godot-api.md` "Resource Ownership" is the rule; the
  style guide, `properties-resources.md` and the demo-porting rules lost the
  sentences that still taught the pre-task-62 "do not close what you handed to a
  setter" exception and link instead. The demos parity audit
  (`kanama-demos/scripts/demo_parity_audit.py`) used to fail exactly the closes
  the rule requires; it now enforces the same table.
- **`@ManualGodotLifetimeApi` is deprecated and no longer applied.**
  `RefCounted.close()` (desktop) and the Web `Texture2D`/`AudioStream`/
  `PackedScene`/`ResourceLoader` members carried a `RequiresOptIn(WARNING)`
  that warned on the one call the ownership rule tells you to make. An existing
  `@OptIn(ManualGodotLifetimeApi::class)` still compiles, with a deprecation
  warning; delete it, nothing replaces it. On iOS the annotation is an inert
  marker until the generator stops emitting it on the generated `RefCounted`.
- `GodotObject.call()`, `callDeferred()`, `callv()`, `get()`, `getMeta()` and
  `getScript()` now document that an object result is a **borrowed** view of the
  Variant-path decode — never `close()` it, and it may already be dead when the
  call minted the object (`call("duplicate")`); use the typed wrapper getter or
  `ClassDB.instantiate` to hold one. No behaviour change.

### Added — Web input-map, key-event, process-mode and window-mode families

- **Web protocol 21 → 22.** The Kotlin/Wasm backend admits the call families the
  third-person demo's shared `Player.kt` and `FullScreenHandler.kt` need so they
  can later compile on Web without `web/kotlin-src` overrides (task 64 tier 3):
  `InputMap.hasAction` / `addAction` / `actionAddEvent` / `eraseAction`,
  `InputEventKey.create()` / `from()` with `keycode` / `physicalKeycode` and the
  `KEY_*` constants, `InputEvent.isEcho()` / `isAction()`,
  `InputEventWithModifiers.isAltPressed()`, `Node.setProcessMode` /
  `getProcessMode` with `PROCESS_MODE_*`, `SceneTree.getRoot()`, and a
  handle-taking `Window(...)` with `setMode` / `getMode`. `Node3D.setVisible` /
  `show()` are now members and `InputEventMouseButton.MOUSE_BUTTON_RIGHT` exists.
  One new contract shape (`STRINGNAME_OBJECT_ARG_SINGLETON`) carries the
  action-plus-event call; `actionAddEvent` fails loud if the engine did not
  attach the event. The in-repo `web3d` fixture proves every family delivered a
  VALUE (`Main.input_map_probe`, required by the smoke gate, must return 255).
  **Existing Web exports must be rebuilt**: a protocol-21 export refuses to load
  against a protocol-22 bridge, and vice versa.

### Changed — Backend call contract is Web-local

- **`kanama-common-api` is the Web backend's call table, not a shared platform
  seam** (task 95). The desktop (JVM/Panama) and iOS (Kotlin/Native)
  `CommonGodotBackend` adapters were installed at bootstrap but no native wrapper
  ever dispatched through them; they are removed, together with the native
  modules' dependency on `kanama-common-api` and the Android plugin's source copy
  of it. The module keeps its name and its `jvm` (KSP Web emitter, contract test)
  and `wasmJs` (Web runtime) targets; its unused iOS targets are gone. Every
  `GodotBackendSpi` member is now abstract — the generated Kotlin/Wasm backend
  implements all 87 — so a call shape the generator stops emitting fails at
  compile time instead of at the first call. No user-visible behaviour change on
  any platform; the desktop runtime jar shrinks by about 170 KB (12,106,707 →
  11,936,181 bytes). `docs/contributing/web-internals.md` now describes one
  source (`extension_api.json`) feeding two mechanisms — the native ptrcall
  wrapper generator and the Web contract, whose hashes are validated against the
  same file — instead of claiming native backends consume the contract.

### Changed — Verification hygiene (task 99)

- **The `kanama-common-api` tests now run.** `local_ci.sh` names
  `:kanama-common-api:jvmTest` and `checkPlatformBackendContract` explicitly;
  `gradlew test` never reached the KMP module, so `GodotBackendContractTest` had
  not executed in any CI run since it landed.
- **Gates ledger.** `evidence/gates.json` records each run of the local-only
  gates (device matrices, Safari corpus, host revalidations) with the Godot pin
  and Kanama commit it ran on. `scripts/check_gate_evidence.py`, inside
  `scripts/audit_claims.sh`, fails when a gate's latest run predates the current
  pin unless the entry carries a dated, reasoned `acceptedStaleUntil`. Seeded
  from the dated evidence in Version Support; the 4.7.0-template entries are
  accepted stale until 2026-10-31 and say so on every run. `ios_device_gate.sh`
  and Safari `web_ci_matrix.sh` runs append to the ledger on PASS.
- **Gates index.** `docs/contributing/gates.md` lists every `local_ci.sh`
  stage, CI job and local-only gate — what it proves, where it runs, when it
  landed — generated from the scripts and workflows, with `--check` as a
  local-CI stage.
- **`audit_scalar_float_abi.py` retired**: `audit_ptrcall_helper_layouts.py`
  checks the same float/Color slot rules across every helper, not 30
  name-matched ones. `audit_wrapper_signatures.py` was reviewed for the same
  fate and kept — its `java.lang.Object`/`AutoCloseable`/`GodotObject`
  name-collision checks have no successor.
- `local_ci.sh` prints per-stage seconds (also in the failure banner) and
  writes `build/local-ci-timings.json`. Web quarantine entries carry an expiry
  date; the matrix prints `QUARANTINE EXPIRED` past it (non-fatal for now).

### Changed — Godot baseline

- **Godot baseline re-pinned to 4.7.2 stable** (task 91). `scripts/upgrade_godot.sh`
  classifies the 4.7.0 → 4.7.2 dump as **metadata-only**: the `extension_api.json`
  body and `gdextension_interface.h` are byte-identical apart from the header's
  version fields, so wrappers, name constants, and struct layouts are unchanged and
  the regen churn is version strings plus a KDoc re-sync from the 4.7.2
  `doc/classes` (comment-only). CI now downloads 4.7.2 (`ci.yml`, `web.yml`,
  `package.yml`); requirements, download links, badges, and templates name 4.7.2;
  support-tier labels keep naming the 4.7 stable line. Desktop gates re-ran green on
  the 4.7.2 binary (`local_ci.sh` on macOS arm64 and the nine-demo desktop smoke
  matrix). Mobile device gates and the
  Web browser matrix have not been re-run on the 4.7.2 export templates yet — their
  evidence in Version Support stays dated on the 4.7.0 templates until they are.
- The upgrade tooling now handles maintenance pins: the docs-tree guard in
  `upgrade_godot.sh` dropped the patch component (a 4.7.2 tree read as
  `4.7.stable`), the iOS re-adopt emitted only the non-hand-shaped classes and so
  stripped every method returning a hand-shaped type (`Image`, …) from 15 wrappers,
  and `check_godot_version_pin.py` checked only `package.yml` while `ci.yml` and
  `web.yml` carried their own pins. All three are fixed; `web.yml` derives its
  template folder from `GODOT_VERSION`.

### Fixed — Web behaviour (agent-surface audit, tasks 88/89)

An adversarial audit of the Web backend's agent-written surfaces found and closed
nineteen defects. The ones that change what a running script does:

- **Queued mutations issued from a Kotlin lambda signal callback were silently
  discarded.** Any `queue_free`, property write or `add_child` made from inside a
  `connect { ... }` lambda never reached Godot: the boundary returned without flushing
  the command buffer. Every Web boundary that runs user Kotlin now flushes.
- **Re-parenting a node destroyed its script state.** `_exit_tree` tore the Kotlin
  instance down, so a node removed and re-added — a pooling pattern, or any
  `reparent()` — came back blank. Teardown moved to `NOTIFICATION_PREDELETE`, where
  Godot actually means it. (Protocol 19.)
- **A `queue_free`'d node became uncallable immediately** instead of surviving to the
  end of the frame as it does on desktop.
- **A null element in an exported object array arrived as a live wrapper.** The array
  arm minted a browser handle for `null`, so a script read a real-looking object where
  its scene held nothing. Null now stays null; if the element type is non-nullable the
  build fails naming the property and the fix, rather than fabricating a value.
- **`tween_property` with a NUMBER had no arm.** Vector2, Color and Vector3 all worked,
  so a component path — `tween_property(node, "position:y", 4.0, 0.5)` — faulted the
  Web boundary. (Protocol 20.)
- **`PropertyTweener.from` did not exist on Web.** A tween could not be given a custom
  starting value, so a demo doing so had to drop the call and animate differently on Web
  than on desktop — silently, since nothing failed. The Color arm is in; other value
  types name the arms that do exist rather than failing vaguely. (Protocol 21.)
- Handle-seeding and snapshot-refresh parity fixes for objects arriving through
  sibling paths, and a self-snapshot refresh guarded for nodes outside the tree.

**Web exports must be rebuilt.** The protocol moved 18 → 21 over these fixes (19 for the
re-parent repair, 20 for the scalar tween arm, 21 for `PropertyTweener.from`). The
generated proxies and the JS bridge compare it at startup, so an export built against an
older protocol will refuse to run — rebuild rather than mixing.

### Fixed — gates that certified the wrong thing

Several gates passed without testing what they claimed. These are developer-facing, but
they are why the defects above went unseen:

- The **post-teardown invariant was never enforced at all**, and nine drivers asserted
  it tautologically.
- **A GDScript parse error did not fail the export** — a broken proxy shipped as BUILD
  SUCCESSFUL.
- **Safari reported an empty console it had never observed**, making the
  zero-console-errors pass leg vacuous on the one engine that gate exists for.
- **Safari runs on a locked screen produced meaningless results**: the engine advances a
  few frames and stops, and the run fails inside a demo assertion with nothing naming
  the cause. The driver now refuses to start in that state.
- The envelope schema's pass mirror now encodes all four legs; the protocol check is
  derived from the export manifest rather than hardcoded.

### Added — checks that would have caught them

- **Exercised-member coverage report** naming every declared member no driver reaches,
  and a **desktop↔Web differential probe**.
- **A backend conformance fixture** asserting that every typed property and signal shape
  delivers its *value*, not merely a dispatch.
- **A build failure when a lifecycle annotation is imported but never applied** — a dead
  `@OnReady` had silently disabled a demo's pause-time input handling.
- **Documentation claims are checked against their sources**, so a marked claim fails the
  build when the code moves out from under it.

### Added

- **The Web corpus grew from two demos to twelve.** After 0.4.0 shipped Match3 and
  Bunnymark, dodge-the-creeps, the 3D Platformer, squash-the-creeps, FPS, the
  character-controller tutorial, the third-person controller, Racing, City-Builder,
  and tps-demo were each brought up as production Web exports (#96–#113, protocols
  7–15), adding the 3D rendering foundation, physics, RayCast3D and slide-collision
  queries, Resource-script hydration, object-carrying signals, and a shared handle
  registry along the way. A Web build now rejects any script virtual the proxy does
  not dispatch (#114) and dispatches `_enter_tree` (#145, protocol 16).
- **Web gameplay compiles from one `kotlin-src` per demo**, with per-file overrides
  under `web/kotlin-src` (#133, #136). NodePath exports, hint metadata, and fail-loud
  property guards reach the Web proxy (#148); extension helpers became members on the
  Web wrapper classes and `Resource.fromHandle` / `AudioStreamPlayer.setStream` gained
  Web parity (#144, #146, #150); a generic `callv` fallback covers calls with no typed
  family (#141).
- **Exported desktop games bundle a jlink-trimmed Java runtime**, found app-relative
  before `JAVA_HOME`, so players never install a JDK: `jlinkGameRuntime`,
  `scripts/export_game_assemble.sh`, and `scripts/export_game_smoke.sh` (#140). The
  cross-target variant below builds on this.
- Exported desktop games are published as CI artifacts
  (`kanama-exported-game-<target>`) so the build CI already boots can be tested
  on real hardware without rebuilding.
- Verified on real Windows hardware: a game exported with a jlink runtime
  cross-built on macOS boots from its own bundled runtime even when a system JDK
  is installed, with no VC++ redistributable required.
- **Web exports can be served to a phone for hand-checks**: `scripts/web/serve_export.py
  --lan --https`, since Godot's Web export needs a secure context, with load progress
  and an up-front refusal on plain HTTP (#122, #134).
- **Web exports package into a publishable artifact.**
  `:web-runtime:packageWebExport -PkanamaWebDemo=<demo>` zips an already-built,
  smoke-validated Web export (index.html at the zip root, deterministic
  `kanama-web-<demo>-v<version>.zip` name) after gating it: stale-export
  refusal (demo/protocol/buildId cross-checks against
  `kanama-web/export-report.json`), itch.io's HTML5 defaults (500 MB / 1000
  files, measured output printed either way, tps-demo named as the documented
  638 MB exception), and a byte-level no-local-paths scan.
  `scripts/web_package_smoke.sh` proves the artifact is the game by unzipping
  it to a scratch directory and driving that copy through the full export
  smoke. Publishing guidance (itch.io via butler, generic static-HTTPS
  hosting, why the nothreads export needs no COOP/COEP) is in
  `docs/exporting/web.md` under "Publishing A Web Export". The Web backend's
  status is unchanged: it remains Experimental.
- **Exported desktop games can be built for another platform.** `./gradlew
  jlinkGameRuntimeCross -PkanamaRuntimeTarget=windows-x64` produces the bundled
  JVM runtime for a target other than the host, so a developer on macOS can ship
  a Windows or Linux game — the same expectation Godot's own cross-platform
  export templates set. Targets: `windows-x64`, `linux-x64`, `linux-arm64`,
  `macos-arm64`. Runtime image sizes, all four cross-built from macOS arm64
  against Temurin 25.0.4+7: 31 MB Windows x64, 42 MB Linux x64, 40 MB Linux
  arm64, 31 MB macOS arm64.

  Two things worth knowing if you look under the hood: a Temurin JDK install no
  longer contains `jmods/` at all (JDK 24's JEP 493 lets jlink link from the
  JDK's own run-time image, but only for the platform it runs on), so the task
  fetches Adoptium's separate per-platform jmods download, SHA-256 pinned and
  cached outside `build/`; and the jmods must be the same JDK *feature* version
  as the build JDK, which the task checks before linking.

  `scripts/export_game_assemble.sh` gained `--runtime DIR` handling that refuses
  to pair a runtime image with an export built for a different platform, and
  `scripts/export_game_smoke.sh` gained `--runtime DIR` plus a Windows branch.
  The `package` workflow now cross-builds the Windows and Linux runtimes on a
  macOS job and boots exported games against those artifacts on `windows-2025`
  and `ubuntu-24.04` — a runner building its own runtime would prove only
  same-OS packaging.

### Fixed

- **Web: coroutine delays resume in every demo.** The frame scheduler is now pumped once
  per engine frame from every generated proxy; before, eight of the twelve demos never
  pumped it, so a `delaySeconds` in them hung forever (#157, protocol 18). The real
  `_process` is the bridge's frame fallthrough and the spike benchmark is opt-in (#162);
  physics ticks read a fresh transform snapshot, so held-input movement is no longer
  quantized to the render frame (#170); a spawned script owns its own lifetime instead of
  dying with the script that spawned it (#125).
- **A bundled Windows runtime no longer needs the Visual C++ redistributable on
  the player's machine.** `jvm.dll` lives in `runtime\bin\server` while its CRT
  dependencies ship one level up in `runtime\bin`, and Windows resolves a loaded
  DLL's dependencies against the *executable's* directory and System32 — never
  next to the DLL itself. The bootstrap now registers `runtime\bin` with the
  loader and loads the JVM with the explicit search flags. It also locates
  itself with `GetModuleFileNameW` instead of the ANSI variant, so an export
  under a player profile whose name has no ANSI representation is still found.

- **Web: `@RegisterFunction` shapes that used to throw at runtime now dispatch**
  (Web protocol 16 → 17). The Web emitter models a hand-maintained set of
  supported method shapes; everything else took an `else` arm that emitted a
  stub throwing `Kanama Web gameplay method is not implemented`. That is why FPS
  enemies were immortal on Web — `Enemy.damage(amount: Double)` had no arm.
  Filled in one protocol bump:
  - every all-numeric argument list, through one six-slot crossing:
    `(Float)`, `(Boolean)`, `(Vector2)`, `(Vector3)`, `(Vector3, Vector3)`,
    `(Vector2, Boolean)`, `(Int, Float)`;
  - the whole value-returning category, which previously had no arm at all —
    `String`, `NodePath`, `Int`, `Float`, `Boolean`, `Vector2`, `Vector2i`,
    `Vector3`, `Quaternion` and `Basis` returns from a zero-argument
    `@RegisterFunction`;
  - scalar `@ScriptSignal` payloads reaching Kotlin lambdas. The one-argument
    delivery helper used to discard the emitted value; new typed
    `GodotSignal.connectLong/connectDouble/connectBoolean/connectString/`
    `connectVector2/connectVector2i/connectVector3` overloads receive it, and a
    zero-argument `connect` lambda still runs and ignores it as before.

  The fps Web smoke now shoots an enemy dead, so the player→enemy damage path is
  gated on Chrome and Firefox instead of untested.

  The last two shapes — `(String, Object)` and `(Int, Object?)`, which mix the
  string and object-handle channels — now dispatch too, packed into one string
  over the existing crossing (no protocol change). **An argument shape the Web
  backend cannot dispatch is now a build error** naming the script, the member,
  the shape and the shapes that *are* supported, instead of a stub that throws
  when the engine happens to call it; the same gate covers `@ScriptSignal`
  payloads a Kotlin lambda could not receive. Floats deliberately may not ride
  the mixed-argument crossing (its decimal text would round them), so a float
  mixed with text or an object is one of the shapes the build now rejects.
- `GD.isInstanceValid`, `GD.typeOf` and `GD.hash` now encode their argument as a
  real Variant on desktop/Android (task 78). `GD.encodeVariant` handled six
  scalar types and stringified everything else, so a wrapper object arrived as a
  STRING variant: `GD.isInstanceValid(node)` asked Godot whether a *string* was a
  live instance and returned false for every live object, and `GD.typeOf(node)`
  reported `TYPE_STRING` (the same collapse hit value types such as `Vector3`).
  The encoder now delegates to the shared `BuiltinTypes.initVariantFromAny` used
  by the ptrcall and `Object.call` paths, and fails with a named error instead of
  silently stringifying an unencodable value; the `print`/`str` family keeps its
  `toString()` rendering, now scoped to those text utilities alone. The runtime
  smoke asserts `typeof(node) == TYPE_OBJECT` and `is_instance_valid` true for a
  live node / false after it is freed.
- iOS now mirrors the object-typed export `class_name` fix (task 64 follow-up):
  the script property descriptor bridge gained a
  `kanama_ios_runtime_script_resource_property_class_name` entry (same
  RESOURCE_TYPE/NODE_TYPE policy as desktop) and the shim caches per-property
  StringNames and emits them in the instance property list, so GDScript on an
  iOS export sees e.g. `class_name=AudioStream` instead of empty. Validated on
  an iPhone 15 Pro via the user-script visual smoke, which now asserts the
  engine-visible `class_name` from GDScript (reported through a Kotlin
  `@RegisterFunction`, since Godot-level `print()` does not reach the device
  console) and doubles as a GDScript→Kotlin String/bool call-dispatch check.
- Object-typed Kotlin exports now carry `PropertyInfo.class_name` (task 64,
  follow-up to issue #106): a `@ScriptProperty var x: SmokeResource?` (or an
  engine wrapper slot like `AudioStream?`) previously reported an empty
  `class_name`, so typed GDScript degraded `node.smoke_resource` to plain
  `Object`/`Resource` (no safe static typing or completion). Both metadata
  paths emit it now — the generated instance property list
  (`ClassDB.PropertySpec` → `GDExtensionPropertyInfo`) and the script-level
  dictionaries (`PropertyInfo.from_dict`). Also replaces the always-true
  `_is_placeholder_fallback_enabled` stub with GDScript-parity semantics
  (fallback only for a script that failed to bind), and documents why the
  remaining constant-returning Script/ScriptLanguage virtuals are correct as
  constants. Runtime smoke asserts `class_name` on both paths plus a statically
  typed member read.
- GDScript can now statically type against Kanama `@GlobalClass` script classes
  (issue #106): `@export var x: CustomResource` plus
  `var copy: CustomResource = x` no longer fails with "Cannot assign a value of
  type res://CustomResource.kt … with specified type res://CustomResource.kt".
  The `.kt` resource loader pre-set the script's path inside `_load`, which made
  the engine's post-load `set_path()` early-return without registering the
  script in ResourceCache — so every `load()` of the same `.kt` produced a
  distinct Script object, and GDScript's analyzer (which compares script types
  by identity) rejected the class as its own type. The loader now leaves path
  assignment to ResourceLoader, and `KanamaScript` implements
  `_inherits_script` (same script object, or same bound Kotlin class) instead
  of always answering `false`, so typed containers such as
  `Array[CustomResource]` also match. Runtime smoke now pins the reported
  shape: typed member + typed local assignment, `is` check, load identity, and
  a property round-trip.

### Changed

- `GD.isInstanceValid` now takes `GodotObject?` instead of `Any?`, matching the
  Web backend (task 78). Godot answers false for every non-object Variant, so
  the old signature let an instance id or a string compile and then always
  return false; a wrong argument is now a compile error. New
  `GD.isInstanceIdValid(id: Long)` covers the id spelling — it does not
  dereference the object, so it is the safe check to keep across a `free()`.
  Source-breaking only for callers that passed a non-`GodotObject` value, which
  could not have been working.
- **Desktop/Android: every native call adapter is now generated during bootstrap,
  before Godot can call back into the JVM** (task 83). Linking a Panama downcall
  handle for a `FunctionDescriptor` the process has not seen makes the JVM
  generate native code; doing that while execution is already inside a
  Godot→JVM upcall puts code generation inside a thread-local
  executable-memory transition. `KanamaBinding.init` now calls
  `NativeCallSurface.prewarm()` before installing the lifecycle upcall stubs, so
  an upcall only ever *executes* adapters that already exist. Measured on the
  example project: 16 of the 18 adapter shapes used to be generated inside an
  upcall, between 0.118 s and 0.544 s after launch; the prewarm moves all of
  them to 0.001–0.064 s and costs about 30 ms before Godot's first callback.
  `KANAMA_TRACE_NATIVE_ADAPTERS=1` prints each adapter with its timestamp and
  the first-upcall boundary; the runtime, tool, and hot-reload smokes assert no
  adapter is created after it, and
  `scripts/check_native_call_surface.py` (a `local_ci.sh` stage) fails the build
  if a source change adds a call shape that is not prewarmed. No ABI change.
  This is a hardening change with no known user-visible symptom.

## 0.4.0 - 2026-07-24

### Added

- **Web backend promoted to Experimental (Kotlin/Wasm preview)** on Godot 4.7
  stable. A reproducible source-checkout export workflow (`buildWebScripts` /
  `exportWeb`) turns the two validated demos (Starter-Kit-Match3, Bunnymark) into
  self-contained, cache-busted, HTTP-servable Web exports with a release payload
  report and a versioned export-smoke harness (`scripts/web_export_smoke.sh`).
  Both demos pass the automated smoke in Chrome (the CI gate) and Firefox; in
  Safari, Bunnymark passes automatically and Match3 is verified by hand. Adds the
  [Web export guide](docs/exporting/web.md). Still **not a Supported target**:
  single-thread Compatibility renderer only, no packaged addon, two-demo corpus.

- Create script-backed custom resources from Kotlin with `newScriptInstance<T>()`
  — the equivalent of GDScript's `MyResource.new()` (issue #38). `T` must be a
  `@ScriptClass(attachTo = "Resource")` `@GlobalClass`. It returns an
  `OwnedScriptResource<T>` holding the live `instance` and its owning `resource`;
  `close()`/`use { }` releases the owning reference (the factory takes one, like
  `.new()`, so the resource survives a `ResourceSaver.save`). Construction is
  exception-safe (a failed attach never leaks the reference) and the loaded
  script wrapper is released after attach. **Deferred on iOS** (compile-compatible
  stub that throws at runtime) — supported on desktop and Android, including
  R8-minified Android release (the `@ScriptClass` annotation is now `BINARY`-retained
  and consumer keep rules preserve `@ScriptClass` class names, so
  `T::class.qualifiedName` still matches the registered template under obfuscation;
  device-validated on Pixel 7).

### Changed

- **Source-breaking:** object parameters that Godot 4.7 explicitly marks
  `meta: "required"` are now generated as **non-null**, including the 65
  Resource/RefCounted-derived required arguments that were previously nullable.
  For example `ResourceSaver.save(resource: Resource, …)`,
  `CanvasItem.drawTexture(texture: Texture2D, …)`, and the required-shape
  arguments on `Control`, `InputMap`, `PhysicsServer2D/3D`,
  `PhysicsDirectSpaceState2D/3D`, `Shape2D`, `CollisionObject2D/3D`, `OS`, and
  `Input` no longer accept `null` and marshal via `requireOpenHandle()` without a
  safe-call. This narrows public signatures: a call site passing `null` (or a
  nullable value without a null-check) to one of these now fails to compile —
  which is the honest contract, since the engine rejects null there. Unmarked
  legacy Resource parameters stay nullable, and the audited
  `NULLABLE_OBJECT_PARAM_OVERRIDES` (e.g. `Node.set_owner`) keep their null. The
  policy (`required` wins over resource ancestry) is centralized in the generator
  and locked by `audit_generator_object_policy.py`.

- Desktop/Android `Resource` now extends `RefCounted`, restoring Godot's real
  `Object > RefCounted > Resource` chain (which iOS already had). Previously
  `Resource` was its own wrapper root that re-implemented the refcount lifetime
  and hid `GodotObject`'s surface, so `setMeta`/`getMeta`/`connect`/
  `callDeferred` were unreachable from every `Resource` subclass without an
  `asObject()` hop. They are now callable directly. The duplicated refcount
  policy is gone — `Resource` inherits one implementation from `RefCounted` —
  and the inheritance audit enforces the parent rather than whitelisting
  `Resource` as a root. `asObject()` is kept as a compatibility alias. No
  intended behaviour change to resource lifetime; purely a surface gain.

- `scripts/local_ci.sh` failures are now self-announcing. A failing stage prints a
  banner naming the stage, the failing command, its exit code, and the source line —
  always as the last output, so it cannot be buried. The smoke scripts
  (`runtime_smoke`, `tool_smoke`, `hot_reload_smoke`,
  `hot_reload_in_process_smoke`) additionally repeat the failed assertion *after*
  their ~120-line Godot log dump, along with the full log path. Previously a failed
  assertion printed its reason before the dump, leaving the run looking like a
  non-zero exit with no error message. Also fixes a genuinely silent exit: the
  Kanama version probe ran before any error trap was installed.

### Fixed

- A resource created with `X.create()`, handed to the engine, and then released
  via `close()`/`use { }` is no longer freed out from under the engine (issue
  #91). Assigning `StandardMaterial3D.create()` to a `MeshInstance3D` surface (or
  material) override and closing it dropped the engine's only reference, so the
  material vanished from the saved scene (`Parameter "material" is null`). The
  desktop/Android backend now constructs via Godot 4.7's `classdb_construct_object3`
  (replacing the deprecated `construct_object2`), which returns RefCounted values
  **already owned** — matching what iOS already did. So `create()` is owning and
  `close()` releases only the wrapper's reference while the engine keeps its own.
  **Contract: close what you create** — a created resource you never `close()`
  (or `use { }`) leaks its reference; prefer `X.create().use { … }`. (This also
  subsumes issue #81 and let the earlier `ResourceSaver.save` guard be removed.)

- Migrated off deprecated 4.7 GDExtension functions and converged the JVM and iOS
  backends on the same entry points: `classdb_construct_object3` (was
  `construct_object2` on desktop/Android) and `get_godot_version2` (was
  `get_godot_version`). This is what makes a freshly created resource owned at
  construction (see the `X.create()` fix above), which is why the earlier
  issue-#81 `ResourceSaver.save` protective-reference guard could be removed —
  `save` needs no special-casing now.
- A throwing `@ScriptProperty` accessor no longer aborts the process. Generated
  property get/set dispatch ran inside an FFM upcall stub with no exception guard
  (unlike method calls), so any `Throwable` escaping a user getter/setter unwound
  through native frames and killed the JVM — taking Godot down with it (exit 134
  + `hs_err` dump) instead of surfacing as an engine error. Every exported
  property shape was one unchecked exception away from a process abort. Both entry
  points now contain the `Throwable` and log it: a rejected write keeps the
  previous value and still reports the property as owned; a throwing getter
  nil-initializes the return so the engine does not treat the property as missing
  (which would abort the *calling* function instead).

- `Node.setOwner(null)` now compiles and clears the owner through the typed
  wrapper (issue #60). Godot uses a null owner to clear it (the engine itself
  calls `child->set_owner(nullptr)` while replacing nodes), but the generator
  emitted a non-null `Node` parameter, so the only way to pass null was the
  dynamic `call("set_owner", null)`. Object-parameter nullability is now decided
  by one contextual policy (consumed by both the type renderer and the
  marshalling so they cannot drift): explicitly `meta: "required"` parameters
  stay non-null; Resource/RefCounted-derived stay nullable (legacy); and an
  audited `(class, method, arg)` override admits null for a specific ordinary
  object — seeded with `Node.set_owner(owner)`, each entry citing the pinned 4.7
  source. Generated `Node.owner` is now a writable `Node?` property, and the
  desktop hand-shaped `Node` exposes the same. (Tightening the 65 explicitly
  `required` resource parameters to non-null is a separate, source-breaking
  follow-up.)

- iOS: a `MutableList<T>` `@ScriptProperty` no longer generates non-compiling
  Kotlin/Native. The iOS registrar's list-property setters decode into an
  immutable `List`, which is not assignable to a `MutableList` field
  (`Assignment type mismatch: List<String> vs MutableList<String>`), so an iOS
  build of any script with a mutable-list export failed to compile. The setters
  now append `.toMutableList()` for mutable properties, mirroring the desktop
  emitter — which had handled this all along, while the iOS emitter dropped the
  mutability flag entirely. Covers the string-list, engine-wrapper-list,
  `@ScriptClass`-element-list, and enum-list arms. Immutable `List<T>` is
  unchanged.

- `ClassDB.class_call_static` no longer frees RefCounted instances before
  returning them: like `ClassDB.instantiate` ([#42](https://github.com/falcon4ever/kanama/pull/42)),
  a static factory can hand the fresh instance's **only** reference back inside
  the return Variant, and the borrowed dynamic decode destroyed that Variant
  after extracting the pointer — a use-after-free for every RefCounted result
  (e.g. `RegEx.create_from_string`, `Image.create`). RefCounted results are now
  retained before the Variant is destroyed and come back as the owning
  `RefCounted` wrapper (`close()` releases; assigning into a node/scene refs
  independently, matching C# semantics). Non-RefCounted and non-object results
  are unchanged. Desktop, Android, and iOS (the retain happens inside the shared
  `kanama_ios_godot_object_call` shim, before it destroys the return Variant).

- `newScriptInstance<T>()`'s editor "build a real instance instead of a
  placeholder" override is now scoped to the specific resource being created, not
  thread-wide. A non-`@Tool` script instantiated reentrantly on the same thread
  during the create (e.g. a scene loaded from a resource constructor) was also
  forced to a real instance, bypassing the editor placeholder it should have
  gotten. The override now keys on the owner under construction, so any unrelated
  reentrant instantiation keeps its placeholder. Editor-only.

## 0.3.0 - 2026-07-16

This release closes Kanama's mobile + convergence phase. The headline is
**full cross-platform parity on Godot 4.7 stable**: desktop (macOS arm64,
Linux x64/arm64, Windows x64) and Android are **Supported**, and the new iOS
Kotlin/Native backend graduated from experimental to **Supported** as well —
all running the same generated Godot API wrappers under a cross-platform drift
gate. Wrapper coverage reached ~99% of classes/methods, the docs were
reorganized into consumer/maintainer/internal tracks, and the support tiers
were formalized. See `docs/internals/release-support-decision.md` (moved out of the public docs
tree on 2026-07-14; see `docs/internals/README.md`) for the
grounded support matrix and §7 for the mobile promotion bar. `0.3.0` is a
pre-1.0 preview baseline.

### Added

- Exporting custom `Map` properties from scripts and resources is now supported
  (issue #40). A `@ScriptProperty`/`@Export` `Map<K, V>` registers as a typed
  Godot `Dictionary` (`PROPERTY_HINT_DICTIONARY_TYPE`) so the inspector shows
  the matching key/value pickers. Keys may be `String`, `Long`/`Int`,
  `Double`/`Float`, `Boolean`, Godot value types (`Vector*`/`Color`), or an
  `enum class`; values reach `List<T>` element parity plus scalars/value types
  (scalars, `String`, `Vector*`/`Color`, engine resource/node wrappers, custom
  `@ScriptClass` scripts, and `enum class`). Resource-typed values are
  ownership-managed like `List<Resource>`; enum keys/values store as ordinals.
  Decoding is **fail-soft**: a wrong-typed key or value (a hand-edited entry, or
  a stale `.tscn` saved before the types changed) is skipped instead of throwing
  a `ClassCastException` out of the FFM upcall — which would abort the process.
  Nil-value handling mirrors Godot C#'s engine-backed `Dictionary`: `Map<K, V?>`
  keeps the key with a `null` value (round-tripping through the write path),
  while non-null `Map<K, V>` drops the entry (Kotlin cannot hold null there).
  Nullable object-value maps (`Map<K, Texture2D?>`) are rejected at build time
  with a clear message. iOS defers dictionary-property marshalling (keeps the
  Kotlin default, warns at build) as the enum-list exports do. See
  [Exporting Dictionaries](docs/game-dev/properties-resources.md#exporting-dictionaries).
- iOS `@ScriptProperty` codegen now **fails the build** (was a warning) when a *data* property is
  settable by the engine but not readable (a write-only get/set asymmetry). This is the exact class
  that silently shipped write-only value types and broke multiplayer replication; a new settable data
  type must gain a `getProperty`/`encodeIosReturn` path before it can compile. Object/custom-script
  refs remain intentionally exempt.
- Fixed iOS lambda signal callbacks dropping non-object scalar arguments.
  Emitted `bool`, `int`, and `float` Variants now reach Kotlin with their
  original values, matching desktop and Android; this fixes integer peer IDs
  from multiplayer signals such as `peer_connected` arriving as `null`.
- Fixed script lifecycle notifications re-enabling `_process` and
  `_physics_process` after user code disabled them in `_ready` or
  `_enter_tree`. Authority-gated multiplayer input scripts now remain disabled
  on non-authoritative peers instead of letting one device drive multiple
  players.
- Fixed iOS value-type `@ScriptProperty` fields being write-only. `Vector2`,
  `Vector3`, `String`, `NodePath`, and `List<String>` are now readable by the
  engine (`Object.get`), matching desktop/Android — the iOS bridge previously
  emitted a reader only for properties with a scalar setter, so these types
  were settable from scene data but read back as `nil`. That silently broke
  `MultiplayerSynchronizer` replication of value-type properties on the
  authority peer (e.g. a `Vector2` movement vector never reaching the host, so
  a client could not move or shoot). A codegen parity guard now flags any
  future set-only data property.
- Fixed iOS lambda/bound signal `Callable`s not being auto-disconnected when
  their receiver is freed. The receiver's `ObjectID` is now recorded so Godot
  disconnects the connection on free, instead of later firing the signal into a
  freed object — a use-after-free crash on the client when a multiplayer host
  ended the game.
- iOS `@Export` / `@ScriptProperty` conversion parity: Kotlin `Int`/`Float`
  fields now narrow correctly from Godot's 64-bit Variant slots, scalar enum
  ordinals clamp and resolve to entries, and `List<Enum>` arrays map each
  ordinal with the same clamp/null-fill semantics as desktop/Android. A
  dedicated integer-array bridge keeps enum ordinals separate from object
  handles. iOS property metadata now preserves enum hints and live
  ScriptInstance getters widen values back to engine slots; scene-loaded
  values plus later `Object.set` / `Object.get` updates are covered by the iOS
  project-script probe.
- **iOS and Android promoted from Experimental to Supported (4.7 stable)**: the
  §7 mobile promotion bar (device-matrix breadth, renderer coverage, release-grade
  packaging, heavy-demo) is fully green on both platforms. Android is
  device-validated across four models (Pixel 7 / Moto g 5G 2023 / Galaxy S10+ /
  Pixel 3 XL) — **debug validated to Android 9; release builds require Android
  13+** (validated 14/16; the A12 release-mode PanamaPort constraint is
  documented and upstream-shaped). iOS is device-validated on iPhone 12 + iPhone
  15 Pro. Carried caveats: packaged mobile addons are runtime-only (compiling
  project scripts needs the Kanama checkout), the Android release path depends on
  the JitPack PanamaPort fork (`0.1.3-kanama-r8.4`), and no mobile hot reload.
  `0.3.0` remains a pre-1.0 preview baseline.
- Linux x86_64 promoted to **Supported (4.7 stable)**: full local CI, native
  bootstrap preflight, strict docs, all 11 demo builds, the nine-demo desktop
  smoke matrix, TPS checked smoke, distribution packaging, and
  desktop-kit/store-addon install smokes passed on Ubuntu 25.04 with Godot
  `4.7.stable.official.5b4e0cb0f` and OpenJDK 25.0.2 (2026-07-13/14). Requires
  the resource-loader/saver teardown fix below.
- Linux arm64 promoted to **Supported (4.7 stable)**: the same full gate suite
  (local CI incl. the resource-loader/saver teardown `runtime_smoke`, strict
  docs, all 11 demo builds, nine-demo desktop smoke matrix, TPS checked smoke,
  distribution packaging, desktop-kit/store-addon install smokes, and an AArch64
  ELF native preflight) passed on a native Ubuntu 26.04 AArch64 host with Godot
  `4.7.stable.official.5b4e0cb0f` and OpenJDK 25.0.3 (2026-07-14).
- Fixed a shutdown hang on Linux x86_64: `KanamaResourceFormatLoader` and
  `KanamaResourceFormatSaver` now destroy their explicitly constructed handler
  objects during `unregister()`, before `ClassDB` tears down their extension
  classes. Leaving the live instances behind corrupted/blocked shutdown on Linux
  (orphan `StringName` and allocator diagnostics); desktop processes now exit
  cleanly.
- Windows x86_64 promoted to **Supported (4.7 stable)**: full local
  revalidation on the 4.7 stable console binary (2026-07-13) — demo audits,
  script builds, imports, nine-demo desktop runtime smoke, TPS smoke, and the
  packaged desktop-kit/store-addon install smokes.
- iOS `Mathf.roundToInt` parity helper (Godot `roundi` semantics, half away
  from zero), matching the desktop `Mathf.roundToInt` facade; demo ports can
  now share the same rounding call on both backends.
- `.gdextension` descriptors written by the addon install and packaging tasks
  now always carry the `ios.*.arm64` xcframework entries. Installing the addon
  from Windows/Linux no longer strips the iOS lines from a project's committed
  descriptor (Godot ignores non-target platform entries).
- iOS wrapper breadth (task 30): the generated iOS Godot API class set grew from
  ~254 to **1017 classes — full desktop-equivalent breadth**. Every desktop
  generated class is now emitted on iOS except the documented exceptions
  (`DirAccess`, whose draft depends on a desktop-only hand-authored
  handle-alias class, and `MethodTweener`, which clashes with the hand-written
  iOS Tween glue; `FileAccess` is since hosted as a hand-written static facade). Un-audited per-method marshalling shapes remain conservatively
  skipped with generator-report entries — never stubbed — and the full
  cross-platform drift gate holds committed == fresh regen at the new counts.
- iOS RefCounted return ownership (task 31 mirror): the C shim now exposes
  `object_destroy`, the generated iOS `RefCounted` owns the +1 reference every
  RefCounted-typed ptrcall return transfers (`close()` releases it: unreference +
  destroy at zero), and fluent self-returning methods emit the same
  reference-neutral collapse pattern as desktop/Android. This closes the
  per-call engine-reference leak on RefCounted-typed returns on iOS; an
  on-device refcount probe (`refcounted-ret-owns-plus1`) guards the convention.
- Resource-typed exports widened
  ([#36](https://github.com/falcon4ever/kanama/issues/36)): `@Export` /
  `@ScriptProperty` slots now accept `AudioStream`, `Texture`, `Mesh`,
  `Shape2D`, `Shape3D`, `Font`, `Animation`, and `StyleBox` (base-typed slots
  accept engine subtypes). Reads stay ownership-managed: retained while held,
  released exactly once on script cleanup.
- Subclassing a generated wrapper (e.g. `class X(...) : Resource(...)`) now
  fails the build with a Kanama error naming the supported pattern
  (`@ScriptClass(attachTo = "Resource")` on a plain class) instead of Kotlin's
  opaque internal-constructor error. `lateinit` exports now warn (no inspector
  default; crashes if read before assignment) and point to the nullable
  `= null` shape. Docs: "Resource Slots" + expanded "Custom Resources" in the
  exports guide.
- `@Export` / `@ScriptProperty` now works on Kotlin `enum class` properties
  ([#37](https://github.com/falcon4ever/kanama/issues/37)). Matching C# enum
  exports, the property registers as an `int` with `PROPERTY_HINT_ENUM` and the
  entry names as the hint string, so the inspector renders a dropdown; values
  are stored as entry ordinals, `.tscn`-stored ints deserialize back into the
  enum slot, and out-of-range stored values clamp to a valid entry instead of
  crashing. Enum-entry initializers (`var mode = MyEnum.NORMAL`) are preserved
  as inspector defaults. `@ScriptClass` scripts work across desktop, Android,
  and iOS.
- `@Export` / `@ScriptProperty` now also works on **lists of Kotlin enums**
  (`List<MyEnum>` / `MutableList<MyEnum>`,
  [#40](https://github.com/falcon4ever/kanama/issues/40)). Matching C# enum-array
  exports, the property registers as a typed int Array whose elements carry
  `PROPERTY_HINT_ENUM` with the entry names (the inspector renders an array of
  dropdowns); elements are stored as ordinals, `.tscn`-stored arrays deserialize
  back into the list, and out-of-range stored elements clamp to a valid entry.
  Same scope as scalar enum exports: `@ScriptClass` scripts work across desktop,
  Android, and iOS. `Map<K, V>` exports remain out of scope (non-String
  Dictionary keys are a policy gate).
- `@OverrideVirtual` now supports **every Variant-expressible return type** in
  the Godot 4.7 virtual surface (170 additional virtuals). New Kotlin return
  types: all fixed-element packed arrays (`ByteArray`, `IntArray`, `LongArray`,
  `FloatArray`, `DoubleArray`, `List<Vector2>`, `List<Vector3>`, `List<Color>`),
  `Map<String, Any?>` (Dictionary), `List<Any?>` (generic and typed Arrays),
  `RID`, `Rect2`, `AABB`, `Transform2D`, `Transform3D`, and `Projection`.
  `StringName`-returning virtuals are declared as `String` and enum/bitfield
  returns as `Long` (the engine converts at the call site). Desktop/Android
  cover all families; iOS covers everything except
  `Rect2`/`AABB`/`Transform2D`/`Transform3D`/`Projection` returns (documented
  in the wrapper-maintenance guide). The only excluded virtuals are the 6
  raw-pointer (`void*`/`const Glyph*`) callbacks, which are not
  Variant-expressible by design.
- Added an iOS Kotlin/Native backend that runs full Kanama project
  scripts: a C GDExtension shim plus GENERATED Godot API wrappers (the same
  wrapper generator as desktop/Android) over a C-shim generic `ptrcall`. The core
  self-test and current demo corpus have playable device runs; per-frame Kanama
  binding overhead measured ~0.63 ms on iPhone 12. iOS shipped experimental in
  this cycle and was then promoted to Supported (4.7 stable, above); see
  `docs/internals/active/ios-backend-roadmap.md` (moved out of the public docs tree
  on 2026-07-14; see `docs/internals/README.md`) for its initial gaps.
- Added an iOS hand-written/stub registry: `// KANAMA-IOS-{STUB,HANDWRITTEN,SUGAR}`
  markers, `scripts/ios_handwritten_report.py` (generates
  `docs/internals/reference/ios-backend-handwritten.md`), and `scripts/check_ios_no_silent_stubs.py`
  (fails CI on an un-annotated silent stub). The current registry is 0 STUB / 0 SUGAR.
- Added iOS physical-device validation tooling: a visual smoke script with an
  optional Kotlin/Native frame probe that updates a Godot `Label` through a
  cached typed `ptrcall`. Simulator checks remain available for compile/link
  debugging only.
- Made the iOS install path build device-only xcframeworks by default, with an
  explicit `kanamaIosXcframeworkMode=full` escape hatch for simulator work.
- Reached iOS demo parity: the public demo corpus runs on device (full 9-demo
  device gate on iPhone 12, playable corpus + singleton/virtual self-tests on
  iPhone 15 Pro, both on Godot 4.7 stable). Landed the iOS long-tail wrapper
  shapes (Transform2D, NodePath/StringName returns, `Packed*`/`Typed*` arrays,
  Variant scalars), `Callable`/vararg support (object+method callables, varargs),
  and non-POD virtual returns (`String`, `PackedStringArray`, `Variant`/`Any?`)
  across desktop/Android/iOS.
- Added `@Rpc` config delivery so Godot receives RPC configuration on all three
  platform backends (desktop, Android, iOS).
- Hardened the Android path: the Godot 4.7 stable debug demo matrix and an
  R8-minified Match3 release APK both pass on a physical Pixel 7. R8 minification
  is validated against Kanama's PanamaPort fork, which fixes an upstream
  sealed-switch miscompile that crashed the FFI bootstrap under R8.
- Grew the generated Godot API wrapper surface to ~99% coverage (classes
  1032/1036 = 99.6%, callable methods 15274/15385 = 99.3%); engine virtuals are
  overridable across POD plus `String`/`PackedStringArray`/`Variant` returns.
  Residual exotic shapes are documented as promote-on-demand.
- Added a full cross-platform wrapper drift gate (`check_full_drift_gate`):
  committed wrappers are byte-identical to a fresh regeneration per platform
  (desktop 985 / iOS 242), making silent cross-platform wrapper drift
  structurally impossible.
- Extended `isEqualApprox` to composite value types (Transform3D, Basis, AABB,
  Rect2, Transform2D, Projection).
- Added a Godot-version pin as a single source of truth (`kanamaGodotVersion`)
  plus a KDoc sync tool and drift check wired into local CI, so a Godot upgrade
  is a repeatable process.
- Added `MobileControls` (virtual joysticks + on-screen buttons) to the
  `tps-demo-kanama` companion demo for Android/iOS touch play.

### Changed

- Updated the Kanama preview baseline to Godot 4.7 stable. Re-dumped
  `extension_api.json` and regenerated Godot API name constants for the stable
  version metadata; the 4.7 stable `extension_api.json` (excluding the
  `version_status` header field) and GDExtension interface header are
  byte-identical to 4.7 rc 2, so generated bindings, wrappers, name constants,
  and struct layouts are unchanged. Docs, scripts, templates, and the demos
  README were updated from `4.7 rc 2` to `4.7 stable`.
- Updated the Kanama preview baseline to Godot 4.7 rc 2. Re-dumped
  `extension_api.json` and regenerated Godot API name constants for the rc 2
  version metadata; the rc 2 GDExtension interface header and API body are
  byte-identical to beta 5, so generated bindings and wrappers are unchanged.
- Updated the build toolchain to Kotlin 2.3.21, KSP 2.3.9, and
  kotlinx.coroutines 1.11.0, with Gradle build cache enabled for the main and
  Android plugin builds.
- Enabled Kotlin Multiplatform cinterop commonization for the experimental
  iOS runtime.
- `installIosAddon` now preserves a project's Android (and desktop)
  `kanama.gdextension` library entries instead of overwriting them, so installing
  the iOS addon no longer regresses Android support (it mirrors `installAddonJar`'s
  Android-metadata preservation and asserts the entries survive).
- Reorganized the documentation into consumer / maintainer / internal tracks and
  refreshed the agent-facing guides (`AGENTS.md`, `CLAUDE.md`) so a fresh session
  can orient quickly.
- Refreshed generated wrapper KDoc against Godot 4.7 stable `doc/classes`
  (comment-only; verified zero code change).
- Updated the public support wording: desktop (macOS, Linux x64/arm64, Windows)
  and mobile (Android, iOS) are all **Supported (4.7 stable)** following the §7
  mobile promotion bar and the desktop 4.7-stable revalidations above.

### Fixed

- `ClassDB.instantiate` no longer frees RefCounted instances before returning
  them ([#42](https://github.com/falcon4ever/kanama/pull/42)): the engine hands
  the fresh instance's **only** reference back inside the return Variant, and
  the borrowed dynamic decode destroyed that Variant after extracting the
  pointer — a use-after-free that crashed or silently dropped anything
  instantiated by class name (the canonical construction path for third-party
  GDExtension classes such as Terrain3D). RefCounted results are now retained
  before the Variant is destroyed and come back as the owning `RefCounted`
  wrapper (`close()` releases; assigning into a node/scene refs independently,
  matching C# semantics). Non-RefCounted results (Nodes) are unchanged.
  Desktop, Android, and iOS (dedicated C-shim entry: the retain must happen
  before the shim destroys the return Variant). Thanks @T-bond for the
  diagnosis and repro.
- Android runtime now works below Android 14: the binding runtime used
  `Path.of(...)` (a Java 11 API Android only ships from API 34) in the `.kt`
  resource loader/saver and the scripts-jar lookup, so script loading died
  with `NoSuchMethodError` on older devices; switched to the equivalent
  `Paths.get(...)` (API 26+). Found and device-validated on a Galaxy S10+
  (Android 12) during the second-device promotion gate, together with a
  PanamaPort-fork fix for the same class of bug (`SDK_INT_FULL` reads that
  crash pre-Android-16 devices).
- Android release builds no longer hit an R8-emitted `filled-new-array` of
  `byte[][]` in PanamaPort's stub generation (the ART interpreter below
  Android 13 segfaults on that instruction): PanamaPort fork
  `0.1.3-kanama-r8.4` routes single-blob code generation through a
  non-varargs path. Validated Android version floors are now documented in
  the Android export guide ("Validated Android Versions"): debug builds
  Android 9+, release builds Android 13+ (validated 14/16 — below 13,
  release-mode ART crashes in PanamaPort's LLVM-driven upcall generation;
  characterized and deferred upstream).
- `@GlobalClass` Kotlin scripts now actually register in the editor's global
  class list ([#39](https://github.com/falcon4ever/kanama/issues/39)): they
  appear in the Create New Resource dialog, and `.tres` resources using them
  match typed export slots (the *"selected resource (Resource) does not match
  … (MyClass)"* rejection reported in
  [#38](https://github.com/falcon4ever/kanama/issues/38) is gone). The script
  language answered the editor's `_handles_global_class_type` routing query
  with global-class-*name* semantics instead of script-resource-*type*
  semantics ("Script" for `.kt` files), so the editor never queried
  `_get_global_class_name` and no Kotlin class ever reached the global class
  cache. A `@GlobalClass` in a file not named `<ClassName>.kt` now gets a
  build warning (the class cannot be mapped back to its script file and stays
  out of the list).

### Known Limitations

- `0.3.0` is a pre-1.0 preview baseline.
- Packaged mobile addons are runtime-only: compiling project scripts still
  requires the Kanama checkout, and there is no hot reload on mobile.
- The Android release path depends on the JitPack PanamaPort fork
  (`0.1.3-kanama-r8.4`). Debug builds run on Android 9+; release builds require
  Android 13+ (validated 14/16 — below 13, release-mode ART crashes in
  PanamaPort's LLVM-driven upcall generation).
- `Map<K, V>` exports remain out of scope (non-String Dictionary keys are a
  policy gate).

## 0.2.2 - 2026-06-05

### Changed

- Updated the Kanama preview baseline to Godot 4.7 beta 5.
- Refreshed `extension_api.json` and generated Godot API name constants for the
  beta 5 version metadata; the beta 5 API/header body matches the beta 4 input
  used by the previous preview.
- Updated Gradle coordinates, release packaging defaults, documentation, and
  demo project defaults for Kanama `0.2.2`.

### Known Limitations

- Android remains experimental. Godot 4.7 beta 5 Android APK smoke validation is
  pending before changing Android support claims.

## 0.2.1 - 2026-05-26

### Added

- Fresh-clone smoke validation for release checks and clean source checkouts.
- A ready-to-run starter project creation task for first-time Kanama projects.

### Changed

- First-run documentation now starts from the new starter project flow and
  source-install validation path.
- Clean Gradle environments now get explicit JVM memory defaults.

### Fixed

- Local CI now creates the generated Godot GDExtension header when it is missing
  from a fresh checkout.
- Maven local validation now publishes/checks the current Kanama version in the
  effective Maven local repository.

## 0.2.0 - 2026-05-26

### Added

- Godot 4.7 beta 4 API baseline updates for generated wrappers, docs, and
  smoke validation.
- Editor workflow helpers for opening Kotlin sources and common build actions
  from the Kanama Tools dock.
- Basic Kotlin syntax highlighting in the Kanama Tools editor integration.
- Additional export inspector metadata support, including property hints,
  categories, groups, subgroups, and inspector tool buttons.
- Typed signal helper overloads for common connect, emit, and await usage while
  keeping the existing string-based signal APIs available.
- Convenience editor-time script helpers for `@Tool` scripts, including editor
  hint checks and inspector/property-list refresh.
- Generated engine-wide `MethodName`, `PropertyName`, and `SignalName`
  constants for type-safe Godot API name references.
- Multiplayer and porting guardrail audits for risky runtime node lookups, raw
  string dispatch, and `SceneReplicationConfig` custom properties.
- Multiplayer docs covering generated RPC helpers, ENet peer setup, replicated
  script properties, and review checklist items for ports.

### Changed

- Script/runtime hot paths are leaner, including vector math, object-array
  decoding, string-name handling, and common object call paths.
- Runtime diagnostics now report more lifecycle and script binding context when
  debugging editor/runtime integration failures.
- Example project registries are separated from runtime registration paths so
  local examples do not hide integration drift in external projects.
- Runtime and local CI smoke checks now assert the QoL metadata, tool button,
  and generated name-constant coverage.
- The replicated-script-property audit can now check multiple project roots in
  one run, so demo aggregate checks can use the same guardrail script as local
  CI.

### Fixed

- Script object lifecycle and property replay now preserve inspector-authored
  values more reliably across load/reload paths.
- Retained resource wrapper lifetime handling no longer drops resource handles
  too early in common script property flows.
- Custom resource script properties now release only the retained custom
  resource handle during parent cleanup. They no longer recursively clean the
  child script's exported resource properties, which could close shared
  resources such as `PackedScene` models too early.
- Getting-started, README, local docs preview, and release-validation examples
  were corrected for the current source-first workflow.

## 0.1.0 - 2026-05-19

### Added

- Desktop Kotlin script runtime for Godot through GDExtension and the JDK
  Foreign Function & Memory API.
- KSP-based script registration for lifecycle callbacks, exported properties,
  signals, global classes, and editor tool scripts.
- Hot reload support for desktop editor workflows.
- Generated Godot API wrappers with reproducibility checks and ABI policy
  audits.
- Promoted Kotlin wrapper classes for the Godot 4.7 beta 4 API baseline, with
  conservative method coverage documented in the wrapper coverage report.
- Generated KDoc carried from Godot API documentation for wrapper classes and
  methods.
- Starter project template and example smoke-test project.
- MkDocs documentation covering setup, API usage, distribution, wrapper
  coverage, and maintainer internals.
- Companion demo projects used as integration coverage for real gameplay code.
- Experimental Android export workflow through a Godot Android plugin AAR,
  Android ART, and PanamaPort, smoke-tested on emulator/Pixel 7 paths with
  eight public demo targets.

### Changed

- Wrapper generation is now an active source-promotion path instead of a report-only
  experiment.
- Public documentation has been organized around getting started, porting,
  manual pages, reference coverage, and internals.

### Known Limitations

- Kanama is desktop-first. macOS arm64 is the active development and smoke-test
  platform; Linux and Windows have runtime/demo smoke coverage with remaining
  automated editor/tool shutdown caveats documented in Version Support.
- Android exports are experimental. The current path includes Android smoke
  targets with selected touch overlays, D-pad controls, virtual joysticks, and
  demo warmup fixes for first-use hitches, but Vulkan/Mobile renderer coverage,
  hot reload, and complete phone-specific UI polish are not release claims yet.
- iOS is not supported. Web export is not planned.
- Broad `Callable`, `Dictionary`, generic container, virtual override, and
  ownership-sensitive APIs remain conservative policy buckets.
