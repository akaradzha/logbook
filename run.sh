#!/usr/bin/env bash
COUNT=0

while true
do
    COUNT=$((COUNT+1))
    curl http://localhost:8081/test1
    echo " ${COUNT}"
    sleep 1
done
