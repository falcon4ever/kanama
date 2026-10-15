package net.multigesture.kanama.binding

import java.lang.foreign.MemorySegment
import java.lang.ref.WeakReference
import java.lang.reflect.Modifier
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.binding.runtime.SignalCallables
import net.multigesture.kanama.binding.runtime.SignalCallbackRegistry

/**
 * Reloads project script templates from `kanama-scripts.jar` without editor restart.
 *
 * Each reload loads the jar in a new [ChildFirstClassLoader] (a build) and retires the previous
 * one. Script objects of a retired build keep running until they are freed (the scene reload frees
 * the scene's; [AutoloadReload] resets the Kotlin autoloads in place). A lambda signal connection
 * is owned by the build that made it (task 138 item 11): once a retired build has no live script
 * object left ([ScriptBridge.liveScriptObjects]), [sweepRetiredConnections] disconnects every
 * lambda connection it made, with one warning, so no old code runs after its objects are gone and
 * nothing pins the retired loader. It runs right after a reload, on the frame after a retired
 * build's last object is freed ([requestSweep]), and before each retired-loader check. By-name
 * connections are Godot's and survive, as in GDScript.
 */
object KanamaHotReload {

  @Volatile private var library: MemorySegment = MemorySegment.NULL

  @Volatile private var scriptClassLoader: ChildFirstClassLoader? = null

  @Volatile private var projectClassLoader: ChildFirstClassLoader? = null

  private val lock = Any()
  private var lastScriptsJarMtime: Long = -1L
  private var nextCheckNanos: Long = 0L
  private var nextLoaderId: Long = 1L
  private var nextRetiredLoaderCheckNanos: Long = 0L
  private var projectClassesRegistered: Boolean = false
  private val retiredLoaders = ArrayList<RetiredLoader>()

  // Set by ScriptBridge when a retired build's last script object is freed; read each frame.
  @Volatile private var sweepRequested: Boolean = false
  // Connections the sweeps dropped since the last retired-loader check line (its `swept=`).
  private var sweptSinceCheck: Int = 0

  private const val CHECK_INTERVAL_NANOS: Long = 500_000_000L
  private const val RETIRED_LOADER_CHECK_INTERVAL_NANOS: Long = 1_000_000_000L

  private val childFirstPrefixes =
    listOf("net.multigesture.kanama.generated.", "net.multigesture.kanama.example.")

  private data class RetiredLoader(
    val reloadId: Long,
    val ref: WeakReference<ChildFirstClassLoader>,
  )

  fun initialize(library: MemorySegment) {
    this.library = library
    reloadScripts(force = true)
  }

  fun shutdown() {
    synchronized(lock) {
      try {
        scriptClassLoader?.close()
      } catch (_: Throwable) {}
      try {
        projectClassLoader?.close()
      } catch (_: Throwable) {}
      scriptClassLoader = null
      projectClassLoader = null
      lastScriptsJarMtime = -1L
      nextCheckNanos = 0L
      nextRetiredLoaderCheckNanos = 0L
      nextLoaderId = 1L
      projectClassesRegistered = false
      retiredLoaders.clear()
      sweepRequested = false
      sweptSinceCheck = 0
      library = MemorySegment.NULL
    }
  }

  fun frameTick() {
    if (sweepRequested) {
      sweepRequested = false
      sweepRetiredConnections()
    }
    val now = System.nanoTime()
    if (now < nextCheckNanos) return
    nextCheckNanos = now + CHECK_INTERVAL_NANOS
    reloadScripts(force = false)
    checkRetiredLoaders(now)
  }

