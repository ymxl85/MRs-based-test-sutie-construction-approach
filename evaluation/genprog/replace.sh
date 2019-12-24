prog=replace
n=3000
v0=./replacev0

for i in {1..10..1}
do
  ets=../$prog"T"$i".sh"
  
  #tar=../../repairs/GenProg/$prog/mf/1repairs

  #/bin/sh CollectPR_10repairs.sh $tar $v0 $ets $n > T$i"_"$prog"_Genprog_MFCC.txt" #done on July 30
  #tar=../../repairs/semfix/$prog/cov/1repairs
  #/bin/sh CollectPR_10repairs.sh $tar $v0 $ets $n > T$i"_"$prog"_Genprog_MFCC.txt"
  #tar=../../repairs/semfix/$prog/rds/1repairs
  #/bin/sh CollectPR_100repairs.sh $tar $v0 $ets $n > T$i"_"$prog"_Genprog_MFCC.txt"
     echo "**********************************************ETS"$i
    for v in {13,8} 
    do
     folder=../../repairs/GenProg/$prog/cov/1repairs/v$v
     d=$(basename $folder)
      echo "-------------------------------"$d
       for t in {1..10..1}
       do
         echo "----------test suite"$t
         if [ -e $folder/repair$t.c ]
         then
           gcc -o r $folder/repair$t.c
           
           /bin/sh ComputeAPR.sh $v0 r $ets $n
         fi
      done
   done
done
