repairfolder=$1 #the folder storing repairs i.e., replace/MFCC/1repairs
v0=$2 # the correct executable
ets=$3 #the evaluation test suite, .sh
n=$4 # the size of ets

for folder in $repairfolder/*
do
  if [ -d $folder ]
  then
      d=$(basename $folder)
      echo "-------------------------------"$d
       for t in {1..10..1}
       do
         echo "----------test suite"$t
         for s in {1..10..1}
         do
           echo "----------seed"$s
           if [ -e $folder/repair$t-$s.c ]
           then
             gcc -o r $folder/repair$t-$s.c $folder/processor.c
             /bin/sh ComputeAPR.sh $v0 r $ets $n
           fi
        done
      done
  fi
done
