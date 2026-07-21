package net.multigesture.kanama.backend

actual class GodotHandle private constructor(private val token: Long) {
  @InternalKanamaBackendApi
  actual companion object {
    actual fun fromBackendToken(token: Long): GodotHandle = GodotHandle(token)
  }

  @InternalKanamaBackendApi actual fun backendToken(): Long = token
}

actual class GodotCallSite private constructor(private val token: Long) {
  @InternalKanamaBackendApi
  actual companion object {
    actual fun fromBackendToken(token: Long): GodotCallSite = GodotCallSite(token)
  }

  @InternalKanamaBackendApi actual fun backendToken(): Long = token
}
