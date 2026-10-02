#!/usr/bin/env python3
"""Gate: Godot enums surface as their value classes, `required` object returns are non-null, and the
generated enum names follow their rules (task 128 A).

Godot types 1,245 parameters and 982 returns as `enum::X` / `bitfield::X`; since task 128 every one
of them is a `@JvmInline value class` (`Node.ProcessMode`, `GodotError`) instead of a raw `Long`, and
the 40 object returns Godot marks `meta: "required"` are non-null. The generator does this, but a
hand edit, a hand-written per-platform class or a generator regression can put a `Long` back, and the
compiler cannot tell a raw `Long` from a forgotten enum. This gate reads the COMMITTED sources:

  (a) typed slots -- every Kotlin function under the api directories (shared, desktop, iOS; generated
      files and hand-written per-platform classes alike) is tied to its Godot method through the
      `getMethodBind("Class", "method", ...)` bind it calls, or -- for hand-written / bind-less
      wrappers (iOS IosGodot glue, delegating facades) -- through its owner class (enclosing class
      or extension receiver) and its name; each parameter whose Godot type is an enum/bitfield, and
      the return of the function named after the method, must use the value class, and an enum
      return is never nullable (builtin value-type enums included: `Vector3.Axis` nests in
      Kanama's `types.Vector3`). A property must have its getter's type.
  (b) required returns -- a function tied to a Godot method returning an object is non-null exactly
      when Godot marks the return `meta: "required"` (nullable otherwise), and a required one goes
      through the shared `requireGodotReturn` helper (or the Tween fluent `wrapOrThis` / iOS
      `releaseIosFluentSelf`, which call it, or a delegation to the same-named non-null member).
      Bind-less hand functions are held to the required rule only (an iOS sugar factory that
      constructs the object itself is not Godot's return).
  (c) names -- no generated top-level name equals a Kotlin default-import classifier (the `Error`
      that made Godot's `Error` `GodotError`) or a public Kanama type of a package scripts import
      (`net.multigesture.kanama.api` hand files, `.types`, `.annotations`).
  (d) prefix lock -- `scripts/enum_prefix_lock.json` covers every enum of the API, and every value
      emitted in a committed value class equals THE naming function (`godot_enum_model.
      enum_value_name`) applied with the locked prefix. (That the lock in git equals what the
      generator writes is the drift gate's job: the lock is one of its generated files.)

    python3 scripts/check_typed_enums.py
    python3 scripts/check_typed_enums.py --root /tmp/scratch   # red runs on a scratch copy
"""

from __future__ import annotations

import argparse
import json
import re
import sys
from dataclasses import dataclass
from pathlib import Path

TAG = "[typed_enums]"

