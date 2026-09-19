#!/usr/bin/env python3
"""Hand-shaped wrapper parity gate (task 117, parcel P0).

The remaining hand-shaped wrapper classes (HAND_SHAPED below; the count shrinks as task 117 P1' retires them) exist twice — `src/jvmMain/.../api/<Class>.kt` and
`src/iosMain/.../api/<Class>.kt` (three live inside `IosGodotApi.kt`) — and the generated wrapper
tree extends them. Until task 117 turns them into `expect`/`actual` (which needs identical public
shapes), this gate is the contract: it parses both copies and reports every difference in

  supertype, modality, primary constructor (visibility + parameters), class-body members
  (one-sided names, overload counts, return types, visibility, modifiers, parameter names,
  arity), companion members (one-sided), nested objects (one-sided), @JvmName/@JvmStatic counts.

Every finding must be listed in `scripts/wrapper_parity_allowlist.txt` with a reason, and an
allowlist line that no longer matches a finding FAILS the gate — so the list can only shrink as
parcel P1 reconciles the shapes (decisions D1–D8 in kanama-tasks/117-…md). The gate reads the
sources directly (no build), so it also runs in local_ci.

    python3 scripts/check_wrapper_parity.py                 # PASS/FAIL against the allowlist
    python3 scripts/check_wrapper_parity.py --write-allowlist "P1 reconciles (task 117)"
                                                            # seed/refresh the allowlist, keeping existing reasons
    python3 scripts/check_wrapper_parity.py --allowlist /tmp/x.txt   # (tests) another allowlist

Parsing is textual (comments and string literals blanked, brace tracking); it was built for the
task-117 measurement and validated against those numbers (147 one-sided members, 15 divergences,
one supertype mismatch). A parser miss shows up as a finding, never as silence.
"""
from __future__ import annotations

import argparse
import collections
import os
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
JVM_API = ROOT / "src/jvmMain/kotlin/net/multigesture/kanama/api"
IOS_API = ROOT / "src/iosMain/kotlin/net/multigesture/kanama/api"
ALLOWLIST = ROOT / "scripts/wrapper_parity_allowlist.txt"
TAG = "[wrapper_parity]"

# The classes the generated tree (src/sharedApi) extends or calls that are NOT part of that shared
# tree: each exists as a separate per-platform file. Provenance is read from the generator's
# PER_PLATFORM_WRAPPERS at run time and printed in the PASS line (GodotObject/GodotCallable are
# hand-written roots outside the table). The gate compares the two committed files regardless of who wrote them; a
# regenerated file that changes shape shows up here like any other change and P1 decides. Every
# name except the two roots must appear in the generator's PER_PLATFORM_WRAPPERS (checked below).
HAND_SHAPED = [
    "GodotObject", "Node", "RefCounted", "Resource", "GodotCallable", "Image",
    "Node3D", "Button", "Light3D",
    "LineEdit", "Range", "MeshLibrary", "Camera3D", "ButtonGroup", "Tweener",
    "StandardMaterial3D", "Viewport", "TabBar", "Slider", "AnimationPlayer", "StaticBody3D",
    "PlaneMesh", "BaseMaterial3D",
]

# ---------------------------------------------------------------------------------------------
# Kotlin source parsing (textual; comments + strings blanked so braces and keywords are honest)


def strip_noise(src: str) -> str:
    out = list(src)
    i = 0
    n = len(src)
    while i < n:
        c = src[i]
        if c == "/" and i + 1 < n and src[i + 1] == "/":
            j = src.find("\n", i)
            if j == -1:
                j = n
            for k in range(i, j):
                out[k] = " "
            i = j
        elif c == "/" and i + 1 < n and src[i + 1] == "*":
            depth = 1
            j = i + 2
            while j < n and depth > 0:
                if src[j] == "/" and j + 1 < n and src[j + 1] == "*":
                    depth += 1
                    j += 2
                elif src[j] == "*" and j + 1 < n and src[j + 1] == "/":
                    depth -= 1
                    j += 2
                else:
                    j += 1
            for k in range(i, min(j, n)):
                if out[k] != "\n":
                    out[k] = " "
            i = j
        elif src.startswith('"""', i):
            j = src.find('"""', i + 3)
            j = n if j == -1 else j + 3
            for k in range(i, j):
                if out[k] != "\n":
                    out[k] = " "
            i = j
        elif c in "\"'":
            q = c
            j = i + 1
            while j < n:
                if src[j] == "\\":
                    j += 2
                    continue
                if src[j] == q:
                    j += 1
                    break
                if src[j] == "\n":
                    break
                j += 1
            for k in range(i, min(j, n)):
                if out[k] != "\n":
                    out[k] = " "
            i = j
        else:
            i += 1
    return "".join(out)


