// demos/fps.mjs -- Starter-Kit-FPS observe + teardown assertions for the Web smoke.
//
// The FPS is self-driven for smoke purposes: the Player runs gravity + move_and_slide and
// the weapon-swap tween chain from _process (change_weapon hydrates the Weapon resources,
// instantiates the weapon model, and sets the crosshair texture), enemies bob in _process
// and return fire at the Player through their RayCast3D on a timer, and the Kanama-scripted
// Audio autoload pumps its pooled players. This driver OBSERVES the first scene generation,
// then FIGHTS (tasks 79 / 80 slice 2: it shoots an enemy dead, which is the only automated
// coverage the player->enemy damage path has ever had), then triggers Smoke.smoke_teardown —
// which frees the Audio autoload and the scene root — and polls live handles to zero.

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[fps ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
};

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
        protocol: bridge.results?.protocolVersion ?? bridge.protocolVersion ?? 0,
        smokeHandle: bridge.fpsSmokeHandle,
        playerHandle: bridge.fpsPlayerHandle,
        readyCount: bridge.readyCount,
        smokeReady: classCount(".Smoke"),
        playerReady: classCount(".Player"),
        hudReady: classCount(".Hud"),
        audioReady: classCount(".Audio"),
        enemyReady: classCount(".Enemy"),
        // LIVE, not cumulative: liveScriptsByClass decrements on the _exit_tree free, which is
        // what lets the combat gate below see an enemy actually die.
        enemyLive: liveCount(".Enemy"),
        doubleArgCalls: bridge.doubleArgCalls ?? 0,
        weaponInstantiations: bridge.match3PackedSceneInstantiations,
        processCalls: bridge.processCalls,
        appliedCommands: bridge.appliedCommands,
        liveHandles: bridge.liveBrowserHandleCount,
        maxLiveHandles: bridge.maxLiveBrowserHandles,
        crossings: bridge.kotlinToGodotCalls,
        callbackErrors: bridge.callbackErrors,
        physicsAfterProcess: bridge.physicsAfterProcessSameTick ?? 0,
        callbacks: bridge.api.kanamaWebPendingSignalCallbackCount(),
        pending: bridge.api.kanamaWebPendingCoroutineCount(),
        jobs: bridge.api.kanamaWebRegisteredCoroutineJobCount(),
        failure: globalThis.KanamaWebFailure?.stack ?? globalThis.KanamaWebFailure?.message ?? null,
      };
    })()`);
  } catch {
    return null; // page mid-navigation or torn down
  }
}

async function observe(evaluate, seed, windowMs, deadline, predicate) {
  const peak = { ...seed };
  let last = null;
  const until = Math.min(deadline, Date.now() + windowMs);
  while (Date.now() < until) {
    const snap = await snapshot(evaluate);
    if (snap) {
      peak.weaponInstantiations = Math.max(peak.weaponInstantiations, snap.weaponInstantiations);
      peak.processCalls = Math.max(peak.processCalls, snap.processCalls);
      peak.appliedCommands = Math.max(peak.appliedCommands, snap.appliedCommands);
      peak.maxLiveHandles = Math.max(peak.maxLiveHandles, snap.maxLiveHandles);
      peak.crossings = Math.max(peak.crossings, snap.crossings);
      peak.callbackErrors = Math.max(peak.callbackErrors, snap.callbackErrors);
      last = snap;
      trace(`process=${snap.processCalls} weapons=${snap.weaponInstantiations} live=${snap.liveHandles} errs=${snap.callbackErrors}`);
      if (predicate && predicate(snap, peak)) break;
    }
    await delay(150);
  }
  return { last, peak };
}

export async function runFps({ url, evaluate, navigate, deadline }) {
  const startupStart = Date.now();
  trace("navigate");
  await navigate(`${url}?fps=${Date.now()}`);

  const readyDeadline = Math.min(deadline, Date.now() + 45_000);
  let ready = null;
  while (Date.now() < readyDeadline) {
    const snap = await snapshot(evaluate);
    if (
      snap &&
      snap.mode === "fps" &&
      snap.protocol > 0 &&
      snap.smokeReady >= 1 &&
      snap.playerReady >= 1 &&
      snap.hudReady >= 1 &&
      snap.audioReady >= 1 &&
      snap.enemyReady >= 1 &&
      snap.smokeHandle > 0 &&
      snap.playerHandle > 0
    ) {
      ready = snap;
      break;
    }
    await delay(100);
  }
  if (!ready) throw new Error("Kotlin/Wasm Starter-Kit-FPS did not become ready");
  const startupDurationMs = Date.now() - startupStart;
  trace(`ready: readyCount=${ready.readyCount} smokeHandle=${ready.smokeHandle} protocol=${ready.protocol}`);

  // Observe the first scene generation running: the Player's _process (gravity +
  // move_and_slide + the RayCast3D re-aim on weapon change), enemies bobbing and firing,
  // the Audio autoload pumping, commands flowing.
  const seed = {
    weaponInstantiations: 0,
    processCalls: ready.processCalls,
    appliedCommands: ready.appliedCommands,
    maxLiveHandles: ready.liveHandles,
    crossings: ready.crossings,
    callbackErrors: 0,
  };
  const gameplay = await observe(evaluate, seed, 9_000, deadline, (snap) =>
    snap.processCalls >= ready.processCalls + 120 && snap.weaponInstantiations >= 1,
  );
  const peak = gameplay.peak;
  const atPeak = gameplay.last ?? ready;
  trace(`gameplay: process=${peak.processCalls} weapons=${peak.weaponInstantiations} live=${atPeak.liveHandles}`);

  // --- Combat: shoot an enemy dead (tasks 79 / 80 slice 2) --------------------------
  //
  // This is the assertion the whole parcel exists for. The damage path is
  //   Player.actionShoot -> collider.call("damage", weapon.damage)  (Kotlin)
  //     -> the enemy's GDScript proxy `func damage(amount: float)`
  //       -> _kanama_bridge.callDoubles -> Kotlin Enemy.damage
  // and the middle step used to emit `unsupportedGameplayMethod`, which threw. FPS enemies
  // were therefore immortal on Web, and no gate noticed because this driver never fired a shot.
  //
  // Everything below is ENGINE-LEVEL injection, the same transport racing/thirdperson/tpsdemo
  // already use, because browser key synthesis stalls headless Firefox and SafariDriver has no
  // `keys` transport at all:
  //   * ops 142/143 set the Player's global_position and global_rotation, which carry the
  //     Head/Camera/RayCast with them. `shoot` is bound to mouse button 1 and mouse LOOK is a
  //     motion event no injected ACTION can express, so the stance is set rather than steered.
  //   * ops 86/87 press and release the `shoot` action Player.kt reads through
  //     Input.isActionPressed("shoot").
  // The SHOT is entirely real: real RayCast3D, real collider, real call("damage", float), real
  // Kotlin Enemy.damage, real queue_free. Only the stance is placed.
  //
  // The stance must be re-issued every poll, and that is not tidiness -- the blaster's knockback
  // is 40 and `movementVelocity += Vector3(0, 0, knockback)` throws the Player backwards several
  // metres per volley, off the level and into Player.process's `position.y < -10` scene reload.
  // Pinning the stance each poll is what makes the burst repeatable instead of a slingshot.
  //
  // Stance: `Enemies/enemy-flying` in scenes/main.tscn sits at (-3.5, 2.5, -6) and its
  // CollisionShape3D sphere (r = 0.75) centres 0.25 higher. The Player's camera rides one metre
  // above its root (Head at local y=1), so a root at (-3.5, 1.75, -3) with zero rotation puts the
  // camera exactly 3 m from that sphere centre along -Z. The blaster's spread cone is +/-1.0 at
  // the 10 m ray end, i.e. +/-0.3 m at 3 m -- inside the sphere, so every one of the three shots
  // per 0.25 s volley connects for 25 damage and the 100-health enemy dies in two volleys.
  const STANCE_POSITION = [-3.5, 1.75, -3];
  const objectQuery = (opcode, payload) =>
    evaluate(
      `globalThis.KanamaWebBridge.immediateObjectQuery(${opcode}, globalThis.KanamaWebBridge.fpsPlayerHandle, ${payload}); true`,
    );
  const takeStance = async () => {
    await objectQuery(142, `[${STANCE_POSITION.join(", ")}].join(String.fromCharCode(31))`);
    await objectQuery(143, "[0, 0, 0].join(String.fromCharCode(31))");
  };
  const shootAction = (opcode) => objectQuery(opcode, '"shoot"');

  const enemiesBefore = atPeak.enemyLive;
  trace(`combat: taking stance, enemiesBefore=${enemiesBefore}`);
  await takeStance();
  await shootAction(86);
  const combatDeadline = Math.min(deadline, Date.now() + 25_000);
  let enemiesAfter = enemiesBefore;
  let combatSnap = atPeak;
  while (Date.now() < combatDeadline) {
    await takeStance();
    await delay(80);
    const snap = await snapshot(evaluate);
    if (snap) {
      combatSnap = snap;
      enemiesAfter = snap.enemyLive;
      peak.callbackErrors = Math.max(peak.callbackErrors, snap.callbackErrors);
      trace(
        `combat: enemies=${snap.enemyLive} damageCalls=${snap.doubleArgCalls} ` +
          `ready=${snap.readyCount} impacts=${snap.weaponInstantiations} errs=${snap.callbackErrors}`,
      );
      if (snap.enemyLive < enemiesBefore) break;
    }
  }
  await shootAction(87);
  trace(`combat: enemiesBefore=${enemiesBefore} enemiesAfter=${enemiesAfter}`);
  peak.maxLiveHandles = Math.max(peak.maxLiveHandles, combatSnap.maxLiveHandles ?? 0);
  peak.crossings = Math.max(peak.crossings, combatSnap.crossings ?? 0);
  peak.processCalls = Math.max(peak.processCalls, combatSnap.processCalls ?? 0);
  peak.appliedCommands = Math.max(peak.appliedCommands, combatSnap.appliedCommands ?? 0);

  // Full teardown: Smoke.smoke_teardown (method#1 on the scene root's script) frees the
  // Audio autoload and the scene root, draining every live handle to zero.
  trace("smoke_teardown");
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.fpsSmokeHandle, 1); true",
  );
  const teardown = await observe(evaluate, peak, 8_000, deadline, (snap) => snap.liveHandles === 0);
  const settled = teardown.last ?? atPeak;
  trace(`teardown: live=${settled.liveHandles} callbacks=${settled.callbacks} errs=${settled.callbackErrors}`);

  const protocolVersion = ready.protocol;
  const checks = {
    modeFps: ready.mode === "fps",
    protocol18: protocolVersion === 18,
    sceneScriptsReady:
      ready.smokeReady >= 1 &&
      ready.playerReady >= 1 &&
      ready.hudReady >= 1 &&
      ready.audioReady >= 1 &&
      ready.enemyReady >= 1,
    // change_weapon ran: the hydrated Weapon resource instantiated its model scene.
    weaponModelInstantiated: peak.weaponInstantiations >= 1,
    // Tasks 79 / 80 slice 2: a shot reached Kotlin's Enemy.damage through the enemy's GDScript
    // proxy, health fell past zero, and destroy() queue_free'd the enemy. Before slice 2 the
    // proxy's damage(amount: float) threw and enemies were immortal on Web.
    enemyKilledByPlayerShot: enemiesBefore >= 1 && enemiesAfter < enemiesBefore,
    processFramesAdvanced: peak.processCalls >= ready.processCalls + 120,
    gameplayCommandsApplied: peak.appliedCommands > ready.appliedCommands,
    crossingsAdvanced: peak.crossings > ready.crossings,
    fullTeardownToZero: settled.liveHandles === 0,
    // Godot runs every physics tick before the idle/_process pass inside one rAF
    // iteration; the bridge counts any same-tick physics-after-process dispatch.
    physicsOrderingDeterministic: (settled.physicsAfterProcess ?? 0) === 0,
    noCallbackFaults: peak.callbackErrors === 0 && settled.callbackErrors === 0 && settled.failure === null,
  };

  const boundaryErrors = [];
  if (peak.callbackErrors !== 0) boundaryErrors.push(`callbackErrors=${peak.callbackErrors}`);
  if (settled.failure !== null) boundaryErrors.push(`failure: ${settled.failure}`);

  return {
    protocolVersion,
    startup: {
      loaded: ready.mode === "fps",
      outcome: ready.mode === "fps" ? "ready" : "failed",
      durationMs: startupDurationMs,
    },
    checks,
    handles: {
      liveAfterGameplay: peak.maxLiveHandles,
      liveAfterTeardown: settled.liveHandles,
      staleRejected: 0,
    },
    crossings: {
      kotlinToGodotCalls: peak.crossings,
      processCalls: peak.processCalls,
      weaponInstantiations: peak.weaponInstantiations,
      appliedCommands: peak.appliedCommands,
      enemiesBeforeShooting: enemiesBefore,
      enemiesAfterShooting: enemiesAfter,
      // Task 80 slice 2's new numeric crossing, counted: each one is a damage(float) that used
      // to reach `unsupportedGameplayMethod` and throw.
      numericMethodCrossings: combatSnap.doubleArgCalls ?? 0,
    },
    callbacks: {
      pendingSignalCallbacks: settled.callbacks,
    },
    connections: {
      afterGameplayLiveHandles: settled.liveHandles,
    },
    scheduler: {
      pendingCoroutines: settled.pending,
      registeredJobs: settled.jobs,
    },
    teardown: {
      outcome:
        checks.fullTeardownToZero && settled.callbackErrors === 0 && settled.failure === null
          ? "clean"
          : "incomplete",
      ownerRegistriesToBaseline: settled.liveHandles <= peak.maxLiveHandles,
    },
    boundaryErrors,
  };
}
