#!/usr/bin/env bash

echo "Compiling Java..."
rm -rf "WEB-INF/classes"
mkdir "WEB-INF/classes"
javac -source 1.5 -target 1.5 -classpath "WEB-INF/lib" -extdirs "WEB-INF/lib" -sourcepath "WEB-INF/src" -d "WEB-INF/classes" $(find WEB-INF/src -name *.java)
if [ $? != 0 ]; then
	echo "Java compile fail."
	exit 1
fi

echo "Creating WAR package..."
rm -rf target
mkdir target
jar cvf target/project.war .
if [ $? != 0 ]; then
	echo "Couldn't create WAR package."
	exit 1
fi

echo ""
echo ""
echo "Deploying to Heroku..."
heroku deploy:war --war target/project.war
