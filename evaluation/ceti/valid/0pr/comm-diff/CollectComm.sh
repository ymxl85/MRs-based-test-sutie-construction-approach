rm Comm_MFCC_COV.txt
#######analyze MFCC vs cov
echo "--------tcas" >> Comm_MFCC_COV.txt
java Comm_sp_cov_ceti tcas MFCC COV 10 >> Comm_MFCC_COV.txt
echo "--------pt2" >> Comm_MFCC_COV.txt
java Comm_sp_cov_ceti pt2 MFCC COV 10 >> Comm_MFCC_COV.txt
echo "--------replace" >> Comm_MFCC_COV.txt
java Comm_sp_cov_ceti replace MFCC COV 10 >> Comm_MFCC_COV.txt

rm Comm_MFCC_RDS.txt
#######analyze MFCC vs rds
echo "--------tcas" >> Comm_MFCC_RDS.txt
java Comm_sp_rds_ceti tcas MFCC RDS 10 >> Comm_MFCC_RDS.txt
echo "--------pt2" >> Comm_MFCC_RDS.txt
java Comm_sp_rds_ceti pt2 MFCC RDS 10 >> Comm_MFCC_RDS.txt
echo "--------replace" >> Comm_MFCC_RDS.txt
java Comm_sp_rds_ceti replace MFCC RDS 10 >> Comm_MFCC_RDS.txt


