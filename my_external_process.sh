#!/bin/bash

count=$1
for i in $(seq 1 $count)
do
  echo "I'm thinking $i"
done
echo "The answer is 42!"