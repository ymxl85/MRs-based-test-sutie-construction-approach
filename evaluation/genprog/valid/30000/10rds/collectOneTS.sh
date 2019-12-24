TS=RDS
tth=$1
prog=tcas
echo "%------------------"$prog
for i in {1..10..1}
do
 a=$(java Collect_pr_Ards10 ../../T$i"_"$prog"_Genprog_"$TS".txt" $tth)
 echo "t"$i"="$a
done
prog=pt2
echo "%------------------"$prog
for i in {1..10..1}
do
 a=$(java Collect_pr_Ards10 ../../T$i"_"$prog"_Genprog_"$TS".txt" $tth)
 echo "t"$i"="$a
done
prog=schedule
echo "%------------------"$prog
for i in {1..10..1}
do
 a=$(java Collect_pr_Ards10 ../../T$i"_"$prog"_Genprog_"$TS".txt" $tth)
 echo "t"$i"="$a
done
prog=schedule2
echo "%------------------"$prog
for i in {1..10..1}
do
 a=$(java Collect_pr_Ards10 ../../T$i"_"$prog"_Genprog_"$TS".txt" $tth)
 echo "t"$i"="$a
done
prog=replace
echo "%------------------"$prog
for i in {1..10..1}
do
 a=$(java Collect_pr_Ards10 ../../T$i"_"$prog"_Genprog_"$TS".txt" $tth)
 echo "t"$i"="$a
done
