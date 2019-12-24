folder=../../repairs/GenProg/tcas/rds/1repairs/v40
v0=./tcasv0
n=3000

     for x in {1..10..1}
     do
       ets=../tcas"T"$x".sh"
      echo "***********************************ETS"$x
      d=$(basename $folder)
      echo "-------------------------------"$d
       for t in {1..10..1}
       do
         echo "----------test suite"$t
         for s in {1..10..1}
         do
           echo "----------seed"$s
           if [ -e $folder/repair$t-$s.c ]
           then
             gcc -o r $folder/repair$t-$s.c $folder/processor.c
             /bin/sh ComputeAPR.sh $v0 r $ets $n
           fi
        done
      done
     done
