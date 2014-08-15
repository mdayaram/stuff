#!/usr/bin/env bash

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
