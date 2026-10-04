package net.multigesture.kanama.types

// Godot's own formulas for the value-type operators that are more than one component-wise
// operation (task 134 B): ported from core/math (transform_2d.h/.cpp, transform_3d.h/.cpp,
// basis.h, quaternion.h, projection.h/.cpp, rect2.h, aabb.h) with Godot's operand order and
// evaluated at the stored `real_t` width, so the results are the engine's to the bit. The
// generated operators (scripts/generate_builtin_ops.py) call these; the runtime smoke's builtin
// parity row compares every one of them with GDScript over fixed-seed random inputs.
//
// A Basis is three column vectors here (`x`, `y`, `z`) and three rows in Godot (`rows[i][j]` is
// column j's component i), so `rows[0]` is (x.x, y.x, z.x).

private inline fun <R> basisRows(
  b: Basis,
  use:
    (
      GodotRealStorage,
      GodotRealStorage,
      GodotRealStorage,
      GodotRealStorage,
      GodotRealStorage,
      GodotRealStorage,
      GodotRealStorage,
      GodotRealStorage,
      GodotRealStorage,
    ) -> R,
): R = use(b.x.rawX, b.y.rawX, b.z.rawX, b.x.rawY, b.y.rawY, b.z.rawY, b.x.rawZ, b.y.rawZ, b.z.rawZ)

/** A Basis from Godot's rows (`set(xx, xy, xz, yx, yy, yz, zx, zy, zz)`). */
internal fun basisFromRows(
  r00: GodotRealStorage,
  r01: GodotRealStorage,
  r02: GodotRealStorage,
  r10: GodotRealStorage,
  r11: GodotRealStorage,
  r12: GodotRealStorage,
  r20: GodotRealStorage,
  r21: GodotRealStorage,
  r22: GodotRealStorage,
): Basis = Basis(Vector3.raw(r00, r10, r20), Vector3.raw(r01, r11, r21), Vector3.raw(r02, r12, r22))

/** `Basis::operator*(Basis)`: row i is `(m.tdotx(rows[i]), m.tdoty(rows[i]), m.tdotz(rows[i]))`. */
internal fun basisMultiply(a: Basis, m: Basis): Basis =
  basisRows(a) { a00, a01, a02, a10, a11, a12, a20, a21, a22 ->
    basisRows(m) { m00, m01, m02, m10, m11, m12, m20, m21, m22 ->
      basisFromRows(
        m00 * a00 + m10 * a01 + m20 * a02,
        m01 * a00 + m11 * a01 + m21 * a02,
        m02 * a00 + m12 * a01 + m22 * a02,
        m00 * a10 + m10 * a11 + m20 * a12,
        m01 * a10 + m11 * a11 + m21 * a12,
        m02 * a10 + m12 * a11 + m22 * a12,
        m00 * a20 + m10 * a21 + m20 * a22,
        m01 * a20 + m11 * a21 + m21 * a22,
        m02 * a20 + m12 * a21 + m22 * a22,
      )
    }
  }

/** `Basis::xform(Vector3)`: `Vector3(rows[0].dot(v), rows[1].dot(v), rows[2].dot(v))`. */
internal fun basisXform(b: Basis, v: Vector3): Vector3 =
  basisRows(b) { r00, r01, r02, r10, r11, r12, r20, r21, r22 ->
    Vector3.raw(
      r00 * v.rawX + r01 * v.rawY + r02 * v.rawZ,
      r10 * v.rawX + r11 * v.rawY + r12 * v.rawZ,
      r20 * v.rawX + r21 * v.rawY + r22 * v.rawZ,
    )
  }

/** `Basis::xform_inv(Vector3)`: the transposed product. */
internal fun basisXformInv(b: Basis, v: Vector3): Vector3 =
  basisRows(b) { r00, r01, r02, r10, r11, r12, r20, r21, r22 ->
    Vector3.raw(
      (r00 * v.rawX) + (r10 * v.rawY) + (r20 * v.rawZ),
      (r01 * v.rawX) + (r11 * v.rawY) + (r21 * v.rawZ),
      (r02 * v.rawX) + (r12 * v.rawY) + (r22 * v.rawZ),
    )
  }

/** `Basis::operator*(real_t)` / `operator/(real_t)`: every element. */
internal inline fun basisMap(b: Basis, f: (GodotRealStorage) -> GodotRealStorage): Basis =
  Basis(
    Vector3.raw(f(b.x.rawX), f(b.x.rawY), f(b.x.rawZ)),
    Vector3.raw(f(b.y.rawX), f(b.y.rawY), f(b.y.rawZ)),
    Vector3.raw(f(b.z.rawX), f(b.z.rawY), f(b.z.rawZ)),
  )

