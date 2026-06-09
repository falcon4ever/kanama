# iOS Backend Backlog

Durable, in-repo backlog of known iOS-backend gaps and follow-ups, so they are
remembered in the repo rather than only in the private session-handoff notes
(`kanama-ios-support.md`). None of these block the current generated-API pivot
(see [implementation plan](./ios-backend-implementation-plan.md)); they are deferred
work. Architecture context: [ios-backend-architecture.md](./ios-backend-architecture.md).

## Open

### Audio not audible
- **What:** AudioStreamPlayer playback is wired and the machinery is device-verified
  (sounds play and the player pool recycles — ~138 `finished` dispatches / 137 frees
  in one Match3 session), but **no audio is heard** on device.
- **Likely causes (unverified):** master bus / `AudioServer` not initialized on iOS;
  `.ogg` stream resource not actually loading on device (resource import); volume/bus
  routing; player not effectively in the tree.
- **First step:** log the loaded stream handle in `setStreamFromPath` (non-zero?) and
  the bus; check `AudioServer` init. Localizes quickly.

### Non-object signal payloads to lambda callbacks
- **What:** the custom-Callable dispatch forwards only **object-typed** signal args to
  lambda callbacks (up to 4, as handles → `GodotObject`); other types (int/float/bool/
  string/vector) arrive as `null`.
- **Why low priority:** real signal lambdas are overwhelmingly 0-arg (`finished`,
  `timeout`) or 1-object (`body_entered`), both already covered.
- **To do:** pass per-arg variant type + value through the dispatch trampoline
  (`kanama_ios_callable_trampoline` → `kanama_ios_runtime_dispatch_callable`) and
  reconstruct typed values Kotlin-side.

### `_get_script_signal_list` (editor-only)
- **What:** the script virtual `_get_script_signal_list` is still stubbed to an empty
  array. `_has_script_signal` is implemented (which is what `connect`/`emit` need at
  runtime), so this only affects editor introspection / `Object.get_signal_list()`.
- **Why low priority:** irrelevant on-device. Pick up if/when editor integration is
  pursued. Needs building a Godot `Array<Dictionary>` in C (no array-push helpers yet).

### 3D performance review — now tracked as Phase 5
- Promoted from backlog to an explicit milestone: see **Phase 5** in
  [ios-backend-implementation-plan.md](./ios-backend-implementation-plan.md). Measure
  Godot rendering/GPU vs Kanama scripting/binding overhead separately, on iPhone 12
  **and** 15 Pro, with per-frame debug logging off. The 60fps baseline was 2D Match3;
  3D is unverified.

## Notes
- Latent: the generic ClassDB `.create()` pattern is stubbed for engine classes other
  than `AudioStreamPlayer`; use `IosGodot.constructObject(name)` (or the generated
  equivalent) as the template. The generated-API pivot supersedes this.
