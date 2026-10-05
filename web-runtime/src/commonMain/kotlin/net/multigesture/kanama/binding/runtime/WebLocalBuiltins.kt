package net.multigesture.kanama.binding.runtime

import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.web.WebPackedValues

/**
 * The builtin methods the Web build runs in Kotlin instead of over the bridge (task 134 D1): the
 * ones gameplay calls every tick (`getRotationQuaternion`, `slerp`, `rotated`, `fromEuler` behind
 * `Node3D.rotation`, …), which would otherwise cost a bridge crossing per call. Natively these are
 * engine calls (the shared value types call them through [BuiltinFrame]); here they are ports of
 * Godot 4.7.2's `core/math` code at `real_t` (float32) width, in Godot's operation order, with
 * release-build semantics (no `MATH_CHECKS` errors). The arithmetic ones (inverse, transposed,
 * determinant, getScale, scaled, orthonormalized, getRotationQuaternion, lookingAt) give Godot's
 * bits; the ones built on sin/cos/atan2/asin/acos use Kotlin's functions, so a last-bit difference
 * from the engine's libm is possible (`interpolateWith`'s slerp shows one). WebBuiltinParityTest
 * checks fourteen of them against the runtime smoke's GDScript facade hashes. A shape a port does
 * not cover (a non-YXZ Euler order, `useModelFront`, colinear `lookingAt` vectors) answers false
 * and goes to the engine.
 */
internal object WebLocalBuiltins {
  private const val VECTOR2 = 5
  private const val VECTOR3 = 9
  private const val QUATERNION = 15
  private const val BASIS = 17
  private const val TRANSFORM3D = 18
  private const val EULER_ORDER_YXZ = 2L
  private const val CMP_EPSILON = 0.00001f
  private const val EULER_EPSILON = 0.00000025f

  fun find(variantType: Int, name: String): ((BuiltinFrame, Int) -> Boolean)? =
    when (variantType) {
      VECTOR2 ->
        when (name) {
          "angle" -> { f, _ -> f.retDouble(atan2(f.real(0, 1), f.real(0, 0)).toDouble()) }
          "rotated" -> { f, _ ->
              f.retReals(vector2Rotated(f.real(0, 0), f.real(0, 1), f.real(1, 0)))
            }
          else -> null
        }
      VECTOR3 ->
        when (name) {
          "signed_angle_to" -> { f, _ ->
              f.retDouble(signedAngleTo(f.reals(0, 3), f.reals(1, 3), f.reals(2, 3)).toDouble())
            }
          "rotated" -> { f, _ ->
              f.retReals(xform(axisAngle(f.reals(1, 3), f.real(2, 0)), f.reals(0, 3)))
            }
          else -> null
        }
      QUATERNION ->
        if (name == "slerp") {
          { f, _ -> f.retReals(slerp(f.reals(0, 4), f.reals(1, 4), f.real(2, 0))) }
        } else null
      BASIS ->
        when (name) {
          "inverse" -> { f, _ -> f.retReals(inverse(f.reals(0, 9))) }
          "transposed" -> { f, _ -> f.retReals(transposed(f.reals(0, 9))) }
          "determinant" -> { f, _ -> f.retDouble(determinant(f.reals(0, 9)).toDouble()) }
          "get_scale" -> { f, _ -> f.retReals(getScale(f.reals(0, 9))) }
          "scaled" -> { f, _ -> f.retReals(scaled(f.reals(0, 9), f.reals(1, 3))) }
          "orthonormalized" -> { f, _ -> f.retReals(orthonormalized(f.reals(0, 9))) }
          "get_rotation_quaternion" -> { f, _ -> f.retReals(getRotationQuaternion(f.reals(0, 9))) }
          "rotated" -> { f, _ ->
              f.retReals(multiply(axisAngle(f.reals(1, 3), f.real(2, 0)), f.reals(0, 9)))
            }
          "get_euler" -> { f, _ ->
              f.longs[1] == EULER_ORDER_YXZ && f.retReals(getEulerYxz(f.reals(0, 9)))
            }
          "from_euler" -> { f, _ ->
              f.longs[2] == EULER_ORDER_YXZ && f.retReals(fromEulerYxz(f.reals(1, 3)))
            }
          "looking_at" -> { f, _ ->
              f.slots[3][0] == 0.0 &&
                lookingAt(f.reals(1, 3), f.reals(2, 3))?.let { f.retReals(it) } == true
            }
          else -> null
        }
      TRANSFORM3D ->
        when (name) {
          "orthonormalized" -> { f, _ ->
              val t = f.reals(0, 12)
              f.retReals(orthonormalized(t.copyOf(9)) + t.copyOfRange(9, 12))
            }
          "looking_at" -> { f, _ ->
              val t = f.reals(0, 12)
              val target = f.reals(1, 3)
              val direction = floatArrayOf(target[0] - t[9], target[1] - t[10], target[2] - t[11])
              f.slots[3][0] == 0.0 &&
                lookingAt(direction, f.reals(2, 3))?.let {
                  f.retReals(it + t.copyOfRange(9, 12))
                } == true
            }
          "interpolate_with" -> { f, _ ->
              f.retReals(interpolateWith(f.reals(0, 12), f.reals(1, 12), f.real(2, 0)))
            }
          else -> null
        }
      else -> null
    }

