package net.multigesture.kanama.binding

import java.lang.foreign.MemorySegment
import java.lang.ref.WeakReference
import java.lang.reflect.Modifier
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/** Reloads project script templates from `kanama-scripts.jar` without editor restart. */
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
      library = MemorySegment.NULL
    }
  }

  fun frameTick() {
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
        retireLoader(oldLoader)
        System.err.println(
          "[kanama:kt] hot-reload: reloaded scripts from $scriptsJar " +
            "(loader=${newLoader.reloadId}, old_loader=${oldLoader?.reloadId ?: 0}, rebound=$rebound)"
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

  private fun checkRetiredLoaders(now: Long = System.nanoTime()) {
    if (retiredLoaders.isEmpty()) return
    if (now < nextRetiredLoaderCheckNanos) return
    nextRetiredLoaderCheckNanos = now + RETIRED_LOADER_CHECK_INTERVAL_NANOS

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
        "alive=$alive alive_ids=${aliveIds.joinToString(",")}"
    )
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
