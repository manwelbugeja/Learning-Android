#!/bin/bash
echo if superSU cannot update su, download the playstore version
adb root
adb remount
git clone https://github.com/0xFireball/root_avd
adb install root_avd/SuperSU/common/Superuser.apk
adb push root_avd/SuperSU/x86/su /system/bin/su
adb shell chmod 06755 /system/bin/su
adb shell /system/bin/su --install
adb shell /system/bin/su --daemon&
adb shell setenforce 0

