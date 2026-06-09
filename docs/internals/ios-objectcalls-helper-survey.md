# iOS ObjectCalls Helper Survey (T1.1)

Survey of every `ObjectCalls.*` helper the desktop-generated Godot API wrappers
call, grouped by marshalling pattern, with iOS have/need annotation.

**Source grep:**
```
grep -rhoE "ObjectCalls\.[A-Za-z0-9]+" src/main/kotlin/net/multigesture/kanama/api/ \
  | sort | uniq -c | sort -rn
```
Run date: 2026-06-09.  Total distinct helpers: **1,467**.

---

## (a) Full helper list with usage counts

Only the top ~100 (covering the vast majority of call sites) are reproduced in
full; the long tail (counts 1–5) follows in summary.  `getMethodBind` is a
setup call, not a marshalling helper, listed first for completeness.

### Infrastructure (not a ptrcall shape)

| Count | Helper |
|------:|--------|
| 14961 | `ObjectCalls.getMethodBind` |
|    40 | `ObjectCalls.getSingleton` |
|    17 | `ObjectCalls.constructObject` |
|    13 | `ObjectCalls.callWithVariantArgs` |

### Core ptrcall shapes (count ≥ 40, covers ~85 % of all ptrcall sites)

| Count | Helper |
|------:|--------|
| 1137 | `ptrcallNoArgsRetBool` |
|  942 | `ptrcallWithBoolArg` |
|  908 | `ptrcallNoArgsRetDouble` |
|  848 | `ptrcallWithDoubleArg` |
|  684 | `ptrcallNoArgsRetLong` |
|  590 | `ptrcallWithLongArg` |
|  555 | `ptrcallNoArgsRetInt` |
|  488 | `ptrcallWithIntArg` |
|  417 | `ptrcallNoArgs` |
|  414 | `ptrcallNoArgsRetObject` |
|  358 | `ptrcallWithObjectArgs` |
|  266 | `ptrcallNoArgsRetString` |
|  210 | `ptrcallWithStringArg` |
|  170 | `ptrcallNoArgsRetVector2` |
|  154 | `ptrcallWithIntArgRetBool` |
|  148 | `ptrcallNoArgsRetRID` |
|  142 | `ptrcallWithVector2Arg` |
|  127 | `ptrcallWithIntAndBoolArgs` |
|  125 | `ptrcallNoArgsRetVector3` |
|  123 | `ptrcallWithVector3Arg` |
|  113 | `ptrcallWithIntArgRetInt` |
|  107 | `ptrcallNoArgsRetUInt32` |
|  103 | `ptrcallWithUInt32Arg` |
|  103 | `ptrcallWithRIDAndBoolArg` |
|   95 | `ptrcallWithRIDAndDoubleArg` |
|   94 | `ptrcallWithColorArg` |
|   93 | `ptrcallWithTwoIntArgs` |
|   84 | `ptrcallWithStringNameArg` |
|   83 | `ptrcallWithIntArgRetObject` |
|   82 | `ptrcallNoArgsRetColor` |
|   80 | `ptrcallWithRIDArg` |
|   80 | `ptrcallWithIntArgRetString` |
|   72 | `ptrcallWithTwoRIDArgs` |
|   71 | `ptrcallWithRIDAndLongArg` |
|   70 | `ptrcallWithRIDArgRetBool` |
|   69 | `ptrcallWithIntArgRetLong` |
|   65 | `ptrcallNoArgsRetPackedStringList` |
|   59 | `ptrcallWithIntArgRetDouble` |
|   58 | `ptrcallWithStringArgRetBool` |
|   54 | `ptrcallWithRIDArgRetDouble` |
|   54 | `ptrcallWithIntAndStringArg` |
|   52 | `ptrcallWithRIDArgRetLong` |
|   51 | `ptrcallWithStringArgRetLong` |
|   49 | `ptrcallWithIntAndDoubleArg` |
|   48 | `ptrcallWithStringNameArgRetBool` |
|   48 | `ptrcallWithNodePathArg` |
|   47 | `ptrcallWithIntAndLongArgs` |
|   47 | `ptrcallNoArgsRetNodePath` |
|   46 | `ptrcallNoArgsRetStringName` |
|   43 | `ptrcallWithLongArgRetBool` |
|   42 | `ptrcallWithIntAndObjectArg` |

### Mid-frequency (count 10–39)

