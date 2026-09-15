package net.multigesture.kanama.types

import net.multigesture.kanama.binding.runtime.BArg
import net.multigesture.kanama.binding.runtime.BuiltinCalls
import net.multigesture.kanama.binding.runtime.PT_TRANSFORM3D
import net.multigesture.kanama.binding.runtime.PT_VECTOR3
import net.multigesture.kanama.binding.runtime.VT_TRANSFORM3D

private const val LOOKING_AT_HASH = 90889270L
private const val INTERPOLATE_WITH_HASH = 1786453358L
private const val NO_ARG_SELF_HASH = 3816817146L
private const val VECTOR3_ARG_SELF_HASH = 1405596198L

// One body for every backend (task 104 step 2): every method here is computed by the engine through
// BuiltinCalls, because each one encodes a Godot convention (the orthonormal-only fast `inverse`,
// the quaternion slerp inside `interpolate_with`, the discarded rotation in `looking_at`). At
// ptrcall a Transform3D is 12 `real_t` — the 9 column-major basis components followed by the 3
// origin components.
/**
 * A 3×4 matrix representing a 3D transformation. Kanama value types are immutable snapshots; assign
 * a new value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Transform3D
 */
data class Transform3D(
  /**
   * The `Basis` of this transform. It is composed by 3 axes (`Basis.x`, `Basis.y`, and `Basis.z`).
   * Together, these represent the transform's rotation, scale, and shear.
   *
   * Generated from Godot docs: Transform3D.basis
   */
  val basis: Basis,
  /**
   * The translation offset of this transform. In 3D space, this can be seen as the position.
   *
   * Generated from Godot docs: Transform3D.origin
   */
  val origin: Vector3,
) {
  /** Godot-style fuzzy compare: true if basis and origin are approximately equal. */
  /**
   * Returns `true` if this transform and `xform` are approximately equal, by running
   * `@GlobalScope.is_equal_approx` on each component.
   *
   * Generated from Godot docs: Transform3D.is_equal_approx
   */
  fun isEqualApprox(other: Transform3D): Boolean =
    basis.isEqualApprox(other.basis) && origin.isEqualApprox(other.origin)

  /**
   * kanama convenience (Godot has no composite `is_zero_approx`): true if basis and origin are
   * approximately zero.
   */
  fun isZeroApprox(): Boolean = basis.isZeroApprox() && origin.isZeroApprox()

  operator fun times(vector: Vector3): Vector3 = basis * vector + origin

  /**
   * Returns the inverted version of this transform
   * (https://en.wikipedia.org/wiki/Invertible_matrix). See also `Basis.inverse`. Note: For this
   * method to return correctly, the transform's `basis` needs to be orthonormal (see
   * `orthonormalized`). That means the basis should only represent a rotation. If it does not, use
   * `affine_inverse` instead.
   *
   * Generated from Godot docs: Transform3D.inverse
   */
  fun inverse(): Transform3D =
    fromGodotRealArray(BuiltinCalls.callNoArgsFloat32(inverseBind, toGodotRealArray()))

  /**
   * Returns the inverted version of this transform. Unlike `inverse`, this method works with almost
   * any `basis`, including non-uniform ones, but is slower. See also `Basis.inverse`. Note: For
   * this method to return correctly, the transform's `basis` needs to have a determinant that is
   * not exactly `0.0` (see `Basis.determinant`).
   *
   * Generated from Godot docs: Transform3D.affine_inverse
   */
  fun affineInverse(): Transform3D =
    fromGodotRealArray(BuiltinCalls.callNoArgsFloat32(affineInverseBind, toGodotRealArray()))

  /**
   * Returns a copy of this transform with its `basis` orthonormalized. An orthonormal basis is both
   * orthogonal (the axes are perpendicular to each other) and normalized (the axes have a length of
   * `1.0`), which also means it can only represent a rotation. See also `Basis.orthonormalized`.
   *
   * Generated from Godot docs: Transform3D.orthonormalized
   */
  fun orthonormalized(): Transform3D =
    fromGodotRealArray(BuiltinCalls.callNoArgsFloat32(orthonormalizedBind, toGodotRealArray()))

  /**
   * Returns the result of the linear interpolation between this transform and `xform` by the given
   * `weight`. The `weight` should be between `0.0` and `1.0` (inclusive). Values outside this range
   * are allowed and can be used to perform extrapolation instead.
   *
   * Generated from Godot docs: Transform3D.interpolate_with
   */
  fun interpolateWith(to: Transform3D, weight: Double): Transform3D =
    fromGodotRealArray(
      BuiltinCalls.call(
        interpolateWithBind,
        toGodotRealArray(),
        12,
        listOf(BArg.Floats(PT_TRANSFORM3D, to.toGodotRealArray()), BArg.Real(weight)),
      )
    )

  /**
   * Returns a copy of this transform rotated so that the forward axis (-Z) points towards the
   * `target` position. The up axis (+Y) points as close to the `up` vector as possible while
   * staying perpendicular to the forward axis. The resulting transform is orthonormalized. The
   * existing rotation, scale, and skew information from the original transform is discarded. The
   * `target` and `up` vectors cannot be zero, cannot be parallel to each other, and are defined in
   * global/parent space. If `use_model_front` is `true`, the +Z axis (asset front) is treated as
   * forward (implies +X is left) and points toward the `target` position. By default, the -Z axis
   * (camera forward) is treated as forward (implies +X is right).
   *
   * Generated from Godot docs: Transform3D.looking_at
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
          BArg.Floats(PT_VECTOR3, vector3Array(target)),
          BArg.Floats(PT_VECTOR3, vector3Array(up)),
          BArg.Bool(useModelFront),
        ),
      )
    )

  /**
   * Returns a copy of this transform scaled by the given `scale` factor. This method is an
   * optimized version of multiplying the given transform `X` with a corresponding scaling transform
   * `S` from the right, i.e., `X * S`. This can be seen as transforming with respect to the local
   * frame.
   *
   * Generated from Godot docs: Transform3D.scaled_local
   */
  fun scaledLocal(scale: Vector3): Transform3D = callVector3RetSelf(scaledLocalBind, scale)

  /**
   * Returns a copy of this transform translated by the given `offset`. This method is an optimized
   * version of multiplying the given transform `X` with a corresponding translation transform `T`
   * from the left, i.e., `T * X`. This can be seen as transforming with respect to the
   * global/parent frame.
   *
   * Generated from Godot docs: Transform3D.translated
   */
  fun translated(offset: Vector3): Transform3D = callVector3RetSelf(translatedBind, offset)

  fun withBasis(value: Basis): Transform3D = copy(basis = value)

  fun withOrigin(value: Vector3): Transform3D = copy(origin = value)

  // The (Vector3) -> Self shape that scaled_local and translated share.
  private fun callVector3RetSelf(methodPtr: Long, vector: Vector3): Transform3D =
    fromGodotRealArray(
      BuiltinCalls.call(
        methodPtr,
        toGodotRealArray(),
        12,
        listOf(BArg.Floats(PT_VECTOR3, vector3Array(vector))),
      )
    )

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
    /**
     * The identity `Transform3D`. This is a transform with no translation, no rotation, and a scale
     * of `Vector3.ONE`. Its `basis` is equal to `Basis.IDENTITY`. This also means that: - Its
     * `Basis.x` points right (`Vector3.RIGHT`); - Its `Basis.y` points up (`Vector3.UP`); - Its
     * `Basis.z` points back (`Vector3.BACK`).
     *
     * Generated from Godot docs: Transform3D.IDENTITY
     */
    val IDENTITY = Transform3D(Basis.IDENTITY, Vector3.ZERO)

    // The hash keys the signature SHAPE, the name selects the method: inverse, affine_inverse and
    // orthonormalized are all no-arg -> Self; scaled_local and translated are both (Vector3) ->
    // Self.
    private val inverseBind by lazy {
      BuiltinCalls.getBuiltinMethod(VT_TRANSFORM3D, "inverse", NO_ARG_SELF_HASH)
    }
    private val affineInverseBind by lazy {
      BuiltinCalls.getBuiltinMethod(VT_TRANSFORM3D, "affine_inverse", NO_ARG_SELF_HASH)
    }
    private val orthonormalizedBind by lazy {
      BuiltinCalls.getBuiltinMethod(VT_TRANSFORM3D, "orthonormalized", NO_ARG_SELF_HASH)
    }
    private val lookingAtBind by lazy {
      BuiltinCalls.getBuiltinMethod(VT_TRANSFORM3D, "looking_at", LOOKING_AT_HASH)
    }
    private val interpolateWithBind by lazy {
      BuiltinCalls.getBuiltinMethod(VT_TRANSFORM3D, "interpolate_with", INTERPOLATE_WITH_HASH)
    }
    private val scaledLocalBind by lazy {
      BuiltinCalls.getBuiltinMethod(VT_TRANSFORM3D, "scaled_local", VECTOR3_ARG_SELF_HASH)
    }
    private val translatedBind by lazy {
      BuiltinCalls.getBuiltinMethod(VT_TRANSFORM3D, "translated", VECTOR3_ARG_SELF_HASH)
    }

    private fun vector3Array(v: Vector3): GodotRealArray =
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
