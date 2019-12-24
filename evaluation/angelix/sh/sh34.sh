
v0=./scheduleorig
n=3000

     for x in {1..10..1}
     do
       rm -r inputs
       rm runtc.sh
       mkdir inputs
       ets=../schedule2"T"$x".sh"
       java ChangeScheduleTS "scheduleT"$x".sh" inputs inputs
       ets=runtc.sh
      echo "***********************************ETS"$x

folder=../../../repairs/Angelix/schedule/rds/1repairs/3
      d=$(basename $folder)
      echo "-------------------------------"$d
       for t in {1..10..1}
       do
         echo "----------test suite"$t
         if [ -e $folder/repair$t.c ]
         then
            rm r
            gcc -o r $folder/repair$t.c
           
            /bin/sh ComputeAPR.sh $v0 r $ets $n
         fi
      done


folder=../../../repairs/Angelix/schedule/rds/1repairs/4
      d=$(basename $folder)
      echo "-------------------------------"$d
       for t in {1..10..1}
       do
         echo "----------test suite"$t
         if [ -e $folder/repair$t.c ]
         then
            rm r
            gcc -o r $folder/repair$t.c
           
            /bin/sh ComputeAPR.sh $v0 r $ets $n
         fi
      done

     done