  // ---- the frame: components in Godot's memory layout (a Basis is its rows), at real_t width ----

  private fun BuiltinFrame.real(slot: Int, index: Int): Float = slots[slot][index].toFloat()

  private fun BuiltinFrame.reals(slot: Int, count: Int): FloatArray =
    FloatArray(count) { slots[slot][it].toFloat() }

  private fun BuiltinFrame.retDouble(value: Double): Boolean {
    ret[0] = value
    return true
  }

  private fun BuiltinFrame.retReals(values: FloatArray): Boolean {
    for (i in values.indices) ret[i] = values[i].toDouble()
    retType = 0
    return true
  }

  // ---- Vector2 / Vector3 / Quaternion ----

  /** `Vector2::rotated`. */
  private fun vector2Rotated(x: Float, y: Float, by: Float): FloatArray {
    val sine = sin(by)
    val cosi = cos(by)
    return floatArrayOf(x * cosi - y * sine, x * sine + y * cosi)
  }

  private fun dot3(a: FloatArray, b: FloatArray): Float = a[0] * b[0] + a[1] * b[1] + a[2] * b[2]

  private fun cross3(a: FloatArray, b: FloatArray): FloatArray =
    floatArrayOf(a[1] * b[2] - a[2] * b[1], a[2] * b[0] - a[0] * b[2], a[0] * b[1] - a[1] * b[0])

  private fun length3(a: FloatArray): Float = sqrt(a[0] * a[0] + a[1] * a[1] + a[2] * a[2])

  /** `Vector3::normalize`: unchanged when the length is zero. */
  private fun normalized3(a: FloatArray): FloatArray {
    val lengthsq = a[0] * a[0] + a[1] * a[1] + a[2] * a[2]
    if (lengthsq == 0f) return floatArrayOf(0f, 0f, 0f)
    val length = sqrt(lengthsq)
    return floatArrayOf(a[0] / length, a[1] / length, a[2] / length)
  }

  /** `Vector3::signed_angle_to`. */
  private fun signedAngleTo(from: FloatArray, to: FloatArray, axis: FloatArray): Float {
    val crossTo = cross3(from, to)
    val unsigned = atan2(length3(crossTo), dot3(from, to))
    return if (dot3(crossTo, axis) < 0f) -unsigned else unsigned
  }

  /** `Quaternion::slerp`. */
  private fun slerp(q: FloatArray, to: FloatArray, weight: Float): FloatArray {
    var cosom = q[0] * to[0] + q[1] * to[1] + q[2] * to[2] + q[3] * to[3]
    val to1: FloatArray
    if (cosom < 0f) {
      cosom = -cosom
      to1 = floatArrayOf(-to[0], -to[1], -to[2], -to[3])
    } else {
      to1 = to
    }
    val scale0: Float
    val scale1: Float
    if (1f - cosom > CMP_EPSILON) {
      val omega = acos(cosom)
      val sinom = sin(omega)
      // `Math::sin((1.0 - p_weight) * omega)` is a double expression in Godot.
      scale0 = (sin((1.0 - weight) * omega) / sinom).toFloat()
      scale1 = sin(weight * omega) / sinom
    } else {
      scale0 = 1f - weight
      scale1 = weight
    }
    return FloatArray(4) { scale0 * q[it] + scale1 * to1[it] }
  }

  // ---- Basis: m is row-major (rows[0][0], rows[0][1], … rows[2][2]) ----

  /** `Basis::xform`. */
  private fun xform(m: FloatArray, v: FloatArray): FloatArray =
    floatArrayOf(
      m[0] * v[0] + m[1] * v[1] + m[2] * v[2],
      m[3] * v[0] + m[4] * v[1] + m[5] * v[2],
      m[6] * v[0] + m[7] * v[1] + m[8] * v[2],
    )

  /** `Basis::operator*`: row i of `a` against the columns of `b`, Godot's tdot order. */
  private fun multiply(a: FloatArray, b: FloatArray): FloatArray =
    FloatArray(9) {
      val row = it / 3
      val col = it % 3
      b[col] * a[row * 3] + b[3 + col] * a[row * 3 + 1] + b[6 + col] * a[row * 3 + 2]
    }

