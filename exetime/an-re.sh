ts=mf
	
	   prog=replace
           echo "-------------------------"$prog >> ang_$ts.txt
           echo "-------------------------"$prog >> ang_$ts.record.txt
           ########################
           tar=../repairs/semfix/$prog/$ts/1repairs
           for folder in $tar/*
           do
             result=$folder/log3.txt
             echo $result >> ang_$ts.record.txt 
             time=$(java CollectTimeAngelix $result)

             echo $time >> ang_$ts.txt
           done
           ########################

ts=cov
	
	   prog=replace
           echo "-------------------------"$prog >> ang_$ts.txt
           echo "-------------------------"$prog >> ang_$ts.record.txt
           ########################
           tar=../repairs/semfix/$prog/$ts/1repairs
           for folder in $tar/*
           do
             result=$folder/log3.txt
             echo $result >> ang_$ts.record.txt 
             time=$(java CollectTimeAngelix $result)
             echo $time  >> ang_$ts.txt
           done
           ########################
	

