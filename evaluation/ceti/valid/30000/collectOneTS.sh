TS=MFCC
prog=tcas
echo "%------------------"$prog
for i in {1..10..1}
do
 java CollectAllData "_"$prog"_"$TS".txt" $i
done
prog=pt2
echo "%------------------"$prog
for i in {1..10..1}
do
java CollectAllData "_"$prog"_"$TS".txt" $i
done
prog=schedule
echo "%------------------"$prog
for i in {1..10..1}
do
java CollectAllData "_"$prog"_"$TS".txt" $i
done
prog=schedule2
echo "%------------------"$prog
for i in {1..10..1}
do
java CollectAllData "_"$prog"_"$TS".txt" $i
done
prog=replace
echo "%------------------"$prog
for i in {1..10..1}
do
java CollectAllData "_"$prog"_"$TS".txt" $i
done
