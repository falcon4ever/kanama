package net.multigesture.kanama.binding.runtime

import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.basisFromRows
import net.multigesture.kanama.types.basisGetRotationQuaternion
import net.multigesture.kanama.types.basisGetScale

/**
 * The engine-backed builtin methods the Web build runs in Kotlin instead of over the bridge (task
 * 134 D1): the ones gameplay calls every tick whose Godot formula needs `sin`/`cos`/`atan2`/`asin`/
 * `acos` (`slerp`, `rotated`, `getEuler`/`fromEuler`, `interpolateWith`) or a debug-only check
 * (`lookingAt`). The purely arithmetic Basis/Transform3D methods are not here: they are shared pure
 * members on every backend (`types/BuiltinFormulas.kt`).
 *
 * Each is a port of Godot 4.7.2's `core/math` at `real_t` (float32) width, in Godot's operation
 * order, with the release build's semantics -- the Web export template is a release build, so the
 * engine has no `MATH_CHECKS` there either. Kotlin's transcendental functions can differ from the
 * engine's libm in the last bit; `WebBuiltinParityTest` runs every method here on the runtime
 * smoke's facade inputs and holds it to Godot's recorded values, bit for bit or within the ulp
 * bound recorded per method (`WEB_LOCAL_FACADE` in `scripts/generate_builtin_ops.py`). A shape a
 * port does not cover (colinear `lookingAt` vectors, a degenerate basis in `interpolateWith`)
 * answers false and goes to the engine.
 */