  /** `Basis(axis, angle)` (`set_axis_angle`; the axis is used as given, as in Godot). */
  private fun axisAngle(axis: FloatArray, angle: Float): FloatArray {
    val m = FloatArray(9)
    val sqx = axis[0] * axis[0]
    val sqy = axis[1] * axis[1]
    val sqz = axis[2] * axis[2]
    val cosine = cos(angle)
    m[0] = sqx + cosine * (1f - sqx)
    m[4] = sqy + cosine * (1f - sqy)
    m[8] = sqz + cosine * (1f - sqz)
    val sine = sin(angle)
    val t = 1f - cosine
    var xyzt = axis[0] * axis[1] * t
    var zyxs = axis[2] * sine
    m[1] = xyzt - zyxs
    m[3] = xyzt + zyxs
    xyzt = axis[0] * axis[2] * t
    zyxs = axis[1] * sine
    m[2] = xyzt + zyxs
    m[6] = xyzt - zyxs
    xyzt = axis[1] * axis[2] * t
    zyxs = axis[0] * sine
    m[5] = xyzt - zyxs
    m[7] = xyzt + zyxs
    return m
  }

  private fun cofac(m: FloatArray, r1: Int, c1: Int, r2: Int, c2: Int): Float =
    m[r1 * 3 + c1] * m[r2 * 3 + c2] - m[r1 * 3 + c2] * m[r2 * 3 + c1]

  /** `Basis::invert`. */
  private fun inverse(m: FloatArray): FloatArray {
    val co0 = cofac(m, 1, 1, 2, 2)
    val co1 = cofac(m, 1, 2, 2, 0)
    val co2 = cofac(m, 1, 0, 2, 1)
    val det = m[0] * co0 + m[1] * co1 + m[2] * co2
    val s = 1.0f / det
    return floatArrayOf(
      co0 * s,
      cofac(m, 0, 2, 2, 1) * s,
      cofac(m, 0, 1, 1, 2) * s,
      co1 * s,
      cofac(m, 0, 0, 2, 2) * s,
      cofac(m, 0, 2, 1, 0) * s,
      co2 * s,
      cofac(m, 0, 1, 2, 0) * s,
      cofac(m, 0, 0, 1, 1) * s,
    )
  }

  private fun transposed(m: FloatArray): FloatArray =
    floatArrayOf(m[0], m[3], m[6], m[1], m[4], m[7], m[2], m[5], m[8])

  /** `Basis::determinant`. */
  private fun determinant(m: FloatArray): Float =
    m[0] * (m[4] * m[8] - m[7] * m[5]) - m[3] * (m[1] * m[8] - m[7] * m[2]) +
      m[6] * (m[1] * m[5] - m[4] * m[2])

  /** `Basis::get_scale`: the column lengths, signed by `SIGN(determinant())` (0 for 0). */
  private fun getScale(m: FloatArray): FloatArray {
    val det = determinant(m)
    val sign = if (det > 0f) 1f else if (det < 0f) -1f else 0f
    return floatArrayOf(
      sign * length3(floatArrayOf(m[0], m[3], m[6])),
      sign * length3(floatArrayOf(m[1], m[4], m[7])),
      sign * length3(floatArrayOf(m[2], m[5], m[8])),
    )
  }

  /** `Basis::scaled`: each row times its scale component. */
  private fun scaled(m: FloatArray, s: FloatArray): FloatArray = FloatArray(9) { m[it] * s[it / 3] }

  private fun column(m: FloatArray, c: Int) = floatArrayOf(m[c], m[3 + c], m[6 + c])

  private fun fromColumns(x: FloatArray, y: FloatArray, z: FloatArray): FloatArray =
    floatArrayOf(x[0], y[0], z[0], x[1], y[1], z[1], x[2], y[2], z[2])

  /** `Basis::orthonormalize` (Gram-Schmidt). */
  private fun orthonormalized(m: FloatArray): FloatArray {
    val x = normalized3(column(m, 0))
    val y0 = column(m, 1)
    val xdy = dot3(x, y0)
    val y = normalized3(floatArrayOf(y0[0] - x[0] * xdy, y0[1] - x[1] * xdy, y0[2] - x[2] * xdy))
    val z0 = column(m, 2)
    val xdz = dot3(x, z0)
    val ydz = dot3(y, z0)
    val z =
      normalized3(
        floatArrayOf(
          z0[0] - x[0] * xdz - y[0] * ydz,
          z0[1] - x[1] * xdz - y[1] * ydz,
          z0[2] - x[2] * xdz - y[2] * ydz,
        )
      )
    return fromColumns(x, y, z)
  }