/** `Basis::transposed` (exact: no arithmetic). */
internal fun basisTransposed(b: Basis): Basis =
  Basis(
    Vector3.raw(b.x.rawX, b.y.rawX, b.z.rawX),
    Vector3.raw(b.x.rawY, b.y.rawY, b.z.rawY),
    Vector3.raw(b.x.rawZ, b.y.rawZ, b.z.rawZ),
  )

/** `Transform3D::xform(Vector3)`: `basis[i].dot(v) + origin[i]`. */
internal fun transform3DXform(t: Transform3D, v: Vector3): Vector3 =
  basisRows(t.basis) { r00, r01, r02, r10, r11, r12, r20, r21, r22 ->
    Vector3.raw(
      r00 * v.rawX + r01 * v.rawY + r02 * v.rawZ + t.origin.rawX,
      r10 * v.rawX + r11 * v.rawY + r12 * v.rawZ + t.origin.rawY,
      r20 * v.rawX + r21 * v.rawY + r22 * v.rawZ + t.origin.rawZ,
    )
  }

/** `Transform3D::xform_inv(Vector3)`: `v - origin` through the transposed basis. */
internal fun transform3DXformInv(t: Transform3D, p: Vector3): Vector3 =
  basisXformInv(
    t.basis,
    Vector3.raw(p.rawX - t.origin.rawX, p.rawY - t.origin.rawY, p.rawZ - t.origin.rawZ),
  )

/** `Transform3D::operator*(Transform3D)`: `origin = xform(t.origin); basis *= t.basis`. */
internal fun transform3DMultiply(a: Transform3D, b: Transform3D): Transform3D =
  Transform3D(basisMultiply(a.basis, b.basis), transform3DXform(a, b.origin))

/** `Transform3D::xform(AABB)` (the theomader per-axis min/max form). */
internal fun transform3DXformAabb(t: Transform3D, box: AABB): AABB {
  val min = floatsOf(box.position)
  val max =
    reals(
      box.position.rawX + box.size.rawX,
      box.position.rawY + box.size.rawY,
      box.position.rawZ + box.size.rawZ,
    )
  val origin = floatsOf(t.origin)
  val rows = basisRows(t.basis) { a, b, c, d, e, f, g, h, i -> reals(a, b, c, d, e, f, g, h, i) }
  val tmin = origin.copyOf()
  val tmax = origin.copyOf()
  for (i in 0 until 3) {
    for (j in 0 until 3) {
      val e = rows[i * 3 + j] * min[j]
      val f = rows[i * 3 + j] * max[j]
      if (e < f) {
        tmin[i] += e
        tmax[i] += f
      } else {
        tmin[i] += f
        tmax[i] += e
      }
    }
  }
  return AABB(
    Vector3.raw(tmin[0], tmin[1], tmin[2]),
    Vector3.raw(tmax[0] - tmin[0], tmax[1] - tmin[1], tmax[2] - tmin[2]),
  )
}

/** `Transform3D::xform_inv(AABB)`: the eight corners through `xform_inv`, expanded. */
internal fun transform3DXformInvAabb(t: Transform3D, box: AABB): AABB {
  val px = box.position.rawX
  val py = box.position.rawY
  val pz = box.position.rawZ
  val ex = px + box.size.rawX
  val ey = py + box.size.rawY
  val ez = pz + box.size.rawZ
  val corners =
    arrayOf(
      Vector3.raw(ex, ey, ez),
      Vector3.raw(ex, ey, pz),
      Vector3.raw(ex, py, ez),
      Vector3.raw(ex, py, pz),
      Vector3.raw(px, ey, ez),
      Vector3.raw(px, ey, pz),
      Vector3.raw(px, py, ez),
      Vector3.raw(px, py, pz),
    )
  val first = transform3DXformInv(t, corners[0])
  val box3 = BoxAccumulator(floatsOf(first), reals(zeroReal(), zeroReal(), zeroReal()))
  for (i in 1 until 8) box3.expandTo(floatsOf(transform3DXformInv(t, corners[i])))
  return AABB(
    Vector3.raw(box3.position[0], box3.position[1], box3.position[2]),
    Vector3.raw(box3.size[0], box3.size[1], box3.size[2]),
  )
}

/**
 * `Transform3D::xform(Plane)`: a point on the plane through `xform`, the normal through the
 * inverse-transposed basis (`basis.inverse()` is the engine's), normalized.
 */
internal fun transform3DXformPlane(t: Transform3D, plane: Plane): Plane =
  planeThrough(transform3DXform(t, planePoint(plane)), basisTransposed(t.basis.inverse()), plane)