internal object WebLocalBuiltins {
  private const val VECTOR2 = 5
  private const val VECTOR3 = 9
  private const val QUATERNION = 15
  private const val BASIS = 17
  private const val TRANSFORM3D = 18
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
          "rotated" -> { f, _ ->
              f.retReals(multiply(axisAngle(f.reals(1, 3), f.real(2, 0)), f.reals(0, 9)))
            }
          "get_euler" -> { f, _ ->
              getEuler(f.reals(0, 9), f.longs[1])?.let { f.retReals(it) } == true
            }
          "from_euler" -> { f, _ ->
              fromEuler(f.reals(1, 3), f.longs[2])?.let { f.retReals(it) } == true
            }
          "looking_at" -> { f, _ ->
              lookingAt(f.reals(1, 3), f.reals(2, 3), f.slots[3][0] != 0.0)?.let {
                f.retReals(it)
              } == true
            }
          else -> null
        }
      TRANSFORM3D ->
        when (name) {
          "looking_at" -> { f, _ ->
              val t = f.reals(0, 12)
              val target = f.reals(1, 3)
              val direction = floatArrayOf(target[0] - t[9], target[1] - t[10], target[2] - t[11])
              lookingAt(direction, f.reals(2, 3), f.slots[3][0] != 0.0)?.let {
                f.retReals(it + t.copyOfRange(9, 12))
              } == true
            }
          "interpolate_with" -> { f, _ ->
              interpolateWith(f.reals(0, 12), f.reals(1, 12), f.real(2, 0))?.let {
                f.retReals(it)
              } == true
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

  /** `Vector3::normalize`: a non-finite or zero vector becomes zero. */
  private fun normalized3(a: FloatArray): FloatArray {
    if (!a.all { it.isFinite() }) return floatArrayOf(0f, 0f, 0f)
    val lengthsq = a[0] * a[0] + a[1] * a[1] + a[2] * a[2]
    if (lengthsq == 0f) return floatArrayOf(0f, 0f, 0f)
    val length = sqrt(lengthsq)
    return floatArrayOf(a[0] / length, a[1] / length, a[2] / length)
  }

  /** `Vector3::signed_angle_to`. */
  private fun signedAngleTo(from: FloatArray, to: FloatArray, axis: FloatArray): Float {
    val crossTo = cross3(from, to)
    val unsigned =
      atan2(
        sqrt(crossTo[0] * crossTo[0] + crossTo[1] * crossTo[1] + crossTo[2] * crossTo[2]),
        dot3(from, to),
      )
    return if (dot3(crossTo, axis) < 0f) -unsigned else unsigned
  }

  /** `Math::acos(float)`: clamped to [-1, 1]. */
  private fun godotAcos(x: Float): Float =
    if (x < -1f) kotlin.math.PI.toFloat() else if (x > 1f) 0f else acos(x)

  /** `Math::asin(float)`: clamped to [-1, 1]. */
  private fun godotAsin(x: Float): Float =
    if (x < -1f) -(kotlin.math.PI.toFloat()) / 2
    else if (x > 1f) kotlin.math.PI.toFloat() / 2 else asin(x)

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
      val omega = godotAcos(cosom)
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

  private val HALF_PI = (kotlin.math.PI / 2.0).toFloat()

  /**
   * `Basis::get_euler(order)`, every Euler order; null for an invalid order (the engine errors).
   */
  private fun getEuler(m: FloatArray, order: Long): FloatArray? {
    val one = 1f - EULER_EPSILON
    return when (order) {
      0L -> { // XYZ
        val sy = m[2]
        if (sy < one) {
          if (sy > -one) {
            if (m[3] == 0f && m[1] == 0f && m[5] == 0f && m[7] == 0f && m[4] == 1f) {
              floatArrayOf(0f, atan2(m[2], m[0]), 0f)
            } else {
              floatArrayOf(atan2(-m[5], m[8]), godotAsin(sy), atan2(-m[1], m[0]))
            }
          } else {
            floatArrayOf(atan2(m[7], m[4]), -HALF_PI, 0f)
          }
        } else {
          floatArrayOf(atan2(m[7], m[4]), HALF_PI, 0f)
        }
      }
      1L -> { // XZY
        val sz = m[1]
        if (sz < one) {
          if (sz > -one) floatArrayOf(atan2(m[7], m[4]), atan2(m[2], m[0]), godotAsin(-sz))
          else floatArrayOf(-atan2(m[5], m[8]), 0f, HALF_PI)
        } else {
          floatArrayOf(-atan2(m[5], m[8]), 0f, -HALF_PI)
        }
      }
      2L -> { // YXZ
        val m12 = m[5]
        if (m12 < one) {
          if (m12 > -one) {
            if (m[3] == 0f && m[1] == 0f && m[2] == 0f && m[6] == 0f && m[0] == 1f) {
              floatArrayOf(atan2(-m12, m[4]), 0f, 0f)
            } else {
              // std::asin here, not Math::asin: unclamped, as Godot writes it.
              floatArrayOf(asin(-m12), atan2(m[2], m[8]), atan2(m[3], m[4]))
            }
          } else {
            floatArrayOf(HALF_PI, atan2(m[1], m[0]), 0f)
          }
        } else {
          floatArrayOf(-HALF_PI, -atan2(m[1], m[0]), 0f)
        }
      }
      3L -> { // YZX
        val sz = m[3]
        if (sz < one) {
          if (sz > -one) floatArrayOf(atan2(-m[5], m[4]), atan2(-m[6], m[0]), godotAsin(sz))
          else floatArrayOf(atan2(m[7], m[8]), 0f, -HALF_PI)
        } else {
          floatArrayOf(atan2(m[7], m[8]), 0f, HALF_PI)
        }
      }
      4L -> { // ZXY
        val sx = m[7]
        if (sx < one) {
          if (sx > -one) floatArrayOf(godotAsin(sx), atan2(-m[6], m[8]), atan2(-m[1], m[4]))
          else floatArrayOf(-HALF_PI, atan2(m[2], m[0]), 0f)
        } else {
          floatArrayOf(HALF_PI, atan2(m[2], m[0]), 0f)
        }
      }
      5L -> { // ZYX
        val sy = m[6]
        if (sy < one) {
          if (sy > -one) floatArrayOf(atan2(m[7], m[8]), godotAsin(-sy), atan2(m[3], m[0]))
          else floatArrayOf(0f, HALF_PI, -atan2(m[1], m[4]))
        } else {
          floatArrayOf(0f, -HALF_PI, -atan2(m[1], m[4]))
        }
      }
      else -> null
    }
  }

  /** `Basis::from_euler(e, order)` (`set_euler`); null for an invalid order (the engine errors). */
  private fun fromEuler(e: FloatArray, order: Long): FloatArray? {
    var c = cos(e[0])
    var s = sin(e[0])
    val xmat = floatArrayOf(1f, 0f, 0f, 0f, c, -s, 0f, s, c)
    c = cos(e[1])
    s = sin(e[1])
    val ymat = floatArrayOf(c, 0f, s, 0f, 1f, 0f, -s, 0f, c)
    c = cos(e[2])
    s = sin(e[2])
    val zmat = floatArrayOf(c, -s, 0f, s, c, 0f, 0f, 0f, 1f)
    return when (order) {
      0L -> multiply(xmat, multiply(ymat, zmat))
      1L -> multiply(multiply(xmat, zmat), ymat)
      2L -> multiply(multiply(ymat, xmat), zmat)
      3L -> multiply(multiply(ymat, zmat), xmat)
      4L -> multiply(multiply(zmat, xmat), ymat)
      5L -> multiply(multiply(zmat, ymat), xmat)
      else -> null
    }
  }

  private fun fromColumns(x: FloatArray, y: FloatArray, z: FloatArray): FloatArray =
    floatArrayOf(x[0], y[0], z[0], x[1], y[1], z[1], x[2], y[2], z[2])

  /**
   * `Basis::looking_at(target, up, use_model_front)`, or null when the vectors are colinear (Godot
   * then warns and picks a perpendicular: the engine path does exactly that).
   */
  private fun lookingAt(target: FloatArray, up: FloatArray, useModelFront: Boolean): FloatArray? {
    val n = normalized3(target)
    val vz = if (useModelFront) n else floatArrayOf(-n[0], -n[1], -n[2])
    val vx0 = cross3(up, vz)
    if (vx0.all { kotlin.math.abs(it) < CMP_EPSILON }) return null
    val vx = normalized3(vx0)
    val vy = cross3(vz, vx)
    return fromColumns(vx, vy, vz)
  }

  // ---- Transform3D: 9 basis rows, then the origin ----

  private fun basisOf(m: FloatArray): Basis =
    basisFromRows(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8])

  /**
   * `Transform3D::interpolate_with`, or null when a basis is degenerate (the engine then errors).
   */
  private fun interpolateWith(from: FloatArray, to: FloatArray, weight: Float): FloatArray? {
    val srcBasis = basisOf(from)
    val dstBasis = basisOf(to)
    val srcScale = basisGetScale(srcBasis)
    val srcRot = basisGetRotationQuaternion(srcBasis) ?: return null
    val dstScale = basisGetScale(dstBasis)
    val dstRot = basisGetRotationQuaternion(dstBasis) ?: return null
    val q = slerp(quat(srcRot), quat(dstRot), weight)
    val qn =
      quat(
        Quaternion(q[0].toDouble(), q[1].toDouble(), q[2].toDouble(), q[3].toDouble()).normalized()
      )
    val ss = vec(srcScale)
    val ds = vec(dstScale)
    val scale = FloatArray(3) { ss[it] + (ds[it] - ss[it]) * weight }
    // set_quaternion_scale: Basis(q) * diagonal(scale).
    val r =
      Basis(Quaternion(qn[0].toDouble(), qn[1].toDouble(), qn[2].toDouble(), qn[3].toDouble()))
    val rotation =
      floatArrayOf(
        r.x.x.toFloat(),
        r.y.x.toFloat(),
        r.z.x.toFloat(),
        r.x.y.toFloat(),
        r.y.y.toFloat(),
        r.z.y.toFloat(),
        r.x.z.toFloat(),
        r.y.z.toFloat(),
        r.z.z.toFloat(),
      )
    val diagonal = floatArrayOf(scale[0], 0f, 0f, 0f, scale[1], 0f, 0f, 0f, scale[2])
    val origin = FloatArray(3) { from[9 + it] + (to[9 + it] - from[9 + it]) * weight }
    return multiply(rotation, diagonal) + origin
  }

  private fun quat(q: Quaternion) =
    floatArrayOf(q.x.toFloat(), q.y.toFloat(), q.z.toFloat(), q.w.toFloat())

  private fun vec(v: Vector3) = floatArrayOf(v.x.toFloat(), v.y.toFloat(), v.z.toFloat())
}