# Classifiers Kotlin imports by default into every file (kotlin.*, kotlin.annotation.*,
# kotlin.collections.*, kotlin.comparisons.*, kotlin.io.*, kotlin.ranges.*, kotlin.sequences.*,
# kotlin.text.*, and kotlin.jvm.* on the JVM). Derived once from kotlin-stdlib 2.3.21 (the public
# top-level classes of those packages plus the common/JVM typealiases); a name a later stdlib adds is
# caught by the compiler on the file that collides, this list keeps the generator from choosing one.
KOTLIN_DEFAULT_IMPORT_NAMES = frozenset(
    """
    Annotation Any Array ArithmeticException AssertionError Boolean BooleanArray Byte ByteArray Char
    CharArray CharSequence ClassCastException Comparable Comparator ConcurrentModificationException
    DeepRecursiveFunction DeepRecursiveScope Deprecated DeprecatedSinceKotlin DeprecationLevel Double
    DoubleArray DslMarker Enum Error Exception ExperimentalMultiplatform ExperimentalStdlibApi
    ExperimentalSubclassOptIn ExperimentalUnsignedTypes ExtensionFunctionType Float FloatArray Function
    IllegalArgumentException IllegalStateException IndexOutOfBoundsException Int IntArray
    KotlinVersion Lazy LazyThreadSafetyMode Long LongArray NoSuchElementException NoWhenBranchMatchedException
    Nothing NotImplementedError NullPointerException Number NumberFormatException OptIn OptionalExpectation
    OverloadResolutionByLambdaReturnType Pair ParameterName PublishedApi ReplaceWith RequiresOptIn
    Result RuntimeException Short ShortArray String Suppress Throwable Throws Triple TypeCastException
    UByte UByteArray UInt UIntArray ULong ULongArray UShort UShortArray UninitializedPropertyAccessException
    Unit UnsafeVariance UnsupportedOperationException SubclassOptInRequired
    AnnotationRetention AnnotationTarget MustBeDocumented Repeatable Retention Target
    AbstractCollection AbstractIterator AbstractList AbstractMap AbstractMutableCollection
    AbstractMutableList AbstractMutableMap AbstractMutableSet AbstractSet ArrayDeque ArrayList
    BooleanIterator ByteIterator CharIterator Collection DoubleIterator FloatIterator Grouping HashMap
    HashSet IndexedValue IntIterator Iterable Iterator LinkedHashMap LinkedHashSet List ListIterator
    LongIterator Map MutableCollection MutableIterable MutableIterator MutableList MutableListIterator
    MutableMap MutableSet RandomAccess Set ShortIterator
    FileAlreadyExistsException FileSystemException FileWalkDirection NoSuchFileException AccessDeniedException
    OnErrorAction FileTreeWalk
    CharProgression CharRange ClosedFloatingPointRange ClosedRange IntProgression IntRange LongProgression
    LongRange OpenEndRange UIntProgression UIntRange ULongProgression ULongRange
    Sequence SequenceScope
    Appendable CharCategory CharDirectionality CharacterCodingException Charsets HexFormat MatchGroup
    MatchGroupCollection MatchNamedGroupCollection MatchResult Regex RegexOption StringBuilder Typography
    JvmField JvmInline JvmMultifileClass JvmName JvmOverloads JvmRecord JvmSerializableLambda JvmStatic
    JvmSuppressWildcards JvmWildcard JvmDefault JvmDefaultWithCompatibility JvmDefaultWithoutCompatibility
    Strictfp Synchronized Throws Transient Volatile PurelyImplements KotlinReflectionNotSupportedError
    """.split()
)


@dataclass(frozen=True)
class Fun:
    name: str
    receiver: str | None
    offset: int
    line: int
    params: list[tuple[str, str]]  # (name, type text)
    ret: str | None
    body: str


def blank_comments(src: str) -> str:
    """Blank // and /* */ comments in place (offsets and newlines survive; strings are kept)."""
    out = list(src)
    i, n = 0, len(src)
    in_string = False
    while i < n:
        c = src[i]
        if in_string:
            if c == "\\":
                i += 2
                continue
            if c == '"':
                in_string = False
            i += 1
            continue
        if c == '"':
            in_string = True
            i += 1
        elif src.startswith("//", i):
            j = src.find("\n", i)
            j = n if j == -1 else j
            for k in range(i, j):
                out[k] = " "
            i = j
        elif src.startswith("/*", i):
            j = src.find("*/", i + 2)
            j = n if j == -1 else j + 2
            for k in range(i, j):
                if out[k] != "\n":
                    out[k] = " "
            i = j
        else:
            i += 1
    return "".join(out)


def closer(src: str, start: int) -> int:
    pairs = {"(": ")", "{": "}", "<": ">"}
    opener, close = src[start], pairs[src[start]]
    depth = 0
    in_string = False
    for i in range(start, len(src)):
        c = src[i]
        if in_string:
            if c == '"' and src[i - 1] != "\\":
                in_string = False
            continue
        if c == '"':
            in_string = True
        elif c == opener:
            depth += 1
        elif c == close:
            depth -= 1
            if depth == 0:
                return i
    return len(src) - 1


