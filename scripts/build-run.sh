#!/bin/sh

x=$(realpath $0)
SCRIPTS=$(dirname $x)

cd $SCRIPTS/.. && javac -d ./bin/ ./src/snek/*
cd ./bin && java snek.Application


