prog=schedule
n=3000
v0=./scheduleorig

for i in {1..10..1}
do
  ets=../../$prog"T"$i".sh"
  #cp $ets $prog"T"$i".sh"
   rm -r inputs
   rm runtc.sh
   mkdir inputs
   java ChangeScheduleTS $prog"T"$i".sh" inputs inputs
   ets=runtc.sh

  tar=../../../repairs/semfix/$prog/mf/1repairs
  /bin/sh CollectPR_1repair.sh $tar $v0 $ets $n > T$i"_"$prog"_MFCC.txt" 
 
  tar=../../../repairs/semfix/$prog/rds/1repairs
  /bin/sh CollectPR_10repairs.sh $tar $v0 $ets $n > T$i"_"$prog"_RDS.txt"
done