  fun reloadScripts(force: Boolean) {
    synchronized(lock) {
      val lib = library
      if (lib.address() == 0L) return

      val scriptsJar = findScriptsJar() ?: return
      val mtime =
        try {
          Files.getLastModifiedTime(scriptsJar).toMillis()
        } catch (_: Throwable) {
          return
        }
      if (!force && mtime == lastScriptsJarMtime) return

      if (!projectClassesRegistered) {
        val classLoader =
          ChildFirstClassLoader(
            urls = arrayOf(scriptsJar.toUri().toURL()),
            parent = KanamaHotReload::class.java.classLoader,
            childFirstPrefixes = childFirstPrefixes,
            reloadId = 0,
          )
        try {
          if (registerProjectClasses(classLoader, lib)) {
            projectClassLoader = classLoader
            projectClassesRegistered = true
          } else {
            classLoader.close()
            projectClassesRegistered = true
          }
        } catch (t: Throwable) {
          try {
            classLoader.close()
          } catch (_: Throwable) {}
          throw t
        }
      }

      val newLoader =
        ChildFirstClassLoader(
          urls = arrayOf(scriptsJar.toUri().toURL()),
          parent = KanamaHotReload::class.java.classLoader,
          childFirstPrefixes = childFirstPrefixes,
          reloadId = nextLoaderId++,
        )

      val previousTemplates = KanamaScript.snapshotScriptTemplates()
      try {
        KanamaScript.clearScriptTemplates()
        // Register only reloadable project script templates from the scripts jar.
        val registryClass =
          newLoader.loadClass("net.multigesture.kanama.generated.KanamaScriptRegistry")
        val registerAll = registryClass.getMethod("registerAll", MemorySegment::class.java)
        if (Modifier.isStatic(registerAll.modifiers)) {
          registerAll.invoke(null, lib)
        } else {
          val instance = registryClass.getField("INSTANCE").get(null)
          registerAll.invoke(instance, lib)
        }
        val rebound = KanamaScript.rebindAllScripts()

        val oldLoader = scriptClassLoader
        scriptClassLoader = newLoader
        lastScriptsJarMtime = mtime
        // Task 133 C2: autoloads outlive the scene reload; give them the new build's objects.
        val autoloads = AutoloadReload.recreate(oldLoader)
        retireLoader(oldLoader)
        // The reset autoloads' old objects are gone: a build with nothing else left is done now.
        sweepRetiredConnections()
        System.err.println(
          "[kanama:kt] hot-reload: reloaded scripts from $scriptsJar " +
            "(loader=${newLoader.reloadId}, old_loader=${oldLoader?.reloadId ?: 0}, rebound=$rebound, " +
            "autoloads=$autoloads)"
        )
      } catch (t: Throwable) {
        KanamaScript.restoreScriptTemplates(previousTemplates)
        try {
          newLoader.close()
        } catch (_: Throwable) {}
        System.err.println(
          "[kanama:kt] hot-reload: failed loader=${newLoader.reloadId}: " +
            "${t::class.qualifiedName}: ${t.message}"
        )
      }
    }
  }

  private fun registerProjectClasses(loader: ClassLoader, library: MemorySegment): Boolean {
    val registryClass =
      try {
        loader.loadClass("net.multigesture.kanama.generated.KanamaClassRegistry")
      } catch (_: ClassNotFoundException) {
        return false
      }
    val registerAll = registryClass.getMethod("registerAll", MemorySegment::class.java)
    if (Modifier.isStatic(registerAll.modifiers)) {
      registerAll.invoke(null, library)
    } else {
      val instance = registryClass.getField("INSTANCE").get(null)
      registerAll.invoke(instance, library)
    }
    return true
  }

  private fun retireLoader(loader: ChildFirstClassLoader?) {
    if (loader == null) return
    try {
      loader.close()
    } catch (_: Throwable) {}
    retiredLoaders += RetiredLoader(loader.reloadId, WeakReference(loader))
    System.err.println("[kanama:kt] hot-reload: retired loader=${loader.reloadId}")
  }

  /** A retired build's last script object was freed: sweep on the next frame tick (main thread). */
  internal fun requestSweep() {
    sweepRequested = true
  }

  /**
   * Main thread. Drops the lambda connections of every retired build that has no live script object
   * left: each is unregistered (no release hook runs: its owner is gone, not Godot's connection)
   * and then disconnected in Godot when its emitter still exists; one warning names them all.
   * Builds that still have live objects keep their connections: a game whose scene was not reloaded
   * keeps running its old objects. Returns the number of connections dropped.
   */
  fun sweepRetiredConnections(): Int {
    val builds = retiredLoaders.mapNotNull { it.ref.get() }
    if (builds.isEmpty()) return 0
    val swept =
      sweepBuilds(
        builds,
        describe = ::describeEmitter,
        disconnect = { id, connection ->
          if (GD.isInstanceIdValid(connection.emitterInstanceId)) {
            SignalCallables.disconnect(
              MemorySegment.ofAddress(connection.emitterAddress),
              connection.signal,
              connection.receiverInstanceId,
              id,
            )
          }
        },
        warn = { runCatching { GD.pushWarning(it) } },
      )
    sweptSinceCheck += swept
    return swept
  }

