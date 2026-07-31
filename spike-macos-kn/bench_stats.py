#!/usr/bin/env python3
"""Task 75 spike — summarize a bench run log ([bench] frame= lines)."""
import re
import statistics
import sys

path = sys.argv[1]
label = sys.argv[2] if len(sys.argv) > 2 else path

per_call = []
for line in open(path, errors="replace"):
    m = re.search(r"\[bench\] frame=(\d+) totalUs=(\d+) perCallNs=([\d.]+)", line)
    if m:
        per_call.append((int(m.group(1)), float(m.group(3))))

if not per_call:
    print(f"{label}: no bench frames found")
    sys.exit(1)

vals = [v for _, v in per_call]
warm = [v for f, v in per_call if f > 30]  # discard first 30 frames

print(f"== {label} ==")
print(f"frames           : {len(vals)}")
print(f"frame 1 (cold)   : {vals[0]:.2f} ns/call")
print(f"frame 2          : {vals[1]:.2f} ns/call")
print(f"frame 5          : {vals[4]:.2f} ns/call" if len(vals) > 4 else "")
print(f"warm mean (f>30) : {statistics.mean(warm):.2f} ns/call")
print(f"warm median      : {statistics.median(warm):.2f} ns/call")
print(f"warm min / max   : {min(warm):.2f} / {max(warm):.2f} ns/call")
print(f"warm stdev       : {statistics.pstdev(warm):.2f} ns/call")
print(f"cold penalty     : {vals[0] / statistics.median(warm):.2f}x frame-1 vs warm median")
