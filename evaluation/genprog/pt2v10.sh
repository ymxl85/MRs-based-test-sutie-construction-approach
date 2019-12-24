folder=../../repairs/GenProg/pt2/cov/1repairs/v10
v0=./pt2v0
n=3000

     for x in {1..10..1}
     do
       ets=../pt2"T"$x".sh"
      echo "***********************************ETS"$x
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
