#!/bin/bash

#BASE_CLASSES_DIR=$1
BASE_CLASSES_DIR=/home/doronin/VAMDC/workspace/VAMDC-XSAMS/trunk/target/generated-sources/xjc/org/vamdc/xsams/schema


#SCHEMA_DIR=$2
SCHEMA_DIR=/home/doronin/VAMDC/xsams/svn-sf/tags/vamdc/release-0.3/

PACKAGES=packages.lst

TEMPLATE=template.java

#SRCBASE=/home/doronin/VAMDC/workspace/VAMDC-XSAMS-extra/trunk/src/main/java
SRCBASE=/tmp/java


for BASECLASS in `ls $BASE_CLASSES_DIR`
do
	CLASS=`echo $BASECLASS | sed -e 's/\..*//g' `
	SCH=`grep -il "name=\"$CLASS\"" $SCHEMA_DIR/*`
	for FLE in $SCH
	do
		if [ -n $FLE ] && [ -f $FLE ];
		then
			FLE=`basename $FLE .xsd`
			PKG=`grep -P "^$FLE " packages.lst | sed -e 's/.* //g'`
			
			if [ -n "$PKG" ];
			then
				
				echo $CLASS $FLE $PKG;
				FPATH=$SRCBASE/`echo $PKG | sed -e 's/\./\//g'`
				#Create template class
				cat template.java | sed -e "s/\[PACKAGE\]/$PKG/g" | sed -e "s/\[CLASSNAME\]/$CLASS/g">$CLASS.java
				mkdir -p $FPATH
				mv -iv $CLASS.java $FPATH
			fi
			
		fi
	done
done
