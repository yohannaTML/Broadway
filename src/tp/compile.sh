#!/bin/sh

         rm -r logger/bin
         mkdir logger/bin
         javac -d logger/bin $(find logger/src -name *.java)

         rm -r restaurant/bin
         mkdir restaurant/bin
         javac -cp logger/bin -d restaurant/bin $(find restaurant/src -name *.java)

         rm -r testframework/bin
         mkdir testframework/bin
         javac -cp logger/bin:testframework/bin -d restaurant/bin $(find testframework/src -name *.java)
