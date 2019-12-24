prog=pt2
n=3000
v0=./v0

for i in {1..10..1}
do
  ets=../$prog"T"$i".sh"
  
  #tar=../../repairs/semfix/$prog/mf/1repairs

  #/bin/sh CollectPR_1repair.sh $tar $v0 $ets $n > T$i"_"$prog"_MFCC.txt" #done
  tar=../../repairs/Angelix/$prog/cov/1repairs
  /bin/sh CollectPR_1repair.sh $tar $v0 $ets $n > T$i"_"$prog"_COV.txt"
  tar=../../repairs/Angelix/$prog/rds/1repairs
  /bin/sh CollectPR_10repairs.sh $tar $v0 $ets $n > T$i"_"$prog"_RDS.txt"
done
