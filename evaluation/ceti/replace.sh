prog=replace
n=3000
v0=./replacev0

for i in {1..10..1}
do
  ets=../$prog"T"$i".sh"
  
  tar=../../repairs/CETI/$prog/mf/1repairs

  #/bin/sh CollectPR_1repair.sh $tar $v0 $ets $n > T$i"_"$prog"_MFCC.txt" #done one July 29
  #tar=../../repairs/semfix/$prog/cov/1repairs
  #/bin/sh CollectPR_1repair.sh $tar $v0 $ets $n > T$i"_"$prog"_COV.txt"
  #tar=../../repairs/semfix/$prog/rds/1repairs
  #/bin/sh CollectPR_10repairs.sh $tar $v0 $ets $n > T$i"_"$prog"_RDS.txt"
done
