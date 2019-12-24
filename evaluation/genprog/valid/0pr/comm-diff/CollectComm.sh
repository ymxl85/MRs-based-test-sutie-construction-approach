#######analyze MFCC vs cov
rm Comm_MFCC_COV.txt
echo "--------tcas" >> Comm_MFCC_COV.txt
java Comm_sp_cov_gp tcas MFCC COV 10 >> Comm_MFCC_COV.txt
echo "--------pt2" >> Comm_MFCC_COV.txt
java Comm_sp_cov_gp pt2 MFCC COV 10 >> Comm_MFCC_COV.txt
echo "--------replace" >> Comm_MFCC_COV.txt
java Comm_sp_cov_gp replace MFCC COV 10 >> Comm_MFCC_COV.txt
#echo "--------schedule" >> Comm_MFCC_COV.txt
#java Comm_sp_cov_gp schedule MFCC COV 10 >> Comm_MFCC_COV.txt
#echo "--------schedule2" >> Comm_MFCC_COV.txt
#java Comm_sp_cov_gp schedule2 MFCC COV 10 >> Comm_MFCC_COV.txt

rm Comm_MFCC_RDS.txt
#######analyze MFCC vs rds
echo "--------tcas" >> Comm_MFCC_RDS.txt
java Comm_sp_rds_gp tcas MFCC RDS 10 >> Comm_MFCC_RDS.txt
echo "--------pt2" >> Comm_MFCC_RDS.txt
java Comm_sp_rds_gp pt2 MFCC RDS 10 >> Comm_MFCC_RDS.txt
echo "--------replace" >> Comm_MFCC_RDS.txt
java Comm_sp_rds_gp replace MFCC RDS 10 >> Comm_MFCC_RDS.txt
echo "--------schedule" >> Comm_MFCC_RDS.txt
java Comm_sp_rds_gp schedule MFCC RDS 10 >> Comm_MFCC_RDS.txt
echo "--------schedule2" >> Comm_MFCC_RDS.txt
java Comm_sp_rds_gp schedule2 MFCC RDS 10 >> Comm_MFCC_RDS.txt

#echo "--------tcas" >> Comm_MFCC_t0.txt
#java Comm_sp_cov_gp tcas MFCC t0 10 >> Comm_MFCC_t0.txt