  /** `Basis::get_rotation_quaternion` + `get_quaternion`. */
  private fun getRotationQuaternion(basis: FloatArray): FloatArray {
    var m = orthonormalized(basis)
    if (determinant(m) < 0f) m = scaled(m, floatArrayOf(-1f, -1f, -1f))
    val trace = m[0] + m[4] + m[8]
    val temp = FloatArray(4)
    if (trace > 0f) {
      var s = sqrt(trace + 1f)
      temp[3] = s * 0.5f
      s = 0.5f / s
      temp[0] = (m[7] - m[5]) * s
      temp[1] = (m[2] - m[6]) * s
      temp[2] = (m[3] - m[1]) * s
    } else {
      val i = if (m[0] < m[4]) (if (m[4] < m[8]) 2 else 1) else (if (m[0] < m[8]) 2 else 0)
      val j = (i + 1) % 3
      val k = (i + 2) % 3
      var s = sqrt(m[i * 3 + i] - m[j * 3 + j] - m[k * 3 + k] + 1f)
      temp[i] = s * 0.5f
      s = 0.5f / s
      temp[3] = (m[k * 3 + j] - m[j * 3 + k]) * s
      temp[j] = (m[j * 3 + i] + m[i * 3 + j]) * s
      temp[k] = (m[k * 3 + i] + m[i * 3 + k]) * s
    }
    return temp
  }

  /** `Basis::get_euler(EULER_ORDER_YXZ)`. */
  private fun getEulerYxz(m: FloatArray): FloatArray {
    val m12 = m[5]
    val halfPi = (kotlin.math.PI * 0.5f).toFloat()
    return if (m12 < 1f - EULER_EPSILON) {
      if (m12 > -(1f - EULER_EPSILON)) {
        if (m[3] == 0f && m[1] == 0f && m[2] == 0f && m[6] == 0f && m[0] == 1f) {
          floatArrayOf(atan2(-m12, m[4]), 0f, 0f)
        } else {
          floatArrayOf(asin(-m12), atan2(m[2], m[8]), atan2(m[3], m[4]))
        }
      } else {
        floatArrayOf(halfPi, atan2(m[1], m[0]), 0f)
      }
    } else {
      floatArrayOf(-halfPi, -atan2(m[1], m[0]), 0f)
    }
  }

  /** `Basis::from_euler(e, EULER_ORDER_YXZ)`: `ymat * xmat * zmat`. */
  private fun fromEulerYxz(e: FloatArray): FloatArray {
    var c = cos(e[0])
    var s = sin(e[0])
    val xmat = floatArrayOf(1f, 0f, 0f, 0f, c, -s, 0f, s, c)
    c = cos(e[1])
    s = sin(e[1])
    val ymat = floatArrayOf(c, 0f, s, 0f, 1f, 0f, -s, 0f, c)
    c = cos(e[2])
    s = sin(e[2])
    val zmat = floatArrayOf(c, -s, 0f, s, c, 0f, 0f, 0f, 1f)
    return multiply(multiply(ymat, xmat), zmat)
  }

  /**
   * `Basis::looking_at(target, up, false)`, or null when the vectors are colinear (Godot then warns
   * and picks a perpendicular: the engine path does exactly that).
   */
  private fun lookingAt(target: FloatArray, up: FloatArray): FloatArray? {
    val n = normalized3(target)
    val vz = floatArrayOf(-n[0], -n[1], -n[2])
    val vx0 = cross3(up, vz)
    if (vx0.all { kotlin.math.abs(it) < CMP_EPSILON }) return null
    val vx = normalized3(vx0)
    val vy = cross3(vz, vx)
    return fromColumns(vx, vy, vz)
  }

  // ---- Transform3D: 9 basis rows, then the origin ----

  /** `Transform3D::interpolate_with`. */
  private fun interpolateWith(from: FloatArray, to: FloatArray, weight: Float): FloatArray {
    val srcBasis = from.copyOf(9)
    val dstBasis = to.copyOf(9)
    val srcScale = getScale(srcBasis)
    val srcRot = getRotationQuaternion(srcBasis)
    val dstScale = getScale(dstBasis)
    val dstRot = getRotationQuaternion(dstBasis)
    val q = slerp(srcRot, dstRot, weight)
    val qn = Quaternion.raw(q[0], q[1], q[2], q[3]).normalized()
    val scale = FloatArray(3) { srcScale[it] + (dstScale[it] - srcScale[it]) * weight }
    // set_quaternion_scale: Basis(q) * diagonal(scale).
    val rotation =
      WebPackedValues.components(Basis(qn))!!.let { c -> FloatArray(9) { c[it].toFloat() } }
    val diagonal = floatArrayOf(scale[0], 0f, 0f, 0f, scale[1], 0f, 0f, 0f, scale[2])
    val origin = FloatArray(3) { from[9 + it] + (to[9 + it] - from[9 + it]) * weight }
    return multiply(rotation, diagonal) + origin
  }
}