/** `Transform3D::xform_inv(Plane)`: through `affine_inverse()` (the engine's) and `basis^T`. */
internal fun transform3DXformInvPlane(t: Transform3D, plane: Plane): Plane =
  planeThrough(
    transform3DXform(t.affineInverse(), planePoint(plane)),
    basisTransposed(t.basis),
    plane,
  )

// `p_plane.normal * p_plane.d`.
private fun planePoint(plane: Plane): Vector3 =
  Vector3.raw(
    plane.normal.rawX * plane.rawD,
    plane.normal.rawY * plane.rawD,
    plane.normal.rawZ * plane.rawD,
  )

// The tail of `xform_fast` / `xform_inv_fast`: normal through [normalBasis], normalized; d =
// normal.dot(point).
private fun planeThrough(point: Vector3, normalBasis: Basis, plane: Plane): Plane {
  val normal = basisXform(normalBasis, plane.normal).normalized()
  return Plane.raw(
    normal,
    realDot(normal.rawX, normal.rawY, normal.rawZ, point.rawX, point.rawY, point.rawZ),
  )
}

/** `Quaternion::xform(Vector3)`: `v + ((uv * w) + u.cross(uv)) * 2` with `uv = u.cross(v)`. */
internal fun quaternionXform(q: Quaternion, v: Vector3): Vector3 {
  val ux = q.rawX
  val uy = q.rawY
  val uz = q.rawZ
  val w = q.rawW
  val uvx = uy * v.rawZ - uz * v.rawY
  val uvy = uz * v.rawX - ux * v.rawZ
  val uvz = ux * v.rawY - uy * v.rawX
  val two = narrowReal(2.0)
  return Vector3.raw(
    v.rawX + ((uvx * w) + (uy * uvz - uz * uvy)) * two,
    v.rawY + ((uvy * w) + (uz * uvx - ux * uvz)) * two,
    v.rawZ + ((uvz * w) + (ux * uvy - uy * uvx)) * two,
  )
}

/** `Quaternion::xform_inv(Vector3)`: `inverse().xform(v)`, the inverse being `(-x, -y, -z, w)`. */
internal fun quaternionXformInv(q: Quaternion, v: Vector3): Vector3 =
  quaternionXform(Quaternion.raw(-q.rawX, -q.rawY, -q.rawZ, q.rawW), v)

/** `Transform2D::xform(Vector2)`: `Vector2(tdotx(v), tdoty(v)) + columns[2]`. */
internal fun transform2DXform(t: Transform2D, v: Vector2): Vector2 =
  Vector2.raw(
    t.x.rawX * v.rawX + t.y.rawX * v.rawY + t.origin.rawX,
    t.x.rawY * v.rawX + t.y.rawY * v.rawY + t.origin.rawY,
  )

/** `Transform2D::xform_inv(Vector2)`: `v - columns[2]`, then `columns[i].dot(v)`. */
internal fun transform2DXformInv(t: Transform2D, p: Vector2): Vector2 {
  val vx = p.rawX - t.origin.rawX
  val vy = p.rawY - t.origin.rawY
  return Vector2.raw(t.x.rawX * vx + t.x.rawY * vy, t.y.rawX * vx + t.y.rawY * vy)
}

/** `Transform2D::operator*(Transform2D)`: origin first, then the 2x2 part by `tdotx/tdoty`. */
internal fun transform2DMultiply(a: Transform2D, b: Transform2D): Transform2D =
  Transform2D(
    Vector2.raw(
      a.x.rawX * b.x.rawX + a.y.rawX * b.x.rawY,
      a.x.rawY * b.x.rawX + a.y.rawY * b.x.rawY,
    ),
    Vector2.raw(
      a.x.rawX * b.y.rawX + a.y.rawX * b.y.rawY,
      a.x.rawY * b.y.rawX + a.y.rawY * b.y.rawY,
    ),
    transform2DXform(a, b.origin),
  )

/** `Transform2D::xform(Rect2)`: the transformed position expanded by the two scaled axes. */
internal fun transform2DXformRect(t: Transform2D, rect: Rect2): Rect2 {
  val xx = t.x.rawX * rect.size.rawX
  val xy = t.x.rawY * rect.size.rawX
  val yx = t.y.rawX * rect.size.rawY
  val yy = t.y.rawY * rect.size.rawY
  val pos = transform2DXform(t, rect.position)
  val box = BoxAccumulator(reals(pos.rawX, pos.rawY), reals(zeroReal(), zeroReal()))
  box.expandTo(reals(pos.rawX + xx, pos.rawY + xy))
  box.expandTo(reals(pos.rawX + yx, pos.rawY + yy))
  box.expandTo(reals(pos.rawX + xx + yx, pos.rawY + xy + yy))
  return Rect2(Vector2.raw(box.position[0], box.position[1]), Vector2.raw(box.size[0], box.size[1]))
}