def split_top(text: str) -> list[str]:
    parts, depth, cur = [], 0, []
    for c in text:
        if c in "(<[{":
            depth += 1
        elif c in ")>]}":
            depth -= 1
        if c == "," and depth == 0:
            parts.append("".join(cur))
            cur = []
        else:
            cur.append(c)
    if "".join(cur).strip():
        parts.append("".join(cur))
    return parts


FUN_RE = re.compile(r"\bfun\s+(?:<[^>]*>\s+)?(?:([\w.]+)\.)?(\w+)\s*\(")
BIND_RE = re.compile(r'(?:private\s+)?val\s+(\w+)\s+by\s+lazy\s*\{\s*ObjectCalls\.getMethodBind\(\s*"(\w+)"\s*,\s*"(\w+)"', re.S)
PROPERTY_RE = re.compile(r"\b(?:val|var)\s+(\w+)\s*:\s*([\w.<>?, ]+?)\s*\n(?:\s*@JvmName\([^)]*\)\s*\n)?\s*get\(\)\s*=\s*(\w+)\(")


def parse_functions(text: str) -> list[Fun]:
    funs: list[Fun] = []
    for m in FUN_RE.finditer(text):
        open_paren = m.end() - 1
        close_paren = closer(text, open_paren)
        params: list[tuple[str, str]] = []
        for part in split_top(text[open_paren + 1 : close_paren]):
            part = re.sub(r"^\s*(?:vararg\s+|@\w+(?:\([^)]*\))?\s+)*", "", part)
            if ":" not in part:
                continue
            name, rest = part.split(":", 1)
            params.append((name.strip(), split_default(rest).strip()))
        rest = text[close_paren + 1 :]
        head = re.match(r"\s*(?::\s*(?P<ret>[\w.<>?, ]+?))?\s*(?P<kind>=|\{|\n)", rest)
        ret = head.group("ret").strip() if head and head.group("ret") else None
        if head and head.group("kind") == "{":
            body_start = close_paren + 1 + head.end() - 1
            body = text[body_start : closer(text, body_start) + 1]
        else:
            end = text.find("\n\n", close_paren)
            body = text[close_paren + 1 : end if end != -1 else len(text)]
        funs.append(Fun(m.group(2), m.group(1), m.start(), text.count("\n", 0, m.start()) + 1, params, ret, body))
    return funs


