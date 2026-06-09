# iOS Backend Backlog

Durable, in-repo backlog of known iOS-backend gaps and follow-ups, so they are
remembered in the repo rather than only in the private session-handoff notes
(`kanama-ios-support.md`). None of these block the current generated-API pivot
(see [implementation plan](./ios-backend-implementation-plan.md)); they are deferred
work. Architecture context: [ios-backend-architecture.md](./ios-backend-architecture.md).

## Open

### Audio not audible — LIKELY FIXED 2026-06-09 (confirm by ear)
- **What it was:** AudioStreamPlayer machinery ran (play + pool recycle) but no sound.
- **Root cause found via the T2.1 self-test matrix:** scalar `float` args marshal as
  8-byte `double` at ptrcall (`PtrToArg<float>=convert<float,double>`), but the audio
  binding used a 4-byte `float` cell — so `set_volume_db` got a garbage dB (→ silence).
  Fixed by making `kanama_ios_godot_ptrcall_float_arg` pass a `double`
  (also covers `set_pitch_scale`, `play`).
- **Status:** needs an on-device by-ear confirmation (swipe Match3 → swap/match
  sounds). If still silent, fall back to the other suspects: master bus /
  `AudioServer` init / `.ogg` import / player in tree.

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
