#!/usr/bin/env sh
set -ue

set -x

case $1 in
  test)
    exec gradlew test
    ;;
esac

exec "$@"