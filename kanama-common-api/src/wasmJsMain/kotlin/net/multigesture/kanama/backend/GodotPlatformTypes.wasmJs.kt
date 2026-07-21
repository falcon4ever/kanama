package net.multigesture.kanama.backend

actual class GodotHandle private constructor(private val token: Int) {
  @InternalKanamaBackendApi
  actual companion object {
    actual fun fromBackendToken(token: Long): GodotHandle {
      require(token in 1..Int.MAX_VALUE) { "Invalid Web Godot handle token=$token" }
      return GodotHandle(token.toInt())
    }
  }

  @InternalKanamaBackendApi actual fun backendToken(): Long = token.toLong()
}

actual class GodotCallSite private constructor(private val opcode: Int) {
  @InternalKanamaBackendApi
  actual companion object {
    actual fun fromBackendToken(token: Long): GodotCallSite {
      require(token in 1..Int.MAX_VALUE) { "Invalid Web Godot call opcode=$token" }
      return GodotCallSite(token.toInt())
    }
  }

  @InternalKanamaBackendApi actual fun backendToken(): Long = opcode.toLong()
}
