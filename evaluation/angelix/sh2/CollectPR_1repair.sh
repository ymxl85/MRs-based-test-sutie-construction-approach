repairfolder=$1 #the folder storing repairs i.e., replace/MFCC/1repairs
v0=$2 # the correct executable
ets=$3 #the evaluation test suite, .sh
n=$4 # the size of ets

for folder in $repairfolder/*
do
  if [ -d $folder ]
  then
    if [ -e $folder/repair.c ]
    then
      d=$(basename $folder)
      echo "-------------------------------"$d
      gcc -o r $folder/repair.c #$folder/processor.c
      /bin/sh ComputeAPR.sh $v0 r $ets $n
    fi
  fi
done
