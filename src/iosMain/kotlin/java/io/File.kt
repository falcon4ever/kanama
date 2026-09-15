package java.io

/**
 * Minimal Kotlin/Native shim for `java.io.File`, mirroring the `java.lang.foreign.MemorySegment`
 * shim so shared demo/game source that references `java.io.File` compiles for iOS.
 *
 * The only current user is JVM-only **smoke-harness** code (env-gated `KANAMA_TPS_SMOKE_*` reload
 * markers) that never runs on device — the smoke env vars aren't set in an iOS app. Desktop/Android
 * use the real JVM `java.io.File`; this shim is inert on iOS (no on-device filesystem persistence).
 * If a real iOS file path is ever needed, back these with Foundation (`NSFileManager`/`NSString`).
 */
class File(val pathname: String) {
  val parentFile: File?
    get() {
      val trimmed = pathname.trimEnd('/')
      val idx = trimmed.lastIndexOf('/')
      return if (idx > 0) File(trimmed.substring(0, idx)) else null
    }

  fun exists(): Boolean = false

  fun mkdirs(): Boolean = false

  fun readText(): String = ""

  fun writeText(text: String) {
    // inert on iOS (see class note)
  }
}
