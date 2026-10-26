#!/usr/bin/env python3
"""Script portability gate (task 138 item 34): one portable path, platform branches only in a shared helper.

kanama#328 failed on `rg: command not found`: `ios_visual_smoke.sh --check-selftest-log` is a macOS
script, but one of its modes runs on the Linux CI runner, and the maintainer's Mac had ripgrep. A
"use rg when present, grep otherwise" fallback is no cure -- it gives two paths, and each machine
only ever exercises one. The rule instead:

* a script uses the tools both macOS and GitHub's ubuntu runners have (`grep -E`, `sed`, `awk`,
  `python3`, `jq`, bash 3.2 features) -- the ALLOWED_COMMANDS list below is the whole vocabulary;
* a real platform difference (in-place `sed` is BSD vs GNU; `xcrun`/`devicectl` exist on macOS only)
  lives in ONE shared helper, `scripts/portable.sh` (`sed_inplace`), that scripts source;
* device scripts may use macOS-only commands, but only the scripts listed in DEVICE_ONLY, with a
  reason, and only after the line that opens their device paths (a CI-invocable mode, such as
  `--check-selftest-log`, comes before it and stays portable: the gate checks that the marker sits AFTER
  that block, so moving the marker up cannot exempt it).

What it scans: every tracked shell script (`*.sh`, plus extensionless files with a sh/bash shebang,
except the generated `gradlew`), the `run:` blocks of `.github/workflows/*.yml` (the commands CI
itself invokes), and the command names the tracked Python scripts hand to `subprocess`,
`os.system` or `shutil.which`. Shell is tokenised, not grepped: comments, quoted text and
here-document bodies are not commands; the string a shell is handed (`bash -c '...'`, `sh -lc "..."`,
`eval "..."`, Python `shell=True`, `["bash", "-c", "..."]`, `os.system`; options may come before `-c`:
`bash -o pipefail -c '...'`, `bash -O extglob -c "..."`) is scanned as a script; a command wrapper (`timeout`,
`stdbuf`, `nohup`, `nice`, `sudo`, `env`, `xargs`) is judged itself before the gate looks at the command it wraps
(skipping the options that take a value: `env -C DIR cmd`, `sudo -u USER cmd`), and so is the command a
`find -exec CMD ... \\;` / `+` runs. Failures (each has a red run in `scripts/gate_red_runs.py`):

* a command that is not in ALLOWED_COMMANDS (`rg`, `fd`, `gsed`, `gawk`, `sha256sum`, `timeout`, `stdbuf`, ...),
  also when it is only probed (`command -v rg`) or only a wrapper (`timeout 5 cmd`);
* a NOT_EVERYWHERE tool (`cygpath`, `cmake`, `shellcheck`, `mkdocs`, `ktfmt`) outside a guarded use listed in
  PLATFORM_ONLY_USES with its reason; each entry pins how many uses the guard covers, so one more use of the tool
  in a listed script fails (`platform-use-count`), and so does a pin above the real count;
* `grep -P` / `--perl-regexp`;
* `sed -i` (any form) outside `scripts/portable.sh`;
* bash 4+: `declare -A`/`-n`/`-g`, `mapfile`/`readarray`, `coproc`, `${x,,}` / `${x^^}` / `${x@U}`,
  `|&`, `&>>`, `;&`, `;;&`, `[[ -v x ]]`, `wait -n`, `shopt -s globstar`, `$EPOCHSECONDS`, ...;
* macOS-only commands (`xcrun`, `devicectl`, `defaults`, `pmset`, `security`, `plutil`, `stat -f`,
  `date -j`, `sed -i ''`, ...) outside a DEVICE_ONLY script's device paths;
* GNU-only options of allowed commands (`date -d`, `xargs -r`, `readlink -f`, `stat -c`, `env -C`, ...).

Usage:
    python3 scripts/check_script_portability.py              # the gate
    python3 scripts/check_script_portability.py --inventory  # every command word per script, to review
    python3 scripts/check_script_portability.py FILE...      # check only these files
    python3 scripts/check_script_portability.py --self-test  # the shape table: each rule fires, no look-alike does
"""

from __future__ import annotations

import argparse
import ast
import os
import re
import subprocess
import sys
import tempfile
from collections import Counter, defaultdict
from dataclasses import dataclass, field
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
TAG = "[script-portability]"
HELPER = "scripts/portable.sh"

# ---- the vocabulary ----------------------------------------------------------------------------------------
# Present, with the options the scripts use, on both a stock macOS and a GitHub ubuntu runner.
ALLOWED_COMMANDS = frozenset(
    """
    awk basename bash cat chmod cp curl cut date df diff dirname du env expr false file find git
    grep egrep fgrep gzip head id java javac jq kill ln ls make mkdir mktemp mv node nohup nice npm npx
    openssl patch pgrep pkill printf ps python3 pwd readlink rm rmdir sed sh sleep sort ssh tail tar tee
    touch tr true uname unzip wc which xargs xmllint zip cmp comm cpio lsof tput tty uniq stat
    adb gradle gradlew sysctl jar keytool seq perl
    """.split()
)
# Tools that are NOT on both a stock macOS and a stock GitHub ubuntu runner: each is judged where it is used
# (PLATFORM_ONLY_USES below names the script, the command and the guard), so an unguarded use elsewhere
# fails with the reason here. (task 138 item 34 review N3; cmake/shellcheck/mkdocs/ktfmt are toolchain installs.)
NOT_EVERYWHERE = {
    "cygpath": "exists only under Git-Bash/MSYS on Windows; use it behind a Windows (`uname -s` MINGW*/MSYS*/CYGWIN*) guard",
    "powershell.exe": "Windows PowerShell, only on Windows; use it behind a Windows (`uname -s` MINGW*/MSYS*/CYGWIN*) guard",
    "cmake": "a toolchain install (not on a stock macOS; the ubuntu runner has it); use it only after a `command -v cmake` skip or in a script whose job is the native build",
    "shellcheck": "a toolchain install (brew/apt), not part of either stock image; the lint gate probes for it and skips with a reason",
    "mkdocs": "a pip install (docs extra); run it only behind the docs gate's own availability check",
    "ktfmt": "fetched by Gradle (ktfmtCheck), not an installed command; call it through ./gradlew",
}
# Shell keywords and builtins (not external commands).
SHELL_WORDS = frozenset(
    """
    if then else elif fi for while until do done case esac in select function time ! { } [[ ]] [ ]
    : . source alias bg break builtin caller cd command compgen complete continue declare dirs disown echo
    enable eval exec exit export fc fg getopts hash help history jobs kill let local logout popd pushd read
    readonly return set shift shopt suspend test times trap type typeset ulimit umask unalias unset wait
    """.split()
)
# Tools that exist on one machine and not the other, or that duplicate a portable one: no conditional
# convenience tools. (Anything not in ALLOWED_COMMANDS fails; these get a pointed message.)
BANNED_TOOLS = {
    "rg": "ripgrep is not on the CI runner; use `grep -E`",
    "ag": "use `grep -E`",
    "ack": "use `grep -E`",
    "fd": "use `find`",
    "fdfind": "use `find`",
    "gsed": "GNU sed under a Homebrew name; use sed (and sed_inplace from scripts/portable.sh)",
    "gawk": "use awk",
    "ggrep": "use grep -E",
    "gtar": "use tar",
    "gdate": "use date +%s / python3",
    "gfind": "use find",
    "gxargs": "use xargs",
    "gstat": "use python3 for file metadata",
    "gtimeout": "use a python3 timeout (see scripts/web_export_smoke.sh)",
    "timeout": "GNU coreutils; not on a stock macOS (use a python3 timeout, see scripts/web_export_smoke.sh)",
    "stdbuf": "GNU coreutils; not on a stock macOS (use `python3 -u`, or let the program flush)",
    "bat": "use cat",
    "exa": "use ls",
    "eza": "use ls",
}
# Commands that exist on macOS only: allowed only in DEVICE_ONLY scripts' device paths.
MACOS_ONLY_COMMANDS = frozenset(
    """
    xcrun xcodebuild xcode-select devicectl simctl defaults pmset security plutil PlistBuddy osascript
    open sw_vers codesign lipo otool dsymutil hdiutil ditto pbcopy pbpaste launchctl caffeinate mdfind
    diskutil system_profiler scutil networksetup xattr iconutil sips SetFile say tccutil spctl pkgbuild
    productbuild notarytool ios-deploy idevice_id ideviceinstaller iproxy
    """.split()
)
# Absolute paths that exist on macOS only.
MACOS_ONLY_PATHS = ("/usr/libexec/", "/System/", "/Applications/", "/Library/", "/usr/bin/xcrun")
# Wrappers whose next word is the command that runs.
WRAPPERS = frozenset("sudo env exec command nohup xargs timeout time nice stdbuf builtin".split())
# The options of each wrapper that take their value as the NEXT word (`env -C DIR cmd`, `sudo -u USER cmd`): without
# this the value is read as the command, and the command it wraps is never judged.
WRAPPER_OPTIONS_WITH_ARG = {
    "xargs": frozenset(["-I", "-n", "-P", "-L", "-s", "-E", "-d", "-a"]),
    "env": frozenset(["-u", "-C", "-S", "--unset", "--chdir", "--split-string"]),
    "nice": frozenset(["-n"]),
    "stdbuf": frozenset(["-i", "-o", "-e"]),
    "sudo": frozenset(["-u", "-g", "-p", "-C", "-r", "-t", "-U", "-D", "-R", "-T", "--user", "--group"]),
    "timeout": frozenset(["-k", "-s"]),
}
# `find` actions that run a command: `-exec CMD ... \;` / `+`.
FIND_EXEC_ACTIONS = frozenset(["-exec", "-execdir", "-ok", "-okdir"])
# Options of a shell that take their value as the next word (`bash -o pipefail -c '...'`, `bash -O extglob -c '...'`).
SHELL_OPTIONS_WITH_ARG = frozenset(["-o", "+o", "-O", "+O", "--rcfile", "--init-file"])

# ---- device scripts ----------------------------------------------------------------------------------------
# path -> (reason, marker, ci_block). A device script may use MACOS_ONLY_COMMANDS only AFTER the line carrying
# `marker`; everything above it (argument parsing, the CI-invocable modes) is held to the portable rule.
# `marker` None means the whole file is device code (never invoked by CI in any mode).
# `ci_block` is the opening line of the CI-invocable block (its closing `fi` is found at the same indent): the
# marker must sit AFTER it, or moving the marker up would quietly exempt the very code CI runs on Linux.
DEVICE_MARKER = "portability: device-only below"
DEVICE_ONLY: dict[str, tuple[str, str | None, str | None]] = {
    "scripts/ios_visual_smoke.sh": (
        "drives xcodebuild/simctl/devicectl on a Mac; only --check-selftest-log (and the CI red runs) run on Linux, "
        "and they are above the marker",
        DEVICE_MARKER,
        'if [[ "${1:-}" == "--check-selftest-log" ]]; then',
    ),
    "scripts/ios_device_gate.sh": (
        "attaches to a physical iPhone through devicectl/xcrun; CI never runs it",
        None,
        None,
    ),
    "scripts/ios_template_preflight.sh": (
        "checks an Xcode/iOS template on a Mac (plutil, xcodebuild); CI never runs it",
        None,
        None,
    ),
}

