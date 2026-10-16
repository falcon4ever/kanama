// demos/dodge.mjs -- dodge-the-creeps play + teardown assertions for the Web smoke.
//
// Unlike Bunnymark (driven by bridge method calls) and Match3 (driven by synthetic
// pointer input), dodge is a real-time, self-driven demo: the driver presses Start
// (HUD._on_StartButton_pressed -> Main.new_game), lets Godot's MobTimer spawn mobs and
// the ScoreTimer tick, plays two rounds (each ended by game_over, then Start) to measure
// per-mob handle retention between rounds, tears the scene down and quits. Beyond those calls (and
// resuming a round the player was hit in early) the driver OBSERVES: it polls the bridge
// telemetry, captures the gameplay peak (mobs instantiated + added to the tree,
// kotlin->godot crossings advancing) and the post-teardown trough, and asserts a clean
// run with no callback faults.

import { callSmokeMethod, collecting, resolveMethodId } from "../envelope.mjs";

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
// Task 88: prove the post-teardown invariant can FAIL. Set
// KANAMA_WEB_T81_FALSIFY=pre-teardown-registries to judge ownerRegistriesToBaseline
// against the mid-play sample (mobs alive, callbacks and coroutine jobs registered)
// instead of the settled one. The run must go RED -- a gate only ever observed
// passing is not evidence that it discriminates.
const FALSIFY = process.env.KANAMA_WEB_T81_FALSIFY ?? "";
// Every run ends round 1 with a restart once it has spawned this many mobs, so every run measures
// two rounds. Four, not two: a handle kept once per round (not per mob) then reads 0.25 per mob,
// well clear of the 0.5 retention threshold instead of exactly on it.
const FORCED_RESTART_AT_MOBS = 4;
// The earliest a mob can reach the stationary player, in game seconds after the round's first spawn:
// StartPosition (240, 450) is at least 240 px from the spawn path (the 480x720 screen edge), the
// player's capsule (radius 27, height 68) and a mob's (radius 37, height 100, lying along its
// velocity) touch at most 34 + 50 = 84 px apart centre to centre, so a mob travels at least 156 px,
// at most 250 px/s: 0.62 s. A hit sooner than this is an instant-kill bug; the margin absorbs the
// physics step and the poll that observes the hit.
const EARLIEST_HIT_SECONDS = 0.4;
// How long round 2 keeps playing, in game seconds after its first spawn, for mobs to fly off screen
// and free themselves (Mob._on_VisibilityNotifier2D_screen_exited -> queue_free) before the driver
// ends it. A mob crosses the 480x720 screen in 2-7 s at 150-250 px/s. Those frees are REPORTED
// (mobScreenExitFrees), not required: on the Linux CI runner's Firefox screen_exited never fires
// (task 71), and every mob is freed by the round's queue_free instead, which mobsSpawnAndFree judges.
const SCREEN_EXIT_WAIT_SECONDS = 8;
// KANAMA_WEB_DODGE_NO_SCREEN_EXIT=1 reproduces that host anywhere: the probe swallows every
// Mob._on_VisibilityNotifier2D_screen_exited dispatch, so no mob frees itself off screen. The run
// must stay GREEN (the round's queue_free frees every mob) with mobScreenExitFrees 0.
const NO_SCREEN_EXIT = process.env.KANAMA_WEB_DODGE_NO_SCREEN_EXIT === "1";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[dodge ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
};