def split_default(type_and_default: str) -> str:
    depth = 0
    for i, c in enumerate(type_and_default):
        if c in "(<[{":
            depth += 1
        elif c in ")>]}":
            depth -= 1
        elif c == "=" and depth == 0:
            return type_and_default[:i]
    return type_and_default


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.split("\n\n")[0])
    parser.add_argument("--root", type=Path, default=Path(__file__).resolve().parents[1])
    args = parser.parse_args()
    root: Path = args.root.resolve()
    sys.path.insert(0, str(root / "scripts"))
    from api_wrapper_candidates import camel_name  # noqa: E402
    import godot_enum_model as model  # noqa: E402

    api = json.loads((root / "extension_api.json").read_text(encoding="utf-8"))
    classes = {c["name"]: c for c in api["classes"]}
    enums = model.load_enums(root / "extension_api.json")
    lock = model.read_lock(root / "scripts/enum_prefix_lock.json")
    api_dirs = [
        root / "src/commonMain/kotlin/net/multigesture/kanama/api",
        root / "src/jvmMain/kotlin/net/multigesture/kanama/api",
        root / "src/iosMain/kotlin/net/multigesture/kanama/api",
    ]
    files = sorted(p for d in api_dirs for p in d.glob("*.kt"))
    types_dir = root / "src/commonMain/kotlin/net/multigesture/kanama/types"
    value_files = sorted([*files, *types_dir.glob("*.kt")])
    failures: list[str] = []
    shadowed = {s.simple_name for s in enums.values() if s.owner is None} & {s.name for s in enums.values() if s.owner}

    def expected_types(type_name: str) -> set[str] | None:
        key = model.enum_key_of_type(type_name)
        if key is None:
            return None
        spec = enums[key]
        names = {spec.kotlin_name, f"{model.API_PACKAGE}.{spec.kotlin_name}"}
        if spec.owner is not None:
            names.add(spec.name)  # inside the owner's own body
        elif spec.simple_name in shadowed:
            names = {f"{model.API_PACKAGE}.{spec.simple_name}"}
        return names

    def methods_of(cls: str, name: str) -> list[dict]:
        found: list[dict] = []
        cursor: str | None = cls
        while cursor:
            found += [m for m in classes[cursor].get("methods", []) if m["name"] == name]
            if found:
                return found
            cursor = classes[cursor].get("inherits")
        return found

    # Kotlin owner names that are not their Godot class's name.
    kotlin_to_godot = {"GodotObject": "Object", "FileAccessHandle": "FileAccess", "DirAccessHandle": "DirAccess"}

    def godot_method_named(cls: str, kotlin_name: str) -> str | None:
        """The Godot method of [cls] or an ancestor whose wrapper name is [kotlin_name]."""
        cursor: str | None = cls
        while cursor and cursor in classes:
            for m in classes[cursor].get("methods", []):
                if not m.get("is_virtual") and camel_name(m["name"]) == kotlin_name:
                    return m["name"]
            cursor = classes[cursor].get("inherits")
        return None

    top_decl_re = re.compile(r"(?m)^(?:(?:public|internal|private|actual|expect|open|abstract|sealed|data)\s+)*(?:class|object)\s+(\w+)")
    checked_slots = checked_required = checked_nullable = checked_bindless = 0
    required_methods: set[tuple[str, str]] = set()
    for path in files:
        raw = path.read_text(encoding="utf-8")
        text = blank_comments(raw)
        rel = path.relative_to(root)
        binds = {m.group(1): (m.group(2), m.group(3)) for m in BIND_RE.finditer(text)}
        owners = [(m.start(), m.group(1)) for m in top_decl_re.finditer(text)]
        fun_returns: dict[str, str] = {}
        for fun in parse_functions(text):
            used = {binds[b] for b in re.findall(r"\b(\w+Bind)\b", fun.body) if b in binds}
            bindless = len(used) != 1
            if not bindless:
                godot_class, godot_method = used.pop()
            else:
                # Hand-written / bind-less wrappers (IosGodot glue, delegating facades): tie the
                # function to its owner's Godot method of the same wrapper name.
                owner = (fun.receiver or "").removesuffix(".Companion") or None
                if owner is None:
                    enclosing = [name for start, name in owners if start < fun.offset]
                    owner = enclosing[-1] if enclosing else None
                godot_class = kotlin_to_godot.get(owner or "", owner or "")
                godot_method = godot_method_named(godot_class, fun.name) if godot_class in classes else None
                if godot_method is None:
                    continue
                checked_bindless += 1
            if godot_class not in classes:
                continue
            candidates = methods_of(godot_class, godot_method)
            if not candidates:
                continue
            method = candidates[0]
            named_after = fun.name == camel_name(godot_method).removeprefix("_") or fun.name.startswith(
                camel_name(godot_method).removeprefix("_")
            )
            arg_types = {camel_name(a["name"]): a["type"] for a in method.get("arguments", [])}
            for pname, ptype in fun.params:
                gtype = arg_types.get(pname) or arg_types.get(pname.removesuffix("Value"))
                if gtype is None:
                    continue
                want = expected_types(gtype)
                if want is None:
                    continue
                checked_slots += 1
                if ptype.rstrip("?") not in want:
                    failures.append(
                        f"{rel}:{fun.line}: {fun.name}({pname}: {ptype}) -- Godot {godot_class}.{godot_method} "
                        f"types `{pname}` {gtype}; expected {' / '.join(sorted(want))}"
                    )
            ret_value = method.get("return_value") or {}
            gret = ret_value.get("type", "void")
            if fun.name == camel_name(godot_method).removeprefix("_") and fun.ret is not None:
                fun_returns[fun.name] = fun.ret
                want = expected_types(gret)
                if want is not None:
                    checked_slots += 1
                    if fun.ret.endswith("?"):
                        failures.append(
                            f"{rel}:{fun.line}: {fun.name}(): {fun.ret} -- Godot {godot_class}.{godot_method} returns "
                            f"{gret}, never null: the enum return must not be nullable"
                        )
                    elif fun.ret not in want:
                        failures.append(
                            f"{rel}:{fun.line}: {fun.name}(): {fun.ret} -- Godot {godot_class}.{godot_method} "
                            f"returns {gret}; expected {' / '.join(sorted(want))}"
                        )
                elif (gret in classes or gret == "Object") and fun.ret.rstrip("?") != "GodotHandle":
                    # A raw `GodotHandle` return (iOS Engine.getMainLoop) is not a wrapper: no null policy.
                    required = ret_value.get("meta") == "required"
                    nullable = fun.ret.endswith("?")
                    if required:
                        checked_required += 1
                        required_methods.add((godot_class, godot_method))
                        if nullable:
                            failures.append(
                                f"{rel}:{fun.line}: {fun.name}(): {fun.ret} -- Godot marks {godot_class}.{godot_method} "
                                "`meta: \"required\"`: the return must be non-null (decision 9)"
                            )
                        elif not re.search(
                            rf"requireGodotReturn|wrapOrThis|releaseIosFluentSelf|return this\b|\.{fun.name}\(", fun.body
                        ):
                            # (a delegation to the same-named non-null member counts: SceneTree.Companion.createTween)
                            failures.append(
                                f"{rel}:{fun.line}: {fun.name}() -- required return of {godot_class}.{godot_method} does "
                                "not go through requireGodotReturn (a null must throw, never pass silently)"
                            )
                    elif named_after and not bindless:
                        # Bind-less hand functions of the same name (iOS sugar factories that construct
                        # the object themselves) are not Godot's return; the nullable rule is for wrappers.
                        checked_nullable += 1
                        if not nullable:
                            failures.append(
                                f"{rel}:{fun.line}: {fun.name}(): {fun.ret} -- {godot_class}.{godot_method} is not "
                                "`meta: \"required\"`: the object return must stay nullable"
                            )
        for m in PROPERTY_RE.finditer(text):
            prop, ptype, getter = m.group(1), m.group(2).strip(), m.group(3)
            if getter in fun_returns and fun_returns[getter] != ptype:
                line = text.count("\n", 0, m.start()) + 1
                failures.append(f"{rel}:{line}: property {prop}: {ptype} -- its getter {getter}() returns {fun_returns[getter]}")

    # (c) generated top-level names.
    public_kanama: dict[str, str] = {}
    decl_re = re.compile(
        r"(?m)^(?:@[\w.]+(?:\([^)]*\))?\s+)*(?:(?:public|actual|expect|open|abstract|data|sealed|enum|annotation|value|fun)\s+)*"
        r"(?:class|object|interface|typealias)\s+(\w+)"
    )
    generated_names: dict[str, str] = {}
    global_file = root / "src/commonMain/kotlin/net/multigesture/kanama/api/GlobalEnums.kt"
    for m in decl_re.finditer(blank_comments(global_file.read_text(encoding="utf-8"))):
        generated_names[m.group(1)] = str(global_file.relative_to(root))
    for package_dir in (
        root / "src/commonMain/kotlin/net/multigesture/kanama/types",
        root / "annotations/src/main/kotlin/net/multigesture/kanama/annotations",
    ):
        for path in package_dir.rglob("*.kt"):
            for m in decl_re.finditer(blank_comments(path.read_text(encoding="utf-8"))):
                if not re.search(r"\b(?:internal|private)\b", m.group(0)):
                    public_kanama.setdefault(m.group(1), str(path.relative_to(root)))
    hand_api = [
        p
        for d in api_dirs
        for p in d.glob("*.kt")
        if p.name not in ("GlobalEnums.kt",) and "GENERATED" not in p.read_text(encoding="utf-8")[:600]
        and not p.name.endswith((".jvm.kt", ".ios.kt", ".expect.kt"))
        and p.stem not in classes
    ]
    for path in hand_api:
        for m in decl_re.finditer(blank_comments(path.read_text(encoding="utf-8"))):
            if not m.group(0).startswith((" ", "\t")) and not re.search(r"\b(?:internal|private)\b", m.group(0)):
                public_kanama.setdefault(m.group(1), str(path.relative_to(root)))
    for name, where in sorted(generated_names.items()):
        if name in KOTLIN_DEFAULT_IMPORT_NAMES:
            failures.append(
                f"{where}: generated top-level `{name}` equals a Kotlin default-import classifier (kotlin.{name}); "
                "rename it in godot_enum_model.GLOBAL_ENUM_RENAMES (decision 1: Error -> GodotError)"
            )
        elif name in public_kanama:
            failures.append(f"{where}: generated top-level `{name}` equals the public Kanama type in {public_kanama[name]}")

    # (d) prefix lock coverage and value names.
    missing = sorted(set(enums) - set(lock))
    if missing:
        failures.append(f"scripts/enum_prefix_lock.json: {len(missing)} enum(s) have no frozen prefix: {missing[:10]}")
    value_class_re = re.compile(r"\bvalue\s+class\s+(\w+)")
    value_re = re.compile(
        r"^\s*(?:actual\s+)?val\s+([A-Z][A-Z0-9_]*)\s*:\s*(\w+)(?:\s*get\(\)\s*=\s*(\w+)\((-?\d+)L\))?\s*$"
    )
    by_owner: dict[tuple[str | None, str], model.EnumSpec] = {(s.kotlin_owner, s.simple_name): s for s in enums.values()}
    checked_values = 0
    for path in value_files:
        text = blank_comments(path.read_text(encoding="utf-8"))
        rel = path.relative_to(root)
        decls = [
            (m.start(), m.group(1))
            for m in re.finditer(r"(?m)^(?:(?:public|actual|expect|open|abstract|sealed|data)\s+)*(?:class|object)\s+(\w+)", text)
        ]
        current: model.EnumSpec | None = None
        for lineno, line in enumerate(text.splitlines(), 1):
            vc = value_class_re.search(line)
            if vc:
                offset = sum(len(l) + 1 for l in text.splitlines()[: lineno - 1])
                owner = None
                for start, name in decls:
                    if start <= offset:
                        owner = name
                owner_key = None if path.name == "GlobalEnums.kt" else owner
                current = by_owner.get((owner_key, vc.group(1)))
                continue
            if current is None:
                continue
            vm = value_re.match(line)
            if not vm:
                continue
            type_name = vm.group(2) or vm.group(3)
            if type_name != current.simple_name:
                continue
            checked_values += 1
            allowed = {model.enum_value_name(lock.get(current.key, ""), v.name) for v in current.values}
            if vm.group(1) not in allowed:
                failures.append(
                    f"{rel}:{lineno}: {current.kotlin_name}.{vm.group(1)} is not a value name the naming function gives "
                    f"under the locked prefix {lock.get(current.key)!r} (expected one of {sorted(allowed)[:6]}...)"
                )

    if failures:
        print(f"{TAG} FAIL {len(failures)} problem(s):", file=sys.stderr)
        for failure in failures[:80]:
            print(f"    {failure}", file=sys.stderr)
        return 1
    print(
        f"{TAG} PASS {checked_slots} enum/bitfield slots typed ({checked_bindless} functions tied by name, "
        f"not by MethodBind), {len(required_methods)} required Godot object returns non-null "
        f"({checked_required} per-platform functions), "
        f"{checked_nullable} other object returns nullable, {len(generated_names)} global enum names clear of "
        f"{len(KOTLIN_DEFAULT_IMPORT_NAMES)} Kotlin default imports + {len(public_kanama)} public Kanama types, "
        f"{len(lock)} locked prefixes cover {len(enums)} enums, {checked_values} emitted value names match "
        f"({len(files)} api files)"
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