class ParseError(Exception):
    """The textual parser lost its footing; reported as a `parse-error` finding, never as silence."""


def match_close(clean: str, start: int, open_ch: str = "{", close_ch: str = "}") -> int:
    depth = 0
    for i in range(start, len(clean)):
        ch = clean[i]
        if ch == open_ch:
            depth += 1
        elif ch == close_ch:
            if close_ch == ">" and i > 0 and clean[i - 1] == "-":
                continue  # the `->` of a function type is not a closing angle bracket
            depth -= 1
            if depth == 0:
                return i
    raise ParseError(f"unbalanced {open_ch}{close_ch} from offset {start}")


def split_top_level(s: str) -> list[str]:
    """Split on top-level commas; `->` does not close an angle bracket."""
    parts: list[str] = []
    depth = 0
    cur = ""
    prev = ""
    for ch in s:
        if ch in "([<{":
            depth += 1
        elif ch in ")]>}" and not (ch == ">" and prev == "-"):
            depth -= 1
        if ch == "," and depth == 0:
            parts.append(cur)
            cur = ""
        else:
            cur += ch
        prev = ch
    if cur.strip():
        parts.append(cur)
    return parts


TYPE_MODS = r"(?:public|private|internal|protected|open|abstract|sealed|final|data|value|enum|annotation|expect|actual|inner|fun)"


def find_types(clean: str, src: str) -> list[dict]:
    """Top-level (column 0) type declarations with header text and body span."""
    res = []
    for m in re.finditer(r"(?m)^((?:" + TYPE_MODS + r"\s+)*)(class|object|interface)\s+([A-Za-z_][A-Za-z0-9_]*)", clean):
        mods = m.group(1).split()
        kind = m.group(2)
        name = m.group(3)
        i = m.end()
        depth = 0
        body_start = -1
        j = i
        while j < len(clean):
            ch = clean[j]
            if ch in "(<[":
                depth += 1
            elif ch in ")>]":
                depth -= 1
            elif ch == "{" and depth <= 0:
                body_start = j
                break
            elif ch == "\n" and depth <= 0:
                before = clean[max(0, j - 300) : j].rstrip()
                if before.endswith((":", ",", "(", "by", "=", "<", "where")):
                    j += 1
                    continue
                k = j + 1
                while k < len(clean) and clean[k] in " \t\n":
                    k += 1
                if k < len(clean) and clean[k] == "{":
                    body_start = k
                    break
                if k < len(clean) and clean[k] not in " \t":
                    body_start = -1
                    break
                j += 1
                continue
            j += 1
        header_end = body_start if body_start != -1 else min(j, len(clean))
        if body_start == -1:
            # Body-less declarations (`data class GodotCallable(val target: GodotObject, ...)`) are
            # legal; a header whose continuation the scan did not follow is not. If a `{` appears
            # before the next column-0 declaration, the layout beat the parser: say so.
            nxt = re.compile(r"(?m)^\S").search(clean, header_end + 1)
            tail = clean[header_end : nxt.start() if nxt else len(clean)]
            if "{" in tail:
                raise ParseError(f"header layout not understood for `{name}`: `{re.sub(r'\s+', ' ', src[m.start():header_end])[:80]}`")
        body_end = match_close(clean, body_start) if body_start != -1 else -1
        res.append(
            dict(
                name=name,
                kind=kind,
                mods=mods,
                start=m.start(),
                header=re.sub(r"\s+", " ", src[m.start() : header_end]).strip(),
                body_start=body_start,
                body_end=body_end,
            )
        )
    return res


MEMBER_MODS = r"(?:public|private|internal|protected|open|abstract|sealed|final|override|lateinit|const|inline|operator|infix|suspend|tailrec|external|actual|expect|data|value|enum|annotation|inner|companion)"
DECL_RE = re.compile(r"^((?:" + MEMBER_MODS + r"\s+)*)(fun|val|var|constructor|init|class|object|interface|typealias)\b")
NAME_RE = re.compile(r"\s*((?:[A-Za-z_][\w.]*(?:<[^>]*>)?\??\.)?)([A-Za-z_][\w]*|`[^`]+`)")


