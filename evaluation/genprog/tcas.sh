prog=tcas
n=3000
v0=./tcasv0

for i in {1..10..1}
do
  ets=../$prog"T"$i".sh"
  
  tar=../../repairs/GenProg/$prog/mf/1repairs

 # /bin/sh CollectPR_10repairs.sh $tar $v0 $ets $n > T$i"_"$prog"_Genprog_MFCC.txt" # done on July 30
  #tar=../../repairs/semfix/$prog/cov/1repairs
  #/bin/sh CollectPR_10repairs.sh $tar $v0 $ets $n > T$i"_"$prog"_Genprog_MFCC.txt"
  #tar=../../repairs/semfix/$prog/rds/1repairs
  #/bin/sh CollectPR_100repairs.sh $tar $v0 $ets $n > T$i"_"$prog"_Genprog_MFCC.txt"
done
