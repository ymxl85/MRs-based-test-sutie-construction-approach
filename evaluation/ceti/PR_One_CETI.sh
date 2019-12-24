prog=$1
n=3000
v0=../CETI/$prog/v0
for i in {1..10..1}
do
  ets=$prog"T"$i".sh"
  tar=../CETI/$prog/MFCC/1repairs
  /bin/sh CollectPR_1repair.sh $tar $v0 $ets $n > T$i"_"$prog"_MFCC.txt"
  tar=../CETI/$prog/coverageTC/1repairs
  /bin/sh CollectPR_1repair.sh $tar $v0 $ets $n > T$i"_"$prog"_COV.txt"
   tar=../CETI/$prog/randomTC/1repairs
  /bin/sh CollectPR_10repairs.sh $tar $v0 $ets $n > T$i"_"$prog"_RDS.txt"
done