def parse_members(clean: str, src: str, body_start: int, body_end: int) -> list[dict]:
    """Direct members of a class body (depth-0 declarations), with annotations captured."""
    members = []
    pos = body_start + 1
    depth = 0
    end = body_end
    line_start = True
    while pos < end:
        ch = clean[pos]
        if ch in "{([":
            depth += 1
            pos += 1
            line_start = False
            continue
        if ch in "})]":
            depth -= 1
            pos += 1
            line_start = False
            continue
        if ch == "\n":
            line_start = True
            pos += 1
            continue
        if ch in " \t":
            pos += 1
            continue
        if depth == 0 and line_start:
            p = pos
            annotations = []
            while True:
                am = re.match(r"@[A-Za-z_][\w.:]*", clean[p:])
                if not am:
                    break
                annotations.append(am.group(0))
                p2 = p + am.end()
                if p2 < end and clean[p2] == "(":
                    p2 = match_close(clean, p2, "(", ")") + 1
                while p2 < end and clean[p2] in " \t\n":
                    p2 += 1
                p = p2
            m = DECL_RE.match(clean[p:end])
            if m:
                mods = m.group(1).split()
                kw = m.group(2)
                q = p + m.end()
                rec = dict(kw=kw, mods=mods, pos=pos, annotations=annotations)
                if kw == "fun":
                    r = q
                    while r < end and clean[r] in " \t\n":
                        r += 1
                    if r < end and clean[r] == "<":
                        r = match_close(clean, r, "<", ">") + 1
                    nm = NAME_RE.match(clean[r:end])
                    if nm:
                        rec["receiver"] = nm.group(1).rstrip(".")
                        rec["name"] = nm.group(2).strip("`")
                        pp = r + nm.end()
                        while pp < end and clean[pp] in " \t\n":
                            pp += 1
                        if pp < end and clean[pp] == "(":
                            pe = match_close(clean, pp, "(", ")")
                            rec["params_clean"] = clean[pp : pe + 1]
                            rt = clean[pe + 1 : min(pe + 300, end)]
                            rtm = re.match(r"\s*:\s*([^=\n{]+)", rt)
                            rec["ret"] = rtm.group(1).strip() if rtm else None
                            q = pe + 1
                        else:
                            rec["params_clean"] = "()"
                            rec["ret"] = None
                elif kw in ("val", "var"):
                    r = q
                    while r < end and clean[r] in " \t\n":
                        r += 1
                    if r < end and clean[r] == "<":
                        r = match_close(clean, r, "<", ">") + 1
                    nm = NAME_RE.match(clean[r:end])
                    if nm:
                        rec["receiver"] = nm.group(1).rstrip(".")
                        rec["name"] = nm.group(2).strip("`")
                        rest = clean[r + nm.end() : min(r + nm.end() + 300, end)]
                        rtm = re.match(r"\s*:\s*([^=\n{]+)", rest)
                        rec["ret"] = rtm.group(1).strip() if rtm else None
                        rec["params_clean"] = ""
                elif kw == "constructor":
                    rec["name"] = "<init>"
                    r = q
                    while r < end and clean[r] in " \t\n":
                        r += 1
                    if r < end and clean[r] == "(":
                        pe = match_close(clean, r, "(", ")")
                        rec["params_clean"] = clean[r : pe + 1]
                        q = pe + 1
                elif kw in ("class", "object", "interface"):
                    if "companion" in mods:
                        rec["name"] = "Companion"
                        cb = clean.find("{", q)
                        if cb != -1 and cb < end:
                            rec["companion_body"] = (cb, match_close(clean, cb))
                    else:
                        nm = re.match(r"\s*([A-Za-z_][\w]*)", clean[q:end])
                        rec["name"] = nm.group(1) if nm else "?"
                    rec["nested"] = True
                elif kw == "init":
                    rec["name"] = "<init-block>"
                elif kw == "typealias":
                    nm = re.match(r"\s*([A-Za-z_][\w]*)", clean[q:end])
                    rec["name"] = nm.group(1) if nm else "?"
                if "name" in rec:
                    members.append(rec)
                pos = max(q, pos + 1)
                line_start = False
                continue
        pos += 1
        line_start = False
    if depth != 0:
        raise ParseError(f"class body ended at bracket depth {depth} (a string template or comment desynced the scan)")
    return members