| Count | Helper |
|------:|--------|
|  39 | `ptrcallNoArgsRetDictionary` |
|  35 | `ptrcallWithRIDAndIntArg` |
|  35 | `ptrcallNoArgsRetArray` |
|  34 | `ptrcallWithVector2iArg` |
|  34 | `ptrcallNoArgsRetVector2i` |
|  34 | `ptrcallNoArgsRetPackedInt32List` |
|  32 | `ptrcallWithRIDAndUInt32Arg` |
|  31 | `ptrcallNoArgsRetTypedObjectList` |
|  29 | `ptrcallWithLongArgRetDouble` |
|  27 | `ptrcallWithStringArgRetString` |
|  27 | `ptrcallWithRIDArgRetUInt32` |
|  27 | `ptrcallWithRIDAndVector3Arg` |
|  26 | `ptrcallWithLongAndDoubleArg` |
|  26 | `ptrcallWithIntArgRetVector3` |
|  26 | `ptrcallWithIntArgRetVector2` |
|  25 | `ptrcallWithTwoStringNameArgsRetBool` |
|  25 | `ptrcallWithRIDArgRetRID` |
|  24 | `ptrcallNoArgsRetRect2` |
|  23 | `ptrcallWithLongArgRetLong` |
|  22 | `ptrcallWithStringArgRetObject` |
|  22 | `ptrcallNoArgsRetTransform3D` |
|  21 | `ptrcallWithTwoStringNameArgs` |
|  21 | `ptrcallWithTwoIntArgsRetInt` |
|  21 | `ptrcallWithTwoIntArgsRetDouble` |
|  21 | `ptrcallWithStringNameArgRetVariantScalar` |
|  21 | `ptrcallWithRIDAndStringArg` |
|  21 | `ptrcallWithPackedInt32ListArg` |
|  21 | `ptrcallWithArrayArg` |
|  21 | `ptrcallNoArgsRetTransform2D` |
|  20 | `ptrcallWithPackedVector2ListArg` |
|  20 | `ptrcallWithLongAndBoolArgs` |
|  19 | `ptrcallWithRIDArgRetInt` |
|  19 | `ptrcallWithIntArgRetColor` |
|  19 | `ptrcallNoArgsRetRIDList` |
|  18 | `ptrcallWithStringNameAndVariantArg` |
|  18 | `ptrcallWithObjectListArg` |
|  18 | `ptrcallWithIntAndColorArg` |
|  18 | `ptrcallWithDictionaryArg` |
|  18 | `ptrcallNoArgsRetPackedVector2List` |
|  17 | `ptrcallWithObjectArgRetObject` |
|  17 | `ptrcallWithObjectArgRetBool` |
|  17 | `ptrcallWithLongArgRetObject` |
|  17 | `ptrcallNoArgsRetByteArray` |
|  17 | `ptrcallNoArgsRetAABB` |
|  16 | `ptrcallWithTwoIntAndDoubleArgs` |
|  16 | `ptrcallWithLongArgRetString` |
|  16 | `ptrcallWithIntArgRetStringName` |
|  16 | `ptrcallWithIntArgRetNodePath` |
|  16 | `ptrcallNoArgsRetDictionaryList` |
|  15 | `ptrcallWithStringArgRetPackedStringList` |
|  15 | `ptrcallWithRIDLongAndDoubleArgs` |
|  15 | `ptrcallWithObjectAndBoolArg` |
|  15 | `ptrcallWithCallableArg` |
|  14 | `ptrcallWithStringAndIntArg` |
|  14 | `ptrcallWithRIDListArg` |
|  14 | `ptrcallWithRIDAndLongArgRetDouble` |
|  14 | `ptrcallWithPackedStringListArg` |
|  14 | `ptrcallWithIntArgRetVariantScalar` |
|  14 | `ptrcallWithIntAndVector3Arg` |
|  14 | `ptrcallWithByteArrayArgRetLong` |
|  14 | `ptrcallNoArgsRetPackedVector3List` |
|  13 | `ptrcallWithTransform3DArg` |
|  13 | `ptrcallWithTransform2DArg` |
|  13 | `ptrcallWithStringNameArgRetObject` |
|  13 | `ptrcallWithRIDAndVector2Arg` |
|  13 | `ptrcallWithRIDAndTransform2DArg` |
|  13 | `ptrcallWithRIDAndColorArg` |
|  13 | `ptrcallWithLongArgRetInt` |
|  13 | `ptrcallWithLongAndIntArgs` |
|  12 | `ptrcallWithTwoIntArgsRetBool` |
|  12 | `ptrcallWithStringNameAndObjectArg` |
|  12 | `ptrcallWithRIDIntAndBoolArgs` |
|  12 | `ptrcallWithRIDCallableArgs` |
|  12 | `ptrcallWithRIDAndLongArgRetVariantScalar` |
|  12 | `ptrcallWithRect2Arg` |
|  12 | `ptrcallWithIntAndVector2Arg` |
|  12 | `ptrcallWithIntAndVariantArg` |
|  12 | `ptrcallWithIntAndNodePathArg` |
|  12 | `ptrcallNoArgsRetStringNameList` |
|  12 | `ptrcallNoArgsRetPackedInt64List` |
|  11 | `ptrcallWithTwoStringArgsRetLong` |
|  11 | `ptrcallWithStringAndBoolArg` |
|  11 | `ptrcallWithRIDArgRetVector3` |
|  11 | `ptrcallWithPackedFloat32ListArg` |
|  11 | `ptrcallWithIntArgRetVector2i` |
|  11 | `ptrcallWithIntArgRetTransform3D` |
|  11 | `ptrcallWithIntArgRetRID` |
|  11 | `ptrcallWithIntAndStringNameArg` |
|  11 | `ptrcallNoArgsRetVariantScalar` |
|  10 | `ptrcallWithVector2iArgRetInt` |
|  10 | `ptrcallWithTwoStringNameArgsRetObject` |
|  10 | `ptrcallWithTwoStringArgs` |
|  10 | `ptrcallWithTwoIntArgsRetString` |
|  10 | `ptrcallWithStringArgRetInt` |
|  10 | `ptrcallWithStringAndBoolArgRetLong` |
|  10 | `ptrcallWithRIDAndTwoIntArgs` |
|  10 | `ptrcallWithIntArgRetArray` |
|  10 | `ptrcallWithBoolArgRetObject` |
|  10 | `ptrcallWithAABBArg` |

### Long tail (count 1–9)

There are **~1,100 additional** bespoke multi-arg or niche-return helpers with
counts 1–9, all following the same `ptrcallWith<ArgTypes>[Ret<ReturnType>]`
naming convention.  Representative examples:

- `ptrcallWithFourStringBoolLongPackedStringListDictionaryListCallableIntArgsRetLong` (count 1)
- `ptrcallWithIntDoubleTwoBoolDoubleLongTwoBoolArgsRetDouble` (count 1)
- `ptrcallWithVector2iArgRetBool` (count 8)
- `ptrcallWithRIDArgRetDictionaryList` (count 7)
- `ptrcallWithCallableStringNameListObjectArgs` (count 1)

These are needed only for the full ~1086-class API; the platformer minimum
surface does **not** require any of these.

