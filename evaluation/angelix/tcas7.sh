v0=tcasv0
prog=tcas
n=3000
for i in {1..3..1}
do
    ets=../$prog"T"$i".sh"
folder=../../repairs/semfix/tcas/mf/1repairs/9
    if [ -e $folder/repair.c ]
    then
      d=$(basename $folder)
      echo "-------------------------------"$d
      gcc -o r $folder/repair.c #$folder/processor.c
      /bin/sh ComputeAPR.sh $v0 r $ets $n
    fi

    
    echo $ets
    echo "************************************************************ET"$i
    folder=../../repairs/semfix/tcas/mf/1repairs/7
    if [ -e $folder/repair.c ]
    then
      d=$(basename $folder)
      echo "-------------------------------"$d
      gcc -o r $folder/repair.c #$folder/processor.c
      /bin/sh ComputeAPR.sh $v0 r $ets $n
    fi
folder=../../repairs/semfix/tcas/mf/1repairs/17
    if [ -e $folder/repair.c ]
    then
      d=$(basename $folder)
      echo "-------------------------------"$d
      gcc -o r $folder/repair.c #$folder/processor.c
      /bin/sh ComputeAPR.sh $v0 r $ets $n
    fi

    folder=../../repairs/semfix/tcas/mf/1repairs/18
    if [ -e $folder/repair.c ]
    then
      d=$(basename $folder)
      echo "-------------------------------"$d
      gcc -o r $folder/repair.c #$folder/processor.c
      /bin/sh ComputeAPR.sh $v0 r $ets $n
    fi

    folder=../../repairs/semfix/tcas/mf/1repairs/19
    if [ -e $folder/repair.c ]
    then
      d=$(basename $folder)
      echo "-------------------------------"$d
      gcc -o r $folder/repair.c #$folder/processor.c
      /bin/sh ComputeAPR.sh $v0 r $ets $n
    fi
done

