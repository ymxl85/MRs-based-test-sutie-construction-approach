folder=../../../repairs/GenProg/tcas/mf/1repairs/v9
v0=./tcasv0
n=3000

     for x in {4..4..1}
     do
       ets=../../tcas"T"$x".sh"
      echo "***********************************ETS"$x
      d=$(basename $folder)
      echo "-------------------------------"$d
       for t in {2..2..1}
       do
         echo "----------test suite"$t
         if [ -e $folder/repair$t.c ]
         then
            gcc -o r $folder/repair$t.c
           
           /bin/sh ComputeAPR.sh $v0 r $ets $n
         fi
      done
     done