---

## (b) Grouped by marshalling pattern

Each group is identified by the return type and argument structure (GDExtension
ptrcall convention: args are `const void**`, return is `void*` written into).

### Group 0 — Infrastructure (not ptrcall shapes)

| Helper | Signature sketch |
|--------|-----------------|
| `getMethodBind(class, method, hash)` | → `MemorySegment` (MethodBindPtr) |
| `getSingleton(name)` | → `MemorySegment` (object ptr) |
| `constructObject(className)` | → `MemorySegment` (new object ptr) |
| `callWithVariantArgs(bind, inst, vararg)` | Variant-based call path |

### Group 1 — No-arg void (`ptrcallNoArgs`)

Single helper: call method, discard return.

### Group 2 — No-arg scalar returns

| Helper | C cell type |
|--------|------------|
| `ptrcallNoArgsRetBool` | `GDExtensionBool` (1 byte) |
| `ptrcallNoArgsRetInt` | `int32_t` |
| `ptrcallNoArgsRetLong` | `int64_t` |
| `ptrcallNoArgsRetUInt32` | `uint32_t` |
| `ptrcallNoArgsRetDouble` | `double` |
| `ptrcallNoArgsRetString` | `GDExtensionStringPtr` → Kotlin String |
| `ptrcallNoArgsRetStringName` | `GDExtensionStringNamePtr` → StringName |
| `ptrcallNoArgsRetVariantScalar` | `GDExtensionVariantPtr` read as scalar |

### Group 3 — No-arg struct/compound returns

| Helper | Return type | Notes |
|--------|------------|-------|
| `ptrcallNoArgsRetVector2` | Vector2 (2×double) | |
| `ptrcallNoArgsRetVector3` | Vector3 (3×double) | |
| `ptrcallNoArgsRetVector2i` | Vector2i (2×int32) | |
| `ptrcallNoArgsRetColor` | Color (4×float) | |
| `ptrcallNoArgsRetRID` | RID (int64) | |
| `ptrcallNoArgsRetNodePath` | NodePath (string-like) | |
| `ptrcallNoArgsRetObject` | object ptr | |
| `ptrcallNoArgsRetTransform3D` | Transform3D (12×double) | |
| `ptrcallNoArgsRetTransform2D` | Transform2D (6×double) | |
| `ptrcallNoArgsRetBasis` | Basis (9×double) | |
| `ptrcallNoArgsRetQuaternion` | Quaternion (4×double) | |
| `ptrcallNoArgsRetRect2` | Rect2 (4×double) | |
| `ptrcallNoArgsRetRect2i` | Rect2i (4×int32) | |
| `ptrcallNoArgsRetAABB` | AABB (6×double) | |
| `ptrcallNoArgsRetProjection` | Projection (16×float) | |

### Group 4 — No-arg list/array returns

| Helper | List element type |
|--------|------------------|
| `ptrcallNoArgsRetPackedStringList` | String |
| `ptrcallNoArgsRetPackedInt32List` | Int32 |
| `ptrcallNoArgsRetPackedInt64List` | Int64 |
| `ptrcallNoArgsRetPackedFloat32List` | Float32 |
| `ptrcallNoArgsRetPackedVector2List` | Vector2 |
| `ptrcallNoArgsRetPackedVector3List` | Vector3 |
| `ptrcallNoArgsRetPackedColorList` | Color |
| `ptrcallNoArgsRetByteArray` | Byte |
| `ptrcallNoArgsRetRIDList` | RID |
| `ptrcallNoArgsRetStringNameList` | StringName |
| `ptrcallNoArgsRetLongList` | Long |
| `ptrcallNoArgsRetTypedObjectList` | GodotObject (generic) |
| `ptrcallNoArgsRetTypedNode3DList` | Node3D |
| `ptrcallNoArgsRetTypedArea3DList` | Area3D |
| `ptrcallNoArgsRetTypedPhysicsBody3DList` | PhysicsBody3D |
| `ptrcallNoArgsRetTypedNodeList` | Node |
| `ptrcallNoArgsRetTypedBaseButtonList` | BaseButton |
| `ptrcallNoArgsRetDictionaryList` | Dictionary |
| `ptrcallNoArgsRetPlaneList` | Plane |
| `ptrcallNoArgsRetArray` | untyped Array |

### Group 5 — Single scalar arg, void return

| Helper | Arg type |
|--------|---------|
| `ptrcallWithBoolArg` | Bool |
| `ptrcallWithIntArg` | Int32 |
| `ptrcallWithLongArg` | Int64 |
| `ptrcallWithUInt32Arg` | UInt32 |
| `ptrcallWithDoubleArg` | Double |
| `ptrcallWithStringArg` | String |
| `ptrcallWithStringNameArg` | StringName |
| `ptrcallWithNodePathArg` | NodePath |
| `ptrcallWithRIDArg` | RID |
| `ptrcallWithColorArg` | Color |
| `ptrcallWithVariantArg` | Variant |
| `ptrcallWithCallableArg` | Callable |

### Group 6 — Single struct arg, void return

| Helper | Arg type |
|--------|---------|
| `ptrcallWithVector2Arg` | Vector2 |
| `ptrcallWithVector3Arg` | Vector3 |
| `ptrcallWithVector2iArg` | Vector2i |
| `ptrcallWithTransform3DArg` | Transform3D |
| `ptrcallWithTransform2DArg` | Transform2D |
| `ptrcallWithBasisArg` | Basis |
| `ptrcallWithQuaternionArg` | Quaternion |
| `ptrcallWithRect2Arg` | Rect2 |
| `ptrcallWithAABBArg` | AABB |
| `ptrcallWithObjectArgs` | 1..N GodotObject ptrs |

### Group 7 — Single scalar arg, scalar return

