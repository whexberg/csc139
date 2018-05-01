#! /bin/bash

mkdir -p hexbergw-asgmt4

cp -r src hexbergw-asgmt4
cp build.sh hexbergw-asgmt4
cp input.txt hexbergw-asgmt4

tar -cvf hexbergw-asgmt4.tar ./hexbergw-asgmt4
gzip hexbergw-asgmt4.tar
