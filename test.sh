
export CLASSPATH=.
export CLASSPATH=$CLASSPATH:jars/*
export CLASSPATH=$CLASSPATH:build/classes/main
export CLASSPATH=$CLASSPATH:build/classes/test

scala -cp $CLASSPATH specs2.run $1

