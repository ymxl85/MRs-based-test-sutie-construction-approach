#######analyze MFCC
rm Diff_MFCC_cov.txt
echo "--------tcas" >> Diff_MFCC_cov.txt
java Exclusive_sp_cov_gp tcas MFCC COV 10 >> Diff_MFCC_cov.txt
echo "--------pt2" >> Diff_MFCC_cov.txt
java Exclusive_sp_cov_gp pt2 MFCC COV 10 >> Diff_MFCC_cov.txt
echo "--------replace" >> Diff_MFCC_cov.txt
java Exclusive_sp_cov_gp replace MFCC COV 10 >> Diff_MFCC_cov.txt
echo "--------schedule" >> Diff_MFCC_cov.txt
java Exclusive_sp_cov_gp schedule MFCC COV 10 >> Diff_MFCC_cov.txt
echo "--------schedule2" >> Diff_MFCC_cov.txt
java Exclusive_sp_cov_gp schedule2 MFCC COV 10 >> Diff_MFCC_cov.txt

#######analyze COV
rm Diff_COV_mf.txt
echo "--------tcas" >> Diff_COV_mf.txt
java Exclusive_sp_cov_gp tcas COV MFCC 10 >> Diff_COV_mf.txt
echo "--------pt2" >> Diff_COV_mf.txt
java Exclusive_sp_cov_gp pt2 COV MFCC 10 >> Diff_COV_mf.txt
echo "--------replace" >> Diff_COV_mf.txt
java Exclusive_sp_cov_gp replace COV MFCC 10 >> Diff_COV_mf.txt

#######analyze MFCC vs rds
rm Diff_MFCC_RDS.txt
echo "--------tcas" >> Diff_MFCC_RDS.txt
java Exclusive_sp_rds_gp tcas MFCC RDS 10 >> Diff_MFCC_RDS.txt
echo "--------pt2" >> Diff_MFCC_RDS.txt
java Exclusive_sp_rds_gp pt2 MFCC RDS 10 >> Diff_MFCC_RDS.txt
echo "--------replace" >> Diff_MFCC_RDS.txt
java Exclusive_sp_rds_gp replace MFCC RDS 10 >> Diff_MFCC_RDS.txt
echo "--------schedule" >> Diff_MFCC_RDS.txt
java Exclusive_sp_rds_gp schedule MFCC RDS 10 >> Diff_MFCC_RDS.txt
echo "--------schedule2" >> Diff_MFCC_RDS.txt
java Exclusive_sp_rds_gp schedule2 MFCC RDS 10 >> Diff_MFCC_RDS.txt

