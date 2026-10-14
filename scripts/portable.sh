# shellcheck shell=bash
# portable.sh -- the one place a script branches on the platform (task 138 item 34). Source it; do not run it.
#
# Gate scripts run on a maintainer's Mac and on GitHub's ubuntu runners, so they use only the tools both
# have (`grep -E`, `sed`, `awk`, `python3`, bash 3.2 features): no `rg`, no `grep -P`, no bash 4. Where
# the two platforms genuinely differ, the difference lives here, once, and the scripts call the helper.
# scripts/check_script_portability.py enforces it (a `sed -i` anywhere but here is a CI failure).
#
#   sed_inplace <file> <sed-args...>    `sed -i` that behaves the same under BSD sed and GNU sed
#
# BSD sed wants `-i EXT` (`-i ''` for none) and GNU sed takes `-i[EXT]`; the attached-suffix spelling
# `-i.EXT` is the one both accept, so the helper edits with a backup suffix and removes the backup
# when sed succeeded.
sed_inplace() {
  local file="$1"
  shift
  sed -i.portable-bak "$@" "$file" || return
  rm -f "${file}.portable-bak"
}
