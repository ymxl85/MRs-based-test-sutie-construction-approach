folder=../../repairs/CETI/tcas/rds/1repairs/v34
v0=./tcasv0
n=3000

     for x in {1..10..1}
     do
       ets=../tcas"T"$x".sh"
      echo "***********************************ETS"$x
      d=$(basename $folder)
      echo "-------------------------------"$d
       #t=7
       for t in {2,3,6}
       do
         echo "----------test suite"$t
         if [ -e $folder/repair$t.c ]
         then
            gcc -o r $folder/repair$t.c
           
           /bin/sh ComputeAPR.sh $v0 r $ets $n
         fi
      done
     done