# Platform-specific commands a CI-invoked script reaches only behind a platform guard (a `uname` test, a
# `case` arm, a macOS export target): (script, command) -> (uses, why that use is not a portability bug). Anything
# not listed here fails; a new entry needs a reason a reviewer can check against the guard. `uses` PINS how many
# times the script runs the command (probes like `command -v cygpath` count): the reason vouches for those uses only,
# so one more use of the same tool in the script (an unguarded `shellcheck`, `cmake`, `mkdocs`, `cygpath`) fails as
# `platform-use-count` until a reviewer checks its guard and raises the pin. A pin above the real count is stale.
PLATFORM_ONLY_USES: dict[tuple[str, str], tuple[int, str]] = {
    ("scripts/local_ci.sh", "ldd"): (2, "Linux native bootstrap preflight, inside the `uname -s` = Linux branch (macOS has otool -L)"),
    ("scripts/local_ci.sh", "readelf"): (4, "same Linux-only preflight; probed with `command -v` because binutils may be absent"),
    ("scripts/package_install_smoke.sh", "xattr"): (2, "inside the `Darwin)` arm of the platform `case`: clears the macOS quarantine flag"),
    ("scripts/export_game_assemble.sh", "codesign"): (2, "re-seals a macOS .app after the export; reached only for a macOS export target"),
    ("scripts/check_bootstrap_jdk_resolution.sh", "/usr/libexec/java_home"): (1, "behind `uname -s` = Darwin: finds a JDK 25 on a Mac (elsewhere JAVA_HOME / KANAMA_TEST_JDK)"),
    ("scripts/check_jdk_lookup_parity.sh", "/usr/libexec/java_home"): (1, "behind `uname -s` = Darwin: finds a JDK 25 on a Mac (elsewhere JAVA_HOME / KANAMA_TEST_JDK)"),
    # NOT_EVERYWHERE tools (task 138 item 34 review N3): each use names the guard that makes it safe.
    ("scripts/local_ci.sh", "cygpath"): (2, "inside the `MINGW*|MSYS*|CYGWIN*)` arm of the host `case`, behind `command -v cygpath`: converts Windows paths for Git-Bash"),
    ("scripts/export_game_smoke.sh", "cygpath"): (2, "only in the `Windows Desktop` preset branch, with a `|| printf` fallback to the native path"),
    ("scripts/hot_reload_smoke.sh", "cygpath"): (3, "behind `command -v cygpath` (present only under Git-Bash/MSYS): path conversion for the Windows Godot binary"),
    ("scripts/hot_reload_in_process_smoke.sh", "cygpath"): (3, "behind `command -v cygpath` (present only under Git-Bash/MSYS): path conversion for the Windows Godot binary"),
    ("scripts/package_install_smoke.sh", "cygpath"): (5, "behind `command -v cygpath` (present only under Git-Bash/MSYS): path conversion for the Windows Godot binary; and twice in --setup-ps1, which exits 2 on a host that is not MINGW*/MSYS*/CYGWIN*"),
    ("scripts/package_install_smoke.sh", "powershell.exe"): (1, "only with --setup-ps1, which exits 2 on a host that is not MINGW*/MSYS*/CYGWIN* (the Windows store-addon job runs the addon's .ps1 setup)"),
    ("scripts/runtime_smoke.sh", "cygpath"): (3, "behind `command -v cygpath` (present only under Git-Bash/MSYS): path conversion for the Windows Godot binary"),
    ("scripts/tool_smoke.sh", "cygpath"): (7, "behind `command -v cygpath` (present only under Git-Bash/MSYS): path conversion for the Windows Godot binary"),
    ("scripts/local_ci.sh", "cmake"): (3, "behind `command -v cmake`; absent cmake is a named gate_skip (bootstrap-cmake), never a silent pass"),
    ("scripts/local_ci.sh", "mkdocs"): (2, "behind `command -v mkdocs`; absent mkdocs is a named gate_skip (mkdocs), never a silent pass"),
    ("scripts/check_bootstrap_jdk_resolution.sh", "cmake"): (3, "the gate's whole job is the cmake configure; it probes `command -v cmake` first and exits 2 with install instructions"),
    ("scripts/check_shell_lint.sh", "shellcheck"): (3, "the lint gate probes `command -v shellcheck` first and fails with install instructions (the ubuntu runner ships it)"),
}

# ---- bash 4+ -----------------------------------------------------------------------------------------------
BASH4_PARAM_RE = re.compile(r"\$\{[!#]?(?:[A-Za-z_][A-Za-z0-9_]*(?:\[[^\]]*\])?|[0-9]+|[@*])(?:,,?|\^\^?|@[UuLQEPAa])[^}]*\}")
BASH4_VARS_RE = re.compile(r"\$\{?(?:EPOCHSECONDS|EPOCHREALTIME|BASHPID|SRANDOM)\b")
BASH4_COMMANDS = frozenset("mapfile readarray coproc".split())
BASH4_DECLARE_FLAGS = frozenset("Ang")  # declare -A (assoc array), -n (nameref), -g (global from a function)
BASH4_SHOPT = frozenset("globstar checkjobs lastpipe autocd dirspell direxpand assoc_expand_once".split())

# A fixed default path in the shared temp dir (`${LOG:-/tmp/kanama_x.log}`, `${4:-/tmp/x.apk}`): two concurrent runs
# write the same file (two runtime smokes once interleaved one log and failed a pattern check). A default comes from
# `mktemp` (a per-run file or directory under ${TMPDIR:-/tmp}) instead.
FIXED_TMP_DEFAULT_RE = re.compile(r"\$\{[A-Za-z0-9_]+:-/tmp/[^}]*\}")
# BSD/macOS mktemp randomizes trailing X's only: `mktemp /tmp/x.XXXXXX.kt` creates the literal path, so concurrent runs
# collide and a leftover file fails every later run with "File exists". GNU mktemp accepts the suffix, so only the Mac
# would break. Keep the X's last.
MKTEMP_SUFFIX_RE = re.compile(r"XXX+[^X/]")

# GNU-only options of otherwise portable commands: (command, predicate on its argv, message)
GNU_ONLY_OPTION_MESSAGES = {
    ("date", "d"): "`date -d` is GNU-only (BSD date has no -d); compute the time in python3",
    ("xargs", "r"): "`xargs -r` is GNU-only; guard the empty case in the script",
    ("xargs", "d"): "`xargs -d` is GNU-only; use `tr '\\n' '\\0' | xargs -0`",
    ("readlink", "f"): "`readlink -f` fails on macOS before 12.3; use `cd ... && pwd -P` or python3",
    ("stat", "c"): "`stat -c` is GNU-only; use python3 for file metadata",
    ("sort", "h"): "`sort -h` is GNU-only",
    ("find", "printf"): "`find -printf` is GNU-only; use python3",
    ("find", "regextype"): "`find -regextype` is GNU-only",
    ("env", "C"): "`env -C DIR` / `env --chdir` is GNU-only (BSD env has no -C); `cd DIR && cmd` in a subshell instead",
}


@dataclass
class Finding:
    path: str
    line: int
    rule: str
    message: str

    def __str__(self) -> str:
        return f"{self.path}:{self.line}: [{self.rule}] {self.message}"


# ---- shell tokeniser ---------------------------------------------------------------------------------------
@dataclass
class Word:
    text: str  # quotes removed; expansions left as written
    line: int
    quoted: bool = False  # any part was quoted
    expands: bool = False  # contains $ or a backtick outside single quotes
    raw: str = ""  # as written, for the bash 4 scan


@dataclass
class Command:
    words: list[Word] = field(default_factory=list)
    line: int = 0


OPERATORS = ("&>>", ";;&", "|&", "||", "&&", ";;", ";&", "<<-", "<<<", "<<", ">>", "&>", ">&", "<&", "<>", ">|", "|", "&", ";", "(", ")", "<", ">")
CONTROL_SPLIT = frozenset(["|", "||", "&&", ";", ";;", "&", "(", ")", "|&", ";&", ";;&"])