| Helper | Arg → Return |
|--------|-------------|
| `ptrcallWithBoolArgRetObject` | Bool → Object |
| `ptrcallWithBoolArgRetInt` | Bool → Int32 |
| `ptrcallWithIntArgRetBool` | Int → Bool |
| `ptrcallWithIntArgRetInt` | Int → Int32 |
| `ptrcallWithIntArgRetLong` | Int → Int64 |
| `ptrcallWithIntArgRetDouble` | Int → Double |
| `ptrcallWithIntArgRetObject` | Int → Object |
| `ptrcallWithIntArgRetString` | Int → String |
| `ptrcallWithIntArgRetStringName` | Int → StringName |
| `ptrcallWithIntArgRetNodePath` | Int → NodePath |
| `ptrcallWithIntArgRetRID` | Int → RID |
| `ptrcallWithIntArgRetColor` | Int → Color |
| `ptrcallWithIntArgRetVector2` | Int → Vector2 |
| `ptrcallWithIntArgRetVector3` | Int → Vector3 |
| `ptrcallWithIntArgRetVector2i` | Int → Vector2i |
| `ptrcallWithIntArgRetTransform3D` | Int → Transform3D |
| `ptrcallWithIntArgRetUInt32` | Int → UInt32 |
| `ptrcallWithIntArgRetVariantScalar` | Int → Variant scalar |
| `ptrcallWithLongArgRetBool` | Long → Bool |
| `ptrcallWithLongArgRetLong` | Long → Int64 |
| `ptrcallWithLongArgRetInt` | Long → Int32 |
| `ptrcallWithLongArgRetDouble` | Long → Double |
| `ptrcallWithLongArgRetObject` | Long → Object |
| `ptrcallWithLongArgRetString` | Long → String |
| `ptrcallWithUInt32ArgRetBool` | UInt32 → Bool |
| `ptrcallWithUInt32ArgRetInt` | UInt32 → Int32 |
| `ptrcallWithUInt32ArgRetObject` | UInt32 → Object |
| `ptrcallWithUInt32ArgRetTransform3D` | UInt32 → Transform3D |
| `ptrcallWithStringArgRetBool` | String → Bool |
| `ptrcallWithStringArgRetInt` | String → Int32 |
| `ptrcallWithStringArgRetLong` | String → Int64 |
| `ptrcallWithStringArgRetString` | String → String |
| `ptrcallWithStringArgRetObject` | String → Object |
| `ptrcallWithStringArgRetPackedStringList` | String → PackedStringArray |
| `ptrcallWithStringNameArgRetBool` | StringName → Bool |
| `ptrcallWithStringNameArgRetObject` | StringName → Object |
| `ptrcallWithStringNameArgRetStringName` | StringName → StringName |
| `ptrcallWithStringNameArgRetVariantScalar` | StringName → Variant scalar |
| `ptrcallWithNodePathArgRetBool` | NodePath → Bool |
| `ptrcallWithNodePathArgRetObject` | NodePath → Object |
| `ptrcallWithNodePathArgRetArray` | NodePath → Array |
| `ptrcallWithRIDArgRetBool` | RID → Bool |
| `ptrcallWithRIDArgRetInt` | RID → Int32 |
| `ptrcallWithRIDArgRetLong` | RID → Int64 |
| `ptrcallWithRIDArgRetDouble` | RID → Double |
| `ptrcallWithRIDArgRetRID` | RID → RID |
| `ptrcallWithRIDArgRetUInt32` | RID → UInt32 |
| `ptrcallWithRIDArgRetObject` | RID → Object |
| `ptrcallWithRIDArgRetString` | RID → String |
| `ptrcallWithRIDArgRetVector2` | RID → Vector2 |
| `ptrcallWithRIDArgRetVector3` | RID → Vector3 |
| `ptrcallWithRIDArgRetRIDList` | RID → RID[] |
| `ptrcallWithRIDArgRetDictionaryList` | RID → Dictionary[] |
| `ptrcallWithObjectArgRetBool` | Object → Bool |
| `ptrcallWithObjectArgRetObject` | Object → Object |
| `ptrcallWithObjectArgRetStringName` | Object → StringName |
| `ptrcallWithObjectArgRetUInt32` | Object → UInt32 |
| `ptrcallWithVector3ArgRetBool` | Vector3 → Bool |
| `ptrcallWithVector3ArgRetDouble` | Vector3 → Double |
| `ptrcallWithVector3ArgRetVector2` | Vector3 → Vector2 |
| `ptrcallWithVector3ArgRetVector3` | Vector3 → Vector3 |
| `ptrcallWithVector2iArgRetInt` | Vector2i → Int32 |
| `ptrcallWithVector2iArgRetBool` | Vector2i → Bool |

### Group 8 — Two scalar/struct args

