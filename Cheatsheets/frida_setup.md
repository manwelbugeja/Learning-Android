# Frida Setup
Download the frida server for android, extract it and rename it
```
wget https://github.com/frida/frida/releases/download/15.1.14/frida-server-15.1.14-android-x86.xz
unxz frida-server-15.1.14-android-x86.xz
mv frida-server-15.1.14-android-x86 frida-server
```

Push it to Android and run it
```
adb root
adb push frida-server /data/local/tmp
```

Run it on Android
```
adb shell chmod +x /data/local/tmp/frida-server
adb shell /data/local/tmp/frida-server &
```

Download frida cient on macos
```
pip3 install frida
```

Port forward
```
adb forward tcp:27042 tcp:27042
adb forward tcp:27043 tcp:27043
```

Test setup
```
frida-ps -R
```