class ShellScanner:
    """A small bash lexer: yields the simple commands of [text] with comments, quoted text and
    here-document bodies already out of the way. Command substitutions inside double quotes, here
    documents with an unquoted delimiter, `$( )` and backticks are scanned recursively."""

    def __init__(self, text: str, line0: int = 1) -> None:
        self.text = text
        self.pos = 0
        self.line = line0
        self.commands: list[Command] = []
        self.raw_code: list[tuple[int, str]] = []  # (line, code text with single-quoted/comment text removed)
        self.pending_heredocs: list[tuple[str, bool, bool]] = []  # (delimiter, strip tabs, expand)
        self.current = Command()
        self.operator_hits: list[tuple[int, str]] = []
        self.case_stack: list[str] = []  # "header" (between `case` and `in`) or "body" (clauses)
        self.expect_pattern = False  # the next thing in a case body is a clause pattern, not a command
        self.in_double_bracket = False  # inside `[[ ... ]]`: && || ( ) < > are test syntax, not control flow

    # -- helpers
    def peek(self, n: int = 1) -> str:
        return self.text[self.pos : self.pos + n]

    def flush(self) -> None:
        if self.current.words:
            self.commands.append(self.current)
        self.current = Command()

    def read_heredoc_bodies(self) -> None:
        """Called right after a newline: consume the bodies of the here-documents opened on that line."""
        for delimiter, strip_tabs, expand in self.pending_heredocs:
            body_start = self.pos
            while self.pos < len(self.text):
                end = self.text.find("\n", self.pos)
                line_text = self.text[self.pos : end if end != -1 else len(self.text)]
                compare = line_text.lstrip("\t") if strip_tabs else line_text
                self.pos = (end + 1) if end != -1 else len(self.text)
                self.line += 1
                if compare == delimiter:
                    break
            if expand:
                body = self.text[body_start : self.pos]
                for sub in re.finditer(r"\$\(((?:[^()]|\([^()]*\))*)\)|`([^`]*)`", body):
                    inner = sub.group(1) if sub.group(1) is not None else sub.group(2)
                    nested = ShellScanner(inner, self.line)
                    nested.scan()
                    self.commands.extend(nested.commands)
                    self.raw_code.extend(nested.raw_code)
        self.pending_heredocs = []

    def read_word(self) -> Word | None:
        start = self.pos
        line = self.line
        out: list[str] = []
        quoted = False
        expands = False
        text = self.text
        while self.pos < len(text):
            c = text[self.pos]
            if c in " \t\n" or c in ";|&()<>":
                break
            if c == "\\":
                if self.peek(2) == "\\\n":
                    self.pos += 2
                    self.line += 1
                    continue
                out.append(text[self.pos + 1 : self.pos + 2])
                quoted = True
                self.pos += 2
                continue
            if c == "'":
                end = text.find("'", self.pos + 1)
                if end == -1:
                    end = len(text) - 1
                chunk = text[self.pos + 1 : end]
                out.append(chunk)
                self.line += chunk.count("\n")
                quoted = True
                self.pos = end + 1
                continue
            if c == '"':
                self.pos += 1
                quoted = True
                buf: list[str] = []
                while self.pos < len(text) and text[self.pos] != '"':
                    d = text[self.pos]
                    if d == "\\" and self.pos + 1 < len(text):
                        buf.append(text[self.pos + 1])
                        if text[self.pos + 1] == "\n":
                            self.line += 1
                        self.pos += 2
                        continue
                    if d == "$" and text.startswith("$((", self.pos):
                        end = text.find("))", self.pos)
                        end = len(text) if end == -1 else end + 2
                        buf.append(text[self.pos : end])
                        expands = True
                        self.pos = end
                        continue
                    if d == "$" and text[self.pos + 1 : self.pos + 2] == "(":
                        inner, self.pos = self.read_substitution(self.pos + 2)
                        nested = ShellScanner(inner, self.line)
                        nested.scan()
                        self.commands.extend(nested.commands)
                        self.raw_code.extend(nested.raw_code)
                        buf.append("$(" + inner + ")")
                        expands = True
                        continue
                    if d == "`":
                        end = text.find("`", self.pos + 1)
                        end = len(text) if end == -1 else end
                        inner = text[self.pos + 1 : end]
                        nested = ShellScanner(inner, self.line)
                        nested.scan()
                        self.commands.extend(nested.commands)
                        self.raw_code.extend(nested.raw_code)
                        buf.append("`" + inner + "`")
                        expands = True
                        self.pos = end + 1
                        continue
                    if d == "$":
                        expands = True
                    if d == "\n":
                        self.line += 1
                    buf.append(d)
                    self.pos += 1
                self.pos += 1
                out.append("".join(buf))
                continue
            if c == "$" and text[self.pos + 1 : self.pos + 2] == "'":
                end = self.pos + 2
                while end < len(text) and text[end] != "'":
                    end += 2 if text[end] == "\\" else 1
                out.append(text[self.pos + 2 : end])
                quoted = True
                self.pos = end + 1
                continue
            if c == "$" and text[self.pos + 1 : self.pos + 2] == "(":
                expands = True
                if text[self.pos + 2 : self.pos + 3] == "(":  # $(( arithmetic ))
                    end = text.find("))", self.pos)
                    end = len(text) if end == -1 else end + 2
                    out.append(text[self.pos : end])
                    self.pos = end
                    continue
                inner, new_pos = self.read_substitution(self.pos + 2)
                nested = ShellScanner(inner, self.line)
                nested.scan()
                self.commands.extend(nested.commands)
                self.raw_code.extend(nested.raw_code)
                out.append("$(" + inner + ")")
                self.line += inner.count("\n")
                self.pos = new_pos
                continue
            if c == "$" and text[self.pos + 1 : self.pos + 2] == "{":
                expands = True
                depth = 0
                end = self.pos
                while end < len(text):
                    if text[end] == "{":
                        depth += 1
                    elif text[end] == "}":
                        depth -= 1
                        if depth == 0:
                            break
                    elif text[end] in "'\"":
                        close = text.find(text[end], end + 1)
                        end = close if close != -1 else end
                    end += 1
                out.append(text[self.pos : end + 1])
                self.pos = end + 1
                continue
            if c == "`":
                expands = True
                end = text.find("`", self.pos + 1)
                end = len(text) if end == -1 else end
                inner = text[self.pos + 1 : end]
                nested = ShellScanner(inner, self.line)
                nested.scan()
                self.commands.extend(nested.commands)
                self.raw_code.extend(nested.raw_code)
                out.append("`" + inner + "`")
                self.pos = end + 1
                continue
            if c == "$":
                expands = True
            out.append(c)
            self.pos += 1
        if self.pos == start:
            return None
        return Word("".join(out), line, quoted, expands, text[start : self.pos])

    def read_substitution(self, pos: int) -> tuple[str, int]:
        """Text of a `$( ... )` starting after the opener, and the position after the closer."""
        depth = 1
        i = pos
        text = self.text
        while i < len(text) and depth:
            c = text[i]
            if c == "\\":
                i += 2
                continue
            if c == "'":
                end = text.find("'", i + 1)
                i = (end if end != -1 else len(text)) + 1
                continue
            if c == '"':
                i += 1
                while i < len(text) and text[i] != '"':
                    if text[i] == "\\":
                        i += 1
                    elif text[i] == "$" and text[i + 1 : i + 2] == "(":
                        _, i = self.read_substitution(i + 2)
                        continue
                    i += 1
                i += 1
                continue
            if c == "(":
                depth += 1
            elif c == ")":
                depth -= 1
                if depth == 0:
                    break
            i += 1
        return text[pos:i], i + 1

    def consume_case_pattern(self) -> None:
        """Skip a case clause pattern list: `a|b)`, `(a)`, `*)`."""
        text = self.text
        if text[self.pos : self.pos + 1] == "(":
            self.pos += 1
        while self.pos < len(text):
            c = text[self.pos]
            if c in " \t":
                self.pos += 1
            elif c == "|":
                self.pos += 1
            elif c == ")":
                self.pos += 1
                return
            elif c == "\n":
                return  # malformed; let the main loop resume
            elif self.read_word() is None:
                self.pos += 1

    # -- main loop
    def scan(self) -> None:
        text = self.text
        expect_delimiter: tuple[bool, bool] | None = None
        while self.pos < len(text):
            c = text[self.pos]
            if c == "\n":
                self.flush()
                self.pos += 1
                self.line += 1
                if self.pending_heredocs:
                    self.read_heredoc_bodies()
                continue
            if c in " \t":
                self.pos += 1
                continue
            if c == "#" and (self.pos == 0 or text[self.pos - 1] in " \t\n;|&()"):
                end = text.find("\n", self.pos)
                self.pos = len(text) if end == -1 else end
                continue
            if c == "\\" and self.peek(2) == "\\\n":
                self.pos += 2
                self.line += 1
                continue
            if self.expect_pattern and not self.current.words and self.case_stack and self.case_stack[-1] == "body":
                self.expect_pattern = False
                if not re.match(r"esac\b", text[self.pos :]):
                    self.consume_case_pattern()
                    continue
            operator = next((op for op in OPERATORS if text.startswith(op, self.pos)), None)
            if operator and self.in_double_bracket and operator != ";":
                # test syntax inside [[ ]]: keep the operator as an ordinary word
                self.current.words.append(Word(operator, self.line, False, False, operator))
                self.pos += len(operator)
                continue
            if (
                operator == "("
                and self.current.words
                and self.pos > 0
                and text[self.pos - 1] == "="
                and ASSIGNMENT_RE.match(self.current.words[-1].raw)
            ):
                self.pos += 1  # an array literal `NAME=( a b c )`: its elements are words, not commands
                while self.pos < len(text) and text[self.pos] != ")":
                    c = text[self.pos]
                    if c == "\n":
                        self.line += 1
                        self.pos += 1
                    elif c in " \t":
                        self.pos += 1
                    elif c == "#":
                        end = text.find("\n", self.pos)
                        self.pos = len(text) if end == -1 else end
                    elif self.read_word() is None:
                        self.pos += 1
                self.pos += 1
                continue
            if (
                operator == "("
                and (not self.current.words or self.current.words[-1].text in KEYWORD_PREFIXES | {"for"})
                and text.startswith("((", self.pos)
            ):
                end = text.find("))", self.pos)
                end = len(text) if end == -1 else end + 2
                self.line += text.count("\n", self.pos, end)
                self.pos = end  # an arithmetic command: no external command in it
                continue
            if operator:
                line = self.line
                self.pos += len(operator)
                if operator in ("<<", "<<-"):
                    expect_delimiter = (operator == "<<-", True)
                    continue
                if operator in (";;", ";&", ";;&") and self.case_stack and self.case_stack[-1] == "body":
                    self.expect_pattern = True
                if operator in CONTROL_SPLIT:
                    self.flush()
                    if operator in ("|&", "&>>", ";&", ";;&"):
                        self.operator_hits.append((line, operator))
                elif operator == "&>>":
                    self.operator_hits.append((line, operator))
                # redirections: swallow their target word so it is not read as a command
                if operator in (">", ">>", "<", "&>", "&>>", ">&", "<&", "<>", ">|", "<<<"):
                    while self.pos < len(text) and text[self.pos] in " \t":
                        self.pos += 1
                    self.read_word()
                continue
            word = self.read_word()
            if word is None:  # pragma: no cover -- defensive; every char is consumed above
                self.pos += 1
                continue
            if expect_delimiter is not None:
                strip_tabs, _ = expect_delimiter
                expand = not word.quoted
                self.pending_heredocs.append((word.text, strip_tabs, expand))
                expect_delimiter = None
                continue
            # `2>&1`, `>&2`, `1>file`: a bare number glued to a redirection is not a word
            if re.fullmatch(r"\d+", word.raw) and self.pos < len(text) and text[self.pos] in "<>":
                continue
            at_start = not self.current.words
            self.current.words.append(word)
            if not self.current.line:
                self.current.line = word.line
            self.raw_code.append((word.line, word.raw))
            if not word.quoted:
                if word.text == "[[":
                    self.in_double_bracket = True
                elif word.text == "]]":
                    self.in_double_bracket = False
                elif word.text == "case" and (at_start or self.current.words[0].text in KEYWORD_PREFIXES):
                    self.case_stack.append("header")
                elif word.text == "in" and self.case_stack and self.case_stack[-1] == "header":
                    self.case_stack[-1] = "body"
                    self.expect_pattern = True
                    # `case $x in a) cmd;; esac` on ONE line: the header ends at `in`, or the first clause
                    # pattern is read as a command word and the rest of the line is mis-scanned (task 138 item 34 N1)
                    self.flush()
                elif word.text == "esac" and self.case_stack and self.case_stack[-1] == "body":
                    self.case_stack.pop()
        self.flush()


# ---- shell checks ------------------------------------------------------------------------------------------
ASSIGNMENT_RE = re.compile(r"^[A-Za-z_][A-Za-z0-9_]*(\[[^\]]*\])?\+?=")
FUNCTION_DEF_RE = re.compile(r"(?m)^\s*(?:function\s+)?([A-Za-z_][A-Za-z0-9_:.-]*)\s*\(\)\s*(?:\{|$)")
KEYWORD_PREFIXES = frozenset("if then else elif while until do { } ! time".split())


SHELL_INTERPRETERS = frozenset("bash sh dash zsh ksh".split())


def shell_script_index(texts: list[str], expands: list[bool]) -> int | None:
    """Index, in the arguments of a shell (`bash`, `sh`, ...), of the `-c` script: the word after the options, where
    `-o`/`-O`/`+o`/`+O` and `--rcfile`/`--init-file` take a value (`bash -o pipefail -c '...'`). None when the shell
    runs a script file instead (the first non-option word) or has no `-c`."""
    index = 0
    while index < len(texts):
        text = texts[index]
        if not text.startswith(("-", "+")):
            return None  # the first non-option word is a script file: no -c script
        if text == "--":
            return None
        if text in SHELL_OPTIONS_WITH_ARG:
            index += 2
            continue
        if not text.startswith("--") and not text.startswith("+") and "c" in text[1:] and not expands[index]:
            return index + 1 if index + 1 < len(texts) else None
        if not text.startswith("--") and not text.startswith("+") and text[-1] in "oO" and len(text) > 1 and not expands[index]:
            index += 2  # a cluster ending in o/O (`-eo pipefail`): its value is the next word
            continue
        index += 1
    return None


def inner_scripts(name_word: Word, args: list[Word]) -> list[tuple[str, int]]:
    """(script text, line) the command hands to a shell to run: `bash -c '...'`, `sh -lc "..."`, `eval "..."`."""
    if name_word.quoted and name_word.expands:
        return []
    base = name_word.text.rsplit("/", 1)[-1]
    if base == "eval":
        text = " ".join(w.text for w in args)
        return [(text, name_word.line)] if text.strip() else []
    if base not in SHELL_INTERPRETERS:
        return []
    index = shell_script_index([w.text for w in args], [w.expands for w in args])
    return [] if index is None else [(args[index].text, args[index].line)]


def skip_wrapper_options(wrapper: str, texts: list[str], raws: list[str], expands: list[bool], start: int) -> int:
    """Index of the word that follows [wrapper]'s own options (and `env`'s assignments, `timeout`'s duration, `nice`'s
    level), scanning from [start]; the options that take a value skip it (WRAPPER_OPTIONS_WITH_ARG)."""
    with_arg = WRAPPER_OPTIONS_WITH_ARG.get(wrapper, frozenset())
    j = start
    while j < len(texts):
        nxt = texts[j]
        if nxt.startswith("-") and not expands[j]:
            j += 2 if nxt in with_arg else 1
            continue
        if wrapper == "env" and ASSIGNMENT_RE.match(raws[j]):
            j += 1
            continue
        if wrapper == "timeout" and re.fullmatch(r"[0-9.]+[smhd]?", nxt):
            j += 1
            continue
        if wrapper == "nice" and re.fullmatch(r"[0-9]+", nxt):
            j += 1
            continue
        break
    return min(j, len(texts))


def find_exec_commands(texts: list[str]) -> list[list[int]]:
    """Index lists of each command a `find` runs: the words after `-exec`/`-execdir`/`-ok`/`-okdir` up to the `;` or `+`."""
    found: list[list[int]] = []
    for index, text in enumerate(texts):
        if text not in FIND_EXEC_ACTIONS:
            continue
        end = index + 1
        while end < len(texts) and texts[end] not in (";", "+"):
            end += 1
        if end > index + 1:
            found.append(list(range(index + 1, end)))
    return found


def flag_chars(args: list[Word]) -> set[str]:
    """Single-letter options in [args] (`-ni` -> n, i), stopping at `--`."""
    letters: set[str] = set()
    for word in args:
        if word.text == "--":
            break
        if word.text.startswith("-") and not word.text.startswith("--") and len(word.text) > 1 and not word.expands:
            letters.update(ch for ch in word.text[1:] if ch.isalpha())
    return letters


