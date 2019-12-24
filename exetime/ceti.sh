
ts=mf
        rm ceti_$ts.txt
        rm ceti_$ts.record.txt
        prog=tcas
           echo "-------------------------"$prog >> ceti_$ts.txt
           echo "-------------------------"$prog >> ceti_$ts.record.txt
           ########################
           tar=../repairs/CETI/$prog/$ts/1repairs
           for folder in $tar/*
           do
             if [ -e $folder/time.txt ]
             then
               time=$(cat $folder/time.txt )
             echo $folder
             echo $time >> ceti_$ts.txt
             fi
           done
           ########################
	
	for prog in {pt2,schedule,schedule2,replace}
	do
           echo "-------------------------"$prog >> ceti_$ts.txt
           echo "-------------------------"$prog >> ceti_$ts.record.txt
           ########################
           tar=../repairs/CETI/$prog/$ts/1repairs
           for folder in $tar/*
           do
             result=$folder/result
             echo $result >> ceti_$ts.record.txt 
             time=$(java CollectTimeCETI $result)

             echo $time >> ceti_$ts.txt
           done
           ########################
	done

ts=cov
        rm ceti_$ts.txt
        rm ceti_$ts.record.txt
	for prog in {tcas,pt2,schedule,schedule2,replace}
	do
           echo "-------------------------"$prog >> ceti_$ts.txt
           echo "-------------------------"$prog >> ceti_$ts.record.txt
           ########################
           tar=../repairs/CETI/$prog/$ts/1repairs
           for folder in $tar/*
           do
             result=$folder/result
             echo $result >> ceti_$ts.record.txt 
             time=$(java CollectTimeCETI $result)

             echo $time >> ceti_$ts.txt
           done
           done
           ########################

ts=rds
        rm ceti_$ts.txt
        rm ceti_$ts.record.txt
	for prog in {tcas,pt2,schedule,schedule2,replace}
	do
           echo "-------------------------"$prog >> ceti_$ts.txt
           echo "-------------------------"$prog >> ceti_$ts.record.txt
           ########################
           tar=../repairs/CETI/$prog/$ts/1repairs
           for folder in $tar/*
           do
             for i in {1..10..1}
             do
             result=$folder/result$i
             if [ -e $result ]
             then
             echo $result >> ceti_$ts.record.txt 
             time=$(java CollectTimeCETI $result)

             echo $time >> ceti_$ts.txt
             fi
             done
           done
           ########################
	done


