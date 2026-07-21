package net.multigesture.kanama.types

import net.multigesture.kanama.binding.runtime.BuiltinCalls

/**
 * A 3×4 matrix representing a 3D transformation: a [Basis] (rotation/scale/shear) plus an origin
 * (translation). Kanama value types are immutable snapshots; assign a new value back to the Godot
 * property after changing components.
 *
 * iOS marshalling note: at ptrcall a Transform3D is 12 `real_t` (float32 on single-precision iOS) —
 * the 9 column-major basis components followed by the 3 origin components — laid out by the
 * generated ObjectCalls helpers. Engine-computed value methods (e.g. [inverse]) route through the
 * BuiltinCalls value-type call path.
 */
data class Transform3D(val basis: Basis, val origin: Vector3) {
  /** Godot-style fuzzy compare: true if basis and origin are approximately equal. */
  fun isEqualApprox(other: Transform3D): Boolean =
    basis.isEqualApprox(other.basis) && origin.isEqualApprox(other.origin)

  /**
   * kanama convenience (Godot has no composite `is_zero_approx`): true if basis and origin are
   * approximately zero.
   */
  fun isZeroApprox(): Boolean = basis.isZeroApprox() && origin.isZeroApprox()

  /**
   * Returns the inverse of this transform, assuming its [basis] is orthonormal (a pure rotation, no
   * scale/shear). Godot computes this the fast way — transposing the basis and re-deriving the
   * origin — so for a scaled/sheared transform use the full matrix inverse (`affine_inverse`, not
   * yet wired on iOS) instead.
   */
  /** Returns a copy with [basis] replaced (matches desktop Transform3D.withBasis). */
  fun withBasis(value: Basis): Transform3D = copy(basis = value)

  /** Returns a copy with [origin] replaced (matches desktop Transform3D.withOrigin). */
  fun withOrigin(value: Vector3): Transform3D = copy(origin = value)

  fun inverse(): Transform3D =
    fromGodotRealArray(BuiltinCalls.callNoArgsFloat32(inverseBind, toGodotRealArray()))

  /**
   * Returns the full (affine) inverse of this transform — the true matrix inverse, valid even when
   * the [basis] has scale or shear (unlike [inverse], which assumes orthonormal).
   */
  fun affineInverse(): Transform3D =
    fromGodotRealArray(BuiltinCalls.callNoArgsFloat32(affineInverseBind, toGodotRealArray()))

  /** Returns a copy of this transform with its [basis] orthonormalized (Gram-Schmidt). */
  fun orthonormalized(): Transform3D =
    fromGodotRealArray(BuiltinCalls.callNoArgsFloat32(orthonormalizedBind, toGodotRealArray()))

  /**
   * Returns a copy of this transform rotated so the forward axis (-Z) points toward [target],
   * keeping the up axis as close to [up] as possible; the result is orthonormalized and the
   * original rotation/scale is discarded (origin is kept). If [useModelFront] is true the +Z axis
   * (asset front) is treated as forward instead.
   */
  fun lookingAt(
    target: Vector3,
    up: Vector3 = Vector3.UP,
    useModelFront: Boolean = false,
  ): Transform3D =
    fromGodotRealArray(
      BuiltinCalls.call(
        lookingAtBind,
        toGodotRealArray(),
        12,
        listOf(
          BuiltinCalls.BArg.Floats(BuiltinCalls.PT_VECTOR3, vec3(target)),
          BuiltinCalls.BArg.Floats(BuiltinCalls.PT_VECTOR3, vec3(up)),
          BuiltinCalls.BArg.Bool(useModelFront),
        ),
      )
    )

  /**
   * Returns the linear interpolation between this transform and [to] by [weight] (0 = this, 1 =
   * [to]); the rotation is interpolated via quaternion slerp.
   */
  fun interpolateWith(to: Transform3D, weight: Double): Transform3D =
    fromGodotRealArray(
      BuiltinCalls.call(
        interpolateWithBind,
        toGodotRealArray(),
        12,
        listOf(
          BuiltinCalls.BArg.Floats(BuiltinCalls.PT_TRANSFORM3D, to.toGodotRealArray()),
          BuiltinCalls.BArg.Real(weight),
        ),
      )
    )