def param_names(params_clean: str) -> list[str]:
    if not params_clean:
        return []
    s = params_clean.strip()
    if s.startswith("("):
        s = s[1:-1]
    out = []
    for p in split_top_level(s):
        p = p.strip()
        if not p:
            continue
        p = re.sub(r"^(?:@[\w.]+(?:\([^)]*\))?\s*)+", "", p)
        for _ in range(2):
            p = re.sub(r"^(?:vararg|noinline|crossinline|val|var|private|public|internal|protected|override)\s+", "", p)
        m = re.match(r"([A-Za-z_][\w]*|`[^`]+`)\s*:", p)
        out.append(m.group(1).strip("`") if m else p.split(":")[0].strip())
    return out


def param_types(params_clean: str) -> str:
    """Parameter list reduced to its types (names and defaults dropped), for overload pairing."""
    if not params_clean:
        return ""
    s = params_clean.strip()
    if s.startswith("("):
        s = s[1:-1]
    types = []
    for p in split_top_level(s):
        p = re.sub(r"=.*$", "", p.strip())
        p = re.sub(r"^(?:@[\w.]+(?:\([^)]*\))?\s*)+", "", p)
        p = re.sub(r"^(?:vararg\s+)", "", p)
        t = p.split(":", 1)[1] if ":" in p else p
        types.append(norm(t))
    return ",".join(types)


def visibility(mods: list[str]) -> str:
    for v in ("private", "internal", "protected", "public"):
        if v in mods:
            return v
    return "public"


def header_parse(header: str) -> tuple[str | None, str, list[str]]:
    """(primary constructor params, constructor visibility, supertypes) from a class header."""
    m = re.match(r"^((?:\w+\s+)*)(class|object|interface)\s+(\w+)\s*(<[^>]*>)?\s*", header)
    tail = header[m.end() :] if m else header
    cm = re.match(r"^((?:@[\w.]+\s*)*)((?:private|internal|protected|public)\s+)?(constructor\s*)?", tail)
    tail2 = tail[cm.end() :]
    ctorvis = (cm.group(2) or "").strip() or "public"
    ctor = None
    if tail2.startswith("("):
        depth = 0
        for k, ch in enumerate(tail2):
            if ch == "(":
                depth += 1
            elif ch == ")":
                depth -= 1
                if depth == 0:
                    ctor = tail2[: k + 1]
                    tail2 = tail2[k + 1 :]
                    break
    supers: list[str] = []
    si = tail2.find(":")
    if si != -1:
        supers = [x.strip() for x in split_top_level(tail2[si + 1 :])]
    return ctor, ctorvis, [re.sub(r"\(.*", "", s).strip() for s in supers]


def scan(root: Path) -> dict[str, dict]:
    idx: dict[str, dict] = {}
    for path in sorted(root.rglob("*.kt")):
        src = path.read_text(encoding="utf-8")
        clean = strip_noise(src)
        for t in find_types(clean, src):
            idx.setdefault(t["name"], dict(path=path, t=t, clean=clean, src=src))
    return idx


def analyze(entry: dict) -> dict:
    t = entry["t"]
    clean = entry["clean"]
    src = entry["src"]
    ctor, ctorvis, supers = header_parse(t["header"])
    res = dict(path=entry["path"].relative_to(ROOT).as_posix(), mods=t["mods"], ctor=ctor, ctorvis=ctorvis, supers=supers)
    members: list[dict] = []
    companion: list[dict] = []
    for m in (parse_members(clean, src, t["body_start"], t["body_end"]) if t["body_start"] != -1 else []):
        if m.get("nested") and m["name"] == "Companion" and "companion_body" in m:
            cb, ce = m["companion_body"]
            companion.extend(parse_members(clean, src, cb, ce))
        members.append(m)
    res["members"] = members
    res["companion"] = companion
    return res


# ---------------------------------------------------------------------------------------------
# Comparison


