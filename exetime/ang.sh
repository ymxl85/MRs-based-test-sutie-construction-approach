ts=mf
	rm ang_$ts.txt
        rm ang_$ts.record.txt
	for prog in {tcas,pt2,schedule,schedule2}
	do
           echo "-------------------------"$prog >> ang_$ts.txt
           echo "-------------------------"$prog >> ang_$ts.record.txt
           ########################
           tar=../repairs/semfix/$prog/$ts/1repairs
           for folder in $tar/*
           do
             result=$folder/log.txt
             echo $result >> ang_$ts.record.txt 
             time=$(java CollectTimeAngelix $result)

             echo $time  >> ang_$ts.txt
           done
           ########################
	done

ts=cov
        rm ang_$ts.txt
        rm ang_$ts.record.txt
	for prog in {tcas,pt2,schedule,schedule2}
	do
           echo "-------------------------"$prog >> ang_$ts.txt
           echo "-------------------------"$prog >> ang_$ts.record.txt
           ########################
           tar=../repairs/semfix/$prog/$ts/1repairs
           for folder in $tar/*
           do
             result=$folder/log.txt
             echo $result >> ang_$ts.record.txt 
             time=$(java CollectTimeAngelix $result)

             echo $time  >> ang_$ts.txt
           done
           done
           ########################


ts=rds
        rm ang_$ts.txt
        rm ang_$ts.record.txt
	for prog in {tcas,pt2,schedule,schedule2,replace}
	do
           echo "-------------------------"$prog >> ang_$ts.txt
           echo "-------------------------"$prog >> ang_$ts.record.txt
           ########################
           tar=../repairs/semfix/$prog/$ts/1repairs
           for folder in $tar/*
           do
             for i in {1..10..1}
             do
             result=$folder/log$i.txt
             if [ -e $result ]
             then
             echo $result
             echo $result >> ang_$ts.record.txt 
             time=$(java CollectTimeAngelix $result)

             echo $time >> ang_$ts.txt
             fi
             done
           done
           ########################
	done


