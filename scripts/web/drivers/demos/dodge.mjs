// demos/dodge.mjs -- dodge-the-creeps play + teardown assertions for the Web smoke.
//
// Unlike Bunnymark (driven by bridge method calls) and Match3 (driven by synthetic
// pointer input), dodge is a real-time, self-driven demo: the driver presses Start
// (HUD._on_StartButton_pressed -> Main.new_game), lets Godot's MobTimer spawn mobs and
// the ScoreTimer tick, then ends the round (game_over, Start) to measure per-mob handle
// retention between rounds, tears the scene down and quits. Beyond those calls (and
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
// KANAMA_WEB_DODGE_FORCE_RESTART=1 ends round 1 with a restart once it has spawned this many mobs.
const FORCED_RESTART_AT_MOBS = 2;
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
        mobInstantiations: bridge.match3PackedSceneInstantiations,
        mobAddChildCommands: bridge.match3AddChildCommands,
        // Main.game_over dispatches so far (the player was hit; MobTimer is stopped).
        gameOvers: (() => {
          const main = Object.entries(bridge.exercisedMembers ?? {}).find(([n]) => n.endsWith(".Main"));
          return main?.[1]?.["method#" + bridge.dodgeMethodId("game_over")] ?? 0;
        })(),
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

async function callMain(evaluate, method) {
  return evaluate(
    `globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.dodgeMainHandle, globalThis.KanamaWebBridge.dodgeMethodId(${JSON.stringify(method)})); true`,
  );
}

// SmokeQuit.smoke_teardown (resolved by name from the export manifest) quits the
// SceneTree so every node exits the tree and releases its handles.
async function callTeardown(evaluate, exportDir) {
  return callSmokeMethod(evaluate, exportDir, "dodge.SmokeQuit", "smoke_teardown");
}

// Polls the snapshot until `predicate` holds or the window elapses, tracking the
// running peak and returning { last, peak }. Robust to the page briefly returning null.
async function observe(evaluate, seedPeak, windowMs, deadline, predicate, onSample = null) {
  const peak = { mobFrees: 0, ...seedPeak };
  let last = null;
  let prevLive = seedPeak.maxLiveHandles;
  const until = Math.min(deadline, Date.now() + windowMs);
  while (Date.now() < until) {
    const snap = await snapshot(evaluate);
    if (snap) {
      peak.mobInstantiations = Math.max(peak.mobInstantiations, snap.mobInstantiations);
      peak.mobAddChildCommands = Math.max(peak.mobAddChildCommands, snap.mobAddChildCommands);
      peak.maxLiveHandles = Math.max(peak.maxLiveHandles, snap.maxLiveHandles);
      peak.crossings = Math.max(peak.crossings, snap.crossings);
      peak.callbackErrors = Math.max(peak.callbackErrors, snap.callbackErrors);
      // A drop in the live-handle count means a mob (and its child nodes) was freed —
      // dodge mobs queue_free themselves when a VisibleOnScreenNotifier2D reports they
      // left the screen. Counting drops proves the free/release path works during play.
      // It UNDERCOUNTS when a spawn lands in the same sample as a free, which is why the
      // window below is a generous ceiling rather than a fixed duration.
      if (snap.liveHandles < prevLive) peak.mobFrees += 1;
      prevLive = snap.liveHandles;
      // A round restart queue_frees the whole mobs group: re-baseline so those drops are
      // not counted as off-screen frees.
      if (onSample && (await onSample(snap, peak))) {
        await delay(300);
        prevLive = (await snapshot(evaluate))?.liveHandles ?? prevLive;
      }
      last = snap;
      trace(`mobs=${snap.mobInstantiations} addChild=${snap.mobAddChildCommands} live=${snap.liveHandles} max=${snap.maxLiveHandles} frees=${peak.mobFrees} crossings=${snap.crossings} errs=${snap.callbackErrors} canvas=${snap.canvasW}x${snap.canvasH} frames=${snap.frames} sim=${snap.simSeconds}s mobs@ ${snap.mobPositions}`);
      if (predicate && predicate(snap, peak)) break;
    }
    await delay(150);
  }
  return { last, peak };
}