/** `Transform2D::xform_inv(Rect2)`: the four corners through `xform_inv`, expanded. */
internal fun transform2DXformInvRect(t: Transform2D, rect: Rect2): Rect2 {
  val px = rect.position.rawX
  val py = rect.position.rawY
  val ex = px + rect.size.rawX
  val ey = py + rect.size.rawY
  val first = transform2DXformInv(t, Vector2.raw(px, py))
  val box = BoxAccumulator(reals(first.rawX, first.rawY), reals(zeroReal(), zeroReal()))
  for (corner in arrayOf(Vector2.raw(px, ey), Vector2.raw(ex, ey), Vector2.raw(ex, py))) {
    val v = transform2DXformInv(t, corner)
    box.expandTo(reals(v.rawX, v.rawY))
  }
  return Rect2(Vector2.raw(box.position[0], box.position[1]), Vector2.raw(box.size[0], box.size[1]))
}

/** `Projection::operator*(Projection)`: `ab` accumulates from 0 over k, column-major. */
internal fun projectionMultiply(a: Projection, m: Projection): Projection {
  val ac = projectionColumns(a)
  val mc = projectionColumns(m)
  val out =
    Array(4) { j ->
      Array(4) { i ->
        var ab = zeroReal()
        for (k in 0 until 4) ab += ac[k][i] * mc[j][k]
        ab
      }
    }
  return Projection(
    Vector4.raw(out[0][0], out[0][1], out[0][2], out[0][3]),
    Vector4.raw(out[1][0], out[1][1], out[1][2], out[1][3]),
    Vector4.raw(out[2][0], out[2][1], out[2][2], out[2][3]),
    Vector4.raw(out[3][0], out[3][1], out[3][2], out[3][3]),
  )
}

/** `Projection::xform(Vector4)`: `sum over k of columns[k][i] * v[k]`. */
internal fun projectionXform(p: Projection, v: Vector4): Vector4 {
  val c = projectionColumns(p)
  return Vector4.raw(
    c[0][0] * v.rawX + c[1][0] * v.rawY + c[2][0] * v.rawZ + c[3][0] * v.rawW,
    c[0][1] * v.rawX + c[1][1] * v.rawY + c[2][1] * v.rawZ + c[3][1] * v.rawW,
    c[0][2] * v.rawX + c[1][2] * v.rawY + c[2][2] * v.rawZ + c[3][2] * v.rawW,
    c[0][3] * v.rawX + c[1][3] * v.rawY + c[2][3] * v.rawZ + c[3][3] * v.rawW,
  )
}

/** `Projection::xform_inv(Vector4)`: the transposed product, `columns[i].dot(v)`. */
internal fun projectionXformInv(p: Projection, v: Vector4): Vector4 {
  val c = projectionColumns(p)
  return Vector4.raw(
    c[0][0] * v.rawX + c[0][1] * v.rawY + c[0][2] * v.rawZ + c[0][3] * v.rawW,
    c[1][0] * v.rawX + c[1][1] * v.rawY + c[1][2] * v.rawZ + c[1][3] * v.rawW,
    c[2][0] * v.rawX + c[2][1] * v.rawY + c[2][2] * v.rawZ + c[2][3] * v.rawW,
    c[3][0] * v.rawX + c[3][1] * v.rawY + c[3][2] * v.rawZ + c[3][3] * v.rawW,
  )
}

private fun projectionColumns(p: Projection): Array<GodotRealArray> =
  arrayOf(floatsOf(p.x), floatsOf(p.y), floatsOf(p.z), floatsOf(p.w))

private fun floatsOf(v: Vector3): GodotRealArray = reals(v.rawX, v.rawY, v.rawZ)

private fun floatsOf(v: Vector4): GodotRealArray = reals(v.rawX, v.rawY, v.rawZ, v.rawW)

private fun zeroReal(): GodotRealStorage = narrowReal(0.0)

// An unboxed `real_t` array of the given components (GodotRealArray is FloatArray/DoubleArray).
private fun reals(vararg values: GodotRealStorage): GodotRealArray = values

/**
 * `Rect2::expand_to` / `AABB::expand_to` over N axes: `begin = position; end = position + size`,
 * each axis widened to include the point, then `size = end - begin`.
 */
private class BoxAccumulator(val position: GodotRealArray, val size: GodotRealArray) {
  fun expandTo(point: GodotRealArray) {
    for (i in position.indices) {
      var begin = position[i]
      var end = position[i] + size[i]
      if (point[i] < begin) begin = point[i]
      if (point[i] > end) end = point[i]
      position[i] = begin
      size[i] = end - begin
    }
  }
}