def norm(t: str | None) -> str:
    """Whitespace- and trailing-comma-insensitive (ktfmt writes `( handle: GodotHandle, )`), and
    `RawSegment` == `MemorySegment` (the common seam is a typealias of the platform type; the
    hand-written files say RawSegment since task 117 P0, generated files still say MemorySegment)."""
    t = re.sub(r"\bMemorySegment\.NULL\b", "NULL_SEGMENT", t or "")
    t = re.sub(r"\bMemorySegment\b", "RawSegment", t)
    return re.sub(r",\)", ")", re.sub(r"\s+", "", t))


MODIFIER_SET = ("open", "abstract", "override", "final", "const", "lateinit")


def compare(cls: str, j: dict, i: dict) -> list[tuple[str, str, str]]:
    """Findings as (category, member, detail) for one class."""
    out: list[tuple[str, str, str]] = []
    js, isup = sorted(j["supers"]), sorted(i["supers"])
    if js != isup:
        out.append(("supertype", "*", f"desktop `: {', '.join(js) or '(none)'}` vs ios `: {', '.join(isup) or '(none)'}`"))
    jm = sorted(x for x in j["mods"] if x in ("open", "abstract", "sealed", "final", "data", "value"))
    im = sorted(x for x in i["mods"] if x in ("open", "abstract", "sealed", "final", "data", "value"))
    if jm != im:
        out.append(("modality", "*", f"desktop {jm} vs ios {im}"))
    if j["ctorvis"] != i["ctorvis"]:
        out.append(("constructor", "<init>", f"visibility desktop {j['ctorvis']} vs ios {i['ctorvis']}"))
    if norm(j["ctor"]) != norm(i["ctor"]):
        out.append(("constructor", "<init>", f"params desktop `{j['ctor']}` vs ios `{i['ctor']}`"))

    def body(rec: dict) -> dict[str, list[dict]]:
        # Keyed by `Receiver.name` so member extension functions are compared too.
        d: dict[str, list[dict]] = collections.defaultdict(list)
        for m in rec["members"]:
            if m["kw"] in ("fun", "val", "var", "constructor"):
                key = f"{m['receiver']}.{m['name']}" if m.get("receiver") else m["name"]
                d[key].append(m)
        return d

    jb, ib = body(j), body(i)
    for name in sorted(set(jb) - set(ib)):
        out.append(("desktop-only", name, ", ".join(f"{m['kw']}{m.get('params_clean', '')}" for m in jb[name])))
    for name in sorted(set(ib) - set(jb)):
        out.append(("ios-only", name, ", ".join(f"{m['kw']}{m.get('params_clean', '')}" for m in ib[name])))
    for name in sorted(set(jb) & set(ib)):
        a_list, b_list = jb[name], ib[name]
        if len(a_list) != len(b_list):
            out.append(("overloads", name, f"desktop {len(a_list)} vs ios {len(b_list)}"))
            continue
        key = lambda m: param_types(m.get("params_clean", ""))  # noqa: E731
        for a, b in zip(sorted(a_list, key=key), sorted(b_list, key=key)):
            if a["kw"] != b["kw"]:
                out.append(("kind", name, f"desktop {a['kw']} vs ios {b['kw']}"))
            if norm(a.get("ret")) != norm(b.get("ret")):
                out.append(("return", name, f"desktop `{a.get('ret')}` vs ios `{b.get('ret')}`"))
            if visibility(a["mods"]) != visibility(b["mods"]):
                out.append(("visibility", name, f"desktop {visibility(a['mods'])} vs ios {visibility(b['mods'])}"))
            ma = sorted(x for x in a["mods"] if x in MODIFIER_SET)
            mb = sorted(x for x in b["mods"] if x in MODIFIER_SET)
            if ma != mb:
                out.append(("modifiers", name, f"desktop {ma} vs ios {mb}"))
            if sorted(a.get("annotations", [])) != sorted(b.get("annotations", [])):
                out.append(("annotations", name, f"desktop {sorted(a.get('annotations', []))} vs ios {sorted(b.get('annotations', []))}"))
            if a["kw"] == "fun":
                pa, pb = param_names(a.get("params_clean", "")), param_names(b.get("params_clean", ""))
                if len(pa) != len(pb):
                    out.append(("arity", name, f"desktop {pa} vs ios {pb}"))
                elif pa != pb:
                    out.append(("param-names", name, f"desktop {pa} vs ios {pb}"))
                elif param_types(a.get("params_clean", "")) != param_types(b.get("params_clean", "")):
                    out.append(("param-types", name, f"desktop `{a.get('params_clean')}` vs ios `{b.get('params_clean')}`"))
    # Companion members: the public/internal surface only — the private `*_HASH` consts and
    # `*Bind by lazy` method-bind caches are per-platform plumbing, not shape (the measurement
    # excluded them too: 2,634 vs 2,768 such lines).
    jc = {m["name"] for m in j["companion"] if m["kw"] in ("fun", "val", "var") and visibility(m["mods"]) != "private"}
    ic = {m["name"] for m in i["companion"] if m["kw"] in ("fun", "val", "var") and visibility(m["mods"]) != "private"}
    for name in sorted(jc - ic):
        out.append(("companion-desktop-only", name, ""))
    for name in sorted(ic - jc):
        out.append(("companion-ios-only", name, ""))
    jn = {m["name"] for m in j["members"] if m.get("nested") and m["name"] != "Companion"}
    inn = {m["name"] for m in i["members"] if m.get("nested") and m["name"] != "Companion"}
    for name in sorted(jn - inn):
        out.append(("nested-desktop-only", name, ""))
    for name in sorted(inn - jn):
        out.append(("nested-ios-only", name, ""))
    if ("Companion" in {m["name"] for m in j["members"]}) != ("Companion" in {m["name"] for m in i["members"]}):
        out.append(("companion-object", "*", "declared on one platform only"))
    return out