  /**
   * Returns a copy of this transform translated by [offset] in global space (offset added to
   * [origin] directly). For translation relative to the transform's own basis use
   * `translated_local` instead (not yet wired on iOS).
   */
  fun translated(offset: Vector3): Transform3D =
    fromGodotRealArray(
      BuiltinCalls.call(
        translatedBind,
        toGodotRealArray(),
        12,
        listOf(BuiltinCalls.BArg.Floats(BuiltinCalls.PT_VECTOR3, vec3(offset))),
      )
    )

  /**
   * Transforms (xforms) [point] by this transform: `basis * point + origin` (matches Transform3D *
   * Vector3).
   */
  operator fun times(point: Vector3): Vector3 = basis * point + origin

  /**
   * Returns a copy scaled by [scale] in local space, keeping [origin] (matches
   * Transform3D.scaled_local == post-multiplying the basis by a diagonal scale, i.e. scaling each
   * basis column).
   */
  fun scaledLocal(scale: Vector3): Transform3D =
    Transform3D(Basis(basis.x * scale.x, basis.y * scale.y, basis.z * scale.z), origin)

  // Column-major real_t values, matching the ObjectCalls Transform3D ptrcall layout.
  private fun toGodotRealArray(): GodotRealArray =
    GodotRealArray(12).also {
      it[0] = GodotReal.toC(basis.x.x)
      it[1] = GodotReal.toC(basis.y.x)
      it[2] = GodotReal.toC(basis.z.x)
      it[3] = GodotReal.toC(basis.x.y)
      it[4] = GodotReal.toC(basis.y.y)
      it[5] = GodotReal.toC(basis.z.y)
      it[6] = GodotReal.toC(basis.x.z)
      it[7] = GodotReal.toC(basis.y.z)
      it[8] = GodotReal.toC(basis.z.z)
      it[9] = GodotReal.toC(origin.x)
      it[10] = GodotReal.toC(origin.y)
      it[11] = GodotReal.toC(origin.z)
    }

  companion object {
    val IDENTITY = Transform3D(Basis.IDENTITY, Vector3.ZERO)

    // The hash keys the signature SHAPE; the name selects the method. The no-arg->Self
    // methods (inverse/affine_inverse/orthonormalized) share one hash; looking_at and
    // interpolate_with have their own argument shapes.
    private val inverseBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_TRANSFORM3D, "inverse", 3816817146L)
    }
    private val affineInverseBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_TRANSFORM3D, "affine_inverse", 3816817146L)
    }
    private val orthonormalizedBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_TRANSFORM3D, "orthonormalized", 3816817146L)
    }
    private val lookingAtBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_TRANSFORM3D, "looking_at", 90889270L)
    }
    private val interpolateWithBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_TRANSFORM3D, "interpolate_with", 1786453358L)
    }
    private val translatedBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_TRANSFORM3D, "translated", 1405596198L)
    }

    private fun vec3(v: Vector3): GodotRealArray =
      GodotRealArray(3).also {
        it[0] = GodotReal.toC(v.x)
        it[1] = GodotReal.toC(v.y)
        it[2] = GodotReal.toC(v.z)
      }

    private fun fromGodotRealArray(c: GodotRealArray): Transform3D =
      Transform3D(
        basis =
          Basis(
            Vector3(GodotReal.fromC(c[0]), GodotReal.fromC(c[3]), GodotReal.fromC(c[6])),
            Vector3(GodotReal.fromC(c[1]), GodotReal.fromC(c[4]), GodotReal.fromC(c[7])),
            Vector3(GodotReal.fromC(c[2]), GodotReal.fromC(c[5]), GodotReal.fromC(c[8])),
          ),
        origin = Vector3(GodotReal.fromC(c[9]), GodotReal.fromC(c[10]), GodotReal.fromC(c[11])),
      )
  }
}