// Reads a flat telemetry snapshot from the bridge. Every field is generic (the
// match3-prefixed instantiation/addChild counters are, despite the name, incremented
// for any mode). Returns null if the page is mid-navigation or has been torn down.
async function snapshot(evaluate) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      if (!bridge) return null;
      const classCount = (suffix) => {
        const entry = Object.entries(bridge.match3ReadyByClass ?? {}).find(([n]) => n.endsWith(suffix));
        return entry?.[1] ?? 0;
      };
      const liveCount = (suffix) => {
        const entry = Object.entries(bridge.liveScriptsByClass ?? {}).find(([n]) => n.endsWith(suffix));
        return entry?.[1] ?? 0;
      };
      return {
        mode: bridge.mode,
        protocol: bridge.results?.protocolVersion ?? 0,
        mainHandle: bridge.dodgeMainHandle,
        smokeQuitHandle: bridge.smokeHandle,
        readyCount: bridge.readyCount,
        mainReady: classCount(".Main"),
        playerReady: classCount(".Player"),
        hudReady: classCount(".HUD"),
        mobReady: classCount(".Mob"),
        // Mob scripts freed so far: readied minus live. liveScriptsByClass drops when the bridge
        // tears a freed script down (its _exit_tree free), whatever freed the node -- its own
        // screen_exited queue_free or new_game's call_group("mobs", "queue_free").
        mobFreed: classCount(".Mob") - liveCount(".Mob"),
        mobLive: liveCount(".Mob"),
        // Mob._on_VisibilityNotifier2D_screen_exited dispatches seen (installProbe).
        mobScreenExits: bridge.__kanamaDodgeProbe?.screenExits ?? 0,
        mobInstantiations: bridge.match3PackedSceneInstantiations,
        mobAddChildCommands: bridge.match3AddChildCommands,
        // Every mob spawn and Main.game_over dispatch so far, with its game time (installProbe).
        probe: bridge.__kanamaDodgeProbe ? bridge.__kanamaDodgeProbe.events.slice() : null,
        liveHandles: bridge.liveBrowserHandleCount,
        // Live handles broken down by kind. A per-entity leak is a RATIO -- handles
        // retained per entity created -- and a ratio needs no long window: one
        // handle per mob is as visible across seven mobs as across seven hundred.
        // Task 72 leaked exactly that and still took a ten-minute nightly soak to
        // surface, because the absolute growth is what needs time, not the ratio.
        // Are the mobs MOVING? (task 71). The mob proxy has no get_position arm,
        // but it does have opcode 27, CanvasItem.get_local_mouse_position, which is
        // the pointer expressed in the node's local space. The pointer never moves
        // in a driven run, so this value changes exactly as the NODE moves -- a
        // motion probe that needs no protocol change.
        //
        // It splits task 71 in half: if these sweep while frees stays 0, the mobs
        // move and VisibleOnScreenNotifier2D is not reporting them off-screen; if
        // they are frozen, it is physics or the velocity write.
        mobPositions: (() => {
          const out = [];
          for (const [key, name] of Object.entries(bridge.scriptNameByHandle ?? {})) {
            if (!String(name).endsWith(".Mob") || out.length >= 3) continue;
            const handle = Number(key);
            try {
              if (bridge.api.kanamaWebIsLive(handle) !== 1) continue;
              const x = bridge.immediateNoArgsVector2X(27, handle);
              const y = bridge.immediateNoArgsVector2Y();
              out.push(handle + ":" + Math.round(x) + "," + Math.round(y));
            } catch (e) {
              out.push(handle + ":err");
            }
          }
          return out.join(" ");
        })(),
        liveByKind: (() => {
          const counts = {};
          for (const slot of bridge.browserHandleSlots ?? []) {
            if (!slot || !slot.live) continue;
            counts[slot.kind ?? "(null-kind)"] = (counts[slot.kind ?? "(null-kind)"] ?? 0) + 1;
          }
          return counts;
        })(),
        maxLiveHandles: bridge.maxLiveBrowserHandles,
        crossings: bridge.kotlinToGodotCalls,
        callbackErrors: bridge.callbackErrors,
        callbacks: bridge.api.kanamaWebPendingSignalCallbackCount(),
        pending: bridge.api.kanamaWebPendingCoroutineCount(),
        jobs: bridge.api.kanamaWebRegisteredCoroutineJobCount(),
        failure: globalThis.KanamaWebFailure?.stack ?? globalThis.KanamaWebFailure?.message ?? null,
        // Diagnostics for a cell that runs but never frees a mob: the canvas the
        // engine sizes its viewport from, and the latest mirrored transform the
        // bridge saw (proof that something is actually moving in the scene).
        canvasW: document.querySelector("canvas")?.clientWidth ?? 0,
        canvasH: document.querySelector("canvas")?.clientHeight ?? 0,
        snapX: Math.round(bridge.latestSnapshotX ?? 0),
        snapY: Math.round(bridge.latestSnapshotY ?? 0),
        // Engine truth, not bridge bookkeeping: Node.get_child_count (opcode 1) on
        // Main. Mobs are added as its children and queue_free themselves off screen,
        // so this rises with every spawn and falls with every real free -- which
        // separates "the mobs never left the screen" from "they did, and the handle
        // release did not follow".
        frames: bridge.processCalls ?? 0,
        simSeconds: Math.round((bridge.simSeconds ?? 0) * 10) / 10,
        // Debug only: every live handle as kind@owner script (rc = RefCounted, lent = a dispatch
        // argument), to name what a retention trough still holds.
        liveDetail: ${DEBUG} ? (() => {
          const counts = {};
          for (const [handle, owner] of bridge.handleOwners ?? []) {
            const slot = bridge.browserHandleSlot?.(handle);
            if (!slot || !slot.live) continue;
            const ownerName = String(bridge.scriptNameByHandle?.[owner] ?? owner).split(".").pop();
            const key = (slot.kind ?? "?") + "@" + ownerName + (slot.refCounted ? " rc" : "") + (slot.lent ? " lent" : "") + (slot.page ? " page" : "");
            counts[key] = (counts[key] ?? 0) + 1;
          }
          return JSON.stringify(counts);
        })() : null,
      };
    })()`);
  } catch {
    return null; // page mid-navigation or torn down
  }
}

// Main's registered methods the driver calls or counts, resolved by NAME from the export manifest:
// a hard-coded ordinal silently runs another method once a registered function is added or moved.
const MAIN_METHODS = ["game_over", "_on_StartTimer_timeout"];
function resolveMainIds(exportDir) {
  const ids = {};
  for (const name of MAIN_METHODS) {
    const id = resolveMethodId(exportDir, "dodge.Main", name);
    if (id === null) throw new Error(`dodge.Main.${name} is not in the export manifest (KanamaWebProtocol.generated.json)`);
    ids[name] = id;
  }
  return ids;
}

async function callMain(evaluate, id) {
  return evaluate(`globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.dodgeMainHandle, ${id}); true`);
}

// Records, in the page, every mob spawn (a PackedScene instantiation) and every Main.game_over
// dispatch with the game time and the mob count at that moment, so the hit checks need no poll
// timing: a poll sees a hit up to 150 ms late, and a round can change in between. It wraps two
// bridge methods the page already calls through the bridge object (Kotlin's instantiate goes
// through `KanamaWebBridge.immediatePackedSceneInstantiate`, every registered-method dispatch
// through `recordExercisedDispatch`), and changes nothing else. `driving` marks the driver's own
// game_over call. It also counts the mobs' screen_exited dispatches (the Mob proxy calls
// `KanamaWebBridge.callNoArgs(handle, <screen_exited id>)`), and under NO_SCREEN_EXIT swallows them.
async function installProbe(evaluate, gameOverId, screenExitId) {
  return evaluate(`(() => {
    const bridge = globalThis.KanamaWebBridge;
    if (bridge.__kanamaDodgeProbe) return true;
    const probe = { events: [], driving: false, screenExits: 0 };
    bridge.__kanamaDodgeProbe = probe;
    const callNoArgs = bridge.callNoArgs;
    bridge.callNoArgs = function (handle, methodId) {
      if (methodId === ${screenExitId} && String(this.scriptNameByHandle?.[handle] ?? "").endsWith(".Mob")) {
        probe.screenExits += 1;
        if (${NO_SCREEN_EXIT}) return 0;
      }
      return callNoArgs.call(this, handle, methodId);
    };
    const recordExercisedDispatch = bridge.recordExercisedDispatch;
    bridge.recordExercisedDispatch = function (owner, callback, member) {
      if (callback === "registered_function" && member === "method#${gameOverId}" && String(this.scriptNameByHandle?.[owner] ?? "").endsWith(".Main")) {
        probe.events.push({ kind: "gameOver", sim: this.simSeconds ?? 0, mobs: this.match3PackedSceneInstantiations, driver: probe.driving });
      }
      return recordExercisedDispatch.call(this, owner, callback, member);
    };
    const instantiate = bridge.immediatePackedSceneInstantiate;
    bridge.immediatePackedSceneInstantiate = function (...args) {
      const before = this.match3PackedSceneInstantiations;
      const result = instantiate.apply(this, args);
      if (this.match3PackedSceneInstantiations > before) {
        probe.events.push({ kind: "spawn", sim: this.simSeconds ?? 0, mobs: this.match3PackedSceneInstantiations });
      }
      return result;
    };
    return true;
  })()`);
}

// SmokeQuit.smoke_teardown (resolved by name from the export manifest) quits the
// SceneTree so every node exits the tree and releases its handles.
async function callTeardown(evaluate, exportDir) {
  return callSmokeMethod(evaluate, exportDir, "dodge.SmokeQuit", "smoke_teardown");
}

// Polls the snapshot until `predicate` holds or the window elapses, tracking the
// running peak and returning { last, peak }. Robust to the page briefly returning null.
async function observe(take, seedPeak, windowMs, deadline, predicate, onSample = null) {
  const peak = { ...seedPeak };
  let last = null;
  const until = Math.min(deadline, Date.now() + windowMs);
  while (Date.now() < until) {
    const snap = await take();
    if (snap) {
      peak.mobInstantiations = Math.max(peak.mobInstantiations, snap.mobInstantiations);
      peak.mobAddChildCommands = Math.max(peak.mobAddChildCommands, snap.mobAddChildCommands);
      peak.maxLiveHandles = Math.max(peak.maxLiveHandles, snap.maxLiveHandles);
      peak.crossings = Math.max(peak.crossings, snap.crossings);
      peak.callbackErrors = Math.max(peak.callbackErrors, snap.callbackErrors);
      // Mob frees are counted from the bridge's script teardown (snapshot mobFreed), not from
      // drops in the live-handle count: a drop is also the browser collecting a mob's dropped
      // SpriteFrames wrapper, and on the Linux CI runner's Firefox, where no mob ever left the
      // screen, every "free" the old drop counter saw was one of those.
      if (onSample) await onSample(snap, peak);
      last = snap;
      trace(`mobs=${snap.mobInstantiations} addChild=${snap.mobAddChildCommands} live=${snap.liveHandles} max=${snap.maxLiveHandles} mobLive=${snap.mobLive} mobFreed=${snap.mobFreed} screenExits=${snap.mobScreenExits} crossings=${snap.crossings} errs=${snap.callbackErrors} canvas=${snap.canvasW}x${snap.canvasH} frames=${snap.frames} sim=${snap.simSeconds}s mobs@ ${snap.mobPositions}`);
      if (predicate && predicate(snap, peak)) break;
    }
    await delay(150);
  }
  return { last, peak };
}

// The scene between two rounds: MobTimer stopped (game_over), the mobs queue_freed (new_game), the
// next mob not due before StartTimer's 2s. `take` asks the page to collect before every poll
// (`collecting`: a RefCounted handle goes when the browser collects its dropped Kotlin wrapper, and
// only a forced collection makes that happen on time on both engines) and the sampler keeps each
// kind's minimum, until every kind is back at or below `reference` (a healthy round: within a poll or
// two), the next round's first mob spawns (after `startMobs`), or `capMs` elapses. Without a
// reference (round 1's baseline) it samples the whole window. A kind missing from a sample counts as
// 0 there. Settling also needs every mob script freed (mobLive 0): on a slow host the queue_free
// lands a frame or more after the press. It returns the probe log of the last poll, the one that saw
// a new mob included, and the old round's mob frees and live mobs as of that last poll.
async function sampleBetweenRounds(take, reference, deadline, capMs, startMobs) {
  const byKind = {};
  let samples = 0;
  let settled = false;
  let detail = null;
  let probe = null;
  let mobFreed = null;
  let mobLive = null;
  const until = Math.min(deadline, Date.now() + capMs);
  while (Date.now() < until) {
    const snap = await take();
    if (snap?.liveByKind) {
      probe = snap.probe ?? probe;
      if (snap.mobInstantiations !== startMobs) {
        // The next round's first mob: the old round's frees still count, its own mob(s) do not.
        mobFreed = snap.mobFreed;
        mobLive = snap.mobLive - (snap.mobInstantiations - startMobs);
        break;
      }
      for (const kind of new Set([...Object.keys(byKind), ...Object.keys(snap.liveByKind)])) {
        const previous = kind in byKind ? byKind[kind] : samples > 0 ? 0 : Number.POSITIVE_INFINITY;
        byKind[kind] = Math.min(previous, snap.liveByKind[kind] ?? 0);
      }
      samples += 1;
      detail = snap.liveDetail;
      mobFreed = snap.mobFreed;
      mobLive = snap.mobLive;
      if (reference && mobLive === 0 && Object.entries(byKind).every(([kind, count]) => count <= (reference[kind] ?? 0))) {
        settled = true;
        break;
      }
    }
    await delay(150);
  }
  return { byKind: samples > 0 ? byKind : null, samples, settled, probe, detail, mobFreed, mobLive };
}

export async function runDodge({ url, evaluate, navigate, deadline, exportDir }) {
  const startupStart = Date.now();
  const mainIds = resolveMainIds(exportDir);
  const startPressedId = resolveMethodId(exportDir, "dodge.HUD", "_on_StartButton_pressed");
  if (startPressedId === null) throw new Error("dodge.HUD._on_StartButton_pressed is not in the export manifest");
  const screenExitId = resolveMethodId(exportDir, "dodge.Mob", "_on_VisibilityNotifier2D_screen_exited");
  if (screenExitId === null) throw new Error("dodge.Mob._on_VisibilityNotifier2D_screen_exited is not in the export manifest");
  const take = () => snapshot(evaluate);
  const takeCollected = () => snapshot(collecting(evaluate));
  trace("navigate");
  await navigate(`${url}?dodge=${Date.now()}`);

  // Ready: dodge mode, the scene scripts recorded ready, the Main handle captured.
  const readyDeadline = Math.min(deadline, Date.now() + 30_000);
  let ready = null;
  while (Date.now() < readyDeadline) {
    const snap = await take();
    if (
      snap &&
      snap.mode === "dodge" &&
      snap.protocol > 0 &&
      snap.mainReady >= 1 &&
      snap.playerReady >= 1 &&
      snap.hudReady >= 1 &&
      snap.mainHandle > 0 &&
      snap.smokeQuitHandle > 0
    ) {
      ready = snap;
      break;
    }
    await delay(100);
  }
  if (!ready) throw new Error("Kotlin/Wasm dodge-the-creeps did not become ready");
  const startupDurationMs = Date.now() - startupStart;
  trace(`ready: readyCount=${ready.readyCount} mainHandle=${ready.mainHandle} protocol=${ready.protocol}`);

  // Drive gameplay from the browser (dodge's SmokeQuit env-var gate is inert on Web), the way a
  // player does: HUD._on_StartButton_pressed hides the Start button and emits start_game, which
  // main.tscn connects to Main.new_game. new_game starts StartTimer (2s), then MobTimer spawns a
  // mob every 0.5s and ScoreTimer ticks. Pressing Start rather than calling new_game also looks the
  // Start button up before the first baseline: the HUD's game-over coroutine shows it again two
  // seconds after a game over, and a handle first minted there would read as one handle retained
  // by the mobs of the round it lands in.
  //
  // One evaluate calls Main.game_over when asked (the round is still spawning; marked as the
  // driver's in the probe) and presses Start, and returns the probe log, the mobs spawned so far, the
  // mob scripts freed before the press and the game time: no mob spawns between game_over and the
  // press, and new_game's queue_free frees the mobs only at the end of the frame.
  const seedPeak = { mobInstantiations: 0, mobAddChildCommands: 0, maxLiveHandles: ready.liveHandles, crossings: ready.crossings, callbackErrors: 0 };
  await installProbe(evaluate, mainIds.game_over, screenExitId);
  const pressStart = (gameOverFirst) =>
    evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      const probe = bridge.__kanamaDodgeProbe;
      if (!probe) throw new Error("dodge: the spawn/game-over probe is gone (did the page reload?)");
      const entry = Object.entries(bridge.scriptNameByHandle ?? {}).find(
        ([handle, name]) => String(name).endsWith(".HUD") && bridge.api.kanamaWebIsLive(Number(handle)) === 1,
      );
      if (!entry) throw new Error("dodge: no live HUD script handle to press Start on");
      const mobCount = (table) => Object.entries(table ?? {}).find(([name]) => name.endsWith(".Mob"))?.[1] ?? 0;
      const mobFreed = mobCount(bridge.match3ReadyByClass) - mobCount(bridge.liveScriptsByClass);
      if (${gameOverFirst ? "true" : "false"}) {
        probe.driving = true;
        try {
          bridge.callNoArgs(bridge.dodgeMainHandle, ${mainIds.game_over});
        } finally {
          probe.driving = false;
        }
      }
      bridge.callNoArgs(Number(entry[0]), ${startPressedId});
      return { probe: probe.events.slice(), mobs: bridge.match3PackedSceneInstantiations, mobFreed, sim: bridge.simSeconds ?? 0 };
    })()`);

  // Dodge is a real game: the player stands still at the start position and a mob can hit it
  // within a second of the first spawn. Main.game_over then stops MobTimer, and how many mobs had
  // spawned by then is decided by the engine RNG, which Godot seeds randomly at startup (an early
  // hit ended a round in roughly one run in ten; three in a row failed CI). A hit is gameplay, not
  // a bridge fault, so the driver RESUMES the round instead of restarting it: it calls
  // Main._on_StartTimer_timeout, the handler StartTimer runs, which starts MobTimer and ScoreTimer
  // again. The player cannot be hit a second time: its own _on_body_entered hides it and disables
  // its CollisionShape2D (set_deferred), and only Player.start (new_game) enables it again. Every
  // round therefore reaches 4 mobs, whatever the RNG does.
  //
  // Every run plays two rounds: round 1 ends with a restart (game_over, then Start) once it spawned
  // FORCED_RESTART_AT_MOBS mobs, round 2 ends with the measuring restart, and both are judged, so a
  // leak that only shows after the first new_game is red in CI too.
  //
  // What the hits prove, in every round, from the probe's exact game times and mob counts:
  // `atMostOneHitPerRound` (a second hit means the player's hide/disable stopped working; resuming
  // would otherwise hide it), `noHitBeforeFirstMob` (nothing else in the scene can hit the player)
  // and `noEarlyHit` (no mob can reach the stationary player sooner than EARLIEST_HIT_SECONDS of game
  // time after the round's first spawn).
  let roundRestarts = 0;
  let roundResumes = 0;
  const hits = []; // { round, mobs, sinceFirstSpawn } at each game over the game itself reported
  // Finished rounds: { index, mobs, baseline, trough, samples, settled, screenExitFrees, roundEndFrees, mobLive }.
  const rounds = [];
  // Mob frees are attributed per round from the bridge's count of mob scripts freed: those before the
  // round's end press flew off screen (screen exit); those between the press and the end of the
  // trough are new_game's queue_free of the mobs group (round end).
  let accountedFrees = 0;
  let round = null;
  let previousRound = null;
  let pressSim = null;
  let consumed = 0; // probe events already attributed
  const recordHit = (target, mobs, sim) => {
    target.hits += 1;
    target.spawning = false;
    const sinceFirstSpawn = target.firstSpawnSim === null ? null : Math.round((sim - target.firstSpawnSim) * 1000) / 1000;
    hits.push({ round: target.index, mobs, sinceFirstSpawn });
    trace(`hit in round ${target.index} after ${mobs} mob(s), ${sinceFirstSpawn}s after its first spawn`);
  };
  // Attributes the probe events not yet seen to their round. A spawn is the current round's. A hit
  // is too, except one reported at the press's own game time, before the engine ran any frame
  // after it: Area2D reports a physics step's overlaps at the start of the next frame, so that is a
  // hit in the round the press ended (new_game's queue_free and Player.start come after it).
  const consume = (log) => {
    for (; consumed < (log?.length ?? 0); consumed += 1) {
      const event = log[consumed];
      if (event.kind === "spawn") {
        if (round.firstSpawnSim === null) round.firstSpawnSim = event.sim;
      } else if (event.kind === "gameOver" && !event.driver) {
        const target = previousRound && event.sim === pressSim ? previousRound : round;
        const mobs = event.mobs - target.startMobs;
        recordHit(target, mobs, event.sim);
        // The round loop resumes a current round hit after its first mob and before its 4th (before
        // the first mob, StartTimer has yet to start MobTimer).
        if (target === round && mobs > 0 && mobs < 4) round.resume = true;
      }
    }
  };
  // Starts round `index`: game_over first when the old round is still spawning, then Start, then the
  // scene between the rounds (the old round's trough, the new round's baseline).
  const startRound = async (index, gameOverFirst, reference, capMs) => {
    const press = await pressStart(gameOverFirst);
    consume(press.probe); // everything up to the press is the old round's
    previousRound = round;
    pressSim = press.sim;
    round = { index, startMobs: press.mobs, baseline: null, spawning: true, firstSpawnSim: null, hits: 0, resume: false };
    const between = await sampleBetweenRounds(takeCollected, reference, deadline, capMs, press.mobs);
    round.baseline = between.byKind;
    consume(between.probe);
    const freedAfter = between.mobFreed ?? press.mobFreed;
    const screenExitFrees = press.mobFreed - accountedFrees;
    const roundEndFrees = freedAfter - press.mobFreed;
    accountedFrees = freedAfter;
    return { ...between, oldMobs: previousRound ? press.mobs - previousRound.startMobs : 0, screenExitFrees, roundEndFrees };
  };
  // Ends the current round the way a player's next game starts (game_over unless the player's hit
  // already ran it, then Start: new_game queue_frees the whole mobs group).
  const endRound = async (label) => {
    const finished = round;
    const between = await startRound(finished.index + 1, finished.spawning, finished.baseline, 4_000);
    rounds.push({
      index: finished.index,
      mobs: between.oldMobs,
      baseline: finished.baseline,
      trough: between.byKind,
      samples: between.samples,
      settled: between.settled,
      screenExitFrees: between.screenExitFrees,
      roundEndFrees: between.roundEndFrees,
      mobLive: between.mobLive,
    });
    trace(`${label}: round ${finished.index} ended after ${between.oldMobs} mob(s), freed ${between.screenExitFrees} off screen + ${between.roundEndFrees} by new_game, ${between.mobLive} still live; trough (${between.samples} samples, settled=${between.settled}): ${JSON.stringify(between.byKind)}${between.detail ? ` detail ${between.detail}` : ""}`);
  };

  trace("press Start (new_game)");
  // Round 1's baseline: the scene with the game started and no mob yet (StartTimer's 2s).
  const firstBetween = await startRound(1, false, null, 1_200);
  if (!round.baseline) round.baseline = ready.liveByKind ?? {};
  trace(`round 1 baseline (${firstBetween.samples} samples): ${JSON.stringify(round.baseline)}`);

  const onRoundSample = async (snap, p) => {
    p.roundMobs = snap.mobInstantiations - round.startMobs;
    p.bestRoundMobs = Math.max(p.bestRoundMobs ?? 0, p.roundMobs);
    consume(snap.probe);
    if (round.resume) {
      round.resume = false;
      roundResumes += 1;
      trace(`game over after ${p.roundMobs} mob(s) in round ${round.index}: resume spawning`);
      await callMain(evaluate, mainIds._on_StartTimer_timeout);
      round.spawning = true;
    }
    if (roundRestarts === 0 && p.roundMobs >= FORCED_RESTART_AT_MOBS) {
      roundRestarts += 1;
      await endRound("restart");
      p.roundMobs = 0;
      return true;
    }
    return false;
  };
  // Play long enough for several mobs to spawn AND for the earliest ones to fly across and leave
  // the screen (each mob queue_frees itself on VisibleOnScreenNotifier2D screen_exited). The window
  // is a CEILING, not a duration: the predicate ends it once round 2 has spawned 4 mobs and two mobs
  // have freed themselves off screen, which on a workstation is a few seconds, or SCREEN_EXIT_WAIT_SECONDS
  // of game time after round 2's first spawn passed without that (the Linux CI runner's Firefox: the
  // round's queue_free frees them instead). It is generous because a software-GL host simulates less
  // game time per wall-clock second.
  const screenExitFreesSoFar = (snap) =>
    rounds.reduce((sum, finished) => sum + finished.screenExitFrees, 0) + Math.max(snap.mobFreed - accountedFrees, 0);
  const gameplay = await observe(
    take,
    seedPeak,
    45_000,
    deadline,
    (snap, p) =>
      roundRestarts > 0 &&
      (p.roundMobs ?? 0) >= 4 &&
      (screenExitFreesSoFar(snap) >= 2 || (round.firstSpawnSim !== null && snap.simSeconds - round.firstSpawnSim >= SCREEN_EXIT_WAIT_SECONDS)),
    onRoundSample,
  );
  const peak = gameplay.peak;
  const atPeak = gameplay.last ?? ready;
  trace(`gameplay: mobs=${peak.mobInstantiations} bestRound=${peak.bestRoundMobs} addChild=${peak.mobAddChildCommands} maxLive=${peak.maxLiveHandles} screenExitFrees=${screenExitFreesSoFar(atPeak)} finalLive=${atPeak.liveHandles} crossings=${peak.crossings} hits=${JSON.stringify(hits)} resumes=${roundResumes} restarts=${roundRestarts}`);

  // Per-entity retention, measured PER ROUND between rounds, where dodge makes it unambiguous: after
  // game_over (no mob spawns any more) and new_game (the whole mobs group is queue_freed), and
  // before StartTimer brings the next mob. Anything a round's trough holds above that round's own
  // baseline was retained by mobs that no longer exist. Each round is judged against the scene as
  // it stood when that round began -- after the previous round's mobs were gone -- so a dead round's
  // leftovers never count against the next round's mobs. The sampler asks the page to collect before
  // every poll: a mob's RefCounted results (its SpriteFrames) are released when the browser collects
  // the dropped Kotlin wrapper, and waiting for the collector to run by itself is what made the
  // trough read 4 SpriteFrames over 7 mobs on CI.
  //
  // Measuring during play cannot work: mobs alive on screen hold their handles legitimately, and in
  // a short run they have no time to die -- which is exactly why task 72's one-handle-per-mob leak
  // needed a ten-minute soak to show as absolute growth. As a RATIO at the trough it needs seconds.
  await endRound("measure retention");
  const retention = {};
  let worstRetention = 0;
  let worstRetentionKind = "none";
  let worstRetentionRound = 0;
  for (const finished of rounds) {
    if (finished.mobs <= 0) continue;
    for (const [kind, count] of Object.entries(finished.trough ?? {})) {
      const above = Math.max(count - (finished.baseline[kind] ?? 0), 0);
      const perMob = Math.round((above / finished.mobs) * 100) / 100;
      retention[`${kind}@round${finished.index}`] = perMob;
      if (perMob > worstRetention) {
        worstRetention = perMob;
        worstRetentionKind = kind;
        worstRetentionRound = finished.index;
      }
    }
  }
  const hitsPerRound = {};
  for (const hit of hits) hitsPerRound[hit.round] = (hitsPerRound[hit.round] ?? 0) + 1;
  trace(`retention per mob, per round (${rounds.map((r) => r.mobs).join(" + ")} mobs): ${JSON.stringify(retention)} worst=${worstRetentionKind}@round${worstRetentionRound}:${worstRetention}`);

  trace("smoke_teardown");
  await callTeardown(evaluate, exportDir);
  const teardown = await observe(takeCollected, { ...peak, maxLiveHandles: peak.maxLiveHandles }, 6_000, deadline, (snap) => snap.liveHandles === 0);
  const settled = teardown.last ?? atPeak;
  trace(`teardown: live=${settled.liveHandles} (peak=${peak.maxLiveHandles}) callbacks=${settled.callbacks} pending=${settled.pending} jobs=${settled.jobs} errs=${settled.callbackErrors}`);

  const protocolVersion = ready.protocol;
  const checks = {
    modeDodge: ready.mode === "dodge",
    sceneScriptsReady: ready.mainReady >= 1 && ready.playerReady >= 1 && ready.hudReady >= 1,
    // Both rounds (the restarted one and the measured one) spawned at least 4 mobs.
    mobsInstantiated: rounds.length >= 2 && rounds.every((finished) => finished.mobs >= 4),
    mobsAddedToTree: peak.mobAddChildCommands >= peak.mobInstantiations,
    handleGrowthDuringGameplay: peak.maxLiveHandles > ready.liveHandles,
    crossingsAdvanced: peak.crossings > ready.crossings,
    // The spawn/free lifecycle works: EVERY mob of both rounds was freed -- off screen (its own
    // queue_free on screen_exited) or by the round's end (new_game's queue_free of the mobs group) --
    // and the bridge tore its script down, by the time the round's trough settled. Counted from the
    // bridge's mob scripts (readied minus live), not from drops in the live-handle count: those are
    // also the browser collecting a mob's dropped SpriteFrames wrapper, and the old ">= 2 drops" form
    // passed on GC timing alone (Linux CI Firefox: 4/4 green while no mob ever left the screen, then
    // red once the collector stayed quiet in the window). Off-screen frees are reported
    // (mobScreenExitFrees), not required: that host never fires screen_exited (task 71).
    mobsSpawnAndFree:
      rounds.length >= 2 &&
      rounds.every((finished) => finished.mobs > 0 && finished.mobLive === 0 && finished.screenExitFrees + finished.roundEndFrees === finished.mobs),
    // Full teardown: quitting the tree drained every live handle to zero.
    fullTeardownToZero: settled.liveHandles === 0,
    // No handle kind is retained per spawned mob, measured per round at the trough between
    // rounds, where every mob of the round is provably gone, against that round's own baseline,
    // with a collection forced before every poll. A real per-spawn leak sits at 1.0 (task 72
    // measured exactly that); a healthy run sits at 0. This is the SHORT-run form of what the soak
    // gate needs ten minutes to see.
    noPerMobHandleRetention: worstRetention <= 0.5,
    // The player's _on_body_entered hides it and disables its collision shape until new_game: a
    // second hit in one round means that stopped working (the resumed round would otherwise pass).
    atMostOneHitPerRound: Object.values(hitsPerRound).every((count) => count <= 1),
    // Nothing in the scene but a mob can hit the player: a hit before a round spawned its first mob
    // is an instant-kill bug.
    noHitBeforeFirstMob: !hits.some((hit) => hit.mobs === 0),
    // No mob reaches the stationary player sooner than EARLIEST_HIT_SECONDS after the round's first spawn.
    noEarlyHit: !hits.some((hit) => hit.mobs > 0 && hit.sinceFirstSpawn !== null && hit.sinceFirstSpawn < EARLIEST_HIT_SECONDS),
    noCallbackFaults: peak.callbackErrors === 0 && settled.callbackErrors === 0 && settled.failure === null,
  };

  const last = settled;
  // Task 88 falsification hook: normally the settled post-teardown sample; the
  // falsify mode judges the invariant against the mid-play peak instead, where mobs
  // are alive and callbacks/jobs are registered, so the check must go false.
  const registries = FALSIFY === "pre-teardown-registries" ? atPeak : last;
  const boundaryErrors = [];
  if (peak.callbackErrors !== 0) boundaryErrors.push(`callbackErrors=${peak.callbackErrors}`);
  if (settled.failure !== null) boundaryErrors.push(`failure: ${settled.failure}`);
  if (!checks.noHitBeforeFirstMob) {
    boundaryErrors.push("dodge: the player was hit before a round spawned its first mob (instant-kill bug: only a mob can hit it)");
  }
  if (!checks.atMostOneHitPerRound) {
    boundaryErrors.push(`dodge: the player was hit more than once in a round (its hide/disable after a hit stopped working): ${JSON.stringify(hits)}`);
  }
  if (!checks.noEarlyHit) {
    boundaryErrors.push(`dodge: a mob hit the player sooner than ${EARLIEST_HIT_SECONDS}s of game time after the round's first spawn (no mob can reach it before ~0.6s): ${JSON.stringify(hits)}`);
  }

  return {
    protocolVersion,
    startup: {
      loaded: ready.mode === "dodge",
      outcome: ready.mode === "dodge" ? "ready" : "failed",
      durationMs: startupDurationMs,
    },
    checks,
    handles: {
      liveAfterGameplay: peak.maxLiveHandles,
      liveAfterTeardown: last.liveHandles,
      staleRejected: 0,
    },
    crossings: {
      kotlinToGodotCalls: peak.crossings,
      mobInstantiations: peak.mobInstantiations,
      // Mobs spawned in the best single round (the count mobsInstantiated judges; mobInstantiations is cumulative).
      bestRoundMobInstantiations: peak.bestRoundMobs ?? 0,
      mobAddChildCommands: peak.mobAddChildCommands,
      // Restarts (game_over + Start) before the measuring one: 1 (round 1 ends after FORCED_RESTART_AT_MOBS mobs).
      roundRestarts,
      // Rounds the driver resumed after the player was hit before the round's 4th mob.
      roundResumes,
      // Game overs the game itself reported (the player was hit).
      playerHits: hits.length,
      // Mobs freed while their round ran (flew off screen; 0 where screen_exited never fires, task 71),
      // and by the round's end (new_game's queue_free), over both rounds; mobsSpawnAndFree needs the
      // two to add up to every mob. Signals: Mob screen_exited dispatches the page saw.
      mobScreenExitFrees: rounds.reduce((sum, finished) => sum + finished.screenExitFrees, 0),
      mobRoundEndFrees: rounds.reduce((sum, finished) => sum + finished.roundEndFrees, 0),
      mobScreenExitSignals: settled.mobScreenExits ?? 0,
      // Worst per-mob handle retention, x100 so the envelope stays integer-friendly
      // (the schema requires numeric counters). 0 is healthy; 100 is one leaked
      // handle per spawn.
      worstHandleRetentionPerMobX100: Math.round(worstRetention * 100),
    },
    callbacks: {
      pendingSignalCallbacks: last.callbacks,
    },
    connections: {
      afterGameplayLiveHandles: last.liveHandles,
    },
    scheduler: {
      pendingCoroutines: last.pending,
      registeredJobs: last.jobs,
    },
    teardown: {
      outcome:
        checks.fullTeardownToZero && last.callbackErrors === 0 && last.failure === null
          ? "clean"
          : "incomplete",
      // Task 88: this was `last.liveHandles <= peak.maxLiveHandles`, which is TRUE BY
      // CONSTRUCTION -- maxLiveHandles is a monotone high-water mark of liveHandles and
      // observe() merges the settled sample into peak before returning it. The field is
      // the contract's post-teardown invariant, so it must assert what match3/tpsdemo
      // assert: every owner registry actually drained.
      ownerRegistriesToBaseline:
        registries.liveHandles === 0 &&
        registries.callbacks === 0 &&
        registries.pending === 0 &&
        registries.jobs === 0,
    },
    boundaryErrors,
  };
}
