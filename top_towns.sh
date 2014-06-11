#!/bin/bash

awk -f townfreq.awk data/pp-monthly.csv | sort -k 2nr | head -n 20