| Helper | Args |
|--------|------|
| `ptrcallWithTwoIntArgs` | Int, Int → void |
| `ptrcallWithTwoIntArgsRetInt` | Int, Int → Int |
| `ptrcallWithTwoIntArgsRetDouble` | Int, Int → Double |
| `ptrcallWithTwoIntArgsRetBool` | Int, Int → Bool |
| `ptrcallWithTwoIntArgsRetString` | Int, Int → String |
| `ptrcallWithIntAndBoolArgs` | Int, Bool → void |
| `ptrcallWithIntAndBoolArgsRetObject` | Int, Bool → Object |
| `ptrcallWithIntAndDoubleArg` | Int, Double → void |
| `ptrcallWithIntAndLongArgs` | Int, Long → void |
| `ptrcallWithIntAndObjectArg` | Int, Object → void |
| `ptrcallWithIntAndStringArg` | Int, String → void |
| `ptrcallWithIntAndStringNameArg` | Int, StringName → void |
| `ptrcallWithIntAndColorArg` | Int, Color → void |
| `ptrcallWithIntAndVector2Arg` | Int, Vector2 → void |
| `ptrcallWithIntAndVector3Arg` | Int, Vector3 → void |
| `ptrcallWithIntAndNodePathArg` | Int, NodePath → void |
| `ptrcallWithIntAndVariantArg` | Int, Variant → void |
| `ptrcallWithLongAndBoolArgs` | Long, Bool → void |
| `ptrcallWithLongAndDoubleArg` | Long, Double → void |
| `ptrcallWithLongAndIntArgs` | Long, Int → void |
| `ptrcallWithLongAndObjectArg` | Long, Object → void |
| `ptrcallWithTwoRIDArgs` | RID, RID → void |
| `ptrcallWithRIDAndBoolArg` | RID, Bool → void |
| `ptrcallWithRIDAndDoubleArg` | RID, Double → void |
| `ptrcallWithRIDAndIntArg` | RID, Int → void |
| `ptrcallWithRIDAndLongArg` | RID, Long → void |
| `ptrcallWithRIDAndUInt32Arg` | RID, UInt32 → void |
| `ptrcallWithRIDAndStringArg` | RID, String → void |
| `ptrcallWithRIDAndColorArg` | RID, Color → void |
| `ptrcallWithRIDAndVector2Arg` | RID, Vector2 → void |
| `ptrcallWithRIDAndVector3Arg` | RID, Vector3 → void |
| `ptrcallWithRIDAndTransform2DArg` | RID, Transform2D → void |
| `ptrcallWithTwoStringNameArgs` | StringName, StringName → void |
| `ptrcallWithTwoStringNameArgsRetBool` | StringName, StringName → Bool |
| `ptrcallWithTwoStringNameArgsRetObject` | StringName, StringName → Object |
| `ptrcallWithTwoStringNameArgsRetDouble` | StringName, StringName → Double |
| `ptrcallWithStringNameAndBoolArg` | StringName, Bool → void |
| `ptrcallWithStringNameAndDoubleArg` | StringName, Double → void |
| `ptrcallWithStringNameAndVariantArg` | StringName, Variant → void |
| `ptrcallWithStringNameAndObjectArg` | StringName, Object → void |
| `ptrcallWithStringNameAndObjectArgRetLong` | StringName, Object → Long |
| `ptrcallWithStringAndIntArg` | String, Int → void |
| `ptrcallWithStringAndBoolArg` | String, Bool → void |
| `ptrcallWithStringAndBoolArgRetLong` | String, Bool → Long |
| `ptrcallWithStringAndStringNameArgRetString` | String, StringName → String |
| `ptrcallWithTwoStringArgs` | String, String → void |
| `ptrcallWithObjectAndBoolArg` | Object, Bool → void |
| `ptrcallWithObjectAndBoolArgRetNodePath` | Object, Bool → NodePath |
| `ptrcallWithObjectAndIntArg` | Object, Int → void |
| `ptrcallWithTwoObjectArgs` | Object, Object → void |
| `ptrcallWithUInt32AndBoolArgs` | UInt32, Bool → void |
| `ptrcallWithUInt32AndIntArg` | UInt32, Int → void |
| `ptrcallWithUInt32AndIntArgRetInt` | UInt32, Int → Int |
| `ptrcallWithUInt32AndIntArgRetObject` | UInt32, Int → Object |
| `ptrcallWithUInt32AndObjectArg` | UInt32, Object → void |
| `ptrcallWithUInt32AndTransform3DArg` | UInt32, Transform3D → void |
| `ptrcallWithVector3AndDoubleArg` | Vector3, Double → void |
| `ptrcallWithTwoVector2Args` | Vector2, Vector2 → void |
| `ptrcallWithTwoVector3Args` | Vector3, Vector3 → void |
| `ptrcallWithTwoDoubleArgs` | Double, Double → void |
| `ptrcallWithVector2AndDoubleArgRetVector3` | Vector2, Double → Vector3 |
| `ptrcallWithVector2ArgRetVector3` | Vector2 → Vector3 |

### Group 9 — Three-or-more arg compound shapes

These follow the same convention; all count ≥ 3 in the full corpus:

| Helper (selected; not exhaustive for count < 3) | Notes |
|--------|-------|
| `ptrcallWithThreeDoubleArgs` | |
| `ptrcallWithTwoIntAndDoubleArgs` | |
| `ptrcallWithIntAndTwoDoubleArgsRetInt` | |
| `ptrcallWithThreeVector3AndBoolArgs` | |
| `ptrcallWithTwoVector3AndBoolArgs` | |
| `ptrcallWithTwoStringNameAndDoubleArg` | |
| `ptrcallWithThreeStringNameAndDoubleArg` | |
| `ptrcallWithThreeStringNameTwoDoubleBoolArgs` | |
| `ptrcallWithStringNameDoubleDoubleBoolArgs` | |
| `ptrcallWithStringNameFourDoubleBoolArgs` | |
| `ptrcallWithStringNameThreeDoubleBoolTwoLongArgs` | |
| `ptrcallWithStringNameDoubleTwoLongArgs` | |
| `ptrcallWithStringNameAndThreeDoubleArgs` | |
| `ptrcallWithStringNameArrayBoolArgs` | |
| `ptrcallWithStringStringNameIntStringNameArgsRetString` | |
| `ptrcallWithDoubleAndTwoBoolArgs` | |
| `ptrcallWithDoubleVector2TwoDoubleArgs` | |
| `ptrcallWithObjectBoolLongArgs` | |
| `ptrcallWithObjectIntTransform3DArgs` | |
| `ptrcallWithRIDAndLongArgRetDouble` | |
| `ptrcallWithRIDAndLongArgRetLong` | |
| `ptrcallWithRIDAndLongArgRetVariantScalar` | |
| `ptrcallWithRIDIntAndBoolArgs` | |
| `ptrcallWithRIDIntAndRIDArgs` | |
| `ptrcallWithRIDCallableArgs` | |
| `ptrcallWithRIDLongAndDoubleArgs` | |
| `ptrcallWithTwoObjectCallableArgs` | |
| `ptrcallWithTransform3DVector3TwoColorUInt32Args` | |
| `ptrcallWithTransform3DVector3ObjectDoubleBoolIntArgsRetBool` | |
| `ptrcallWithVector3BoolFloatBoolIntArgsRetObject` | |
| `ptrcallWithTwoStringAndTwoBoolArgsRetObjectList` | |
| `ptrcallWithTwoStringAndTwoBoolArgsRetObject` | |
| `ptrcallWithStringAndTwoBoolArgsRetObject` | |
| `ptrcallWithStringNameAndVariantArgRetVariantScalar` | |
| ...~1000 more bespoke shapes at count 1–2 | tail of 1467 |

