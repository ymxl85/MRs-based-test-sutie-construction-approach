ts=cov

rm gp_$ts.record.txt
rm gp_$ts.txt
while read -r line
do
   if [ -e $line ]
   then
     time=$(java CollectTimeGenProg $line)
     echo $line >> gp_$ts.record.txt
     echo $time >> gp_$ts.txt
   else
     echo $line >> gp_$ts.txt
     echo $line >> gp_$ts.record.txt
   fi
done < fl-gp-valid-$ts.txt

