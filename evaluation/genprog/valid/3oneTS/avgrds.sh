TS=RDS
prog=tcas
echo "------------------"$prog
for i in {1..10..1}
do
 java Collect_rds_avg100 ../T$i"_"$prog"_Genprog_"$TS".txt"
done
prog=pt2
echo "------------------"$prog
for i in {1..10..1}
do
 java Collect_rds_avg100 ../T$i"_"$prog"_Genprog_"$TS".txt"
done
prog=schedule
echo "------------------"$prog
for i in {1..10..1}
do
 java Collect_rds_avg100 ../T$i"_"$prog"_Genprog_"$TS".txt"
done
prog=schedule2
echo "------------------"$prog
for i in {1..10..1}
do
 java Collect_rds_avg100 ../T$i"_"$prog"_Genprog_"$TS".txt"
done
prog=replace
echo "------------------"$prog
for i in {1..10..1}
do
 java Collect_rds_avg100 ../T$i"_"$prog"_Genprog_"$TS".txt"
done
