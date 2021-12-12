#!/usr/bin/env sh
set -ue

set -x

case $1 in
  test)
    exec chmod +x ./gradlew test
    ;;
esac

exec "$@"