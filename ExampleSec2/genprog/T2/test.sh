#!/bin/bash
export CLASSPATH=$CLASSPATH:../../0javaTools/median
DIR=`dirname $0`
ulimit -t 1
case $2 in
  p1) ./limit $1  1 98 200 | diff exp/O1 - && exit 0 ;;
  n1) ./limit $1  1 101 200 | diff exp/O2 - && exit 0 ;;
esac
exit 1