  /** The sweep over [builds], with its engine calls passed in (unit-tested without an engine). */
  internal fun sweepBuilds(
    builds: List<ChildFirstClassLoader>,
    describe: (SignalCallbackRegistry.Connection) -> String,
    disconnect: (Long, SignalCallbackRegistry.Connection) -> Unit,
    warn: (String) -> Unit,
  ): Int {
    val sweptBuilds = ArrayList<Long>()
    val bySignal = LinkedHashMap<String, Int>()
    var swept = 0
    for (build in builds) {
      if (ScriptBridge.liveScriptObjects(build) > 0) continue
      val connections = SignalCallbackRegistry.connectionsOf(build)
      if (connections.isEmpty()) continue
      sweptBuilds += build.reloadId
      for ((id, connection) in connections) {
        val label = runCatching { describe(connection) }.getOrDefault(connection.signal)
        // Unregistered first: the disconnect's free_func then finds no entry to release.
        SignalCallbackRegistry.unregister(id)
        runCatching { disconnect(id, connection) }
          .onFailure { error ->
            System.err.println(
              "[kanama:kt] hot-reload: disconnecting $label failed: ${error.message}"
            )
          }
        bySignal[label] = (bySignal[label] ?: 0) + 1
        swept++
      }
    }
    if (swept > 0) {
      val which =
        if (sweptBuilds.size == 1) "build ${sweptBuilds.single()}"
        else "builds ${sweptBuilds.joinToString(", ")}"
      warn(
        "hot-reload: dropped $swept lambda connection(s) of $which whose script objects are gone " +
          "(${bySignal.entries.joinToString(", ") { "${it.key} x${it.value}" }}); the new build's " +
          "_ready connects again"
      )
    }
    return swept
  }

  // `Node.signal` for a node emitter (its name: an autoload reads as its global), else
  // `Class.signal`.
  private fun describeEmitter(connection: SignalCallbackRegistry.Connection): String {
    if (!GD.isInstanceIdValid(connection.emitterInstanceId)) return "<freed>.${connection.signal}"
    val emitter = GodotObject(GodotHandle(MemorySegment.ofAddress(connection.emitterAddress)))
    val name =
      if (emitter.isClass("Node")) Node(emitter.handle).getName() else emitter.getClassName()
    return "$name.${connection.signal}"
  }

  private fun checkRetiredLoaders(now: Long = System.nanoTime()) {
    if (retiredLoaders.isEmpty()) return
    if (now < nextRetiredLoaderCheckNanos) return
    nextRetiredLoaderCheckNanos = now + RETIRED_LOADER_CHECK_INTERVAL_NANOS
    // Backstop for the per-frame sweep: nothing a finished build made may pin its loader.
    sweepRetiredConnections()

    var collected = 0
    var alive = 0
    val aliveIds = ArrayList<Long>()
    // System.gc() is a hint: under machine load a single cycle often leaves the
    // retired loader uncollected until the next check, and a short-lived process
    // (the in-process reload smoke) can exit before that check fires. Retry a few
    // bounded rounds within one check — this path only runs while a retired
    // loader exists, i.e. right after a hot reload.
    for (attempt in 0 until 3) {
      System.gc()
      alive = 0
      aliveIds.clear()
      val iterator = retiredLoaders.iterator()
      while (iterator.hasNext()) {
        val retired = iterator.next()
        if (retired.ref.get() == null) {
          collected++
          iterator.remove()
        } else {
          alive++
          aliveIds += retired.reloadId
        }
      }
      if (alive == 0) break
      Thread.sleep(25)
    }
    System.err.println(
      "[kanama:kt] hot-reload: retired-loader-check collected=$collected " +
        "alive=$alive alive_ids=${aliveIds.joinToString(",")} swept=$sweptSinceCheck"
    )
    sweptSinceCheck = 0
  }

  private fun findScriptsJar(): Path? {
    val cp = System.getProperty("java.class.path") ?: return null
    val sep = System.getProperty("path.separator")
    val runtimeJar =
      cp
        .split(sep)
        .asSequence()
        .map { Paths.get(it) }
        .firstOrNull { it.fileName.toString() == "kanama.jar" && Files.exists(it) } ?: return null
    val scriptsJar = runtimeJar.parent?.resolve("kanama-scripts.jar") ?: return null
    return if (Files.exists(scriptsJar)) scriptsJar else null
  }
}
