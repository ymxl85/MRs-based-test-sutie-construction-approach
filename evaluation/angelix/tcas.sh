prog=tcas
n=3000
v0=./tcasv0
for i in {1..10..1}
do
  ets=../$prog"T"$i".sh"
 
  tar=../../repairs/Angelix/$prog/t0/1repairs
  /bin/sh CollectPR_1repair.sh $tar $v0 $ets $n > T$i"_"$prog"_t0.txt"
  #tar=../../repairs/Angelix/$prog/cov/1repairs
  #/bin/sh CollectPR_1repair.sh $tar $v0 $ets $n > T$i"_"$prog"_COV.txt"
  # tar=../../repairs/Angelix/$prog/rds/1repairs
  #/bin/sh CollectPR_10repairs.sh $tar $v0 $ets $n > T$i"_"$prog"_RDS.txt"
done