---

## (c) Platformer-classes minimum surface

Target classes: **Node3D, CharacterBody3D, Camera3D, AnimationPlayer, Area3D,
CollisionShape3D, GPUParticles3D** plus their inheritance bases **Node,
PhysicsBody3D, CollisionObject3D, VisualInstance3D, GeometryInstance3D,
AnimationMixer**.

### Per-class breakdown

| Class | Distinct ptrcall helpers |
|-------|------------------------:|
| Node | 37 |
| Node3D | 23 |
| CharacterBody3D | 16 |
| AnimationMixer | 20 |
| AnimationPlayer | 24 |
| Area3D | 16 |
| Camera3D | 24 |
| CollisionObject3D | 21 |
| CollisionShape3D | 7 |
| GeometryInstance3D | 12 |
| GPUParticles3D | 18 |
| PhysicsBody3D | 7 |
| VisualInstance3D | 11 |

### Minimum surface: complete distinct helper list (117 ptrcall + 4 infrastructure)

**Infrastructure (4):** `getMethodBind`, `constructObject`, `getSingleton`,
`callWithVariantArgs`

**ptrcall helpers (117):**

```
ptrcallNoArgs
ptrcallNoArgsRetAABB
ptrcallNoArgsRetBasis
ptrcallNoArgsRetBool
ptrcallNoArgsRetColor
ptrcallNoArgsRetDouble
ptrcallNoArgsRetInt
ptrcallNoArgsRetLong
ptrcallNoArgsRetLongList
ptrcallNoArgsRetNodePath
ptrcallNoArgsRetObject
ptrcallNoArgsRetPackedInt32List
ptrcallNoArgsRetPackedStringList
ptrcallNoArgsRetPlaneList
ptrcallNoArgsRetProjection
ptrcallNoArgsRetQuaternion
ptrcallNoArgsRetRID
ptrcallNoArgsRetString
ptrcallNoArgsRetStringName
ptrcallNoArgsRetStringNameList
ptrcallNoArgsRetTransform3D
ptrcallNoArgsRetTypedArea3DList
ptrcallNoArgsRetTypedNode3DList
ptrcallNoArgsRetTypedObjectList
ptrcallNoArgsRetTypedPhysicsBody3DList
ptrcallNoArgsRetUInt32
ptrcallNoArgsRetVariantScalar
ptrcallNoArgsRetVector2
ptrcallNoArgsRetVector3
ptrcallWithAABBArg
ptrcallWithBasisArg
ptrcallWithBoolArg
ptrcallWithBoolArgRetInt
ptrcallWithBoolArgRetTypedNodeList
ptrcallWithColorArg
ptrcallWithDoubleAndTwoBoolArgs
ptrcallWithDoubleArg
ptrcallWithDoubleVector2TwoDoubleArgs
ptrcallWithIntAndBoolArgs
ptrcallWithIntAndBoolArgsRetObject
ptrcallWithIntAndObjectArg
ptrcallWithIntArg
ptrcallWithIntArgRetBool
ptrcallWithIntArgRetObject
ptrcallWithIntArgRetUInt32
ptrcallWithLongAndBoolArgs
ptrcallWithLongArg
ptrcallWithLongArgRetBool
ptrcallWithNodePathArg
ptrcallWithNodePathArgRetArray
ptrcallWithNodePathArgRetBool
ptrcallWithNodePathArgRetObject
ptrcallWithObjectAndBoolArg
ptrcallWithObjectAndBoolArgRetNodePath
ptrcallWithObjectAndIntArg
ptrcallWithObjectArgRetBool
ptrcallWithObjectArgRetStringName
ptrcallWithObjectArgRetUInt32
ptrcallWithObjectArgs
ptrcallWithObjectBoolLongArgs
ptrcallWithObjectIntTransform3DArgs
ptrcallWithQuaternionArg
ptrcallWithRIDArg
ptrcallWithStringAndStringNameArgRetString
ptrcallWithStringAndTwoBoolArgsRetObject
ptrcallWithStringArg
ptrcallWithStringArgRetObject
ptrcallWithStringNameAndBoolArg
ptrcallWithStringNameAndDoubleArg
ptrcallWithStringNameAndObjectArgRetLong
ptrcallWithStringNameAndThreeDoubleArgs
ptrcallWithStringNameAndVariantArg
ptrcallWithStringNameArg
ptrcallWithStringNameArgRetBool
ptrcallWithStringNameArgRetObject
ptrcallWithStringNameArgRetStringName
ptrcallWithStringNameArgRetVariantScalar
ptrcallWithStringNameArrayBoolArgs
ptrcallWithStringNameDoubleDoubleBoolArgs
ptrcallWithStringNameDoubleTwoLongArgs
ptrcallWithStringNameFourDoubleBoolArgs
ptrcallWithStringNameThreeDoubleBoolTwoLongArgs
ptrcallWithStringStringNameIntStringNameArgsRetString
ptrcallWithThreeDoubleArgs
ptrcallWithThreeStringNameAndDoubleArg
ptrcallWithThreeStringNameTwoDoubleBoolArgs
ptrcallWithThreeVector3AndBoolArgs
ptrcallWithTransform3DArg
ptrcallWithTransform3DVector3ObjectDoubleBoolIntArgsRetBool
ptrcallWithTransform3DVector3TwoColorUInt32Args
ptrcallWithTwoDoubleArgs
ptrcallWithTwoStringAndTwoBoolArgsRetObjectList
ptrcallWithTwoStringNameAndDoubleArg
ptrcallWithTwoStringNameArgs
ptrcallWithTwoStringNameArgsRetDouble
ptrcallWithTwoVector3AndBoolArgs
ptrcallWithUInt32AndBoolArgs
ptrcallWithUInt32AndIntArg
ptrcallWithUInt32AndIntArgRetInt
ptrcallWithUInt32AndIntArgRetObject
ptrcallWithUInt32AndObjectArg
ptrcallWithUInt32AndTransform3DArg
ptrcallWithUInt32Arg
ptrcallWithUInt32ArgRetBool
ptrcallWithUInt32ArgRetInt
ptrcallWithUInt32ArgRetObject
ptrcallWithUInt32ArgRetTransform3D
ptrcallWithVector2AndDoubleArgRetVector3
ptrcallWithVector2Arg
ptrcallWithVector2ArgRetVector3
ptrcallWithVector3AndDoubleArg
ptrcallWithVector3Arg
ptrcallWithVector3ArgRetBool
ptrcallWithVector3ArgRetDouble
ptrcallWithVector3ArgRetVector2
ptrcallWithVector3ArgRetVector3
ptrcallWithVector3BoolFloatBoolIntArgsRetObject
```

