#!/bin/bash
for i in {1..10000}; do
  curl http://localhost:31500/liveness
  sleep 0.0001
done