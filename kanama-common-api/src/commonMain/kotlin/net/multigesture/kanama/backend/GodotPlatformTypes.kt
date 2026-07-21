package net.multigesture.kanama.backend

/** Opaque engine-object identity. The target-specific representation is not part of commonMain. */
@InternalKanamaBackendApi
expect class GodotHandle {
  @InternalKanamaBackendApi
  companion object {
    fun fromBackendToken(token: Long): GodotHandle
  }

  @InternalKanamaBackendApi fun backendToken(): Long
}

/** Opaque resolved call site: native MethodBind on native backends, opcode on Web. */
@InternalKanamaBackendApi
expect class GodotCallSite {
  @InternalKanamaBackendApi
  companion object {
    fun fromBackendToken(token: Long): GodotCallSite
  }

  @InternalKanamaBackendApi fun backendToken(): Long
}
