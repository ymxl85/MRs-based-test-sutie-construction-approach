for t in {1..10..1}
do
  /bin/sh collectOneTS.sh $t > c_rds$t.txt
done