def long_flags(args: list[Word]) -> set[str]:
    return {w.text.split("=", 1)[0] for w in args if w.text.startswith("--") and len(w.text) > 2}


def resolve_command(words: list[Word], functions: set[str]) -> tuple[Word, list[Word], list[Word]] | None:
    """The word that names the command, the words after it and the wrapper words skipped on the way
    (`timeout`, `env`, `nohup`, ...: each is itself a command the gate must judge), skipping assignments and keywords."""
    wrappers: list[Word] = []
    i = 0
    while i < len(words):
        text = words[i].text
        if ASSIGNMENT_RE.match(words[i].raw):
            i += 1
            continue
        if not words[i].quoted and text in KEYWORD_PREFIXES:
            i += 1
            continue
        break
    if i >= len(words):
        return None
    while words[i].text in WRAPPERS and not words[i].quoted:
        wrapper = words[i].text
        wrappers.append(words[i])
        j = skip_wrapper_options(wrapper, [w.text for w in words], [w.raw for w in words], [w.expands for w in words], i + 1)
        if wrapper == "command" and j > i + 1 and words[i + 1].text in ("-v", "-V", "-p"):
            # `command -v X` probes X: X must be a portable tool too
            if words[i + 1].text in ("-v", "-V") and j < len(words):
                return words[j], words[j + 1 :], wrappers[:-1]  # `command` is a shell word, not a wrapper to judge
        if j >= len(words):
            return words[i], words[i + 1 :], wrappers[:-1]
        i = j
    return words[i], words[i + 1 :], wrappers


@dataclass
class ShellCheck:
    path: str
    text: str
    device_from_line: int | None = None  # macOS-only commands are allowed from this line on
    functions: set[str] = field(default_factory=set)  # shell functions defined anywhere in the repo's scripts
    runner_steps: bool = False  # a workflow `run:` block: it runs on ONE runner, whose own tools (apt-get, gh) are fine
    findings: list[Finding] = field(default_factory=list)
    platform_uses: dict = field(default_factory=lambda: defaultdict(list))  # (script, command) -> lines of the uses PLATFORM_ONLY_USES exempts

    def add(self, line: int, rule: str, message: str) -> None:
        self.findings.append(Finding(self.path, line, rule, message))

    def run(self) -> None:
        functions = self.functions | set(FUNCTION_DEF_RE.findall(self.text))
        self.scan_text(self.text, 1, functions, 0)

    def scan_text(self, text: str, line0: int, functions: set[str], depth: int) -> None:
        """Judge every command in [text]; the string a `bash -c` / `eval` hands to a shell is scanned the same way."""
        scanner = ShellScanner(text, line0)
        scanner.scan()
        for command in scanner.commands:
            self.judge(command.words, functions, depth)
        for line, operator in scanner.operator_hits:
            self.add(line, "bash4", f"`{operator}` needs bash 4+ (macOS ships bash 3.2)")
        self.check_bash4_text(scanner)

    def judge(self, words: list[Word], functions: set[str], depth: int) -> None:
        """Judge one simple command: its wrappers, the command, the script a shell is handed and the commands a `find` runs."""
        resolved = resolve_command(words, functions)
        if resolved is None:
            return
        name_word, args, wrappers = resolved
        self.check_wrapper_options(words, wrappers)
        for wrapper in wrappers:
            self.check_wrapper(wrapper)
        self.check_command(name_word, args, functions, self.path == HELPER)
        if depth < 4:
            for inner, inner_line in inner_scripts(name_word, args):
                self.scan_text(inner, inner_line, functions, depth + 1)
            if not name_word.expands and name_word.text.rsplit("/", 1)[-1] == "find":
                # `find . -exec CMD ... \;` runs CMD: a banned tool hidden behind it is still the script's command
                for indexes in find_exec_commands([w.text for w in args]):
                    self.judge([args[i] for i in indexes], functions, depth + 1)

    def check_wrapper(self, word: Word) -> None:
        """A wrapper (`timeout 5 cmd`, `stdbuf -oL cmd`, `nohup cmd`) is a command in its own right: the gate used to
        skip straight to the wrapped command, so a GNU-only `timeout` or `stdbuf` was never judged."""
        base = word.text.rsplit("/", 1)[-1]
        line = word.line
        if (self.path, base) in PLATFORM_ONLY_USES:
            self.platform_uses[(self.path, base)].append(line)
            return
        if base in BANNED_TOOLS:
            self.add(line, "banned-tool", f"`{base}`: {BANNED_TOOLS[base]}")
        elif base in SHELL_WORDS or base in ALLOWED_COMMANDS:
            return
        elif not self.runner_steps:  # a workflow step runs on one runner, whose own `sudo` is fine
            self.add(line, "unlisted-command", f"`{base}` (a command wrapper) is not in the portable command list (ALLOWED_COMMANDS in {Path(__file__).name}); use a tool both macOS and the ubuntu runner have, or add it with a reason")

    def check_wrapper_options(self, words: list[Word], wrappers: list[Word]) -> None:
        """GNU-only options of the wrappers themselves: `xargs -r` / `-d`, `env -C` / `--chdir`. The command a wrapper
        runs is checked separately."""
        for index, word in enumerate(words):
            if word.text != "xargs" or word.quoted:
                continue
            letters: list[Word] = []
            j = index + 1
            while j < len(words) and words[j].text.startswith("-") and not words[j].expands:
                letters.append(words[j])
                j += 2 if words[j].text in ("-I", "-n", "-P", "-L", "-s", "-E", "-d") else 1
            if "r" in flag_chars(letters):
                self.add(word.line, "gnu-only", GNU_ONLY_OPTION_MESSAGES[("xargs", "r")])
            if "d" in flag_chars(letters):
                self.add(word.line, "gnu-only", GNU_ONLY_OPTION_MESSAGES[("xargs", "d")])
        for wrapper in wrappers:
            if wrapper.text != "env" or wrapper.quoted:
                continue
            index = next(i for i, w in enumerate(words) if w is wrapper)
            j = index + 1
            while j < len(words) and words[j].text.startswith("-") and not words[j].expands:
                option = words[j].text
                if option == "-C" or option.startswith("-C") and not option.startswith("--") or option == "--chdir" or option.startswith("--chdir="):
                    self.add(wrapper.line, "gnu-only", GNU_ONLY_OPTION_MESSAGES[("env", "C")])
                j += 2 if option in WRAPPER_OPTIONS_WITH_ARG["env"] else 1

    def device_allowed(self, line: int) -> bool:
        return self.device_from_line is not None and line >= self.device_from_line

    def check_command(self, name_word: Word, args: list[Word], functions: set[str], is_helper: bool) -> None:
        name = name_word.text
        line = name_word.line
        if name_word.expands or name in functions or name in SHELL_WORDS:
            # `$GODOT --headless`, a function of this script or a builtin: not a named external tool.
            self.check_builtin_options(name, args, line)
            return
        base = name.rsplit("/", 1)[-1]
        for key in ((self.path, name), (self.path, base)):
            if key in PLATFORM_ONLY_USES:
                self.platform_uses[key].append(line)
                return
        if "/" in name:
            if name.startswith(MACOS_ONLY_PATHS):
                if not self.device_allowed(line):
                    self.add(line, "macos-only", f"`{name}` exists on macOS only; allowed only in a DEVICE_ONLY script's device paths")
                return
            if name.startswith(("./", "../")) or "$" in name or name.startswith(("scripts/", "gradlew")):
                return  # a repo script or tool resolved by path
            if name.startswith("/") and base in ALLOWED_COMMANDS | BANNED_TOOLS.keys() | MACOS_ONLY_COMMANDS:
                name, base = base, base  # `/bin/bash`, `/usr/bin/env`: judge by the tool
            else:
                return
        if base in BANNED_TOOLS:
            self.add(line, "banned-tool", f"`{base}`: {BANNED_TOOLS[base]}")
            return
        if base in MACOS_ONLY_COMMANDS:
            if self.runner_steps:
                return  # a macOS runner's own step
            if not self.device_allowed(line):
                self.add(line, "macos-only", f"`{base}` exists on macOS only; allowed only in a DEVICE_ONLY script's device paths (after `# {DEVICE_MARKER}`)")
            return
        if base in BASH4_COMMANDS:
            self.add(line, "bash4", f"`{base}` needs bash 4+ (macOS ships bash 3.2); loop over `read` instead")
            return
        if base in NOT_EVERYWHERE:
            if self.runner_steps:
                return  # a workflow step installs what it uses
            self.add(line, "platform-only", f"`{base}` is not on every platform: {NOT_EVERYWHERE[base]}; list a guarded use in PLATFORM_ONLY_USES with its reason")
            return
        if base not in ALLOWED_COMMANDS:
            if self.runner_steps:
                return  # apt-get, gh, google-chrome: installed by or for this runner
            self.add(line, "unlisted-command", f"`{base}` is not in the portable command list (ALLOWED_COMMANDS in {Path(__file__).name}); use a tool both macOS and the ubuntu runner have, or add it with a reason")
            return
        self.check_options(base, args, line, is_helper)

    def check_builtin_options(self, name: str, args: list[Word], line: int) -> None:
        if name in ("declare", "typeset", "local", "readonly", "export"):
            bad = flag_chars(args) & BASH4_DECLARE_FLAGS
            if bad and name in ("declare", "typeset", "local"):
                self.add(line, "bash4", f"`{name} -{''.join(sorted(bad))}` needs bash 4+ (associative arrays, namerefs, -g)")
        elif name == "shopt":
            letters = [w.text for w in args if not w.text.startswith("-")]
            for option in letters:
                if option in BASH4_SHOPT:
                    self.add(line, "bash4", f"`shopt {option}` needs bash 4+")
        elif name == "wait" and "n" in flag_chars(args):
            self.add(line, "bash4", "`wait -n` needs bash 4.3+")
        elif name == "read":
            if "t" in flag_chars(args):
                for index, word in enumerate(args):
                    if word.text == "-t" and index + 1 < len(args) and re.fullmatch(r"[0-9]*\.[0-9]+", args[index + 1].text):
                        self.add(line, "bash4", "`read -t` with a fractional timeout needs bash 4+")
            if "N" in flag_chars(args) or "i" in flag_chars(args):
                self.add(line, "bash4", "`read -N` / `-i` need bash 4+")
        elif name in ("[[", "test", "["):
            if any(w.text == "-v" for w in args):
                self.add(line, "bash4", f"`{name} -v var` needs bash 4.2+")

    def check_options(self, base: str, args: list[Word], line: int, is_helper: bool) -> None:
        letters = flag_chars(args)
        longs = long_flags(args)
        if base in ("grep", "egrep", "fgrep") and ("P" in letters or "--perl-regexp" in longs):
            self.add(line, "grep-P", "`grep -P` is not available in BSD grep; use `grep -E` (or python3 for lookaround)")
        if base == "sed":
            in_place = "i" in letters or "--in-place" in longs or any(w.text.startswith("-i") and not w.text.startswith("--") and not w.expands for w in args)
            if in_place and not is_helper:
                bsd_form = any(w.text == "-i" for w in args) and any(w.text == "" and w.quoted for w in args)
                self.add(
                    line,
                    "sed-i",
                    ("`sed -i ''` is the BSD spelling (GNU sed reads the empty string as the script); " if bsd_form else "`sed -i` differs between BSD and GNU sed; ")
                    + f"use `sed_inplace` from {HELPER}",
                )
        if base == "stat":
            if "f" in letters and not self.device_allowed(line):
                self.add(line, "macos-only", "`stat -f` is BSD stat (GNU stat -f means filesystem status); read file metadata with python3")
            if "c" in letters or "--format" in longs or "--printf" in longs:
                self.add(line, "gnu-only", GNU_ONLY_OPTION_MESSAGES[("stat", "c")])
        if base == "date":
            if "j" in letters:
                if not self.device_allowed(line):
                    self.add(line, "macos-only", "`date -j` exists on BSD date only; use python3 for date arithmetic")
            if "d" in letters or "--date" in longs:
                self.add(line, "gnu-only", GNU_ONLY_OPTION_MESSAGES[("date", "d")])
            if any(w.text.startswith("+") and ("%N" in w.text) for w in args):
                self.add(line, "gnu-only", "`date +%N` (nanoseconds) is GNU-only; use python3 time.time_ns()")
        if base == "readlink" and "f" in letters:
            self.add(line, "gnu-only", GNU_ONLY_OPTION_MESSAGES[("readlink", "f")])
        if base == "find" and any(w.text in ("-printf", "-regextype") for w in args):
            self.add(line, "gnu-only", GNU_ONLY_OPTION_MESSAGES[("find", "printf")])
        if base == "sort" and "h" in letters:
            self.add(line, "gnu-only", GNU_ONLY_OPTION_MESSAGES[("sort", "h")])
        if base == "mktemp":
            for w in args:
                if not w.text.startswith("-") and MKTEMP_SUFFIX_RE.search(w.text):
                    self.add(line, "mktemp-suffix", f"`mktemp {w.text}`: BSD/macOS mktemp only randomizes trailing X's, so this creates one fixed path; end the template with the X's")

    def check_bash4_text(self, scanner: ShellScanner) -> None:
        for line, raw in scanner.raw_code:
            if FIXED_TMP_DEFAULT_RE.search(raw):
                self.add(line, "fixed-tmp", "a fixed default path under /tmp is shared by concurrent runs; default to a `mktemp` file or directory under ${TMPDIR:-/tmp} instead")
            if BASH4_PARAM_RE.search(raw):
                self.add(line, "bash4", "case-modifying / transform parameter expansion (`${x,,}`, `${x^^}`, `${x@U}`) needs bash 4+; use tr")
            if BASH4_VARS_RE.search(raw):
                self.add(line, "bash4", "`$EPOCHSECONDS` / `$EPOCHREALTIME` / `$BASHPID` / `$SRANDOM` need bash 4+/5")


