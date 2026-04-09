#!/usr/bin/env bash
set -euo pipefail

if command -v gradle >/dev/null 2>&1; then
  exec gradle "$@"
fi

echo "Gradle bulunamadı. Lütfen Gradle 8.14.3+ kurup PATH'e ekleyin." >&2
exit 1