// The scene between two rounds: MobTimer stopped (game_over), the mobs queue_freed (new_game), the
// next mob not due before StartTimer's 2s. Asks the page to collect before every poll (`collecting`:
// a RefCounted handle goes when the browser collects its dropped Kotlin wrapper, and only a forced
// collection makes that happen on time on both engines) and keeps each kind's minimum, until every
// kind is back at or below `reference` (a healthy round: within a poll or two), the next round's
// first mob spawns, or `capMs` elapses. Without a reference (round 1's baseline) it samples the
// whole window. A kind missing from a sample counts as 0 there.
async function sampleBetweenRounds(evaluate, reference, deadline, capMs) {
  const byKind = {};
  let samples = 0;
  let settled = false;
  let mobs = null;
  let gameOvers = 0;
  let detail = null;
  const collect = collecting(evaluate);
  const until = Math.min(deadline, Date.now() + capMs);
  while (Date.now() < until) {
    const snap = await snapshot(collect);
    if (snap?.liveByKind) {
      if (mobs === null) mobs = snap.mobInstantiations;
      else if (snap.mobInstantiations !== mobs) break; // the next round's first mob
      gameOvers = Math.max(gameOvers, snap.gameOvers);
      for (const kind of new Set([...Object.keys(byKind), ...Object.keys(snap.liveByKind)])) {
        const previous = kind in byKind ? byKind[kind] : samples > 0 ? 0 : Number.POSITIVE_INFINITY;
        byKind[kind] = Math.min(previous, snap.liveByKind[kind] ?? 0);
      }
      samples += 1;
      detail = snap.liveDetail;
      if (reference && Object.entries(byKind).every(([kind, count]) => count <= (reference[kind] ?? 0))) {
        settled = true;
        break;
      }
    }
    await delay(150);
  }
  return { byKind: samples > 0 ? byKind : null, samples, settled, mobs: mobs ?? 0, gameOvers, detail };
}