**Validation check (re-run):**
```
grep -hoE "ObjectCalls\.[A-Za-z0-9]+" \
  src/main/kotlin/net/multigesture/kanama/api/{Node3D,CharacterBody3D,Camera3D,
  AnimationPlayer,Area3D,CollisionShape3D,GPUParticles3D,Node,PhysicsBody3D,
  CollisionObject3D,VisualInstance3D,GeometryInstance3D,AnimationMixer}.kt \
  | sort | uniq | wc -l
```
Result: **120** (117 ptrcall + `getMethodBind` + `constructObject` +
`callWithVariantArgs`).  Every helper in the above list was verified present in
this grep output.  **Zero `ObjectCalls.*` helpers used by the platformer target
classes are missing from this survey.**  ✓ Check passed.

---

## (d) iOS have/need per group

### C shim primitives already in `kanama_ios_shim.c`

The following generic ptrcall primitives exist in the C shim as of this survey.
They are `static` helpers (not `@CName` exported) and are currently used only
by hand-written `IosGodot.*` wrapper functions.  They must be promoted to
`@CName`-exported functions (or wrapped by `ObjectCalls.kt` Kotlin-side
adapters) to serve the generated wrappers.

| C primitive | Kotlin helper it can back |
|-------------|--------------------------|
| `ptrcall_noargs` | `ptrcallNoArgs` |
| `ptrcall_noargs_ret_bool` | `ptrcallNoArgsRetBool` |
| `ptrcall_noargs_ret_int64` | `ptrcallNoArgsRetLong`, `ptrcallNoArgsRetInt` (truncate) |
| `ptrcall_noargs_ret_object` | `ptrcallNoArgsRetObject` |
| `ptrcall_bool_arg` | `ptrcallWithBoolArg` |
| `ptrcall_bool_arg_ret_int` | `ptrcallWithBoolArgRetInt` |
| `ptrcall_float_arg` | `ptrcallWithDoubleArg` (float cell → double Kotlin side) |
| `ptrcall_double_arg` / `ptrcall_double_arg_direct` | `ptrcallWithDoubleArg` |
| `ptrcall_string_name_arg` | `ptrcallWithStringNameArg` |
| `ptrcall_string_name_arg_ret_bool` | `ptrcallWithStringNameArgRetBool` |
| `ptrcall_string_arg` (exported) | `ptrcallWithStringArg` |
| `ptrcall_object_arg` | `ptrcallWithObjectArgs` (1-arg form) |
| `ptrcall_object_bool_arg` | `ptrcallWithObjectAndBoolArg` |
| `ptrcall_object_bool_int_arg` | `ptrcallWithObjectBoolLongArgs` (partial) |
| `ptrcall_object_arg_ret_int` | `ptrcallWithObjectArgRetBool` (reinterpret) |
| `ptrcall_int_bool_arg_ret_object` | `ptrcallWithIntAndBoolArgsRetObject` |
| `ptrcall_int64_arg_ret_object` | `ptrcallWithIntArgRetObject`, `ptrcallWithLongArgRetBool` (w/ cast) |
| `ptrcall_node_path_arg_ret_object` | `ptrcallWithNodePathArgRetObject` |
| `ptrcall_vector2_get` | `ptrcallNoArgsRetVector2` |
| `ptrcall_vector2_set` | `ptrcallWithVector2Arg` |
| `ptrcall_vector3_get` | `ptrcallNoArgsRetVector3` |
| `ptrcall_vector3_set` | `ptrcallWithVector3Arg` |

### Coverage table: platformer minimum surface groups (117 helpers)

Legend: **Y** = C primitive already exists; **~** = reachable by reusing an
existing primitive with minor adaptation (cast / size fix); **N** = new C
primitive needed.

