#!/bin/bash

awk -F "\"*,\"*" '{ print $12 }' data/pp-monthly.csv | sort | uniq -u 
