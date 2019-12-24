prog=schedule
n=3000
v0=./schedulev0

for i in {1..10..1}
do
  ets=../$prog"T"$i".sh"
  
  tar=../../repairs/CETI/$prog/mf/1repairs

  /bin/sh CollectPR_1repair.sh $tar $v0 $ets $n > T$i"_"$prog"_MFCC.txt" #done on Aug 1
  
done