def device_from_line(path: str, text: str) -> int | None:
    """The first line macOS-only commands are allowed on, for a DEVICE_ONLY script (else None)."""
    entry = DEVICE_ONLY.get(path)
    if entry is None:
        return None
    marker = entry[1]
    if marker is None:
        return 1
    for number, line in enumerate(text.splitlines(), 1):
        if line.lstrip().startswith("#") and marker in line:
            return number
    return None


def merge_uses(into: dict | None, uses: dict) -> None:
    if into is not None:
        for key, lines in uses.items():
            into.setdefault(key, []).extend(lines)


def check_shell_file(path: str, text: str, functions: set[str], uses: dict | None = None) -> list[Finding]:
    check = ShellCheck(path, text, device_from_line(path, text), functions)
    check.run()
    merge_uses(uses, check.platform_uses)
    findings = check.findings
    entry = DEVICE_ONLY.get(path)
    if entry and entry[1] is not None and check.device_from_line is None:
        findings.append(Finding(path, 1, "device-marker", f"listed in DEVICE_ONLY with marker `{entry[1]}` but the script has no `# {entry[1]}` line"))
    elif entry and entry[1] is not None and entry[2] is not None:
        end = ci_block_end(text, entry[2])
        if end is None:
            findings.append(Finding(path, 1, "device-marker", f"the CI-invocable block `{entry[2]}` listed in DEVICE_ONLY is not in the script (or has no closing `fi` at its indent)"))
        elif check.device_from_line is not None and check.device_from_line <= end:
            findings.append(
                Finding(
                    path,
                    check.device_from_line,
                    "device-marker",
                    f"the `# {entry[1]}` marker is above the end of the CI-invocable block (line {end}): code CI runs on Linux would be exempt from the portable rule; keep the marker after it",
                )
            )
    return findings


def ci_block_end(text: str, opener: str) -> int | None:
    """Line of the `fi` that closes the block opened by the line [opener] (matched at the same indent)."""
    lines = text.splitlines()
    for index, line in enumerate(lines):
        if line.strip() == opener:
            indent = line[: len(line) - len(line.lstrip())]
            for end in range(index + 1, len(lines)):
                if lines[end].rstrip() == indent + "fi":
                    return end + 1
            return None
    return None


# ---- workflows ---------------------------------------------------------------------------------------------
def workflow_run_blocks(text: str) -> list[tuple[int, str]]:
    """(first line, shell text) of every `run:` step in a workflow file; `${{ ... }}` becomes a variable."""
    blocks: list[tuple[int, str]] = []
    lines = text.splitlines()
    i = 0
    while i < len(lines):
        match = re.match(r"^(\s*)(?:-\s+)?run:\s*(.*)$", lines[i])
        if not match:
            i += 1
            continue
        indent = len(match.group(1)) + (2 if lines[i].lstrip().startswith("- ") else 0)
        rest = match.group(2).strip()
        if rest and rest not in ("|", ">", "|-", ">-", "|+", ">+"):
            blocks.append((i + 1, rest))
            i += 1
            continue
        body: list[str] = []
        first = i + 2
        i += 1
        while i < len(lines) and (not lines[i].strip() or len(lines[i]) - len(lines[i].lstrip()) > indent):
            body.append(lines[i])
            i += 1
        blocks.append((first, "\n".join(body)))
    return [(line, re.sub(r"\$\{\{.*?\}\}", "${GH_EXPR}", block)) for line, block in blocks]


def check_workflow(path: str, text: str, functions: set[str], uses: dict | None = None) -> list[Finding]:
    findings: list[Finding] = []
    for first_line, block in workflow_run_blocks(text):
        check = ShellCheck(path, block, None, functions, runner_steps=True)
        check.run()
        merge_uses(uses, {key: [line + first_line - 1 for line in lines] for key, lines in check.platform_uses.items()})
        for finding in check.findings:
            findings.append(Finding(path, finding.line + first_line - 1, finding.rule, finding.message))
    return findings


# ---- python scripts ----------------------------------------------------------------------------------------
SUBPROCESS_FUNCS = frozenset("run check_output check_call call Popen system popen which getoutput getstatusoutput".split())
SHELL_STRING_FUNCS = frozenset("system popen getoutput getstatusoutput".split())  # their string IS a shell command line


def literal_text(node: ast.AST) -> str | None:
    """The text of a string expression: a literal, an f-string (each `{...}` becomes `${PY}`), `a + b`, `"..." % x`."""
    if isinstance(node, ast.Constant) and isinstance(node.value, str):
        return node.value
    if isinstance(node, ast.JoinedStr):
        parts = []
        for value in node.values:
            parts.append(value.value if isinstance(value, ast.Constant) and isinstance(value.value, str) else "${PY}")
        return "".join(parts)
    if isinstance(node, ast.BinOp) and isinstance(node.op, ast.Add):
        left, right = literal_text(node.left), literal_text(node.right)
        if left is not None and right is not None:
            return left + right
    if isinstance(node, ast.BinOp) and isinstance(node.op, ast.Mod):
        return literal_text(node.left)
    return None


@dataclass
class PyCommand:
    line: int
    argv: list[str] = field(default_factory=list)  # a literal argv: [0] is the command
    shell: str | None = None  # a shell command line (os.system, shell=True, ["bash", "-c", "<this>"])


def argv_commands(argv: list[str], line: int, texts: list[str | None] | None = None, depth: int = 0) -> list[PyCommand]:
    """The commands a literal argv runs: itself, the `-c` script of a shell (after `-o pipefail`-style options), the command
    a wrapper runs (after its options and their values) and each command a `find -exec` runs."""
    found = [PyCommand(line, argv=argv)]
    if depth >= 4 or not argv:
        return found
    base = argv[0].rsplit("/", 1)[-1]
    if base in SHELL_INTERPRETERS:
        # [texts] keeps the positions of f-string pieces that are not literal (None): the script is the word after -c
        words = texts[1:] if texts is not None and len(texts) == len(argv) else list(argv[1:])
        literal = [w if w is not None else "${PY}" for w in words]
        index = shell_script_index(literal, [False] * len(literal))
        if index is not None and words[index] is not None:
            found.append(PyCommand(line, shell=words[index]))
    elif base in WRAPPERS and len(argv) > 1:
        rest = skip_wrapper_options(base, argv, argv, [False] * len(argv), 1)
        if base == "env" and any(a in ("-C", "--chdir") or a.startswith("--chdir=") for a in argv[1:rest]):
            found.append(PyCommand(line, argv=["env", "-C"]))  # judged by check_python_file as a GNU-only option
        if rest < len(argv):
            found.extend(argv_commands(argv[rest:], line, None, depth + 1))
    elif base == "find":
        for indexes in find_exec_commands(argv):
            found.extend(argv_commands([argv[i] for i in indexes], line, None, depth + 1))
    return found


def python_command_names(tree: ast.AST) -> list[PyCommand]:
    """Each command a script hands to subprocess/os.system/which: its literal argv, and every shell command line."""
    found: list[PyCommand] = []
    for node in ast.walk(tree):
        if not isinstance(node, ast.Call):
            continue
        func = node.func
        name = func.attr if isinstance(func, ast.Attribute) else func.id if isinstance(func, ast.Name) else ""
        if name not in SUBPROCESS_FUNCS or not node.args:
            continue
        first = node.args[0]
        shell_true = any(k.arg == "shell" and isinstance(k.value, ast.Constant) and k.value.value is True for k in node.keywords)
        if isinstance(first, (ast.List, ast.Tuple)):
            if not first.elts:
                continue
            texts = [literal_text(e) for e in first.elts]
            if shell_true and texts[0] is not None:
                found.append(PyCommand(node.lineno, shell=texts[0]))  # with shell=True the first element is the command line
                continue
            if not isinstance(first.elts[0], ast.Constant):
                continue
            found.extend(argv_commands([t for t in texts if t is not None], node.lineno, texts))
            continue
        text = literal_text(first)
        if text is None:
            continue
        if name in SHELL_STRING_FUNCS or shell_true:
            found.append(PyCommand(node.lineno, shell=text))
        else:
            found.append(PyCommand(node.lineno, argv=[text]))
    return found


def check_python_file(path: str, text: str, functions: set[str] | None = None, uses: dict | None = None) -> list[Finding]:
    try:
        tree = ast.parse(text)
    except SyntaxError as error:  # justified: reported as a `parse` finding (the return value), the gate stays red
        return [Finding(path, error.lineno or 1, "parse", f"not valid Python: {error.msg}")]
    findings: list[Finding] = []
    for command in python_command_names(tree):
        line = command.line
        if command.shell is not None:
            # a shell command line (os.system, shell=True, ["bash", "-c", ...]): judged like a script's own text
            check = ShellCheck(path, command.shell, 1 if path in DEVICE_ONLY_PY else None, functions or set())
            check.run()
            merge_uses(uses, {key: [line + f_line - 1 for f_line in lines] for key, lines in check.platform_uses.items()})
            findings += [Finding(path, line + f.line - 1, f.rule, f.message) for f in check.findings]
            continue
        argv = command.argv
        if not argv:
            continue
        base = argv[0].rsplit("/", 1)[-1]
        if (path, base) in PLATFORM_ONLY_USES:
            merge_uses(uses, {(path, base): [line]})
            continue
        if base in BANNED_TOOLS:
            findings.append(Finding(path, line, "banned-tool", f"`{base}`: {BANNED_TOOLS[base]}"))
        elif base in MACOS_ONLY_COMMANDS and path not in DEVICE_ONLY_PY:
            findings.append(Finding(path, line, "macos-only", f"`{base}` exists on macOS only; allowed only in a DEVICE_ONLY_PY script"))
        elif base in NOT_EVERYWHERE:
            findings.append(Finding(path, line, "platform-only", f"`{base}` is not on every platform: {NOT_EVERYWHERE[base]}; list a guarded use in PLATFORM_ONLY_USES with its reason"))
        elif base == "env" and argv[1:] == ["-C"]:
            findings.append(Finding(path, line, "gnu-only", GNU_ONLY_OPTION_MESSAGES[("env", "C")]))
        elif base == "sed" and any(a.startswith("-i") for a in argv[1:]):
            findings.append(Finding(path, line, "sed-i", f"`sed -i` differs between BSD and GNU sed; edit the file in python3"))
        elif base in ("grep", "egrep") and any(a.startswith("-") and not a.startswith("--") and "P" in a for a in argv[1:]):
            findings.append(Finding(path, line, "grep-P", "`grep -P` is not available in BSD grep"))
    return findings