# ---------------------------------------------------------------------------------------------
# Allowlist: `Class | category | member | reason` — member `*` covers the whole category.


def load_allowlist(path: Path) -> dict[tuple[str, str, str], str]:
    entries: dict[tuple[str, str, str], str] = {}
    if not path.exists():
        return entries
    for ln, raw in enumerate(path.read_text(encoding="utf-8").splitlines(), 1):
        line = raw.strip()
        if not line or line.startswith("#"):
            continue
        parts = [p.strip() for p in line.split("|")]
        if len(parts) != 4 or not parts[3]:
            sys.exit(f"{TAG} FAIL {path.name}:{ln}: expected `Class | category | member | reason`, got: {raw}")
        entries[(parts[0], parts[1], parts[2])] = parts[3]
    return entries


def main() -> int:
    ap = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    ap.add_argument("--allowlist", type=Path, default=ALLOWLIST)
    ap.add_argument("--write-allowlist", metavar="REASON", help="rewrite the allowlist from the current findings (existing reasons kept)")
    ap.add_argument("--verbose", action="store_true", help="print every finding, allowlisted or not")
    args = ap.parse_args()

    try:
        jvm, ios = scan(JVM_API), scan(IOS_API)
    except ParseError as e:
        print(f"{TAG} FAIL parser: {e}")
        return 1
    sys.path.insert(0, str(ROOT / "scripts"))
    from generate_api_wrapper import PER_PLATFORM_WRAPPERS  # the provenance table, not a regex over its source

    findings: list[tuple[str, str, str, str]] = []
    provenance = collections.Counter()
    for cls in HAND_SHAPED:
        if cls in ("GodotObject", "GodotCallable"):
            provenance["hand/hand (root)"] += 1
        elif cls not in PER_PLATFORM_WRAPPERS:
            findings.append((cls, "not-in-generator-table", "*", "HAND_SHAPED names a class PER_PLATFORM_WRAPPERS does not know (retired? drop it here too)"))
        else:
            home = PER_PLATFORM_WRAPPERS[cls]
            provenance[f"{home.desktop}/{home.ios}"] += 1
        if cls not in jvm or cls not in ios:
            missing = [p for p, idx in (("desktop", jvm), ("ios", ios)) if cls not in idx]
            findings.append((cls, "missing-on-platform", "*", ", ".join(missing)))
            continue
        try:
            for cat, member, detail in compare(cls, analyze(jvm[cls]), analyze(ios[cls])):
                findings.append((cls, cat, member, detail))
        except ParseError as e:
            findings.append((cls, "parse-error", "*", str(e)))

    allow = load_allowlist(args.allowlist)
    if args.write_allowlist:
        # "auto" spells the reason per category from the task-117 decisions (D1–D8) and parcel.
        auto = {
            "desktop-only": "D1 full parity, desktop canonical: add to iOS or rename (P1)",
            "ios-only": "D1 full parity, desktop canonical: add to desktop or drop/rename (P1)",
            "return": "D2 Long wins over Int / follow the generator's Object nullability (P1)",
            "param-types": "D2 Long wins over Int; desktop nullability (P1)",
            "param-names": "D2 desktop parameter names, except pack(node) (P1)",
            "overloads": "D2 add the missing overload / drop the iOS specialisations (P1)",
            "visibility": "D2 internal like desktop unless an iOS public caller exists (P1)",
            "modifiers": "D2 requireOpenHandle on GodotObject as internal open, RefCounted overrides (P1)",
            "kind": "D2 same declaration kind on both platforms (P1)",
            "arity": "D2 same arity on both platforms (P1)",
            "supertype": "D3 StaticBody3D : PhysicsBody3D on iOS too (P1)",
            "constructor": "D4/D10: internal constructor only for the expect/actual roots (RefCounted; Resource/StandardMaterial3D retire in P1(c)); no Long ctor",
            "companion-desktop-only": "D5 same companion shape on both platforms (P1)",
            "companion-ios-only": "D5 same companion shape on both platforms (P1)",
            "companion-object": "D5 every class gets a companion with wrap/fromHandle (P1)",
            "nested-desktop-only": "D5 mirrored nested Signals object, parity-gated (P1)",
            "nested-ios-only": "D5 mirrored nested Signals object, parity-gated (P1)",
            "annotations": "D6 @JvmName/@JvmStatic live on the expect declaration only (P2)",
            "modality": "D2 same modality on both platforms (P1)",
            "parse-error": "NEVER allowlist: fix the parser or the source layout",
            "not-in-generator-table": "NEVER allowlist: fix HAND_SHAPED or PER_PLATFORM_WRAPPERS",
            "missing-on-platform": "class must exist on both platforms (P1)",
        }
        keys = sorted({(c, cat, m) for c, cat, m, _ in findings})
        lines = [
            "# Hand-shaped wrapper parity allowlist (task 117). One line per finding of",
            "# scripts/check_wrapper_parity.py: `Class | category | member | reason`. A line that no longer",
            "# matches a finding FAILS the gate, so this list only shrinks as parcel P1 reconciles shapes.",
            "# Categories: supertype, modality, constructor, desktop-only, ios-only, overloads, kind, return,",
            "# visibility, modifiers, arity, param-names, param-types, companion-desktop-only, companion-ios-only,",
            "# nested-desktop-only, nested-ios-only, companion-object, annotations.",
            "",
        ]
        for c, cat, m in keys:
            default = auto.get(cat, args.write_allowlist) if args.write_allowlist == "auto" else args.write_allowlist
            lines.append(f"{c} | {cat} | {m} | {allow.get((c, cat, m), default)}")
        args.allowlist.write_text("\n".join(lines) + "\n", encoding="utf-8")
        print(f"{TAG} wrote {len(keys)} allowlist entries to {args.allowlist}")
        return 0

    unallowed = []
    used: set[tuple[str, str, str]] = set()
    for c, cat, m, detail in findings:
        key = (c, cat, m)
        wild = (c, cat, "*")
        if key in allow:
            used.add(key)
        elif wild in allow:
            used.add(wild)
        else:
            unallowed.append((c, cat, m, detail))
        if args.verbose:
            print(f"{TAG}   {c}.{m} [{cat}] {detail}")
    stale = sorted(set(allow) - used)
    by_cat = collections.Counter(cat for _, cat, _, _ in findings)
    if unallowed:
        print(f"{TAG} FAIL {len(unallowed)} finding(s) not in {args.allowlist.name}:")
        for c, cat, m, detail in unallowed:
            print(f"{TAG}   {c} | {cat} | {m}  {detail}")
    if stale:
        print(f"{TAG} FAIL {len(stale)} stale allowlist line(s) — the shapes agree now; delete them:")
        for c, cat, m in stale:
            print(f"{TAG}   {c} | {cat} | {m}")
    if unallowed or stale:
        return 1
    print(
        f"{TAG} PASS {len(HAND_SHAPED)} classes ({', '.join(f'{k} {v}' for k, v in sorted(provenance.items()))}), "
        f"{len(findings)} known divergence(s) all allowlisted "
        f"({', '.join(f'{k}={v}' for k, v in sorted(by_cat.items()))})"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
