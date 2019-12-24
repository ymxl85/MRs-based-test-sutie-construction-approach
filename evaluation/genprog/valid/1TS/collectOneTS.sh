TS=RDS
prog=tcas
echo "------------------"$prog
java CollectAllData "_"$prog"_Genprog_"$TS".txt"

prog=pt2
echo "------------------"$prog
java CollectAllData "_"$prog"_Genprog_"$TS".txt"

prog=schedule
echo "------------------"$prog
java CollectAllData "_"$prog"_Genprog_"$TS".txt"

prog=schedule2
echo "------------------"$prog
java CollectAllData "_"$prog"_Genprog_"$TS".txt"

prog=replace
echo "------------------"$prog
java CollectAllData "_"$prog"_Genprog_"$TS".txt"