# macOS-only commands a Python script may hand to subprocess (script path -> reason).
DEVICE_ONLY_PY: dict[str, str] = {}


# ---- self-test ---------------------------------------------------------------------------------------------
# (name, script path, text, rules the findings must be exactly). Proves the tokeniser sees each shape and
# ignores the look-alikes (comments, quoted text, here-document bodies, case patterns, function names).
SELFTEST_BLOCK = 'if [[ "${1:-}" == "--check-selftest-log" ]]; then\n  exit 0\nfi\n'  # the CI-invocable block of ios_visual_smoke.sh
SELF_TESTS: list[tuple[str, str, str, list[str]]] = [
    ("rg", "scripts/x.sh", "rg -q foo file\n", ["banned-tool"]),
    ("rg after a pipe", "scripts/x.sh", "cat f | rg foo\n", ["banned-tool"]),
    ("rg in $( )", "scripts/x.sh", 'x="$(rg foo f)"\n', ["banned-tool"]),
    ("rg probe", "scripts/x.sh", "if command -v rg >/dev/null; then :; fi\n", ["banned-tool"]),
    ("fd", "scripts/x.sh", "fd -e kt\n", ["banned-tool"]),
    ("gsed", "scripts/x.sh", "gsed -n 1p f\n", ["banned-tool"]),
    ("gawk", "scripts/x.sh", "gawk '{print}' f\n", ["banned-tool"]),
    ("unlisted command", "scripts/x.sh", "sha256sum f\n", ["unlisted-command"]),
    ("grep -P", "scripts/x.sh", "grep -P 'a(?=b)' f\n", ["grep-P"]),
    ("grep -oP", "scripts/x.sh", "grep -oP 'a\\K.*' f\n", ["grep-P"]),
    ("grep --perl-regexp", "scripts/x.sh", "grep --perl-regexp x f\n", ["grep-P"]),
    ("grep -E is fine", "scripts/x.sh", "grep -Eq 'a|b' f\n", []),
    ("sed -i", "scripts/x.sh", "sed -i 's/a/b/' f\n", ["sed-i"]),
    ("sed -i ''", "scripts/x.sh", "sed -i '' 's/a/b/' f\n", ["sed-i"]),
    ("sed -i.bak", "scripts/x.sh", "sed -i.bak 's/a/b/' f\n", ["sed-i"]),
    ("sed -ni", "scripts/x.sh", "sed -ni 1p f\n", ["sed-i"]),
    ("sed --in-place", "scripts/x.sh", "sed --in-place s/a/b/ f\n", ["sed-i"]),
    ("/usr/bin/sed -i ''", "scripts/x.sh", "/usr/bin/sed -i '' s/a/b/ f\n", ["sed-i"]),
    ("sed -i in the helper", HELPER, "sed_inplace() { sed -i.bak \"$@\"; }\n", []),
    ("sed_inplace call", "scripts/x.sh", "sed_inplace f 's/a/b/'\n", []),
    ("sed -n", "scripts/x.sh", "sed -n '1p' f\n", []),
    ("declare -A", "scripts/x.sh", "declare -A m\n", ["bash4"]),
    ("local -n", "scripts/x.sh", "f() { local -n ref=$1; }\n", ["bash4"]),
    ("mapfile", "scripts/x.sh", "mapfile -t a < f\n", ["bash4"]),
    ("readarray", "scripts/x.sh", "readarray -t a < f\n", ["bash4"]),
    ("coproc", "scripts/x.sh", "coproc cat\n", ["bash4"]),
    ("${x,,}", "scripts/x.sh", 'y="${x,,}"\n', ["bash4"]),
    ("${x^^}", "scripts/x.sh", "y=${x^^}\n", ["bash4"]),
    ("${1,,} positional", "scripts/x.sh", 'name="${1,,}"\n', ["bash4"]),
    ("${arr[0]^}", "scripts/x.sh", 'y="${arr[0]^}"\n', ["bash4"]),
    ("${x@U}", "scripts/x.sh", "y=${x@U}\n", ["bash4"]),
    ("|&", "scripts/x.sh", "cat f |& grep x\n", ["bash4"]),
    ("&>>", "scripts/x.sh", "cat f &>> out\n", ["bash4"]),
    ("[[ -v ]]", "scripts/x.sh", "[[ -v x ]]\n", ["bash4"]),
    ("wait -n", "scripts/x.sh", "wait -n\n", ["bash4"]),
    ("shopt globstar", "scripts/x.sh", "shopt -s globstar\n", ["bash4"]),
    ("EPOCHSECONDS", "scripts/x.sh", 'echo "$EPOCHSECONDS"\n', ["bash4"]),
    ("read -t 0.5", "scripts/x.sh", "read -t 0.5 line\n", ["bash4"]),
    ("declare -a is fine", "scripts/x.sh", "declare -a arr\nlocal x\n", []),
    ("${x:-d} and ${#x} are fine", "scripts/x.sh", 'y="${x:-d}${#x}${x%%/*}"\n', []),
    ("xcrun", "scripts/x.sh", "xcrun simctl list\n", ["macos-only"]),
    ("devicectl", "scripts/x.sh", "devicectl list devices\n", ["macos-only"]),
    ("defaults", "scripts/x.sh", "defaults read com.apple.finder\n", ["macos-only"]),
    ("pmset", "scripts/x.sh", "pmset -g\n", ["macos-only"]),
    ("security", "scripts/x.sh", "security find-identity\n", ["macos-only"]),
    ("plutil", "scripts/x.sh", "plutil -p f.plist\n", ["macos-only"]),
    ("stat -f", "scripts/x.sh", "stat -f %m f\n", ["macos-only"]),
    ("date -j", "scripts/x.sh", "date -j -f %s 0\n", ["macos-only"]),
    ("/usr/libexec path", "scripts/x.sh", "/usr/libexec/java_home -v 25\n", ["macos-only"]),
    ("xattr in a listed platform-guarded use", "scripts/package_install_smoke.sh", "xattr -dr x d\n", []),
    ("date -d", "scripts/x.sh", "date -d yesterday\n", ["gnu-only"]),
    ("xargs -r", "scripts/x.sh", "ls | xargs -r rm\n", ["gnu-only"]),
    ("readlink -f", "scripts/x.sh", "readlink -f f\n", ["gnu-only"]),
    ("mktemp with a suffix after the X's", "scripts/x.sh", "f=$(mktemp /tmp/x.XXXXXX.kt)\n", ["mktemp-suffix"]),
    ("mktemp with trailing X's is fine", "scripts/x.sh", 'f=$(mktemp "${TMPDIR:-/tmp}/x.XXXXXX")\nd=$(mktemp -d /tmp/y.XXXXXX)\n', []),
    ("fixed /tmp default", "scripts/x.sh", 'LOG="${KANAMA_LOG:-/tmp/kanama_x.log}"\n', ["fixed-tmp"]),
    ("fixed /tmp positional default", "scripts/x.sh", 'APK="${4:-/tmp/x.apk}"\n', ["fixed-tmp"]),
    ("a TMPDIR default is fine", "scripts/x.sh", 'root="${TMPDIR:-/tmp}"\n', []),
    ("stat -c", "scripts/x.sh", "stat -c %s f\n", ["gnu-only"]),
    ("xcrun in a device script, before its marker", "scripts/ios_visual_smoke.sh", "xcrun simctl list\n" + SELFTEST_BLOCK + "# portability: device-only below\n", ["macos-only"]),
    ("xcrun in a device script, after its marker", "scripts/ios_visual_smoke.sh", SELFTEST_BLOCK + "# portability: device-only below\nxcrun simctl list\n", []),
    ("rg in a device script is still banned", "scripts/ios_visual_smoke.sh", SELFTEST_BLOCK + "# portability: device-only below\nrg x f\n", ["banned-tool"]),
    ("device script without its marker", "scripts/ios_visual_smoke.sh", "echo hi\n", ["device-marker"]),
    # the marker must sit AFTER the CI-invocable block (review N2): moved above it, the block is silently exempt
    ("device marker moved above the CI block", "scripts/ios_visual_smoke.sh", "# portability: device-only below\n" + SELFTEST_BLOCK, ["device-marker"]),
    ("device marker inside the CI block", "scripts/ios_visual_smoke.sh", 'if [[ "${1:-}" == "--check-selftest-log" ]]; then\n  # portability: device-only below\n  exit 0\nfi\n', ["device-marker"]),
    ("device script without its CI block", "scripts/ios_visual_smoke.sh", "# portability: device-only below\nxcrun x\n", ["device-marker"]),
    ("whole-file device script", "scripts/ios_device_gate.sh", "xcrun devicectl list\n", []),
    # command wrappers are commands too (review S3): `timeout` / `stdbuf` are GNU-only
    ("timeout wrapper", "scripts/x.sh", "timeout 5 sleep 1\n", ["banned-tool"]),
    ("timeout with options", "scripts/x.sh", "timeout -k 2 --signal=INT 5 sleep 1\n", ["banned-tool"]),
    ("timeout in a pipeline and $( )", "scripts/x.sh", 'cat f | timeout 3 grep x\nx="$(timeout 3 date)"\n', ["banned-tool", "banned-tool"]),
    ("gtimeout wrapper", "scripts/x.sh", "gtimeout 5 sleep 1\n", ["banned-tool"]),
    ("stdbuf wrapper", "scripts/x.sh", "stdbuf -oL cat f | cat\n", ["banned-tool"]),
    ("env then timeout then a banned tool", "scripts/x.sh", "env A=1 timeout 3 rg x f\n", ["banned-tool", "banned-tool"]),
    ("sudo wrapper outside a workflow", "scripts/x.sh", "sudo ls\n", ["unlisted-command"]),
    ("sudo in a workflow step is the runner's", ".github/workflows/x.yml", "jobs:\n  a:\n    steps:\n      - run: sudo apt-get install -y x\n", []),
    ("nohup, nice, env, xargs wrappers are fine", "scripts/x.sh", "nohup sleep 1 &\nnice -n 5 sleep 1\nenv A=1 ls\nls | xargs -n 1 echo\n", []),
    ("command -v timeout", "scripts/x.sh", "if command -v timeout >/dev/null; then :; fi\n", ["banned-tool"]),
    # the string a shell is handed is a script too (review N1)
    ("bash -c", "scripts/x.sh", "bash -c 'rg x f'\n", ["banned-tool"]),
    ("sh -c double-quoted", "scripts/x.sh", 'sh -c "cat f | rg x"\n', ["banned-tool"]),
    ("bash -lc", "scripts/x.sh", "bash -lc 'gsed -n 1p f'\n", ["banned-tool"]),
    ("env bash -c", "scripts/x.sh", "env A=1 bash -c 'rg x'\n", ["banned-tool"]),
    ("xargs bash -c", "scripts/x.sh", "ls | xargs -I{} bash -c 'rg x {}'\n", ["banned-tool"]),
    ("bash -c in $( )", "scripts/x.sh", "x=$(bash -c 'xcrun simctl list')\n", ["macos-only"]),
    ("bash -c nested", "scripts/x.sh", "bash -c \"sh -c 'rg x'\"\n", ["banned-tool"]),
    ("bash -c with a bash 4 construct", "scripts/x.sh", "bash -c 'declare -A m'\n", ["bash4"]),
    ("bash -c multi-line", "scripts/x.sh", "bash -c '\n  echo ok\n  sed -i s/a/b/ f\n'\n", ["sed-i"]),
    ("eval", "scripts/x.sh", 'eval "rg x f"\n', ["banned-tool"]),
    ("eval with pieces", "scripts/x.sh", 'eval rg "$pattern" f\n', ["banned-tool"]),
    ("bash script.sh -c is not a -c script", "scripts/x.sh", "bash script.sh -c rg\n", []),
    ("bash -c from a variable", "scripts/x.sh", 'bash -c "$cmd"\neval "$cmd"\n', []),
    ("bash -c with a portable script", "scripts/x.sh", "bash -c 'grep -E x f | sort'\n", []),
    ("echo is not bash", "scripts/x.sh", "echo bash -c rg\n", []),
    # the single-line case swallow (review N1)
    ("one-line case body", "scripts/x.sh", "case $x in a) rg foo f;; esac\n", ["banned-tool"]),
    ("one-line case, command after esac", "scripts/x.sh", "case $x in a) echo;; esac; rg y\n", ["banned-tool"]),
    ("one-line case, patterns only", "scripts/x.sh", "case $x in rg) echo a;; fd|gsed) echo b;; *) echo c;; esac\n", []),
    ("one-line case in a function", "scripts/x.sh", "f() { case $1 in a) fd x;; b) :;; esac; }\n", ["banned-tool"]),
    # tools that are not on every platform (review N3)
    ("cygpath unguarded", "scripts/x.sh", 'p="$(cygpath -u "$1")"\n', ["platform-only"]),
    ("cygpath probe", "scripts/x.sh", "if command -v cygpath >/dev/null; then :; fi\n", ["platform-only"]),
    ("cygpath in a listed guarded use", "scripts/tool_smoke.sh", 'p="$(cygpath -u "$1")"\n', []),
    ("cmake unguarded", "scripts/x.sh", "cmake -S . -B build\n", ["platform-only"]),
    ("cmake in a listed guarded use", "scripts/local_ci.sh", "cmake -S . -B build\n", []),
    ("mkdocs unguarded", "scripts/x.sh", "mkdocs build --strict\n", ["platform-only"]),
    ("shellcheck unguarded", "scripts/x.sh", "shellcheck a.sh\n", ["platform-only"]),
    ("ktfmt unguarded", "scripts/x.sh", "ktfmt --version\n", ["platform-only"]),
    ("cmake in a workflow step", ".github/workflows/x.yml", "jobs:\n  a:\n    steps:\n      - run: cmake --version\n", []),
    ("python cygpath", "scripts/x.py", 'import subprocess\nsubprocess.run(["cygpath", "-u", "x"])\n', ["platform-only"]),
    # look-alikes that must NOT be findings
    ("comment", "scripts/x.sh", "# rg is not used; sed -i; declare -A; xcrun\necho ok # rg too\n", []),
    ("quoted text", "scripts/x.sh", 'echo "use rg, not grep -P; sed -i is BSD"\nprintf \'%s\' "xcrun"\n', []),
    ("here document", "scripts/x.sh", "cat <<'EOF'\nrg foo\nsed -i x\nEOF\ncat <<-EOF\n\trg bar\n\tEOF\n", []),
    ("here document with a substitution", "scripts/x.sh", "cat <<EOF\n$(rg foo f)\nEOF\n", ["banned-tool"]),
    ("case pattern", "scripts/x.sh", "case $x in\n  rg) echo a ;;\n  fd|gsed) echo b ;;\n  *) echo c ;;\nesac\n", []),
    ("case body command", "scripts/x.sh", "case $x in\n  a) rg foo f ;;\nesac\n", ["banned-tool"]),
    ("function name", "scripts/x.sh", "rg() { grep -E \"$@\"; }\nrg x f\n", []),
    ("array literal", "scripts/x.sh", "DEMOS=(\n  rg fd\n  gsed\n)\n", []),
    ("[[ ]] with || and &&", "scripts/x.sh", '[[ -z "$a" || -n "$b" && "$c" == x ]] && echo ok\n', []),
    ("(( ))", "scripts/x.sh", "if (( n > 3 )); then echo big; fi\nfor ((i = 0; i < 3; i++)); do :; done\n", []),
    ("assignment prefix", "scripts/x.sh", "LC_ALL=C sort f\nFOO=bar rg x f\n", ["banned-tool"]),
    ("wrapper", "scripts/x.sh", "xargs -0 rg x\nenv A=1 gsed s/a/b/ f\n", ["banned-tool", "banned-tool"]),
    ("variable command", "scripts/x.sh", '"$GODOT" --headless\n$TOOL x\n', []),
    ("repo script by path", "scripts/x.sh", './scripts/foo.sh\n"$ROOT_DIR/scripts/bar.sh" x\n', []),
    ("continued command", "scripts/x.sh", "grep -E \\\n  -q x f\n", []),
    ("workflow run block", ".github/workflows/x.yml", "jobs:\n  a:\n    steps:\n      - run: |\n          sudo apt-get install -y x\n          rg foo\n", ["banned-tool"]),
    ("workflow runner tools are fine", ".github/workflows/x.yml", "jobs:\n  a:\n    steps:\n      - run: gh release create\n", []),
    ("python subprocess rg", "scripts/x.py", 'import subprocess\nsubprocess.run(["rg", "x"])\n', ["banned-tool"]),
    ("python which", "scripts/x.py", 'import shutil\nshutil.which("gsed")\n', ["banned-tool"]),
    ("python sed -i", "scripts/x.py", 'import subprocess\nsubprocess.run(["sed", "-i", "s/a/b/", "f"])\n', ["sed-i"]),
    ("python xcrun", "scripts/x.py", 'import subprocess\nsubprocess.run(["xcrun", "simctl"])\n', ["macos-only"]),
    ("python git is fine", "scripts/x.py", 'import subprocess\nsubprocess.run(["git", "status"])\n', []),
    ("python shell=True", "scripts/x.py", 'import subprocess\nsubprocess.run("cat f | rg x", shell=True)\n', ["banned-tool"]),
    ("python shell=True f-string", "scripts/x.py", 'import subprocess\nsubprocess.check_output(f"rg {pat} f", shell=True)\n', ["banned-tool"]),
    ("python shell=True portable", "scripts/x.py", 'import subprocess\nsubprocess.run("git status | grep -E x", shell=True)\n', []),
    ("python os.system", "scripts/x.py", 'import os\nos.system("ls | rg x")\n', ["banned-tool"]),
    ("python os.popen", "scripts/x.py", 'import os\nos.popen("sed -i s/a/b/ f")\n', ["sed-i"]),
    ("python bash -c", "scripts/x.py", 'import subprocess\nsubprocess.run(["bash", "-c", "rg x f"])\n', ["banned-tool"]),
    ("python sh -lc f-string", "scripts/x.py", 'import subprocess\nsubprocess.run(["sh", "-lc", f"timeout 5 {cmd}"])\n', ["banned-tool"]),
    ("python bash script is fine", "scripts/x.py", 'import subprocess\nsubprocess.run(["bash", "scripts/x.sh", "-c"])\n', []),
    ("python env wrapper", "scripts/x.py", 'import subprocess\nsubprocess.run(["env", "A=1", "rg", "x"])\n', ["banned-tool"]),
    ("python timeout", "scripts/x.py", 'import subprocess\nsubprocess.run(["timeout", "5", "sleep", "1"])\n', ["banned-tool"]),
    ("python stdbuf", "scripts/x.py", 'import subprocess\nsubprocess.run(["stdbuf", "-oL", "cat"])\n', ["banned-tool"]),
    # options before -c, find -exec, wrappers with valued options (review round 2)
    ("bash -o pipefail -c", "scripts/x.sh", "bash -o pipefail -c 'rg x f'\n", ["banned-tool"]),
    ("bash -O extglob -c double-quoted", "scripts/x.sh", 'bash -O extglob -c "cat f | rg x"\n', ["banned-tool"]),
    ("bash -eo pipefail -c", "scripts/x.sh", "bash -eo pipefail -c 'rg x f'\n", ["banned-tool"]),
    ("bash --norc -o pipefail -lc", "scripts/x.sh", "bash --norc -o pipefail -lc 'gsed -n 1p f'\n", ["banned-tool"]),
    ("bash +o history -c", "scripts/x.sh", "bash +o history -c 'rg x f'\n", ["banned-tool"]),
    ("env bash -o pipefail -c", "scripts/x.sh", "env A=1 bash -o pipefail -c 'rg x'\n", ["banned-tool"]),
    ("bash -o pipefail script.sh is a script file", "scripts/x.sh", "bash -o pipefail script.sh -c rg\n", []),
    ("bash -o pipefail -c from a variable", "scripts/x.sh", 'bash -o pipefail -c "$cmd"\n', []),
    ("bash -o pipefail -c portable", "scripts/x.sh", "bash -o pipefail -c 'grep -E x f | sort'\n", []),
    ("find -exec banned tool", "scripts/x.sh", "find . -name '*.kt' -exec rg x {} \\;\n", ["banned-tool"]),
    ("find -exec + form", "scripts/x.sh", "find . -type f -exec rg x {} +\n", ["banned-tool"]),
    ("find -execdir through env", "scripts/x.sh", "find . -execdir env A=1 rg x {} \\;\n", ["banned-tool"]),
    ("find -exec sh -c script", "scripts/x.sh", "find . -exec sh -c 'rg x \"$1\"' _ {} \\;\n", ["banned-tool"]),
    ("find -exec sed -i", "scripts/x.sh", "find . -exec sed -i s/a/b/ {} \\;\n", ["sed-i"]),
    ("find -exec a portable command", "scripts/x.sh", "find . -exec grep -lE x {} \\; | sort\n", []),
    ("find -ok unlisted command", "scripts/x.sh", "find . -ok sha256sum {} \\;\n", ["unlisted-command"]),
    ("find without -exec", "scripts/x.sh", "find . -name rg -print\n", []),
    ("echo find -exec is not find", "scripts/x.sh", "echo find . -exec rg x {} \\;\n", []),
    ("env -C is GNU-only", "scripts/x.sh", "env -C /tmp ls\n", ["gnu-only"]),
    ("env -C then a banned tool", "scripts/x.sh", "env -C /tmp rg x\n", ["gnu-only", "banned-tool"]),
    ("env --chdir", "scripts/x.sh", "env --chdir=/tmp ls\n", ["gnu-only"]),
    ("env -u NAME cmd", "scripts/x.sh", "env -u FOO ls\nenv -u FOO rg x\n", ["banned-tool"]),
    ("env -i and assignments", "scripts/x.sh", "env -i A=1 B=2 ls\n", []),
    ("sudo -u USER banned tool", "scripts/x.sh", "sudo -u root rg x\n", ["unlisted-command", "banned-tool"]),
    ("sudo -u USER portable tool", "scripts/x.sh", "sudo -u root ls\n", ["unlisted-command"]),
    ("sudo -u USER in a workflow step", ".github/workflows/x.yml", "jobs:\n  a:\n    steps:\n      - run: sudo -u root rg x\n", ["banned-tool"]),
    ("nice -n N banned tool", "scripts/x.sh", "nice -n 5 rg x\n", ["banned-tool"]),
    ("xargs -n N banned tool", "scripts/x.sh", "ls | xargs -n 1 rg x\n", ["banned-tool"]),
    ("python bash -o pipefail -c", "scripts/x.py", 'import subprocess\nsubprocess.run(["bash", "-o", "pipefail", "-c", "rg x f"])\n', ["banned-tool"]),
    ("python bash -O extglob -c", "scripts/x.py", 'import subprocess\nsubprocess.run(["bash", "-O", "extglob", "-c", "rg x f"])\n', ["banned-tool"]),
    ("python bash -o pipefail script", "scripts/x.py", 'import subprocess\nsubprocess.run(["bash", "-o", "pipefail", "scripts/x.sh", "-c"])\n', []),
    ("python env -C", "scripts/x.py", 'import subprocess\nsubprocess.run(["env", "-C", "/tmp", "ls"])\n', ["gnu-only"]),
    ("python env -C banned tool", "scripts/x.py", 'import subprocess\nsubprocess.run(["env", "-C", "/tmp", "rg", "x"])\n', ["gnu-only", "banned-tool"]),
    ("python sudo -u USER banned tool", "scripts/x.py", 'import subprocess\nsubprocess.run(["sudo", "-u", "root", "rg", "x"])\n', ["banned-tool"]),
    ("python find -exec", "scripts/x.py", 'import subprocess\nsubprocess.run(["find", ".", "-exec", "rg", "x", "{}", ";"])\n', ["banned-tool"]),
    ("python find -exec +", "scripts/x.py", 'import subprocess\nsubprocess.run(["find", ".", "-exec", "gsed", "-n", "1p", "{}", "+"])\n', ["banned-tool"]),
    ("python find -exec portable", "scripts/x.py", 'import subprocess\nsubprocess.run(["find", ".", "-exec", "grep", "-l", "x", "{}", ";"])\n', []),
    ("python env assignment", "scripts/x.py", 'import subprocess\nsubprocess.run(["env", "A=1", "ls"])\n', []),
    ("python shell string, multi-line line numbers", "scripts/x.py", 'import subprocess\nsubprocess.run("""\necho ok\nrg x\n""", shell=True)\n', ["banned-tool"]),
]


