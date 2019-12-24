export CLASSPATH=$CLASSPATH:../javaTools

tcn=3000
for i in {1..10..1}
do
    #java RandomTCTcas $tcn tcasT$i.sh
    #java RandomTCPT $tcn ptT$i.sh
    #java RandomTCReplace $tcn replaceT$i.sh
    java RandomGenerator_schedule2 schedule2T$i.sh $tcn 
    java RandomGenerator_schedule scheduleT$i.sh $tcn 
done