export async function runDodge({ url, evaluate, navigate, deadline, exportDir }) {
  const startupStart = Date.now();
  trace("navigate");
  await navigate(`${url}?dodge=${Date.now()}`);

  // Ready: dodge mode, the scene scripts recorded ready, the Main handle captured.
  const readyDeadline = Math.min(deadline, Date.now() + 30_000);
  let ready = null;
  while (Date.now() < readyDeadline) {
    const snap = await snapshot(evaluate);
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
  const seedPeak = { mobInstantiations: 0, mobAddChildCommands: 0, maxLiveHandles: ready.liveHandles, crossings: ready.crossings, callbackErrors: 0 };
  const forceRestart = process.env.KANAMA_WEB_DODGE_FORCE_RESTART === "1";
  const startPressedId = resolveMethodId(exportDir, "dodge.HUD", "_on_StartButton_pressed");
  if (startPressedId === null) throw new Error("dodge.HUD._on_StartButton_pressed is not in the export manifest");
  const pressStart = async () => {
    await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      const entry = Object.entries(bridge.scriptNameByHandle ?? {}).find(
        ([handle, name]) => String(name).endsWith(".HUD") && bridge.api.kanamaWebIsLive(Number(handle)) === 1,
      );
      if (!entry) throw new Error("dodge: no live HUD script handle to press Start on");
      bridge.callNoArgs(Number(entry[0]), ${startPressedId});
      return true;
    })()`);
  };
  trace("press Start (new_game)");
  await pressStart();
  // Round 1's baseline: the scene with the game started and no mob yet (StartTimer's 2s).
  const firstBetween = await sampleBetweenRounds(evaluate, null, deadline, 1_200);
  const baselineByKind = firstBetween.byKind ?? ready.liveByKind ?? {};
  trace(`round 1 baseline (${firstBetween.samples} samples): ${JSON.stringify(baselineByKind)}`);

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
  // What the hits still prove: `noHitBeforeFirstMob` fails a build where the player is hit before
  // round 1 has spawned a mob (nothing else in the scene can hit it). A build that kills the player
  // on the first mob is no longer told apart from bad luck; it was a probability before (it needed
  // three early rounds in a row), and that probability is what made the cell flaky.
  //
  // KANAMA_WEB_DODGE_FORCE_RESTART=1 ends round 1 after FORCED_RESTART_AT_MOBS mobs with exactly one
  // restart (game_over, then Start), so a local run can prove the retention measurement across a
  // restart on demand.
  let gameOversSeen = 0;
  let roundRestarts = 0;
  let roundResumes = 0;
  const hits = []; // [round, mobs spawned in that round] at each game over the game itself reported
  const rounds = []; // finished rounds: { index, mobs, baseline, trough, samples, settled }
  let round = { index: 1, startMobs: firstBetween.mobs ?? 0, baseline: baselineByKind, spawning: true };
  if (firstBetween.gameOvers > 0) {
    // Hit while the baseline was sampled, before round 1's first mob. StartTimer still starts MobTimer.
    gameOversSeen = firstBetween.gameOvers;
    hits.push([1, 0]);
  }
  // Ends the current round the way a player's next game starts: game_over (unless the player's hit
  // already ran it: MobTimer stops, no mob spawns any more), then Start (new_game queue_frees the
  // whole mobs group). The scene between the two rounds is the finished round's trough and the next
  // round's baseline.
  const endRound = async (label) => {
    if (round.spawning) {
      await callMain(evaluate, "game_over");
      gameOversSeen += 1;
    }
    await pressStart();
    const between = await sampleBetweenRounds(evaluate, round.baseline, deadline, 4_000);
    // A dead round's mob that reaches the player before new_game's queue_free lands is that round's.
    gameOversSeen = Math.max(gameOversSeen, between.gameOvers);
    const mobs = between.mobs - round.startMobs;
    rounds.push({ index: round.index, mobs, baseline: round.baseline, trough: between.byKind, samples: between.samples, settled: between.settled });
    trace(`${label}: round ${round.index} ended after ${mobs} mob(s); trough (${between.samples} samples, settled=${between.settled}): ${JSON.stringify(between.byKind)}${between.detail ? ` detail ${between.detail}` : ""}`);
    round = { index: round.index + 1, startMobs: between.mobs, baseline: between.byKind, spawning: true };
  };
  const trackRound = (snap, p) => {
    p.roundMobs = snap.mobInstantiations - round.startMobs;
    p.bestRoundMobs = Math.max(p.bestRoundMobs ?? 0, p.roundMobs);
  };
  const onRoundSample = async (snap, p) => {
    trackRound(snap, p);
    if (snap.gameOvers > gameOversSeen) {
      gameOversSeen = snap.gameOvers;
      hits.push([round.index, p.roundMobs]);
      round.spawning = false;
      if (p.roundMobs >= 4) return false;
      if (forceRestart && roundRestarts === 0) {
        roundRestarts += 1;
        await endRound(`hit after ${p.roundMobs} mob(s), forced restart`);
        p.roundMobs = 0;
        return true;
      }
      roundResumes += 1;
      trace(`game over after ${p.roundMobs} mob(s) in round ${round.index}: resume spawning`);
      await callMain(evaluate, "_on_StartTimer_timeout");
      round.spawning = true;
      return false;
    }
    if (forceRestart && roundRestarts === 0 && p.roundMobs >= FORCED_RESTART_AT_MOBS) {
      roundRestarts += 1;
      await endRound("forced restart");
      p.roundMobs = 0;
      return true;
    }
    return false;
  };
  // Play long enough for several mobs to spawn AND for the earliest ones to fly across and leave
  // the screen (each mob queue_frees itself on VisibleOnScreenNotifier2D screen_exited). The window
  // is a CEILING, not a duration: the predicate ends it as soon as a round has spawned 4 mobs and two
  // mobs have been freed, which on a workstation is a few seconds. It is generous because a
  // software-GL host simulates less game time per wall-clock second.
  const gameplay = await observe(
    evaluate,
    seedPeak,
    45_000,
    deadline,
    (snap, p) => p.mobFrees >= 2 && (p.roundMobs ?? 0) >= 4,
    onRoundSample,
  );
  const peak = gameplay.peak;
  const atPeak = gameplay.last ?? ready;
  trace(`gameplay: mobs=${peak.mobInstantiations} bestRound=${peak.bestRoundMobs} addChild=${peak.mobAddChildCommands} maxLive=${peak.maxLiveHandles} frees=${peak.mobFrees} finalLive=${atPeak.liveHandles} crossings=${peak.crossings} hits=${JSON.stringify(hits)} resumes=${roundResumes} restarts=${roundRestarts}`);

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
  const mobsBeforeRestart = peak.mobInstantiations;
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
  trace(`retention per mob, per round (${mobsBeforeRestart} mobs over ${rounds.length} round(s)): ${JSON.stringify(retention)} worst=${worstRetentionKind}@round${worstRetentionRound}:${worstRetention}`);

  trace("smoke_teardown");
  await callTeardown(evaluate, exportDir);
  const teardown = await observe(collecting(evaluate), { ...peak, maxLiveHandles: peak.maxLiveHandles }, 6_000, deadline, (snap) => snap.liveHandles === 0);
  const settled = teardown.last ?? atPeak;
  trace(`teardown: live=${settled.liveHandles} (peak=${peak.maxLiveHandles}) callbacks=${settled.callbacks} pending=${settled.pending} jobs=${settled.jobs} errs=${settled.callbackErrors}`);

  const protocolVersion = ready.protocol;
  const checks = {
    modeDodge: ready.mode === "dodge",
    sceneScriptsReady: ready.mainReady >= 1 && ready.playerReady >= 1 && ready.hudReady >= 1,
    mobsInstantiated: (peak.bestRoundMobs ?? 0) >= 4,
    mobsAddedToTree: peak.mobAddChildCommands >= peak.mobInstantiations,
    handleGrowthDuringGameplay: peak.maxLiveHandles > ready.liveHandles,
    crossingsAdvanced: peak.crossings > ready.crossings,
    // The spawn/free lifecycle works: mobs left the screen and released their handles
    // (>=2 observed drops during play) and stayed bounded by the peak — no leak.
    // Task 88: the second clause used to be `atPeak.liveHandles <= peak.maxLiveHandles`,
    // which cannot be false (maxLiveHandles is a monotone high-water mark of
    // liveHandles). Dropped rather than rewritten: the free path is already proven by
    // the mobFrees count here, by the per-entity retention ratio (task 73) during play,
    // and by liveAfterTeardown === 0 at the end. A clause that cannot fail only makes
    // the check read stronger than it is.
    mobsSpawnAndFree: peak.mobFrees >= 2,
    // Full teardown: quitting the tree drained every live handle to zero.
    fullTeardownToZero: settled.liveHandles === 0,
    // No handle kind is retained per spawned mob, measured per round at the trough between
    // rounds, where every mob of the round is provably gone, against that round's own baseline,
    // with a collection forced before every poll. A real per-spawn leak sits at 1.0 (task 72
    // measured exactly that); a healthy run sits at 0. This is the SHORT-run form of what the soak
    // gate needs ten minutes to see.
    noPerMobHandleRetention: worstRetention <= 0.5,
    // Nothing in the scene but a mob can hit the player: a hit before round 1 spawned its first mob
    // is an instant-kill bug (the hit is detected a poll late, so this can only err towards green).
    noHitBeforeFirstMob: !hits.some(([index, mobs]) => index === 1 && mobs === 0),
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
    boundaryErrors.push("dodge: the player was hit before round 1 spawned a mob (instant-kill bug: only a mob can hit it)");
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
      // Restarts (game_over + Start) before the measuring one: 0, or 1 with KANAMA_WEB_DODGE_FORCE_RESTART=1.
      roundRestarts,
      // Rounds the driver resumed after the player was hit before the round's 4th mob.
      roundResumes,
      // Game overs the game itself reported (the player was hit).
      playerHits: hits.length,
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