# The pinned use counts of PLATFORM_ONLY_USES (review round 2): (name, script, command, uses in the text, rules that must be reported).
# The pins are the real ones, so the counts below are generated against the entry for that script and command.
PIN_SELF_TESTS: list[tuple[str, str, str, int, list[str]]] = [
    ("shellcheck at its pinned count", "scripts/check_shell_lint.sh", "shellcheck", 3, []),
    ("one more shellcheck in a listed script", "scripts/check_shell_lint.sh", "shellcheck", 4, ["platform-use-count"]),
    ("a stale pin (the guarded use is gone)", "scripts/check_shell_lint.sh", "shellcheck", 2, ["platform-use-count"]),
    ("cygpath at its pinned count", "scripts/tool_smoke.sh", "cygpath", 7, []),
    ("one more cygpath in a listed script", "scripts/tool_smoke.sh", "cygpath", 8, ["platform-use-count"]),
    ("cmake at its pinned count", "scripts/local_ci.sh", "cmake", 3, []),
    ("one more cmake in a listed script", "scripts/local_ci.sh", "cmake", 4, ["platform-use-count"]),
    ("one more mkdocs in a listed script", "scripts/local_ci.sh", "mkdocs", 3, ["platform-use-count"]),
]


def run_self_test() -> int:
    failures: list[str] = []
    for name, path, command, count, expected in PIN_SELF_TESTS:
        uses: dict = {}
        text = "".join(f"{command} --check x{n}\n" for n in range(count))
        # only the pin is under test here: the other entries of the script are not part of the text
        found = [f for f in check_shell_file(path, text, {"sed_inplace"}, uses) if f.rule != "device-marker"]
        found += [f for f in pin_findings(uses, {path}) if f"`{command}`" in f.message]
        rules = sorted(f.rule for f in found)
        if rules != sorted(expected):
            failures.append(f"{name}: expected {sorted(expected)}, got {rules}" + "".join(f"\n      {f}" for f in found))
        # an over-count finding lists every use of the command, so the reader can see the extra one
        for finding in found:
            if finding.rule == "platform-use-count" and " times here" in finding.message:
                listed = f"(lines {', '.join(str(n) for n in range(1, count + 1))})"
                if listed not in finding.message:
                    failures.append(f"{name}: the finding must list all {count} lines {listed}: {finding}")
    for name, path, text, expected in SELF_TESTS:
        if path.endswith(".py"):
            found = check_python_file(path, text)
        elif path.startswith(".github/workflows/"):
            found = check_workflow(path, text, {"sed_inplace"})
        else:
            found = check_shell_file(path, text, {"sed_inplace"})
        rules = sorted(f.rule for f in found)
        if rules != sorted(expected):
            failures.append(f"{name}: expected {sorted(expected)}, got {rules}" + "".join(f"\n      {f}" for f in found))
    # sed_inplace must edit in place on this platform's sed, keep the file's content otherwise, and leave no backup
    with tempfile.TemporaryDirectory() as tmp:
        target = Path(tmp) / "f.txt"
        target.write_text("one\ntwo\nthree\n", encoding="utf-8")
        script = f'source "{ROOT / HELPER}" && sed_inplace "{target}" -e "s/two/2/" -e "/three/d"'
        result = subprocess.run(["bash", "-c", script], capture_output=True, text=True)
        content = target.read_text(encoding="utf-8")
        leftovers = [p.name for p in Path(tmp).iterdir() if p.name != "f.txt"]
        if result.returncode != 0 or content != "one\n2\n" or leftovers:
            failures.append(f"sed_inplace: rc={result.returncode} content={content!r} leftovers={leftovers} stderr={result.stderr!r}")
        failing = subprocess.run(["bash", "-c", f'source "{ROOT / HELPER}" && sed_inplace "{Path(tmp) / "missing.txt"}" -e s/a/b/'], capture_output=True, text=True)
        if failing.returncode == 0:
            failures.append("sed_inplace: a missing file must be a failure")
    if failures:
        print(f"{TAG} --self-test FAIL:", file=sys.stderr)
        for failure in failures:
            print(f"  {failure}", file=sys.stderr)
        return 1
    print(f"{TAG} --self-test PASS {len(SELF_TESTS)} shapes and {len(PIN_SELF_TESTS)} pin cases classified as expected; sed_inplace edits in place and leaves no backup")
    return 0