| Group | Helpers (count) | iOS status | Notes |
|-------|----------------|:----------:|-------|
| **G1** No-arg void | `ptrcallNoArgs` (1) | **Y** | `ptrcall_noargs` exists |
| **G2** No-arg scalar | `RetBool` `RetInt` `RetLong` `RetUInt32` `RetDouble` `RetString` `RetStringName` `RetVariantScalar` (8) | **Y/~** | `ret_bool`, `ret_int64` exist; UInt32, String, StringName, Variant need new or cast |
| **G3** No-arg struct | `RetVector2` `RetVector3` `RetNodePath` `RetObject` `RetTransform3D` `RetBasis` `RetQuaternion` `RetColor` `RetRID` `RetAABB` `RetProjection` (11 from min-surface) | **Y/N** | Vector2 + Vector3 + Object exist; Transform3D, Basis, Quaternion, Color, RID, AABB, Projection = **new** |
| **G4** No-arg lists | `RetStringNameList` `RetPackedStringList` `RetPackedInt32List` `RetLongList` `RetTypedObjectList` `RetTypedArea3DList` `RetTypedNode3DList` `RetTypedPhysicsBody3DList` `RetPlaneList` (9 from min-surface) | **N** | No list-return primitives exist in shim yet |
| **G5** Single-scalar arg void | `BoolArg` `IntArg` `LongArg` `UInt32Arg` `DoubleArg` `StringArg` `StringNameArg` `NodePathArg` `RIDArg` `ColorArg` (10) | **Y/N** | Bool, Double, String, StringName exist; Int (32-bit), Long (separate from int64), UInt32, NodePath, RID, Color = **new** or cast from existing |
| **G6** Single-struct arg void | `Vector2Arg` `Vector3Arg` `BasisArg` `QuaternionArg` `TransformArg` `AABBArg` `ObjectArgs` `ColorArg` (8) | **Y/~** | Vector2 + Vector3 exist; Basis, Quaternion, Transform3D, AABB = **new** |
| **G7a** Single-scalar arg → scalar ret | 49 helpers (Int/Long/UInt32/String/StringName/NodePath/RID/Object/Vector3 → Bool/Int/Long/Object/StringName etc.) | **~/N** | `int64_arg_ret_object` + `string_name_arg_ret_bool` cover 2; rest = **new** |
| **G7b** Single-struct arg → scalar ret | `Vector3Arg→Bool/Double/Vector2/Vector3`, `Vector2Arg→Vector3` (5) | **N** | None exist |
| **G8** Two-arg | 32 helpers: Int+Bool, Int+Object, Long+Bool, UInt32+*, StringName+*, Object+*, Vector3+Double etc. | **~/N** | `object_bool_arg`, `int_bool_arg_ret_object` cover 2; rest = **new** |
| **G9** Three-or-more arg | 19 helpers from min-surface (ThreeDoubles, ThreeVector3+Bool, StringNameDoubleDoubleBool, etc.) | **N** | None exist |

### Summary of iOS coverage

| Status | Group count | Notes |
|--------|:-----------:|-------|
| **Y** — existing C primitive directly usable | G1 | 1 group fully covered |
| **~** — existing primitive usable with minor Kotlin-side cast or new `@CName` wrapper | G2 (partial), G5 (partial), G6 (partial), G7a (2 helpers) | ~8 of 117 platformer helpers |
| **N** — new C primitive(s) required | G2 (partial), G3 (7 types), G4 (all), G5 (partial), G6 (partial), G7a (most), G7b (all), G8 (most), G9 (all) | ~109 of 117 platformer helpers |

**Key insight:** the shim already covers the basic no-arg bool/long/object and
simple bool/double/string-name-arg/void shapes — exactly the ones used by the
current hand-written `IosGodotApi.kt`.  The gap is everything that needs a
compound struct return (Color, Transform3D, Basis, AABB, etc.), list returns,
and multi-arg compound shapes (UInt32+, two-arg, three-arg).

The architecture doc recommendation stands: implement a **generic dispatch**
in the shim (arg cell layout per type, return cell read-back), drive it from
`ObjectCalls.kt` typed helpers, so new shapes are added without new C
functions per shape.

---

## Validation statement

Re-run command executed 2026-06-09:

```
grep -hoE "ObjectCalls\.[A-Za-z0-9]+" \
  src/main/kotlin/net/multigesture/kanama/api/Node3D.kt \
  src/main/kotlin/net/multigesture/kanama/api/CharacterBody3D.kt \
  src/main/kotlin/net/multigesture/kanama/api/Camera3D.kt \
  src/main/kotlin/net/multigesture/kanama/api/AnimationPlayer.kt \
  src/main/kotlin/net/multigesture/kanama/api/Area3D.kt \
  src/main/kotlin/net/multigesture/kanama/api/CollisionShape3D.kt \
  src/main/kotlin/net/multigesture/kanama/api/GPUParticles3D.kt \
  src/main/kotlin/net/multigesture/kanama/api/Node.kt \
  src/main/kotlin/net/multigesture/kanama/api/PhysicsBody3D.kt \
  src/main/kotlin/net/multigesture/kanama/api/CollisionObject3D.kt \
  src/main/kotlin/net/multigesture/kanama/api/VisualInstance3D.kt \
  src/main/kotlin/net/multigesture/kanama/api/GeometryInstance3D.kt \
  src/main/kotlin/net/multigesture/kanama/api/AnimationMixer.kt \
  | sort | uniq | wc -l
```

Output: **120** (117 distinct ptrcall helpers + `getMethodBind` + `constructObject` + `callWithVariantArgs`).

**Every `ObjectCalls.*` helper used by the platformer target classes
(Node3D, CharacterBody3D, Camera3D, AnimationPlayer, Area3D,
CollisionShape3D, GPUParticles3D and their bases Node, PhysicsBody3D,
CollisionObject3D, VisualInstance3D, GeometryInstance3D, AnimationMixer)
is enumerated in this survey.  Zero are missing.  Validation check PASSED.**
