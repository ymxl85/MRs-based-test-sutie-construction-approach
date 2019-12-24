#######analyze MFCC
rm Diff_MFCC_cov.txt
echo "--------tcas" >> Diff_MFCC_cov.txt
java Exclusive_sp_cov_ceti tcas MFCC ../../T1_tcas_COV.txt 10 >> Diff_MFCC_cov.txt
echo "--------pt2" >> Diff_MFCC_cov.txt
java Exclusive_sp_cov_ceti pt2 MFCC ../../T1_pt2_COV.txt 10 >> Diff_MFCC_cov.txt
echo "--------replace" >> Diff_MFCC_cov.txt
java Exclusive_sp_cov_ceti replace MFCC ../../T1_replace_COV.txt 10 >> Diff_MFCC_cov.txt
echo "--------schedule" >> Use_MFCC_cov.txt
java Exclusive_sp_cov_ceti schedule MFCC ../../T1_schedule_COV.txt 10 >> Use_MFCC_cov.txt
#######analyze COV
rm Diff_COV_mf.txt
echo "--------tcas" >> Diff_COV_mf.txt
java Exclusive_sp_cov_ceti tcas COV ../../T1_tcas_MFCC.txt 10 >> Diff_COV_mf.txt
echo "--------pt2" >> Diff_COV_mf.txt
java Exclusive_sp_cov_ceti pt2 COV ../../T1_pt2_MFCC.txt 10 >> Diff_COV_mf.txt
echo "--------replace" >> Diff_COV_mf.txt
java Exclusive_sp_cov_ceti replace COV ../../T1_replace_MFCC.txt 10 >> Diff_COV_mf.txt

#######analyze MFCC vs rds
rm Diff_MFCC_RDS.txt
echo "--------tcas" >> Diff_MFCC_RDS.txt
java Exclusive_sp_rds_ceti tcas MFCC RDS 10 >> Diff_MFCC_RDS.txt
echo "--------pt2" >> Diff_MFCC_RDS.txt
java Exclusive_sp_rds_ceti pt2 MFCC RDS 10 >> Diff_MFCC_RDS.txt
echo "--------replace" >> Diff_MFCC_RDS.txt
java Exclusive_sp_rds_ceti replace MFCC RDS 10 >> Diff_MFCC_RDS.txt