# ---- driver ------------------------------------------------------------------------------------------------
def tracked_files() -> list[str]:
    # tracked files plus new, not-yet-added ones (a script written this minute is scanned before its commit)
    out = subprocess.run(
        ["git", "ls-files", "--cached", "--others", "--exclude-standard"], cwd=ROOT, check=True, capture_output=True, text=True
    ).stdout
    return [line for line in out.splitlines() if line]


def is_shell_script(rel: str, head: str) -> bool:
    if rel == "gradlew" or rel.endswith("/gradlew"):
        return False
    if rel.endswith(".sh"):
        return True
    return head.startswith("#!") and bool(re.search(r"\b(ba)?sh\b", head.splitlines()[0] if head else ""))


def collect_targets(explicit: list[str]) -> list[str]:
    if explicit:
        return [str(Path(p)) if not Path(p).is_absolute() else str(Path(p).relative_to(ROOT)) for p in explicit]
    return tracked_files()


def read_text(rel: str) -> str | None:
    path = ROOT / rel
    if not path.is_file() or path.is_symlink():
        return None
    try:
        return path.read_text(encoding="utf-8")
    except UnicodeDecodeError:  # justified: a binary file is not a script; only text files are scanned
        return None


def repo_functions(files: list[str]) -> set[str]:
    """Every shell function the repo's scripts define: scripts source each other's helpers, so a call
    to `gate_skip` in one file is a call to repo code, checked where it is defined."""
    names: set[str] = set()
    for rel in files:
        text = read_text(rel)
        if text is not None and is_shell_script(rel, text[:200]):
            names.update(FUNCTION_DEF_RE.findall(text))
    return names


def check_files(files: list[str], functions: set[str]) -> tuple[list[Finding], dict[str, Counter]]:
    findings: list[Finding] = []
    inventory: dict[str, Counter] = defaultdict(Counter)
    uses: dict = {}
    for rel in files:
        text = read_text(rel)
        if text is None:
            continue
        head = text[:200]
        if rel.startswith(".github/workflows/") and rel.endswith((".yml", ".yaml")):
            findings += check_workflow(rel, text, functions, uses)
        elif is_shell_script(rel, head):
            findings += check_shell_file(rel, text, functions, uses)
            scanner = ShellScanner(text)
            scanner.scan()
            known = functions | set(FUNCTION_DEF_RE.findall(text))
            for command in scanner.commands:
                resolved = resolve_command(command.words, known)
                if resolved and not resolved[0].expands and resolved[0].text not in SHELL_WORDS | known:
                    inventory[rel][resolved[0].text] += 1
        elif rel.endswith(".py") and rel != "scripts/check_script_portability.py":
            findings += check_python_file(rel, text, functions, uses)
    findings += pin_findings(uses, set(files))
    return findings, inventory


def pin_findings(uses: dict, scanned: set[str], pins: dict | None = None) -> list[Finding]:
    """PLATFORM_ONLY_USES exempts one guarded use per pinned count: an additional use of the same tool in an already
    listed script (an unguarded `shellcheck`, `cmake`, `mkdocs`, `cygpath`) is not covered by the guard the entry
    names, so it fails until a reviewer lists it (and bumps the pin); a pin the script no longer reaches is stale."""
    pins = PLATFORM_ONLY_USES if pins is None else pins
    findings: list[Finding] = []
    for (path, command), (expected, _reason) in sorted(pins.items()):
        if path not in scanned:
            continue  # an explicit file subset: only the scripts that were scanned are judged
        lines = uses.get((path, command), [])
        if len(lines) > expected:
            findings.append(
                Finding(
                    path,
                    min(lines),
                    "platform-use-count",
                    f"`{command}` is used {len(lines)} times here (lines {', '.join(str(n) for n in sorted(lines))}) but PLATFORM_ONLY_USES pins {expected} guarded use(s): "
                    f"one of them is not covered by the listed guard; guard it and raise the pin with the reason, or remove it",
                )
            )
        elif len(lines) < expected:
            findings.append(
                Finding(path, 1, "platform-use-count", f"`{command}` is used {len(lines)} time(s) here but PLATFORM_ONLY_USES pins {expected}: lower the pin or delete the stale entry")
            )
    return findings


def check_registry(files: list[str]) -> list[Finding]:
    """The DEVICE_ONLY table names real, tracked scripts (a rename must not leave a stale exemption)."""
    tracked = set(files)
    return [
        Finding(rel, 1, "device-registry", "listed in DEVICE_ONLY but is not a tracked script; remove or rename the entry")
        for rel in DEVICE_ONLY
        if rel not in tracked
    ] + [
        Finding(rel, 1, "device-registry", "listed in DEVICE_ONLY_PY but is not a tracked script")
        for rel in DEVICE_ONLY_PY
        if rel not in tracked
    ] + [
        Finding(rel, 1, "device-registry", f"`{command}` is listed in PLATFORM_ONLY_USES but the script is not tracked; remove or rename the entry")
        for rel, command in sorted(PLATFORM_ONLY_USES)
        if rel not in tracked
    ]


def main(argv: list[str]) -> int:
    parser = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    parser.add_argument("files", nargs="*", help="check only these files (default: every tracked script and workflow)")
    parser.add_argument("--inventory", action="store_true", help="print the command words each shell script uses and exit")
    parser.add_argument("--self-test", action="store_true", help="run the built-in shape table and the sed_inplace check, then exit")
    args = parser.parse_args(argv)
    if args.self_test:
        return run_self_test()
    files = collect_targets(args.files)
    findings, inventory = check_files(files, repo_functions(tracked_files()))
    if args.inventory:
        for rel in sorted(inventory):
            print(f"{rel}: " + " ".join(f"{name}x{count}" for name, count in sorted(inventory[rel].items())))
        return 0
    if not args.files:
        findings += check_registry(files)
    if findings:
        print(f"{TAG} FAIL {len(findings)} portability violation(s):", file=sys.stderr)
        for finding in sorted(findings, key=lambda f: (f.path, f.line)):
            print(f"  {finding}", file=sys.stderr)
        print(
            f"{TAG} rule: scripts use tools both macOS and the ubuntu runner have; platform differences live in {HELPER}; "
            f"macOS-only commands only in DEVICE_ONLY scripts' device paths",
            file=sys.stderr,
        )
        return 1
    shell = sum(1 for f in files if (text := read_text(f)) is not None and is_shell_script(f, text[:200]))
    workflows = sum(1 for f in files if f.startswith(".github/workflows/") and f.endswith((".yml", ".yaml")))
    pythons = sum(1 for f in files if f.endswith(".py"))
    print(
        f"{TAG} PASS {shell} shell script(s), {workflows} workflow file(s) and {pythons} Python file(s) scanned; "
        f"no unlisted command, grep -P, stray sed -i, bash 4+ construct or macOS-only command outside "
        f"{len(DEVICE_ONLY)} DEVICE_ONLY script(s) and {len(PLATFORM_ONLY_USES)} listed platform-guarded use(s) at their pinned counts"
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv[1:]))
