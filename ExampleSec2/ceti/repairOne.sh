path=$1
version=$2
cilly $path/m$version.c --save-temps=$path --noPrintLn --useLogicalOperators
rm -rf /tmp/cece_ &>t
echo "**Begin v$version**"
time time ./tf $path/m$version.cil.i $path/exe.inputs $path/exe.outputs --no_parallel --top_n_ssids 80 --min_sscore 0.01
echo "**Done v$version**"
