v0=$1 # the correct executable
vr=$2
ets=$3 #the evaluation test suite, .sh
n=$4


rm -r output0
rm -r outputr
mkdir output0
mkdir outputr

/bin/sh $ets $v0 output0
/bin/sh $ets $vr outputr

i=1
pass=0
#fail=0
while [ $i -le $n ]
do
  dex=$(diff output0/O$i outputr/O$i)
  if [[ -z $dex ]] # dex is null
  then
    pass=$((pass+1))
  #else
  #  fail=$((fail+1))
  fi
  i=$((i+1))
done
echo $pass
echo $fail
