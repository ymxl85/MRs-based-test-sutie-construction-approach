tool=genprog


#rm $tool-mf.txt
#rm $tool-cov.txt
#for prog in {tcas,pt2,schedule,schedule2,replace}
#do
#echo "----"$prog >> $tool-mf.txt
#java AP_mf10 ../$tool/valid/T1_$prog"_Genprog_MFCC.txt" >> $tool-mf.txt
#echo "----"$prog >> $tool-cov.txt
#java AP_mf10 ../$tool/valid/T1_$prog"_Genprog_COV.txt" >> $tool-cov.txt
#done

#tool=genprog
#rm $tool-cov.txt
#for prog in {tcas,pt2,schedule,schedule2,replace}
#do
#echo "----"$prog >> $tool-cov.txt
#java AP_mf10 ../$tool/valid/T3_$prog"_Genprog_COV.txt" >> $tool-cov.txt
#done

tool=semfix
rm $tool-rds.txt
for prog in {tcas,pt2,schedule,schedule2,replace}
do
echo "----"$prog >> $tool-rds.txt
java AP_rds1 ../$tool/T3_$prog"_RDS.txt" >> $tool-rds.txt
done


