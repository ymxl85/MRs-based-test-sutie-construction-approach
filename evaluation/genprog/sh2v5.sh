folder=../../repairs/GenProg/schedule2/mf/1repairs/v5
v0=./schedule2v0
n=3000

     for x in {1..10..1}
     do
       ets=../schedule2"T"$x".sh"
      echo "***********************************ETS"$x
      d=$(basename $folder)
      echo "-------------------------------"$d
       for t in {1..10..1}
       do
         echo "----------test suite"$t
         if [ -e $folder/repair$t.c ]
         then
            rm r
            gcc -o r $folder/repair$t.c $folder/processor.c
           
           /bin/sh ComputeAPR.sh $v0 r $ets $n
         fi
      done
     done
