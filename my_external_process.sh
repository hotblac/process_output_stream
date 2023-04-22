#!/bin/bash

verbose=false
if [ $1 = '-v' ]
then
  verbose=true
  shift
fi
count=$1
for i in $(seq 1 $count)
do
  echo "I'm thinking $i"
  if $verbose
  then
    echo "I don't know the answer!" >&2
  fi
done
echo "The answer is 42!"