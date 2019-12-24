path=T2
cilly $path/updown.c --save-temps=$path --noPrintLn --useLogicalOperators
rm -rf /tmp/cece_ &>t

time time ./tf $path/updown.cil.i $path/exe.inputs $path/exe.outputs --no_parallel --top_n_ssids 80 --min_sscore 0.01
