#!/bin/bash

adb backup -f backup.ab -all
echo password must be 'password'
java -jar abp.jar -debug unpack backup.ab backup.tar password
tar xzvf backup.tar
